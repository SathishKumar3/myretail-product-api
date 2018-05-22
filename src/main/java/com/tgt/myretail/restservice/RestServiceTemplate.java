package com.tgt.myretail.restservice;


import com.tgt.myretail.dto.DefaultHeaderRequestDto;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


public class RestServiceTemplate<R> {

    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(RestServiceTemplate.class);
    @Autowired
    public RestTemplate restTemplate;
    private Class<R> responseClass;

    public RestServiceTemplate(Class<R> responseClass) {
        this.responseClass = responseClass;
        this.restTemplate = new RestTemplate();
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public R callRestService(DefaultHeaderRequestDto headerDto, Map uriVariables) {
        ResponseEntity<R> reponse = null;

        try {
            reponse = restTemplate.exchange(headerDto.getEndPointURL(), HttpMethod.GET, headerDto.getEntity(), responseClass, uriVariables);
            return reponse.getBody();

        } catch (HttpClientErrorException e) {
            LOGGER.debug("redsky service exception -" + e.getStatusCode());
            return null;
        } catch (Exception e) {
            throw e;
        }
    }
}
