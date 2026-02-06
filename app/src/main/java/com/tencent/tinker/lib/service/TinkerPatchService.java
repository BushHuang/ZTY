package com.tencent.tinker.lib.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import com.tencent.tinker.lib.patch.AbstractPatch;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.TinkerRuntimeException;
import com.tencent.tinker.loader.shareutil.ShareIntentUtil;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

public class TinkerPatchService extends IntentService {
    private static final String PATCH_PATH_EXTRA = "patch_path_extra";
    private static final String RESULT_CLASS_EXTRA = "patch_result_class";
    private static final String TAG = "Tinker.TinkerPatchService";
    private static int notificationId = -1119860829;
    private static Class<? extends AbstractResultService> resultServiceClass;
    private static AtomicBoolean sIsPatchApplying = new AtomicBoolean(false);
    private static AbstractPatch upgradePatchProcessor;

    public static class InnerService extends Service {
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public void onCreate() {
            super.onCreate();
            try {
                startForeground(TinkerPatchService.notificationId, new Notification());
            } catch (Throwable th) {
                TinkerLog.e("Tinker.TinkerPatchService", "InnerService set service for push exception:%s.", th);
            }
            stopSelf();
        }

        @Override
        public void onDestroy() {
            stopForeground(true);
            super.onDestroy();
        }
    }

    public TinkerPatchService() {
        super("TinkerPatchService");
    }

    private static void doApplyPatch(Context context, Intent intent) {
        boolean zTryPatch;
        if (!sIsPatchApplying.compareAndSet(false, true)) {
            TinkerLog.w("Tinker.TinkerPatchService", "TinkerPatchService doApplyPatch is running by another runner.", new Object[0]);
            return;
        }
        Tinker tinkerWith = Tinker.with(context);
        tinkerWith.getPatchReporter().onPatchServiceStart(intent);
        if (intent == null) {
            TinkerLog.e("Tinker.TinkerPatchService", "TinkerPatchService received a null intent, ignoring.", new Object[0]);
            return;
        }
        String patchPathExtra = getPatchPathExtra(intent);
        if (patchPathExtra == null) {
            TinkerLog.e("Tinker.TinkerPatchService", "TinkerPatchService can't get the path extra, ignoring.", new Object[0]);
            return;
        }
        File file = new File(patchPathExtra);
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        Throwable th = null;
        PatchResult patchResult = new PatchResult();
        try {
        } catch (Throwable th2) {
            th = th2;
            tinkerWith.getPatchReporter().onPatchException(file, th);
            zTryPatch = false;
        }
        if (upgradePatchProcessor == null) {
            throw new TinkerRuntimeException("upgradePatchProcessor is null.");
        }
        zTryPatch = upgradePatchProcessor.tryPatch(context, patchPathExtra, patchResult);
        long jElapsedRealtime2 = SystemClock.elapsedRealtime() - jElapsedRealtime;
        tinkerWith.getPatchReporter().onPatchResult(file, zTryPatch, jElapsedRealtime2);
        patchResult.isSuccess = zTryPatch;
        patchResult.rawPatchFilePath = patchPathExtra;
        patchResult.costTime = jElapsedRealtime2;
        patchResult.e = th;
        AbstractResultService.runResultService(context, patchResult, getPatchResultExtra(intent));
        sIsPatchApplying.set(false);
    }

    public static String getPatchPathExtra(Intent intent) {
        if (intent != null) {
            return ShareIntentUtil.getStringExtra(intent, "patch_path_extra");
        }
        throw new TinkerRuntimeException("getPatchPathExtra, but intent is null");
    }

    public static String getPatchResultExtra(Intent intent) {
        if (intent != null) {
            return ShareIntentUtil.getStringExtra(intent, "patch_result_class");
        }
        throw new TinkerRuntimeException("getPatchResultExtra, but intent is null");
    }

    private void increasingPriority() {
        if (Build.VERSION.SDK_INT >= 26) {
            TinkerLog.i("Tinker.TinkerPatchService", "for system version >= Android O, we just ignore increasingPriority job to avoid crash or toasts.", new Object[0]);
            return;
        }
        if ("ZUK".equals(Build.MANUFACTURER)) {
            TinkerLog.i("Tinker.TinkerPatchService", "for ZUK device, we just ignore increasingPriority job to avoid crash.", new Object[0]);
            return;
        }
        TinkerLog.i("Tinker.TinkerPatchService", "try to increase patch process priority", new Object[0]);
        try {
            Notification notification = new Notification();
            if (Build.VERSION.SDK_INT < 18) {
                startForeground(notificationId, notification);
            } else {
                startForeground(notificationId, notification);
                startService(new Intent(this, (Class<?>) InnerService.class));
            }
        } catch (Throwable th) {
            TinkerLog.i("Tinker.TinkerPatchService", "try to increase patch process priority error:" + th, new Object[0]);
        }
    }

    public static void runPatchService(Context context, String str) {
        TinkerLog.i("Tinker.TinkerPatchService", "run patch service...", new Object[0]);
        Intent intent = new Intent(context, (Class<?>) TinkerPatchService.class);
        intent.putExtra("patch_path_extra", str);
        intent.putExtra("patch_result_class", resultServiceClass.getName());
        try {
            context.startService(intent);
        } catch (Throwable th) {
            TinkerLog.e("Tinker.TinkerPatchService", "run patch service fail, exception:" + th, new Object[0]);
        }
    }

    public static void setPatchProcessor(AbstractPatch abstractPatch, Class<? extends AbstractResultService> cls) {
        upgradePatchProcessor = abstractPatch;
        resultServiceClass = cls;
        try {
            Class.forName(cls.getName());
        } catch (ClassNotFoundException e) {
            TinkerLog.printErrStackTrace("Tinker.TinkerPatchService", e, "patch processor class not found.", new Object[0]);
        }
    }

    public static void setTinkerNotificationId(int i) {
        notificationId = i;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        increasingPriority();
        doApplyPatch(this, intent);
    }
}
