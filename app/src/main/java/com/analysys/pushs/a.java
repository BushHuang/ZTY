package com.analysys.pushs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.analysys.AnalysysAgent;
import com.analysys.push.PushListener;
import com.analysys.utils.ExceptionUtil;
import com.analysys.utils.InternalAgent;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class a {

    private static Map<String, String> f71a;
    private Context b;

    static class C0010a {

        public static final a f72a = new a();
    }

    private a() {
        this.b = null;
        HashMap map = new HashMap();
        f71a = map;
        map.put("$JPUSH", "");
        f71a.put("$GETUI", "");
        f71a.put("$BAIDU", "");
        f71a.put("$XIAOMI", "");
        f71a.put("$ALIYUN", "");
        f71a.put("$HUAWEI", "");
        f71a.put("$OPPO", "");
        f71a.put("$VIVO", "");
        f71a.put("$MEIZU", "");
        f71a.put("$XINGE", "");
    }

    public static a a(Context context) {
        if (InternalAgent.isEmpty(C0010a.f72a.b) && !InternalAgent.isEmpty(context)) {
            C0010a.f72a.b = context;
        }
        return C0010a.f72a;
    }

    private Map<String, Object> a(String str) {
        HashMap map = null;
        try {
            HashMap map2 = new HashMap();
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("EGPUSH_CINFO")) {
                    jSONObject = new JSONObject(jSONObject.optString("EGPUSH_CINFO"));
                }
                String strOptString = jSONObject.optString("campaign_id");
                String strOptString2 = jSONObject.optString("ACTION");
                int iOptInt = jSONObject.optInt("ACTIONTYPE");
                String strOptString3 = jSONObject.optString("CPD");
                String strOptString4 = jSONObject.optString("utm_campaign");
                String strOptString5 = jSONObject.optString("utm_medium");
                String strOptString6 = jSONObject.optString("utm_source");
                String strOptString7 = jSONObject.optString("utm_content");
                String strOptString8 = jSONObject.optString("utm_term");
                if (iOptInt <= 0 || iOptInt > 4) {
                    try {
                        InternalAgent.e("推送信息下发的Action值不在规定范围[1,2,3,4]内,error:" + iOptInt);
                    } catch (Throwable th) {
                        ExceptionUtil.exceptionThrow(th);
                        InternalAgent.e("推送信息下发的Action值不在规定范围[1,2,3,4]内,error:" + iOptInt);
                    }
                }
                map2.put("$action", strOptString2);
                map2.put("$action_type", Integer.valueOf(iOptInt));
                map2.put("$utm_campaign_id", strOptString);
                map2.put("$cpd", strOptString3);
                map2.put("$utm_campaign", strOptString4);
                map2.put("$utm_medium", strOptString5);
                map2.put("$utm_source", strOptString6);
                map2.put("$utm_content", strOptString7);
                map2.put("$utm_term", strOptString8);
                return map2;
            } catch (Throwable th2) {
                th = th2;
                map = map2;
                ExceptionUtil.exceptionThrow(th);
                return map;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    private void a(PushListener pushListener, String str, String str2) {
        if (pushListener != null) {
            pushListener.execute(str, str2);
        }
    }

    private void a(PushListener pushListener, String str, Map<String, Object> map, String str2) {
        try {
            if (this.b != null) {
                Intent launchIntentForPackage = this.b.getPackageManager().getLaunchIntentForPackage(this.b.getPackageName());
                launchIntentForPackage.addFlags(268435456);
                this.b.startActivity(launchIntentForPackage);
                AnalysysAgent.track(this.b, "$push_process_success", map);
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
        if (pushListener != null) {
            pushListener.execute(str, str2);
        }
    }

    private void b(PushListener pushListener, String str, Map<String, Object> map, String str2) {
        try {
            if (!TextUtils.isEmpty(str) && this.b != null) {
                Intent intent = new Intent(this.b, Class.forName(str));
                intent.addFlags(268435456);
                if (!TextUtils.isEmpty(str2)) {
                    intent.putExtra("CPD", str2);
                }
                this.b.startActivity(intent);
                AnalysysAgent.track(this.b, "$push_process_success", map);
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
        if (pushListener != null) {
            pushListener.execute(str, str2);
        }
    }

    private void c(PushListener pushListener, String str, Map<String, Object> map, String str2) {
        try {
            if (!TextUtils.isEmpty(str) && this.b != null) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.addFlags(268435456);
                intent.setData(Uri.parse(str));
                if (!TextUtils.isEmpty(str2)) {
                    intent.putExtra("CPD", str2);
                }
                this.b.startActivity(intent);
                AnalysysAgent.track(this.b, "$push_process_success", map);
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
        if (pushListener != null) {
            pushListener.execute(str, str2);
        }
    }

    public void a(String str, String str2) {
        try {
            if (!InternalAgent.checkClass("com.analysys.push", "PushProvider")) {
                InternalAgent.w("该版本未集成Push功能");
                return;
            }
            if (InternalAgent.isEmpty(str)) {
                InternalAgent.w("传入参数Push平台值为空");
                return;
            }
            if (InternalAgent.isEmpty(str2)) {
                InternalAgent.w("传入参数PushId值为空");
            } else {
                if (f71a.get(str).equals(str2)) {
                    return;
                }
                f71a.put(str, str2);
                AnalysysAgent.profileSet(this.b, str, str2);
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    public void a(String str, boolean z, PushListener pushListener) {
        if (!InternalAgent.checkClass("com.analysys.push", "PushProvider")) {
            InternalAgent.w("该版本未集成Push功能");
            return;
        }
        if (InternalAgent.isEmpty(str)) {
            InternalAgent.w("传入参数campaign的透传消息值为空");
            return;
        }
        Map<String, Object> mapA = a(str);
        if (mapA == null) {
            return;
        }
        if (!z) {
            AnalysysAgent.track(this.b, "$push_receiver_success", mapA);
        } else {
            AnalysysAgent.track(this.b, "$push_click", mapA);
            a(mapA, pushListener);
        }
    }

    public void a(Map<String, Object> map, PushListener pushListener) {
        try {
            int iIntValue = ((Integer) map.get("$action_type")).intValue();
            String str = (String) map.get("$action");
            String str2 = (String) map.get("$cpd");
            if (iIntValue == 1) {
                a(pushListener, str, map, str2);
            } else if (iIntValue == 2) {
                b(pushListener, str, map, str2);
            } else if (iIntValue == 3) {
                c(pushListener, str, map, str2);
            } else if (iIntValue == 4) {
                AnalysysAgent.track(this.b, "$push_process_success", map);
                a(pushListener, str, str2);
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }
}
