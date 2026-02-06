package com.samsung.android.knox.container;

import com.samsung.android.knox.SupportLibUtils;
import java.util.List;

public class RCPPolicy {
    private com.sec.enterprise.knox.container.RCPPolicy mRCPPolicy;

    public RCPPolicy(com.sec.enterprise.knox.container.RCPPolicy rCPPolicy) {
        this.mRCPPolicy = rCPPolicy;
    }

    public boolean allowMoveAppsToContainer(boolean z) {
        return this.mRCPPolicy.allowMoveAppsToContainer(z);
    }

    public boolean allowMoveFilesToContainer(boolean z) {
        return this.mRCPPolicy.allowMoveFilesToContainer(z);
    }

    public boolean allowMoveFilesToOwner(boolean z) {
        return this.mRCPPolicy.allowMoveFilesToOwner(z);
    }

    public boolean allowShareClipboardDataToOwner(boolean z) {
        try {
            return this.mRCPPolicy.allowShareClipboardDataToOwner(z);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(RCPPolicy.class, "allowShareClipboardDataToOwner", new Class[]{Boolean.TYPE}, 19));
        }
    }

    public boolean getAllowChangeDataSyncPolicy(String str, String str2) {
        return this.mRCPPolicy.getAllowChangeDataSyncPolicy(str, str2);
    }

    public List<String> getListFromAllowChangeDataSyncPolicy(String str, boolean z) {
        return this.mRCPPolicy.getListFromAllowChangeDataSyncPolicy(str, z);
    }

    public boolean isMoveAppsToContainerAllowed() {
        return this.mRCPPolicy.isMoveAppsToContainerAllowed();
    }

    public boolean isMoveFilesToContainerAllowed() {
        return this.mRCPPolicy.isMoveFilesToContainerAllowed();
    }

    public boolean isMoveFilesToOwnerAllowed() {
        return this.mRCPPolicy.isMoveFilesToOwnerAllowed();
    }

    public boolean isShareClipboardDataToOwnerAllowed() {
        try {
            return this.mRCPPolicy.isShareClipboardDataToOwnerAllowed();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(RCPPolicy.class, "isShareClipboardDataToOwnerAllowed", null, 19));
        }
    }

    public boolean setAllowChangeDataSyncPolicy(List<String> list, String str, boolean z) {
        return this.mRCPPolicy.setAllowChangeDataSyncPolicy(list, str, z);
    }
}
