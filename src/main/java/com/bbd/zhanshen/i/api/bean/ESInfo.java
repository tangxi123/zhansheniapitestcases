package com.bbd.zhanshen.i.api.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ESInfo {
    private String id;
    private String title;
    private String content;
    private List<EventsInfo> eventsInfos;
    private List<IndicationsInfo> indicationsInfos;
    private List<LabelsInfo> labelsInfos;
    private List<TokensInfo> tokensInfos;

    public String getId() {
        return id;
    }

    public ESInfo setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ESInfo setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ESInfo setContent(String content) {
        this.content = content;
        return this;
    }

    public List<EventsInfo> getEventsInfos() {
        return eventsInfos;
    }

    public ESInfo setEventsInfos(List<EventsInfo> eventsInfos) {
        this.eventsInfos = eventsInfos;
        return this;
    }

    public List<IndicationsInfo> getIndicationsInfos() {
        return indicationsInfos;
    }

    public ESInfo setIndicationsInfos(List<IndicationsInfo> indicationsInfos) {
        this.indicationsInfos = indicationsInfos;
        return this;
    }

    public List<LabelsInfo> getLabelsInfos() {
        return labelsInfos;
    }

    public ESInfo setLabelsInfos(List<LabelsInfo> labelsInfos) {
        this.labelsInfos = labelsInfos;
        return this;
    }

    public List<TokensInfo> getTokensInfos() {
        return tokensInfos;
    }

    public ESInfo setTokensInfos(List<TokensInfo> tokensInfos) {
        this.tokensInfos = tokensInfos;
        return this;
    }
}
