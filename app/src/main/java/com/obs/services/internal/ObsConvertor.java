package com.obs.services.internal;

import com.jamesmurty.utils.BaseXMLBuilder;
import com.jamesmurty.utils.XMLBuilder;
import com.obs.services.internal.utils.ServiceUtils;
import com.obs.services.model.AccessControlList;
import com.obs.services.model.BucketEncryption;
import com.obs.services.model.BucketLoggingConfiguration;
import com.obs.services.model.BucketNotificationConfiguration;
import com.obs.services.model.BucketStoragePolicyConfiguration;
import com.obs.services.model.CanonicalGrantee;
import com.obs.services.model.EventTypeEnum;
import com.obs.services.model.FunctionGraphConfiguration;
import com.obs.services.model.GrantAndPermission;
import com.obs.services.model.GranteeInterface;
import com.obs.services.model.GroupGrantee;
import com.obs.services.model.GroupGranteeEnum;
import com.obs.services.model.Owner;
import com.obs.services.model.Permission;
import com.obs.services.model.ReplicationConfiguration;
import com.obs.services.model.RestoreObjectRequest;
import com.obs.services.model.SSEAlgorithmEnum;
import com.obs.services.model.StorageClassEnum;
import com.obs.services.model.TopicConfiguration;
import java.util.Iterator;
import javax.xml.parsers.FactoryConfigurationError;

public class ObsConvertor extends V2Convertor {
    private static ObsConvertor instance = new ObsConvertor();

    static class AnonymousClass1 {
        static final int[] $SwitchMap$com$obs$services$model$EventTypeEnum;
        static final int[] $SwitchMap$com$obs$services$model$GroupGranteeEnum;
        static final int[] $SwitchMap$com$obs$services$model$StorageClassEnum;

        static {
            int[] iArr = new int[GroupGranteeEnum.values().length];
            $SwitchMap$com$obs$services$model$GroupGranteeEnum = iArr;
            try {
                iArr[GroupGranteeEnum.ALL_USERS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            int[] iArr2 = new int[StorageClassEnum.values().length];
            $SwitchMap$com$obs$services$model$StorageClassEnum = iArr2;
            try {
                iArr2[StorageClassEnum.STANDARD.ordinal()] = 1;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$obs$services$model$StorageClassEnum[StorageClassEnum.WARM.ordinal()] = 2;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$obs$services$model$StorageClassEnum[StorageClassEnum.COLD.ordinal()] = 3;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr3 = new int[EventTypeEnum.values().length];
            $SwitchMap$com$obs$services$model$EventTypeEnum = iArr3;
            try {
                iArr3[EventTypeEnum.OBJECT_CREATED_ALL.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$obs$services$model$EventTypeEnum[EventTypeEnum.OBJECT_CREATED_PUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$obs$services$model$EventTypeEnum[EventTypeEnum.OBJECT_CREATED_POST.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$obs$services$model$EventTypeEnum[EventTypeEnum.OBJECT_CREATED_COPY.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$obs$services$model$EventTypeEnum[EventTypeEnum.OBJECT_CREATED_COMPLETE_MULTIPART_UPLOAD.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$obs$services$model$EventTypeEnum[EventTypeEnum.OBJECT_REMOVED_ALL.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$obs$services$model$EventTypeEnum[EventTypeEnum.OBJECT_REMOVED_DELETE.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$obs$services$model$EventTypeEnum[EventTypeEnum.OBJECT_REMOVED_DELETE_MARKER_CREATED.ordinal()] = 8;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    ObsConvertor() {
    }

    public static IConvertor getInstance() {
        return instance;
    }

    public static String transEventTypeStatic(EventTypeEnum eventTypeEnum) {
        if (eventTypeEnum != null) {
            switch (AnonymousClass1.$SwitchMap$com$obs$services$model$EventTypeEnum[eventTypeEnum.ordinal()]) {
                case 1:
                    return "ObjectCreated:*";
                case 2:
                    return "ObjectCreated:Put";
                case 3:
                    return "ObjectCreated:Post";
                case 4:
                    return "ObjectCreated:Copy";
                case 5:
                    return "ObjectCreated:CompleteMultipartUpload";
                case 6:
                    return "ObjectRemoved:*";
                case 7:
                    return "ObjectRemoved:Delete";
                case 8:
                    return "ObjectRemoved:DeleteMarkerCreated";
            }
        }
        return "";
    }

    @Override
    public String transAccessControlList(AccessControlList accessControlList, boolean z) throws ServiceException, FactoryConfigurationError {
        Owner owner = accessControlList.getOwner();
        GrantAndPermission[] grantAndPermissions = accessControlList.getGrantAndPermissions();
        try {
            XMLBuilder xMLBuilderCreate = XMLBuilder.create("AccessControlPolicy");
            if (owner != null) {
                xMLBuilderCreate.elem("Owner").elem("ID").text(ServiceUtils.toValid(owner.getId()));
            }
            if (!z) {
                xMLBuilderCreate.elem("Delivered").text(String.valueOf(accessControlList.isDelivered()));
            }
            if (grantAndPermissions.length > 0) {
                XMLBuilder xMLBuilderElem = xMLBuilderCreate.elem("AccessControlList");
                for (GrantAndPermission grantAndPermission : grantAndPermissions) {
                    GranteeInterface grantee = grantAndPermission.getGrantee();
                    Permission permission = grantAndPermission.getPermission();
                    XMLBuilder xMLBuilderText = null;
                    if (grantee instanceof CanonicalGrantee) {
                        xMLBuilderText = XMLBuilder.create("Grantee").element("ID").text(ServiceUtils.toValid(grantee.getIdentifier()));
                    } else if (grantee instanceof GroupGrantee) {
                        if (((GroupGrantee) grantee).getGroupGranteeType() == GroupGranteeEnum.ALL_USERS) {
                            xMLBuilderText = XMLBuilder.create("Grantee").element("Canned").text(transGroupGrantee(((GroupGrantee) grantee).getGroupGranteeType()));
                        }
                    } else if (grantee != null) {
                        xMLBuilderText = XMLBuilder.create("Grantee").element("ID").text(ServiceUtils.toValid(grantee.getIdentifier()));
                    }
                    if (xMLBuilderText != null) {
                        XMLBuilder xMLBuilderImportXMLBuilder = xMLBuilderElem.elem("Grant").importXMLBuilder((BaseXMLBuilder) xMLBuilderText);
                        if (permission != null) {
                            xMLBuilderImportXMLBuilder.elem("Permission").text(ServiceUtils.toValid(permission.getPermissionString()));
                        }
                        if (z) {
                            xMLBuilderImportXMLBuilder.e("Delivered").t(String.valueOf(grantAndPermission.isDelivered()));
                        }
                    }
                }
            }
            return xMLBuilderCreate.asString();
        } catch (Exception e) {
            throw new ServiceException("Failed to build XML document for ACL", e);
        }
    }

    @Override
    public String transBucketEcryption(BucketEncryption bucketEncryption) throws ServiceException {
        String code = bucketEncryption.getSseAlgorithm().getCode();
        String kmsKeyId = code.equals(SSEAlgorithmEnum.KMS.getCode()) ? bucketEncryption.getKmsKeyId() : "";
        try {
            XMLBuilder xMLBuilderE = XMLBuilder.create("ServerSideEncryptionConfiguration").e("Rule").e("ApplyServerSideEncryptionByDefault");
            xMLBuilderE.e("SSEAlgorithm").t(code);
            if (ServiceUtils.isValid(kmsKeyId)) {
                xMLBuilderE.e("KMSMasterKeyID").t(kmsKeyId);
            }
            return xMLBuilderE.asString();
        } catch (Exception e) {
            throw new ServiceException("Failed to build XML document for bucketEncryption", e);
        }
    }

    @Override
    public String transBucketLoction(String str) throws ServiceException {
        try {
            return XMLBuilder.create("CreateBucketConfiguration").elem("Location").text(ServiceUtils.toValid(str)).asString();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public String transBucketLoggingConfiguration(BucketLoggingConfiguration bucketLoggingConfiguration) throws ServiceException, FactoryConfigurationError {
        try {
            XMLBuilder xMLBuilderCreate = XMLBuilder.create("BucketLoggingStatus");
            if (bucketLoggingConfiguration.getAgency() != null) {
                xMLBuilderCreate.e("Agency").t(ServiceUtils.toValid(bucketLoggingConfiguration.getAgency()));
            }
            if (bucketLoggingConfiguration.isLoggingEnabled()) {
                XMLBuilder xMLBuilderElem = xMLBuilderCreate.elem("LoggingEnabled");
                if (bucketLoggingConfiguration.getTargetBucketName() != null) {
                    xMLBuilderElem.elem("TargetBucket").text(ServiceUtils.toValid(bucketLoggingConfiguration.getTargetBucketName()));
                }
                if (bucketLoggingConfiguration.getLogfilePrefix() != null) {
                    xMLBuilderElem.elem("TargetPrefix").text(ServiceUtils.toValid(bucketLoggingConfiguration.getLogfilePrefix()));
                }
                GrantAndPermission[] targetGrants = bucketLoggingConfiguration.getTargetGrants();
                if (targetGrants.length > 0) {
                    XMLBuilder xMLBuilderElem2 = xMLBuilderElem.elem("TargetGrants");
                    for (GrantAndPermission grantAndPermission : targetGrants) {
                        GranteeInterface grantee = grantAndPermission.getGrantee();
                        Permission permission = grantAndPermission.getPermission();
                        if (permission != null) {
                            XMLBuilder xMLBuilderText = null;
                            if (grantee instanceof CanonicalGrantee) {
                                xMLBuilderText = XMLBuilder.create("Grantee").element("ID").text(ServiceUtils.toValid(grantee.getIdentifier()));
                            } else if (grantee instanceof GroupGrantee) {
                                xMLBuilderText = XMLBuilder.create("Grantee").element("Canned").text(transGroupGrantee(((GroupGrantee) grantee).getGroupGranteeType()));
                            }
                            if (xMLBuilderText != null) {
                                xMLBuilderElem2.elem("Grant").importXMLBuilder((BaseXMLBuilder) xMLBuilderText).elem("Permission").text(ServiceUtils.toValid(permission.getPermissionString())).up();
                            }
                        }
                    }
                }
            }
            return xMLBuilderCreate.asString();
        } catch (Exception e) {
            throw new ServiceException("Failed to build XML document for BucketLoggingConfiguration", e);
        }
    }

    @Override
    public String transBucketNotificationConfiguration(BucketNotificationConfiguration bucketNotificationConfiguration) throws ServiceException, FactoryConfigurationError {
        try {
            XMLBuilder xMLBuilderCreate = XMLBuilder.create("NotificationConfiguration");
            if (bucketNotificationConfiguration == null) {
                return xMLBuilderCreate.asString();
            }
            Iterator<TopicConfiguration> it = bucketNotificationConfiguration.getTopicConfigurations().iterator();
            while (it.hasNext()) {
                packNotificationConfig(xMLBuilderCreate, it.next(), "TopicConfiguration", "Topic", "Object");
            }
            Iterator<FunctionGraphConfiguration> it2 = bucketNotificationConfiguration.getFunctionGraphConfigurations().iterator();
            while (it2.hasNext()) {
                packNotificationConfig(xMLBuilderCreate, it2.next(), "FunctionGraphConfiguration", "FunctionGraph", "Object");
            }
            return xMLBuilderCreate.asString();
        } catch (Exception e) {
            throw new ServiceException("Failed to build XML document for Notification", e);
        }
    }

    @Override
    public AccessControlList transCannedAcl(String str) {
        if ("private".equals(str)) {
            return AccessControlList.REST_CANNED_PRIVATE;
        }
        if ("public-read".equals(str)) {
            return AccessControlList.REST_CANNED_PUBLIC_READ;
        }
        if ("public-read-write".equals(str)) {
            return AccessControlList.REST_CANNED_PUBLIC_READ_WRITE;
        }
        if ("public-read-delivered".equals(str)) {
            return AccessControlList.REST_CANNED_PUBLIC_READ_DELIVERED;
        }
        if ("public-read-write-delivered".equals(str)) {
            return AccessControlList.REST_CANNED_PUBLIC_READ_WRITE_DELIVERED;
        }
        return null;
    }

    @Override
    public String transEventType(EventTypeEnum eventTypeEnum) {
        return transEventTypeStatic(eventTypeEnum);
    }

    @Override
    public String transGroupGrantee(GroupGranteeEnum groupGranteeEnum) {
        return (groupGranteeEnum == null || AnonymousClass1.$SwitchMap$com$obs$services$model$GroupGranteeEnum[groupGranteeEnum.ordinal()] != 1) ? "" : "Everyone";
    }

    @Override
    public String transReplicationConfiguration(ReplicationConfiguration replicationConfiguration) throws ServiceException {
        try {
            XMLBuilder xMLBuilderUp = XMLBuilder.create("ReplicationConfiguration").e("Agency").t(ServiceUtils.toValid(replicationConfiguration.getAgency())).up();
            for (ReplicationConfiguration.Rule rule : replicationConfiguration.getRules()) {
                XMLBuilder xMLBuilderE = xMLBuilderUp.e("Rule");
                if (rule.getId() != null) {
                    xMLBuilderE.e("ID").t(rule.getId());
                }
                xMLBuilderE.e("Prefix").t(ServiceUtils.toValid(rule.getPrefix()));
                if (rule.getStatus() != null) {
                    xMLBuilderE.e("Status").t(rule.getStatus().getCode());
                }
                if (rule.getDestination() != null) {
                    XMLBuilder xMLBuilderUp2 = xMLBuilderE.e("Destination").e("Bucket").t(ServiceUtils.toValid(rule.getDestination().getBucket())).up();
                    if (rule.getDestination().getObjectStorageClass() != null) {
                        xMLBuilderUp2.e("StorageClass").t(transStorageClass(rule.getDestination().getObjectStorageClass()));
                    }
                    xMLBuilderE = xMLBuilderUp2.up();
                }
                xMLBuilderUp = xMLBuilderE.up();
            }
            return xMLBuilderUp.asString();
        } catch (Exception e) {
            throw new ServiceException("Failed to build XML document for Replication", e);
        }
    }

    @Override
    public String transRestoreObjectRequest(RestoreObjectRequest restoreObjectRequest) throws ServiceException {
        try {
            XMLBuilder xMLBuilderUp = XMLBuilder.create("RestoreRequest").elem("Days").t(String.valueOf(restoreObjectRequest.getDays())).up();
            if (restoreObjectRequest.getRestoreTier() != null) {
                xMLBuilderUp.e("RestoreJob").e("Tier").t(restoreObjectRequest.getRestoreTier().getCode());
            }
            return xMLBuilderUp.asString();
        } catch (Exception e) {
            throw new ServiceException("Failed to build XML document for restoreobject", e);
        }
    }

    @Override
    public String transStorageClass(StorageClassEnum storageClassEnum) {
        if (storageClassEnum != null) {
            int i = AnonymousClass1.$SwitchMap$com$obs$services$model$StorageClassEnum[storageClassEnum.ordinal()];
            if (i == 1) {
                return "STANDARD";
            }
            if (i == 2) {
                return "WARM";
            }
            if (i == 3) {
                return "COLD";
            }
        }
        return "";
    }

    @Override
    public String transStoragePolicy(BucketStoragePolicyConfiguration bucketStoragePolicyConfiguration) throws ServiceException {
        try {
            return XMLBuilder.create("StorageClass").text(transStorageClass(bucketStoragePolicyConfiguration.getBucketStorageClass())).asString();
        } catch (Exception e) {
            throw new ServiceException("Failed to build XML document for StorageClass", e);
        }
    }
}
