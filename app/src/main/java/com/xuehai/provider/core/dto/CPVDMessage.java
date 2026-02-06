package com.xuehai.provider.core.dto;

public class CPVDMessage {
    private long id;
    private String packageName;
    private int unReadCount;
    private long userId;

    public CPVDMessage() {
    }

    public CPVDMessage(long j, long j2, String str, int i) {
        this.id = j;
        this.userId = j2;
        this.packageName = str;
        this.unReadCount = i;
    }

    public CPVDMessage(long j, String str, int i) {
        this.userId = j;
        this.packageName = str;
        this.unReadCount = i;
    }

    public long getId() {
        return this.id;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public int getUnReadCount() {
        return this.unReadCount;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setId(long j) {
        this.id = j;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public void setUnReadCount(int i) {
        this.unReadCount = i;
    }

    public void setUserId(long j) {
        this.userId = j;
    }

    public String toString() {
        return "CPVDMessage{id=" + this.id + ", userId=" + this.userId + ", packageName='" + this.packageName + "', unReadCount=" + this.unReadCount + '}';
    }
}
