package com.xh.networkclient.bean;

import java.io.Serializable;
import java.util.Arrays;

public class FileDTO implements Serializable {
    private static final long serialVersionUID = -1995887501435219261L;
    private String fileId;
    private String[] url;

    public FileDTO() {
    }

    public FileDTO(String str, String[] strArr) {
        this.fileId = str;
        this.url = strArr;
    }

    public String getFileId() {
        return this.fileId;
    }

    public String[] getUrl() {
        return this.url;
    }

    public void setFileId(String str) {
        this.fileId = str;
    }

    public void setUrl(String[] strArr) {
        this.url = strArr;
    }

    public String toString() {
        return "FileDTO [fileId=" + this.fileId + ", url=" + Arrays.toString(this.url) + "]";
    }
}
