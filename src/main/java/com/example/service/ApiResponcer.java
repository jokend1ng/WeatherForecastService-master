package com.example.service;

import com.example.model.ApiModel;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ApiResponcer {

    private final Logger logger = LoggerFactory.getLogger(ApiResponcer.class);

    public String getResponse(String api) {
        String resultContent = null;
        HttpGet httpGet = new HttpGet(api);
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
                HttpEntity entity = response.getEntity();
                resultContent = (EntityUtils.toString(entity));
            } catch (ParseException e) {
                logger.error(e.getMessage());
            }

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return resultContent;
    }

    public String getWeatherFromApi(ApiModel api) {
        System.out.println(String.format("%s%s=%s&%s=%s&%s=%s", api.getRequestUrl(), api.getCityName(),
                api.getCityValue(), api.getDaysName(), api.getDaysValue(), api.getKeyName(), api.getKeyValue()));
        return getResponse(String.format("%s%s=%s&%s=%s&%s=%s", api.getRequestUrl(), api.getCityName(),
                api.getCityValue(), api.getDaysName(), api.getDaysValue(), api.getKeyName(), api.getKeyValue()));
    }
}
