package org.example_feign.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactivefeign.ReactiveFeignBuilder;
import reactivefeign.rx2.Rx2ReactiveFeign;
import reactivefeign.spring.config.ReactiveFeignClientsConfiguration;

@Configuration
public class PrivatBankApiReactiveConfig extends ReactiveFeignClientsConfiguration {

    @Bean
    WebClient.Builder webClient() {
        return WebClient.builder();
    }

    @Bean
    public ReactiveFeignBuilder reactiveFeignBuilder() {
        return Rx2ReactiveFeign.builder();
    }
}
