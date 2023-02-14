package org.example_feign.feign;


import feign.Headers;
import io.reactivex.Observable;
import org.example_feign.dto.ExchangeRatesResponse;
import org.example_feign.dto.ExchangeTwoCurrencyDTO;
import org.example_feign.config.PrivatBankApiReactiveConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactivefeign.spring.config.ReactiveFeignClient;

import java.util.List;

@ReactiveFeignClient(value = "privat-bank-api-reactive", url = "https://api.privatbank.ua", configuration = PrivatBankApiReactiveConfig.class)
@Headers({"Accept: application/json"})
public interface PrivatBankApiReactiveClient {

    @GetMapping("/p24api/exchange_rates")
    Observable<ExchangeRatesResponse> getExchangeRatesPBAndNB(@RequestParam("date") String date);

    @GetMapping("/p24api/exchange_rates")
    Observable<ExchangeRatesResponse> getError();

    @GetMapping(value = "/p24api/pubinfo")
    Observable<List<ExchangeTwoCurrencyDTO>> getExchangeCurrent(@RequestParam("coursid") Integer courseId);
}
