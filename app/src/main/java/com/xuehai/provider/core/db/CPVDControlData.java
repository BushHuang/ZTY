package com.xuehai.provider.core.db;

import android.content.Context;
import com.xuehai.provider.core.dto.CPVDCache;
import com.xuehai.provider.core.dto.CPVDControl;
import com.xuehai.provider.utils.JsonUtil;

public class CPVDControlData {
    public static CPVDControl get(Context context) {
        return (CPVDControl) JsonUtil.parseJson(CPVDCacheData.getCacheValue(context, getKey()), CPVDControl.class);
    }

    public static String getKey() {
        return "platform_control";
    }

    public static void update(Context context, CPVDControl cPVDControl) {
        CPVDCacheData.saveCache(context, new CPVDCache(getKey(), JsonUtil.objToJson(cPVDControl)));
    }
}
