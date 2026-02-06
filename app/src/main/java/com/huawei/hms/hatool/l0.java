package com.huawei.hms.hatool;

import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

public class l0 implements n0 {

    public String f309a;
    public String b;
    public String c;
    public List<q> d;

    public l0(List<q> list, String str, String str2, String str3) {
        this.f309a = str;
        this.b = str2;
        this.c = str3;
        this.d = list;
    }

    public final void a() {
        g0.a(b.i(), "backup_event", u0.a(this.f309a, this.c, this.b));
    }

    @Override
    public void run() {
        List<q> list = this.d;
        if (list == null || list.size() == 0) {
            y.d("hmsSdk", "failed events is empty");
            return;
        }
        if (q0.a(b.i(), "cached_v2_1", b.k() * 1048576)) {
            y.e("hmsSdk", "The cacheFile is full,Can not writing data! reqID:" + this.b);
            return;
        }
        String strA = u0.a(this.f309a, this.c);
        List<q> list2 = w.b(b.i(), "cached_v2_1", strA).get(strA);
        if (list2 != null && list2.size() != 0) {
            this.d.addAll(list2);
        }
        JSONArray jSONArray = new JSONArray();
        Iterator<q> it = this.d.iterator();
        while (it.hasNext()) {
            try {
                jSONArray.put(it.next().d());
            } catch (JSONException unused) {
                y.e("hmsSdk", "event to json error");
            }
        }
        String string = jSONArray.toString();
        if (string.length() > b.h() * 1048576) {
            y.e("hmsSdk", "this failed data is too long,can not writing it");
            this.d = null;
            return;
        }
        y.d("hmsSdk", "data send failed, write to cache file...reqID:" + this.b);
        g0.b(b.i(), "cached_v2_1", strA, string);
        a();
    }
}
