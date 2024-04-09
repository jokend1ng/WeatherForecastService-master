package com.example.controller;


import com.example.service.WeatherForecastService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("weather-api")
public class WeatherForecastController {
    private final WeatherForecastService weatherForecastService;
    @Autowired
    public WeatherForecastController(WeatherForecastService weatherForecastService) {
        this.weatherForecastService = weatherForecastService;
    }

    @GetMapping("/day")
    public JsonNode getWeatherForecastForToday(@RequestParam(required = false) String city) {
        return weatherForecastService.getWeatherForToday(city);
    }

    @GetMapping("/week")
    public JsonNode getWeatherForecastForWeek(@RequestParam(required = false) String city) {
        return weatherForecastService.getWeatherForWeek(city);
    }
}
