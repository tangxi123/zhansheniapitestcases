package com.bbd.zhanshen.i.api.poc.login;

import com.bbd.zhanshen.i.api.bean.Testcase;
import com.bbd.zhanshen.i.api.common.BaseIT;
import org.junit.Test;
import org.testng.annotations.DataProvider;

import java.util.Iterator;
import java.util.List;

public class LoginTest extends BaseIT {


    @Test
    public void loginSuccess(){
        List<Testcase> testcaseList = getTestcase("POC_User","login");
        for (Testcase testcase : testcaseList){
            System.out.println(testcase.getSuite());
            System.out.println(testcase.getTestModule());
            System.out.println(testcase.getTestname());
            System.out.println(testcase.getParameters());
            System.out.println(testcase.getExpected());
        }

    }

    @Test
    public void loginFail(){

    }


}
