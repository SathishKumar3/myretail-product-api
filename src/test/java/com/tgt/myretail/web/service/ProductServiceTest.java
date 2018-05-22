package com.tgt.myretail.web.service;

import com.tgt.myretail.dao.ProductDao;
import com.tgt.myretail.dto.DefaultHeaderRequestDto;
import com.tgt.myretail.dto.GenericResponse;
import com.tgt.myretail.dto.ProductDto;
import com.tgt.myretail.exceptionhandler.exception.ProductNotFoundException;
import com.tgt.myretail.request.ProductRequest;
import com.tgt.myretail.response.Item;
import com.tgt.myretail.response.Product;
import com.tgt.myretail.response.ProductDescription;
import com.tgt.myretail.response.RedSkyServiceResponse;
import com.tgt.myretail.restservice.RestServiceTemplate;
import com.tgt.myretail.service.ProductServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.when;

;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {


    @Mock
    public RestServiceTemplate<RedSkyServiceResponse> redSkyRestServiceTemplateMock;
    @Mock
    private ProductDao productDaoMock;
    @InjectMocks
    private ProductServiceImpl productService = new ProductServiceImpl();

    @After
    public void tearDown() {

    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testfetchProductById() {
        //Given:
        String productId = "12345";
        RedSkyServiceResponse redSkyServiceResponse = new RedSkyServiceResponse();
        Product product = new Product();
        Item item = new Item();
        ProductDescription productDescription = new ProductDescription();
        productDescription.setTitle("Title");
        item.setProductDescription(productDescription);
        product.setItem(item);

        ProductDto productDto = new ProductDto();
        productDto.setProductId("12345");
        redSkyServiceResponse.setProduct(product);

        when(redSkyRestServiceTemplateMock.callRestService(any(DefaultHeaderRequestDto.class), anyMap())).thenReturn(redSkyServiceResponse);
        ProductDto actualResults = productService.fetchProductById(productDto);
        assertEquals(productDto.getProductId(), actualResults.getProductId());

    }

    @Test(expected = ProductNotFoundException.class)
    public void testfetchProductById_noData_from_dataStore() {
        ProductDto productDto = new ProductDto();
        productDto.setProductId("12345");
        productService.fetchProductById(productDto);
    }

    @Test(expected = ProductNotFoundException.class)
    public void testfetchProductById_noData_from_restService() {
        //Given:
        ProductDto productDto = new ProductDto();
        productDto.setProductId("12345");
        when(redSkyRestServiceTemplateMock.callRestService(any(DefaultHeaderRequestDto.class), anyMap())).thenThrow(ProductNotFoundException.class);
        productService.fetchProductById(productDto);
    }


    @Test
    public void test_updateProductPrice_success() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductId("12345");
        GenericResponse expectedResults = new GenericResponse();

        expectedResults.setMessage("price updated successfully for Product ID :" + productRequest.getProductId());
        expectedResults.setStatus("Success");
        expectedResults.setStatusCode(String.valueOf(HttpStatus.CREATED));


        when(productDaoMock.updatePriceByProductId(productRequest)).thenReturn(true);
        GenericResponse actualResults = productService.updateProductPrice(productRequest);

        assertEquals(actualResults.getMessage(), expectedResults.getMessage());

    }

    @Test(expected = ProductNotFoundException.class)
    public void test_updateProductPrice_failure() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductId("12345");
        when(productDaoMock.updatePriceByProductId(productRequest)).thenReturn(false);
        when(productService.updateProductPrice(productRequest)).thenThrow(ProductNotFoundException.class);
    }
}
