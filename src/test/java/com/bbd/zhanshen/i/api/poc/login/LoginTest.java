package com.bbd.zhanshen.i.api.poc.login;

import com.bbd.zhanshen.i.api.bean.Testcase;
import com.bbd.zhanshen.i.api.common.BaseIT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


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

    @BeforeMethod
    public void init(Object[] tc, ITestContext ctx){
        Testcase testcase = (Testcase)tc[0];
        String url = testcase.getUrl();
        HttpMethod method = testcase.getMethod();
        Map<String, Object> parameters = testcase.getParameters();
        Map<String, Object> expected = testcase.getExpected();
        System.out.println("###########注入restTemplate######################");
        restTemplate.toString();
    }



    @Test(dataProvider = "loginData")
    public void loginSuccess(Testcase testcase){
        System.out.println(testcase.getParameters());

    }

    @Test
    public void testA(){
        Assert.assertEquals(1,1);
    }


}
