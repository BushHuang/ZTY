package com.obs.services.model.fs;

import com.obs.services.ObsClient;
import com.obs.services.exception.ObsException;
import com.obs.services.internal.utils.ServiceUtils;
import com.obs.services.model.HeaderResponse;
import com.obs.services.model.ObjectMetadata;
import java.io.File;
import java.io.InputStream;

public class ObsFSBucket {
    private String bucketName;
    protected ObsClient innerClient;
    private String location;

    public ObsFSBucket(String str, String str2) {
        this.bucketName = str;
        this.location = str2;
    }

    protected void checkInternalClient() {
        ServiceUtils.asserParameterNotNull(this.innerClient, "ObsClient is null");
    }

    protected void finalize() throws Throwable {
        this.innerClient = null;
        super.finalize();
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public String getLocation() {
        return this.location;
    }

    public ObsFSFile newFile(String str, File file) throws ObsException {
        return newFile(str, file, (ObjectMetadata) null);
    }

    public ObsFSFile newFile(String str, File file, ObjectMetadata objectMetadata) throws ObsException {
        checkInternalClient();
        NewFileRequest newFileRequest = new NewFileRequest(this.bucketName, str);
        newFileRequest.setFile(file);
        return this.innerClient.newFile(newFileRequest);
    }

    public ObsFSFile newFile(String str, InputStream inputStream) throws ObsException {
        return newFile(str, inputStream, (ObjectMetadata) null);
    }

    public ObsFSFile newFile(String str, InputStream inputStream, ObjectMetadata objectMetadata) throws ObsException {
        checkInternalClient();
        NewFileRequest newFileRequest = new NewFileRequest(this.bucketName, str);
        newFileRequest.setInput(inputStream);
        return this.innerClient.newFile(newFileRequest);
    }

    public ObsFSFolder newFolder(String str) throws ObsException {
        checkInternalClient();
        return this.innerClient.newFolder(new NewFolderRequest(this.bucketName, str));
    }

    public HeaderResponse setFSStatus(FSStatusEnum fSStatusEnum) throws ObsException {
        checkInternalClient();
        return this.innerClient.setBucketFSStatus(new SetBucketFSStatusRequest(this.bucketName, fSStatusEnum));
    }
}
