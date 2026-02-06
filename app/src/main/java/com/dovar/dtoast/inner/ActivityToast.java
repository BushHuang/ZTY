package com.dovar.dtoast.inner;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;

public class ActivityToast extends DovaToast {
    public ActivityToast(Context context) {
        super(context);
    }

    @Override
    public WindowManager getWMManager() {
        if (this.mContext instanceof Activity) {
            return ((Activity) this.mContext).getWindowManager();
        }
        return null;
    }

    @Override
    public WindowManager.LayoutParams getWMParams() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = 8;
        layoutParams.format = -3;
        layoutParams.height = -2;
        layoutParams.width = -2;
        layoutParams.windowAnimations = 16973828;
        layoutParams.gravity = getGravity();
        layoutParams.x = getXOffset();
        layoutParams.y = getYOffset();
        return layoutParams;
    }
}
