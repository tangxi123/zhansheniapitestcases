package com.bbd.zhanshen.i.api.poc.searchinfo;

import com.bbd.zhanshen.i.api.bean.ESInfo;
import com.bbd.zhanshen.i.api.bean.Response;
import com.bbd.zhanshen.i.api.bean.Testcase;
import com.bbd.zhanshen.i.api.common.BaseIT;
import com.bbd.zhanshen.i.api.common.TokenUtil;
import com.bbd.zhanshen.i.api.common.util.JacksonUtil;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;

import java.util.Map;

import static com.jayway.restassured.RestAssured.given;


public class NewDetailTest extends BaseIT {

    private static RequestSpecification spec;

    @DataProvider(name = "newDetailTestData")
    public Iterator<Object[]> createData(){
        return getTestcase("POC_Info","newDetail");
    }

    @BeforeMethod
    public void init(Object[] tc, ITestContext ctx){
        spec = initSpec();
        setParameters(tc,ctx);
        setUrl(tc,ctx);
        setStatusCode(tc,ctx);

    }

    @Test(dataProvider = "newDetailTestData")
    public void getInfoIdTest(Testcase testcase,ITestContext ctx){
        ESInfo actualEsInfo = getActualESInfo(ctx);
        ESInfo expectedEsInfo = createExtpectedESInfo(testcase);

        assertEqualESInfo(actualEsInfo,expectedEsInfo);

    }


    private ESInfo createExtpectedESInfo(Testcase testcase){
        String result = JacksonUtil.toJson(testcase.getExpected());
        ESInfo esInfo = JacksonUtil.fromJson(result,ESInfo.class);
        return esInfo;
    }

    private ESInfo getActualESInfo(ITestContext ctx){
        return given()
                .spec(spec)
                .param("id",ctx.getCurrentXmlTest().getParameter("id"))
                .when()
                .get(ctx.getCurrentXmlTest().getParameter("url"))
                .then()
                .statusCode(Integer.parseInt(ctx.getCurrentXmlTest().getParameter("statusCode")))
                .extract().as(ESInfo.class);
    }

    private void assertEqualESInfo(ESInfo actualEsInfo,ESInfo expectedEsInfo){
        Assert.assertEquals(actualEsInfo.getId(),expectedEsInfo.getId());
        Assert.assertEquals(actualEsInfo.getTitle(),expectedEsInfo.getTitle());
    }






}
