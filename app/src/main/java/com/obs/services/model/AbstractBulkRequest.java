package com.obs.services.model;

public abstract class AbstractBulkRequest {
    protected String bucketName;
    protected TaskProgressListener listener;
    protected int taskThreadNum = 10;
    protected int taskQueueNum = 20000;
    protected int taskProgressInterval = 50;

    public AbstractBulkRequest() {
    }

    public AbstractBulkRequest(String str) {
        this.bucketName = str;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public int getProgressInterval() {
        return this.taskProgressInterval;
    }

    public TaskProgressListener getProgressListener() {
        return this.listener;
    }

    public int getTaskQueueNum() {
        return this.taskQueueNum;
    }

    public int getTaskThreadNum() {
        return this.taskThreadNum;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setProgressInterval(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("ProgressInterval should be greater than 0.");
        }
        this.taskProgressInterval = i;
    }

    public void setProgressListener(TaskProgressListener taskProgressListener) {
        this.listener = taskProgressListener;
    }

    public void setTaskQueueNum(int i) {
        this.taskQueueNum = i;
    }

    public void setTaskThreadNum(int i) {
        this.taskThreadNum = i;
    }
}
