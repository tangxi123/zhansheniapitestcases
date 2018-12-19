package com.bbd.zhanshen.i.api.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LabelsInfo {
    private String id;
    private String label;
    private String labelLv1;
    private String labelLv2;
    private String labelLv3;
    private String labelLv4;
    private String labelLv5;
    private String labelLv6;
    private String labelLv7;
    private String parentId;

    public String getId() {
        return id;
    }

    public LabelsInfo setId(String id) {
        this.id = id;
        return this;
    }

    public String getLabel() {
        return label;
    }

    public LabelsInfo setLabel(String label) {
        this.label = label;
        return this;
    }

    public String getLabelLv1() {
        return labelLv1;
    }

    public LabelsInfo setLabelLv1(String labelLv1) {
        this.labelLv1 = labelLv1;
        return this;
    }

    public String getLabelLv2() {
        return labelLv2;
    }

    public LabelsInfo setLabelLv2(String labelLv2) {
        this.labelLv2 = labelLv2;
        return this;
    }

    public String getLabelLv3() {
        return labelLv3;
    }

    public LabelsInfo setLabelLv3(String labelLv3) {
        this.labelLv3 = labelLv3;
        return this;
    }

    public String getLabelLv4() {
        return labelLv4;
    }

    public LabelsInfo setLabelLv4(String labelLv4) {
        this.labelLv4 = labelLv4;
        return this;
    }

    public String getLabelLv5() {
        return labelLv5;
    }

    public LabelsInfo setLabelLv5(String labelLv5) {
        this.labelLv5 = labelLv5;
        return this;
    }

    public String getLabelLv6() {
        return labelLv6;
    }

    public LabelsInfo setLabelLv6(String labelLv6) {
        this.labelLv6 = labelLv6;
        return this;
    }

    public String getLabelLv7() {
        return labelLv7;
    }

    public LabelsInfo setLabelLv7(String labelLv7) {
        this.labelLv7 = labelLv7;
        return this;
    }

    public String getParentId() {
        return parentId;
    }

    public LabelsInfo setParentId(String parentId) {
        this.parentId = parentId;
        return this;
    }
}
