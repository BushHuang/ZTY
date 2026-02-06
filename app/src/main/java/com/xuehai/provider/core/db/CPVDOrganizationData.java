package com.xuehai.provider.core.db;

import android.content.Context;
import com.xuehai.provider.core.dto.CPVDCache;
import com.xuehai.provider.core.dto.CPVDOrganization;
import com.xuehai.provider.utils.JsonUtil;

public class CPVDOrganizationData {
    public static CPVDOrganization get(Context context) {
        return (CPVDOrganization) JsonUtil.parseJson(CPVDCacheData.getCacheValue(context, getKey()), CPVDOrganization.class);
    }

    public static String getKey() {
        return "organization";
    }

    public static void update(Context context, CPVDOrganization cPVDOrganization) {
        CPVDCacheData.saveCache(context, new CPVDCache(getKey(), JsonUtil.objToJson(cPVDOrganization)));
    }
}
