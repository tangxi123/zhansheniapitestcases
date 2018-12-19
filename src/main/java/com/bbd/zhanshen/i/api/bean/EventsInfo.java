package com.bbd.zhanshen.i.api.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventsInfo {
    private String infoId;
    private String eventId;
    private String cleanedText;
    private String sentense;

    public String getInfoId() {
        return infoId;
    }

    public EventsInfo setInfoId(String infoId) {
        this.infoId = infoId;
        return this;
    }

    public String getEventId() {
        return eventId;
    }

    public EventsInfo setEventId(String eventId) {
        this.eventId = eventId;
        return this;
    }

    public String getCleanedText() {
        return cleanedText;
    }

    public EventsInfo setCleanedText(String cleanedText) {
        this.cleanedText = cleanedText;
        return this;
    }

    public String getSentense() {
        return sentense;
    }

    public EventsInfo setSentense(String sentense) {
        this.sentense = sentense;
        return this;
    }
}
