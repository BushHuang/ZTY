package com.xuehai.launcher;

import android.app.Application;
import android.os.Build;
import androidx.lifecycle.ViewModelKt;
import com.xuehai.launcher.common.base.AbsAndroidViewModel;
import com.xuehai.launcher.common.base.BaseApplication;
import com.xuehai.launcher.common.cache.SessionData;
import com.xuehai.launcher.common.config.DynamicConfig;
import com.xuehai.launcher.common.crack.DeviceChecker;
import com.xuehai.launcher.common.crash.CrashLevelManager;
import com.xuehai.launcher.common.ext.LiveDataExtKt;
import com.xuehai.launcher.common.ext.SingleLiveEvent;
import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.launcher.common.plugins.ThreadPlugins;
import com.xuehai.launcher.common.util.DeviceActiveManager;
import com.xuehai.launcher.common.util.PermissionManager;
import com.xuehai.launcher.util.PolicyManagerExtKt;
import com.xuehai.launcher.util.SettingManager;
import com.xuehai.launcher.util.ZtyClientUtil;
import com.xuehai.system.mdm.proxy.PolicyManager;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\u0006\u0010\u0014\u001a\u00020\u0013J\u000e\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0017J\b\u0010\u0018\u001a\u00020\u0013H\u0014J\u0006\u0010\u0019\u001a\u00020\u0013J\u0006\u0010\u001a\u001a\u00020\u0013R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000b¨\u0006\u001c"}, d2 = {"Lcom/xuehai/launcher/MainViewModel;", "Lcom/xuehai/launcher/common/base/AbsAndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "handleDeferredResumeRunnable", "Ljava/lang/Runnable;", "openZtyClient", "Lcom/xuehai/launcher/common/ext/SingleLiveEvent;", "Ljava/lang/Void;", "getOpenZtyClient", "()Lcom/xuehai/launcher/common/ext/SingleLiveEvent;", "showNormalMode", "getShowNormalMode", "showSafeMode", "getShowSafeMode", "startGuide", "getStartGuide", "handleDeferredResume", "", "load", "onAppUpdated", "packageName", "", "onCleared", "onResume", "onStartFailed", "Companion", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MainViewModel extends AbsAndroidViewModel {

    public static final Companion INSTANCE = new Companion(null);
    public static final long MIN_RESUME = 300;
    private static long latelyResume;
    private final Runnable handleDeferredResumeRunnable;
    private final SingleLiveEvent<Void> openZtyClient;
    private final SingleLiveEvent<Void> showNormalMode;
    private final SingleLiveEvent<Void> showSafeMode;
    private final SingleLiveEvent<Void> startGuide;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/xuehai/launcher/MainViewModel$Companion;", "", "()V", "MIN_RESUME", "", "latelyResume", "getLatelyResume", "()J", "setLatelyResume", "(J)V", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final long getLatelyResume() {
            return MainViewModel.latelyResume;
        }

        public final void setLatelyResume(long j) {
            MainViewModel.latelyResume = j;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.xuehai.launcher.MainViewModel$handleDeferredResume$1", f = "MainViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(continuation);
        }

        @Override
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            PolicyManagerExtKt.tryUpgrade(PolicyManager.INSTANCE);
            PolicyManager.INSTANCE.doFixedRules();
            DynamicConfig.updateUserInnerApkState(false);
            MyLog.i("Debug[MDM]", "启动智通平台前，初始化完成");
            return Unit.INSTANCE;
        }
    }

    public MainViewModel(Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.showNormalMode = new SingleLiveEvent<>();
        this.showSafeMode = new SingleLiveEvent<>();
        this.startGuide = new SingleLiveEvent<>();
        this.openZtyClient = new SingleLiveEvent<>();
        this.handleDeferredResumeRunnable = new Runnable() {
            @Override
            public final void run() {
                MainViewModel.m49handleDeferredResumeRunnable$lambda0(this.f$0);
            }
        };
    }

    private final void handleDeferredResume() {
        if (CrashLevelManager.INSTANCE.isSeriousCrash()) {
            MyLog.w("Debug[MDM]", "当前处于严重错误状态");
            return;
        }
        Application applicationCreateDeviceProtectedStorageContext = Build.VERSION.SDK_INT >= 24 ? ((BaseApplication) getApplication()).createDeviceProtectedStorageContext() : getApplication();
        DeviceActiveManager deviceActiveManager = DeviceActiveManager.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(applicationCreateDeviceProtectedStorageContext, "cxt");
        if (deviceActiveManager.isDeviceActivated(applicationCreateDeviceProtectedStorageContext) && ZtyClientUtil.INSTANCE.isZtyClientInstalled() && SettingManager.INSTANCE.checkAppUsagePermission()) {
            PermissionManager permissionManager = PermissionManager.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(PermissionManager.INSTANCE.getPermissionMap().keySet(), "PermissionManager.permissionMap.keys");
            if (!(!permissionManager.getDeniedPermission(r1).isEmpty())) {
                LiveDataExtKt.action(this.openZtyClient);
                BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(null), 3, null);
                return;
            }
        }
        LiveDataExtKt.action(this.startGuide);
    }

    private static final void m49handleDeferredResumeRunnable$lambda0(MainViewModel mainViewModel) {
        Intrinsics.checkNotNullParameter(mainViewModel, "this$0");
        mainViewModel.handleDeferredResume();
    }

    public final SingleLiveEvent<Void> getOpenZtyClient() {
        return this.openZtyClient;
    }

    public final SingleLiveEvent<Void> getShowNormalMode() {
        return this.showNormalMode;
    }

    public final SingleLiveEvent<Void> getShowSafeMode() {
        return this.showSafeMode;
    }

    public final SingleLiveEvent<Void> getStartGuide() {
        return this.startGuide;
    }

    public final void load() throws Throwable {
        if (CrashLevelManager.INSTANCE.isSeriousCrash()) {
            MyLog.w("Debug[MDM]", "MDM当前处于严重错误状态, 前往安全页");
            LiveDataExtKt.action(this.showSafeMode);
        } else {
            ZtyClientUtil.INSTANCE.backupZtyHistoryData();
            DeviceChecker.detectSafely$default(DeviceChecker.INSTANCE, false, 1, null);
            CrashLevelManager.postClear$default(CrashLevelManager.INSTANCE, 0L, 1, null);
            LiveDataExtKt.action(this.showNormalMode);
        }
    }

    public final void onAppUpdated(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        if (ZtyClientUtil.INSTANCE.isZtyClient(packageName)) {
            MyLog.i("Debug[MDM]", "智通平台安装完成,取消更新状态并下一步");
            SessionData.INSTANCE.setZtyUpdating(false);
            onResume();
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        ThreadPlugins.removeUICallbacks(this.handleDeferredResumeRunnable);
    }

    public final void onResume() {
        if (SessionData.INSTANCE.isZtyUpdating()) {
            MyLog.i("Debug[MDM]", "智通平台正在更新中, 等待安装结果...");
            return;
        }
        if (System.currentTimeMillis() - latelyResume > 300) {
            this.handleDeferredResumeRunnable.run();
        } else {
            ThreadPlugins.runInUIThread(this.handleDeferredResumeRunnable, 300L);
        }
        latelyResume = System.currentTimeMillis();
    }

    public final void onStartFailed() {
        LiveDataExtKt.action(this.startGuide);
    }
}
