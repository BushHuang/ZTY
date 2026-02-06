package com.xuehai.launcher.common.util;

import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\u00062\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000bR.\u0010\u0003\u001a\"\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004j\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/xuehai/launcher/common/util/RequestCodeBuilder;", "", "()V", "codeMap", "Ljava/util/HashMap;", "", "", "Lkotlin/collections/HashMap;", "nextCode", "get", "clazz", "Ljava/lang/Class;", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RequestCodeBuilder {
    public static final RequestCodeBuilder INSTANCE = new RequestCodeBuilder();
    private static int nextCode = 10;
    private static final HashMap<String, Integer> codeMap = new HashMap<>();

    private RequestCodeBuilder() {
    }

    public final int get(Class<?> clazz) {
        Integer numValueOf;
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        synchronized (codeMap) {
            String name = clazz.getName();
            Intrinsics.checkNotNullExpressionValue(name, "clazz.name");
            numValueOf = codeMap.get(name);
            if (numValueOf == null) {
                codeMap.put(name, Integer.valueOf(nextCode));
                int i = nextCode;
                nextCode = i + 1;
                numValueOf = Integer.valueOf(i);
            }
        }
        Intrinsics.checkNotNullExpressionValue(numValueOf, "synchronized(codeMap) {\n…+\n            }\n        }");
        return numValueOf.intValue();
    }
}
