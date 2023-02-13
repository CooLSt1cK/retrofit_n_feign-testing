package org.example_retrofit;

import org.example.dto.ExchangeRateDTO;
import org.example.dto.ExchangeRatesResponse;
import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.stream.Collectors;

public class TestPrivatRetrofitAPI {

    private BasicApi basicApi;

    @Test
    public void getExchangeRates() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.privatbank.ua").addConverterFactory(JacksonConverterFactory.create()).build();
        ExchangeRatesResponse response;

        basicApi = retrofit.create(BasicApi.class);

        try {
            response = basicApi.getExchangeRates("01.12.2014").execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(response.exchangeRate.stream().map(ExchangeRateDTO::toString).collect(Collectors.joining("\n")));
    }
}
