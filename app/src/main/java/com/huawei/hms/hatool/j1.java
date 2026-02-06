package com.huawei.hms.hatool;

import android.content.Context;
import android.text.TextUtils;
import java.util.LinkedHashMap;
import org.json.JSONException;

public class j1 {

    public String f304a;
    public m b;

    public j1(String str) {
        this.f304a = str;
        this.b = new m(str);
        i.c().a(this.f304a, this.b);
    }

    public void a(int i) {
        y.d("hmsSdk", "onReport. TAG: " + this.f304a + ", TYPE: " + i);
        i1.a().a(this.f304a, i);
    }

    public void a(int i, String str, LinkedHashMap<String, String> linkedHashMap) throws JSONException {
        y.d("hmsSdk", "onEvent. TAG: " + this.f304a + ", TYPE: " + i + ", eventId : " + str);
        if (s0.a(str) || !c(i)) {
            y.e("hmsSdk", "onEvent() parameters check fail. Nothing will be recorded.TAG: " + this.f304a + ", TYPE: " + i);
            return;
        }
        if (!s0.a(linkedHashMap)) {
            y.e("hmsSdk", "onEvent() parameter mapValue will be cleared.TAG: " + this.f304a + ", TYPE: " + i);
            linkedHashMap = null;
        }
        i1.a().a(this.f304a, i, str, linkedHashMap);
    }

    public void a(Context context, String str, String str2) throws JSONException {
        y.d("hmsSdk", "onEvent(context). TAG: " + this.f304a + ", eventId : " + str);
        if (context == null) {
            y.e("hmsSdk", "context is null in onevent ");
            return;
        }
        if (s0.a(str) || !c(0)) {
            y.e("hmsSdk", "onEvent() parameters check fail. Nothing will be recorded.TAG: " + this.f304a);
            return;
        }
        if (!s0.a("value", str2, 65536)) {
            y.e("hmsSdk", "onEvent() parameter VALUE is overlong, content will be cleared.TAG: " + this.f304a);
            str2 = "";
        }
        i1.a().a(this.f304a, context, str, str2);
    }

    public void a(k kVar) {
        y.c("hmsSdk", "HiAnalyticsInstanceImpl.setMaintConf() is executed.TAG : " + this.f304a);
        if (kVar != null) {
            this.b.a(kVar);
        } else {
            y.e("hmsSdk", "HiAnalyticsInstanceImpl.setMaintConf(): config for maint is null!");
            this.b.a((k) null);
        }
    }

    public final k b(int i) {
        if (i == 0) {
            return this.b.c();
        }
        if (i == 1) {
            return this.b.b();
        }
        if (i == 2) {
            return this.b.d();
        }
        if (i != 3) {
            return null;
        }
        return this.b.a();
    }

    public void b(k kVar) {
        y.c("hmsSdk", "HiAnalyticsInstanceImpl.setOperConf() is executed.TAG: " + this.f304a);
        if (kVar != null) {
            this.b.b(kVar);
        } else {
            this.b.b(null);
            y.e("hmsSdk", "HiAnalyticsInstanceImpl.setOperConf(): config for oper is null!");
        }
    }

    public final boolean c(int i) {
        String str;
        if (i != 2) {
            k kVarB = b(i);
            if (kVarB != null && !TextUtils.isEmpty(kVarB.h())) {
                return true;
            }
            str = "verifyURL(): URL check failed. type: " + i;
        } else {
            if ("_default_config_tag".equals(this.f304a)) {
                return true;
            }
            str = "verifyURL(): type: preins. Only default config can report Pre-install data.";
        }
        y.e("hmsSdk", str);
        return false;
    }
}
