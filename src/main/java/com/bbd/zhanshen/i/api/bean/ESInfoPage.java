package com.bbd.zhanshen.i.api.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ESInfoPage {
    private int beginPageIndex;
    private int currentPage;
    private int endPageIndex;
    private int pageSize;
    private List<ESInfo> esInfoList;

    public int getBeginPageIndex() {
        return beginPageIndex;
    }

    public ESInfoPage setBeginPageIndex(int beginPageIndex) {
        this.beginPageIndex = beginPageIndex;
        return this;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public ESInfoPage setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public int getEndPageIndex() {
        return endPageIndex;
    }

    public ESInfoPage setEndPageIndex(int endPageIndex) {
        this.endPageIndex = endPageIndex;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public ESInfoPage setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public List<ESInfo> getEsInfoList() {
        return esInfoList;
    }

    public ESInfoPage setEsInfoList(List<ESInfo> esInfoList) {
        this.esInfoList = esInfoList;
        return this;
    }
}
