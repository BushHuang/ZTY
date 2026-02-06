package com.xh.xhcore.common.http;

public class XHUploadFilesResBean {
    private String httpResponse;

    public static class UploadResBean {
        private String url = "";
        private String md5 = "";

        public String getMd5() {
            return this.md5;
        }

        public String getUrl() {
            return this.url;
        }

        public void setMd5(String str) {
            this.md5 = str;
        }

        public void setUrl(String str) {
            this.url = str;
        }
    }

    public String getHttpResponse() {
        return this.httpResponse;
    }

    public void setHttpResponse(String str) {
        this.httpResponse = str;
    }
}
