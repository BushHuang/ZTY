package com.huawei.hms.base.log;

import android.content.Context;
import android.util.Log;

public class e implements b {

    public b f247a;

    @Override
    public void a(Context context, String str) {
        b bVar = this.f247a;
        if (bVar != null) {
            bVar.a(context, str);
        }
    }

    @Override
    public void a(b bVar) {
        this.f247a = bVar;
    }

    @Override
    public void a(String str, int i, String str2, String str3) {
        Log.println(i, "HMSSDK_" + str2, str3);
        b bVar = this.f247a;
        if (bVar != null) {
            bVar.a(str, i, str2, str3);
        }
    }
}
