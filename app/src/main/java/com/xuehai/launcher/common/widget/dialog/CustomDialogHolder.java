package com.xuehai.launcher.common.widget.dialog;

import android.app.Dialog;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import com.xuehai.launcher.common.R;
import com.xuehai.launcher.common.widget.dialog.DialogProvider;
import com.zaze.utils.DisplayUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013J\u0016\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016J\u0018\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bJ\u0018\u0010\u001c\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0015\u001a\u00020\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\u0007X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u001d"}, d2 = {"Lcom/xuehai/launcher/common/widget/dialog/CustomDialogHolder;", "", "()V", "dialogCancelBtn", "Landroid/widget/TextView;", "dialogMessageTv", "dialogSplitLine", "Landroid/view/View;", "dialogSureBtn", "dialogTitleSplitLine", "dialogTitleTv", "view", "getView", "()Landroid/view/View;", "setView", "(Landroid/view/View;)V", "measure", "", "dialog", "Landroid/app/Dialog;", "onCreateDialog", "builder", "Lcom/xuehai/launcher/common/widget/dialog/DialogProvider$Builder;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CustomDialogHolder {
    private TextView dialogCancelBtn;
    private TextView dialogMessageTv;
    private View dialogSplitLine;
    private TextView dialogSureBtn;
    private View dialogTitleSplitLine;
    private TextView dialogTitleTv;
    public View view;

    private static final void m85onViewCreated$lambda7(Dialog dialog, DialogProvider.Builder builder, CustomDialogHolder customDialogHolder, View view) {
        Intrinsics.checkNotNullParameter(builder, "$builder");
        Intrinsics.checkNotNullParameter(customDialogHolder, "this$0");
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        Function1<View, Unit> positiveListener = builder.getPositiveListener();
        if (positiveListener != null) {
            TextView textView = customDialogHolder.dialogSureBtn;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dialogSureBtn");
                textView = null;
            }
            positiveListener.invoke(textView);
        }
    }

    private static final void m86onViewCreated$lambda8(Dialog dialog, DialogProvider.Builder builder, CustomDialogHolder customDialogHolder, View view) {
        Intrinsics.checkNotNullParameter(builder, "$builder");
        Intrinsics.checkNotNullParameter(customDialogHolder, "this$0");
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        Function1<View, Unit> negativeListener = builder.getNegativeListener();
        if (negativeListener != null) {
            TextView textView = customDialogHolder.dialogCancelBtn;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dialogCancelBtn");
                textView = null;
            }
            negativeListener.invoke(textView);
        }
    }

    public final View getView() {
        View view = this.view;
        if (view != null) {
            return view;
        }
        Intrinsics.throwUninitializedPropertyAccessException("view");
        return null;
    }

    public final void measure(Dialog dialog) {
        Window window;
        if (dialog == null || (window = dialog.getWindow()) == null) {
            return;
        }
        window.setLayout((int) DisplayUtil.pxFromDp$default(480.0f, null, 2, null), -2);
    }

    public final void onCreateDialog(Dialog dialog, DialogProvider.Builder builder) {
        Window window;
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        Intrinsics.checkNotNullParameter(builder, "builder");
        dialog.setCancelable(builder.getCancelable());
        if (!builder.getApplicationOverlay() || (window = dialog.getWindow()) == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            window.setType(2038);
        } else {
            window.setType(2003);
        }
        window.setGravity(17);
    }

    public final void onCreateView(LayoutInflater inflater, ViewGroup container) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.custom_dialog_frag, container, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…g_frag, container, false)");
        setView(viewInflate);
        View viewFindViewById = getView().findViewById(R.id.dialogTitleTv);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "view.findViewById(R.id.dialogTitleTv)");
        this.dialogTitleTv = (TextView) viewFindViewById;
        View viewFindViewById2 = getView().findViewById(R.id.dialogTitleSplitLine);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "view.findViewById<View>(R.id.dialogTitleSplitLine)");
        this.dialogTitleSplitLine = viewFindViewById2;
        View viewFindViewById3 = getView().findViewById(R.id.dialogMessageTv);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "view.findViewById(R.id.dialogMessageTv)");
        this.dialogMessageTv = (TextView) viewFindViewById3;
        View viewFindViewById4 = getView().findViewById(R.id.dialogSureBtn);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "view.findViewById(R.id.dialogSureBtn)");
        this.dialogSureBtn = (TextView) viewFindViewById4;
        View viewFindViewById5 = getView().findViewById(R.id.dialogCancelBtn);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "view.findViewById(R.id.dialogCancelBtn)");
        this.dialogCancelBtn = (TextView) viewFindViewById5;
        View viewFindViewById6 = getView().findViewById(R.id.dialogSplitLine);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "view.findViewById(R.id.dialogSplitLine)");
        this.dialogSplitLine = viewFindViewById6;
    }

    public final void onViewCreated(final Dialog dialog, final DialogProvider.Builder builder) {
        Unit unit;
        Unit unit2;
        Unit unit3;
        Intrinsics.checkNotNullParameter(builder, "builder");
        CharSequence message = builder.getMessage();
        TextView textView = null;
        if (message != null) {
            TextView textView2 = this.dialogMessageTv;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dialogMessageTv");
                textView2 = null;
            }
            textView2.setGravity(builder.getMessageGravity());
            TextView textView3 = this.dialogMessageTv;
            if (textView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dialogMessageTv");
                textView3 = null;
            }
            textView3.setText(message);
        }
        String title = builder.getTitle();
        if (title != null) {
            TextView textView4 = this.dialogTitleTv;
            if (textView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dialogTitleTv");
                textView4 = null;
            }
            textView4.setVisibility(0);
            View view = this.dialogTitleSplitLine;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dialogTitleSplitLine");
                view = null;
            }
            view.setVisibility(0);
            TextView textView5 = this.dialogTitleTv;
            if (textView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dialogTitleTv");
                textView5 = null;
            }
            textView5.setText(title);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            TextView textView6 = this.dialogTitleTv;
            if (textView6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dialogTitleTv");
                textView6 = null;
            }
            textView6.setVisibility(8);
            View view2 = this.dialogTitleSplitLine;
            if (view2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dialogTitleSplitLine");
                view2 = null;
            }
            view2.setVisibility(8);
        }
        View view3 = this.dialogSplitLine;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dialogSplitLine");
            view3 = null;
        }
        view3.setVisibility(0);
        String negative = builder.getNegative();
        if (negative != null) {
            TextView textView7 = this.dialogCancelBtn;
            if (textView7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dialogCancelBtn");
                textView7 = null;
            }
            textView7.setVisibility(0);
            TextView textView8 = this.dialogCancelBtn;
            if (textView8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dialogCancelBtn");
                textView8 = null;
            }
            textView8.setText(negative);
            unit2 = Unit.INSTANCE;
        } else {
            unit2 = null;
        }
        if (unit2 == null) {
            TextView textView9 = this.dialogCancelBtn;
            if (textView9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dialogCancelBtn");
                textView9 = null;
            }
            textView9.setVisibility(8);
            View view4 = this.dialogSplitLine;
            if (view4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dialogSplitLine");
                view4 = null;
            }
            view4.setVisibility(8);
        }
        String positive = builder.getPositive();
        if (positive != null) {
            TextView textView10 = this.dialogSureBtn;
            if (textView10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dialogSureBtn");
                textView10 = null;
            }
            textView10.setVisibility(0);
            TextView textView11 = this.dialogSureBtn;
            if (textView11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dialogSureBtn");
                textView11 = null;
            }
            textView11.setText(positive);
            unit3 = Unit.INSTANCE;
        } else {
            unit3 = null;
        }
        if (unit3 == null) {
            TextView textView12 = this.dialogSureBtn;
            if (textView12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dialogSureBtn");
                textView12 = null;
            }
            textView12.setVisibility(8);
            View view5 = this.dialogSplitLine;
            if (view5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dialogSplitLine");
                view5 = null;
            }
            view5.setVisibility(8);
        }
        TextView textView13 = this.dialogSureBtn;
        if (textView13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dialogSureBtn");
            textView13 = null;
        }
        textView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view6) {
                CustomDialogHolder.m85onViewCreated$lambda7(dialog, builder, this, view6);
            }
        });
        TextView textView14 = this.dialogCancelBtn;
        if (textView14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dialogCancelBtn");
        } else {
            textView = textView14;
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view6) {
                CustomDialogHolder.m86onViewCreated$lambda8(dialog, builder, this, view6);
            }
        });
    }

    public final void setView(View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        this.view = view;
    }
}
