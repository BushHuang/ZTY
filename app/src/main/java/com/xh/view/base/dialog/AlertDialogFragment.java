package com.xh.view.base.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import com.xh.view.base.dialog.BaseDialogFragment;
import com.xh.view.base.dialog.gravity.GravityEnum;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\tJ\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\tJ\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\tJ\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\tJ\u000e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/xh/view/base/dialog/AlertDialogFragment;", "Lcom/xh/view/base/dialog/BaseDialogFragment;", "()V", "mButtonNegativeText", "", "mButtonPositiveText", "mMessage", "mTitle", "which", "", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "setButtonNegativeText", "buttonNegativeText", "setButtonPositiveText", "buttonPositiveText", "setMessage", "message", "setTitle", "title", "setWhich", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AlertDialogFragment extends BaseDialogFragment {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private CharSequence mButtonNegativeText;
    private CharSequence mButtonPositiveText;
    private CharSequence mMessage;
    private CharSequence mTitle;
    private String which;

    public AlertDialogFragment() {
        applyGravityStyle(GravityEnum.Center);
    }

    private static final void m15onCreateDialog$lambda0(AlertDialogFragment alertDialogFragment, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(alertDialogFragment, "this$0");
        BaseDialogFragment.BaseDialogListener mDefaultListener = alertDialogFragment.getMDefaultListener();
        if (mDefaultListener != null) {
            AlertDialogFragment alertDialogFragment2 = alertDialogFragment;
            String str = alertDialogFragment.which;
            if (str == null) {
                Intrinsics.throwUninitializedPropertyAccessException("which");
                str = null;
            }
            mDefaultListener.onDialogPositiveClick(alertDialogFragment2, str);
        }
        alertDialogFragment.dismiss();
    }

    private static final void m16onCreateDialog$lambda1(AlertDialogFragment alertDialogFragment, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(alertDialogFragment, "this$0");
        BaseDialogFragment.BaseDialogListener mDefaultListener = alertDialogFragment.getMDefaultListener();
        if (mDefaultListener != null) {
            AlertDialogFragment alertDialogFragment2 = alertDialogFragment;
            String str = alertDialogFragment.which;
            if (str == null) {
                Intrinsics.throwUninitializedPropertyAccessException("which");
                str = null;
            }
            mDefaultListener.onDialogNegativeClick(alertDialogFragment2, str);
        }
        alertDialogFragment.dismiss();
    }

    @Override
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override
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
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        CharSequence charSequence = this.mTitle;
        if (charSequence != null) {
            builder.setTitle(charSequence);
        }
        CharSequence charSequence2 = this.mMessage;
        if (charSequence2 != null) {
            builder.setMessage(charSequence2);
        }
        CharSequence charSequence3 = this.mButtonPositiveText;
        if (charSequence3 != null) {
            builder.setPositiveButton(charSequence3, new DialogInterface.OnClickListener() {
                @Override
                public final void onClick(DialogInterface dialogInterface, int i) {
                    AlertDialogFragment.m15onCreateDialog$lambda0(this.f$0, dialogInterface, i);
                }
            });
        }
        CharSequence charSequence4 = this.mButtonNegativeText;
        if (charSequence4 != null) {
            builder.setNegativeButton(charSequence4, new DialogInterface.OnClickListener() {
                @Override
                public final void onClick(DialogInterface dialogInterface, int i) {
                    AlertDialogFragment.m16onCreateDialog$lambda1(this.f$0, dialogInterface, i);
                }
            });
        }
        AlertDialog alertDialogCreate = builder.create();
        Intrinsics.checkNotNullExpressionValue(alertDialogCreate, "builder.create()");
        return alertDialogCreate;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final AlertDialogFragment setButtonNegativeText(String buttonNegativeText) {
        Intrinsics.checkNotNullParameter(buttonNegativeText, "buttonNegativeText");
        this.mButtonNegativeText = buttonNegativeText;
        return this;
    }

    public final AlertDialogFragment setButtonPositiveText(String buttonPositiveText) {
        Intrinsics.checkNotNullParameter(buttonPositiveText, "buttonPositiveText");
        this.mButtonPositiveText = buttonPositiveText;
        return this;
    }

    public final AlertDialogFragment setMessage(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        this.mMessage = message;
        return this;
    }

    public final AlertDialogFragment setTitle(String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        this.mTitle = title;
        return this;
    }

    public final AlertDialogFragment setWhich(String which) {
        Intrinsics.checkNotNullParameter(which, "which");
        this.which = which;
        return this;
    }
}
