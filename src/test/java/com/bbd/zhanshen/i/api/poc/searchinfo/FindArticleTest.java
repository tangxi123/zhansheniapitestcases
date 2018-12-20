package com.bbd.zhanshen.i.api.poc.searchinfo;

import com.bbd.zhanshen.i.api.bean.Article;
import com.bbd.zhanshen.i.api.bean.NiPic;
import com.bbd.zhanshen.i.api.bean.Response;
import com.bbd.zhanshen.i.api.bean.Testcase;
import com.bbd.zhanshen.i.api.common.BaseIT;
import com.bbd.zhanshen.i.api.common.util.JacksonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.jayway.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.IReporter;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class FindArticleTest extends BaseIT {
    private static RequestSpecification spec;

    @DataProvider(name = "findArticleTestData")
    public Iterator<Object[]> createData(){
        return getTestcase("POC_Info","findArticle");
    }

    @BeforeMethod
    public void init(Object[] testcase, ITestContext iTestContext){
        spec = initSpec();
        setParameters(testcase,iTestContext);
        setUrl(testcase,iTestContext);
        setStatusCode(testcase,iTestContext);

    }

    @Test(dataProvider = "findArticleTestData")
    public void findArticleTest(Testcase testcase, ITestContext iTestContext){
        Article actualArtical = getActualArticle(iTestContext);
        Article expectedArticle = createExpectedArticle(testcase);
        assertEqualsArticle(actualArtical,expectedArticle);
    }

    private Article getActualArticle(ITestContext iTestContext){
        String actualArticle =  given()
                .spec(spec)
                .when()
                .get(iTestContext.getCurrentXmlTest().getParameter("url")+"/"+iTestContext.getCurrentXmlTest().getParameter("id"))
                .then()
                .statusCode(Integer.parseInt(iTestContext.getCurrentXmlTest().getParameter("statusCode")))
                .extract().asString();
        Response<Article> response = JacksonUtil.fromJson(actualArticle, new TypeReference<Response<Article>>() {});
        return response.getResult();
    }

    private Article createExpectedArticle(Testcase testcase){
        String articleStr = JacksonUtil.toJson(testcase.getExpected());
        Response<Article> response = JacksonUtil.fromJson(articleStr, new TypeReference<Response<Article>>() {});
        return response.getResult();
    }

    private void assertEqualsArticle(Article actualArticle, Article expectedArticle){
        boolean actualArticleExits = (actualArticle !=null);
        boolean expectedArticleExists = (expectedArticle !=null);
        if(actualArticleExits && expectedArticleExists)
        {
            Assert.assertEquals(actualArticle.getNiBelonger(),expectedArticle.getNiBelonger());
            Assert.assertEquals(actualArticle.getNiCreator(),expectedArticle.getNiCreator());
            Assert.assertEquals(actualArticle.getNiNumber(),expectedArticle.getNiNumber());
            assertEqualsNiPic(actualArticle,expectedArticle);
        }else if(!actualArticleExits && !expectedArticleExists) {
            Assert.assertEquals("", "");
        }else if(!actualArticleExits ){
            Assert.assertEquals("",expectedArticle.toString());
        }else{
            Assert.assertEquals(actualArticle.toString(),"");
        }

    }



    private void assertEqualsNiPic(Article actualArtical, Article expectedArticle){
        List<NiPic> actualNiPicList = actualArtical.getNiPicList();
        List<NiPic> expectedNiPicList = expectedArticle.getNiPicList();
        assertThat(actualNiPicList.size()).isEqualTo(expectedNiPicList.size());

        for(int i=0; i<actualNiPicList.size(); i++){
            assertThat(actualNiPicList.get(i).getNipDesc()).isEqualTo(expectedNiPicList.get(i).getNipDesc());
        }








    }

}
