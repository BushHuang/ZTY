package com.analysys;

import android.content.Context;
import android.util.Log;
import com.analysys.process.AgentProcess;
import java.lang.Thread;
import java.util.HashMap;

public class af implements Thread.UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler f16a;
    private boolean b;
    private a c;

    public interface a {
        void onAppCrash(Throwable th);
    }

    static class b {

        public static af f17a = new af();
    }

    private af() {
        this.b = false;
        this.c = null;
        if (Thread.getDefaultUncaughtExceptionHandler() == this) {
            return;
        }
        this.f16a = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public static af a() {
        return b.f17a;
    }

    private void a(Throwable th) {
        a aVar = this.c;
        if (aVar == null) {
            return;
        }
        if (this.b) {
            aVar.onAppCrash(th);
        } else {
            aVar.onAppCrash(null);
        }
    }

    public af a(a aVar) {
        if (aVar != null) {
            this.c = aVar;
        }
        return b.f17a;
    }

    public af a(boolean z) {
        this.b = z;
        return b.f17a;
    }

    public void a(Context context, Throwable th, int i) {
        if (th != null) {
            HashMap map = new HashMap(2);
            map.put("$crash_data", Log.getStackTraceString(th));
            AgentProcess.getInstance().trackSync("$app_crash", map);
        }
    }

    public boolean b() {
        return this.b;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable th) {
        a(th);
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.f16a;
        if (uncaughtExceptionHandler == null || uncaughtExceptionHandler == Thread.getDefaultUncaughtExceptionHandler()) {
            return;
        }
        this.f16a.uncaughtException(thread, th);
    }
}
