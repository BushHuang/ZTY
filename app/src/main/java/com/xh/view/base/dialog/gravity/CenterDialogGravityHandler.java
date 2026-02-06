package com.xh.view.base.dialog.gravity;

import androidx.fragment.app.DialogFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/xh/view/base/dialog/gravity/CenterDialogGravityHandler;", "Lcom/xh/view/base/dialog/gravity/BaseDialogGravityHandler;", "dialogFragment", "Landroidx/fragment/app/DialogFragment;", "(Landroidx/fragment/app/DialogFragment;)V", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CenterDialogGravityHandler extends BaseDialogGravityHandler {
    public CenterDialogGravityHandler(DialogFragment dialogFragment) {
        super(dialogFragment);
        Intrinsics.checkNotNullParameter(dialogFragment, "dialogFragment");
        setGravityEnum(GravityEnum.Center);
        setAnimationStyleresId(null);
    }
}
