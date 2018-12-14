package com.bbd.zhanshen.i.api.poc.login;

import com.bbd.zhanshen.i.api.bean.Testcase;
import com.bbd.zhanshen.i.api.common.BaseIT;
import com.bbd.zhanshen.i.api.common.CustomRestErrorHandler;
import com.bbd.zhanshen.i.api.common.Suite;
import com.bbd.zhanshen.i.api.common.exception.CustomException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestNG;
import org.testng.annotations.*;

import org.testng.xml.XmlSuite;


import java.lang.reflect.Method;
import java.util.*;


public class LoginTest extends BaseIT{
    @Autowired
    RestTemplate restTemplate;

    @DataProvider(name = "loginData")
    public Iterator<Object[]> createData(){
        List<Object[]> data = new ArrayList<>();
        List<Testcase> testcaseList = getTestcase("POC_User","login");
        for(Testcase testcase : testcaseList){
            data.add(new Object[]{testcase});
        }
        return data.iterator();
    }

    /**
     * 发送http请求
     * @param tc
     */
    @BeforeMethod
    public void initTestMethod(Object[] tc,ITestContext ctx)  {
//        获取测试用例
        Testcase testcase = (Testcase)tc[0];
//        获取期望的测试结果
        Map<String, Object> expected = testcase.getExpected();
        String expectedTestResult;
        try {
            expectedTestResult = new ObjectMapper().writeValueAsString(expected);
            ctx.getCurrentXmlTest().addParameter("expectedTestResult",expectedTestResult);
            System.out.println("expectedTestResult"+expectedTestResult);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
//        获取url method parameters
        String url = "http://192.168.31.100:8011/poc2"+testcase.getUrl();
        HttpMethod method = testcase.getMethod();
        Map<String, Object> parameters = testcase.getParameters();


//       发送http请求
        restTemplate.setErrorHandler(new CustomRestErrorHandler());
        ResponseEntity<String> responseEntity = null;
        try{
             responseEntity= restTemplate.exchange(url,method,new HttpEntity<>(parameters),String.class);

        }catch (CustomException ce){
            responseEntity = new ResponseEntity<>(ce.getBody(), HttpStatus.BAD_REQUEST);
        }
        System.out.println(responseEntity.getBody());
        ctx.getCurrentXmlTest().addParameter("actualTestResult",responseEntity.getBody());


    }



    @Test(dataProvider = "loginData")
    public void loginSuccess(Testcase testcase,ITestContext ctx, Method method){
        Map<String,String> actualAndExpected = ctx.getCurrentXmlTest().getAllParameters();
        String expectedTestResult = actualAndExpected.get("expectedTestResult");
        String actualTestResult = actualAndExpected.get("actualTestResult");
        Assert.assertEquals(expectedTestResult,actualTestResult);



    }

    public static void main(String[] args) {
        List<XmlSuite> suites = new ArrayList<XmlSuite>();
        suites.add(Suite.createSuite("POC_User","login",LoginTest.class));
        TestNG tng = new TestNG();
        tng.setXmlSuites(suites);
        tng.run();
    }





}
