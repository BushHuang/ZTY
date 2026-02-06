package com.obs.services.model.fs;

import com.obs.services.exception.ObsException;
import com.obs.services.model.StorageClassEnum;
import java.io.File;
import java.io.InputStream;

public class ObsFSFile extends ObsFSFolder {
    public ObsFSFile(String str, String str2, String str3, String str4, StorageClassEnum storageClassEnum, String str5) {
        super(str, str2, str3, str4, storageClassEnum, str5);
    }

    public ObsFSFile append(File file) throws ObsException {
        checkInternalClient();
        return this.innerClient.appendFile(new WriteFileRequest(getBucketName(), getObjectKey(), file));
    }

    public ObsFSFile append(InputStream inputStream) throws ObsException {
        checkInternalClient();
        return this.innerClient.appendFile(new WriteFileRequest(getBucketName(), getObjectKey(), inputStream));
    }

    @Override
    public ObsFSAttribute attribute() throws ObsException {
        return super.attribute();
    }

    public DropFileResult drop() throws ObsException {
        checkInternalClient();
        return this.innerClient.dropFile(new DropFileRequest(getBucketName(), getObjectKey(), getVersionId()));
    }

    public ReadFileResult read() throws ObsException {
        checkInternalClient();
        return this.innerClient.readFile(new ReadFileRequest(getBucketName(), getObjectKey()));
    }

    public ReadFileResult read(long j, long j2) throws ObsException {
        checkInternalClient();
        ReadFileRequest readFileRequest = new ReadFileRequest(getBucketName(), getObjectKey());
        readFileRequest.setRangeStart(Long.valueOf(j));
        readFileRequest.setRangeEnd(Long.valueOf(j2));
        return this.innerClient.readFile(readFileRequest);
    }

    @Override
    public RenameResult rename(String str) throws ObsException {
        checkInternalClient();
        return this.innerClient.renameFile(new RenameRequest(getBucketName(), getObjectKey(), str));
    }

    public TruncateFileResult truncate(long j) throws ObsException {
        checkInternalClient();
        return this.innerClient.truncateFile(new TruncateFileRequest(getBucketName(), getObjectKey(), j));
    }

    public ObsFSFile write(File file) throws ObsException {
        return write(file, 0L);
    }

    public ObsFSFile write(File file, long j) throws ObsException {
        checkInternalClient();
        return this.innerClient.writeFile(new WriteFileRequest(getBucketName(), getObjectKey(), file, j));
    }

    public ObsFSFile write(InputStream inputStream) throws ObsException {
        return write(inputStream, 0L);
    }

    public ObsFSFile write(InputStream inputStream, long j) throws ObsException {
        checkInternalClient();
        return this.innerClient.writeFile(new WriteFileRequest(getBucketName(), getObjectKey(), inputStream, j));
    }
}
