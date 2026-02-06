package com.tencent.tinker.lib.util;

import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.os.Process;
import android.util.Log;
import com.tencent.tinker.lib.service.TinkerPatchService;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import java.util.Iterator;
import java.util.List;

public class TinkerServiceInternals extends ShareTinkerInternals {
    private static final String TAG = "Tinker.ServiceInternals";
    private static String patchServiceProcessName;

    private static String getServiceProcessName(Context context, Class<? extends Service> cls) {
        try {
            return context.getPackageManager().getServiceInfo(new ComponentName(context, cls), 0).processName;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String getTinkerPatchServiceName(Context context) {
        String str = patchServiceProcessName;
        if (str != null) {
            return str;
        }
        String serviceProcessName = getServiceProcessName(context, TinkerPatchService.class);
        if (serviceProcessName == null) {
            return null;
        }
        patchServiceProcessName = serviceProcessName;
        return serviceProcessName;
    }

    public static boolean isInTinkerPatchServiceProcess(Context context) {
        String processName = getProcessName(context);
        String tinkerPatchServiceName = getTinkerPatchServiceName(context);
        if (tinkerPatchServiceName == null || tinkerPatchServiceName.length() == 0) {
            return false;
        }
        return processName.equals(tinkerPatchServiceName);
    }

    public static boolean isTinkerPatchServiceRunning(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        String tinkerPatchServiceName = getTinkerPatchServiceName(context);
        if (tinkerPatchServiceName == null) {
            return false;
        }
        try {
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses == null) {
                return false;
            }
            Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
            while (it.hasNext()) {
                if (it.next().processName.equals(tinkerPatchServiceName)) {
                    return true;
                }
            }
            return false;
        } catch (Error e) {
            Log.e("Tinker.ServiceInternals", "isTinkerPatchServiceRunning Error: " + e.toString());
            return false;
        } catch (Exception e2) {
            Log.e("Tinker.ServiceInternals", "isTinkerPatchServiceRunning Exception: " + e2.toString());
            return false;
        }
    }

    public static void killTinkerPatchServiceProcess(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        String tinkerPatchServiceName = getTinkerPatchServiceName(context);
        if (tinkerPatchServiceName == null || (runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) == null) {
            return;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.processName.equals(tinkerPatchServiceName)) {
                Process.killProcess(runningAppProcessInfo.pid);
            }
        }
    }
}
