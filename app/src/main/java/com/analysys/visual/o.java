package com.analysys.visual;

import android.text.TextUtils;
import android.view.View;
import com.analysys.utils.ExceptionUtil;
import com.analysys.visual.i;
import com.analysys.visual.q;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class o extends i {
    private View q;

    public o(String str) {
        super(str);
    }

    @Override
    protected View a(i.c cVar) {
        View view = this.q;
        if (view == null) {
            return null;
        }
        for (int i = 0; i < cVar.b.h; i++) {
            Object parent = view.getParent();
            if (!(parent instanceof View)) {
                return null;
            }
            view = (View) parent;
        }
        return view;
    }

    public String a(Object obj, String str) {
        if (!(obj instanceof View) || TextUtils.isEmpty(str)) {
            return null;
        }
        this.q = (View) obj;
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                Map<String, Object> mapB = b(d.a(jSONArray.getJSONObject(i)));
                if (mapB != null) {
                    for (String str2 : mapB.keySet()) {
                        jSONObject.put(str2, mapB.get(str2));
                    }
                }
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
        this.q = null;
        return jSONObject.toString();
    }

    @Override
    protected void a(q.a aVar) {
    }

    public void a(String str, String str2) {
        this.n = new HashMap();
        try {
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject(str);
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    this.n.put(next, jSONObject.get(next));
                }
            }
            if (TextUtils.isEmpty(str2) || !b.a().b()) {
                return;
            }
            JSONObject jSONObject2 = new JSONObject(str2);
            Iterator<String> itKeys2 = jSONObject2.keys();
            while (itKeys2.hasNext()) {
                String next2 = itKeys2.next();
                this.n.put(next2, jSONObject2.get(next2));
            }
        } catch (Throwable th) {
            this.n = null;
            ExceptionUtil.exceptionPrint(th);
        }
    }

    @Override
    public void a(Object... objArr) {
    }

    @Override
    protected void b(q.a aVar) {
    }
}
