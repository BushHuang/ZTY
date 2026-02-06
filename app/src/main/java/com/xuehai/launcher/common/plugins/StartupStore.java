package com.xuehai.launcher.common.plugins;

import android.util.Log;
import com.xuehai.launcher.common.plugins.DefaultThreadFactory;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u0000J\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/xuehai/launcher/common/plugins/StartupStore;", "", "storeName", "", "(Ljava/lang/String;)V", "debugLog", "", "executor", "Lcom/xuehai/launcher/common/plugins/DefaultThreadFactory$DefaultPoolExecutor;", "isRunning", "tasks", "Ljava/util/ArrayList;", "Lcom/xuehai/launcher/common/plugins/StartupTask;", "Lkotlin/collections/ArrayList;", "execute", "", "openLogger", "push", "task", "Companion", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StartupStore {

    public static final Companion INSTANCE = new Companion(null);
    private boolean debugLog;
    private final DefaultThreadFactory.DefaultPoolExecutor executor;
    private boolean isRunning;
    private final String storeName;
    private final ArrayList<StartupTask> tasks;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/xuehai/launcher/common/plugins/StartupStore$Companion;", "", "()V", "build", "Lcom/xuehai/launcher/common/plugins/StartupStore;", "storeName", "", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final StartupStore build(String storeName) {
            Intrinsics.checkNotNullParameter(storeName, "storeName");
            return new StartupStore(storeName, null);
        }
    }

    private StartupStore(String str) {
        this.storeName = str;
        this.tasks = new ArrayList<>();
        this.executor = DefaultThreadFactory.DefaultPoolExecutor.INSTANCE.getInstance();
    }

    public StartupStore(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    @JvmStatic
    public static final StartupStore build(String str) {
        return INSTANCE.build(str);
    }

    public final void execute() throws InterruptedException {
        if (this.isRunning) {
            return;
        }
        this.isRunning = true;
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (true ^ this.tasks.isEmpty()) {
            CountDownLatch countDownLatch = new CountDownLatch(this.tasks.size());
            for (StartupTask startupTask : this.tasks) {
                startupTask.setCountDownLatch(countDownLatch);
                startupTask.setDebugLog(this.debugLog);
                this.executor.execute(startupTask);
            }
            countDownLatch.await();
            this.tasks.clear();
        }
        if (this.debugLog) {
            Log.i("StartupStore", this.storeName + " 该批次 总耗时 : " + (System.currentTimeMillis() - jCurrentTimeMillis) + "ms");
        }
        this.isRunning = false;
    }

    public final StartupStore openLogger() {
        this.debugLog = true;
        return this;
    }

    public final StartupStore push(StartupTask task) {
        Intrinsics.checkNotNullParameter(task, "task");
        this.tasks.add(task);
        return this;
    }
}
