package com.obs.services.model.fs;

import com.obs.services.model.PutObjectBasicRequest;

public class NewFolderRequest extends PutObjectBasicRequest {
    public NewFolderRequest() {
    }

    public NewFolderRequest(String str, String str2) {
        this.bucketName = str;
        this.objectKey = str2;
    }
}
