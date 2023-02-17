package org.example_retrofit_rx;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.example_feign.dto.ExchangeRateDTO;
import org.example_feign.dto.ExchangeRatesResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import rx.observers.TestSubscriber;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p style="color: green; font-size: 1.5em">
 * Testing Privat24 exchange rates api using Retrofit framework and RXJava with asynchronous approach</p>
 */
public class TestPrivatRetrofitRxAPI {

    private BasicRxApi basicRxApi;

    @Before
    public void init() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.privatbank.ua")
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        basicRxApi = retrofit.create(BasicRxApi.class);
    }

    @Test
    public void checkThatExchangeRatesNotNull() {
        TestSubscriber<ExchangeRatesResponse> subscriber = new TestSubscriber<>();
        basicRxApi.getExchangeRates("01.12.2014")
                .subscribe(subscriber);
        subscriber.awaitValueCount(1, 20, TimeUnit.SECONDS);
        subscriber.assertCompleted();
        subscriber.assertNoErrors();
        List<String> exchangeRates = subscriber.getOnNextEvents().get(0).exchangeRate.stream()
                .map(ExchangeRateDTO::toString).toList();
        exchangeRates.forEach(System.out::println);
        exchangeRates.forEach(Assert::assertNotNull);
    }
}
