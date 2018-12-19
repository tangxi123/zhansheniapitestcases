package com.bbd.zhanshen.i.api.poc.pageSyscode;

import com.bbd.zhanshen.i.api.bean.Testcase;
import com.bbd.zhanshen.i.api.common.BaseIT;
import com.bbd.zhanshen.i.api.common.util.JacksonUtil;
import com.jayway.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import static com.jayway.restassured.RestAssured.given;

public class GetCodesTest extends BaseIT {
    private static RequestSpecification spec;

    @DataProvider(name = "getCodesData")
    public Iterator<Object[]> createData(){
        return getTestcase("PageSyscode","getCodes");
    }

    @BeforeMethod
    public void init(Object[] tc, ITestContext ctx){
        spec = initSpec();
        setParameters(tc,ctx);
        setUrl(tc,ctx);
        setStatusCode(tc,ctx);

    }

    @Test(dataProvider = "getCodesData")
    public void getCodesTest(Testcase testcase, ITestContext ctx){
        String actualCodesResult = getActualCodesResult(ctx);
        String expectedCodesResult = getExpectedCodesResult(testcase);
        assertCodesResult(actualCodesResult,expectedCodesResult);
    }

    private String getActualCodesResult(ITestContext ctx){
        return given()
                .spec(spec)
                .when()
                .get(ctx.getCurrentXmlTest().getParameter("url"))
                .then()
                .statusCode(Integer.parseInt(ctx.getCurrentXmlTest().getParameter("statusCode")))
                .extract().asString();
    }

    private String getExpectedCodesResult(Testcase testcase){
        return JacksonUtil.toJson(testcase.getExpected());
    }

    private void assertCodesResult(String actualCodesResult, String expectedCodesResult){
        Assert.assertEquals(actualCodesResult,expectedCodesResult);
    }


}
