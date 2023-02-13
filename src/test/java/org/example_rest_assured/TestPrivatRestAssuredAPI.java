package org.example_rest_assured;

import io.restassured.RestAssured;
import org.dto.ExchangeRateDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

/**
 * <p style="color: green; font-size: 1.5em">
 * Testing Privat24 exchange rates api using Rest Assured framework</p>
 */
public class TestPrivatRestAssuredAPI {

    @Before
    public void setup() {
        RestAssured.baseURI = "https://api.privatbank.ua/p24api/exchange_rates?";
    }

    @Test
    public void checkThatExchangeRatesNotNull() {
        String date = "01.12.2014";

        List<ExchangeRateDTO> listDTO = given().get(baseURI + "date=" + date)
                .jsonPath()
                .getList("exchangeRate", ExchangeRateDTO.class);

        System.out.println(listDTO
                .stream()
                .map(ExchangeRateDTO::toString)
                .collect(Collectors.joining("\n")));

        listDTO.stream()
                .map(ExchangeRateDTO::toString)
                .forEach(Assert::assertNotNull);
    }
}
