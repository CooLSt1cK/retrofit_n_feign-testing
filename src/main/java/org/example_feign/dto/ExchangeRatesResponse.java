package org.example_feign.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class ExchangeRatesResponse {

    @JsonFormat(pattern = "dd.MM.YYYY")
    public Date date;
    public String bank;
    public Integer baseCurrency;
    public String baseCurrencyLit;

    public List<ExchangeRateDTO> exchangeRate;

    @Override
    public String toString() {
        return "ExchangeRatesResponse{" +
                "date='" + date + '\'' +
                ", bank='" + bank + '\'' +
                ", baseCurrency=" + baseCurrency +
                ", baseCurrencyLit='" + baseCurrencyLit + '\'' +
                ", exchangeRate=" + exchangeRate +
                '}';
    }
}
