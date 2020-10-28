package com.gang.alphaspoon.config;

import com.gang.alphaspoon.converters.CustomerConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConverterConfig {
    @Bean
    public CustomerConverter getCustomerConverter(){
        return new CustomerConverter();
    }
}
