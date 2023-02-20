package org.example_retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.example_feign.dto.ExchangeRateDTO;
import org.example_feign.dto.ExchangeRatesResponse;
import org.example_retrofit.utils.ErrorHandlingCallAdapterFactory;
import org.example_retrofit.utils.MyCall;
import org.example_retrofit.utils.MyCallback;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestPrivatRetrofitErrorHandling {

    private MyCall<ExchangeRatesResponse> call;
    private BasicApi service;

    @Before
    public void init() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl("https://api.privatbank.ua")
                        .client(client)
                        .addCallAdapterFactory(new ErrorHandlingCallAdapterFactory())
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();

        service = retrofit.create(BasicApi.class);
    }

    @Test
    public void checkValidSuccesfullResponse() {
        call = service.getExchangeRatesTest("01.12.2014");

        call.enqueue(
                new MyCallback<ExchangeRatesResponse>() {
                    @Override
                    public void success(Response<ExchangeRatesResponse> response) {
                        response.body().exchangeRate.stream()
                                .map(ExchangeRateDTO::toString)
                                .forEach(Assert::assertNotNull);
                    }

                    @Override
                    public void unauthenticated(Response<?> response) {
                        throw new AssertionError();
                    }

                    @Override
                    public void clientError(Response<?> response) {
                        throw new AssertionError();
                    }

                    @Override
                    public void serverError(Response<?> response) {
                        throw new AssertionError();
                    }

                    @Override
                    public void networkError(IOException e) {
                        throw new AssertionError();
                    }

                    @Override
                    public void unexpectedError(Throwable t) {
                        throw new AssertionError();
                    }
                });
    }

    @Test
    public void checkServerUnavailable() {
        call = service.getExchangeRatesTest("01.12.2030");

        call.enqueue(
                new MyCallback<ExchangeRatesResponse>() {
                    @Override
                    public void success(Response<ExchangeRatesResponse> response) {
                        throw new AssertionError();
                    }

                    @Override
                    public void unauthenticated(Response<?> response) {
                        throw new AssertionError();
                    }

                    @Override
                    public void clientError(Response<?> response) {
                        throw new AssertionError();
                    }

                    @Override
                    public void serverError(Response<?> response) {
                        assertEquals(500, response.code());
                    }

                    @Override
                    public void networkError(IOException e) {
                        throw new AssertionError();
                    }

                    @Override
                    public void unexpectedError(Throwable t) {
                        throw new AssertionError();
                    }
                });
    }
}
