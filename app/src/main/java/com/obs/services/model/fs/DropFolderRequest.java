package com.obs.services.model.fs;

import com.obs.services.model.AbstractBulkRequest;
import com.obs.services.model.DeleteObjectResult;
import com.obs.services.model.TaskCallback;

public class DropFolderRequest extends AbstractBulkRequest {
    private TaskCallback<DeleteObjectResult, String> callback;
    private String folderName;

    public DropFolderRequest() {
    }

    public DropFolderRequest(String str) {
        super(str);
    }

    public DropFolderRequest(String str, String str2) {
        super(str);
        this.folderName = str2;
    }

    public TaskCallback<DeleteObjectResult, String> getCallback() {
        return this.callback;
    }

    public String getFolderName() {
        return this.folderName;
    }

    public void setCallback(TaskCallback<DeleteObjectResult, String> taskCallback) {
        this.callback = taskCallback;
    }

    public void setFolderName(String str) {
        this.folderName = str;
    }

    public String toString() {
        return "DropFolderRequest [bucketName=" + this.bucketName + ", folderName=" + this.folderName + "]";
    }
}
