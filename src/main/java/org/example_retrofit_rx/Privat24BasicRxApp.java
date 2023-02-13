package org.example_retrofit_rx;

import org.example_feign.dto.ExchangeRateDTO;

public class Privat24BasicRxApp {

    public static void main(String[] args) {
        new Privat24BasicRxService()
                .getExchangeRates().subscribe(x -> x.exchangeRate.stream()
                        .map(ExchangeRateDTO::toString)
                        .forEach(System.out::println));
    }
}
