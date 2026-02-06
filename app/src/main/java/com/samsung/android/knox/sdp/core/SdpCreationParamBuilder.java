package com.samsung.android.knox.sdp.core;

import com.samsung.android.knox.SupportLibUtils;

public class SdpCreationParamBuilder {
    private com.sec.enterprise.knox.sdp.engine.SdpCreationParamBuilder mSdpCreationParamBuilder;

    public SdpCreationParamBuilder(String str, int i) {
        this.mSdpCreationParamBuilder = new com.sec.enterprise.knox.sdp.engine.SdpCreationParamBuilder(str, i);
    }

    public void addPrivilegedApp(SdpDomain sdpDomain) {
        try {
            this.mSdpCreationParamBuilder.addPrivilegedApp(SdpDomain.convertToOld(sdpDomain));
        } catch (NoClassDefFoundError e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public SdpCreationParam getParam() {
        try {
            return SdpCreationParam.convertToNew(this.mSdpCreationParamBuilder.getParam());
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(SdpCreationParamBuilder.class, "getParam", null, 19));
        }
    }

    public void setMdfpp() {
        try {
            this.mSdpCreationParamBuilder.setMdfpp();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(SdpCreationParamBuilder.class, "setMdfpp", null, 19));
        }
    }

    public void setMinor() {
        try {
            this.mSdpCreationParamBuilder.setMinor();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(SdpCreationParamBuilder.class, "setMinor", null, 19));
        }
    }
}
