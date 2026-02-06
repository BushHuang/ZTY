package com.obs.services;

import com.obs.services.exception.ObsException;
import com.obs.services.internal.security.ProviderCredentialThreadContext;
import com.obs.services.internal.security.ProviderCredentials;
import com.obs.services.model.AbortMultipartUploadRequest;
import com.obs.services.model.AccessControlList;
import com.obs.services.model.BucketCors;
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
import com.obs.services.model.DeleteObjectsRequest;
import com.obs.services.model.DeleteObjectsResult;
import com.obs.services.model.GetObjectMetadataRequest;
import com.obs.services.model.GetObjectRequest;
import com.obs.services.model.HeaderResponse;
import com.obs.services.model.InitiateMultipartUploadRequest;
import com.obs.services.model.InitiateMultipartUploadResult;
import com.obs.services.model.LifecycleConfiguration;
import com.obs.services.model.ListBucketsRequest;
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
import com.obs.services.model.PutObjectRequest;
import com.obs.services.model.PutObjectResult;
import com.obs.services.model.ReplicationConfiguration;
import com.obs.services.model.RestoreObjectRequest;
import com.obs.services.model.UploadPartRequest;
import com.obs.services.model.UploadPartResult;
import com.obs.services.model.WebsiteConfiguration;
import java.util.List;

public class SecretFlexibleObsClient extends ObsClient {
    public SecretFlexibleObsClient(ObsConfiguration obsConfiguration) {
        this((String) null, (String) null, obsConfiguration);
    }

    public SecretFlexibleObsClient(String str) {
        this((String) null, (String) null, str);
    }

    public SecretFlexibleObsClient(String str, String str2, ObsConfiguration obsConfiguration) {
        super(str, str2, obsConfiguration);
    }

    public SecretFlexibleObsClient(String str, String str2, String str3) {
        super(str, str2, str3);
    }

    public SecretFlexibleObsClient(String str, String str2, String str3, ObsConfiguration obsConfiguration) {
        super(str, str2, str3, obsConfiguration);
    }

    public SecretFlexibleObsClient(String str, String str2, String str3, String str4) {
        super(str, str2, str3, str4);
    }

    private void clearContextProviderCredentials() {
        ProviderCredentialThreadContext.getInstance().clearProviderCredentials();
    }

    private void setContextProviderCredentials(String str, String str2) {
        setContextProviderCredentials(str, str2, null);
    }

    private void setContextProviderCredentials(String str, String str2, String str3) {
        ProviderCredentials providerCredentials = new ProviderCredentials(str, str2, str3);
        providerCredentials.setAuthType(getProviderCredentials().getAuthType());
        ProviderCredentialThreadContext.getInstance().setProviderCredentials(providerCredentials);
    }

    public HeaderResponse abortMultipartUpload(AbortMultipartUploadRequest abortMultipartUploadRequest, String str, String str2) throws ObsException {
        setContextProviderCredentials(str, str2);
        try {
            return super.abortMultipartUpload(abortMultipartUploadRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse abortMultipartUpload(AbortMultipartUploadRequest abortMultipartUploadRequest, String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str, str2, str3);
        try {
            return super.abortMultipartUpload(abortMultipartUploadRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public CompleteMultipartUploadResult completeMultipartUpload(CompleteMultipartUploadRequest completeMultipartUploadRequest, String str, String str2) throws ObsException {
        setContextProviderCredentials(str, str2);
        try {
            return super.completeMultipartUpload(completeMultipartUploadRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public CompleteMultipartUploadResult completeMultipartUpload(CompleteMultipartUploadRequest completeMultipartUploadRequest, String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str, str2, str3);
        try {
            return super.completeMultipartUpload(completeMultipartUploadRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public CopyObjectResult copyObject(CopyObjectRequest copyObjectRequest, String str, String str2) throws ObsException {
        setContextProviderCredentials(str, str2);
        try {
            return super.copyObject(copyObjectRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public CopyObjectResult copyObject(CopyObjectRequest copyObjectRequest, String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str, str2, str3);
        try {
            return super.copyObject(copyObjectRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public CopyPartResult copyPart(CopyPartRequest copyPartRequest, String str, String str2) throws ObsException {
        setContextProviderCredentials(str, str2);
        try {
            return super.copyPart(copyPartRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public CopyPartResult copyPart(CopyPartRequest copyPartRequest, String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str, str2, str3);
        try {
            return super.copyPart(copyPartRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public ObsBucket createBucket(ObsBucket obsBucket, String str, String str2) throws ObsException {
        setContextProviderCredentials(str, str2);
        try {
            return super.createBucket(obsBucket);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public ObsBucket createBucket(ObsBucket obsBucket, String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str, str2, str3);
        try {
            return super.createBucket(obsBucket);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse deleteBucket(String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.deleteBucket(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse deleteBucket(String str, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.deleteBucket(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse deleteBucketCors(String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.deleteBucketCors(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse deleteBucketCors(String str, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.deleteBucketCors(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse deleteBucketLifecycle(String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.deleteBucketLifecycle(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse deleteBucketLifecycle(String str, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.deleteBucketLifecycle(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    @Deprecated
    public HeaderResponse deleteBucketLifecycleConfiguration(String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.deleteBucketLifecycleConfiguration(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    @Deprecated
    public HeaderResponse deleteBucketLifecycleConfiguration(String str, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.deleteBucketLifecycleConfiguration(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse deleteBucketPolicy(String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.deleteBucketPolicy(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse deleteBucketPolicy(String str, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.deleteBucketPolicy(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse deleteBucketReplication(String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.deleteBucketReplication(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse deleteBucketReplication(String str, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.deleteBucketReplication(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    @Deprecated
    public HeaderResponse deleteBucketReplicationConfiguration(String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.deleteBucketReplicationConfiguration(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    @Deprecated
    public HeaderResponse deleteBucketReplicationConfiguration(String str, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.deleteBucketReplicationConfiguration(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse deleteBucketTagging(String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.deleteBucketTagging(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse deleteBucketTagging(String str, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.deleteBucketTagging(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse deleteBucketWebsite(String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.deleteBucketWebsite(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse deleteBucketWebsite(String str, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.deleteBucketWebsite(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    @Deprecated
    public HeaderResponse deleteBucketWebsiteConfiguration(String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.deleteBucketWebsiteConfiguration(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    @Deprecated
    public HeaderResponse deleteBucketWebsiteConfiguration(String str, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.deleteBucketWebsiteConfiguration(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse deleteObject(String str, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str3, str4);
        try {
            return super.deleteObject(str, str2);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse deleteObject(String str, String str2, String str3, String str4, String str5) throws ObsException {
        setContextProviderCredentials(str4, str5);
        try {
            return super.deleteObject(str, str2, str3);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse deleteObject(String str, String str2, String str3, String str4, String str5, String str6) throws ObsException {
        setContextProviderCredentials(str4, str5, str6);
        try {
            return super.deleteObject(str, str2, str3);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public DeleteObjectsResult deleteObjects(DeleteObjectsRequest deleteObjectsRequest, String str, String str2) throws ObsException {
        setContextProviderCredentials(str, str2);
        try {
            return super.deleteObjects(deleteObjectsRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public DeleteObjectsResult deleteObjects(DeleteObjectsRequest deleteObjectsRequest, String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str, str2, str3);
        try {
            return super.deleteObjects(deleteObjectsRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public AccessControlList getBucketAcl(String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.getBucketAcl(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public AccessControlList getBucketAcl(String str, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.getBucketAcl(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public BucketCors getBucketCors(String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.getBucketCors(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public BucketCors getBucketCors(String str, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.getBucketCors(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public LifecycleConfiguration getBucketLifecycle(String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.getBucketLifecycle(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public LifecycleConfiguration getBucketLifecycle(String str, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.getBucketLifecycle(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    @Deprecated
    public LifecycleConfiguration getBucketLifecycleConfiguration(String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.getBucketLifecycleConfiguration(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    @Deprecated
    public LifecycleConfiguration getBucketLifecycleConfiguration(String str, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.getBucketLifecycleConfiguration(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public BucketLocationResponse getBucketLocation(String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.getBucketLocationV2(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public BucketLocationResponse getBucketLocation(String str, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.getBucketLocationV2(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public BucketLoggingConfiguration getBucketLogging(String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.getBucketLogging(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public BucketLoggingConfiguration getBucketLogging(String str, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.getBucketLogging(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    @Deprecated
    public BucketLoggingConfiguration getBucketLoggingConfiguration(String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.getBucketLoggingConfiguration(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    @Deprecated
    public BucketLoggingConfiguration getBucketLoggingConfiguration(String str, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.getBucketLoggingConfiguration(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public BucketMetadataInfoResult getBucketMetadata(BucketMetadataInfoRequest bucketMetadataInfoRequest, String str, String str2) throws ObsException {
        setContextProviderCredentials(str, str2);
        try {
            return super.getBucketMetadata(bucketMetadataInfoRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public BucketMetadataInfoResult getBucketMetadata(BucketMetadataInfoRequest bucketMetadataInfoRequest, String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str, str2, str3);
        try {
            return super.getBucketMetadata(bucketMetadataInfoRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public BucketNotificationConfiguration getBucketNotification(String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.getBucketNotification(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public BucketNotificationConfiguration getBucketNotification(String str, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.getBucketNotification(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public BucketPolicyResponse getBucketPolicy(String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.getBucketPolicyV2(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public BucketPolicyResponse getBucketPolicy(String str, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.getBucketPolicyV2(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public BucketQuota getBucketQuota(String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.getBucketQuota(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public BucketQuota getBucketQuota(String str, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.getBucketQuota(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public ReplicationConfiguration getBucketReplication(String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.getBucketReplication(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public ReplicationConfiguration getBucketReplication(String str, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.getBucketReplication(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    @Deprecated
    public ReplicationConfiguration getBucketReplicationConfiguration(String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.getBucketReplicationConfiguration(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    @Deprecated
    public ReplicationConfiguration getBucketReplicationConfiguration(String str, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.getBucketReplicationConfiguration(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public BucketStorageInfo getBucketStorageInfo(String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.getBucketStorageInfo(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public BucketStorageInfo getBucketStorageInfo(String str, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.getBucketStorageInfo(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public BucketStoragePolicyConfiguration getBucketStoragePolicy(String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.getBucketStoragePolicy(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public BucketStoragePolicyConfiguration getBucketStoragePolicy(String str, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.getBucketStoragePolicy(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public BucketTagInfo getBucketTagging(String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.getBucketTagging(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public BucketTagInfo getBucketTagging(String str, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.getBucketTagging(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public BucketVersioningConfiguration getBucketVersioning(String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.getBucketVersioning(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public BucketVersioningConfiguration getBucketVersioning(String str, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.getBucketVersioning(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public WebsiteConfiguration getBucketWebsite(String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.getBucketWebsite(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public WebsiteConfiguration getBucketWebsite(String str, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.getBucketWebsite(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    @Deprecated
    public WebsiteConfiguration getBucketWebsiteConfiguration(String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.getBucketWebsiteConfiguration(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    @Deprecated
    public WebsiteConfiguration getBucketWebsiteConfiguration(String str, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.getBucketWebsiteConfiguration(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public ObsObject getObject(GetObjectRequest getObjectRequest, String str, String str2) throws ObsException {
        setContextProviderCredentials(str, str2);
        try {
            return super.getObject(getObjectRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public ObsObject getObject(GetObjectRequest getObjectRequest, String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str, str2, str3);
        try {
            return super.getObject(getObjectRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public AccessControlList getObjectAcl(String str, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str3, str4);
        try {
            return super.getObjectAcl(str, str2);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public AccessControlList getObjectAcl(String str, String str2, String str3, String str4, String str5) throws ObsException {
        setContextProviderCredentials(str4, str5);
        try {
            return super.getObjectAcl(str, str2, str3);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public AccessControlList getObjectAcl(String str, String str2, String str3, String str4, String str5, String str6) throws ObsException {
        setContextProviderCredentials(str4, str5, str6);
        try {
            return super.getObjectAcl(str, str2, str3);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public ObjectMetadata getObjectMetadata(GetObjectMetadataRequest getObjectMetadataRequest, String str, String str2) throws ObsException {
        setContextProviderCredentials(str, str2);
        try {
            return super.getObjectMetadata(getObjectMetadataRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public ObjectMetadata getObjectMetadata(GetObjectMetadataRequest getObjectMetadataRequest, String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str, str2, str3);
        try {
            return super.getObjectMetadata(getObjectMetadataRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public boolean headBucket(String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.headBucket(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public boolean headBucket(String str, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.headBucket(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public InitiateMultipartUploadResult initiateMultipartUpload(InitiateMultipartUploadRequest initiateMultipartUploadRequest, String str, String str2) throws ObsException {
        setContextProviderCredentials(str, str2);
        try {
            return super.initiateMultipartUpload(initiateMultipartUploadRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public InitiateMultipartUploadResult initiateMultipartUpload(InitiateMultipartUploadRequest initiateMultipartUploadRequest, String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str, str2, str3);
        try {
            return super.initiateMultipartUpload(initiateMultipartUploadRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public List<ObsBucket> listBuckets(ListBucketsRequest listBucketsRequest, String str, String str2) throws ObsException {
        setContextProviderCredentials(str, str2);
        try {
            return super.listBuckets(listBucketsRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public List<ObsBucket> listBuckets(ListBucketsRequest listBucketsRequest, String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str, str2, str3);
        try {
            return super.listBuckets(listBucketsRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public List<ObsBucket> listBuckets(String str, String str2) throws ObsException {
        setContextProviderCredentials(str, str2);
        try {
            return super.listBuckets(null);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public List<ObsBucket> listBuckets(String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str, str2, str3);
        try {
            return super.listBuckets(null);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public MultipartUploadListing listMultipartUploads(ListMultipartUploadsRequest listMultipartUploadsRequest, String str, String str2) throws ObsException {
        setContextProviderCredentials(str, str2);
        try {
            return super.listMultipartUploads(listMultipartUploadsRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public MultipartUploadListing listMultipartUploads(ListMultipartUploadsRequest listMultipartUploadsRequest, String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str, str2, str3);
        try {
            return super.listMultipartUploads(listMultipartUploadsRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public ObjectListing listObjects(ListObjectsRequest listObjectsRequest, String str, String str2) throws ObsException {
        setContextProviderCredentials(str, str2);
        try {
            return super.listObjects(listObjectsRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public ObjectListing listObjects(ListObjectsRequest listObjectsRequest, String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str, str2, str3);
        try {
            return super.listObjects(listObjectsRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public ListPartsResult listParts(ListPartsRequest listPartsRequest, String str, String str2) throws ObsException {
        setContextProviderCredentials(str, str2);
        try {
            return super.listParts(listPartsRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public ListPartsResult listParts(ListPartsRequest listPartsRequest, String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str, str2, str3);
        try {
            return super.listParts(listPartsRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public ListVersionsResult listVersions(ListVersionsRequest listVersionsRequest, String str, String str2) throws ObsException {
        setContextProviderCredentials(str, str2);
        try {
            return super.listVersions(listVersionsRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public ListVersionsResult listVersions(ListVersionsRequest listVersionsRequest, String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str, str2, str3);
        try {
            return super.listVersions(listVersionsRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public ListVersionsResult listVersions(String str, long j, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.listVersions(str, j);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public ListVersionsResult listVersions(String str, long j, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.listVersions(str, j);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public ListVersionsResult listVersions(String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.listVersions(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public ListVersionsResult listVersions(String str, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.listVersions(str);
        } finally {
            clearContextProviderCredentials();
        }
    }

    @Deprecated
    public ListVersionsResult listVersions(String str, String str2, String str3, String str4, String str5, long j, String str6, String str7, String str8) throws ObsException {
        setContextProviderCredentials(str7, str8);
        try {
            return super.listVersions(str, str2, str3, str4, str5, j, str6);
        } finally {
            clearContextProviderCredentials();
        }
    }

    @Deprecated
    public ListVersionsResult listVersions(String str, String str2, String str3, String str4, String str5, long j, String str6, String str7, String str8, String str9) throws ObsException {
        setContextProviderCredentials(str7, str8, str9);
        try {
            return super.listVersions(str, str2, str3, str4, str5, j, str6);
        } finally {
            clearContextProviderCredentials();
        }
    }

    @Deprecated
    public OptionsInfoResult optionsBucket(String str, OptionsInfoRequest optionsInfoRequest, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.optionsBucket(str, optionsInfoRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    @Deprecated
    public OptionsInfoResult optionsBucket(String str, OptionsInfoRequest optionsInfoRequest, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.optionsBucket(str, optionsInfoRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    @Deprecated
    public OptionsInfoResult optionsObject(String str, String str2, OptionsInfoRequest optionsInfoRequest, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str3, str4);
        try {
            return super.optionsObject(str, str2, optionsInfoRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    @Deprecated
    public OptionsInfoResult optionsObject(String str, String str2, OptionsInfoRequest optionsInfoRequest, String str3, String str4, String str5) throws ObsException {
        setContextProviderCredentials(str3, str4, str5);
        try {
            return super.optionsObject(str, str2, optionsInfoRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public PutObjectResult putObject(PutObjectRequest putObjectRequest, String str, String str2) throws ObsException {
        setContextProviderCredentials(str, str2);
        try {
            return super.putObject(putObjectRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public PutObjectResult putObject(PutObjectRequest putObjectRequest, String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str, str2, str3);
        try {
            return super.putObject(putObjectRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public RestoreObjectRequest.RestoreObjectStatus restoreObject(RestoreObjectRequest restoreObjectRequest, String str, String str2) throws ObsException {
        setContextProviderCredentials(str, str2);
        try {
            return super.restoreObject(restoreObjectRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public RestoreObjectRequest.RestoreObjectStatus restoreObject(RestoreObjectRequest restoreObjectRequest, String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str, str2, str3);
        try {
            return super.restoreObject(restoreObjectRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse setBucketAcl(String str, AccessControlList accessControlList, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.setBucketAcl(str, accessControlList);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse setBucketAcl(String str, AccessControlList accessControlList, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.setBucketAcl(str, accessControlList);
        } finally {
            clearContextProviderCredentials();
        }
    }

    @Deprecated
    public HeaderResponse setBucketAcl(String str, String str2, AccessControlList accessControlList, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str3, str4);
        try {
            return super.setBucketAcl(str, str2, accessControlList);
        } finally {
            clearContextProviderCredentials();
        }
    }

    @Deprecated
    public HeaderResponse setBucketAcl(String str, String str2, AccessControlList accessControlList, String str3, String str4, String str5) throws ObsException {
        setContextProviderCredentials(str3, str4, str5);
        try {
            return super.setBucketAcl(str, str2, accessControlList);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse setBucketCors(String str, BucketCors bucketCors, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.setBucketCors(str, bucketCors);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse setBucketCors(String str, BucketCors bucketCors, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.setBucketCors(str, bucketCors);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse setBucketLifecycle(String str, LifecycleConfiguration lifecycleConfiguration, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.setBucketLifecycle(str, lifecycleConfiguration);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse setBucketLifecycle(String str, LifecycleConfiguration lifecycleConfiguration, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.setBucketLifecycle(str, lifecycleConfiguration);
        } finally {
            clearContextProviderCredentials();
        }
    }

    @Deprecated
    public HeaderResponse setBucketLifecycleConfiguration(String str, LifecycleConfiguration lifecycleConfiguration, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.setBucketLifecycleConfiguration(str, lifecycleConfiguration);
        } finally {
            clearContextProviderCredentials();
        }
    }

    @Deprecated
    public HeaderResponse setBucketLifecycleConfiguration(String str, LifecycleConfiguration lifecycleConfiguration, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.setBucketLifecycleConfiguration(str, lifecycleConfiguration);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse setBucketLogging(String str, BucketLoggingConfiguration bucketLoggingConfiguration, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.setBucketLogging(str, bucketLoggingConfiguration);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse setBucketLogging(String str, BucketLoggingConfiguration bucketLoggingConfiguration, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.setBucketLogging(str, bucketLoggingConfiguration);
        } finally {
            clearContextProviderCredentials();
        }
    }

    @Deprecated
    public HeaderResponse setBucketLoggingConfiguration(String str, BucketLoggingConfiguration bucketLoggingConfiguration, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.setBucketLoggingConfiguration(str, bucketLoggingConfiguration);
        } finally {
            clearContextProviderCredentials();
        }
    }

    @Deprecated
    public HeaderResponse setBucketLoggingConfiguration(String str, BucketLoggingConfiguration bucketLoggingConfiguration, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.setBucketLoggingConfiguration(str, bucketLoggingConfiguration);
        } finally {
            clearContextProviderCredentials();
        }
    }

    @Deprecated
    public HeaderResponse setBucketLoggingConfiguration(String str, BucketLoggingConfiguration bucketLoggingConfiguration, boolean z, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.setBucketLoggingConfiguration(str, bucketLoggingConfiguration, z);
        } finally {
            clearContextProviderCredentials();
        }
    }

    @Deprecated
    public HeaderResponse setBucketLoggingConfiguration(String str, BucketLoggingConfiguration bucketLoggingConfiguration, boolean z, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.setBucketLoggingConfiguration(str, bucketLoggingConfiguration, z);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse setBucketNotification(String str, BucketNotificationConfiguration bucketNotificationConfiguration, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.setBucketNotification(str, bucketNotificationConfiguration);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse setBucketNotification(String str, BucketNotificationConfiguration bucketNotificationConfiguration, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.setBucketNotification(str, bucketNotificationConfiguration);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse setBucketPolicy(String str, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str3, str4);
        try {
            return super.setBucketPolicy(str, str2);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse setBucketPolicy(String str, String str2, String str3, String str4, String str5) throws ObsException {
        setContextProviderCredentials(str3, str4, str5);
        try {
            return super.setBucketPolicy(str, str2);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse setBucketQuota(String str, BucketQuota bucketQuota, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.setBucketQuota(str, bucketQuota);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse setBucketQuota(String str, BucketQuota bucketQuota, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.setBucketQuota(str, bucketQuota);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse setBucketReplication(String str, ReplicationConfiguration replicationConfiguration, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.setBucketReplication(str, replicationConfiguration);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse setBucketReplication(String str, ReplicationConfiguration replicationConfiguration, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.setBucketReplication(str, replicationConfiguration);
        } finally {
            clearContextProviderCredentials();
        }
    }

    @Deprecated
    public HeaderResponse setBucketReplicationConfiguration(String str, ReplicationConfiguration replicationConfiguration, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.setBucketReplicationConfiguration(str, replicationConfiguration);
        } finally {
            clearContextProviderCredentials();
        }
    }

    @Deprecated
    public HeaderResponse setBucketReplicationConfiguration(String str, ReplicationConfiguration replicationConfiguration, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.setBucketReplicationConfiguration(str, replicationConfiguration);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse setBucketStoragePolicy(String str, BucketStoragePolicyConfiguration bucketStoragePolicyConfiguration, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.setBucketStoragePolicy(str, bucketStoragePolicyConfiguration);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse setBucketStoragePolicy(String str, BucketStoragePolicyConfiguration bucketStoragePolicyConfiguration, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.setBucketStoragePolicy(str, bucketStoragePolicyConfiguration);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse setBucketTagging(String str, BucketTagInfo bucketTagInfo, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.setBucketTagging(str, bucketTagInfo);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse setBucketTagging(String str, BucketTagInfo bucketTagInfo, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.setBucketTagging(str, bucketTagInfo);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse setBucketVersioning(String str, BucketVersioningConfiguration bucketVersioningConfiguration, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.setBucketVersioning(str, bucketVersioningConfiguration);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse setBucketVersioning(String str, BucketVersioningConfiguration bucketVersioningConfiguration, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.setBucketVersioning(str, bucketVersioningConfiguration);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse setBucketWebsite(String str, WebsiteConfiguration websiteConfiguration, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.setBucketWebsite(str, websiteConfiguration);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse setBucketWebsite(String str, WebsiteConfiguration websiteConfiguration, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.setBucketWebsite(str, websiteConfiguration);
        } finally {
            clearContextProviderCredentials();
        }
    }

    @Deprecated
    public HeaderResponse setBucketWebsiteConfiguration(String str, WebsiteConfiguration websiteConfiguration, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str2, str3);
        try {
            return super.setBucketWebsiteConfiguration(str, websiteConfiguration);
        } finally {
            clearContextProviderCredentials();
        }
    }

    @Deprecated
    public HeaderResponse setBucketWebsiteConfiguration(String str, WebsiteConfiguration websiteConfiguration, String str2, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str2, str3, str4);
        try {
            return super.setBucketWebsiteConfiguration(str, websiteConfiguration);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse setObjectAcl(String str, String str2, AccessControlList accessControlList, String str3, String str4) throws ObsException {
        setContextProviderCredentials(str3, str4);
        try {
            return super.setObjectAcl(str, str2, accessControlList);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse setObjectAcl(String str, String str2, AccessControlList accessControlList, String str3, String str4, String str5) throws ObsException {
        setContextProviderCredentials(str4, str5);
        try {
            return super.setObjectAcl(str, str2, accessControlList, str3);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public HeaderResponse setObjectAcl(String str, String str2, AccessControlList accessControlList, String str3, String str4, String str5, String str6) throws ObsException {
        setContextProviderCredentials(str4, str5, str6);
        try {
            return super.setObjectAcl(str, str2, accessControlList, str3);
        } finally {
            clearContextProviderCredentials();
        }
    }

    @Deprecated
    public HeaderResponse setObjectAcl(String str, String str2, String str3, AccessControlList accessControlList, String str4, String str5, String str6) throws ObsException {
        setContextProviderCredentials(str5, str6);
        try {
            return super.setObjectAcl(str, str2, str3, accessControlList, str4);
        } finally {
            clearContextProviderCredentials();
        }
    }

    @Deprecated
    public HeaderResponse setObjectAcl(String str, String str2, String str3, AccessControlList accessControlList, String str4, String str5, String str6, String str7) throws ObsException {
        setContextProviderCredentials(str5, str6, str7);
        try {
            return super.setObjectAcl(str, str2, str3, accessControlList, str4);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public UploadPartResult uploadPart(UploadPartRequest uploadPartRequest, String str, String str2) throws ObsException {
        setContextProviderCredentials(str, str2);
        try {
            return super.uploadPart(uploadPartRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }

    public UploadPartResult uploadPart(UploadPartRequest uploadPartRequest, String str, String str2, String str3) throws ObsException {
        setContextProviderCredentials(str, str2, str3);
        try {
            return super.uploadPart(uploadPartRequest);
        } finally {
            clearContextProviderCredentials();
        }
    }
}
