package com.xh.xhcore.common.http;

public class MicroServiceReqBean {
    private String desc = "";
    private String[] microServiceUrls;
    private int schoolId;
    private String serverTypes;
    private long updateTime;

    public XHMicroServiceReqBean covert() {
        XHMicroServiceReqBean xHMicroServiceReqBean = new XHMicroServiceReqBean();
        xHMicroServiceReqBean.setiSchoolId(this.schoolId);
        xHMicroServiceReqBean.setsServiceType(this.serverTypes);
        xHMicroServiceReqBean.setiUpdateTime(this.updateTime);
        xHMicroServiceReqBean.setMicroServiceUrls(this.microServiceUrls);
        xHMicroServiceReqBean.setDesc(this.desc);
        return xHMicroServiceReqBean;
    }

    public String getDesc() {
        return this.desc;
    }

    public String[] getMicroServiceUrls() {
        return this.microServiceUrls;
    }

    public int getSchoolId() {
        return this.schoolId;
    }

    public String getServerTypes() {
        return this.serverTypes;
    }

    public long getUpdateTime() {
        return this.updateTime;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public void setMicroServiceUrls(String[] strArr) {
        this.microServiceUrls = strArr;
    }

    public void setSchoolId(int i) {
        this.schoolId = i;
    }

    public void setServerTypes(String str) {
        this.serverTypes = str;
    }

    public void setUpdateTime(long j) {
        this.updateTime = j;
    }
}
