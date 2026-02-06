package com.obs.services.internal;

import com.obs.log.ILogger;
import com.obs.log.LoggerBuilder;
import com.obs.services.ObsClient;
import com.obs.services.exception.ObsException;
import com.obs.services.internal.io.ProgressInputStream;
import com.obs.services.internal.utils.SecureObjectInputStream;
import com.obs.services.internal.utils.ServiceUtils;
import com.obs.services.model.AbortMultipartUploadRequest;
import com.obs.services.model.CompleteMultipartUploadRequest;
import com.obs.services.model.CompleteMultipartUploadResult;
import com.obs.services.model.DownloadFileRequest;
import com.obs.services.model.DownloadFileResult;
import com.obs.services.model.GetObjectMetadataRequest;
import com.obs.services.model.GetObjectRequest;
import com.obs.services.model.HeaderResponse;
import com.obs.services.model.InitiateMultipartUploadRequest;
import com.obs.services.model.ObjectMetadata;
import com.obs.services.model.PartEtag;
import com.obs.services.model.UploadFileRequest;
import com.obs.services.model.UploadPartRequest;
import com.obs.services.model.UploadPartResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ResumableClient {
    private static final ILogger log = LoggerBuilder.getLogger("com.obs.services.ObsClient");
    private ObsClient obsClient;

    static class DownloadCheckPoint implements Serializable {
        private static final long serialVersionUID = 2282950186694419179L;
        public String bucketName;
        public String downloadFile;
        ArrayList<DownloadPart> downloadParts;
        public volatile transient boolean isAbort = false;
        public int md5;
        public String objectKey;
        public ObjectStatus objectStatus;
        public TmpFileStatus tmpFileStatus;
        public String versionId;

        DownloadCheckPoint() {
        }

        private void assign(DownloadCheckPoint downloadCheckPoint) {
            this.md5 = downloadCheckPoint.md5;
            this.downloadFile = downloadCheckPoint.downloadFile;
            this.bucketName = downloadCheckPoint.bucketName;
            this.objectKey = downloadCheckPoint.objectKey;
            this.versionId = downloadCheckPoint.versionId;
            this.objectStatus = downloadCheckPoint.objectStatus;
            this.tmpFileStatus = downloadCheckPoint.tmpFileStatus;
            this.downloadParts = downloadCheckPoint.downloadParts;
        }

        public boolean equals(Object obj) {
            return obj != null && (obj instanceof DownloadCheckPoint) && ((DownloadCheckPoint) obj).hashCode() == hashCode();
        }

        public int hashCode() {
            String str = this.bucketName;
            int iHashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
            String str2 = this.downloadFile;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.versionId;
            int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.objectKey;
            int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
            ObjectStatus objectStatus = this.objectStatus;
            int iHashCode5 = (iHashCode4 + (objectStatus == null ? 0 : objectStatus.hashCode())) * 31;
            TmpFileStatus tmpFileStatus = this.tmpFileStatus;
            int iHashCode6 = (iHashCode5 + (tmpFileStatus == null ? 0 : tmpFileStatus.hashCode())) * 31;
            ArrayList<DownloadPart> arrayList = this.downloadParts;
            return iHashCode6 + (arrayList != null ? arrayList.hashCode() : 0);
        }

        public boolean isValid(String str, ObjectMetadata objectMetadata) {
            if (this.md5 == hashCode() && objectMetadata.getContentLength().longValue() == this.objectStatus.size && objectMetadata.getLastModified().equals(this.objectStatus.lastModified) && objectMetadata.getEtag().equals(this.objectStatus.Etag)) {
                return this.tmpFileStatus.size == new File(str).length();
            }
            return false;
        }

        public void load(String str) throws Exception {
            FileInputStream fileInputStream;
            Throwable th;
            SecureObjectInputStream secureObjectInputStream;
            try {
                fileInputStream = new FileInputStream(str);
                try {
                    secureObjectInputStream = new SecureObjectInputStream(fileInputStream);
                } catch (Throwable th2) {
                    th = th2;
                    secureObjectInputStream = null;
                }
                try {
                    assign((DownloadCheckPoint) secureObjectInputStream.readObject());
                    ServiceUtils.closeStream(secureObjectInputStream);
                    ServiceUtils.closeStream(fileInputStream);
                } catch (Throwable th3) {
                    th = th3;
                    ServiceUtils.closeStream(secureObjectInputStream);
                    ServiceUtils.closeStream(fileInputStream);
                    throw th;
                }
            } catch (Throwable th4) {
                fileInputStream = null;
                th = th4;
                secureObjectInputStream = null;
            }
        }

        public synchronized void record(String str) throws IOException {
            FileOutputStream fileOutputStream;
            Throwable th;
            ObjectOutputStream objectOutputStream;
            this.md5 = hashCode();
            try {
                fileOutputStream = new FileOutputStream(str);
                try {
                    objectOutputStream = new ObjectOutputStream(fileOutputStream);
                } catch (Throwable th2) {
                    th = th2;
                    objectOutputStream = null;
                }
                try {
                    objectOutputStream.writeObject(this);
                    try {
                        objectOutputStream.close();
                    } catch (Exception unused) {
                    }
                    try {
                        fileOutputStream.close();
                    } catch (Exception unused2) {
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (Exception unused3) {
                        }
                    }
                    if (fileOutputStream == null) {
                        throw th;
                    }
                    try {
                        fileOutputStream.close();
                        throw th;
                    } catch (Exception unused4) {
                        throw th;
                    }
                }
            } catch (Throwable th4) {
                fileOutputStream = null;
                th = th4;
                objectOutputStream = null;
            }
        }

        public synchronized void update(int i, boolean z, String str) throws IOException {
            this.downloadParts.get(i).isCompleted = z;
            File file = new File(str);
            this.tmpFileStatus.lastModified = new Date(file.lastModified());
        }

        public synchronized void updateTmpFile(String str) throws IOException {
            File file = new File(str);
            this.tmpFileStatus.lastModified = new Date(file.lastModified());
        }
    }

    static class DownloadPart implements Serializable {
        private static final long serialVersionUID = 961987949814206093L;
        public long end;
        public boolean isCompleted;
        public long offset;
        public int partNumber;

        DownloadPart() {
        }

        public boolean equals(Object obj) {
            return obj != null && (obj instanceof DownloadPart) && ((DownloadPart) obj).hashCode() == hashCode();
        }

        public int hashCode() {
            int i = (((this.partNumber + 31) * 31) + (this.isCompleted ? 0 : 8)) * 31;
            long j = this.end;
            long j2 = this.offset;
            return ((i + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)));
        }
    }

    static class DownloadResult {
        private List<PartResultDown> partResults;

        DownloadResult() {
        }

        public List<PartResultDown> getPartResults() {
            return this.partResults;
        }

        public void setPartResults(List<PartResultDown> list) {
            this.partResults = list;
        }
    }

    static class FileStatus implements Serializable {
        private static final long serialVersionUID = -3135754191745936521L;
        public String checkSum;
        public long lastModified;
        public long size;

        FileStatus() {
        }

        public static FileStatus getFileStatus(String str, boolean z) throws IOException {
            FileStatus fileStatus = new FileStatus();
            File file = new File(str);
            fileStatus.size = file.length();
            fileStatus.lastModified = file.lastModified();
            if (z) {
                try {
                    fileStatus.checkSum = ServiceUtils.toBase64(ServiceUtils.computeMD5Hash(new FileInputStream(file)));
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
            return fileStatus;
        }

        public boolean equals(Object obj) {
            return obj != null && (obj instanceof FileStatus) && ((FileStatus) obj).hashCode() == obj.hashCode();
        }

        public int hashCode() {
            String str = this.checkSum;
            int iHashCode = str == null ? 0 : str.hashCode();
            long j = this.lastModified;
            long j2 = this.size;
            return ((((iHashCode + 31) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)));
        }
    }

    static class Mission implements Callable<PartResult> {
        private int id;
        private ObsClient obsClient;
        private int partIndex;
        private ProgressManager progressManager;
        private UploadCheckPoint uploadCheckPoint;
        private UploadFileRequest uploadFileRequest;

        public Mission(int i, UploadCheckPoint uploadCheckPoint, int i2, UploadFileRequest uploadFileRequest, ObsClient obsClient) {
            this.id = i;
            this.uploadCheckPoint = uploadCheckPoint;
            this.partIndex = i2;
            this.uploadFileRequest = uploadFileRequest;
            this.obsClient = obsClient;
        }

        @Override
        public PartResult call() throws Exception {
            UploadPart uploadPart = this.uploadCheckPoint.uploadParts.get(this.partIndex);
            PartResult partResult = new PartResult(this.partIndex + 1, uploadPart.offset, uploadPart.size);
            if (this.uploadCheckPoint.isAbort) {
                partResult.setFailed(true);
            } else {
                try {
                    UploadPartRequest uploadPartRequest = new UploadPartRequest();
                    uploadPartRequest.setBucketName(this.uploadFileRequest.getBucketName());
                    uploadPartRequest.setObjectKey(this.uploadFileRequest.getObjectKey());
                    uploadPartRequest.setUploadId(this.uploadCheckPoint.uploadID);
                    uploadPartRequest.setPartSize(Long.valueOf(uploadPart.size));
                    uploadPartRequest.setPartNumber(uploadPart.partNumber);
                    if (this.progressManager == null) {
                        uploadPartRequest.setFile(new File(this.uploadFileRequest.getUploadFile()));
                        uploadPartRequest.setOffset(uploadPart.offset);
                    } else {
                        FileInputStream fileInputStream = new FileInputStream(this.uploadFileRequest.getUploadFile());
                        long j = uploadPart.offset;
                        long jSkip = fileInputStream.skip(j);
                        if (j < jSkip) {
                            ResumableClient.log.error((CharSequence) String.format("The actual number of skipped bytes (%d) is less than expected (%d): ", Long.valueOf(jSkip), Long.valueOf(j)));
                        }
                        uploadPartRequest.setInput(new ProgressInputStream(fileInputStream, this.progressManager, false));
                    }
                    UploadPartResult uploadPartResultUploadPart = this.obsClient.uploadPart(uploadPartRequest);
                    this.uploadCheckPoint.update(this.partIndex, new PartEtag(uploadPartResultUploadPart.getEtag(), Integer.valueOf(uploadPartResultUploadPart.getPartNumber())), true);
                    partResult.setFailed(false);
                    if (this.uploadFileRequest.isEnableCheckpoint()) {
                        this.uploadCheckPoint.record(this.uploadFileRequest.getCheckpointFile());
                    }
                } catch (ObsException e) {
                    if (e.getResponseCode() >= 300 && e.getResponseCode() < 500 && e.getResponseCode() != 408) {
                        this.uploadCheckPoint.isAbort = true;
                    }
                    partResult.setFailed(true);
                    partResult.setException(e);
                    if (ResumableClient.log.isErrorEnabled()) {
                        ResumableClient.log.error(String.format("Task %d:%s upload part %d failed: ", Integer.valueOf(this.id), "upload" + this.id, Integer.valueOf(this.partIndex + 1)), e);
                    }
                } catch (Exception e2) {
                    partResult.setFailed(true);
                    partResult.setException(e2);
                    if (ResumableClient.log.isErrorEnabled()) {
                        ResumableClient.log.error(String.format("Task %d:%s upload part %d failed: ", Integer.valueOf(this.id), "upload" + this.id, Integer.valueOf(this.partIndex + 1)), e2);
                    }
                }
            }
            return partResult;
        }

        public void setProgressManager(ProgressManager progressManager) {
            this.progressManager = progressManager;
        }
    }

    static class ObjectStatus implements Serializable {
        private static final long serialVersionUID = -6267040832855296342L;
        public String Etag;
        public Date lastModified;
        public long size;

        ObjectStatus() {
        }

        public boolean equals(Object obj) {
            return obj != null && (obj instanceof ObjectStatus) && ((ObjectStatus) obj).hashCode() == hashCode();
        }

        public int hashCode() {
            String str = this.Etag;
            int iHashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
            Date date = this.lastModified;
            int iHashCode2 = (iHashCode + (date != null ? date.hashCode() : 0)) * 31;
            long j = this.size;
            return iHashCode2 + ((int) (j ^ (j >>> 32)));
        }
    }

    static class PartResult {
        private Exception exception;
        private boolean isfailed;
        private long length;
        private long offset;
        private int partNumber;

        public PartResult(int i, long j, long j2) {
            this.partNumber = i;
            this.offset = j;
            this.length = j2;
        }

        public Exception getException() {
            return this.exception;
        }

        public long getLength() {
            return this.length;
        }

        public long getOffset() {
            return this.offset;
        }

        public int getpartNumber() {
            return this.partNumber;
        }

        public boolean isFailed() {
            return this.isfailed;
        }

        public void setException(Exception exc) {
            this.exception = exc;
        }

        public void setFailed(boolean z) {
            this.isfailed = z;
        }

        public void setLength(long j) {
            this.length = j;
        }

        public void setOffset(long j) {
            this.offset = j;
        }

        public void setpartNumber(int i) {
            this.partNumber = i;
        }
    }

    static class PartResultDown {
        private long end;
        private Exception exception;
        private boolean isFailed;
        private int partNumber;
        private long start;

        public PartResultDown(int i, long j, long j2) {
            this.partNumber = i;
            this.start = j;
            this.end = j2;
        }

        public long getEnd() {
            return this.end;
        }

        public Exception getException() {
            return this.exception;
        }

        public long getStart() {
            return this.start;
        }

        public int getpartNumber() {
            return this.partNumber;
        }

        public boolean isFailed() {
            return this.isFailed;
        }

        public void setEnd(long j) {
            this.end = j;
        }

        public void setException(Exception exc) {
            this.exception = exc;
        }

        public void setFailed(boolean z) {
            this.isFailed = z;
        }

        public void setStart(long j) {
            this.start = j;
        }
    }

    static class Task implements Callable<PartResultDown> {
        private DownloadCheckPoint downloadCheckPoint;
        private DownloadFileRequest downloadFileRequest;
        private int id;
        private String name;
        private ObsClient obsClient;
        private int partIndex;
        private ProgressManager progressManager;

        public Task(int i, String str, DownloadCheckPoint downloadCheckPoint, int i2, DownloadFileRequest downloadFileRequest, ObsClient obsClient) {
            this.id = i;
            this.name = str;
            this.downloadCheckPoint = downloadCheckPoint;
            this.partIndex = i2;
            this.downloadFileRequest = downloadFileRequest;
            this.obsClient = obsClient;
        }

        @Override
        public PartResultDown call() throws Exception {
            InputStream inputStream;
            InputStream inputStream2;
            DownloadPart downloadPart = this.downloadCheckPoint.downloadParts.get(this.partIndex);
            int i = this.partIndex + 1;
            long j = downloadPart.offset;
            ?? r6 = downloadPart.end;
            PartResultDown partResultDown = new PartResultDown(i, j, r6);
            if (this.downloadCheckPoint.isAbort) {
                partResultDown.setFailed(true);
            } else {
                InputStream objectContent = null;
                try {
                    try {
                        ?? randomAccessFile = new RandomAccessFile(this.downloadFileRequest.getTempDownloadFile(), "rw");
                        try {
                            randomAccessFile.seek(downloadPart.offset);
                            GetObjectRequest getObjectRequest = new GetObjectRequest(this.downloadFileRequest.getBucketName(), this.downloadFileRequest.getObjectKey());
                            getObjectRequest.setIfMatchTag(this.downloadFileRequest.getIfMatchTag());
                            getObjectRequest.setIfNoneMatchTag(this.downloadFileRequest.getIfNoneMatchTag());
                            getObjectRequest.setIfModifiedSince(this.downloadFileRequest.getIfModifiedSince());
                            getObjectRequest.setIfUnmodifiedSince(this.downloadFileRequest.getIfUnmodifiedSince());
                            getObjectRequest.setRangeStart(Long.valueOf(downloadPart.offset));
                            getObjectRequest.setRangeEnd(Long.valueOf(downloadPart.end));
                            getObjectRequest.setVersionId(this.downloadFileRequest.getVersionId());
                            getObjectRequest.setCacheOption(this.downloadFileRequest.getCacheOption());
                            getObjectRequest.setTtl(this.downloadFileRequest.getTtl());
                            objectContent = this.obsClient.getObject(getObjectRequest).getObjectContent();
                            if (this.progressManager != null) {
                                objectContent = new ProgressInputStream(objectContent, this.progressManager, false);
                            }
                            byte[] bArr = new byte[4096];
                            while (true) {
                                int i2 = objectContent.read(bArr);
                                if (i2 == -1) {
                                    break;
                                }
                                randomAccessFile.write(bArr, 0, i2);
                            }
                            this.downloadCheckPoint.update(this.partIndex, true, this.downloadFileRequest.getTempDownloadFile());
                            ServiceUtils.closeStream(randomAccessFile);
                            ServiceUtils.closeStream(objectContent);
                        } catch (ObsException e) {
                            e = e;
                            inputStream2 = objectContent;
                            objectContent = randomAccessFile;
                            if (e.getResponseCode() >= 300 && e.getResponseCode() < 500 && e.getResponseCode() != 408) {
                                this.downloadCheckPoint.isAbort = true;
                            }
                            partResultDown.setFailed(true);
                            partResultDown.setException(e);
                            if (ResumableClient.log.isErrorEnabled()) {
                                ResumableClient.log.error(String.format("Task %d:%s download part %d failed: ", Integer.valueOf(this.id), this.name, Integer.valueOf(this.partIndex)), e);
                            }
                            ServiceUtils.closeStream(objectContent);
                            ServiceUtils.closeStream(inputStream2);
                            if (this.downloadFileRequest.isEnableCheckpoint()) {
                            }
                            return partResultDown;
                        } catch (Exception e2) {
                            e = e2;
                            inputStream = objectContent;
                            objectContent = randomAccessFile;
                            partResultDown.setFailed(true);
                            partResultDown.setException(e);
                            if (ResumableClient.log.isErrorEnabled()) {
                                ResumableClient.log.error(String.format("Task %d:%s download part %d failed: ", Integer.valueOf(this.id), this.name, Integer.valueOf(this.partIndex)), e);
                            }
                            ServiceUtils.closeStream(objectContent);
                            ServiceUtils.closeStream(inputStream);
                            if (this.downloadFileRequest.isEnableCheckpoint()) {
                            }
                            return partResultDown;
                        } catch (Throwable th) {
                            th = th;
                            r6 = objectContent;
                            objectContent = randomAccessFile;
                            ServiceUtils.closeStream(objectContent);
                            ServiceUtils.closeStream(r6);
                            if (this.downloadFileRequest.isEnableCheckpoint()) {
                                this.downloadCheckPoint.updateTmpFile(this.downloadFileRequest.getTempDownloadFile());
                                this.downloadCheckPoint.record(this.downloadFileRequest.getCheckpointFile());
                            }
                            throw th;
                        }
                    } catch (ObsException e3) {
                        e = e3;
                        inputStream2 = null;
                    } catch (Exception e4) {
                        e = e4;
                        inputStream = null;
                    } catch (Throwable th2) {
                        th = th2;
                        r6 = 0;
                    }
                    if (this.downloadFileRequest.isEnableCheckpoint()) {
                        this.downloadCheckPoint.updateTmpFile(this.downloadFileRequest.getTempDownloadFile());
                        this.downloadCheckPoint.record(this.downloadFileRequest.getCheckpointFile());
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }
            return partResultDown;
        }

        public void setProgressManager(ProgressManager progressManager) {
            this.progressManager = progressManager;
        }
    }

    static class TmpFileStatus implements Serializable {
        private static final long serialVersionUID = 4478330948103112660L;
        public Date lastModified;
        public long size;
        public String tmpFilePath;

        public TmpFileStatus(long j, Date date, String str) {
            this.size = j;
            this.lastModified = date;
            this.tmpFilePath = str;
        }

        public boolean equals(Object obj) {
            return obj != null && (obj instanceof TmpFileStatus) && ((TmpFileStatus) obj).hashCode() == hashCode();
        }

        public int hashCode() {
            Date date = this.lastModified;
            int iHashCode = ((date == null ? 0 : date.hashCode()) + 31) * 31;
            String str = this.tmpFilePath;
            int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
            long j = this.size;
            return iHashCode2 + ((int) (j ^ (j >>> 32)));
        }
    }

    static class UploadCheckPoint implements Serializable {
        private static final long serialVersionUID = 5564757792864743464L;
        public String bucketName;
        public volatile transient boolean isAbort = false;
        public int md5;
        public String objectKey;
        public ArrayList<PartEtag> partEtags;
        public String uploadFile;
        public FileStatus uploadFileStatus;
        public String uploadID;
        public ArrayList<UploadPart> uploadParts;

        UploadCheckPoint() {
        }

        private void assign(UploadCheckPoint uploadCheckPoint) {
            this.md5 = uploadCheckPoint.md5;
            this.bucketName = uploadCheckPoint.bucketName;
            this.uploadFile = uploadCheckPoint.uploadFile;
            this.uploadFileStatus = uploadCheckPoint.uploadFileStatus;
            this.objectKey = uploadCheckPoint.objectKey;
            this.uploadID = uploadCheckPoint.uploadID;
            this.uploadParts = uploadCheckPoint.uploadParts;
            this.partEtags = uploadCheckPoint.partEtags;
        }

        public boolean equals(Object obj) {
            return obj != null && (obj instanceof UploadCheckPoint) && ((UploadCheckPoint) obj).hashCode() == obj.hashCode();
        }

        public int hashCode() {
            String str = this.objectKey;
            int iHashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
            String str2 = this.bucketName;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            ArrayList<PartEtag> arrayList = this.partEtags;
            int iHashCode3 = (iHashCode2 + (arrayList == null ? 0 : arrayList.hashCode())) * 31;
            String str3 = this.uploadFile;
            int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
            FileStatus fileStatus = this.uploadFileStatus;
            int iHashCode5 = (iHashCode4 + (fileStatus == null ? 0 : fileStatus.hashCode())) * 31;
            String str4 = this.uploadID;
            int iHashCode6 = (iHashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
            ArrayList<UploadPart> arrayList2 = this.uploadParts;
            return iHashCode6 + (arrayList2 != null ? arrayList2.hashCode() : 0);
        }

        public boolean isValid(String str) throws IOException {
            if (this.md5 != hashCode()) {
                return false;
            }
            File file = new File(str);
            if (!this.uploadFile.equals(str) || this.uploadFileStatus.size != file.length() || this.uploadFileStatus.lastModified != file.lastModified()) {
                return false;
            }
            if (this.uploadFileStatus.checkSum == null) {
                return true;
            }
            try {
                return this.uploadFileStatus.checkSum.equals(ServiceUtils.toBase64(ServiceUtils.computeMD5Hash(new FileInputStream(file))));
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        public void load(String str) throws Exception {
            FileInputStream fileInputStream;
            Throwable th;
            SecureObjectInputStream secureObjectInputStream;
            try {
                fileInputStream = new FileInputStream(str);
                try {
                    secureObjectInputStream = new SecureObjectInputStream(fileInputStream);
                } catch (Throwable th2) {
                    th = th2;
                    secureObjectInputStream = null;
                }
                try {
                    assign((UploadCheckPoint) secureObjectInputStream.readObject());
                    ServiceUtils.closeStream(secureObjectInputStream);
                    ServiceUtils.closeStream(fileInputStream);
                } catch (Throwable th3) {
                    th = th3;
                    ServiceUtils.closeStream(secureObjectInputStream);
                    ServiceUtils.closeStream(fileInputStream);
                    throw th;
                }
            } catch (Throwable th4) {
                fileInputStream = null;
                th = th4;
                secureObjectInputStream = null;
            }
        }

        public synchronized void record(String str) throws IOException {
            FileOutputStream fileOutputStream;
            Throwable th;
            ObjectOutputStream objectOutputStream;
            this.md5 = hashCode();
            try {
                fileOutputStream = new FileOutputStream(str);
                try {
                    objectOutputStream = new ObjectOutputStream(fileOutputStream);
                } catch (Throwable th2) {
                    th = th2;
                    objectOutputStream = null;
                }
                try {
                    objectOutputStream.writeObject(this);
                    ServiceUtils.closeStream(objectOutputStream);
                    ServiceUtils.closeStream(fileOutputStream);
                } catch (Throwable th3) {
                    th = th3;
                    ServiceUtils.closeStream(objectOutputStream);
                    ServiceUtils.closeStream(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th4) {
                fileOutputStream = null;
                th = th4;
                objectOutputStream = null;
            }
        }

        public synchronized void update(int i, PartEtag partEtag, boolean z) {
            this.partEtags.add(partEtag);
            this.uploadParts.get(i).isCompleted = z;
        }
    }

    static class UploadPart implements Serializable {
        private static final long serialVersionUID = 751520598820222785L;
        public boolean isCompleted;
        public long offset;
        public int partNumber;
        public long size;

        UploadPart() {
        }

        public boolean equals(Object obj) {
            return obj != null && (obj instanceof UploadPart) && ((UploadPart) obj).hashCode() == obj.hashCode();
        }

        public int hashCode() {
            int i = ((((this.isCompleted ? 1 : 0) + 31) * 31) + this.partNumber) * 31;
            long j = this.offset;
            long j2 = this.size;
            return ((i + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)));
        }
    }

    public ResumableClient(ObsClient obsClient) {
        this.obsClient = obsClient;
    }

    private DownloadResult download(DownloadCheckPoint downloadCheckPoint, DownloadFileRequest downloadFileRequest) throws Exception {
        ConcurrentProgressManager concurrentProgressManager;
        int i;
        ArrayList arrayList = new ArrayList();
        DownloadResult downloadResult = new DownloadResult();
        ExecutorService executorServiceNewFixedThreadPool = Executors.newFixedThreadPool(downloadFileRequest.getTaskNum());
        ArrayList arrayList2 = new ArrayList();
        if (downloadFileRequest.getProgressListener() == null) {
            for (int i2 = 0; i2 < downloadCheckPoint.downloadParts.size(); i2++) {
                DownloadPart downloadPart = downloadCheckPoint.downloadParts.get(i2);
                if (downloadPart.isCompleted) {
                    arrayList.add(new PartResultDown(i2 + 1, downloadPart.offset, downloadPart.end));
                } else {
                    arrayList2.add(executorServiceNewFixedThreadPool.submit(new Task(i2, "download-" + i2, downloadCheckPoint, i2, downloadFileRequest, this.obsClient)));
                }
            }
            concurrentProgressManager = null;
        } else {
            LinkedList<Task> linkedList = new LinkedList();
            int i3 = 0;
            long j = 0;
            while (i3 < downloadCheckPoint.downloadParts.size()) {
                DownloadPart downloadPart2 = downloadCheckPoint.downloadParts.get(i3);
                if (downloadPart2.isCompleted) {
                    i = i3;
                    j += (downloadPart2.end - downloadPart2.offset) + 1;
                    arrayList.add(new PartResultDown(i + 1, downloadPart2.offset, downloadPart2.end));
                } else {
                    i = i3;
                    linkedList.add(new Task(i3, "download-" + i3, downloadCheckPoint, i3, downloadFileRequest, this.obsClient));
                }
                i3 = i + 1;
            }
            ConcurrentProgressManager concurrentProgressManager2 = new ConcurrentProgressManager(downloadCheckPoint.objectStatus.size, j, downloadFileRequest.getProgressListener(), downloadFileRequest.getProgressInterval() > 0 ? downloadFileRequest.getProgressInterval() : 102400L);
            for (Task task : linkedList) {
                task.setProgressManager(concurrentProgressManager2);
                arrayList2.add(executorServiceNewFixedThreadPool.submit(task));
            }
            concurrentProgressManager = concurrentProgressManager2;
        }
        executorServiceNewFixedThreadPool.shutdown();
        executorServiceNewFixedThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            try {
                arrayList.add((PartResultDown) ((Future) it.next()).get());
            } catch (ExecutionException e) {
                throw e;
            }
        }
        downloadResult.setPartResults(arrayList);
        if (concurrentProgressManager != null) {
            concurrentProgressManager.progressEnd();
        }
        return downloadResult;
    }

    private DownloadFileResult downloadCheckPoint(DownloadFileRequest downloadFileRequest) throws Exception {
        DownloadFileResult downloadFileResult = new DownloadFileResult();
        try {
            ObjectMetadata objectMetadata = this.obsClient.getObjectMetadata(new GetObjectMetadataRequest(downloadFileRequest.getBucketName(), downloadFileRequest.getObjectKey(), downloadFileRequest.getVersionId()));
            downloadFileResult.setObjectMetadata(objectMetadata);
            if (objectMetadata.getContentLength().longValue() == 0) {
                File file = new File(downloadFileRequest.getTempDownloadFile());
                if (file.exists() && file.isFile()) {
                    file.delete();
                }
                File file2 = new File(downloadFileRequest.getCheckpointFile());
                if (file2.isFile() && file2.exists()) {
                    file2.delete();
                }
                File file3 = new File(downloadFileRequest.getDownloadFile());
                file3.getParentFile().mkdirs();
                new RandomAccessFile(file3, "rw").close();
                if (downloadFileRequest.getProgressListener() != null) {
                    downloadFileRequest.getProgressListener().progressChanged(new DefaultProgressStatus(0L, 0L, 0L, 0L, 0L));
                }
                return downloadFileResult;
            }
            DownloadCheckPoint downloadCheckPoint = new DownloadCheckPoint();
            if (downloadFileRequest.isEnableCheckpoint()) {
                boolean z = false;
                boolean z2 = true;
                try {
                    downloadCheckPoint.load(downloadFileRequest.getCheckpointFile());
                } catch (Exception unused) {
                    z = true;
                }
                if (z || (downloadFileRequest.getBucketName().equals(downloadCheckPoint.bucketName) && downloadFileRequest.getObjectKey().equals(downloadCheckPoint.objectKey) && downloadFileRequest.getDownloadFile().equals(downloadCheckPoint.downloadFile) && downloadCheckPoint.isValid(downloadFileRequest.getTempDownloadFile(), objectMetadata) && (downloadFileRequest.getVersionId() != null ? downloadFileRequest.getVersionId().equals(downloadCheckPoint.versionId) : downloadCheckPoint.versionId == null))) {
                    z2 = z;
                }
                if (z2) {
                    if (downloadCheckPoint.tmpFileStatus != null) {
                        File file4 = new File(downloadCheckPoint.tmpFileStatus.tmpFilePath);
                        if (file4.exists() && file4.isFile()) {
                            file4.delete();
                        }
                    }
                    File file5 = new File(downloadFileRequest.getCheckpointFile());
                    if (file5.isFile() && file5.exists()) {
                        file5.delete();
                    }
                    prepare(downloadFileRequest, downloadCheckPoint, objectMetadata);
                }
            } else {
                prepare(downloadFileRequest, downloadCheckPoint, objectMetadata);
            }
            for (PartResultDown partResultDown : download(downloadCheckPoint, downloadFileRequest).getPartResults()) {
                if (partResultDown.isFailed() && partResultDown.getException() != null) {
                    if (!downloadFileRequest.isEnableCheckpoint()) {
                        File file6 = new File(downloadCheckPoint.tmpFileStatus.tmpFilePath);
                        if (file6.exists() && file6.isFile()) {
                            file6.delete();
                        }
                    } else if (downloadCheckPoint.isAbort) {
                        File file7 = new File(downloadCheckPoint.tmpFileStatus.tmpFilePath);
                        if (file7.exists() && file7.isFile()) {
                            file7.delete();
                        }
                        File file8 = new File(downloadFileRequest.getCheckpointFile());
                        if (file8.isFile() && file8.exists()) {
                            file8.delete();
                        }
                    }
                    throw partResultDown.getException();
                }
            }
            renameTo(downloadFileRequest.getTempDownloadFile(), downloadFileRequest.getDownloadFile());
            if (downloadFileRequest.isEnableCheckpoint()) {
                File file9 = new File(downloadFileRequest.getCheckpointFile());
                if (file9.isFile() && file9.exists()) {
                    file9.delete();
                }
            }
            return downloadFileResult;
        } catch (ObsException e) {
            if (e.getResponseCode() >= 300 && e.getResponseCode() < 500 && e.getResponseCode() != 408) {
                File file10 = new File(downloadFileRequest.getTempDownloadFile());
                if (file10.exists() && file10.isFile()) {
                    file10.delete();
                }
                File file11 = new File(downloadFileRequest.getCheckpointFile());
                if (file11.isFile() && file11.exists()) {
                    file11.delete();
                }
            }
            throw e;
        }
    }

    private void prepare(DownloadFileRequest downloadFileRequest, DownloadCheckPoint downloadCheckPoint, ObjectMetadata objectMetadata) throws Exception {
        downloadCheckPoint.bucketName = downloadFileRequest.getBucketName();
        downloadCheckPoint.objectKey = downloadFileRequest.getObjectKey();
        downloadCheckPoint.versionId = downloadFileRequest.getVersionId();
        downloadCheckPoint.downloadFile = downloadFileRequest.getDownloadFile();
        ObjectStatus objectStatus = new ObjectStatus();
        objectStatus.size = objectMetadata.getContentLength().longValue();
        objectStatus.lastModified = objectMetadata.getLastModified();
        objectStatus.Etag = objectMetadata.getEtag();
        downloadCheckPoint.objectStatus = objectStatus;
        downloadCheckPoint.downloadParts = splitObject(downloadCheckPoint.objectStatus.size, downloadFileRequest.getPartSize());
        File file = new File(downloadFileRequest.getTempDownloadFile());
        file.getParentFile().mkdirs();
        RandomAccessFile randomAccessFile = null;
        try {
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rw");
            try {
                randomAccessFile2.setLength(downloadCheckPoint.objectStatus.size);
                ServiceUtils.closeStream(randomAccessFile2);
                downloadCheckPoint.tmpFileStatus = new TmpFileStatus(downloadCheckPoint.objectStatus.size, new Date(file.lastModified()), downloadFileRequest.getTempDownloadFile());
                if (downloadFileRequest.isEnableCheckpoint()) {
                    try {
                        downloadCheckPoint.record(downloadFileRequest.getCheckpointFile());
                    } catch (Exception e) {
                        if (file.exists() && file.isFile()) {
                            file.delete();
                        }
                        throw e;
                    }
                }
            } catch (Throwable th) {
                th = th;
                randomAccessFile = randomAccessFile2;
                ServiceUtils.closeStream(randomAccessFile);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private void prepare(UploadFileRequest uploadFileRequest, UploadCheckPoint uploadCheckPoint) throws Exception {
        uploadCheckPoint.uploadFile = uploadFileRequest.getUploadFile();
        uploadCheckPoint.bucketName = uploadFileRequest.getBucketName();
        uploadCheckPoint.objectKey = uploadFileRequest.getObjectKey();
        uploadCheckPoint.uploadFileStatus = FileStatus.getFileStatus(uploadCheckPoint.uploadFile, uploadFileRequest.isEnableCheckSum());
        uploadCheckPoint.uploadParts = splitUploadFile(uploadCheckPoint.uploadFileStatus.size, uploadFileRequest.getPartSize());
        uploadCheckPoint.partEtags = new ArrayList<>();
        InitiateMultipartUploadRequest initiateMultipartUploadRequest = new InitiateMultipartUploadRequest(uploadFileRequest.getBucketName(), uploadFileRequest.getObjectKey());
        initiateMultipartUploadRequest.setExtensionPermissionMap(uploadFileRequest.getExtensionPermissionMap());
        initiateMultipartUploadRequest.setAcl(uploadFileRequest.getAcl());
        initiateMultipartUploadRequest.setSuccessRedirectLocation(uploadFileRequest.getSuccessRedirectLocation());
        initiateMultipartUploadRequest.setSseCHeader(uploadFileRequest.getSseCHeader());
        initiateMultipartUploadRequest.setSseKmsHeader(uploadFileRequest.getSseKmsHeader());
        uploadCheckPoint.uploadID = this.obsClient.initiateMultipartUpload(initiateMultipartUploadRequest).getUploadId();
        if (uploadFileRequest.isEnableCheckpoint()) {
            try {
                uploadCheckPoint.record(uploadFileRequest.getCheckpointFile());
            } catch (Exception e) {
                abortMultipartUploadSilent(uploadCheckPoint.uploadID, uploadCheckPoint.bucketName, uploadCheckPoint.objectKey);
                throw e;
            }
        }
    }

    private void renameTo(String str, String str2) throws Throwable {
        FileOutputStream fileOutputStream;
        File file = new File(str);
        File file2 = new File(str2);
        if (!file.exists()) {
            throw new FileNotFoundException("tmpFile '" + file + "' does not exist");
        }
        if (file2.exists() && !file2.delete()) {
            throw new IOException("downloadFile '" + file2 + "' is exist");
        }
        if (file.isDirectory() || file2.isDirectory()) {
            throw new IOException("downloadPath is a directory");
        }
        if (file.renameTo(file2)) {
            return;
        }
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
                try {
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int i = fileInputStream2.read(bArr);
                        if (i <= 0) {
                            break;
                        } else {
                            fileOutputStream.write(bArr, 0, i);
                        }
                    }
                    ServiceUtils.closeStream(fileInputStream2);
                    ServiceUtils.closeStream(fileOutputStream);
                    if (file.delete()) {
                        return;
                    }
                    throw new IOException("the tmpfile '" + file + "' can not delete, please delete it to ensure the download finish.");
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                    ServiceUtils.closeStream(fileInputStream);
                    ServiceUtils.closeStream(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = null;
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
        }
    }

    private ArrayList<DownloadPart> splitObject(long j, long j2) {
        ArrayList<DownloadPart> arrayList = new ArrayList<>();
        long j3 = 0;
        if (j / j2 >= 10000) {
            j2 = j / 10000;
            if (j % 10000 != 0) {
                j2++;
            }
        }
        int i = 0;
        while (j3 < j) {
            DownloadPart downloadPart = new DownloadPart();
            downloadPart.partNumber = i;
            downloadPart.offset = j3;
            j3 += j2;
            if (j3 > j) {
                downloadPart.end = j - 1;
            } else {
                downloadPart.end = j3 - 1;
            }
            arrayList.add(downloadPart);
            i++;
        }
        return arrayList;
    }

    private ArrayList<UploadPart> splitUploadFile(long j, long j2) {
        long j3;
        long j4;
        ArrayList<UploadPart> arrayList = new ArrayList<>();
        long j5 = j / j2;
        long j6 = 1;
        long j7 = 0;
        if (j5 >= 10000) {
            j4 = j / 10000;
            if (j % 10000 != 0) {
                j4++;
            }
            j3 = j / j4;
        } else {
            j3 = j5;
            j4 = j2;
        }
        long j8 = j % j4;
        if (j8 > 0) {
            j3++;
        }
        if (j3 == 0) {
            UploadPart uploadPart = new UploadPart();
            uploadPart.partNumber = 1;
            uploadPart.offset = 0L;
            uploadPart.size = 0L;
            uploadPart.isCompleted = false;
            arrayList.add(uploadPart);
        } else {
            long j9 = 0;
            while (j9 < j3) {
                UploadPart uploadPart2 = new UploadPart();
                long j10 = j9 + j6;
                uploadPart2.partNumber = (int) j10;
                uploadPart2.offset = j9 * j4;
                uploadPart2.size = j4;
                uploadPart2.isCompleted = false;
                arrayList.add(uploadPart2);
                j9 = j10;
                j6 = 1;
                j7 = 0;
            }
            if (j8 > j7) {
                arrayList.get(arrayList.size() - 1).size = j8;
            }
        }
        return arrayList;
    }

    private CompleteMultipartUploadResult uploadFileCheckPoint(UploadFileRequest uploadFileRequest) throws Exception {
        UploadCheckPoint uploadCheckPoint = new UploadCheckPoint();
        if (uploadFileRequest.isEnableCheckpoint()) {
            boolean z = false;
            boolean z2 = true;
            try {
                uploadCheckPoint.load(uploadFileRequest.getCheckpointFile());
            } catch (Exception unused) {
                z = true;
            }
            if (z || (uploadFileRequest.getBucketName().equals(uploadCheckPoint.bucketName) && uploadFileRequest.getObjectKey().equals(uploadCheckPoint.objectKey) && uploadFileRequest.getUploadFile().equals(uploadCheckPoint.uploadFile) && uploadCheckPoint.isValid(uploadFileRequest.getUploadFile()))) {
                z2 = z;
            }
            if (z2) {
                if (uploadCheckPoint.bucketName != null && uploadCheckPoint.objectKey != null && uploadCheckPoint.uploadID != null) {
                    abortMultipartUploadSilent(uploadCheckPoint.uploadID, uploadCheckPoint.bucketName, uploadCheckPoint.objectKey);
                }
                File file = new File(uploadFileRequest.getCheckpointFile());
                if (file.exists() && file.isFile()) {
                    file.delete();
                }
                prepare(uploadFileRequest, uploadCheckPoint);
            }
        } else {
            prepare(uploadFileRequest, uploadCheckPoint);
        }
        for (PartResult partResult : uploadfile(uploadFileRequest, uploadCheckPoint)) {
            if (partResult.isFailed() && partResult.getException() != null) {
                if (!uploadFileRequest.isEnableCheckpoint()) {
                    abortMultipartUploadSilent(uploadCheckPoint.uploadID, uploadFileRequest.getBucketName(), uploadFileRequest.getObjectKey());
                } else if (uploadCheckPoint.isAbort) {
                    abortMultipartUploadSilent(uploadCheckPoint.uploadID, uploadFileRequest.getBucketName(), uploadFileRequest.getObjectKey());
                    File file2 = new File(uploadFileRequest.getCheckpointFile());
                    if (file2.exists() && file2.isFile()) {
                        file2.delete();
                    }
                }
                throw partResult.getException();
            }
        }
        try {
            CompleteMultipartUploadResult completeMultipartUploadResultCompleteMultipartUpload = this.obsClient.completeMultipartUpload(new CompleteMultipartUploadRequest(uploadFileRequest.getBucketName(), uploadFileRequest.getObjectKey(), uploadCheckPoint.uploadID, uploadCheckPoint.partEtags));
            if (uploadFileRequest.isEnableCheckpoint()) {
                File file3 = new File(uploadFileRequest.getCheckpointFile());
                if (file3.isFile() && file3.exists()) {
                    file3.delete();
                }
            }
            return completeMultipartUploadResultCompleteMultipartUpload;
        } catch (ObsException e) {
            if (!uploadFileRequest.isEnableCheckpoint()) {
                abortMultipartUpload(uploadCheckPoint.uploadID, uploadFileRequest.getBucketName(), uploadFileRequest.getObjectKey());
            } else if (e.getResponseCode() >= 300 && e.getResponseCode() < 500 && e.getResponseCode() != 408) {
                abortMultipartUploadSilent(uploadCheckPoint.uploadID, uploadFileRequest.getBucketName(), uploadFileRequest.getObjectKey());
                File file4 = new File(uploadFileRequest.getCheckpointFile());
                if (file4.exists() && file4.isFile()) {
                    file4.delete();
                }
            }
            throw e;
        }
    }

    private List<PartResult> uploadfile(UploadFileRequest uploadFileRequest, UploadCheckPoint uploadCheckPoint) throws Exception {
        ConcurrentProgressManager concurrentProgressManager;
        ArrayList arrayList = new ArrayList();
        ExecutorService executorServiceNewFixedThreadPool = Executors.newFixedThreadPool(uploadFileRequest.getTaskNum());
        ArrayList arrayList2 = new ArrayList();
        if (uploadFileRequest.getProgressListener() == null) {
            for (int i = 0; i < uploadCheckPoint.uploadParts.size(); i++) {
                UploadPart uploadPart = uploadCheckPoint.uploadParts.get(i);
                if (uploadPart.isCompleted) {
                    PartResult partResult = new PartResult(uploadPart.partNumber, uploadPart.offset, uploadPart.size);
                    partResult.setFailed(false);
                    arrayList.add(partResult);
                } else {
                    arrayList2.add(executorServiceNewFixedThreadPool.submit(new Mission(i, uploadCheckPoint, i, uploadFileRequest, this.obsClient)));
                }
            }
            concurrentProgressManager = null;
        } else {
            LinkedList<Mission> linkedList = new LinkedList();
            long j = 0;
            for (int i2 = 0; i2 < uploadCheckPoint.uploadParts.size(); i2++) {
                UploadPart uploadPart2 = uploadCheckPoint.uploadParts.get(i2);
                if (uploadPart2.isCompleted) {
                    PartResult partResult2 = new PartResult(uploadPart2.partNumber, uploadPart2.offset, uploadPart2.size);
                    partResult2.setFailed(false);
                    arrayList.add(partResult2);
                    j += uploadPart2.size;
                } else {
                    linkedList.add(new Mission(i2, uploadCheckPoint, i2, uploadFileRequest, this.obsClient));
                }
            }
            concurrentProgressManager = new ConcurrentProgressManager(uploadCheckPoint.uploadFileStatus.size, j, uploadFileRequest.getProgressListener(), uploadFileRequest.getProgressInterval() > 0 ? uploadFileRequest.getProgressInterval() : 102400L);
            for (Mission mission : linkedList) {
                mission.setProgressManager(concurrentProgressManager);
                arrayList2.add(executorServiceNewFixedThreadPool.submit(mission));
            }
        }
        executorServiceNewFixedThreadPool.shutdown();
        executorServiceNewFixedThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            try {
                arrayList.add((PartResult) ((Future) it.next()).get());
            } catch (ExecutionException e) {
                if (!uploadFileRequest.isEnableCheckpoint()) {
                    abortMultipartUploadSilent(uploadCheckPoint.uploadID, uploadFileRequest.getBucketName(), uploadFileRequest.getObjectKey());
                }
                throw e;
            }
        }
        if (concurrentProgressManager != null) {
            concurrentProgressManager.progressEnd();
        }
        return arrayList;
    }

    protected HeaderResponse abortMultipartUpload(String str, String str2, String str3) {
        return this.obsClient.abortMultipartUpload(new AbortMultipartUploadRequest(str2, str3, str));
    }

    protected void abortMultipartUploadSilent(String str, String str2, String str3) {
        try {
            this.obsClient.abortMultipartUpload(new AbortMultipartUploadRequest(str2, str3, str));
        } catch (Exception e) {
            if (log.isWarnEnabled()) {
                log.warn("Abort multipart upload failed", e);
            }
        }
    }

    public DownloadFileResult downloadFileResume(DownloadFileRequest downloadFileRequest) {
        ServiceUtils.asserParameterNotNull(downloadFileRequest, "DownloadFileRequest is null");
        ServiceUtils.asserParameterNotNull(downloadFileRequest.getBucketName(), "the bucketName is null");
        String objectKey = downloadFileRequest.getObjectKey();
        ServiceUtils.asserParameterNotNull2(objectKey, "the objectKey is null");
        if (downloadFileRequest.getDownloadFile() == null) {
            downloadFileRequest.setDownloadFile(objectKey);
        }
        if (downloadFileRequest.isEnableCheckpoint() && (downloadFileRequest.getCheckpointFile() == null || downloadFileRequest.getCheckpointFile().isEmpty())) {
            downloadFileRequest.setCheckpointFile(downloadFileRequest.getDownloadFile() + ".downloadFile_record");
        }
        try {
            return downloadCheckPoint(downloadFileRequest);
        } catch (ObsException e) {
            throw e;
        } catch (ServiceException e2) {
            throw ServiceUtils.changeFromServiceException(e2);
        } catch (Exception e3) {
            throw new ObsException(e3.getMessage(), e3);
        }
    }

    public CompleteMultipartUploadResult uploadFileResume(UploadFileRequest uploadFileRequest) {
        ServiceUtils.asserParameterNotNull(uploadFileRequest, "UploadFileRequest is null");
        ServiceUtils.asserParameterNotNull(uploadFileRequest.getBucketName(), "bucketName is null");
        ServiceUtils.asserParameterNotNull2(uploadFileRequest.getObjectKey(), "objectKey is null");
        ServiceUtils.asserParameterNotNull(uploadFileRequest.getUploadFile(), "uploadfile is null");
        if (uploadFileRequest.isEnableCheckpoint() && !ServiceUtils.isValid(uploadFileRequest.getCheckpointFile())) {
            uploadFileRequest.setCheckpointFile(uploadFileRequest.getUploadFile() + ".uploadFile_record");
        }
        try {
            return uploadFileCheckPoint(uploadFileRequest);
        } catch (ObsException e) {
            throw e;
        } catch (ServiceException e2) {
            throw ServiceUtils.changeFromServiceException(e2);
        } catch (Exception e3) {
            throw new ObsException(e3.getMessage(), e3);
        }
    }
}
