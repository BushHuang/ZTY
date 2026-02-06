package com.xuehai.launcher.common.ext;

import android.os.Build;
import android.view.Window;
import androidx.activity.ComponentActivity;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import com.xuehai.launcher.common.util.ScreenUtils;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000^\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a4\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\t0\b\"\n\b\u0000\u0010\t\u0018\u0001*\u00020\n*\u00020\u000b2\u0010\b\n\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rH\u0086\bø\u0001\u0000\u001a\u0012\u0010\u000f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u001f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\t0\b\"\n\b\u0000\u0010\t\u0018\u0001*\u00020\n*\u00020\u000bH\u0087\b\u001a\u0012\u0010\u0011\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u001a\u0010\u0012\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a:\u0010\u0013\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00042\b\b\u0002\u0010\u0019\u001a\u00020\u0004\u001a&\u0010\u001a\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u0015\u001a-\u0010\u001c\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u001e2\u0019\b\u0002\u0010\u001f\u001a\u0013\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00010 ¢\u0006\u0002\b\"\u001a\u0012\u0010#\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006\u001a#\u0010$\u001a\u00020\u0001*\u00020%2\u0017\u0010\u001f\u001a\u0013\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u00010 ¢\u0006\u0002\b\"\u001a#\u0010'\u001a\u00020\u0001*\u00020%2\u0017\u0010\u001f\u001a\u0013\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u00010 ¢\u0006\u0002\b\"\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006("}, d2 = {"addFragmentToActivity", "", "Landroidx/appcompat/app/AppCompatActivity;", "frameId", "", "fragment", "Landroidx/fragment/app/Fragment;", "customViewModels", "Lkotlin/Lazy;", "VM", "Landroidx/lifecycle/ViewModel;", "Landroidx/activity/ComponentActivity;", "factoryProducer", "Lkotlin/Function0;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "hideFragmentInActivity", "myViewModels", "removeFragmentFromActivity", "replaceFragmentInActivity", "setImmersion", "isFullScreen", "", "isLayoutFullScreen", "isLighting", "statusBarColor", "navBarColor", "setImmersionOnWindowFocusChanged", "hasFocus", "setupActionBar", "toolbar", "Landroidx/appcompat/widget/Toolbar;", "action", "Lkotlin/Function1;", "Landroidx/appcompat/app/ActionBar;", "Lkotlin/ExtensionFunctionType;", "showFragmentInActivity", "transact", "Landroidx/fragment/app/FragmentManager;", "Landroidx/fragment/app/FragmentTransaction;", "transactAllowingStateLoss", "common_studentToBRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class AppCompatActivityExtKt {

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/activity/ComponentActivity;", "VM", "Landroidx/lifecycle/ViewModel;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 176)
    public static final class AnonymousClass1 extends Lambda implements Function0<ComponentActivity> {
        final ComponentActivity $this_customViewModels;

        public AnonymousClass1(ComponentActivity componentActivity) {
            super(0);
            this.$this_customViewModels = componentActivity;
        }

        @Override
        public final ComponentActivity invoke() {
            return this.$this_customViewModels;
        }
    }

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/lifecycle/ViewModelStore;", "VM", "Landroidx/lifecycle/ViewModel;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 176)
    public static final class AnonymousClass2 extends Lambda implements Function0<ViewModelStore> {
        final ComponentActivity $this_customViewModels;

        public AnonymousClass2(ComponentActivity componentActivity) {
            super(0);
            this.$this_customViewModels = componentActivity;
        }

        @Override
        public final ViewModelStore invoke() {
            ViewModelStore viewModelStore = this.$this_customViewModels.getViewModelStore();
            Intrinsics.checkNotNullExpressionValue(viewModelStore, "viewModelStore");
            return viewModelStore;
        }
    }

    public static final void addFragmentToActivity(AppCompatActivity appCompatActivity, final int i, final Fragment fragment) {
        Intrinsics.checkNotNullParameter(appCompatActivity, "<this>");
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        FragmentManager supportFragmentManager = appCompatActivity.getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        transactAllowingStateLoss(supportFragmentManager, new Function1<FragmentTransaction, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(FragmentTransaction fragmentTransaction) {
                invoke2(fragmentTransaction);
                return Unit.INSTANCE;
            }

            public final void invoke2(FragmentTransaction fragmentTransaction) {
                Intrinsics.checkNotNullParameter(fragmentTransaction, "$this$transactAllowingStateLoss");
                fragmentTransaction.add(i, fragment);
            }
        });
    }

    public static final <VM extends ViewModel> Lazy<VM> customViewModels(ComponentActivity componentActivity, Function0<? extends ViewModelProvider.Factory> function0) {
        Intrinsics.checkNotNullParameter(componentActivity, "<this>");
        if (function0 == null) {
            function0 = new AppCompatActivityExtKt$customViewModels$factoryPromise$1(componentActivity);
        }
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(componentActivity);
        Intrinsics.reifiedOperationMarker(4, "VM");
        return new MyViewModelLazy(anonymousClass1, Reflection.getOrCreateKotlinClass(ViewModel.class), new AnonymousClass2(componentActivity), function0);
    }

    public static Lazy customViewModels$default(ComponentActivity componentActivity, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = null;
        }
        Intrinsics.checkNotNullParameter(componentActivity, "<this>");
        if (function0 == null) {
            function0 = new AppCompatActivityExtKt$customViewModels$factoryPromise$1(componentActivity);
        }
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(componentActivity);
        Intrinsics.reifiedOperationMarker(4, "VM");
        return new MyViewModelLazy(anonymousClass1, Reflection.getOrCreateKotlinClass(ViewModel.class), new AnonymousClass2(componentActivity), function0);
    }

    public static final void hideFragmentInActivity(AppCompatActivity appCompatActivity, final Fragment fragment) {
        Intrinsics.checkNotNullParameter(appCompatActivity, "<this>");
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        FragmentManager supportFragmentManager = appCompatActivity.getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        transactAllowingStateLoss(supportFragmentManager, new Function1<FragmentTransaction, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(FragmentTransaction fragmentTransaction) {
                invoke2(fragmentTransaction);
                return Unit.INSTANCE;
            }

            public final void invoke2(FragmentTransaction fragmentTransaction) {
                Intrinsics.checkNotNullParameter(fragmentTransaction, "$this$transactAllowingStateLoss");
                fragmentTransaction.hide(fragment);
            }
        });
    }

    public static final <VM extends ViewModel> Lazy<VM> myViewModels(ComponentActivity componentActivity) {
        Intrinsics.checkNotNullParameter(componentActivity, "<this>");
        AppCompatActivityExtKt$customViewModels$factoryPromise$1 appCompatActivityExtKt$customViewModels$factoryPromise$1 = new Function0<ViewModelProvider.Factory>() {
            @Override
            public final ViewModelProvider.Factory invoke() {
                return ViewModelFactoryKt.obtainViewModelFactory();
            }
        };
        if (appCompatActivityExtKt$customViewModels$factoryPromise$1 == null) {
            appCompatActivityExtKt$customViewModels$factoryPromise$1 = new AppCompatActivityExtKt$customViewModels$factoryPromise$1(componentActivity);
        }
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(componentActivity);
        Intrinsics.reifiedOperationMarker(4, "VM");
        return new MyViewModelLazy(anonymousClass1, Reflection.getOrCreateKotlinClass(ViewModel.class), new AnonymousClass2(componentActivity), appCompatActivityExtKt$customViewModels$factoryPromise$1);
    }

    public static final void removeFragmentFromActivity(AppCompatActivity appCompatActivity, final Fragment fragment) {
        Intrinsics.checkNotNullParameter(appCompatActivity, "<this>");
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        FragmentManager supportFragmentManager = appCompatActivity.getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        transactAllowingStateLoss(supportFragmentManager, new Function1<FragmentTransaction, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(FragmentTransaction fragmentTransaction) {
                invoke2(fragmentTransaction);
                return Unit.INSTANCE;
            }

            public final void invoke2(FragmentTransaction fragmentTransaction) {
                Intrinsics.checkNotNullParameter(fragmentTransaction, "$this$transactAllowingStateLoss");
                fragmentTransaction.remove(fragment);
            }
        });
    }

    public static final void replaceFragmentInActivity(AppCompatActivity appCompatActivity, final int i, final Fragment fragment) {
        Intrinsics.checkNotNullParameter(appCompatActivity, "<this>");
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        FragmentManager supportFragmentManager = appCompatActivity.getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        transactAllowingStateLoss(supportFragmentManager, new Function1<FragmentTransaction, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(FragmentTransaction fragmentTransaction) {
                invoke2(fragmentTransaction);
                return Unit.INSTANCE;
            }

            public final void invoke2(FragmentTransaction fragmentTransaction) {
                Intrinsics.checkNotNullParameter(fragmentTransaction, "$this$transactAllowingStateLoss");
                fragmentTransaction.replace(i, fragment);
            }
        });
    }

    public static final void setImmersion(AppCompatActivity appCompatActivity, boolean z, boolean z2, boolean z3, int i, int i2) {
        int layoutFullScreenFlag$default;
        Intrinsics.checkNotNullParameter(appCompatActivity, "<this>");
        if (Build.VERSION.SDK_INT < 19) {
            return;
        }
        if (Build.VERSION.SDK_INT < 21) {
            appCompatActivity.getWindow().addFlags(67108864);
            return;
        }
        if (z2) {
            layoutFullScreenFlag$default = ScreenUtils.getLayoutFullScreenFlag$default(ScreenUtils.INSTANCE, false, 1, null);
        } else {
            layoutFullScreenFlag$default = 4352;
            appCompatActivity.getWindow().addFlags(Integer.MIN_VALUE);
        }
        if (z) {
            layoutFullScreenFlag$default |= 4;
        }
        if (i2 > 0) {
            appCompatActivity.getWindow().setNavigationBarColor(ContextCompat.getColor(appCompatActivity, i2));
        } else if (z2) {
            appCompatActivity.getWindow().setNavigationBarColor(0);
        }
        if (z3 && Build.VERSION.SDK_INT >= 23) {
            layoutFullScreenFlag$default |= 8192;
        }
        appCompatActivity.getWindow().setStatusBarColor(z2 ? 0 : ContextCompat.getColor(appCompatActivity, i));
        appCompatActivity.getWindow().getDecorView().setSystemUiVisibility(layoutFullScreenFlag$default);
    }

    public static final void setImmersionOnWindowFocusChanged(AppCompatActivity appCompatActivity, boolean z, boolean z2, boolean z3) {
        Intrinsics.checkNotNullParameter(appCompatActivity, "<this>");
        if (!z3 || Build.VERSION.SDK_INT < 19) {
            return;
        }
        if (z2) {
            ScreenUtils screenUtils = ScreenUtils.INSTANCE;
            Window window = appCompatActivity.getWindow();
            Intrinsics.checkNotNullExpressionValue(window, "window");
            ScreenUtils.addLayoutFullScreen$default(screenUtils, window, false, 2, null);
        }
        if (z) {
            appCompatActivity.getWindow().getDecorView().setSystemUiVisibility(appCompatActivity.getWindow().getDecorView().getSystemUiVisibility() | 4);
        }
    }

    public static void setImmersionOnWindowFocusChanged$default(AppCompatActivity appCompatActivity, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = false;
        }
        setImmersionOnWindowFocusChanged(appCompatActivity, z, z2, z3);
    }

    public static final void setupActionBar(AppCompatActivity appCompatActivity, Toolbar toolbar, Function1<? super ActionBar, Unit> function1) {
        Intrinsics.checkNotNullParameter(appCompatActivity, "<this>");
        Intrinsics.checkNotNullParameter(toolbar, "toolbar");
        Intrinsics.checkNotNullParameter(function1, "action");
        appCompatActivity.setSupportActionBar(toolbar);
        ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
        if (supportActionBar != null) {
            function1.invoke(supportActionBar);
        }
    }

    public static void setupActionBar$default(AppCompatActivity appCompatActivity, Toolbar toolbar, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1<ActionBar, Unit>() {
                @Override
                public Unit invoke(ActionBar actionBar) {
                    invoke2(actionBar);
                    return Unit.INSTANCE;
                }

                public final void invoke2(ActionBar actionBar) {
                    Intrinsics.checkNotNullParameter(actionBar, "$this$null");
                }
            };
        }
        setupActionBar(appCompatActivity, toolbar, function1);
    }

    public static final void showFragmentInActivity(AppCompatActivity appCompatActivity, final Fragment fragment) {
        Intrinsics.checkNotNullParameter(appCompatActivity, "<this>");
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        FragmentManager supportFragmentManager = appCompatActivity.getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        transactAllowingStateLoss(supportFragmentManager, new Function1<FragmentTransaction, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(FragmentTransaction fragmentTransaction) {
                invoke2(fragmentTransaction);
                return Unit.INSTANCE;
            }

            public final void invoke2(FragmentTransaction fragmentTransaction) {
                Intrinsics.checkNotNullParameter(fragmentTransaction, "$this$transactAllowingStateLoss");
                fragmentTransaction.show(fragment);
            }
        });
    }

    public static final void transact(FragmentManager fragmentManager, Function1<? super FragmentTransaction, Unit> function1) {
        Intrinsics.checkNotNullParameter(fragmentManager, "<this>");
        Intrinsics.checkNotNullParameter(function1, "action");
        FragmentTransaction fragmentTransactionBeginTransaction = fragmentManager.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "");
        function1.invoke(fragmentTransactionBeginTransaction);
        fragmentTransactionBeginTransaction.commit();
    }

    public static final void transactAllowingStateLoss(FragmentManager fragmentManager, Function1<? super FragmentTransaction, Unit> function1) {
        Intrinsics.checkNotNullParameter(fragmentManager, "<this>");
        Intrinsics.checkNotNullParameter(function1, "action");
        FragmentTransaction fragmentTransactionBeginTransaction = fragmentManager.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "");
        function1.invoke(fragmentTransactionBeginTransaction);
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
    }
}
