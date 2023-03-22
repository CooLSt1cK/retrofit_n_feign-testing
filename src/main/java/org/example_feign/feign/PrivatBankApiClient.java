package org.example_feign.feign;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;
import org.example_feign.config.PrivatBankApiConfig;
import org.example_feign.dto.ExchangeRatesResponse;
import org.example_feign.dto.ExchangeTwoCurrencyDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "privat-bank-api",
        url = "https://api.privatbank.ua",
        configuration = PrivatBankApiConfig.class)
@Headers({"Accept: application/json"})
public interface PrivatBankApiClient {

    //@RequestLine("GET /p24api/exchange_rates")
    //ExchangeRatesResponse getExchangeRatesWithoutSpring(@Param("date") String date);

    @GetMapping("/p24api/exchange_rates")
    ExchangeRatesResponse getExchangeRatesPBAndNB(@RequestParam("date") String date);

    @GetMapping("/p24api/exchange_rates")
    Response getResponse(@RequestParam("date") String date);

    @GetMapping(value = "/p24api/pubinfo")
    List<ExchangeTwoCurrencyDTO> getExchangeCurrent(@RequestParam("coursid") Integer courseId);

    @GetMapping("/p24api/exchange_rates")
    ExchangeRatesResponse getError();

}
