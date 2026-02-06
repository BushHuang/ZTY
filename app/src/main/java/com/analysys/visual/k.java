package com.analysys.visual;

import android.view.View;
import com.analysys.utils.ExceptionUtil;

public abstract class k extends i {
    private final int q;

    k(String str, int i) {
        super(str);
        this.q = i;
    }

    public abstract View.AccessibilityDelegate a(int i, View view);

    @Override
    public void a(Object... objArr) {
        if (objArr == null || objArr.length < 2 || !c()) {
            return;
        }
        if (((Integer) objArr[1]).intValue() == this.q) {
            final View view = (View) objArr[0];
            final boolean z = objArr.length >= 3 && ((Boolean) objArr[2]).booleanValue();
            b.a().a(new Runnable() {
                @Override
                public void run() {
                    try {
                        k.this.a(view, z);
                    } catch (Throwable th) {
                        ExceptionUtil.exceptionThrow(th);
                    }
                }
            });
        }
    }

    public int g() {
        return this.q;
    }
}
