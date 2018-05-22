package com.tgt.myretail.web.controller;

import com.tgt.myretail.controller.ProductController;
import com.tgt.myretail.dto.GenericResponse;
import com.tgt.myretail.dto.PriceInfoDto;
import com.tgt.myretail.dto.ProductDto;
import com.tgt.myretail.exceptionhandler.exception.ProductNotFoundException;
import com.tgt.myretail.logger.interceptors.RequestInterceptor;
import com.tgt.myretail.request.ProductRequest;
import com.tgt.myretail.service.ProductCompositeService;
import com.tgt.myretail.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = ProductController.class, secure = false)
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductControllerTest {


    @MockBean
    private ProductService productServiceMock;

    @MockBean
    private ProductCompositeService productCompositeServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RequestInterceptor requestInterceptor;

    @InjectMocks
    private ProductController productController;


    @Test
    public void test_fecthProduct_success() throws Exception {
        //Given:
        String productId = "1234567";
        ProductDto productDto = new ProductDto();
        PriceInfoDto priceInfoDto = new PriceInfoDto(new BigDecimal("15.00"), "USD");
        productDto.setProductId(productId);
        productDto.setName("Name");
        productDto.setPriceDetails(priceInfoDto);
        Mockito.when(productCompositeServiceMock.fetchProductById(productId)).thenReturn(productDto);

        //When:
        final MvcResult result = mockMvc.perform(get("/products/v1/product/{productId}", productId)).
                andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();
        //Then:
        String actualResult = "{\"productId\":\"1234567\",\"name\":\"Name\",\"priceDetails\":{\"price\":15.00,\"currencyCode\":\"USD\"}}";
        JSONAssert.assertEquals(actualResult, result.getResponse().getContentAsString(), true);
        Mockito.verify(productCompositeServiceMock, Mockito.times(1)).fetchProductById(Mockito.anyString());
        Assert.assertEquals(new Integer(result.getResponse().getStatus()), new Integer(200));
    }

    @Test
    public void test_fecthProduct_no_data() throws Exception {
        //Given:
        String productId = "1234567";

        Mockito.when(productCompositeServiceMock.fetchProductById(productId)).thenThrow(ProductNotFoundException.class);

        //When:
        final MvcResult result = mockMvc.perform(get("/products/v1/product/{productId}", productId)).
                andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();
        //Then:
        String actualResult = "{\"message\":\"Product Not found for Product Id: null\",\"status\":\"Failed\",\"statusCode\":\"204\"}";
        JSONAssert.assertEquals(actualResult, result.getResponse().getContentAsString(), true);
        Mockito.verify(productCompositeServiceMock, Mockito.times(1)).fetchProductById(Mockito.anyString());
        Assert.assertEquals(new Integer(result.getResponse().getStatus()), new Integer(200));
    }

    @Test
    public void test_updateProductPrice_success() throws Exception {
        //Given:
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductId("123456");
        productRequest.setPrice(new BigDecimal("12.99"));

        GenericResponse response = new GenericResponse();
        response.setMessage("");
        response.setStatus("Success");
        response.setStatusCode("201");

        Mockito.when(productServiceMock.updateProductPrice(productRequest)).thenReturn(response);
        String inputRequest = "{\n" +
                "\"productId\":\"13860428\",\"price\": \"11\"\n" +
                "}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/products/v1/product").accept(MediaType.APPLICATION_JSON)
                .content(inputRequest).contentType(MediaType.APPLICATION_JSON);

        final MvcResult result = mockMvc.perform(requestBuilder).
                andExpect(status().isOk()).andReturn();

        Mockito.verify(productServiceMock, Mockito.times(1)).updateProductPrice(Mockito.any());
        Assert.assertEquals(new Integer(result.getResponse().getStatus()), new Integer(200));
    }

    @Test
    public void test_updateProductPrice_noData() throws Exception {
        //Given:
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductId("123");
        productRequest.setPrice(new BigDecimal("12.99"));

        Mockito.when(productServiceMock.updateProductPrice(productRequest)).thenThrow(ProductNotFoundException.class);
        String inputRequest = "{\n" +
                "\"productId\":\"13860428\",\"price\": \"11\"\n" +
                "}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/products/v1/product").accept(MediaType.APPLICATION_JSON)
                .content(inputRequest).contentType(MediaType.APPLICATION_JSON);

        final MvcResult result = mockMvc.perform(requestBuilder).
                andExpect(status().isOk()).andReturn();

        Mockito.verify(productServiceMock, Mockito.times(1)).updateProductPrice(Mockito.any());
        Assert.assertEquals(new Integer(result.getResponse().getStatus()), new Integer(200));
    }


}
