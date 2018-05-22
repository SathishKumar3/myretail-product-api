package com.tgt.myretail.dao;

import com.tgt.myretail.domain.Product;
import com.tgt.myretail.dto.PriceInfoDto;
import com.tgt.myretail.dto.ProductDto;
import com.tgt.myretail.request.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDaoImpl implements ProductDao {

    public static final String CURRENCY_CODE = "USD";
    @Autowired
    public ProductRepository productRepository;

    /**
     * Fetch product price details
     *
     * @param productDto
     * @return
     */
    @Override
    public ProductDto findByProductId(ProductDto productDto) {
        Product product = fetchProductById(productDto.getProductId());
        if (product != null) {
            PriceInfoDto priceDetails = new PriceInfoDto(product.getPrice(), CURRENCY_CODE);
            productDto.setPriceDetails(priceDetails);
            return productDto;
        } else {
            return null;
        }
    }

    private Product fetchProductById(String productId) {
        return productRepository.findByProductId(productId);
    }

    /**
     * Update price details for the product
     *
     * @param productRequest
     * @return
     */

    @Override
    public boolean updatePriceByProductId(ProductRequest productRequest) {
        boolean isUpdateSuccess = false;
        Product product = fetchProductById(productRequest.getProductId());

        if (product != null) {
            product.setPrice(productRequest.getPrice());
            productRepository.save(product);
            isUpdateSuccess = true;
        }
        return isUpdateSuccess;
    }
}
