package com.xh.networkclient.bean;

public class UploadFileRes extends FSCommonRes {
    private UploadFileDTO uploadFileDTO;

    public UploadFileRes() {
    }

    public UploadFileRes(UploadFileDTO uploadFileDTO) {
        this.uploadFileDTO = uploadFileDTO;
    }

    public UploadFileDTO getUploadFileDTO() {
        return this.uploadFileDTO;
    }

    public void setUploadFileDTO(UploadFileDTO uploadFileDTO) {
        this.uploadFileDTO = uploadFileDTO;
    }

    public String toString() {
        return "UploadFileRes [uploadFileDTO=" + this.uploadFileDTO + "]";
    }
}
