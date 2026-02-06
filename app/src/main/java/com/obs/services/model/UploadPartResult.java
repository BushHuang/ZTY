package com.obs.services.model;

public class UploadPartResult extends HeaderResponse {
    private String etag;
    private int partNumber;

    public String getEtag() {
        return this.etag;
    }

    public int getPartNumber() {
        return this.partNumber;
    }

    public void setEtag(String str) {
        this.etag = str;
    }

    public void setPartNumber(int i) {
        this.partNumber = i;
    }

    @Override
    public String toString() {
        return "UploadPartResult [partNumber=" + this.partNumber + ", etag=" + this.etag + "]";
    }
}
