package com.xh.networkclient.bean;

import java.io.Serializable;

public class UrlDTO implements Serializable {
    private static final long serialVersionUID = 4868706122996421626L;
    private String url;

    public UrlDTO() {
    }

    public UrlDTO(String str) {
        this.url = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String toString() {
        return "UrlDTO{url='" + this.url + "'}";
    }
}
