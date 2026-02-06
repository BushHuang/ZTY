package com.xh.xhcore.common.hotfix;

import com.tencent.tinker.loader.shareutil.SharePatchInfo;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u0002¨\u0006\u0003"}, d2 = {"toString", "", "Lcom/tencent/tinker/loader/shareutil/SharePatchInfo;", "xhcore_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class UploadPatchReporterKt {
    public static final String toString(SharePatchInfo sharePatchInfo) {
        if (sharePatchInfo == null) {
            return "null";
        }
        return "{oldVer:" + ((Object) sharePatchInfo.oldVersion) + ", newVer:" + ((Object) sharePatchInfo.newVersion) + ", isProtectedApp:" + (sharePatchInfo.isProtectedApp ? 1 : 0) + ", isRemoveNewVersion:" + (sharePatchInfo.isRemoveNewVersion ? 1 : 0) + ", fingerprint:" + ((Object) sharePatchInfo.fingerPrint) + ", oatDir:" + ((Object) sharePatchInfo.oatDir) + '}';
    }
}
