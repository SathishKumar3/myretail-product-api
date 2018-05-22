package com.tgt.myretail.dao;

import com.tgt.myretail.dto.ProductDto;
import com.tgt.myretail.request.ProductRequest;

public interface ProductDao {

    public ProductDto findByProductId(ProductDto productDto);

    public boolean updatePriceByProductId(ProductRequest productRequest);
}
