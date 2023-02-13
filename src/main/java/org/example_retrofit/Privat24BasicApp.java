package org.example_retrofit;

import org.dto.ExchangeRateDTO;
import org.dto.ExchangeRatesResponse;

import java.util.stream.Collectors;

public class Privat24BasicApp {

    public static void main(String[] args) {
        ExchangeRatesResponse response = new Privat24BasicService().getCurrencies();

        System.out.println(response.exchangeRate.stream()
                .map(ExchangeRateDTO::toString)
                .collect(Collectors.joining("\n")));
    }
}
