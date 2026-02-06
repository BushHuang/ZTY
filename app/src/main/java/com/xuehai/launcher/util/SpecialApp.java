package com.xuehai.launcher.util;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import com.xuehai.launcher.common.util.app.ApplicationUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\nH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/xuehai/launcher/util/SpecialApp;", "", "()V", "IME", "", "getDefaultInputMethod", "context", "Landroid/content/Context;", "startNetCheck", "", "Landroid/app/Activity;", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SpecialApp {
    public static final String IME = "com.xh.ime";
    public static final SpecialApp INSTANCE = new SpecialApp();

    private SpecialApp() {
    }

    @JvmStatic
    public static final String getDefaultInputMethod(Context context) {
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
                    String id2 = inputMethodInfo.getId();
                    Intrinsics.checkNotNullExpressionValue(id2, "inputMethod.id");
                    return id2;
                }
            }
            return "";
        }
        return "";
    }

    @JvmStatic
    public static final boolean startNetCheck(Activity context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return ApplicationUtil.startApp$default(ApplicationUtil.INSTANCE, context, "com.xh.assist", 0, 4, (Object) null);
    }
}
