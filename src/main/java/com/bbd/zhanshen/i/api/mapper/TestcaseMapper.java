package com.bbd.zhanshen.i.api.mapper;

import com.bbd.zhanshen.i.api.bean.Testcase;
import com.bbd.zhanshen.i.api.mapper.sqlbuilder.TestcaseSqlBuilder;
import com.bbd.zhanshen.i.api.mapper.typehandler.MapTypeHandler;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Map;

@Mapper
public interface TestcaseMapper {
//    @TypeDiscriminator(column = "parameters",javaType = Map.class,jdbcType = JdbcType.VARCHAR,typeHandler = MapTypeHandler.class,cases = )
    @Results(id = "testcaseResultMap",value = {
            @Result(property = "headers",column = "headers",typeHandler = MapTypeHandler.class),
            @Result(property = "parameters",column = "parameters",typeHandler = MapTypeHandler.class),
            @Result(property = "expected",column = "expected",typeHandler = MapTypeHandler.class),

    }
    )
    @SelectProvider(type = TestcaseSqlBuilder.class, method = "buildGetTestcaseByModuleAndSuite")
    List<Testcase> GetTestcaseByModuleAndSuite(@Param("suite") String suite, @Param("testModule") String testModule);


}


