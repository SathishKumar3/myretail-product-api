package com.tgt.myretail.web.service;


import com.tgt.myretail.dto.ProductDto;
import com.tgt.myretail.exceptionhandler.exception.ProductNotFoundException;
import com.tgt.myretail.service.ProductCompositeService;
import com.tgt.myretail.service.ProductCompositeServiceImpl;
import com.tgt.myretail.service.ProductService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class ProductCompositeServiceTest {

    @Mock
    private ProductService productServiceMock;

    @InjectMocks
    private ProductCompositeService productCompositeService = new ProductCompositeServiceImpl();


    @After
    public void tearDown() {

    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_fetchProductById() {

        String productId = "12345";
        ProductDto productDto = new ProductDto();
        productDto.setProductId(productId);

        Mockito.when(productServiceMock.fetchProductById(any(ProductDto.class))).thenReturn(productDto);
        Mockito.when(productServiceMock.fetchPriceDetails(any(ProductDto.class))).thenReturn(productDto);

        ProductDto actualResults = productCompositeService.fetchProductById(productId);
        assertEquals(actualResults.getProductId(), productDto.getProductId());
    }


    @Test(expected = ProductNotFoundException.class)
    public void testfetchProductById_noData_from_restService() {
        //Given:
        String productId = "12345";
        Mockito.when(productServiceMock.fetchProductById(any(ProductDto.class))).thenThrow(ProductNotFoundException.class);
        productCompositeService.fetchProductById(productId);
        Mockito.verify(productServiceMock, Mockito.times(0)).fetchPriceDetails(any(ProductDto.class));

    }

    @Test(expected = ProductNotFoundException.class)
    public void testfetchProductById_noData_from_dataBase() {
        //Given:
        String productId = "12345";
        ProductDto productDto = new ProductDto();
        productDto.setProductId(productId);
        Mockito.when(productServiceMock.fetchProductById(any(ProductDto.class))).thenReturn(productDto);
        Mockito.when(productServiceMock.fetchPriceDetails(any(ProductDto.class))).thenThrow(ProductNotFoundException.class);
        productCompositeService.fetchProductById(productId);
        Mockito.verify(productServiceMock, Mockito.times(1)).fetchProductById(any(ProductDto.class));

    }
}
