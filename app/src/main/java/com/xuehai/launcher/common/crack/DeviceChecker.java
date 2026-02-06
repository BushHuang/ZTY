package com.xuehai.launcher.common.crack;

import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.launcher.common.plugins.ThreadPlugins;
import com.xuehai.launcher.common.util.LauncherSPUtil;
import com.zaze.utils.JsonUtil;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/xuehai/launcher/common/crack/DeviceChecker;", "", "()V", "TAG", "", "isDeviceSafely", "", "latelyHitKey", "detectSafely", "", "force", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DeviceChecker {
    private static final String TAG = "DeviceChecker";
    private static final String latelyHitKey = "lately_hit_key";
    public static final DeviceChecker INSTANCE = new DeviceChecker();
    private static boolean isDeviceSafely = true;

    private DeviceChecker() {
    }

    public static void detectSafely$default(DeviceChecker deviceChecker, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        deviceChecker.detectSafely(z);
    }

    private static final void m55detectSafely$lambda5() throws InterruptedException {
        StringBuilder sb = new StringBuilder();
        final XposedChecker xposedChecker = new XposedChecker();
        xposedChecker.detectByClassLoader();
        xposedChecker.detectByMaps();
        QemuChecker qemuChecker = new QemuChecker();
        qemuChecker.detectEmulator();
        RootChecker rootChecker = new RootChecker();
        rootChecker.detectRoot();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ThreadPlugins.runInUIThread$default(new Runnable() {
            @Override
            public final void run() {
                DeviceChecker.m56detectSafely$lambda5$lambda3(xposedChecker, countDownLatch);
            }
        }, 0L, 2, null);
        countDownLatch.await(10000L, TimeUnit.SECONDS);
        sb.append("Emulator " + qemuChecker.getResult().isError() + " \n");
        sb.append("Root " + rootChecker.getResult().isError() + "  \n");
        sb.append("Xposed " + xposedChecker.getResult().isError() + "  \n");
        sb.append((CharSequence) qemuChecker.getResult().getMessageBuilder());
        sb.append((CharSequence) rootChecker.getResult().getMessageBuilder());
        sb.append((CharSequence) xposedChecker.getResult().getMessageBuilder());
        MyLog.i("DeviceChecker", "check messages: " + ((Object) sb));
        boolean z = qemuChecker.getResult().isSafely() && rootChecker.getResult().isSafely() && xposedChecker.getResult().isSafely();
        isDeviceSafely = z;
        if (z) {
            MyLog.i("DeviceChecker", "device is Safely");
            return;
        }
        if (Math.abs(System.currentTimeMillis() - LauncherSPUtil.getUpdateTimeByKey("lately_hit_key")) < 86400000) {
            MyLog.i("DeviceChecker", "一天内已上报到过异常！！不必重新上报");
            return;
        }
        HashMap<String, String> allProp = qemuChecker.getAllProp();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, String> entry : allProp.entrySet()) {
            if ((StringsKt.startsWith$default(entry.getKey(), "init.", false, 2, (Object) null) || StringsKt.startsWith$default(entry.getKey(), "iwc.", false, 2, (Object) null) || StringsKt.startsWith$default(entry.getKey(), "persist.", false, 2, (Object) null) || StringsKt.startsWith$default(entry.getKey(), "service.", false, 2, (Object) null) || StringsKt.startsWith$default(entry.getKey(), "sys.", false, 2, (Object) null) || StringsKt.startsWith$default(entry.getKey(), "gsm.", false, 2, (Object) null) || StringsKt.startsWith$default(entry.getKey(), "debug.", false, 2, (Object) null) || StringsKt.startsWith$default(entry.getKey(), "dev.", false, 2, (Object) null) || StringsKt.startsWith$default(entry.getKey(), "vold.", false, 2, (Object) null) || StringsKt.startsWith$default(entry.getKey(), "ro.security.", false, 2, (Object) null) || StringsKt.startsWith$default(entry.getKey(), "net.", false, 2, (Object) null) || StringsKt.startsWith$default(entry.getKey(), "pm.dexopt.", false, 2, (Object) null) || StringsKt.startsWith$default(entry.getKey(), "dumpstate.", false, 2, (Object) null) || StringsKt.startsWith$default(entry.getKey(), "ro.cfg.", false, 2, (Object) null) || StringsKt.startsWith$default(entry.getKey(), "wifi.", false, 2, (Object) null) || StringsKt.startsWith$default(entry.getKey(), "dalvik.vm.", false, 2, (Object) null) || StringsKt.startsWith$default(entry.getKey(), "ro.config.dha_", false, 2, (Object) null) || StringsKt.startsWith$default(entry.getKey(), "security.", false, 2, (Object) null)) ? false : true) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        Map map = MapsKt.toMap(linkedHashMap);
        sb.append("---- prop ---\n");
        sb.append(String.valueOf(JsonUtil.mapToJson(map)));
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "messageBuilder.toString()");
        MyLog.saveAppErrorLog(string);
        LauncherSPUtil.setUpdateTimeByKey("lately_hit_key");
    }

    private static final void m56detectSafely$lambda5$lambda3(XposedChecker xposedChecker, CountDownLatch countDownLatch) {
        Intrinsics.checkNotNullParameter(xposedChecker, "$xposedChecker");
        Intrinsics.checkNotNullParameter(countDownLatch, "$countDownLatch");
        xposedChecker.detectByStackTrace();
        countDownLatch.countDown();
    }

    public final void detectSafely(boolean force) {
        if (force || isDeviceSafely) {
            ThreadPlugins.INSTANCE.getMultiExecutorStub().execute(new Runnable() {
                @Override
                public final void run() throws InterruptedException {
                    DeviceChecker.m55detectSafely$lambda5();
                }
            });
        } else {
            MyLog.w("DeviceChecker", "当前设备已被检测到不安全，不必重新检测！！");
        }
    }
}
