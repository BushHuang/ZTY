package com.obs.services.model;

import java.util.Date;

public class Multipart extends HeaderResponse {
    private String etag;
    private Date lastModified;
    private Integer partNumber;
    private Long size;

    public Multipart() {
    }

    public Multipart(Integer num, Date date, String str, Long l) {
        this.partNumber = num;
        this.lastModified = date;
        this.etag = str;
        this.size = l;
    }

    public String getEtag() {
        return this.etag;
    }

    public Date getLastModified() {
        return this.lastModified;
    }

    public Integer getPartNumber() {
        return this.partNumber;
    }

    public Long getSize() {
        return this.size;
    }

    @Override
    public String toString() {
        return "Multipart [partNumber=" + this.partNumber + ", lastModified=" + this.lastModified + ", etag=" + this.etag + ", size=" + this.size + "]";
    }
}
