package com.obs.services.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReadAheadResult extends HeaderResponse {

    @JsonProperty("bucket")
    private String bucketName;

    @JsonProperty("prefix")
    private String prefix;

    @JsonProperty("taskID")
    private String taskId;

    public ReadAheadResult() {
    }

    public ReadAheadResult(String str, String str2, String str3) {
        this.bucketName = str;
        this.prefix = str2;
        this.taskId = str3;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getTaskId() {
        return this.taskId;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setPrefix(String str) {
        this.prefix = str;
    }

    public void setTaskId(String str) {
        this.taskId = str;
    }

    @Override
    public String toString() {
        return "ReadAheadResult [bucketName=" + this.bucketName + ", prefix=" + this.prefix + ", taskId=" + this.taskId + "]";
    }
}
