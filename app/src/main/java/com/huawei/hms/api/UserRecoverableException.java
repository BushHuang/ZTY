package com.huawei.hms.api;

import android.content.Intent;

public class UserRecoverableException extends Exception {

    private final Intent f238a;

    public UserRecoverableException(String str, Intent intent) {
        super(str);
        this.f238a = intent;
    }

    public Intent getIntent() {
        return new Intent(this.f238a);
    }
}
