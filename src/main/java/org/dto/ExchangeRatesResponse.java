package org.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * <p style="color: green; font-size: 1.5em">
 * Exchange rate Response</p>
 */
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
