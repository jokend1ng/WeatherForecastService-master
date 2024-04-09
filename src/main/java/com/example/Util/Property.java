package com.example.Util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
/**
Класс для работы с переменными окружения прописаннными в application.yaml.
*/
@Component
public class Property {
    @Value("${defaultApi}")
    private String defaultApi;
    @Value("${apis}")
    private List<String> apis;
    @Value("${town}")
    private String town;

    public String getDefaultApi() {
        return defaultApi;
    }

    public List<String> getApis() {
        return apis;
    }

    public String getTown() {
        return town;
    }
}
