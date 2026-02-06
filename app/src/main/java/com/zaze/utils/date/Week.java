package com.zaze.utils.date;

public enum Week {
    MONDAY("星期一", "周一", "Monday", "Mon.", 1),
    TUESDAY("星期二", "周二", "Tuesday", "Tues.", 2),
    WEDNESDAY("星期三", "周三", "Wednesday", "Wed.", 3),
    THURSDAY("星期四", "周四", "Thursday", "Thur.", 4),
    FRIDAY("星期五", "周五", "Friday", "Fri.", 5),
    SATURDAY("星期六", "周六", "Saturday", "Sat.", 6),
    SUNDAY("星期日", "周日", "Sunday", "Sun.", 7);


    String f436cn;
    String cn_s;
    String en;
    String en_s;
    int number;

    Week(String str, String str2, String str3, String str4, int i) {
        this.f436cn = str;
        this.cn_s = str2;
        this.en = str3;
        this.en_s = str4;
        this.number = i;
    }

    public String getCn() {
        return this.f436cn;
    }

    public String getCn_s() {
        return this.cn_s;
    }

    public String getEn() {
        return this.en;
    }

    public String getEn_s() {
        return this.en_s;
    }

    public int getNumber() {
        return this.number;
    }

    public void setCn(String str) {
        this.f436cn = str;
    }

    public void setCn_s(String str) {
        this.cn_s = str;
    }

    public void setEn(String str) {
        this.en = str;
    }

    public void setEn_s(String str) {
        this.en_s = str;
    }

    public void setNumber(int i) {
        this.number = i;
    }
}
