package com.xuehai.platform.hotfix;

import com.xuehai.provider.core.CPVDManager;
import com.xuehai.provider.core.db.CPVDCacheData;
import com.xuehai.provider.core.dto.CPVDCache;
import com.xuehai.provider.utils.JsonUtil;

public class HotfixPatchInfoManager {
    public static final String HOTFIX_PATCH_INFOS = "hotfix_patch_infos";

    public static PatchInfo queryPatchInfo(String str) {
        RemotePatchInfos allPatchInfos = readAllPatchInfos();
        PatchInfo patchInfo = null;
        if (allPatchInfos != null && allPatchInfos.getContent() != null) {
            for (PatchInfo patchInfo2 : allPatchInfos.getContent()) {
                if (patchInfo2 != null && str.equals(patchInfo2.getPackageName()) && (patchInfo == null || patchInfo.getVersion() < patchInfo2.getVersion())) {
                    patchInfo = patchInfo2;
                }
            }
        }
        return patchInfo;
    }

    public static RemotePatchInfos readAllPatchInfos() {
        return (RemotePatchInfos) JsonUtil.parseJson(CPVDCacheData.getCacheValue(CPVDManager.getContext(), "hotfix_patch_infos"), RemotePatchInfos.class);
    }

    public static void writePatchInfos(RemotePatchInfos remotePatchInfos) {
        CPVDCacheData.saveCache(CPVDManager.getContext(), new CPVDCache("hotfix_patch_infos", JsonUtil.objToJson(remotePatchInfos)));
    }

    public static void writePatchInfos(String str) {
        CPVDCacheData.saveCache(CPVDManager.getContext(), new CPVDCache("hotfix_patch_infos", str));
    }
}
