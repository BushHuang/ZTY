package com.analysys.strategy;

import android.content.Context;
import android.text.TextUtils;
import com.analysys.aa;
import com.analysys.utils.SharedUtil;
import com.analysys.x;
import com.analysys.y;
import com.analysys.z;
import org.json.JSONObject;

public class PolicyManager {
    public static void analysysStrategy(Context context, JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() <= 0) {
            return;
        }
        SharedUtil.setLong(context, "policyNo", jSONObject.optLong("policyNo", -1L));
        SharedUtil.setLong(context, "serviceEventCount", jSONObject.optLong("eventCount", -1L));
        long jOptLong = jSONObject.optLong("timerInterval", -1L);
        if (jOptLong > -1) {
            jOptLong *= 1000;
        }
        SharedUtil.setLong(context, "serviceTimerInterval", jOptLong);
        SharedUtil.setLong(context, "failCount", jSONObject.optLong("failCount", -1L));
        long jOptLong2 = jSONObject.optLong("failTryDelay", -1L);
        if (jOptLong2 > -1) {
            jOptLong2 *= 1000;
        }
        SharedUtil.setLong(context, "failTryDelay", jOptLong2);
        SharedUtil.setInt(context, "serviceDebug", jSONObject.optInt("debugMode", -1));
        String strOptString = jSONObject.optString("serverUrl", "");
        if (!TextUtils.isEmpty(strOptString) && (strOptString.startsWith("http://") || strOptString.startsWith("https://"))) {
            SharedUtil.setString(context, "serviceUrl", strOptString);
        }
        SharedUtil.setString(context, "serviceHash", jSONObject.optString("hash", ""));
    }

    public static long getEventCount(Context context) {
        long j = SharedUtil.getLong(context, "serviceEventCount", -1L);
        if (j != -1) {
            return j;
        }
        long j2 = SharedUtil.getLong(context, "userEventCount", -1L);
        if (j2 != -1) {
            return j2;
        }
        return 0L;
    }

    public static long getIntervalTime(Context context) {
        long j = SharedUtil.getLong(context, "serviceTimerInterval", -1L);
        if (j != -1) {
            return j;
        }
        long j2 = SharedUtil.getLong(context, "userIntervalTime", -1L);
        if (j2 != -1) {
            return j2;
        }
        return 0L;
    }

    public static x getPolicyType(Context context) {
        if (SharedUtil.getLong(context, "policyNo", -1L) != -1) {
            return new z();
        }
        return (SharedUtil.getLong(context, "userIntervalTime", -1L) == -1 && SharedUtil.getLong(context, "userEventCount", -1L) == -1) ? new y() : new aa();
    }
}
