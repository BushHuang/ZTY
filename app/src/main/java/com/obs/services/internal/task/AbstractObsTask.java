package com.obs.services.internal.task;

import com.obs.services.ObsClient;
import com.obs.services.model.TaskProgressListener;

public abstract class AbstractObsTask implements Runnable {
    protected String bucketName;
    protected ObsClient obsClient;
    protected TaskProgressListener progressListener;
    protected DefaultTaskProgressStatus progressStatus;
    protected int taskProgressInterval;

    public AbstractObsTask(ObsClient obsClient, String str) {
        this.obsClient = obsClient;
        this.bucketName = str;
    }

    public AbstractObsTask(ObsClient obsClient, String str, DefaultTaskProgressStatus defaultTaskProgressStatus, TaskProgressListener taskProgressListener, int i) {
        this.obsClient = obsClient;
        this.bucketName = str;
        this.progressStatus = defaultTaskProgressStatus;
        this.progressListener = taskProgressListener;
        this.taskProgressInterval = i;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public ObsClient getObsClient() {
        return this.obsClient;
    }

    public TaskProgressListener getProgressListener() {
        return this.progressListener;
    }

    public DefaultTaskProgressStatus getProgressStatus() {
        return this.progressStatus;
    }

    public int getTaskProgressInterval() {
        return this.taskProgressInterval;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setObsClient(ObsClient obsClient) {
        this.obsClient = obsClient;
    }

    public void setProgressListener(TaskProgressListener taskProgressListener) {
        this.progressListener = taskProgressListener;
    }

    public void setProgressStatus(DefaultTaskProgressStatus defaultTaskProgressStatus) {
        this.progressStatus = defaultTaskProgressStatus;
    }

    public void setTaskProgressInterval(int i) {
        this.taskProgressInterval = i;
    }
}
