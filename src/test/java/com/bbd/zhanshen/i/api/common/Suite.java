package com.bbd.zhanshen.i.api.common;

import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import java.util.ArrayList;
import java.util.List;

/***
 * 测试套件类
 */
public class Suite {
    /**
     * 创建测试套件
     * @param testSuite
     * @param testModule
     * @param testClss
     * @return
     */
    public static XmlSuite createSuite(String testSuite, String testModule, Class testClss){
        XmlSuite suite = new XmlSuite();
        suite.setName(testSuite);

        XmlTest test = new XmlTest(suite);
        test.setName(testModule);
        List<XmlClass> classes = new ArrayList<XmlClass>();
        classes.add(new XmlClass(testClss));
        test.setXmlClasses(classes) ;
        return suite;

    }
}
