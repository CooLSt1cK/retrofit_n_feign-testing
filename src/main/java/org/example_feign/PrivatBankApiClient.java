package org.example_feign;

import feign.Body;
import feign.Headers;
import feign.Response;
import org.example_feign.config.PrivateBankApiConfig;
import org.example_feign.dto.ExchangeRatesResponse;
import org.example_feign.dto.ExchangeTwoCurrencyDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient(value = "privat-bank-api", url = "https://api.privatbank.ua", configuration = PrivateBankApiConfig.class)
@Headers({"Accept: application/json"})
public interface PrivatBankApiClient {

    @GetMapping("/p24api/exchange_rates")
    ExchangeRatesResponse getExchangeRatesPBAndNB(@RequestParam("date") String date);

    @GetMapping("/p24api/exchange_rates")
    ExchangeRatesResponse getError();

    @GetMapping(value = "/p24api/pubinfo")
    List<ExchangeTwoCurrencyDTO> getExchangeCurrent(@RequestParam("coursid") Integer courseId);
}
