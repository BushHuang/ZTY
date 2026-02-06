package com.obs.services.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReadAheadQueryResult extends HeaderResponse {

    @JsonProperty("bucket")
    private String bucketName;

    @JsonProperty("consumedTime")
    private long consumedTime;

    @JsonProperty("finishedObjectNum")
    private long finishedObjectNum;

    @JsonProperty("finishedSize")
    private long finishedSize;

    @JsonProperty("prefix")
    private String prefix;

    @JsonProperty("status")
    private String status;

    public ReadAheadQueryResult() {
    }

    public ReadAheadQueryResult(String str, String str2, long j, long j2, long j3, String str3) {
        this.bucketName = str;
        this.prefix = str2;
        this.consumedTime = j;
        this.finishedObjectNum = j2;
        this.finishedSize = j3;
        this.status = str3;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public long getConsumedTime() {
        return this.consumedTime;
    }

    public long getFinishedObjectNum() {
        return this.finishedObjectNum;
    }

    public long getFinishedSize() {
        return this.finishedSize;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getStatus() {
        return this.status;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setConsumedTime(long j) {
        this.consumedTime = j;
    }

    public void setFinishedObjectNum(long j) {
        this.finishedObjectNum = j;
    }

    public void setFinishedSize(long j) {
        this.finishedSize = j;
    }

    public void setPrefix(String str) {
        this.prefix = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    @Override
    public String toString() {
        return "ReadAheadQueryResult [bucketName=" + this.bucketName + ", prefix=" + this.prefix + ", consumedTime=" + this.consumedTime + ", finishedObjectNum=" + this.finishedObjectNum + ", finishedSize=" + this.finishedSize + ", status=" + this.status + "]";
    }
}
