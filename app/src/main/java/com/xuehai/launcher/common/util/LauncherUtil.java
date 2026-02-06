package com.xuehai.launcher.common.util;

import android.content.Context;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONException;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u001c\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\nH\u0007¨\u0006\u000b"}, d2 = {"Lcom/xuehai/launcher/common/util/LauncherUtil;", "", "()V", "getXHInputMethod", "", "context", "Landroid/content/Context;", "mapToJson", "Lorg/json/JSONObject;", "map", "", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LauncherUtil {
    public static final LauncherUtil INSTANCE = new LauncherUtil();

    private LauncherUtil() {
    }

    @JvmStatic
    public static final String getXHInputMethod(Context context) {
        Object systemService;
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            systemService = context.getSystemService("input_method");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        }
        List<InputMethodInfo> inputMethodList = ((InputMethodManager) systemService).getInputMethodList();
        if (inputMethodList != null && !inputMethodList.isEmpty()) {
            for (InputMethodInfo inputMethodInfo : inputMethodList) {
                String id = inputMethodInfo.getId();
                Intrinsics.checkNotNullExpressionValue(id, "inputMethod.id");
                if (StringsKt.contains$default((CharSequence) id, (CharSequence) "com.xh.ime", false, 2, (Object) null)) {
                    return inputMethodInfo.getId();
                }
            }
            return null;
        }
        return null;
    }

    @JvmStatic
    public static final JSONObject mapToJson(Map<String, ? extends Object> map) throws JSONException {
        Intrinsics.checkNotNullParameter(map, "map");
        JSONObject jSONObject = new JSONObject();
        try {
            for (String str : map.keySet()) {
                jSONObject.put(str, map.get(str));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
