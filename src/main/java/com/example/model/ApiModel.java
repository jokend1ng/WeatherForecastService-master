package com.example.model;

public class ApiModel {

    private String requestUrl;
    private String keyName;
    private String keyValue;
    private String cityName;
    private String cityValue;
    private String daysName;
    private String daysValue;

    private ApiModel(String requestUrl, String keyName, String keyValue, String cityName, String cityValue,
                     String daysName, String daysValue) {
        this.requestUrl = requestUrl;
        this.keyName = keyName;
        this.keyValue = keyValue;
        this.cityName = cityName;
        this.daysName = daysName;
        this.daysValue = daysValue;
        this.cityValue = cityValue;
    }

    public static ApiModel of(String requestUrl, String keyName, String keyValue, String cityName,
                              String cityValue, String daysName, String daysValue) {
        return new ApiModel(requestUrl, keyName, keyValue, cityName, cityValue, daysName, daysValue);
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public String getKeyName() {
        return keyName;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public String getCityName() {
        return cityName;
    }

    public String getDaysName() {
        return daysName;
    }

    public String getCityValue() {
        return cityValue;
    }

    public String getDaysValue() {
        return daysValue;
    }

    public void setCityValue(String cityValue) {
        this.cityValue = cityValue;
    }

    public void setDaysValue(String daysValue) {
        this.daysValue = daysValue;
    }
}
