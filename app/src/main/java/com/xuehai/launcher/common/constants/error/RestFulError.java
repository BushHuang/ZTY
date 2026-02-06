package com.xuehai.launcher.common.constants.error;

public class RestFulError {
    private int code;
    private String description;
    private String[] errors;
    private String msg;
    private String traceId;

    public int getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    public String[] getErrors() {
        return this.errors;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getTraceId() {
        return this.traceId;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setErrors(String[] strArr) {
        this.errors = strArr;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public void setTraceId(String str) {
        this.traceId = str;
    }
}
