package com.obs.services.model;

import java.io.File;
import java.io.InputStream;

public class PutObjectRequest extends PutObjectBasicRequest {
    protected File file;
    protected InputStream input;
    protected ObjectMetadata metadata;
    protected long offset;
    private ProgressListener progressListener;
    protected int expires = -1;
    private boolean autoClose = true;
    private long progressInterval = 102400;

    public PutObjectRequest() {
    }

    public PutObjectRequest(PutObjectBasicRequest putObjectBasicRequest) {
        if (putObjectBasicRequest != null) {
            this.bucketName = putObjectBasicRequest.getBucketName();
            this.objectKey = putObjectBasicRequest.getObjectKey();
            this.acl = putObjectBasicRequest.getAcl();
            this.extensionPermissionMap = putObjectBasicRequest.getExtensionPermissionMap();
            this.sseCHeader = putObjectBasicRequest.getSseCHeader();
            this.sseKmsHeader = putObjectBasicRequest.getSseKmsHeader();
            this.successRedirectLocation = putObjectBasicRequest.getSuccessRedirectLocation();
        }
    }

    public PutObjectRequest(String str, String str2) {
        this.bucketName = str;
        this.objectKey = str2;
    }

    public PutObjectRequest(String str, String str2, File file) {
        this.bucketName = str;
        this.objectKey = str2;
        this.file = file;
    }

    public PutObjectRequest(String str, String str2, InputStream inputStream) {
        this.bucketName = str;
        this.objectKey = str2;
        this.input = inputStream;
    }

    public int getExpires() {
        return this.expires;
    }

    public File getFile() {
        return this.file;
    }

    public InputStream getInput() {
        return this.input;
    }

    public ObjectMetadata getMetadata() {
        return this.metadata;
    }

    public long getOffset() {
        return this.offset;
    }

    public long getProgressInterval() {
        return this.progressInterval;
    }

    public ProgressListener getProgressListener() {
        return this.progressListener;
    }

    public boolean isAutoClose() {
        return this.autoClose;
    }

    public void setAutoClose(boolean z) {
        this.autoClose = z;
    }

    public void setExpires(int i) {
        this.expires = i;
    }

    public void setFile(File file) {
        this.file = file;
        this.input = null;
    }

    public void setInput(InputStream inputStream) {
        this.input = inputStream;
        this.file = null;
    }

    public void setMetadata(ObjectMetadata objectMetadata) {
        this.metadata = objectMetadata;
    }

    public void setOffset(long j) {
        this.offset = j;
    }

    public void setProgressInterval(long j) {
        this.progressInterval = j;
    }

    public void setProgressListener(ProgressListener progressListener) {
        this.progressListener = progressListener;
    }

    public String toString() {
        return "PutObjectRequest [bucketName=" + this.bucketName + ", objectKey=" + this.objectKey + ", file=" + this.file + ", input=" + this.input + ", metadata=" + this.metadata + ", sseKmsHeader=" + this.sseKmsHeader + ", sseCHeader=" + this.sseCHeader + ", acl=" + this.acl + ", expires=" + this.expires + ", successRedirectLocation=" + this.successRedirectLocation + "]";
    }
}
