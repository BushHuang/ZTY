package com.obs.services.internal.task;

import com.obs.services.ObsClient;
import com.obs.services.exception.ObsException;
import com.obs.services.model.RestoreObjectRequest;
import com.obs.services.model.RestoreObjectResult;
import com.obs.services.model.TaskCallback;
import com.obs.services.model.TaskProgressListener;

public class RestoreObjectTask extends AbstractObsTask {
    protected TaskCallback<RestoreObjectResult, RestoreObjectRequest> callback;
    private RestoreObjectRequest taskRequest;

    public RestoreObjectTask(ObsClient obsClient, String str) {
        super(obsClient, str);
    }

    public RestoreObjectTask(ObsClient obsClient, String str, RestoreObjectRequest restoreObjectRequest, TaskCallback<RestoreObjectResult, RestoreObjectRequest> taskCallback) {
        super(obsClient, str);
        this.taskRequest = restoreObjectRequest;
        this.callback = taskCallback;
    }

    public RestoreObjectTask(ObsClient obsClient, String str, RestoreObjectRequest restoreObjectRequest, TaskCallback<RestoreObjectResult, RestoreObjectRequest> taskCallback, TaskProgressListener taskProgressListener, DefaultTaskProgressStatus defaultTaskProgressStatus, int i) {
        super(obsClient, str, defaultTaskProgressStatus, taskProgressListener, i);
        this.taskRequest = restoreObjectRequest;
        this.callback = taskCallback;
    }

    private void restoreObjects() {
        try {
            RestoreObjectResult restoreObjectResultRestoreObjectV2 = this.obsClient.restoreObjectV2(this.taskRequest);
            this.progressStatus.succeedTaskIncrement();
            this.callback.onSuccess(restoreObjectResultRestoreObjectV2);
        } catch (ObsException e) {
            this.progressStatus.failTaskIncrement();
            this.callback.onException(e, this.taskRequest);
        }
        this.progressStatus.execTaskIncrement();
        if (this.progressListener != null) {
            if (this.progressStatus.getExecTaskNum() % this.taskProgressInterval == 0) {
                this.progressListener.progressChanged(this.progressStatus);
            }
            if (this.progressStatus.getExecTaskNum() == this.progressStatus.getTotalTaskNum()) {
                this.progressListener.progressChanged(this.progressStatus);
            }
        }
    }

    public TaskCallback<RestoreObjectResult, RestoreObjectRequest> getCallback() {
        return this.callback;
    }

    public RestoreObjectRequest getTaskRequest() {
        return this.taskRequest;
    }

    @Override
    public void run() {
        restoreObjects();
    }

    public void setCallback(TaskCallback<RestoreObjectResult, RestoreObjectRequest> taskCallback) {
        this.callback = taskCallback;
    }

    public void setTaskRequest(RestoreObjectRequest restoreObjectRequest) {
        this.taskRequest = restoreObjectRequest;
    }
}
