package com.tencent.tinker.lib.tinker;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import com.tencent.tinker.lib.listener.DefaultPatchListener;
import com.tencent.tinker.lib.listener.PatchListener;
import com.tencent.tinker.lib.patch.AbstractPatch;
import com.tencent.tinker.lib.patch.UpgradePatch;
import com.tencent.tinker.lib.reporter.DefaultLoadReporter;
import com.tencent.tinker.lib.reporter.DefaultPatchReporter;
import com.tencent.tinker.lib.reporter.LoadReporter;
import com.tencent.tinker.lib.reporter.PatchReporter;
import com.tencent.tinker.lib.service.AbstractResultService;
import com.tencent.tinker.lib.service.DefaultTinkerResultService;
import com.tencent.tinker.lib.service.TinkerPatchService;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.lib.util.TinkerServiceInternals;
import com.tencent.tinker.loader.TinkerRuntimeException;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.SharePatchInfo;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import java.io.File;

public class Tinker {
    private static final String TAG = "Tinker.Tinker";
    private static boolean sInstalled = false;
    private static Tinker sInstance;
    final Context context;
    final boolean isMainProcess;
    final boolean isPatchProcess;
    final PatchListener listener;
    final LoadReporter loadReporter;
    private boolean loaded;
    final File patchDirectory;
    final File patchInfoFile;
    final File patchInfoLockFile;
    final PatchReporter patchReporter;
    int tinkerFlags;
    TinkerLoadResult tinkerLoadResult;
    final boolean tinkerLoadVerifyFlag;

    public static class Builder {
        private final Context context;
        private PatchListener listener;
        private LoadReporter loadReporter;
        private final boolean mainProcess;
        private File patchDirectory;
        private File patchInfoFile;
        private File patchInfoLockFile;
        private final boolean patchProcess;
        private PatchReporter patchReporter;
        private int status = -1;
        private Boolean tinkerLoadVerifyFlag;

        public Builder(Context context) {
            if (context == null) {
                throw new TinkerRuntimeException("Context must not be null.");
            }
            this.context = context;
            this.mainProcess = TinkerServiceInternals.isInMainProcess(context);
            this.patchProcess = TinkerServiceInternals.isInTinkerPatchServiceProcess(context);
            File patchDirectory = SharePatchFileUtil.getPatchDirectory(context);
            this.patchDirectory = patchDirectory;
            if (patchDirectory == null) {
                TinkerLog.e("Tinker.Tinker", "patchDirectory is null!", new Object[0]);
                return;
            }
            this.patchInfoFile = SharePatchFileUtil.getPatchInfoFile(patchDirectory.getAbsolutePath());
            this.patchInfoLockFile = SharePatchFileUtil.getPatchInfoLockFile(this.patchDirectory.getAbsolutePath());
            TinkerLog.w("Tinker.Tinker", "tinker patch directory: %s", this.patchDirectory);
        }

        public Tinker build() {
            if (this.status == -1) {
                this.status = 15;
            }
            if (this.loadReporter == null) {
                this.loadReporter = new DefaultLoadReporter(this.context);
            }
            if (this.patchReporter == null) {
                this.patchReporter = new DefaultPatchReporter(this.context);
            }
            if (this.listener == null) {
                this.listener = new DefaultPatchListener(this.context);
            }
            if (this.tinkerLoadVerifyFlag == null) {
                this.tinkerLoadVerifyFlag = false;
            }
            return new Tinker(this.context, this.status, this.loadReporter, this.patchReporter, this.listener, this.patchDirectory, this.patchInfoFile, this.patchInfoLockFile, this.mainProcess, this.patchProcess, this.tinkerLoadVerifyFlag.booleanValue());
        }

        public Builder listener(PatchListener patchListener) {
            if (patchListener == null) {
                throw new TinkerRuntimeException("listener must not be null.");
            }
            if (this.listener != null) {
                throw new TinkerRuntimeException("listener is already set.");
            }
            this.listener = patchListener;
            return this;
        }

        public Builder loadReport(LoadReporter loadReporter) {
            if (loadReporter == null) {
                throw new TinkerRuntimeException("loadReporter must not be null.");
            }
            if (this.loadReporter != null) {
                throw new TinkerRuntimeException("loadReporter is already set.");
            }
            this.loadReporter = loadReporter;
            return this;
        }

        public Builder patchReporter(PatchReporter patchReporter) {
            if (patchReporter == null) {
                throw new TinkerRuntimeException("patchReporter must not be null.");
            }
            if (this.patchReporter != null) {
                throw new TinkerRuntimeException("patchReporter is already set.");
            }
            this.patchReporter = patchReporter;
            return this;
        }

        public Builder tinkerFlags(int i) {
            if (this.status != -1) {
                throw new TinkerRuntimeException("tinkerFlag is already set.");
            }
            this.status = i;
            return this;
        }

        public Builder tinkerLoadVerifyFlag(Boolean bool) {
            if (bool == null) {
                throw new TinkerRuntimeException("tinkerLoadVerifyFlag must not be null.");
            }
            if (this.tinkerLoadVerifyFlag != null) {
                throw new TinkerRuntimeException("tinkerLoadVerifyFlag is already set.");
            }
            this.tinkerLoadVerifyFlag = bool;
            return this;
        }
    }

    private Tinker(Context context, int i, LoadReporter loadReporter, PatchReporter patchReporter, PatchListener patchListener, File file, File file2, File file3, boolean z, boolean z2, boolean z3) {
        this.loaded = false;
        this.context = context;
        this.listener = patchListener;
        this.loadReporter = loadReporter;
        this.patchReporter = patchReporter;
        this.tinkerFlags = i;
        this.patchDirectory = file;
        this.patchInfoFile = file2;
        this.patchInfoLockFile = file3;
        this.isMainProcess = z;
        this.tinkerLoadVerifyFlag = z3;
        this.isPatchProcess = z2;
    }

    public static void create(Tinker tinker) {
        if (sInstance != null) {
            throw new TinkerRuntimeException("Tinker instance is already set.");
        }
        sInstance = tinker;
    }

    public static boolean isTinkerInstalled() {
        return sInstalled;
    }

    public static Tinker with(Context context) {
        if (!sInstalled) {
            throw new TinkerRuntimeException("you must install tinker before get tinker sInstance");
        }
        synchronized (Tinker.class) {
            if (sInstance == null) {
                sInstance = new Builder(context).build();
            }
        }
        return sInstance;
    }

    public void cleanPatch() {
        File file = this.patchDirectory;
        if (file == null) {
            return;
        }
        File patchInfoFile = SharePatchFileUtil.getPatchInfoFile(file.getAbsolutePath());
        if (!patchInfoFile.exists()) {
            TinkerLog.w("Tinker.Tinker", "try to clean patch while patch info file does not exist.", new Object[0]);
            return;
        }
        File patchInfoLockFile = SharePatchFileUtil.getPatchInfoLockFile(this.patchDirectory.getAbsolutePath());
        SharePatchInfo andCheckPropertyWithLock = SharePatchInfo.readAndCheckPropertyWithLock(patchInfoFile, patchInfoLockFile);
        if (andCheckPropertyWithLock != null) {
            andCheckPropertyWithLock.isRemoveNewVersion = true;
            SharePatchInfo.rewritePatchInfoFileWithLock(patchInfoFile, andCheckPropertyWithLock, patchInfoLockFile);
        }
    }

    public void cleanPatchByPatchApk(File file) {
        if (this.patchDirectory == null || file == null || !file.exists()) {
            return;
        }
        cleanPatchByVersion(SharePatchFileUtil.getPatchVersionDirectory(SharePatchFileUtil.getMD5(file)));
    }

    public void cleanPatchByVersion(String str) {
        if (this.patchDirectory == null || str == null) {
            return;
        }
        SharePatchFileUtil.deleteDir(this.patchDirectory.getAbsolutePath() + "/" + str);
    }

    public Context getContext() {
        return this.context;
    }

    public LoadReporter getLoadReporter() {
        return this.loadReporter;
    }

    public File getPatchDirectory() {
        return this.patchDirectory;
    }

    public File getPatchInfoFile() {
        return this.patchInfoFile;
    }

    public File getPatchInfoLockFile() {
        return this.patchInfoLockFile;
    }

    public PatchListener getPatchListener() {
        return this.listener;
    }

    public PatchReporter getPatchReporter() {
        return this.patchReporter;
    }

    public int getTinkerFlags() {
        return this.tinkerFlags;
    }

    public TinkerLoadResult getTinkerLoadResultIfPresent() {
        return this.tinkerLoadResult;
    }

    public long getTinkerRomSpace() {
        File file = this.patchDirectory;
        if (file == null) {
            return 0L;
        }
        return SharePatchFileUtil.getFileOrDirectorySize(file) / 1024;
    }

    public void install(Intent intent) {
        install(intent, DefaultTinkerResultService.class, new UpgradePatch());
    }

    public void install(Intent intent, Class<? extends AbstractResultService> cls, AbstractPatch abstractPatch) {
        sInstalled = true;
        TinkerPatchService.setPatchProcessor(abstractPatch, cls);
        TinkerLog.i("Tinker.Tinker", "try to install tinker, isEnable: %b, version: %s", Boolean.valueOf(isTinkerEnabled()), "1.9.14.7");
        if (!isTinkerEnabled()) {
            TinkerLog.e("Tinker.Tinker", "tinker is disabled", new Object[0]);
            return;
        }
        if (intent == null) {
            throw new TinkerRuntimeException("intentResult must not be null.");
        }
        TinkerLoadResult tinkerLoadResult = new TinkerLoadResult();
        this.tinkerLoadResult = tinkerLoadResult;
        tinkerLoadResult.parseTinkerResult(getContext(), intent);
        this.loadReporter.onLoadResult(this.patchDirectory, this.tinkerLoadResult.loadCode, this.tinkerLoadResult.costTime);
        if (this.loaded) {
            return;
        }
        TinkerLog.w("Tinker.Tinker", "tinker load fail!", new Object[0]);
    }

    public boolean isEnabledForDex() {
        return ShareTinkerInternals.isTinkerEnabledForDex(this.tinkerFlags);
    }

    public boolean isEnabledForNativeLib() {
        return ShareTinkerInternals.isTinkerEnabledForNativeLib(this.tinkerFlags);
    }

    public boolean isEnabledForResource() {
        return ShareTinkerInternals.isTinkerEnabledForResource(this.tinkerFlags);
    }

    public boolean isMainProcess() {
        return this.isMainProcess;
    }

    public boolean isPatchProcess() {
        return this.isPatchProcess;
    }

    public boolean isTinkerEnabled() {
        return ShareTinkerInternals.isTinkerEnabled(this.tinkerFlags);
    }

    public boolean isTinkerLoadVerify() {
        return this.tinkerLoadVerifyFlag;
    }

    public boolean isTinkerLoaded() {
        return this.loaded;
    }

    public void rollbackPatch() {
        if (!isTinkerLoaded()) {
            TinkerLog.w("Tinker.Tinker", "rollbackPatch: tinker is not loaded, just return", new Object[0]);
            return;
        }
        ShareTinkerInternals.killAllOtherProcess(this.context);
        cleanPatch();
        Process.killProcess(Process.myPid());
    }

    public void setPatchServiceNotificationId(int i) {
        TinkerPatchService.setTinkerNotificationId(i);
    }

    public void setTinkerDisable() {
        this.tinkerFlags = 0;
    }

    public void setTinkerLoaded(boolean z) {
        this.loaded = z;
    }
}
