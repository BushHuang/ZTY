package com.dovar.dtoast.inner;

import android.view.View;

public interface IToast {
    void cancel();

    View getView();

    IToast setAnimation(int i);

    IToast setDuration(int i);

    IToast setGravity(int i);

    IToast setGravity(int i, int i2, int i3);

    IToast setPriority(int i);

    IToast setText(int i, String str);

    IToast setView(View view);

    void show();

    void showLong();
}
