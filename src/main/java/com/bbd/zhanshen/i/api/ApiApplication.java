package com.bbd.zhanshen.i.api;

import com.bbd.zhanshen.i.api.bean.Testcase;
import com.bbd.zhanshen.i.api.common.TokenUtil;
import com.bbd.zhanshen.i.api.mapper.TestcaseMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bbd.zhanshen.i.api.mapper")
public class ApiApplication implements CommandLineRunner{

    @Autowired
    TestcaseMapper testcaseMapper;

    @Autowired
    TokenUtil tokenUtil;

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
//        System.out.println(this.testcaseMapper.GetTestcaseByModuleAndSuite("POC_User","login"));
//        System.out.println(tokenUtil.getToken());
    }
}
