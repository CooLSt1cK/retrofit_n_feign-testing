package org.example_retrofit_rx;

import org.dto.ExchangeRatesResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface BasicRxApi {

    @GET("/p24api/exchange_rates")
    Observable<ExchangeRatesResponse> getExchangeRates(@Query("date") String date);
}
