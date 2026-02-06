package com.xh.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class XHAlertDialog extends Dialog {

    public interface DialogActionListener {
        void onNegativeAction(Dialog dialog);

        void onPositiveAction(Dialog dialog);

        void onReportAction(Dialog dialog);
    }

    public static class DialogProperty {
        boolean canClose;
        String content;
        String errorMsg;
        String negativeLab;
        String positiveLab;
        boolean reportVisible;
        String title;

        public DialogProperty() {
            this.negativeLab = "取消";
            this.canClose = false;
        }

        public DialogProperty(String str, String str2, String str3, String str4, String str5, boolean z) {
            this.negativeLab = "取消";
            this.canClose = false;
            this.content = str;
            this.title = str2;
            this.errorMsg = str3;
            this.negativeLab = str4;
            this.positiveLab = str5;
            this.reportVisible = z;
        }

        public DialogProperty copy() {
            DialogProperty dialogProperty = new DialogProperty();
            dialogProperty.setContent(this.content);
            dialogProperty.setTitle(this.title);
            dialogProperty.setReportVisible(this.reportVisible);
            dialogProperty.setPositiveLab(this.positiveLab);
            dialogProperty.setNegativeLab(this.negativeLab);
            dialogProperty.setErrorMsg(this.errorMsg);
            dialogProperty.setCanClose(this.canClose);
            return dialogProperty;
        }

        public String getContent() {
            String str = this.content;
            return str != null ? str : "";
        }

        public String getErrorMsg() {
            String str = this.errorMsg;
            return str != null ? str : "";
        }

        public String getNegativeLab() {
            return !TextUtils.isEmpty(this.negativeLab) ? this.negativeLab : "";
        }

        public String getPositiveLab() {
            return !TextUtils.isEmpty(this.positiveLab) ? this.positiveLab : "确认";
        }

        public String getTitle() {
            String str = this.title;
            return str != null ? str : "";
        }

        public boolean isCanClose() {
            return this.canClose;
        }

        public void setCanClose(boolean z) {
            this.canClose = z;
        }

        public void setContent(String str) {
            this.content = str;
        }

        public void setErrorMsg(String str) {
            this.errorMsg = str;
        }

        public void setNegativeLab(String str) {
            this.negativeLab = str;
        }

        public void setPositiveLab(String str) {
            this.positiveLab = str;
        }

        public void setReportVisible(boolean z) {
            this.reportVisible = z;
        }

        public void setTitle(String str) {
            this.title = str;
        }
    }

    public XHAlertDialog(Context context) {
        super(context, R.style.XhDialog);
    }

    private void bindView(DialogProperty dialogProperty, View view, final DialogActionListener dialogActionListener) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        View viewFindViewById = view.findViewById(R.id.layout_scroll);
        TextView textView = (TextView) view.findViewById(R.id.tv_title);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_content);
        TextView textView3 = (TextView) view.findViewById(R.id.tv_error_msg);
        TextView textView4 = (TextView) view.findViewById(R.id.tv_report);
        TextView textView5 = (TextView) view.findViewById(R.id.btnNegative);
        View viewFindViewById2 = view.findViewById(R.id.divider);
        TextView textView6 = (TextView) view.findViewById(R.id.btnPositive);
        textView.setVisibility(TextUtils.isEmpty(dialogProperty.getTitle()) ? 8 : 0);
        textView.setText(dialogProperty.getTitle());
        textView2.setText(dialogProperty.getContent());
        boolean zIsEmpty = TextUtils.isEmpty(dialogProperty.getErrorMsg());
        textView3.setText(dialogProperty.getErrorMsg());
        textView3.setVisibility(zIsEmpty ? 8 : 0);
        textView4.setVisibility(dialogProperty.reportVisible ? 0 : 8);
        boolean zIsEmpty2 = TextUtils.isEmpty(dialogProperty.getNegativeLab());
        viewFindViewById2.setVisibility(zIsEmpty2 ? 8 : 0);
        textView5.setText(dialogProperty.getNegativeLab());
        textView5.setVisibility(zIsEmpty2 ? 8 : 0);
        textView6.setBackgroundResource(zIsEmpty2 ? R.drawable.selector_round_gray : R.drawable.sel_def_gray_right);
        textView6.setText(dialogProperty.getPositiveLab());
        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                DialogActionListener dialogActionListener2 = dialogActionListener;
                if (dialogActionListener2 != null) {
                    dialogActionListener2.onNegativeAction(XHAlertDialog.this);
                }
            }
        });
        textView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                DialogActionListener dialogActionListener2 = dialogActionListener;
                if (dialogActionListener2 != null) {
                    dialogActionListener2.onPositiveAction(XHAlertDialog.this);
                }
            }
        });
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                DialogActionListener dialogActionListener2 = dialogActionListener;
                if (dialogActionListener2 != null) {
                    dialogActionListener2.onReportAction(XHAlertDialog.this);
                }
            }
        });
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        viewFindViewById.measure(iMakeMeasureSpec, iMakeMeasureSpec);
        if (viewFindViewById.getMeasuredHeight() > displayMetrics.heightPixels / 2) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewFindViewById.getLayoutParams();
            layoutParams.height = displayMetrics.heightPixels / 2;
            viewFindViewById.setLayoutParams(layoutParams);
        }
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(View.inflate(getContext(), R.layout.layout_msg_dialog, null));
    }

    @Override
    @Deprecated
    public void show() {
    }

    void show(DialogProperty dialogProperty, DialogActionListener dialogActionListener) {
        if (dialogProperty == null) {
            return;
        }
        super.show();
        setCancelable(dialogProperty.isCanClose());
        setCanceledOnTouchOutside(dialogProperty.isCanClose());
        if (getWindow() != null) {
            getWindow().setBackgroundDrawable(new ColorDrawable(0));
            View decorView = getWindow().getDecorView();
            if (decorView != null) {
                bindView(dialogProperty, decorView, dialogActionListener);
            }
        }
    }

    void showNoNegativeDialog(String str, String str2, String str3, DialogActionListener dialogActionListener) {
        showNoNegativeDialog(str, str2, str3, dialogActionListener, false);
    }

    public void showNoNegativeDialog(String str, String str2, String str3, DialogActionListener dialogActionListener, boolean z) {
        DialogProperty dialogProperty = new DialogProperty();
        dialogProperty.setContent(str2);
        if (TextUtils.isEmpty(str)) {
            str = "提示";
        }
        dialogProperty.setTitle(str);
        dialogProperty.setNegativeLab(null);
        dialogProperty.setCanClose(z);
        dialogProperty.setPositiveLab(str3);
        show(dialogProperty, dialogActionListener);
    }

    void showNoTitleDialog(String str, String str2, String str3, DialogActionListener dialogActionListener) {
        showNoTitleDialog(str, str2, str3, dialogActionListener, false);
    }

    public void showNoTitleDialog(String str, String str2, String str3, DialogActionListener dialogActionListener, boolean z) {
        DialogProperty dialogProperty = new DialogProperty();
        dialogProperty.setContent(str);
        if (!TextUtils.isEmpty(str2)) {
            dialogProperty.setNegativeLab(str2);
        }
        dialogProperty.setCanClose(z);
        dialogProperty.setPositiveLab(str3);
        show(dialogProperty, dialogActionListener);
    }
}
