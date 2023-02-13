package org.example_feign;

import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import org.example_feign.dto.ExchangeRateDTO;
import org.example_feign.dto.ExchangeRatesResponse;
import org.example_feign.dto.ExchangeTwoCurrencyDTO;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@EnableFeignClients
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class TestPrivatBankAPI {

    @Autowired
    public PrivatBankApiClient client;
    public Decoder jsonDecoder = new JacksonDecoder();

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
        System.out.println(response.exchangeRate.stream().map(ExchangeRateDTO::toString).collect(Collectors.joining("\n")));
    }

    @Test
    public void getError() {
        ExchangeRatesResponse response = client.getError();
        System.out.println();
    }

    @Test
    public void getCurrentExchange() {
        List<ExchangeTwoCurrencyDTO> response = client.getExchangeCurrent(11);
        System.out.println(response.stream().map(ExchangeTwoCurrencyDTO::toString).collect(Collectors.joining("\n")));
    }
}