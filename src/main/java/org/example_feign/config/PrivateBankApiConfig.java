package org.example_feign.config;

import feign.Logger;
import feign.Logger.Level;
import feign.Response;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrivateBankApiConfig extends FeignClientsConfiguration {

    @Bean
    public Retryer feignRetryer() {
        return new MyRetryer(6, 1000L);
    }

    @Bean
    public ErrorDecoder feignErrorDecoder(){
        return new MyErrorDecoder();
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Level.FULL;
    }

    @Bean
    public Decoder feignDecoder() {
        return new JacksonDecoder();
    }

    @Bean
    public Encoder feignEncoder() {
        return new JacksonEncoder();
    }

}