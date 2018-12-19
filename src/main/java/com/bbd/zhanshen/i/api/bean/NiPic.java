package com.bbd.zhanshen.i.api.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NiPic{
    private String nipId;
    private String nipDesc;

    public String getNipId() {
        return nipId;
    }

    public NiPic setNipId(String nipId) {
        this.nipId = nipId;
        return this;
    }

    public String getNipDesc() {
        return nipDesc;
    }

    public NiPic setNipDesc(String nipDesc) {
        this.nipDesc = nipDesc;
        return this;
    }
}

