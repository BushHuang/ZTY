package com.xh.xhcore.common.http;

import java.util.List;

public class MicroServerResponse {
    private List<MicroServerDto> microServerDtoList;
    private String serverType;
    private long updateTime;

    public static class MicroServerDto {
        private String dirLocation;
        private String domainOrIp;
        private int index;
        private int mode;
        private int port;
        private List<Integer> schList;
        private long schoolId;
        private int serverId;
        private String serverType;

        public String getDirLocation() {
            return this.dirLocation;
        }

        public String getDomainOrIp() {
            return this.domainOrIp;
        }

        public int getIndex() {
            return this.index;
        }

        public int getMode() {
            return this.mode;
        }

        public int getPort() {
            return this.port;
        }

        public List<Integer> getSchList() {
            return this.schList;
        }

        public long getSchoolId() {
            return this.schoolId;
        }

        public int getServerId() {
            return this.serverId;
        }

        public String getServerType() {
            return this.serverType;
        }

        public void setDirLocation(String str) {
            this.dirLocation = str;
        }

        public void setDomainOrIp(String str) {
            this.domainOrIp = str;
        }

        public void setIndex(int i) {
            this.index = i;
        }

        public void setMode(int i) {
            this.mode = i;
        }

        public void setPort(int i) {
            this.port = i;
        }

        public void setSchList(List<Integer> list) {
            this.schList = list;
        }

        public void setSchoolId(long j) {
            this.schoolId = j;
        }

        public void setServerId(int i) {
            this.serverId = i;
        }

        public void setServerType(String str) {
            this.serverType = str;
        }
    }

    public List<MicroServerDto> getMicroServerDtoList() {
        return this.microServerDtoList;
    }

    public String getServerType() {
        return this.serverType;
    }

    public long getUpdateTime() {
        return this.updateTime;
    }

    public void setMicroServerDtoList(List<MicroServerDto> list) {
        this.microServerDtoList = list;
    }

    public void setServerType(String str) {
        this.serverType = str;
    }

    public void setUpdateTime(long j) {
        this.updateTime = j;
    }

    public String toString() {
        return "MicroServerResponse{serverType='" + this.serverType + "', updateTime=" + this.updateTime + ", microServerDtoList=" + this.microServerDtoList + '}';
    }
}
