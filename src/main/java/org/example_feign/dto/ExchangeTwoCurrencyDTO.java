package org.example_feign.dto;

public class ExchangeTwoCurrencyDTO {

    public String ccy;
    public String base_ccy;
    public Double buy;
    public Double sell;

    @Override
    public String toString() {
        return "ExchangeTwoCurrencyDTO{" +
                "ccy='" + ccy + '\'' +
                ", base_ccy='" + base_ccy + '\'' +
                ", buy=" + buy +
                ", sell=" + sell +
                '}';
    }
}
