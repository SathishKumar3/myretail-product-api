package com.tgt.myretail.dto;

public class ProductDto {

    private String productId;

    private String name;

    private PriceInfoDto priceDetails;

    public ProductDto() {

    }

    public ProductDto(String productId, String name, PriceInfoDto priceInfoDto) {
        this.productId = productId;
        this.name = name;
        this.priceDetails = priceInfoDto;
    }

    public PriceInfoDto getPriceDetails() {

        return priceDetails;
    }

    public void setPriceDetails(PriceInfoDto priceDetails) {
        this.priceDetails = priceDetails;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
