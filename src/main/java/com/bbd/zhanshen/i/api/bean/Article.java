package com.bbd.zhanshen.i.api.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Article {
    private String niBelonger;
    private String niCreator;
    private String niNumber;
    private List<NiPic> niPicList;

    public String getNiBelonger() {
        return niBelonger;
    }

    public Article setNiBelonger(String niBelonger) {
        this.niBelonger = niBelonger;
        return this;
    }

    public String getNiCreator() {
        return niCreator;
    }

    public Article setNiCreator(String niCreator) {
        this.niCreator = niCreator;
        return this;
    }

    public String getNiNumber() {
        return niNumber;
    }

    public Article setNiNumber(String niNumber) {
        this.niNumber = niNumber;
        return this;
    }

    public List<NiPic> getNiPicList() {
        return niPicList;
    }

    public Article setNiPicList(List<NiPic> niPicList) {
        this.niPicList = niPicList;
        return this;
    }


}
