package com.obs.services.model;

import java.util.Date;

public class DownloadFileRequest {
    private String bucketName;
    private CacheOptionEnum cacheOption;
    private String checkpointFile;
    private String downloadFile;
    private boolean enableCheckpoint;
    private String ifMatchTag;
    private Date ifModifiedSince;
    private String ifNoneMatchTag;
    private Date ifUnmodifiedSince;
    private String objectKey;
    private long partSize;
    private long progressInterval;
    private ProgressListener progressListener;
    private int taskNum;
    private long ttl;
    private String versionId;

    public DownloadFileRequest(String str, String str2) {
        this.partSize = 102400L;
        this.taskNum = 1;
        this.progressInterval = 102400L;
        this.bucketName = str;
        this.objectKey = str2;
        this.downloadFile = str2;
    }

    public DownloadFileRequest(String str, String str2, String str3) {
        this(str, str2);
        this.downloadFile = str3;
    }

    public DownloadFileRequest(String str, String str2, String str3, long j) {
        this(str, str2);
        this.downloadFile = str3;
        this.partSize = j;
    }

    public DownloadFileRequest(String str, String str2, String str3, long j, int i) {
        this(str, str2, str3, j, i, false);
    }

    public DownloadFileRequest(String str, String str2, String str3, long j, int i, boolean z) {
        this(str, str2, str3, j, i, z, null);
    }

    public DownloadFileRequest(String str, String str2, String str3, long j, int i, boolean z, String str4) {
        this(str, str2);
        this.partSize = j;
        this.taskNum = i;
        this.downloadFile = str3;
        this.enableCheckpoint = z;
        this.checkpointFile = str4;
    }

    public DownloadFileRequest(String str, String str2, String str3, long j, int i, boolean z, String str4, String str5) {
        this(str, str2);
        this.partSize = j;
        this.taskNum = i;
        this.downloadFile = str3;
        this.enableCheckpoint = z;
        this.checkpointFile = str4;
        this.versionId = str5;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public CacheOptionEnum getCacheOption() {
        return this.cacheOption;
    }

    public String getCheckpointFile() {
        return this.checkpointFile;
    }

    public String getDownloadFile() {
        return this.downloadFile;
    }

    public String getIfMatchTag() {
        return this.ifMatchTag;
    }

    public Date getIfModifiedSince() {
        return this.ifModifiedSince;
    }

    public String getIfNoneMatchTag() {
        return this.ifNoneMatchTag;
    }

    public Date getIfUnmodifiedSince() {
        return this.ifUnmodifiedSince;
    }

    public String getObjectKey() {
        return this.objectKey;
    }

    public long getPartSize() {
        return this.partSize;
    }

    public long getProgressInterval() {
        return this.progressInterval;
    }

    public ProgressListener getProgressListener() {
        return this.progressListener;
    }

    public int getTaskNum() {
        return this.taskNum;
    }

    public String getTempDownloadFile() {
        return this.downloadFile + ".tmp";
    }

    public long getTtl() {
        return this.ttl;
    }

    public String getVersionId() {
        return this.versionId;
    }

    public boolean isEnableCheckpoint() {
        return this.enableCheckpoint;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setCacheOption(CacheOptionEnum cacheOptionEnum) {
        this.cacheOption = cacheOptionEnum;
    }

    public void setCheckpointFile(String str) {
        this.checkpointFile = str;
    }

    public void setDownloadFile(String str) {
        this.downloadFile = str;
    }

    public void setEnableCheckpoint(boolean z) {
        this.enableCheckpoint = z;
    }

    public void setIfMatchTag(String str) {
        this.ifMatchTag = str;
    }

    public void setIfModifiedSince(Date date) {
        this.ifModifiedSince = date;
    }

    public void setIfNoneMatchTag(String str) {
        this.ifNoneMatchTag = str;
    }

    public void setIfUnmodifiedSince(Date date) {
        this.ifUnmodifiedSince = date;
    }

    public void setObjectKey(String str) {
        this.objectKey = str;
    }

    public void setPartSize(long j) {
        this.partSize = j;
    }

    public void setProgressInterval(long j) {
        this.progressInterval = j;
    }

    public void setProgressListener(ProgressListener progressListener) {
        this.progressListener = progressListener;
    }

    public void setTaskNum(int i) {
        if (i < 1) {
            this.taskNum = 1;
        } else if (i > 1000) {
            this.taskNum = 1000;
        } else {
            this.taskNum = i;
        }
    }

    public void setTtl(long j) {
        if (j < 0 || j > 259200) {
            j = 86400;
        }
        this.ttl = j;
    }

    public void setVersionId(String str) {
        this.versionId = str;
    }

    public String toString() {
        return "DownloadFileRequest [bucketName=" + this.bucketName + ", objectKey=" + this.objectKey + ", downloadFile=" + this.downloadFile + ", partSize=" + this.partSize + ", taskNum=" + this.taskNum + ", checkpointFile=" + this.checkpointFile + ", enableCheckpoint=" + this.enableCheckpoint + ", ifModifiedSince=" + this.ifModifiedSince + ", ifUnmodifiedSince=" + this.ifUnmodifiedSince + ", ifMatchTag=" + this.ifMatchTag + ", ifNoneMatchTag=" + this.ifNoneMatchTag + ", versionId=" + this.versionId + "]";
    }
}
