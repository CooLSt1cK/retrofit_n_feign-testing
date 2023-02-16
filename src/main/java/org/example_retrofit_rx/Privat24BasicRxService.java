package org.example_retrofit_rx;

import org.example_feign.dto.ExchangeRatesResponse;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import rx.Observable;

public class Privat24BasicRxService {

    private final BasicRxApi basicRxApi;

    Privat24BasicRxService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.privatbank.ua")
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        basicRxApi = retrofit.create(BasicRxApi.class);
    }

    public Observable<ExchangeRatesResponse> getExchangeRates() {
        return basicRxApi.getExchangeRates("01.12.2014");
    }
}
