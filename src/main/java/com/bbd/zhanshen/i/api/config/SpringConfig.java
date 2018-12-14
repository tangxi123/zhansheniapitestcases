package com.bbd.zhanshen.i.api.config;

import com.bbd.zhanshen.i.api.common.TokenUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpringConfig {
    @Bean(name = "restTemplate")
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean(name = "tokenUtil")
    public TokenUtil getTokenUtil(){
        return new TokenUtil(getRestTemplate());
    }



}
