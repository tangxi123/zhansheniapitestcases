package com.bbd.zhanshen.i.api.mapper.sqlbuilder;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class TestcaseSqlBuilder {
    public static String buildGetTestcaseByModuleAndSuite(@Param("suite")final String suite, @Param("testModule")final String testModule){
        return new SQL()
                .SELECT("*")
                .FROM("zsi_test_case")
                .WHERE("suite = #{suite}","test_module = #{testModule}")
                .toString();
    }
}
