package com.xh.view.base.dialog.cancelable;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\r\u001a\u00020\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u000e"}, d2 = {"Lcom/xh/view/base/dialog/cancelable/BaseCancelableHandler;", "Lcom/xh/view/base/dialog/cancelable/ICancelableHandler;", "dialogFragment", "Landroidx/fragment/app/DialogFragment;", "(Landroidx/fragment/app/DialogFragment;)V", "cancelable", "", "getCancelable", "()Z", "setCancelable", "(Z)V", "getDialogFragment", "()Landroidx/fragment/app/DialogFragment;", "build", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class BaseCancelableHandler implements ICancelableHandler {
    private boolean cancelable;
    private final DialogFragment dialogFragment;

    public BaseCancelableHandler(DialogFragment dialogFragment) {
        Intrinsics.checkNotNullParameter(dialogFragment, "dialogFragment");
        this.cancelable = true;
        this.dialogFragment = dialogFragment;
    }

    public final BaseCancelableHandler build() {
        return this.cancelable ? new CanCancelHandler(this.dialogFragment) : new CanNotCancelHandler(this.dialogFragment);
    }

    public final boolean getCancelable() {
        return this.cancelable;
    }

    protected final DialogFragment getDialogFragment() {
        return this.dialogFragment;
    }

    @Override
    public void onCreate(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onCreate(this, lifecycleOwner);
    }

    @Override
    public void onDestroy(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onDestroy(this, lifecycleOwner);
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
    public void onStart(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onStart(this, lifecycleOwner);
    }

    @Override
    public void onStop(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onStop(this, lifecycleOwner);
    }

    public final void setCancelable(boolean z) {
        this.cancelable = z;
    }
}
