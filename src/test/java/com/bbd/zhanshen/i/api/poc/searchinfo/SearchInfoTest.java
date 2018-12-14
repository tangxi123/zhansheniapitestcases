package com.bbd.zhanshen.i.api.poc.searchinfo;

import com.bbd.zhanshen.i.api.bean.Testcase;
import com.bbd.zhanshen.i.api.common.BaseIT;
import com.bbd.zhanshen.i.api.common.CustomRestErrorHandler;
import com.bbd.zhanshen.i.api.common.Suite;
import com.bbd.zhanshen.i.api.common.TokenUtil;
import com.bbd.zhanshen.i.api.common.exception.CustomException;
import com.bbd.zhanshen.i.api.poc.login.LoginTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.TestNG;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlSuite;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SearchInfoTest extends BaseIT{
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    TokenUtil tokenUtil;

    @DataProvider(name = "searchInfoData")
    public Iterator<Object[]> createData(){
        List<Object[]> data = new ArrayList<>();
        List<Testcase> testcaseList = getTestcase("POC_Info","searchInfo");
        for(Testcase testcase : testcaseList){
            data.add(new Object[]{testcase});
        }
        return data.iterator();
    }

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
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
//        获取url method parameters
        String url = "http://192.168.31.100:8011/poc2"+testcase.getUrl();
        HttpMethod method = testcase.getMethod();
        Map<String, Object> parameters = testcase.getParameters();
//        设置http头信息
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",tokenUtil.getToken());


//       发送http请求
        restTemplate.setErrorHandler(new CustomRestErrorHandler());
        ResponseEntity<String> responseEntity = null;
        try{
            responseEntity= restTemplate.exchange(url,method,new HttpEntity<>(parameters,httpHeaders),String.class);

        }catch (CustomException ce){
            responseEntity = new ResponseEntity<>(ce.getBody(), HttpStatus.BAD_REQUEST);
        }
//        System.out.println(responseEntity.getBody());
        ctx.getCurrentXmlTest().addParameter("actualTestResult",responseEntity.getBody());


    }

    @Test(dataProvider = "searchInfoData" )
    public void testSearchInfo(Testcase testcase, ITestContext ctx, Method method){
        Map<String,String> actualAndExpected = ctx.getCurrentXmlTest().getAllParameters();
        String expectedTestResult = actualAndExpected.get("expectedTestResult");
        String actualTestResult = actualAndExpected.get("actualTestResult");

        System.out.println("actualTestResult"+actualTestResult);
        System.out.println("expectedTestResult"+expectedTestResult);

        Assert.assertEquals(expectedTestResult,actualTestResult);
    }

    public static void main(String[] args) {
        List<XmlSuite> suites = new ArrayList<XmlSuite>();
        suites.add(Suite.createSuite("POC_User","login",LoginTest.class));
        suites.add(Suite.createSuite("POC_Info","searchInfo",SearchInfoTest.class));
        TestNG tng = new TestNG();
        tng.setXmlSuites(suites);
        tng.run();
    }
}
