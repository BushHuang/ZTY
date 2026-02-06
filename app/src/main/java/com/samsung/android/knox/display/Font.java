package com.samsung.android.knox.display;

import android.app.enterprise.MiscPolicy;

public class Font {
    private MiscPolicy mMiscPolicy;

    public Font(MiscPolicy miscPolicy) {
        this.mMiscPolicy = miscPolicy;
    }

    public String getSystemActiveFont() {
        return this.mMiscPolicy.getSystemActiveFont();
    }

    public float getSystemActiveFontSize() {
        return this.mMiscPolicy.getSystemActiveFontSize();
    }

    public float[] getSystemFontSizes() {
        return this.mMiscPolicy.getSystemFontSizes();
    }

    public String[] getSystemFonts() {
        return this.mMiscPolicy.getSystemFonts();
    }

    public boolean setSystemActiveFont(String str, String str2) {
        return this.mMiscPolicy.setSystemActiveFont(str, str2);
    }

    public boolean setSystemActiveFontSize(float f) {
        return this.mMiscPolicy.setSystemActiveFontSize(f);
    }
}
