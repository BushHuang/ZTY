package com.xuehai.launcher.common.crack;

import java.io.File;
import kotlin.Metadata;
import kotlin.Pair;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\bH\u0002J\u0006\u0010\n\u001a\u00020\bR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f"}, d2 = {"Lcom/xuehai/launcher/common/crack/RootChecker;", "", "()V", "result", "Lcom/xuehai/launcher/common/crack/CheckResult;", "getResult", "()Lcom/xuehai/launcher/common/crack/CheckResult;", "detectByCmd", "", "detectByFile", "detectRoot", "Companion", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RootChecker {
    public static final String TAG = "RootChecker";
    private final CheckResult result = new CheckResult();

    private final void detectByCmd() {
        Pair<Integer, StringBuilder> pairExec = ExecUtil.INSTANCE.exec(new String[]{"su"});
        if (pairExec.getFirst().intValue() == ExecUtil.INSTANCE.getSUCCESS()) {
            this.result.addError("RootChecker hit detectByCmd: su " + ((Object) pairExec.getSecond()) + ' ');
        }
    }

    private final void detectByFile() {
        String[] strArr = {"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        for (int i = 0; i < 5; i++) {
            String str = strArr[i];
            if (new File(str + "su").exists()) {
                this.result.addError("RootChecker hit detectByFile: " + str + "su");
            }
        }
    }

    public final void detectRoot() {
        detectByFile();
        detectByCmd();
    }

    public final CheckResult getResult() {
        return this.result;
    }
}
