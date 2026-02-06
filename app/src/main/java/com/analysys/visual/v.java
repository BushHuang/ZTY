package com.analysys.visual;

import android.view.View;
import com.analysys.utils.ExceptionUtil;

public class v extends u {
    public v(String str, String str2, String str3, ak akVar, Object obj, String str4) {
        super(str, str2, str3, akVar, obj, str4);
    }

    @Override
    protected Object a(View view) {
        return view.getClass().getName();
    }

    @Override
    public boolean a(Object obj) {
        Object objB = b();
        if (!(objB instanceof String)) {
            return false;
        }
        String str = (String) objB;
        if (!str.startsWith(">")) {
            return super.a(obj);
        }
        if (obj == null) {
            return false;
        }
        try {
            return Class.forName(str.substring(1)).isAssignableFrom(obj.getClass());
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
            return false;
        }
    }
}
