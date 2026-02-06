package com.obs.services;

import com.obs.services.exception.ObsException;
import com.obs.services.model.AbortMultipartUploadRequest;
import com.obs.services.model.AccessControlList;
import com.obs.services.model.AppendObjectRequest;
import com.obs.services.model.AppendObjectResult;
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
import com.obs.services.model.GetObjectMetadataRequest;
import com.obs.services.model.GetObjectRequest;
import com.obs.services.model.HeaderResponse;
import com.obs.services.model.InitiateMultipartUploadRequest;
import com.obs.services.model.InitiateMultipartUploadResult;
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
import com.obs.services.model.PostSignatureRequest;
import com.obs.services.model.PostSignatureResponse;
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
import com.obs.services.model.SetObjectMetadataRequest;
import com.obs.services.model.TaskProgressStatus;
import com.obs.services.model.TemporarySignatureRequest;
import com.obs.services.model.TemporarySignatureResponse;
import com.obs.services.model.UploadFileRequest;
import com.obs.services.model.UploadPartRequest;
import com.obs.services.model.UploadPartResult;
import com.obs.services.model.UploadProgressStatus;
import com.obs.services.model.WebsiteConfiguration;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface IObsClient {
    HeaderResponse abortMultipartUpload(AbortMultipartUploadRequest abortMultipartUploadRequest) throws ObsException;

    AppendObjectResult appendObject(AppendObjectRequest appendObjectRequest) throws ObsException;

    void close() throws IOException;

    CompleteMultipartUploadResult completeMultipartUpload(CompleteMultipartUploadRequest completeMultipartUploadRequest) throws ObsException;

    CopyObjectResult copyObject(CopyObjectRequest copyObjectRequest) throws ObsException;

    CopyObjectResult copyObject(String str, String str2, String str3, String str4) throws ObsException;

    CopyPartResult copyPart(CopyPartRequest copyPartRequest) throws ObsException;

    ObsBucket createBucket(CreateBucketRequest createBucketRequest) throws ObsException;

    ObsBucket createBucket(ObsBucket obsBucket) throws ObsException;

    ObsBucket createBucket(String str) throws ObsException;

    ObsBucket createBucket(String str, String str2) throws ObsException;

    PostSignatureResponse createPostSignature(PostSignatureRequest postSignatureRequest) throws ObsException;

    TemporarySignatureResponse createTemporarySignature(TemporarySignatureRequest temporarySignatureRequest);

    HeaderResponse deleteBucket(String str) throws ObsException;

    HeaderResponse deleteBucketCors(String str) throws ObsException;

    HeaderResponse deleteBucketDirectColdAccess(String str) throws ObsException;

    HeaderResponse deleteBucketEncryption(String str) throws ObsException;

    HeaderResponse deleteBucketLifecycle(String str) throws ObsException;

    HeaderResponse deleteBucketPolicy(String str) throws ObsException;

    HeaderResponse deleteBucketReplication(String str) throws ObsException;

    HeaderResponse deleteBucketTagging(String str) throws ObsException;

    HeaderResponse deleteBucketWebsite(String str) throws ObsException;

    DeleteObjectResult deleteObject(String str, String str2) throws ObsException;

    DeleteObjectResult deleteObject(String str, String str2, String str3) throws ObsException;

    DeleteObjectsResult deleteObjects(DeleteObjectsRequest deleteObjectsRequest) throws ObsException;

    ReadAheadResult deleteReadAheadObjects(String str, String str2) throws ObsException;

    DownloadFileResult downloadFile(DownloadFileRequest downloadFileRequest) throws ObsException;

    AccessControlList getBucketAcl(String str) throws ObsException;

    BucketCors getBucketCors(String str) throws ObsException;

    BucketDirectColdAccess getBucketDirectColdAccess(String str) throws ObsException;

    BucketEncryption getBucketEncryption(String str) throws ObsException;

    LifecycleConfiguration getBucketLifecycle(String str) throws ObsException;

    String getBucketLocation(String str) throws ObsException;

    BucketLocationResponse getBucketLocationV2(String str) throws ObsException;

    BucketLoggingConfiguration getBucketLogging(String str) throws ObsException;

    BucketMetadataInfoResult getBucketMetadata(BucketMetadataInfoRequest bucketMetadataInfoRequest) throws ObsException;

    BucketNotificationConfiguration getBucketNotification(String str) throws ObsException;

    String getBucketPolicy(String str) throws ObsException;

    BucketPolicyResponse getBucketPolicyV2(String str) throws ObsException;

    BucketQuota getBucketQuota(String str) throws ObsException;

    ReplicationConfiguration getBucketReplication(String str) throws ObsException;

    BucketStorageInfo getBucketStorageInfo(String str) throws ObsException;

    BucketStoragePolicyConfiguration getBucketStoragePolicy(String str) throws ObsException;

    BucketTagInfo getBucketTagging(String str) throws ObsException;

    BucketVersioningConfiguration getBucketVersioning(String str) throws ObsException;

    WebsiteConfiguration getBucketWebsite(String str) throws ObsException;

    ObsObject getObject(GetObjectRequest getObjectRequest) throws ObsException;

    ObsObject getObject(String str, String str2) throws ObsException;

    ObsObject getObject(String str, String str2, String str3) throws ObsException;

    AccessControlList getObjectAcl(String str, String str2) throws ObsException;

    AccessControlList getObjectAcl(String str, String str2, String str3) throws ObsException;

    ObjectMetadata getObjectMetadata(GetObjectMetadataRequest getObjectMetadataRequest) throws ObsException;

    ObjectMetadata getObjectMetadata(String str, String str2) throws ObsException;

    ObjectMetadata getObjectMetadata(String str, String str2, String str3) throws ObsException;

    boolean headBucket(String str) throws ObsException;

    InitiateMultipartUploadResult initiateMultipartUpload(InitiateMultipartUploadRequest initiateMultipartUploadRequest) throws ObsException;

    List<ObsBucket> listBuckets(ListBucketsRequest listBucketsRequest) throws ObsException;

    ListBucketsResult listBucketsV2(ListBucketsRequest listBucketsRequest) throws ObsException;

    MultipartUploadListing listMultipartUploads(ListMultipartUploadsRequest listMultipartUploadsRequest) throws ObsException;

    ObjectListing listObjects(ListObjectsRequest listObjectsRequest) throws ObsException;

    ObjectListing listObjects(String str) throws ObsException;

    ListPartsResult listParts(ListPartsRequest listPartsRequest) throws ObsException;

    ListVersionsResult listVersions(ListVersionsRequest listVersionsRequest) throws ObsException;

    ListVersionsResult listVersions(String str) throws ObsException;

    ListVersionsResult listVersions(String str, long j) throws ObsException;

    ListVersionsResult listVersions(String str, String str2, String str3, String str4, String str5, long j) throws ObsException;

    PutObjectResult putObject(PutObjectRequest putObjectRequest) throws ObsException;

    PutObjectResult putObject(String str, String str2, File file) throws ObsException;

    PutObjectResult putObject(String str, String str2, File file, ObjectMetadata objectMetadata) throws ObsException;

    PutObjectResult putObject(String str, String str2, InputStream inputStream) throws ObsException;

    PutObjectResult putObject(String str, String str2, InputStream inputStream, ObjectMetadata objectMetadata) throws ObsException;

    UploadProgressStatus putObjects(PutObjectsRequest putObjectsRequest) throws ObsException;

    ReadAheadQueryResult queryReadAheadObjectsTask(String str, String str2) throws ObsException;

    ReadAheadResult readAheadObjects(ReadAheadRequest readAheadRequest) throws ObsException;

    void refresh(String str, String str2, String str3);

    @Deprecated
    RestoreObjectRequest.RestoreObjectStatus restoreObject(RestoreObjectRequest restoreObjectRequest) throws ObsException;

    RestoreObjectResult restoreObjectV2(RestoreObjectRequest restoreObjectRequest) throws ObsException;

    TaskProgressStatus restoreObjects(RestoreObjectsRequest restoreObjectsRequest) throws ObsException;

    HeaderResponse setBucketAcl(String str, AccessControlList accessControlList) throws ObsException;

    HeaderResponse setBucketCors(String str, BucketCors bucketCors) throws ObsException;

    HeaderResponse setBucketDirectColdAccess(String str, BucketDirectColdAccess bucketDirectColdAccess) throws ObsException;

    HeaderResponse setBucketEncryption(String str, BucketEncryption bucketEncryption) throws ObsException;

    HeaderResponse setBucketLifecycle(String str, LifecycleConfiguration lifecycleConfiguration) throws ObsException;

    HeaderResponse setBucketLogging(String str, BucketLoggingConfiguration bucketLoggingConfiguration) throws ObsException;

    HeaderResponse setBucketLoggingConfiguration(String str, BucketLoggingConfiguration bucketLoggingConfiguration, boolean z) throws ObsException;

    HeaderResponse setBucketNotification(String str, BucketNotificationConfiguration bucketNotificationConfiguration) throws ObsException;

    HeaderResponse setBucketPolicy(String str, String str2) throws ObsException;

    HeaderResponse setBucketQuota(String str, BucketQuota bucketQuota) throws ObsException;

    HeaderResponse setBucketReplication(String str, ReplicationConfiguration replicationConfiguration) throws ObsException;

    HeaderResponse setBucketStoragePolicy(String str, BucketStoragePolicyConfiguration bucketStoragePolicyConfiguration) throws ObsException;

    HeaderResponse setBucketTagging(String str, BucketTagInfo bucketTagInfo) throws ObsException;

    HeaderResponse setBucketVersioning(String str, BucketVersioningConfiguration bucketVersioningConfiguration) throws ObsException;

    HeaderResponse setBucketWebsite(String str, WebsiteConfiguration websiteConfiguration) throws ObsException;

    HeaderResponse setObjectAcl(String str, String str2, AccessControlList accessControlList) throws ObsException;

    HeaderResponse setObjectAcl(String str, String str2, AccessControlList accessControlList, String str3) throws ObsException;

    ObjectMetadata setObjectMetadata(SetObjectMetadataRequest setObjectMetadataRequest) throws ObsException;

    CompleteMultipartUploadResult uploadFile(UploadFileRequest uploadFileRequest) throws ObsException;

    UploadPartResult uploadPart(UploadPartRequest uploadPartRequest) throws ObsException;

    UploadPartResult uploadPart(String str, String str2, String str3, int i, File file) throws ObsException;

    UploadPartResult uploadPart(String str, String str2, String str3, int i, InputStream inputStream) throws ObsException;
}
