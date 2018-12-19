package com.bbd.zhanshen.i.api.common;


import com.bbd.zhanshen.i.api.ApiApplication;
import com.bbd.zhanshen.i.api.bean.Testcase;
import com.bbd.zhanshen.i.api.common.interfaces.TestcaseIn;
import com.bbd.zhanshen.i.api.mapper.TestcaseMapper;


import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import org.junit.experimental.theories.internal.SpecificDataPointsSupplier;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@SpringBootTest(classes = ApiApplication.class)
public class BaseIT extends AbstractTestNGSpringContextTests {
    @Autowired
    TestcaseMapper testcaseMapper;

    @Autowired
    TokenUtil tokenUtil;

    private RequestSpecification spec;


    public Iterator<Object[]> getTestcase(String suite, String testModule){
//        return testcaseMapper.GetTestcaseByModuleAndSuite(suite, testModule);
        List<Testcase> testcaseList =testcaseMapper.GetTestcaseByModuleAndSuite(suite, testModule);
        List<Object[]> data = new ArrayList<>();
        for(Testcase testcase : testcaseList){
            data.add(new Object[]{testcase});
        }
        return data.iterator();
    }

    public RequestSpecification initSpec(){
        spec = new RequestSpecBuilder()
                .addHeader("Authorization", tokenUtil.getToken())
                .setContentType(ContentType.JSON)
                .setBaseUri("http://192.168.31.100:8011/poc2")
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();

        return spec;
    }
    public void setParameters(Object[] tc, ITestContext ctx){
        Testcase testcase = (Testcase) tc[0];
        Map<String,String> parameters = testcase.getParameters();
        ctx.getCurrentXmlTest().setParameters(parameters);

    }

    public void setUrl(Object[] tc, ITestContext ctx){
        Testcase testcase = (Testcase) tc[0];
        String url = testcase.getUrl();
        ctx.getCurrentXmlTest().addParameter("url",url);
    }

    public void setStatusCode(Object[] tc, ITestContext ctx){
        Testcase testcase = (Testcase) tc[0];
        String statusCode = testcase.getStatusCode();
        ctx.getCurrentXmlTest().addParameter("statusCode",statusCode);
    }








}
