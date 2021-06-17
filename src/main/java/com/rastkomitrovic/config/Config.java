package com.rastkomitrovic.config;

import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.text.SimpleDateFormat;


@Configuration
public class Config {

    @Bean
    @Primary
    public Jackson2ObjectMapperBuilder customObjectMapper() {
        return new Jackson2ObjectMapperBuilder()
                .modules(new JsonNullableModule())
                .dateFormat(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss"));
    }
}
