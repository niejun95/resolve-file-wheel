package com.example.constant;

public enum DocumentType {
    TXT("txt"),
    EXCEL("excel");


    DocumentType(String desc) {
        this.desc = desc;
    }

    public String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
