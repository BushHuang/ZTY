package com.xh.networkclient.bean;

public class FileRes extends FSCommonRes {
    private FileDTO fileDTO;

    public FileRes() {
    }

    public FileRes(FileDTO fileDTO) {
        this.fileDTO = fileDTO;
    }

    public FileDTO getFileDTO() {
        return this.fileDTO;
    }

    public void setFileDTO(FileDTO fileDTO) {
        this.fileDTO = fileDTO;
    }

    public String toString() {
        return "FileRes [fileDTO=" + this.fileDTO + "]";
    }
}
