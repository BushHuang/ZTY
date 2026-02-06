package com.obs.services.model.fs;

import com.obs.services.ObsClient;
import com.obs.services.exception.ObsException;
import com.obs.services.internal.utils.ServiceUtils;
import com.obs.services.model.PutObjectResult;
import com.obs.services.model.StorageClassEnum;
import com.obs.services.model.TaskProgressStatus;

public class ObsFSFolder extends PutObjectResult {
    protected ObsClient innerClient;

    public ObsFSFolder(String str, String str2, String str3, String str4, StorageClassEnum storageClassEnum, String str5) {
        super(str, str2, str3, str4, storageClassEnum, str5);
    }

    public ObsFSAttribute attribute() throws ObsException {
        checkInternalClient();
        return (ObsFSAttribute) this.innerClient.getObjectMetadata(getBucketName(), getObjectKey());
    }

    protected void checkInternalClient() {
        ServiceUtils.asserParameterNotNull(this.innerClient, "ObsClient is null");
    }

    public TaskProgressStatus dropFolder() throws ObsException {
        checkInternalClient();
        return this.innerClient.dropFolder(new DropFolderRequest(getBucketName(), getObjectKey()));
    }

    protected void finalize() throws Throwable {
        this.innerClient = null;
        super.finalize();
    }

    public RenameResult rename(String str) throws ObsException {
        checkInternalClient();
        return this.innerClient.renameFolder(new RenameRequest(getBucketName(), getObjectKey(), str));
    }
}
