package com.example.service;

import com.example.Util.Property;
import com.example.model.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
/** 
    Фабрика для создания модели API  чтобы в дальнейшем использовать ее при 
    создании строки подключения
*/    

@Service
public class ApiFactory {

    @Autowired
    private Environment environment;
    private final Property properties;

    public ApiFactory(Property properties) {
        this.properties = properties;
    }

    public ApiModel create(String type) {
        return ApiModel.of(environment.getProperty(type + "." + "requestUrl"),
                environment.getProperty(type + "." + "params.key.name"),
                environment.getProperty(type + "." + "params.key.value"),
                environment.getProperty(type + "." + "params.city.name"),
                properties.getTown(),
                environment.getProperty(type + "." + "params.days.name"),
                environment.getProperty(type + "." + "params.days.value"));
    }
}
