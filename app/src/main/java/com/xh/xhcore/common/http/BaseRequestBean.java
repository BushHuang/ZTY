package com.xh.xhcore.common.http;

@Deprecated
public class BaseRequestBean {
    private HttpRespone httpResponse;

    public class HttpRespone {
        private String D;
        private String I;
        private String M;
        private int R;
        private int Z;

        public HttpRespone() {
        }

        public String getD() {
            return this.D;
        }

        public String getI() {
            return this.I;
        }

        public String getM() {
            return this.M;
        }

        public int getR() {
            return this.R;
        }

        public int getZ() {
            return this.Z;
        }

        public void setD(String str) {
            this.D = str;
        }

        public void setI(String str) {
            this.I = str;
        }

        public void setM(String str) {
            this.M = str;
        }

        public void setR(int i) {
            this.R = i;
        }

        public void setZ(int i) {
            this.Z = i;
        }
    }

    public HttpRespone getHttpRespone() {
        return this.httpResponse;
    }

    public void setHttpRespone(HttpRespone httpRespone) {
        this.httpResponse = httpRespone;
    }
}
