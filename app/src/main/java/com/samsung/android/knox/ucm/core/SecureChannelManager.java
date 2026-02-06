package com.samsung.android.knox.ucm.core;

import android.os.Bundle;
import com.samsung.android.knox.SupportLibUtils;

public class SecureChannelManager {
    private com.sec.enterprise.knox.ucm.core.SecureChannelManager mSecureChannelManager;

    private SecureChannelManager(com.sec.enterprise.knox.ucm.core.SecureChannelManager secureChannelManager) {
        this.mSecureChannelManager = secureChannelManager;
    }

    public static SecureChannelManager getInstance() {
        try {
            com.sec.enterprise.knox.ucm.core.SecureChannelManager secureChannelManager = com.sec.enterprise.knox.ucm.core.SecureChannelManager.getInstance();
            if (secureChannelManager == null) {
                return null;
            }
            return new SecureChannelManager(secureChannelManager);
        } catch (NoClassDefFoundError unused) {
            throw new NoClassDefFoundError(SupportLibUtils.buildClassErrorMsg(SecureChannelManager.class, 20));
        }
    }

    public ApduMessage createSecureChannel(int i, Bundle bundle) {
        try {
            return ApduMessage.convertToNew(this.mSecureChannelManager.createSecureChannel(i, bundle));
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(SecureChannelManager.class, "createSecureChannel", new Class[]{Integer.TYPE, Bundle.class}, 20));
        }
    }

    public int destroySecureChannel() {
        try {
            return this.mSecureChannelManager.destroySecureChannel();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(SecureChannelManager.class, "destroySecureChannel", null, 20));
        }
    }

    public ApduMessage processMessage(int i, byte[] bArr) {
        try {
            return ApduMessage.convertToNew(this.mSecureChannelManager.processMessage(i, bArr));
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(SecureChannelManager.class, "processMessage", new Class[]{Integer.TYPE, byte[].class}, 20));
        }
    }
}
