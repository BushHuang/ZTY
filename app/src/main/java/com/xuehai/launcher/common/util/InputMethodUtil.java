package com.xuehai.launcher.common.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class InputMethodUtil {
    public static void dismiss(Context context) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (activity.isFinishing()) {
                return;
            }
            InputMethodManager inputMethodManager = getInputMethodManager(context);
            try {
                View currentFocus = activity.getCurrentFocus();
                if (currentFocus == null || inputMethodManager == null) {
                    return;
                }
                inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void dismiss(Context context, View view) {
        InputMethodManager inputMethodManager;
        if (context == null || view == null || (inputMethodManager = getInputMethodManager(context)) == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 2);
    }

    private static InputMethodManager getInputMethodManager(Context context) {
        return (InputMethodManager) context.getSystemService("input_method");
    }

    public static boolean isActive(Context context) {
        InputMethodManager inputMethodManager = getInputMethodManager(context);
        if (inputMethodManager == null) {
            return false;
        }
        return inputMethodManager.isActive();
    }

    public static void show(Context context) {
        InputMethodManager inputMethodManager;
        if (context == null || (inputMethodManager = getInputMethodManager(context)) == null) {
            return;
        }
        inputMethodManager.toggleSoftInput(0, 2);
    }
}
