package com.obs.services.internal.handler;

import com.obs.log.ILogger;
import com.obs.log.LoggerBuilder;
import com.obs.services.internal.ServiceException;
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
import com.obs.services.model.BucketStorageInfo;
import com.obs.services.model.BucketStoragePolicyConfiguration;
import com.obs.services.model.BucketTagInfo;
import com.obs.services.model.BucketVersioningConfiguration;
import com.obs.services.model.CanonicalGrantee;
import com.obs.services.model.CopyPartResult;
import com.obs.services.model.DeleteObjectsResult;
import com.obs.services.model.DeleteObjectsResult.DeleteObjectResult;
import com.obs.services.model.DeleteObjectsResult.ErrorResult;
import com.obs.services.model.EventTypeEnum;
import com.obs.services.model.FunctionGraphConfiguration;
import com.obs.services.model.GrantAndPermission;
import com.obs.services.model.GranteeInterface;
import com.obs.services.model.GroupGrantee;
import com.obs.services.model.InitiateMultipartUploadResult;
import com.obs.services.model.LifecycleConfiguration;
import com.obs.services.model.LifecycleConfiguration.Expiration;
import com.obs.services.model.LifecycleConfiguration.NoncurrentVersionExpiration;
import com.obs.services.model.LifecycleConfiguration.NoncurrentVersionTransition;
import com.obs.services.model.LifecycleConfiguration.Rule;
import com.obs.services.model.LifecycleConfiguration.Transition;
import com.obs.services.model.Multipart;
import com.obs.services.model.MultipartUpload;
import com.obs.services.model.ObsBucket;
import com.obs.services.model.ObsObject;
import com.obs.services.model.Owner;
import com.obs.services.model.Permission;
import com.obs.services.model.ProtocolEnum;
import com.obs.services.model.Redirect;
import com.obs.services.model.RedirectAllRequest;
import com.obs.services.model.ReplicationConfiguration;
import com.obs.services.model.RouteRule;
import com.obs.services.model.RouteRuleCondition;
import com.obs.services.model.RuleStatusEnum;
import com.obs.services.model.SSEAlgorithmEnum;
import com.obs.services.model.StorageClassEnum;
import com.obs.services.model.TopicConfiguration;
import com.obs.services.model.VersionOrDeleteMarker;
import com.obs.services.model.VersioningStatusEnum;
import com.obs.services.model.WebsiteConfiguration;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class XmlResponsesSaxParser {
    private static final ILogger log = LoggerBuilder.getLogger("com.obs.services.internal.RestStorageService");
    private XMLReader xr = ServiceUtils.loadXMLReader();

    public static class AccessControlListHandler extends DefaultXmlHandler {
        protected AccessControlList accessControlList;
        protected boolean currentDelivered;
        protected GranteeInterface currentGrantee;
        protected Permission currentPermission;
        protected boolean insideACL = false;
        protected Owner owner;

        @Override
        public void endElement(String str, String str2) {
            if (str.equals("ID") && !this.insideACL) {
                this.owner.setId(str2);
                return;
            }
            if (str.equals("DisplayName") && !this.insideACL) {
                this.owner.setDisplayName(str2);
                return;
            }
            if (str.equals("ID")) {
                CanonicalGrantee canonicalGrantee = new CanonicalGrantee();
                this.currentGrantee = canonicalGrantee;
                canonicalGrantee.setIdentifier(str2);
                return;
            }
            if (str.equals("URI") || str.equals("Canned")) {
                GroupGrantee groupGrantee = new GroupGrantee();
                this.currentGrantee = groupGrantee;
                groupGrantee.setIdentifier(str2);
                return;
            }
            if (str.equals("DisplayName")) {
                GranteeInterface granteeInterface = this.currentGrantee;
                if (granteeInterface instanceof CanonicalGrantee) {
                    ((CanonicalGrantee) granteeInterface).setDisplayName(str2);
                    return;
                }
                return;
            }
            if (str.equals("Permission")) {
                this.currentPermission = Permission.parsePermission(str2);
                return;
            }
            if (str.equals("Delivered")) {
                if (this.insideACL) {
                    this.currentDelivered = Boolean.parseBoolean(str2);
                    return;
                } else {
                    this.accessControlList.setDelivered(Boolean.parseBoolean(str2));
                    return;
                }
            }
            if (str.equals("Grant")) {
                this.accessControlList.grantPermission(this.currentGrantee, this.currentPermission).setDelivered(this.currentDelivered);
            } else if (str.equals("AccessControlList")) {
                this.insideACL = false;
            }
        }

        public AccessControlList getAccessControlList() {
            return this.accessControlList;
        }

        @Override
        public void startElement(String str) {
            if (str.equals("AccessControlPolicy")) {
                this.accessControlList = new AccessControlList();
                return;
            }
            if (str.equals("Owner")) {
                Owner owner = new Owner();
                this.owner = owner;
                this.accessControlList.setOwner(owner);
            } else if (str.equals("AccessControlList")) {
                this.insideACL = true;
            }
        }
    }

    public static class BucketCorsHandler extends DefaultXmlHandler {
        private BucketCorsRule currentRule;
        private final BucketCors configuration = new BucketCors();
        private List<String> allowedMethods = null;
        private List<String> allowedOrigins = null;
        private List<String> exposedHeaders = null;
        private List<String> allowedHeaders = null;

        @Override
        public void endElement(String str, String str2) {
            List<String> list;
            List<String> list2;
            BucketCorsRule bucketCorsRule;
            List<String> list3;
            List<String> list4;
            BucketCorsRule bucketCorsRule2;
            if (str.equals("CORSRule")) {
                this.currentRule.setAllowedHeader(this.allowedHeaders);
                this.currentRule.setAllowedMethod(this.allowedMethods);
                this.currentRule.setAllowedOrigin(this.allowedOrigins);
                this.currentRule.setExposeHeader(this.exposedHeaders);
                this.configuration.getRules().add(this.currentRule);
                this.allowedHeaders = null;
                this.allowedMethods = null;
                this.allowedOrigins = null;
                this.exposedHeaders = null;
                this.currentRule = null;
            }
            if (str.equals("ID") && (bucketCorsRule2 = this.currentRule) != null) {
                bucketCorsRule2.setId(str2);
                return;
            }
            if (str.equals("AllowedOrigin") && (list4 = this.allowedOrigins) != null) {
                list4.add(str2);
                return;
            }
            if (str.equals("AllowedMethod") && (list3 = this.allowedMethods) != null) {
                list3.add(str2);
                return;
            }
            if (str.equals("MaxAgeSeconds") && (bucketCorsRule = this.currentRule) != null) {
                bucketCorsRule.setMaxAgeSecond(Integer.parseInt(str2));
                return;
            }
            if (str.equals("ExposeHeader") && (list2 = this.exposedHeaders) != null) {
                list2.add(str2);
            } else {
                if (!str.equals("AllowedHeader") || (list = this.allowedHeaders) == null) {
                    return;
                }
                list.add(str2);
            }
        }

        public BucketCors getConfiguration() {
            return this.configuration;
        }

        @Override
        public void startElement(String str) {
            if ("CORSRule".equals(str)) {
                this.currentRule = new BucketCorsRule();
            }
            if ("AllowedOrigin".equals(str)) {
                if (this.allowedOrigins == null) {
                    this.allowedOrigins = new ArrayList();
                }
            } else if ("AllowedMethod".equals(str)) {
                if (this.allowedMethods == null) {
                    this.allowedMethods = new ArrayList();
                }
            } else if ("ExposeHeader".equals(str)) {
                if (this.exposedHeaders == null) {
                    this.exposedHeaders = new ArrayList();
                }
            } else if ("AllowedHeader".equals(str) && this.allowedHeaders == null) {
                this.allowedHeaders = new LinkedList();
            }
        }
    }

    public static class BucketDirectColdAccessHandler extends DefaultXmlHandler {
        private BucketDirectColdAccess access = new BucketDirectColdAccess();

        @Override
        public void endElement(String str, String str2) {
            if ("Status".equals(str)) {
                this.access.setStatus(RuleStatusEnum.getValueFromCode(str2));
            }
        }

        public BucketDirectColdAccess getBucketDirectColdAccess() {
            return this.access;
        }
    }

    public static class BucketEncryptionHandler extends DefaultXmlHandler {
        protected BucketEncryption encryption;

        @Override
        public void endElement(String str, String str2) {
            try {
                if (str.equals("SSEAlgorithm")) {
                    this.encryption.setSseAlgorithm(SSEAlgorithmEnum.getValueFromCode(str2.replace("aws:", "")));
                } else if (str.equals("KMSMasterKeyID")) {
                    this.encryption.setKmsKeyId(str2);
                }
            } catch (NullPointerException e) {
                if (XmlResponsesSaxParser.log.isWarnEnabled()) {
                    XmlResponsesSaxParser.log.warn("Response xml is not well-formt", e);
                }
            }
        }

        public BucketEncryption getEncryption() {
            return this.encryption;
        }

        @Override
        public void startElement(String str) {
            if (str.equals("ApplyServerSideEncryptionByDefault")) {
                this.encryption = new BucketEncryption();
            }
        }
    }

    public static class BucketLifecycleConfigurationHandler extends SimpleHandler {
        private LifecycleConfiguration config;
        private LifecycleConfiguration.Rule latestRule;
        private LifecycleConfiguration.TimeEvent latestTimeEvent;

        public BucketLifecycleConfigurationHandler(XMLReader xMLReader) {
            super(xMLReader);
            this.config = new LifecycleConfiguration();
        }

        public void endDate(String str) throws ParseException {
            LifecycleConfiguration.setDate(this.latestTimeEvent, ServiceUtils.parseIso8601Date(str));
        }

        public void endDays(String str) {
            LifecycleConfiguration.setDays(this.latestTimeEvent, Integer.valueOf(Integer.parseInt(str)));
        }

        public void endID(String str) {
            this.latestRule.setId(str);
        }

        public void endNoncurrentDays(String str) {
            LifecycleConfiguration.setDays(this.latestTimeEvent, Integer.valueOf(Integer.parseInt(str)));
        }

        public void endPrefix(String str) {
            this.latestRule.setPrefix(str);
        }

        public void endRule(String str) {
            this.config.addRule(this.latestRule);
        }

        public void endStatus(String str) {
            this.latestRule.setEnabled(Boolean.valueOf("Enabled".equals(str)));
        }

        public void endStorageClass(String str) {
            LifecycleConfiguration.setStorageClass(this.latestTimeEvent, StorageClassEnum.getValueFromCode(str));
        }

        public LifecycleConfiguration getLifecycleConfig() {
            return this.config;
        }

        public void startExpiration() {
            LifecycleConfiguration lifecycleConfiguration = this.config;
            Objects.requireNonNull(lifecycleConfiguration);
            LifecycleConfiguration.Expiration expiration = lifecycleConfiguration.new Expiration();
            this.latestTimeEvent = expiration;
            this.latestRule.setExpiration(expiration);
        }

        public void startNoncurrentVersionExpiration() {
            LifecycleConfiguration lifecycleConfiguration = this.config;
            Objects.requireNonNull(lifecycleConfiguration);
            LifecycleConfiguration.NoncurrentVersionExpiration noncurrentVersionExpiration = lifecycleConfiguration.new NoncurrentVersionExpiration();
            this.latestTimeEvent = noncurrentVersionExpiration;
            this.latestRule.setNoncurrentVersionExpiration(noncurrentVersionExpiration);
        }

        public void startNoncurrentVersionTransition() {
            LifecycleConfiguration lifecycleConfiguration = this.config;
            Objects.requireNonNull(lifecycleConfiguration);
            this.latestTimeEvent = lifecycleConfiguration.new NoncurrentVersionTransition();
            this.latestRule.getNoncurrentVersionTransitions().add((LifecycleConfiguration.NoncurrentVersionTransition) this.latestTimeEvent);
        }

        public void startRule() {
            LifecycleConfiguration lifecycleConfiguration = this.config;
            Objects.requireNonNull(lifecycleConfiguration);
            this.latestRule = lifecycleConfiguration.new Rule();
        }

        public void startTransition() {
            LifecycleConfiguration lifecycleConfiguration = this.config;
            Objects.requireNonNull(lifecycleConfiguration);
            this.latestTimeEvent = lifecycleConfiguration.new Transition();
            this.latestRule.getTransitions().add((LifecycleConfiguration.Transition) this.latestTimeEvent);
        }
    }

    public static class BucketLocationHandler extends DefaultXmlHandler {
        private String location;

        @Override
        public void endElement(String str, String str2) {
            if (str.equals("LocationConstraint") || str.equals("Location")) {
                this.location = str2;
            }
        }

        public String getLocation() {
            return this.location;
        }
    }

    public static class BucketLoggingHandler extends DefaultXmlHandler {
        private BucketLoggingConfiguration bucketLoggingStatus = new BucketLoggingConfiguration();
        private boolean currentDelivered;
        private GranteeInterface currentGrantee;
        private Permission currentPermission;
        private String targetBucket;
        private String targetPrefix;

        @Override
        public void endElement(String str, String str2) {
            if (str.equals("TargetBucket")) {
                this.targetBucket = str2;
                return;
            }
            if (str.equals("TargetPrefix")) {
                this.targetPrefix = str2;
                return;
            }
            if (str.equals("LoggingEnabled")) {
                this.bucketLoggingStatus.setTargetBucketName(this.targetBucket);
                this.bucketLoggingStatus.setLogfilePrefix(this.targetPrefix);
                return;
            }
            if (str.equals("Agency")) {
                this.bucketLoggingStatus.setAgency(str2);
                return;
            }
            if (str.equals("ID")) {
                CanonicalGrantee canonicalGrantee = new CanonicalGrantee();
                this.currentGrantee = canonicalGrantee;
                canonicalGrantee.setIdentifier(str2);
                return;
            }
            if (str.equals("URI") || str.equals("Canned")) {
                GroupGrantee groupGrantee = new GroupGrantee();
                this.currentGrantee = groupGrantee;
                groupGrantee.setIdentifier(str2);
                return;
            }
            if (str.equals("DisplayName")) {
                GranteeInterface granteeInterface = this.currentGrantee;
                if (granteeInterface instanceof CanonicalGrantee) {
                    ((CanonicalGrantee) granteeInterface).setDisplayName(str2);
                    return;
                }
                return;
            }
            if (str.equals("Delivered")) {
                this.currentDelivered = Boolean.parseBoolean(str2);
                return;
            }
            if (str.equals("Permission")) {
                this.currentPermission = Permission.parsePermission(str2);
            } else if (str.equals("Grant")) {
                GrantAndPermission grantAndPermission = new GrantAndPermission(this.currentGrantee, this.currentPermission);
                grantAndPermission.setDelivered(this.currentDelivered);
                this.bucketLoggingStatus.addTargetGrant(grantAndPermission);
            }
        }

        public BucketLoggingConfiguration getBucketLoggingStatus() {
            return this.bucketLoggingStatus;
        }
    }

    public static class BucketNotificationConfigurationHandler extends DefaultXmlHandler {
        private BucketNotificationConfiguration bucketNotificationConfiguration = new BucketNotificationConfiguration();
        private List<EventTypeEnum> events = new ArrayList();
        private AbstractNotification.Filter filter;
        private String id;
        private String ruleName;
        private String ruleValue;
        private String urn;

        @Override
        public void endElement(String str, String str2) {
            try {
                if ("Id".equals(str)) {
                    this.id = str2;
                } else if ("Topic".equals(str) || "FunctionGraph".equals(str)) {
                    this.urn = str2;
                } else if ("Event".equals(str)) {
                    this.events.add(EventTypeEnum.getValueFromCode(str2));
                } else if ("Name".equals(str)) {
                    this.ruleName = str2;
                } else if ("Value".equals(str)) {
                    this.ruleValue = str2;
                } else if ("FilterRule".equals(str)) {
                    this.filter.addFilterRule(this.ruleName, this.ruleValue);
                } else if ("TopicConfiguration".equals(str)) {
                    this.bucketNotificationConfiguration.addTopicConfiguration(new TopicConfiguration(this.id, this.filter, this.urn, this.events));
                    this.events = new ArrayList();
                } else if ("FunctionGraphConfiguration".equals(str)) {
                    this.bucketNotificationConfiguration.addFunctionGraphConfiguration(new FunctionGraphConfiguration(this.id, this.filter, this.urn, this.events));
                    this.events = new ArrayList();
                }
            } catch (NullPointerException e) {
                if (XmlResponsesSaxParser.log.isErrorEnabled()) {
                    XmlResponsesSaxParser.log.error("Response xml is not well-formt", e);
                }
            }
        }

        public BucketNotificationConfiguration getBucketNotificationConfiguration() {
            return this.bucketNotificationConfiguration;
        }

        @Override
        public void startElement(String str) {
            if ("Filter".equals(str)) {
                this.filter = new AbstractNotification.Filter();
            }
        }
    }

    public static class BucketQuotaHandler extends DefaultXmlHandler {
        protected BucketQuota quota;

        @Override
        public void endElement(String str, String str2) {
            BucketQuota bucketQuota;
            if (!str.equals("StorageQuota") || (bucketQuota = this.quota) == null) {
                return;
            }
            bucketQuota.setBucketQuota(Long.parseLong(str2));
        }

        public BucketQuota getQuota() {
            return this.quota;
        }

        @Override
        public void startElement(String str) {
            if (str.equals("Quota")) {
                this.quota = new BucketQuota();
            }
        }
    }

    public static class BucketReplicationConfigurationHandler extends DefaultXmlHandler {
        private ReplicationConfiguration.Rule currentRule;
        private ReplicationConfiguration replicationConfiguration = new ReplicationConfiguration();

        @Override
        public void endElement(String str, String str2) {
            try {
                if ("Agency".equals(str)) {
                    this.replicationConfiguration.setAgency(str2);
                } else if ("Rule".equals(str)) {
                    this.replicationConfiguration.getRules().add(this.currentRule);
                } else if ("ID".equals(str)) {
                    this.currentRule.setId(str2);
                } else if ("Status".equals(str)) {
                    this.currentRule.setStatus(RuleStatusEnum.getValueFromCode(str2));
                } else if ("Prefix".equals(str)) {
                    this.currentRule.setPrefix(str2);
                } else if ("Bucket".equals(str)) {
                    this.currentRule.getDestination().setBucket(str2);
                } else if ("StorageClass".equals(str)) {
                    this.currentRule.getDestination().setObjectStorageClass(StorageClassEnum.getValueFromCode(str2));
                }
            } catch (NullPointerException e) {
                if (XmlResponsesSaxParser.log.isErrorEnabled()) {
                    XmlResponsesSaxParser.log.error("Response xml is not well-formt", e);
                }
            }
        }

        public ReplicationConfiguration getReplicationConfiguration() {
            return this.replicationConfiguration;
        }

        @Override
        public void startElement(String str) {
            if ("Rule".equals(str)) {
                this.currentRule = new ReplicationConfiguration.Rule();
            } else if ("Destination".equals(str)) {
                this.currentRule.setDestination(new ReplicationConfiguration.Destination());
            }
        }
    }

    public static class BucketStorageInfoHandler extends DefaultXmlHandler {
        private BucketStorageInfo storageInfo;

        @Override
        public void endElement(String str, String str2) {
            try {
                if (str.equals("Size")) {
                    this.storageInfo.setSize(Long.parseLong(str2));
                } else if (str.equals("ObjectNumber")) {
                    this.storageInfo.setObjectNumber(Long.parseLong(str2));
                }
            } catch (NullPointerException e) {
                if (XmlResponsesSaxParser.log.isWarnEnabled()) {
                    XmlResponsesSaxParser.log.warn("Response xml is not well-formt", e);
                }
            }
        }

        public BucketStorageInfo getStorageInfo() {
            return this.storageInfo;
        }

        @Override
        public void startElement(String str) {
            if (str.equals("GetBucketStorageInfoResult")) {
                this.storageInfo = new BucketStorageInfo();
            }
        }
    }

    public static class BucketStoragePolicyHandler extends DefaultXmlHandler {
        protected BucketStoragePolicyConfiguration storagePolicyConfiguration;

        @Override
        public void endElement(String str, String str2) {
            BucketStoragePolicyConfiguration bucketStoragePolicyConfiguration;
            if ((str.equals("DefaultStorageClass") || str.equals("StorageClass")) && (bucketStoragePolicyConfiguration = this.storagePolicyConfiguration) != null) {
                bucketStoragePolicyConfiguration.setBucketStorageClass(StorageClassEnum.getValueFromCode(str2));
            }
        }

        public BucketStoragePolicyConfiguration getStoragePolicy() {
            return this.storagePolicyConfiguration;
        }

        @Override
        public void startElement(String str) {
            if (str.equals("StoragePolicy") || str.equals("StorageClass")) {
                this.storagePolicyConfiguration = new BucketStoragePolicyConfiguration();
            }
        }
    }

    public static class BucketTagInfoHandler extends DefaultXmlHandler {
        private String currentKey;
        private String currentValue;
        private BucketTagInfo tagInfo = new BucketTagInfo();

        @Override
        public void endElement(String str, String str2) {
            if ("Key".equals(str)) {
                this.currentKey = str2;
            } else if ("Value".equals(str)) {
                this.currentValue = str2;
            } else if ("Tag".equals(str)) {
                this.tagInfo.getTagSet().addTag(this.currentKey, this.currentValue);
            }
        }

        public BucketTagInfo getBucketTagInfo() {
            return this.tagInfo;
        }
    }

    public static class BucketVersioningHandler extends DefaultXmlHandler {
        private String status;
        private BucketVersioningConfiguration versioningStatus;

        @Override
        public void endElement(String str, String str2) {
            if (str.equals("Status")) {
                this.status = str2;
            } else if (str.equals("VersioningConfiguration")) {
                this.versioningStatus = new BucketVersioningConfiguration(VersioningStatusEnum.getValueFromCode(this.status));
            }
        }

        public BucketVersioningConfiguration getVersioningStatus() {
            return this.versioningStatus;
        }
    }

    public static class BucketWebsiteConfigurationHandler extends DefaultXmlHandler {
        private WebsiteConfiguration config = new WebsiteConfiguration();
        private RouteRuleCondition currentCondition;
        private RedirectAllRequest currentRedirectAllRule;
        private Redirect currentRedirectRule;
        private RouteRule currentRoutingRule;

        @Override
        public void endElement(String str, String str2) {
            try {
                if (str.equals("Suffix")) {
                    this.config.setSuffix(str2);
                } else if (str.equals("Key")) {
                    this.config.setKey(str2);
                } else if (str.equals("KeyPrefixEquals")) {
                    this.currentCondition.setKeyPrefixEquals(str2);
                } else if (str.equals("HttpErrorCodeReturnedEquals")) {
                    this.currentCondition.setHttpErrorCodeReturnedEquals(str2);
                } else if (str.equals("Protocol")) {
                    if (this.currentRedirectAllRule != null) {
                        this.currentRedirectAllRule.setRedirectProtocol(ProtocolEnum.getValueFromCode(str2));
                    } else if (this.currentRedirectRule != null) {
                        this.currentRedirectRule.setRedirectProtocol(ProtocolEnum.getValueFromCode(str2));
                    }
                } else if (str.equals("HostName")) {
                    if (this.currentRedirectAllRule != null) {
                        this.currentRedirectAllRule.setHostName(str2);
                    } else if (this.currentRedirectRule != null) {
                        this.currentRedirectRule.setHostName(str2);
                    }
                } else if (str.equals("ReplaceKeyPrefixWith")) {
                    this.currentRedirectRule.setReplaceKeyPrefixWith(str2);
                } else if (str.equals("ReplaceKeyWith")) {
                    this.currentRedirectRule.setReplaceKeyWith(str2);
                } else if (str.equals("HttpRedirectCode")) {
                    this.currentRedirectRule.setHttpRedirectCode(str2);
                }
            } catch (NullPointerException e) {
                if (XmlResponsesSaxParser.log.isErrorEnabled()) {
                    XmlResponsesSaxParser.log.error("Response xml is not well-formt", e);
                }
            }
        }

        public WebsiteConfiguration getWebsiteConfig() {
            return this.config;
        }

        @Override
        public void startElement(String str) {
            if (str.equals("RedirectAllRequestsTo")) {
                RedirectAllRequest redirectAllRequest = new RedirectAllRequest();
                this.currentRedirectAllRule = redirectAllRequest;
                this.config.setRedirectAllRequestsTo(redirectAllRequest);
            } else if (str.equals("RoutingRule")) {
                this.currentRoutingRule = new RouteRule();
                this.config.getRouteRules().add(this.currentRoutingRule);
            } else if (str.equals("Condition")) {
                RouteRuleCondition routeRuleCondition = new RouteRuleCondition();
                this.currentCondition = routeRuleCondition;
                this.currentRoutingRule.setCondition(routeRuleCondition);
            } else if (str.equals("Redirect")) {
                Redirect redirect = new Redirect();
                this.currentRedirectRule = redirect;
                this.currentRoutingRule.setRedirect(redirect);
            }
        }
    }

    public static class CompleteMultipartUploadHandler extends SimpleHandler {
        private String bucketName;
        private String etag;
        private String location;
        private String objectKey;

        public CompleteMultipartUploadHandler(XMLReader xMLReader) {
            super(xMLReader);
        }

        public void endBucket(String str) {
            this.bucketName = str;
        }

        public void endETag(String str) {
            this.etag = str;
        }

        public void endKey(String str) {
            this.objectKey = str;
        }

        public void endLocation(String str) {
            this.location = str;
        }

        public String getBucketName() {
            return this.bucketName;
        }

        public String getEtag() {
            return this.etag;
        }

        public String getLocation() {
            return this.location;
        }

        public String getObjectKey() {
            return this.objectKey;
        }
    }

    public static class CopyObjectResultHandler extends DefaultXmlHandler {
        private String etag;
        private Date lastModified;

        @Override
        public void endElement(String str, String str2) {
            if (!str.equals("LastModified")) {
                if (str.equals("ETag")) {
                    this.etag = str2;
                    return;
                }
                return;
            }
            try {
                this.lastModified = ServiceUtils.parseIso8601Date(str2);
            } catch (ParseException e) {
                if (XmlResponsesSaxParser.log.isErrorEnabled()) {
                    XmlResponsesSaxParser.log.error("Non-ISO8601 date for LastModified in copy object output: " + str2, e);
                }
            }
        }

        public String getETag() {
            return this.etag;
        }

        public Date getLastModified() {
            return this.lastModified;
        }
    }

    public static class CopyPartResultHandler extends SimpleHandler {
        private String etag;
        private Date lastModified;

        public CopyPartResultHandler(XMLReader xMLReader) {
            super(xMLReader);
        }

        public void endETag(String str) {
            this.etag = str;
        }

        public void endLastModified(String str) {
            try {
                this.lastModified = ServiceUtils.parseIso8601Date(str);
            } catch (ParseException unused) {
            }
        }

        public CopyPartResult getCopyPartResult(int i) {
            return new CopyPartResult(i, this.etag, this.lastModified);
        }
    }

    public static class DeleteObjectsHandler extends DefaultXmlHandler {
        private String deleteMarkerVersion;
        private String errorCode;
        private String key;
        private String message;
        private DeleteObjectsResult result;
        private String version;
        private boolean withDeleteMarker;
        private List<DeleteObjectsResult.DeleteObjectResult> deletedObjectResults = new ArrayList();
        private List<DeleteObjectsResult.ErrorResult> errorResults = new ArrayList();

        @Override
        public void endElement(String str, String str2) {
            if ("Key".equals(str)) {
                this.key = str2;
                return;
            }
            if ("VersionId".equals(str)) {
                this.version = str2;
                return;
            }
            if ("DeleteMarker".equals(str)) {
                this.withDeleteMarker = Boolean.parseBoolean(str2);
                return;
            }
            if ("DeleteMarkerVersionId".equals(str)) {
                this.deleteMarkerVersion = str2;
                return;
            }
            if ("Code".equals(str)) {
                this.errorCode = str2;
                return;
            }
            if ("Message".equals(str)) {
                this.message = str2;
                return;
            }
            if ("Deleted".equals(str)) {
                DeleteObjectsResult deleteObjectsResult = this.result;
                Objects.requireNonNull(deleteObjectsResult);
                this.deletedObjectResults.add(deleteObjectsResult.new DeleteObjectResult(this.key, this.version, this.withDeleteMarker, this.deleteMarkerVersion));
                this.deleteMarkerVersion = null;
                this.version = null;
                this.key = null;
                this.withDeleteMarker = false;
                return;
            }
            if (!"Error".equals(str)) {
                if (str.equals("DeleteResult")) {
                    this.result.getDeletedObjectResults().addAll(this.deletedObjectResults);
                    this.result.getErrorResults().addAll(this.errorResults);
                    return;
                }
                return;
            }
            List<DeleteObjectsResult.ErrorResult> list = this.errorResults;
            DeleteObjectsResult deleteObjectsResult2 = this.result;
            Objects.requireNonNull(deleteObjectsResult2);
            list.add(deleteObjectsResult2.new ErrorResult(this.key, this.version, this.errorCode, this.message));
            this.message = null;
            this.errorCode = null;
            this.version = null;
            this.key = null;
        }

        public DeleteObjectsResult getMultipleDeleteResult() {
            return this.result;
        }

        @Override
        public void startElement(String str) {
            if (str.equals("DeleteResult")) {
                this.result = new DeleteObjectsResult();
            }
        }
    }

    public static class InitiateMultipartUploadHandler extends SimpleHandler {
        private String bucketName;
        private String objectKey;
        private String uploadId;

        public InitiateMultipartUploadHandler(XMLReader xMLReader) {
            super(xMLReader);
        }

        public void endBucket(String str) {
            this.bucketName = str;
        }

        public void endKey(String str) {
            this.objectKey = str;
        }

        public void endUploadId(String str) {
            this.uploadId = str;
        }

        public InitiateMultipartUploadResult getInitiateMultipartUploadResult() {
            return new InitiateMultipartUploadResult(this.bucketName, this.objectKey, this.uploadId);
        }
    }

    public static class ListBucketsHandler extends DefaultXmlHandler {
        private final List<ObsBucket> buckets = new ArrayList();
        private Owner bucketsOwner;
        private ObsBucket currentBucket;

        @Override
        public void endElement(String str, String str2) {
            try {
                if (str.equals("ID")) {
                    this.bucketsOwner.setId(str2);
                } else if (str.equals("DisplayName")) {
                    this.bucketsOwner.setDisplayName(str2);
                } else if (str.equals("Bucket")) {
                    this.currentBucket.setOwner(this.bucketsOwner);
                    this.buckets.add(this.currentBucket);
                } else if (str.equals("Name")) {
                    this.currentBucket.setBucketName(str2);
                } else if (str.equals("Location")) {
                    this.currentBucket.setLocation(str2);
                } else if (str.equals("CreationDate")) {
                    String str3 = str2 + ".000Z";
                    try {
                        this.currentBucket.setCreationDate(ServiceUtils.parseIso8601Date(str3));
                    } catch (ParseException e) {
                        if (XmlResponsesSaxParser.log.isWarnEnabled()) {
                            XmlResponsesSaxParser.log.warn("Non-ISO8601 date for CreationDate in list buckets output: " + str3, e);
                        }
                    }
                }
            } catch (NullPointerException e2) {
                if (XmlResponsesSaxParser.log.isErrorEnabled()) {
                    XmlResponsesSaxParser.log.error("Response xml is not well-formt", e2);
                }
            }
        }

        public List<ObsBucket> getBuckets() {
            return this.buckets;
        }

        public Owner getOwner() {
            return this.bucketsOwner;
        }

        @Override
        public void startElement(String str) {
            if (str.equals("Bucket")) {
                this.currentBucket = new ObsBucket();
            } else if (str.equals("Owner")) {
                this.bucketsOwner = new Owner();
            }
        }
    }

    public static class ListMultipartUploadsHandler extends SimpleHandler {
        private String bucketName;
        private final List<String> commonPrefixes;
        private String delimiter;
        private boolean insideCommonPrefixes;
        private boolean isTruncated;
        private String keyMarker;
        private int maxUploads;
        private String nextKeyMarker;
        private String nextUploadIdMarker;
        private String prefix;
        private String uploadIdMarker;
        private final List<MultipartUpload> uploads;

        public ListMultipartUploadsHandler(XMLReader xMLReader) {
            super(xMLReader);
            this.uploads = new ArrayList();
            this.commonPrefixes = new ArrayList();
            this.isTruncated = false;
        }

        @Override
        public void controlReturned(SimpleHandler simpleHandler) {
            this.uploads.add(((MultipartUploadHandler) simpleHandler).getMultipartUpload());
        }

        public void endBucket(String str) {
            this.bucketName = str;
        }

        public void endCommonPrefixes() {
            this.insideCommonPrefixes = false;
        }

        public void endDelimiter(String str) {
            this.delimiter = str;
        }

        public void endIsTruncated(String str) {
            this.isTruncated = Boolean.parseBoolean(str);
        }

        public void endKeyMarker(String str) {
            this.keyMarker = str;
        }

        public void endMaxUploads(String str) {
            try {
                this.maxUploads = Integer.parseInt(str);
            } catch (Exception e) {
                if (XmlResponsesSaxParser.log.isErrorEnabled()) {
                    XmlResponsesSaxParser.log.error("Response xml is not well-format", e);
                }
            }
        }

        public void endNextKeyMarker(String str) {
            this.nextKeyMarker = str;
        }

        public void endNextUploadIdMarker(String str) {
            this.nextUploadIdMarker = str;
        }

        public void endPrefix(String str) {
            if (this.insideCommonPrefixes) {
                this.commonPrefixes.add(str);
            } else {
                this.prefix = str;
            }
        }

        public void endUploadIdMarker(String str) {
            this.uploadIdMarker = str;
        }

        public String getBucketName() {
            return this.bucketName;
        }

        public List<String> getCommonPrefixes() {
            return this.commonPrefixes;
        }

        public String getDelimiter() {
            return this.delimiter;
        }

        public String getKeyMarker() {
            return this.keyMarker;
        }

        public int getMaxUploads() {
            return this.maxUploads;
        }

        public List<MultipartUpload> getMultipartUploadList() {
            Iterator<MultipartUpload> it = this.uploads.iterator();
            while (it.hasNext()) {
                it.next().setBucketName(this.bucketName);
            }
            return this.uploads;
        }

        public String getNextKeyMarker() {
            return this.nextKeyMarker;
        }

        public String getNextUploadIdMarker() {
            return this.nextUploadIdMarker;
        }

        public String getPrefix() {
            return this.prefix;
        }

        public String getUploadIdMarker() {
            return this.uploadIdMarker;
        }

        public boolean isTruncated() {
            return this.isTruncated;
        }

        public void startCommonPrefixes() {
            this.insideCommonPrefixes = true;
        }

        public void startUpload() {
            transferControlToHandler(new MultipartUploadHandler(this.xr));
        }
    }

    public static class ListObjectsHandler extends DefaultXmlHandler {
        private String bucketName;
        private ObsObject currentObject;
        private Owner currentOwner;
        private String lastKey;
        private String nextMarker;
        private String requestDelimiter;
        private String requestMarker;
        private String requestPrefix;
        private boolean insideCommonPrefixes = false;
        private final List<ObsObject> objects = new ArrayList();
        private final List<String> commonPrefixes = new ArrayList();
        private int requestMaxKeys = 0;
        private boolean listingTruncated = false;

        @Override
        public void endElement(String str, String str2) {
            try {
                if (str.equals("Name")) {
                    this.bucketName = str2;
                    return;
                }
                if (!this.insideCommonPrefixes && str.equals("Prefix")) {
                    this.requestPrefix = str2;
                    return;
                }
                if (str.equals("Marker")) {
                    this.requestMarker = str2;
                    return;
                }
                if (str.equals("NextMarker")) {
                    this.nextMarker = str2;
                    return;
                }
                if (str.equals("MaxKeys")) {
                    this.requestMaxKeys = Integer.parseInt(str2);
                    return;
                }
                if (str.equals("Delimiter")) {
                    this.requestDelimiter = str2;
                    return;
                }
                if (str.equals("IsTruncated")) {
                    this.listingTruncated = Boolean.valueOf(str2).booleanValue();
                    return;
                }
                if (str.equals("Contents")) {
                    this.objects.add(this.currentObject);
                    return;
                }
                if (str.equals("Key")) {
                    this.currentObject.setObjectKey(str2);
                    this.lastKey = str2;
                    return;
                }
                if (str.equals("LastModified")) {
                    try {
                        this.currentObject.getMetadata().setLastModified(ServiceUtils.parseIso8601Date(str2));
                        return;
                    } catch (ParseException e) {
                        if (XmlResponsesSaxParser.log.isErrorEnabled()) {
                            XmlResponsesSaxParser.log.error("Non-ISO8601 date for LastModified in bucket's object listing output: " + str2, e);
                            return;
                        }
                        return;
                    }
                }
                if (str.equals("ETag")) {
                    this.currentObject.getMetadata().setEtag(str2);
                    return;
                }
                if (str.equals("Size")) {
                    this.currentObject.getMetadata().setContentLength(Long.valueOf(Long.parseLong(str2)));
                    return;
                }
                if (str.equals("StorageClass")) {
                    this.currentObject.getMetadata().setObjectStorageClass(StorageClassEnum.getValueFromCode(str2));
                    return;
                }
                if (str.equals("ID")) {
                    if (this.currentOwner == null) {
                        this.currentOwner = new Owner();
                    }
                    this.currentObject.setOwner(this.currentOwner);
                    this.currentOwner.setId(str2);
                    return;
                }
                if (str.equals("Type")) {
                    this.currentObject.getMetadata().setAppendable("Appendable".equals(str2));
                    return;
                }
                if (str.equals("DisplayName")) {
                    if (this.currentOwner != null) {
                        this.currentOwner.setDisplayName(str2);
                    }
                } else if (this.insideCommonPrefixes && str.equals("Prefix")) {
                    this.commonPrefixes.add(str2);
                } else if (str.equals("CommonPrefixes")) {
                    this.insideCommonPrefixes = false;
                }
            } catch (NullPointerException e2) {
                if (XmlResponsesSaxParser.log.isErrorEnabled()) {
                    XmlResponsesSaxParser.log.error("Response xml is not well-formt", e2);
                }
            }
        }

        public String getBucketName() {
            return this.bucketName;
        }

        public List<String> getCommonPrefixes() {
            return this.commonPrefixes;
        }

        public String getMarkerForNextListing() {
            if (!this.listingTruncated) {
                return null;
            }
            String str = this.nextMarker;
            return str == null ? this.lastKey : str;
        }

        public String getNextMarker() {
            return this.nextMarker;
        }

        public List<ObsObject> getObjects() {
            return this.objects;
        }

        public String getRequestDelimiter() {
            return this.requestDelimiter;
        }

        public String getRequestMarker() {
            return this.requestMarker;
        }

        public int getRequestMaxKeys() {
            return this.requestMaxKeys;
        }

        public String getRequestPrefix() {
            return this.requestPrefix;
        }

        public boolean isListingTruncated() {
            return this.listingTruncated;
        }

        @Override
        public void startElement(String str) {
            if (str.equals("Contents")) {
                ObsObject obsObject = new ObsObject();
                this.currentObject = obsObject;
                obsObject.setBucketName(this.bucketName);
            } else if (str.equals("Owner")) {
                this.currentOwner = new Owner();
            } else if (str.equals("CommonPrefixes")) {
                this.insideCommonPrefixes = true;
            }
        }
    }

    public static class ListPartsHandler extends SimpleHandler {
        private String bucketName;
        private boolean inInitiator;
        private Owner initiator;
        private boolean isTruncated;
        private int maxParts;
        private String nextPartNumberMarker;
        private String objectKey;
        private Owner owner;
        private String partNumberMarker;
        private final List<Multipart> parts;
        private String storageClass;
        private String uploadId;

        public ListPartsHandler(XMLReader xMLReader) {
            super(xMLReader);
            this.parts = new ArrayList();
            this.isTruncated = false;
            this.inInitiator = false;
        }

        @Override
        public void controlReturned(SimpleHandler simpleHandler) {
            if (simpleHandler instanceof PartResultHandler) {
                this.parts.add(((PartResultHandler) simpleHandler).getMultipartPart());
            } else if (this.inInitiator) {
                this.initiator = ((OwnerHandler) simpleHandler).getOwner();
            } else {
                this.owner = ((OwnerHandler) simpleHandler).getOwner();
            }
        }

        public void endBucket(String str) {
            this.bucketName = str;
        }

        public void endIsTruncated(String str) {
            this.isTruncated = Boolean.parseBoolean(str);
        }

        public void endKey(String str) {
            this.objectKey = str;
        }

        public void endMaxParts(String str) {
            this.maxParts = Integer.parseInt(str);
        }

        public void endNextPartNumberMarker(String str) {
            this.nextPartNumberMarker = str;
        }

        public void endPartNumberMarker(String str) {
            this.partNumberMarker = str;
        }

        public void endStorageClass(String str) {
            this.storageClass = str;
        }

        public void endUploadId(String str) {
            this.uploadId = str;
        }

        public String getBucketName() {
            return this.bucketName;
        }

        public Owner getInitiator() {
            return this.initiator;
        }

        public int getMaxParts() {
            return this.maxParts;
        }

        public List<Multipart> getMultiPartList() {
            return this.parts;
        }

        public String getNextPartNumberMarker() {
            return this.nextPartNumberMarker;
        }

        public String getObjectKey() {
            return this.objectKey;
        }

        public Owner getOwner() {
            return this.owner;
        }

        public String getPartNumberMarker() {
            return this.partNumberMarker;
        }

        public String getStorageClass() {
            return this.storageClass;
        }

        public String getUploadId() {
            return this.uploadId;
        }

        public boolean isTruncated() {
            return this.isTruncated;
        }

        public void startInitiator() {
            this.inInitiator = true;
            transferControlToHandler(new OwnerHandler(this.xr));
        }

        public void startOwner() {
            this.inInitiator = false;
            transferControlToHandler(new OwnerHandler(this.xr));
        }

        public void startPart() {
            transferControlToHandler(new PartResultHandler(this.xr));
        }
    }

    public static class ListVersionsHandler extends DefaultXmlHandler {
        private String bucketName;
        private String delimiter;
        private String etag;
        private boolean isAppendable;
        private String key;
        private String keyMarker;
        private Date lastModified;
        private String nextMarker;
        private String nextVersionIdMarker;
        private Owner owner;
        private String requestPrefix;
        private String storageClass;
        private String versionId;
        private String versionIdMarker;
        private final List<VersionOrDeleteMarker> items = new ArrayList();
        private final List<String> commonPrefixes = new ArrayList();
        private boolean isLatest = false;
        private long size = 0;
        private boolean insideCommonPrefixes = false;
        private long requestMaxKeys = 0;
        private boolean listingTruncated = false;

        private void reset() {
            this.key = null;
            this.versionId = null;
            this.isLatest = false;
            this.lastModified = null;
            this.etag = null;
            this.isAppendable = false;
            this.size = 0L;
            this.storageClass = null;
            this.owner = null;
        }

        @Override
        public void endElement(String str, String str2) {
            try {
                if (str.equals("Name")) {
                    this.bucketName = str2;
                    return;
                }
                if (!this.insideCommonPrefixes && str.equals("Prefix")) {
                    this.requestPrefix = str2;
                    return;
                }
                if (str.equals("KeyMarker")) {
                    this.keyMarker = str2;
                    return;
                }
                if (str.equals("NextKeyMarker")) {
                    this.nextMarker = str2;
                    return;
                }
                if (str.equals("VersionIdMarker")) {
                    this.versionIdMarker = str2;
                    return;
                }
                if (str.equals("NextVersionIdMarker")) {
                    this.nextVersionIdMarker = str2;
                    return;
                }
                if (str.equals("MaxKeys")) {
                    this.requestMaxKeys = Long.parseLong(str2);
                    return;
                }
                if (str.equals("IsTruncated")) {
                    this.listingTruncated = Boolean.valueOf(str2).booleanValue();
                    return;
                }
                if (str.equals("Delimiter")) {
                    this.delimiter = str2;
                    return;
                }
                if (str.equals("Version")) {
                    this.items.add(new VersionOrDeleteMarker(this.bucketName, this.key, this.versionId, this.isLatest, this.lastModified, this.owner, this.etag, this.size, StorageClassEnum.getValueFromCode(this.storageClass), false, this.isAppendable));
                    reset();
                    return;
                }
                if (str.equals("DeleteMarker")) {
                    this.items.add(new VersionOrDeleteMarker(this.bucketName, this.key, this.versionId, this.isLatest, this.lastModified, this.owner, null, 0L, null, true, false));
                    reset();
                    return;
                }
                if (str.equals("Key")) {
                    this.key = str2;
                    return;
                }
                if (str.equals("VersionId")) {
                    this.versionId = str2;
                    return;
                }
                if (str.equals("IsLatest")) {
                    this.isLatest = Boolean.valueOf(str2).booleanValue();
                    return;
                }
                if (str.equals("LastModified")) {
                    try {
                        this.lastModified = ServiceUtils.parseIso8601Date(str2);
                        return;
                    } catch (ParseException e) {
                        if (XmlResponsesSaxParser.log.isWarnEnabled()) {
                            XmlResponsesSaxParser.log.warn("Non-ISO8601 date for LastModified in bucket's versions listing output: " + str2, e);
                            return;
                        }
                        return;
                    }
                }
                if (str.equals("ETag")) {
                    this.etag = str2;
                    return;
                }
                if (str.equals("Size")) {
                    this.size = Long.parseLong(str2);
                    return;
                }
                if (str.equals("StorageClass")) {
                    this.storageClass = str2;
                    return;
                }
                if (str.equals("Type")) {
                    this.isAppendable = "Appendable".equals(str2);
                    return;
                }
                if (str.equals("ID")) {
                    if (this.owner == null) {
                        this.owner = new Owner();
                    }
                    this.owner.setId(str2);
                } else if (str.equals("DisplayName")) {
                    if (this.owner != null) {
                        this.owner.setDisplayName(str2);
                    }
                } else if (this.insideCommonPrefixes && str.equals("Prefix")) {
                    this.commonPrefixes.add(str2);
                } else if (str.equals("CommonPrefixes")) {
                    this.insideCommonPrefixes = false;
                }
            } catch (NullPointerException e2) {
                if (XmlResponsesSaxParser.log.isErrorEnabled()) {
                    XmlResponsesSaxParser.log.error("Response xml is not well-formt", e2);
                }
            }
        }

        public String getBucketName() {
            return this.bucketName;
        }

        public List<String> getCommonPrefixes() {
            return this.commonPrefixes;
        }

        public String getDelimiter() {
            return this.delimiter;
        }

        public List<VersionOrDeleteMarker> getItems() {
            return this.items;
        }

        public String getKeyMarker() {
            return this.keyMarker;
        }

        public String getNextKeyMarker() {
            return this.nextMarker;
        }

        public String getNextVersionIdMarker() {
            return this.nextVersionIdMarker;
        }

        public long getRequestMaxKeys() {
            return this.requestMaxKeys;
        }

        public String getRequestPrefix() {
            return this.requestPrefix;
        }

        public String getVersionIdMarker() {
            return this.versionIdMarker;
        }

        public boolean isListingTruncated() {
            return this.listingTruncated;
        }

        @Override
        public void startElement(String str) {
            if (str.equals("Owner")) {
                this.owner = new Owner();
            } else if (str.equals("CommonPrefixes")) {
                this.insideCommonPrefixes = true;
            }
        }
    }

    public static class MultipartUploadHandler extends SimpleHandler {
        private boolean inInitiator;
        private Date initiatedDate;
        private Owner initiator;
        private String objectKey;
        private Owner owner;
        private String storageClass;
        private String uploadId;

        public MultipartUploadHandler(XMLReader xMLReader) {
            super(xMLReader);
            this.inInitiator = false;
        }

        @Override
        public void controlReturned(SimpleHandler simpleHandler) {
            if (this.inInitiator) {
                this.owner = ((OwnerHandler) simpleHandler).getOwner();
            } else {
                this.initiator = ((OwnerHandler) simpleHandler).getOwner();
            }
        }

        public void endInitiated(String str) {
            try {
                this.initiatedDate = ServiceUtils.parseIso8601Date(str);
            } catch (ParseException unused) {
            }
        }

        public void endKey(String str) {
            this.objectKey = str;
        }

        public void endStorageClass(String str) {
            this.storageClass = str;
        }

        public void endUpload(String str) {
            returnControlToParentHandler();
        }

        public void endUploadId(String str) {
            this.uploadId = str;
        }

        public MultipartUpload getMultipartUpload() {
            return new MultipartUpload(this.uploadId, this.objectKey, this.initiatedDate, StorageClassEnum.getValueFromCode(this.storageClass), this.owner, this.initiator);
        }

        public void startInitiator() {
            this.inInitiator = true;
            transferControlToHandler(new OwnerHandler(this.xr));
        }

        public void startOwner() {
            this.inInitiator = false;
            transferControlToHandler(new OwnerHandler(this.xr));
        }
    }

    public static class OwnerHandler extends SimpleHandler {
        private String displayName;
        private String id;

        public OwnerHandler(XMLReader xMLReader) {
            super(xMLReader);
        }

        public void endDisplayName(String str) {
            this.displayName = str;
        }

        public void endID(String str) {
            this.id = str;
        }

        public void endInitiator(String str) {
            returnControlToParentHandler();
        }

        public void endOwner(String str) {
            returnControlToParentHandler();
        }

        public Owner getOwner() {
            Owner owner = new Owner();
            owner.setId(this.id);
            owner.setDisplayName(this.displayName);
            return owner;
        }
    }

    public static class PartResultHandler extends SimpleHandler {
        private String etag;
        private Date lastModified;
        private int partNumber;
        private long size;

        public PartResultHandler(XMLReader xMLReader) {
            super(xMLReader);
        }

        public void endETag(String str) {
            this.etag = str;
        }

        public void endLastModified(String str) {
            try {
                this.lastModified = ServiceUtils.parseIso8601Date(str);
            } catch (ParseException unused) {
            }
        }

        public void endPart(String str) {
            returnControlToParentHandler();
        }

        public void endPartNumber(String str) {
            this.partNumber = Integer.parseInt(str);
        }

        public void endSize(String str) {
            this.size = Long.parseLong(str);
        }

        public Multipart getMultipartPart() {
            return new Multipart(Integer.valueOf(this.partNumber), this.lastModified, this.etag, Long.valueOf(this.size));
        }
    }

    public static class RequestPaymentConfigurationHandler extends DefaultXmlHandler {
        private String payer = null;

        @Override
        public void endElement(String str, String str2) {
            if (str.equals("Payer")) {
                this.payer = str2;
            }
        }

        public boolean isRequesterPays() {
            return "Requester".equals(this.payer);
        }
    }

    public <T> T parse(InputStream inputStream, Class<T> cls, boolean z) throws ServiceException {
        try {
            T tNewInstance = SimpleHandler.class.isAssignableFrom(cls) ? cls.getConstructor(XMLReader.class).newInstance(this.xr) : cls.getConstructor(new Class[0]).newInstance(new Object[0]);
            if (tNewInstance instanceof DefaultHandler) {
                if (z) {
                    inputStream = sanitizeXmlDocument(inputStream);
                }
                parseXmlInputStream(tNewInstance, inputStream);
            }
            return tNewInstance;
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e2) {
            throw new ServiceException(e2);
        }
    }

    protected void parseXmlInputStream(DefaultHandler defaultHandler, InputStream inputStream) throws IOException, ServiceException {
        if (inputStream == null) {
            return;
        }
        try {
            try {
                if (log.isDebugEnabled()) {
                    log.debug((CharSequence) ("Parsing XML response document with handler: " + defaultHandler.getClass()));
                }
                this.xr.setContentHandler(defaultHandler);
                this.xr.setErrorHandler(defaultHandler);
                this.xr.parse(new InputSource(inputStream));
            } catch (Exception e) {
                throw new ServiceException("Failed to parse XML document with handler " + defaultHandler.getClass(), e);
            }
        } finally {
            ServiceUtils.closeStream(inputStream);
        }
    }

    protected InputStream sanitizeXmlDocument(InputStream inputStream) throws IOException, ServiceException {
        BufferedReader bufferedReader = null;
        if (inputStream == null) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            try {
                char[] cArr = new char[8192];
                while (true) {
                    int i = bufferedReader2.read(cArr);
                    if (i == -1) {
                        break;
                    }
                    sb.append(cArr, 0, i);
                }
                String strReplaceAll = sb.toString().replaceAll("\r", "&#013;");
                if (log.isTraceEnabled()) {
                    log.trace((CharSequence) ("Response entity: " + strReplaceAll));
                }
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(strReplaceAll.getBytes("UTF-8"));
                ServiceUtils.closeStream(bufferedReader2);
                ServiceUtils.closeStream(inputStream);
                return byteArrayInputStream;
            } catch (Throwable th) {
                th = th;
                bufferedReader = bufferedReader2;
                try {
                    throw new ServiceException("Failed to sanitize XML document destined", th);
                } catch (Throwable th2) {
                    ServiceUtils.closeStream(bufferedReader);
                    ServiceUtils.closeStream(inputStream);
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }
}
