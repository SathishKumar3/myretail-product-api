package com.tgt.myretail.dto;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.util.HashMap;
import java.util.Map;


public class DefaultHeaderRequestDto {


    private String endPointURL;

    private HttpEntity<Object> entity;

    private HttpHeaders httpHeaders;
    private Map<String, Long> uriParams = new HashMap<>();

    public Map getUriParams() {
        return uriParams;
    }

    public void setUriParams(Map<String, Long> uriParams) {
        this.uriParams = uriParams;
    }

    public String getEndPointURL() {
        return endPointURL;
    }

    public void setEndPointURL(String endPointURL) {
        this.endPointURL = endPointURL;
    }


    public HttpEntity<Object> getEntity() {
        return entity;
    }

    public void setEntity(HttpHeaders httpHeaders) {
        entity = new HttpEntity<Object>(httpHeaders);
    }

    public HttpHeaders getHttpHeaders() {

        return httpHeaders;
    }

    public void setHttpHeaders(HttpHeaders httpHeaders) {

        this.httpHeaders = httpHeaders;
    }


}
