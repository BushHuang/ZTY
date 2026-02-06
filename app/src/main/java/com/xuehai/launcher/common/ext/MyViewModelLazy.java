package com.xuehai.launcher.common.ext;

import androidx.activity.ComponentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B?\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0005¢\u0006\u0002\u0010\rJ\b\u0010\u0015\u001a\u00020\u0011H\u0016J\u001a\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0019\u001a\u00020\u0002H\u0002R\u0016\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000fR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\u00028\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/xuehai/launcher/common/ext/MyViewModelLazy;", "VM", "Landroidx/lifecycle/ViewModel;", "Lkotlin/Lazy;", "activity", "Lkotlin/Function0;", "Landroidx/activity/ComponentActivity;", "viewModelClass", "Lkotlin/reflect/KClass;", "storeProducer", "Landroidx/lifecycle/ViewModelStore;", "factoryProducer", "Landroidx/lifecycle/ViewModelProvider$Factory;", "(Lkotlin/jvm/functions/Function0;Lkotlin/reflect/KClass;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "cached", "Landroidx/lifecycle/ViewModel;", "observed", "", "value", "getValue", "()Landroidx/lifecycle/ViewModel;", "isInitialized", "observe", "", "owner", "viewModel", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MyViewModelLazy<VM extends ViewModel> implements Lazy<VM> {
    private final Function0<ComponentActivity> activity;
    private VM cached;
    private final Function0<ViewModelProvider.Factory> factoryProducer;
    private boolean observed;
    private final Function0<ViewModelStore> storeProducer;
    private final KClass<VM> viewModelClass;

    public MyViewModelLazy(Function0<? extends ComponentActivity> function0, KClass<VM> kClass, Function0<? extends ViewModelStore> function02, Function0<? extends ViewModelProvider.Factory> function03) {
        Intrinsics.checkNotNullParameter(function0, "activity");
        Intrinsics.checkNotNullParameter(kClass, "viewModelClass");
        Intrinsics.checkNotNullParameter(function02, "storeProducer");
        Intrinsics.checkNotNullParameter(function03, "factoryProducer");
        this.activity = function0;
        this.viewModelClass = kClass;
        this.storeProducer = function02;
        this.factoryProducer = function03;
    }

    private final void observe(ComponentActivity owner, ViewModel viewModel) {
        if (this.observed) {
            return;
        }
        if (owner == null) {
            this.observed = false;
        } else {
            this.observed = true;
            ViewModelFactoryKt.initAbsViewModel(owner, viewModel);
        }
    }

    @Override
    public VM getValue() {
        VM vm = this.cached;
        if (vm == null) {
            vm = (VM) new ViewModelProvider(this.storeProducer.invoke(), this.factoryProducer.invoke()).get(JvmClassMappingKt.getJavaClass((KClass) this.viewModelClass));
            this.cached = vm;
        }
        Intrinsics.checkNotNullExpressionValue(vm, "if (viewModel == null) {…  viewModel\n            }");
        observe(this.activity.invoke(), vm);
        return vm;
    }

    @Override
    public boolean isInitialized() {
        return this.cached != null;
    }
}
