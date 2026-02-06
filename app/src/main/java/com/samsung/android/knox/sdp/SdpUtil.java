package com.samsung.android.knox.sdp;

import com.samsung.android.knox.SupportLibUtils;
import com.samsung.android.knox.sdp.core.SdpEngineInfo;
import com.samsung.android.knox.sdp.core.SdpException;
import com.sec.enterprise.knox.sdp.exception.SdpEngineNotExistsException;
import com.sec.enterprise.knox.sdp.exception.SdpInternalException;
import com.sec.enterprise.knox.sdp.exception.SdpLicenseRequiredException;

public class SdpUtil {
    private static SdpUtil mInstance;
    private static com.sec.enterprise.knox.sdp.SdpUtil mSdpUtil;

    private SdpUtil(com.sec.enterprise.knox.sdp.SdpUtil sdpUtil) {
        mSdpUtil = sdpUtil;
    }

    public static SdpUtil getInstance() {
        if (mInstance == null) {
            try {
                com.sec.enterprise.knox.sdp.SdpUtil sdpUtil = com.sec.enterprise.knox.sdp.SdpUtil.getInstance();
                if (sdpUtil != null) {
                    mInstance = new SdpUtil(sdpUtil);
                }
            } catch (NoSuchMethodError unused) {
                throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(SdpUtil.class, "getInstance", null, 19));
            }
        }
        return mInstance;
    }

    public static boolean isKnoxWorkspace(int i) {
        try {
            return com.sec.enterprise.knox.sdp.SdpUtil.isKnoxWorkspace(i);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(SdpUtil.class, "isKnoxWorkspace", new Class[]{Integer.TYPE}, 19));
        }
    }

    public SdpEngineInfo getEngineInfo(String str) throws SdpException {
        try {
            return SdpEngineInfo.convertToNew(mSdpUtil.getEngineInfo(str));
        } catch (SdpInternalException unused) {
            throw new SdpException(-15);
        } catch (SdpLicenseRequiredException unused2) {
            throw new SdpException(-8);
        } catch (SdpEngineNotExistsException unused3) {
            throw new SdpException(-5);
        } catch (NoSuchMethodError unused4) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(SdpUtil.class, "getEngineInfo", new Class[]{String.class}, 19));
        }
    }

    public boolean isSdpSupported() {
        try {
            return mSdpUtil.isSdpSupported();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(SdpUtil.class, "isSdpSupported", null, 19));
        }
    }

    public boolean registerListener(String str, SdpStateListener sdpStateListener) {
        try {
            return mSdpUtil.registerListener(str, new SdpStateListenerProxy(sdpStateListener));
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(SdpUtil.class, "registerListener", new Class[]{String.class, SdpStateListener.class}, 19));
        }
    }

    public boolean unregisterListener(String str, SdpStateListener sdpStateListener) {
        try {
            return mSdpUtil.unregisterListener(str, new SdpStateListenerProxy(sdpStateListener));
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(SdpUtil.class, "unregisterListener", new Class[]{String.class, SdpStateListener.class}, 19));
        }
    }
}
