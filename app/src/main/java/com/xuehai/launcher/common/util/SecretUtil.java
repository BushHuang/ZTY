package com.xuehai.launcher.common.util;

import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H\u0007¨\u0006\b"}, d2 = {"Lcom/xuehai/launcher/common/util/SecretUtil;", "", "()V", "createRandomText", "", "length", "", "scope", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SecretUtil {
    public static final SecretUtil INSTANCE = new SecretUtil();

    private SecretUtil() {
    }

    @JvmStatic
    public static final String createRandomText(int length) {
        return createRandomText(length, "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
    }

    @JvmStatic
    public static final String createRandomText(int length, String scope) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        char[] charArray = scope.toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "this as java.lang.String).toCharArray()");
        Random random = new Random();
        String str = "";
        for (int i = 0; i < length; i++) {
            str = str + charArray[random.nextInt(charArray.length)];
        }
        return str;
    }
}
