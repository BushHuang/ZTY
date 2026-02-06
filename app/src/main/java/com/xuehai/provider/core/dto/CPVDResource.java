package com.xuehai.provider.core.dto;

public class CPVDResource {
    public static final int STATUS_DEFAULT = 0;
    public static final int STATUS_NEED_UPDATE = 1;
    public static final int UNKNOW = -1;
    private String dataCode;
    private int status;

    public CPVDResource() {
    }

    public CPVDResource(String str, int i) {
        this.dataCode = str;
        this.status = i;
    }

    public String getDataCode() {
        return this.dataCode;
    }

    public int getStatus() {
        return this.status;
    }

    public void setDataCode(String str) {
        this.dataCode = str;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String toString() {
        return "CPVDResource{dataCode='" + this.dataCode + "', status=" + this.status + '}';
    }
}
