package com.analysys;

import com.analysys.process.AgentProcess;
import com.analysys.utils.ANSLog;

public class ag {
    public static boolean a(String str) {
        if (AgentProcess.getInstance().isInited()) {
            return true;
        }
        ANSLog.e("The SDK is not initialized, please call " + str + " after initialization");
        return false;
    }
}
