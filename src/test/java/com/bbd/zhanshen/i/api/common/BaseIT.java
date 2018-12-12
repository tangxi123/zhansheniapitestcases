package com.bbd.zhanshen.i.api.common;


import com.bbd.zhanshen.i.api.ApiApplication;
import com.bbd.zhanshen.i.api.bean.Testcase;
import com.bbd.zhanshen.i.api.mapper.TestcaseMapper;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.util.List;



@SpringBootTest(classes = ApiApplication.class)
public class BaseIT extends AbstractTestNGSpringContextTests {
    @Autowired
    TestcaseMapper testcaseMapper;

    public List<Testcase> getTestcase(String suite, String testModule){
        return testcaseMapper.GetTestcaseByModuleAndSuite(suite, testModule);

    }

    @Test
    public void testA(){
        Assert.assertEquals(1,1);

    }







}
