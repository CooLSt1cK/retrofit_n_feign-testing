package org.example_feign.dto;

import java.util.Objects;

public class ExchangeRateDTO {

    private String baseCurrency;
    private String currency;
    private Double saleRateNB;
    private Double purchaseRateNB;

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getSaleRateNB() {
        return saleRateNB;
    }

    public void setSaleRateNB(Double saleRateNB) {
        this.saleRateNB = saleRateNB;
    }

    public Double getPurchaseRateNB() {
        return purchaseRateNB;
    }

    public void setPurchaseRateNB(Double purchaseRateNB) {
        this.purchaseRateNB = purchaseRateNB;
    }

    public Double getSaleRate() {
        return saleRate;
    }

    public void setSaleRate(Double saleRate) {
        this.saleRate = saleRate;
    }

    public Double getPurchaseRate() {
        return purchaseRate;
    }

    public void setPurchaseRate(Double purchaseRate) {
        this.purchaseRate = purchaseRate;
    }

    private Double saleRate;
    private Double purchaseRate;

    @Override
    public String toString() {
        return "ExchangeRateDTO{" +
                "baseCurrency='" + baseCurrency + '\'' +
                ", currency='" + currency + '\'' +
                ", saleRateNB=" + saleRateNB +
                ", purchaseRateNB=" + purchaseRateNB +
                ", saleRate=" + saleRate +
                ", purchaseRate=" + purchaseRate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ExchangeRateDTO that = (ExchangeRateDTO) o;

        if (!Objects.equals(baseCurrency, that.baseCurrency)) {
            return false;
        }
        if (!Objects.equals(currency, that.currency)) {
            return false;
        }
        if (!Objects.equals(saleRateNB, that.saleRateNB)) {
            return false;
        }
        if (!Objects.equals(purchaseRateNB, that.purchaseRateNB)) {
            return false;
        }
        if (!Objects.equals(saleRate, that.saleRate)) {
            return false;
        }
        return Objects.equals(purchaseRate, that.purchaseRate);
    }

    @Override
    public int hashCode() {
        int result = baseCurrency != null ? baseCurrency.hashCode() : 0;
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (saleRateNB != null ? saleRateNB.hashCode() : 0);
        result = 31 * result + (purchaseRateNB != null ? purchaseRateNB.hashCode() : 0);
        result = 31 * result + (saleRate != null ? saleRate.hashCode() : 0);
        result = 31 * result + (purchaseRate != null ? purchaseRate.hashCode() : 0);
        return result;
    }
}
