package org.example;

import feign.Response;
import org.example.dto.ExchangeRateDTO;
import org.example.dto.ExchangeRatesResponse;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
        System.out.println(response.exchangeRate.stream().map(ExchangeRateDTO::toString).collect(Collectors.joining("\n")));
    }

    @Test
    public void getError() {
        Response response = client.getError();
        System.out.println(response.status());
    }
}
