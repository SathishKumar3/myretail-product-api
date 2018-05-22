package com.tgt.myretail.itegrationtest;

import com.tgt.myretail.dao.ProductRepository;
import com.tgt.myretail.request.ProductRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerIntegrationTest {

    @LocalServerPort
    private int port;


    @Value("${spring.security.user.name}")
    private String authClientId;

    @Value("${spring.security.user.password}")
    private String authSecret;

    private TestRestTemplate restTemplate;
    private HttpHeaders headers;

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void setUp() {
        headers = new HttpHeaders();
        restTemplate = new TestRestTemplate(authClientId, authSecret);
    }

    @Test
    public void test_fecthProduct_which_has_Data() {
        HttpEntity httpEntity = new HttpEntity<>(null, headers);
        String productId = "13860428";
        String baseUrl = "http://localhost:" + getPort() + "/products/v1/product/" + productId;

        ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.GET, httpEntity, String.class);

        JacksonJsonParser jacksonJsonParser = new JacksonJsonParser();
        Map<String, Object> mapResponse = jacksonJsonParser.parseMap(response.getBody());

        assertThat(response.getStatusCode().is2xxSuccessful());
        assertThat(mapResponse).containsKeys("productId");
        assertThat(mapResponse).containsKeys("name");
        assertThat(mapResponse).containsKeys("priceDetails");
        assertThat(mapResponse).containsValue("The Big Lebowski (Blu-ray)");
        assertThat(response.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON));
    }

    @Test
    public void test_fecthProduct_which_has_no_data() {
        HttpEntity httpEntity = new HttpEntity<>(null, headers);
        String productId = "12345";
        String baseUrl = "http://localhost:" + getPort() + "/products/v1/product/" + productId;

        ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.GET, httpEntity, String.class);


        JacksonJsonParser jacksonJsonParser = new JacksonJsonParser();
        Map<String, Object> mapResponse = jacksonJsonParser.parseMap(response.getBody());

        assertThat(response.getStatusCode().is2xxSuccessful());
        assertThat(mapResponse).containsKeys("message", "status", "statusCode");
        assertThat(mapResponse).containsValue("Product Not found for Product Id: 12345");
        assertThat(mapResponse).containsValue("Failed");
        assertThat(mapResponse).containsValue("204");
        assertThat(response.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON));
    }

    @Test
    public void test_updatePrice_which_has_Data() {

        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductId("13860428");
        productRequest.setPrice(new BigDecimal("10.00"));

        HttpEntity httpEntity = new HttpEntity<>(productRequest, headers);

        String baseUrl = "http://localhost:" + getPort() + "/products/v1/product";

        ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.POST, httpEntity, String.class);

        JacksonJsonParser jacksonJsonParser = new JacksonJsonParser();
        Map<String, Object> mapResponse = jacksonJsonParser.parseMap(response.getBody());

        assertThat(response.getStatusCode().is2xxSuccessful());
        assertThat(mapResponse).containsKeys("message");
        assertThat(mapResponse).containsKeys("status");
        assertThat(mapResponse).containsKeys("statusCode");
        assertThat(mapResponse).containsValue("price updated successfully for Product ID :13860428");
        assertThat(response.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON));
    }

    @Test
    public void test_updatePrice_which_has_invalid_value() {

        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductId("13860428");
        productRequest.setPrice(new BigDecimal("0.01"));

        HttpEntity httpEntity = new HttpEntity<>(productRequest, headers);

        String baseUrl = "http://localhost:" + getPort() + "/products/v1/product";

        ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.POST, httpEntity, String.class);

        JacksonJsonParser jacksonJsonParser = new JacksonJsonParser();
        Map<String, Object> mapResponse = jacksonJsonParser.parseMap(response.getBody());

        assertThat(response.getStatusCode().is2xxSuccessful());
        assertThat(mapResponse).containsKeys("message");
        assertThat(mapResponse).containsKeys("status");
        assertThat(mapResponse).containsKeys("statusCode");
        assertThat(mapResponse).containsValue("Price value should be greater than 0.1");
        assertThat(response.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON));
    }


    @Test
    public void test_updatePrice_which_has_no_Data() {

        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductId("12345");
        productRequest.setPrice(new BigDecimal("10.00"));

        HttpEntity httpEntity = new HttpEntity<>(productRequest, headers);

        String baseUrl = "http://localhost:" + getPort() + "/products/v1/product";

        ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.POST, httpEntity, String.class);

        JacksonJsonParser jacksonJsonParser = new JacksonJsonParser();
        Map<String, Object> mapResponse = jacksonJsonParser.parseMap(response.getBody());

        assertThat(response.getStatusCode().is2xxSuccessful());
        assertThat(mapResponse).containsKeys("message");
        assertThat(mapResponse).containsKeys("status");
        assertThat(mapResponse).containsKeys("statusCode");
        assertThat(mapResponse).containsValue("Product Not found for Product Id: 12345");
        assertThat(mapResponse).containsValue("204");
        assertThat(response.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON));
    }


    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAuthClientId() {
        return authClientId;
    }

    public void setAuthClientId(String authClientId) {
        this.authClientId = authClientId;
    }

    public String getAuthSecret() {
        return authSecret;
    }

    public void setAuthSecret(String authSecret) {
        this.authSecret = authSecret;
    }

    public TestRestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(TestRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

}
