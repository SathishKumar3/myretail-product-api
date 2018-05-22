package com.tgt.myretail.service;

import com.tgt.myretail.dao.ProductDao;
import com.tgt.myretail.dto.DefaultHeaderRequestDto;
import com.tgt.myretail.dto.GenericResponse;
import com.tgt.myretail.dto.ProductDto;
import com.tgt.myretail.exceptionhandler.exception.ProductNotFoundException;
import com.tgt.myretail.request.ProductRequest;
import com.tgt.myretail.response.RedSkyServiceResponse;
import com.tgt.myretail.restservice.RestServiceTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);


    @Value("${redskyEndpointUrl}")
    private String redskyEndpointUrl;

    @Autowired
    private ProductDao productDao;

    private RestServiceTemplate restServiceTemplate;

    @PostConstruct
    void init() {
        restServiceTemplate = new RestServiceTemplate(RedSkyServiceResponse.class);
    }

    /**
     * @param productDto
     * @return
     * @throws ProductNotFoundException
     */
    @Override
    public ProductDto fetchProductById(ProductDto productDto) throws ProductNotFoundException {

        DefaultHeaderRequestDto headerRequestDto = getRedSkyDefaultRequestDTO();

        RedSkyServiceResponse redSkyServiceResponse = (RedSkyServiceResponse) restServiceTemplate.callRestService(headerRequestDto,
                buildURLVariables(productDto.getProductId()));
        if (redSkyServiceResponse == null) {
            throw new ProductNotFoundException(productDto.getProductId());
        }
        //TODO:Null Check
        productDto.setName(redSkyServiceResponse.getProduct().getItem().getProductDescription().getTitle());
        return productDto;
    }

    /**
     *
     * @param productDto
     * @return
     * @throws ProductNotFoundException
     */
    @Override
    public ProductDto fetchPriceDetails(ProductDto productDto) throws ProductNotFoundException {
        productDao.findByProductId(productDto);
        if (productDto == null) {
            LOGGER.debug("Product not available in DB - " + productDto.getProductId());
            throw new ProductNotFoundException(productDto.getProductId());
        }
        return productDto;

    }

    /**
     *
     * @param productRequest
     * @return
     * @throws ProductNotFoundException
     */

    public GenericResponse updateProductPrice(ProductRequest productRequest) throws ProductNotFoundException {
        boolean isUpdateSuccess = productDao.updatePriceByProductId(productRequest);
        GenericResponse genericResponse = new GenericResponse();
        if (isUpdateSuccess) {
            genericResponse.setMessage("price updated successfully for Product ID :" + productRequest.getProductId());
            genericResponse.setStatus("Success");
            genericResponse.setStatusCode(String.valueOf(HttpStatus.CREATED));
        } else {
            throw new ProductNotFoundException(productRequest.getProductId());
        }

        return genericResponse;
    }


    private Map<String, Long> buildURLVariables(String productId) {
        Map<String, Long> uriVariables = new HashMap<>();
        uriVariables.put("id", Long.valueOf(productId));
        return uriVariables;
    }


    private DefaultHeaderRequestDto getRedSkyDefaultRequestDTO() {
        Map<Object, Object> uriVariables = new HashMap<Object, Object>();
        DefaultHeaderRequestDto defaultHeaderRequestDTO = new DefaultHeaderRequestDto();
        defaultHeaderRequestDTO.setEndPointURL(redskyEndpointUrl);
        defaultHeaderRequestDTO.setHttpHeaders(new HttpHeaders());
        defaultHeaderRequestDTO.setEntity(defaultHeaderRequestDTO.getHttpHeaders());
        return defaultHeaderRequestDTO;
    }


}
