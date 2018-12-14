package com.bbd.zhanshen.i.api.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * TokenUtil类用来获取Token
 */
public class TokenUtil {
    private RestTemplate restTemplate;

    public TokenUtil(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    public String getToken(){
        String url = "http://192.168.31.100:8011/poc2/user/login";
        HttpMethod method = HttpMethod.POST;
        Map<String,String> parameters = new HashMap<>();
        parameters.put("username","admin");
        parameters.put("password","0192023a7bbd73250516f069df18b500");
        ResponseEntity<String> responseEntity = restTemplate.exchange(url,method,new HttpEntity<>(parameters),String.class);
        try {
            Map<String,String> response =  new ObjectMapper().readValue(responseEntity.getBody(),Map.class);
            String result = new ObjectMapper().writeValueAsString(response.get("result"));
            Map<String,String> token = new ObjectMapper().readValue(result,Map.class);
            return token.get("token");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }


}
