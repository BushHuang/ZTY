package com.xuehai.provider.core.dto;

import java.util.Arrays;

public class CPVDUser {
    private String accessToken;
    private String accountName;
    private String activateDate;
    private String avatarMd5;
    private String avatarPath;

    @Deprecated
    private int[] classIds;
    private String deviceNum;
    private int displaySchoolId;
    private String displaySchoolName;
    private int environment;
    private Integer grade;
    private int groupId;
    private String host;
    private String hotLine;
    private long id;
    private String ip1;
    private String ip2;
    private String ip3;
    private int loginStatus;
    private long[] longClassIds;
    private String[] microRestfulArray;
    private String[] microServerArray;
    private String microService;
    private String openId;
    private String parentMobile;
    private String password;
    private String phone;
    private String roles;
    private int schoolId;
    private String schoolName;
    private Integer sex;
    private long userId;
    private int userType;
    private String username;
    private boolean schoolFabricated = false;
    private boolean training = false;
    private boolean dynamicCodeLogin = false;
    private boolean custom = false;

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public String getActivateDate() {
        return this.activateDate;
    }

    public String getAvatarMd5() {
        return this.avatarMd5;
    }

    public String getAvatarPath() {
        return this.avatarPath;
    }

    @Deprecated
    public int[] getClassIds() {
        return this.classIds;
    }

    public String getDeviceNum() {
        return this.deviceNum;
    }

    public int getDisplaySchoolId() {
        return this.displaySchoolId;
    }

    public String getDisplaySchoolName() {
        return this.displaySchoolName;
    }

    public int getEnvironment() {
        return this.environment;
    }

    public Integer getGrade() {
        return this.grade;
    }

    public int getGroupId() {
        return this.groupId;
    }

    public String getHost() {
        return this.host;
    }

    public String getHotLine() {
        return this.hotLine;
    }

    public long getId() {
        return this.id;
    }

    public String getIp1() {
        return this.ip1;
    }

    public String getIp2() {
        return this.ip2;
    }

    public String getIp3() {
        return this.ip3;
    }

    public int getLoginStatus() {
        return this.loginStatus;
    }

    public long[] getLongClassIds() {
        int[] iArr;
        long[] jArr = this.longClassIds;
        if ((jArr == null || jArr.length == 0) && (iArr = this.classIds) != null && iArr.length > 0) {
            int[] iArr2 = (int[]) iArr.clone();
            this.longClassIds = new long[iArr2.length];
            for (int i = 0; i < iArr2.length; i++) {
                this.longClassIds[i] = iArr2[i];
            }
        }
        return this.longClassIds;
    }

    public String[] getMicroRestfulArray() {
        return this.microRestfulArray;
    }

    public String[] getMicroServerArray() {
        return this.microServerArray;
    }

    public String getMicroService() {
        return this.microService;
    }

    public String getOpenId() {
        return this.openId;
    }

    public String getParentMobile() {
        return this.parentMobile;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getRoles() {
        return this.roles;
    }

    public int getSchoolId() {
        return this.schoolId;
    }

    public String getSchoolName() {
        return this.schoolName;
    }

    public Integer getSex() {
        return this.sex;
    }

    public long getUserId() {
        return this.userId;
    }

    public int getUserType() {
        return this.userType;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean isCustom() {
        return this.custom;
    }

    public boolean isDynamicCodeLogin() {
        return this.dynamicCodeLogin;
    }

    public boolean isSchoolFabricated() {
        return this.schoolFabricated;
    }

    public boolean isTocUser() {
        return isCustom();
    }

    public boolean isTraining() {
        return this.training;
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public void setAccountName(String str) {
        this.accountName = str;
    }

    public void setActivateDate(String str) {
        this.activateDate = str;
    }

    public void setAvatarMd5(String str) {
        this.avatarMd5 = str;
    }

    public void setAvatarPath(String str) {
        this.avatarPath = str;
    }

    @Deprecated
    public void setClassIds(int[] iArr) {
        this.classIds = iArr;
    }

    public void setCustom(boolean z) {
        this.custom = z;
    }

    public void setDeviceNum(String str) {
        this.deviceNum = str;
    }

    public void setDisplaySchoolId(int i) {
        this.displaySchoolId = i;
    }

    public void setDisplaySchoolName(String str) {
        this.displaySchoolName = str;
    }

    public void setDynamicCodeLogin(boolean z) {
        this.dynamicCodeLogin = z;
    }

    public void setEnvironment(int i) {
        this.environment = i;
    }

    public void setGrade(Integer num) {
        this.grade = num;
    }

    public void setGroupId(int i) {
        this.groupId = i;
    }

    public void setHost(String str) {
        this.host = str;
    }

    public void setHotLine(String str) {
        this.hotLine = str;
    }

    public void setId(long j) {
        this.id = j;
    }

    public void setIp1(String str) {
        this.ip1 = str;
    }

    public void setIp2(String str) {
        this.ip2 = str;
    }

    public void setIp3(String str) {
        this.ip3 = str;
    }

    public void setLoginStatus(int i) {
        this.loginStatus = i;
    }

    public void setLongClassIds(long[] jArr) {
        this.longClassIds = jArr;
    }

    public void setMicroRestfulArray(String[] strArr) {
        this.microRestfulArray = strArr;
    }

    public void setMicroServerArray(String[] strArr) {
        this.microServerArray = strArr;
    }

    public void setMicroService(String str) {
        this.microService = str;
    }

    public void setOpenId(String str) {
        this.openId = str;
    }

    public void setParentMobile(String str) {
        this.parentMobile = str;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public void setPhone(String str) {
        this.phone = str;
    }

    public void setRoles(String str) {
        this.roles = str;
    }

    public void setSchoolFabricated(boolean z) {
        this.schoolFabricated = z;
    }

    public void setSchoolId(int i) {
        this.schoolId = i;
    }

    public void setSchoolName(String str) {
        this.schoolName = str;
    }

    public void setSex(Integer num) {
        this.sex = num;
    }

    public void setTraining(boolean z) {
        this.training = z;
    }

    public void setUserId(long j) {
        this.userId = j;
    }

    public void setUserType(int i) {
        this.userType = i;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public String toString() {
        return "CPVDUser{id=" + this.id + ", userId=" + this.userId + ", username='" + this.username + "', userType=" + this.userType + ", accountName='" + this.accountName + "', password='" + this.password + "', schoolId=" + this.schoolId + ", schoolName='" + this.schoolName + "', accessToken='" + this.accessToken + "', phone='" + this.phone + "', groupId=" + this.groupId + ", host='" + this.host + "', ip1='" + this.ip1 + "', ip2='" + this.ip2 + "', ip3='" + this.ip3 + "', environment=" + this.environment + ", microService='" + this.microService + "', roles='" + this.roles + "', deviceNum='" + this.deviceNum + "', loginStatus=" + this.loginStatus + ", hotLine='" + this.hotLine + "', microServerArray=" + Arrays.toString(this.microServerArray) + ", microRestfulArray=" + Arrays.toString(this.microRestfulArray) + ", classIds=" + Arrays.toString(this.classIds) + ", avatarPath='" + this.avatarPath + "', avatarMd5='" + this.avatarMd5 + "', schoolFabricated=" + this.schoolFabricated + ", displaySchoolId=" + this.displaySchoolId + ", displaySchoolName='" + this.displaySchoolName + "', training=" + this.training + ", dynamicCodeLogin=" + this.dynamicCodeLogin + '}';
    }
}
