package com.xh.xhcore.common.statistic;

import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.util.XHAppUtil;
import com.xuehai.provider.core.db.CPVDUserData;
import com.xuehai.provider.core.dto.CPVDUser;

public class BuryEvent {
    private String appPkg;
    private String appVer;
    private String data;
    private String name;
    private long schoolId;
    private long time;
    private int type;
    private long uid;
    private String uuid;

    public BuryEvent() {
        this(true);
    }

    public BuryEvent(int i, String str, String str2) {
        this.name = "";
        this.data = "";
        this.time = System.currentTimeMillis();
        initCommon();
        this.type = i;
        this.name = str;
        this.data = str2;
    }

    public BuryEvent(int i, String str, String str2, long j) {
        this.name = "";
        this.data = "";
        this.time = System.currentTimeMillis();
        initCommon();
        this.type = i;
        this.name = str;
        this.data = str2;
        this.time = j;
    }

    public BuryEvent(String str) {
        this.name = "";
        this.data = "";
        this.time = System.currentTimeMillis();
        initCommon();
        this.name = str;
    }

    public BuryEvent(String str, String str2) {
        this.name = "";
        this.data = "";
        this.time = System.currentTimeMillis();
        initCommon();
        this.name = str;
        this.data = str2;
    }

    public BuryEvent(boolean z) {
        this.name = "";
        this.data = "";
        this.time = System.currentTimeMillis();
        if (z) {
            initCommon();
        }
    }

    public String getAppPkg() {
        return this.appPkg;
    }

    public String getAppVer() {
        return this.appVer;
    }

    public String getData() {
        return this.data;
    }

    public String getName() {
        return this.name;
    }

    public long getSchoolId() {
        return this.schoolId;
    }

    public int getType() {
        return this.type;
    }

    public long getUid() {
        return this.uid;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void initCommon() {
        CPVDUser user = CPVDUserData.getUser(XhBaseApplication.mContext);
        if (user != null) {
            setUid(user.getUserId());
            setUuid(user.getDeviceNum());
            setSchoolId(user.getSchoolId());
        }
        setAppPkg(XHAppUtil.getPackageName());
        setAppVer(XHAppUtil.getVersionName());
    }

    public BuryEvent setAppPkg(String str) {
        this.appPkg = str;
        return this;
    }

    public BuryEvent setAppVer(String str) {
        this.appVer = str;
        return this;
    }

    public BuryEvent setData(String str) {
        this.data = str;
        return this;
    }

    public BuryEvent setName(String str) {
        this.name = str;
        return this;
    }

    public BuryEvent setSchoolId(long j) {
        this.schoolId = j;
        return this;
    }

    public BuryEvent setType(int i) {
        this.type = i;
        return this;
    }

    public BuryEvent setUid(long j) {
        this.uid = j;
        return this;
    }

    public BuryEvent setUuid(String str) {
        this.uuid = str;
        return this;
    }

    public String toString() {
        return "BuryEvent{uid=" + this.uid + ", uuid='" + this.uuid + "', schoolId=" + this.schoolId + ", appPkg='" + this.appPkg + "', appVer='" + this.appVer + "', type=" + this.type + ", name='" + this.name + "', data='" + this.data + "', time=" + this.time + '}';
    }
}
