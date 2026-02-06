package com.obs.services.model;

import java.util.Date;

public class CopyPartResult extends HeaderResponse {
    private String etag;
    private Date lastModified;
    private int partNumber;

    public CopyPartResult(int i, String str, Date date) {
        this.partNumber = i;
        this.etag = str;
        this.lastModified = date;
    }

    public String getEtag() {
        return this.etag;
    }

    public Date getLastModified() {
        return this.lastModified;
    }

    public int getPartNumber() {
        return this.partNumber;
    }

    @Override
    public String toString() {
        return "CopyPartResult [partNumber=" + this.partNumber + ", etag=" + this.etag + ", lastModified=" + this.lastModified + "]";
    }
}
