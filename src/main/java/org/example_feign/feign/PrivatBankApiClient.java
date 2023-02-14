package org.example_feign.feign;

import feign.Headers;
import org.example_feign.config.PrivatBankApiConfig;
import org.example_feign.dto.ExchangeRatesResponse;
import org.example_feign.dto.ExchangeTwoCurrencyDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "privat-bank-api", url = "https://api.privatbank.ua", configuration = PrivatBankApiConfig.class)
@Headers({"Accept: application/json"})
public interface PrivatBankApiClient {

    @GetMapping("/p24api/exchange_rates")
    ExchangeRatesResponse getExchangeRatesPBAndNB(@RequestParam("date") String date);

    @GetMapping("/p24api/exchange_rates")
    ExchangeRatesResponse getError();

    @GetMapping(value = "/p24api/pubinfo")
    List<ExchangeTwoCurrencyDTO> getExchangeCurrent(@RequestParam("coursid") Integer courseId);
}
