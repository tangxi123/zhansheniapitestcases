package com.bbd.zhanshen.i.api.poc.searchinfo;

import com.bbd.zhanshen.i.api.bean.Testcase;
import com.bbd.zhanshen.i.api.common.BaseIT;
import com.bbd.zhanshen.i.api.common.util.JacksonUtil;
import com.jayway.restassured.specification.RequestSpecification;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import java.util.Iterator;

import static com.jayway.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class QueryElementInfoTest extends BaseIT {
    private static RequestSpecification spec;

    @DataProvider(name = "queryElementInfoTestData")
    public Iterator<Object[]> createData(){
        return getTestcase("POC_Info","queryElementInfo");
    }

    @BeforeMethod
    public void init(Object[] testcase, ITestContext iTestContext){
        spec = initSpec();
        setParameters(testcase,iTestContext);
        setUrl(testcase,iTestContext);
        setStatusCode(testcase,iTestContext);
    }

    @Test(dataProvider = "queryElementInfoTestData")
    public void queryElementInfoTest(Testcase testcase, ITestContext iTestContext){
        String acutalElementInfo = getAcutalElementInfo(iTestContext);
        String expectedElementInfo = createExpectedElemetInfo(testcase);
        assertEqualsElementInfo(acutalElementInfo,expectedElementInfo);
    }

    private String getAcutalElementInfo(ITestContext iTestContext){
        XmlTest xmlTest = iTestContext.getCurrentXmlTest();
        return given()
                .spec(spec)
                .params(xmlTest.getAllParameters())
                .when()
                .get(xmlTest.getParameter("url"))
                .then()
                .statusCode(Integer.parseInt(xmlTest.getParameter("statusCode")))
                .extract()
                .asString();
    }

    private String createExpectedElemetInfo(Testcase testcase){
        return JacksonUtil.toJson(testcase.getExpected());
    }

    private void assertEqualsElementInfo(String acutalElementInfo,String expectedElementInfo){
        assertThat(acutalElementInfo).isEqualTo(expectedElementInfo);
    }

}
