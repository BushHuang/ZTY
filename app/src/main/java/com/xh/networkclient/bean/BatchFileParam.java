package com.xh.networkclient.bean;

import java.util.List;

public class BatchFileParam {
    private List<UrlDTO> urls;

    public BatchFileParam() {
    }

    public BatchFileParam(List<UrlDTO> list) {
        this.urls = list;
    }

    public List<UrlDTO> getUrls() {
        return this.urls;
    }

    public void setUrls(List<UrlDTO> list) {
        this.urls = list;
    }

    public String toString() {
        return "BatchFileParam{urls=" + this.urls + '}';
    }
}
