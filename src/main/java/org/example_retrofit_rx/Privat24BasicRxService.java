package org.example_retrofit_rx;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.example_feign.dto.ExchangeRatesResponse;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import rx.Observable;

public class Privat24BasicRxService {

    private final BasicRxApi basicRxApi;

    Privat24BasicRxService() {
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

    public Observable<ExchangeRatesResponse> getExchangeRates() {
        return basicRxApi.getExchangeRates("01.12.2014");
    }
}
