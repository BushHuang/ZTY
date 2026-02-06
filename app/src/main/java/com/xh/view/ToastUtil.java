package com.xh.view;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import com.dovar.dtoast.DToast;
import com.dovar.dtoast.inner.IToast;

public class ToastUtil {
    private static final SparseArray<String> sErrorCodeMsgMap;

    static {
        SparseArray<String> sparseArray = new SparseArray<>();
        sErrorCodeMsgMap = sparseArray;
        sparseArray.put(107000429, "您发送请求太频繁，请休息一会儿！");
    }

    private ToastUtil() {
    }

    private static void runToastHandlerThread(Runnable runnable, int i) {
        Handler customHandler = HandlerThreadManager.getCustomHandler("handler_thread_toast");
        customHandler.removeCallbacks(runnable);
        customHandler.postDelayed(runnable, i);
    }

    private static void showInnerToast(Context context, CharSequence charSequence, int i, boolean z) {
        if (TextUtils.isEmpty(charSequence)) {
            charSequence = "";
        }
        if (i != 0 && i != 1) {
            i = 0;
        }
        IToast text = DToast.make(context).setView(View.inflate(context, R.layout.layout_toast, null)).setText(R.id.tv_toast_msg, charSequence.toString());
        if (z) {
            text.setGravity(17);
        } else {
            text.setGravity(81, 0, 60);
        }
        if (i == 0) {
            text.show();
        } else {
            text.showLong();
        }
    }

    public static void showLong(Context context, int i) {
        showLong(context, i, false);
    }

    public static void showLong(Context context, int i, boolean z) {
        showToast(context, i, 1, z);
    }

    public static void showLong(Context context, CharSequence charSequence) {
        showLong(context, charSequence, false);
    }

    public static void showLong(Context context, CharSequence charSequence, boolean z) {
        showToast(context, charSequence, 1, z);
    }

    public static void showShort(Context context, int i) {
        showShort(context, i, false);
    }

    public static void showShort(Context context, int i, boolean z) {
        showToast(context, i, 0, z);
    }

    public static void showShort(Context context, CharSequence charSequence) {
        showShort(context, charSequence, false);
    }

    public static void showShort(Context context, CharSequence charSequence, boolean z) {
        showToast(context, charSequence.toString(), 0, z);
    }

    public static void showToast(Context context, int i, int i2) {
        showToast(context, i, i2, false);
    }

    public static void showToast(Context context, int i, int i2, boolean z) {
        showToast(context, context.getResources().getString(i), i2, z);
    }

    public static void showToast(Context context, CharSequence charSequence, int i) {
        showToast(context, charSequence, i, false);
    }

    public static void showToast(final Context context, final CharSequence charSequence, final int i, final boolean z) {
        runToastHandlerThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showInnerToast(context, charSequence, i, z);
            }
        }, 0);
    }

    public static boolean showToast(Context context, int i) {
        String str = sErrorCodeMsgMap.get(i);
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        showToast(context, str, 0);
        return true;
    }
}
