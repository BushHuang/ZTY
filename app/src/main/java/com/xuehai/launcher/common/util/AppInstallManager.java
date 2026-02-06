package com.xuehai.launcher.common.util;

import android.content.pm.PackageInfo;
import android.os.Build;
import com.xh.xhcore.common.util.XHStringUtil;
import com.xuehai.launcher.common.base.BaseApplication;
import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.launcher.common.plugins.ThreadPlugins;
import com.xuehai.launcher.common.plugins.rx.MyObserver;
import com.xuehai.launcher.common.util.AppInstallManager;
import com.xuehai.launcher.common.util.app.ApplicationUtil;
import com.xuehai.system.mdm.proxy.PolicyManager;
import com.zaze.utils.AppUtil;
import com.zaze.utils.FileUtil;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\u0017\u0018B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0006J\b\u0010\u0010\u001a\u00020\u000eH\u0002J\u000e\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0005J\b\u0010\u0013\u001a\u00020\u000eH\u0002J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u0006H\u0002J\u000e\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u0005R*\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\b\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/xuehai/launcher/common/util/AppInstallManager;", "", "()V", "currInstallTasks", "Ljava/util/HashMap;", "", "Lcom/xuehai/launcher/common/util/AppInstallManager$AppUpdateTask;", "Lkotlin/collections/HashMap;", "installedTasks", "installing", "Ljava/util/concurrent/atomic/AtomicBoolean;", "timeoutTask", "Ljava/lang/Runnable;", "addToInstallPool", "", "appUpdateTask", "doNext", "onAppUpdated", "packageName", "skipToNext", "toInstall", "", "unInstallApp", "AppUpdateTask", "InstallError", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AppInstallManager {
    public static final AppInstallManager INSTANCE = new AppInstallManager();
    private static final HashMap<String, AppUpdateTask> installedTasks = new HashMap<>();
    private static final AtomicBoolean installing = new AtomicBoolean(false);
    private static final HashMap<String, AppUpdateTask> currInstallTasks = new HashMap<>();
    private static final Runnable timeoutTask = new Runnable() {
        @Override
        public final void run() {
            AppInstallManager.m83timeoutTask$lambda0();
        }
    };

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B8\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012!\u0010\u0005\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0006¢\u0006\u0002\u0010\fJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J$\u0010\u0014\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0006HÆ\u0003JB\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032#\b\u0002\u0010\u0005\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0006HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R,\u0010\u0005\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010¨\u0006\u001c"}, d2 = {"Lcom/xuehai/launcher/common/util/AppInstallManager$AppUpdateTask;", "", "packageName", "", "savePath", "onError", "Lkotlin/Function1;", "Lcom/xuehai/launcher/common/util/AppInstallManager$InstallError;", "Lkotlin/ParameterName;", "name", "error", "", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getOnError", "()Lkotlin/jvm/functions/Function1;", "getPackageName", "()Ljava/lang/String;", "getSavePath", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class AppUpdateTask {
        private final Function1<InstallError, Unit> onError;
        private final String packageName;
        private final String savePath;

        public AppUpdateTask(String str, String str2, Function1<? super InstallError, Unit> function1) {
            Intrinsics.checkNotNullParameter(str, "packageName");
            Intrinsics.checkNotNullParameter(str2, "savePath");
            Intrinsics.checkNotNullParameter(function1, "onError");
            this.packageName = str;
            this.savePath = str2;
            this.onError = function1;
        }

        public static AppUpdateTask copy$default(AppUpdateTask appUpdateTask, String str, String str2, Function1 function1, int i, Object obj) {
            if ((i & 1) != 0) {
                str = appUpdateTask.packageName;
            }
            if ((i & 2) != 0) {
                str2 = appUpdateTask.savePath;
            }
            if ((i & 4) != 0) {
                function1 = appUpdateTask.onError;
            }
            return appUpdateTask.copy(str, str2, function1);
        }

        public final String getPackageName() {
            return this.packageName;
        }

        public final String getSavePath() {
            return this.savePath;
        }

        public final Function1<InstallError, Unit> component3() {
            return this.onError;
        }

        public final AppUpdateTask copy(String packageName, String savePath, Function1<? super InstallError, Unit> onError) {
            Intrinsics.checkNotNullParameter(packageName, "packageName");
            Intrinsics.checkNotNullParameter(savePath, "savePath");
            Intrinsics.checkNotNullParameter(onError, "onError");
            return new AppUpdateTask(packageName, savePath, onError);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AppUpdateTask)) {
                return false;
            }
            AppUpdateTask appUpdateTask = (AppUpdateTask) other;
            return Intrinsics.areEqual(this.packageName, appUpdateTask.packageName) && Intrinsics.areEqual(this.savePath, appUpdateTask.savePath) && Intrinsics.areEqual(this.onError, appUpdateTask.onError);
        }

        public final Function1<InstallError, Unit> getOnError() {
            return this.onError;
        }

        public final String getPackageName() {
            return this.packageName;
        }

        public final String getSavePath() {
            return this.savePath;
        }

        public int hashCode() {
            return (((this.packageName.hashCode() * 31) + this.savePath.hashCode()) * 31) + this.onError.hashCode();
        }

        public String toString() {
            return "AppUpdateTask(packageName=" + this.packageName + ", savePath=" + this.savePath + ", onError=" + this.onError + ')';
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n¨\u0006\u000b"}, d2 = {"Lcom/xuehai/launcher/common/util/AppInstallManager$InstallError;", "", "()V", "ApkFileError", "DowngradeError", "ExecuteError", "InstalledError", "Lcom/xuehai/launcher/common/util/AppInstallManager$InstallError$ApkFileError;", "Lcom/xuehai/launcher/common/util/AppInstallManager$InstallError$InstalledError;", "Lcom/xuehai/launcher/common/util/AppInstallManager$InstallError$DowngradeError;", "Lcom/xuehai/launcher/common/util/AppInstallManager$InstallError$ExecuteError;", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static abstract class InstallError {

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/xuehai/launcher/common/util/AppInstallManager$InstallError$ApkFileError;", "Lcom/xuehai/launcher/common/util/AppInstallManager$InstallError;", "()V", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        public static final class ApkFileError extends InstallError {
            public static final ApkFileError INSTANCE = new ApkFileError();

            private ApkFileError() {
                super(null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/xuehai/launcher/common/util/AppInstallManager$InstallError$DowngradeError;", "Lcom/xuehai/launcher/common/util/AppInstallManager$InstallError;", "()V", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        public static final class DowngradeError extends InstallError {
            public static final DowngradeError INSTANCE = new DowngradeError();

            private DowngradeError() {
                super(null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/xuehai/launcher/common/util/AppInstallManager$InstallError$ExecuteError;", "Lcom/xuehai/launcher/common/util/AppInstallManager$InstallError;", "()V", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        public static final class ExecuteError extends InstallError {
            public static final ExecuteError INSTANCE = new ExecuteError();

            private ExecuteError() {
                super(null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/xuehai/launcher/common/util/AppInstallManager$InstallError$InstalledError;", "Lcom/xuehai/launcher/common/util/AppInstallManager$InstallError;", "()V", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        public static final class InstalledError extends InstallError {
            public static final InstalledError INSTANCE = new InstalledError();

            private InstalledError() {
                super(null);
            }
        }

        private InstallError() {
        }

        public InstallError(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private AppInstallManager() {
    }

    private final void doNext() {
        Collection<AppUpdateTask> collectionValues = installedTasks.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues, "installedTasks.values");
        final AppUpdateTask appUpdateTask = (AppUpdateTask) CollectionsKt.firstOrNull(collectionValues);
        if (appUpdateTask != null) {
            final String packageName = appUpdateTask.getPackageName();
            MyLog.i("[MDM]", "AppInstall doNext: " + packageName);
            installedTasks.remove(packageName);
            Observable.fromCallable(new Callable() {
                @Override
                public final Object call() {
                    return AppInstallManager.m80doNext$lambda1(appUpdateTask);
                }
            }).subscribeOn(ThreadPlugins.installScheduler()).map(new Function() {
                @Override
                public final Object apply(Object obj) {
                    return AppInstallManager.m81doNext$lambda2(appUpdateTask, packageName, (AppInstallManager.AppUpdateTask) obj);
                }
            }).subscribe(new MyObserver<Unit>() {
                @Override
                public void onError(Throwable e) {
                    Intrinsics.checkNotNullParameter(e, "e");
                    super.onError(e);
                    AppInstallManager.INSTANCE.skipToNext();
                }
            });
        }
    }

    private static final AppUpdateTask m80doNext$lambda1(AppUpdateTask appUpdateTask) {
        return appUpdateTask;
    }

    private static final Unit m81doNext$lambda2(AppUpdateTask appUpdateTask, String str, AppUpdateTask appUpdateTask2) {
        Intrinsics.checkNotNullParameter(str, "$packageName");
        Intrinsics.checkNotNullParameter(appUpdateTask2, "it");
        if (INSTANCE.toInstall(appUpdateTask)) {
            currInstallTasks.put(str, appUpdateTask);
            ThreadPlugins.runInWorkThread(timeoutTask, Build.VERSION.SDK_INT >= 24 ? 30000L : 120000L);
            installing.set(true);
        } else {
            currInstallTasks.remove(str);
            INSTANCE.skipToNext();
        }
        return Unit.INSTANCE;
    }

    private final void skipToNext() {
        installing.set(false);
        doNext();
    }

    private static final void m83timeoutTask$lambda0() {
        for (AppUpdateTask appUpdateTask : currInstallTasks.values()) {
            MyLog.i("[MDM]", "AppInstall timeout: " + appUpdateTask.getPackageName());
            AppInstallManager appInstallManager = INSTANCE;
            Intrinsics.checkNotNullExpressionValue(appUpdateTask, "it");
            appInstallManager.addToInstallPool(appUpdateTask);
        }
        INSTANCE.skipToNext();
    }

    private final boolean toInstall(AppUpdateTask appUpdateTask) {
        String packageName = appUpdateTask.getPackageName();
        String savePath = appUpdateTask.getSavePath();
        MyLog.i("Application[MDM]", "------------------------------------------------------");
        try {
            PackageInfo packageArchiveInfo$default = savePath.length() == 0 ? (PackageInfo) null : ApplicationUtil.getPackageArchiveInfo$default(ApplicationUtil.INSTANCE, null, new File(savePath), 1, null);
            if (packageArchiveInfo$default == null) {
                MyLog.w("Application[MDM]", "安装文件异常!" + savePath + " (" + packageName + ')');
                FileUtil.deleteFile(savePath);
                appUpdateTask.getOnError().invoke(InstallError.ApkFileError.INSTANCE);
                return false;
            }
            long j = packageArchiveInfo$default.versionCode;
            PackageInfo packageInfo$default = AppUtil.getPackageInfo$default(BaseApplication.INSTANCE.getInstance(), packageName, 0, 4, (Object) null);
            long j2 = packageInfo$default != null ? packageInfo$default.versionCode : 0L;
            if (packageInfo$default != null && j2 == j) {
                MyLog.i("Application[MDM]", "本地已安装相同版本, 不需要安装(" + j + ')');
                appUpdateTask.getOnError().invoke(InstallError.InstalledError.INSTANCE);
                return false;
            }
            if (j2 > j) {
                StringBuilder sb = new StringBuilder();
                sb.append(packageName);
                sb.append(" 版本回退: 本地版本");
                sb.append(packageInfo$default != null ? Integer.valueOf(packageInfo$default.versionCode) : null);
                sb.append(", 需要更新版本");
                sb.append(j);
                MyLog.i("Application[MDM]", sb.toString());
                appUpdateTask.getOnError().invoke(InstallError.DowngradeError.INSTANCE);
                return false;
            }
            MyLog.i("Application[MDM]", "开始安装:" + packageName + '(' + j + ") >>  " + savePath);
            if (PolicyManager.getApplicationPolicyProxy().installApplication(savePath)) {
                MyLog.i("Application[MDM]", "调用安装成功, 等待最终安装结果 " + packageName);
                return true;
            }
            MyLog.w("Application[MDM]", "应用安装失败(" + packageName + ')');
            appUpdateTask.getOnError().invoke(InstallError.ExecuteError.INSTANCE);
            return false;
        } finally {
            MyLog.i("Application[MDM]", "------------------------------------------------------");
        }
    }

    public final void addToInstallPool(AppUpdateTask appUpdateTask) {
        Intrinsics.checkNotNullParameter(appUpdateTask, "appUpdateTask");
        installedTasks.put(appUpdateTask.getPackageName(), appUpdateTask);
        if (installing.compareAndSet(false, true)) {
            doNext();
        }
    }

    public final void onAppUpdated(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        if (currInstallTasks.containsKey(packageName)) {
            currInstallTasks.remove(packageName);
            installedTasks.remove(packageName);
            if (currInstallTasks.isEmpty()) {
                ThreadPlugins.removeWorkCallbacks(timeoutTask);
            }
            skipToNext();
        }
    }

    public final boolean unInstallApp(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        if (XHStringUtil.isEmpty(packageName)) {
            return true;
        }
        MyLog.i("Application[MDM]", "删除应用 packageName(" + packageName + ')');
        PolicyManager.getApplicationPolicyProxy().uninstallApplication(packageName);
        return true;
    }
}
