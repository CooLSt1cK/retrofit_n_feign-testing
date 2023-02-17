package org.example_retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.example_feign.dto.ExchangeRatesResponse;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

public class Privat24BasicService {

    private final BasicApi basicApi;

    Privat24BasicService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.privatbank.ua")
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create()).
                build();

        basicApi = retrofit.create(BasicApi.class);
    }

    public ExchangeRatesResponse getCurrencies() {
        ExchangeRatesResponse response;

        try {
            response = basicApi.getExchangeRates("01.12.2014").execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return response;
    }
}
