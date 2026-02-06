package com.xh.xhcore.common.http;

public class XHUploadResBean {
    public String message;
    public int status;
    public UploadFileDTOBean uploadFileDTO;

    public static class UploadFileDTOBean {
        public String fileId;
    }
}
