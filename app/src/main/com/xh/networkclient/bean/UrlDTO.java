package com.xh.networkclient.bean;

import java.io.Serializable;

public class UrlDTO implements Serializable {
    private static final long serialVersionUID = 4868706122996421626L;
    private String url;

    public UrlDTO() {
    }

    public UrlDTO(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "UrlDTO{" +
                "url='" + url + '\'' +
                '}';
    }
}
