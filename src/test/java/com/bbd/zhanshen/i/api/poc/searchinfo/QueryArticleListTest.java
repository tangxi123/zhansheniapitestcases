package com.bbd.zhanshen.i.api.poc.searchinfo;

import com.bbd.zhanshen.i.api.bean.Article;
import com.bbd.zhanshen.i.api.bean.Response;
import com.bbd.zhanshen.i.api.bean.Testcase;
import com.bbd.zhanshen.i.api.common.BaseIT;
import com.bbd.zhanshen.i.api.common.util.JacksonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.jayway.restassured.specification.RequestSpecification;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import java.util.Iterator;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class QueryArticleListTest extends BaseIT {
    private static RequestSpecification spec;

    @DataProvider(name = "queryArticleListTestData")
    public Iterator<Object[]> createData(){
        return getTestcase("POC_Info","queryArticleList");
    }

    @BeforeMethod
    public void init(Object[] testcase, ITestContext iTestContext){
        spec = initSpec();
        setParameters(testcase,iTestContext);
        setUrl(testcase,iTestContext);
        setStatusCode(testcase,iTestContext);
    }

    @Test(dataProvider = "queryArticleListTestData")
    public void queryArticleListTest(Testcase testcase, ITestContext iTestContext){
        List<Article> actualListArticle = getActualListArticle(iTestContext);
        List<Article> expectedListArticle = createExpectedListArticle(testcase);
        assertEqualsListArticle(actualListArticle,expectedListArticle);
    }

    private List<Article> getActualListArticle(ITestContext iTestContext){
        XmlTest xmlTest = iTestContext.getCurrentXmlTest();
        String actualListArticleStr = given()
                .spec(spec)
                .body(xmlTest.getAllParameters())
                .when()
                .post(xmlTest.getParameter("url"))
                .then()
                .statusCode(Integer.parseInt(xmlTest.getParameter("statusCode")))
                .extract().asString();
        Response<List<Article>> response = JacksonUtil.fromJson(actualListArticleStr, new TypeReference<Response<List<Article>>>() {});
        return response.getResult();
    }

    private List<Article> createExpectedListArticle(Testcase testcase){
        String expectedListArticleStr = JacksonUtil.toJson(testcase.getExpected());
        Response<List<Article>> response = JacksonUtil.fromJson(expectedListArticleStr, new TypeReference<Response<List<Article>>>() {});
        return response.getResult();
    }

    private void assertEqualsListArticle(List<Article> actualListArticle,List<Article> expectedListArticle){
        assertThat(actualListArticle.size()).isEqualTo(expectedListArticle.size());
    }
}
