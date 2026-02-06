package com.analysys.visual;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import android.util.SparseArray;
import com.analysys.utils.ANSLog;
import com.analysys.utils.ExceptionUtil;
import com.analysys.visual.utils.VisualIpc;
import java.util.HashMap;
import java.util.Map;

public class a {

    private static a f85a;
    private String b;
    private ai c;
    private Handler d;
    private SparseArray<ad> e;

    private a() {
    }

    public static a a() {
        if (f85a == null) {
            synchronized (a.class) {
                if (f85a == null) {
                    f85a = new a();
                }
            }
        }
        return f85a;
    }

    private void c() {
        this.c.d();
        this.c.b();
        ac acVar = (ac) this.e.get(2);
        if (acVar != null) {
            acVar.a();
        }
        VisualIpc.getInstance().setVisualEditing(false);
    }

    public void a(String str) {
        this.b = str;
        SparseArray<ad> sparseArray = new SparseArray<>();
        this.e = sparseArray;
        sparseArray.put(3, new z());
        this.e.put(2, new ac());
        this.e.put(5, new aa());
        this.e.put(7, new ab());
        HandlerThread handlerThread = new HandlerThread(a.class.getCanonicalName());
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message message) {
                try {
                    int i = message.what;
                    if (i == 1) {
                        a.this.c.a();
                        if (a.this.c.c()) {
                            ANSLog.i("analysys.visual", "WS connect success. url:" + a.this.b);
                        } else {
                            ANSLog.i("analysys.visual", "WS connect failed. url:" + a.this.b);
                            a.this.c.d();
                        }
                    } else if (i != 6) {
                        ad adVar = (ad) a.this.e.get(message.what);
                        if (adVar != null) {
                            adVar.a(message.obj, a.this.c.f());
                        }
                    } else {
                        ANSLog.i("analysys.visual", "WS closed");
                        a.this.c();
                    }
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        };
        this.d = handler;
        ai aiVar = new ai(handler);
        this.c = aiVar;
        aiVar.d();
    }

    public void a(String str, String str2, Map<String, Object> map) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ai aiVar = this.c;
        if (aiVar == null || aiVar.c()) {
            Message messageObtainMessage = this.d.obtainMessage(7);
            HashMap map2 = new HashMap();
            map2.put("event_id", str);
            map2.put("event_page_name", str2);
            map2.put("event_properties", map);
            messageObtainMessage.obj = map2;
            this.d.sendMessage(messageObtainMessage);
        }
    }

    public String b() {
        return this.b;
    }
}
