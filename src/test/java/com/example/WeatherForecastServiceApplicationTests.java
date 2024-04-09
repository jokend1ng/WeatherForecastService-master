package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class WeatherForecastServiceApplicationTests {


    @Test
    void getTestApiWithController() {
        String apiResponce = RestAssured
                .get("http://api.openweathermap.org/data/2.5/forecast?q=Moscow&cnt=9&appid=928ebd8d3e967da51398fce48a97561c")
                .andReturn().asString();
        String controllerResponse = RestAssured
                .get("http://localhost:7777/weather-api/day")
                .andReturn().asString();
        assertTrue(apiResponce.equals(controllerResponse));
    }

    @Test
    void getTestWeatherForecastForToday() {
        Response response = RestAssured
                .get("http://localhost:7777/weather-api/day")
                .andReturn();
        response.prettyPeek();
    }

    @Test
    void getTestWeatherForecastForWeek() {
        Response response = RestAssured
                .get("http://localhost:7777/weather-api/week")
                .andReturn();
        response.prettyPeek();
    }

}
