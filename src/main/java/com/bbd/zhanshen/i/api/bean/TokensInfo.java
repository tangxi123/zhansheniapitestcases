package com.bbd.zhanshen.i.api.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TokensInfo {
    private String infoId;
    private String dealStatus;
    private String elementId;
    private boolean firstFound;

    public String getInfoId() {
        return infoId;
    }

    public TokensInfo setInfoId(String infoId) {
        this.infoId = infoId;
        return this;
    }

    public String getDealStatus() {
        return dealStatus;
    }

    public TokensInfo setDealStatus(String dealStatus) {
        this.dealStatus = dealStatus;
        return this;
    }

    public String getElementId() {
        return elementId;
    }

    public TokensInfo setElementId(String elementId) {
        this.elementId = elementId;
        return this;
    }

    public boolean isFirstFound() {
        return firstFound;
    }

    public TokensInfo setFirstFound(boolean firstFound) {
        this.firstFound = firstFound;
        return this;
    }
}
