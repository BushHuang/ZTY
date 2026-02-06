package com.obs.services.model;

import java.io.Serializable;

public class PartEtag implements Serializable {
    private static final long serialVersionUID = -2946156755118245847L;
    private String etag;
    private Integer partNumber;

    public PartEtag() {
    }

    public PartEtag(String str, Integer num) {
        this.etag = str;
        this.partNumber = num;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof PartEtag)) {
            PartEtag partEtag = (PartEtag) obj;
            if (partEtag.etag.equals(this.etag) && partEtag.partNumber.equals(this.partNumber)) {
                return true;
            }
        }
        return false;
    }

    public String getEtag() {
        return this.etag;
    }

    public Integer getPartNumber() {
        return this.partNumber;
    }

    @Deprecated
    public String geteTag() {
        return getEtag();
    }

    public int hashCode() {
        String str = this.etag;
        return (((str == null ? 0 : str.hashCode()) + 31) * 31) + this.partNumber.intValue();
    }

    public void setEtag(String str) {
        this.etag = str;
    }

    public void setPartNumber(Integer num) {
        this.partNumber = num;
    }

    @Deprecated
    public void seteTag(String str) {
        setEtag(str);
    }

    public String toString() {
        return "PartEtag [etag=" + this.etag + ", partNumber=" + this.partNumber + "]";
    }
}
