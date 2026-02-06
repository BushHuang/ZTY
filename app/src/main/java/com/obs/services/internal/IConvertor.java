package com.obs.services.internal;

import com.obs.services.model.AccessControlList;
import com.obs.services.model.BucketCors;
import com.obs.services.model.BucketDirectColdAccess;
import com.obs.services.model.BucketEncryption;
import com.obs.services.model.BucketLoggingConfiguration;
import com.obs.services.model.BucketNotificationConfiguration;
import com.obs.services.model.BucketQuota;
import com.obs.services.model.BucketStoragePolicyConfiguration;
import com.obs.services.model.BucketTagInfo;
import com.obs.services.model.EventTypeEnum;
import com.obs.services.model.GroupGranteeEnum;
import com.obs.services.model.KeyAndVersion;
import com.obs.services.model.LifecycleConfiguration;
import com.obs.services.model.PartEtag;
import com.obs.services.model.ReplicationConfiguration;
import com.obs.services.model.RestoreObjectRequest;
import com.obs.services.model.StorageClassEnum;
import com.obs.services.model.WebsiteConfiguration;
import com.obs.services.model.fs.FSStatusEnum;
import java.util.List;

public interface IConvertor {
    String transAccessControlList(AccessControlList accessControlList, boolean z) throws ServiceException;

    String transBucketCors(BucketCors bucketCors) throws ServiceException;

    String transBucketDirectColdAccess(BucketDirectColdAccess bucketDirectColdAccess) throws ServiceException;

    String transBucketEcryption(BucketEncryption bucketEncryption) throws ServiceException;

    String transBucketFileInterface(FSStatusEnum fSStatusEnum) throws ServiceException;

    String transBucketLoction(String str) throws ServiceException;

    String transBucketLoggingConfiguration(BucketLoggingConfiguration bucketLoggingConfiguration) throws ServiceException;

    String transBucketNotificationConfiguration(BucketNotificationConfiguration bucketNotificationConfiguration) throws ServiceException;

    String transBucketQuota(BucketQuota bucketQuota) throws ServiceException;

    String transBucketTagInfo(BucketTagInfo bucketTagInfo) throws ServiceException;

    AccessControlList transCannedAcl(String str);

    String transCompleteMultipartUpload(List<PartEtag> list) throws ServiceException;

    String transEventType(EventTypeEnum eventTypeEnum);

    String transGroupGrantee(GroupGranteeEnum groupGranteeEnum);

    String transKeyAndVersion(KeyAndVersion[] keyAndVersionArr, boolean z) throws ServiceException;

    String transLifecycleConfiguration(LifecycleConfiguration lifecycleConfiguration) throws ServiceException;

    String transReplicationConfiguration(ReplicationConfiguration replicationConfiguration) throws ServiceException;

    String transRestoreObjectRequest(RestoreObjectRequest restoreObjectRequest) throws ServiceException;

    String transStorageClass(StorageClassEnum storageClassEnum);

    String transStoragePolicy(BucketStoragePolicyConfiguration bucketStoragePolicyConfiguration) throws ServiceException;

    String transVersioningConfiguration(String str, String str2) throws ServiceException;

    String transWebsiteConfiguration(WebsiteConfiguration websiteConfiguration) throws ServiceException;
}
