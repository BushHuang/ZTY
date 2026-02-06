package com.xuehai.launcher.common.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;

public interface ResourceAdapter {
    <T extends View> T findView(View view, int i);

    Bitmap getBitmap(int i);

    int getColor(int i);

    Drawable getDrawable(int i);

    String getString(int i, Object... objArr);
}
