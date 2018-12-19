package com.bbd.zhanshen.i.api.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IndicationsInfo {
    private String id;
    private String alertEventId;
    private String name;

    public String getId() {
        return id;
    }

    public IndicationsInfo setId(String id) {
        this.id = id;
        return this;
    }

    public String getAlertEventId() {
        return alertEventId;
    }

    public IndicationsInfo setAlertEventId(String alertEventId) {
        this.alertEventId = alertEventId;
        return this;
    }

    public String getName() {
        return name;
    }

    public IndicationsInfo setName(String name) {
        this.name = name;
        return this;
    }
}
