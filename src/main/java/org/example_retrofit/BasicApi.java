package org.example_retrofit;

import org.example.dto.ExchangeRatesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BasicApi {

    @GET("/p24api/exchange_rates")
    Call<ExchangeRatesResponse> getExchangeRates(@Query("date") String date);
}
