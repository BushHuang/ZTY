package com.oef.services.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.obs.services.model.HeaderResponse;

public class QueryExtensionPolicyResult extends HeaderResponse {

    @JsonProperty("compress")
    private CompressBean compress;

    @JsonProperty("fetch")
    private FetchBean fetch;

    @JsonProperty("transcode")
    private TranscodeBean transcode;

    public QueryExtensionPolicyResult() {
        this.fetch = new FetchBean();
        this.transcode = new TranscodeBean();
        this.compress = new CompressBean();
    }

    public QueryExtensionPolicyResult(FetchBean fetchBean, TranscodeBean transcodeBean, CompressBean compressBean) {
        this.fetch = fetchBean;
        this.transcode = transcodeBean;
        this.compress = compressBean;
    }

    public CompressBean getCompress() {
        return this.compress;
    }

    public FetchBean getFetch() {
        return this.fetch;
    }

    public TranscodeBean getTranscode() {
        return this.transcode;
    }

    public void setCompress(CompressBean compressBean) {
        this.compress = compressBean;
    }

    public void setFetch(FetchBean fetchBean) {
        this.fetch = fetchBean;
    }

    public void setTranscode(TranscodeBean transcodeBean) {
        this.transcode = transcodeBean;
    }

    @Override
    public String toString() {
        return "ExtensionPolicyResult [fetch status=" + this.fetch.getStatus() + ", fetch agency=" + this.fetch.getAgency() + ", transcode status=" + this.transcode.getStatus() + ", transcode agency=" + this.transcode.getAgency() + ", compress status=" + this.compress.getStatus() + ", compress agency=" + this.compress.getAgency() + "]";
    }
}
