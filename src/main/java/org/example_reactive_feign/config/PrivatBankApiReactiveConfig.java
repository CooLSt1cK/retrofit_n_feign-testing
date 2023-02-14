package org.example_reactive_feign.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactivefeign.spring.config.ReactiveFeignClientsConfiguration;

@Configuration
public class PrivatBankApiReactiveConfig extends ReactiveFeignClientsConfiguration {
        @Bean
        WebClient.Builder webClient() {
            return WebClient.builder();
        }
}
