package com.xh.view.base.dialog.gravity;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LifecycleOwner;
import com.xh.view.base.ext.WindowExtentionKt;
import com.xh.xhcore.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0016J&\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016¨\u0006\u0014"}, d2 = {"Lcom/xh/view/base/dialog/gravity/HeightFullScreenDialogGravityHandler;", "Lcom/xh/view/base/dialog/gravity/BaseDialogGravityHandler;", "dialogFragment", "Landroidx/fragment/app/DialogFragment;", "(Landroidx/fragment/app/DialogFragment;)V", "applyGravity", "", "window", "Landroid/view/Window;", "onCreate", "owner", "Landroidx/lifecycle/LifecycleOwner;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HeightFullScreenDialogGravityHandler extends BaseDialogGravityHandler {
    public HeightFullScreenDialogGravityHandler(DialogFragment dialogFragment) {
        super(dialogFragment);
        Intrinsics.checkNotNullParameter(dialogFragment, "dialogFragment");
        setAnimationStyleresId(Integer.valueOf(R.style.dialogAnimAlpha));
    }

    @Override
    public void applyGravity(Window window) {
        Intrinsics.checkNotNullParameter(window, "window");
        WindowExtentionKt.applyGravityStyle(window, 48, getAnimationStyleresId(), (12 & 4) != 0 ? -1 : -1, (12 & 8) != 0 ? -2 : -1, (12 & 16) != 0 ? 0 : 0, (12 & 32) != 0 ? 0 : 0);
    }

    @Override
    public void onCreate(LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        getMDialogFragment().setStyle(2, R.style.TransparentBase);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Window window;
        Window window2;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        Dialog dialog = getMDialogFragment().getDialog();
        if (dialog != null && (window2 = dialog.getWindow()) != null) {
            window2.requestFeature(1);
        }
        Dialog dialog2 = getMDialogFragment().getDialog();
        if (dialog2 == null || (window = dialog2.getWindow()) == null) {
            return null;
        }
        window.setBackgroundDrawable(new ColorDrawable(0));
        return null;
    }
}
