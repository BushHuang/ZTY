package com.dovar.dtoast.inner;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import com.dovar.dtoast.DUtil;
import com.dovar.dtoast.R;

public class DovaToast implements IToast, Cloneable {
    static long Count4BadTokenException;
    private View contentView;
    boolean isShowing;
    Context mContext;
    private int priority;
    private long timestamp;
    private int xOffset;
    private int yOffset;
    private int animation = 16973828;
    private int gravity = 81;
    private int width = -2;
    private int height = -2;
    private int duration = 2000;

    public DovaToast(Context context) {
        this.mContext = context;
    }

    private View assertContentViewNotNull() {
        if (this.contentView == null) {
            this.contentView = View.inflate(this.mContext, R.layout.layout_toast, null);
        }
        return this.contentView;
    }

    public static void cancelActivityToast(Activity activity) {
        DovaTN.instance().cancelActivityToast(activity);
    }

    public static void cancelAll() {
        DovaTN.instance().cancelAll();
    }

    public static boolean isBadChoice() {
        return Count4BadTokenException >= 5;
    }

    @Override
    public void cancel() {
        DovaTN.instance().cancelAll();
    }

    public DovaToast m7clone() {
        DovaToast dovaToast;
        CloneNotSupportedException e;
        try {
            dovaToast = (DovaToast) super.clone();
        } catch (CloneNotSupportedException e2) {
            dovaToast = null;
            e = e2;
        }
        try {
            dovaToast.mContext = this.mContext;
            dovaToast.contentView = this.contentView;
            dovaToast.duration = this.duration;
            dovaToast.animation = this.animation;
            dovaToast.gravity = this.gravity;
            dovaToast.height = this.height;
            dovaToast.width = this.width;
            dovaToast.xOffset = this.xOffset;
            dovaToast.yOffset = this.yOffset;
            dovaToast.priority = this.priority;
        } catch (CloneNotSupportedException e3) {
            e = e3;
            e.printStackTrace();
            return dovaToast;
        }
        return dovaToast;
    }

    public Context getContext() {
        return this.mContext;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getGravity() {
        return this.gravity;
    }

    public int getPriority() {
        return this.priority;
    }

    long getTimestamp() {
        return this.timestamp;
    }

    @Override
    public View getView() {
        return assertContentViewNotNull();
    }

    View getViewInternal() {
        return this.contentView;
    }

    protected WindowManager getWMManager() {
        Context context = this.mContext;
        if (context == null) {
            return null;
        }
        return (WindowManager) context.getApplicationContext().getSystemService("window");
    }

    protected WindowManager.LayoutParams getWMParams() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = 8;
        layoutParams.format = -3;
        if (Build.VERSION.SDK_INT < 26 || !Settings.canDrawOverlays(this.mContext)) {
            layoutParams.type = 2005;
        } else {
            layoutParams.type = 2038;
        }
        layoutParams.height = this.height;
        layoutParams.width = this.width;
        layoutParams.windowAnimations = this.animation;
        layoutParams.gravity = this.gravity;
        layoutParams.x = this.xOffset;
        layoutParams.y = this.yOffset;
        return layoutParams;
    }

    public int getXOffset() {
        return this.xOffset;
    }

    public int getYOffset() {
        return this.yOffset;
    }

    boolean isShowing() {
        View view;
        return this.isShowing && (view = this.contentView) != null && view.isShown();
    }

    @Override
    public DovaToast setAnimation(int i) {
        this.animation = i;
        return this;
    }

    @Override
    public DovaToast setDuration(int i) {
        this.duration = i;
        return this;
    }

    @Override
    public DovaToast setGravity(int i) {
        return setGravity(i, 0, 0);
    }

    @Override
    public DovaToast setGravity(int i, int i2, int i3) {
        this.gravity = i;
        this.xOffset = i2;
        this.yOffset = i3;
        return this;
    }

    @Override
    public DovaToast setPriority(int i) {
        this.priority = i;
        return this;
    }

    @Override
    public IToast setText(int i, String str) {
        TextView textView = (TextView) assertContentViewNotNull().findViewById(i);
        if (textView != null) {
            textView.setText(str);
        }
        return this;
    }

    DovaToast setTimestamp(long j) {
        this.timestamp = j;
        return this;
    }

    @Override
    public DovaToast setView(View view) {
        if (view == null) {
            DUtil.log("contentView cannot be null!");
            return this;
        }
        this.contentView = view;
        return this;
    }

    @Override
    public void show() {
        assertContentViewNotNull();
        DovaTN.instance().add(this);
    }

    @Override
    public void showLong() {
        setDuration(3500).show();
    }
}
