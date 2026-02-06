package com.xh.networkclient.bean;

import java.util.List;

public class BatchFileParam {

    private List<UrlDTO> urls;


    public BatchFileParam() {
    }

    public BatchFileParam(List<UrlDTO> urls) {
        this.urls = urls;
    }

    public List<UrlDTO> getUrls() {
        return urls;
    }

    public void setUrls(List<UrlDTO> urls) {
        this.urls = urls;
    }

    @Override
    public String toString() {
        return "BatchFileParam{" +
                "urls=" + urls +
                '}';
    }
}
