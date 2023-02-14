package org.example_reactive_feign;


import feign.Headers;
import org.example_feign.dto.ExchangeRatesResponse;
import org.example_feign.dto.ExchangeTwoCurrencyDTO;
import org.example_reactive_feign.config.PrivatBankApiReactiveConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import rx.Observable;

import java.util.List;

@ReactiveFeignClient(value = "privat-bank-api-reactive", url = "https://api.privatbank.ua", configuration = PrivatBankApiReactiveConfig.class)
@Headers({"Accept: application/json"})
public interface PrivatBankApiReactiveClient {

    @GetMapping("/p24api/exchange_rates")
    Flux<ExchangeRatesResponse> getExchangeRatesPBAndNB(@RequestParam("date") String date);

    @GetMapping("/p24api/exchange_rates")
    Flux<ExchangeRatesResponse> getError();

    @GetMapping(value = "/p24api/pubinfo")
    Flux<List<ExchangeTwoCurrencyDTO>> getExchangeCurrent(@RequestParam("coursid") Integer courseId);
}
