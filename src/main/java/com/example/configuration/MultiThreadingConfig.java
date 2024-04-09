package com.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class MultiThreadingConfig {
    @Bean
    public ExecutorService getEecutorService() {
        return Executors.newCachedThreadPool();
    }
}
