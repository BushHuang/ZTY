package com.xh.view.base.dialog.cancelable;

import android.app.Dialog;
import android.content.DialogInterface;
import android.view.KeyEvent;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LifecycleOwner;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/xh/view/base/dialog/cancelable/CanNotCancelHandler;", "Lcom/xh/view/base/dialog/cancelable/BaseCancelableHandler;", "dialogFragment", "Landroidx/fragment/app/DialogFragment;", "(Landroidx/fragment/app/DialogFragment;)V", "onResume", "", "owner", "Landroidx/lifecycle/LifecycleOwner;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CanNotCancelHandler extends BaseCancelableHandler {
    public CanNotCancelHandler(DialogFragment dialogFragment) {
        super(dialogFragment);
        Intrinsics.checkNotNullParameter(dialogFragment, "dialogFragment");
    }

    private static final boolean m18onResume$lambda0(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        return i == 4;
    }

    @Override
    public void onResume(LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        super.onResume(owner);
        Dialog dialog = getDialogFragment().getDialog();
        if (dialog != null) {
            dialog.setCanceledOnTouchOutside(false);
        }
        Dialog dialog2 = getDialogFragment().getDialog();
        if (dialog2 == null) {
            return;
        }
        dialog2.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return CanNotCancelHandler.m18onResume$lambda0(dialogInterface, i, keyEvent);
            }
        });
    }
}
