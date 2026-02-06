package com.xh.view.base.dialog.gravity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import com.xh.view.base.ext.WindowExtentionKt;
import com.xh.view.base.outlifecycle.OutLifecycleFragmentObserver;
import com.xh.xhcore.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0016J\u0006\u0010\"\u001a\u00020\u0000J\u0010\u0010#\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020%H\u0016J\u0010\u0010&\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020%H\u0016R\"\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0018\"\u0004\b\u001d\u0010\u001a¨\u0006'"}, d2 = {"Lcom/xh/view/base/dialog/gravity/BaseDialogGravityHandler;", "Lcom/xh/view/base/dialog/gravity/IDialogGravityHandler;", "Lcom/xh/view/base/outlifecycle/OutLifecycleFragmentObserver;", "dialogFragment", "Landroidx/fragment/app/DialogFragment;", "(Landroidx/fragment/app/DialogFragment;)V", "animationStyleresId", "", "getAnimationStyleresId", "()Ljava/lang/Integer;", "setAnimationStyleresId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "gravityEnum", "Lcom/xh/view/base/dialog/gravity/GravityEnum;", "getGravityEnum", "()Lcom/xh/view/base/dialog/gravity/GravityEnum;", "setGravityEnum", "(Lcom/xh/view/base/dialog/gravity/GravityEnum;)V", "mDialogFragment", "getMDialogFragment", "()Landroidx/fragment/app/DialogFragment;", "xPosition", "getXPosition", "()I", "setXPosition", "(I)V", "yPosition", "getYPosition", "setYPosition", "applyGravity", "", "window", "Landroid/view/Window;", "build", "onCreate", "owner", "Landroidx/lifecycle/LifecycleOwner;", "onStart", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class BaseDialogGravityHandler implements IDialogGravityHandler, OutLifecycleFragmentObserver {
    private Integer animationStyleresId;
    private GravityEnum gravityEnum;
    private final DialogFragment mDialogFragment;
    private int xPosition;
    private int yPosition;

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[GravityEnum.values().length];
            iArr[GravityEnum.Bottom.ordinal()] = 1;
            iArr[GravityEnum.FixPosition.ordinal()] = 2;
            iArr[GravityEnum.Center.ordinal()] = 3;
            iArr[GravityEnum.HeightFullScreen.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public BaseDialogGravityHandler(DialogFragment dialogFragment) {
        Intrinsics.checkNotNullParameter(dialogFragment, "dialogFragment");
        this.mDialogFragment = dialogFragment;
    }

    @Override
    public void applyGravity(Window window) {
        int i;
        Intrinsics.checkNotNullParameter(window, "window");
        GravityEnum gravityEnum = this.gravityEnum;
        int i2 = gravityEnum == null ? -1 : WhenMappings.$EnumSwitchMapping$0[gravityEnum.ordinal()];
        if (i2 == 1) {
            i = 80;
        } else if (i2 == 2) {
            i = 48;
        } else if (i2 == 3) {
            i = 17;
        }
        WindowExtentionKt.applyGravityStyle(window, i, this.animationStyleresId, (12 & 4) != 0 ? -1 : 0, (12 & 8) != 0 ? -2 : 0, (12 & 16) != 0 ? 0 : 0, (12 & 32) != 0 ? 0 : 0);
    }

    public final BaseDialogGravityHandler build() {
        GravityEnum gravityEnum = this.gravityEnum;
        int i = gravityEnum == null ? -1 : WhenMappings.$EnumSwitchMapping$0[gravityEnum.ordinal()];
        BottomDialogGravityHandler bottomDialogGravityHandler = i != 1 ? i != 2 ? i != 3 ? i != 4 ? new BottomDialogGravityHandler(this.mDialogFragment) : new HeightFullScreenDialogGravityHandler(this.mDialogFragment) : new CenterDialogGravityHandler(this.mDialogFragment) : new FixPositionDialogGravityHandler(this.mDialogFragment) : new BottomDialogGravityHandler(this.mDialogFragment);
        Integer num = this.animationStyleresId;
        if (num != null) {
            bottomDialogGravityHandler.setAnimationStyleresId(Integer.valueOf(num.intValue()));
        }
        GravityEnum gravityEnum2 = this.gravityEnum;
        if (gravityEnum2 != null) {
            bottomDialogGravityHandler.setGravityEnum(gravityEnum2);
        }
        bottomDialogGravityHandler.xPosition = this.xPosition;
        bottomDialogGravityHandler.yPosition = this.yPosition;
        return bottomDialogGravityHandler;
    }

    public final Integer getAnimationStyleresId() {
        return this.animationStyleresId;
    }

    public final GravityEnum getGravityEnum() {
        return this.gravityEnum;
    }

    public final DialogFragment getMDialogFragment() {
        return this.mDialogFragment;
    }

    public final int getXPosition() {
        return this.xPosition;
    }

    public final int getYPosition() {
        return this.yPosition;
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        OutLifecycleFragmentObserver.DefaultImpls.onActivityCreated(this, bundle);
    }

    @Override
    public void onCreate(LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.mDialogFragment.setStyle(2, R.style.dialogFullScreen);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return OutLifecycleFragmentObserver.DefaultImpls.onCreateView(this, layoutInflater, viewGroup, bundle);
    }

    @Override
    public void onDestroy(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onDestroy(this, lifecycleOwner);
    }

    @Override
    public void onDestroyView() {
        OutLifecycleFragmentObserver.DefaultImpls.onDestroyView(this);
    }

    @Override
    public void onHiddenChanged(boolean z) {
        OutLifecycleFragmentObserver.DefaultImpls.onHiddenChanged(this, z);
    }

    @Override
    public void onPause(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onPause(this, lifecycleOwner);
    }

    @Override
    public void onResume(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onResume(this, lifecycleOwner);
    }

    @Override
    public void onStart(LifecycleOwner owner) {
        Window window;
        Intrinsics.checkNotNullParameter(owner, "owner");
        Dialog dialog = this.mDialogFragment.getDialog();
        if (dialog != null && (window = dialog.getWindow()) != null) {
            applyGravity(window);
        }
        Dialog dialog2 = this.mDialogFragment.getDialog();
        if (dialog2 == null) {
            return;
        }
        dialog2.setCanceledOnTouchOutside(true);
    }

    @Override
    public void onStop(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onStop(this, lifecycleOwner);
    }

    public final void setAnimationStyleresId(Integer num) {
        this.animationStyleresId = num;
    }

    public final void setGravityEnum(GravityEnum gravityEnum) {
        this.gravityEnum = gravityEnum;
    }

    @Override
    public void setUserVisibleHint(boolean z) {
        OutLifecycleFragmentObserver.DefaultImpls.setUserVisibleHint(this, z);
    }

    public final void setXPosition(int i) {
        this.xPosition = i;
    }

    public final void setYPosition(int i) {
        this.yPosition = i;
    }
}
