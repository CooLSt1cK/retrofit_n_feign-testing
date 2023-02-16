package org.example_feign.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ExchangeRatesResponse that = (ExchangeRatesResponse) o;

        if (!date.equals(that.date)) {
            return false;
        }
        if (!bank.equals(that.bank)) {
            return false;
        }
        if (!baseCurrency.equals(that.baseCurrency)) {
            return false;
        }
        if (!baseCurrencyLit.equals(that.baseCurrencyLit)) {
            return false;
        }
        return exchangeRate.equals(that.exchangeRate);
    }

    @Override
    public int hashCode() {
        int result = date.hashCode();
        result = 31 * result + bank.hashCode();
        result = 31 * result + baseCurrency.hashCode();
        result = 31 * result + baseCurrencyLit.hashCode();
        result = 31 * result + exchangeRate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ExchangeRatesResponse{" +
                "date='" + date + '\'' +
                ", bank='" + bank + '\'' +
                ", baseCurrency=" + baseCurrency +
                ", baseCurrencyLit='" + baseCurrencyLit + '\'' +
                ", exchangeRate=" + exchangeRate.stream()
                .map(ExchangeRateDTO::toString)
                .collect(Collectors.joining("\n")) +
                '}';
    }
}
