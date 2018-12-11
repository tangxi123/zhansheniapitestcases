package com.bbd.zhanshen.i.api.mapper;

import com.bbd.zhanshen.i.api.bean.Testcase;
import com.bbd.zhanshen.i.api.mapper.sqlbuilder.TestcaseSqlBuilder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;


import java.util.List;


public interface TestcaseMapper {

    @SelectProvider(type = TestcaseSqlBuilder.class, method = "buildGetTestcaseByModuleAndSuite")
    List<Testcase> GetTestcaseByModuleAndSuite(@Param("suite") String suite, @Param("testModule") String testModule);

}


