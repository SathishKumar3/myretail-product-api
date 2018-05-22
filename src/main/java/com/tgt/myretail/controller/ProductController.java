package com.tgt.myretail.controller;

import com.tgt.myretail.dto.GenericResponse;
import com.tgt.myretail.dto.ProductDto;
import com.tgt.myretail.exceptionhandler.exception.ProductValidationException;
import com.tgt.myretail.request.ProductRequest;
import com.tgt.myretail.service.ProductCompositeService;
import com.tgt.myretail.service.ProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * ProductController exposes Get and Post service for /products
 */

@RestController
@RequestMapping("/products")
@Api(value = "MyRetail", description = "MyRetail-Product-Rest-API")
public class ProductController {

    public static final String VERSION = "v1";

    @Autowired
    private ProductCompositeService productCompositeService;

    @Autowired
    private ProductService productService;

    /**
     * Get Method- Retrieve product details from redsky service and price details from database
     *
     * @param productId
     * @return
     */
    @GetMapping(VERSION + "/product/{productId}")
    public ResponseEntity<?> retrieveProductDetails(@PathVariable("productId") String productId) {
        ProductDto productDto = productCompositeService.fetchProductById(productId);
        return ResponseEntity.ok().body(productDto);
    }

    /**
     * Post method-  Update price details for the available product
     *
     * @param productRequest
     * @param bindingResult
     * @return
     * @throws ProductValidationException
     */
    @PostMapping(VERSION + "/product")
    public ResponseEntity<?> updateProductPrice(@Valid @RequestBody ProductRequest productRequest, BindingResult bindingResult) throws ProductValidationException {

        if (bindingResult.hasErrors()) {
            throw new ProductValidationException(bindingResult, productRequest);
        }
        GenericResponse response = productService.updateProductPrice(productRequest);
        return ResponseEntity.ok().body(response);
    }

}
