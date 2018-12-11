package com.bbd.zhanshen.i.api.common;

import com.bbd.zhanshen.i.api.bean.Testcase;
import com.bbd.zhanshen.i.api.mapper.TestcaseMapper;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import javax.lang.model.element.VariableElement;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@MapperScan("com.bbd.zhanshen.i.api.mapper")
public class BaseIT extends AbstractTestNGSpringContextTests {
    @Autowired
    TestcaseMapper testcaseMapper;

    public List<Testcase> getTestcase(String suite, String testModule){
        return testcaseMapper.GetTestcaseByModuleAndSuite(suite, testModule);

    }





}
