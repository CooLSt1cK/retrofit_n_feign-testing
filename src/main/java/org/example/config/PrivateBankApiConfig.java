package org.example.config;

import feign.Logger;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p style="color: green; font-size: 1.5em">
 * Spring configuration</p>
 */
@Configuration
public class PrivateBankApiConfig extends FeignClientsConfiguration {

    @Override
    @Bean
    public Retryer feignRetryer(){
        return new MyRetryer();
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
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
