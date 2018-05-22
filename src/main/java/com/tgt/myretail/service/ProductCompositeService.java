package com.tgt.myretail.service;


import com.tgt.myretail.dto.ProductDto;

public interface ProductCompositeService {

    public ProductDto fetchProductById(String productId);
}
