package com.bbd.zhanshen.i.api.poc.login;

import com.bbd.zhanshen.i.api.bean.Testcase;
import com.bbd.zhanshen.i.api.common.BaseIT;
import com.bbd.zhanshen.i.api.common.CustomRestErrorHandler;
import com.bbd.zhanshen.i.api.common.Suite;
import com.bbd.zhanshen.i.api.common.exception.CustomException;
import com.bbd.zhanshen.i.api.common.util.JacksonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.specification.RequestSpecification;
import io.swagger.models.auth.In;
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

import static com.jayway.restassured.RestAssured.given;


public class LoginTest extends BaseIT{

    private static RequestSpecification spec;

    @DataProvider(name = "loginData")
    public Iterator<Object[]> createData(){
        return getTestcase("POC_User","login");

    }

    /**
     * 发送http请求
     * @param tc
     */
    @BeforeMethod
    public void init(Object[] tc, ITestContext ctx){
        spec = initSpec();
        setParameters(tc,ctx);
        setUrl(tc,ctx);
        setStatusCode(tc,ctx);

    }



    @Test(dataProvider = "loginData")
    public void loginSuccess(Testcase testcase,ITestContext ctx){
        String actualLoginResult = createActualLoginResult(ctx);
        String expectedLoginResult = createExpectedLoginResult(testcase);
        assertLoginResult(actualLoginResult,expectedLoginResult);



    }

    private String createExpectedLoginResult(Testcase testcase){
        return JacksonUtil.toJson(testcase.getExpected());
    }

    private String createActualLoginResult(ITestContext ctx){
        return given()
                .spec(spec)
                .body(ctx.getCurrentXmlTest().getAllParameters())
                .when()
                .post(ctx.getCurrentXmlTest().getParameter("url"))
                .then()
                .statusCode(Integer.parseInt(ctx.getCurrentXmlTest().getParameter("statusCode")))
                .extract().asString();
    }

    private void assertLoginResult(String actualLoginResult,String expectedLoginResult){
        Assert.assertEquals(actualLoginResult,expectedLoginResult);
    }

    public static void main(String[] args) {
        List<XmlSuite> suites = new ArrayList<XmlSuite>();
        suites.add(Suite.createSuite("POC_User","login",LoginTest.class));
        TestNG tng = new TestNG();
        tng.setXmlSuites(suites);
        tng.run();
    }





}
