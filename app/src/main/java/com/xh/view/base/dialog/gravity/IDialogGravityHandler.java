package com.xh.view.base.dialog.gravity;

import android.view.Window;
import androidx.lifecycle.DefaultLifecycleObserver;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/xh/view/base/dialog/gravity/IDialogGravityHandler;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "applyGravity", "", "window", "Landroid/view/Window;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface IDialogGravityHandler extends DefaultLifecycleObserver {
    void applyGravity(Window window);
}
