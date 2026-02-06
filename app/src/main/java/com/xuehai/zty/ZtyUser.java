package com.xuehai.zty;

public class ZtyUser {
    private String activateDate;
    private Integer grade;
    private String mobile;
    private String openId;
    private Integer sex;
    private String userName;

    public String getActivateDate() {
        return this.activateDate;
    }

    public Integer getGrade() {
        return this.grade;
    }

    public String getMobile() {
        return this.mobile;
    }

    public String getOpenId() {
        return this.openId;
    }

    public Integer getSex() {
        return this.sex;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setActivateDate(String str) {
        this.activateDate = str;
    }

    public void setGrade(Integer num) {
        this.grade = num;
    }

    public void setMobile(String str) {
        this.mobile = str;
    }

    public void setOpenId(String str) {
        this.openId = str;
    }

    public void setSex(Integer num) {
        this.sex = num;
    }

    public void setUserName(String str) {
        this.userName = str;
    }
}
