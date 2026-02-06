package com.obs.services.model;

import java.io.File;
import java.io.InputStream;

public class UploadPartRequest {
    private String bucketName;
    private String contentMd5;
    private File file;
    private InputStream input;
    private String objectKey;
    private long offset;
    private int partNumber;
    private Long partSize;
    private ProgressListener progressListener;
    private SseCHeader sseCHeader;
    private String uploadId;
    private boolean attachMd5 = false;
    private boolean autoClose = true;
    private long progressInterval = 102400;

    public UploadPartRequest() {
    }

    public UploadPartRequest(String str, String str2) {
        this.bucketName = str;
        this.objectKey = str2;
    }

    public UploadPartRequest(String str, String str2, File file) {
        this.bucketName = str;
        this.objectKey = str2;
        this.file = file;
    }

    public UploadPartRequest(String str, String str2, Long l, long j, File file) {
        this.bucketName = str;
        this.objectKey = str2;
        this.partSize = l;
        this.offset = j;
        this.file = file;
    }

    public UploadPartRequest(String str, String str2, Long l, InputStream inputStream) {
        this.bucketName = str;
        this.objectKey = str2;
        this.partSize = l;
        this.input = inputStream;
    }

    public UploadPartRequest(String str, String str2, String str3) {
        this.bucketName = str;
        this.objectKey = str2;
        this.file = new File(str3);
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public String getContentMd5() {
        return this.contentMd5;
    }

    public File getFile() {
        return this.file;
    }

    public InputStream getInput() {
        return this.input;
    }

    public String getObjectKey() {
        return this.objectKey;
    }

    public long getOffset() {
        return this.offset;
    }

    public int getPartNumber() {
        return this.partNumber;
    }

    public Long getPartSize() {
        return this.partSize;
    }

    public long getProgressInterval() {
        return this.progressInterval;
    }

    public ProgressListener getProgressListener() {
        return this.progressListener;
    }

    public SseCHeader getSseCHeader() {
        return this.sseCHeader;
    }

    public String getUploadId() {
        return this.uploadId;
    }

    public boolean isAttachMd5() {
        return this.attachMd5;
    }

    public boolean isAutoClose() {
        return this.autoClose;
    }

    public void setAttachMd5(boolean z) {
        this.attachMd5 = z;
    }

    public void setAutoClose(boolean z) {
        this.autoClose = z;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setContentMd5(String str) {
        this.contentMd5 = str;
    }

    public void setFile(File file) {
        this.file = file;
        this.input = null;
    }

    public void setInput(InputStream inputStream) {
        this.input = inputStream;
        this.file = null;
    }

    public void setObjectKey(String str) {
        this.objectKey = str;
    }

    public void setOffset(long j) {
        this.offset = j;
    }

    public void setPartNumber(int i) {
        this.partNumber = i;
    }

    public void setPartSize(Long l) {
        this.partSize = l;
    }

    public void setProgressInterval(long j) {
        this.progressInterval = j;
    }

    public void setProgressListener(ProgressListener progressListener) {
        this.progressListener = progressListener;
    }

    public void setSseCHeader(SseCHeader sseCHeader) {
        this.sseCHeader = sseCHeader;
    }

    public void setUploadId(String str) {
        this.uploadId = str;
    }

    public String toString() {
        return "UploadPartRequest [uploadId=" + this.uploadId + ", bucketName=" + this.bucketName + ", objectKey=" + this.objectKey + ", partNumber=" + this.partNumber + ", partSize=" + this.partSize + ", offset=" + this.offset + ", sseCHeader=" + this.sseCHeader + ", contentMd5=" + this.contentMd5 + ", attachMd5=" + this.attachMd5 + ", file=" + this.file + ", input=" + this.input + "]";
    }
}
