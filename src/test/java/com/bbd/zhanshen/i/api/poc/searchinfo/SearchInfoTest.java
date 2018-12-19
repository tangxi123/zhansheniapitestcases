package com.bbd.zhanshen.i.api.poc.searchinfo;

import com.bbd.zhanshen.i.api.bean.ESInfo;
import com.bbd.zhanshen.i.api.bean.ESInfoPage;
import com.bbd.zhanshen.i.api.bean.Testcase;
import com.bbd.zhanshen.i.api.common.*;
import com.bbd.zhanshen.i.api.common.exception.CustomException;
import com.bbd.zhanshen.i.api.common.util.JacksonUtil;
import com.bbd.zhanshen.i.api.poc.login.LoginTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.specification.RequestSpecification;
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

import static com.jayway.restassured.RestAssured.given;

public class SearchInfoTest extends BaseIT{

    private static RequestSpecification spec;

    @DataProvider(name = "searchInfoData")
    public Iterator<Object[]> createData(){
        return getTestcase("POC_Info","searchInfo");
    }

    @BeforeMethod
    public void init(Object[] tc, ITestContext ctx){
        spec = initSpec();
        setParameters(tc,ctx);
        setUrl(tc,ctx);
        setStatusCode(tc,ctx);

    }

    @Test(dataProvider = "searchInfoData" )
    public void testSearchInfo(Testcase testcase, ITestContext ctx){
        ESInfoPage actualESInfoPage = getActualESInfoPage(ctx);
        ESInfoPage expectedESInfoPage = createExpectedESInfoPage(testcase);
        assertEqualsESInfoPage(actualESInfoPage,expectedESInfoPage);

    }

    private ESInfoPage createExpectedESInfoPage(Testcase testcase){
        String result = JacksonUtil.toJson(testcase.getExpected());
        ESInfoPage esInfoPage = JacksonUtil.fromJson(result,ESInfoPage.class);
        return esInfoPage;

    }

    private ESInfoPage getActualESInfoPage(ITestContext ctx){
        return given()
                .spec(spec)
                .body(ctx.getCurrentXmlTest().getAllParameters())
                .when()
                .post(ctx.getCurrentXmlTest().getParameter("url"))
                .then()
                .statusCode(Integer.parseInt(ctx.getCurrentXmlTest().getParameter("statusCode")))
                .extract().as(ESInfoPage.class);
    }

    public void assertEqualsESInfoPage(ESInfoPage actualESInfoPage,ESInfoPage expectedESInfoPage){
        Assert.assertEquals(actualESInfoPage.getCurrentPage(),expectedESInfoPage.getCurrentPage());
        Assert.assertEquals(actualESInfoPage.getBeginPageIndex(),expectedESInfoPage.getBeginPageIndex());
        Assert.assertEquals(actualESInfoPage.getPageSize(),expectedESInfoPage.getPageSize());
    }



    public static void main(String[] args) {
        List<XmlSuite> suites = new ArrayList<XmlSuite>();
//        suites.add(Suite.createSuite("POC_User","login",LoginTest.class));
        suites.add(Suite.createSuite("POC_Info","searchInfo",SearchInfoTest.class));
        TestNG tng = new TestNG();
        tng.setXmlSuites(suites);
        tng.run();
    }
}
