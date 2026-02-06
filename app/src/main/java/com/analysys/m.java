package com.analysys;

import android.app.ActivityManager;
import android.os.Process;
import android.text.TextUtils;
import com.analysys.utils.AnalysysUtil;
import com.analysys.utils.ExceptionUtil;
import java.util.Iterator;

public class m {

    private static String f64a;

    public static String a() {
        if (TextUtils.isEmpty(f64a)) {
            try {
                int iMyPid = Process.myPid();
                Iterator<ActivityManager.RunningAppProcessInfo> it = ((ActivityManager) AnalysysUtil.getContext().getApplicationContext().getSystemService("activity")).getRunningAppProcesses().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    ActivityManager.RunningAppProcessInfo next = it.next();
                    if (next.pid == iMyPid) {
                        break;
                    }
                }
            } catch (Throwable th) {
                ExceptionUtil.exceptionThrow(th);
            }
        }
        return f64a;
    }
}
