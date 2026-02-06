package com.samsung.android.knox.dlp;

import android.os.Bundle;
import com.samsung.android.knox.SupportLibUtils;
import java.util.List;

public class DLPManagerPolicy {
    private com.sec.enterprise.knox.dlp.DLPManagerPolicy mDLPManagerPolicy;

    public DLPManagerPolicy(com.sec.enterprise.knox.dlp.DLPManagerPolicy dLPManagerPolicy) {
        this.mDLPManagerPolicy = dLPManagerPolicy;
    }

    public boolean addPackagesToAllowDLPWhiteList(List<DLPPackageInfo> list) {
        try {
            return this.mDLPManagerPolicy.addPackagesToAllowDLPWhiteList(DLPPackageInfo.convertToOldList(list));
        } catch (NoClassDefFoundError e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public Bundle getDLPConfig() {
        try {
            return this.mDLPManagerPolicy.getDLPConfig();
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(DLPManagerPolicy.class, "getDLPConfig", null, 19));
        }
    }

    public List<DLPPackageInfo> getPackagesFromAllowDLPWhiteList() {
        try {
            return DLPPackageInfo.convertToNewList(this.mDLPManagerPolicy.getPackagesFromAllowDLPWhiteList());
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(DLPManagerPolicy.class, "getPackagesFromAllowDLPWhiteList", null, 19));
        }
    }

    public boolean removeDLPFiles(boolean z) {
        try {
            return this.mDLPManagerPolicy.removeDLPFiles(z);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(DLPManagerPolicy.class, "removeDLPFiles", new Class[]{Boolean.TYPE}, 19));
        }
    }

    public boolean removePackagesFromAllowDLPWhiteList(List<String> list) {
        try {
            return this.mDLPManagerPolicy.removePackagesFromAllowDLPWhiteList(list);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(DLPManagerPolicy.class, "removePackagesFromAllowDLPWhiteList", new Class[]{List.class}, 19));
        }
    }

    public boolean setDLPConfig(Bundle bundle) {
        try {
            return this.mDLPManagerPolicy.setDLPConfig(bundle);
        } catch (NoSuchMethodError unused) {
            throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(DLPManagerPolicy.class, "setDLPConfig", new Class[]{Bundle.class}, 19));
        }
    }
}
