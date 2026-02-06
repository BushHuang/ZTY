package com.xh.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

public class UpdateDialog extends Dialog {
    private String appVersion;

    public UpdateDialog(Context context, DialogInterface.OnCancelListener onCancelListener) {
        super(context, R.style.XhDialog);
        this.appVersion = "";
        setOnCancelListener(onCancelListener);
        setCancelable(false);
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        View viewInflate = View.inflate(getContext(), R.layout.update_dialog_layout, null);
        setContentView(viewInflate);
        viewInflate.findViewById(R.id.update_dialog_exit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateDialog.this.cancel();
            }
        });
        ((TextView) viewInflate.findViewById(R.id.update_dialog_app_tv)).setText(String.format("发现新版本:\n%s", this.appVersion));
    }

    public void setAppVersion(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.appVersion = str;
    }
}
