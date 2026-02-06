package com.kwai.koom.javaoom.test;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.kwai.koom.javaoom.KOOM;
import com.kwai.koom.javaoom.common.KConstants;
import com.kwai.koom.javaoom.test.leaked.LeakMaker;
import com.xh.xhcore.common.http.strategy.LogUtils;

public class KOOMTestUtil {
    static void lambda$leakAndCreateOOM$0(int i, int[] iArr, Context context) {
        while (true) {
            int i2 = 0;
            while (i2 < (i * 5) - iArr[0]) {
                LeakMaker.makeLeak(context);
                i2 += i;
            }
            LeakMaker.makeLeak(context);
            iArr[0] = iArr[0] + 1;
        }
    }

    public static void leakAndCreateOOM(final Context context) {
        LogUtils.d("onClick is called");
        final int iMaxMemory = ((int) (Runtime.getRuntime().maxMemory() / KConstants.Bytes.MB)) / 100;
        LeakMaker.makeLeak(context);
        final int[] iArr = {1};
        new Thread(new Runnable() {
            @Override
            public final void run() {
                KOOMTestUtil.lambda$leakAndCreateOOM$0(iMaxMemory, iArr, context);
            }
        }).start();
    }

    public static void leakAndReport(Context context) {
        LeakMaker.makeLeak(context);
        testReport();
    }

    public static void testReport() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public final void run() {
                KOOM.getInstance().manualTrigger();
            }
        }, 1500L);
    }
}
