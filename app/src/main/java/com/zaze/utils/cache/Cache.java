package com.zaze.utils.cache;

import java.util.Arrays;

class Cache {
    private byte[] bytes;
    private long createDate;
    private long keepTime;
    private String key;
    private long lastTimeMillis;
    private int type;
    private int usedNum;

    public Cache(String str, byte[] bArr, long j, int i, long j2) {
        this.key = str;
        if (bArr != null) {
            byte[] bArr2 = new byte[bArr.length];
            this.bytes = bArr2;
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        }
        this.keepTime = j;
        this.usedNum = i;
        this.lastTimeMillis = j2;
        this.createDate = j2;
    }

    public byte[] getBytes() {
        return this.bytes;
    }

    public long getCreateDate() {
        return this.createDate;
    }

    public long getKeepTime() {
        return this.keepTime;
    }

    public String getKey() {
        return this.key;
    }

    public long getLastTimeMillis() {
        return this.lastTimeMillis;
    }

    public int getUsedNum() {
        return this.usedNum;
    }

    public void increaseUsedNum() {
        this.usedNum++;
    }

    public void setBytes(byte[] bArr) {
        this.bytes = bArr;
    }

    public void setCreateDate(long j) {
        this.createDate = j;
    }

    public void setKeepTime(long j) {
        this.keepTime = j;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public void setLastTimeMillis(long j) {
        this.lastTimeMillis = j;
    }

    public void setUsedNum(int i) {
        this.usedNum = i;
    }

    public String toString() {
        return "Cache{bytes=" + Arrays.toString(this.bytes) + ", keepTime=" + this.keepTime + ", usedNum=" + this.usedNum + ", lastTimeMillis=" + this.lastTimeMillis + ", createDate=" + this.createDate + ", type=" + this.type + ", key='" + this.key + "'}";
    }

    public long updateCache(byte[] bArr, long j) {
        long length;
        long length2 = this.bytes != null ? r0.length : 0L;
        if (bArr == null || bArr.length <= 0) {
            this.bytes = null;
            length = -length2;
        } else {
            length = bArr.length - length2;
            byte[] bArr2 = new byte[bArr.length];
            this.bytes = bArr2;
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        }
        this.keepTime = j;
        increaseUsedNum();
        setLastTimeMillis(System.currentTimeMillis());
        return length;
    }
}
