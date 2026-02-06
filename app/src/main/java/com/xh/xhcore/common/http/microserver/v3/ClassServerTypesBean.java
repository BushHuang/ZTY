package com.xh.xhcore.common.http.microserver.v3;

import com.xh.xhcore.common.http.XHMicroServerV2;
import java.util.ArrayList;
import java.util.List;

public class ClassServerTypesBean {
    private int classId;
    private List<XHMicroServerV2.MicroServerReqBeanV2.ServerTypeBean> serverTypes;

    public ClassServerTypesBean() {
        this.serverTypes = new ArrayList();
    }

    public ClassServerTypesBean(int i, List<XHMicroServerV2.MicroServerReqBeanV2.ServerTypeBean> list) {
        this.serverTypes = new ArrayList();
        this.classId = i;
        this.serverTypes = list;
    }

    public int getClassId() {
        return this.classId;
    }

    public List<XHMicroServerV2.MicroServerReqBeanV2.ServerTypeBean> getServerTypes() {
        return this.serverTypes;
    }

    public ClassServerTypesBean setClassId(int i) {
        this.classId = i;
        return this;
    }

    public ClassServerTypesBean setServerTypes(List<XHMicroServerV2.MicroServerReqBeanV2.ServerTypeBean> list) {
        this.serverTypes = list;
        return this;
    }
}
