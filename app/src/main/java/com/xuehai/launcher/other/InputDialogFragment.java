package com.xuehai.launcher.other;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.ComponentActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import com.xuehai.launcher.R;
import com.xuehai.launcher.common.ext.FragmentExtKt;
import com.xuehai.launcher.common.ext.ViewModelFactoryKt;
import com.xuehai.launcher.device.DeviceResetActivity;
import com.zaze.utils.DisplayUtil;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J&\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u001a\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u000e2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0017"}, d2 = {"Lcom/xuehai/launcher/other/InputDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "()V", "inputViewModel", "Lcom/xuehai/launcher/other/InputViewModel;", "getInputViewModel", "()Lcom/xuehai/launcher/other/InputViewModel;", "inputViewModel$delegate", "Lkotlin/Lazy;", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onStart", "", "onViewCreated", "view", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class InputDialogFragment extends DialogFragment {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    private final Lazy inputViewModel;

    public InputDialogFragment() {
        final InputDialogFragment inputDialogFragment = this;
        this.inputViewModel = FragmentExtKt.createMyViewModelLazy(inputDialogFragment, new Function0<ComponentActivity>() {
            {
                super(0);
            }

            @Override
            public final ComponentActivity invoke() {
                return inputDialogFragment.getActivity();
            }
        }, Reflection.getOrCreateKotlinClass(InputViewModel.class), new Function0<ViewModelStore>() {
            {
                super(0);
            }

            @Override
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = inputDialogFragment.getViewModelStore();
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

    private final InputViewModel getInputViewModel() {
        return (InputViewModel) this.inputViewModel.getValue();
    }

    private static final void m153onViewCreated$lambda0(InputDialogFragment inputDialogFragment, Void r3) {
        Dialog dialog;
        Intrinsics.checkNotNullParameter(inputDialogFragment, "this$0");
        Dialog dialog2 = inputDialogFragment.getDialog();
        if (!(dialog2 != null && dialog2.isShowing()) || (dialog = inputDialogFragment.getDialog()) == null) {
            return;
        }
        dialog.dismiss();
    }

    private static final void m154onViewCreated$lambda1(InputDialogFragment inputDialogFragment, Void r3) {
        Intrinsics.checkNotNullParameter(inputDialogFragment, "this$0");
        inputDialogFragment.startActivity(new Intent(inputDialogFragment.getActivity(), (Class<?>) DeviceResetActivity.class));
    }

    private static final void m155onViewCreated$lambda2(InputDialogFragment inputDialogFragment, Boolean bool) {
        Intrinsics.checkNotNullParameter(inputDialogFragment, "this$0");
        if (Intrinsics.areEqual((Object) bool, (Object) true)) {
            ((ImageView) inputDialogFragment._$_findCachedViewById(R.id.inputPointIv)).setVisibility(0);
        } else {
            ((ImageView) inputDialogFragment._$_findCachedViewById(R.id.inputPointIv)).setVisibility(8);
        }
    }

    private static final void m156onViewCreated$lambda3(InputDialogFragment inputDialogFragment, String str) {
        Intrinsics.checkNotNullParameter(inputDialogFragment, "this$0");
        ((TextView) inputDialogFragment._$_findCachedViewById(R.id.inputTipTv)).setText(str);
    }

    private static final void m157onViewCreated$lambda4(InputDialogFragment inputDialogFragment, View view) {
        Intrinsics.checkNotNullParameter(inputDialogFragment, "this$0");
        inputDialogFragment.getInputViewModel().onSure(((EditText) inputDialogFragment._$_findCachedViewById(R.id.inputDialogInputEt)).getText().toString());
    }

    private static final void m158onViewCreated$lambda5(InputDialogFragment inputDialogFragment, View view) {
        Intrinsics.checkNotNullParameter(inputDialogFragment, "this$0");
        inputDialogFragment.getInputViewModel().onCancel();
    }

    private static final void m159onViewCreated$lambda6(InputDialogFragment inputDialogFragment, View view) {
        Intrinsics.checkNotNullParameter(inputDialogFragment, "this$0");
        inputDialogFragment.getInputViewModel().a();
    }

    private static final void m160onViewCreated$lambda7(InputDialogFragment inputDialogFragment, View view) {
        Intrinsics.checkNotNullParameter(inputDialogFragment, "this$0");
        inputDialogFragment.getInputViewModel().b();
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        View viewFindViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null || (viewFindViewById = view2.findViewById(i)) == null) {
            return null;
        }
        map.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setStyle(0, 2131755408);
        setCancelable(false);
        Dialog dialogOnCreateDialog = super.onCreateDialog(savedInstanceState);
        Intrinsics.checkNotNullExpressionValue(dialogOnCreateDialog, "super.onCreateDialog(savedInstanceState)");
        dialogOnCreateDialog.setCancelable(isCancelable());
        return dialogOnCreateDialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Window window;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        Dialog dialog = getDialog();
        if (dialog != null && (window = dialog.getWindow()) != null) {
            window.requestFeature(1);
        }
        return inflater.inflate(2131492921, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override
    public void onStart() {
        Window window;
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog == null || (window = dialog.getWindow()) == null) {
            return;
        }
        window.setLayout((int) DisplayUtil.pxFromDp$default(480.0f, null, 2, null), -2);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        InputDialogFragment inputDialogFragment = this;
        getInputViewModel().getCloseDialogEvent().observe(inputDialogFragment, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                InputDialogFragment.m153onViewCreated$lambda0(this.f$0, (Void) obj);
            }
        });
        getInputViewModel().getShowDeviceResetView().observe(inputDialogFragment, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                InputDialogFragment.m154onViewCreated$lambda1(this.f$0, (Void) obj);
            }
        });
        getInputViewModel().getShowRedPoint().observe(inputDialogFragment, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                InputDialogFragment.m155onViewCreated$lambda2(this.f$0, (Boolean) obj);
            }
        });
        getInputViewModel().getShowLocalKey().observe(inputDialogFragment, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                InputDialogFragment.m156onViewCreated$lambda3(this.f$0, (String) obj);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.inputDialogSureBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view2) {
                InputDialogFragment.m157onViewCreated$lambda4(this.f$0, view2);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.inputDialogCancelBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view2) {
                InputDialogFragment.m158onViewCreated$lambda5(this.f$0, view2);
            }
        });
        _$_findCachedViewById(R.id.inputDialogAView).setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view2) {
                InputDialogFragment.m159onViewCreated$lambda6(this.f$0, view2);
            }
        });
        _$_findCachedViewById(R.id.inputDialogBView).setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view2) {
                InputDialogFragment.m160onViewCreated$lambda7(this.f$0, view2);
            }
        });
        getInputViewModel().load();
    }
}
