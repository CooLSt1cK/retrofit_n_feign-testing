package org.example_feign;

import org.example_feign.feign.PrivatBankApiClient;
import org.example_feign.dto.ExchangeRateDTO;
import org.example_feign.dto.ExchangeRatesResponse;
import org.example_feign.dto.ExchangeTwoCurrencyDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@EnableFeignClients
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class TestPrivatBankAPI {

    @Autowired
    public PrivatBankApiClient client;

    @BeforeEach
    public void setup() {
        /*client = Feign.builder()
                .contract(new SpringMvcContract())
                ls.decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .target(PrivatBankApiClient.class, BASE_URI);
        /**/
    }

    @Test
    public void getExchangeRates() {
        ExchangeRatesResponse response = client.getExchangeRatesPBAndNB("01.12.2014");
        System.out.println(response);
        response.exchangeRate.stream()
                .map(ExchangeRateDTO::toString)
                .forEach(Assert::assertNotNull);
    }

    @Test
    public void getError() {
        ExchangeRatesResponse response = client.getError();
    }

    @Test
    public void getCurrentExchange() {
        List<ExchangeTwoCurrencyDTO> response = client.getExchangeCurrent(11);
        System.out.println(response.stream()
                .map(ExchangeTwoCurrencyDTO::toString)
                .collect(Collectors.joining("\n")));
        response.stream()
                .map(ExchangeTwoCurrencyDTO::toString)
                .forEach(Assert::assertNotNull);
    }
}
