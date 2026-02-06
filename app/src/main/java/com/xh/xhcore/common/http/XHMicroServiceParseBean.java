package com.xh.xhcore.common.http;

import com.xh.xhcore.common.http.strategy.LogUtils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class XHMicroServiceParseBean {
    private List<XHMicroServiceBodyBean> XsList = new ArrayList();
    private int iUpdateTime;
    private int iVersion;
    private String sServiceType;

    private LinkedHashMap<Integer, XHMicroServiceBodyBean> toServerIdList() {
        LinkedHashMap<Integer, XHMicroServiceBodyBean> linkedHashMap = new LinkedHashMap<>();
        for (XHMicroServiceBodyBean xHMicroServiceBodyBean : getXsList()) {
            LogUtils.d(XHMicroServer.class.getSimpleName(), "old micro server list put = " + xHMicroServiceBodyBean);
            linkedHashMap.put(Integer.valueOf(xHMicroServiceBodyBean.getIServerId()), xHMicroServiceBodyBean);
        }
        return linkedHashMap;
    }

    public List<XHMicroServiceBodyBean> getXsList() {
        return this.XsList;
    }

    public int getiUpdateTime() {
        return this.iUpdateTime;
    }

    public int getiVersion() {
        return this.iVersion;
    }

    public String getsServiceType() {
        return this.sServiceType;
    }

    public void increaseUpdate(XHMicroServiceParseBean xHMicroServiceParseBean) {
        if (xHMicroServiceParseBean == null) {
            xHMicroServiceParseBean = new XHMicroServiceParseBean();
        }
        List<XHMicroServiceBodyBean> xsList = getXsList();
        LinkedHashMap<Integer, XHMicroServiceBodyBean> serverIdList = xHMicroServiceParseBean.toServerIdList();
        for (XHMicroServiceBodyBean xHMicroServiceBodyBean : xsList) {
            LogUtils.d(XHMicroServer.class.getSimpleName(), "increase micro server list = " + xHMicroServiceBodyBean);
            if (xHMicroServiceBodyBean.getiMode() == 1) {
                serverIdList.remove(Integer.valueOf(xHMicroServiceBodyBean.getIServerId()));
            } else {
                serverIdList.put(Integer.valueOf(xHMicroServiceBodyBean.getIServerId()), xHMicroServiceBodyBean);
            }
        }
        setXsList(new ArrayList(serverIdList.values()));
    }

    public void setXsList(List<XHMicroServiceBodyBean> list) {
        this.XsList = list;
    }

    public XHMicroServiceParseBean setiUpdateTime(int i) {
        this.iUpdateTime = i;
        return this;
    }

    public void setiVersion(int i) {
        this.iVersion = i;
    }

    public void setsServiceType(String str) {
        this.sServiceType = str;
    }

    public String toString() {
        return "MicroServiceParseBean{iVersion=" + this.iVersion + ", sServiceType='" + this.sServiceType + "', XsList=" + this.XsList + '}';
    }
}
