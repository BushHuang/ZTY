package com.xuehai.launcher.guide;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import com.xh.common.lib.sdk.samsung.AdminReceiver;
import com.xh.xhcore.common.util.XHSPUtil;
import com.xuehai.launcher.App;
import com.xuehai.launcher.common.base.AbsViewModel;
import com.xuehai.launcher.common.cache.SessionData;
import com.xuehai.launcher.common.config.ClientConfig;
import com.xuehai.launcher.common.crash.CrashLevelManager;
import com.xuehai.launcher.common.ext.LiveDataExtKt;
import com.xuehai.launcher.common.ext.SingleLiveEvent;
import com.xuehai.launcher.common.http.DownloadCallback;
import com.xuehai.launcher.common.http.NetStore;
import com.xuehai.launcher.common.http.download.DownloadManager;
import com.xuehai.launcher.common.http.response.CheckDeviceResponse;
import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.launcher.common.plugins.LooperTask;
import com.xuehai.launcher.common.plugins.TaskPlugins;
import com.xuehai.launcher.common.plugins.ThreadPlugins;
import com.xuehai.launcher.common.plugins.rx.MyObserver;
import com.xuehai.launcher.common.util.AppInstallManager;
import com.xuehai.launcher.common.util.DeviceActiveManager;
import com.xuehai.launcher.common.util.DeviceModelUtil;
import com.xuehai.launcher.common.util.PermissionManager;
import com.xuehai.launcher.common.widget.dialog.CustomDialogFragment;
import com.xuehai.launcher.common.widget.dialog.DialogProvider;
import com.xuehai.launcher.guide.InitGuideViewModel;
import com.xuehai.launcher.util.PolicyManagerExtKt;
import com.xuehai.launcher.util.SettingManager;
import com.xuehai.launcher.util.ZtyClientUtil;
import com.xuehai.system.mdm.proxy.ApplicationPolicyProxy;
import com.xuehai.system.mdm.proxy.PolicyManager;
import com.zaze.utils.FileUtil;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;

@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0002\u001e>\u0018\u0000 m2\u00020\u0001:\u0002mnB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010@\u001a\u00020&H\u0002J\b\u0010A\u001a\u00020&H\u0002J\b\u0010B\u001a\u00020\u0013H\u0002J\u0006\u0010C\u001a\u00020\u0013J\b\u0010D\u001a\u00020&H\u0002J\u0010\u0010E\u001a\u00020&2\b\b\u0002\u0010F\u001a\u00020\u0013J\u0006\u0010G\u001a\u00020&J\u0010\u0010H\u001a\u00020&2\u0006\u0010F\u001a\u00020\u0013H\u0002J\u0012\u0010I\u001a\u00020&2\b\b\u0002\u0010J\u001a\u00020\u0013H\u0002J\u000e\u0010K\u001a\u00020&2\u0006\u0010L\u001a\u00020\u0012J\u0010\u0010M\u001a\u00020&2\u0006\u0010N\u001a\u00020OH\u0002J\u0010\u0010P\u001a\u00020&2\u0006\u0010N\u001a\u00020OH\u0002J\u0011\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u00120R¢\u0006\u0002\u0010SJ\u0010\u0010T\u001a\u00020&2\u0006\u0010U\u001a\u00020\u0012H\u0002J\b\u0010V\u001a\u00020&H\u0002J\b\u0010W\u001a\u00020\u0013H\u0002J\u0006\u0010X\u001a\u00020\u0013J\u0016\u0010Y\u001a\u00020&2\u0006\u0010Z\u001a\u00020[2\u0006\u0010\\\u001a\u00020[J\u000e\u0010]\u001a\u00020&2\u0006\u0010^\u001a\u00020\u0012J\b\u0010_\u001a\u00020&H\u0014J\u0016\u0010`\u001a\u00020&2\u0006\u0010a\u001a\u00020\u00122\u0006\u0010b\u001a\u00020\u0013J\u0006\u0010c\u001a\u00020&J$\u0010d\u001a\u00020&2\u0006\u0010L\u001a\u00020\u00122\b\b\u0002\u0010e\u001a\u00020\u00132\b\b\u0002\u0010f\u001a\u00020\u0013H\u0002J\u0006\u0010g\u001a\u00020&J\u0006\u0010h\u001a\u00020&J\b\u0010i\u001a\u00020&H\u0002J\u0010\u0010j\u001a\u00020&2\u0006\u0010k\u001a\u00020lH\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001d\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R#\u0010\u0010\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u00110\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\rR!\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00120\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00130\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0007R\u0010\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001fR\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00130\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0007R\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0007R\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0017\u0010)\u001a\b\u0012\u0004\u0012\u00020&0%¢\u0006\b\n\u0000\u001a\u0004\b*\u0010(R\u001d\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00160\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u0007R\u001f\u0010-\u001a\u00060.R\u00020\u00008FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b1\u0010\u001a\u001a\u0004\b/\u00100R\u0017\u00102\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u0007R\u0017\u00104\u001a\b\u0012\u0004\u0012\u00020\u00130\t¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\rR\u0017\u00106\u001a\b\u0012\u0004\u0012\u00020\u00120\t¢\u0006\b\n\u0000\u001a\u0004\b7\u0010\rR\u0017\u00108\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0004¢\u0006\b\n\u0000\u001a\u0004\b9\u0010\u0007R\u001f\u0010:\u001a\u00060.R\u00020\u00008FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b<\u0010\u001a\u001a\u0004\b;\u00100R\u0010\u0010=\u001a\u00020>X\u0082\u0004¢\u0006\u0004\n\u0002\u0010?¨\u0006o"}, d2 = {"Lcom/xuehai/launcher/guide/InitGuideViewModel;", "Lcom/xuehai/launcher/common/base/AbsViewModel;", "()V", "activateDeviceManagerAction", "Lcom/xuehai/launcher/common/ext/SingleLiveEvent;", "Ljava/lang/Void;", "getActivateDeviceManagerAction", "()Lcom/xuehai/launcher/common/ext/SingleLiveEvent;", "allStepData", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/xuehai/launcher/guide/StepProgressItem;", "getAllStepData", "()Landroidx/lifecycle/MutableLiveData;", "connectWifiDialog", "Lcom/xuehai/launcher/common/widget/dialog/CustomDialogFragment;", "currentMessage", "Lkotlin/Pair;", "", "", "getCurrentMessage", "customOsVersionSet", "", "getCustomOsVersionSet", "()Ljava/util/Set;", "customOsVersionSet$delegate", "Lkotlin/Lazy;", "needRetryAction", "getNeedRetryAction", "onceAdminListener", "com/xuehai/launcher/guide/InitGuideViewModel$onceAdminListener$1", "Lcom/xuehai/launcher/guide/InitGuideViewModel$onceAdminListener$1;", "openWifiAction", "getOpenWifiAction", "openZtyClient", "getOpenZtyClient", "requireAppUsagePermissions", "Lkotlinx/coroutines/channels/Channel;", "", "getRequireAppUsagePermissions", "()Lkotlinx/coroutines/channels/Channel;", "requireAppWriteSettingsPermissions", "getRequireAppWriteSettingsPermissions", "requirePermissions", "getRequirePermissions", "settingSafeGuard", "Lcom/xuehai/launcher/guide/InitGuideViewModel$SettingSafeGuard;", "getSettingSafeGuard", "()Lcom/xuehai/launcher/guide/InitGuideViewModel$SettingSafeGuard;", "settingSafeGuard$delegate", "showSafeMode", "getShowSafeMode", "startSucStatusAction", "getStartSucStatusAction", "titleData", "getTitleData", "wifiTipDialog", "getWifiTipDialog", "writeSettingsSafeGuard", "getWriteSettingsSafeGuard", "writeSettingsSafeGuard$delegate", "ztyDownloadCallback", "com/xuehai/launcher/guide/InitGuideViewModel$ztyDownloadCallback$1", "Lcom/xuehai/launcher/guide/InitGuideViewModel$ztyDownloadCallback$1;", "activateDeviceManager", "activateLicense", "checkAndApplyPermissions", "checkDeviceOSIsCustomSystem", "checkOSVersion", "continueToGuide", "forceAuto", "dismissWifiConnectDialog", "doLast", "downloadBusinessClient", "autoDownload", "downloadFailed", "message", "downloadOffline", "downloadCallback", "Lcom/xuehai/launcher/common/http/DownloadCallback;", "downloadOnline", "getPermissionsToRequest", "", "()[Ljava/lang/String;", "installBusinessClient", "filePath", "installFailed", "isCheckedDeviceOSFlag", "isP620InvalidSys", "onActivityResult", "requestCode", "", "resultCode", "onAppUpdated", "packageName", "onCleared", "onLicenseStateChanged", "desc", "success", "putCheckedDeviceOSFlag", "showCurrentMessage", "waiting", "log", "startGuide", "startZtyFailed", "toConnectWifi", "updateStep", "step", "Lcom/xuehai/launcher/guide/InitStep;", "Companion", "SettingSafeGuard", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class InitGuideViewModel extends AbsViewModel {
    private static final String IS_CHECKED_DEVICE_OS_FLAG = "isCheckedDeviceOSFlag";
    private CustomDialogFragment connectWifiDialog;
    private final SingleLiveEvent<Boolean> openWifiAction = new SingleLiveEvent<>();
    private final SingleLiveEvent<Boolean> needRetryAction = new SingleLiveEvent<>();
    private final MutableLiveData<List<StepProgressItem>> allStepData = new MutableLiveData<>();
    private final MutableLiveData<String> titleData = new MutableLiveData<>();
    private final SingleLiveEvent<Void> openZtyClient = new SingleLiveEvent<>();
    private final MutableLiveData<Pair<String, Boolean>> currentMessage = new MutableLiveData<>();
    private final SingleLiveEvent<Void> activateDeviceManagerAction = new SingleLiveEvent<>();
    private final MutableLiveData<Boolean> startSucStatusAction = new MutableLiveData<>();
    private final SingleLiveEvent<Set<String>> requirePermissions = new SingleLiveEvent<>();
    private final Channel<Unit> requireAppUsagePermissions = ChannelKt.Channel$default(0, null, null, 7, null);
    private final Channel<Unit> requireAppWriteSettingsPermissions = ChannelKt.Channel$default(0, null, null, 7, null);
    private final SingleLiveEvent<Void> showSafeMode = new SingleLiveEvent<>();
    private final SingleLiveEvent<CustomDialogFragment> wifiTipDialog = new SingleLiveEvent<>();

    private final Lazy settingSafeGuard = LazyKt.lazy(new Function0<SettingSafeGuard>() {
        {
            super(0);
        }

        @Override
        public final InitGuideViewModel.SettingSafeGuard invoke() {
            int i = Build.VERSION.SDK_INT;
            return new InitGuideViewModel.SettingSafeGuard(this.this$0, "android.permission.PACKAGE_USAGE_STATS");
        }
    });

    private final Lazy writeSettingsSafeGuard = LazyKt.lazy(new Function0<SettingSafeGuard>() {
        {
            super(0);
        }

        @Override
        public final InitGuideViewModel.SettingSafeGuard invoke() {
            return new InitGuideViewModel.SettingSafeGuard(this.this$0, "android.permission.WRITE_SETTINGS");
        }
    });
    private final InitGuideViewModel$ztyDownloadCallback$1 ztyDownloadCallback = new DownloadCallback() {
        @Override
        public void onFailure(int errorCode, String errorMessage) {
            super.onFailure(errorCode, errorMessage);
            LiveDataExtKt.set(this.this$0.getNeedRetryAction(), true);
            InitGuideViewModel initGuideViewModel = this.this$0;
            StringBuilder sb = new StringBuilder();
            if (errorMessage == null) {
                errorMessage = "error";
            }
            sb.append(errorMessage);
            sb.append('/');
            sb.append(errorCode);
            initGuideViewModel.downloadFailed(sb.toString());
        }

        @Override
        public void onProgress(double fileTotalSize, double fileDownSize, double speed) {
            super.onProgress(fileTotalSize, fileDownSize, speed);
            InitGuideViewModel initGuideViewModel = this.this$0;
            double d = 1.0f;
            Double.isNaN(d);
            double d2 = (d * fileDownSize) / fileTotalSize;
            double d3 = 100;
            Double.isNaN(d3);
            InitGuideViewModel.showCurrentMessage$default(initGuideViewModel, initGuideViewModel.getString(2131689575, Double.valueOf(d2 * d3), "%"), false, false, 2, null);
        }

        @Override
        public void onStart(double total) {
            super.onStart(total);
            InitGuideViewModel initGuideViewModel = this.this$0;
            InitGuideViewModel.showCurrentMessage$default(initGuideViewModel, initGuideViewModel.getString(2131689575, Float.valueOf(0.0f), "%"), false, false, 6, null);
        }

        @Override
        public void onSuccess(String message, String savePath) {
            Intrinsics.checkNotNullParameter(savePath, "savePath");
            super.onSuccess(message, savePath);
            this.this$0.installBusinessClient(savePath);
        }
    };
    private final InitGuideViewModel$onceAdminListener$1 onceAdminListener = new InitGuideViewModel$onceAdminListener$1(this);

    private final Lazy customOsVersionSet = LazyKt.lazy(new Function0<Set<? extends String>>() {
        @Override
        public final Set<? extends String> invoke() {
            String upperCase = "UP1A.231005.007.P620ZCU2AYH1_B2BF".toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            String upperCase2 = "UP1A.231005.007.P620ZCU2AYG2_B2BF".toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase2, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            String upperCase3 = "UP1A.231005.007.P620ZCU2AXI3_B2BF".toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase3, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            String upperCase4 = "RP1A.200720.012.P615CZCU3CVH2_B2BF".toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase4, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            String upperCase5 = "RP1A.200720.012.P615CZCU3CVA3_B2BF".toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase5, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            String upperCase6 = "RP1A.200720.012.P615CZCU3CUG4_B2BF".toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase6, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            String upperCase7 = "P615CZCU3CUE6_B2BF".toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase7, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            String upperCase8 = "PPR1.180610.011.P200ZCU2AWC1_B2BF".toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase8, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            String upperCase9 = "PPR1.180610.011.P200ZCU2AUH1_B2BF".toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase9, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            String upperCase10 = "PPR1.180610.011.P200ZCU2ATG3_B2BF".toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase10, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            String upperCase11 = "PPR1.180610.011.P200ZCU2ASJ3_B2BF".toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase11, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            String upperCase12 = "PPR1.180610.011.P200ZCU1ASE2_B2BF".toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase12, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            String upperCase13 = "PPR1.180610.011.P200ZCU1ASG7_B2BF".toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase13, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            String upperCase14 = "P355CZCU3BRK1".toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase14, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            String upperCase15 = "MMB29M.P355CZCU3BRK1".toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase15, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            String upperCase16 = "TB223FC_S000002_240717_XueHai".toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase16, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            String upperCase17 = "TB223FC_S000003_251119_XueHai".toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase17, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            String upperCase18 = "TB-8604F_S000025_191021_PRC_XH".toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase18, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            String upperCase19 = "TB-8604F_S000024_190531_PRC_XH".toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase19, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            String upperCase20 = "TB-8604F_S000023_190121_PRC_XH".toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase20, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            String upperCase21 = "TB-X6C6F_S000042_220105_XueHaiJiaoYu".toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase21, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            String upperCase22 = "BZT-W09 8.0.0.232(C833)".toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase22, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            String upperCase23 = "BZT-W09 8.0.0.6(C833)".toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase23, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            String upperCase24 = "BZT-W09 8.0.0.1(C833)".toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase24, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            return SetsKt.setOf((Object[]) new String[]{upperCase, upperCase2, upperCase3, upperCase4, upperCase5, upperCase6, upperCase7, upperCase8, upperCase9, upperCase10, upperCase11, upperCase12, upperCase13, upperCase14, upperCase15, upperCase16, upperCase17, upperCase18, upperCase19, upperCase20, upperCase21, upperCase22, upperCase23, upperCase24});
        }
    });

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000f\u001a\u00020\tH\u0014J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0011H\u0002J\b\u0010\u0013\u001a\u00020\u0011H\u0002J\u0010\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\tH\u0014J\u001a\u0010\u0018\u001a\u00020\u00062\b\u0010\u0019\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u001a\u001a\u00020\u0003H\u0002J\u001a\u0010\u001b\u001a\u00020\u00062\b\u0010\u0019\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u001a\u001a\u00020\u0003H\u0002J\u001a\u0010\u001c\u001a\u00020\u00062\b\u0010\u0019\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u001a\u001a\u00020\u0003H\u0002J\u001a\u0010\u001d\u001a\u00020\u00062\b\u0010\u0019\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u001a\u001a\u00020\u0003H\u0002J\b\u0010\u001e\u001a\u00020\u0011H\u0016J\u0010\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u0003H\u0002J\b\u0010!\u001a\u00020\u0006H\u0002J\u0006\u0010\"\u001a\u00020\u0011J\u0010\u0010#\u001a\u00020\u00112\b\b\u0002\u0010$\u001a\u00020\u0006J\b\u0010%\u001a\u00020\u0011H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/xuehai/launcher/guide/InitGuideViewModel$SettingSafeGuard;", "Lcom/xuehai/launcher/common/plugins/LooperTask;", "permission", "", "(Lcom/xuehai/launcher/guide/InitGuideViewModel;Ljava/lang/String;)V", "hasOpenUsagePermissionActivity", "", "hasOpenWriteSettingsPermissionActivity", "interval", "", "maxStayingMills", "monitoring", "stayInMills", "times", "", "calculateNextTimeMills", "check", "", "clearActivityTop", "disableSettingsDeferred", "getTopActivityName", "context", "Landroid/content/Context;", "initialDelay", "isBack", "pkg", "activity", "isUnSafeActivity", "isUsageAccessActivity", "maybeUnsafeActivity", "run", "showTips", "msg", "skipCheck", "start", "stop", "shouldDisableSettings", "toSafeMode", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class SettingSafeGuard extends LooperTask {
        private boolean hasOpenUsagePermissionActivity;
        private boolean hasOpenWriteSettingsPermissionActivity;
        private long interval;
        private final long maxStayingMills;
        private boolean monitoring;
        private final String permission;
        private long stayInMills;
        final InitGuideViewModel this$0;
        private int times;

        public SettingSafeGuard(InitGuideViewModel initGuideViewModel, String str) {
            Intrinsics.checkNotNullParameter(str, "permission");
            this.this$0 = initGuideViewModel;
            this.permission = str;
            this.maxStayingMills = 10000L;
            this.interval = 10L;
        }

        private final void check() throws SecurityException {
            String topAppPackageName = PolicyManager.getApplicationPolicyProxy().getTopAppPackageName();
            String topActivityName = getTopActivityName(App.INSTANCE.getInstance());
            if (System.currentTimeMillis() - this.stayInMills > this.maxStayingMills) {
                MyLog.i("[MDM]", "安全检查护卫，超时");
                showTips(this.this$0.getString(TextUtils.equals(this.permission, "android.permission.WRITE_SETTINGS") ? 2131689581 : 2131689580, Long.valueOf(this.maxStayingMills / 1000)));
                stop$default(this, false, 1, null);
                return;
            }
            if (!this.hasOpenUsagePermissionActivity && !TextUtils.equals(this.permission, "android.permission.WRITE_SETTINGS")) {
                if (isUsageAccessActivity(topAppPackageName, topActivityName)) {
                    MyLog.i("[MDM]", "安全检查护卫，打开权限申请页面");
                    showTips(this.this$0.getString(2131689578, Long.valueOf(this.maxStayingMills / 1000)));
                    this.hasOpenUsagePermissionActivity = true;
                    ThreadPlugins.runInUIThread$default(new Runnable() {
                        @Override
                        public final void run() {
                            InitGuideViewModel.SettingSafeGuard.m146check$lambda0(this.f$0);
                        }
                    }, 0L, 2, null);
                    return;
                }
                return;
            }
            if (!this.hasOpenWriteSettingsPermissionActivity && TextUtils.equals(this.permission, "android.permission.WRITE_SETTINGS")) {
                if (isUsageAccessActivity(topAppPackageName, topActivityName)) {
                    MyLog.i("[MDM]", "安全检查护卫，打开权限申请页面");
                    showTips(this.this$0.getString(2131689579, Long.valueOf(this.maxStayingMills / 1000)));
                    this.hasOpenWriteSettingsPermissionActivity = true;
                    ThreadPlugins.runInUIThread$default(new Runnable() {
                        @Override
                        public final void run() {
                            InitGuideViewModel.SettingSafeGuard.m147check$lambda1(this.f$0);
                        }
                    }, 0L, 2, null);
                    return;
                }
                return;
            }
            if (isBack(topAppPackageName, topActivityName)) {
                MyLog.i("[MDM]", "安全检查护卫, 退出：" + topAppPackageName + '/' + topActivityName);
                stop$default(this, false, 1, null);
                return;
            }
            if (!isUnSafeActivity(topAppPackageName, topActivityName)) {
                if (maybeUnsafeActivity(topAppPackageName, topActivityName)) {
                    MyLog.i("[MDM]", "安全检查护卫, 检测到打开不安全页面：" + topAppPackageName + '/' + topActivityName);
                    showTips(this.this$0.getString(2131689578, Long.valueOf(this.maxStayingMills / ((long) 1000))));
                    stop$default(this, false, 1, null);
                    return;
                }
                return;
            }
            this.times++;
            stop$default(this, false, 1, null);
            MyLog.w("Debug[MDM]", "安全检查护卫，失败，打开设备管理器页面, times=" + this.times);
            if (this.times <= 3) {
                showTips(this.this$0.getString(2131689577));
            } else {
                MyLog.w("Debug[MDM]", "安全检查护卫，失败，强制前往安全页");
                toSafeMode();
            }
        }

        private static final void m146check$lambda0(SettingSafeGuard settingSafeGuard) {
            Intrinsics.checkNotNullParameter(settingSafeGuard, "this$0");
            TaskPlugins.addLooperTask(settingSafeGuard);
        }

        private static final void m147check$lambda1(SettingSafeGuard settingSafeGuard) {
            Intrinsics.checkNotNullParameter(settingSafeGuard, "this$0");
            TaskPlugins.addLooperTask(settingSafeGuard);
        }

        private final void clearActivityTop() {
            try {
                Intent intent = new Intent(App.INSTANCE.getInstance(), (Class<?>) InitGuideActivity.class);
                intent.addFlags(335544320);
                App.INSTANCE.getInstance().startActivity(intent);
            } catch (Throwable th) {
                MyLog.INSTANCE.e("[MDM]", "安全检查护卫，清空任务栈异常", th);
            }
        }

        private final void disableSettingsDeferred() {
            clearActivityTop();
            PolicyManager.INSTANCE.disableSetting();
            PolicyManager.getRestrictionPolicyProxy().wipeRecentTasks();
            if (DeviceModelUtil.isHuaweiHEMDevice()) {
                return;
            }
            ApplicationPolicyProxy applicationPolicyProxy = PolicyManager.getApplicationPolicyProxy();
            applicationPolicyProxy.stopApp("com.android.settings");
            applicationPolicyProxy.stopApp("com.samsung.android.permissioncontroller");
            applicationPolicyProxy.stopApp("com.samsung.android.forest");
        }

        private final String getTopActivityName(Context context) throws SecurityException {
            ComponentName componentName;
            Object systemService = context.getSystemService("activity");
            if (systemService == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.app.ActivityManager");
            }
            List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) systemService).getRunningTasks(1);
            Intrinsics.checkNotNullExpressionValue(runningTasks, "activityManager.getRunningTasks(1)");
            ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo) CollectionsKt.firstOrNull((List) runningTasks);
            String className = (runningTaskInfo == null || (componentName = runningTaskInfo.topActivity) == null) ? null : componentName.getClassName();
            return className == null ? "" : className;
        }

        private final boolean isBack(String pkg, String activity) {
            return Intrinsics.areEqual(activity, "com.xuehai.launcher.guide.InitGuideActivity") || Intrinsics.areEqual(pkg, ClientConfig.INSTANCE.getPackageName());
        }

        private final boolean isUnSafeActivity(String pkg, String activity) {
            return Intrinsics.areEqual(activity, "com.android.settings.DeviceAdminAdd");
        }

        private final boolean isUsageAccessActivity(String pkg, String activity) {
            if (Intrinsics.areEqual(pkg, "com.android.settings")) {
                return true;
            }
            if (TextUtils.equals(this.permission, "android.permission.WRITE_SETTINGS")) {
                if (Intrinsics.areEqual(Build.MODEL, "SM-P620")) {
                    return Intrinsics.areEqual(activity, "com.android.settings.Settings$AppWriteSettingsActivity");
                }
                return true;
            }
            String str = Build.MODEL;
            if (str == null) {
                return true;
            }
            switch (str.hashCode()) {
                case -1398169867:
                    if (!str.equals("SM-P200")) {
                        return true;
                    }
                    return !Intrinsics.areEqual(activity, "com.android.settings.Settings$UsageAccessSettingsActivity") || Intrinsics.areEqual(activity, "com.android.settings.SubSettings");
                case -1398168751:
                    if (!str.equals("SM-P350")) {
                        return true;
                    }
                    if (Intrinsics.areEqual(activity, "com.android.settings.Settings$UsageAccessSettingsActivity")) {
                        return true;
                    }
                case -1398165961:
                    if (!str.equals("SM-P620")) {
                        return true;
                    }
                    return Intrinsics.areEqual(activity, "com.android.settings.Settings$AppUsageAccessSettingsActivity");
                case -393558099:
                    if (!str.equals("SM-P355C")) {
                        return true;
                    }
                    if (Intrinsics.areEqual(activity, "com.android.settings.Settings$UsageAccessSettingsActivity")) {
                    }
                    break;
                case -393472570:
                    if (!str.equals("SM-P615C")) {
                        return true;
                    }
                    return Intrinsics.areEqual(activity, "com.android.settings.Settings$AppUsageAccessSettingsActivity");
                default:
                    return true;
            }
        }

        private final boolean maybeUnsafeActivity(String pkg, String activity) {
            return !isUsageAccessActivity(pkg, activity);
        }

        private final void showTips(final String msg) {
            final InitGuideViewModel initGuideViewModel = this.this$0;
            ThreadPlugins.runInUIThread$default(new Runnable() {
                @Override
                public final void run() {
                    InitGuideViewModel.SettingSafeGuard.m149showTips$lambda2(initGuideViewModel, msg);
                }
            }, 0L, 2, null);
        }

        private static final void m149showTips$lambda2(InitGuideViewModel initGuideViewModel, String str) {
            Intrinsics.checkNotNullParameter(initGuideViewModel, "this$0");
            Intrinsics.checkNotNullParameter(str, "$msg");
            LiveDataExtKt.set(initGuideViewModel.getShowMessage(), str);
        }

        private final boolean skipCheck() {
            return ClientConfig.INSTANCE.isTeacherClient();
        }

        public static void stop$default(SettingSafeGuard settingSafeGuard, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = true;
            }
            settingSafeGuard.stop(z);
        }

        private final void toSafeMode() {
            CrashLevelManager.INSTANCE.simulateFatalCrash();
            LiveDataExtKt.action(this.this$0.getShowSafeMode());
        }

        @Override
        protected long calculateNextTimeMills() {
            return this.interval - (System.currentTimeMillis() % this.interval);
        }

        @Override
        protected long initialDelay() {
            return 0L;
        }

        @Override
        public void run() {
            try {
                check();
            } catch (Throwable th) {
                MyLog.INSTANCE.e("[MDM]", "安全检查护卫，异常", th);
                stop(false);
            }
        }

        public final void start() {
            if (this.monitoring || skipCheck()) {
                return;
            }
            MyLog.i("[MDM]", "安全检查护卫， 启用设置，开始监听顶部应用变化");
            this.monitoring = true;
            if (TextUtils.equals(this.permission, "android.permission.WRITE_SETTINGS")) {
                this.hasOpenWriteSettingsPermissionActivity = false;
            } else {
                this.hasOpenUsagePermissionActivity = false;
            }
            this.stayInMills = System.currentTimeMillis();
            showTips(this.this$0.getString(TextUtils.equals(this.permission, "android.permission.WRITE_SETTINGS") ? 2131689579 : 2131689578, Long.valueOf(this.maxStayingMills / 1000)));
            TaskPlugins.addLooperTask(this);
            PolicyManager.INSTANCE.enableSettingForUsagePermission();
        }

        public final void stop(boolean shouldDisableSettings) {
            if (this.monitoring) {
                MyLog.i("[MDM]", "安全检查护卫，禁用设置，停止监听顶部应用变化");
                TaskPlugins.removeLooperTask(this);
                if (shouldDisableSettings) {
                    disableSettingsDeferred();
                }
                this.monitoring = false;
            }
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.xuehai.launcher.guide.InitGuideViewModel$doLast$1", f = "InitGuideViewModel.kt", i = {}, l = {312}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final boolean $forceAuto;
        int label;

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.xuehai.launcher.guide.InitGuideViewModel$doLast$1$1", f = "InitGuideViewModel.kt", i = {}, l = {315, 322}, m = "invokeSuspend", n = {}, s = {})
        static final class C00241 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final boolean $forceAuto;
            int label;
            final InitGuideViewModel this$0;

            C00241(InitGuideViewModel initGuideViewModel, boolean z, Continuation<? super C00241> continuation) {
                super(2, continuation);
                this.this$0 = initGuideViewModel;
                this.$forceAuto = z;
            }

            @Override
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00241(this.this$0, this.$forceAuto, continuation);
            }

            @Override
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00241) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override
            public final Object invokeSuspend(Object obj) throws Throwable {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    if (!SettingManager.INSTANCE.checkAppUsagePermission()) {
                        this.label = 1;
                        if (this.this$0.getRequireAppUsagePermissions().send(Unit.INSTANCE, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        MyLog.i("Debug[MDM]", "checkAppUsagePermission");
                        InitGuideViewModel initGuideViewModel = this.this$0;
                        InitGuideViewModel.showCurrentMessage$default(initGuideViewModel, initGuideViewModel.getString(2131689556), false, false, 6, null);
                        LiveDataExtKt.set(this.this$0.getNeedRetryAction(), Boxing.boxBoolean(true));
                    } else if (!SettingManager.INSTANCE.checkAppWriteSettingPermission()) {
                        this.label = 2;
                        if (this.this$0.getRequireAppWriteSettingsPermissions().send(Unit.INSTANCE, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        MyLog.i("Debug[MDM]", "checkAppWriteSettingPermission");
                        InitGuideViewModel initGuideViewModel2 = this.this$0;
                        InitGuideViewModel.showCurrentMessage$default(initGuideViewModel2, initGuideViewModel2.getString(2131689556), false, false, 6, null);
                        LiveDataExtKt.set(this.this$0.getNeedRetryAction(), Boxing.boxBoolean(true));
                    } else if (!this.this$0.checkAndApplyPermissions()) {
                        MyLog.i("Debug[MDM]", "请求runtime permissions");
                        InitGuideViewModel initGuideViewModel3 = this.this$0;
                        InitGuideViewModel.showCurrentMessage$default(initGuideViewModel3, initGuideViewModel3.getString(2131689556), false, false, 6, null);
                        LiveDataExtKt.set(this.this$0.getNeedRetryAction(), Boxing.boxBoolean(true));
                    } else if (ZtyClientUtil.INSTANCE.isZtyClientInstalled()) {
                        MyLog.i("Debug[MDM]", "智通平台已安装");
                        LiveDataExtKt.action(this.this$0.getOpenZtyClient());
                    } else {
                        this.this$0.updateStep(InitStep.ZTY_DOWNLOADING);
                        InitGuideViewModel initGuideViewModel4 = this.this$0;
                        InitGuideViewModel.showCurrentMessage$default(initGuideViewModel4, initGuideViewModel4.getString(2131689574), false, false, 6, null);
                        String unMatchedZtyClient = ZtyClientUtil.INSTANCE.getUnMatchedZtyClient();
                        PolicyManager.getApplicationPolicyProxy().setApplicationUninstallationEnabled(unMatchedZtyClient);
                        PolicyManager.getApplicationPolicyProxy().uninstallApplication(unMatchedZtyClient);
                        this.this$0.downloadBusinessClient(this.$forceAuto);
                    }
                } else if (i == 1) {
                    ResultKt.throwOnFailure(obj);
                    MyLog.i("Debug[MDM]", "checkAppUsagePermission");
                    InitGuideViewModel initGuideViewModel5 = this.this$0;
                    InitGuideViewModel.showCurrentMessage$default(initGuideViewModel5, initGuideViewModel5.getString(2131689556), false, false, 6, null);
                    LiveDataExtKt.set(this.this$0.getNeedRetryAction(), Boxing.boxBoolean(true));
                } else {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    MyLog.i("Debug[MDM]", "checkAppWriteSettingPermission");
                    InitGuideViewModel initGuideViewModel22 = this.this$0;
                    InitGuideViewModel.showCurrentMessage$default(initGuideViewModel22, initGuideViewModel22.getString(2131689556), false, false, 6, null);
                    LiveDataExtKt.set(this.this$0.getNeedRetryAction(), Boxing.boxBoolean(true));
                }
                return Unit.INSTANCE;
            }
        }

        AnonymousClass1(boolean z, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$forceAuto = z;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return InitGuideViewModel.this.new AnonymousClass1(this.$forceAuto, continuation);
        }

        @Override
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PolicyManagerExtKt.tryInitialize(PolicyManager.INSTANCE);
                this.label = 1;
                if (BuildersKt.withContext(ThreadPlugins.INSTANCE.getInstallExecutorStub().getCoroutineDispatcher(), new C00241(InitGuideViewModel.this, this.$forceAuto, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    private final void activateDeviceManager() {
        LiveDataExtKt.set(this.needRetryAction, false);
        showCurrentMessage$default(this, getString(2131689502), false, false, 6, null);
        updateStep(InitStep.ACTIVATE_DEVICE_MANAGER);
        AdminReceiver.addListener(this.onceAdminListener);
        ThreadPlugins.runInUIThread(new Runnable() {
            @Override
            public final void run() {
                InitGuideViewModel.m141activateDeviceManager$lambda0(this.f$0);
            }
        }, 1000L);
    }

    private static final void m141activateDeviceManager$lambda0(InitGuideViewModel initGuideViewModel) {
        Intrinsics.checkNotNullParameter(initGuideViewModel, "this$0");
        LiveDataExtKt.action(initGuideViewModel.activateDeviceManagerAction);
    }

    private final void activateLicense() {
        showCurrentMessage$default(this, getString(2131689505), true, false, 4, null);
        updateStep(InitStep.ACTIVATE_LICENSE);
        if (SessionData.INSTANCE.isOnline()) {
            DeviceActiveManager.INSTANCE.activateSDK();
        } else {
            showCurrentMessage$default(this, getString(2131689503, "当前无网络连接"), false, false, 6, null);
            toConnectWifi();
        }
    }

    private final boolean checkAndApplyPermissions() {
        Set mutableSet = ArraysKt.toMutableSet(getPermissionsToRequest());
        if (DeviceModelUtil.isHuaweiHEMDevice() && Build.VERSION.SDK_INT >= 31) {
            mutableSet.add("com.android.permission.GET_INSTALLED_APPS");
        }
        Set set = mutableSet;
        PolicyManager.getApplicationPolicyProxy().applyRuntimePermissions(ClientConfig.INSTANCE.getPackageName(), CollectionsKt.toList(PermissionManager.INSTANCE.getDeniedPermission(set)));
        Set<String> deniedPermission = PermissionManager.INSTANCE.getDeniedPermission(set);
        if (deniedPermission.isEmpty()) {
            return true;
        }
        LiveDataExtKt.set(this.requirePermissions, deniedPermission);
        return false;
    }

    private final void checkOSVersion() {
        showCurrentMessage$default(this, getString(2131689501), false, false, 6, null);
        updateStep(InitStep.ACTIVATE_DEVICE_MANAGER);
        NetStore.INSTANCE.checkOSVersion().subscribe(new MyObserver<Boolean>(getCompositeDisposable()) {
            @Override
            public void onError(Throwable e) {
                Intrinsics.checkNotNullParameter(e, "e");
                super.onError(e);
                LiveDataExtKt.set(InitGuideViewModel.this.getNeedRetryAction(), true);
            }

            @Override
            public void onNext(Object obj) {
                onNext(((Boolean) obj).booleanValue());
            }

            public void onNext(boolean result) {
                super.onNext((AnonymousClass1) Boolean.valueOf(result));
                if (result) {
                    InitGuideViewModel.this.activateDeviceManager();
                } else {
                    LiveDataExtKt.set(InitGuideViewModel.this.getNeedRetryAction(), true);
                }
            }
        });
    }

    public static void continueToGuide$default(InitGuideViewModel initGuideViewModel, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        initGuideViewModel.continueToGuide(z);
    }

    private final void doLast(boolean forceAuto) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(forceAuto, null), 3, null);
    }

    private final void downloadBusinessClient(boolean autoDownload) {
        boolean zNeedUseInnerApk = ZtyClientUtil.INSTANCE.needUseInnerApk();
        if (!zNeedUseInnerApk && !autoDownload) {
            MyLog.i("[MDM]", "在线安装智通平台" + zNeedUseInnerApk + ", " + autoDownload);
            LiveDataExtKt.set(this.needRetryAction, true);
            return;
        }
        if (zNeedUseInnerApk) {
            downloadOffline(this.ztyDownloadCallback);
        } else if (SessionData.INSTANCE.isOnline()) {
            downloadOnline(this.ztyDownloadCallback);
        } else {
            downloadFailed("当前无网络连接");
            toConnectWifi();
        }
    }

    static void downloadBusinessClient$default(InitGuideViewModel initGuideViewModel, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        initGuideViewModel.downloadBusinessClient(z);
    }

    private final void downloadOffline(DownloadCallback downloadCallback) {
        ZtyClientUtil.INSTANCE.downloadOffline(downloadCallback).subscribe(new MyObserver<Object>(getCompositeDisposable()) {
            @Override
            public void onError(Throwable e) {
                Intrinsics.checkNotNullParameter(e, "e");
                InitGuideViewModel initGuideViewModel = InitGuideViewModel.this;
                String message = e.getMessage();
                if (message == null) {
                    message = "发生未知异常";
                }
                initGuideViewModel.downloadFailed(message);
            }
        });
    }

    private final void downloadOnline(final DownloadCallback downloadCallback) {
        NetStore.INSTANCE.checkDevice(App.INSTANCE.getInstance().getDeviceId()).subscribe(new MyObserver<CheckDeviceResponse>() {
            {
                super(null, 1, null);
            }

            @Override
            public void onError(Throwable e) {
                Intrinsics.checkNotNullParameter(e, "e");
                super.onError(e);
                InitGuideViewModel initGuideViewModel = InitGuideViewModel.this;
                String message = e.getMessage();
                if (message == null) {
                    message = "请求设备校验失败";
                }
                initGuideViewModel.downloadFailed(message);
            }

            @Override
            public void onNext(CheckDeviceResponse result) {
                Intrinsics.checkNotNullParameter(result, "result");
                super.onNext((AnonymousClass1) result);
                if (!result.isValid()) {
                    String str = "非学海设备！请将设备号提供给运营，进行设备入库。\n设备号: " + App.INSTANCE.getInstance().getDeviceId();
                    InitGuideViewModel.this.downloadFailed(str);
                    LiveDataExtKt.set(InitGuideViewModel.this.getTipDialog(), DialogProvider.Builder.positive$default(new DialogProvider.Builder().title("智通云下载失败").message(str, 8388627).cancelable(false), InitGuideViewModel.this.getString(2131689542), null, 2, null));
                    return;
                }
                if (result.getSystemIsValid()) {
                    Observable<String> observableDownloadOnline = ZtyClientUtil.INSTANCE.downloadOnline(downloadCallback);
                    final CompositeDisposable compositeDisposable = InitGuideViewModel.this.getCompositeDisposable();
                    final InitGuideViewModel initGuideViewModel = InitGuideViewModel.this;
                    observableDownloadOnline.subscribe(new MyObserver<Object>(compositeDisposable) {
                        @Override
                        public void onError(Throwable e) {
                            Intrinsics.checkNotNullParameter(e, "e");
                            InitGuideViewModel initGuideViewModel2 = initGuideViewModel;
                            String message = e.getMessage();
                            if (message == null) {
                                message = "发生未知异常";
                            }
                            initGuideViewModel2.downloadFailed(message);
                        }
                    });
                    return;
                }
                SingleLiveEvent<DialogProvider.Builder> tipDialog = InitGuideViewModel.this.getTipDialog();
                DialogProvider.Builder builderMessage$default = DialogProvider.Builder.message$default(new DialogProvider.Builder(), "当前安装包为非定制包\n是否继续安装", 0, 2, null);
                final InitGuideViewModel initGuideViewModel2 = InitGuideViewModel.this;
                DialogProvider.Builder builderPositive = builderMessage$default.positive("取消安装", new Function1<View, Unit>() {
                    {
                        super(1);
                    }

                    @Override
                    public Unit invoke(View view) {
                        invoke2(view);
                        return Unit.INSTANCE;
                    }

                    public final void invoke2(View view) {
                        Intrinsics.checkNotNullParameter(view, "it");
                        LiveDataExtKt.set(initGuideViewModel2.getNeedRetryAction(), true);
                    }
                });
                final DownloadCallback downloadCallback2 = downloadCallback;
                final InitGuideViewModel initGuideViewModel3 = InitGuideViewModel.this;
                LiveDataExtKt.set(tipDialog, builderPositive.negative("继续安装", new Function1<View, Unit>() {
                    {
                        super(1);
                    }

                    @Override
                    public Unit invoke(View view) {
                        invoke2(view);
                        return Unit.INSTANCE;
                    }

                    public final void invoke2(View view) {
                        Intrinsics.checkNotNullParameter(view, "it");
                        Observable<String> observableDownloadOnline2 = ZtyClientUtil.INSTANCE.downloadOnline(downloadCallback2);
                        CompositeDisposable compositeDisposable2 = initGuideViewModel3.getCompositeDisposable();
                        final InitGuideViewModel initGuideViewModel4 = initGuideViewModel3;
                        observableDownloadOnline2.subscribe(new MyObserver<Object>(compositeDisposable2) {
                            @Override
                            public void onError(Throwable e) {
                                Intrinsics.checkNotNullParameter(e, "e");
                                InitGuideViewModel initGuideViewModel5 = initGuideViewModel4;
                                String message = e.getMessage();
                                if (message == null) {
                                    message = "发生未知异常";
                                }
                                initGuideViewModel5.downloadFailed(message);
                            }
                        });
                    }
                }));
            }
        });
    }

    private final Set<String> getCustomOsVersionSet() {
        return (Set) this.customOsVersionSet.getValue();
    }

    private final void installBusinessClient(final String filePath) {
        MyLog.i("[MDM]", "installBusinessClient : thread :" + Thread.currentThread().getName());
        updateStep(InitStep.ZTY_INSTALLING);
        showCurrentMessage$default(this, getString(2131689584), true, false, 4, null);
        final String matchedZtyClient = ZtyClientUtil.INSTANCE.getMatchedZtyClient();
        ThreadPlugins.runInInstallThread(new Runnable() {
            @Override
            public final void run() {
                InitGuideViewModel.m142installBusinessClient$lambda3(matchedZtyClient, filePath, this);
            }
        });
    }

    private static final void m142installBusinessClient$lambda3(final String str, final String str2, final InitGuideViewModel initGuideViewModel) {
        Intrinsics.checkNotNullParameter(str, "$packageName");
        Intrinsics.checkNotNullParameter(str2, "$filePath");
        Intrinsics.checkNotNullParameter(initGuideViewModel, "this$0");
        AppInstallManager.INSTANCE.addToInstallPool(new AppInstallManager.AppUpdateTask(str, str2, new Function1<AppInstallManager.InstallError, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(AppInstallManager.InstallError installError) {
                invoke2(installError);
                return Unit.INSTANCE;
            }

            public final void invoke2(AppInstallManager.InstallError installError) {
                Intrinsics.checkNotNullParameter(installError, "it");
                if (Intrinsics.areEqual(installError, AppInstallManager.InstallError.ApkFileError.INSTANCE)) {
                    LiveDataExtKt.set(this.this$0.getNeedRetryAction(), true);
                    InitGuideViewModel initGuideViewModel2 = this.this$0;
                    InitGuideViewModel.showCurrentMessage$default(initGuideViewModel2, initGuideViewModel2.getString(2131689582, "安装文件异常"), false, false, 6, null);
                    FileUtil.deleteFile(new File(str2));
                    return;
                }
                if (Intrinsics.areEqual(installError, AppInstallManager.InstallError.InstalledError.INSTANCE)) {
                    this.this$0.onAppUpdated(str);
                } else if (Intrinsics.areEqual(installError, AppInstallManager.InstallError.DowngradeError.INSTANCE)) {
                    this.this$0.onAppUpdated(str);
                } else if (Intrinsics.areEqual(installError, AppInstallManager.InstallError.ExecuteError.INSTANCE)) {
                    this.this$0.installFailed();
                }
            }
        }));
    }

    private final void installFailed() {
        LiveDataExtKt.set(this.needRetryAction, true);
        showCurrentMessage$default(this, getString(2131689582, "执行安装发生错误"), false, false, 6, null);
        PolicyManager.getApplicationPolicyProxy().setApplicationUninstallationEnabled(ZtyClientUtil.INSTANCE.getMatchedZtyClient());
        PolicyManager.getApplicationPolicyProxy().uninstallApplication(ZtyClientUtil.INSTANCE.getMatchedZtyClient());
    }

    private final boolean isCheckedDeviceOSFlag() {
        return XHSPUtil.getBoolean("isCheckedDeviceOSFlag");
    }

    private static final void m145onAppUpdated$lambda4(InitGuideViewModel initGuideViewModel) {
        Intrinsics.checkNotNullParameter(initGuideViewModel, "this$0");
        LiveDataExtKt.action(initGuideViewModel.openZtyClient);
    }

    private final void showCurrentMessage(String message, boolean waiting, boolean log) {
        if (log) {
            MyLog.i("[MDM]", message + "; waiting: " + waiting);
        }
        LiveDataExtKt.set(this.currentMessage, new Pair(message, Boolean.valueOf(waiting)));
    }

    static void showCurrentMessage$default(InitGuideViewModel initGuideViewModel, String str, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = true;
        }
        initGuideViewModel.showCurrentMessage(str, z, z2);
    }

    private final void toConnectWifi() {
        CustomDialogFragment customDialogFragmentBuild = DialogProvider.Builder.message$default(new DialogProvider.Builder(), getString(2131689546), 0, 2, null).cancelable(false).positive(" 连接网络 ", new Function1<View, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            public final void invoke2(View view) {
                Intrinsics.checkNotNullParameter(view, "it");
                LiveDataExtKt.action(InitGuideViewModel.this.getOpenWifiAction());
                InitGuideViewModel.this.dismissWifiConnectDialog();
            }
        }).build();
        this.connectWifiDialog = customDialogFragmentBuild;
        LiveDataExtKt.set(this.wifiTipDialog, customDialogFragmentBuild);
        LiveDataExtKt.set(this.needRetryAction, true);
    }

    private final void updateStep(InitStep step) {
        ArrayList arrayListArrayListOf;
        MutableLiveData<List<StepProgressItem>> mutableLiveData = this.allStepData;
        if (step.getStep() < InitStep.ZTY_DOWNLOADING.getStep()) {
            LiveDataExtKt.set(this.titleData, getString(2131689516));
            arrayListArrayListOf = CollectionsKt.arrayListOf(InitStep.ACTIVATE_DEVICE_MANAGER, InitStep.ACTIVATE_LICENSE, InitStep.ACTIVATE_FINISH);
        } else {
            LiveDataExtKt.set(this.titleData, getString(2131689585));
            arrayListArrayListOf = CollectionsKt.arrayListOf(InitStep.ZTY_DOWNLOADING, InitStep.ZTY_INSTALLING, InitStep.ZTY_FINISH);
        }
        ArrayList arrayList = arrayListArrayListOf;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            StepProgressItem stepProgressItem = new StepProgressItem((InitStep) it.next());
            stepProgressItem.updateState(step);
            arrayList2.add(stepProgressItem);
        }
        LiveDataExtKt.set(mutableLiveData, CollectionsKt.toList(arrayList2));
    }

    public final boolean checkDeviceOSIsCustomSystem() {
        if (ClientConfig.INSTANCE.isTeacherClient() || DeviceModelUtil.isHuaweiHEMDevice()) {
            return true;
        }
        if (!Intrinsics.areEqual(Build.MODEL, "SM-P620") && isCheckedDeviceOSFlag()) {
            return true;
        }
        String str = Build.DISPLAY;
        Set<String> customOsVersionSet = getCustomOsVersionSet();
        Intrinsics.checkNotNullExpressionValue(str, "customSystemOS");
        String upperCase = str.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
        boolean zContains = customOsVersionSet.contains(upperCase);
        StringBuilder sb = new StringBuilder();
        sb.append("检测当前设备系统版本：");
        sb.append(str);
        sb.append((char) 65307);
        sb.append(zContains ? "是" : "非");
        sb.append("定制的系统");
        MyLog.i("[MDM]", sb.toString());
        return zContains;
    }

    public final void continueToGuide(boolean forceAuto) {
        LiveDataExtKt.set(this.needRetryAction, false);
        LiveDataExtKt.set(this.startSucStatusAction, true);
        if (isP620InvalidSys()) {
            return;
        }
        if (!DeviceActiveManager.INSTANCE.isAdminActive(App.INSTANCE.getInstance())) {
            if (forceAuto) {
                activateDeviceManager();
                return;
            } else {
                checkOSVersion();
                return;
            }
        }
        if (!DeviceActiveManager.INSTANCE.isSDKActivated()) {
            activateLicense();
            return;
        }
        if (PolicyManager.INSTANCE.checkMdmPermission(App.INSTANCE.getInstance())) {
            doLast(forceAuto);
            return;
        }
        showCurrentMessage$default(this, getString(2131689504) + ", 等待权限初始化", true, false, 4, null);
        updateStep(InitStep.ACTIVATE_FINISH);
    }

    public final void dismissWifiConnectDialog() {
        Unit unit;
        try {
            try {
                CustomDialogFragment customDialogFragment = this.connectWifiDialog;
                if (customDialogFragment != null) {
                    customDialogFragment.dismiss();
                }
            } catch (Throwable th) {
                Result.Companion companion = Result.INSTANCE;
                Result.m228constructorimpl(ResultKt.createFailure(th));
            }
        } catch (Throwable unused) {
            Result.Companion companion2 = Result.INSTANCE;
            CustomDialogFragment customDialogFragment2 = this.connectWifiDialog;
            if (customDialogFragment2 != null) {
                customDialogFragment2.dismissAllowingStateLoss();
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            Result.m228constructorimpl(unit);
        }
    }

    public final void downloadFailed(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        LiveDataExtKt.set(this.needRetryAction, true);
        String string = getString(2131689573, message);
        MyLog.w("Application[MDM]", string);
        showCurrentMessage$default(this, string, false, false, 6, null);
    }

    public final SingleLiveEvent<Void> getActivateDeviceManagerAction() {
        return this.activateDeviceManagerAction;
    }

    public final MutableLiveData<List<StepProgressItem>> getAllStepData() {
        return this.allStepData;
    }

    public final MutableLiveData<Pair<String, Boolean>> getCurrentMessage() {
        return this.currentMessage;
    }

    public final SingleLiveEvent<Boolean> getNeedRetryAction() {
        return this.needRetryAction;
    }

    public final SingleLiveEvent<Boolean> getOpenWifiAction() {
        return this.openWifiAction;
    }

    public final SingleLiveEvent<Void> getOpenZtyClient() {
        return this.openZtyClient;
    }

    public final String[] getPermissionsToRequest() {
        return new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_PHONE_STATE"};
    }

    public final Channel<Unit> getRequireAppUsagePermissions() {
        return this.requireAppUsagePermissions;
    }

    public final Channel<Unit> getRequireAppWriteSettingsPermissions() {
        return this.requireAppWriteSettingsPermissions;
    }

    public final SingleLiveEvent<Set<String>> getRequirePermissions() {
        return this.requirePermissions;
    }

    public final SettingSafeGuard getSettingSafeGuard() {
        return (SettingSafeGuard) this.settingSafeGuard.getValue();
    }

    public final SingleLiveEvent<Void> getShowSafeMode() {
        return this.showSafeMode;
    }

    public final MutableLiveData<Boolean> getStartSucStatusAction() {
        return this.startSucStatusAction;
    }

    public final MutableLiveData<String> getTitleData() {
        return this.titleData;
    }

    public final SingleLiveEvent<CustomDialogFragment> getWifiTipDialog() {
        return this.wifiTipDialog;
    }

    public final SettingSafeGuard getWriteSettingsSafeGuard() {
        return (SettingSafeGuard) this.writeSettingsSafeGuard.getValue();
    }

    public final boolean isP620InvalidSys() {
        return (checkDeviceOSIsCustomSystem() ^ true) && Intrinsics.areEqual(Build.MODEL, "SM-P620");
    }

    public final void onActivityResult(int requestCode, int resultCode) {
        MyLog.i("[MDM]", "requestCode = " + requestCode + ";resultCode = resultCode");
        if (requestCode == 9) {
            if (resultCode == -1) {
                this.onceAdminListener.onDeviceAdminStateChanged(true);
            } else {
                if (resultCode != 0) {
                    return;
                }
                this.onceAdminListener.onDeviceAdminStateChanged(false);
            }
        }
    }

    public final void onAppUpdated(String packageName) {
        long j;
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        if (ZtyClientUtil.INSTANCE.isZtyClient(packageName)) {
            LiveDataExtKt.set(this.needRetryAction, false);
            updateStep(InitStep.ZTY_FINISH);
            showCurrentMessage$default(this, getString(2131689583), false, false, 6, null);
            if (DeviceModelUtil.isHuaweiHEMDevice() && Intrinsics.areEqual(packageName, "com.xh.zhitongyunstu")) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(packageName);
                PolicyManager.getApplicationPolicyProxy().addRuntimePermissionFixAppList(arrayList);
                j = 5000;
            } else {
                j = 1000;
            }
            ThreadPlugins.runInUIThread(new Runnable() {
                @Override
                public final void run() {
                    InitGuideViewModel.m145onAppUpdated$lambda4(this.f$0);
                }
            }, j);
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        DownloadManager.stopAll();
        AdminReceiver.removeListener(this.onceAdminListener);
    }

    public final void onLicenseStateChanged(String desc, boolean success) {
        Intrinsics.checkNotNullParameter(desc, "desc");
        if (success) {
            continueToGuide(true);
            return;
        }
        LiveDataExtKt.set(this.needRetryAction, true);
        showCurrentMessage$default(this, getString(2131689503, desc), false, false, 6, null);
        NetStore.INSTANCE.requestServerTimestamp().subscribe(new MyObserver(getCompositeDisposable()));
    }

    public final void putCheckedDeviceOSFlag() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        XHSPUtil.put("isCheckedDeviceOSFlag", true);
    }

    public final void startGuide() {
        MyLog.i("[MDM]", "开始引导");
        continueToGuide$default(this, false, 1, null);
    }

    public final void startZtyFailed() {
        showCurrentMessage$default(this, getString(2131689586), false, false, 4, null);
        LiveDataExtKt.set(this.needRetryAction, true);
        LiveDataExtKt.set(this.startSucStatusAction, false);
    }
}
