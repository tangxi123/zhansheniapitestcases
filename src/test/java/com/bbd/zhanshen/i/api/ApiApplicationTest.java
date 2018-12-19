package com.bbd.zhanshen.i.api;

import com.bbd.zhanshen.i.api.common.Suite;
import com.bbd.zhanshen.i.api.common.TokenUtil;
import com.bbd.zhanshen.i.api.poc.login.LoginTest;
import com.bbd.zhanshen.i.api.poc.searchinfo.NewDetailTest;
import com.bbd.zhanshen.i.api.poc.searchinfo.SearchInfoTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class ApiApplicationTest extends AbstractTestNGSpringContextTests {

    public static void main(String[] args) {
        List<XmlSuite> suites = new ArrayList<XmlSuite>();
        suites.add(Suite.createSuite("POC_User","login",LoginTest.class));
        suites.add(Suite.createSuite("POC_Info","searchInfo",SearchInfoTest.class));
        suites.add(Suite.createSuite("POC_Info2","newDetail",NewDetailTest.class));
        TestNG tng = new TestNG();
        tng.setXmlSuites(suites);
        tng.run();
    }

}
