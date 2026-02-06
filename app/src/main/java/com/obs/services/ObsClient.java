package com.obs.services;

import com.obs.log.ILogger;
import com.obs.log.InterfaceLogBean;
import com.obs.log.LoggerBuilder;
import com.obs.services.exception.ObsException;
import com.obs.services.internal.ObsProperties;
import com.obs.services.internal.ObsService;
import com.obs.services.internal.ResumableClient;
import com.obs.services.internal.ServiceException;
import com.obs.services.internal.consensus.CacheManager;
import com.obs.services.internal.consensus.SegmentLock;
import com.obs.services.internal.security.ProviderCredentials;
import com.obs.services.internal.task.DefaultTaskProgressStatus;
import com.obs.services.internal.task.DropFolderTask;
import com.obs.services.internal.task.LazyTaksCallback;
import com.obs.services.internal.task.PutObjectTask;
import com.obs.services.internal.task.RestoreObjectTask;
import com.obs.services.internal.task.ResumableUploadTask;
import com.obs.services.internal.task.UploadTaskProgressStatus;
import com.obs.services.internal.utils.AccessLoggerUtils;
import com.obs.services.internal.utils.ReflectUtils;
import com.obs.services.internal.utils.ServiceUtils;
import com.obs.services.model.AbortMultipartUploadRequest;
import com.obs.services.model.AccessControlList;
import com.obs.services.model.AppendObjectRequest;
import com.obs.services.model.AppendObjectResult;
import com.obs.services.model.AuthTypeEnum;
import com.obs.services.model.BucketCors;
import com.obs.services.model.BucketDirectColdAccess;
import com.obs.services.model.BucketEncryption;
import com.obs.services.model.BucketLocationResponse;
import com.obs.services.model.BucketLoggingConfiguration;
import com.obs.services.model.BucketMetadataInfoRequest;
import com.obs.services.model.BucketMetadataInfoResult;
import com.obs.services.model.BucketNotificationConfiguration;
import com.obs.services.model.BucketPolicyResponse;
import com.obs.services.model.BucketQuota;
import com.obs.services.model.BucketStorageInfo;
import com.obs.services.model.BucketStoragePolicyConfiguration;
import com.obs.services.model.BucketTagInfo;
import com.obs.services.model.BucketVersioningConfiguration;
import com.obs.services.model.CompleteMultipartUploadRequest;
import com.obs.services.model.CompleteMultipartUploadResult;
import com.obs.services.model.CopyObjectRequest;
import com.obs.services.model.CopyObjectResult;
import com.obs.services.model.CopyPartRequest;
import com.obs.services.model.CopyPartResult;
import com.obs.services.model.CreateBucketRequest;
import com.obs.services.model.DeleteObjectResult;
import com.obs.services.model.DeleteObjectsRequest;
import com.obs.services.model.DeleteObjectsResult;
import com.obs.services.model.DownloadFileRequest;
import com.obs.services.model.DownloadFileResult;
import com.obs.services.model.ExtensionObjectPermissionEnum;
import com.obs.services.model.GetObjectMetadataRequest;
import com.obs.services.model.GetObjectRequest;
import com.obs.services.model.HeaderResponse;
import com.obs.services.model.HttpMethodEnum;
import com.obs.services.model.InitiateMultipartUploadRequest;
import com.obs.services.model.InitiateMultipartUploadResult;
import com.obs.services.model.KeyAndVersion;
import com.obs.services.model.LifecycleConfiguration;
import com.obs.services.model.ListBucketsRequest;
import com.obs.services.model.ListBucketsResult;
import com.obs.services.model.ListMultipartUploadsRequest;
import com.obs.services.model.ListObjectsRequest;
import com.obs.services.model.ListPartsRequest;
import com.obs.services.model.ListPartsResult;
import com.obs.services.model.ListVersionsRequest;
import com.obs.services.model.ListVersionsResult;
import com.obs.services.model.MultipartUploadListing;
import com.obs.services.model.ObjectListing;
import com.obs.services.model.ObjectMetadata;
import com.obs.services.model.ObsBucket;
import com.obs.services.model.ObsObject;
import com.obs.services.model.OptionsInfoRequest;
import com.obs.services.model.OptionsInfoResult;
import com.obs.services.model.PolicyConditionItem;
import com.obs.services.model.PolicyTempSignatureRequest;
import com.obs.services.model.PostSignatureRequest;
import com.obs.services.model.PostSignatureResponse;
import com.obs.services.model.ProgressListener;
import com.obs.services.model.ProgressStatus;
import com.obs.services.model.PutObjectBasicRequest;
import com.obs.services.model.PutObjectRequest;
import com.obs.services.model.PutObjectResult;
import com.obs.services.model.PutObjectsRequest;
import com.obs.services.model.ReadAheadQueryResult;
import com.obs.services.model.ReadAheadRequest;
import com.obs.services.model.ReadAheadResult;
import com.obs.services.model.ReplicationConfiguration;
import com.obs.services.model.RestoreObjectRequest;
import com.obs.services.model.RestoreObjectResult;
import com.obs.services.model.RestoreObjectsRequest;
import com.obs.services.model.RestoreTierEnum;
import com.obs.services.model.S3Bucket;
import com.obs.services.model.S3BucketCors;
import com.obs.services.model.SetObjectMetadataRequest;
import com.obs.services.model.SpecialParamEnum;
import com.obs.services.model.SseCHeader;
import com.obs.services.model.SseKmsHeader;
import com.obs.services.model.StorageClassEnum;
import com.obs.services.model.TaskCallback;
import com.obs.services.model.TaskProgressListener;
import com.obs.services.model.TaskProgressStatus;
import com.obs.services.model.TemporarySignatureRequest;
import com.obs.services.model.TemporarySignatureResponse;
import com.obs.services.model.UploadFileRequest;
import com.obs.services.model.UploadObjectsProgressListener;
import com.obs.services.model.UploadPartRequest;
import com.obs.services.model.UploadPartResult;
import com.obs.services.model.UploadProgressStatus;
import com.obs.services.model.V4PostSignatureRequest;
import com.obs.services.model.V4PostSignatureResponse;
import com.obs.services.model.V4TemporarySignatureRequest;
import com.obs.services.model.V4TemporarySignatureResponse;
import com.obs.services.model.VersionOrDeleteMarker;
import com.obs.services.model.WebsiteConfiguration;
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
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ObsClient extends ObsService implements Closeable, IObsClient, IFSClient {
    private static final ILogger ILOG = LoggerBuilder.getLogger((Class<?>) ObsClient.class);

    private abstract class ActionCallbackWithResult<T> {
        private ActionCallbackWithResult() {
        }

        abstract T action() throws ServiceException;

        void authTypeNegotiate(String str) throws ServiceException {
            ObsClient.this.getProviderCredentials().setThreadLocalAuthType(ObsClient.this.getApiVersion(str));
        }
    }

    public ObsClient(ObsConfiguration obsConfiguration) throws NumberFormatException {
        init("", "", null, obsConfiguration == null ? new ObsConfiguration() : obsConfiguration);
    }

    public ObsClient(String str) throws NumberFormatException {
        ObsConfiguration obsConfiguration = new ObsConfiguration();
        obsConfiguration.setEndPoint(str);
        init("", "", null, obsConfiguration);
    }

    public ObsClient(String str, String str2, ObsConfiguration obsConfiguration) throws NumberFormatException {
        init(str, str2, null, obsConfiguration == null ? new ObsConfiguration() : obsConfiguration);
    }

    public ObsClient(String str, String str2, String str3) throws NumberFormatException {
        ObsConfiguration obsConfiguration = new ObsConfiguration();
        obsConfiguration.setEndPoint(str3);
        init(str, str2, null, obsConfiguration);
    }

    public ObsClient(String str, String str2, String str3, ObsConfiguration obsConfiguration) throws NumberFormatException {
        init(str, str2, str3, obsConfiguration == null ? new ObsConfiguration() : obsConfiguration);
    }

    public ObsClient(String str, String str2, String str3, String str4) throws NumberFormatException {
        ObsConfiguration obsConfiguration = new ObsConfiguration();
        obsConfiguration.setEndPoint(str4);
        init(str, str2, str3, obsConfiguration);
    }

    private boolean checkDropFutures(Map<String, Future<?>> map, DefaultTaskProgressStatus defaultTaskProgressStatus, TaskCallback<DeleteObjectResult, String> taskCallback, TaskProgressListener taskProgressListener, int i) throws ExecutionException, InterruptedException {
        boolean z = true;
        for (Map.Entry<String, Future<?>> entry : map.entrySet()) {
            try {
                entry.getValue().get();
            } catch (InterruptedException e) {
                defaultTaskProgressStatus.failTaskIncrement();
                taskCallback.onException(new ObsException(e.getMessage(), e), entry.getKey());
                z = false;
                recordBulkTaskStatus(defaultTaskProgressStatus, taskCallback, taskProgressListener, i);
            } catch (ExecutionException e2) {
                defaultTaskProgressStatus.failTaskIncrement();
                if (e2.getCause() instanceof ObsException) {
                    taskCallback.onException((ObsException) e2.getCause(), entry.getKey());
                } else {
                    taskCallback.onException(new ObsException(e2.getMessage(), e2), entry.getKey());
                }
                z = false;
                recordBulkTaskStatus(defaultTaskProgressStatus, taskCallback, taskProgressListener, i);
            }
            recordBulkTaskStatus(defaultTaskProgressStatus, taskCallback, taskProgressListener, i);
        }
        return z;
    }

    private PolicyTempSignatureRequest createPolicyGetRequest(String str, String str2, String str3, Map<String, String> map, Map<String, Object> map2) {
        PolicyTempSignatureRequest policyTempSignatureRequest = new PolicyTempSignatureRequest(HttpMethodEnum.GET, str, str2);
        ArrayList arrayList = new ArrayList();
        PolicyConditionItem policyConditionItem = new PolicyConditionItem(PolicyConditionItem.ConditionOperator.STARTS_WITH, "key", str3);
        if (isCname()) {
            str = getEndpoint();
        }
        PolicyConditionItem policyConditionItem2 = new PolicyConditionItem(PolicyConditionItem.ConditionOperator.EQUAL, "bucket", str);
        arrayList.add(policyConditionItem);
        arrayList.add(policyConditionItem2);
        policyTempSignatureRequest.setConditions(arrayList);
        policyTempSignatureRequest.setHeaders(map);
        policyTempSignatureRequest.setQueryParams(map2);
        return policyTempSignatureRequest;
    }

    private <T> T doActionWithResult(String str, String str2, ActionCallbackWithResult<T> actionCallbackWithResult) throws ObsException {
        if (!isCname()) {
            ServiceUtils.asserParameterNotNull(str2, "bucketName is null");
        }
        InterfaceLogBean interfaceLogBean = new InterfaceLogBean(str, getEndpoint(), "");
        try {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (isAuthTypeNegotiation()) {
                    actionCallbackWithResult.authTypeNegotiate(str2);
                }
                T tAction = actionCallbackWithResult.action();
                interfaceLogBean.setRespTime(new Date());
                interfaceLogBean.setResultCode("0");
                if (ILOG.isInfoEnabled()) {
                    ILOG.info(interfaceLogBean);
                }
                if (ILOG.isInfoEnabled()) {
                    ILOG.info((CharSequence) ("ObsClient [" + str + "] cost " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms"));
                }
                return tAction;
            } catch (ServiceException e) {
                ObsException obsExceptionChangeFromServiceException = ServiceUtils.changeFromServiceException(e);
                if (obsExceptionChangeFromServiceException.getResponseCode() < 400 || obsExceptionChangeFromServiceException.getResponseCode() >= 500) {
                    if (!ILOG.isErrorEnabled()) {
                        throw obsExceptionChangeFromServiceException;
                    }
                    interfaceLogBean.setRespTime(new Date());
                    interfaceLogBean.setResultCode(String.valueOf(obsExceptionChangeFromServiceException.getResponseCode()));
                    ILOG.error(interfaceLogBean);
                    throw obsExceptionChangeFromServiceException;
                }
                if (!ILOG.isWarnEnabled()) {
                    throw obsExceptionChangeFromServiceException;
                }
                interfaceLogBean.setRespTime(new Date());
                interfaceLogBean.setResultCode(String.valueOf(e.getResponseCode()));
                ILOG.warn(interfaceLogBean);
                throw obsExceptionChangeFromServiceException;
            }
        } finally {
            if (isAuthTypeNegotiation()) {
                getProviderCredentials().removeThreadLocalAuthType();
            }
            AccessLoggerUtils.printLog();
        }
    }

    private void init(String str, String str2, String str3, ObsConfiguration obsConfiguration) throws NumberFormatException {
        String str4;
        InterfaceLogBean interfaceLogBean = new InterfaceLogBean("ObsClient", obsConfiguration.getEndPoint(), "");
        ProviderCredentials providerCredentials = new ProviderCredentials(str, str2, str3);
        ObsProperties obsPropertiesChangeFromObsConfiguration = ServiceUtils.changeFromObsConfiguration(obsConfiguration);
        providerCredentials.setAuthType(obsConfiguration.getAuthType());
        this.obsProperties = obsPropertiesChangeFromObsConfiguration;
        this.credentials = providerCredentials;
        this.obsProperties = obsPropertiesChangeFromObsConfiguration;
        this.keyManagerFactory = obsConfiguration.getKeyManagerFactory();
        this.trustManagerFactory = obsConfiguration.getTrustManagerFactory();
        if (isAuthTypeNegotiation()) {
            this.apiVersionCache = new CacheManager();
            getProviderCredentials().initThreadLocalAuthType();
            this.segmentLock = new SegmentLock();
        }
        initHttpClient(obsConfiguration.getHttpDispatcher());
        interfaceLogBean.setRespTime(new Date());
        interfaceLogBean.setResultCode("0");
        if (ILOG.isInfoEnabled()) {
            ILOG.info(interfaceLogBean);
        }
        if (ILOG.isWarnEnabled()) {
            StringBuilder sb = new StringBuilder("[OBS SDK Version=");
            sb.append("3.19.5.3");
            sb.append("];");
            sb.append("[Endpoint=");
            if (getHttpsOnly()) {
                str4 = "https://" + getEndpoint() + ":" + getHttpsPort() + "/";
            } else {
                str4 = "http://" + getEndpoint() + ":" + getHttpPort() + "/";
            }
            sb.append(str4);
            sb.append("];");
            sb.append("[Access Mode=");
            sb.append(isPathStyle() ? "Path" : "Virtul Hosting");
            sb.append("]");
            ILOG.warn((CharSequence) sb);
        }
    }

    private boolean recurseFolders(String str, String str2, TaskCallback<DeleteObjectResult, String> taskCallback, int i, DefaultTaskProgressStatus defaultTaskProgressStatus, TaskProgressListener taskProgressListener, ThreadPoolExecutor threadPoolExecutor, int[] iArr) throws ObsException {
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(str2);
        listObjectsRequest.setDelimiter("/");
        listObjectsRequest.setPrefix(str);
        int i2 = 1;
        boolean z = true;
        while (true) {
            ObjectListing objectListingListObjects = listObjects(listObjectsRequest);
            HashMap map = new HashMap();
            boolean z2 = z;
            for (ObsObject obsObject : objectListingListObjects.getObjects()) {
                if (!obsObject.getObjectKey().endsWith("/")) {
                    iArr[0] = iArr[0] + i2;
                    boolean z3 = submitDropTask(obsObject.getObjectKey(), str2, taskCallback, i, defaultTaskProgressStatus, taskProgressListener, threadPoolExecutor, map) && z2;
                    if (ILOG.isInfoEnabled() && iArr[0] % 1000 == 0) {
                        ILOG.info((CharSequence) ("DropFolder: " + iArr + " tasks have submitted to delete objects"));
                    }
                    z2 = z3;
                }
                i2 = 1;
            }
            for (String str3 : objectListingListObjects.getCommonPrefixes()) {
                boolean zRecurseFolders = recurseFolders(str3, str2, taskCallback, i, defaultTaskProgressStatus, taskProgressListener, threadPoolExecutor, iArr);
                iArr[0] = iArr[0] + 1;
                if (zRecurseFolders) {
                    z2 = submitDropTask(str3, str2, taskCallback, i, defaultTaskProgressStatus, taskProgressListener, threadPoolExecutor, map) && z2;
                } else {
                    defaultTaskProgressStatus.failTaskIncrement();
                    taskCallback.onException(new ObsException("Failed to delete due to child file deletion failed"), str3);
                    recordBulkTaskStatus(defaultTaskProgressStatus, taskCallback, taskProgressListener, i);
                }
                if (ILOG.isInfoEnabled() && iArr[0] % 1000 == 0) {
                    ILOG.info((CharSequence) ("DropFolder: " + iArr + " tasks have submitted to delete objects"));
                }
            }
            listObjectsRequest.setMarker(objectListingListObjects.getNextMarker());
            z = checkDropFutures(map, defaultTaskProgressStatus, taskCallback, taskProgressListener, i) && z2;
            if (!objectListingListObjects.isTruncated()) {
                return z;
            }
            i2 = 1;
        }
    }

    private boolean submitDropTask(String str, String str2, TaskCallback<DeleteObjectResult, String> taskCallback, int i, DefaultTaskProgressStatus defaultTaskProgressStatus, TaskProgressListener taskProgressListener, ThreadPoolExecutor threadPoolExecutor, Map<String, Future<?>> map) {
        try {
            map.put(str, threadPoolExecutor.submit(new DropFolderTask(this, str2, str, defaultTaskProgressStatus, taskProgressListener, i, taskCallback)));
            return true;
        } catch (RejectedExecutionException e) {
            defaultTaskProgressStatus.failTaskIncrement();
            taskCallback.onException(new ObsException(e.getMessage(), e), str);
            return false;
        }
    }

    private void uploadObjectTask(PutObjectsRequest putObjectsRequest, String str, final String str2, ThreadPoolExecutor threadPoolExecutor, final UploadTaskProgressStatus uploadTaskProgressStatus, TaskCallback<PutObjectResult, PutObjectBasicRequest> taskCallback, final UploadObjectsProgressListener uploadObjectsProgressListener) {
        File file = new File(str);
        String bucketName = putObjectsRequest.getBucketName();
        int progressInterval = putObjectsRequest.getProgressInterval();
        int taskNum = putObjectsRequest.getTaskNum();
        long detailProgressInterval = putObjectsRequest.getDetailProgressInterval();
        long bigfileThreshold = putObjectsRequest.getBigfileThreshold();
        long partSize = putObjectsRequest.getPartSize();
        AccessControlList acl = putObjectsRequest.getAcl();
        Map<ExtensionObjectPermissionEnum, Set<String>> extensionPermissionMap = putObjectsRequest.getExtensionPermissionMap();
        SseCHeader sseCHeader = putObjectsRequest.getSseCHeader();
        SseKmsHeader sseKmsHeader = putObjectsRequest.getSseKmsHeader();
        String successRedirectLocation = putObjectsRequest.getSuccessRedirectLocation();
        if (file.length() <= bigfileThreshold) {
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, str2, file);
            putObjectRequest.setExtensionPermissionMap(extensionPermissionMap);
            putObjectRequest.setAcl(acl);
            putObjectRequest.setSuccessRedirectLocation(successRedirectLocation);
            putObjectRequest.setSseCHeader(sseCHeader);
            putObjectRequest.setSseKmsHeader(sseKmsHeader);
            uploadTaskProgressStatus.addTotalSize(file.length());
            putObjectRequest.setProgressListener(new ProgressListener() {
                @Override
                public void progressChanged(ProgressStatus progressStatus) {
                    uploadTaskProgressStatus.putTaskTable(str2, progressStatus);
                    if (uploadTaskProgressStatus.isRefreshprogress()) {
                        uploadTaskProgressStatus.setTotalMilliseconds(new Date().getTime() - uploadTaskProgressStatus.getStartDate().getTime());
                        uploadObjectsProgressListener.progressChanged(uploadTaskProgressStatus);
                    }
                }
            });
            putObjectRequest.setProgressInterval(detailProgressInterval);
            threadPoolExecutor.execute(new PutObjectTask(this, bucketName, putObjectRequest, taskCallback, uploadObjectsProgressListener, uploadTaskProgressStatus, progressInterval));
            return;
        }
        UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName, str2);
        uploadFileRequest.setUploadFile(str);
        uploadFileRequest.setPartSize(partSize);
        uploadFileRequest.setTaskNum(taskNum);
        uploadFileRequest.setExtensionPermissionMap(extensionPermissionMap);
        uploadFileRequest.setAcl(acl);
        uploadFileRequest.setSuccessRedirectLocation(successRedirectLocation);
        uploadFileRequest.setSseCHeader(sseCHeader);
        uploadFileRequest.setSseKmsHeader(sseKmsHeader);
        uploadFileRequest.setEnableCheckpoint(true);
        uploadTaskProgressStatus.addTotalSize(file.length());
        uploadFileRequest.setProgressListener(new ProgressListener() {
            @Override
            public void progressChanged(ProgressStatus progressStatus) {
                uploadTaskProgressStatus.putTaskTable(str2, progressStatus);
                if (uploadTaskProgressStatus.isRefreshprogress()) {
                    uploadTaskProgressStatus.setTotalMilliseconds(new Date().getTime() - uploadTaskProgressStatus.getStartDate().getTime());
                    uploadObjectsProgressListener.progressChanged(uploadTaskProgressStatus);
                }
            }
        });
        uploadFileRequest.setProgressInterval(detailProgressInterval);
        threadPoolExecutor.execute(new ResumableUploadTask(this, bucketName, uploadFileRequest, taskCallback, uploadObjectsProgressListener, uploadTaskProgressStatus, progressInterval));
    }

    @Override
    public HeaderResponse abortMultipartUpload(final AbortMultipartUploadRequest abortMultipartUploadRequest) throws ObsException {
        ServiceUtils.asserParameterNotNull(abortMultipartUploadRequest, "AbortMultipartUploadRequest is null");
        ServiceUtils.asserParameterNotNull2(abortMultipartUploadRequest.getObjectKey(), "objectKey is null");
        ServiceUtils.asserParameterNotNull(abortMultipartUploadRequest.getUploadId(), "uploadId is null");
        return (HeaderResponse) doActionWithResult("abortMultipartUpload", abortMultipartUploadRequest.getBucketName(), new ActionCallbackWithResult<HeaderResponse>() {
            {
                super();
            }

            @Override
            public HeaderResponse action() throws ServiceException {
                return ObsClient.this.abortMultipartUploadImpl(abortMultipartUploadRequest.getUploadId(), abortMultipartUploadRequest.getBucketName(), abortMultipartUploadRequest.getObjectKey());
            }
        });
    }

    @Override
    public ObsFSFile appendFile(WriteFileRequest writeFileRequest) throws ObsException {
        ServiceUtils.asserParameterNotNull(writeFileRequest, "WriteFileRequest is null");
        ServiceUtils.asserParameterNotNull2(writeFileRequest.getObjectKey(), "objectKey is null");
        ObjectMetadata objectMetadata = getObjectMetadata(new GetObjectMetadataRequest(writeFileRequest.getBucketName(), writeFileRequest.getObjectKey()));
        if (writeFileRequest.getPosition() >= 0 && writeFileRequest.getPosition() != objectMetadata.getNextPosition()) {
            throw new IllegalArgumentException("Where you proposed append to is not equal to length");
        }
        writeFileRequest.setPosition(objectMetadata.getNextPosition());
        return writeFile(writeFileRequest);
    }

    @Override
    public AppendObjectResult appendObject(final AppendObjectRequest appendObjectRequest) throws ObsException {
        ServiceUtils.asserParameterNotNull(appendObjectRequest, "AppendObjectRequest is null");
        ServiceUtils.asserParameterNotNull2(appendObjectRequest.getObjectKey(), "objectKey is null");
        return (AppendObjectResult) doActionWithResult("appendObject", appendObjectRequest.getBucketName(), new ActionCallbackWithResult<AppendObjectResult>() {
            {
                super();
            }

            @Override
            public AppendObjectResult action() throws ServiceException {
                if (appendObjectRequest.getInput() == null || appendObjectRequest.getFile() == null) {
                    return ObsClient.this.appendObjectImpl(appendObjectRequest);
                }
                throw new ServiceException("Both input and file are set, only one is allowed");
            }
        });
    }

    public String base64Md5(InputStream inputStream) throws NoSuchAlgorithmException, IOException {
        return ServiceUtils.toBase64(ServiceUtils.computeMD5Hash(inputStream));
    }

    public String base64Md5(InputStream inputStream, long j, long j2) throws NoSuchAlgorithmException, IOException {
        return ServiceUtils.toBase64(ServiceUtils.computeMD5Hash(inputStream, j, j2));
    }

    @Override
    public void close() throws IOException {
        shutdown();
    }

    @Override
    public CompleteMultipartUploadResult completeMultipartUpload(final CompleteMultipartUploadRequest completeMultipartUploadRequest) throws ObsException {
        ServiceUtils.asserParameterNotNull(completeMultipartUploadRequest, "CompleteMultipartUploadRequest is null");
        ServiceUtils.asserParameterNotNull2(completeMultipartUploadRequest.getObjectKey(), "objectKey is null");
        ServiceUtils.asserParameterNotNull(completeMultipartUploadRequest.getUploadId(), "uploadId is null");
        return (CompleteMultipartUploadResult) doActionWithResult("completeMultipartUpload", completeMultipartUploadRequest.getBucketName(), new ActionCallbackWithResult<CompleteMultipartUploadResult>() {
            {
                super();
            }

            @Override
            public CompleteMultipartUploadResult action() throws ServiceException {
                return ObsClient.this.completeMultipartUploadImpl(completeMultipartUploadRequest);
            }
        });
    }

    @Override
    public CopyObjectResult copyObject(final CopyObjectRequest copyObjectRequest) throws ObsException {
        ServiceUtils.asserParameterNotNull(copyObjectRequest, "CopyObjectRequest is null");
        ServiceUtils.asserParameterNotNull(copyObjectRequest.getDestinationBucketName(), "destinationBucketName is null");
        ServiceUtils.asserParameterNotNull2(copyObjectRequest.getSourceObjectKey(), "sourceObjectKey is null");
        ServiceUtils.asserParameterNotNull2(copyObjectRequest.getDestinationObjectKey(), "destinationObjectKey is null");
        return (CopyObjectResult) doActionWithResult("copyObject", copyObjectRequest.getSourceBucketName(), new ActionCallbackWithResult<CopyObjectResult>() {
            {
                super();
            }

            @Override
            public CopyObjectResult action() throws ServiceException {
                return ObsClient.this.copyObjectImpl(copyObjectRequest);
            }
        });
    }

    @Override
    public CopyObjectResult copyObject(String str, String str2, String str3, String str4) throws ObsException {
        return copyObject(new CopyObjectRequest(str, str2, str3, str4));
    }

    @Override
    public CopyPartResult copyPart(final CopyPartRequest copyPartRequest) throws ObsException {
        ServiceUtils.asserParameterNotNull(copyPartRequest, "CopyPartRequest is null");
        ServiceUtils.asserParameterNotNull2(copyPartRequest.getSourceObjectKey(), "sourceObjectKey is null");
        ServiceUtils.asserParameterNotNull(copyPartRequest.getDestinationBucketName(), "destinationBucketName is null");
        ServiceUtils.asserParameterNotNull2(copyPartRequest.getDestinationObjectKey(), "destinationObjectKey is null");
        ServiceUtils.asserParameterNotNull(copyPartRequest.getUploadId(), "uploadId is null");
        return (CopyPartResult) doActionWithResult("copyPart", copyPartRequest.getSourceBucketName(), new ActionCallbackWithResult<CopyPartResult>() {
            {
                super();
            }

            @Override
            public CopyPartResult action() throws ServiceException {
                return ObsClient.this.copyPartImpl(copyPartRequest);
            }
        });
    }

    @Override
    public ObsBucket createBucket(final CreateBucketRequest createBucketRequest) throws ObsException {
        ServiceUtils.asserParameterNotNull(createBucketRequest, "CreateBucketRequest is null");
        return (ObsBucket) doActionWithResult("createBucket", createBucketRequest.getBucketName(), new ActionCallbackWithResult<ObsBucket>() {
            {
                super();
            }

            @Override
            public ObsBucket action() throws ServiceException {
                if (ObsClient.this.isCname()) {
                    throw new ServiceException("createBucket is not allowed in customdomain mode");
                }
                try {
                    return ObsClient.this.createBucketImpl(createBucketRequest);
                } catch (ServiceException e) {
                    if (!ObsClient.this.isAuthTypeNegotiation() || e.getResponseCode() != 400 || !"Unsupported Authorization Type".equals(e.getErrorMessage()) || ObsClient.this.getProviderCredentials().getAuthType() != AuthTypeEnum.OBS) {
                        throw e;
                    }
                    ObsClient.this.getProviderCredentials().setThreadLocalAuthType(AuthTypeEnum.V2);
                    return ObsClient.this.createBucketImpl(createBucketRequest);
                }
            }

            @Override
            void authTypeNegotiate(String str) throws ServiceException {
                AuthTypeEnum apiVersionInCache = ObsClient.this.getApiVersionCache().getApiVersionInCache(str);
                if (apiVersionInCache == null) {
                    apiVersionInCache = ObsClient.this.getApiVersion("");
                }
                ObsClient.this.getProviderCredentials().setThreadLocalAuthType(apiVersionInCache);
            }
        });
    }

    @Override
    public ObsBucket createBucket(ObsBucket obsBucket) throws ObsException {
        CreateBucketRequest createBucketRequest = new CreateBucketRequest();
        createBucketRequest.setBucketName(obsBucket.getBucketName());
        createBucketRequest.setAcl(obsBucket.getAcl());
        createBucketRequest.setBucketStorageClass(obsBucket.getBucketStorageClass());
        createBucketRequest.setLocation(obsBucket.getLocation());
        return createBucket(createBucketRequest);
    }

    @Deprecated
    public ObsBucket createBucket(S3Bucket s3Bucket) throws ObsException {
        ServiceUtils.asserParameterNotNull(s3Bucket, "bucket is null");
        ObsBucket obsBucket = new ObsBucket();
        obsBucket.setBucketName(s3Bucket.getBucketName());
        obsBucket.setLocation(s3Bucket.getLocation());
        obsBucket.setAcl(s3Bucket.getAcl());
        obsBucket.setMetadata(s3Bucket.getMetadata());
        obsBucket.setBucketStorageClass(s3Bucket.getBucketStorageClass());
        return createBucket(obsBucket);
    }

    @Override
    public ObsBucket createBucket(String str) throws ObsException {
        ObsBucket obsBucket = new ObsBucket();
        obsBucket.setBucketName(str);
        return createBucket(obsBucket);
    }

    @Override
    public ObsBucket createBucket(String str, String str2) throws ObsException {
        ObsBucket obsBucket = new ObsBucket();
        obsBucket.setBucketName(str);
        obsBucket.setLocation(str2);
        return createBucket(obsBucket);
    }

    public TemporarySignatureResponse createGetTemporarySignature(String str, String str2, String str3, long j, Map<String, String> map, Map<String, Object> map2) {
        try {
            PolicyTempSignatureRequest policyTempSignatureRequestCreatePolicyGetRequest = createPolicyGetRequest(str, str2, str3, map, map2);
            policyTempSignatureRequestCreatePolicyGetRequest.setExpires(j);
            return _createTemporarySignature(policyTempSignatureRequestCreatePolicyGetRequest);
        } catch (Exception e) {
            throw new ObsException(e.getMessage(), e);
        }
    }

    public TemporarySignatureResponse createGetTemporarySignature(String str, String str2, String str3, Date date, Map<String, String> map, Map<String, Object> map2) {
        try {
            PolicyTempSignatureRequest policyTempSignatureRequestCreatePolicyGetRequest = createPolicyGetRequest(str, str2, str3, map, map2);
            policyTempSignatureRequestCreatePolicyGetRequest.setExpiryDate(date);
            return _createTemporarySignature(policyTempSignatureRequestCreatePolicyGetRequest);
        } catch (Exception e) {
            throw new ObsException(e.getMessage(), e);
        }
    }

    public PostSignatureResponse createPostSignature(long j, String str, String str2) throws ObsException {
        return createPostSignature(new PostSignatureRequest(j, new Date(), str, str2));
    }

    @Override
    public PostSignatureResponse createPostSignature(PostSignatureRequest postSignatureRequest) throws ObsException {
        ServiceUtils.asserParameterNotNull(postSignatureRequest, "PostSignatureRequest is null");
        InterfaceLogBean interfaceLogBean = new InterfaceLogBean("createPostSignature", getEndpoint(), "");
        try {
            PostSignatureResponse postSignatureResponse_createPostSignature = _createPostSignature(postSignatureRequest, getProviderCredentials().getAuthType() == AuthTypeEnum.V4);
            interfaceLogBean.setRespTime(new Date());
            interfaceLogBean.setResultCode("0");
            if (ILOG.isInfoEnabled()) {
                ILOG.info(interfaceLogBean);
            }
            return postSignatureResponse_createPostSignature;
        } catch (Exception e) {
            interfaceLogBean.setRespTime(new Date());
            if (ILOG.isErrorEnabled()) {
                ILOG.error(interfaceLogBean);
            }
            throw new ObsException(e.getMessage(), e);
        }
    }

    public PostSignatureResponse createPostSignature(String str, String str2, long j, String str3, String str4) throws ObsException {
        PostSignatureRequest postSignatureRequest = new PostSignatureRequest(j, new Date(), str3, str4);
        postSignatureRequest.getFormParams().put(getProviderCredentials().getAuthType() == AuthTypeEnum.V4 ? "acl" : getIHeaders().aclHeader(), str);
        postSignatureRequest.getFormParams().put("Content-Type", str2);
        return createPostSignature(postSignatureRequest);
    }

    @Deprecated
    public String createSignedUrl(HttpMethodEnum httpMethodEnum, String str, String str2, SpecialParamEnum specialParamEnum, long j, Map<String, String> map, Map<String, Object> map2) {
        TemporarySignatureRequest temporarySignatureRequest = new TemporarySignatureRequest();
        temporarySignatureRequest.setMethod(httpMethodEnum);
        temporarySignatureRequest.setBucketName(str);
        temporarySignatureRequest.setObjectKey(str2);
        temporarySignatureRequest.setSpecialParam(specialParamEnum);
        temporarySignatureRequest.setHeaders(map);
        temporarySignatureRequest.setQueryParams(map2);
        if (j > 0) {
            temporarySignatureRequest.setExpires(j);
        }
        return createTemporarySignature(temporarySignatureRequest).getSignedUrl();
    }

    @Deprecated
    public String createSignedUrl(HttpMethodEnum httpMethodEnum, String str, String str2, SpecialParamEnum specialParamEnum, Date date, Map<String, String> map, Map<String, Object> map2) throws ObsException {
        return createSignedUrl(httpMethodEnum, str, str2, specialParamEnum, date == null ? 300L : (date.getTime() - System.currentTimeMillis()) / 1000, map, map2);
    }

    @Override
    public TemporarySignatureResponse createTemporarySignature(TemporarySignatureRequest temporarySignatureRequest) {
        ServiceUtils.asserParameterNotNull(temporarySignatureRequest, "TemporarySignatureRequest is null");
        InterfaceLogBean interfaceLogBean = new InterfaceLogBean("createTemporarySignature", getEndpoint(), "");
        try {
            return getProviderCredentials().getAuthType() == AuthTypeEnum.V4 ? createV4TemporarySignature(temporarySignatureRequest) : _createTemporarySignature(temporarySignatureRequest);
        } catch (Exception e) {
            interfaceLogBean.setRespTime(new Date());
            if (ILOG.isErrorEnabled()) {
                ILOG.error(interfaceLogBean);
            }
            throw new ObsException(e.getMessage(), e);
        }
    }

    @Deprecated
    public V4PostSignatureResponse createV4PostSignature(long j, String str, String str2) throws ObsException {
        return createV4PostSignature(new V4PostSignatureRequest(j, new Date(), str, str2));
    }

    @Deprecated
    public V4PostSignatureResponse createV4PostSignature(V4PostSignatureRequest v4PostSignatureRequest) throws ObsException {
        ServiceUtils.asserParameterNotNull(v4PostSignatureRequest, "V4PostSignatureRequest is null");
        InterfaceLogBean interfaceLogBean = new InterfaceLogBean("createV4PostSignature", getEndpoint(), "");
        try {
            V4PostSignatureResponse v4PostSignatureResponse = (V4PostSignatureResponse) _createPostSignature(v4PostSignatureRequest, true);
            interfaceLogBean.setRespTime(new Date());
            interfaceLogBean.setResultCode("0");
            if (ILOG.isInfoEnabled()) {
                ILOG.info(interfaceLogBean);
            }
            return v4PostSignatureResponse;
        } catch (Exception e) {
            interfaceLogBean.setRespTime(new Date());
            if (ILOG.isErrorEnabled()) {
                ILOG.error(interfaceLogBean);
            }
            throw new ObsException(e.getMessage(), e);
        }
    }

    @Deprecated
    public V4PostSignatureResponse createV4PostSignature(String str, String str2, long j, String str3, String str4) throws ObsException {
        V4PostSignatureRequest v4PostSignatureRequest = new V4PostSignatureRequest(j, new Date(), str3, str4);
        v4PostSignatureRequest.getFormParams().put("acl", str);
        v4PostSignatureRequest.getFormParams().put("content-type", str2);
        return createV4PostSignature(v4PostSignatureRequest);
    }

    @Deprecated
    public V4TemporarySignatureResponse createV4TemporarySignature(V4TemporarySignatureRequest v4TemporarySignatureRequest) {
        ServiceUtils.asserParameterNotNull(v4TemporarySignatureRequest, "V4TemporarySignatureRequest is null");
        InterfaceLogBean interfaceLogBean = new InterfaceLogBean("createV4TemporarySignature", getEndpoint(), "");
        try {
            TemporarySignatureResponse temporarySignatureResponseCreateV4TemporarySignature = createV4TemporarySignature((TemporarySignatureRequest) v4TemporarySignatureRequest);
            V4TemporarySignatureResponse v4TemporarySignatureResponse = new V4TemporarySignatureResponse(temporarySignatureResponseCreateV4TemporarySignature.getSignedUrl());
            v4TemporarySignatureResponse.getActualSignedRequestHeaders().putAll(temporarySignatureResponseCreateV4TemporarySignature.getActualSignedRequestHeaders());
            return v4TemporarySignatureResponse;
        } catch (Exception e) {
            interfaceLogBean.setRespTime(new Date());
            if (ILOG.isErrorEnabled()) {
                ILOG.error(interfaceLogBean);
            }
            throw new ObsException(e.getMessage(), e);
        }
    }

    @Override
    public HeaderResponse deleteBucket(final String str) throws ObsException {
        return (HeaderResponse) doActionWithResult("deleteBucket", str, new ActionCallbackWithResult<HeaderResponse>() {
            {
                super();
            }

            @Override
            public HeaderResponse action() throws ServiceException {
                return ObsClient.this.deleteBucketImpl(str);
            }
        });
    }

    @Override
    public HeaderResponse deleteBucketCors(final String str) throws ObsException {
        return (HeaderResponse) doActionWithResult("deleteBucketCors", str, new ActionCallbackWithResult<HeaderResponse>() {
            {
                super();
            }

            @Override
            public HeaderResponse action() throws ServiceException {
                return ObsClient.this.deleteBucketCorsImpl(str);
            }
        });
    }

    @Override
    public HeaderResponse deleteBucketDirectColdAccess(final String str) throws ObsException {
        ServiceUtils.asserParameterNotNull(str, "bucketName is null");
        return (HeaderResponse) doActionWithResult("deleteBucketDirectColdAccess", str, new ActionCallbackWithResult<HeaderResponse>() {
            {
                super();
            }

            @Override
            public HeaderResponse action() throws ServiceException {
                return ObsClient.this.deleteBucketDirectColdAccessImpl(str);
            }
        });
    }

    @Override
    public HeaderResponse deleteBucketEncryption(final String str) throws ObsException {
        return (HeaderResponse) doActionWithResult("deleteBucketEncryption", str, new ActionCallbackWithResult<HeaderResponse>() {
            {
                super();
            }

            @Override
            HeaderResponse action() throws ServiceException {
                return ObsClient.this.deleteBucketEncryptionImpl(str);
            }
        });
    }

    @Override
    public HeaderResponse deleteBucketLifecycle(final String str) throws ObsException {
        return (HeaderResponse) doActionWithResult("deleteBucketLifecycleConfiguration", str, new ActionCallbackWithResult<HeaderResponse>() {
            {
                super();
            }

            @Override
            public HeaderResponse action() throws ServiceException {
                return ObsClient.this.deleteBucketLifecycleConfigurationImpl(str);
            }
        });
    }

    @Deprecated
    public HeaderResponse deleteBucketLifecycleConfiguration(String str) throws ObsException {
        return deleteBucketLifecycle(str);
    }

    @Override
    public HeaderResponse deleteBucketPolicy(final String str) throws ObsException {
        return (HeaderResponse) doActionWithResult("deleteBucketPolicy", str, new ActionCallbackWithResult<HeaderResponse>() {
            {
                super();
            }

            @Override
            public HeaderResponse action() throws ServiceException {
                return ObsClient.this.deleteBucketPolicyImpl(str);
            }
        });
    }

    @Override
    public HeaderResponse deleteBucketReplication(final String str) throws ObsException {
        return (HeaderResponse) doActionWithResult("deleteBucketReplicationConfiguration", str, new ActionCallbackWithResult<HeaderResponse>() {
            {
                super();
            }

            @Override
            public HeaderResponse action() throws ServiceException {
                return ObsClient.this.deleteBucketReplicationConfigurationImpl(str);
            }
        });
    }

    @Deprecated
    public HeaderResponse deleteBucketReplicationConfiguration(String str) throws ObsException {
        return deleteBucketReplication(str);
    }

    @Override
    public HeaderResponse deleteBucketTagging(final String str) throws ObsException {
        return (HeaderResponse) doActionWithResult("deleteBucketTagging", str, new ActionCallbackWithResult<HeaderResponse>() {
            {
                super();
            }

            @Override
            public HeaderResponse action() throws ServiceException {
                return ObsClient.this.deleteBucketTaggingImpl(str);
            }
        });
    }

    @Override
    public HeaderResponse deleteBucketWebsite(final String str) throws ObsException {
        return (HeaderResponse) doActionWithResult("deleteBucketWebsiteConfiguration", str, new ActionCallbackWithResult<HeaderResponse>() {
            {
                super();
            }

            @Override
            public HeaderResponse action() throws ServiceException {
                return ObsClient.this.deleteBucketWebsiteConfigurationImpl(str);
            }
        });
    }

    @Deprecated
    public HeaderResponse deleteBucketWebsiteConfiguration(String str) throws ObsException {
        return deleteBucketWebsite(str);
    }

    @Override
    public DeleteObjectResult deleteObject(String str, String str2) throws ObsException {
        return deleteObject(str, str2, null);
    }

    @Override
    public DeleteObjectResult deleteObject(final String str, final String str2, final String str3) throws ObsException {
        return (DeleteObjectResult) doActionWithResult("deleteObject", str, new ActionCallbackWithResult<DeleteObjectResult>() {
            {
                super();
            }

            @Override
            public DeleteObjectResult action() throws ServiceException {
                ServiceUtils.asserParameterNotNull2(str2, "objectKey is null");
                return ObsClient.this.deleteObjectImpl(str, str2, str3);
            }
        });
    }

    @Override
    public DeleteObjectsResult deleteObjects(final DeleteObjectsRequest deleteObjectsRequest) throws ObsException {
        ServiceUtils.asserParameterNotNull(deleteObjectsRequest, "DeleteObjectsRequest is null");
        return (DeleteObjectsResult) doActionWithResult("deleteObjects", deleteObjectsRequest.getBucketName(), new ActionCallbackWithResult<DeleteObjectsResult>() {
            {
                super();
            }

            @Override
            public DeleteObjectsResult action() throws ServiceException {
                return ObsClient.this.deleteObjectsImpl(deleteObjectsRequest);
            }
        });
    }

    @Override
    public ReadAheadResult deleteReadAheadObjects(final String str, final String str2) throws ObsException {
        ServiceUtils.asserParameterNotNull(str, "bucketName is null");
        ServiceUtils.asserParameterNotNull(str2, "prefix is null");
        return (ReadAheadResult) doActionWithResult("deleteReadAheadObjects", str, new ActionCallbackWithResult<ReadAheadResult>() {
            {
                super();
            }

            @Override
            public ReadAheadResult action() throws ServiceException {
                return ObsClient.this.DeleteReadAheadObjectsImpl(str, str2);
            }
        });
    }

    @Override
    public DownloadFileResult downloadFile(DownloadFileRequest downloadFileRequest) throws ObsException {
        return new ResumableClient(this).downloadFileResume(downloadFileRequest);
    }

    @Override
    public DropFileResult dropFile(final DropFileRequest dropFileRequest) throws ObsException {
        ServiceUtils.asserParameterNotNull(dropFileRequest, "DropFileRequest is null");
        return (DropFileResult) doActionWithResult("dropFile", dropFileRequest.getBucketName(), new ActionCallbackWithResult<DropFileResult>() {
            {
                super();
            }

            @Override
            public DropFileResult action() throws ServiceException {
                ServiceUtils.asserParameterNotNull2(dropFileRequest.getObjectKey(), "objectKey is null");
                return (DropFileResult) ObsClient.this.deleteObjectImpl(dropFileRequest.getBucketName(), dropFileRequest.getObjectKey(), dropFileRequest.getVersionId());
            }
        });
    }

    @Override
    public TaskProgressStatus dropFolder(DropFolderRequest dropFolderRequest) throws InterruptedException, ObsException {
        ServiceUtils.asserParameterNotNull(dropFolderRequest, "DropFolderRequest is null");
        if (!isCname()) {
            ServiceUtils.asserParameterNotNull(dropFolderRequest.getBucketName(), "bucketName is null");
        }
        ThreadPoolExecutor threadPoolExecutorInitThreadPool = initThreadPool(dropFolderRequest);
        DefaultTaskProgressStatus defaultTaskProgressStatus = new DefaultTaskProgressStatus();
        try {
            String bucketName = dropFolderRequest.getBucketName();
            String folderName = dropFolderRequest.getFolderName();
            String fileSystemDelimiter = getFileSystemDelimiter();
            if (!folderName.endsWith(fileSystemDelimiter)) {
                folderName = folderName + fileSystemDelimiter;
            }
            String str = folderName;
            TaskCallback<DeleteObjectResult, String> lazyTaksCallback = dropFolderRequest.getCallback() == null ? new LazyTaksCallback<>() : dropFolderRequest.getCallback();
            TaskProgressListener progressListener = dropFolderRequest.getProgressListener();
            int progressInterval = dropFolderRequest.getProgressInterval();
            int[] iArr = {0};
            boolean zRecurseFolders = recurseFolders(str, bucketName, lazyTaksCallback, progressInterval, defaultTaskProgressStatus, progressListener, threadPoolExecutorInitThreadPool, iArr);
            HashMap map = new HashMap();
            iArr[0] = iArr[0] + 1;
            defaultTaskProgressStatus.setTotalTaskNum(iArr[0]);
            if (zRecurseFolders) {
                submitDropTask(str, bucketName, lazyTaksCallback, progressInterval, defaultTaskProgressStatus, progressListener, threadPoolExecutorInitThreadPool, map);
                checkDropFutures(map, defaultTaskProgressStatus, lazyTaksCallback, progressListener, progressInterval);
            } else {
                defaultTaskProgressStatus.failTaskIncrement();
                lazyTaksCallback.onException(new ObsException("Failed to delete due to child file deletion failed"), str);
                try {
                    recordBulkTaskStatus(defaultTaskProgressStatus, lazyTaksCallback, progressListener, progressInterval);
                } catch (ObsException e) {
                    throw e;
                } catch (Exception e2) {
                    e = e2;
                    throw new ObsException(e.getMessage(), e);
                }
            }
            threadPoolExecutorInitThreadPool.shutdown();
            threadPoolExecutorInitThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
            return defaultTaskProgressStatus;
        } catch (ObsException e3) {
            throw e3;
        } catch (Exception e4) {
            e = e4;
        }
    }

    protected void finalize() throws Throwable {
        super.finalize();
        close();
    }

    @Override
    public ObsFSAttribute getAttribute(GetAttributeRequest getAttributeRequest) throws ObsException {
        return (ObsFSAttribute) getObjectMetadata(getAttributeRequest);
    }

    @Override
    public AccessControlList getBucketAcl(final String str) throws ObsException {
        return (AccessControlList) doActionWithResult("getBucketAcl", str, new ActionCallbackWithResult<AccessControlList>() {
            {
                super();
            }

            @Override
            public AccessControlList action() throws ServiceException {
                return ObsClient.this.getBucketAclImpl(str);
            }
        });
    }

    @Override
    public BucketCors getBucketCors(final String str) throws ObsException {
        return (BucketCors) doActionWithResult("getBucketCors", str, new ActionCallbackWithResult<BucketCors>() {
            {
                super();
            }

            @Override
            public BucketCors action() throws ServiceException {
                return ObsClient.this.getBucketCorsImpl(str);
            }
        });
    }

    @Override
    public BucketDirectColdAccess getBucketDirectColdAccess(final String str) throws ObsException {
        ServiceUtils.asserParameterNotNull(str, "bucketName is null");
        return (BucketDirectColdAccess) doActionWithResult("getBucketDirectColdAccess", str, new ActionCallbackWithResult<BucketDirectColdAccess>() {
            {
                super();
            }

            @Override
            public BucketDirectColdAccess action() throws ServiceException {
                return ObsClient.this.getBucketDirectColdAccessImpl(str);
            }
        });
    }

    @Override
    public BucketEncryption getBucketEncryption(final String str) throws ObsException {
        return (BucketEncryption) doActionWithResult("getBucketEncryption", str, new ActionCallbackWithResult<BucketEncryption>() {
            {
                super();
            }

            @Override
            BucketEncryption action() throws ServiceException {
                return ObsClient.this.getBucketEncryptionImpl(str);
            }
        });
    }

    @Override
    public GetBucketFSStatusResult getBucketFSStatus(final GetBucketFSStatusRequest getBucketFSStatusRequest) throws ObsException {
        ServiceUtils.asserParameterNotNull(getBucketFSStatusRequest, "GetBucketFSStatusRequest is null");
        return (GetBucketFSStatusResult) doActionWithResult("getBucketFSStatus", getBucketFSStatusRequest.getBucketName(), new ActionCallbackWithResult<GetBucketFSStatusResult>() {
            {
                super();
            }

            @Override
            public GetBucketFSStatusResult action() throws ServiceException {
                return ObsClient.this.getBucketMetadataImpl(getBucketFSStatusRequest);
            }
        });
    }

    @Override
    public LifecycleConfiguration getBucketLifecycle(final String str) throws ObsException {
        return (LifecycleConfiguration) doActionWithResult("getBucketLifecycleConfiguration", str, new ActionCallbackWithResult<LifecycleConfiguration>() {
            {
                super();
            }

            @Override
            public LifecycleConfiguration action() throws ServiceException {
                return ObsClient.this.getBucketLifecycleConfigurationImpl(str);
            }
        });
    }

    @Deprecated
    public LifecycleConfiguration getBucketLifecycleConfiguration(String str) throws ObsException {
        return getBucketLifecycle(str);
    }

    @Override
    public String getBucketLocation(String str) throws ObsException {
        return getBucketLocationV2(str).getLocation();
    }

    @Override
    public BucketLocationResponse getBucketLocationV2(final String str) throws ObsException {
        return (BucketLocationResponse) doActionWithResult("getBucketLocation", str, new ActionCallbackWithResult<BucketLocationResponse>() {
            {
                super();
            }

            @Override
            public BucketLocationResponse action() throws ServiceException {
                return ObsClient.this.getBucketLocationImpl(str);
            }
        });
    }

    @Override
    public BucketLoggingConfiguration getBucketLogging(final String str) throws ObsException {
        return (BucketLoggingConfiguration) doActionWithResult("getBucketLoggingConfiguration", str, new ActionCallbackWithResult<BucketLoggingConfiguration>() {
            {
                super();
            }

            @Override
            public BucketLoggingConfiguration action() throws ServiceException {
                return ObsClient.this.getBucketLoggingConfigurationImpl(str);
            }
        });
    }

    @Deprecated
    public BucketLoggingConfiguration getBucketLoggingConfiguration(String str) throws ObsException {
        return getBucketLogging(str);
    }

    @Override
    public BucketMetadataInfoResult getBucketMetadata(final BucketMetadataInfoRequest bucketMetadataInfoRequest) throws ObsException {
        ServiceUtils.asserParameterNotNull(bucketMetadataInfoRequest, "BucketMetadataInfoRequest is null");
        return (BucketMetadataInfoResult) doActionWithResult("getBucketMetadata", bucketMetadataInfoRequest.getBucketName(), new ActionCallbackWithResult<BucketMetadataInfoResult>() {
            {
                super();
            }

            @Override
            public BucketMetadataInfoResult action() throws ServiceException {
                return ObsClient.this.getBucketMetadataImpl(bucketMetadataInfoRequest);
            }
        });
    }

    @Override
    public BucketNotificationConfiguration getBucketNotification(final String str) throws ObsException {
        return (BucketNotificationConfiguration) doActionWithResult("getBucketNotification", str, new ActionCallbackWithResult<BucketNotificationConfiguration>() {
            {
                super();
            }

            @Override
            public BucketNotificationConfiguration action() throws ServiceException {
                return ObsClient.this.getBucketNotificationConfigurationImpl(str);
            }
        });
    }

    @Override
    public String getBucketPolicy(String str) throws ObsException {
        return getBucketPolicyV2(str).getPolicy();
    }

    @Override
    public BucketPolicyResponse getBucketPolicyV2(final String str) throws ObsException {
        return (BucketPolicyResponse) doActionWithResult("getBucketPolicy", str, new ActionCallbackWithResult<BucketPolicyResponse>() {
            {
                super();
            }

            @Override
            public BucketPolicyResponse action() throws ServiceException {
                return ObsClient.this.getBucketPolicyImpl(str);
            }
        });
    }

    @Override
    public BucketQuota getBucketQuota(final String str) throws ObsException {
        return (BucketQuota) doActionWithResult("getBucketQuota", str, new ActionCallbackWithResult<BucketQuota>() {
            {
                super();
            }

            @Override
            public BucketQuota action() throws ServiceException {
                return ObsClient.this.getBucketQuotaImpl(str);
            }
        });
    }

    @Override
    public ReplicationConfiguration getBucketReplication(final String str) throws ObsException {
        return (ReplicationConfiguration) doActionWithResult("getBucketReplicationConfiguration", str, new ActionCallbackWithResult<ReplicationConfiguration>() {
            {
                super();
            }

            @Override
            public ReplicationConfiguration action() throws ServiceException {
                return ObsClient.this.getBucketReplicationConfigurationImpl(str);
            }
        });
    }

    @Deprecated
    public ReplicationConfiguration getBucketReplicationConfiguration(String str) throws ObsException {
        return getBucketReplication(str);
    }

    @Override
    public BucketStorageInfo getBucketStorageInfo(final String str) throws ObsException {
        return (BucketStorageInfo) doActionWithResult("getBucketStorageInfo", str, new ActionCallbackWithResult<BucketStorageInfo>() {
            {
                super();
            }

            @Override
            public BucketStorageInfo action() throws ServiceException {
                return ObsClient.this.getBucketStorageInfoImpl(str);
            }
        });
    }

    @Override
    public BucketStoragePolicyConfiguration getBucketStoragePolicy(final String str) throws ObsException {
        return (BucketStoragePolicyConfiguration) doActionWithResult("getBucketStoragePolicy", str, new ActionCallbackWithResult<BucketStoragePolicyConfiguration>() {
            {
                super();
            }

            @Override
            public BucketStoragePolicyConfiguration action() throws ServiceException {
                return ObsClient.this.getBucketStoragePolicyImpl(str);
            }
        });
    }

    @Override
    public BucketTagInfo getBucketTagging(final String str) throws ObsException {
        return (BucketTagInfo) doActionWithResult("getBucketTagging", str, new ActionCallbackWithResult<BucketTagInfo>() {
            {
                super();
            }

            @Override
            public BucketTagInfo action() throws ServiceException {
                return ObsClient.this.getBucketTaggingImpl(str);
            }
        });
    }

    @Override
    public BucketVersioningConfiguration getBucketVersioning(final String str) throws ObsException {
        return (BucketVersioningConfiguration) doActionWithResult("getBucketVersioning", str, new ActionCallbackWithResult<BucketVersioningConfiguration>() {
            {
                super();
            }

            @Override
            public BucketVersioningConfiguration action() throws ServiceException {
                return ObsClient.this.getBucketVersioningImpl(str);
            }
        });
    }

    @Override
    public WebsiteConfiguration getBucketWebsite(final String str) throws ObsException {
        return (WebsiteConfiguration) doActionWithResult("getBucketWebsiteConfiguration", str, new ActionCallbackWithResult<WebsiteConfiguration>() {
            {
                super();
            }

            @Override
            public WebsiteConfiguration action() throws ServiceException {
                return ObsClient.this.getBucketWebsiteConfigurationImpl(str);
            }
        });
    }

    @Deprecated
    public WebsiteConfiguration getBucketWebsiteConfiguration(String str) throws ObsException {
        return getBucketWebsite(str);
    }

    @Override
    public ObsObject getObject(final GetObjectRequest getObjectRequest) throws ObsException {
        ServiceUtils.asserParameterNotNull(getObjectRequest, "GetObjectRequest is null");
        ServiceUtils.asserParameterNotNull2(getObjectRequest.getObjectKey(), "objectKey is null");
        return (ObsObject) doActionWithResult("getObject", getObjectRequest.getBucketName(), new ActionCallbackWithResult<ObsObject>() {
            {
                super();
            }

            @Override
            public ObsObject action() throws ServiceException {
                return ObsClient.this.getObjectImpl(getObjectRequest);
            }
        });
    }

    @Override
    public ObsObject getObject(String str, String str2) throws ObsException {
        return getObject(str, str2, null);
    }

    @Override
    public ObsObject getObject(String str, String str2, String str3) throws ObsException {
        return getObject(new GetObjectRequest(str, str2, str3));
    }

    @Override
    public AccessControlList getObjectAcl(String str, String str2) throws ObsException {
        return getObjectAcl(str, str2, null);
    }

    @Override
    public AccessControlList getObjectAcl(final String str, final String str2, final String str3) throws ObsException {
        ServiceUtils.asserParameterNotNull2(str2, "objectKey is null");
        return (AccessControlList) doActionWithResult("getObjectAcl", str, new ActionCallbackWithResult<AccessControlList>() {
            {
                super();
            }

            @Override
            public AccessControlList action() throws ServiceException {
                return ObsClient.this.getObjectAclImpl(str, str2, str3);
            }
        });
    }

    @Override
    public ObjectMetadata getObjectMetadata(final GetObjectMetadataRequest getObjectMetadataRequest) throws ObsException {
        ServiceUtils.asserParameterNotNull(getObjectMetadataRequest, "GetObjectMetadataRequest is null");
        ServiceUtils.asserParameterNotNull2(getObjectMetadataRequest.getObjectKey(), "objectKey is null");
        return (ObjectMetadata) doActionWithResult("getObjectMetadata", getObjectMetadataRequest.getBucketName(), new ActionCallbackWithResult<ObjectMetadata>() {
            {
                super();
            }

            @Override
            public ObjectMetadata action() throws ServiceException {
                return ObsClient.this.getObjectMetadataImpl(getObjectMetadataRequest);
            }
        });
    }

    @Override
    public ObjectMetadata getObjectMetadata(String str, String str2) throws ObsException {
        return getObjectMetadata(str, str2, null);
    }

    @Override
    public ObjectMetadata getObjectMetadata(String str, String str2, String str3) throws ObsException {
        GetObjectMetadataRequest getObjectMetadataRequest = new GetObjectMetadataRequest();
        getObjectMetadataRequest.setBucketName(str);
        getObjectMetadataRequest.setObjectKey(str2);
        getObjectMetadataRequest.setVersionId(str3);
        return getObjectMetadata(getObjectMetadataRequest);
    }

    @Override
    public boolean headBucket(final String str) throws ObsException {
        return ((Boolean) doActionWithResult("headBucket", str, new ActionCallbackWithResult<Boolean>() {
            {
                super();
            }

            @Override
            public Boolean action() throws ServiceException {
                return Boolean.valueOf(ObsClient.this.headBucketImpl(str));
            }

            @Override
            void authTypeNegotiate(String str2) throws ServiceException {
                try {
                    ObsClient.this.getProviderCredentials().setThreadLocalAuthType(ObsClient.this.getApiVersion(str2));
                } catch (ServiceException e) {
                    if (e.getResponseCode() != 404) {
                        throw e;
                    }
                }
            }
        })).booleanValue();
    }

    @Override
    public InitiateMultipartUploadResult initiateMultipartUpload(final InitiateMultipartUploadRequest initiateMultipartUploadRequest) throws ObsException {
        ServiceUtils.asserParameterNotNull(initiateMultipartUploadRequest, "InitiateMultipartUploadRequest is null");
        ServiceUtils.asserParameterNotNull2(initiateMultipartUploadRequest.getObjectKey(), "objectKey is null");
        return (InitiateMultipartUploadResult) doActionWithResult("initiateMultipartUpload", initiateMultipartUploadRequest.getBucketName(), new ActionCallbackWithResult<InitiateMultipartUploadResult>() {
            {
                super();
            }

            @Override
            public InitiateMultipartUploadResult action() throws ServiceException {
                return ObsClient.this.initiateMultipartUploadImpl(initiateMultipartUploadRequest);
            }
        });
    }

    @Deprecated
    public List<S3Bucket> listBuckets() throws ObsException {
        List<ObsBucket> listListBuckets = listBuckets(null);
        ArrayList arrayList = new ArrayList(listListBuckets.size());
        arrayList.addAll(listListBuckets);
        return arrayList;
    }

    @Override
    public List<ObsBucket> listBuckets(ListBucketsRequest listBucketsRequest) throws ObsException {
        return listBucketsV2(listBucketsRequest).getBuckets();
    }

    @Override
    public ListBucketsResult listBucketsV2(final ListBucketsRequest listBucketsRequest) throws ObsException {
        return (ListBucketsResult) doActionWithResult("listBuckets", "All Buckets", new ActionCallbackWithResult<ListBucketsResult>() {
            {
                super();
            }

            @Override
            public ListBucketsResult action() throws ServiceException {
                if (ObsClient.this.isCname()) {
                    throw new ServiceException("listBuckets is not allowed in customdomain mode");
                }
                return ObsClient.this.listAllBucketsImpl(listBucketsRequest);
            }

            @Override
            void authTypeNegotiate(String str) throws ServiceException {
                ObsClient.this.getProviderCredentials().setThreadLocalAuthType(ObsClient.this.getApiVersion(""));
            }
        });
    }

    @Override
    public MultipartUploadListing listMultipartUploads(final ListMultipartUploadsRequest listMultipartUploadsRequest) throws ObsException {
        ServiceUtils.asserParameterNotNull(listMultipartUploadsRequest, "ListMultipartUploadsRequest is null");
        return (MultipartUploadListing) doActionWithResult("listMultipartUploads", listMultipartUploadsRequest.getBucketName(), new ActionCallbackWithResult<MultipartUploadListing>() {
            {
                super();
            }

            @Override
            public MultipartUploadListing action() throws ServiceException {
                return ObsClient.this.listMultipartUploadsImpl(listMultipartUploadsRequest);
            }
        });
    }

    @Override
    public ObjectListing listObjects(final ListObjectsRequest listObjectsRequest) throws ObsException {
        ServiceUtils.asserParameterNotNull(listObjectsRequest, "ListObjectsRequest is null");
        return (ObjectListing) doActionWithResult("listObjects", listObjectsRequest.getBucketName(), new ActionCallbackWithResult<ObjectListing>() {
            {
                super();
            }

            @Override
            public ObjectListing action() throws ServiceException {
                return ObsClient.this.listObjectsImpl(listObjectsRequest);
            }
        });
    }

    @Override
    public ObjectListing listObjects(String str) throws ObsException {
        return listObjects(new ListObjectsRequest(str));
    }

    @Override
    public ListPartsResult listParts(final ListPartsRequest listPartsRequest) throws ObsException {
        ServiceUtils.asserParameterNotNull(listPartsRequest, "ListPartsRequest is null");
        ServiceUtils.asserParameterNotNull2(listPartsRequest.getKey(), "objectKey is null");
        ServiceUtils.asserParameterNotNull(listPartsRequest.getUploadId(), "uploadId is null");
        return (ListPartsResult) doActionWithResult("listParts", listPartsRequest.getBucketName(), new ActionCallbackWithResult<ListPartsResult>() {
            {
                super();
            }

            @Override
            public ListPartsResult action() throws ServiceException {
                return ObsClient.this.listPartsImpl(listPartsRequest);
            }
        });
    }

    @Override
    public ListVersionsResult listVersions(final ListVersionsRequest listVersionsRequest) throws ObsException {
        ServiceUtils.asserParameterNotNull(listVersionsRequest, "ListVersionsRequest is null");
        return (ListVersionsResult) doActionWithResult("listVersions", listVersionsRequest.getBucketName(), new ActionCallbackWithResult<ListVersionsResult>() {
            {
                super();
            }

            @Override
            public ListVersionsResult action() throws ServiceException {
                return ObsClient.this.listVersionsImpl(listVersionsRequest);
            }
        });
    }

    @Override
    public ListVersionsResult listVersions(String str) throws ObsException {
        ListVersionsRequest listVersionsRequest = new ListVersionsRequest();
        listVersionsRequest.setBucketName(str);
        return listVersions(listVersionsRequest);
    }

    @Override
    public ListVersionsResult listVersions(String str, long j) throws ObsException {
        ListVersionsRequest listVersionsRequest = new ListVersionsRequest();
        listVersionsRequest.setBucketName(str);
        listVersionsRequest.setMaxKeys((int) j);
        return listVersions(listVersionsRequest);
    }

    @Override
    public ListVersionsResult listVersions(String str, String str2, String str3, String str4, String str5, long j) throws ObsException {
        return listVersions(str, str2, str3, str4, str5, j, null);
    }

    @Deprecated
    public ListVersionsResult listVersions(String str, String str2, String str3, String str4, String str5, long j, String str6) throws ObsException {
        ListVersionsRequest listVersionsRequest = new ListVersionsRequest();
        listVersionsRequest.setBucketName(str);
        listVersionsRequest.setPrefix(str2);
        listVersionsRequest.setKeyMarker(str4);
        listVersionsRequest.setMaxKeys((int) j);
        listVersionsRequest.setVersionIdMarker(str5);
        listVersionsRequest.setDelimiter(str3);
        return listVersions(listVersionsRequest);
    }

    @Override
    public ObsFSBucket newBucket(NewBucketRequest newBucketRequest) throws IllegalAccessException, IllegalArgumentException, ObsException {
        ObsBucket obsBucketCreateBucket = createBucket(newBucketRequest);
        ObsFSBucket obsFSBucket = new ObsFSBucket(obsBucketCreateBucket.getBucketName(), obsBucketCreateBucket.getLocation());
        ReflectUtils.setInnerClient(obsFSBucket, this);
        return obsFSBucket;
    }

    @Override
    public ObsFSFile newFile(NewFileRequest newFileRequest) throws IllegalAccessException, IllegalArgumentException, ObsException {
        ObsFSFile obsFSFile = (ObsFSFile) putObject(newFileRequest);
        ReflectUtils.setInnerClient(obsFSFile, this);
        return obsFSFile;
    }

    @Override
    public ObsFSFolder newFolder(NewFolderRequest newFolderRequest) throws IllegalAccessException, IllegalArgumentException, ObsException {
        ServiceUtils.asserParameterNotNull(newFolderRequest, "CreateFolderRequest is null");
        if (newFolderRequest.getObjectKey() != null) {
            String fileSystemDelimiter = getFileSystemDelimiter();
            if (!newFolderRequest.getObjectKey().endsWith(fileSystemDelimiter)) {
                newFolderRequest.setObjectKey(newFolderRequest.getObjectKey() + fileSystemDelimiter);
            }
        }
        ObsFSFolder obsFSFolder = (ObsFSFolder) putObject(new PutObjectRequest(newFolderRequest));
        ReflectUtils.setInnerClient(obsFSFolder, this);
        return obsFSFolder;
    }

    @Deprecated
    public OptionsInfoResult optionsBucket(final String str, final OptionsInfoRequest optionsInfoRequest) throws ObsException {
        return (OptionsInfoResult) doActionWithResult("optionsBucket", str, new ActionCallbackWithResult<OptionsInfoResult>() {
            {
                super();
            }

            @Override
            public OptionsInfoResult action() throws ServiceException {
                ServiceUtils.asserParameterNotNull(optionsInfoRequest, "OptionsInfoRequest is null");
                return ObsClient.this.optionsImpl(str, null, optionsInfoRequest);
            }
        });
    }

    @Deprecated
    public OptionsInfoResult optionsObject(final String str, final String str2, final OptionsInfoRequest optionsInfoRequest) throws ObsException {
        return (OptionsInfoResult) doActionWithResult("optionsObject", str, new ActionCallbackWithResult<OptionsInfoResult>() {
            {
                super();
            }

            @Override
            public OptionsInfoResult action() throws ServiceException {
                ServiceUtils.asserParameterNotNull(optionsInfoRequest, "OptionsInfoRequest is null");
                return ObsClient.this.optionsImpl(str, str2, optionsInfoRequest);
            }
        });
    }

    @Override
    public PutObjectResult putObject(final PutObjectRequest putObjectRequest) throws ObsException {
        ServiceUtils.asserParameterNotNull(putObjectRequest, "PutObjectRequest is null");
        ServiceUtils.asserParameterNotNull2(putObjectRequest.getObjectKey(), "objectKey is null");
        return (PutObjectResult) doActionWithResult("putObject", putObjectRequest.getBucketName(), new ActionCallbackWithResult<PutObjectResult>() {
            {
                super();
            }

            @Override
            public PutObjectResult action() throws ServiceException {
                if (putObjectRequest.getInput() == null || putObjectRequest.getFile() == null) {
                    return ObsClient.this.putObjectImpl(putObjectRequest);
                }
                throw new ServiceException("Both input and file are set, only one is allowed");
            }
        });
    }

    @Override
    public PutObjectResult putObject(String str, String str2, File file) throws ObsException {
        return putObject(str, str2, file, (ObjectMetadata) null);
    }

    @Override
    public PutObjectResult putObject(String str, String str2, File file, ObjectMetadata objectMetadata) throws ObsException {
        PutObjectRequest putObjectRequest = new PutObjectRequest();
        putObjectRequest.setBucketName(str);
        putObjectRequest.setFile(file);
        putObjectRequest.setObjectKey(str2);
        putObjectRequest.setMetadata(objectMetadata);
        return putObject(putObjectRequest);
    }

    @Override
    public PutObjectResult putObject(String str, String str2, InputStream inputStream) throws ObsException {
        return putObject(str, str2, inputStream, (ObjectMetadata) null);
    }

    @Override
    public PutObjectResult putObject(String str, String str2, InputStream inputStream, ObjectMetadata objectMetadata) throws ObsException {
        PutObjectRequest putObjectRequest = new PutObjectRequest();
        putObjectRequest.setBucketName(str);
        putObjectRequest.setInput(inputStream);
        putObjectRequest.setMetadata(objectMetadata);
        putObjectRequest.setObjectKey(str2);
        return putObject(putObjectRequest);
    }

    @Override
    public UploadProgressStatus putObjects(PutObjectsRequest putObjectsRequest) throws InterruptedException, IOException, ObsException {
        UploadTaskProgressStatus uploadTaskProgressStatus;
        UploadTaskProgressStatus uploadTaskProgressStatus2;
        int i;
        String str;
        File[] fileArr;
        LinkedList linkedList;
        String str2;
        String str3;
        String str4;
        String str5;
        UploadTaskProgressStatus uploadTaskProgressStatus3;
        String str6;
        TaskCallback<PutObjectResult, PutObjectBasicRequest> taskCallback;
        String str7;
        ServiceUtils.asserParameterNotNull(putObjectsRequest, "PutObjectsRequest is null");
        ThreadPoolExecutor threadPoolExecutorInitThreadPool = initThreadPool(putObjectsRequest);
        UploadTaskProgressStatus uploadTaskProgressStatus4 = new UploadTaskProgressStatus(putObjectsRequest.getTaskProgressInterval(), new Date());
        try {
            UploadObjectsProgressListener uploadObjectsProgressListener = putObjectsRequest.getUploadObjectsProgressListener();
            TaskCallback<PutObjectResult, PutObjectBasicRequest> lazyTaksCallback = putObjectsRequest.getCallback() == null ? new LazyTaksCallback<>() : putObjectsRequest.getCallback();
            String prefix = putObjectsRequest.getPrefix() == null ? "" : putObjectsRequest.getPrefix();
            String str8 = "putObjects: the file \"";
            if (putObjectsRequest.getFolderPath() != null) {
                String folderPath = putObjectsRequest.getFolderPath();
                File file = new File(folderPath);
                String str9 = "\" dose not exist";
                String str10 = "putObjects: the folder \"";
                if (!file.exists()) {
                    String str11 = "putObjects: the folder \"" + folderPath + "\" dose not exist";
                    ILOG.warn((CharSequence) str11);
                    throw new ObsException(str11);
                }
                String str12 = "\" dose not a folder";
                if (!file.isDirectory()) {
                    String str13 = "putObjects: the folder \"" + folderPath + "\" dose not a folder";
                    ILOG.warn((CharSequence) str13);
                    throw new ObsException(str13);
                }
                String name = file.getName();
                LinkedList linkedList2 = new LinkedList();
                linkedList2.add(file);
                file.listFiles();
                int i2 = 0;
                while (!linkedList2.isEmpty()) {
                    File[] fileArrListFiles = ((File) linkedList2.removeFirst()).listFiles();
                    int length = fileArrListFiles.length;
                    int i3 = 0;
                    while (i3 < length) {
                        int i4 = length;
                        File file2 = fileArrListFiles[i3];
                        if (file2.isDirectory()) {
                            if (file2.exists()) {
                                fileArr = fileArrListFiles;
                                linkedList2.add(file2);
                            } else {
                                fileArr = fileArrListFiles;
                                ILOG.warn((CharSequence) (str10 + file2.getCanonicalPath() + str12));
                            }
                            linkedList = linkedList2;
                        } else {
                            fileArr = fileArrListFiles;
                            String canonicalPath = file2.getCanonicalPath();
                            if (file2.exists()) {
                                linkedList = linkedList2;
                                int i5 = i2 + 1;
                                StringBuilder sb = new StringBuilder();
                                sb.append(prefix);
                                sb.append(name);
                                str2 = name;
                                sb.append(canonicalPath.substring(folderPath.length(), canonicalPath.length()).replace("\\", "/"));
                                str3 = str12;
                                str4 = str8;
                                str5 = str10;
                                UploadTaskProgressStatus uploadTaskProgressStatus5 = uploadTaskProgressStatus4;
                                uploadTaskProgressStatus3 = uploadTaskProgressStatus4;
                                str6 = str9;
                                TaskCallback<PutObjectResult, PutObjectBasicRequest> taskCallback2 = lazyTaksCallback;
                                taskCallback = lazyTaksCallback;
                                str7 = folderPath;
                                uploadObjectTask(putObjectsRequest, canonicalPath, sb.toString(), threadPoolExecutorInitThreadPool, uploadTaskProgressStatus5, taskCallback2, uploadObjectsProgressListener);
                                i2 = i5;
                                i3++;
                                str9 = str6;
                                folderPath = str7;
                                str10 = str5;
                                str12 = str3;
                                length = i4;
                                fileArrListFiles = fileArr;
                                linkedList2 = linkedList;
                                name = str2;
                                str8 = str4;
                                uploadTaskProgressStatus4 = uploadTaskProgressStatus3;
                                lazyTaksCallback = taskCallback;
                            } else {
                                linkedList = linkedList2;
                                ILOG.warn((CharSequence) (str8 + canonicalPath + str9));
                            }
                        }
                        str2 = name;
                        str3 = str12;
                        uploadTaskProgressStatus3 = uploadTaskProgressStatus4;
                        taskCallback = lazyTaksCallback;
                        str4 = str8;
                        str5 = str10;
                        str6 = str9;
                        str7 = folderPath;
                        i3++;
                        str9 = str6;
                        folderPath = str7;
                        str10 = str5;
                        str12 = str3;
                        length = i4;
                        fileArrListFiles = fileArr;
                        linkedList2 = linkedList;
                        name = str2;
                        str8 = str4;
                        uploadTaskProgressStatus4 = uploadTaskProgressStatus3;
                        lazyTaksCallback = taskCallback;
                    }
                }
                uploadTaskProgressStatus = uploadTaskProgressStatus4;
                i = i2;
            } else {
                uploadTaskProgressStatus = uploadTaskProgressStatus4;
                String str14 = "putObjects: the file \"";
                if (putObjectsRequest.getFilePaths() == null) {
                    uploadTaskProgressStatus2 = uploadTaskProgressStatus;
                    i = 0;
                    uploadTaskProgressStatus2.setTotalTaskNum(i);
                    threadPoolExecutorInitThreadPool.shutdown();
                    threadPoolExecutorInitThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
                    return uploadTaskProgressStatus2;
                }
                i = 0;
                for (String str15 : putObjectsRequest.getFilePaths()) {
                    File file3 = new File(str15);
                    if (file3.exists()) {
                        i++;
                        uploadObjectTask(putObjectsRequest, str15, prefix + file3.getName(), threadPoolExecutorInitThreadPool, uploadTaskProgressStatus, lazyTaksCallback, uploadObjectsProgressListener);
                        str = str14;
                    } else {
                        ILogger iLogger = ILOG;
                        StringBuilder sb2 = new StringBuilder();
                        str = str14;
                        sb2.append(str);
                        sb2.append(str15);
                        sb2.append("\" is not exist");
                        iLogger.warn((CharSequence) sb2.toString());
                    }
                    str14 = str;
                }
            }
            uploadTaskProgressStatus2 = uploadTaskProgressStatus;
            uploadTaskProgressStatus2.setTotalTaskNum(i);
            threadPoolExecutorInitThreadPool.shutdown();
            threadPoolExecutorInitThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
            return uploadTaskProgressStatus2;
        } catch (ObsException e) {
            throw e;
        } catch (Exception e2) {
            throw new ObsException(e2.getMessage(), e2);
        }
    }

    @Override
    public ReadAheadQueryResult queryReadAheadObjectsTask(final String str, final String str2) throws ObsException {
        ServiceUtils.asserParameterNotNull(str, "bucketName is null");
        ServiceUtils.asserParameterNotNull(str2, "taskId is null");
        return (ReadAheadQueryResult) doActionWithResult("queryReadAheadObjectsTask", str, new ActionCallbackWithResult<ReadAheadQueryResult>() {
            {
                super();
            }

            @Override
            public ReadAheadQueryResult action() throws ServiceException {
                return ObsClient.this.queryReadAheadObjectsTaskImpl(str, str2);
            }
        });
    }

    @Override
    public ReadAheadResult readAheadObjects(final ReadAheadRequest readAheadRequest) throws ObsException {
        ServiceUtils.asserParameterNotNull(readAheadRequest, "request is null");
        return (ReadAheadResult) doActionWithResult("readAheadObjects", readAheadRequest.getBucketName(), new ActionCallbackWithResult<ReadAheadResult>() {
            {
                super();
            }

            @Override
            public ReadAheadResult action() throws ServiceException {
                return ObsClient.this.readAheadObjectsImpl(readAheadRequest);
            }
        });
    }

    @Override
    public ReadFileResult readFile(ReadFileRequest readFileRequest) throws ObsException {
        return (ReadFileResult) getObject(readFileRequest);
    }

    @Override
    public void refresh(String str, String str2, String str3) {
        ProviderCredentials providerCredentials = new ProviderCredentials(str, str2, str3);
        providerCredentials.setAuthType(this.credentials.getAuthType());
        setProviderCredentials(providerCredentials);
    }

    @Override
    public RenameResult renameFile(final RenameRequest renameRequest) throws ObsException {
        ServiceUtils.asserParameterNotNull(renameRequest, "RenameRequest is null");
        ServiceUtils.asserParameterNotNull2(renameRequest.getObjectKey(), "ObjectKey is null");
        ServiceUtils.asserParameterNotNull2(renameRequest.getNewObjectKey(), "NewObjectKey is null");
        return (RenameResult) doActionWithResult("rename", renameRequest.getBucketName(), new ActionCallbackWithResult<RenameResult>() {
            {
                super();
            }

            @Override
            public RenameResult action() throws ServiceException {
                return ObsClient.this.renameObjectImpl(renameRequest);
            }
        });
    }

    @Override
    public RenameResult renameFolder(RenameRequest renameRequest) throws ObsException {
        if (renameRequest != null && renameRequest.getObjectKey() != null && renameRequest.getNewObjectKey() != null) {
            String fileSystemDelimiter = getFileSystemDelimiter();
            if (!renameRequest.getObjectKey().endsWith(fileSystemDelimiter)) {
                renameRequest.setObjectKey(renameRequest.getObjectKey() + fileSystemDelimiter);
            }
            if (!renameRequest.getNewObjectKey().endsWith(fileSystemDelimiter)) {
                renameRequest.setNewObjectKey(renameRequest.getNewObjectKey() + fileSystemDelimiter);
            }
        }
        return renameFile(renameRequest);
    }

    @Override
    @Deprecated
    public RestoreObjectRequest.RestoreObjectStatus restoreObject(final RestoreObjectRequest restoreObjectRequest) throws ObsException {
        ServiceUtils.asserParameterNotNull(restoreObjectRequest, "RestoreObjectRequest is null");
        return (RestoreObjectRequest.RestoreObjectStatus) doActionWithResult("restoreObject", restoreObjectRequest.getBucketName(), new ActionCallbackWithResult<RestoreObjectRequest.RestoreObjectStatus>() {
            {
                super();
            }

            @Override
            public RestoreObjectRequest.RestoreObjectStatus action() throws ServiceException {
                ServiceUtils.asserParameterNotNull2(restoreObjectRequest.getObjectKey(), "objectKey is null");
                return ObsClient.this.restoreObjectImpl(restoreObjectRequest);
            }
        });
    }

    @Override
    public RestoreObjectResult restoreObjectV2(final RestoreObjectRequest restoreObjectRequest) throws ObsException {
        ServiceUtils.asserParameterNotNull(restoreObjectRequest, "RestoreObjectRequest is null");
        return (RestoreObjectResult) doActionWithResult("restoreObjectV2", restoreObjectRequest.getBucketName(), new ActionCallbackWithResult<RestoreObjectResult>() {
            {
                super();
            }

            @Override
            public RestoreObjectResult action() throws ServiceException {
                ServiceUtils.asserParameterNotNull2(restoreObjectRequest.getObjectKey(), "objectKey is null");
                return ObsClient.this.restoreObjectV2Impl(restoreObjectRequest);
            }
        });
    }

    @Override
    public TaskProgressStatus restoreObjects(RestoreObjectsRequest restoreObjectsRequest) throws InterruptedException, ObsException {
        int i;
        String str;
        DefaultTaskProgressStatus defaultTaskProgressStatus;
        int i2;
        int i3;
        int i4;
        VersionOrDeleteMarker[] versionOrDeleteMarkerArr;
        String str2;
        RestoreTierEnum restoreTierEnum;
        TaskCallback<RestoreObjectResult, RestoreObjectRequest> taskCallback;
        TaskProgressListener taskProgressListener;
        ListVersionsRequest listVersionsRequest;
        ObsClient obsClient = this;
        ServiceUtils.asserParameterNotNull(restoreObjectsRequest, "RestoreObjectsRequest is null");
        if (!isCname()) {
            ServiceUtils.asserParameterNotNull(restoreObjectsRequest.getBucketName(), "bucketName is null");
        }
        if (restoreObjectsRequest.getKeyAndVersions() != null && restoreObjectsRequest.getPrefix() != null) {
            throw new IllegalArgumentException("Prefix and keyandVersions cannot coexist in the same request");
        }
        int days = restoreObjectsRequest.getDays();
        if (days < 1 || days > 30) {
            throw new IllegalArgumentException("Restoration days should be at least 1 and at most 30");
        }
        DefaultTaskProgressStatus defaultTaskProgressStatus2 = new DefaultTaskProgressStatus();
        ThreadPoolExecutor threadPoolExecutorInitThreadPool = initThreadPool(restoreObjectsRequest);
        try {
            String bucketName = restoreObjectsRequest.getBucketName();
            String prefix = restoreObjectsRequest.getPrefix();
            RestoreTierEnum restoreTier = restoreObjectsRequest.getRestoreTier();
            boolean zIsVersionRestored = restoreObjectsRequest.isVersionRestored();
            TaskCallback<RestoreObjectResult, RestoreObjectRequest> lazyTaksCallback = restoreObjectsRequest.getCallback() == null ? new LazyTaksCallback<>() : restoreObjectsRequest.getCallback();
            TaskProgressListener progressListener = restoreObjectsRequest.getProgressListener();
            int progressInterval = restoreObjectsRequest.getProgressInterval();
            if (restoreObjectsRequest.getKeyAndVersions() != null) {
                int size = restoreObjectsRequest.getKeyAndVersions().size();
                for (KeyAndVersion keyAndVersion : restoreObjectsRequest.getKeyAndVersions()) {
                    DefaultTaskProgressStatus defaultTaskProgressStatus3 = defaultTaskProgressStatus2;
                    threadPoolExecutorInitThreadPool.execute(new RestoreObjectTask(this, bucketName, new RestoreObjectRequest(bucketName, keyAndVersion.getKey(), keyAndVersion.getVersion(), days, restoreTier), lazyTaksCallback, progressListener, defaultTaskProgressStatus2, progressInterval));
                    defaultTaskProgressStatus2 = defaultTaskProgressStatus3;
                }
                defaultTaskProgressStatus = defaultTaskProgressStatus2;
                i = size;
            } else {
                String str3 = "RestoreObjects: ";
                int i5 = 0;
                if (zIsVersionRestored) {
                    ListVersionsRequest listVersionsRequest2 = new ListVersionsRequest(bucketName);
                    listVersionsRequest2.setPrefix(prefix);
                    i = 0;
                    while (true) {
                        ListVersionsResult listVersionsResultListVersions = obsClient.listVersions(listVersionsRequest2);
                        VersionOrDeleteMarker[] versions = listVersionsResultListVersions.getVersions();
                        int length = versions.length;
                        int i6 = 0;
                        while (i6 < length) {
                            VersionOrDeleteMarker versionOrDeleteMarker = versions[i6];
                            ListVersionsRequest listVersionsRequest3 = listVersionsRequest2;
                            if (versionOrDeleteMarker.getObjectStorageClass() == StorageClassEnum.COLD) {
                                int i7 = i + 1;
                                i3 = i6;
                                i4 = length;
                                versionOrDeleteMarkerArr = versions;
                                restoreTierEnum = restoreTier;
                                TaskCallback<RestoreObjectResult, RestoreObjectRequest> taskCallback2 = lazyTaksCallback;
                                taskCallback = lazyTaksCallback;
                                listVersionsRequest = listVersionsRequest3;
                                taskProgressListener = progressListener;
                                i2 = days;
                                str2 = str3;
                                threadPoolExecutorInitThreadPool.execute(new RestoreObjectTask(this, bucketName, new RestoreObjectRequest(bucketName, versionOrDeleteMarker.getKey(), versionOrDeleteMarker.getVersionId(), days, restoreTier), taskCallback2, progressListener, defaultTaskProgressStatus2, progressInterval));
                                if (ILOG.isInfoEnabled() && i7 % 1000 == 0) {
                                    ILOG.info((CharSequence) (str2 + i7 + " tasks have submitted to restore objects"));
                                }
                                i = i7;
                            } else {
                                i2 = days;
                                i3 = i6;
                                i4 = length;
                                versionOrDeleteMarkerArr = versions;
                                str2 = str3;
                                restoreTierEnum = restoreTier;
                                taskCallback = lazyTaksCallback;
                                taskProgressListener = progressListener;
                                listVersionsRequest = listVersionsRequest3;
                            }
                            i6 = i3 + 1;
                            str3 = str2;
                            listVersionsRequest2 = listVersionsRequest;
                            restoreTier = restoreTierEnum;
                            length = i4;
                            versions = versionOrDeleteMarkerArr;
                            lazyTaksCallback = taskCallback;
                            progressListener = taskProgressListener;
                            days = i2;
                        }
                        int i8 = days;
                        String str4 = str3;
                        RestoreTierEnum restoreTierEnum2 = restoreTier;
                        TaskCallback<RestoreObjectResult, RestoreObjectRequest> taskCallback3 = lazyTaksCallback;
                        TaskProgressListener taskProgressListener2 = progressListener;
                        ListVersionsRequest listVersionsRequest4 = listVersionsRequest2;
                        listVersionsRequest4.setKeyMarker(listVersionsResultListVersions.getNextKeyMarker());
                        listVersionsRequest4.setVersionIdMarker(listVersionsResultListVersions.getNextVersionIdMarker());
                        if (!listVersionsResultListVersions.isTruncated()) {
                            break;
                        }
                        str3 = str4;
                        listVersionsRequest2 = listVersionsRequest4;
                        restoreTier = restoreTierEnum2;
                        lazyTaksCallback = taskCallback3;
                        progressListener = taskProgressListener2;
                        days = i8;
                    }
                } else {
                    ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName);
                    listObjectsRequest.setPrefix(prefix);
                    while (true) {
                        ObjectListing objectListingListObjects = obsClient.listObjects(listObjectsRequest);
                        for (ObsObject obsObject : objectListingListObjects.getObjects()) {
                            if (obsObject.getMetadata().getObjectStorageClass() == StorageClassEnum.COLD) {
                                int i9 = i5 + 1;
                                str = bucketName;
                                threadPoolExecutorInitThreadPool.execute(new RestoreObjectTask(this, bucketName, new RestoreObjectRequest(bucketName, obsObject.getObjectKey(), (String) null, days, restoreTier), lazyTaksCallback, progressListener, defaultTaskProgressStatus2, progressInterval));
                                if (ILOG.isInfoEnabled() && i9 % 1000 == 0) {
                                    ILOG.info((CharSequence) ("RestoreObjects: " + i9 + " tasks have submitted to restore objects"));
                                }
                                i5 = i9;
                            } else {
                                str = bucketName;
                            }
                            bucketName = str;
                        }
                        String str5 = bucketName;
                        listObjectsRequest.setMarker(objectListingListObjects.getNextMarker());
                        if (!objectListingListObjects.isTruncated()) {
                            break;
                        }
                        obsClient = this;
                        bucketName = str5;
                    }
                    i = i5;
                }
                defaultTaskProgressStatus = defaultTaskProgressStatus2;
            }
            defaultTaskProgressStatus.setTotalTaskNum(i);
            threadPoolExecutorInitThreadPool.shutdown();
            threadPoolExecutorInitThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
            return defaultTaskProgressStatus;
        } catch (ObsException e) {
            throw e;
        } catch (Exception e2) {
            throw new ObsException(e2.getMessage(), e2);
        }
    }

    @Override
    public HeaderResponse setBucketAcl(String str, AccessControlList accessControlList) throws ObsException {
        return setBucketAcl(str, null, accessControlList);
    }

    @Deprecated
    public HeaderResponse setBucketAcl(final String str, final String str2, final AccessControlList accessControlList) throws ObsException {
        return (HeaderResponse) doActionWithResult("setBucketAcl", str, new ActionCallbackWithResult<HeaderResponse>() {
            {
                super();
            }

            @Override
            public HeaderResponse action() throws ServiceException {
                if (accessControlList == null && str2 == null) {
                    throw new IllegalArgumentException("Both CannedACL and AccessControlList is null");
                }
                return ObsClient.this.setBucketAclImpl(str, str2, accessControlList);
            }
        });
    }

    @Override
    public HeaderResponse setBucketCors(final String str, final BucketCors bucketCors) throws ObsException {
        return (HeaderResponse) doActionWithResult("setBucketCors", str, new ActionCallbackWithResult<HeaderResponse>() {
            {
                super();
            }

            @Override
            public HeaderResponse action() throws ServiceException {
                ServiceUtils.asserParameterNotNull(bucketCors, "BucketCors is null");
                return ObsClient.this.setBucketCorsImpl(str, bucketCors);
            }
        });
    }

    @Deprecated
    public HeaderResponse setBucketCors(String str, S3BucketCors s3BucketCors) throws ObsException {
        ServiceUtils.asserParameterNotNull(s3BucketCors, "The bucket '" + str + "' does not include Cors information");
        BucketCors bucketCors = new BucketCors();
        bucketCors.setRules(s3BucketCors.getRules());
        return setBucketCors(str, bucketCors);
    }

    @Override
    public HeaderResponse setBucketDirectColdAccess(final String str, final BucketDirectColdAccess bucketDirectColdAccess) throws ObsException {
        ServiceUtils.asserParameterNotNull(str, "bucketName is null");
        ServiceUtils.asserParameterNotNull(bucketDirectColdAccess, "bucketDirectColdAccess is null");
        return (HeaderResponse) doActionWithResult("setBucketDirectColdAccess", str, new ActionCallbackWithResult<HeaderResponse>() {
            {
                super();
            }

            @Override
            public HeaderResponse action() throws ServiceException {
                return ObsClient.this.setBucketDirectColdAccessImpl(str, bucketDirectColdAccess);
            }
        });
    }

    @Override
    public HeaderResponse setBucketEncryption(final String str, final BucketEncryption bucketEncryption) throws ObsException {
        return (HeaderResponse) doActionWithResult("setBucketEncryption", str, new ActionCallbackWithResult<HeaderResponse>() {
            {
                super();
            }

            @Override
            HeaderResponse action() throws ServiceException {
                ServiceUtils.asserParameterNotNull(bucketEncryption, "BucketEncryption is null");
                return ObsClient.this.setBucketEncryptionImpl(str, bucketEncryption);
            }
        });
    }

    @Override
    public HeaderResponse setBucketFSStatus(final SetBucketFSStatusRequest setBucketFSStatusRequest) throws ObsException {
        ServiceUtils.asserParameterNotNull(setBucketFSStatusRequest, "SetBucketFileInterfaceRequest is null");
        return (HeaderResponse) doActionWithResult("setBucketFSStatus", setBucketFSStatusRequest.getBucketName(), new ActionCallbackWithResult<HeaderResponse>() {
            {
                super();
            }

            @Override
            public HeaderResponse action() throws ServiceException {
                return ObsClient.this.setBucketFSStatusImpl(setBucketFSStatusRequest);
            }
        });
    }

    @Override
    public HeaderResponse setBucketLifecycle(final String str, final LifecycleConfiguration lifecycleConfiguration) throws ObsException {
        return (HeaderResponse) doActionWithResult("setBucketLifecycleConfiguration", str, new ActionCallbackWithResult<HeaderResponse>() {
            {
                super();
            }

            @Override
            public HeaderResponse action() throws ServiceException {
                ServiceUtils.asserParameterNotNull(lifecycleConfiguration, "LifecycleConfiguration is null");
                return ObsClient.this.setBucketLifecycleConfigurationImpl(str, lifecycleConfiguration);
            }
        });
    }

    @Deprecated
    public HeaderResponse setBucketLifecycleConfiguration(String str, LifecycleConfiguration lifecycleConfiguration) throws ObsException {
        return setBucketLifecycle(str, lifecycleConfiguration);
    }

    @Override
    public HeaderResponse setBucketLogging(String str, BucketLoggingConfiguration bucketLoggingConfiguration) throws ObsException {
        return setBucketLoggingConfiguration(str, bucketLoggingConfiguration, false);
    }

    @Deprecated
    public HeaderResponse setBucketLoggingConfiguration(String str, BucketLoggingConfiguration bucketLoggingConfiguration) throws ObsException {
        return setBucketLogging(str, bucketLoggingConfiguration);
    }

    @Override
    public HeaderResponse setBucketLoggingConfiguration(final String str, final BucketLoggingConfiguration bucketLoggingConfiguration, final boolean z) throws ObsException {
        return (HeaderResponse) doActionWithResult("setBucketLoggingConfiguration", str, new ActionCallbackWithResult<HeaderResponse>() {
            {
                super();
            }

            @Override
            public HeaderResponse action() throws ServiceException {
                ObsClient obsClient = ObsClient.this;
                String str2 = str;
                BucketLoggingConfiguration bucketLoggingConfiguration2 = bucketLoggingConfiguration;
                if (bucketLoggingConfiguration2 == null) {
                    bucketLoggingConfiguration2 = new BucketLoggingConfiguration();
                }
                return obsClient.setBucketLoggingConfigurationImpl(str2, bucketLoggingConfiguration2, z);
            }
        });
    }

    @Override
    public HeaderResponse setBucketNotification(final String str, final BucketNotificationConfiguration bucketNotificationConfiguration) throws ObsException {
        return (HeaderResponse) doActionWithResult("setBucketNotification", str, new ActionCallbackWithResult<HeaderResponse>() {
            {
                super();
            }

            @Override
            public HeaderResponse action() throws ServiceException {
                ObsClient obsClient = ObsClient.this;
                String str2 = str;
                BucketNotificationConfiguration bucketNotificationConfiguration2 = bucketNotificationConfiguration;
                if (bucketNotificationConfiguration2 == null) {
                    bucketNotificationConfiguration2 = new BucketNotificationConfiguration();
                }
                return obsClient.setBucketNotificationImpl(str2, bucketNotificationConfiguration2);
            }
        });
    }

    @Override
    public HeaderResponse setBucketPolicy(final String str, final String str2) throws ObsException {
        return (HeaderResponse) doActionWithResult("setBucketPolicy", str, new ActionCallbackWithResult<HeaderResponse>() {
            {
                super();
            }

            @Override
            public HeaderResponse action() throws ServiceException {
                ServiceUtils.asserParameterNotNull(str2, "policy is null");
                return ObsClient.this.setBucketPolicyImpl(str, str2);
            }
        });
    }

    @Override
    public HeaderResponse setBucketQuota(final String str, final BucketQuota bucketQuota) throws ObsException {
        return (HeaderResponse) doActionWithResult("setBucketQuota", str, new ActionCallbackWithResult<HeaderResponse>() {
            {
                super();
            }

            @Override
            public HeaderResponse action() throws ServiceException {
                ServiceUtils.asserParameterNotNull(bucketQuota, "The bucket '" + str + "' does not include Quota information");
                return ObsClient.this.setBucketQuotaImpl(str, bucketQuota);
            }
        });
    }

    @Override
    public HeaderResponse setBucketReplication(final String str, final ReplicationConfiguration replicationConfiguration) throws ObsException {
        return (HeaderResponse) doActionWithResult("setBucketReplication", str, new ActionCallbackWithResult<HeaderResponse>() {
            {
                super();
            }

            @Override
            public HeaderResponse action() throws ServiceException {
                ServiceUtils.asserParameterNotNull(replicationConfiguration, "ReplicationConfiguration is null");
                return ObsClient.this.setBucketReplicationConfigurationImpl(str, replicationConfiguration);
            }
        });
    }

    @Deprecated
    public HeaderResponse setBucketReplicationConfiguration(String str, ReplicationConfiguration replicationConfiguration) throws ObsException {
        return setBucketReplication(str, replicationConfiguration);
    }

    @Override
    public HeaderResponse setBucketStoragePolicy(final String str, final BucketStoragePolicyConfiguration bucketStoragePolicyConfiguration) throws ObsException {
        return (HeaderResponse) doActionWithResult("setBucketStoragePolicy", str, new ActionCallbackWithResult<HeaderResponse>() {
            {
                super();
            }

            @Override
            public HeaderResponse action() throws ServiceException {
                ServiceUtils.asserParameterNotNull(bucketStoragePolicyConfiguration, "The bucket '" + str + "' does not include storagePolicy information");
                return ObsClient.this.setBucketStorageImpl(str, bucketStoragePolicyConfiguration);
            }
        });
    }

    @Override
    public HeaderResponse setBucketTagging(final String str, final BucketTagInfo bucketTagInfo) throws ObsException {
        return (HeaderResponse) doActionWithResult("setBucketTagging", str, new ActionCallbackWithResult<HeaderResponse>() {
            {
                super();
            }

            @Override
            public HeaderResponse action() throws ServiceException {
                ServiceUtils.asserParameterNotNull(bucketTagInfo, "BucketTagInfo is null");
                return ObsClient.this.setBucketTaggingImpl(str, bucketTagInfo);
            }
        });
    }

    @Override
    public HeaderResponse setBucketVersioning(final String str, final BucketVersioningConfiguration bucketVersioningConfiguration) throws ObsException {
        return (HeaderResponse) doActionWithResult("setBucketVersioning", str, new ActionCallbackWithResult<HeaderResponse>() {
            {
                super();
            }

            @Override
            public HeaderResponse action() throws ServiceException {
                ServiceUtils.asserParameterNotNull(bucketVersioningConfiguration, "BucketVersioningConfiguration is null");
                return ObsClient.this.setBucketVersioningImpl(str, bucketVersioningConfiguration.getVersioningStatus());
            }
        });
    }

    @Deprecated
    public HeaderResponse setBucketVersioning(String str, String str2) throws ObsException {
        return setBucketVersioning(str, new BucketVersioningConfiguration(str2));
    }

    @Override
    public HeaderResponse setBucketWebsite(final String str, final WebsiteConfiguration websiteConfiguration) throws ObsException {
        return (HeaderResponse) doActionWithResult("setBucketWebsiteConfiguration", str, new ActionCallbackWithResult<HeaderResponse>() {
            {
                super();
            }

            @Override
            public HeaderResponse action() throws ServiceException {
                ServiceUtils.asserParameterNotNull(websiteConfiguration, "WebsiteConfiguration is null");
                return ObsClient.this.setBucketWebsiteConfigurationImpl(str, websiteConfiguration);
            }
        });
    }

    @Deprecated
    public HeaderResponse setBucketWebsiteConfiguration(String str, WebsiteConfiguration websiteConfiguration) throws ObsException {
        return setBucketWebsite(str, websiteConfiguration);
    }

    @Override
    public HeaderResponse setObjectAcl(String str, String str2, AccessControlList accessControlList) throws ObsException {
        return setObjectAcl(str, str2, null, accessControlList, null);
    }

    @Override
    public HeaderResponse setObjectAcl(String str, String str2, AccessControlList accessControlList, String str3) throws ObsException {
        return setObjectAcl(str, str2, null, accessControlList, str3);
    }

    @Deprecated
    public HeaderResponse setObjectAcl(final String str, final String str2, final String str3, final AccessControlList accessControlList, final String str4) throws ObsException {
        return (HeaderResponse) doActionWithResult("setObjectAcl", str, new ActionCallbackWithResult<HeaderResponse>() {
            {
                super();
            }

            @Override
            public HeaderResponse action() throws ServiceException {
                if (accessControlList == null && str3 == null) {
                    throw new IllegalArgumentException("Both cannedACL and AccessControlList is null");
                }
                return ObsClient.this.setObjectAclImpl(str, str2, str3, accessControlList, str4);
            }
        });
    }

    @Override
    public ObjectMetadata setObjectMetadata(final SetObjectMetadataRequest setObjectMetadataRequest) throws ObsException {
        ServiceUtils.asserParameterNotNull(setObjectMetadataRequest, "SetObjectMetadataRequest is null");
        return (ObjectMetadata) doActionWithResult("setObjectMetadata", setObjectMetadataRequest.getBucketName(), new ActionCallbackWithResult<ObjectMetadata>() {
            {
                super();
            }

            @Override
            public ObjectMetadata action() throws ServiceException {
                return ObsClient.this.setObjectMetadataImpl(setObjectMetadataRequest);
            }
        });
    }

    @Override
    public TruncateFileResult truncateFile(final TruncateFileRequest truncateFileRequest) throws ObsException {
        ServiceUtils.asserParameterNotNull(truncateFileRequest, "TruncateFileRequest is null");
        ServiceUtils.asserParameterNotNull2(truncateFileRequest.getObjectKey(), "ObjectKey is null");
        return (TruncateFileResult) doActionWithResult("truncateFile", truncateFileRequest.getBucketName(), new ActionCallbackWithResult<TruncateFileResult>() {
            {
                super();
            }

            @Override
            public TruncateFileResult action() throws ServiceException {
                return ObsClient.this.truncateFileImpl(truncateFileRequest);
            }
        });
    }

    @Override
    public CompleteMultipartUploadResult uploadFile(UploadFileRequest uploadFileRequest) throws ObsException {
        return new ResumableClient(this).uploadFileResume(uploadFileRequest);
    }

    @Override
    public UploadPartResult uploadPart(final UploadPartRequest uploadPartRequest) throws ObsException {
        ServiceUtils.asserParameterNotNull(uploadPartRequest, "UploadPartRequest is null");
        ServiceUtils.asserParameterNotNull2(uploadPartRequest.getObjectKey(), "objectKey is null");
        ServiceUtils.asserParameterNotNull(uploadPartRequest.getUploadId(), "uploadId is null");
        return (UploadPartResult) doActionWithResult("uploadPart", uploadPartRequest.getBucketName(), new ActionCallbackWithResult<UploadPartResult>() {
            {
                super();
            }

            @Override
            public UploadPartResult action() throws ServiceException {
                if (uploadPartRequest.getInput() == null || uploadPartRequest.getFile() == null) {
                    return ObsClient.this.uploadPartImpl(uploadPartRequest);
                }
                throw new ServiceException("Both input and file are set, only one is allowed");
            }
        });
    }

    @Override
    public UploadPartResult uploadPart(String str, String str2, String str3, int i, File file) throws ObsException {
        UploadPartRequest uploadPartRequest = new UploadPartRequest();
        uploadPartRequest.setBucketName(str);
        uploadPartRequest.setObjectKey(str2);
        uploadPartRequest.setUploadId(str3);
        uploadPartRequest.setPartNumber(i);
        uploadPartRequest.setFile(file);
        return uploadPart(uploadPartRequest);
    }

    @Override
    public UploadPartResult uploadPart(String str, String str2, String str3, int i, InputStream inputStream) throws ObsException {
        UploadPartRequest uploadPartRequest = new UploadPartRequest();
        uploadPartRequest.setBucketName(str);
        uploadPartRequest.setObjectKey(str2);
        uploadPartRequest.setUploadId(str3);
        uploadPartRequest.setPartNumber(i);
        uploadPartRequest.setInput(inputStream);
        return uploadPart(uploadPartRequest);
    }

    @Override
    public ObsFSFile writeFile(final WriteFileRequest writeFileRequest) throws IllegalAccessException, IllegalArgumentException, ObsException {
        ServiceUtils.asserParameterNotNull(writeFileRequest, "WriteFileRequest is null");
        ServiceUtils.asserParameterNotNull2(writeFileRequest.getObjectKey(), "objectKey is null");
        ObsFSFile obsFSFile = (ObsFSFile) doActionWithResult("writeFile", writeFileRequest.getBucketName(), new ActionCallbackWithResult<ObsFSFile>() {
            {
                super();
            }

            @Override
            public ObsFSFile action() throws ServiceException {
                if (writeFileRequest.getInput() == null || writeFileRequest.getFile() == null) {
                    return ObsClient.this.writeFileImpl(writeFileRequest);
                }
                throw new ServiceException("Both input and file are set, only one is allowed");
            }
        });
        ReflectUtils.setInnerClient(obsFSFile, this);
        return obsFSFile;
    }
}
