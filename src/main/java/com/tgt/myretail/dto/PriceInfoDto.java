package com.tgt.myretail.dto;

import java.math.BigDecimal;

public class PriceInfoDto {


    private BigDecimal price;
    private String currencyCode;

    public PriceInfoDto() {

    }

    public PriceInfoDto(BigDecimal price, String currencyCode) {
        this.price = price;
        this.currencyCode = currencyCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

}
