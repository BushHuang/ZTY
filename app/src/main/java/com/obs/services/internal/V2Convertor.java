package com.obs.services.internal;

import com.jamesmurty.utils.BaseXMLBuilder;
import com.jamesmurty.utils.XMLBuilder;
import com.obs.services.internal.utils.ServiceUtils;
import com.obs.services.model.AbstractNotification;
import com.obs.services.model.AccessControlList;
import com.obs.services.model.BucketCors;
import com.obs.services.model.BucketCorsRule;
import com.obs.services.model.BucketDirectColdAccess;
import com.obs.services.model.BucketEncryption;
import com.obs.services.model.BucketLoggingConfiguration;
import com.obs.services.model.BucketNotificationConfiguration;
import com.obs.services.model.BucketQuota;
import com.obs.services.model.BucketStoragePolicyConfiguration;
import com.obs.services.model.BucketTagInfo;
import com.obs.services.model.CanonicalGrantee;
import com.obs.services.model.EventTypeEnum;
import com.obs.services.model.FunctionGraphConfiguration;
import com.obs.services.model.GrantAndPermission;
import com.obs.services.model.GranteeInterface;
import com.obs.services.model.GroupGrantee;
import com.obs.services.model.GroupGranteeEnum;
import com.obs.services.model.KeyAndVersion;
import com.obs.services.model.LifecycleConfiguration;
import com.obs.services.model.Owner;
import com.obs.services.model.PartEtag;
import com.obs.services.model.Permission;
import com.obs.services.model.Redirect;
import com.obs.services.model.ReplicationConfiguration;
import com.obs.services.model.RestoreObjectRequest;
import com.obs.services.model.RouteRule;
import com.obs.services.model.RouteRuleCondition;
import com.obs.services.model.SSEAlgorithmEnum;
import com.obs.services.model.StorageClassEnum;
import com.obs.services.model.TopicConfiguration;
import com.obs.services.model.WebsiteConfiguration;
import com.obs.services.model.fs.FSStatusEnum;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javax.xml.parsers.FactoryConfigurationError;

public class V2Convertor implements IConvertor {
    private static IConvertor instance = new V2Convertor();

    static class AnonymousClass2 {
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
            try {
                $SwitchMap$com$obs$services$model$GroupGranteeEnum[GroupGranteeEnum.AUTHENTICATED_USERS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$obs$services$model$GroupGranteeEnum[GroupGranteeEnum.LOG_DELIVERY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[StorageClassEnum.values().length];
            $SwitchMap$com$obs$services$model$StorageClassEnum = iArr2;
            try {
                iArr2[StorageClassEnum.STANDARD.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$obs$services$model$StorageClassEnum[StorageClassEnum.WARM.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$obs$services$model$StorageClassEnum[StorageClassEnum.COLD.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            int[] iArr3 = new int[EventTypeEnum.values().length];
            $SwitchMap$com$obs$services$model$EventTypeEnum = iArr3;
            try {
                iArr3[EventTypeEnum.OBJECT_CREATED_ALL.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$obs$services$model$EventTypeEnum[EventTypeEnum.OBJECT_CREATED_PUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$obs$services$model$EventTypeEnum[EventTypeEnum.OBJECT_CREATED_POST.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$obs$services$model$EventTypeEnum[EventTypeEnum.OBJECT_CREATED_COPY.ordinal()] = 4;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$obs$services$model$EventTypeEnum[EventTypeEnum.OBJECT_CREATED_COMPLETE_MULTIPART_UPLOAD.ordinal()] = 5;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$obs$services$model$EventTypeEnum[EventTypeEnum.OBJECT_REMOVED_ALL.ordinal()] = 6;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$obs$services$model$EventTypeEnum[EventTypeEnum.OBJECT_REMOVED_DELETE.ordinal()] = 7;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$obs$services$model$EventTypeEnum[EventTypeEnum.OBJECT_REMOVED_DELETE_MARKER_CREATED.ordinal()] = 8;
            } catch (NoSuchFieldError unused14) {
            }
        }
    }

    protected V2Convertor() {
    }

    public static IConvertor getInstance() {
        return instance;
    }

    protected void packNotificationConfig(XMLBuilder xMLBuilder, AbstractNotification abstractNotification, String str, String str2, String str3) {
        XMLBuilder xMLBuilderE = xMLBuilder.e(str);
        if (abstractNotification.getId() != null) {
            xMLBuilderE.e("Id").t(abstractNotification.getId());
        }
        if (abstractNotification.getFilter() != null && !abstractNotification.getFilter().getFilterRules().isEmpty()) {
            XMLBuilder xMLBuilderE2 = xMLBuilderE.e("Filter").e(str3);
            for (AbstractNotification.Filter.FilterRule filterRule : abstractNotification.getFilter().getFilterRules()) {
                if (filterRule != null) {
                    xMLBuilderE2.e("FilterRule").e("Name").t(ServiceUtils.toValid(filterRule.getName())).up().e("Value").t(ServiceUtils.toValid(filterRule.getValue()));
                }
            }
            xMLBuilderE = xMLBuilderE2.up().up();
        }
        String topic = abstractNotification instanceof TopicConfiguration ? ((TopicConfiguration) abstractNotification).getTopic() : null;
        if (abstractNotification instanceof FunctionGraphConfiguration) {
            topic = ((FunctionGraphConfiguration) abstractNotification).getFunctionGraph();
        }
        if (topic != null) {
            xMLBuilderE.e(str2).t(topic);
        }
        if (abstractNotification.getEventTypes() != null) {
            for (EventTypeEnum eventTypeEnum : abstractNotification.getEventTypes()) {
                if (eventTypeEnum != null) {
                    xMLBuilderE.e("Event").t(transEventType(eventTypeEnum));
                }
            }
        }
        xMLBuilderE.up();
    }

    @Override
    public String transAccessControlList(AccessControlList accessControlList, boolean z) throws ServiceException, FactoryConfigurationError {
        Owner owner = accessControlList.getOwner();
        GrantAndPermission[] grantAndPermissions = accessControlList.getGrantAndPermissions();
        try {
            XMLBuilder xMLBuilderCreate = XMLBuilder.create("AccessControlPolicy");
            if (owner != null) {
                XMLBuilder xMLBuilderText = xMLBuilderCreate.elem("Owner").elem("ID").text(ServiceUtils.toValid(owner.getId()));
                if (owner.getDisplayName() != null) {
                    xMLBuilderText.up().elem("DisplayName").text(owner.getDisplayName());
                }
                xMLBuilderCreate = xMLBuilderText.up().up();
            }
            if (grantAndPermissions.length > 0) {
                XMLBuilder xMLBuilderElem = xMLBuilderCreate.elem("AccessControlList");
                for (GrantAndPermission grantAndPermission : grantAndPermissions) {
                    GranteeInterface grantee = grantAndPermission.getGrantee();
                    Permission permission = grantAndPermission.getPermission();
                    XMLBuilder xMLBuilderText2 = null;
                    if (grantee instanceof CanonicalGrantee) {
                        xMLBuilderText2 = XMLBuilder.create("Grantee").attr("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance").attr("xsi:type", "CanonicalUser").element("ID").text(ServiceUtils.toValid(grantee.getIdentifier()));
                        String displayName = ((CanonicalGrantee) grantee).getDisplayName();
                        if (ServiceUtils.isValid2(displayName)) {
                            xMLBuilderText2.up().element("DisplayName").text(displayName);
                        }
                    } else if (grantee instanceof GroupGrantee) {
                        xMLBuilderText2 = XMLBuilder.create("Grantee").attr("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance").attr("xsi:type", "Group").element("URI").text(transGroupGrantee(((GroupGrantee) grantee).getGroupGranteeType()));
                    } else if (grantee != null) {
                        xMLBuilderText2 = XMLBuilder.create("Grantee").attr("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance").attr("xsi:type", "CanonicalUser").element("ID").text(ServiceUtils.toValid(grantee.getIdentifier()));
                    }
                    if (xMLBuilderText2 != null) {
                        try {
                            XMLBuilder xMLBuilderImportXMLBuilder = xMLBuilderElem.elem("Grant").importXMLBuilder((BaseXMLBuilder) xMLBuilderText2);
                            if (permission != null) {
                                xMLBuilderImportXMLBuilder.elem("Permission").text(ServiceUtils.toValid(permission.getPermissionString()));
                            }
                        } catch (Exception e) {
                            e = e;
                            throw new ServiceException("Failed to build XML document for ACL", e);
                        }
                    }
                }
            }
            return xMLBuilderCreate.asString();
        } catch (Exception e2) {
            e = e2;
        }
    }

    @Override
    public String transBucketCors(BucketCors bucketCors) throws ServiceException, FactoryConfigurationError {
        try {
            XMLBuilder xMLBuilderCreate = XMLBuilder.create("CORSConfiguration");
            for (BucketCorsRule bucketCorsRule : bucketCors.getRules()) {
                XMLBuilder xMLBuilderE = xMLBuilderCreate.e("CORSRule");
                if (bucketCorsRule.getId() != null) {
                    xMLBuilderE.e("ID").t(bucketCorsRule.getId());
                }
                if (bucketCorsRule.getAllowedMethod() != null) {
                    Iterator<String> it = bucketCorsRule.getAllowedMethod().iterator();
                    while (it.hasNext()) {
                        xMLBuilderE.e("AllowedMethod").t(ServiceUtils.toValid(it.next()));
                    }
                }
                if (bucketCorsRule.getAllowedOrigin() != null) {
                    Iterator<String> it2 = bucketCorsRule.getAllowedOrigin().iterator();
                    while (it2.hasNext()) {
                        xMLBuilderE.e("AllowedOrigin").t(ServiceUtils.toValid(it2.next()));
                    }
                }
                if (bucketCorsRule.getAllowedHeader() != null) {
                    Iterator<String> it3 = bucketCorsRule.getAllowedHeader().iterator();
                    while (it3.hasNext()) {
                        xMLBuilderE.e("AllowedHeader").t(ServiceUtils.toValid(it3.next()));
                    }
                }
                xMLBuilderE.e("MaxAgeSeconds").t(String.valueOf(bucketCorsRule.getMaxAgeSecond()));
                if (bucketCorsRule.getExposeHeader() != null) {
                    Iterator<String> it4 = bucketCorsRule.getExposeHeader().iterator();
                    while (it4.hasNext()) {
                        xMLBuilderE.e("ExposeHeader").t(ServiceUtils.toValid(it4.next()));
                    }
                }
                xMLBuilderCreate = xMLBuilderE.up();
            }
            return xMLBuilderCreate.asString();
        } catch (Exception e) {
            throw new ServiceException("Failed to build XML document for cors", e);
        }
    }

    @Override
    public String transBucketDirectColdAccess(BucketDirectColdAccess bucketDirectColdAccess) throws ServiceException {
        try {
            return XMLBuilder.create("DirectColdAccessConfiguration").e("Status").t(bucketDirectColdAccess.getStatus().getCode()).up().up().asString();
        } catch (Exception e) {
            throw new ServiceException("Failed to build XML document for Tagging", e);
        }
    }

    @Override
    public String transBucketEcryption(BucketEncryption bucketEncryption) throws ServiceException {
        String kmsKeyId;
        String code = bucketEncryption.getSseAlgorithm().getCode();
        if (code.equals(SSEAlgorithmEnum.KMS.getCode())) {
            code = "aws:" + code;
            kmsKeyId = bucketEncryption.getKmsKeyId();
        } else {
            kmsKeyId = "";
        }
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
    public String transBucketFileInterface(FSStatusEnum fSStatusEnum) throws ServiceException {
        try {
            return XMLBuilder.create("FileInterfaceConfiguration").e("Status").t(fSStatusEnum.getCode()).up().asString();
        } catch (Exception e) {
            throw new ServiceException("Failed to build XML document for FileInterface", e);
        }
    }

    @Override
    public String transBucketLoction(String str) throws ServiceException {
        try {
            return XMLBuilder.create("CreateBucketConfiguration").elem("LocationConstraint").text(ServiceUtils.toValid(str)).asString();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public String transBucketLoggingConfiguration(BucketLoggingConfiguration bucketLoggingConfiguration) throws ServiceException, FactoryConfigurationError {
        try {
            XMLBuilder xMLBuilderCreate = XMLBuilder.create("BucketLoggingStatus");
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
                                xMLBuilderText = XMLBuilder.create("Grantee").attr("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance").attr("xsi:type", "CanonicalUser").element("ID").text(ServiceUtils.toValid(grantee.getIdentifier()));
                                String displayName = ((CanonicalGrantee) grantee).getDisplayName();
                                if (ServiceUtils.isValid2(displayName)) {
                                    xMLBuilderText.up().element("DisplayName").text(displayName);
                                }
                            } else if (grantee instanceof GroupGrantee) {
                                xMLBuilderText = XMLBuilder.create("Grantee").attr("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance").attr("xsi:type", "Group").element("URI").text(transGroupGrantee(((GroupGrantee) grantee).getGroupGranteeType()));
                            }
                            if (xMLBuilderText != null) {
                                xMLBuilderElem2.elem("Grant").importXMLBuilder((BaseXMLBuilder) xMLBuilderText).elem("Permission").text(ServiceUtils.toValid(permission.getPermissionString()));
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
                packNotificationConfig(xMLBuilderCreate, it.next(), "TopicConfiguration", "Topic", "S3Key");
            }
            Iterator<FunctionGraphConfiguration> it2 = bucketNotificationConfiguration.getFunctionGraphConfigurations().iterator();
            while (it2.hasNext()) {
                packNotificationConfig(xMLBuilderCreate, it2.next(), "FunctionGraphConfiguration", "FunctionGraph", "S3Key");
            }
            return xMLBuilderCreate.asString();
        } catch (Exception e) {
            throw new ServiceException("Failed to build XML document for Notification", e);
        }
    }

    @Override
    public String transBucketQuota(BucketQuota bucketQuota) throws ServiceException {
        try {
            return XMLBuilder.create("Quota").elem("StorageQuota").text(String.valueOf(bucketQuota.getBucketQuota())).up().asString();
        } catch (Exception e) {
            throw new ServiceException("Failed to build XML document for storageQuota", e);
        }
    }

    @Override
    public String transBucketTagInfo(BucketTagInfo bucketTagInfo) throws ServiceException {
        try {
            XMLBuilder xMLBuilderE = XMLBuilder.create("Tagging").e("TagSet");
            for (BucketTagInfo.TagSet.Tag tag : bucketTagInfo.getTagSet().getTags()) {
                if (tag != null) {
                    xMLBuilderE.e("Tag").e("Key").t(ServiceUtils.toValid(tag.getKey())).up().e("Value").t(ServiceUtils.toValid(tag.getValue()));
                }
            }
            return xMLBuilderE.up().asString();
        } catch (Exception e) {
            throw new ServiceException("Failed to build XML document for Tagging", e);
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
            return AccessControlList.REST_CANNED_PUBLIC_READ;
        }
        if ("public-read-write-delivered".equals(str)) {
            return AccessControlList.REST_CANNED_PUBLIC_READ_WRITE;
        }
        if ("authenticated-read".equals(str)) {
            return AccessControlList.REST_CANNED_AUTHENTICATED_READ;
        }
        if ("bucket-owner-read".equals(str)) {
            return AccessControlList.REST_CANNED_BUCKET_OWNER_READ;
        }
        if ("bucket-owner-full-control".equals(str)) {
            return AccessControlList.REST_CANNED_BUCKET_OWNER_FULL_CONTROL;
        }
        if ("log-delivery-write".equals(str)) {
            return AccessControlList.REST_CANNED_LOG_DELIVERY_WRITE;
        }
        return null;
    }

    @Override
    public String transCompleteMultipartUpload(List<PartEtag> list) throws ServiceException, FactoryConfigurationError {
        try {
            XMLBuilder xMLBuilderCreate = XMLBuilder.create("CompleteMultipartUpload");
            Collections.sort(list, new Comparator<PartEtag>() {
                @Override
                public int compare(PartEtag partEtag, PartEtag partEtag2) {
                    if (partEtag == partEtag2) {
                        return 0;
                    }
                    if (partEtag == null) {
                        return -1;
                    }
                    if (partEtag2 == null) {
                        return 1;
                    }
                    return partEtag.getPartNumber().compareTo(partEtag2.getPartNumber());
                }
            });
            for (PartEtag partEtag : list) {
                xMLBuilderCreate.e("Part").e("PartNumber").t(partEtag.getPartNumber() == null ? "" : partEtag.getPartNumber().toString()).up().e("ETag").t(ServiceUtils.toValid(partEtag.geteTag()));
            }
            return xMLBuilderCreate.asString();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public String transEventType(EventTypeEnum eventTypeEnum) {
        if (eventTypeEnum != null) {
            switch (AnonymousClass2.$SwitchMap$com$obs$services$model$EventTypeEnum[eventTypeEnum.ordinal()]) {
                case 1:
                    return "s3:ObjectCreated:*";
                case 2:
                    return "s3:ObjectCreated:Put";
                case 3:
                    return "s3:ObjectCreated:Post";
                case 4:
                    return "s3:ObjectCreated:Copy";
                case 5:
                    return "s3:ObjectCreated:CompleteMultipartUpload";
                case 6:
                    return "s3:ObjectRemoved:*";
                case 7:
                    return "s3:ObjectRemoved:Delete";
                case 8:
                    return "s3:ObjectRemoved:DeleteMarkerCreated";
            }
        }
        return "";
    }

    @Override
    public String transGroupGrantee(GroupGranteeEnum groupGranteeEnum) {
        if (groupGranteeEnum != null) {
            int i = AnonymousClass2.$SwitchMap$com$obs$services$model$GroupGranteeEnum[groupGranteeEnum.ordinal()];
            if (i == 1) {
                return "http://acs.amazonaws.com/groups/global/AllUsers";
            }
            if (i == 2) {
                return "http://acs.amazonaws.com/groups/global/AuthenticatedUsers";
            }
            if (i == 3) {
                return "http://acs.amazonaws.com/groups/s3/LogDelivery";
            }
        }
        return "";
    }

    @Override
    public String transKeyAndVersion(KeyAndVersion[] keyAndVersionArr, boolean z) throws ServiceException {
        try {
            XMLBuilder xMLBuilderUp = XMLBuilder.create("Delete").elem("Quiet").text(String.valueOf(z)).up();
            for (KeyAndVersion keyAndVersion : keyAndVersionArr) {
                XMLBuilder xMLBuilderUp2 = xMLBuilderUp.elem("Object").elem("Key").text(ServiceUtils.toValid(keyAndVersion.getKey())).up();
                if (ServiceUtils.isValid(keyAndVersion.getVersion())) {
                    xMLBuilderUp2.elem("VersionId").text(keyAndVersion.getVersion());
                }
            }
            return xMLBuilderUp.asString();
        } catch (Exception e) {
            throw new ServiceException("Failed to build XML document", e);
        }
    }

    @Override
    public String transLifecycleConfiguration(LifecycleConfiguration lifecycleConfiguration) throws ServiceException, FactoryConfigurationError {
        try {
            XMLBuilder xMLBuilderCreate = XMLBuilder.create("LifecycleConfiguration");
            for (LifecycleConfiguration.Rule rule : lifecycleConfiguration.getRules()) {
                XMLBuilder xMLBuilderElem = xMLBuilderCreate.elem("Rule");
                if (ServiceUtils.isValid2(rule.getId())) {
                    xMLBuilderElem.elem("ID").t(rule.getId());
                }
                if (rule.getPrefix() != null) {
                    xMLBuilderElem.elem("Prefix").t(ServiceUtils.toValid(rule.getPrefix()));
                }
                xMLBuilderElem.elem("Status").t(rule.getEnabled().booleanValue() ? "Enabled" : "Disabled");
                if (rule.getTransitions() != null) {
                    for (LifecycleConfiguration.Transition transition : rule.getTransitions()) {
                        if (transition.getObjectStorageClass() != null) {
                            XMLBuilder xMLBuilderElem2 = xMLBuilderElem.elem("Transition");
                            if (transition.getDate() != null) {
                                xMLBuilderElem2.elem("Date").t(ServiceUtils.formatIso8601MidnightDate(transition.getDate()));
                            } else if (transition.getDays() != null) {
                                xMLBuilderElem2.elem("Days").t(transition.getDays().toString());
                            }
                            xMLBuilderElem2.elem("StorageClass").t(transStorageClass(transition.getObjectStorageClass()));
                        }
                    }
                }
                if (rule.getExpiration() != null) {
                    XMLBuilder xMLBuilderElem3 = xMLBuilderElem.elem("Expiration");
                    if (rule.getExpiration().getDate() != null) {
                        xMLBuilderElem3.elem("Date").t(ServiceUtils.formatIso8601MidnightDate(rule.getExpiration().getDate()));
                    } else if (rule.getExpiration().getDays() != null) {
                        xMLBuilderElem3.elem("Days").t(rule.getExpiration().getDays().toString());
                    }
                }
                if (rule.getNoncurrentVersionTransitions() != null) {
                    for (LifecycleConfiguration.NoncurrentVersionTransition noncurrentVersionTransition : rule.getNoncurrentVersionTransitions()) {
                        if (noncurrentVersionTransition.getObjectStorageClass() != null && noncurrentVersionTransition.getDays() != null) {
                            XMLBuilder xMLBuilderElem4 = xMLBuilderElem.elem("NoncurrentVersionTransition");
                            xMLBuilderElem4.elem("NoncurrentDays").t(noncurrentVersionTransition.getDays().toString());
                            xMLBuilderElem4.elem("StorageClass").t(transStorageClass(noncurrentVersionTransition.getObjectStorageClass()));
                        }
                    }
                }
                if (rule.getNoncurrentVersionExpiration() != null && rule.getNoncurrentVersionExpiration().getDays() != null) {
                    xMLBuilderElem.elem("NoncurrentVersionExpiration").elem("NoncurrentDays").t(rule.getNoncurrentVersionExpiration().getDays().toString());
                }
            }
            return xMLBuilderCreate.asString();
        } catch (Exception e) {
            throw new ServiceException("Failed to build XML document for lifecycle", e);
        }
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
                    String valid = ServiceUtils.toValid(rule.getDestination().getBucket());
                    XMLBuilder xMLBuilderE2 = xMLBuilderE.e("Destination").e("Bucket");
                    if (!valid.startsWith("arn:aws:s3:::")) {
                        valid = "arn:aws:s3:::" + valid;
                    }
                    XMLBuilder xMLBuilderUp2 = xMLBuilderE2.t(valid).up();
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
                xMLBuilderUp.e("GlacierJobParameters").e("Tier").t(restoreObjectRequest.getRestoreTier().getCode());
            }
            return xMLBuilderUp.asString();
        } catch (Exception e) {
            throw new ServiceException("Failed to build XML document for restoreobject", e);
        }
    }

    @Override
    public String transStorageClass(StorageClassEnum storageClassEnum) {
        if (storageClassEnum != null) {
            int i = AnonymousClass2.$SwitchMap$com$obs$services$model$StorageClassEnum[storageClassEnum.ordinal()];
            if (i == 1) {
                return "STANDARD";
            }
            if (i == 2) {
                return "STANDARD_IA";
            }
            if (i == 3) {
                return "GLACIER";
            }
        }
        return "";
    }

    @Override
    public String transStoragePolicy(BucketStoragePolicyConfiguration bucketStoragePolicyConfiguration) throws ServiceException {
        try {
            return XMLBuilder.create("StoragePolicy").elem("DefaultStorageClass").text(transStorageClass(bucketStoragePolicyConfiguration.getBucketStorageClass())).asString();
        } catch (Exception e) {
            throw new ServiceException("Failed to build XML document for StoragePolicy", e);
        }
    }

    @Override
    public String transVersioningConfiguration(String str, String str2) throws ServiceException {
        try {
            return XMLBuilder.create("VersioningConfiguration").elem("Status").text(ServiceUtils.toValid(str2)).asString();
        } catch (Exception e) {
            throw new ServiceException("Failed to build XML document for versioning", e);
        }
    }

    @Override
    public String transWebsiteConfiguration(WebsiteConfiguration websiteConfiguration) throws ServiceException, FactoryConfigurationError {
        try {
            XMLBuilder xMLBuilderCreate = XMLBuilder.create("WebsiteConfiguration");
            if (websiteConfiguration.getRedirectAllRequestsTo() != null) {
                if (websiteConfiguration.getRedirectAllRequestsTo().getHostName() != null) {
                    xMLBuilderCreate = xMLBuilderCreate.elem("RedirectAllRequestsTo").elem("HostName").text(ServiceUtils.toValid(websiteConfiguration.getRedirectAllRequestsTo().getHostName()));
                }
                if (websiteConfiguration.getRedirectAllRequestsTo().getRedirectProtocol() != null) {
                    xMLBuilderCreate = xMLBuilderCreate.up().elem("Protocol").text(websiteConfiguration.getRedirectAllRequestsTo().getRedirectProtocol().getCode());
                }
                xMLBuilderCreate.up().up();
                return xMLBuilderCreate.asString();
            }
            if (ServiceUtils.isValid2(websiteConfiguration.getSuffix())) {
                xMLBuilderCreate.elem("IndexDocument").elem("Suffix").text(websiteConfiguration.getSuffix()).up().up();
            }
            if (ServiceUtils.isValid2(websiteConfiguration.getKey())) {
                xMLBuilderCreate.elem("ErrorDocument").elem("Key").text(websiteConfiguration.getKey()).up().up();
            }
            if (websiteConfiguration.getRouteRules() != null && websiteConfiguration.getRouteRules().size() > 0) {
                XMLBuilder xMLBuilderElem = xMLBuilderCreate.elem("RoutingRules");
                for (RouteRule routeRule : websiteConfiguration.getRouteRules()) {
                    XMLBuilder xMLBuilderElem2 = xMLBuilderElem.elem("RoutingRule");
                    RouteRuleCondition condition = routeRule.getCondition();
                    Redirect redirect = routeRule.getRedirect();
                    if (condition != null) {
                        XMLBuilder xMLBuilderElem3 = xMLBuilderElem2.elem("Condition");
                        String keyPrefixEquals = condition.getKeyPrefixEquals();
                        String httpErrorCodeReturnedEquals = condition.getHttpErrorCodeReturnedEquals();
                        if (ServiceUtils.isValid2(keyPrefixEquals)) {
                            xMLBuilderElem3 = xMLBuilderElem3.elem("KeyPrefixEquals").text(keyPrefixEquals).up();
                        }
                        if (ServiceUtils.isValid2(httpErrorCodeReturnedEquals)) {
                            xMLBuilderElem3 = xMLBuilderElem3.elem("HttpErrorCodeReturnedEquals").text(httpErrorCodeReturnedEquals).up();
                        }
                        xMLBuilderElem2 = xMLBuilderElem3.up();
                    }
                    if (redirect != null) {
                        XMLBuilder xMLBuilderElem4 = xMLBuilderElem2.elem("Redirect");
                        String hostName = redirect.getHostName();
                        String replaceKeyWith = redirect.getReplaceKeyWith();
                        String replaceKeyPrefixWith = redirect.getReplaceKeyPrefixWith();
                        String httpRedirectCode = redirect.getHttpRedirectCode();
                        if (ServiceUtils.isValid2(hostName)) {
                            xMLBuilderElem4 = xMLBuilderElem4.elem("HostName").text(hostName).up();
                        }
                        if (ServiceUtils.isValid2(httpRedirectCode)) {
                            xMLBuilderElem4 = xMLBuilderElem4.elem("HttpRedirectCode").text(httpRedirectCode).up();
                        }
                        if (ServiceUtils.isValid2(replaceKeyWith)) {
                            xMLBuilderElem4 = xMLBuilderElem4.elem("ReplaceKeyWith").text(replaceKeyWith).up();
                        }
                        if (ServiceUtils.isValid2(replaceKeyPrefixWith)) {
                            xMLBuilderElem4 = xMLBuilderElem4.elem("ReplaceKeyPrefixWith").text(replaceKeyPrefixWith).up();
                        }
                        if (redirect.getRedirectProtocol() != null) {
                            xMLBuilderElem4 = xMLBuilderElem4.elem("Protocol").text(redirect.getRedirectProtocol().getCode()).up();
                        }
                        xMLBuilderElem2 = xMLBuilderElem4.up();
                    }
                    xMLBuilderElem = xMLBuilderElem2.up();
                }
                xMLBuilderCreate = xMLBuilderElem.up();
            }
            return xMLBuilderCreate.asString();
        } catch (Exception e) {
            throw new ServiceException("Failed to build XML document for website", e);
        }
    }
}
