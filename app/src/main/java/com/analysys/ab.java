package com.analysys;

import android.text.TextUtils;
import com.analysys.utils.AnalysysUtil;
import com.analysys.utils.CommonUtils;
import com.analysys.utils.SharedUtil;
import java.util.UUID;

public class ab {
    public static String a() {
        String idFile = CommonUtils.getIdFile(AnalysysUtil.getContext(), "aliasId");
        if (TextUtils.isEmpty(idFile)) {
            return SharedUtil.getString(AnalysysUtil.getContext(), "aliasId", "");
        }
        SharedUtil.setString(AnalysysUtil.getContext(), "aliasId", idFile);
        CommonUtils.setIdFile(AnalysysUtil.getContext(), "aliasId", "");
        return idFile;
    }

    public static void a(String str) {
        if (TextUtils.isEmpty(str)) {
            SharedUtil.remove(AnalysysUtil.getContext(), "aliasId");
        } else {
            SharedUtil.setString(AnalysysUtil.getContext(), "aliasId", str);
        }
        CommonUtils.setIdFile(AnalysysUtil.getContext(), "aliasId", "");
    }

    public static String b() {
        String strH = h();
        return TextUtils.isEmpty(strH) ? i() : strH;
    }

    public static void b(String str) {
        if (TextUtils.isEmpty(str)) {
            SharedUtil.remove(AnalysysUtil.getContext(), "distinctId");
        } else {
            SharedUtil.setString(AnalysysUtil.getContext(), "distinctId", str);
        }
        CommonUtils.setIdFile(AnalysysUtil.getContext(), "distinctId", "");
    }

    public static String c() {
        String strA = a();
        if (!TextUtils.isEmpty(strA)) {
            return strA;
        }
        String strH = h();
        return TextUtils.isEmpty(strH) ? i() : strH;
    }

    public static void c(String str) {
        if (TextUtils.isEmpty(str)) {
            SharedUtil.remove(AnalysysUtil.getContext(), "adid");
        } else {
            SharedUtil.setString(AnalysysUtil.getContext(), "adid", str);
        }
    }

    @Deprecated
    public static void d(String str) {
        if (TextUtils.isEmpty(str)) {
            SharedUtil.remove(AnalysysUtil.getContext(), "originalId");
        } else {
            SharedUtil.setString(AnalysysUtil.getContext(), "originalId", str);
        }
    }

    public static boolean d() {
        return !TextUtils.isEmpty(a());
    }

    public static void e() {
        SharedUtil.remove(AnalysysUtil.getContext(), "aliasId");
        SharedUtil.remove(AnalysysUtil.getContext(), "distinctId");
        SharedUtil.remove(AnalysysUtil.getContext(), "uuid");
        CommonUtils.setIdFile(AnalysysUtil.getContext(), "aliasId", "");
        CommonUtils.setIdFile(AnalysysUtil.getContext(), "distinctId", "");
        CommonUtils.setIdFile(AnalysysUtil.getContext(), "uuid", "");
        SharedUtil.remove(AnalysysUtil.getContext(), "originalId");
    }

    public static String f() {
        return SharedUtil.getString(AnalysysUtil.getContext(), "adid", null);
    }

    public static String g() {
        String strF = f();
        if (!TextUtils.isEmpty(strF)) {
            return strF;
        }
        String androidID = CommonUtils.getAndroidID(AnalysysUtil.getContext());
        return TextUtils.isEmpty(androidID) ? i() : androidID;
    }

    private static String h() {
        String idFile = CommonUtils.getIdFile(AnalysysUtil.getContext(), "distinctId");
        if (TextUtils.isEmpty(idFile)) {
            return SharedUtil.getString(AnalysysUtil.getContext(), "distinctId", "");
        }
        SharedUtil.setString(AnalysysUtil.getContext(), "distinctId", idFile);
        CommonUtils.setIdFile(AnalysysUtil.getContext(), "distinctId", "");
        return idFile;
    }

    private static String i() {
        String idFile = CommonUtils.getIdFile(AnalysysUtil.getContext(), "uuid");
        if (!TextUtils.isEmpty(idFile)) {
            SharedUtil.setString(AnalysysUtil.getContext(), "uuid", idFile);
            CommonUtils.setIdFile(AnalysysUtil.getContext(), "uuid", "");
            return idFile;
        }
        String string = SharedUtil.getString(AnalysysUtil.getContext(), "uuid", "");
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        String strValueOf = String.valueOf(UUID.randomUUID());
        SharedUtil.setString(AnalysysUtil.getContext(), "uuid", strValueOf);
        return strValueOf;
    }
}
