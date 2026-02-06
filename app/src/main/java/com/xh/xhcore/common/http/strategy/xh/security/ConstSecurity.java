package com.xh.xhcore.common.http.strategy.xh.security;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.xh.xhcore.common.http.archi.MD5Util;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010$\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\n\u001a\n \u000b*\u0004\u0018\u00010\u00040\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0004J\u0014\u0010\u0011\u001a\u00020\u00042\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0013H\u0007J\u0018\u0010\u0014\u001a\u00020\u00042\u000e\u0010\u0015\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0016H\u0007R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/xh/xhcore/common/http/strategy/xh/security/ConstSecurity;", "", "()V", "SECURITY_CLASS_ID", "", "SECURITY_CONDITIONS_KEY", "SECURITY_CONDITION_SIGN_KEY", "SECURITY_IS_TEACHER", "SECURITY_SCHOOL_ID", "SECURITY_USER_ID", "computeUploadSecuritySign", "kotlin.jvm.PlatformType", "securityType", "conditions", "isPrivate", "", "url", "listToJsonStr", "list", "", "mapToJsonStr", "map", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ConstSecurity {
    public static final ConstSecurity INSTANCE = new ConstSecurity();
    public static final String SECURITY_CONDITIONS_KEY = "Conditions";
    public static final String SECURITY_CONDITION_SIGN_KEY = "Cond-Sign";
    public static final String SECURITY_SCHOOL_ID = "schoolId";
    public static final String SECURITY_CLASS_ID = "classId";
    public static final String SECURITY_USER_ID = "userId";
    public static final String SECURITY_IS_TEACHER = "isTeacher";

    private ConstSecurity() {
    }

    @JvmStatic
    public static final String listToJsonStr(List<?> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        String json = new Gson().toJson(list);
        Intrinsics.checkNotNullExpressionValue(json, "Gson().toJson(list)");
        return json;
    }

    @JvmStatic
    public static final String mapToJsonStr(Map<?, ?> map) {
        Intrinsics.checkNotNullParameter(map, "map");
        String json = new Gson().toJson(map);
        Intrinsics.checkNotNullExpressionValue(json, "Gson().toJson(map)");
        return json;
    }

    public final String computeUploadSecuritySign(String securityType, String conditions) {
        Intrinsics.checkNotNullParameter(securityType, "securityType");
        Intrinsics.checkNotNullParameter(conditions, "conditions");
        return MD5Util.encode(StringsKt.reversed((CharSequence) (securityType + ';' + conditions)).toString());
    }

    public final boolean isPrivate(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        return !TextUtils.isEmpty(url) && StringsKt.endsWith$default(url, "!", false, 2, (Object) null);
    }
}
