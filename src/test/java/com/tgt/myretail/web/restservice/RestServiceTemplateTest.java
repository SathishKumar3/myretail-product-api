package com.tgt.myretail.web.restservice;

import com.tgt.myretail.dto.DefaultHeaderRequestDto;
import com.tgt.myretail.dto.ProductDto;
import com.tgt.myretail.response.Item;
import com.tgt.myretail.response.Product;
import com.tgt.myretail.response.ProductDescription;
import com.tgt.myretail.response.RedSkyServiceResponse;
import com.tgt.myretail.restservice.RestServiceTemplate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RestServiceTemplateTest {

    @Mock
    public RestTemplate restTemplateMock;


    @InjectMocks
    public RestServiceTemplate<RedSkyServiceResponse> restServiceTemplate;


    @Before
    public void setup() {
        restServiceTemplate.setRestTemplate(restTemplateMock);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_callRestService() {

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

        ResponseEntity<RedSkyServiceResponse> response = new ResponseEntity<RedSkyServiceResponse>(redSkyServiceResponse, HttpStatus.ACCEPTED);
        DefaultHeaderRequestDto defaultHeaderRequestDto = new DefaultHeaderRequestDto();
        defaultHeaderRequestDto.setEndPointURL("");
        Map<String, Long> map = new HashMap<>();
        map.put("id", 1L);

        Mockito.when(restTemplateMock.exchange(Mockito.anyString(),
                Mockito.<HttpMethod>any(),
                Mockito.<HttpEntity<?>>any(),
                Mockito.<Class<?>>any(),
                Mockito.<String, String>anyMap()))
                .thenReturn((ResponseEntity) response);

        RedSkyServiceResponse restServiceResponse = (RedSkyServiceResponse) restServiceTemplate.callRestService(defaultHeaderRequestDto, map);
        assertEquals(restServiceResponse.getProduct().getItem().getProductDescription().getTitle(), "Title");
    }

    @Test
    public void test_callRestService_httpClientServerException() {
        DefaultHeaderRequestDto defaultHeaderRequestDto = new DefaultHeaderRequestDto();
        defaultHeaderRequestDto.setEndPointURL("");
        Map<String, Long> map = new HashMap<>();
        map.put("id", 1L);

        Mockito.when(restTemplateMock.exchange(Mockito.anyString(),
                Mockito.<HttpMethod>any(),
                Mockito.<HttpEntity<?>>any(),
                Mockito.<Class<?>>any(),
                Mockito.<String, String>anyMap()))
                .thenThrow(new HttpClientErrorException(HttpStatus.BAD_GATEWAY, "Test HttpClientErrorException"));

        RedSkyServiceResponse restServiceResponse = (RedSkyServiceResponse) restServiceTemplate.callRestService(defaultHeaderRequestDto, map);
        assert restServiceResponse == null;
    }

    @Test(expected = Exception.class)
    public void test_callRestService_Exception() {
        DefaultHeaderRequestDto defaultHeaderRequestDto = new DefaultHeaderRequestDto();
        defaultHeaderRequestDto.setEndPointURL("");
        Map<String, Long> map = new HashMap<>();
        map.put("id", 1L);

        Mockito.when(restTemplateMock.exchange(Mockito.anyString(),
                Mockito.<HttpMethod>any(),
                Mockito.<HttpEntity<?>>any(),
                Mockito.<Class<?>>any(),
                Mockito.<String, String>anyMap()))
                .thenThrow(new Exception("Test Exception"));

        RedSkyServiceResponse restServiceResponse = (RedSkyServiceResponse) restServiceTemplate.callRestService(defaultHeaderRequestDto, map);
    }
}