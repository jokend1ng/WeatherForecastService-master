package com.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
    В контексе содается бин для ExecutorService, чтобы использовать многопоточку
*/    
@Configuration
public class MultiThreadingConfig {
    @Bean
    public ExecutorService getEecutorService() {
        return Executors.newCachedThreadPool();
    }
}
