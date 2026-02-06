package com.samsung.android.knox.browser;

import android.app.enterprise.MiscPolicy;
import android.graphics.Bitmap;
import android.net.Uri;

public class BrowserPolicy {
    private android.app.enterprise.BrowserPolicy mBroserPolicy;
    private MiscPolicy mMiscPolicy;

    public BrowserPolicy(android.app.enterprise.BrowserPolicy browserPolicy, MiscPolicy miscPolicy) {
        this.mBroserPolicy = browserPolicy;
        this.mMiscPolicy = miscPolicy;
    }

    public boolean addWebBookmarkBitmap(Uri uri, String str, Bitmap bitmap) {
        return this.mMiscPolicy.addWebBookmarkBitmap(uri, str, bitmap);
    }

    public boolean addWebBookmarkByteBuffer(Uri uri, String str, byte[] bArr) {
        return this.mMiscPolicy.addWebBookmarkByteBuffer(uri, str, bArr);
    }

    public boolean clearHttpProxy() {
        return this.mBroserPolicy.clearHttpProxy();
    }

    public boolean deleteWebBookmark(Uri uri, String str) {
        return this.mMiscPolicy.deleteWebBookmark(uri, str);
    }

    public boolean getAutoFillSetting() {
        return this.mBroserPolicy.getAutoFillSetting();
    }

    public boolean getCookiesSetting() {
        return this.mBroserPolicy.getCookiesSetting();
    }

    public boolean getForceFraudWarningSetting() {
        return this.mBroserPolicy.getForceFraudWarningSetting();
    }

    public String getHttpProxy() {
        return this.mBroserPolicy.getHttpProxy();
    }

    public boolean getJavaScriptSetting() {
        return this.mBroserPolicy.getJavaScriptSetting();
    }

    public boolean getPopupsSetting() {
        return this.mBroserPolicy.getPopupsSetting();
    }

    public boolean setAutoFillSetting(boolean z) {
        return this.mBroserPolicy.setAutoFillSetting(z);
    }

    public boolean setCookiesSetting(boolean z) {
        return this.mBroserPolicy.setCookiesSetting(z);
    }

    public boolean setForceFraudWarningSetting(boolean z) {
        return this.mBroserPolicy.setForceFraudWarningSetting(z);
    }

    public boolean setHttpProxy(String str) {
        return this.mBroserPolicy.setHttpProxy(str);
    }

    public boolean setJavaScriptSetting(boolean z) {
        return this.mBroserPolicy.setJavaScriptSetting(z);
    }

    public boolean setPopupsSetting(boolean z) {
        return this.mBroserPolicy.setPopupsSetting(z);
    }
}
