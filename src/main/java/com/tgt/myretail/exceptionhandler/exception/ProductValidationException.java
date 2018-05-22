package com.tgt.myretail.exceptionhandler.exception;


import com.tgt.myretail.exceptionhandler.BusinessException;
import com.tgt.myretail.request.ProductRequest;
import org.springframework.validation.BindingResult;

public class ProductValidationException extends BusinessException {

    private BindingResult bindingResult;
    private ProductRequest productRequest;

    public ProductValidationException(BindingResult bindingResult, ProductRequest productRequest) {
        super();
        this.bindingResult = bindingResult;
        this.productRequest = productRequest;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }

    public void setBindingResult(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    public ProductRequest getProductRequest() {
        return productRequest;
    }

    public void setProductRequest(ProductRequest productRequest) {
        this.productRequest = productRequest;
    }
}
