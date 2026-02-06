package com.xuehai.provider.core.db;

import android.content.Context;
import com.xuehai.provider.core.dto.CPVDCache;
import com.xuehai.provider.core.dto.CPVDUser;
import com.xuehai.provider.utils.CPVDUtil;
import com.xuehai.provider.utils.JsonUtil;

public class CPVDUserData {
    private static CPVDUser user;

    public static void clearCache(String str) {
        if (getKey().equals(str)) {
            user = null;
        }
    }

    public static String getKey() {
        return "user";
    }

    public static String getSession(Context context) {
        CPVDUser user2 = getUser(context);
        return user2 == null ? "" : CPVDUtil.parserString(user2.getAccessToken());
    }

    public static CPVDUser getUser(Context context) {
        if (user == null) {
            user = (CPVDUser) JsonUtil.parseJson(CPVDCacheData.getCacheValue(context, getKey()), CPVDUser.class);
        }
        return user;
    }

    public static void saveUser(Context context, CPVDUser cPVDUser) {
        CPVDCacheData.saveCache(context, new CPVDCache(getKey(), JsonUtil.objToJson(cPVDUser)));
        user = cPVDUser;
    }
}
