package com.tgt.myretail.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "products")
public class Product {

    @Id
    public String productId;
    @Transient
    public String productName;

    public BigDecimal price;


    /**
     * Default constructor.
     */
    public Product() {
        // TODO Auto-generated constructor stub
    }

    /**
     * parameterized constructor
     *
     * @param productId
     * @param productName
     * @param price
     */
    public Product(String productId, String productName, BigDecimal price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
