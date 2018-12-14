package com.bbd.zhanshen.i.api.common.exception;

import org.springframework.web.client.RestClientException;

public class CustomException extends RestClientException{
    RestClientException restClientException;
    private String body;

    public CustomException(String msg, String body){
        super(msg);
        this.body = body;
    }

    public CustomException(String msg, RestClientException restClientException, String body) {
        super(msg);
        this.restClientException = restClientException;
        this.body = body;
    }

    public RestClientException getRestClientException() {
        return restClientException;
    }

    public void setRestClientException(RestClientException restClientException) {
        this.restClientException = restClientException;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

