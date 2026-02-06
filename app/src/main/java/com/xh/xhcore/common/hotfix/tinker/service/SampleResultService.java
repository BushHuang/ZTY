package com.xh.xhcore.common.hotfix.tinker.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.tencent.tinker.lib.service.DefaultTinkerResultService;
import com.tencent.tinker.lib.service.PatchResult;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.lib.util.TinkerServiceInternals;
import com.xh.xhcore.common.hotfix.tinker.util.Utils;
import com.xh.xhcore.common.hotfix.tinkerpatch.ReportBroadCast;
import com.xh.xhcore.common.util.XHAppUtil;
import java.io.File;

public class SampleResultService extends DefaultTinkerResultService {
    private static final String TAG = "Tinker.SampleResultService";
    public static boolean patchRestartOnScreenOff = false;

    public static void restartProcess(Context context) {
        TinkerLog.i("Tinker.SampleResultService", "app is background now, i can kill quietly", new Object[0]);
        Intent intent = new Intent(context, (Class<?>) ReportBroadCast.class);
        intent.putExtra("tinkerpatch_intent_patch_restart", true);
        TinkerLog.i("Tinker.TinkerServerResultService", "app is background now, send broadcast to restart", new Object[0]);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intent, 268435456);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        if (alarmManager != null) {
            if (Build.VERSION.SDK_INT >= 19) {
                try {
                    alarmManager.setExact(1, System.currentTimeMillis() + 1000, broadcast);
                } catch (Throwable unused) {
                    alarmManager.set(1, System.currentTimeMillis() + 1000, broadcast);
                }
            } else {
                alarmManager.set(1, System.currentTimeMillis() + 1000, broadcast);
            }
        }
        TinkerLog.i("Tinker.TinkerServerResultService", "app is background now, kill process now", new Object[0]);
        XHAppUtil.killAllProcess();
    }

    public void lambda$onPatchResult$0$SampleResultService() {
        restartProcess(getApplicationContext());
    }

    @Override
    public void onPatchResult(PatchResult patchResult) {
        if (patchResult == null) {
            TinkerLog.e("Tinker.SampleResultService", "SampleResultService received null result!!!!", new Object[0]);
            return;
        }
        TinkerLog.i("Tinker.SampleResultService", "SampleResultService receive result: %s", patchResult.toString());
        TinkerServiceInternals.killTinkerPatchServiceProcess(getApplicationContext());
        if (patchResult.isSuccess) {
            deleteRawPatchFile(new File(patchResult.rawPatchFilePath));
            if (!checkIfNeedKill(patchResult)) {
                TinkerLog.i("Tinker.SampleResultService", "I have already install the newly patch version!", new Object[0]);
                return;
            }
            if (Utils.isBackground()) {
                TinkerLog.i("Tinker.SampleResultService", "it is in background, just restart process", new Object[0]);
                restartProcess(getApplicationContext());
            } else if (patchRestartOnScreenOff) {
                TinkerLog.i("Tinker.SampleResultService", "tinker wait screen to restart process", new Object[0]);
                new Utils.ScreenState(getApplicationContext(), new Utils.ScreenState.IOnScreenOff() {
                    @Override
                    public final void onScreenOff() {
                        this.f$0.lambda$onPatchResult$0$SampleResultService();
                    }
                });
            }
        }
    }
}
