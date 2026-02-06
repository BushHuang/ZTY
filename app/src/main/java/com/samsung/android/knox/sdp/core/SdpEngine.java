package com.samsung.android.knox.sdp.core;

import com.samsung.android.knox.SupportLibUtils;
import com.sec.enterprise.knox.sdp.exception.SdpAccessDeniedException;
import com.sec.enterprise.knox.sdp.exception.SdpEngineExistsException;
import com.sec.enterprise.knox.sdp.exception.SdpEngineLockedException;
import com.sec.enterprise.knox.sdp.exception.SdpEngineNotExistsException;
import com.sec.enterprise.knox.sdp.exception.SdpInternalException;
import com.sec.enterprise.knox.sdp.exception.SdpInvalidPasswordException;
import com.sec.enterprise.knox.sdp.exception.SdpInvalidResetTokenException;
import com.sec.enterprise.knox.sdp.exception.SdpLicenseRequiredException;
import com.sec.enterprise.knox.sdp.exception.SdpNotSupportedException;

public class SdpEngine {
    private static SdpEngine mInstance;
    private static com.sec.enterprise.knox.sdp.engine.SdpEngine mSdpEngine;

    private SdpEngine(com.sec.enterprise.knox.sdp.engine.SdpEngine sdpEngine) {
        mSdpEngine = sdpEngine;
    }

    public static SdpEngine getInstance() throws SdpException {
        if (mInstance == null) {
            try {
                com.sec.enterprise.knox.sdp.engine.SdpEngine sdpEngine = com.sec.enterprise.knox.sdp.engine.SdpEngine.getInstance();
                if (sdpEngine != null) {
                    mInstance = new SdpEngine(sdpEngine);
                }
            } catch (NoSuchMethodError unused) {
                throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(SdpEngine.class, "getInstance", null, 19));
            } catch (SdpLicenseRequiredException unused2) {
                throw new SdpException(-8);
            }
        }
        return mInstance;
    }

    public void addEngine(SdpCreationParam sdpCreationParam, String str, String str2) throws SdpException {
        try {
            mSdpEngine.addEngine(SdpCreationParam.convertToOld(sdpCreationParam), str, str2);
        } catch (SdpInvalidResetTokenException unused) {
            throw new SdpException(-2);
        } catch (SdpInternalException unused2) {
            throw new SdpException(-15);
        } catch (SdpNotSupportedException unused3) {
            throw new SdpException(-9);
        } catch (SdpAccessDeniedException unused4) {
            throw new SdpException(-7);
        } catch (SdpInvalidPasswordException unused5) {
            throw new SdpException(-1);
        } catch (NoClassDefFoundError e) {
            throw new NoClassDefFoundError(e.getMessage());
        } catch (SdpEngineExistsException unused6) {
            throw new SdpException(-4);
        }
    }

    public boolean exists(String str) {
        try {
            return mSdpEngine.exists(str);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(SdpEngine.class, "exists", new Class[]{String.class}, 19));
        }
    }

    public void lock(String str) throws SdpException {
        try {
            mSdpEngine.lock(str);
        } catch (SdpAccessDeniedException unused) {
            throw new SdpException(-7);
        } catch (NoSuchMethodError unused2) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(SdpEngine.class, "lock", new Class[]{String.class}, 19));
        } catch (SdpInternalException unused3) {
            throw new SdpException(-15);
        } catch (SdpEngineNotExistsException unused4) {
            throw new SdpException(-5);
        }
    }

    public void removeEngine(String str) throws SdpException {
        try {
            mSdpEngine.removeEngine(str);
        } catch (SdpAccessDeniedException unused) {
            throw new SdpException(-7);
        } catch (NoSuchMethodError unused2) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(SdpEngine.class, "removeEngine", new Class[]{String.class}, 19));
        } catch (SdpInternalException unused3) {
            throw new SdpException(-15);
        } catch (SdpEngineNotExistsException unused4) {
            throw new SdpException(-5);
        }
    }

    public void resetPassword(String str, String str2, String str3) throws SdpException {
        try {
            mSdpEngine.resetPassword(str, str2, str3);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(SdpEngine.class, "resetPassword", new Class[]{String.class, String.class, String.class}, 19));
        } catch (SdpInvalidPasswordException unused2) {
            throw new SdpException(-1);
        } catch (SdpInternalException unused3) {
            throw new SdpException(-15);
        } catch (SdpInvalidResetTokenException unused4) {
            throw new SdpException(-2);
        } catch (SdpAccessDeniedException unused5) {
            throw new SdpException(-7);
        } catch (SdpEngineNotExistsException unused6) {
            throw new SdpException(-5);
        }
    }

    public void setPassword(String str, String str2) throws SdpException {
        try {
            mSdpEngine.setPassword(str, str2);
        } catch (SdpEngineLockedException unused) {
            throw new SdpException(-6);
        } catch (SdpInternalException unused2) {
            throw new SdpException(-15);
        } catch (SdpEngineNotExistsException unused3) {
            throw new SdpException(-5);
        } catch (SdpAccessDeniedException unused4) {
            throw new SdpException(-7);
        } catch (SdpInvalidPasswordException unused5) {
            throw new SdpException(-1);
        } catch (NoSuchMethodError unused6) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(SdpEngine.class, "setPassword", new Class[]{String.class, String.class}, 19));
        }
    }

    public void unlock(String str, String str2) throws SdpException {
        try {
            mSdpEngine.unlock(str, str2);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(SdpEngine.class, "unlock", new Class[]{String.class, String.class}, 19));
        } catch (SdpInvalidPasswordException unused2) {
            throw new SdpException(-1);
        } catch (SdpInternalException unused3) {
            throw new SdpException(-15);
        } catch (SdpAccessDeniedException unused4) {
            throw new SdpException(-7);
        } catch (SdpEngineNotExistsException unused5) {
            throw new SdpException(-5);
        }
    }
}
