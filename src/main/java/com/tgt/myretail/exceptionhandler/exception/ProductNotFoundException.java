package com.tgt.myretail.exceptionhandler.exception;


import com.tgt.myretail.exceptionhandler.BusinessException;


public class ProductNotFoundException extends BusinessException {

    private String productId;

    public ProductNotFoundException(String productId) {
        super();
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
