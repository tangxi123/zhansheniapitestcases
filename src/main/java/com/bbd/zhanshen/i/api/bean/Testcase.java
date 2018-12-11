package com.bbd.zhanshen.i.api.bean;

import org.springframework.http.HttpMethod;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

public class Testcase {
    private Long id;

    private String suite;

    private String testModule;

    private String descs;

    private String testname;

    private HttpMethod method;

    private URI url;

    private Map<String, String> headers;

    private Map<String, Object> parameters;

    private Map<String, Object> expected;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Map<String, Object> actual;

    private int isPassed;

    private LocalDateTime testAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getTestModule() {
        return testModule;
    }

    public void setTestModule(String testModule) {
        this.testModule = testModule;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    public String getTestname() {
        return testname;
    }

    public void setTestname(String testname) {
        this.testname = testname;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public URI getUrl() {
        return url;
    }

    public void setUrl(URI url) {
        this.url = url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Map<String, Object> getExpected() {
        return expected;
    }

    public void setExpected(Map<String, Object> expected) {
        this.expected = expected;
    }

    public Map<String, Object> getActual() {
        return actual;
    }

    public void setActual(Map<String, Object> actual) {
        this.actual = actual;
    }

    public int getIsPassed() {
        return isPassed;
    }

    public void setIsPassed(int isPassed) {
        this.isPassed = isPassed;
    }

    public LocalDateTime getTestAt() {
        return testAt;
    }

    public void setTestAt(LocalDateTime testAt) {
        this.testAt = testAt;
    }
}
