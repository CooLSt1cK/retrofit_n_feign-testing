package org.example;

import feign.Response;
import org.example.config.PrivateBankApiConfig;
import org.example.dto.ExchangeRatesResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "privat-bank-api", url = "https://api.privatbank.ua", configuration = PrivateBankApiConfig.class)
public interface PrivatBankApiClient {

    @GetMapping("/p24api/exchange_rates")
    ExchangeRatesResponse getExchangeRatesPBAndNB(@RequestParam("date") String date);

    @GetMapping("/p24api/exchange_rates")
    Response getError();

}
