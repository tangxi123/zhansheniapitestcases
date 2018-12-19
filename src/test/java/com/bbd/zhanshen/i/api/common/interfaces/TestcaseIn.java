package com.bbd.zhanshen.i.api.common.interfaces;

import com.bbd.zhanshen.i.api.bean.Testcase;

import java.util.List;

public interface TestcaseIn {
    List<Testcase> getTestcase(String suite, String testModule);
}
