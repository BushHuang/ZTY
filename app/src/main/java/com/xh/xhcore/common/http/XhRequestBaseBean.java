package com.xh.xhcore.common.http;

import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.util.XHDeviceUtil;

public class XhRequestBaseBean {
    private String D = XHDeviceUtil.getUUID(XhBaseApplication.mContext);
    private String V = XhBaseApplication.sVersionName;
    private String P = XhBaseApplication.sPackageName;
    private String C = XhBaseApplication.sAppId;
    private String M = XhBaseApplication.sUserId;
    private String S = XhBaseApplication.sSessionId;

    public String getC() {
        return this.C;
    }

    public String getD() {
        return this.D;
    }

    public String getM() {
        return this.M;
    }

    public String getP() {
        return XhBaseApplication.sPackageName;
    }

    public String getS() {
        return this.S;
    }

    public String getV() {
        return this.V;
    }

    public void setC(String str) {
        this.C = str;
    }

    public void setD(String str) {
        this.D = str;
    }

    public void setM(String str) {
        this.M = str;
    }

    public void setP(String str) {
        this.P = str;
    }

    public void setS(String str) {
        this.S = str;
    }

    public void setV(String str) {
        this.V = str;
    }
}
