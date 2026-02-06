package com.xuehai.launcher.guide;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import com.xh.xhcore.common.util.XHAppUtil;
import com.xuehai.launcher.R;
import com.xuehai.launcher.common.base.AbsActivity;
import com.xuehai.launcher.common.config.ClientConfig;
import com.xuehai.launcher.common.ext.MyViewModelLazy;
import com.xuehai.launcher.common.ext.ViewModelFactoryKt;
import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.launcher.common.network.NetworkManger;
import com.xuehai.launcher.common.network.NetworkType;
import com.xuehai.launcher.common.util.ActivityUtil;
import com.xuehai.launcher.common.util.DeviceActiveManager;
import com.xuehai.launcher.common.util.LauncherSPUtil;
import com.xuehai.launcher.common.widget.dialog.CustomDialogFragment;
import com.xuehai.launcher.common.widget.dialog.DialogProvider;
import com.xuehai.launcher.device.DeviceInfoActivity;
import com.xuehai.launcher.guide.InitGuideViewModel;
import com.xuehai.launcher.other.OtherActivity;
import com.xuehai.launcher.receiver.PackageReceiver;
import com.xuehai.launcher.safe.SafeActivity;
import com.xuehai.launcher.util.SettingManager;
import com.xuehai.launcher.util.ZtyClientUtil;
import com.xuehai.launcher.util.ZtyFlagManager;
import com.xuehai.launcher.widget.LoadingTextView;
import com.xuehai.launcher.wifi.WifiViewModel;
import com.xuehai.system.common.appusage.AppUsageHelper;
import com.xuehai.system.mdm.proxy.LicenseReceiver;
import com.xuehai.system.mdm.proxy.PolicyManager;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ChannelIterator;

@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u000e\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020 J\b\u0010!\u001a\u00020\u001dH\u0016J\b\u0010\"\u001a\u00020#H\u0014J\u0013\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00180%H\u0016¢\u0006\u0002\u0010&J\b\u0010'\u001a\u00020#H\u0014J\b\u0010(\u001a\u00020\u001dH\u0014J\b\u0010)\u001a\u00020\u001dH\u0014J\"\u0010*\u001a\u00020\u00162\u0006\u0010+\u001a\u00020#2\u0006\u0010,\u001a\u00020#2\b\u0010-\u001a\u0004\u0018\u00010\u0007H\u0014J\u0010\u0010.\u001a\u00020\u00162\u0006\u0010/\u001a\u000200H\u0016J\u0012\u00101\u001a\u00020\u00162\b\u00102\u001a\u0004\u0018\u000103H\u0014J\b\u00104\u001a\u00020\u0016H\u0014J\u0018\u00105\u001a\u00020\u001d2\u0006\u00106\u001a\u00020#2\u0006\u00107\u001a\u000208H\u0016J\u0018\u00109\u001a\u00020\u00162\u0006\u0010:\u001a\u00020\u00182\u0006\u0010;\u001a\u00020\u001dH\u0016J\u000e\u0010<\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020 J\u001e\u0010=\u001a\u00020\u00162\u0014\u0010>\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u001d\u0018\u00010?H\u0002J\u0006\u0010@\u001a\u00020\u0016J\u0012\u0010A\u001a\u00020\u00162\b\b\u0002\u0010B\u001a\u00020\u001dH\u0002R\u001c\u0010\u0005\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0012\u0010\u0013¨\u0006C"}, d2 = {"Lcom/xuehai/launcher/guide/InitGuideActivity;", "Lcom/xuehai/launcher/common/base/AbsActivity;", "Lcom/xuehai/system/mdm/proxy/LicenseReceiver$LicenseListener;", "Lcom/xuehai/launcher/receiver/PackageReceiver$PackageListener;", "()V", "appUsagePermissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "appWriteSettingsPermissionLauncher", "initGuideViewModel", "Lcom/xuehai/launcher/guide/InitGuideViewModel;", "getInitGuideViewModel", "()Lcom/xuehai/launcher/guide/InitGuideViewModel;", "initGuideViewModel$delegate", "Lkotlin/Lazy;", "wifiViewModel", "Lcom/xuehai/launcher/wifi/WifiViewModel;", "getWifiViewModel", "()Lcom/xuehai/launcher/wifi/WifiViewModel;", "wifiViewModel$delegate", "afterAppAdded", "", "packageName", "", "afterAppRemoved", "afterAppReplaced", "afterPermissionGranted", "isNew", "", "authenticationNetwork", "view", "Landroid/view/View;", "autoSetupPermission", "getNavBarColor", "", "getPermissionsToRequest", "", "()[Ljava/lang/String;", "getStatusBarColor", "isFullScreen", "isLightStatusBar", "onActivityResult", "requestCode", "resultCode", "data", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onKeyDown", "keyCode", "event", "Landroid/view/KeyEvent;", "onLicenseStateChanged", "desc", "success", "openWifi", "showGuideTip", "pair", "Lkotlin/Pair;", "showInvalidSystemDialog", "showOrHideRetryView", "boolean", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class InitGuideActivity extends AbsActivity implements LicenseReceiver.LicenseListener, PackageReceiver.PackageListener {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private final ActivityResultLauncher<Intent> appUsagePermissionLauncher;
    private final ActivityResultLauncher<Intent> appWriteSettingsPermissionLauncher;

    private final Lazy initGuideViewModel;

    private final Lazy wifiViewModel;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.xuehai.launcher.guide.InitGuideActivity$onCreate$10", f = "InitGuideActivity.kt", i = {}, l = {209}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass10 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        AnonymousClass10(Continuation<? super AnonymousClass10> continuation) {
            super(2, continuation);
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return InitGuideActivity.this.new AnonymousClass10(continuation);
        }

        @Override
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass10) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            ChannelIterator<Unit> it;
            AnonymousClass10 anonymousClass10;
            Object objHasNext;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                it = InitGuideActivity.this.getInitGuideViewModel().getRequireAppUsagePermissions().iterator();
                anonymousClass10 = this;
                anonymousClass10.L$0 = it;
                anonymousClass10.label = 1;
                objHasNext = it.hasNext(anonymousClass10);
                if (objHasNext != coroutine_suspended) {
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ChannelIterator<Unit> channelIterator = (ChannelIterator) this.L$0;
                ResultKt.throwOnFailure(obj);
                ChannelIterator<Unit> channelIterator2 = channelIterator;
                Object obj2 = coroutine_suspended;
                AnonymousClass10 anonymousClass102 = this;
                if (!((Boolean) obj).booleanValue()) {
                    channelIterator2.next();
                    if (Build.VERSION.SDK_INT >= 21) {
                        try {
                            try {
                            } catch (Exception e) {
                                e.printStackTrace();
                                InitGuideActivity.this.showToast("无法打开用户授权页面");
                            }
                        } catch (Throwable unused) {
                            InitGuideActivity.this.appUsagePermissionLauncher.launch(new Intent("android.settings.USAGE_ACCESS_SETTINGS"));
                        }
                        InitGuideActivity.this.getInitGuideViewModel().getSettingSafeGuard().start();
                        InitGuideActivity.this.appUsagePermissionLauncher.launch(AppUsageHelper.INSTANCE.getAppUsageIntent(InitGuideActivity.this));
                    }
                    anonymousClass10 = anonymousClass102;
                    coroutine_suspended = obj2;
                    it = channelIterator2;
                    anonymousClass10.L$0 = it;
                    anonymousClass10.label = 1;
                    objHasNext = it.hasNext(anonymousClass10);
                    if (objHasNext != coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    Object obj3 = coroutine_suspended;
                    anonymousClass102 = anonymousClass10;
                    obj = objHasNext;
                    channelIterator2 = it;
                    obj2 = obj3;
                    if (!((Boolean) obj).booleanValue()) {
                        return Unit.INSTANCE;
                    }
                }
            }
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.xuehai.launcher.guide.InitGuideActivity$onCreate$11", f = "InitGuideActivity.kt", i = {}, l = {229}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass11 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        AnonymousClass11(Continuation<? super AnonymousClass11> continuation) {
            super(2, continuation);
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return InitGuideActivity.this.new AnonymousClass11(continuation);
        }

        @Override
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass11) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            ChannelIterator<Unit> it;
            AnonymousClass11 anonymousClass11;
            Object objHasNext;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                it = InitGuideActivity.this.getInitGuideViewModel().getRequireAppWriteSettingsPermissions().iterator();
                anonymousClass11 = this;
                anonymousClass11.L$0 = it;
                anonymousClass11.label = 1;
                objHasNext = it.hasNext(anonymousClass11);
                if (objHasNext != coroutine_suspended) {
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ChannelIterator<Unit> channelIterator = (ChannelIterator) this.L$0;
                ResultKt.throwOnFailure(obj);
                ChannelIterator<Unit> channelIterator2 = channelIterator;
                Object obj2 = coroutine_suspended;
                AnonymousClass11 anonymousClass112 = this;
                if (!((Boolean) obj).booleanValue()) {
                    channelIterator2.next();
                    if (Build.VERSION.SDK_INT >= 23) {
                        try {
                            try {
                            } catch (Exception e) {
                                e.printStackTrace();
                                InitGuideActivity.this.showToast("无法打开用户授权页面");
                            }
                        } catch (Throwable unused) {
                            InitGuideActivity.this.appWriteSettingsPermissionLauncher.launch(new Intent("android.settings.action.MANAGE_WRITE_SETTINGS"));
                        }
                        InitGuideActivity.this.getInitGuideViewModel().getWriteSettingsSafeGuard().start();
                        InitGuideActivity.this.appWriteSettingsPermissionLauncher.launch(SettingManager.INSTANCE.getOpenWriteSettingIntent());
                    }
                    anonymousClass11 = anonymousClass112;
                    coroutine_suspended = obj2;
                    it = channelIterator2;
                    anonymousClass11.L$0 = it;
                    anonymousClass11.label = 1;
                    objHasNext = it.hasNext(anonymousClass11);
                    if (objHasNext != coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    Object obj3 = coroutine_suspended;
                    anonymousClass112 = anonymousClass11;
                    obj = objHasNext;
                    channelIterator2 = it;
                    obj2 = obj3;
                    if (!((Boolean) obj).booleanValue()) {
                        return Unit.INSTANCE;
                    }
                }
            }
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.xuehai.launcher.guide.InitGuideActivity$onCreate$12", f = "InitGuideActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass12 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass12(Continuation<? super AnonymousClass12> continuation) {
            super(2, continuation);
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return InitGuideActivity.this.new AnonymousClass12(continuation);
        }

        @Override
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass12) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            boolean z = !InitGuideActivity.this.getInitGuideViewModel().checkDeviceOSIsCustomSystem();
            if (Intrinsics.areEqual(Build.MODEL, "SM-P620")) {
                if (z) {
                    InitGuideActivity.this.showInvalidSystemDialog();
                } else {
                    InitGuideActivity.this.getInitGuideViewModel().startGuide();
                }
            } else {
                if (z) {
                    InitGuideActivity.this.showGuideTip(new Pair("检测到非定制系统，请确认...", Boxing.boxBoolean(true)));
                    DialogProvider.Builder builderMessage$default = DialogProvider.Builder.message$default(new DialogProvider.Builder(), "系统为非定制系统，存在脱管风险！\n是否继续安装？", 0, 2, null);
                    final InitGuideActivity initGuideActivity = InitGuideActivity.this;
                    DialogProvider.Builder builderPositive$default = DialogProvider.Builder.positive$default(builderMessage$default, null, new Function1<View, Unit>() {
                        {
                            super(1);
                        }

                        @Override
                        public Unit invoke(View view) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                            invoke2(view);
                            return Unit.INSTANCE;
                        }

                        public final void invoke2(View view) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                            Intrinsics.checkNotNullParameter(view, "it");
                            initGuideActivity.getInitGuideViewModel().putCheckedDeviceOSFlag();
                            initGuideActivity.getInitGuideViewModel().startGuide();
                        }
                    }, 1, null);
                    final InitGuideActivity initGuideActivity2 = InitGuideActivity.this;
                    DialogProvider.Builder.negative$default(builderPositive$default, null, new Function1<View, Unit>() {
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
                            initGuideActivity2.finish();
                            XHAppUtil.killProcess();
                        }
                    }, 1, null).cancelable(false).buildCustomDialog(InitGuideActivity.this).show();
                    return Unit.INSTANCE;
                }
                InitGuideActivity.this.getInitGuideViewModel().startGuide();
            }
            return Unit.INSTANCE;
        }
    }

    public InitGuideActivity() {
        final InitGuideActivity initGuideActivity = this;
        Function0<ViewModelProvider.Factory> function0 = new Function0<ViewModelProvider.Factory>() {
            @Override
            public final ViewModelProvider.Factory invoke() {
                return ViewModelFactoryKt.obtainViewModelFactory();
            }
        };
        this.initGuideViewModel = new MyViewModelLazy(new Function0<ComponentActivity>() {
            {
                super(0);
            }

            @Override
            public final ComponentActivity invoke() {
                return initGuideActivity;
            }
        }, Reflection.getOrCreateKotlinClass(InitGuideViewModel.class), new Function0<ViewModelStore>() {
            {
                super(0);
            }

            @Override
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = initGuideActivity.getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "viewModelStore");
                return viewModelStore;
            }
        }, function0 == null ? new Function0<ViewModelProvider.Factory>() {
            {
                super(0);
            }

            @Override
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory = initGuideActivity.getDefaultViewModelProviderFactory();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "defaultViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        } : function0);
        Function0<ViewModelProvider.Factory> function02 = new Function0<ViewModelProvider.Factory>() {
            @Override
            public final ViewModelProvider.Factory invoke() {
                return ViewModelFactoryKt.obtainViewModelFactory();
            }
        };
        this.wifiViewModel = new MyViewModelLazy(new Function0<ComponentActivity>() {
            {
                super(0);
            }

            @Override
            public final ComponentActivity invoke() {
                return initGuideActivity;
            }
        }, Reflection.getOrCreateKotlinClass(WifiViewModel.class), new Function0<ViewModelStore>() {
            {
                super(0);
            }

            @Override
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = initGuideActivity.getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "viewModelStore");
                return viewModelStore;
            }
        }, function02 == null ? new Function0<ViewModelProvider.Factory>() {
            {
                super(0);
            }

            @Override
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory = initGuideActivity.getDefaultViewModelProviderFactory();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "defaultViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        } : function02);
        ActivityResultLauncher<Intent> activityResultLauncherRegisterForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() {
            @Override
            public final void onActivityResult(Object obj) {
                InitGuideActivity.m107appUsagePermissionLauncher$lambda0(this.f$0, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult, "registerForActivityResul…)\n            }\n        }");
        this.appUsagePermissionLauncher = activityResultLauncherRegisterForActivityResult;
        ActivityResultLauncher<Intent> activityResultLauncherRegisterForActivityResult2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() {
            @Override
            public final void onActivityResult(Object obj) {
                InitGuideActivity.m108appWriteSettingsPermissionLauncher$lambda1(this.f$0, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult2, "registerForActivityResul…)\n            }\n        }");
        this.appWriteSettingsPermissionLauncher = activityResultLauncherRegisterForActivityResult2;
    }

    private static final void m107appUsagePermissionLauncher$lambda0(InitGuideActivity initGuideActivity, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(initGuideActivity, "this$0");
        if (SettingManager.INSTANCE.checkAppUsagePermission()) {
            initGuideActivity.getInitGuideViewModel().continueToGuide(true);
        }
    }

    private static final void m108appWriteSettingsPermissionLauncher$lambda1(InitGuideActivity initGuideActivity, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(initGuideActivity, "this$0");
        if (SettingManager.INSTANCE.checkAppWriteSettingPermission()) {
            initGuideActivity.getInitGuideViewModel().continueToGuide(true);
        }
    }

    private final InitGuideViewModel getInitGuideViewModel() {
        return (InitGuideViewModel) this.initGuideViewModel.getValue();
    }

    private final WifiViewModel getWifiViewModel() {
        return (WifiViewModel) this.wifiViewModel.getValue();
    }

    private static final void m120onCreate$lambda20$lambda10(InitGuideActivity initGuideActivity, Pair pair) {
        Intrinsics.checkNotNullParameter(initGuideActivity, "this$0");
        initGuideActivity.showGuideTip(pair);
    }

    private static final void m121onCreate$lambda20$lambda11(InitGuideActivity initGuideActivity, Boolean bool) {
        Intrinsics.checkNotNullParameter(initGuideActivity, "this$0");
        initGuideActivity.showOrHideRetryView(Intrinsics.areEqual((Object) bool, (Object) true));
    }

    private static final void m122onCreate$lambda20$lambda12(InitGuideActivity initGuideActivity, InitGuideViewModel initGuideViewModel, Void r3) {
        Intrinsics.checkNotNullParameter(initGuideActivity, "this$0");
        Intrinsics.checkNotNullParameter(initGuideViewModel, "$vm");
        if (ZtyClientUtil.INSTANCE.startZtyClient(initGuideActivity)) {
            initGuideActivity.finish();
        } else {
            initGuideViewModel.startZtyFailed();
        }
        initGuideActivity.progress((Boolean) false);
    }

    private static final void m123onCreate$lambda20$lambda13(InitGuideActivity initGuideActivity, List list) {
        Intrinsics.checkNotNullParameter(initGuideActivity, "this$0");
        ((StepProgressBar) initGuideActivity._$_findCachedViewById(R.id.initGuideProgressBar)).resetStep(list);
    }

    private static final void m124onCreate$lambda20$lambda14(InitGuideActivity initGuideActivity, Void r1) {
        Intrinsics.checkNotNullParameter(initGuideActivity, "this$0");
        if (DeviceActiveManager.INSTANCE.addDeviceAdmin(initGuideActivity)) {
            LauncherSPUtil.setUpdateTimeByKey("start_admin_time_key");
        }
    }

    private static final void m125onCreate$lambda20$lambda15(InitGuideActivity initGuideActivity, Boolean bool) {
        Intrinsics.checkNotNullParameter(initGuideActivity, "this$0");
        if (Intrinsics.areEqual((Object) bool, (Object) false)) {
            ((Group) initGuideActivity._$_findCachedViewById(R.id.initGuideBannerGroup)).setVisibility(4);
            ((ImageView) initGuideActivity._$_findCachedViewById(R.id.initGuideErrorIv)).setVisibility(0);
        } else {
            ((Group) initGuideActivity._$_findCachedViewById(R.id.initGuideBannerGroup)).setVisibility(0);
            ((ImageView) initGuideActivity._$_findCachedViewById(R.id.initGuideErrorIv)).setVisibility(8);
        }
    }

    private static final void m126onCreate$lambda20$lambda16(InitGuideActivity initGuideActivity, Set set) {
        Intrinsics.checkNotNullParameter(initGuideActivity, "this$0");
        initGuideActivity.setupPermission();
    }

    private static final void m127onCreate$lambda20$lambda17(InitGuideActivity initGuideActivity, DialogProvider.Builder builder) {
        Intrinsics.checkNotNullParameter(initGuideActivity, "this$0");
        CustomDialogFragment customDialogFragmentBuild = builder.build();
        FragmentManager supportFragmentManager = initGuideActivity.getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        customDialogFragmentBuild.show(supportFragmentManager);
    }

    private static final void m128onCreate$lambda20$lambda18(InitGuideActivity initGuideActivity, CustomDialogFragment customDialogFragment) {
        Intrinsics.checkNotNullParameter(initGuideActivity, "this$0");
        FragmentManager supportFragmentManager = initGuideActivity.getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        customDialogFragment.show(supportFragmentManager);
    }

    private static final void m129onCreate$lambda20$lambda19(InitGuideActivity initGuideActivity, Void r7) {
        Intrinsics.checkNotNullParameter(initGuideActivity, "this$0");
        InitGuideActivity initGuideActivity2 = initGuideActivity;
        ActivityUtil.startActivity$default(ActivityUtil.INSTANCE, initGuideActivity2, new Intent(initGuideActivity2, (Class<?>) SafeActivity.class), 0, 4, null);
    }

    private static final void m130onCreate$lambda20$lambda8(InitGuideActivity initGuideActivity, String str) {
        Intrinsics.checkNotNullParameter(initGuideActivity, "this$0");
        ((TextView) initGuideActivity._$_findCachedViewById(R.id.initGuideTitleTv)).setText(str);
    }

    private static final void m131onCreate$lambda20$lambda9(InitGuideActivity initGuideActivity, Boolean bool) throws PackageManager.NameNotFoundException {
        Intrinsics.checkNotNullParameter(initGuideActivity, "this$0");
        initGuideActivity.getWifiViewModel().openWifiSetting();
    }

    private static final void m132onCreate$lambda21(InitGuideActivity initGuideActivity, View view) throws PackageManager.NameNotFoundException {
        Intrinsics.checkNotNullParameter(initGuideActivity, "this$0");
        initGuideActivity.getWifiViewModel().selectWifi();
    }

    private static final boolean m133onCreate$lambda22(InitGuideActivity initGuideActivity, View view) {
        Intrinsics.checkNotNullParameter(initGuideActivity, "this$0");
        DialogProvider.Builder.negative$default(DialogProvider.Builder.positive$default(DialogProvider.Builder.message$default(new DialogProvider.Builder(), "重置网络", 0, 2, null), null, new Function1<View, Unit>() {
            @Override
            public Unit invoke(View view2) {
                invoke2(view2);
                return Unit.INSTANCE;
            }

            public final void invoke2(View view2) {
                Intrinsics.checkNotNullParameter(view2, "it");
                PolicyManager.getWifiPolicyProxy().resetNetworkSetting();
            }
        }, 1, null), null, new Function1<View, Unit>() {
            @Override
            public Unit invoke(View view2) {
                invoke2(view2);
                return Unit.INSTANCE;
            }

            public final void invoke2(View view2) {
                Intrinsics.checkNotNullParameter(view2, "it");
            }
        }, 1, null).cancelable(true).buildCustomDialog(initGuideActivity).show();
        return true;
    }

    private static final void m134onCreate$lambda23(InitGuideActivity initGuideActivity, View view) {
        Intrinsics.checkNotNullParameter(initGuideActivity, "this$0");
        ZtyFlagManager.INSTANCE.clearFlag();
        initGuideActivity.getInitGuideViewModel().continueToGuide(true);
    }

    private static final void m135onCreate$lambda24(InitGuideActivity initGuideActivity, View view) {
        Intrinsics.checkNotNullParameter(initGuideActivity, "this$0");
        InitGuideActivity initGuideActivity2 = initGuideActivity;
        ActivityUtil.startActivity$default(ActivityUtil.INSTANCE, initGuideActivity2, new Intent(initGuideActivity2, (Class<?>) OtherActivity.class), 0, 4, null);
    }

    private static final boolean m136onCreate$lambda25(InitGuideActivity initGuideActivity, View view) throws PackageManager.NameNotFoundException {
        Intrinsics.checkNotNullParameter(initGuideActivity, "this$0");
        initGuideActivity.getWifiViewModel().startSettings();
        return true;
    }

    private static final void m137onCreate$lambda26(InitGuideActivity initGuideActivity, View view) {
        Intrinsics.checkNotNullParameter(initGuideActivity, "this$0");
        InitGuideActivity initGuideActivity2 = initGuideActivity;
        ActivityUtil.startActivity$default(ActivityUtil.INSTANCE, initGuideActivity2, new Intent(initGuideActivity2, (Class<?>) DeviceInfoActivity.class), 0, 4, null);
    }

    private static final void m138onCreate$lambda6$lambda3(InitGuideActivity initGuideActivity, Boolean bool) {
        Intrinsics.checkNotNullParameter(initGuideActivity, "this$0");
        if (bool != null) {
            bool.booleanValue();
            ((LinearLayout) initGuideActivity._$_findCachedViewById(R.id.initGuideWifiLayout)).setVisibility(bool.booleanValue() ? 0 : 4);
        }
    }

    private static final void m139onCreate$lambda6$lambda5(InitGuideActivity initGuideActivity, Integer num) {
        Intrinsics.checkNotNullParameter(initGuideActivity, "this$0");
        if (num != null) {
            num.intValue();
            ((TextView) initGuideActivity._$_findCachedViewById(R.id.initGuideWifiTv)).setCompoundDrawablesWithIntrinsicBounds(0, 0, num.intValue(), 0);
        }
    }

    private static final void m140onCreate$lambda7(InitGuideActivity initGuideActivity, NetworkType networkType) {
        Intrinsics.checkNotNullParameter(initGuideActivity, "this$0");
        boolean z = false;
        if (networkType != null && !networkType.isEnable()) {
            z = true;
        }
        if (z) {
            return;
        }
        MyLog.i("Network[MDM]", "网络变为可用");
        initGuideActivity.getInitGuideViewModel().dismissWifiConnectDialog();
        initGuideActivity.getInitGuideViewModel().continueToGuide(true);
    }

    private final void showGuideTip(Pair<String, Boolean> pair) {
        if (pair != null) {
            ((LoadingTextView) _$_findCachedViewById(R.id.initGuideTipTv)).setText(pair.getFirst());
            ((LoadingTextView) _$_findCachedViewById(R.id.initGuideTipTv)).updateLoadingStatus(pair.getSecond().booleanValue());
        }
    }

    private final void showOrHideRetryView(boolean z) {
        ((TextView) _$_findCachedViewById(R.id.initGuideRetryTv)).setVisibility(z ? 0 : 8);
    }

    static void showOrHideRetryView$default(InitGuideActivity initGuideActivity, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        initGuideActivity.showOrHideRetryView(z);
    }

    @Override
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        if (viewFindViewById == null) {
            return null;
        }
        map.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override
    public void afterAppAdded(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        getInitGuideViewModel().onAppUpdated(packageName);
    }

    @Override
    public void afterAppRemoved(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
    }

    @Override
    public void afterAppReplaced(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        getInitGuideViewModel().onAppUpdated(packageName);
    }

    @Override
    public void afterPermissionGranted(boolean isNew) {
        super.afterPermissionGranted(isNew);
        if (isNew) {
            getInitGuideViewModel().continueToGuide(true);
        }
    }

    public final void authenticationNetwork(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        getWifiViewModel().authenticationNetwork();
    }

    @Override
    public boolean autoSetupPermission() {
        return false;
    }

    @Override
    protected int getNavBarColor() {
        return 2131099816;
    }

    @Override
    public String[] getPermissionsToRequest() {
        return getInitGuideViewModel().getPermissionsToRequest();
    }

    @Override
    protected int getStatusBarColor() {
        return 2131099816;
    }

    @Override
    protected boolean isFullScreen() {
        return true;
    }

    @Override
    protected boolean isLightStatusBar() {
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getInitGuideViewModel().onActivityResult(requestCode, resultCode);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        getInitGuideViewModel().dismissWifiConnectDialog();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        super.onCreate(savedInstanceState);
        setContentView(2131492920);
        WifiViewModel wifiViewModel = getWifiViewModel();
        InitGuideActivity initGuideActivity = this;
        wifiViewModel.getWifiStepState().observe(initGuideActivity, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                InitGuideActivity.m138onCreate$lambda6$lambda3(this.f$0, (Boolean) obj);
            }
        });
        wifiViewModel.getWifiBtnRes().observe(initGuideActivity, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                InitGuideActivity.m139onCreate$lambda6$lambda5(this.f$0, (Integer) obj);
            }
        });
        NetworkManger.INSTANCE.getNetworkData().observeForever(new Observer() {
            @Override
            public final void onChanged(Object obj) {
                InitGuideActivity.m140onCreate$lambda7(this.f$0, (NetworkType) obj);
            }
        });
        final InitGuideViewModel initGuideViewModel = getInitGuideViewModel();
        initGuideViewModel.getTitleData().observe(initGuideActivity, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                InitGuideActivity.m130onCreate$lambda20$lambda8(this.f$0, (String) obj);
            }
        });
        initGuideViewModel.getOpenWifiAction().observe(initGuideActivity, new Observer() {
            @Override
            public final void onChanged(Object obj) throws PackageManager.NameNotFoundException {
                InitGuideActivity.m131onCreate$lambda20$lambda9(this.f$0, (Boolean) obj);
            }
        });
        initGuideViewModel.getCurrentMessage().observe(initGuideActivity, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                InitGuideActivity.m120onCreate$lambda20$lambda10(this.f$0, (Pair) obj);
            }
        });
        initGuideViewModel.getNeedRetryAction().observe(initGuideActivity, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                InitGuideActivity.m121onCreate$lambda20$lambda11(this.f$0, (Boolean) obj);
            }
        });
        initGuideViewModel.getOpenZtyClient().observe(initGuideActivity, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                InitGuideActivity.m122onCreate$lambda20$lambda12(this.f$0, initGuideViewModel, (Void) obj);
            }
        });
        initGuideViewModel.getAllStepData().observe(initGuideActivity, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                InitGuideActivity.m123onCreate$lambda20$lambda13(this.f$0, (List) obj);
            }
        });
        initGuideViewModel.getActivateDeviceManagerAction().observe(initGuideActivity, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                InitGuideActivity.m124onCreate$lambda20$lambda14(this.f$0, (Void) obj);
            }
        });
        initGuideViewModel.getStartSucStatusAction().observe(initGuideActivity, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                InitGuideActivity.m125onCreate$lambda20$lambda15(this.f$0, (Boolean) obj);
            }
        });
        initGuideViewModel.getRequirePermissions().observe(initGuideActivity, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                InitGuideActivity.m126onCreate$lambda20$lambda16(this.f$0, (Set) obj);
            }
        });
        initGuideViewModel.getTipDialog().observe(initGuideActivity, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                InitGuideActivity.m127onCreate$lambda20$lambda17(this.f$0, (DialogProvider.Builder) obj);
            }
        });
        initGuideViewModel.getWifiTipDialog().observe(initGuideActivity, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                InitGuideActivity.m128onCreate$lambda20$lambda18(this.f$0, (CustomDialogFragment) obj);
            }
        });
        initGuideViewModel.getShowSafeMode().observe(initGuideActivity, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                InitGuideActivity.m129onCreate$lambda20$lambda19(this.f$0, (Void) obj);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.initGuideVersionTv)).setText("版本号: " + ClientConfig.INSTANCE.getAppVersion());
        ((TextView) _$_findCachedViewById(R.id.initGuideWifiTv)).setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) throws PackageManager.NameNotFoundException {
                InitGuideActivity.m132onCreate$lambda21(this.f$0, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.initGuideWifiTv)).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public final boolean onLongClick(View view) {
                return InitGuideActivity.m133onCreate$lambda22(this.f$0, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.initGuideRetryTv)).setText(Html.fromHtml(getString(2131689557)));
        ((TextView) _$_findCachedViewById(R.id.initGuideRetryTv)).setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                InitGuideActivity.m134onCreate$lambda23(this.f$0, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.initGuideOtherTv)).setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                InitGuideActivity.m135onCreate$lambda24(this.f$0, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.initGuideOtherTv)).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public final boolean onLongClick(View view) {
                return InitGuideActivity.m136onCreate$lambda25(this.f$0, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.initGuideVersionTv)).setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                InitGuideActivity.m137onCreate$lambda26(this.f$0, view);
            }
        });
        LicenseReceiver.INSTANCE.addListener(this);
        PackageReceiver.addListener(this);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(initGuideActivity), null, null, new AnonymousClass10(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(initGuideActivity), null, null, new AnonymousClass11(null), 3, null);
        getWifiViewModel().loadDefault();
        LifecycleOwnerKt.getLifecycleScope(initGuideActivity).launchWhenResumed(new AnonymousClass12(null));
    }

    @Override
    protected void onDestroy() {
        LicenseReceiver.INSTANCE.removeListener(this);
        PackageReceiver.removeListener(this);
        InitGuideViewModel.SettingSafeGuard.stop$default(getInitGuideViewModel().getSettingSafeGuard(), false, 1, null);
        InitGuideViewModel.SettingSafeGuard.stop$default(getInitGuideViewModel().getWriteSettingsSafeGuard(), false, 1, null);
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (4 == keyCode) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onLicenseStateChanged(String desc, boolean success) {
        Intrinsics.checkNotNullParameter(desc, "desc");
        getInitGuideViewModel().onLicenseStateChanged(desc, success);
    }

    public final void openWifi(View view) throws PackageManager.NameNotFoundException {
        Intrinsics.checkNotNullParameter(view, "view");
        getWifiViewModel().openWifiSetting();
    }

    public final void showInvalidSystemDialog() {
        showGuideTip(new Pair<>("检测到系统异常，请确认...", true));
        DialogProvider.Builder.negative$default(DialogProvider.Builder.positive$default(DialogProvider.Builder.message$default(new DialogProvider.Builder(), "系统异常，不可使用", 0, 2, null), null, new Function1<View, Unit>() {
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
                this.this$0.finish();
                XHAppUtil.killProcess();
            }
        }, 1, null), null, new Function1<View, Unit>() {
            @Override
            public Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            public final void invoke2(View view) {
                Intrinsics.checkNotNullParameter(view, "it");
            }
        }, 1, null).cancelable(false).buildCustomDialog(this).show();
    }
}
