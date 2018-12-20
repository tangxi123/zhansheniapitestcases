package com.bbd.zhanshen.i.api.poc.searchinfo;

import com.bbd.zhanshen.i.api.bean.Testcase;
import com.bbd.zhanshen.i.api.common.BaseIT;
import com.bbd.zhanshen.i.api.common.util.DateTimeUtil;
import com.bbd.zhanshen.i.api.common.util.JacksonUtil;
import com.jayway.restassured.specification.RequestSpecification;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class GroupByInfoSourceTypeTest extends BaseIT {
    private static RequestSpecification spec;

    @DataProvider(name = "groupByInfoSourceTypeTestData")
    public Iterator<Object[]> createData(){
        return getTestcase("POC_Info","groupByInfoSourceType");
    }

    @BeforeMethod
    public void init(Object[] testcase, ITestContext iTestContext){
        spec = initSpec();
        setParameters(testcase,iTestContext);
        setUrl(testcase,iTestContext);
        setStatusCode(testcase,iTestContext);

    }

    @Test(dataProvider = "groupByInfoSourceTypeTestData")
    public void groupByCarryTypeTest(Testcase testcase, ITestContext iTestContext){
        String actualGroupByCarryType = getActualGroupByCarryType(iTestContext);
        String expectedGroupByCarryType = createExpectedGroupByCarryType(testcase);
        assertEqualsGroupByCarryType(actualGroupByCarryType,expectedGroupByCarryType);

    }

    private String getActualGroupByCarryType(ITestContext iTestContext){
        XmlTest xmlTest = iTestContext.getCurrentXmlTest();
        Map<String, String> parameters = xmlTest.getAllParameters();
        if(parameters.containsKey("startDay")){
            Map<String,String> params = new HashMap<>();
            params.put("startDay",parameters.get("startDay"));
            params.put("endDay",parameters.get("endDay"));
            return given()
                    .spec(spec)
                    .params(params)
                    .when()
                    .get(iTestContext.getCurrentXmlTest().getParameter("url"))
                    .then()
                    .statusCode(Integer.parseInt(iTestContext.getCurrentXmlTest().getParameter("statusCode")))
                    .extract().asString();
        }else{
            Map<String,String> params = new HashMap<>();
            params.put("dateType",parameters.get("dateType"));
            return given()
                    .spec(spec)
                    .params(params)
                    .when()
                    .get(iTestContext.getCurrentXmlTest().getParameter("url"))
                    .then()
                    .statusCode(Integer.parseInt(iTestContext.getCurrentXmlTest().getParameter("statusCode")))
                    .extract().asString();
        }

    }

    private String createExpectedGroupByCarryType(Testcase testcase){
        return JacksonUtil.toJson(testcase.getExpected());
    }

    private void assertEqualsGroupByCarryType(String actualGroupByCarryType, String expectedGroupByCarryType){
        assertThat(actualGroupByCarryType).isEqualTo(expectedGroupByCarryType);
    }

    @Override
    public void setParameters(Object[] tc, ITestContext iTestContext){
        Testcase testcase = (Testcase) tc[0];
        Map<String,String> parameters = testcase.getParameters();
        if(parameters.containsKey("startDay")){
            String startDayParam = DateTimeUtil.getAllDateBeginStartOfDay();
            String endDayParam = DateTimeUtil.getCurrentStartOfDay();
            parameters.put("startDay",startDayParam);
            parameters.put("endDay",endDayParam);
            iTestContext.getCurrentXmlTest().setParameters(parameters);
        }else{
            iTestContext.getCurrentXmlTest().setParameters(parameters);
        }
    }
}
