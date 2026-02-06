package com.oef.services.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompressBean {

    @JsonProperty("agency")
    private String agency;

    @JsonProperty("status")
    private String status;

    public CompressBean() {
    }

    public CompressBean(String str, String str2) {
        this.status = str;
        this.agency = str2;
    }

    public String getAgency() {
        return this.agency;
    }

    public String getStatus() {
        return this.status;
    }

    public void setAgency(String str) {
        this.agency = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String toString() {
        return "CompressBean [status=" + this.status + ", agency=" + this.agency + "]";
    }
}
