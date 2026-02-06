package com.xh.view.base.dialog.gravity;

import android.view.Window;
import androidx.fragment.app.DialogFragment;
import com.xh.view.base.ext.WindowExtentionKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/xh/view/base/dialog/gravity/FixPositionDialogGravityHandler;", "Lcom/xh/view/base/dialog/gravity/BaseDialogGravityHandler;", "dialogFragment", "Landroidx/fragment/app/DialogFragment;", "(Landroidx/fragment/app/DialogFragment;)V", "applyGravity", "", "window", "Landroid/view/Window;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FixPositionDialogGravityHandler extends BaseDialogGravityHandler {
    public FixPositionDialogGravityHandler(DialogFragment dialogFragment) {
        super(dialogFragment);
        Intrinsics.checkNotNullParameter(dialogFragment, "dialogFragment");
        setGravityEnum(GravityEnum.TOP);
        setAnimationStyleresId(null);
    }

    @Override
    public void applyGravity(Window window) {
        Intrinsics.checkNotNullParameter(window, "window");
        WindowExtentionKt.applyGravityStyle(window, 48, null, (12 & 4) != 0 ? -1 : 0, (12 & 8) != 0 ? -2 : 0, (12 & 16) != 0 ? 0 : getXPosition(), (12 & 32) != 0 ? 0 : getYPosition());
    }
}
