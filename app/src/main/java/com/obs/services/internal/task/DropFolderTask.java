package com.obs.services.internal.task;

import com.obs.services.ObsClient;
import com.obs.services.exception.ObsException;
import com.obs.services.model.DeleteObjectResult;
import com.obs.services.model.TaskCallback;
import com.obs.services.model.TaskProgressListener;

public class DropFolderTask extends AbstractObsTask {
    private TaskCallback<DeleteObjectResult, String> callback;
    private String objectKey;

    public DropFolderTask(ObsClient obsClient, String str) {
        super(obsClient, str);
    }

    public DropFolderTask(ObsClient obsClient, String str, String str2, DefaultTaskProgressStatus defaultTaskProgressStatus, TaskProgressListener taskProgressListener, int i, TaskCallback<DeleteObjectResult, String> taskCallback) {
        super(obsClient, str, defaultTaskProgressStatus, taskProgressListener, i);
        this.objectKey = str2;
        this.callback = taskCallback;
    }

    private void dropFolder() throws ObsException {
        DeleteObjectResult deleteObjectResultDeleteObject = this.obsClient.deleteObject(this.bucketName, this.objectKey);
        this.progressStatus.succeedTaskIncrement();
        this.callback.onSuccess(deleteObjectResultDeleteObject);
    }

    public TaskCallback<DeleteObjectResult, String> getCallback() {
        return this.callback;
    }

    public String getObjectKey() {
        return this.objectKey;
    }

    @Override
    public void run() throws ObsException {
        dropFolder();
    }

    public void setCallback(TaskCallback<DeleteObjectResult, String> taskCallback) {
        this.callback = taskCallback;
    }

    public void setObjeceKey(String str) {
        this.objectKey = str;
    }
}
