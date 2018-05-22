package com.tgt.myretail.dao;


import com.tgt.myretail.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Service
public class DataLoderDao {

    @Autowired
    private ProductRepository productRepository;

    public DataLoderDao() {
    }

    @PostConstruct
    public void init() {
        insertProductData();
    }

    private void insertProductData() {
        if (productRepository != null) {
            this.productRepository.deleteAll();
            Product product = new Product("13860428", "product", new BigDecimal("15.99"));
            this.productRepository.save(product);
        }
    }

}
