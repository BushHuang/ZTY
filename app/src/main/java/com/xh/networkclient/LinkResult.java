package com.xh.networkclient;

public class LinkResult implements Comparable<LinkResult> {
    private String ip;
    private double time;

    public LinkResult(String str, double d) {
        this.ip = str;
        this.time = d;
    }

    @Override
    public int compareTo(LinkResult linkResult) {
        double d = this.time;
        if (d == 0.0d) {
            return 1;
        }
        double d2 = linkResult.time;
        if (d > d2) {
            return 1;
        }
        if (d < d2) {
            return -1;
        }
        return this.ip.compareTo(linkResult.ip);
    }

    public String getIp() {
        return this.ip;
    }

    public double getTime() {
        return this.time;
    }

    public void setIp(String str) {
        this.ip = str;
    }

    public void setTime(double d) {
        this.time = d;
    }
}
