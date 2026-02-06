package com.analysys.visual;

import android.view.View;
import android.widget.TextView;

public class y extends u {
    public y(String str, String str2, String str3, ak akVar, Object obj, String str4) {
        super(str, str2, str3, akVar, obj, str4);
    }

    private String a(TextView textView) {
        CharSequence text = textView.getText();
        if (text != null) {
            return text.toString();
        }
        return null;
    }

    @Override
    protected Object a(View view) {
        if (view instanceof TextView) {
            return a((TextView) view);
        }
        return null;
    }
}
