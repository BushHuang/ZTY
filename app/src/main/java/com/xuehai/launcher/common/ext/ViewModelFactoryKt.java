package com.xuehai.launcher.common.ext;

import androidx.activity.ComponentActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.xuehai.launcher.common.base.AbsActivity;
import com.xuehai.launcher.common.base.AbsViewModel;
import com.xuehai.launcher.common.widget.dialog.CustomDialogFragment;
import com.xuehai.launcher.common.widget.dialog.DialogProvider;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0006\u0010\u0006\u001a\u00020\u0007\u001a)\u0010\b\u001a\u0002H\t\"\b\b\u0000\u0010\t*\u00020\u0005*\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\t0\fH\u0007¢\u0006\u0002\u0010\r\u001a)\u0010\u000e\u001a\u0002H\t\"\b\b\u0000\u0010\t*\u00020\u0005*\u00020\u000f2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\t0\fH\u0007¢\u0006\u0002\u0010\u0010\u001a)\u0010\u000e\u001a\u0002H\t\"\b\b\u0000\u0010\t*\u00020\u0005*\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\t0\fH\u0007¢\u0006\u0002\u0010\r¨\u0006\u0011"}, d2 = {"initAbsViewModel", "", "owner", "Landroidx/activity/ComponentActivity;", "viewModel", "Landroidx/lifecycle/ViewModel;", "obtainViewModelFactory", "Lcom/xuehai/launcher/common/ext/ViewModelFactory;", "obtainFragViewModel", "T", "Landroidx/fragment/app/Fragment;", "viewModelClass", "Ljava/lang/Class;", "(Landroidx/fragment/app/Fragment;Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "obtainViewModel", "Landroidx/appcompat/app/AppCompatActivity;", "(Landroidx/appcompat/app/AppCompatActivity;Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "common_studentToBRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class ViewModelFactoryKt {
    public static final void initAbsViewModel(final ComponentActivity componentActivity, ViewModel viewModel) {
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        if (componentActivity != null && (viewModel instanceof AbsViewModel)) {
            AbsViewModel absViewModel = (AbsViewModel) viewModel;
            ComponentActivity componentActivity2 = componentActivity;
            absViewModel.get_finish$common_studentToBRelease().observe(componentActivity2, new Observer() {
                @Override
                public final void onChanged(Object obj) {
                    componentActivity.finish();
                }
            });
            if (componentActivity instanceof FragmentActivity) {
                absViewModel.get_tipDialog$common_studentToBRelease().observe(componentActivity2, new Observer() {
                    @Override
                    public final void onChanged(Object obj) {
                        ViewModelFactoryKt.m62initAbsViewModel$lambda4(componentActivity, (DialogProvider.Builder) obj);
                    }
                });
            } else {
                absViewModel.get_tipDialog$common_studentToBRelease().observe(componentActivity2, new Observer() {
                    @Override
                    public final void onChanged(Object obj) {
                        ViewModelFactoryKt.m63initAbsViewModel$lambda5(componentActivity, (DialogProvider.Builder) obj);
                    }
                });
            }
            if (componentActivity instanceof AbsActivity) {
                absViewModel.get_showMessage$common_studentToBRelease().observe(componentActivity2, new Observer() {
                    @Override
                    public final void onChanged(Object obj) {
                        ViewModelFactoryKt.m64initAbsViewModel$lambda6(componentActivity, (String) obj);
                    }
                });
                absViewModel.get_progress$common_studentToBRelease().observe(componentActivity2, new Observer() {
                    @Override
                    public final void onChanged(Object obj) {
                        ViewModelFactoryKt.m65initAbsViewModel$lambda7(componentActivity, (String) obj);
                    }
                });
            }
        }
    }

    private static final void m62initAbsViewModel$lambda4(ComponentActivity componentActivity, DialogProvider.Builder builder) {
        CustomDialogFragment customDialogFragmentBuild;
        FragmentActivity fragmentActivity = (FragmentActivity) componentActivity;
        Fragment fragmentFindFragmentByTag = fragmentActivity.getSupportFragmentManager().findFragmentByTag(builder != null ? builder.getTag() : null);
        if (fragmentFindFragmentByTag instanceof DialogFragment) {
            ((DialogFragment) fragmentFindFragmentByTag).dismiss();
        }
        if (builder == null || (customDialogFragmentBuild = builder.build()) == null) {
            return;
        }
        FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "owner.supportFragmentManager");
        customDialogFragmentBuild.show(supportFragmentManager);
    }

    private static final void m63initAbsViewModel$lambda5(ComponentActivity componentActivity, DialogProvider.Builder builder) {
        if (builder != null) {
            builder.buildCustomDialog(componentActivity);
        }
    }

    private static final void m64initAbsViewModel$lambda6(ComponentActivity componentActivity, String str) {
        ((AbsActivity) componentActivity).showToast(str);
    }

    private static final void m65initAbsViewModel$lambda7(ComponentActivity componentActivity, String str) {
        ((AbsActivity) componentActivity).progress(str);
    }

    @Deprecated(message = "use myViewModel ", replaceWith = @ReplaceWith(expression = "Fragment.myViewModel()", imports = {}))
    public static final <T extends ViewModel> T obtainFragViewModel(Fragment fragment, Class<T> cls) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        Intrinsics.checkNotNullParameter(cls, "viewModelClass");
        T t = (T) ViewModelProviders.of(fragment, obtainViewModelFactory()).get(cls);
        Intrinsics.checkNotNullExpressionValue(t, "of(\n        this,\n      …    ).get(viewModelClass)");
        return t;
    }

    @Deprecated(message = "use obtainViewModelFactory ")
    public static final <T extends ViewModel> T obtainViewModel(AppCompatActivity appCompatActivity, Class<T> cls) {
        Intrinsics.checkNotNullParameter(appCompatActivity, "<this>");
        Intrinsics.checkNotNullParameter(cls, "viewModelClass");
        T t = (T) ViewModelProviders.of(appCompatActivity, obtainViewModelFactory()).get(cls);
        Intrinsics.checkNotNullExpressionValue(t, "vm");
        initAbsViewModel(appCompatActivity, t);
        Intrinsics.checkNotNullExpressionValue(t, "of(\n        this,\n      …Model(this, vm)\n        }");
        return t;
    }

    @Deprecated(message = "use obtainViewModelFactory ")
    public static final <T extends ViewModel> T obtainViewModel(Fragment fragment, Class<T> cls) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        Intrinsics.checkNotNullParameter(cls, "viewModelClass");
        FragmentActivity fragmentActivityRequireActivity = fragment.requireActivity();
        T t = (T) ViewModelProviders.of(fragmentActivityRequireActivity, obtainViewModelFactory()).get(cls);
        Intrinsics.checkNotNullExpressionValue(t, "vm");
        initAbsViewModel(fragmentActivityRequireActivity, t);
        Intrinsics.checkNotNullExpressionValue(t, "requireActivity().let {\n…l(it, vm)\n        }\n    }");
        return t;
    }

    public static final ViewModelFactory obtainViewModelFactory() {
        return new ViewModelFactory();
    }
}
