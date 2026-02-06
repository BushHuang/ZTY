package com.xuehai.launcher.common.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import com.xuehai.launcher.common.log.MyLog;
import com.xuehai.launcher.common.widget.dialog.DialogProvider;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\fR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/xuehai/launcher/common/widget/dialog/CustomDialog;", "", "context", "Landroid/content/Context;", "builder", "Lcom/xuehai/launcher/common/widget/dialog/DialogProvider$Builder;", "(Landroid/content/Context;Lcom/xuehai/launcher/common/widget/dialog/DialogProvider$Builder;)V", "customDialogHolder", "Lcom/xuehai/launcher/common/widget/dialog/CustomDialogHolder;", "dialog", "Landroid/app/Dialog;", "dismiss", "", "show", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CustomDialog {
    private final CustomDialogHolder customDialogHolder;
    private final Dialog dialog;

    public CustomDialog(Context context, DialogProvider.Builder builder) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(builder, "builder");
        this.dialog = new Dialog(context, builder.getTheme());
        CustomDialogHolder customDialogHolder = new CustomDialogHolder();
        this.customDialogHolder = customDialogHolder;
        customDialogHolder.onCreateDialog(this.dialog, builder);
        CustomDialogHolder customDialogHolder2 = this.customDialogHolder;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        Intrinsics.checkNotNullExpressionValue(layoutInflaterFrom, "from(context)");
        customDialogHolder2.onCreateView(layoutInflaterFrom, null);
        this.customDialogHolder.onViewCreated(this.dialog, builder);
        this.dialog.setContentView(this.customDialogHolder.getView());
    }

    public final void dismiss() {
        try {
            Result.Companion companion = Result.INSTANCE;
            this.dialog.dismiss();
            Result.m228constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m228constructorimpl(ResultKt.createFailure(th));
        }
    }

    public final void show() {
        try {
            Context context = this.dialog.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "dialog.context");
            if ((context instanceof Activity) && ((Activity) context).isFinishing()) {
                MyLog.w("Error[MDM]", "Activity isFinishing return it!");
            } else {
                this.dialog.show();
                this.customDialogHolder.measure(this.dialog);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
