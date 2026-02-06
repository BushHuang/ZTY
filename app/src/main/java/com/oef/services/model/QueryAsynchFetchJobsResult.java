package com.oef.services.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.obs.services.model.HeaderResponse;

public class QueryAsynchFetchJobsResult extends HeaderResponse {

    @JsonProperty("code")
    private String code;

    @JsonProperty("err")
    private String err;

    @JsonProperty("job")
    private CreateAsyncFetchJobsRequest job;

    @JsonProperty("request_Id")
    private String requestId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("wait")
    private int wait;

    public QueryAsynchFetchJobsResult() {
        this.job = new CreateAsyncFetchJobsRequest();
    }

    public QueryAsynchFetchJobsResult(String str, String str2, String str3, String str4, int i, CreateAsyncFetchJobsRequest createAsyncFetchJobsRequest) {
        setRequestId(str);
        setErr(str2);
        setCode(str3);
        setStatus(str4);
        setWait(i);
        setJob(createAsyncFetchJobsRequest);
    }

    public String getCode() {
        return this.code;
    }

    public String getErr() {
        return this.err;
    }

    public CreateAsyncFetchJobsRequest getJob() {
        return this.job;
    }

    @Override
    public String getRequestId() {
        return this.requestId;
    }

    public String getStatus() {
        return this.status;
    }

    public int getWait() {
        return this.wait;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public void setErr(String str) {
        this.err = str;
    }

    public void setJob(CreateAsyncFetchJobsRequest createAsyncFetchJobsRequest) {
        this.job = createAsyncFetchJobsRequest;
    }

    public void setRequestId(String str) {
        this.requestId = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setWait(int i) {
        this.wait = i;
    }

    @Override
    public String toString() {
        return "QueryAsynchFetchJobsResult [requestId=" + this.requestId + ", err=" + this.err + ", code=" + this.code + ", status=" + this.status + ", wait=" + this.wait + ", job url=" + this.job.getUrl() + ", job bucket=" + this.job.getBucketName() + ", job key=" + this.job.getObjectKey() + ", job callbackurl=" + this.job.getCallBackUrl() + ", job callbackbody=" + this.job.getCallBackBody() + "]";
    }
}
