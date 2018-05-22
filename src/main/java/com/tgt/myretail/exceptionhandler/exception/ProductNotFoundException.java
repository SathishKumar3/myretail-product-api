package com.tgt.myretail.exceptionhandler.exception;


import com.tgt.myretail.exceptionhandler.BusinessException;

/**
 * ProductNotFoundException- When product not found from rest service and database for the product
 */


public class ProductNotFoundException extends BusinessException {

    private String productId;

    /**
     * Handles When product not found from rest service and database
     * @param productId
     */
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
