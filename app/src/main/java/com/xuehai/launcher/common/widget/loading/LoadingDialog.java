package com.xuehai.launcher.common.widget.loading;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import com.xuehai.launcher.common.R;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000b\u001a\u00020\fJ\u0018\u0010\r\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\u0010\u0010\u000e\u001a\u00020\u00002\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010J\u0006\u0010\u0011\u001a\u00020\fR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/xuehai/launcher/common/widget/loading/LoadingDialog;", "", "context", "Landroid/content/Context;", "loadingView", "Lcom/xuehai/launcher/common/widget/loading/LoadingView;", "(Landroid/content/Context;Lcom/xuehai/launcher/common/widget/loading/LoadingView;)V", "dismissRunnable", "Ljava/lang/Runnable;", "loadingDialog", "Landroid/app/Dialog;", "dismiss", "", "initDialog", "setText", "text", "", "show", "Companion", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LoadingDialog {
    public static final long MIN_TIME = 500;
    private final Runnable dismissRunnable;
    private Dialog loadingDialog;
    private LoadingView loadingView;

    public LoadingDialog(Context context, LoadingView loadingView) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(loadingView, "loadingView");
        this.dismissRunnable = new Runnable() {
            @Override
            public final void run() {
                LoadingDialog.m87dismissRunnable$lambda1(this.f$0);
            }
        };
        this.loadingView = loadingView;
        initDialog(context, loadingView);
    }

    public LoadingDialog(Context context, LoadingView loadingView, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? LoadingView.INSTANCE.createVerticalLoading(context) : loadingView);
    }

    private static final void m87dismissRunnable$lambda1(LoadingDialog loadingDialog) {
        Intrinsics.checkNotNullParameter(loadingDialog, "this$0");
        Dialog dialog = loadingDialog.loadingDialog;
        Dialog dialog2 = null;
        if (dialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingDialog");
            dialog = null;
        }
        if (dialog.getWindow() != null) {
            LoadingView loadingView = loadingDialog.loadingView;
            if (loadingView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("loadingView");
                loadingView = null;
            }
            loadingView.stopAnimation();
            Dialog dialog3 = loadingDialog.loadingDialog;
            if (dialog3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("loadingDialog");
            } else {
                dialog2 = dialog3;
            }
            dialog2.dismiss();
        }
    }

    private final void initDialog(Context context, LoadingView loadingView) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setGravity(17);
        linearLayout.addView(loadingView);
        Dialog dialog = new Dialog(context, R.style.LoadingDialog);
        dialog.setContentView(linearLayout);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        this.loadingDialog = dialog;
    }

    public final void dismiss() {
        this.dismissRunnable.run();
    }

    public final LoadingDialog setText(String text) {
        Dialog dialog = this.loadingDialog;
        LoadingView loadingView = null;
        if (dialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingDialog");
            dialog = null;
        }
        if (dialog.getWindow() != null) {
            LoadingView loadingView2 = this.loadingView;
            if (loadingView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("loadingView");
            } else {
                loadingView = loadingView2;
            }
            loadingView.setText(text);
        }
        return this;
    }

    public final void show() {
        Dialog dialog = this.loadingDialog;
        Dialog dialog2 = null;
        if (dialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingDialog");
            dialog = null;
        }
        if (dialog.isShowing()) {
            return;
        }
        LoadingView loadingView = this.loadingView;
        if (loadingView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingView");
            loadingView = null;
        }
        loadingView.startAnimation();
        Dialog dialog3 = this.loadingDialog;
        if (dialog3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingDialog");
            dialog3 = null;
        }
        dialog3.show();
        Dialog dialog4 = this.loadingDialog;
        if (dialog4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingDialog");
        } else {
            dialog2 = dialog4;
        }
        Window window = dialog2.getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = -1;
            window.setAttributes(attributes);
        }
    }
}
