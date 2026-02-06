package com.obs.services.internal.task;

import com.obs.services.ObsClient;
import com.obs.services.exception.ObsException;
import com.obs.services.model.CompleteMultipartUploadResult;
import com.obs.services.model.ProgressStatus;
import com.obs.services.model.PutObjectBasicRequest;
import com.obs.services.model.PutObjectResult;
import com.obs.services.model.TaskCallback;
import com.obs.services.model.UploadFileRequest;
import com.obs.services.model.UploadObjectsProgressListener;

public class ResumableUploadTask implements Runnable {
    protected String bucketName;
    private TaskCallback<PutObjectResult, PutObjectBasicRequest> callback;
    protected ObsClient obsClient;
    private UploadObjectsProgressListener progressListener;
    private int taskProgressInterval;
    private UploadFileRequest taskRequest;
    private UploadTaskProgressStatus taskStatus;

    public ResumableUploadTask(ObsClient obsClient, String str, UploadFileRequest uploadFileRequest, TaskCallback<PutObjectResult, PutObjectBasicRequest> taskCallback, UploadObjectsProgressListener uploadObjectsProgressListener, UploadTaskProgressStatus uploadTaskProgressStatus, int i) {
        this.obsClient = obsClient;
        this.bucketName = str;
        this.taskRequest = uploadFileRequest;
        this.callback = taskCallback;
        this.progressListener = uploadObjectsProgressListener;
        this.taskStatus = uploadTaskProgressStatus;
        this.taskProgressInterval = i;
    }

    private void ResumableUpload() {
        String objectKey;
        ProgressStatus taskStatus;
        try {
            try {
                CompleteMultipartUploadResult completeMultipartUploadResultUploadFile = this.obsClient.uploadFile(this.taskRequest);
                this.taskStatus.succeedTaskIncrement();
                this.callback.onSuccess(new PutObjectResult(completeMultipartUploadResultUploadFile.getBucketName(), completeMultipartUploadResultUploadFile.getObjectKey(), completeMultipartUploadResultUploadFile.getEtag(), completeMultipartUploadResultUploadFile.getVersionId(), completeMultipartUploadResultUploadFile.getObjectUrl(), completeMultipartUploadResultUploadFile.getResponseHeaders(), completeMultipartUploadResultUploadFile.getStatusCode()));
                this.taskStatus.execTaskIncrement();
                if (this.progressListener != null) {
                    if (this.taskStatus.getExecTaskNum() % this.taskProgressInterval == 0) {
                        this.progressListener.progressChanged(this.taskStatus);
                    }
                    if (this.taskStatus.getExecTaskNum() == this.taskStatus.getTotalTaskNum()) {
                        this.progressListener.progressChanged(this.taskStatus);
                    }
                }
                objectKey = this.taskRequest.getObjectKey();
                taskStatus = this.taskStatus.getTaskStatus(objectKey);
            } catch (ObsException e) {
                this.taskStatus.failTaskIncrement();
                this.callback.onException(e, this.taskRequest);
                this.taskStatus.execTaskIncrement();
                if (this.progressListener != null) {
                    if (this.taskStatus.getExecTaskNum() % this.taskProgressInterval == 0) {
                        this.progressListener.progressChanged(this.taskStatus);
                    }
                    if (this.taskStatus.getExecTaskNum() == this.taskStatus.getTotalTaskNum()) {
                        this.progressListener.progressChanged(this.taskStatus);
                    }
                }
                objectKey = this.taskRequest.getObjectKey();
                taskStatus = this.taskStatus.getTaskStatus(objectKey);
                if (taskStatus != null) {
                }
            }
            if (taskStatus != null) {
                this.taskStatus.addEndingTaskSize(taskStatus.getTransferredBytes());
            }
            this.taskStatus.removeTaskTable(objectKey);
        } catch (Throwable th) {
            this.taskStatus.execTaskIncrement();
            if (this.progressListener != null) {
                if (this.taskStatus.getExecTaskNum() % this.taskProgressInterval == 0) {
                    this.progressListener.progressChanged(this.taskStatus);
                }
                if (this.taskStatus.getExecTaskNum() == this.taskStatus.getTotalTaskNum()) {
                    this.progressListener.progressChanged(this.taskStatus);
                }
            }
            String objectKey2 = this.taskRequest.getObjectKey();
            ProgressStatus taskStatus2 = this.taskStatus.getTaskStatus(objectKey2);
            if (taskStatus2 != null) {
                this.taskStatus.addEndingTaskSize(taskStatus2.getTransferredBytes());
            }
            this.taskStatus.removeTaskTable(objectKey2);
            throw th;
        }
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public TaskCallback<PutObjectResult, PutObjectBasicRequest> getCallback() {
        return this.callback;
    }

    public ObsClient getObsClient() {
        return this.obsClient;
    }

    public int getTaskProgressInterval() {
        return this.taskProgressInterval;
    }

    public UploadFileRequest getTaskRequest() {
        return this.taskRequest;
    }

    public UploadTaskProgressStatus getTaskStatus() {
        return this.taskStatus;
    }

    public UploadObjectsProgressListener getUploadObjectsProgressListener() {
        return this.progressListener;
    }

    @Override
    public void run() {
        ResumableUpload();
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public void setCallback(TaskCallback<PutObjectResult, PutObjectBasicRequest> taskCallback) {
        this.callback = taskCallback;
    }

    public void setObsClient(ObsClient obsClient) {
        this.obsClient = obsClient;
    }

    public void setTaskProgressInterval(int i) {
        this.taskProgressInterval = i;
    }

    public void setTaskRequest(UploadFileRequest uploadFileRequest) {
        this.taskRequest = uploadFileRequest;
    }

    public void setTaskStatus(UploadTaskProgressStatus uploadTaskProgressStatus) {
        this.taskStatus = uploadTaskProgressStatus;
    }

    public void setUploadObjectsProgressListener(UploadObjectsProgressListener uploadObjectsProgressListener) {
        this.progressListener = uploadObjectsProgressListener;
    }
}
