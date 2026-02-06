package com.xuehai.launcher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.UserManager;
import android.view.KeyEvent;
import android.view.View;
import androidx.activity.ComponentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import com.xuehai.launcher.common.base.AbsActivity;
import com.xuehai.launcher.common.cache.SessionData;
import com.xuehai.launcher.common.ext.MyViewModelLazy;
import com.xuehai.launcher.common.ext.ViewModelFactoryKt;
import com.xuehai.launcher.common.plugins.ThreadPlugins;
import com.xuehai.launcher.common.util.ActivityUtil;
import com.xuehai.launcher.common.util.DeviceModelUtil;
import com.xuehai.launcher.guide.InitGuideActivity;
import com.xuehai.launcher.receiver.PackageReceiver;
import com.xuehai.launcher.safe.SafeActivity;
import com.xuehai.launcher.util.ZtyClientUtil;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000M\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\t\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0014J\b\u0010\u0019\u001a\u00020\u0018H\u0014J\b\u0010\u001a\u001a\u00020\u0005H\u0014J\b\u0010\u001b\u001a\u00020\u0012H\u0002J\u0012\u0010\u001c\u001a\u00020\u00122\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\b\u0010\u001f\u001a\u00020\u0012H\u0014J\u0018\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020#H\u0016J\b\u0010$\u001a\u00020\u0012H\u0014J\b\u0010%\u001a\u00020\u0012H\u0002J\b\u0010&\u001a\u00020\u0012H\u0002J\b\u0010'\u001a\u00020\u0012H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000e¨\u0006("}, d2 = {"Lcom/xuehai/launcher/MainActivity;", "Lcom/xuehai/launcher/common/base/AbsActivity;", "Lcom/xuehai/launcher/receiver/PackageReceiver$PackageListener;", "()V", "hasLoadedAfterUnlock", "", "isNationThemeSet", "unlocked", "userUnlockedReceiver", "com/xuehai/launcher/MainActivity$userUnlockedReceiver$1", "Lcom/xuehai/launcher/MainActivity$userUnlockedReceiver$1;", "viewModel", "Lcom/xuehai/launcher/MainViewModel;", "getViewModel", "()Lcom/xuehai/launcher/MainViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "afterAppAdded", "", "packageName", "", "afterAppRemoved", "afterAppReplaced", "getNavBarColor", "", "getStatusBarColor", "isFullScreen", "loadData", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onKeyDown", "keyCode", "event", "Landroid/view/KeyEvent;", "onResume", "onResumeAgain", "showNormalMode", "showSafeMode", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MainActivity extends AbsActivity implements PackageReceiver.PackageListener {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private boolean hasLoadedAfterUnlock;
    private boolean isNationThemeSet;
    private boolean unlocked;
    private final MainActivity$userUnlockedReceiver$1 userUnlockedReceiver;

    private final Lazy viewModel;

    public MainActivity() {
        final MainActivity mainActivity = this;
        Function0<ViewModelProvider.Factory> function0 = new Function0<ViewModelProvider.Factory>() {
            @Override
            public final ViewModelProvider.Factory invoke() {
                return ViewModelFactoryKt.obtainViewModelFactory();
            }
        };
        this.viewModel = new MyViewModelLazy(new Function0<ComponentActivity>() {
            {
                super(0);
            }

            @Override
            public final ComponentActivity invoke() {
                return mainActivity;
            }
        }, Reflection.getOrCreateKotlinClass(MainViewModel.class), new Function0<ViewModelStore>() {
            {
                super(0);
            }

            @Override
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = mainActivity.getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "viewModelStore");
                return viewModelStore;
            }
        }, function0 == null ? new Function0<ViewModelProvider.Factory>() {
            {
                super(0);
            }

            @Override
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory = mainActivity.getDefaultViewModelProviderFactory();
                Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "defaultViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        } : function0);
        this.unlocked = true;
        this.userUnlockedReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) throws Throwable {
                if (Intrinsics.areEqual(intent != null ? intent.getAction() : null, "android.intent.action.USER_UNLOCKED")) {
                    this.this$0.unlocked = true;
                    if (!this.this$0.hasLoadedAfterUnlock) {
                        this.this$0.loadData();
                        this.this$0.hasLoadedAfterUnlock = true;
                    }
                    this.this$0.onResumeAgain();
                }
            }
        };
    }

    private final MainViewModel getViewModel() {
        return (MainViewModel) this.viewModel.getValue();
    }

    private final void loadData() throws Throwable {
        if (DeviceModelUtil.isHuaweiHEMDevice()) {
            ThreadPlugins.runInUIThread(new Runnable() {
                @Override
                public final void run() throws Throwable {
                    MainActivity.m43loadData$lambda5(this.f$0);
                }
            }, 1000L);
        } else {
            getViewModel().load();
        }
    }

    private static final void m43loadData$lambda5(MainActivity mainActivity) throws Throwable {
        Intrinsics.checkNotNullParameter(mainActivity, "this$0");
        mainActivity.getViewModel().load();
    }

    private static final void m44onCreate$lambda4$lambda0(MainActivity mainActivity, Void r1) {
        Intrinsics.checkNotNullParameter(mainActivity, "this$0");
        mainActivity.showNormalMode();
    }

    private static final void m45onCreate$lambda4$lambda1(MainActivity mainActivity, Void r1) {
        Intrinsics.checkNotNullParameter(mainActivity, "this$0");
        mainActivity.showSafeMode();
    }

    private static final void m46onCreate$lambda4$lambda2(MainActivity mainActivity, MainViewModel mainViewModel, Void r2) {
        Intrinsics.checkNotNullParameter(mainActivity, "this$0");
        Intrinsics.checkNotNullParameter(mainViewModel, "$vm");
        if (ZtyClientUtil.INSTANCE.startZtyClient(mainActivity)) {
            return;
        }
        mainViewModel.onStartFailed();
    }

    private static final void m47onCreate$lambda4$lambda3(MainActivity mainActivity, Void r7) {
        Intrinsics.checkNotNullParameter(mainActivity, "this$0");
        Context contextCreateDeviceProtectedStorageContext = Build.VERSION.SDK_INT >= 24 ? mainActivity.createDeviceProtectedStorageContext() : mainActivity;
        ActivityUtil activityUtil = ActivityUtil.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(contextCreateDeviceProtectedStorageContext, "ctx");
        ActivityUtil.startActivity$default(activityUtil, contextCreateDeviceProtectedStorageContext, new Intent(contextCreateDeviceProtectedStorageContext, (Class<?>) InitGuideActivity.class), 0, 4, null);
    }

    private final void onResumeAgain() {
        if (DeviceModelUtil.isHuaweiHEMDevice()) {
            ThreadPlugins.runInUIThread(new Runnable() {
                @Override
                public final void run() {
                    MainActivity.m48onResumeAgain$lambda6(this.f$0);
                }
            }, 1000L);
        } else {
            getViewModel().onResume();
        }
    }

    private static final void m48onResumeAgain$lambda6(MainActivity mainActivity) {
        Intrinsics.checkNotNullParameter(mainActivity, "this$0");
        mainActivity.getViewModel().onResume();
    }

    private final void showNormalMode() {
    }

    private final void showSafeMode() {
        MainActivity mainActivity = this;
        ActivityUtil.startActivity$default(ActivityUtil.INSTANCE, mainActivity, new Intent(mainActivity, (Class<?>) SafeActivity.class), 0, 4, null);
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
        getViewModel().onAppUpdated(packageName);
    }

    @Override
    public void afterAppRemoved(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
    }

    @Override
    public void afterAppReplaced(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        getViewModel().onAppUpdated(packageName);
    }

    @Override
    protected int getNavBarColor() {
        return 2131099816;
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
    protected void onCreate(Bundle savedInstanceState) throws Throwable {
        boolean zIsUserUnlocked;
        if (Build.VERSION.SDK_INT >= 24) {
            Object systemService = getSystemService(UserManager.class);
            if (systemService == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.os.UserManager");
            }
            zIsUserUnlocked = ((UserManager) systemService).isUserUnlocked();
        } else {
            zIsUserUnlocked = true;
        }
        this.unlocked = zIsUserUnlocked;
        if (!zIsUserUnlocked) {
            setTheme(2131755246);
        } else if (SessionData.INSTANCE.isNationServiceVersion()) {
            setTheme(2131755247);
            this.isNationThemeSet = true;
        } else if (SessionData.INSTANCE.isCooperationZS() || SessionData.INSTANCE.isQw()) {
            setTheme(2131755248);
        } else {
            setTheme(2131755246);
        }
        super.onCreate(savedInstanceState);
        final MainViewModel viewModel = getViewModel();
        MainActivity mainActivity = this;
        viewModel.getShowNormalMode().observe(mainActivity, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                MainActivity.m44onCreate$lambda4$lambda0(this.f$0, (Void) obj);
            }
        });
        viewModel.getShowSafeMode().observe(mainActivity, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                MainActivity.m45onCreate$lambda4$lambda1(this.f$0, (Void) obj);
            }
        });
        viewModel.getOpenZtyClient().observe(mainActivity, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                MainActivity.m46onCreate$lambda4$lambda2(this.f$0, viewModel, (Void) obj);
            }
        });
        viewModel.getStartGuide().observe(mainActivity, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                MainActivity.m47onCreate$lambda4$lambda3(this.f$0, (Void) obj);
            }
        });
        PackageReceiver.addListener(this);
        if (this.unlocked) {
            loadData();
            this.hasLoadedAfterUnlock = true;
        }
        registerReceiver(this.userUnlockedReceiver, new IntentFilter("android.intent.action.USER_UNLOCKED"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PackageReceiver.removeListener(this);
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
    protected void onResume() {
        if (this.unlocked && SessionData.INSTANCE.isNationServiceVersion() && !this.isNationThemeSet) {
            getWindow().setBackgroundDrawableResource(2131230885);
            this.isNationThemeSet = true;
        }
        super.onResume();
        if (this.unlocked) {
            onResumeAgain();
        }
    }
}
