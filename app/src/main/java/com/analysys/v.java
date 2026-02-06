package com.analysys;

import android.content.Context;
import android.text.TextUtils;
import com.analysys.utils.CommonUtils;
import com.analysys.utils.ExceptionUtil;
import com.analysys.utils.SharedUtil;
import java.security.MessageDigest;
import java.util.Random;

public class v {

    private Context f81a = null;
    private String b = "";

    static class a {

        public static final v f82a = new v();
    }

    public static v a(Context context) {
        if (a.f82a.f81a == null && context != null) {
            a.f82a.f81a = context.getApplicationContext();
        }
        return a.f82a;
    }

    private String a(String str) {
        byte[] bArrDigest;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            bArrDigest = messageDigest.digest();
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
            bArrDigest = null;
        }
        return a(bArrDigest);
    }

    private String a(byte[] bArr) {
        if (bArr == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            int i2 = bArr[i];
            if (i2 < 0) {
                i2 += 256;
            }
            if (i2 < 16) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(i2));
        }
        return sb.toString().substring(8, 24);
    }

    private boolean b() {
        if (CommonUtils.isEmpty(this.b)) {
            String string = SharedUtil.getString(this.f81a, "startDay", "");
            this.b = string;
            if (CommonUtils.isEmpty(string)) {
                c();
                return false;
            }
        }
        if (CommonUtils.getDay().equals(this.b)) {
            return false;
        }
        c();
        return true;
    }

    private boolean b(Context context) {
        if (context == null) {
            return true;
        }
        String string = SharedUtil.getString(context, "pageEndTime", "");
        if (TextUtils.isEmpty(string)) {
            return true;
        }
        return System.currentTimeMillis() - CommonUtils.parseLong(string, 0L) >= 30000;
    }

    private void c() {
        String day = CommonUtils.getDay();
        this.b = day;
        SharedUtil.setString(this.f81a, "startDay", day);
    }

    private void d() {
        SharedUtil.setString(this.f81a, "getSessionId", e());
    }

    private String e() {
        return a(System.currentTimeMillis() + "Android" + String.valueOf(new Random().nextInt(1000000)));
    }

    public synchronized String a() {
        return SharedUtil.getString(this.f81a, "getSessionId", "");
    }

    public synchronized void a(boolean z) {
        if (b()) {
            d();
            return;
        }
        if (z) {
            d();
        } else if (CommonUtils.isEmpty(a())) {
            d();
        } else {
            if (b(this.f81a)) {
                d();
            }
        }
    }
}
