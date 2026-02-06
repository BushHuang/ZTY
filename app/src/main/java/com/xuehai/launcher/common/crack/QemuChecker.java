package com.xuehai.launcher.common.crack;

import com.xuehai.launcher.common.log.MyLog;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J,\u0010\r\u001a\u00020\u000e2\"\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005`\u0006H\u0002J\u0006\u0010\u000f\u001a\u00020\u000eJ,\u0010\u0010\u001a\u00020\u000e2\"\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005`\u0006H\u0002J\"\u0010\u0011\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005`\u0006R-\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0013"}, d2 = {"Lcom/xuehai/launcher/common/crack/QemuChecker;", "", "()V", "propMap", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "getPropMap", "()Ljava/util/HashMap;", "result", "Lcom/xuehai/launcher/common/crack/CheckResult;", "getResult", "()Lcom/xuehai/launcher/common/crack/CheckResult;", "checkFingerprintMatched", "", "detectEmulator", "detectEmulatorByProp", "getAllProp", "Companion", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class QemuChecker {
    private static final String TAG = "QemuChecker";
    private final CheckResult result = new CheckResult();
    private final HashMap<String, String> propMap = new HashMap<>();

    private final void checkFingerprintMatched(HashMap<String, String> propMap) {
        List listSplit$default;
        String str = propMap.get("ro.build.fingerprint");
        MyLog.d("QemuChecker", "ro.build.fingerprint : " + str);
        boolean z = false;
        if (str != null && (listSplit$default = StringsKt.split$default((CharSequence) str, new String[]{"/"}, false, 0, 6, (Object) null)) != null) {
            String str2 = propMap.get("ro.product.brand");
            if (str2 == null) {
                str2 = "";
            }
            if (listSplit$default.contains(str2)) {
                String str3 = propMap.get("ro.product.name");
                if (str3 == null) {
                    str3 = "";
                }
                if (listSplit$default.contains(str3)) {
                    if (listSplit$default.contains(propMap.get("ro.product.device") + ':' + propMap.get("ro.build.version.release"))) {
                        String str4 = propMap.get("ro.build.id");
                        if (str4 == null) {
                            str4 = "";
                        }
                        if (listSplit$default.contains(str4)) {
                            String str5 = propMap.get("ro.build.tags");
                            if (listSplit$default.contains(str5 != null ? str5 : "")) {
                                z = true;
                            }
                        }
                    }
                }
            }
        }
        if (z) {
            return;
        }
        this.result.addError("QemuChecker hit >> prop is not matched: " + str);
    }

    private final void detectEmulatorByProp(HashMap<String, String> propMap) {
        QemuChecker$detectEmulatorByProp$matchKv$1 qemuChecker$detectEmulatorByProp$matchKv$1 = new Function2<String, String, Boolean>() {
            @Override
            public final Boolean invoke(String str, String str2) {
                Intrinsics.checkNotNullParameter(str, "key");
                boolean zAreEqual = true;
                switch (str.hashCode()) {
                    case -186769550:
                        if (!str.equals("ro.product.cpu.abi")) {
                            zAreEqual = false;
                            break;
                        } else {
                            zAreEqual = Intrinsics.areEqual("x86", str2);
                            break;
                        }
                    case -40165511:
                        if (!str.equals("ro.product.model") || str2 == null || (!StringsKt.contains$default((CharSequence) str2, (CharSequence) "Android SDK", false, 2, (Object) null) && !Intrinsics.areEqual(str2, "sdk"))) {
                        }
                        break;
                    case 784020458:
                        if (!str.equals("ro.build.tags") || Intrinsics.areEqual("release-keys", str2)) {
                        }
                        break;
                    case 1093157788:
                        if (str.equals("ro.kernel.qemu")) {
                            zAreEqual = Intrinsics.areEqual("1", str2);
                            break;
                        }
                        break;
                }
                return Boolean.valueOf(zAreEqual);
            }
        };
        Set<String> setKeySet = propMap.keySet();
        Intrinsics.checkNotNullExpressionValue(setKeySet, "propMap.keys");
        for (String str : setKeySet) {
            String str2 = propMap.get(str);
            Intrinsics.checkNotNullExpressionValue(str, "key");
            if (qemuChecker$detectEmulatorByProp$matchKv$1.invoke((QemuChecker$detectEmulatorByProp$matchKv$1) str, str2).booleanValue()) {
                this.result.addError("QemuChecker hit " + str + ": " + str2);
            }
        }
    }

    public final void detectEmulator() {
        this.propMap.clear();
        this.propMap.putAll(getAllProp());
        detectEmulatorByProp(this.propMap);
        checkFingerprintMatched(this.propMap);
    }

    public final HashMap<String, String> getAllProp() {
        HashMap<String, String> map = new HashMap<>();
        String string = ExecUtil.INSTANCE.exec(new String[]{"getprop"}).getSecond().toString();
        Intrinsics.checkNotNullExpressionValue(string, "ExecUtil.exec(arrayOf(\"g…prop\")).second.toString()");
        Iterator it = StringsKt.split$default((CharSequence) StringsKt.replace$default(StringsKt.replace$default(string, "[", "", false, 4, (Object) null), "]", "", false, 4, (Object) null), new String[]{"\n"}, false, 0, 6, (Object) null).iterator();
        while (it.hasNext()) {
            List listSplit$default = StringsKt.split$default((CharSequence) it.next(), new String[]{": "}, false, 0, 6, (Object) null);
            if (listSplit$default.size() >= 2) {
                map.put(listSplit$default.get(0), listSplit$default.get(1));
            }
        }
        return map;
    }

    public final HashMap<String, String> getPropMap() {
        return this.propMap;
    }

    public final CheckResult getResult() {
        return this.result;
    }
}
