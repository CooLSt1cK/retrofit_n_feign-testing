package org.example_retrofit_rx;

import org.example.dto.ExchangeRateDTO;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class TestPrivatRetrofitRxAPI {

    private BasicRxApi basicRxApi;

    @Before
    public void init() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.privatbank.ua").addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(
                        RxJavaCallAdapterFactory.create()).build();

        basicRxApi = retrofit.create(BasicRxApi.class);
    }

    @Test
    public void getExchangeRates() {
        basicRxApi.getExchangeRates("01.12.2014")
                .subscribe(x -> x.exchangeRate.stream().map(ExchangeRateDTO::toString).forEach(System.out::println));
    }
}
