package com.xuehai.platform;

import android.content.Context;
import android.content.Intent;
import com.xuehai.provider.core.CPVDProcess;

public class ResourceUpdateManager {
    public static final String KEY_RESOURCE_CODE = "dataCode";

    public static String resourceUpdate(String str) {
        return "com.xuehai.cpvd.resource_update_" + str;
    }

    public static void resourceUpdate(Context context, String str, String str2) {
        Intent intent = new Intent(resourceUpdate(str));
        intent.putExtra("dataCode", str2);
        CPVDProcess.sendBroadcast(context, intent);
    }
}
