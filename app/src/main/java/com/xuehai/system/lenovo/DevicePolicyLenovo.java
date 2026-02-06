package com.xuehai.system.lenovo;

import android.content.Context;
import com.xuehai.system.common.compat.DevicePolicyDefault;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u00020\tH\u0002J\b\u0010\n\u001a\u00020\tH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/xuehai/system/lenovo/DevicePolicyLenovo;", "Lcom/xuehai/system/common/compat/DevicePolicyDefault;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "PN_LENGTH", "", "SN_LENGTH", "getSN", "", "getSerialNumber", "mdm_lenovo_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DevicePolicyLenovo extends DevicePolicyDefault {
    private final int PN_LENGTH;
    private final int SN_LENGTH;

    public DevicePolicyLenovo(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.SN_LENGTH = 8;
        this.PN_LENGTH = 23;
    }

    private final String getSN() throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, SecurityException, IllegalArgumentException, InvocationTargetException {
        String str;
        Object objInvoke;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Intrinsics.checkNotNullExpressionValue(cls, "forName(\"android.os.SystemProperties\")");
            Method method = cls.getMethod("get", String.class);
            Intrinsics.checkNotNullExpressionValue(method, "c.getMethod(\"get\", String::class.java)");
            objInvoke = method.invoke(cls, "ro.serialno");
        } catch (Exception e) {
            e.printStackTrace();
            str = null;
        }
        if (objInvoke == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        str = (String) objInvoke;
        return str == null ? "" : str;
    }

    @Override
    public String getSerialNumber() throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, SecurityException, IllegalArgumentException, InvocationTargetException {
        String sn = getSN();
        int length = sn.length();
        int i = this.SN_LENGTH;
        if (length < i) {
            return super.getSerialNumber();
        }
        String strSubstring = sn.substring(0, i);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }
}
