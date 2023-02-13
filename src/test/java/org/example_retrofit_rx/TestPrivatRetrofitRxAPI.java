package org.example_retrofit_rx;

import org.example_feign.dto.ExchangeRateDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * <p style="color: green; font-size: 1.5em">
 * Testing Privat24 exchange rates api using Retrofit framework and RXJava with asynchronous approach</p>
 */
public class TestPrivatRetrofitRxAPI {

    private BasicRxApi basicRxApi;

    @Before
    public void init() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.privatbank.ua")
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        basicRxApi = retrofit.create(BasicRxApi.class);
    }

    @Test
    public void vcheckThatExchangeRatesNotNull() {
        basicRxApi.getExchangeRates("01.12.2014")
                .subscribe(x -> x.exchangeRate.stream()
                        .map(ExchangeRateDTO::toString)
                        .forEach(Assert::assertNotNull));
    }
}
