package com.analysys.hybrid;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.WebView;
import com.analysys.AnalysysAgent;
import com.analysys.process.AgentProcess;
import com.analysys.utils.AnalysysUtil;
import com.analysys.utils.Constants;
import com.analysys.utils.ExceptionUtil;
import com.analysys.utils.SharedUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HybridBridge {

    static class a {

        private static final HybridBridge f48a = new HybridBridge();
    }

    private HybridBridge() {
    }

    private void alias(Context context, JSONArray jSONArray) {
        if (jSONArray != null) {
            if (jSONArray.length() == 2) {
                AnalysysAgent.alias(context, jSONArray.optString(0), jSONArray.optString(1));
            } else if (jSONArray.length() == 1) {
                AnalysysAgent.alias(context, jSONArray.optString(0));
            }
        }
    }

    private void clearSuperProperties(Context context, JSONArray jSONArray) {
        AgentProcess.getInstance().clearJsSuperProperty();
    }

    private Map<String, Object> convertToMap(JSONObject jSONObject) {
        HashMap map = new HashMap();
        if (jSONObject != null && jSONObject.length() > 0) {
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                map.put(next, jSONObject.opt(next));
            }
        }
        return map;
    }

    private Map<String, Number> convertToNumberMap(JSONObject jSONObject) {
        HashMap map = new HashMap();
        if (jSONObject != null && jSONObject.length() > 0) {
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                map.put(next, (Number) jSONObject.opt(next));
            }
        }
        return map;
    }

    private void getDistinctId(Context context, JSONArray jSONArray, String str, WebView webView) {
        String distinctId = AnalysysAgent.getDistinctId(context);
        if (TextUtils.isEmpty(distinctId)) {
            return;
        }
        webView.loadUrl("javascript:" + str + "('" + distinctId + "')");
    }

    public static HybridBridge getInstance() {
        return a.f48a;
    }

    private void getPresetProperties(Context context, JSONArray jSONArray, String str, WebView webView) {
        Map<String, Object> presetProperties = AnalysysAgent.getPresetProperties(context);
        if (presetProperties == null || presetProperties.size() <= 0) {
            return;
        }
        webView.loadUrl("javascript:" + str + "('" + presetProperties.toString() + "')");
    }

    private void getSuperProperties(Context context, JSONArray jSONArray, String str, WebView webView) {
        Map<String, Object> superProperties = AnalysysAgent.getSuperProperties(context);
        if (superProperties == null || superProperties.size() <= 0) {
            return;
        }
        webView.loadUrl("javascript:" + str + "('" + superProperties.toString() + "')");
    }

    private void getSuperProperty(Context context, JSONArray jSONArray, String str, WebView webView) {
        Object superProperty;
        if (jSONArray == null || jSONArray.length() <= 0) {
            return;
        }
        String strOptString = jSONArray.optString(0);
        if (TextUtils.isEmpty(strOptString) || (superProperty = AnalysysAgent.getSuperProperty(context, strOptString)) == null) {
            return;
        }
        webView.loadUrl("javascript:" + str + "('" + superProperty.toString() + "')");
    }

    private void identify(Context context, JSONArray jSONArray) {
        String strOptString = jSONArray.optString(0);
        if (TextUtils.isEmpty(strOptString)) {
            return;
        }
        AnalysysAgent.identify(context, strOptString);
    }

    private void pageView(Context context, JSONArray jSONArray) throws JSONException {
        if (jSONArray.length() > 1) {
            try {
                String strOptString = jSONArray.optString(0);
                JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(1);
                if (TextUtils.isEmpty(jSONObjectOptJSONObject.optString("$referrer", null))) {
                    String string = SharedUtil.getString(AnalysysUtil.getContext(), Constants.SP_REFER, "");
                    if (!TextUtils.isEmpty(string)) {
                        jSONObjectOptJSONObject.put("$referrer", string);
                    }
                }
                AgentProcess.getInstance().hybridPageView(strOptString, convertToMap(jSONObjectOptJSONObject));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void profileAppend(Context context, JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            return;
        }
        if (jSONArray.length() == 2) {
            AnalysysAgent.profileAppend(context, jSONArray.optString(0), jSONArray.opt(1));
            return;
        }
        JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(0);
        if (jSONObjectOptJSONObject == null || jSONObjectOptJSONObject.length() <= 0) {
            return;
        }
        AnalysysAgent.profileAppend(context, convertToMap(jSONObjectOptJSONObject));
    }

    private void profileDelete(Context context, JSONArray jSONArray) {
        AnalysysAgent.profileDelete(context);
    }

    private void profileIncrement(Context context, JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            return;
        }
        if (jSONArray.length() == 2) {
            AnalysysAgent.profileIncrement(context, jSONArray.optString(0), (Number) jSONArray.opt(1));
            return;
        }
        JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(0);
        if (jSONObjectOptJSONObject == null || jSONObjectOptJSONObject.length() <= 0) {
            return;
        }
        AnalysysAgent.profileIncrement(context, convertToNumberMap(jSONObjectOptJSONObject));
    }

    private void profileSet(Context context, JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            return;
        }
        if (jSONArray.length() == 2) {
            AnalysysAgent.profileSet(context, jSONArray.optString(0), jSONArray.opt(1));
            return;
        }
        JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(0);
        if (jSONObjectOptJSONObject == null || jSONObjectOptJSONObject.length() <= 0) {
            return;
        }
        AnalysysAgent.profileSet(context, convertToMap(jSONObjectOptJSONObject));
    }

    private void profileSetOnce(Context context, JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            return;
        }
        if (jSONArray.length() == 2) {
            AnalysysAgent.profileSetOnce(context, jSONArray.optString(0), jSONArray.opt(1));
            return;
        }
        JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(0);
        if (jSONObjectOptJSONObject == null || jSONObjectOptJSONObject.length() <= 0) {
            return;
        }
        AnalysysAgent.profileSetOnce(context, convertToMap(jSONObjectOptJSONObject));
    }

    private void profileUnset(Context context, JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            return;
        }
        String strOptString = jSONArray.optString(0);
        if (TextUtils.isEmpty(strOptString)) {
            return;
        }
        AnalysysAgent.profileUnset(context, strOptString);
    }

    private void registerSuperProperties(Context context, JSONArray jSONArray) {
        JSONObject jSONObjectOptJSONObject;
        if (context == null || jSONArray == null || jSONArray.length() <= 0 || (jSONObjectOptJSONObject = jSONArray.optJSONObject(0)) == null || jSONObjectOptJSONObject.length() <= 0) {
            return;
        }
        AgentProcess.getInstance().registerJsSuperProperties(convertToMap(jSONObjectOptJSONObject));
    }

    private void registerSuperProperty(Context context, JSONArray jSONArray) {
        if (context == null || jSONArray == null || jSONArray.length() <= 0) {
            return;
        }
        AgentProcess.getInstance().registerJsSuperProperty(jSONArray.optString(0), jSONArray.opt(1));
    }

    private void reset(Context context, JSONArray jSONArray) {
        AnalysysAgent.reset(context);
    }

    private void track(Context context, JSONArray jSONArray) {
        if (jSONArray.length() > 1) {
            AnalysysAgent.track(context, jSONArray.optString(0), convertToMap(jSONArray.optJSONObject(1)));
            return;
        }
        String strOptString = jSONArray.optString(0);
        if (TextUtils.isEmpty(strOptString)) {
            return;
        }
        AnalysysAgent.track(context, strOptString);
    }

    private void unRegisterSuperProperty(Context context, JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            return;
        }
        String strOptString = jSONArray.optString(0);
        if (TextUtils.isEmpty(strOptString)) {
            return;
        }
        AgentProcess.getInstance().unregisterJsSuperProperty(strOptString);
    }

    public void execute(String str, Object obj) {
        try {
            Context context = AnalysysUtil.getContext();
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.length() > 0) {
                String strOptString = jSONObject.optString("functionName");
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("functionParams");
                String strOptString2 = jSONObject.optString("callbackFunName");
                if (TextUtils.isEmpty(strOptString2)) {
                    HybridBridge.class.getDeclaredMethod(strOptString, Context.class, JSONArray.class).invoke(a.f48a, context, jSONArrayOptJSONArray);
                } else {
                    HybridBridge.class.getDeclaredMethod(strOptString, Context.class, JSONArray.class, String.class, WebView.class).invoke(a.f48a, context, jSONArrayOptJSONArray, strOptString2, obj);
                }
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }
}
