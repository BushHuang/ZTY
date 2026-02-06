package com.tencent.tinker.lib.listener;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.text.TextUtils;
import com.tencent.tinker.lib.service.TinkerPatchForeService;
import com.tencent.tinker.lib.service.TinkerPatchService;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerLoadResult;
import com.tencent.tinker.lib.util.TinkerServiceInternals;
import com.tencent.tinker.lib.util.UpgradePatchRetry;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.SharePatchInfo;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import java.io.File;

public class DefaultPatchListener implements PatchListener {
    private ServiceConnection connection;
    protected final Context context;

    public DefaultPatchListener(Context context) {
        this.context = context;
    }

    private void runForgService() {
        try {
            this.connection = new ServiceConnection() {
                @Override
                public void onBindingDied(ComponentName componentName) {
                }

                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                }

                @Override
                public void onServiceDisconnected(ComponentName componentName) {
                    if (DefaultPatchListener.this.context == null || DefaultPatchListener.this.connection == null) {
                        return;
                    }
                    try {
                        DefaultPatchListener.this.context.unbindService(DefaultPatchListener.this.connection);
                    } catch (Throwable unused) {
                    }
                }
            };
            this.context.bindService(new Intent(this.context, (Class<?>) TinkerPatchForeService.class), this.connection, 1);
        } catch (Throwable unused) {
        }
    }

    @Override
    public int onPatchReceived(String str) {
        int iPatchCheck = patchCheck(str, SharePatchFileUtil.getMD5(new File(str)));
        if (iPatchCheck == 0) {
            runForgService();
            TinkerPatchService.runPatchService(this.context, str);
        } else {
            Tinker.with(this.context).getLoadReporter().onLoadPatchListenerReceiveFail(new File(str), iPatchCheck);
        }
        return iPatchCheck;
    }

    protected int patchCheck(String str, String str2) {
        Tinker tinkerWith = Tinker.with(this.context);
        if (!tinkerWith.isTinkerEnabled() || !ShareTinkerInternals.isTinkerEnableWithSharedPreferences(this.context)) {
            return -1;
        }
        if (TextUtils.isEmpty(str2) || !SharePatchFileUtil.isLegalFile(new File(str))) {
            return -2;
        }
        if (tinkerWith.isPatchProcess()) {
            return -4;
        }
        if (TinkerServiceInternals.isTinkerPatchServiceRunning(this.context)) {
            return -3;
        }
        if (ShareTinkerInternals.isVmJit()) {
            return -5;
        }
        TinkerLoadResult tinkerLoadResultIfPresent = tinkerWith.getTinkerLoadResultIfPresent();
        if (!(tinkerWith.isMainProcess() && tinkerLoadResultIfPresent != null && tinkerLoadResultIfPresent.useInterpretMode)) {
            if (tinkerWith.isTinkerLoaded() && tinkerLoadResultIfPresent != null && str2.equals(tinkerLoadResultIfPresent.currentVersion)) {
                return -6;
            }
            String absolutePath = tinkerWith.getPatchDirectory().getAbsolutePath();
            try {
                SharePatchInfo andCheckPropertyWithLock = SharePatchInfo.readAndCheckPropertyWithLock(SharePatchFileUtil.getPatchInfoFile(absolutePath), SharePatchFileUtil.getPatchInfoLockFile(absolutePath));
                if (andCheckPropertyWithLock != null && !ShareTinkerInternals.isNullOrNil(andCheckPropertyWithLock.newVersion) && !andCheckPropertyWithLock.isRemoveNewVersion) {
                    if (str2.equals(andCheckPropertyWithLock.newVersion)) {
                        return -6;
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return !UpgradePatchRetry.getInstance(this.context).onPatchListenerCheck(str2) ? -7 : 0;
    }
}
