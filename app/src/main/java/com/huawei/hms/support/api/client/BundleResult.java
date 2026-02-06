package com.huawei.hms.support.api.client;

import android.os.Bundle;

public class BundleResult {

    private int f349a;
    private Bundle b;

    public BundleResult(int i, Bundle bundle) {
        this.f349a = i;
        this.b = bundle;
    }

    public int getResultCode() {
        return this.f349a;
    }

    public Bundle getRspBody() {
        return this.b;
    }

    public void setResultCode(int i) {
        this.f349a = i;
    }

    public void setRspBody(Bundle bundle) {
        this.b = bundle;
    }
}
