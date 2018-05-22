package com.tgt.myretail.service;

import com.tgt.myretail.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCompositeServiceImpl implements ProductCompositeService {

    @Autowired
    private ProductService productService;

    @Override
    public ProductDto fetchProductById(String productId) {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(productId);
        productService.fetchProductById(productDto);
        productService.fetchPriceDetails(productDto);
        return productDto;
    }


}
