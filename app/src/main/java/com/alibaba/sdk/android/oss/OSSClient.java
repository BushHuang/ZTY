package com.alibaba.sdk.android.oss;

import android.content.Context;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
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

public class OSSClient implements OSS {
    private OSS mOss;

    public OSSClient(Context context, OSSCredentialProvider oSSCredentialProvider, ClientConfiguration clientConfiguration) {
        this.mOss = new OSSImpl(context, oSSCredentialProvider, clientConfiguration);
    }

    public OSSClient(Context context, String str, OSSCredentialProvider oSSCredentialProvider) {
        this(context, str, oSSCredentialProvider, null);
    }

    public OSSClient(Context context, String str, OSSCredentialProvider oSSCredentialProvider, ClientConfiguration clientConfiguration) {
        this.mOss = new OSSImpl(context, str, oSSCredentialProvider, clientConfiguration);
    }

    @Override
    public AbortMultipartUploadResult abortMultipartUpload(AbortMultipartUploadRequest abortMultipartUploadRequest) throws ServiceException, ClientException {
        return this.mOss.abortMultipartUpload(abortMultipartUploadRequest);
    }

    @Override
    public void abortResumableUpload(ResumableUploadRequest resumableUploadRequest) throws IOException {
        this.mOss.abortResumableUpload(resumableUploadRequest);
    }

    @Override
    public AppendObjectResult appendObject(AppendObjectRequest appendObjectRequest) throws ServiceException, ClientException {
        return this.mOss.appendObject(appendObjectRequest);
    }

    @Override
    public OSSAsyncTask<AbortMultipartUploadResult> asyncAbortMultipartUpload(AbortMultipartUploadRequest abortMultipartUploadRequest, OSSCompletedCallback<AbortMultipartUploadRequest, AbortMultipartUploadResult> oSSCompletedCallback) {
        return this.mOss.asyncAbortMultipartUpload(abortMultipartUploadRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<AppendObjectResult> asyncAppendObject(AppendObjectRequest appendObjectRequest, OSSCompletedCallback<AppendObjectRequest, AppendObjectResult> oSSCompletedCallback) {
        return this.mOss.asyncAppendObject(appendObjectRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<CompleteMultipartUploadResult> asyncCompleteMultipartUpload(CompleteMultipartUploadRequest completeMultipartUploadRequest, OSSCompletedCallback<CompleteMultipartUploadRequest, CompleteMultipartUploadResult> oSSCompletedCallback) {
        return this.mOss.asyncCompleteMultipartUpload(completeMultipartUploadRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<CopyObjectResult> asyncCopyObject(CopyObjectRequest copyObjectRequest, OSSCompletedCallback<CopyObjectRequest, CopyObjectResult> oSSCompletedCallback) {
        return this.mOss.asyncCopyObject(copyObjectRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<CreateBucketResult> asyncCreateBucket(CreateBucketRequest createBucketRequest, OSSCompletedCallback<CreateBucketRequest, CreateBucketResult> oSSCompletedCallback) {
        return this.mOss.asyncCreateBucket(createBucketRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<DeleteBucketResult> asyncDeleteBucket(DeleteBucketRequest deleteBucketRequest, OSSCompletedCallback<DeleteBucketRequest, DeleteBucketResult> oSSCompletedCallback) {
        return this.mOss.asyncDeleteBucket(deleteBucketRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<DeleteMultipleObjectResult> asyncDeleteMultipleObject(DeleteMultipleObjectRequest deleteMultipleObjectRequest, OSSCompletedCallback<DeleteMultipleObjectRequest, DeleteMultipleObjectResult> oSSCompletedCallback) {
        return this.mOss.asyncDeleteMultipleObject(deleteMultipleObjectRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<DeleteObjectResult> asyncDeleteObject(DeleteObjectRequest deleteObjectRequest, OSSCompletedCallback<DeleteObjectRequest, DeleteObjectResult> oSSCompletedCallback) {
        return this.mOss.asyncDeleteObject(deleteObjectRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<GetBucketACLResult> asyncGetBucketACL(GetBucketACLRequest getBucketACLRequest, OSSCompletedCallback<GetBucketACLRequest, GetBucketACLResult> oSSCompletedCallback) {
        return this.mOss.asyncGetBucketACL(getBucketACLRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<GetBucketInfoResult> asyncGetBucketInfo(GetBucketInfoRequest getBucketInfoRequest, OSSCompletedCallback<GetBucketInfoRequest, GetBucketInfoResult> oSSCompletedCallback) {
        return this.mOss.asyncGetBucketInfo(getBucketInfoRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<GetObjectResult> asyncGetObject(GetObjectRequest getObjectRequest, OSSCompletedCallback<GetObjectRequest, GetObjectResult> oSSCompletedCallback) {
        return this.mOss.asyncGetObject(getObjectRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<GetObjectACLResult> asyncGetObjectACL(GetObjectACLRequest getObjectACLRequest, OSSCompletedCallback<GetObjectACLRequest, GetObjectACLResult> oSSCompletedCallback) {
        return this.mOss.asyncGetObjectACL(getObjectACLRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<GetSymlinkResult> asyncGetSymlink(GetSymlinkRequest getSymlinkRequest, OSSCompletedCallback<GetSymlinkRequest, GetSymlinkResult> oSSCompletedCallback) {
        return this.mOss.asyncGetSymlink(getSymlinkRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<HeadObjectResult> asyncHeadObject(HeadObjectRequest headObjectRequest, OSSCompletedCallback<HeadObjectRequest, HeadObjectResult> oSSCompletedCallback) {
        return this.mOss.asyncHeadObject(headObjectRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<ImagePersistResult> asyncImagePersist(ImagePersistRequest imagePersistRequest, OSSCompletedCallback<ImagePersistRequest, ImagePersistResult> oSSCompletedCallback) {
        return this.mOss.asyncImagePersist(imagePersistRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<InitiateMultipartUploadResult> asyncInitMultipartUpload(InitiateMultipartUploadRequest initiateMultipartUploadRequest, OSSCompletedCallback<InitiateMultipartUploadRequest, InitiateMultipartUploadResult> oSSCompletedCallback) {
        return this.mOss.asyncInitMultipartUpload(initiateMultipartUploadRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<ListBucketsResult> asyncListBuckets(ListBucketsRequest listBucketsRequest, OSSCompletedCallback<ListBucketsRequest, ListBucketsResult> oSSCompletedCallback) {
        return this.mOss.asyncListBuckets(listBucketsRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<ListMultipartUploadsResult> asyncListMultipartUploads(ListMultipartUploadsRequest listMultipartUploadsRequest, OSSCompletedCallback<ListMultipartUploadsRequest, ListMultipartUploadsResult> oSSCompletedCallback) {
        return this.mOss.asyncListMultipartUploads(listMultipartUploadsRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<ListObjectsResult> asyncListObjects(ListObjectsRequest listObjectsRequest, OSSCompletedCallback<ListObjectsRequest, ListObjectsResult> oSSCompletedCallback) {
        return this.mOss.asyncListObjects(listObjectsRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<ListPartsResult> asyncListParts(ListPartsRequest listPartsRequest, OSSCompletedCallback<ListPartsRequest, ListPartsResult> oSSCompletedCallback) {
        return this.mOss.asyncListParts(listPartsRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<CompleteMultipartUploadResult> asyncMultipartUpload(MultipartUploadRequest multipartUploadRequest, OSSCompletedCallback<MultipartUploadRequest, CompleteMultipartUploadResult> oSSCompletedCallback) {
        return this.mOss.asyncMultipartUpload(multipartUploadRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<PutObjectResult> asyncPutObject(PutObjectRequest putObjectRequest, OSSCompletedCallback<PutObjectRequest, PutObjectResult> oSSCompletedCallback) {
        return this.mOss.asyncPutObject(putObjectRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<PutSymlinkResult> asyncPutSymlink(PutSymlinkRequest putSymlinkRequest, OSSCompletedCallback<PutSymlinkRequest, PutSymlinkResult> oSSCompletedCallback) {
        return this.mOss.asyncPutSymlink(putSymlinkRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<RestoreObjectResult> asyncRestoreObject(RestoreObjectRequest restoreObjectRequest, OSSCompletedCallback<RestoreObjectRequest, RestoreObjectResult> oSSCompletedCallback) {
        return this.mOss.asyncRestoreObject(restoreObjectRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<ResumableUploadResult> asyncResumableUpload(ResumableUploadRequest resumableUploadRequest, OSSCompletedCallback<ResumableUploadRequest, ResumableUploadResult> oSSCompletedCallback) {
        return this.mOss.asyncResumableUpload(resumableUploadRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<ResumableUploadResult> asyncSequenceUpload(ResumableUploadRequest resumableUploadRequest, OSSCompletedCallback<ResumableUploadRequest, ResumableUploadResult> oSSCompletedCallback) {
        return this.mOss.asyncSequenceUpload(resumableUploadRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<TriggerCallbackResult> asyncTriggerCallback(TriggerCallbackRequest triggerCallbackRequest, OSSCompletedCallback<TriggerCallbackRequest, TriggerCallbackResult> oSSCompletedCallback) {
        return this.mOss.asyncTriggerCallback(triggerCallbackRequest, oSSCompletedCallback);
    }

    @Override
    public OSSAsyncTask<UploadPartResult> asyncUploadPart(UploadPartRequest uploadPartRequest, OSSCompletedCallback<UploadPartRequest, UploadPartResult> oSSCompletedCallback) {
        return this.mOss.asyncUploadPart(uploadPartRequest, oSSCompletedCallback);
    }

    @Override
    public CompleteMultipartUploadResult completeMultipartUpload(CompleteMultipartUploadRequest completeMultipartUploadRequest) throws ServiceException, ClientException {
        return this.mOss.completeMultipartUpload(completeMultipartUploadRequest);
    }

    @Override
    public CopyObjectResult copyObject(CopyObjectRequest copyObjectRequest) throws ServiceException, ClientException {
        return this.mOss.copyObject(copyObjectRequest);
    }

    @Override
    public CreateBucketResult createBucket(CreateBucketRequest createBucketRequest) throws ServiceException, ClientException {
        return this.mOss.createBucket(createBucketRequest);
    }

    @Override
    public DeleteBucketResult deleteBucket(DeleteBucketRequest deleteBucketRequest) throws ServiceException, ClientException {
        return this.mOss.deleteBucket(deleteBucketRequest);
    }

    @Override
    public DeleteMultipleObjectResult deleteMultipleObject(DeleteMultipleObjectRequest deleteMultipleObjectRequest) throws ServiceException, ClientException {
        return this.mOss.deleteMultipleObject(deleteMultipleObjectRequest);
    }

    @Override
    public DeleteObjectResult deleteObject(DeleteObjectRequest deleteObjectRequest) throws ServiceException, ClientException {
        return this.mOss.deleteObject(deleteObjectRequest);
    }

    @Override
    public boolean doesObjectExist(String str, String str2) throws ServiceException, ClientException {
        return this.mOss.doesObjectExist(str, str2);
    }

    @Override
    public GetBucketACLResult getBucketACL(GetBucketACLRequest getBucketACLRequest) throws ServiceException, ClientException {
        return this.mOss.getBucketACL(getBucketACLRequest);
    }

    @Override
    public GetBucketInfoResult getBucketInfo(GetBucketInfoRequest getBucketInfoRequest) throws ServiceException, ClientException {
        return this.mOss.getBucketInfo(getBucketInfoRequest);
    }

    @Override
    public GetObjectResult getObject(GetObjectRequest getObjectRequest) throws ServiceException, ClientException {
        return this.mOss.getObject(getObjectRequest);
    }

    @Override
    public GetObjectACLResult getObjectACL(GetObjectACLRequest getObjectACLRequest) throws ServiceException, ClientException {
        return this.mOss.getObjectACL(getObjectACLRequest);
    }

    @Override
    public GetSymlinkResult getSymlink(GetSymlinkRequest getSymlinkRequest) throws ServiceException, ClientException {
        return this.mOss.getSymlink(getSymlinkRequest);
    }

    @Override
    public HeadObjectResult headObject(HeadObjectRequest headObjectRequest) throws ServiceException, ClientException {
        return this.mOss.headObject(headObjectRequest);
    }

    @Override
    public ImagePersistResult imagePersist(ImagePersistRequest imagePersistRequest) throws ServiceException, ClientException {
        return this.mOss.imagePersist(imagePersistRequest);
    }

    @Override
    public InitiateMultipartUploadResult initMultipartUpload(InitiateMultipartUploadRequest initiateMultipartUploadRequest) throws ServiceException, ClientException {
        return this.mOss.initMultipartUpload(initiateMultipartUploadRequest);
    }

    @Override
    public ListBucketsResult listBuckets(ListBucketsRequest listBucketsRequest) throws ServiceException, ClientException {
        return this.mOss.listBuckets(listBucketsRequest);
    }

    @Override
    public ListMultipartUploadsResult listMultipartUploads(ListMultipartUploadsRequest listMultipartUploadsRequest) throws ServiceException, ClientException {
        return this.mOss.listMultipartUploads(listMultipartUploadsRequest);
    }

    @Override
    public ListObjectsResult listObjects(ListObjectsRequest listObjectsRequest) throws ServiceException, ClientException {
        return this.mOss.listObjects(listObjectsRequest);
    }

    @Override
    public ListPartsResult listParts(ListPartsRequest listPartsRequest) throws ServiceException, ClientException {
        return this.mOss.listParts(listPartsRequest);
    }

    @Override
    public CompleteMultipartUploadResult multipartUpload(MultipartUploadRequest multipartUploadRequest) throws ServiceException, ClientException {
        return this.mOss.multipartUpload(multipartUploadRequest);
    }

    @Override
    public String presignConstrainedObjectURL(GeneratePresignedUrlRequest generatePresignedUrlRequest) throws ClientException {
        return this.mOss.presignConstrainedObjectURL(generatePresignedUrlRequest);
    }

    @Override
    public String presignConstrainedObjectURL(String str, String str2, long j) throws ClientException {
        return this.mOss.presignConstrainedObjectURL(str, str2, j);
    }

    @Override
    public String presignPublicObjectURL(String str, String str2) {
        return this.mOss.presignPublicObjectURL(str, str2);
    }

    @Override
    public PutObjectResult putObject(PutObjectRequest putObjectRequest) throws ServiceException, ClientException {
        return this.mOss.putObject(putObjectRequest);
    }

    @Override
    public PutSymlinkResult putSymlink(PutSymlinkRequest putSymlinkRequest) throws ServiceException, ClientException {
        return this.mOss.putSymlink(putSymlinkRequest);
    }

    @Override
    public RestoreObjectResult restoreObject(RestoreObjectRequest restoreObjectRequest) throws ServiceException, ClientException {
        return this.mOss.restoreObject(restoreObjectRequest);
    }

    @Override
    public ResumableUploadResult resumableUpload(ResumableUploadRequest resumableUploadRequest) throws ServiceException, ClientException {
        return this.mOss.resumableUpload(resumableUploadRequest);
    }

    @Override
    public ResumableUploadResult sequenceUpload(ResumableUploadRequest resumableUploadRequest) throws ServiceException, ClientException {
        return this.mOss.sequenceUpload(resumableUploadRequest);
    }

    @Override
    public TriggerCallbackResult triggerCallback(TriggerCallbackRequest triggerCallbackRequest) throws ServiceException, ClientException {
        return this.mOss.triggerCallback(triggerCallbackRequest);
    }

    @Override
    public void updateCredentialProvider(OSSCredentialProvider oSSCredentialProvider) {
        this.mOss.updateCredentialProvider(oSSCredentialProvider);
    }

    @Override
    public UploadPartResult uploadPart(UploadPartRequest uploadPartRequest) throws ServiceException, ClientException {
        return this.mOss.uploadPart(uploadPartRequest);
    }
}
