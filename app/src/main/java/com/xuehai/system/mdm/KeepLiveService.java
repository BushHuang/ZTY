package com.xuehai.system.mdm;

import android.content.Intent;
import android.os.PowerManager;
import com.xuehai.launcher.App;
import com.xuehai.launcher.common.base.BaseApplication;
import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.launcher.common.plugins.TaskPlugins;
import com.xuehai.launcher.common.plugins.ThreadPlugins;
import com.xuehai.launcher.common.util.ActivityUtil;
import com.xuehai.launcher.util.ZtyClientUtil;
import com.xuehai.launcher.util.ZtyFlagManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.Thread;
import java.nio.channels.FileLock;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 \u00162\u00020\u0001:\u0002\u0016\u0017B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0002J\u0006\u0010\u000f\u001a\u00020\u000bJ \u0010\u0010\u001a\u00020\u000b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014R\"\u0010\u0003\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u00060\u0007R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/xuehai/system/mdm/KeepLiveService;", "", "()V", "keepApp", "Lkotlin/Triple;", "", "thread", "Lcom/xuehai/system/mdm/KeepLiveService$LockThread;", "wakeupAppTask", "Ljava/lang/Runnable;", "log", "", "msg", "e", "", "onDestroy", "onStartCommand", "intent", "Landroid/content/Intent;", "flags", "", "startId", "Companion", "LockThread", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class KeepLiveService {

    public static final Companion INSTANCE = new Companion(null);
    private static boolean isAlive;
    private Triple<String, String, String> keepApp;
    private LockThread thread = new LockThread();
    private final Runnable wakeupAppTask = new Runnable() {
        @Override
        public final void run() {
            KeepLiveService.m216wakeupAppTask$lambda1(this.f$0);
        }
    };

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/xuehai/system/mdm/KeepLiveService$Companion;", "", "()V", "<set-?>", "", "isAlive", "()Z", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean isAlive() {
            return KeepLiveService.isAlive;
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/xuehai/system/mdm/KeepLiveService$LockThread;", "Ljava/lang/Thread;", "(Lcom/xuehai/system/mdm/KeepLiveService;)V", "lock", "Ljava/nio/channels/FileLock;", "lockFile", "Ljava/io/File;", "run", "", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class LockThread extends Thread {
        public LockThread() {
        }

        private final FileLock lock(File lockFile) throws IOException {
            try {
                FileLock fileLockLock = new FileOutputStream(lockFile).getChannel().lock();
                if (fileLockLock.isValid()) {
                    return fileLockLock;
                }
                return null;
            } catch (Exception e) {
                KeepLiveService.this.log(lockFile.getAbsolutePath() + " FAIL!", e);
                return null;
            }
        }

        @Override
        public void run() throws IOException {
            Object systemService;
            super.run();
            Triple triple = KeepLiveService.this.keepApp;
            if (triple != null) {
                KeepLiveService keepLiveService = KeepLiveService.this;
                KeepLiveService.log$default(keepLiveService, triple + " start", null, 2, null);
                ZtyFlagManager.INSTANCE.increase();
                ZtyFlagManager.postClear$default(ZtyFlagManager.INSTANCE, 0L, 1, null);
                ZtyFlagManager.INSTANCE.stopMarkErrorTask();
                TaskPlugins.removeTask(keepLiveService.wakeupAppTask);
                Companion companion = KeepLiveService.INSTANCE;
                KeepLiveService.isAlive = true;
                FileLock fileLockLock = lock(new File((String) triple.getThird()));
                if (fileLockLock != null) {
                    fileLockLock.release();
                }
                Companion companion2 = KeepLiveService.INSTANCE;
                KeepLiveService.isAlive = false;
                try {
                    systemService = BaseApplication.INSTANCE.getInstance().getSystemService("power");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (systemService == null) {
                    throw new NullPointerException("null cannot be cast to non-null type android.os.PowerManager");
                }
                PowerManager.WakeLock wakeLockNewWakeLock = ((PowerManager) systemService).newWakeLock(268435462, getClass().getName());
                wakeLockNewWakeLock.acquire(10000L);
                wakeLockNewWakeLock.release();
                ThreadPlugins.runInUIThread(keepLiveService.wakeupAppTask, 1000L);
                ZtyFlagManager.postMarkError$default(ZtyFlagManager.INSTANCE, false, 1, null);
                KeepLiveService.log$default(keepLiveService, triple + " end", null, 2, null);
            }
        }
    }

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Thread.State.values().length];
            iArr[Thread.State.RUNNABLE.ordinal()] = 1;
            iArr[Thread.State.NEW.ordinal()] = 2;
            iArr[Thread.State.TERMINATED.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private final void log(String msg, Throwable e) {
        Unit unit;
        if (e != null) {
            MyLog.w("[MDM]", "LockThread lock " + Thread.currentThread().getId() + ' ' + msg, e);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            MyLog.i("[MDM]", "LockThread lock " + Thread.currentThread().getId() + ' ' + msg);
        }
    }

    static void log$default(KeepLiveService keepLiveService, String str, Throwable th, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        keepLiveService.log(str, th);
    }

    private static final void m216wakeupAppTask$lambda1(KeepLiveService keepLiveService) {
        Intrinsics.checkNotNullParameter(keepLiveService, "this$0");
        MyLog.i("[MDM]", "KeepLiveService wakeupAppTask : " + keepLiveService.keepApp);
        final Triple<String, String, String> triple = keepLiveService.keepApp;
        if (triple != null) {
            ZtyClientUtil.INSTANCE.openClient(new Function0<Boolean>() {
                {
                    super(0);
                }

                @Override
                public final Boolean invoke() {
                    ActivityUtil activityUtil = ActivityUtil.INSTANCE;
                    App companion = App.INSTANCE.getInstance();
                    Intent intent = new Intent(triple.getSecond());
                    intent.setPackage(triple.getFirst());
                    Unit unit = Unit.INSTANCE;
                    return Boolean.valueOf(ActivityUtil.startActivity$default(activityUtil, companion, intent, 0, 4, null));
                }
            });
        }
    }

    public final void onDestroy() {
        this.thread.interrupt();
    }

    public final void onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            return;
        }
        String stringExtra = intent.getStringExtra("package_name");
        String stringExtra2 = intent.getStringExtra("lockPath");
        String stringExtra3 = intent.getStringExtra("emptyActivityAction");
        String str = stringExtra;
        if (str == null || str.length() == 0) {
            return;
        }
        String str2 = stringExtra2;
        if (str2 == null || str2.length() == 0) {
            return;
        }
        String str3 = stringExtra3;
        if (str3 == null || str3.length() == 0) {
            return;
        }
        Intrinsics.checkNotNullExpressionValue(stringExtra, "appPackage");
        Intrinsics.checkNotNullExpressionValue(stringExtra3, "emptyActivityAction");
        Intrinsics.checkNotNullExpressionValue(stringExtra2, "lockFile");
        this.keepApp = new Triple<>(stringExtra, stringExtra3, stringExtra2);
        MyLog.i("[MDM]", "KeepLiveService onStartCommand : " + this.thread + " >> " + this.thread.getState());
        Thread.State state = this.thread.getState();
        int i = state == null ? -1 : WhenMappings.$EnumSwitchMapping$0[state.ordinal()];
        if (i != 2) {
            if (i != 3) {
                return;
            } else {
                this.thread = new LockThread();
            }
        }
        if (this.thread.isInterrupted()) {
            this.thread = new LockThread();
        }
        this.thread.start();
    }
}
