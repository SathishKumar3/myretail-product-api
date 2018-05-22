package com.tgt.myretail.request;


import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


public class ProductRequest {

    @NotEmpty(message = "Product Id should not be empty")
    private String productId;

    @DecimalMin(value = "0.1", inclusive = false, message = "Price value should be greater than 0.1")
    @NotNull(message = "Price value should not be empty")
    @Digits(integer = 6, fraction = 2, message = "Price value should be in the range of xxxxxx.xx")
    private BigDecimal price;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
