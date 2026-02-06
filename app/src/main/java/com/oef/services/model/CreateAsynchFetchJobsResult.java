package com.oef.services.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.obs.services.model.HeaderResponse;

public class CreateAsynchFetchJobsResult extends HeaderResponse {

    @JsonProperty("Wait")
    private int Wait;

    @JsonProperty("id")
    private String id;

    public CreateAsynchFetchJobsResult() {
    }

    public CreateAsynchFetchJobsResult(String str, int i) {
        setId(str);
        setWait(i);
    }

    public String getId() {
        return this.id;
    }

    public int getWait() {
        return this.Wait;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setWait(int i) {
        this.Wait = i;
    }

    @Override
    public String toString() {
        return "CreateAsynchFetchJobsResult [id=" + this.id + ", Wait=" + this.Wait + "]";
    }
}
