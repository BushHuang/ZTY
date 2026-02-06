package com.xh.networkclient.bean;

import java.io.Serializable;

public class UploadFileDTO implements Serializable {
    private static final long serialVersionUID = 4528484731036424038L;
    private String fileId;

    public UploadFileDTO() {
    }

    public UploadFileDTO(String str) {
        this.fileId = str;
    }

    public String getFileId() {
        return this.fileId;
    }

    public void setFileId(String str) {
        this.fileId = str;
    }

    public String toString() {
        return "UploadFileDTO [fileId=" + this.fileId + "]";
    }
}
