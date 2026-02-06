package com.obs.services.internal;

import com.obs.log.ILogger;
import com.obs.log.LoggerBuilder;
import com.obs.services.internal.handler.XmlResponsesSaxParser;
import com.obs.services.internal.io.HttpMethodReleaseInputStream;
import com.obs.services.internal.io.ProgressInputStream;
import com.obs.services.internal.task.BlockRejectedExecutionHandler;
import com.obs.services.internal.task.DefaultTaskProgressStatus;
import com.obs.services.internal.utils.AbstractAuthentication;
import com.obs.services.internal.utils.JSONChange;
import com.obs.services.internal.utils.Mimetypes;
import com.obs.services.internal.utils.RestUtils;
import com.obs.services.internal.utils.ServiceUtils;
import com.obs.services.internal.utils.V2Authentication;
import com.obs.services.internal.utils.V4Authentication;
import com.obs.services.model.AbstractBulkRequest;
import com.obs.services.model.AbstractTemporarySignatureRequest;
import com.obs.services.model.AccessControlList;
import com.obs.services.model.AppendObjectRequest;
import com.obs.services.model.AppendObjectResult;
import com.obs.services.model.AuthTypeEnum;
import com.obs.services.model.AvailableZoneEnum;
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
import com.obs.services.model.ExtensionBucketPermissionEnum;
import com.obs.services.model.ExtensionObjectPermissionEnum;
import com.obs.services.model.GetObjectMetadataRequest;
import com.obs.services.model.GetObjectRequest;
import com.obs.services.model.GrantAndPermission;
import com.obs.services.model.GroupGrantee;
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
import com.obs.services.model.Multipart;
import com.obs.services.model.MultipartUploadListing;
import com.obs.services.model.ObjectListing;
import com.obs.services.model.ObjectMetadata;
import com.obs.services.model.ObsBucket;
import com.obs.services.model.ObsObject;
import com.obs.services.model.OptionsInfoRequest;
import com.obs.services.model.Owner;
import com.obs.services.model.Permission;
import com.obs.services.model.PolicyTempSignatureRequest;
import com.obs.services.model.PostSignatureRequest;
import com.obs.services.model.PostSignatureResponse;
import com.obs.services.model.ProgressListener;
import com.obs.services.model.PutObjectBasicRequest;
import com.obs.services.model.PutObjectRequest;
import com.obs.services.model.ReadAheadQueryResult;
import com.obs.services.model.ReadAheadRequest;
import com.obs.services.model.ReadAheadResult;
import com.obs.services.model.ReplicationConfiguration;
import com.obs.services.model.RestoreObjectRequest;
import com.obs.services.model.RestoreObjectResult;
import com.obs.services.model.SetObjectMetadataRequest;
import com.obs.services.model.SpecialParamEnum;
import com.obs.services.model.SseCHeader;
import com.obs.services.model.SseKmsHeader;
import com.obs.services.model.StorageClassEnum;
import com.obs.services.model.TaskCallback;
import com.obs.services.model.TaskProgressListener;
import com.obs.services.model.TemporarySignatureRequest;
import com.obs.services.model.TemporarySignatureResponse;
import com.obs.services.model.UploadPartRequest;
import com.obs.services.model.UploadPartResult;
import com.obs.services.model.V4PostSignatureResponse;
import com.obs.services.model.VersionOrDeleteMarker;
import com.obs.services.model.VersioningStatusEnum;
import com.obs.services.model.WebsiteConfiguration;
import com.obs.services.model.fs.DropFileResult;
import com.obs.services.model.fs.FSStatusEnum;
import com.obs.services.model.fs.GetBucketFSStatusResult;
import com.obs.services.model.fs.NewBucketRequest;
import com.obs.services.model.fs.ObsFSAttribute;
import com.obs.services.model.fs.ObsFSFile;
import com.obs.services.model.fs.ReadFileResult;
import com.obs.services.model.fs.RenameRequest;
import com.obs.services.model.fs.RenameResult;
import com.obs.services.model.fs.SetBucketFSStatusRequest;
import com.obs.services.model.fs.TruncateFileRequest;
import com.obs.services.model.fs.TruncateFileResult;
import com.obs.services.model.fs.WriteFileRequest;
import com.oef.services.model.CreateAsynchFetchJobsResult;
import com.oef.services.model.DisPolicy;
import com.oef.services.model.GetDisPolicyResult;
import com.oef.services.model.QueryAsynchFetchJobsResult;
import com.oef.services.model.QueryExtensionPolicyResult;
import com.oef.services.model.RequestParamEnum;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ObsService extends RestStorageService {
    private static final ILogger log = LoggerBuilder.getLogger("com.obs.services.ObsClient");

    private static class TransResult {
        private RequestBody body;
        private Map<String, String> headers;
        private Map<String, String> params;

        TransResult(Map<String, String> map) {
            this(map, null, null);
        }

        TransResult(Map<String, String> map, Map<String, String> map2, RequestBody requestBody) {
            this.headers = map;
            this.params = map2;
            this.body = requestBody;
        }

        TransResult(Map<String, String> map, RequestBody requestBody) {
            this(map, null, requestBody);
        }

        Map<String, String> getHeaders() {
            if (this.headers == null) {
                this.headers = new HashMap();
            }
            return this.headers;
        }

        Map<String, String> getParams() {
            if (this.params == null) {
                this.params = new HashMap();
            }
            return this.params;
        }
    }

    protected ObsService() {
    }

    static HeaderResponse build(Map<String, Object> map) {
        HeaderResponse headerResponse = new HeaderResponse();
        setResponseHeaders(headerResponse, map);
        return headerResponse;
    }

    private Response getAuthTypeNegotiationResponseImpl(String str) throws ServiceException {
        HashMap map = new HashMap();
        map.put("apiversion", "");
        return performRestForApiVersion(str, null, map, null);
    }

    private String getObjectUrl(String str, String str2) {
        String str3;
        boolean zIsPathStyle = isPathStyle();
        boolean httpsOnly = getHttpsOnly();
        boolean zIsCname = isCname();
        StringBuilder sb = new StringBuilder();
        sb.append(httpsOnly ? "https://" : "http://");
        String str4 = "";
        if (zIsPathStyle || zIsCname) {
            str3 = "";
        } else {
            str3 = str + ".";
        }
        sb.append(str3);
        sb.append(getEndpoint());
        sb.append(":");
        sb.append(httpsOnly ? getHttpsPort() : getHttpPort());
        sb.append("/");
        if (zIsPathStyle) {
            str4 = str + "/";
        }
        sb.append(str4);
        sb.append(RestUtils.uriEncode(str2, false));
        return sb.toString();
    }

    private ObsFSAttribute getObsFSAttributeFromResponse(Response response) {
        Date rfc822Date;
        String strHeader = response.header("Last-Modified");
        if (strHeader != null) {
            try {
                rfc822Date = ServiceUtils.parseRfc822Date(strHeader);
            } catch (ParseException e) {
                if (log.isWarnEnabled()) {
                    log.warn("Response last-modified is not well-format", e);
                }
            }
        } else {
            rfc822Date = null;
        }
        ObsFSAttribute obsFSAttribute = new ObsFSAttribute();
        obsFSAttribute.setLastModified(rfc822Date);
        obsFSAttribute.setContentEncoding(response.header("Content-Encoding"));
        obsFSAttribute.setContentType(response.header("Content-Type"));
        String strHeader2 = response.header("Content-Length");
        if (strHeader2 != null) {
            try {
                obsFSAttribute.setContentLength(Long.valueOf(Long.parseLong(strHeader2)));
            } catch (NumberFormatException e2) {
                if (log.isWarnEnabled()) {
                    log.warn("Response content-length is not well-format", e2);
                }
            }
        }
        String strHeader3 = response.header(getIHeaders().fsModeHeader());
        if (strHeader3 != null) {
            obsFSAttribute.setMode(Integer.parseInt(strHeader3));
        }
        obsFSAttribute.setWebSiteRedirectLocation(response.header(getIHeaders().websiteRedirectLocationHeader()));
        obsFSAttribute.setObjectStorageClass(StorageClassEnum.getValueFromCode(response.header(getIHeaders().storageClassHeader())));
        String strHeader4 = response.header("ETag");
        obsFSAttribute.setEtag(strHeader4);
        if (strHeader4 != null && !strHeader4.contains("-")) {
            if (strHeader4.startsWith("\"")) {
                strHeader4 = strHeader4.substring(1);
            }
            if (strHeader4.endsWith("\"")) {
                strHeader4 = strHeader4.substring(0, strHeader4.length() - 1);
            }
            try {
                obsFSAttribute.setContentMd5(ServiceUtils.toBase64(ServiceUtils.fromHex(strHeader4)));
            } catch (Exception e3) {
                if (log.isDebugEnabled()) {
                    log.debug(e3.getMessage(), e3);
                }
            }
        }
        obsFSAttribute.setAppendable("Appendable".equals(response.header(getIHeaders().objectTypeHeader())));
        obsFSAttribute.setNextPosition(Long.parseLong(response.header(getIHeaders().nextPositionHeader(), "-1")));
        if (obsFSAttribute.getNextPosition() == -1) {
            obsFSAttribute.setNextPosition(Long.parseLong(response.header("Content-Length", "-1")));
        }
        obsFSAttribute.getMetadata().putAll(cleanResponseHeaders(response));
        setStatusCode(obsFSAttribute, response.code());
        return obsFSAttribute;
    }

    private AuthTypeEnum parseAuthTypeInResponse(String str) throws ServiceException {
        String str2;
        try {
            Response authTypeNegotiationResponseImpl = getAuthTypeNegotiationResponseImpl(str);
            return (authTypeNegotiationResponseImpl.code() != 200 || (str2 = authTypeNegotiationResponseImpl.headers().get("x-obs-api")) == null || str2.compareTo("3.0") < 0) ? AuthTypeEnum.V2 : AuthTypeEnum.OBS;
        } catch (ServiceException e) {
            if (e.getResponseCode() == 404 || e.getResponseCode() <= 0 || e.getResponseCode() == 408 || e.getResponseCode() >= 500) {
                throw e;
            }
            return AuthTypeEnum.V2;
        }
    }

    static void setResponseHeaders(HeaderResponse headerResponse, Map<String, Object> map) {
        headerResponse.setResponseHeaders(map);
    }

    static void setStatusCode(HeaderResponse headerResponse, int i) {
        headerResponse.setStatusCode(i);
    }

    protected ReadAheadResult DeleteReadAheadObjectsImpl(String str, String str2) throws ServiceException {
        HashMap map = new HashMap();
        map.put("readAhead", "");
        map.put("prefix", str2);
        Response responsePerformRestDelete = performRestDelete(str, (String) null, (Map<String, String>) map, false);
        verifyResponseContentTypeForJson(responsePerformRestDelete);
        try {
            ReadAheadResult readAheadResult = (ReadAheadResult) JSONChange.jsonToObj(new ReadAheadResult(), responsePerformRestDelete.body().string());
            readAheadResult.setResponseHeaders(cleanResponseHeaders(responsePerformRestDelete));
            setStatusCode(readAheadResult, responsePerformRestDelete.code());
            return readAheadResult;
        } catch (IOException e) {
            throw new ServiceException(e);
        }
    }

    protected PostSignatureResponse _createPostSignature(PostSignatureRequest postSignatureRequest, boolean z) throws Exception {
        Date requestDate = postSignatureRequest.getRequestDate() != null ? postSignatureRequest.getRequestDate() : new Date();
        String str = ServiceUtils.getExpirationDateFormat().format(postSignatureRequest.getExpiryDate() == null ? new Date(requestDate.getTime() + ((postSignatureRequest.getExpires() <= 0 ? 300L : postSignatureRequest.getExpires()) * 1000)) : postSignatureRequest.getExpiryDate());
        StringBuilder sb = new StringBuilder();
        sb.append("{\"expiration\":");
        sb.append("\"");
        sb.append(str);
        sb.append("\",");
        sb.append("\"conditions\":[");
        String str2 = ServiceUtils.getShortDateFormat().format(requestDate);
        String str3 = ServiceUtils.getLongDateFormat().format(requestDate);
        String credential = getCredential(str2);
        if (postSignatureRequest.getConditions() == null || postSignatureRequest.getConditions().isEmpty()) {
            TreeMap treeMap = new TreeMap();
            if (z) {
                treeMap.put("X-Amz-Algorithm", "AWS4-HMAC-SHA256");
                treeMap.put("X-Amz-Date", str3);
                treeMap.put("X-Amz-Credential", credential);
            }
            treeMap.putAll(postSignatureRequest.getFormParams());
            if (!treeMap.containsKey(getIHeaders().securityTokenHeader())) {
                String securityToken = getProviderCredentials().getSecurityToken();
                if (ServiceUtils.isValid(securityToken)) {
                    treeMap.put(getIHeaders().securityTokenHeader(), securityToken);
                }
            }
            if (ServiceUtils.isValid(postSignatureRequest.getBucketName())) {
                treeMap.put("bucket", postSignatureRequest.getBucketName());
            }
            if (ServiceUtils.isValid(postSignatureRequest.getObjectKey())) {
                treeMap.put("key", postSignatureRequest.getObjectKey());
            }
            boolean z2 = true;
            boolean z3 = true;
            for (Map.Entry entry : treeMap.entrySet()) {
                if (ServiceUtils.isValid((String) entry.getKey())) {
                    String strTrim = ((String) entry.getKey()).toLowerCase().trim();
                    if (strTrim.equals("bucket")) {
                        z2 = false;
                    } else if (strTrim.equals("key")) {
                        z3 = false;
                    }
                    if (Constants.ALLOWED_REQUEST_HTTP_HEADER_METADATA_NAMES.contains(strTrim) || strTrim.startsWith(getRestHeaderPrefix()) || strTrim.startsWith("x-obs-") || strTrim.equals("acl") || strTrim.equals("bucket") || strTrim.equals("key") || strTrim.equals("success_action_redirect") || strTrim.equals("redirect") || strTrim.equals("success_action_status")) {
                        String string = entry.getValue() == null ? "" : entry.getValue().toString();
                        sb.append("{\"");
                        sb.append(strTrim);
                        sb.append("\":");
                        sb.append("\"");
                        sb.append(string);
                        sb.append("\"},");
                    }
                }
            }
            if (z2) {
                sb.append("[\"starts-with\", \"$bucket\", \"\"],");
            }
            if (z3) {
                sb.append("[\"starts-with\", \"$key\", \"\"],");
            }
        } else {
            sb.append(ServiceUtils.join(postSignatureRequest.getConditions(), ","));
            sb.append(",");
        }
        sb.append("]}");
        String base64 = ServiceUtils.toBase64(sb.toString().getBytes("UTF-8"));
        if (z) {
            return new V4PostSignatureResponse(base64, sb.toString(), "AWS4-HMAC-SHA256", credential, str3, V4Authentication.caculateSignature(base64, str2, getProviderCredentials().getSecretKey()), str);
        }
        return new PostSignatureResponse(base64, sb.toString(), AbstractAuthentication.caculateSignature(base64, getProviderCredentials().getSecretKey()), str, getProviderCredentials().getAccessKey());
    }

    protected TemporarySignatureResponse _createTemporarySignature(AbstractTemporarySignatureRequest abstractTemporarySignatureRequest) throws Exception {
        String string;
        String str;
        String base64;
        String str2;
        String str3;
        String operationType = abstractTemporarySignatureRequest.getMethod() != null ? abstractTemporarySignatureRequest.getMethod().getOperationType() : "GET";
        TreeMap treeMap = new TreeMap();
        treeMap.putAll(abstractTemporarySignatureRequest.getQueryParams());
        if (!treeMap.containsKey(getIHeaders().securityTokenHeader())) {
            String securityToken = getProviderCredentials().getSecurityToken();
            if (ServiceUtils.isValid(securityToken)) {
                treeMap.put(getIHeaders().securityTokenHeader(), securityToken);
            }
        }
        String endpoint = getEndpoint();
        String bucketName = abstractTemporarySignatureRequest.getBucketName();
        String objectKey = abstractTemporarySignatureRequest.getObjectKey();
        String strGenerateHostnameForBucket = ServiceUtils.generateHostnameForBucket(bucketName, isPathStyle(), endpoint);
        String strEncodeUrlPath = objectKey != null ? RestUtils.encodeUrlPath(objectKey, "/") : "";
        boolean z = false;
        if (endpoint.equals(strGenerateHostnameForBucket)) {
            StringBuilder sb = new StringBuilder();
            sb.append(!ServiceUtils.isValid(bucketName) ? "" : bucketName.trim());
            sb.append("/");
            sb.append(strEncodeUrlPath);
            string = sb.toString();
            str = "";
        } else {
            int iLastIndexOf = strGenerateHostnameForBucket.lastIndexOf("." + endpoint);
            str = iLastIndexOf > 0 ? strGenerateHostnameForBucket.substring(0, iLastIndexOf) + "/" : "";
            string = strEncodeUrlPath;
        }
        if (isCname()) {
            str = endpoint + "/";
        } else {
            strEncodeUrlPath = string;
            endpoint = strGenerateHostnameForBucket;
        }
        String str4 = strEncodeUrlPath + "?";
        if (abstractTemporarySignatureRequest.getSpecialParam() != null) {
            if (abstractTemporarySignatureRequest.getSpecialParam() == SpecialParamEnum.STORAGECLASS || abstractTemporarySignatureRequest.getSpecialParam() == SpecialParamEnum.STORAGEPOLICY) {
                abstractTemporarySignatureRequest.setSpecialParam(getSpecialParamForStorageClass());
            }
            str4 = str4 + abstractTemporarySignatureRequest.getSpecialParam().getOriginalStringCode() + "&";
        }
        String str5 = str4 + (getProviderCredentials().getAuthType() == AuthTypeEnum.OBS ? "AccessKeyId=" : "AWSAccessKeyId=") + getProviderCredentials().getAccessKey();
        boolean z2 = abstractTemporarySignatureRequest instanceof TemporarySignatureRequest;
        if (z2) {
            TemporarySignatureRequest temporarySignatureRequest = (TemporarySignatureRequest) abstractTemporarySignatureRequest;
            base64 = String.valueOf((temporarySignatureRequest.getExpires() <= 0 ? 300L : temporarySignatureRequest.getExpires()) + (System.currentTimeMillis() / 1000));
            str2 = "&Expires=" + base64;
        } else if (abstractTemporarySignatureRequest instanceof PolicyTempSignatureRequest) {
            base64 = ServiceUtils.toBase64(((PolicyTempSignatureRequest) abstractTemporarySignatureRequest).generatePolicy().getBytes("UTF-8"));
            str2 = "&Policy=" + base64;
        } else {
            base64 = "";
            str2 = base64;
        }
        String str6 = str5 + str2;
        loop0: while (true) {
            str3 = str6;
            for (Map.Entry entry : treeMap.entrySet()) {
                if (entry.getKey() == null || entry.getValue() == null) {
                }
            }
            str6 = (((str3 + "&") + RestUtils.uriEncode((CharSequence) entry.getKey(), false)) + "=") + RestUtils.uriEncode(entry.getValue().toString(), false);
        }
        HashMap map = new HashMap();
        map.putAll(abstractTemporarySignatureRequest.getHeaders());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(endpoint);
        sb2.append(":");
        sb2.append(getHttpsOnly() ? getHttpsPort() : getHttpPort());
        map.put("Host", sb2.toString());
        TreeMap treeMap2 = new TreeMap();
        for (Map.Entry entry2 : map.entrySet()) {
            if (ServiceUtils.isValid((String) entry2.getKey())) {
                String strTrim = ((String) entry2.getKey()).toLowerCase().trim();
                if (Constants.ALLOWED_REQUEST_HTTP_HEADER_METADATA_NAMES.contains(strTrim) || strTrim.startsWith(getRestHeaderPrefix()) || strTrim.startsWith("x-obs-")) {
                    z = true;
                    if (z) {
                        String strTrim2 = entry2.getValue() == null ? "" : ((String) entry2.getValue()).trim();
                        if (strTrim.startsWith(getRestMetadataPrefix())) {
                            strTrim2 = RestUtils.uriEncode(strTrim2, true);
                        }
                        treeMap2.put(((String) entry2.getKey()).trim(), strTrim2);
                    }
                } else {
                    if (operationType.equals("PUT") || operationType.equals("POST")) {
                        strTrim = getRestMetadataPrefix() + strTrim;
                        z = true;
                    }
                    if (z) {
                    }
                }
            }
            z = false;
        }
        String str7 = z2 ? "/" + str + str3 : "";
        AbstractAuthentication authentication = getAuthentication();
        if (authentication == null) {
            authentication = V2Authentication.getInstance();
        }
        String strMakeServiceCanonicalString = authentication.makeServiceCanonicalString(operationType, str7, treeMap2, base64, Constants.ALLOWED_RESOURCE_PARAMTER_NAMES);
        if (log.isDebugEnabled()) {
            log.debug((CharSequence) ("CanonicalString is :" + strMakeServiceCanonicalString));
        }
        TemporarySignatureResponse temporarySignatureResponse = new TemporarySignatureResponse((getHttpsOnly() ? "https://" : "http://") + ((String) map.get("Host")) + "/" + (str3 + "&Signature=" + RestUtils.encodeUrlString(ServiceUtils.signWithHmacSha1(this.credentials.getSecretKey(), strMakeServiceCanonicalString))));
        temporarySignatureResponse.getActualSignedRequestHeaders().putAll(treeMap2);
        return temporarySignatureResponse;
    }

    protected HeaderResponse abortMultipartUploadImpl(String str, String str2, String str3) throws ServiceException {
        HashMap map = new HashMap();
        map.put("uploadId", str);
        Response responsePerformRestDelete = performRestDelete(str2, str3, map);
        HeaderResponse headerResponseBuild = build(cleanResponseHeaders(responsePerformRestDelete));
        setStatusCode(headerResponseBuild, responsePerformRestDelete.code());
        return headerResponseBuild;
    }

    protected AppendObjectResult appendObjectImpl(AppendObjectRequest appendObjectRequest) throws Throwable {
        TransResult transResultTransAppendObjectRequest;
        AccessControlList acl = appendObjectRequest.getAcl();
        TransResult transResult = null;
        try {
            transResultTransAppendObjectRequest = transAppendObjectRequest(appendObjectRequest);
        } catch (Throwable th) {
            th = th;
        }
        try {
            boolean z = !prepareRESTHeaderAcl(transResultTransAppendObjectRequest.getHeaders(), acl);
            Response responsePerformRestPost = performRestPost(appendObjectRequest.getBucketName(), appendObjectRequest.getObjectKey(), transResultTransAppendObjectRequest.getHeaders(), transResultTransAppendObjectRequest.getParams(), transResultTransAppendObjectRequest.body, true);
            if (transResultTransAppendObjectRequest != null && transResultTransAppendObjectRequest.body != null && appendObjectRequest.isAutoClose()) {
                ServiceUtils.closeStream((RepeatableRequestEntity) transResultTransAppendObjectRequest.body);
            }
            String strHeader = responsePerformRestPost.header(getIHeaders().nextPositionHeader());
            AppendObjectResult appendObjectResult = new AppendObjectResult(appendObjectRequest.getBucketName(), appendObjectRequest.getObjectKey(), responsePerformRestPost.header("ETag"), strHeader != null ? Long.parseLong(strHeader) : -1L, StorageClassEnum.getValueFromCode(responsePerformRestPost.header(getIHeaders().storageClassHeader())), getObjectUrl(appendObjectRequest.getBucketName(), appendObjectRequest.getObjectKey()));
            setResponseHeaders(appendObjectResult, cleanResponseHeaders(responsePerformRestPost));
            setStatusCode(appendObjectResult, responsePerformRestPost.code());
            if (z && acl != null) {
                try {
                    putAclImpl(appendObjectRequest.getBucketName(), appendObjectRequest.getObjectKey(), acl, null);
                } catch (Exception e) {
                    if (log.isWarnEnabled()) {
                        log.warn("Try to set object acl error", e);
                    }
                }
            }
            return appendObjectResult;
        } catch (Throwable th2) {
            th = th2;
            transResult = transResultTransAppendObjectRequest;
            if (transResult != null && transResult.body != null && appendObjectRequest.isAutoClose()) {
                ServiceUtils.closeStream((RepeatableRequestEntity) transResult.body);
            }
            throw th;
        }
    }

    HeaderResponse build(Response response) {
        HeaderResponse headerResponse = new HeaderResponse();
        setResponseHeaders(headerResponse, cleanResponseHeaders(response));
        setStatusCode(headerResponse, response.code());
        return headerResponse;
    }

    Map<String, Object> cleanResponseHeaders(Response response) {
        return ServiceUtils.cleanRestMetadataMap(response.headers().toMultimap(), getIHeaders().headerPrefix(), getIHeaders().headerMetaPrefix());
    }

    protected CompleteMultipartUploadResult completeMultipartUploadImpl(CompleteMultipartUploadRequest completeMultipartUploadRequest) throws ServiceException {
        HashMap map = new HashMap();
        map.put("uploadId", completeMultipartUploadRequest.getUploadId());
        HashMap map2 = new HashMap();
        map2.put("Content-Type", "application/xml");
        Response responsePerformRestPost = performRestPost(completeMultipartUploadRequest.getBucketName(), completeMultipartUploadRequest.getObjectKey(), map2, map, createRequestBody("application/xml", getIConvertor().transCompleteMultipartUpload(completeMultipartUploadRequest.getPartEtag())), false);
        verifyResponseContentType(responsePerformRestPost);
        XmlResponsesSaxParser.CompleteMultipartUploadHandler completeMultipartUploadHandler = (XmlResponsesSaxParser.CompleteMultipartUploadHandler) getXmlResponseSaxParser().parse(new HttpMethodReleaseInputStream(responsePerformRestPost), XmlResponsesSaxParser.CompleteMultipartUploadHandler.class, true);
        CompleteMultipartUploadResult completeMultipartUploadResult = new CompleteMultipartUploadResult(completeMultipartUploadHandler.getBucketName(), completeMultipartUploadHandler.getObjectKey(), completeMultipartUploadHandler.getEtag(), completeMultipartUploadHandler.getLocation(), responsePerformRestPost.header(getIHeaders().versionIdHeader()), getObjectUrl(completeMultipartUploadHandler.getBucketName(), completeMultipartUploadHandler.getObjectKey()));
        setResponseHeaders(completeMultipartUploadResult, cleanResponseHeaders(responsePerformRestPost));
        setStatusCode(completeMultipartUploadResult, responsePerformRestPost.code());
        return completeMultipartUploadResult;
    }

    protected CopyObjectResult copyObjectImpl(CopyObjectRequest copyObjectRequest) throws ServiceException {
        TransResult transResultTransCopyObjectRequest = transCopyObjectRequest(copyObjectRequest);
        AccessControlList acl = copyObjectRequest.getAcl();
        boolean z = !prepareRESTHeaderAcl(transResultTransCopyObjectRequest.getHeaders(), acl);
        Response responsePerformRestPut = performRestPut(copyObjectRequest.getDestinationBucketName(), copyObjectRequest.getDestinationObjectKey(), transResultTransCopyObjectRequest.getHeaders(), null, null, false);
        verifyResponseContentType(responsePerformRestPut);
        XmlResponsesSaxParser.CopyObjectResultHandler copyObjectResultHandler = (XmlResponsesSaxParser.CopyObjectResultHandler) getXmlResponseSaxParser().parse(new HttpMethodReleaseInputStream(responsePerformRestPut), XmlResponsesSaxParser.CopyObjectResultHandler.class, false);
        CopyObjectResult copyObjectResult = new CopyObjectResult(copyObjectResultHandler.getETag(), copyObjectResultHandler.getLastModified(), responsePerformRestPut.header(getIHeaders().versionIdHeader()), responsePerformRestPut.header(getIHeaders().copySourceVersionIdHeader()), StorageClassEnum.getValueFromCode(responsePerformRestPut.header(getIHeaders().storageClassHeader())));
        setResponseHeaders(copyObjectResult, cleanResponseHeaders(responsePerformRestPut));
        setStatusCode(copyObjectResult, responsePerformRestPut.code());
        if (z && acl != null) {
            if (log.isDebugEnabled()) {
                log.debug((CharSequence) "Creating object with a non-canned ACL using REST, so an extra ACL Put is required");
            }
            try {
                putAclImpl(copyObjectRequest.getDestinationBucketName(), copyObjectRequest.getDestinationObjectKey(), acl, null);
            } catch (Exception e) {
                if (log.isWarnEnabled()) {
                    log.warn("Try to set object acl error", e);
                }
            }
        }
        return copyObjectResult;
    }

    protected CopyPartResult copyPartImpl(CopyPartRequest copyPartRequest) throws ServiceException {
        TransResult transResultTransCopyPartRequest = transCopyPartRequest(copyPartRequest);
        Response responsePerformRestPut = performRestPut(copyPartRequest.getDestinationBucketName(), copyPartRequest.getDestinationObjectKey(), transResultTransCopyPartRequest.getHeaders(), transResultTransCopyPartRequest.getParams(), null, false);
        verifyResponseContentType(responsePerformRestPut);
        CopyPartResult copyPartResult = ((XmlResponsesSaxParser.CopyPartResultHandler) getXmlResponseSaxParser().parse(new HttpMethodReleaseInputStream(responsePerformRestPut), XmlResponsesSaxParser.CopyPartResultHandler.class, true)).getCopyPartResult(copyPartRequest.getPartNumber());
        setResponseHeaders(copyPartResult, cleanResponseHeaders(responsePerformRestPut));
        setStatusCode(copyPartResult, responsePerformRestPut.code());
        return copyPartResult;
    }

    protected ObsBucket createBucketImpl(CreateBucketRequest createBucketRequest) throws ServiceException {
        TransResult transResultTransCreateBucketRequest = transCreateBucketRequest(createBucketRequest);
        String bucketName = createBucketRequest.getBucketName();
        AccessControlList acl = createBucketRequest.getAcl();
        boolean z = !prepareRESTHeaderAcl(transResultTransCreateBucketRequest.getHeaders(), acl);
        Response responsePerformRestPut = performRestPut(bucketName, null, transResultTransCreateBucketRequest.getHeaders(), null, transResultTransCreateBucketRequest.body, true);
        if (z && acl != null) {
            if (log.isDebugEnabled()) {
                log.debug((CharSequence) "Creating bucket with a non-canned ACL using REST, so an extra ACL Put is required");
            }
            try {
                putAclImpl(bucketName, null, acl, null);
            } catch (Exception e) {
                if (log.isWarnEnabled()) {
                    log.warn("Try to set bucket acl error", e);
                }
            }
        }
        Map<String, Object> mapCleanResponseHeaders = cleanResponseHeaders(responsePerformRestPut);
        ObsBucket obsBucket = new ObsBucket();
        obsBucket.setBucketName(bucketName);
        obsBucket.setLocation(createBucketRequest.getLocation());
        obsBucket.setAcl(acl);
        obsBucket.setBucketStorageClass(createBucketRequest.getBucketStorageClass());
        setResponseHeaders(obsBucket, mapCleanResponseHeaders);
        setStatusCode(obsBucket, responsePerformRestPut.code());
        return obsBucket;
    }

    protected CreateAsynchFetchJobsResult createFetchJobImpl(String str, String str2) throws ServiceException {
        HashMap map = new HashMap();
        map.put(RequestParamEnum.ASYNC_FETCH_JOBS.getOriginalStringCode(), "");
        HashMap map2 = new HashMap();
        map2.put("Content-Type", "application/json");
        StringBuilder sb = new StringBuilder();
        sb.append(getProviderCredentials().getAuthType() != AuthTypeEnum.OBS ? "x-amz-" : "x-obs-");
        sb.append("oef-marker");
        map2.put(sb.toString(), "yes");
        Response responsePerformRestPost = performRestPost(str, null, map2, map, createRequestBody("application/json", str2), false, true);
        verifyResponseContentTypeForJson(responsePerformRestPost);
        try {
            CreateAsynchFetchJobsResult createAsynchFetchJobsResult = (CreateAsynchFetchJobsResult) JSONChange.jsonToObj(new CreateAsynchFetchJobsResult(), responsePerformRestPost.body().string());
            createAsynchFetchJobsResult.setResponseHeaders(cleanResponseHeaders(responsePerformRestPost));
            setStatusCode(createAsynchFetchJobsResult, responsePerformRestPost.code());
            return createAsynchFetchJobsResult;
        } catch (IOException e) {
            throw new ServiceException(e);
        }
    }

    protected RequestBody createRequestBody(String str, String str2) throws ServiceException {
        try {
            if (log.isTraceEnabled()) {
                try {
                    log.trace((CharSequence) ("Entity Content:" + str2));
                } catch (Exception unused) {
                }
            }
            return RequestBody.create(MediaType.parse(str), str2.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new ServiceException(e);
        }
    }

    protected TemporarySignatureResponse createV4TemporarySignature(TemporarySignatureRequest temporarySignatureRequest) throws Exception {
        TreeMap treeMap;
        String str;
        String str2;
        Iterator it;
        String str3;
        String str4;
        Iterator it2;
        boolean z;
        int i;
        StringBuilder sb = new StringBuilder();
        String bucketName = temporarySignatureRequest.getBucketName();
        String endpoint = getEndpoint();
        String objectKey = temporarySignatureRequest.getObjectKey();
        String str5 = "/";
        if (isCname()) {
            if (ServiceUtils.isValid(objectKey)) {
                sb.append("/");
                sb.append(RestUtils.uriEncode(objectKey, false));
            }
        } else if (ServiceUtils.isValid(bucketName)) {
            if (isPathStyle() || !ServiceUtils.isBucketNameValidDNSName(bucketName)) {
                sb.append("/");
                sb.append(bucketName.trim());
            } else {
                endpoint = bucketName.trim() + "." + endpoint;
            }
            if (ServiceUtils.isValid(objectKey)) {
                sb.append("/");
                sb.append(RestUtils.uriEncode(objectKey, false));
            }
        }
        if (isCname()) {
            endpoint = getEndpoint();
        }
        TreeMap treeMap2 = new TreeMap();
        treeMap2.putAll(temporarySignatureRequest.getHeaders());
        TreeMap treeMap3 = new TreeMap();
        treeMap3.putAll(temporarySignatureRequest.getQueryParams());
        Date requestDate = temporarySignatureRequest.getRequestDate();
        if (requestDate == null) {
            requestDate = new Date();
        }
        if (!(getHttpsOnly() && getHttpsPort() == 443) && (getHttpsOnly() || getHttpPort() != 80)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(endpoint);
            sb2.append(":");
            sb2.append(getHttpsOnly() ? getHttpsPort() : getHttpPort());
            treeMap2.put("Host", sb2.toString());
        } else {
            treeMap2.put("Host", endpoint);
        }
        if (!treeMap3.containsKey(getIHeaders().securityTokenHeader())) {
            String securityToken = getProviderCredentials().getSecurityToken();
            if (ServiceUtils.isValid(securityToken)) {
                treeMap3.put(getIHeaders().securityTokenHeader(), securityToken);
            }
        }
        String operationType = temporarySignatureRequest.getMethod() != null ? temporarySignatureRequest.getMethod().getOperationType() : "GET";
        StringBuilder sb3 = new StringBuilder();
        StringBuilder sb4 = new StringBuilder();
        TreeMap treeMap4 = new TreeMap();
        Iterator it3 = treeMap2.entrySet().iterator();
        int i2 = 0;
        while (true) {
            if (!it3.hasNext()) {
                break;
            }
            Map.Entry entry = (Map.Entry) it3.next();
            if (ServiceUtils.isValid((String) entry.getKey())) {
                String strTrim = ((String) entry.getKey()).toLowerCase().trim();
                it2 = it3;
                if (Constants.ALLOWED_REQUEST_HTTP_HEADER_METADATA_NAMES.contains(strTrim) || strTrim.startsWith(getRestHeaderPrefix()) || strTrim.startsWith("x-obs-")) {
                    str4 = str5;
                } else if (operationType.equals("PUT") || operationType.equals("POST")) {
                    StringBuilder sb5 = new StringBuilder();
                    str4 = str5;
                    sb5.append(getRestMetadataPrefix());
                    sb5.append(strTrim);
                    strTrim = sb5.toString();
                } else {
                    str4 = str5;
                    z = false;
                    if (z) {
                        String strTrim2 = entry.getValue() != null ? ((String) entry.getValue()).trim() : "";
                        if (strTrim.startsWith(getRestMetadataPrefix())) {
                            i = 1;
                            strTrim2 = RestUtils.uriEncode(strTrim2, true);
                        } else {
                            i = 1;
                        }
                        sb3.append(strTrim);
                        sb4.append(strTrim);
                        sb4.append(":");
                        sb4.append(strTrim2);
                        sb4.append("\n");
                        int i3 = i2 + 1;
                        if (i2 != treeMap2.size() - i) {
                            sb3.append(";");
                        }
                        treeMap4.put(((String) entry.getKey()).trim(), strTrim2);
                        i2 = i3;
                    }
                }
                z = true;
                if (z) {
                }
            } else {
                str4 = str5;
                it2 = it3;
            }
            it3 = it2;
            str5 = str4;
        }
        String str6 = str5;
        String str7 = ServiceUtils.getShortDateFormat().format(requestDate);
        String str8 = ServiceUtils.getLongDateFormat().format(requestDate);
        treeMap3.put("X-Amz-Algorithm", "AWS4-HMAC-SHA256");
        treeMap3.put("X-Amz-Credential", getCredential(str7));
        treeMap3.put("X-Amz-Date", str8);
        treeMap3.put("X-Amz-Expires", Long.valueOf(temporarySignatureRequest.getExpires() <= 0 ? 300L : temporarySignatureRequest.getExpires()));
        treeMap3.put("X-Amz-SignedHeaders", sb3.toString());
        StringBuilder sb6 = new StringBuilder();
        StringBuilder sb7 = new StringBuilder();
        if (getHttpsOnly()) {
            treeMap = treeMap4;
            if (getHttpsPort() == 443) {
                str3 = "";
            } else {
                str3 = ":" + getHttpsPort();
            }
            sb7.append("https://");
            sb7.append(endpoint);
            sb7.append(str3);
        } else {
            treeMap = treeMap4;
            if (getHttpPort() == 80) {
                str = "";
            } else {
                str = ":" + getHttpPort();
            }
            sb7.append("http://");
            sb7.append(endpoint);
            sb7.append(str);
        }
        sb7.append((CharSequence) sb);
        sb7.append("?");
        if (temporarySignatureRequest.getSpecialParam() != null) {
            if (temporarySignatureRequest.getSpecialParam() == SpecialParamEnum.STORAGECLASS || temporarySignatureRequest.getSpecialParam() == SpecialParamEnum.STORAGEPOLICY) {
                temporarySignatureRequest.setSpecialParam(getSpecialParamForStorageClass());
            }
            treeMap3.put(temporarySignatureRequest.getSpecialParam().getOriginalStringCode(), null);
        }
        Iterator it4 = treeMap3.entrySet().iterator();
        int i4 = 0;
        while (it4.hasNext()) {
            Map.Entry entry2 = (Map.Entry) it4.next();
            if (ServiceUtils.isValid((String) entry2.getKey())) {
                it = it4;
                str2 = str7;
                String strUriEncode = RestUtils.uriEncode((CharSequence) entry2.getKey(), false);
                sb6.append(strUriEncode);
                sb6.append("=");
                sb7.append(strUriEncode);
                if (entry2.getValue() != null) {
                    String strUriEncode2 = RestUtils.uriEncode(entry2.getValue().toString(), false);
                    sb6.append(strUriEncode2);
                    sb7.append("=");
                    sb7.append(strUriEncode2);
                } else {
                    sb6.append("");
                }
                int i5 = i4 + 1;
                if (i4 != treeMap3.size() - 1) {
                    sb6.append("&");
                    sb7.append("&");
                }
                i4 = i5;
            } else {
                str2 = str7;
                it = it4;
            }
            it4 = it;
            str7 = str2;
        }
        String str9 = str7;
        StringBuilder sb8 = new StringBuilder(operationType);
        sb8.append("\n");
        if (sb.length() == 0) {
            sb = str6;
        }
        sb8.append((CharSequence) sb);
        sb8.append("\n");
        sb8.append((CharSequence) sb6);
        sb8.append("\n");
        sb8.append((CharSequence) sb4);
        sb8.append("\n");
        sb8.append((CharSequence) sb3);
        sb8.append("\n");
        sb8.append("UNSIGNED-PAYLOAD");
        sb7.append("&");
        sb7.append("X-Amz-");
        sb7.append("Signature=");
        sb7.append(V4Authentication.caculateSignature("AWS4-HMAC-SHA256\n" + str8 + "\n" + str9 + str6 + "region" + str6 + "s3" + str6 + "aws4_request\n" + V4Authentication.byteToHex(V4Authentication.sha256encode(sb8.toString())), str9, getProviderCredentials().getSecretKey()));
        TemporarySignatureResponse temporarySignatureResponse = new TemporarySignatureResponse(sb7.toString());
        temporarySignatureResponse.getActualSignedRequestHeaders().putAll(treeMap);
        return temporarySignatureResponse;
    }

    protected HeaderResponse deleteBucketCorsImpl(String str) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.CORS.getOriginalStringCode(), "");
        Response responsePerformRestDelete = performRestDelete(str, null, map);
        HeaderResponse headerResponseBuild = build(cleanResponseHeaders(responsePerformRestDelete));
        setStatusCode(headerResponseBuild, responsePerformRestDelete.code());
        return headerResponseBuild;
    }

    protected HeaderResponse deleteBucketDirectColdAccessImpl(String str) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.DIRECTCOLDACCESS.getOriginalStringCode(), "");
        Response responsePerformRestDelete = performRestDelete(str, null, map);
        HeaderResponse headerResponseBuild = build(cleanResponseHeaders(responsePerformRestDelete));
        setStatusCode(headerResponseBuild, responsePerformRestDelete.code());
        return headerResponseBuild;
    }

    protected HeaderResponse deleteBucketEncryptionImpl(String str) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.ENCRYPTION.getOriginalStringCode(), "");
        Response responsePerformRestDelete = performRestDelete(str, null, map);
        HeaderResponse headerResponseBuild = build(cleanResponseHeaders(responsePerformRestDelete));
        setStatusCode(headerResponseBuild, responsePerformRestDelete.code());
        return headerResponseBuild;
    }

    protected HeaderResponse deleteBucketImpl(String str) throws ServiceException {
        return build(performRestDelete(str, null, null));
    }

    protected HeaderResponse deleteBucketLifecycleConfigurationImpl(String str) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.LIFECYCLE.getOriginalStringCode(), "");
        Response responsePerformRestDelete = performRestDelete(str, null, map);
        HeaderResponse headerResponseBuild = build(cleanResponseHeaders(responsePerformRestDelete));
        setStatusCode(headerResponseBuild, responsePerformRestDelete.code());
        return headerResponseBuild;
    }

    protected HeaderResponse deleteBucketPolicyImpl(String str) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.POLICY.getOriginalStringCode(), "");
        Response responsePerformRestDelete = performRestDelete(str, null, map);
        HeaderResponse headerResponseBuild = build(cleanResponseHeaders(responsePerformRestDelete));
        setStatusCode(headerResponseBuild, responsePerformRestDelete.code());
        return headerResponseBuild;
    }

    protected HeaderResponse deleteBucketReplicationConfigurationImpl(String str) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.REPLICATION.getOriginalStringCode(), "");
        Response responsePerformRestDelete = performRestDelete(str, null, map);
        HeaderResponse headerResponseBuild = build(cleanResponseHeaders(responsePerformRestDelete));
        setStatusCode(headerResponseBuild, responsePerformRestDelete.code());
        return headerResponseBuild;
    }

    protected HeaderResponse deleteBucketTaggingImpl(String str) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.TAGGING.getOriginalStringCode(), "");
        Response responsePerformRestDelete = performRestDelete(str, null, map);
        HeaderResponse headerResponseBuild = build(cleanResponseHeaders(responsePerformRestDelete));
        setStatusCode(headerResponseBuild, responsePerformRestDelete.code());
        return headerResponseBuild;
    }

    protected HeaderResponse deleteBucketWebsiteConfigurationImpl(String str) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.WEBSITE.getOriginalStringCode(), "");
        Response responsePerformRestDelete = performRestDelete(str, null, map);
        HeaderResponse headerResponseBuild = build(cleanResponseHeaders(responsePerformRestDelete));
        setStatusCode(headerResponseBuild, responsePerformRestDelete.code());
        return headerResponseBuild;
    }

    protected HeaderResponse deleteDisPolicyImpl(String str) throws ServiceException {
        HashMap map = new HashMap();
        map.put(RequestParamEnum.DIS_POLICIES.getOriginalStringCode(), "");
        HashMap map2 = new HashMap();
        StringBuilder sb = new StringBuilder();
        sb.append(getProviderCredentials().getAuthType() != AuthTypeEnum.OBS ? "x-amz-" : "x-obs-");
        sb.append("oef-marker");
        map2.put(sb.toString(), "yes");
        return build(performRestDelete(str, null, map, map2, true));
    }

    protected HeaderResponse deleteExtensionPolicyImpl(String str) throws ServiceException {
        HashMap map = new HashMap();
        map.put(RequestParamEnum.EXTENSION_POLICY.getOriginalStringCode(), "");
        HashMap map2 = new HashMap();
        StringBuilder sb = new StringBuilder();
        sb.append(getProviderCredentials().getAuthType() != AuthTypeEnum.OBS ? "x-amz-" : "x-obs-");
        sb.append("oef-marker");
        map2.put(sb.toString(), "yes");
        return build(performRestDelete(str, null, map, map2, true));
    }

    protected DeleteObjectResult deleteObjectImpl(String str, String str2, String str3) throws ServiceException {
        HashMap map = new HashMap();
        if (str3 != null) {
            map.put("versionId", str3);
        }
        Response responsePerformRestDelete = performRestDelete(str, str2, map);
        DropFileResult dropFileResult = new DropFileResult(Boolean.valueOf(responsePerformRestDelete.header(getIHeaders().deleteMarkerHeader())).booleanValue(), str2, responsePerformRestDelete.header(getIHeaders().versionIdHeader()));
        setResponseHeaders(dropFileResult, cleanResponseHeaders(responsePerformRestDelete));
        setStatusCode(dropFileResult, responsePerformRestDelete.code());
        return dropFileResult;
    }

    protected DeleteObjectsResult deleteObjectsImpl(DeleteObjectsRequest deleteObjectsRequest) throws ServiceException {
        String strTransKeyAndVersion = getIConvertor().transKeyAndVersion(deleteObjectsRequest.getKeyAndVersions(), deleteObjectsRequest.isQuiet());
        HashMap map = new HashMap();
        map.put("Content-MD5", ServiceUtils.computeMD5(strTransKeyAndVersion));
        map.put("Content-Type", "application/xml");
        HashMap map2 = new HashMap();
        map2.put(SpecialParamEnum.DELETE.getOriginalStringCode(), "");
        Response responsePerformRestPost = performRestPost(deleteObjectsRequest.getBucketName(), null, map, map2, createRequestBody("application/xml", strTransKeyAndVersion), false);
        verifyResponseContentType(responsePerformRestPost);
        DeleteObjectsResult multipleDeleteResult = ((XmlResponsesSaxParser.DeleteObjectsHandler) getXmlResponseSaxParser().parse(new HttpMethodReleaseInputStream(responsePerformRestPost), XmlResponsesSaxParser.DeleteObjectsHandler.class, true)).getMultipleDeleteResult();
        setResponseHeaders(multipleDeleteResult, cleanResponseHeaders(responsePerformRestPost));
        setStatusCode(multipleDeleteResult, responsePerformRestPost.code());
        return multipleDeleteResult;
    }

    protected AuthTypeEnum getApiVersion(String str) throws ServiceException {
        if (!ServiceUtils.isValid(str)) {
            return parseAuthTypeInResponse("");
        }
        AuthTypeEnum apiVersionInCache = this.apiVersionCache.getApiVersionInCache(str);
        if (apiVersionInCache == null) {
            try {
                this.segmentLock.lock(str);
                apiVersionInCache = this.apiVersionCache.getApiVersionInCache(str);
                if (apiVersionInCache == null) {
                    apiVersionInCache = parseAuthTypeInResponse(str);
                    this.apiVersionCache.addApiVersion(str, apiVersionInCache);
                }
            } finally {
                this.segmentLock.unlock(str);
            }
        }
        return apiVersionInCache;
    }

    AbstractAuthentication getAuthentication() {
        return Constants.AUTHTICATION_MAP.get(getProviderCredentials().getAuthType());
    }

    protected AccessControlList getBucketAclImpl(String str) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.ACL.getOriginalStringCode(), "");
        Response responsePerformRestGet = performRestGet(str, null, map, null);
        verifyResponseContentType(responsePerformRestGet);
        AccessControlList accessControlList = ((XmlResponsesSaxParser.AccessControlListHandler) getXmlResponseSaxParser().parse(new HttpMethodReleaseInputStream(responsePerformRestGet), XmlResponsesSaxParser.AccessControlListHandler.class, false)).getAccessControlList();
        setResponseHeaders(accessControlList, cleanResponseHeaders(responsePerformRestGet));
        setStatusCode(accessControlList, responsePerformRestGet.code());
        return accessControlList;
    }

    protected BucketCors getBucketCorsImpl(String str) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.CORS.getOriginalStringCode(), "");
        Response responsePerformRestGet = performRestGet(str, null, map, null);
        verifyResponseContentType(responsePerformRestGet);
        BucketCors configuration = ((XmlResponsesSaxParser.BucketCorsHandler) getXmlResponseSaxParser().parse(new HttpMethodReleaseInputStream(responsePerformRestGet), XmlResponsesSaxParser.BucketCorsHandler.class, false)).getConfiguration();
        setResponseHeaders(configuration, cleanResponseHeaders(responsePerformRestGet));
        setStatusCode(configuration, responsePerformRestGet.code());
        return configuration;
    }

    protected BucketDirectColdAccess getBucketDirectColdAccessImpl(String str) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.DIRECTCOLDACCESS.getOriginalStringCode(), "");
        Response responsePerformRestGet = performRestGet(str, null, map, null);
        verifyResponseContentType(responsePerformRestGet);
        BucketDirectColdAccess bucketDirectColdAccess = ((XmlResponsesSaxParser.BucketDirectColdAccessHandler) getXmlResponseSaxParser().parse(new HttpMethodReleaseInputStream(responsePerformRestGet), XmlResponsesSaxParser.BucketDirectColdAccessHandler.class, false)).getBucketDirectColdAccess();
        setResponseHeaders(bucketDirectColdAccess, cleanResponseHeaders(responsePerformRestGet));
        setStatusCode(bucketDirectColdAccess, responsePerformRestGet.code());
        return bucketDirectColdAccess;
    }

    protected BucketEncryption getBucketEncryptionImpl(String str) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.ENCRYPTION.getOriginalStringCode(), "");
        Response responsePerformRestGet = performRestGet(str, null, map, null);
        verifyResponseContentType(responsePerformRestGet);
        BucketEncryption encryption = ((XmlResponsesSaxParser.BucketEncryptionHandler) getXmlResponseSaxParser().parse(new HttpMethodReleaseInputStream(responsePerformRestGet), XmlResponsesSaxParser.BucketEncryptionHandler.class, false)).getEncryption();
        setResponseHeaders(encryption, cleanResponseHeaders(responsePerformRestGet));
        setStatusCode(encryption, responsePerformRestGet.code());
        return encryption;
    }

    protected LifecycleConfiguration getBucketLifecycleConfigurationImpl(String str) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.LIFECYCLE.getOriginalStringCode(), "");
        Response responsePerformRestGet = performRestGet(str, null, map, null);
        verifyResponseContentType(responsePerformRestGet);
        LifecycleConfiguration lifecycleConfig = ((XmlResponsesSaxParser.BucketLifecycleConfigurationHandler) getXmlResponseSaxParser().parse(new HttpMethodReleaseInputStream(responsePerformRestGet), XmlResponsesSaxParser.BucketLifecycleConfigurationHandler.class, false)).getLifecycleConfig();
        setResponseHeaders(lifecycleConfig, cleanResponseHeaders(responsePerformRestGet));
        setStatusCode(lifecycleConfig, responsePerformRestGet.code());
        return lifecycleConfig;
    }

    protected BucketLocationResponse getBucketLocationImpl(String str) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.LOCATION.getOriginalStringCode(), "");
        Response responsePerformRestGet = performRestGet(str, null, map, null);
        verifyResponseContentType(responsePerformRestGet);
        BucketLocationResponse bucketLocationResponse = new BucketLocationResponse(((XmlResponsesSaxParser.BucketLocationHandler) getXmlResponseSaxParser().parse(new HttpMethodReleaseInputStream(responsePerformRestGet), XmlResponsesSaxParser.BucketLocationHandler.class, false)).getLocation());
        setResponseHeaders(bucketLocationResponse, cleanResponseHeaders(responsePerformRestGet));
        setStatusCode(bucketLocationResponse, responsePerformRestGet.code());
        return bucketLocationResponse;
    }

    protected BucketLoggingConfiguration getBucketLoggingConfigurationImpl(String str) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.LOGGING.getOriginalStringCode(), "");
        Response responsePerformRestGet = performRestGet(str, null, map, null);
        verifyResponseContentType(responsePerformRestGet);
        BucketLoggingConfiguration bucketLoggingStatus = ((XmlResponsesSaxParser.BucketLoggingHandler) getXmlResponseSaxParser().parse(new HttpMethodReleaseInputStream(responsePerformRestGet), XmlResponsesSaxParser.BucketLoggingHandler.class, false)).getBucketLoggingStatus();
        setResponseHeaders(bucketLoggingStatus, cleanResponseHeaders(responsePerformRestGet));
        setStatusCode(bucketLoggingStatus, responsePerformRestGet.code());
        return bucketLoggingStatus;
    }

    protected GetBucketFSStatusResult getBucketMetadataImpl(BucketMetadataInfoRequest bucketMetadataInfoRequest) throws ServiceException {
        String origin = bucketMetadataInfoRequest.getOrigin();
        List<String> requestHeaders = bucketMetadataInfoRequest.getRequestHeaders();
        if (origin == null || requestHeaders == null || requestHeaders.size() <= 0) {
            HashMap map = new HashMap();
            if (origin != null) {
                map.put("Origin", origin);
            }
            Response responsePerformRestHead = performRestHead(bucketMetadataInfoRequest.getBucketName(), null, null, map);
            GetBucketFSStatusResult optionInfoResult = getOptionInfoResult(responsePerformRestHead);
            responsePerformRestHead.close();
            return optionInfoResult;
        }
        GetBucketFSStatusResult optionInfoResult2 = null;
        for (int i = 0; i < requestHeaders.size(); i++) {
            String str = requestHeaders.get(i);
            HashMap map2 = new HashMap();
            map2.put("Origin", origin);
            map2.put("Access-Control-Request-Headers", str);
            Response responsePerformRestHead2 = performRestHead(bucketMetadataInfoRequest.getBucketName(), null, null, map2);
            if (optionInfoResult2 == null) {
                optionInfoResult2 = getOptionInfoResult(responsePerformRestHead2);
            } else {
                String strHeader = responsePerformRestHead2.header("Access-Control-Allow-Headers");
                if (strHeader != null && !optionInfoResult2.getAllowHeaders().contains(strHeader)) {
                    optionInfoResult2.getAllowHeaders().add(strHeader);
                }
            }
            responsePerformRestHead2.close();
        }
        return optionInfoResult2;
    }

    protected BucketNotificationConfiguration getBucketNotificationConfigurationImpl(String str) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.NOTIFICATION.getOriginalStringCode(), "");
        Response responsePerformRestGet = performRestGet(str, null, map, null);
        verifyResponseContentType(responsePerformRestGet);
        BucketNotificationConfiguration bucketNotificationConfiguration = ((XmlResponsesSaxParser.BucketNotificationConfigurationHandler) getXmlResponseSaxParser().parse(new HttpMethodReleaseInputStream(responsePerformRestGet), XmlResponsesSaxParser.BucketNotificationConfigurationHandler.class, false)).getBucketNotificationConfiguration();
        setResponseHeaders(bucketNotificationConfiguration, cleanResponseHeaders(responsePerformRestGet));
        setStatusCode(bucketNotificationConfiguration, responsePerformRestGet.code());
        return bucketNotificationConfiguration;
    }

    protected BucketPolicyResponse getBucketPolicyImpl(String str) throws ServiceException {
        try {
            HashMap map = new HashMap();
            map.put(SpecialParamEnum.POLICY.getOriginalStringCode(), "");
            Response responsePerformRestGet = performRestGet(str, null, map, null);
            BucketPolicyResponse bucketPolicyResponse = new BucketPolicyResponse(responsePerformRestGet.body().string());
            setResponseHeaders(bucketPolicyResponse, cleanResponseHeaders(responsePerformRestGet));
            setStatusCode(bucketPolicyResponse, responsePerformRestGet.code());
            return bucketPolicyResponse;
        } catch (IOException e) {
            throw new ServiceException(e);
        }
    }

    protected BucketQuota getBucketQuotaImpl(String str) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.QUOTA.getOriginalStringCode(), "");
        Response responsePerformRestGet = performRestGet(str, null, map, null);
        verifyResponseContentType(responsePerformRestGet);
        BucketQuota quota = ((XmlResponsesSaxParser.BucketQuotaHandler) getXmlResponseSaxParser().parse(new HttpMethodReleaseInputStream(responsePerformRestGet), XmlResponsesSaxParser.BucketQuotaHandler.class, false)).getQuota();
        setResponseHeaders(quota, cleanResponseHeaders(responsePerformRestGet));
        setStatusCode(quota, responsePerformRestGet.code());
        return quota;
    }

    protected ReplicationConfiguration getBucketReplicationConfigurationImpl(String str) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.REPLICATION.getOriginalStringCode(), "");
        Response responsePerformRestGet = performRestGet(str, null, map, null);
        verifyResponseContentType(responsePerformRestGet);
        ReplicationConfiguration replicationConfiguration = ((XmlResponsesSaxParser.BucketReplicationConfigurationHandler) getXmlResponseSaxParser().parse(new HttpMethodReleaseInputStream(responsePerformRestGet), XmlResponsesSaxParser.BucketReplicationConfigurationHandler.class, false)).getReplicationConfiguration();
        setResponseHeaders(replicationConfiguration, cleanResponseHeaders(responsePerformRestGet));
        setStatusCode(replicationConfiguration, responsePerformRestGet.code());
        return replicationConfiguration;
    }

    protected BucketStorageInfo getBucketStorageInfoImpl(String str) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.STORAGEINFO.getOriginalStringCode(), "");
        Response responsePerformRestGet = performRestGet(str, null, map, null);
        verifyResponseContentType(responsePerformRestGet);
        BucketStorageInfo storageInfo = ((XmlResponsesSaxParser.BucketStorageInfoHandler) getXmlResponseSaxParser().parse(new HttpMethodReleaseInputStream(responsePerformRestGet), XmlResponsesSaxParser.BucketStorageInfoHandler.class, false)).getStorageInfo();
        setResponseHeaders(storageInfo, cleanResponseHeaders(responsePerformRestGet));
        setStatusCode(storageInfo, responsePerformRestGet.code());
        return storageInfo;
    }

    protected BucketStoragePolicyConfiguration getBucketStoragePolicyImpl(String str) throws ServiceException {
        HashMap map = new HashMap();
        map.put(getSpecialParamForStorageClass().getOriginalStringCode(), "");
        Response responsePerformRestGet = performRestGet(str, null, map, null);
        verifyResponseContentType(responsePerformRestGet);
        BucketStoragePolicyConfiguration storagePolicy = ((XmlResponsesSaxParser.BucketStoragePolicyHandler) getXmlResponseSaxParser().parse(new HttpMethodReleaseInputStream(responsePerformRestGet), XmlResponsesSaxParser.BucketStoragePolicyHandler.class, false)).getStoragePolicy();
        setResponseHeaders(storagePolicy, cleanResponseHeaders(responsePerformRestGet));
        setStatusCode(storagePolicy, responsePerformRestGet.code());
        return storagePolicy;
    }

    protected BucketTagInfo getBucketTaggingImpl(String str) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.TAGGING.getOriginalStringCode(), "");
        Response responsePerformRestGet = performRestGet(str, null, map, null);
        verifyResponseContentType(responsePerformRestGet);
        BucketTagInfo bucketTagInfo = ((XmlResponsesSaxParser.BucketTagInfoHandler) getXmlResponseSaxParser().parse(new HttpMethodReleaseInputStream(responsePerformRestGet), XmlResponsesSaxParser.BucketTagInfoHandler.class, false)).getBucketTagInfo();
        setResponseHeaders(bucketTagInfo, cleanResponseHeaders(responsePerformRestGet));
        setStatusCode(bucketTagInfo, responsePerformRestGet.code());
        return bucketTagInfo;
    }

    protected BucketVersioningConfiguration getBucketVersioningImpl(String str) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.VERSIONING.getOriginalStringCode(), "");
        Response responsePerformRestGet = performRestGet(str, null, map, null);
        verifyResponseContentType(responsePerformRestGet);
        BucketVersioningConfiguration versioningStatus = ((XmlResponsesSaxParser.BucketVersioningHandler) getXmlResponseSaxParser().parse(new HttpMethodReleaseInputStream(responsePerformRestGet), XmlResponsesSaxParser.BucketVersioningHandler.class, false)).getVersioningStatus();
        setResponseHeaders(versioningStatus, cleanResponseHeaders(responsePerformRestGet));
        setStatusCode(versioningStatus, responsePerformRestGet.code());
        return versioningStatus;
    }

    protected WebsiteConfiguration getBucketWebsiteConfigurationImpl(String str) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.WEBSITE.getOriginalStringCode(), "");
        Response responsePerformRestGet = performRestGet(str, null, map, null);
        verifyResponseContentType(responsePerformRestGet);
        WebsiteConfiguration websiteConfig = ((XmlResponsesSaxParser.BucketWebsiteConfigurationHandler) getXmlResponseSaxParser().parse(new HttpMethodReleaseInputStream(responsePerformRestGet), XmlResponsesSaxParser.BucketWebsiteConfigurationHandler.class, false)).getWebsiteConfig();
        setResponseHeaders(websiteConfig, cleanResponseHeaders(responsePerformRestGet));
        setStatusCode(websiteConfig, responsePerformRestGet.code());
        return websiteConfig;
    }

    protected String getCredential(String str) {
        return getProviderCredentials().getAccessKey() + "/" + str + "/region/s3/aws4_request";
    }

    protected GetDisPolicyResult getDisPolicyImpl(String str) throws ServiceException {
        HashMap map = new HashMap();
        map.put(RequestParamEnum.DIS_POLICIES.getOriginalStringCode(), "");
        HashMap map2 = new HashMap();
        StringBuilder sb = new StringBuilder();
        sb.append(getProviderCredentials().getAuthType() != AuthTypeEnum.OBS ? "x-amz-" : "x-obs-");
        sb.append("oef-marker");
        map2.put(sb.toString(), "yes");
        Response responsePerformRestGet = performRestGet(str, null, map, map2, true);
        verifyResponseContentTypeForJson(responsePerformRestGet);
        try {
            GetDisPolicyResult getDisPolicyResult = new GetDisPolicyResult((DisPolicy) JSONChange.jsonToObj(new DisPolicy(), responsePerformRestGet.body().string()));
            getDisPolicyResult.setResponseHeaders(cleanResponseHeaders(responsePerformRestGet));
            setStatusCode(getDisPolicyResult, responsePerformRestGet.code());
            return getDisPolicyResult;
        } catch (IOException e) {
            throw new ServiceException(e);
        }
    }

    String getHeaderByMethodName(String str) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        try {
            IHeaders iHeaders = getIHeaders();
            Object objInvoke = iHeaders.getClass().getMethod(str, new Class[0]).invoke(iHeaders, new Object[0]);
            return objInvoke == null ? "" : objInvoke.toString();
        } catch (Exception e) {
            if (!log.isWarnEnabled()) {
                return null;
            }
            log.warn("Invoke getHeaderByMethodName error", e);
            return null;
        }
    }

    protected AccessControlList getObjectAclImpl(String str, String str2, String str3) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.ACL.getOriginalStringCode(), "");
        if (ServiceUtils.isValid(str3)) {
            map.put("versionId", str3.trim());
        }
        Response responsePerformRestGet = performRestGet(str, str2, map, null);
        verifyResponseContentType(responsePerformRestGet);
        AccessControlList accessControlList = ((XmlResponsesSaxParser.AccessControlListHandler) getXmlResponseSaxParser().parse(new HttpMethodReleaseInputStream(responsePerformRestGet), XmlResponsesSaxParser.AccessControlListHandler.class, false)).getAccessControlList();
        setResponseHeaders(accessControlList, cleanResponseHeaders(responsePerformRestGet));
        setStatusCode(accessControlList, responsePerformRestGet.code());
        return accessControlList;
    }

    protected ObsObject getObjectImpl(GetObjectRequest getObjectRequest) throws ServiceException {
        TransResult transResultTransGetObjectRequest = transGetObjectRequest(getObjectRequest);
        if (getObjectRequest.getRequestParameters() != null) {
            transResultTransGetObjectRequest.getParams().putAll(getObjectRequest.getRequestParameters());
        }
        return (ObsObject) getObjectImpl(false, getObjectRequest.getBucketName(), getObjectRequest.getObjectKey(), transResultTransGetObjectRequest.getHeaders(), transResultTransGetObjectRequest.getParams(), getObjectRequest.getProgressListener(), getObjectRequest.getProgressInterval());
    }

    protected Object getObjectImpl(boolean z, String str, String str2, Map<String, String> map, Map<String, String> map2, ProgressListener progressListener, long j) throws NumberFormatException, ServiceException {
        Response responsePerformRestHead = z ? performRestHead(str, str2, map2, map) : performRestGet(str, str2, map2, map);
        ObsFSAttribute obsFSAttributeFromResponse = getObsFSAttributeFromResponse(responsePerformRestHead);
        if (z) {
            responsePerformRestHead.close();
            return obsFSAttributeFromResponse;
        }
        ReadFileResult readFileResult = new ReadFileResult();
        readFileResult.setObjectKey(str2);
        readFileResult.setBucketName(str);
        readFileResult.setMetadata(obsFSAttributeFromResponse);
        InputStream inputStreamByteStream = responsePerformRestHead.body().byteStream();
        if (progressListener != null) {
            inputStreamByteStream = new ProgressInputStream(inputStreamByteStream, new SimpleProgressManager(obsFSAttributeFromResponse.getContentLength().longValue(), 0L, progressListener, j > 0 ? j : 102400L));
        }
        int intProperty = this.obsProperties.getIntProperty("httpclient.read-buffer-size", 8192);
        if (intProperty > 0) {
            inputStreamByteStream = new BufferedInputStream(inputStreamByteStream, intProperty);
        }
        readFileResult.setObjectContent(inputStreamByteStream);
        return readFileResult;
    }

    protected ObsFSAttribute getObjectMetadataImpl(GetObjectMetadataRequest getObjectMetadataRequest) throws ServiceException {
        HashMap map = new HashMap();
        transSseCHeaders(getObjectMetadataRequest.getSseCHeader(), map, getIHeaders());
        HashMap map2 = new HashMap();
        if (getObjectMetadataRequest.getVersionId() != null) {
            map2.put("versionId", getObjectMetadataRequest.getVersionId());
        }
        return (ObsFSAttribute) getObjectImpl(true, getObjectMetadataRequest.getBucketName(), getObjectMetadataRequest.getObjectKey(), map, map2, null, -1L);
    }

    GetBucketFSStatusResult getOptionInfoResult(Response response) {
        Headers headers = response.headers();
        Map<String, List<String>> multimap = headers.toMultimap();
        String str = headers.get("Access-Control-Max-Age");
        IHeaders iHeaders = getIHeaders();
        GetBucketFSStatusResult getBucketFSStatusResult = new GetBucketFSStatusResult(headers.get("Access-Control-Allow-Origin"), multimap.get("Access-Control-Allow-Headers"), str == null ? 0 : Integer.parseInt(str), multimap.get("Access-Control-Allow-Methods"), multimap.get("Access-Control-Expose-Headers"), StorageClassEnum.getValueFromCode(headers.get(iHeaders.defaultStorageClassHeader())), headers.get(iHeaders.bucketRegionHeader()), headers.get(iHeaders.serverVersionHeader()), FSStatusEnum.getValueFromCode(headers.get(iHeaders.fsFileInterfaceHeader())), AvailableZoneEnum.getValueFromCode(headers.get(iHeaders.azRedundancyHeader())), headers.get(iHeaders.epidHeader()));
        setResponseHeaders(getBucketFSStatusResult, cleanResponseHeaders(response));
        setStatusCode(getBucketFSStatusResult, response.code());
        return getBucketFSStatusResult;
    }

    SpecialParamEnum getSpecialParamForStorageClass() {
        return getProviderCredentials().getAuthType() == AuthTypeEnum.OBS ? SpecialParamEnum.STORAGECLASS : SpecialParamEnum.STORAGEPOLICY;
    }

    protected boolean headBucketImpl(String str) throws ServiceException {
        try {
            performRestHead(str, null, null, null);
            return true;
        } catch (ServiceException e) {
            if (e.getResponseCode() == 404) {
                return false;
            }
            throw e;
        }
    }

    protected ThreadPoolExecutor initThreadPool(AbstractBulkRequest abstractBulkRequest) {
        int taskThreadNum = abstractBulkRequest.getTaskThreadNum();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(taskThreadNum, taskThreadNum, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue(abstractBulkRequest.getTaskQueueNum()));
        threadPoolExecutor.setRejectedExecutionHandler(new BlockRejectedExecutionHandler());
        return threadPoolExecutor;
    }

    protected InitiateMultipartUploadResult initiateMultipartUploadImpl(InitiateMultipartUploadRequest initiateMultipartUploadRequest) throws ServiceException {
        TransResult transResultTransInitiateMultipartUploadRequest = transInitiateMultipartUploadRequest(initiateMultipartUploadRequest);
        prepareRESTHeaderAcl(transResultTransInitiateMultipartUploadRequest.getHeaders(), initiateMultipartUploadRequest.getAcl());
        Response responsePerformRestPost = performRestPost(initiateMultipartUploadRequest.getBucketName(), initiateMultipartUploadRequest.getObjectKey(), transResultTransInitiateMultipartUploadRequest.getHeaders(), transResultTransInitiateMultipartUploadRequest.getParams(), null, false);
        verifyResponseContentType(responsePerformRestPost);
        InitiateMultipartUploadResult initiateMultipartUploadResult = ((XmlResponsesSaxParser.InitiateMultipartUploadHandler) getXmlResponseSaxParser().parse(new HttpMethodReleaseInputStream(responsePerformRestPost), XmlResponsesSaxParser.InitiateMultipartUploadHandler.class, true)).getInitiateMultipartUploadResult();
        setResponseHeaders(initiateMultipartUploadResult, cleanResponseHeaders(responsePerformRestPost));
        setStatusCode(initiateMultipartUploadResult, responsePerformRestPost.code());
        return initiateMultipartUploadResult;
    }

    protected ListBucketsResult listAllBucketsImpl(ListBucketsRequest listBucketsRequest) throws ServiceException {
        HashMap map = new HashMap();
        if (listBucketsRequest != null && listBucketsRequest.isQueryLocation()) {
            putHeader(map, getIHeaders().locationHeader(), "true");
        }
        Response responsePerformRestGetForListBuckets = performRestGetForListBuckets("", null, null, map);
        verifyResponseContentType(responsePerformRestGetForListBuckets);
        XmlResponsesSaxParser.ListBucketsHandler listBucketsHandler = (XmlResponsesSaxParser.ListBucketsHandler) getXmlResponseSaxParser().parse(new HttpMethodReleaseInputStream(responsePerformRestGetForListBuckets), XmlResponsesSaxParser.ListBucketsHandler.class, true);
        Map<String, Object> mapCleanResponseHeaders = cleanResponseHeaders(responsePerformRestGetForListBuckets);
        ListBucketsResult listBucketsResult = new ListBucketsResult(listBucketsHandler.getBuckets(), listBucketsHandler.getOwner());
        setResponseHeaders(listBucketsResult, mapCleanResponseHeaders);
        setStatusCode(listBucketsResult, responsePerformRestGetForListBuckets.code());
        return listBucketsResult;
    }

    protected MultipartUploadListing listMultipartUploadsImpl(ListMultipartUploadsRequest listMultipartUploadsRequest) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.UPLOADS.getOriginalStringCode(), "");
        if (listMultipartUploadsRequest.getPrefix() != null) {
            map.put("prefix", listMultipartUploadsRequest.getPrefix());
        }
        if (listMultipartUploadsRequest.getDelimiter() != null) {
            map.put("delimiter", listMultipartUploadsRequest.getDelimiter());
        }
        if (listMultipartUploadsRequest.getMaxUploads() != null) {
            map.put("max-uploads", listMultipartUploadsRequest.getMaxUploads().toString());
        }
        if (listMultipartUploadsRequest.getKeyMarker() != null) {
            map.put("key-marker", listMultipartUploadsRequest.getKeyMarker());
        }
        if (listMultipartUploadsRequest.getUploadIdMarker() != null) {
            map.put("upload-id-marker", listMultipartUploadsRequest.getUploadIdMarker());
        }
        Response responsePerformRestGet = performRestGet(listMultipartUploadsRequest.getBucketName(), null, map, null);
        verifyResponseContentType(responsePerformRestGet);
        XmlResponsesSaxParser.ListMultipartUploadsHandler listMultipartUploadsHandler = (XmlResponsesSaxParser.ListMultipartUploadsHandler) getXmlResponseSaxParser().parse(new HttpMethodReleaseInputStream(responsePerformRestGet), XmlResponsesSaxParser.ListMultipartUploadsHandler.class, true);
        MultipartUploadListing multipartUploadListing = new MultipartUploadListing(listMultipartUploadsHandler.getBucketName() == null ? listMultipartUploadsRequest.getBucketName() : listMultipartUploadsHandler.getBucketName(), listMultipartUploadsHandler.getKeyMarker() == null ? listMultipartUploadsRequest.getKeyMarker() : listMultipartUploadsHandler.getKeyMarker(), listMultipartUploadsHandler.getUploadIdMarker() == null ? listMultipartUploadsRequest.getUploadIdMarker() : listMultipartUploadsHandler.getUploadIdMarker(), listMultipartUploadsHandler.getNextKeyMarker(), listMultipartUploadsHandler.getNextUploadIdMarker(), listMultipartUploadsHandler.getPrefix() == null ? listMultipartUploadsRequest.getPrefix() : listMultipartUploadsHandler.getPrefix(), listMultipartUploadsHandler.getMaxUploads(), listMultipartUploadsHandler.isTruncated(), listMultipartUploadsHandler.getMultipartUploadList(), listMultipartUploadsHandler.getDelimiter() == null ? listMultipartUploadsRequest.getDelimiter() : listMultipartUploadsHandler.getDelimiter(), (String[]) listMultipartUploadsHandler.getCommonPrefixes().toArray(new String[listMultipartUploadsHandler.getCommonPrefixes().size()]));
        setResponseHeaders(multipartUploadListing, cleanResponseHeaders(responsePerformRestGet));
        setStatusCode(multipartUploadListing, responsePerformRestGet.code());
        return multipartUploadListing;
    }

    protected ObjectListing listObjectsImpl(ListObjectsRequest listObjectsRequest) throws ServiceException {
        Response responsePerformRestGet = performRestGet(listObjectsRequest.getBucketName(), null, transListObjectsRequest(listObjectsRequest).getParams(), null);
        verifyResponseContentType(responsePerformRestGet);
        XmlResponsesSaxParser.ListObjectsHandler listObjectsHandler = (XmlResponsesSaxParser.ListObjectsHandler) getXmlResponseSaxParser().parse(new HttpMethodReleaseInputStream(responsePerformRestGet), XmlResponsesSaxParser.ListObjectsHandler.class, true);
        ObjectListing objectListing = new ObjectListing(listObjectsHandler.getObjects(), listObjectsHandler.getCommonPrefixes(), listObjectsHandler.getBucketName() == null ? listObjectsRequest.getBucketName() : listObjectsHandler.getBucketName(), listObjectsHandler.isListingTruncated(), listObjectsHandler.getRequestPrefix() == null ? listObjectsRequest.getPrefix() : listObjectsHandler.getRequestPrefix(), listObjectsHandler.getRequestMarker() == null ? listObjectsRequest.getMarker() : listObjectsHandler.getRequestMarker(), listObjectsHandler.getRequestMaxKeys(), listObjectsHandler.getRequestDelimiter() == null ? listObjectsRequest.getDelimiter() : listObjectsHandler.getRequestDelimiter(), listObjectsHandler.getMarkerForNextListing(), responsePerformRestGet.header(getIHeaders().bucketRegionHeader()));
        setResponseHeaders(objectListing, cleanResponseHeaders(responsePerformRestGet));
        setStatusCode(objectListing, responsePerformRestGet.code());
        return objectListing;
    }

    protected ListPartsResult listPartsImpl(ListPartsRequest listPartsRequest) throws ServiceException {
        HashMap map = new HashMap();
        map.put("uploadId", listPartsRequest.getUploadId());
        if (listPartsRequest.getMaxParts() != null) {
            map.put("max-parts", listPartsRequest.getMaxParts().toString());
        }
        if (listPartsRequest.getPartNumberMarker() != null) {
            map.put("part-number-marker", listPartsRequest.getPartNumberMarker().toString());
        }
        String partNumberMarker = null;
        Response responsePerformRestGet = performRestGet(listPartsRequest.getBucketName(), listPartsRequest.getKey(), map, null);
        verifyResponseContentType(responsePerformRestGet);
        XmlResponsesSaxParser.ListPartsHandler listPartsHandler = (XmlResponsesSaxParser.ListPartsHandler) getXmlResponseSaxParser().parse(new HttpMethodReleaseInputStream(responsePerformRestGet), XmlResponsesSaxParser.ListPartsHandler.class, true);
        String bucketName = listPartsHandler.getBucketName() == null ? listPartsRequest.getBucketName() : listPartsHandler.getBucketName();
        String key = listPartsHandler.getObjectKey() == null ? listPartsRequest.getKey() : listPartsHandler.getObjectKey();
        String uploadId = listPartsHandler.getUploadId() == null ? listPartsRequest.getUploadId() : listPartsHandler.getUploadId();
        Owner initiator = listPartsHandler.getInitiator();
        Owner owner = listPartsHandler.getOwner();
        StorageClassEnum valueFromCode = StorageClassEnum.getValueFromCode(listPartsHandler.getStorageClass());
        List<Multipart> multiPartList = listPartsHandler.getMultiPartList();
        Integer numValueOf = Integer.valueOf(listPartsHandler.getMaxParts());
        boolean zIsTruncated = listPartsHandler.isTruncated();
        if (listPartsHandler.getPartNumberMarker() != null) {
            partNumberMarker = listPartsHandler.getPartNumberMarker();
        } else if (listPartsRequest.getPartNumberMarker() != null) {
            partNumberMarker = listPartsRequest.getPartNumberMarker().toString();
        }
        ListPartsResult listPartsResult = new ListPartsResult(bucketName, key, uploadId, initiator, owner, valueFromCode, multiPartList, numValueOf, zIsTruncated, partNumberMarker, listPartsHandler.getNextPartNumberMarker());
        setResponseHeaders(listPartsResult, cleanResponseHeaders(responsePerformRestGet));
        setStatusCode(listPartsResult, responsePerformRestGet.code());
        return listPartsResult;
    }

    protected ListVersionsResult listVersionsImpl(ListVersionsRequest listVersionsRequest) throws ServiceException {
        Response responsePerformRestGet = performRestGet(listVersionsRequest.getBucketName(), null, transListVersionsRequest(listVersionsRequest).getParams(), null);
        verifyResponseContentType(responsePerformRestGet);
        XmlResponsesSaxParser.ListVersionsHandler listVersionsHandler = (XmlResponsesSaxParser.ListVersionsHandler) getXmlResponseSaxParser().parse(new HttpMethodReleaseInputStream(responsePerformRestGet), XmlResponsesSaxParser.ListVersionsHandler.class, true);
        List<VersionOrDeleteMarker> items = listVersionsHandler.getItems();
        ListVersionsResult listVersionsResult = new ListVersionsResult(listVersionsHandler.getBucketName() == null ? listVersionsRequest.getBucketName() : listVersionsHandler.getBucketName(), listVersionsHandler.getRequestPrefix() == null ? listVersionsRequest.getPrefix() : listVersionsHandler.getRequestPrefix(), listVersionsHandler.getKeyMarker() == null ? listVersionsRequest.getKeyMarker() : listVersionsHandler.getKeyMarker(), listVersionsHandler.getNextKeyMarker(), listVersionsHandler.getVersionIdMarker() == null ? listVersionsRequest.getVersionIdMarker() : listVersionsHandler.getVersionIdMarker(), listVersionsHandler.getNextVersionIdMarker(), String.valueOf(listVersionsHandler.getRequestMaxKeys()), listVersionsHandler.isListingTruncated(), (VersionOrDeleteMarker[]) items.toArray(new VersionOrDeleteMarker[items.size()]), listVersionsHandler.getCommonPrefixes(), responsePerformRestGet.header(getIHeaders().bucketRegionHeader()), listVersionsHandler.getDelimiter() == null ? listVersionsRequest.getDelimiter() : listVersionsHandler.getDelimiter());
        setResponseHeaders(listVersionsResult, cleanResponseHeaders(responsePerformRestGet));
        setStatusCode(listVersionsResult, responsePerformRestGet.code());
        return listVersionsResult;
    }

    protected BucketMetadataInfoResult optionsImpl(String str, String str2, OptionsInfoRequest optionsInfoRequest) throws ServiceException {
        IdentityHashMap identityHashMap = new IdentityHashMap();
        if (ServiceUtils.isValid(optionsInfoRequest.getOrigin())) {
            identityHashMap.put("Origin", optionsInfoRequest.getOrigin().trim());
        }
        for (int i = 0; optionsInfoRequest.getRequestMethod() != null && i < optionsInfoRequest.getRequestMethod().size(); i++) {
            identityHashMap.put(new String("Access-Control-Request-Method"), optionsInfoRequest.getRequestMethod().get(i));
        }
        for (int i2 = 0; optionsInfoRequest.getRequestHeaders() != null && i2 < optionsInfoRequest.getRequestHeaders().size(); i2++) {
            identityHashMap.put(new String("Access-Control-Request-Headers"), optionsInfoRequest.getRequestHeaders().get(i2));
        }
        return getOptionInfoResult(performRestOptions(str, str2, identityHashMap, null, true));
    }

    boolean prepareRESTHeaderAcl(Map<String, String> map, AccessControlList accessControlList) throws ServiceException {
        return getProviderCredentials().getAuthType() == AuthTypeEnum.OBS ? prepareRESTHeaderAclForOBS(map, accessControlList) : prepareRESTHeaderAclForV2(map, accessControlList);
    }

    boolean prepareRESTHeaderAclForOBS(Map<String, String> map, AccessControlList accessControlList) throws ServiceException {
        String str;
        boolean z = true;
        if (accessControlList == AccessControlList.REST_CANNED_PRIVATE) {
            str = "private";
        } else if (accessControlList == AccessControlList.REST_CANNED_PUBLIC_READ) {
            str = "public-read";
        } else if (accessControlList == AccessControlList.REST_CANNED_PUBLIC_READ_WRITE) {
            str = "public-read-write";
        } else if (accessControlList == AccessControlList.REST_CANNED_PUBLIC_READ_DELIVERED) {
            str = "public-read-delivered";
        } else {
            if (accessControlList != AccessControlList.REST_CANNED_PUBLIC_READ_WRITE_DELIVERED) {
                if (accessControlList == AccessControlList.REST_CANNED_AUTHENTICATED_READ) {
                    str = "authenticated-read";
                } else if (accessControlList == AccessControlList.REST_CANNED_BUCKET_OWNER_READ) {
                    str = "bucket-owner-read";
                } else if (accessControlList == AccessControlList.REST_CANNED_BUCKET_OWNER_FULL_CONTROL) {
                    str = "bucket-owner-full-control";
                } else if (accessControlList == AccessControlList.REST_CANNED_LOG_DELIVERY_WRITE) {
                    str = "log-delivery-write";
                } else {
                    str = null;
                }
                if (z) {
                    log.info((CharSequence) ("Invalid Canned ACL:" + str));
                }
                String strAclHeader = getIHeaders().aclHeader();
                if (str != null) {
                    map.put(strAclHeader, str);
                }
                return map.containsKey(strAclHeader);
            }
            str = "public-read-write-delivered";
        }
        z = false;
        if (z) {
        }
        String strAclHeader2 = getIHeaders().aclHeader();
        if (str != null) {
        }
        return map.containsKey(strAclHeader2);
    }

    boolean prepareRESTHeaderAclForOBSObject(Map<String, String> map, AccessControlList accessControlList) throws ServiceException {
        String str = "public-read-write";
        boolean z = true;
        if (accessControlList != AccessControlList.REST_CANNED_PRIVATE) {
            if (accessControlList != AccessControlList.REST_CANNED_PUBLIC_READ) {
                if (accessControlList != AccessControlList.REST_CANNED_PUBLIC_READ_WRITE) {
                    if (accessControlList == AccessControlList.REST_CANNED_PUBLIC_READ_DELIVERED) {
                        str = "public-read";
                    } else if (accessControlList != AccessControlList.REST_CANNED_PUBLIC_READ_WRITE_DELIVERED) {
                        if (accessControlList == AccessControlList.REST_CANNED_AUTHENTICATED_READ) {
                            str = "authenticated-read";
                        } else if (accessControlList == AccessControlList.REST_CANNED_BUCKET_OWNER_READ) {
                            str = "bucket-owner-read";
                        } else if (accessControlList == AccessControlList.REST_CANNED_BUCKET_OWNER_FULL_CONTROL) {
                            str = "bucket-owner-full-control";
                        } else if (accessControlList == AccessControlList.REST_CANNED_LOG_DELIVERY_WRITE) {
                            str = "log-delivery-write";
                        } else {
                            str = null;
                        }
                    }
                }
            }
            if (z) {
                log.info((CharSequence) ("Invalid Canned ACL:" + str));
            }
            String strAclHeader = getIHeaders().aclHeader();
            if (str != null) {
                map.put(strAclHeader, str);
            }
            return map.containsKey(strAclHeader);
        }
        str = "private";
        z = false;
        if (z) {
        }
        String strAclHeader2 = getIHeaders().aclHeader();
        if (str != null) {
        }
        return map.containsKey(strAclHeader2);
    }

    boolean prepareRESTHeaderAclForV2(Map<String, String> map, AccessControlList accessControlList) {
        String str = "public-read-write";
        if (accessControlList == AccessControlList.REST_CANNED_PRIVATE) {
            str = "private";
        } else if (accessControlList != AccessControlList.REST_CANNED_PUBLIC_READ) {
            if (accessControlList != AccessControlList.REST_CANNED_PUBLIC_READ_WRITE) {
                if (accessControlList == AccessControlList.REST_CANNED_PUBLIC_READ_DELIVERED) {
                    str = "public-read";
                } else if (accessControlList != AccessControlList.REST_CANNED_PUBLIC_READ_WRITE_DELIVERED) {
                    str = accessControlList == AccessControlList.REST_CANNED_AUTHENTICATED_READ ? "authenticated-read" : accessControlList == AccessControlList.REST_CANNED_BUCKET_OWNER_READ ? "bucket-owner-read" : accessControlList == AccessControlList.REST_CANNED_BUCKET_OWNER_FULL_CONTROL ? "bucket-owner-full-control" : accessControlList == AccessControlList.REST_CANNED_LOG_DELIVERY_WRITE ? "log-delivery-write" : null;
                }
            }
        }
        String strAclHeader = getIHeaders().aclHeader();
        if (str != null) {
            map.put(strAclHeader, str);
        }
        return map.containsKey(strAclHeader);
    }

    boolean prepareRESTHeaderAclObject(Map<String, String> map, AccessControlList accessControlList) throws ServiceException {
        return getProviderCredentials().getAuthType() == AuthTypeEnum.OBS ? prepareRESTHeaderAclForOBSObject(map, accessControlList) : prepareRESTHeaderAclForV2(map, accessControlList);
    }

    void putAclImpl(String str, String str2, AccessControlList accessControlList, String str3) throws ServiceException {
        if (accessControlList != null) {
            HashMap map = new HashMap();
            map.put(SpecialParamEnum.ACL.getOriginalStringCode(), "");
            if (str3 != null) {
                map.put("versionId", str3);
            }
            HashMap map2 = new HashMap();
            map2.put("Content-Type", "application/xml");
            String strTransAccessControlList = getIConvertor().transAccessControlList(accessControlList, !ServiceUtils.isValid(str2));
            map2.put("Content-Length", String.valueOf(strTransAccessControlList.length()));
            performRestPut(str, str2, map2, map, createRequestBody("application/xml", strTransAccessControlList), true);
        }
    }

    protected HeaderResponse putDisPolicyImpl(String str, String str2) throws ServiceException {
        HashMap map = new HashMap();
        map.put(RequestParamEnum.DIS_POLICIES.getOriginalStringCode(), "");
        HashMap map2 = new HashMap();
        map2.put("Content-Type", "application/json");
        StringBuilder sb = new StringBuilder();
        sb.append(getProviderCredentials().getAuthType() != AuthTypeEnum.OBS ? "x-amz-" : "x-obs-");
        sb.append("oef-marker");
        map2.put(sb.toString(), "yes");
        Response responsePerformRestPut = performRestPut(str, null, map2, map, createRequestBody("application/json", str2), false, true);
        HeaderResponse headerResponseBuild = build(cleanResponseHeaders(responsePerformRestPut));
        setStatusCode(headerResponseBuild, responsePerformRestPut.code());
        return headerResponseBuild;
    }

    void putHeader(Map<String, String> map, String str, String str2) {
        if (ServiceUtils.isValid(str)) {
            map.put(str, str2);
        }
    }

    protected ObsFSFile putObjectImpl(PutObjectRequest putObjectRequest) throws Throwable {
        TransResult transResultTransPutObjectRequest;
        AccessControlList acl = putObjectRequest.getAcl();
        TransResult transResult = null;
        try {
            transResultTransPutObjectRequest = transPutObjectRequest(putObjectRequest);
        } catch (Throwable th) {
            th = th;
        }
        try {
            boolean z = !prepareRESTHeaderAcl(transResultTransPutObjectRequest.getHeaders(), acl);
            Response responsePerformRestPut = performRestPut(putObjectRequest.getBucketName(), putObjectRequest.getObjectKey(), transResultTransPutObjectRequest.getHeaders(), null, transResultTransPutObjectRequest.body, true);
            if (transResultTransPutObjectRequest != null && transResultTransPutObjectRequest.body != null && putObjectRequest.isAutoClose() && (transResultTransPutObjectRequest.body instanceof Closeable)) {
                ServiceUtils.closeStream((Closeable) transResultTransPutObjectRequest.body);
            }
            ObsFSFile obsFSFile = new ObsFSFile(putObjectRequest.getBucketName(), putObjectRequest.getObjectKey(), responsePerformRestPut.header("ETag"), responsePerformRestPut.header(getIHeaders().versionIdHeader()), StorageClassEnum.getValueFromCode(responsePerformRestPut.header(getIHeaders().storageClassHeader())), getObjectUrl(putObjectRequest.getBucketName(), putObjectRequest.getObjectKey()));
            setResponseHeaders(obsFSFile, cleanResponseHeaders(responsePerformRestPut));
            setStatusCode(obsFSFile, responsePerformRestPut.code());
            if (z && acl != null) {
                try {
                    putAclImpl(putObjectRequest.getBucketName(), putObjectRequest.getObjectKey(), acl, null);
                } catch (Exception e) {
                    if (log.isWarnEnabled()) {
                        log.warn("Try to set object acl error", e);
                    }
                }
            }
            return obsFSFile;
        } catch (Throwable th2) {
            th = th2;
            transResult = transResultTransPutObjectRequest;
            if (transResult != null && transResult.body != null && putObjectRequest.isAutoClose() && (transResult.body instanceof Closeable)) {
                ServiceUtils.closeStream((Closeable) transResult.body);
            }
            throw th;
        }
    }

    protected QueryExtensionPolicyResult queryExtensionPolicyImpl(String str) throws ServiceException {
        HashMap map = new HashMap();
        map.put(RequestParamEnum.EXTENSION_POLICY.getOriginalStringCode(), "");
        HashMap map2 = new HashMap();
        StringBuilder sb = new StringBuilder();
        sb.append(getProviderCredentials().getAuthType() != AuthTypeEnum.OBS ? "x-amz-" : "x-obs-");
        sb.append("oef-marker");
        map2.put(sb.toString(), "yes");
        Response responsePerformRestGet = performRestGet(str, null, map, map2, true);
        verifyResponseContentTypeForJson(responsePerformRestGet);
        try {
            QueryExtensionPolicyResult queryExtensionPolicyResult = (QueryExtensionPolicyResult) JSONChange.jsonToObj(new QueryExtensionPolicyResult(), responsePerformRestGet.body().string());
            queryExtensionPolicyResult.setResponseHeaders(cleanResponseHeaders(responsePerformRestGet));
            setStatusCode(queryExtensionPolicyResult, responsePerformRestGet.code());
            return queryExtensionPolicyResult;
        } catch (IOException e) {
            throw new ServiceException(e);
        }
    }

    protected QueryAsynchFetchJobsResult queryFetchJobImpl(String str, String str2) throws ServiceException {
        HashMap map = new HashMap();
        map.put(RequestParamEnum.ASYNC_FETCH_JOBS.getOriginalStringCode() + "/" + str2, "");
        HashMap map2 = new HashMap();
        map2.put("Content-Type", "application/json");
        StringBuilder sb = new StringBuilder();
        sb.append(getProviderCredentials().getAuthType() != AuthTypeEnum.OBS ? "x-amz-" : "x-obs-");
        sb.append("oef-marker");
        map2.put(sb.toString(), "yes");
        Response responsePerformRestGet = performRestGet(str, null, map, map2, true);
        verifyResponseContentTypeForJson(responsePerformRestGet);
        try {
            QueryAsynchFetchJobsResult queryAsynchFetchJobsResult = (QueryAsynchFetchJobsResult) JSONChange.jsonToObj(new QueryAsynchFetchJobsResult(), responsePerformRestGet.body().string());
            queryAsynchFetchJobsResult.setResponseHeaders(cleanResponseHeaders(responsePerformRestGet));
            setStatusCode(queryAsynchFetchJobsResult, responsePerformRestGet.code());
            return queryAsynchFetchJobsResult;
        } catch (IOException e) {
            throw new ServiceException(e);
        }
    }

    protected ReadAheadQueryResult queryReadAheadObjectsTaskImpl(String str, String str2) throws ServiceException {
        HashMap map = new HashMap();
        map.put("readAhead", "");
        map.put("taskID", str2);
        Response responsePerformRestGet = performRestGet(str, null, map, null);
        verifyResponseContentTypeForJson(responsePerformRestGet);
        try {
            ReadAheadQueryResult readAheadQueryResult = (ReadAheadQueryResult) JSONChange.jsonToObj(new ReadAheadQueryResult(), responsePerformRestGet.body().string());
            readAheadQueryResult.setResponseHeaders(cleanResponseHeaders(responsePerformRestGet));
            setStatusCode(readAheadQueryResult, responsePerformRestGet.code());
            return readAheadQueryResult;
        } catch (IOException e) {
            throw new ServiceException(e);
        }
    }

    protected ReadAheadResult readAheadObjectsImpl(ReadAheadRequest readAheadRequest) throws ServiceException {
        HashMap map = new HashMap();
        map.put("readAhead", "");
        map.put("prefix", readAheadRequest.getPrefix());
        HashMap map2 = new HashMap();
        map2.put("x-cache-control", readAheadRequest.getCacheOption().getCode() + ", ttl=" + readAheadRequest.getTtl());
        Response responsePerformRestPost = performRestPost(readAheadRequest.getBucketName(), null, map2, map, null, false);
        verifyResponseContentTypeForJson(responsePerformRestPost);
        try {
            ReadAheadResult readAheadResult = (ReadAheadResult) JSONChange.jsonToObj(new ReadAheadResult(), responsePerformRestPost.body().string());
            readAheadResult.setResponseHeaders(cleanResponseHeaders(responsePerformRestPost));
            setStatusCode(readAheadResult, responsePerformRestPost.code());
            return readAheadResult;
        } catch (IOException e) {
            throw new ServiceException(e);
        }
    }

    protected void recordBulkTaskStatus(DefaultTaskProgressStatus defaultTaskProgressStatus, TaskCallback<DeleteObjectResult, String> taskCallback, TaskProgressListener taskProgressListener, int i) {
        defaultTaskProgressStatus.execTaskIncrement();
        if (taskProgressListener != null) {
            if (defaultTaskProgressStatus.getExecTaskNum() % i == 0) {
                taskProgressListener.progressChanged(defaultTaskProgressStatus);
            }
            if (defaultTaskProgressStatus.getExecTaskNum() == defaultTaskProgressStatus.getTotalTaskNum()) {
                taskProgressListener.progressChanged(defaultTaskProgressStatus);
            }
        }
    }

    protected RenameResult renameObjectImpl(RenameRequest renameRequest) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.RENAME.getOriginalStringCode(), "");
        map.put("name", renameRequest.getNewObjectKey());
        Response responsePerformRestPost = performRestPost(renameRequest.getBucketName(), renameRequest.getObjectKey(), null, map, null, true);
        RenameResult renameResult = new RenameResult();
        setResponseHeaders(renameResult, cleanResponseHeaders(responsePerformRestPost));
        setStatusCode(renameResult, responsePerformRestPost.code());
        return renameResult;
    }

    protected RestoreObjectRequest.RestoreObjectStatus restoreObjectImpl(RestoreObjectRequest restoreObjectRequest) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.RESTORE.getOriginalStringCode(), "");
        if (restoreObjectRequest.getVersionId() != null) {
            map.put("versionId", restoreObjectRequest.getVersionId());
        }
        HashMap map2 = new HashMap();
        String strTransRestoreObjectRequest = getIConvertor().transRestoreObjectRequest(restoreObjectRequest);
        map2.put("Content-MD5", ServiceUtils.computeMD5(strTransRestoreObjectRequest));
        map2.put("Content-Type", "application/xml");
        Response responsePerformRestPost = performRestPost(restoreObjectRequest.getBucketName(), restoreObjectRequest.getObjectKey(), map2, map, createRequestBody("application/xml", strTransRestoreObjectRequest), true);
        RestoreObjectRequest.RestoreObjectStatus restoreObjectStatusValueOf = RestoreObjectRequest.RestoreObjectStatus.valueOf(responsePerformRestPost.code());
        setResponseHeaders(restoreObjectStatusValueOf, cleanResponseHeaders(responsePerformRestPost));
        setStatusCode(restoreObjectStatusValueOf, responsePerformRestPost.code());
        return restoreObjectStatusValueOf;
    }

    protected RestoreObjectResult restoreObjectV2Impl(RestoreObjectRequest restoreObjectRequest) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.RESTORE.getOriginalStringCode(), "");
        if (restoreObjectRequest.getVersionId() != null) {
            map.put("versionId", restoreObjectRequest.getVersionId());
        }
        HashMap map2 = new HashMap();
        String strTransRestoreObjectRequest = getIConvertor().transRestoreObjectRequest(restoreObjectRequest);
        map2.put("Content-MD5", ServiceUtils.computeMD5(strTransRestoreObjectRequest));
        map2.put("Content-Type", "application/xml");
        Response responsePerformRestPost = performRestPost(restoreObjectRequest.getBucketName(), restoreObjectRequest.getObjectKey(), map2, map, createRequestBody("application/xml", strTransRestoreObjectRequest), true);
        RestoreObjectResult restoreObjectResult = new RestoreObjectResult(restoreObjectRequest.getBucketName(), restoreObjectRequest.getObjectKey(), restoreObjectRequest.getVersionId());
        setResponseHeaders(restoreObjectResult, cleanResponseHeaders(responsePerformRestPost));
        setStatusCode(restoreObjectResult, responsePerformRestPost.code());
        return restoreObjectResult;
    }

    protected HeaderResponse setBucketAclImpl(String str, String str2, AccessControlList accessControlList) throws ServiceException {
        RequestBody requestBodyCreateRequestBody;
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.ACL.getOriginalStringCode(), "");
        if (ServiceUtils.isValid(str2)) {
            accessControlList = getIConvertor().transCannedAcl(str2.trim());
        }
        HashMap map2 = new HashMap();
        map2.put("Content-Type", "application/xml");
        if (!prepareRESTHeaderAcl(map2, accessControlList)) {
            String strTransAccessControlList = accessControlList != null ? getIConvertor().transAccessControlList(accessControlList, true) : "";
            map2.put("Content-Length", String.valueOf(strTransAccessControlList.length()));
            requestBodyCreateRequestBody = createRequestBody("application/xml", strTransAccessControlList);
        } else {
            requestBodyCreateRequestBody = null;
        }
        Response responsePerformRestPut = performRestPut(str, null, map2, map, requestBodyCreateRequestBody, true);
        HeaderResponse headerResponseBuild = build(cleanResponseHeaders(responsePerformRestPut));
        setStatusCode(headerResponseBuild, responsePerformRestPut.code());
        return headerResponseBuild;
    }

    protected HeaderResponse setBucketCorsImpl(String str, BucketCors bucketCors) throws ServiceException {
        String strTransBucketCors = bucketCors == null ? "" : getIConvertor().transBucketCors(bucketCors);
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.CORS.getOriginalStringCode(), "");
        HashMap map2 = new HashMap();
        map2.put("Content-Type", "application/xml");
        map2.put("Content-MD5", ServiceUtils.computeMD5(strTransBucketCors));
        map2.put("Content-Length", String.valueOf(strTransBucketCors.length()));
        Response responsePerformRestPut = performRestPut(str, null, map2, map, createRequestBody("application/xml", strTransBucketCors), true);
        HeaderResponse headerResponseBuild = build(cleanResponseHeaders(responsePerformRestPut));
        setStatusCode(headerResponseBuild, responsePerformRestPut.code());
        return headerResponseBuild;
    }

    protected HeaderResponse setBucketDirectColdAccessImpl(String str, BucketDirectColdAccess bucketDirectColdAccess) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.DIRECTCOLDACCESS.getOriginalStringCode(), "");
        HashMap map2 = new HashMap();
        String strTransBucketDirectColdAccess = getIConvertor().transBucketDirectColdAccess(bucketDirectColdAccess);
        map2.put("Content-MD5", ServiceUtils.computeMD5(strTransBucketDirectColdAccess));
        map2.put("Content-Type", "application/xml");
        Response responsePerformRestPut = performRestPut(str, null, map2, map, createRequestBody("application/xml", strTransBucketDirectColdAccess), true);
        HeaderResponse headerResponseBuild = build(cleanResponseHeaders(responsePerformRestPut));
        setStatusCode(headerResponseBuild, responsePerformRestPut.code());
        return headerResponseBuild;
    }

    protected HeaderResponse setBucketEncryptionImpl(String str, BucketEncryption bucketEncryption) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.ENCRYPTION.getOriginalStringCode(), "");
        HashMap map2 = new HashMap();
        map2.put("Content-Type", "application/xml");
        String strTransBucketEcryption = bucketEncryption != null ? getIConvertor().transBucketEcryption(bucketEncryption) : "";
        map2.put("Content-Length", String.valueOf(strTransBucketEcryption.length()));
        Response responsePerformRestPut = performRestPut(str, null, map2, map, createRequestBody("application/xml", strTransBucketEcryption), true);
        HeaderResponse headerResponseBuild = build(cleanResponseHeaders(responsePerformRestPut));
        setStatusCode(headerResponseBuild, responsePerformRestPut.code());
        return headerResponseBuild;
    }

    protected HeaderResponse setBucketFSStatusImpl(SetBucketFSStatusRequest setBucketFSStatusRequest) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.FILEINTERFACE.getOriginalStringCode(), "");
        Response responsePerformRestPut = performRestPut(setBucketFSStatusRequest.getBucketName(), null, null, map, createRequestBody("application/xml", getIConvertor().transBucketFileInterface(setBucketFSStatusRequest.getStatus())), true);
        HeaderResponse headerResponseBuild = build(cleanResponseHeaders(responsePerformRestPut));
        setStatusCode(headerResponseBuild, responsePerformRestPut.code());
        return headerResponseBuild;
    }

    protected HeaderResponse setBucketLifecycleConfigurationImpl(String str, LifecycleConfiguration lifecycleConfiguration) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.LIFECYCLE.getOriginalStringCode(), "");
        HashMap map2 = new HashMap();
        String strTransLifecycleConfiguration = getIConvertor().transLifecycleConfiguration(lifecycleConfiguration);
        map2.put("Content-MD5", ServiceUtils.computeMD5(strTransLifecycleConfiguration));
        map2.put("Content-Type", "application/xml");
        Response responsePerformRestPut = performRestPut(str, null, map2, map, createRequestBody("application/xml", strTransLifecycleConfiguration), true);
        HeaderResponse headerResponseBuild = build(cleanResponseHeaders(responsePerformRestPut));
        setStatusCode(headerResponseBuild, responsePerformRestPut.code());
        return headerResponseBuild;
    }

    HeaderResponse setBucketLoggingConfigurationImpl(String str, BucketLoggingConfiguration bucketLoggingConfiguration) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.LOGGING.getOriginalStringCode(), "");
        HashMap map2 = new HashMap();
        map2.put("Content-Type", "application/xml");
        Response responsePerformRestPut = performRestPut(str, null, map2, map, createRequestBody("application/xml", bucketLoggingConfiguration != null ? getIConvertor().transBucketLoggingConfiguration(bucketLoggingConfiguration) : ""), true);
        HeaderResponse headerResponseBuild = build(cleanResponseHeaders(responsePerformRestPut));
        setStatusCode(headerResponseBuild, responsePerformRestPut.code());
        return headerResponseBuild;
    }

    protected HeaderResponse setBucketLoggingConfigurationImpl(String str, BucketLoggingConfiguration bucketLoggingConfiguration, boolean z) throws ServiceException {
        if (bucketLoggingConfiguration.isLoggingEnabled() && z && getProviderCredentials().getAuthType() != AuthTypeEnum.OBS) {
            AccessControlList bucketAclImpl = getBucketAclImpl(bucketLoggingConfiguration.getTargetBucketName());
            boolean z2 = false;
            boolean z3 = false;
            for (GrantAndPermission grantAndPermission : bucketAclImpl.getGrantAndPermissions()) {
                if (grantAndPermission.getGrantee() instanceof GroupGrantee) {
                    if ("http://acs.amazonaws.com/groups/s3/LogDelivery".equals(getIConvertor().transGroupGrantee(((GroupGrantee) grantAndPermission.getGrantee()).getGroupGranteeType()))) {
                        if (Permission.PERMISSION_WRITE.equals(grantAndPermission.getPermission())) {
                            z2 = true;
                        } else if (Permission.PERMISSION_READ_ACP.equals(grantAndPermission.getPermission())) {
                            z3 = true;
                        }
                    }
                }
            }
            if (!z2 || !z3) {
                if (log.isWarnEnabled()) {
                    log.warn((CharSequence) ("Target logging bucket '" + bucketLoggingConfiguration.getTargetBucketName() + "' does not have the necessary ACL settings, updating ACL now"));
                }
                if (bucketAclImpl.getOwner() != null) {
                    bucketAclImpl.getOwner().setDisplayName(null);
                }
                bucketAclImpl.grantPermission(GroupGrantee.LOG_DELIVERY, Permission.PERMISSION_WRITE);
                bucketAclImpl.grantPermission(GroupGrantee.LOG_DELIVERY, Permission.PERMISSION_READ_ACP);
                setBucketAclImpl(bucketLoggingConfiguration.getTargetBucketName(), null, bucketAclImpl);
            } else if (log.isDebugEnabled()) {
                log.debug((CharSequence) ("Target logging bucket '" + bucketLoggingConfiguration.getTargetBucketName() + "' has the necessary ACL settings"));
            }
        }
        return setBucketLoggingConfigurationImpl(str, bucketLoggingConfiguration);
    }

    protected HeaderResponse setBucketNotificationImpl(String str, BucketNotificationConfiguration bucketNotificationConfiguration) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.NOTIFICATION.getOriginalStringCode(), "");
        HashMap map2 = new HashMap();
        map2.put("Content-Type", "application/xml");
        Response responsePerformRestPut = performRestPut(str, null, map2, map, createRequestBody("application/xml", getIConvertor().transBucketNotificationConfiguration(bucketNotificationConfiguration)), true);
        HeaderResponse headerResponseBuild = build(cleanResponseHeaders(responsePerformRestPut));
        setStatusCode(headerResponseBuild, responsePerformRestPut.code());
        return headerResponseBuild;
    }

    protected HeaderResponse setBucketPolicyImpl(String str, String str2) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.POLICY.getOriginalStringCode(), "");
        HashMap map2 = new HashMap();
        map2.put("Content-Type", "text/plain");
        Response responsePerformRestPut = performRestPut(str, null, map2, map, createRequestBody("text/plain", str2), true);
        HeaderResponse headerResponseBuild = build(cleanResponseHeaders(responsePerformRestPut));
        setStatusCode(headerResponseBuild, responsePerformRestPut.code());
        return headerResponseBuild;
    }

    protected HeaderResponse setBucketQuotaImpl(String str, BucketQuota bucketQuota) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.QUOTA.getOriginalStringCode(), "");
        HashMap map2 = new HashMap();
        map2.put("Content-Type", "application/xml");
        String strTransBucketQuota = bucketQuota != null ? getIConvertor().transBucketQuota(bucketQuota) : "";
        map2.put("Content-Length", String.valueOf(strTransBucketQuota.length()));
        Response responsePerformRestPut = performRestPut(str, null, map2, map, createRequestBody("application/xml", strTransBucketQuota), true);
        HeaderResponse headerResponseBuild = build(cleanResponseHeaders(responsePerformRestPut));
        setStatusCode(headerResponseBuild, responsePerformRestPut.code());
        return headerResponseBuild;
    }

    protected HeaderResponse setBucketReplicationConfigurationImpl(String str, ReplicationConfiguration replicationConfiguration) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.REPLICATION.getOriginalStringCode(), "");
        HashMap map2 = new HashMap();
        String strTransReplicationConfiguration = getIConvertor().transReplicationConfiguration(replicationConfiguration);
        map2.put("Content-MD5", ServiceUtils.computeMD5(strTransReplicationConfiguration));
        map2.put("Content-Type", "application/xml");
        Response responsePerformRestPut = performRestPut(str, null, map2, map, createRequestBody("application/xml", strTransReplicationConfiguration), true);
        HeaderResponse headerResponseBuild = build(cleanResponseHeaders(responsePerformRestPut));
        setStatusCode(headerResponseBuild, responsePerformRestPut.code());
        return headerResponseBuild;
    }

    protected HeaderResponse setBucketStorageImpl(String str, BucketStoragePolicyConfiguration bucketStoragePolicyConfiguration) throws ServiceException {
        HashMap map = new HashMap();
        map.put(getSpecialParamForStorageClass().getOriginalStringCode(), "");
        HashMap map2 = new HashMap();
        map2.put("Content-Type", "application/xml");
        String strTransStoragePolicy = bucketStoragePolicyConfiguration != null ? getIConvertor().transStoragePolicy(bucketStoragePolicyConfiguration) : "";
        map2.put("Content-Length", String.valueOf(strTransStoragePolicy.length()));
        Response responsePerformRestPut = performRestPut(str, null, map2, map, createRequestBody("application/xml", strTransStoragePolicy), true);
        HeaderResponse headerResponseBuild = build(cleanResponseHeaders(responsePerformRestPut));
        setStatusCode(headerResponseBuild, responsePerformRestPut.code());
        return headerResponseBuild;
    }

    protected HeaderResponse setBucketTaggingImpl(String str, BucketTagInfo bucketTagInfo) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.TAGGING.getOriginalStringCode(), "");
        HashMap map2 = new HashMap();
        String strTransBucketTagInfo = getIConvertor().transBucketTagInfo(bucketTagInfo);
        map2.put("Content-MD5", ServiceUtils.computeMD5(strTransBucketTagInfo));
        map2.put("Content-Type", "application/xml");
        Response responsePerformRestPut = performRestPut(str, null, map2, map, createRequestBody("application/xml", strTransBucketTagInfo), true);
        HeaderResponse headerResponseBuild = build(cleanResponseHeaders(responsePerformRestPut));
        setStatusCode(headerResponseBuild, responsePerformRestPut.code());
        return headerResponseBuild;
    }

    protected HeaderResponse setBucketVersioningImpl(String str, VersioningStatusEnum versioningStatusEnum) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.VERSIONING.getOriginalStringCode(), "");
        HashMap map2 = new HashMap();
        map2.put("Content-Type", "application/xml");
        return build(performRestPut(str, null, map2, map, createRequestBody("application/xml", getIConvertor().transVersioningConfiguration(str, versioningStatusEnum != null ? versioningStatusEnum.getCode() : null)), true));
    }

    protected HeaderResponse setBucketWebsiteConfigurationImpl(String str, WebsiteConfiguration websiteConfiguration) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.WEBSITE.getOriginalStringCode(), "");
        HashMap map2 = new HashMap();
        map2.put("Content-Type", "application/xml");
        Response responsePerformRestPut = performRestPut(str, null, map2, map, createRequestBody("application/xml", getIConvertor().transWebsiteConfiguration(websiteConfiguration)), true);
        HeaderResponse headerResponseBuild = build(cleanResponseHeaders(responsePerformRestPut));
        setStatusCode(headerResponseBuild, responsePerformRestPut.code());
        return headerResponseBuild;
    }

    protected HeaderResponse setExtensionPolicyImpl(String str, String str2) throws ServiceException {
        HashMap map = new HashMap();
        map.put(RequestParamEnum.EXTENSION_POLICY.getOriginalStringCode(), "");
        HashMap map2 = new HashMap();
        map2.put("Content-Type", "application/json");
        StringBuilder sb = new StringBuilder();
        sb.append(getProviderCredentials().getAuthType() != AuthTypeEnum.OBS ? "x-amz-" : "x-obs-");
        sb.append("oef-marker");
        map2.put(sb.toString(), "yes");
        Response responsePerformRestPut = performRestPut(str, null, map2, map, createRequestBody("application/json", str2), false, true);
        HeaderResponse headerResponseBuild = build(cleanResponseHeaders(responsePerformRestPut));
        setStatusCode(headerResponseBuild, responsePerformRestPut.code());
        return headerResponseBuild;
    }

    protected HeaderResponse setObjectAclImpl(String str, String str2, String str3, AccessControlList accessControlList, String str4) throws ServiceException {
        RequestBody requestBodyCreateRequestBody;
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.ACL.getOriginalStringCode(), "");
        if (str4 != null) {
            map.put("versionId", str4);
        }
        if (ServiceUtils.isValid(str3)) {
            accessControlList = getIConvertor().transCannedAcl(str3.trim());
        }
        HashMap map2 = new HashMap();
        map2.put("Content-Type", "application/xml");
        if (!prepareRESTHeaderAclObject(map2, accessControlList)) {
            String strTransAccessControlList = accessControlList != null ? getIConvertor().transAccessControlList(accessControlList, false) : "";
            map2.put("Content-Length", String.valueOf(strTransAccessControlList.length()));
            requestBodyCreateRequestBody = createRequestBody("application/xml", strTransAccessControlList);
        } else {
            requestBodyCreateRequestBody = null;
        }
        Response responsePerformRestPut = performRestPut(str, str2, map2, map, requestBodyCreateRequestBody, true);
        HeaderResponse headerResponseBuild = build(cleanResponseHeaders(responsePerformRestPut));
        setStatusCode(headerResponseBuild, responsePerformRestPut.code());
        return headerResponseBuild;
    }

    protected ObjectMetadata setObjectMetadataImpl(SetObjectMetadataRequest setObjectMetadataRequest) throws ServiceException {
        TransResult transResultTransSetObjectMetadataRequest = transSetObjectMetadataRequest(setObjectMetadataRequest);
        return getObsFSAttributeFromResponse(performRestPut(setObjectMetadataRequest.getBucketName(), setObjectMetadataRequest.getObjectKey(), transResultTransSetObjectMetadataRequest.headers, transResultTransSetObjectMetadataRequest.params, transResultTransSetObjectMetadataRequest.body, true));
    }

    TransResult transAppendObjectRequest(AppendObjectRequest appendObjectRequest) throws IOException, ServiceException {
        TransResult transResultTransPutObjectRequest = transPutObjectRequest(appendObjectRequest);
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.APPEND.getOriginalStringCode(), "");
        map.put("position", String.valueOf(appendObjectRequest.getPosition()));
        transResultTransPutObjectRequest.params = map;
        return transResultTransPutObjectRequest;
    }

    void transConditionCopyHeaders(CopyObjectRequest copyObjectRequest, Map<String, String> map, IHeaders iHeaders) {
        if (copyObjectRequest.getIfModifiedSince() != null) {
            putHeader(map, iHeaders.copySourceIfModifiedSinceHeader(), ServiceUtils.formatRfc822Date(copyObjectRequest.getIfModifiedSince()));
        }
        if (copyObjectRequest.getIfUnmodifiedSince() != null) {
            putHeader(map, iHeaders.copySourceIfUnmodifiedSinceHeader(), ServiceUtils.formatRfc822Date(copyObjectRequest.getIfUnmodifiedSince()));
        }
        if (ServiceUtils.isValid(copyObjectRequest.getIfMatchTag())) {
            putHeader(map, iHeaders.copySourceIfMatchHeader(), copyObjectRequest.getIfMatchTag().trim());
        }
        if (ServiceUtils.isValid(copyObjectRequest.getIfNoneMatchTag())) {
            putHeader(map, iHeaders.copySourceIfNoneMatchHeader(), copyObjectRequest.getIfNoneMatchTag().trim());
        }
    }

    void transConditionGetObjectHeaders(GetObjectRequest getObjectRequest, Map<String, String> map) {
        if (getObjectRequest.getIfModifiedSince() != null) {
            map.put("If-Modified-Since", ServiceUtils.formatRfc822Date(getObjectRequest.getIfModifiedSince()));
        }
        if (getObjectRequest.getIfUnmodifiedSince() != null) {
            map.put("If-Unmodified-Since", ServiceUtils.formatRfc822Date(getObjectRequest.getIfUnmodifiedSince()));
        }
        if (ServiceUtils.isValid(getObjectRequest.getIfMatchTag())) {
            map.put("If-Match", getObjectRequest.getIfMatchTag().trim());
        }
        if (ServiceUtils.isValid(getObjectRequest.getIfNoneMatchTag())) {
            map.put("If-None-Match", getObjectRequest.getIfNoneMatchTag().trim());
        }
    }

    TransResult transCopyObjectRequest(CopyObjectRequest copyObjectRequest) throws ServiceException {
        HashMap map = new HashMap();
        IConvertor iConvertor = getIConvertor();
        IHeaders iHeaders = getIHeaders();
        ObjectMetadata objectMetadata = copyObjectRequest.getNewObjectMetadata() == null ? new ObjectMetadata() : copyObjectRequest.getNewObjectMetadata();
        putHeader(map, iHeaders.metadataDirectiveHeader(), copyObjectRequest.isReplaceMetadata() ? "REPLACE" : "COPY");
        if (copyObjectRequest.isReplaceMetadata()) {
            objectMetadata.getMetadata().remove(iHeaders.requestIdHeader());
            objectMetadata.getMetadata().remove(iHeaders.requestId2Header());
            for (Map.Entry<String, Object> entry : objectMetadata.getMetadata().entrySet()) {
                String key = entry.getKey();
                if (ServiceUtils.isValid(key)) {
                    String strTrim = key.trim();
                    if (!Constants.ALLOWED_REQUEST_HTTP_HEADER_METADATA_NAMES.contains(strTrim.toLowerCase())) {
                        map.put(strTrim, entry.getValue() == null ? "" : entry.getValue().toString());
                    }
                }
            }
        }
        if (objectMetadata.getContentType() != null) {
            map.put("Content-Type", objectMetadata.getContentType().trim());
        }
        if (objectMetadata.getContentEncoding() != null) {
            map.put("Content-Encoding", objectMetadata.getContentEncoding().trim());
        }
        if (objectMetadata.getObjectStorageClass() != null) {
            putHeader(map, iHeaders.storageClassHeader(), iConvertor.transStorageClass(objectMetadata.getObjectStorageClass()));
        }
        if (objectMetadata.getWebSiteRedirectLocation() != null) {
            putHeader(map, iHeaders.websiteRedirectLocationHeader(), objectMetadata.getWebSiteRedirectLocation());
        }
        if (copyObjectRequest.getSuccessRedirectLocation() != null) {
            putHeader(map, iHeaders.successRedirectLocationHeader(), copyObjectRequest.getSuccessRedirectLocation());
        }
        transExtensionPermissions(copyObjectRequest, map);
        transSseHeaders(copyObjectRequest, map, iHeaders);
        transSseCSourceHeaders(copyObjectRequest.getSseCHeaderSource(), map, iHeaders);
        transConditionCopyHeaders(copyObjectRequest, map, iHeaders);
        String str = RestUtils.encodeUrlString(copyObjectRequest.getSourceBucketName()) + "/" + RestUtils.encodeUrlString(copyObjectRequest.getSourceObjectKey());
        if (ServiceUtils.isValid(copyObjectRequest.getVersionId())) {
            str = str + "?versionId=" + copyObjectRequest.getVersionId().trim();
        }
        putHeader(map, iHeaders.copySourceHeader(), str);
        return new TransResult(map);
    }

    TransResult transCopyPartRequest(CopyPartRequest copyPartRequest) throws ServiceException {
        HashMap map = new HashMap();
        map.put("partNumber", String.valueOf(copyPartRequest.getPartNumber()));
        map.put("uploadId", copyPartRequest.getUploadId());
        HashMap map2 = new HashMap();
        IHeaders iHeaders = getIHeaders();
        String str = RestUtils.encodeUrlString(copyPartRequest.getSourceBucketName()) + "/" + RestUtils.encodeUrlString(copyPartRequest.getSourceObjectKey());
        if (ServiceUtils.isValid(copyPartRequest.getVersionId())) {
            str = str + "?versionId=" + copyPartRequest.getVersionId().trim();
        }
        putHeader(map2, iHeaders.copySourceHeader(), str);
        if (copyPartRequest.getByteRangeStart() != null) {
            putHeader(map2, iHeaders.copySourceRangeHeader(), String.format("bytes=%s-%s", copyPartRequest.getByteRangeStart(), copyPartRequest.getByteRangeEnd() != null ? String.valueOf(copyPartRequest.getByteRangeEnd()) : ""));
        }
        transSseCHeaders(copyPartRequest.getSseCHeaderDestination(), map2, iHeaders);
        transSseCSourceHeaders(copyPartRequest.getSseCHeaderSource(), map2, iHeaders);
        return new TransResult(map2, map, null);
    }

    TransResult transCreateBucketRequest(CreateBucketRequest createBucketRequest) throws ServiceException {
        HashMap map = new HashMap();
        IConvertor iConvertor = getIConvertor();
        if (createBucketRequest.getBucketStorageClass() != null) {
            putHeader(map, getIHeaders().defaultStorageClassHeader(), iConvertor.transStorageClass(createBucketRequest.getBucketStorageClass()));
        }
        if (createBucketRequest.getEpid() != null) {
            putHeader(map, getIHeaders().epidHeader(), createBucketRequest.getEpid());
        }
        if (createBucketRequest instanceof NewBucketRequest) {
            putHeader(map, getIHeaders().fsFileInterfaceHeader(), "Enabled");
        }
        if (createBucketRequest.getAvailableZone() != null) {
            putHeader(map, getIHeaders().azRedundancyHeader(), createBucketRequest.getAvailableZone().getCode());
        }
        Set<ExtensionBucketPermissionEnum> allGrantPermissions = createBucketRequest.getAllGrantPermissions();
        if (!allGrantPermissions.isEmpty()) {
            for (ExtensionBucketPermissionEnum extensionBucketPermissionEnum : allGrantPermissions) {
                Set<String> domainIdsByGrantPermission = createBucketRequest.getDomainIdsByGrantPermission(extensionBucketPermissionEnum);
                ArrayList arrayList = new ArrayList(domainIdsByGrantPermission.size());
                Iterator<String> it = domainIdsByGrantPermission.iterator();
                while (it.hasNext()) {
                    arrayList.add("id=" + it.next());
                }
                putHeader(map, getHeaderByMethodName(extensionBucketPermissionEnum.getCode()), ServiceUtils.join(arrayList, ","));
            }
        }
        if (createBucketRequest.getExtensionHeaderMap() != null) {
            for (Map.Entry<String, String> entry : createBucketRequest.getExtensionHeaderMap().entrySet()) {
                putHeader(map, entry.getKey(), entry.getValue());
            }
        }
        map.put("Content-Type", "application/xml");
        TransResult transResult = new TransResult(map);
        if (ServiceUtils.isValid(createBucketRequest.getLocation())) {
            String strTransBucketLoction = iConvertor.transBucketLoction(createBucketRequest.getLocation());
            map.put("Content-Length", String.valueOf(strTransBucketLoction.length()));
            transResult.body = createRequestBody("application/xml", strTransBucketLoction);
        }
        return transResult;
    }

    void transExtensionPermissions(PutObjectBasicRequest putObjectBasicRequest, Map<String, String> map) {
        Set<ExtensionObjectPermissionEnum> allGrantPermissions = putObjectBasicRequest.getAllGrantPermissions();
        if (allGrantPermissions.isEmpty()) {
            return;
        }
        for (ExtensionObjectPermissionEnum extensionObjectPermissionEnum : allGrantPermissions) {
            Set<String> domainIdsByGrantPermission = putObjectBasicRequest.getDomainIdsByGrantPermission(extensionObjectPermissionEnum);
            ArrayList arrayList = new ArrayList(domainIdsByGrantPermission.size());
            Iterator<String> it = domainIdsByGrantPermission.iterator();
            while (it.hasNext()) {
                arrayList.add("id=" + it.next());
            }
            putHeader(map, getHeaderByMethodName(extensionObjectPermissionEnum.getCode()), ServiceUtils.join(arrayList, ","));
        }
    }

    void transGetObjectParams(GetObjectRequest getObjectRequest, Map<String, String> map) {
        if (getObjectRequest.getReplaceMetadata() != null) {
            if (ServiceUtils.isValid(getObjectRequest.getReplaceMetadata().getCacheControl())) {
                map.put("response-cache-control", getObjectRequest.getReplaceMetadata().getCacheControl());
            }
            if (ServiceUtils.isValid(getObjectRequest.getReplaceMetadata().getContentDisposition())) {
                map.put("response-content-disposition", getObjectRequest.getReplaceMetadata().getContentDisposition());
            }
            if (ServiceUtils.isValid(getObjectRequest.getReplaceMetadata().getContentEncoding())) {
                map.put("response-content-encoding", getObjectRequest.getReplaceMetadata().getContentEncoding());
            }
            if (ServiceUtils.isValid(getObjectRequest.getReplaceMetadata().getContentLanguage())) {
                map.put("response-content-language", getObjectRequest.getReplaceMetadata().getContentLanguage());
            }
            if (ServiceUtils.isValid(getObjectRequest.getReplaceMetadata().getContentType())) {
                map.put("response-content-type", getObjectRequest.getReplaceMetadata().getContentType());
            }
            if (ServiceUtils.isValid(getObjectRequest.getReplaceMetadata().getExpires())) {
                map.put("response-expires", getObjectRequest.getReplaceMetadata().getExpires());
            }
        }
        if (ServiceUtils.isValid(getObjectRequest.getImageProcess())) {
            map.put("x-image-process", getObjectRequest.getImageProcess());
        }
        if (getObjectRequest.getVersionId() != null) {
            map.put("versionId", getObjectRequest.getVersionId());
        }
        if (getObjectRequest.getCacheOption() != null) {
            map.put("x-cache-control", getObjectRequest.getCacheOption().getCode() + ", ttl=" + getObjectRequest.getTtl());
        }
    }

    TransResult transGetObjectRequest(GetObjectRequest getObjectRequest) throws ServiceException {
        HashMap map = new HashMap();
        transSseCHeaders(getObjectRequest.getSseCHeader(), map, getIHeaders());
        transConditionGetObjectHeaders(getObjectRequest, map);
        if (getObjectRequest.getRangeStart() != null) {
            map.put("Range", String.format("bytes=%s-%s", getObjectRequest.getRangeStart(), getObjectRequest.getRangeEnd() != null ? String.valueOf(getObjectRequest.getRangeEnd()) : ""));
        }
        HashMap map2 = new HashMap();
        transGetObjectParams(getObjectRequest, map2);
        return new TransResult(map, map2, null);
    }

    TransResult transInitiateMultipartUploadRequest(InitiateMultipartUploadRequest initiateMultipartUploadRequest) throws ServiceException {
        HashMap map = new HashMap();
        IHeaders iHeaders = getIHeaders();
        IConvertor iConvertor = getIConvertor();
        ObjectMetadata objectMetadata = initiateMultipartUploadRequest.getMetadata() == null ? new ObjectMetadata() : initiateMultipartUploadRequest.getMetadata();
        Iterator<Map.Entry<String, Object>> it = objectMetadata.getMetadata().entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map.Entry<String, Object> next = it.next();
            String key = next.getKey();
            if (ServiceUtils.isValid(key)) {
                String strTrim = key.trim();
                if (!Constants.ALLOWED_REQUEST_HTTP_HEADER_METADATA_NAMES.contains(strTrim.toLowerCase())) {
                    map.put(strTrim, next.getValue() != null ? next.getValue().toString() : "");
                }
            }
        }
        if (objectMetadata.getObjectStorageClass() != null) {
            putHeader(map, iHeaders.storageClassHeader(), iConvertor.transStorageClass(objectMetadata.getObjectStorageClass()));
        }
        if (initiateMultipartUploadRequest.getExpires() > 0) {
            putHeader(map, iHeaders.expiresHeader(), String.valueOf(initiateMultipartUploadRequest.getExpires()));
        }
        if (ServiceUtils.isValid(objectMetadata.getWebSiteRedirectLocation())) {
            putHeader(map, iHeaders.websiteRedirectLocationHeader(), objectMetadata.getWebSiteRedirectLocation());
        }
        if (ServiceUtils.isValid(initiateMultipartUploadRequest.getSuccessRedirectLocation())) {
            putHeader(map, iHeaders.successRedirectLocationHeader(), initiateMultipartUploadRequest.getSuccessRedirectLocation());
        }
        if (ServiceUtils.isValid(objectMetadata.getContentEncoding())) {
            map.put("Content-Encoding", objectMetadata.getContentEncoding().trim());
        }
        transExtensionPermissions(initiateMultipartUploadRequest, map);
        transSseHeaders(initiateMultipartUploadRequest, map, iHeaders);
        Object value = objectMetadata.getContentType() == null ? objectMetadata.getValue("Content-Type") : objectMetadata.getContentType();
        if (value == null) {
            value = Mimetypes.getInstance().getMimetype(initiateMultipartUploadRequest.getObjectKey());
        }
        map.put("Content-Type", value.toString().trim());
        HashMap map2 = new HashMap();
        map2.put(SpecialParamEnum.UPLOADS.getOriginalStringCode(), "");
        return new TransResult(map, map2, null);
    }

    TransResult transListObjectsRequest(ListObjectsRequest listObjectsRequest) {
        HashMap map = new HashMap();
        if (listObjectsRequest.getPrefix() != null) {
            map.put("prefix", listObjectsRequest.getPrefix());
        }
        if (listObjectsRequest.getDelimiter() != null) {
            map.put("delimiter", listObjectsRequest.getDelimiter());
        }
        if (listObjectsRequest.getMaxKeys() > 0) {
            map.put("max-keys", String.valueOf(listObjectsRequest.getMaxKeys()));
        }
        if (listObjectsRequest.getMarker() != null) {
            map.put("marker", listObjectsRequest.getMarker());
        }
        HashMap map2 = new HashMap();
        if (listObjectsRequest.getListTimeout() > 0) {
            putHeader(map2, getIHeaders().listTimeoutHeader(), String.valueOf(listObjectsRequest.getListTimeout()));
        }
        return new TransResult(map2, map, null);
    }

    TransResult transListVersionsRequest(ListVersionsRequest listVersionsRequest) {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.VERSIONS.getOriginalStringCode(), "");
        if (listVersionsRequest.getPrefix() != null) {
            map.put("prefix", listVersionsRequest.getPrefix());
        }
        if (listVersionsRequest.getDelimiter() != null) {
            map.put("delimiter", listVersionsRequest.getDelimiter());
        }
        if (listVersionsRequest.getMaxKeys() > 0) {
            map.put("max-keys", String.valueOf(listVersionsRequest.getMaxKeys()));
        }
        if (listVersionsRequest.getKeyMarker() != null) {
            map.put("key-marker", listVersionsRequest.getKeyMarker());
        }
        if (listVersionsRequest.getVersionIdMarker() != null) {
            map.put("version-id-marker", listVersionsRequest.getVersionIdMarker());
        }
        HashMap map2 = new HashMap();
        if (listVersionsRequest.getListTimeout() > 0) {
            putHeader(map2, getIHeaders().listTimeoutHeader(), String.valueOf(listVersionsRequest.getListTimeout()));
        }
        return new TransResult(map2, map, null);
    }

    TransResult transPutObjectRequest(PutObjectRequest putObjectRequest) throws IOException, ServiceException {
        long j;
        InputStream inputStream;
        InputStream progressInputStream;
        HashMap map = new HashMap();
        IConvertor iConvertor = getIConvertor();
        IHeaders iHeaders = getIHeaders();
        ObjectMetadata objectMetadata = putObjectRequest.getMetadata() == null ? new ObjectMetadata() : putObjectRequest.getMetadata();
        for (Map.Entry<String, Object> entry : objectMetadata.getMetadata().entrySet()) {
            String key = entry.getKey();
            if (ServiceUtils.isValid(key)) {
                String strTrim = key.trim();
                if (!Constants.ALLOWED_REQUEST_HTTP_HEADER_METADATA_NAMES.contains(strTrim.toLowerCase())) {
                    map.put(strTrim, entry.getValue() == null ? "" : entry.getValue().toString());
                }
            }
        }
        if (ServiceUtils.isValid(objectMetadata.getContentMd5())) {
            map.put("Content-MD5", objectMetadata.getContentMd5().trim());
        }
        if (ServiceUtils.isValid(objectMetadata.getContentEncoding())) {
            map.put("Content-Encoding", objectMetadata.getContentEncoding().trim());
        }
        if (objectMetadata.getObjectStorageClass() != null) {
            putHeader(map, iHeaders.storageClassHeader(), iConvertor.transStorageClass(objectMetadata.getObjectStorageClass()));
        }
        if (putObjectRequest.getExpires() >= 0) {
            putHeader(map, iHeaders.expiresHeader(), String.valueOf(putObjectRequest.getExpires()));
        }
        if (objectMetadata.getWebSiteRedirectLocation() != null) {
            putHeader(map, iHeaders.websiteRedirectLocationHeader(), objectMetadata.getWebSiteRedirectLocation());
        }
        if (putObjectRequest.getSuccessRedirectLocation() != null) {
            putHeader(map, iHeaders.successRedirectLocationHeader(), putObjectRequest.getSuccessRedirectLocation());
        }
        transExtensionPermissions(putObjectRequest, map);
        transSseHeaders(putObjectRequest, map, iHeaders);
        Object value = objectMetadata.getContentType() == null ? objectMetadata.getValue("Content-Type") : objectMetadata.getContentType();
        if (value == null) {
            value = Mimetypes.getInstance().getMimetype(putObjectRequest.getObjectKey());
        }
        Object contentLength = objectMetadata.getContentLength();
        if (contentLength == null) {
            contentLength = objectMetadata.getValue("Content-Length");
        }
        long offset = contentLength == null ? -1L : Long.parseLong(contentLength.toString());
        if (putObjectRequest.getFile() != null) {
            if ("application/octet-stream".equals(value)) {
                value = Mimetypes.getInstance().getMimetype(putObjectRequest.getFile());
            }
            try {
                progressInputStream = new FileInputStream(putObjectRequest.getFile());
                long length = putObjectRequest.getFile().length();
                if (putObjectRequest.getOffset() > 0 && putObjectRequest.getOffset() < length) {
                    if (offset <= 0 || offset > length - putObjectRequest.getOffset()) {
                        offset = length - putObjectRequest.getOffset();
                    }
                    try {
                        progressInputStream.skip(putObjectRequest.getOffset());
                    } catch (IOException e) {
                        ServiceUtils.closeStream(progressInputStream);
                        throw new ServiceException(e);
                    }
                } else if (offset < 0 || offset > length) {
                    offset = length;
                }
                if (putObjectRequest.getProgressListener() != null) {
                    progressInputStream = new ProgressInputStream(progressInputStream, new SimpleProgressManager(offset, 0L, putObjectRequest.getProgressListener(), putObjectRequest.getProgressInterval() > 0 ? putObjectRequest.getProgressInterval() : 102400L));
                }
                j = offset;
            } catch (FileNotFoundException unused) {
                throw new IllegalArgumentException("File doesnot exist");
            }
        } else {
            InputStream input = putObjectRequest.getInput();
            if (input == null || putObjectRequest.getProgressListener() == null) {
                j = offset;
                inputStream = input;
                String strTrim2 = value.toString().trim();
                map.put("Content-Type", strTrim2);
                if (j > -1) {
                    putHeader(map, "Content-Length", String.valueOf(j));
                }
                return new TransResult(map, inputStream != null ? null : new RepeatableRequestEntity(inputStream, strTrim2, j, this.obsProperties));
            }
            j = offset;
            progressInputStream = new ProgressInputStream(input, new SimpleProgressManager(j, 0L, putObjectRequest.getProgressListener(), putObjectRequest.getProgressInterval() > 0 ? putObjectRequest.getProgressInterval() : 102400L));
        }
        inputStream = progressInputStream;
        String strTrim22 = value.toString().trim();
        map.put("Content-Type", strTrim22);
        if (j > -1) {
        }
        return new TransResult(map, inputStream != null ? null : new RepeatableRequestEntity(inputStream, strTrim22, j, this.obsProperties));
    }

    TransResult transSetObjectMetadataRequest(SetObjectMetadataRequest setObjectMetadataRequest) throws ServiceException {
        HashMap map = new HashMap();
        IHeaders iHeaders = getIHeaders();
        IConvertor iConvertor = getIConvertor();
        Iterator<Map.Entry<String, String>> it = setObjectMetadataRequest.getMetadata().entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map.Entry<String, String> next = it.next();
            String key = next.getKey();
            if (ServiceUtils.isValid(key)) {
                map.put(key.trim(), next.getValue() != null ? next.getValue().toString() : "");
            }
        }
        if (setObjectMetadataRequest.getObjectStorageClass() != null) {
            putHeader(map, iHeaders.storageClassHeader(), iConvertor.transStorageClass(setObjectMetadataRequest.getObjectStorageClass()));
        }
        if (setObjectMetadataRequest.getWebSiteRedirectLocation() != null) {
            putHeader(map, iHeaders.websiteRedirectLocationHeader(), setObjectMetadataRequest.getWebSiteRedirectLocation());
        }
        if (setObjectMetadataRequest.getContentDisposition() != null) {
            putHeader(map, "Content-Disposition", setObjectMetadataRequest.getContentDisposition());
        }
        if (setObjectMetadataRequest.getContentEncoding() != null) {
            putHeader(map, "Content-Encoding", setObjectMetadataRequest.getContentEncoding());
        }
        if (setObjectMetadataRequest.getContentLanguage() != null) {
            putHeader(map, "Content-Language", setObjectMetadataRequest.getContentLanguage());
        }
        if (setObjectMetadataRequest.getContentType() != null) {
            putHeader(map, "Content-Type", setObjectMetadataRequest.getContentType());
        }
        if (setObjectMetadataRequest.getCacheControl() != null) {
            putHeader(map, "Cache-Control", setObjectMetadataRequest.getCacheControl());
        }
        if (setObjectMetadataRequest.getExpires() != null) {
            putHeader(map, "Expires", setObjectMetadataRequest.getExpires());
        }
        putHeader(map, iHeaders.metadataDirectiveHeader(), setObjectMetadataRequest.isRemoveUnset() ? "REPLACE" : "REPLACE_NEW");
        HashMap map2 = new HashMap();
        map2.put(SpecialParamEnum.METADATA.getOriginalStringCode(), "");
        if (setObjectMetadataRequest.getVersionId() != null) {
            map2.put("versionId", setObjectMetadataRequest.getVersionId());
        }
        return new TransResult(map, map2, null);
    }

    void transSseCHeaders(SseCHeader sseCHeader, Map<String, String> map, IHeaders iHeaders) throws ServiceException {
        if (sseCHeader == null) {
            return;
        }
        putHeader(map, iHeaders.sseCHeader(), ServiceUtils.toValid(sseCHeader.getSSEAlgorithm().getCode()));
        if (sseCHeader.getSseCKeyBase64() != null) {
            try {
                putHeader(map, iHeaders.sseCKeyHeader(), sseCHeader.getSseCKeyBase64());
                putHeader(map, iHeaders.sseCKeyMd5Header(), ServiceUtils.toBase64(ServiceUtils.computeMD5Hash(ServiceUtils.fromBase64(sseCHeader.getSseCKeyBase64()))));
                return;
            } catch (IOException e) {
                throw new IllegalStateException("fail to read sseCkey", e);
            } catch (NoSuchAlgorithmException e2) {
                throw new IllegalStateException("fail to read sseCkey", e2);
            }
        }
        if (sseCHeader.getSseCKey() != null) {
            try {
                byte[] sseCKey = sseCHeader.getSseCKey();
                putHeader(map, iHeaders.sseCKeyHeader(), ServiceUtils.toBase64(sseCKey));
                putHeader(map, iHeaders.sseCKeyMd5Header(), ServiceUtils.toBase64(ServiceUtils.computeMD5Hash(sseCKey)));
            } catch (IOException e3) {
                throw new IllegalStateException("fail to read sseCkey", e3);
            } catch (NoSuchAlgorithmException e4) {
                throw new IllegalStateException("fail to read sseCkey", e4);
            }
        }
    }

    void transSseCSourceHeaders(SseCHeader sseCHeader, Map<String, String> map, IHeaders iHeaders) throws ServiceException {
        if (sseCHeader != null) {
            putHeader(map, iHeaders.copySourceSseCHeader(), ServiceUtils.toValid(sseCHeader.getSSEAlgorithm().getCode()));
            if (sseCHeader.getSseCKeyBase64() != null) {
                try {
                    putHeader(map, iHeaders.copySourceSseCKeyHeader(), sseCHeader.getSseCKeyBase64());
                    putHeader(map, iHeaders.copySourceSseCKeyMd5Header(), ServiceUtils.toBase64(ServiceUtils.computeMD5Hash(ServiceUtils.fromBase64(sseCHeader.getSseCKeyBase64()))));
                    return;
                } catch (IOException e) {
                    throw new IllegalStateException("fail to read sseCkey", e);
                } catch (NoSuchAlgorithmException e2) {
                    throw new IllegalStateException("fail to read sseCkey", e2);
                }
            }
            if (sseCHeader.getSseCKey() != null) {
                try {
                    byte[] sseCKey = sseCHeader.getSseCKey();
                    putHeader(map, iHeaders.copySourceSseCKeyHeader(), ServiceUtils.toBase64(sseCKey));
                    putHeader(map, iHeaders.copySourceSseCKeyMd5Header(), ServiceUtils.toBase64(ServiceUtils.computeMD5Hash(sseCKey)));
                } catch (IOException e3) {
                    throw new IllegalStateException("fail to read sseCkey", e3);
                } catch (NoSuchAlgorithmException e4) {
                    throw new IllegalStateException("fail to read sseCkey", e4);
                }
            }
        }
    }

    void transSseHeaders(PutObjectBasicRequest putObjectBasicRequest, Map<String, String> map, IHeaders iHeaders) throws ServiceException {
        if (putObjectBasicRequest.getSseCHeader() != null) {
            transSseCHeaders(putObjectBasicRequest.getSseCHeader(), map, iHeaders);
        } else if (putObjectBasicRequest.getSseKmsHeader() != null) {
            transSseKmsHeaders(putObjectBasicRequest.getSseKmsHeader(), map, iHeaders);
        }
    }

    void transSseKmsHeaders(SseKmsHeader sseKmsHeader, Map<String, String> map, IHeaders iHeaders) {
        String code;
        if (sseKmsHeader == null) {
            return;
        }
        if (getProviderCredentials().getAuthType() != AuthTypeEnum.OBS) {
            code = "aws:" + sseKmsHeader.getSSEAlgorithm().getCode();
        } else {
            code = sseKmsHeader.getSSEAlgorithm().getCode();
        }
        putHeader(map, iHeaders.sseKmsHeader(), ServiceUtils.toValid(code));
        if (ServiceUtils.isValid(sseKmsHeader.getKmsKeyId())) {
            putHeader(map, iHeaders.sseKmsKeyHeader(), sseKmsHeader.getKmsKeyId());
        }
        if (ServiceUtils.isValid(sseKmsHeader.getProjectId())) {
            putHeader(map, iHeaders.sseKmsProjectIdHeader(), sseKmsHeader.getProjectId());
        }
    }

    TransResult transUploadPartRequest(UploadPartRequest uploadPartRequest) throws IOException, ServiceException {
        long j;
        InputStream progressInputStream;
        InputStream inputStream;
        HashMap map = new HashMap();
        map.put("partNumber", String.valueOf(uploadPartRequest.getPartNumber()));
        map.put("uploadId", uploadPartRequest.getUploadId());
        HashMap map2 = new HashMap();
        IHeaders iHeaders = getIHeaders();
        if (ServiceUtils.isValid(uploadPartRequest.getContentMd5())) {
            map2.put("Content-MD5", uploadPartRequest.getContentMd5().trim());
        }
        transSseCHeaders(uploadPartRequest.getSseCHeader(), map2, iHeaders);
        if (uploadPartRequest.getFile() != null) {
            long length = uploadPartRequest.getFile().length();
            long offset = (uploadPartRequest.getOffset() < 0 || uploadPartRequest.getOffset() >= length) ? 0L : uploadPartRequest.getOffset();
            long jLongValue = (uploadPartRequest.getPartSize() == null || uploadPartRequest.getPartSize().longValue() <= 0 || uploadPartRequest.getPartSize().longValue() > length - offset) ? length - offset : uploadPartRequest.getPartSize().longValue();
            try {
                if (uploadPartRequest.isAttachMd5() && !ServiceUtils.isValid(uploadPartRequest.getContentMd5())) {
                    map2.put("Content-MD5", ServiceUtils.toBase64(ServiceUtils.computeMD5Hash(new FileInputStream(uploadPartRequest.getFile()), jLongValue, offset)));
                }
                InputStream fileInputStream = new FileInputStream(uploadPartRequest.getFile());
                try {
                    fileInputStream.skip(offset);
                    if (uploadPartRequest.getProgressListener() != null) {
                        fileInputStream = new ProgressInputStream(fileInputStream, new SimpleProgressManager(jLongValue, 0L, uploadPartRequest.getProgressListener(), uploadPartRequest.getProgressInterval() > 0 ? uploadPartRequest.getProgressInterval() : 102400L));
                    }
                    j = jLongValue;
                    progressInputStream = fileInputStream;
                } catch (Exception e) {
                    e = e;
                    inputStream = fileInputStream;
                    ServiceUtils.closeStream(inputStream);
                    throw new ServiceException(e);
                }
            } catch (Exception e2) {
                e = e2;
                inputStream = null;
            }
        } else if (uploadPartRequest.getInput() != null) {
            long jLongValue2 = (uploadPartRequest.getPartSize() == null || uploadPartRequest.getPartSize().longValue() <= 0) ? -1L : uploadPartRequest.getPartSize().longValue();
            InputStream input = uploadPartRequest.getInput();
            if (input == null || uploadPartRequest.getProgressListener() == null) {
                j = jLongValue2;
                progressInputStream = input;
            } else {
                j = jLongValue2;
                progressInputStream = new ProgressInputStream(input, new SimpleProgressManager(jLongValue2, 0L, uploadPartRequest.getProgressListener(), uploadPartRequest.getProgressInterval() > 0 ? uploadPartRequest.getProgressInterval() : 102400L));
            }
        } else {
            j = -1;
            progressInputStream = null;
        }
        String mimetype = Mimetypes.getInstance().getMimetype(uploadPartRequest.getObjectKey());
        map2.put("Content-Type", mimetype);
        if (j > -1) {
            putHeader(map2, "Content-Length", String.valueOf(j));
        }
        return new TransResult(map2, map, progressInputStream == null ? null : new RepeatableRequestEntity(progressInputStream, mimetype, j, this.obsProperties));
    }

    TransResult transWriteFileRequest(WriteFileRequest writeFileRequest) throws IOException, ServiceException {
        TransResult transResultTransPutObjectRequest = transPutObjectRequest(writeFileRequest);
        if (writeFileRequest.getPosition() > 0) {
            HashMap map = new HashMap();
            map.put(SpecialParamEnum.MODIFY.getOriginalStringCode(), "");
            map.put("position", String.valueOf(writeFileRequest.getPosition()));
            transResultTransPutObjectRequest.params = map;
        }
        return transResultTransPutObjectRequest;
    }

    protected TruncateFileResult truncateFileImpl(TruncateFileRequest truncateFileRequest) throws ServiceException {
        HashMap map = new HashMap();
        map.put(SpecialParamEnum.TRUNCATE.getOriginalStringCode(), "");
        map.put("length", String.valueOf(truncateFileRequest.getNewLength()));
        Response responsePerformRestPut = performRestPut(truncateFileRequest.getBucketName(), truncateFileRequest.getObjectKey(), null, map, null, true);
        TruncateFileResult truncateFileResult = new TruncateFileResult();
        setResponseHeaders(truncateFileResult, cleanResponseHeaders(responsePerformRestPut));
        setStatusCode(truncateFileResult, responsePerformRestPut.code());
        return truncateFileResult;
    }

    protected UploadPartResult uploadPartImpl(UploadPartRequest uploadPartRequest) throws Throwable {
        TransResult transResultTransUploadPartRequest;
        try {
            transResultTransUploadPartRequest = transUploadPartRequest(uploadPartRequest);
        } catch (Throwable th) {
            th = th;
            transResultTransUploadPartRequest = null;
        }
        try {
            Response responsePerformRestPut = performRestPut(uploadPartRequest.getBucketName(), uploadPartRequest.getObjectKey(), transResultTransUploadPartRequest.getHeaders(), transResultTransUploadPartRequest.getParams(), transResultTransUploadPartRequest.body, true);
            if (transResultTransUploadPartRequest != null && transResultTransUploadPartRequest.body != null && uploadPartRequest.isAutoClose()) {
                ServiceUtils.closeStream((RepeatableRequestEntity) transResultTransUploadPartRequest.body);
            }
            UploadPartResult uploadPartResult = new UploadPartResult();
            uploadPartResult.setEtag(responsePerformRestPut.header("ETag"));
            uploadPartResult.setPartNumber(uploadPartRequest.getPartNumber());
            setResponseHeaders(uploadPartResult, cleanResponseHeaders(responsePerformRestPut));
            setStatusCode(uploadPartResult, responsePerformRestPut.code());
            return uploadPartResult;
        } catch (Throwable th2) {
            th = th2;
            if (transResultTransUploadPartRequest != null && transResultTransUploadPartRequest.body != null && uploadPartRequest.isAutoClose()) {
                ServiceUtils.closeStream((RepeatableRequestEntity) transResultTransUploadPartRequest.body);
            }
            throw th;
        }
    }

    protected void verifyResponseContentType(Response response) throws ServiceException {
        if (this.obsProperties.getBoolProperty("obs.verify-content-type", true)) {
            String strHeader = response.header("Content-Type");
            if ("application/xml".equalsIgnoreCase(strHeader) || "text/xml".equalsIgnoreCase(strHeader)) {
                return;
            }
            throw new ServiceException("Expected XML document response from OBS but received content type " + strHeader);
        }
    }

    protected void verifyResponseContentTypeForJson(Response response) throws ServiceException {
        if (this.obsProperties.getBoolProperty("obs.verify-content-type", true)) {
            String strHeader = response.header("Content-Type");
            if (strHeader == null) {
                throw new ServiceException("Expected JSON document response  but received content type is null");
            }
            if (-1 != strHeader.toString().indexOf("application/json")) {
                return;
            }
            throw new ServiceException("Expected JSON document response  but received content type is " + strHeader);
        }
    }

    protected ObsFSFile writeFileImpl(WriteFileRequest writeFileRequest) throws Throwable {
        TransResult transResultTransWriteFileRequest;
        AccessControlList acl = writeFileRequest.getAcl();
        TransResult transResult = null;
        try {
            transResultTransWriteFileRequest = transWriteFileRequest(writeFileRequest);
        } catch (Throwable th) {
            th = th;
        }
        try {
            boolean z = !prepareRESTHeaderAcl(transResultTransWriteFileRequest.getHeaders(), acl);
            Response responsePerformRestPut = performRestPut(writeFileRequest.getBucketName(), writeFileRequest.getObjectKey(), transResultTransWriteFileRequest.getHeaders(), transResultTransWriteFileRequest.getParams(), transResultTransWriteFileRequest.body, true);
            if (transResultTransWriteFileRequest != null && transResultTransWriteFileRequest.body != null && writeFileRequest.isAutoClose() && (transResultTransWriteFileRequest.body instanceof Closeable)) {
                ServiceUtils.closeStream((Closeable) transResultTransWriteFileRequest.body);
            }
            ObsFSFile obsFSFile = new ObsFSFile(writeFileRequest.getBucketName(), writeFileRequest.getObjectKey(), responsePerformRestPut.header("ETag"), responsePerformRestPut.header(getIHeaders().versionIdHeader()), StorageClassEnum.getValueFromCode(responsePerformRestPut.header(getIHeaders().storageClassHeader())), getObjectUrl(writeFileRequest.getBucketName(), writeFileRequest.getObjectKey()));
            setResponseHeaders(obsFSFile, cleanResponseHeaders(responsePerformRestPut));
            setStatusCode(obsFSFile, responsePerformRestPut.code());
            if (z && acl != null) {
                try {
                    putAclImpl(writeFileRequest.getBucketName(), writeFileRequest.getObjectKey(), acl, null);
                } catch (Exception e) {
                    if (log.isWarnEnabled()) {
                        log.warn("Try to set object acl error", e);
                    }
                }
            }
            return obsFSFile;
        } catch (Throwable th2) {
            th = th2;
            transResult = transResultTransWriteFileRequest;
            if (transResult != null && transResult.body != null && writeFileRequest.isAutoClose() && (transResult.body instanceof Closeable)) {
                ServiceUtils.closeStream((Closeable) transResult.body);
            }
            throw th;
        }
    }
}
