package com.oef.services.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PutExtensionPolicyRequest {

    @JsonProperty("compress")
    private CompressBean compress;

    @JsonProperty("fetch")
    private FetchBean fetch;

    @JsonProperty("transcode")
    private TranscodeBean transcode;

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

    public String toString() {
        FetchBean fetchBean = this.fetch;
        String status = fetchBean == null ? null : fetchBean.getStatus();
        FetchBean fetchBean2 = this.fetch;
        String agency = fetchBean2 == null ? null : fetchBean2.getAgency();
        TranscodeBean transcodeBean = this.transcode;
        String status2 = transcodeBean == null ? null : transcodeBean.getStatus();
        TranscodeBean transcodeBean2 = this.transcode;
        String agency2 = transcodeBean2 == null ? null : transcodeBean2.getAgency();
        CompressBean compressBean = this.compress;
        String status3 = compressBean == null ? null : compressBean.getStatus();
        CompressBean compressBean2 = this.compress;
        return "ExtensionPolicyRequest [fetch status=" + status + ", fetch agency=" + agency + ", transcode status=" + status2 + ", transcode agency=" + agency2 + ", compress status=" + status3 + ", compress agency=" + (compressBean2 != null ? compressBean2.getAgency() : null) + "]";
    }
}
