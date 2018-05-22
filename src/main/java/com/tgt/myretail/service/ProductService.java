package com.tgt.myretail.service;

import com.tgt.myretail.dto.GenericResponse;
import com.tgt.myretail.dto.ProductDto;
import com.tgt.myretail.exceptionhandler.exception.ProductNotFoundException;
import com.tgt.myretail.request.ProductRequest;

public interface ProductService {

    public ProductDto fetchProductById(ProductDto productDto) throws ProductNotFoundException;

    public ProductDto fetchPriceDetails(ProductDto productDto) throws ProductNotFoundException;

    GenericResponse updateProductPrice(ProductRequest productRequest);
}
