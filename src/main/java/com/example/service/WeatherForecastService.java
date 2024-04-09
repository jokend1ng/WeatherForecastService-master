package com.example.service;

import com.example.Util.Property;
import com.example.model.ApiModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
/**
    Сервис приложения, переводит строку  в jsonNode, запускает многопоточное полуения данных со сторонних сервисов
*/  
@Service
public class WeatherForecastService {
    private final Logger logger = LoggerFactory.getLogger(WeatherForecastService.class);
    private final Property property;
    private final ObjectMapper mapper;
    private final ApiFactory apiFactory;
    private final ApiResponcer apiResponcer;
    private final ExecutorService executorService;

    @Autowired
    public WeatherForecastService(Property property, ObjectMapper mapper, ApiFactory apiFactory, ApiResponcer apiResponcer, ExecutorService executorService) {
        this.property = property;
        this.mapper = mapper;
        this.apiFactory = apiFactory;
        this.apiResponcer = apiResponcer;
        this.executorService = executorService;
    }

    public JsonNode getWeatherForToday(String city) {
        return getWeatherForDays(city, "1");
    }

    public JsonNode getWeatherForWeek(String city) {
        return getWeatherForDays(city, "7");
    }
    /*получение из списка JsonNode*/
    private JsonNode getWeatherForDays(String city, String days) {
        String result = "all".equals(property.getDefaultApi()) ?
                getInformationFromAllApis(city, days).toString() :
                getInformationFromApi(property.getDefaultApi(), city, days);
        JsonNode node = null;
        try {
            node = mapper.readTree(result);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }
        return node;
    }
    /* Создаеся Модель API, отправляется на ApiResponcer для получения данных. */
   
    private String getInformationFromApi(String api, String city, String days) {
        ApiModel apiModel = apiFactory.create(api);
        if (city != null) {
            apiModel.setCityValue(city);
        }
        apiModel.setDaysValue(String.valueOf(Integer.parseInt(days) * Integer.parseInt(apiModel.getDaysValue())));
        return apiResponcer.getWeatherFromApi(apiModel);
    }
/*Получение данных используется многопоточка и упаковка их в список*/
    private List<String> getInformationFromAllApis(String city, String days) {
        List<String> dataFromApis = new ArrayList<>();
        List<Future<String>> futures = new ArrayList<>();
        for (String apiModel : property.getApis()) {
            futures.add(executorService.submit(() -> {
                return getInformationFromApi(apiModel, city, days);
            }));
        }
        for (Future future : futures) {
            try {
                dataFromApis.add(String.valueOf(future.get()));
            } catch (InterruptedException | ExecutionException e) {
                logger.error(e.getMessage());
            }
        }
        return dataFromApis;
    }
}

