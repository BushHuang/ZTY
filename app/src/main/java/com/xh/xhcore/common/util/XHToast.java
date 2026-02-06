package com.xh.xhcore.common.util;

import com.xh.view.ToastUtil;
import com.xh.xhcore.common.base.XhBaseApplication;

@Deprecated
public class XHToast {
    private XHToast() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void show(int i, int i2) {
        ToastUtil.showToast(XhBaseApplication.mContext, i, i2);
    }

    public static void show(CharSequence charSequence, int i) {
        ToastUtil.showToast(XhBaseApplication.mContext, charSequence, i);
    }

    public static void showLong(int i) {
        ToastUtil.showToast(XhBaseApplication.mContext, i, 1);
    }

    public static void showLong(CharSequence charSequence) {
        ToastUtil.showToast(XhBaseApplication.mContext, charSequence, 1);
    }

    public static void showShort(int i) {
        ToastUtil.showToast(XhBaseApplication.mContext, XhBaseApplication.mContext.getResources().getText(i).toString(), 0);
    }

    public static void showShort(CharSequence charSequence) {
        ToastUtil.showToast(XhBaseApplication.mContext, charSequence.toString(), 0);
    }
}
