package com.oef.services.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.obs.services.internal.ServiceException;
import com.obs.services.internal.utils.ServiceUtils;
import java.io.UnsupportedEncodingException;

public class CreateAsyncFetchJobsRequest {

    @JsonProperty("bucket")
    private String bucket;

    @JsonProperty("callbackbody")
    private String callBackBody;

    @JsonProperty("callbackbodytype")
    private String callBackBodyType;

    @JsonProperty("callbackhost")
    private String callBackHost;

    @JsonProperty("callbackurl")
    private String callBackUrl;

    @JsonProperty("file_type")
    private String fileType;

    @JsonProperty("host")
    private String host;

    @JsonProperty("ignore_same_key")
    private boolean ignoreSameKey;

    @JsonProperty("key")
    private String key;

    @JsonProperty("md5")
    private String md5;

    @JsonProperty("url")
    private String url;

    public CreateAsyncFetchJobsRequest() {
    }

    public CreateAsyncFetchJobsRequest(String str, String str2) {
        setUrl(str);
        setBucketName(str2);
    }

    public String getBucketName() {
        return this.bucket;
    }

    public String getCallBackBody() {
        return this.callBackBody;
    }

    public String getCallBackHost() {
        return this.callBackHost;
    }

    public String getCallBackUrl() {
        return this.callBackUrl;
    }

    public String getCallbackBodyType() {
        return this.callBackBodyType;
    }

    public String getFileType() {
        return this.fileType;
    }

    public String getHost() {
        return this.host;
    }

    public String getMd5() {
        return this.md5;
    }

    public String getObjectKey() {
        return this.key;
    }

    public String getUrl() {
        return this.url;
    }

    public boolean isignoreSameKey() {
        return this.ignoreSameKey;
    }

    public void setBucketName(String str) {
        this.bucket = str;
    }

    public void setCallBackBody(String str) throws ServiceException {
        try {
            this.callBackBody = ServiceUtils.toBase64(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new ServiceException("Unable to get bytes from canonical string", e);
        }
    }

    public void setCallBackHost(String str) {
        this.callBackHost = str;
    }

    public void setCallBackUrl(String str) {
        this.callBackUrl = str;
    }

    public void setCallbackBodyType(String str) {
        this.callBackBodyType = str;
    }

    public void setFileType(String str) {
        this.fileType = str;
    }

    public void setHost(String str) {
        this.host = str;
    }

    public void setMd5(String str) {
        this.md5 = str;
    }

    public void setObjectKey(String str) {
        this.key = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void setignoreSameKey(boolean z) {
        this.ignoreSameKey = z;
    }

    public String toString() {
        return "CreateAsyncFetchJobsRequest [url=" + this.url + ", bucket=" + this.bucket + ", host=" + this.host + ", key=" + this.key + ", md5=" + this.md5 + ", callBackUrl=" + this.callBackUrl + ", callBackBody=" + this.callBackBody + ", callBackBodyType=" + this.callBackBodyType + ", callBackHost=" + this.callBackHost + ", fileType=" + this.fileType + ", ignoreSameKey=" + this.ignoreSameKey + "]";
    }
}
