package com.board.gd.config;

import com.board.gd.iamport.IamportAuthInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * Created by godong9 on 2017. 6. 4..
 */

@Configuration
public class RestTemplateConfig {
    @Bean
    @Autowired
    public RestTemplate restTemplate(ObjectMapper jacksonObjectMapper) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().removeIf(converter -> converter instanceof MappingJackson2HttpMessageConverter);
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter(jacksonObjectMapper));
        return restTemplate;
    }

    @Bean
    @Autowired
    public RestTemplate iamportRestTemplate(IamportAuthInterceptor iamportAuthInterceptor, ObjectMapper jacksonObjectMapper) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(iamportAuthInterceptor));
        restTemplate.getMessageConverters().removeIf(converter -> converter instanceof MappingJackson2HttpMessageConverter);
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter(jacksonObjectMapper));
        restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
        return restTemplate;
    }
}
