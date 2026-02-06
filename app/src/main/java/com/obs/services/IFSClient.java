package com.obs.services;

import com.obs.services.exception.ObsException;
import com.obs.services.model.HeaderResponse;
import com.obs.services.model.TaskProgressStatus;
import com.obs.services.model.fs.DropFileRequest;
import com.obs.services.model.fs.DropFileResult;
import com.obs.services.model.fs.DropFolderRequest;
import com.obs.services.model.fs.GetAttributeRequest;
import com.obs.services.model.fs.GetBucketFSStatusRequest;
import com.obs.services.model.fs.GetBucketFSStatusResult;
import com.obs.services.model.fs.NewBucketRequest;
import com.obs.services.model.fs.NewFileRequest;
import com.obs.services.model.fs.NewFolderRequest;
import com.obs.services.model.fs.ObsFSAttribute;
import com.obs.services.model.fs.ObsFSBucket;
import com.obs.services.model.fs.ObsFSFile;
import com.obs.services.model.fs.ObsFSFolder;
import com.obs.services.model.fs.ReadFileRequest;
import com.obs.services.model.fs.ReadFileResult;
import com.obs.services.model.fs.RenameRequest;
import com.obs.services.model.fs.RenameResult;
import com.obs.services.model.fs.SetBucketFSStatusRequest;
import com.obs.services.model.fs.TruncateFileRequest;
import com.obs.services.model.fs.TruncateFileResult;
import com.obs.services.model.fs.WriteFileRequest;
import java.io.IOException;

public interface IFSClient {
    ObsFSFile appendFile(WriteFileRequest writeFileRequest) throws ObsException;

    void close() throws IOException;

    DropFileResult dropFile(DropFileRequest dropFileRequest) throws ObsException;

    TaskProgressStatus dropFolder(DropFolderRequest dropFolderRequest) throws ObsException;

    ObsFSAttribute getAttribute(GetAttributeRequest getAttributeRequest) throws ObsException;

    GetBucketFSStatusResult getBucketFSStatus(GetBucketFSStatusRequest getBucketFSStatusRequest) throws ObsException;

    ObsFSBucket newBucket(NewBucketRequest newBucketRequest) throws ObsException;

    ObsFSFile newFile(NewFileRequest newFileRequest) throws ObsException;

    ObsFSFolder newFolder(NewFolderRequest newFolderRequest) throws ObsException;

    ReadFileResult readFile(ReadFileRequest readFileRequest) throws ObsException;

    RenameResult renameFile(RenameRequest renameRequest) throws ObsException;

    RenameResult renameFolder(RenameRequest renameRequest) throws ObsException;

    HeaderResponse setBucketFSStatus(SetBucketFSStatusRequest setBucketFSStatusRequest) throws ObsException;

    TruncateFileResult truncateFile(TruncateFileRequest truncateFileRequest) throws ObsException;

    ObsFSFile writeFile(WriteFileRequest writeFileRequest) throws ObsException;
}
