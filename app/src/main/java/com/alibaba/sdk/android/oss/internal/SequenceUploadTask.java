package com.alibaba.sdk.android.oss.internal;

import android.text.TextUtils;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.TaskCancelException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.utils.BinaryUtil;
import com.alibaba.sdk.android.oss.common.utils.CRC64;
import com.alibaba.sdk.android.oss.common.utils.OSSSharedPreferences;
import com.alibaba.sdk.android.oss.common.utils.OSSUtils;
import com.alibaba.sdk.android.oss.model.AbortMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.InitiateMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.InitiateMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.ListPartsRequest;
import com.alibaba.sdk.android.oss.model.ListPartsResult;
import com.alibaba.sdk.android.oss.model.PartETag;
import com.alibaba.sdk.android.oss.model.PartSummary;
import com.alibaba.sdk.android.oss.model.ResumableUploadRequest;
import com.alibaba.sdk.android.oss.model.ResumableUploadResult;
import com.alibaba.sdk.android.oss.model.UploadPartRequest;
import com.alibaba.sdk.android.oss.model.UploadPartResult;
import com.alibaba.sdk.android.oss.network.ExecutionContext;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.zip.CheckedInputStream;

public class SequenceUploadTask extends BaseMultipartUploadTask<ResumableUploadRequest, ResumableUploadResult> implements Callable<ResumableUploadResult> {
    private List<Integer> mAlreadyUploadIndex;
    private File mCRC64RecordFile;
    private long mFirstPartSize;
    private File mRecordFile;
    private OSSSharedPreferences mSp;

    public SequenceUploadTask(ResumableUploadRequest resumableUploadRequest, OSSCompletedCallback<ResumableUploadRequest, ResumableUploadResult> oSSCompletedCallback, ExecutionContext executionContext, InternalRequestOperation internalRequestOperation) {
        super(internalRequestOperation, resumableUploadRequest, oSSCompletedCallback, executionContext);
        this.mAlreadyUploadIndex = new ArrayList();
        this.mSp = OSSSharedPreferences.instance(this.mContext.getApplicationContext());
    }

    @Override
    protected void abortThisUpload() throws ExecutionException, InterruptedException {
        if (this.mUploadId != null) {
            this.mApiOperation.abortMultipartUpload(new AbortMultipartUploadRequest(((ResumableUploadRequest) this.mRequest).getBucketName(), ((ResumableUploadRequest) this.mRequest).getObjectKey(), this.mUploadId), null).waitUntilFinished();
        }
    }

    @Override
    protected void checkException() throws Throwable {
        if (this.mContext.getCancellationHandler().isCancelled()) {
            if (((ResumableUploadRequest) this.mRequest).deleteUploadOnCancelling().booleanValue()) {
                abortThisUpload();
                File file = this.mRecordFile;
                if (file != null) {
                    file.delete();
                }
            } else if (this.mPartETags != null && this.mPartETags.size() > 0 && this.mCheckCRC64 && ((ResumableUploadRequest) this.mRequest).getRecordDirectory() != null) {
                HashMap map = new HashMap();
                for (PartETag partETag : this.mPartETags) {
                    map.put(Integer.valueOf(partETag.getPartNumber()), Long.valueOf(partETag.getCRC64()));
                }
                ObjectOutputStream objectOutputStream = null;
                try {
                    try {
                        File file2 = new File(((ResumableUploadRequest) this.mRequest).getRecordDirectory() + File.separator + this.mUploadId);
                        this.mCRC64RecordFile = file2;
                        if (!file2.exists()) {
                            this.mCRC64RecordFile.createNewFile();
                        }
                        ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(new FileOutputStream(this.mCRC64RecordFile));
                        try {
                            objectOutputStream2.writeObject(map);
                            objectOutputStream2.close();
                        } catch (IOException e) {
                            e = e;
                            objectOutputStream = objectOutputStream2;
                            OSSLog.logThrowable2Local(e);
                            if (objectOutputStream != null) {
                                objectOutputStream.close();
                            }
                            super.checkException();
                        } catch (Throwable th) {
                            th = th;
                            objectOutputStream = objectOutputStream2;
                            if (objectOutputStream != null) {
                                objectOutputStream.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (IOException e2) {
                    e = e2;
                }
            }
        }
        super.checkException();
    }

    @Override
    protected ResumableUploadResult doMultipartUpload() throws Throwable {
        long j = this.mUploadedLength;
        checkCancel();
        int i = this.mPartAttr[0];
        int i2 = this.mPartAttr[1];
        if (this.mPartETags.size() > 0 && this.mAlreadyUploadIndex.size() > 0) {
            if (this.mUploadedLength > this.mFileLength) {
                throw new ClientException("The uploading file is inconsistent with before");
            }
            if (this.mFirstPartSize != i) {
                throw new ClientException("The part size setting is inconsistent with before");
            }
            long jLongValue = this.mUploadedLength;
            if (!TextUtils.isEmpty(this.mSp.getStringValue(this.mUploadId))) {
                jLongValue = Long.valueOf(this.mSp.getStringValue(this.mUploadId)).longValue();
            }
            long j2 = jLongValue;
            if (this.mProgressCallback != null) {
                this.mProgressCallback.onProgress(this.mRequest, j2, this.mFileLength);
            }
            this.mSp.removeKey(this.mUploadId);
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (this.mAlreadyUploadIndex.size() == 0 || !this.mAlreadyUploadIndex.contains(Integer.valueOf(i3 + 1))) {
                if (i3 == i2 - 1) {
                    i = (int) (this.mFileLength - j);
                }
                OSSLog.logDebug("upload part readByte : " + i);
                j += (long) i;
                uploadPart(i3, i, i2);
                if (this.mUploadException != null) {
                    break;
                }
            }
        }
        checkException();
        CompleteMultipartUploadResult completeMultipartUploadResult = completeMultipartUploadResult();
        ResumableUploadResult resumableUploadResult = completeMultipartUploadResult != null ? new ResumableUploadResult(completeMultipartUploadResult) : null;
        File file = this.mRecordFile;
        if (file != null) {
            file.delete();
        }
        File file2 = this.mCRC64RecordFile;
        if (file2 != null) {
            file2.delete();
        }
        return resumableUploadResult;
    }

    @Override
    protected void initMultipartUploadId() throws ExecutionException, ServiceException, InterruptedException, ClientException, IOException {
        Map map;
        int nextPartNumberMarker;
        boolean zIsTruncated;
        List<PartSummary> parts;
        int i;
        if (!OSSUtils.isEmptyString(((ResumableUploadRequest) this.mRequest).getRecordDirectory())) {
            String strCalculateMd5Str = BinaryUtil.calculateMd5Str(this.mUploadFilePath);
            StringBuilder sb = new StringBuilder();
            sb.append(strCalculateMd5Str);
            sb.append(((ResumableUploadRequest) this.mRequest).getBucketName());
            sb.append(((ResumableUploadRequest) this.mRequest).getObjectKey());
            sb.append(String.valueOf(((ResumableUploadRequest) this.mRequest).getPartSize()));
            sb.append(this.mCheckCRC64 ? "-crc64" : "");
            sb.append("-sequence");
            File file = new File(((ResumableUploadRequest) this.mRequest).getRecordDirectory() + File.separator + BinaryUtil.calculateMd5Str(sb.toString().getBytes()));
            this.mRecordFile = file;
            if (file.exists()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(this.mRecordFile));
                this.mUploadId = bufferedReader.readLine();
                bufferedReader.close();
                OSSLog.logDebug("sequence [initUploadId] - Found record file, uploadid: " + this.mUploadId);
            }
            if (!OSSUtils.isEmptyString(this.mUploadId)) {
                if (this.mCheckCRC64) {
                    File file2 = new File(((ResumableUploadRequest) this.mRequest).getRecordDirectory() + File.separator + this.mUploadId);
                    if (file2.exists()) {
                        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file2));
                        try {
                            try {
                                map = (Map) objectInputStream.readObject();
                                try {
                                    file2.delete();
                                } catch (ClassNotFoundException e) {
                                    e = e;
                                    OSSLog.logThrowable2Local(e);
                                    nextPartNumberMarker = 0;
                                    do {
                                        ListPartsRequest listPartsRequest = new ListPartsRequest(((ResumableUploadRequest) this.mRequest).getBucketName(), ((ResumableUploadRequest) this.mRequest).getObjectKey(), this.mUploadId);
                                        if (nextPartNumberMarker > 0) {
                                        }
                                        OSSAsyncTask<ListPartsResult> oSSAsyncTaskListParts = this.mApiOperation.listParts(listPartsRequest, null);
                                        try {
                                            ListPartsResult listPartsResult = (ListPartsResult) oSSAsyncTaskListParts.getResult();
                                            zIsTruncated = listPartsResult.isTruncated();
                                            nextPartNumberMarker = listPartsResult.getNextPartNumberMarker();
                                            parts = listPartsResult.getParts();
                                            while (i < parts.size()) {
                                            }
                                        } catch (ClientException e2) {
                                            throw e2;
                                        } catch (ServiceException e3) {
                                            if (e3.getStatusCode() != 404) {
                                                throw e3;
                                            }
                                            this.mUploadId = null;
                                            zIsTruncated = false;
                                        }
                                        oSSAsyncTaskListParts.waitUntilFinished();
                                    } while (zIsTruncated);
                                    if (!this.mRecordFile.exists()) {
                                        throw new ClientException("Can't create file at path: " + this.mRecordFile.getAbsolutePath() + "\nPlease make sure the directory exist!");
                                    }
                                    if (OSSUtils.isEmptyString(this.mUploadId)) {
                                    }
                                    ((ResumableUploadRequest) this.mRequest).setUploadId(this.mUploadId);
                                }
                            } finally {
                                objectInputStream.close();
                                file2.delete();
                            }
                        } catch (ClassNotFoundException e4) {
                            e = e4;
                            map = null;
                        }
                    } else {
                        map = null;
                    }
                    nextPartNumberMarker = 0;
                    do {
                        ListPartsRequest listPartsRequest2 = new ListPartsRequest(((ResumableUploadRequest) this.mRequest).getBucketName(), ((ResumableUploadRequest) this.mRequest).getObjectKey(), this.mUploadId);
                        if (nextPartNumberMarker > 0) {
                            listPartsRequest2.setPartNumberMarker(Integer.valueOf(nextPartNumberMarker));
                        }
                        OSSAsyncTask<ListPartsResult> oSSAsyncTaskListParts2 = this.mApiOperation.listParts(listPartsRequest2, null);
                        ListPartsResult listPartsResult2 = (ListPartsResult) oSSAsyncTaskListParts2.getResult();
                        zIsTruncated = listPartsResult2.isTruncated();
                        nextPartNumberMarker = listPartsResult2.getNextPartNumberMarker();
                        parts = listPartsResult2.getParts();
                        for (i = 0; i < parts.size(); i++) {
                            PartSummary partSummary = parts.get(i);
                            PartETag partETag = new PartETag(partSummary.getPartNumber(), partSummary.getETag());
                            partETag.setPartSize(partSummary.getSize());
                            if (map != null && map.size() > 0 && map.containsKey(Integer.valueOf(partETag.getPartNumber()))) {
                                partETag.setCRC64(((Long) map.get(Integer.valueOf(partETag.getPartNumber()))).longValue());
                            }
                            this.mPartETags.add(partETag);
                            this.mUploadedLength += partSummary.getSize();
                            this.mAlreadyUploadIndex.add(Integer.valueOf(partSummary.getPartNumber()));
                            if (i == 0) {
                                this.mFirstPartSize = partSummary.getSize();
                            }
                        }
                        oSSAsyncTaskListParts2.waitUntilFinished();
                    } while (zIsTruncated);
                }
            }
            if (!this.mRecordFile.exists() && !this.mRecordFile.createNewFile()) {
                throw new ClientException("Can't create file at path: " + this.mRecordFile.getAbsolutePath() + "\nPlease make sure the directory exist!");
            }
        }
        if (OSSUtils.isEmptyString(this.mUploadId)) {
            InitiateMultipartUploadRequest initiateMultipartUploadRequest = new InitiateMultipartUploadRequest(((ResumableUploadRequest) this.mRequest).getBucketName(), ((ResumableUploadRequest) this.mRequest).getObjectKey(), ((ResumableUploadRequest) this.mRequest).getMetadata());
            initiateMultipartUploadRequest.isSequential = true;
            this.mUploadId = ((InitiateMultipartUploadResult) this.mApiOperation.initMultipartUpload(initiateMultipartUploadRequest, null).getResult()).getUploadId();
            if (this.mRecordFile != null) {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.mRecordFile));
                bufferedWriter.write(this.mUploadId);
                bufferedWriter.close();
            }
        }
        ((ResumableUploadRequest) this.mRequest).setUploadId(this.mUploadId);
    }

    @Override
    protected void processException(Exception exc) {
        if (this.mUploadException == null || !exc.getMessage().equals(this.mUploadException.getMessage())) {
            this.mUploadException = exc;
        }
        OSSLog.logThrowable2Local(exc);
        if (!this.mContext.getCancellationHandler().isCancelled() || this.mIsCancel) {
            return;
        }
        this.mIsCancel = true;
    }

    @Override
    public void uploadPart(int i, int i2, int i3) throws Throwable {
        RandomAccessFile randomAccessFile;
        RandomAccessFile randomAccessFile2 = null;
        uploadPartRequest = null;
        UploadPartRequest uploadPartRequest = null;
        randomAccessFile2 = null;
        try {
            try {
                try {
                    try {
                    } catch (ServiceException e) {
                        e = e;
                        randomAccessFile = null;
                    } catch (Exception e2) {
                        e = e2;
                    }
                    if (this.mContext.getCancellationHandler().isCancelled()) {
                        return;
                    }
                    this.mRunPartTaskCount++;
                    preUploadPart(i, i2, i3);
                    randomAccessFile = new RandomAccessFile(this.mUploadFile, "r");
                    try {
                        try {
                            UploadPartRequest uploadPartRequest2 = new UploadPartRequest(((ResumableUploadRequest) this.mRequest).getBucketName(), ((ResumableUploadRequest) this.mRequest).getObjectKey(), this.mUploadId, i + 1);
                            try {
                                byte[] bArr = new byte[i2];
                                randomAccessFile.seek(i * ((ResumableUploadRequest) this.mRequest).getPartSize());
                                randomAccessFile.readFully(bArr, 0, i2);
                                uploadPartRequest2.setPartContent(bArr);
                                uploadPartRequest2.setMd5Digest(BinaryUtil.calculateBase64Md5(bArr));
                                uploadPartRequest2.setCRC64(((ResumableUploadRequest) this.mRequest).getCRC64());
                                UploadPartResult uploadPartResultSyncUploadPart = this.mApiOperation.syncUploadPart(uploadPartRequest2);
                                PartETag partETag = new PartETag(uploadPartRequest2.getPartNumber(), uploadPartResultSyncUploadPart.getETag());
                                long j = i2;
                                partETag.setPartSize(j);
                                if (this.mCheckCRC64) {
                                    partETag.setCRC64(uploadPartResultSyncUploadPart.getClientCRC().longValue());
                                }
                                this.mPartETags.add(partETag);
                                this.mUploadedLength += j;
                                uploadPartFinish(partETag);
                            } catch (ServiceException e3) {
                                e = e3;
                                uploadPartRequest = uploadPartRequest2;
                                if (e.getStatusCode() != 409) {
                                    processException(e);
                                } else {
                                    PartETag partETag2 = new PartETag(uploadPartRequest.getPartNumber(), e.getPartEtag());
                                    partETag2.setPartSize(uploadPartRequest.getPartContent().length);
                                    if (this.mCheckCRC64) {
                                        partETag2.setCRC64(new CheckedInputStream(new ByteArrayInputStream(uploadPartRequest.getPartContent()), new CRC64()).getChecksum().getValue());
                                    }
                                    this.mPartETags.add(partETag2);
                                    this.mUploadedLength += i2;
                                }
                                if (randomAccessFile != null) {
                                    randomAccessFile.close();
                                }
                                return;
                            }
                        } catch (Exception e4) {
                            e = e4;
                            randomAccessFile2 = randomAccessFile;
                            processException(e);
                            if (randomAccessFile2 != null) {
                                randomAccessFile2.close();
                            }
                            return;
                        }
                    } catch (ServiceException e5) {
                        e = e5;
                    }
                    if (this.mContext.getCancellationHandler().isCancelled()) {
                        TaskCancelException taskCancelException = new TaskCancelException("sequence upload task cancel");
                        throw new ClientException(taskCancelException.getMessage(), taskCancelException, true);
                    }
                    onProgressCallback(this.mRequest, this.mUploadedLength, this.mFileLength);
                    randomAccessFile.close();
                } catch (Throwable th) {
                    th = th;
                    if (randomAccessFile2 != null) {
                        try {
                            randomAccessFile2.close();
                        } catch (IOException e6) {
                            OSSLog.logThrowable2Local(e6);
                        }
                    }
                    throw th;
                }
            } catch (IOException e7) {
                OSSLog.logThrowable2Local(e7);
            }
        } catch (Throwable th2) {
            th = th2;
            randomAccessFile2 = i3;
        }
    }

    @Override
    protected void uploadPartFinish(PartETag partETag) throws Exception {
        if (!this.mContext.getCancellationHandler().isCancelled() || this.mSp.contains(this.mUploadId)) {
            return;
        }
        this.mSp.setStringValue(this.mUploadId, String.valueOf(this.mUploadedLength));
        onProgressCallback(this.mRequest, this.mUploadedLength, this.mFileLength);
    }
}
