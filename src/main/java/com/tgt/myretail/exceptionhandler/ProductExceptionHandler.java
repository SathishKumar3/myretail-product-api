package com.tgt.myretail.exceptionhandler;


import com.tgt.myretail.dto.GenericResponse;
import com.tgt.myretail.exceptionhandler.exception.ProductNotFoundException;
import com.tgt.myretail.exceptionhandler.exception.ProductValidationException;
import com.tgt.myretail.it.controller.ProductController;
import com.tgt.myretail.request.ProductRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice(assignableTypes = ProductController.class)
public class ProductExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ProductNotFoundException.class)
    protected ResponseEntity<?> handleProductNotFoundException(ProductNotFoundException ex, WebRequest webRequest) {
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setMessage("Product Not found for Product Id: " + ex.getProductId());
        genericResponse.setStatus("Failed");
        genericResponse.setStatusCode(String.valueOf(HttpStatus.NO_CONTENT));
        return handleExceptionInternal(ex, genericResponse, new HttpHeaders(), HttpStatus.OK, webRequest);
    }

    @ExceptionHandler(value = ProductValidationException.class)
    protected ResponseEntity<?> handleProductValidationException(ProductValidationException ex, WebRequest webRequest) {
        GenericResponse genericResponse = new GenericResponse();
        BindingResult bindingResult = ex.getBindingResult();
        ProductRequest productRequest = ex.getProductRequest();

        String message = bindingResult.getFieldErrors().
                stream().
                map(DefaultMessageSourceResolvable::getDefaultMessage).
                collect(Collectors.joining(","));
        genericResponse.setMessage(message);
        genericResponse.setStatus("Failed");
        genericResponse.setStatusCode(String.valueOf(HttpStatus.BAD_REQUEST));
        return handleExceptionInternal(ex, genericResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }

}
