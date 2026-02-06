package com.xuehai.launcher.common.ext;

import androidx.activity.ComponentActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aZ\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00042\u000e\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00062\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u0006H\u0007\u001aF\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\u0010\b\b\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u0010\b\n\u0010\f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u0006H\u0086\bø\u0001\u0000\u001a\u001f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u0004H\u0087\b\u001a\u001f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u0004H\u0087\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0011"}, d2 = {"createMyViewModelLazy", "Lkotlin/Lazy;", "VM", "Landroidx/lifecycle/ViewModel;", "Landroidx/fragment/app/Fragment;", "activity", "Lkotlin/Function0;", "Landroidx/activity/ComponentActivity;", "viewModelClass", "Lkotlin/reflect/KClass;", "storeProducer", "Landroidx/lifecycle/ViewModelStore;", "factoryProducer", "Landroidx/lifecycle/ViewModelProvider$Factory;", "customActivityViewModels", "myActivityViewModels", "myViewModels", "common_studentToBRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class FragmentExtKt {

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/lifecycle/ViewModelStore;", "VM", "Landroidx/lifecycle/ViewModel;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 176)
    public static final class AnonymousClass1 extends Lambda implements Function0<ViewModelStore> {
        final Fragment $this_customActivityViewModels;

        public AnonymousClass1(Fragment fragment) {
            super(0);
            this.$this_customActivityViewModels = fragment;
        }

        @Override
        public final ViewModelStore invoke() {
            ViewModelStore viewModelStore = this.$this_customActivityViewModels.requireActivity().getViewModelStore();
            Intrinsics.checkNotNullExpressionValue(viewModelStore, "requireActivity().viewModelStore");
            return viewModelStore;
        }
    }

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/lifecycle/ViewModelProvider$Factory;", "VM", "Landroidx/lifecycle/ViewModel;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 176)
    public static final class AnonymousClass2 extends Lambda implements Function0<ViewModelProvider.Factory> {
        final Fragment $this_customActivityViewModels;

        public AnonymousClass2(Fragment fragment) {
            super(0);
            this.$this_customActivityViewModels = fragment;
        }

        @Override
        public final ViewModelProvider.Factory invoke() {
            ViewModelProvider.Factory defaultViewModelProviderFactory = this.$this_customActivityViewModels.requireActivity().getDefaultViewModelProviderFactory();
            Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "requireActivity().defaultViewModelProviderFactory");
            return defaultViewModelProviderFactory;
        }
    }

    public static final <VM extends ViewModel> Lazy<VM> createMyViewModelLazy(final Fragment fragment, Function0<? extends ComponentActivity> function0, KClass<VM> kClass, Function0<? extends ViewModelStore> function02, Function0<? extends ViewModelProvider.Factory> function03) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        Intrinsics.checkNotNullParameter(function0, "activity");
        Intrinsics.checkNotNullParameter(kClass, "viewModelClass");
        Intrinsics.checkNotNullParameter(function02, "storeProducer");
        if (function03 == null) {
            function03 = new Function0<ViewModelProvider.Factory>() {
                {
                    super(0);
                }

                @Override
                public final ViewModelProvider.Factory invoke() {
                    ViewModelProvider.Factory defaultViewModelProviderFactory = fragment.getDefaultViewModelProviderFactory();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelProviderFactory, "defaultViewModelProviderFactory");
                    return defaultViewModelProviderFactory;
                }
            };
        }
        return new MyViewModelLazy(function0, kClass, function02, function03);
    }

    public static Lazy createMyViewModelLazy$default(Fragment fragment, Function0 function0, KClass kClass, Function0 function02, Function0 function03, int i, Object obj) {
        if ((i & 8) != 0) {
            function03 = null;
        }
        return createMyViewModelLazy(fragment, function0, kClass, function02, function03);
    }

    public static final <VM extends ViewModel> Lazy<VM> customActivityViewModels(Fragment fragment, Function0<? extends ComponentActivity> function0, Function0<? extends ViewModelProvider.Factory> function02) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        Intrinsics.checkNotNullParameter(function0, "activity");
        Intrinsics.reifiedOperationMarker(4, "VM");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(ViewModel.class);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(fragment);
        if (function02 == null) {
            function02 = new AnonymousClass2(fragment);
        }
        return createMyViewModelLazy(fragment, function0, orCreateKotlinClass, anonymousClass1, function02);
    }

    public static Lazy customActivityViewModels$default(Fragment fragment, Function0 function0, Function0 function02, int i, Object obj) {
        if ((i & 2) != 0) {
            function02 = null;
        }
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        Intrinsics.checkNotNullParameter(function0, "activity");
        Intrinsics.reifiedOperationMarker(4, "VM");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(ViewModel.class);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(fragment);
        if (function02 == null) {
            function02 = new AnonymousClass2(fragment);
        }
        return createMyViewModelLazy(fragment, function0, orCreateKotlinClass, anonymousClass1, function02);
    }

    public static final <VM extends ViewModel> Lazy<VM> myActivityViewModels(final Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        Function0<ComponentActivity> function0 = new Function0<ComponentActivity>() {
            {
                super(0);
            }

            @Override
            public final ComponentActivity invoke() {
                return fragment.getActivity();
            }
        };
        AnonymousClass2 anonymousClass2 = new Function0<ViewModelProvider.Factory>() {
            @Override
            public final ViewModelProvider.Factory invoke() {
                return ViewModelFactoryKt.obtainViewModelFactory();
            }
        };
        Intrinsics.reifiedOperationMarker(4, "VM");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(ViewModel.class);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(fragment);
        if (anonymousClass2 == null) {
            anonymousClass2 = new AnonymousClass2(fragment);
        }
        return createMyViewModelLazy(fragment, function0, orCreateKotlinClass, anonymousClass1, anonymousClass2);
    }

    public static final <VM extends ViewModel> Lazy<VM> myViewModels(final Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        Function0<ComponentActivity> function0 = new Function0<ComponentActivity>() {
            {
                super(0);
            }

            @Override
            public final ComponentActivity invoke() {
                return fragment.getActivity();
            }
        };
        Intrinsics.reifiedOperationMarker(4, "VM");
        return createMyViewModelLazy(fragment, function0, Reflection.getOrCreateKotlinClass(ViewModel.class), new Function0<ViewModelStore>() {
            {
                super(0);
            }

            @Override
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = fragment.getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore, "this.viewModelStore");
                return viewModelStore;
            }
        }, new Function0<ViewModelProvider.Factory>() {
            @Override
            public final ViewModelProvider.Factory invoke() {
                return ViewModelFactoryKt.obtainViewModelFactory();
            }
        });
    }
}
