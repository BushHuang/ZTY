package com.alibaba.sdk.android.oss;

import android.content.Context;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.common.OSSLogToFileUtils;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.utils.OSSUtils;
import com.alibaba.sdk.android.oss.internal.ExtensionRequestOperation;
import com.alibaba.sdk.android.oss.internal.InternalRequestOperation;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.internal.ObjectURLPresigner;
import com.alibaba.sdk.android.oss.model.AbortMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.AbortMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.AppendObjectRequest;
import com.alibaba.sdk.android.oss.model.AppendObjectResult;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.CopyObjectRequest;
import com.alibaba.sdk.android.oss.model.CopyObjectResult;
import com.alibaba.sdk.android.oss.model.CreateBucketRequest;
import com.alibaba.sdk.android.oss.model.CreateBucketResult;
import com.alibaba.sdk.android.oss.model.DeleteBucketRequest;
import com.alibaba.sdk.android.oss.model.DeleteBucketResult;
import com.alibaba.sdk.android.oss.model.DeleteMultipleObjectRequest;
import com.alibaba.sdk.android.oss.model.DeleteMultipleObjectResult;
import com.alibaba.sdk.android.oss.model.DeleteObjectRequest;
import com.alibaba.sdk.android.oss.model.DeleteObjectResult;
import com.alibaba.sdk.android.oss.model.GeneratePresignedUrlRequest;
import com.alibaba.sdk.android.oss.model.GetBucketACLRequest;
import com.alibaba.sdk.android.oss.model.GetBucketACLResult;
import com.alibaba.sdk.android.oss.model.GetBucketInfoRequest;
import com.alibaba.sdk.android.oss.model.GetBucketInfoResult;
import com.alibaba.sdk.android.oss.model.GetObjectACLRequest;
import com.alibaba.sdk.android.oss.model.GetObjectACLResult;
import com.alibaba.sdk.android.oss.model.GetObjectRequest;
import com.alibaba.sdk.android.oss.model.GetObjectResult;
import com.alibaba.sdk.android.oss.model.GetSymlinkRequest;
import com.alibaba.sdk.android.oss.model.GetSymlinkResult;
import com.alibaba.sdk.android.oss.model.HeadObjectRequest;
import com.alibaba.sdk.android.oss.model.HeadObjectResult;
import com.alibaba.sdk.android.oss.model.ImagePersistRequest;
import com.alibaba.sdk.android.oss.model.ImagePersistResult;
import com.alibaba.sdk.android.oss.model.InitiateMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.InitiateMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.ListBucketsRequest;
import com.alibaba.sdk.android.oss.model.ListBucketsResult;
import com.alibaba.sdk.android.oss.model.ListMultipartUploadsRequest;
import com.alibaba.sdk.android.oss.model.ListMultipartUploadsResult;
import com.alibaba.sdk.android.oss.model.ListObjectsRequest;
import com.alibaba.sdk.android.oss.model.ListObjectsResult;
import com.alibaba.sdk.android.oss.model.ListPartsRequest;
import com.alibaba.sdk.android.oss.model.ListPartsResult;
import com.alibaba.sdk.android.oss.model.MultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.alibaba.sdk.android.oss.model.PutSymlinkRequest;
import com.alibaba.sdk.android.oss.model.PutSymlinkResult;
import com.alibaba.sdk.android.oss.model.RestoreObjectRequest;
import com.alibaba.sdk.android.oss.model.RestoreObjectResult;
import com.alibaba.sdk.android.oss.model.ResumableUploadRequest;
import com.alibaba.sdk.android.oss.model.ResumableUploadResult;
import com.alibaba.sdk.android.oss.model.TriggerCallbackRequest;
import com.alibaba.sdk.android.oss.model.TriggerCallbackResult;
import com.alibaba.sdk.android.oss.model.UploadPartRequest;
import com.alibaba.sdk.android.oss.model.UploadPartResult;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

class OSSImpl implements OSS {
    private ClientConfiguration conf;
    private OSSCredentialProvider credentialProvider;
    private URI endpointURI;
    private ExtensionRequestOperation extensionRequestOperation;
    private InternalRequestOperation internalRequestOperation;

    public OSSImpl(Context context, OSSCredentialProvider oSSCredentialProvider, ClientConfiguration clientConfiguration) {
        this.credentialProvider = oSSCredentialProvider;
        this.conf = clientConfiguration == null ? ClientConfiguration.getDefaultConf() : clientConfiguration;
        this.internalRequestOperation = new InternalRequestOperation(context.getApplicationContext(), oSSCredentialProvider, this.conf);
        this.extensionRequestOperation = new ExtensionRequestOperation(this.internalRequestOperation);
    }

    public OSSImpl(Context context, String str, OSSCredentialProvider oSSCredentialProvider, ClientConfiguration clientConfiguration) {
        OSSLogToFileUtils.init(context.getApplicationContext(), clientConfiguration);
        try {
            String strTrim = str.trim();
            if (!strTrim.startsWith("http")) {
                strTrim = "http://" + strTrim;
            }
            this.endpointURI = new URI(strTrim);
            if (oSSCredentialProvider == null) {
                throw new IllegalArgumentException("CredentialProvider can't be null.");
            }
            Boolean boolValueOf = false;
            try {
                boolValueOf = Boolean.valueOf(OSSUtils.isValidateIP(this.endpointURI.getHost()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (this.endpointURI.getScheme().equals("https") && boolValueOf.booleanValue()) {
                throw new IllegalArgumentException("endpoint should not be format with https://ip.");
            }
            this.credentialProvider = oSSCredentialProvider;
            this.conf = clientConfiguration == null ? ClientConfiguration.getDefaultConf() : clientConfiguration;
            this.internalRequestOperation = new InternalRequestOperation(context.getApplicationContext(), this.endpointURI, oSSCredentialProvider, this.conf);
            this.extensionRequestOperation = new ExtensionRequestOperation(this.internalRequestOperation);
        } catch (URISyntaxException unused) {
            throw new IllegalArgumentException("Endpoint must be a string like 'http://oss-cn-****.aliyuncs.com',or your cname like 'http://image.cnamedomain.com'!");
        }
    }

    @Override
    public AbortMultipartUploadResult abortMultipartUpload(AbortMultipartUploadRequest abortMultipartUploadRequest) throws ServiceException, ClientException {
        return (AbortMultipartUploadResult) this.internalRequestOperation.abortMultipartUpload(abortMultipartUploadRequest, null).getResult();
    }

    @Override
    public void abortResumableUpload(ResumableUploadRequest resumableUploadRequest) throws IOException {
        this.extensionRequestOperation.abortResumableUpload(resumableUploadRequest);
    }

    @Override
    public AppendObjectResult appendObject(AppendObjectRequest appendObjectRequest) throws ServiceException, ClientException {
        return this.internalRequestOperation.syncAppendObject(appendObjectRequest);
    }

    @Override
    public OSSAsyncTask<AbortMultipartUploadResult> asyncAbortMultipartUpload(AbortMultipartUploadRequest abortMultipartUploadRequest, OSSCompletedCallback<AbortMultipartUploadRequest, AbortMultipartUploadResult> oSSCompletedCallback) {
        return this.internalRequestOperation.abortMultipartUpload(abortMultipartUploadRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<AppendObjectResult> asyncAppendObject(AppendObjectRequest appendObjectRequest, OSSCompletedCallback<AppendObjectRequest, AppendObjectResult> oSSCompletedCallback) {
        return this.internalRequestOperation.appendObject(appendObjectRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<CompleteMultipartUploadResult> asyncCompleteMultipartUpload(CompleteMultipartUploadRequest completeMultipartUploadRequest, OSSCompletedCallback<CompleteMultipartUploadRequest, CompleteMultipartUploadResult> oSSCompletedCallback) {
        return this.internalRequestOperation.completeMultipartUpload(completeMultipartUploadRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<CopyObjectResult> asyncCopyObject(CopyObjectRequest copyObjectRequest, OSSCompletedCallback<CopyObjectRequest, CopyObjectResult> oSSCompletedCallback) {
        return this.internalRequestOperation.copyObject(copyObjectRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<CreateBucketResult> asyncCreateBucket(CreateBucketRequest createBucketRequest, OSSCompletedCallback<CreateBucketRequest, CreateBucketResult> oSSCompletedCallback) {
        return this.internalRequestOperation.createBucket(createBucketRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<DeleteBucketResult> asyncDeleteBucket(DeleteBucketRequest deleteBucketRequest, OSSCompletedCallback<DeleteBucketRequest, DeleteBucketResult> oSSCompletedCallback) {
        return this.internalRequestOperation.deleteBucket(deleteBucketRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<DeleteMultipleObjectResult> asyncDeleteMultipleObject(DeleteMultipleObjectRequest deleteMultipleObjectRequest, OSSCompletedCallback<DeleteMultipleObjectRequest, DeleteMultipleObjectResult> oSSCompletedCallback) {
        return this.internalRequestOperation.deleteMultipleObject(deleteMultipleObjectRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<DeleteObjectResult> asyncDeleteObject(DeleteObjectRequest deleteObjectRequest, OSSCompletedCallback<DeleteObjectRequest, DeleteObjectResult> oSSCompletedCallback) {
        return this.internalRequestOperation.deleteObject(deleteObjectRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<GetBucketACLResult> asyncGetBucketACL(GetBucketACLRequest getBucketACLRequest, OSSCompletedCallback<GetBucketACLRequest, GetBucketACLResult> oSSCompletedCallback) {
        return this.internalRequestOperation.getBucketACL(getBucketACLRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<GetBucketInfoResult> asyncGetBucketInfo(GetBucketInfoRequest getBucketInfoRequest, OSSCompletedCallback<GetBucketInfoRequest, GetBucketInfoResult> oSSCompletedCallback) {
        return this.internalRequestOperation.getBucketInfo(getBucketInfoRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<GetObjectResult> asyncGetObject(GetObjectRequest getObjectRequest, OSSCompletedCallback<GetObjectRequest, GetObjectResult> oSSCompletedCallback) {
        return this.internalRequestOperation.getObject(getObjectRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<GetObjectACLResult> asyncGetObjectACL(GetObjectACLRequest getObjectACLRequest, OSSCompletedCallback<GetObjectACLRequest, GetObjectACLResult> oSSCompletedCallback) {
        return this.internalRequestOperation.getObjectACL(getObjectACLRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<GetSymlinkResult> asyncGetSymlink(GetSymlinkRequest getSymlinkRequest, OSSCompletedCallback<GetSymlinkRequest, GetSymlinkResult> oSSCompletedCallback) {
        return this.internalRequestOperation.getSymlink(getSymlinkRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<HeadObjectResult> asyncHeadObject(HeadObjectRequest headObjectRequest, OSSCompletedCallback<HeadObjectRequest, HeadObjectResult> oSSCompletedCallback) {
        return this.internalRequestOperation.headObject(headObjectRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<ImagePersistResult> asyncImagePersist(ImagePersistRequest imagePersistRequest, OSSCompletedCallback<ImagePersistRequest, ImagePersistResult> oSSCompletedCallback) {
        return this.internalRequestOperation.imageActionPersist(imagePersistRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<InitiateMultipartUploadResult> asyncInitMultipartUpload(InitiateMultipartUploadRequest initiateMultipartUploadRequest, OSSCompletedCallback<InitiateMultipartUploadRequest, InitiateMultipartUploadResult> oSSCompletedCallback) {
        return this.internalRequestOperation.initMultipartUpload(initiateMultipartUploadRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<ListBucketsResult> asyncListBuckets(ListBucketsRequest listBucketsRequest, OSSCompletedCallback<ListBucketsRequest, ListBucketsResult> oSSCompletedCallback) {
        return this.internalRequestOperation.listBuckets(listBucketsRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<ListMultipartUploadsResult> asyncListMultipartUploads(ListMultipartUploadsRequest listMultipartUploadsRequest, OSSCompletedCallback<ListMultipartUploadsRequest, ListMultipartUploadsResult> oSSCompletedCallback) {
        return this.internalRequestOperation.listMultipartUploads(listMultipartUploadsRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<ListObjectsResult> asyncListObjects(ListObjectsRequest listObjectsRequest, OSSCompletedCallback<ListObjectsRequest, ListObjectsResult> oSSCompletedCallback) {
        return this.internalRequestOperation.listObjects(listObjectsRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<ListPartsResult> asyncListParts(ListPartsRequest listPartsRequest, OSSCompletedCallback<ListPartsRequest, ListPartsResult> oSSCompletedCallback) {
        return this.internalRequestOperation.listParts(listPartsRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<CompleteMultipartUploadResult> asyncMultipartUpload(MultipartUploadRequest multipartUploadRequest, OSSCompletedCallback<MultipartUploadRequest, CompleteMultipartUploadResult> oSSCompletedCallback) {
        return this.extensionRequestOperation.multipartUpload(multipartUploadRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<PutObjectResult> asyncPutObject(PutObjectRequest putObjectRequest, OSSCompletedCallback<PutObjectRequest, PutObjectResult> oSSCompletedCallback) {
        return this.internalRequestOperation.putObject(putObjectRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<PutSymlinkResult> asyncPutSymlink(PutSymlinkRequest putSymlinkRequest, OSSCompletedCallback<PutSymlinkRequest, PutSymlinkResult> oSSCompletedCallback) {
        return this.internalRequestOperation.putSymlink(putSymlinkRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<RestoreObjectResult> asyncRestoreObject(RestoreObjectRequest restoreObjectRequest, OSSCompletedCallback<RestoreObjectRequest, RestoreObjectResult> oSSCompletedCallback) {
        return this.internalRequestOperation.restoreObject(restoreObjectRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<ResumableUploadResult> asyncResumableUpload(ResumableUploadRequest resumableUploadRequest, OSSCompletedCallback<ResumableUploadRequest, ResumableUploadResult> oSSCompletedCallback) {
        return this.extensionRequestOperation.resumableUpload(resumableUploadRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<ResumableUploadResult> asyncSequenceUpload(ResumableUploadRequest resumableUploadRequest, OSSCompletedCallback<ResumableUploadRequest, ResumableUploadResult> oSSCompletedCallback) {
        return this.extensionRequestOperation.sequenceUpload(resumableUploadRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<TriggerCallbackResult> asyncTriggerCallback(TriggerCallbackRequest triggerCallbackRequest, OSSCompletedCallback<TriggerCallbackRequest, TriggerCallbackResult> oSSCompletedCallback) {
        return this.internalRequestOperation.triggerCallback(triggerCallbackRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<UploadPartResult> asyncUploadPart(UploadPartRequest uploadPartRequest, OSSCompletedCallback<UploadPartRequest, UploadPartResult> oSSCompletedCallback) {
        return this.internalRequestOperation.uploadPart(uploadPartRequest, oSSCompletedCallback);
    }

    @Override
    public CompleteMultipartUploadResult completeMultipartUpload(CompleteMultipartUploadRequest completeMultipartUploadRequest) throws ServiceException, ClientException {
        return this.internalRequestOperation.syncCompleteMultipartUpload(completeMultipartUploadRequest);
    }

    @Override
    public CopyObjectResult copyObject(CopyObjectRequest copyObjectRequest) throws ServiceException, ClientException {
        return (CopyObjectResult) this.internalRequestOperation.copyObject(copyObjectRequest, null).getResult();
    }

    @Override
    public CreateBucketResult createBucket(CreateBucketRequest createBucketRequest) throws ServiceException, ClientException {
        return (CreateBucketResult) this.internalRequestOperation.createBucket(createBucketRequest, null).getResult();
    }

    @Override
    public DeleteBucketResult deleteBucket(DeleteBucketRequest deleteBucketRequest) throws ServiceException, ClientException {
        return (DeleteBucketResult) this.internalRequestOperation.deleteBucket(deleteBucketRequest, null).getResult();
    }

    @Override
    public DeleteMultipleObjectResult deleteMultipleObject(DeleteMultipleObjectRequest deleteMultipleObjectRequest) throws ServiceException, ClientException {
        return (DeleteMultipleObjectResult) this.internalRequestOperation.deleteMultipleObject(deleteMultipleObjectRequest, null).getResult();
    }

    @Override
    public DeleteObjectResult deleteObject(DeleteObjectRequest deleteObjectRequest) throws ServiceException, ClientException {
        return (DeleteObjectResult) this.internalRequestOperation.deleteObject(deleteObjectRequest, null).getResult();
    }

    @Override
    public boolean doesObjectExist(String str, String str2) throws ServiceException, ClientException {
        return this.extensionRequestOperation.doesObjectExist(str, str2);
    }

    @Override
    public GetBucketACLResult getBucketACL(GetBucketACLRequest getBucketACLRequest) throws ServiceException, ClientException {
        return (GetBucketACLResult) this.internalRequestOperation.getBucketACL(getBucketACLRequest, null).getResult();
    }

    @Override
    public GetBucketInfoResult getBucketInfo(GetBucketInfoRequest getBucketInfoRequest) throws ServiceException, ClientException {
        return (GetBucketInfoResult) this.internalRequestOperation.getBucketInfo(getBucketInfoRequest, null).getResult();
    }

    @Override
    public GetObjectResult getObject(GetObjectRequest getObjectRequest) throws ServiceException, ClientException {
        return (GetObjectResult) this.internalRequestOperation.getObject(getObjectRequest, null).getResult();
    }

    @Override
    public GetObjectACLResult getObjectACL(GetObjectACLRequest getObjectACLRequest) throws ServiceException, ClientException {
        return (GetObjectACLResult) this.internalRequestOperation.getObjectACL(getObjectACLRequest, null).getResult();
    }

    @Override
    public GetSymlinkResult getSymlink(GetSymlinkRequest getSymlinkRequest) throws ServiceException, ClientException {
        return this.internalRequestOperation.syncGetSymlink(getSymlinkRequest);
    }

    @Override
    public HeadObjectResult headObject(HeadObjectRequest headObjectRequest) throws ServiceException, ClientException {
        return (HeadObjectResult) this.internalRequestOperation.headObject(headObjectRequest, null).getResult();
    }

    @Override
    public ImagePersistResult imagePersist(ImagePersistRequest imagePersistRequest) throws ServiceException, ClientException {
        return (ImagePersistResult) this.internalRequestOperation.imageActionPersist(imagePersistRequest, null).getResult();
    }

    @Override
    public InitiateMultipartUploadResult initMultipartUpload(InitiateMultipartUploadRequest initiateMultipartUploadRequest) throws ServiceException, ClientException {
        return (InitiateMultipartUploadResult) this.internalRequestOperation.initMultipartUpload(initiateMultipartUploadRequest, null).getResult();
    }

    @Override
    public ListBucketsResult listBuckets(ListBucketsRequest listBucketsRequest) throws ServiceException, ClientException {
        return (ListBucketsResult) this.internalRequestOperation.listBuckets(listBucketsRequest, null).getResult();
    }

    @Override
    public ListMultipartUploadsResult listMultipartUploads(ListMultipartUploadsRequest listMultipartUploadsRequest) throws ServiceException, ClientException {
        return (ListMultipartUploadsResult) this.internalRequestOperation.listMultipartUploads(listMultipartUploadsRequest, null).getResult();
    }

    @Override
    public ListObjectsResult listObjects(ListObjectsRequest listObjectsRequest) throws ServiceException, ClientException {
        return (ListObjectsResult) this.internalRequestOperation.listObjects(listObjectsRequest, null).getResult();
    }

    @Override
    public ListPartsResult listParts(ListPartsRequest listPartsRequest) throws ServiceException, ClientException {
        return (ListPartsResult) this.internalRequestOperation.listParts(listPartsRequest, null).getResult();
    }

    @Override
    public CompleteMultipartUploadResult multipartUpload(MultipartUploadRequest multipartUploadRequest) throws ServiceException, ClientException {
        return (CompleteMultipartUploadResult) this.extensionRequestOperation.multipartUpload(multipartUploadRequest, null).getResult();
    }

    @Override
    public String presignConstrainedObjectURL(GeneratePresignedUrlRequest generatePresignedUrlRequest) throws ClientException {
        return new ObjectURLPresigner(this.endpointURI, this.credentialProvider, this.conf).presignConstrainedURL(generatePresignedUrlRequest);
    }

    @Override
    public String presignConstrainedObjectURL(String str, String str2, long j) throws ClientException {
        return new ObjectURLPresigner(this.endpointURI, this.credentialProvider, this.conf).presignConstrainedURL(str, str2, j);
    }

    @Override
    public String presignPublicObjectURL(String str, String str2) {
        return new ObjectURLPresigner(this.endpointURI, this.credentialProvider, this.conf).presignPublicURL(str, str2);
    }

    @Override
    public PutObjectResult putObject(PutObjectRequest putObjectRequest) throws ServiceException, ClientException {
        return this.internalRequestOperation.syncPutObject(putObjectRequest);
    }

    @Override
    public PutSymlinkResult putSymlink(PutSymlinkRequest putSymlinkRequest) throws ServiceException, ClientException {
        return this.internalRequestOperation.syncPutSymlink(putSymlinkRequest);
    }

    @Override
    public RestoreObjectResult restoreObject(RestoreObjectRequest restoreObjectRequest) throws ServiceException, ClientException {
        return this.internalRequestOperation.syncRestoreObject(restoreObjectRequest);
    }

    @Override
    public ResumableUploadResult resumableUpload(ResumableUploadRequest resumableUploadRequest) throws ServiceException, ClientException {
        return (ResumableUploadResult) this.extensionRequestOperation.resumableUpload(resumableUploadRequest, null).getResult();
    }

    @Override
    public ResumableUploadResult sequenceUpload(ResumableUploadRequest resumableUploadRequest) throws ServiceException, ClientException {
        return (ResumableUploadResult) this.extensionRequestOperation.sequenceUpload(resumableUploadRequest, null).getResult();
    }

    @Override
    public TriggerCallbackResult triggerCallback(TriggerCallbackRequest triggerCallbackRequest) throws ServiceException, ClientException {
        return this.internalRequestOperation.asyncTriggerCallback(triggerCallbackRequest);
    }

    @Override
    public void updateCredentialProvider(OSSCredentialProvider oSSCredentialProvider) {
        this.credentialProvider = oSSCredentialProvider;
        this.internalRequestOperation.setCredentialProvider(oSSCredentialProvider);
    }

    @Override
    public UploadPartResult uploadPart(UploadPartRequest uploadPartRequest) throws ServiceException, ClientException {
        return this.internalRequestOperation.syncUploadPart(uploadPartRequest);
    }
}
