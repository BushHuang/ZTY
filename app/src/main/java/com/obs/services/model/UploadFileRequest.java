package com.obs.services.model;

public class UploadFileRequest extends PutObjectBasicRequest {
    private String checkpointFile;
    private boolean enableCheckSum;
    private boolean enableCheckpoint;
    private ObjectMetadata objectMetadata;
    private long partSize;
    private long progressInterval;
    private ProgressListener progressListener;
    private int taskNum;
    private String uploadFile;

    public UploadFileRequest(String str, String str2) {
        this.partSize = 9437184L;
        this.taskNum = 1;
        this.enableCheckpoint = false;
        this.enableCheckSum = false;
        this.progressInterval = 102400L;
        this.bucketName = str;
        this.objectKey = str2;
    }

    public UploadFileRequest(String str, String str2, String str3) {
        this(str, str2);
        this.uploadFile = str3;
    }

    public UploadFileRequest(String str, String str2, String str3, long j) {
        this(str, str2, str3);
        this.partSize = j;
    }

    public UploadFileRequest(String str, String str2, String str3, long j, int i) {
        this(str, str2, str3, j);
        this.taskNum = i;
    }

    public UploadFileRequest(String str, String str2, String str3, long j, int i, boolean z) {
        this(str, str2, str3, j, i, z, null);
    }

    public UploadFileRequest(String str, String str2, String str3, long j, int i, boolean z, String str4) {
        this(str, str2);
        this.partSize = j;
        this.taskNum = i;
        this.uploadFile = str3;
        this.enableCheckpoint = z;
        this.checkpointFile = str4;
    }

    public UploadFileRequest(String str, String str2, String str3, long j, int i, boolean z, String str4, boolean z2) {
        this(str, str2, str3, j, i, z, str4);
        this.enableCheckSum = z2;
    }

    public String getCheckpointFile() {
        return this.checkpointFile;
    }

    public ObjectMetadata getObjectMetadata() {
        return this.objectMetadata;
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

    public String getUploadFile() {
        return this.uploadFile;
    }

    public boolean isEnableCheckSum() {
        return this.enableCheckSum;
    }

    public boolean isEnableCheckpoint() {
        return this.enableCheckpoint;
    }

    public void setCheckpointFile(String str) {
        this.checkpointFile = str;
    }

    public void setEnableCheckSum(boolean z) {
        this.enableCheckSum = z;
    }

    public void setEnableCheckpoint(boolean z) {
        this.enableCheckpoint = z;
    }

    public void setObjectMetadata(ObjectMetadata objectMetadata) {
        this.objectMetadata = objectMetadata;
    }

    public void setPartSize(long j) {
        if (j < 102400) {
            this.partSize = 102400L;
        } else if (j > 5368709120L) {
            this.partSize = 5368709120L;
        } else {
            this.partSize = j;
        }
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

    public void setUploadFile(String str) {
        this.uploadFile = str;
    }

    public String toString() {
        return "UploadFileRequest [bucketName=" + this.bucketName + ", objectKey=" + this.objectKey + ", partSize=" + this.partSize + ", taskNum=" + this.taskNum + ", uploadFile=" + this.uploadFile + ", enableCheckpoint=" + this.enableCheckpoint + ", checkpointFile=" + this.checkpointFile + ", objectMetadata=" + this.objectMetadata + ", enableCheckSum=" + this.enableCheckSum + "]";
    }
}
