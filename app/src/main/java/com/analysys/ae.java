package com.analysys;

import com.analysys.utils.ExceptionUtil;
import java.io.Closeable;

public final class ae {
    public static void a(Closeable... closeableArr) {
        if (closeableArr == null) {
            return;
        }
        for (Closeable closeable : closeableArr) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        }
    }
}
