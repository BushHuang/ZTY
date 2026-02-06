package com.analysys.visual;

import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;
import com.analysys.utils.ANSLog;
import com.analysys.utils.AnalysysUtil;
import com.analysys.utils.ExceptionUtil;
import com.analysys.utils.InternalAgent;
import com.analysys.visual.ah;
import com.analysys.visual.aj;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URI;
import java.security.GeneralSecurityException;
import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import org.json.JSONObject;

public class ai {

    private Handler f99a;
    private aj.a b = new aj.a() {
        @Override
        public void a() {
            ANSLog.i("analysys.visual", "Gesture detected");
            ai.this.e();
            ai.this.a(1, null);
        }
    };
    private aj c = new aj(this.b);
    private boolean d;
    private ah e;

    class a implements ah.a {
        a() {
        }

        @Override
        public void a() {
            ai.this.a(3, null);
        }

        @Override
        public void a(JSONObject jSONObject) {
            ai.this.a(2, jSONObject);
        }

        @Override
        public void b() {
            ai.this.a(6, null);
        }

        @Override
        public void b(JSONObject jSONObject) {
            ai.this.a(5, jSONObject);
        }
    }

    public ai(Handler handler) {
        this.f99a = handler;
    }

    private void a(int i, Object obj) {
        this.f99a.removeMessages(i);
        Message messageObtainMessage = this.f99a.obtainMessage(i);
        if (obj != null) {
            messageObtainMessage.obj = obj;
        }
        this.f99a.sendMessage(messageObtainMessage);
    }

    public void a() {
        SSLSocketFactory socketFactory;
        ah ahVar = this.e;
        if (ahVar != null && ahVar.a()) {
            InternalAgent.v("analysys.visual", "already connected");
            return;
        }
        String strB = com.analysys.visual.a.a().b();
        Socket socketCreateSocket = null;
        if (strB.startsWith("wss")) {
            try {
                SSLContext sSLContext = SSLContext.getInstance("TLS");
                sSLContext.init(null, null, null);
                socketFactory = sSLContext.getSocketFactory();
            } catch (GeneralSecurityException e) {
                ANSLog.i("analysys.visual", "ssl support fail", e);
                return;
            } catch (Throwable th) {
                ExceptionUtil.exceptionThrow(th);
                socketFactory = null;
            }
            try {
                socketCreateSocket = socketFactory.createSocket();
            } catch (Throwable th2) {
                InternalAgent.e(th2);
                ExceptionUtil.exceptionThrow(th2);
            }
        } else {
            try {
                socketCreateSocket = SocketFactory.getDefault().createSocket();
            } catch (Throwable th3) {
                InternalAgent.e(th3);
                ExceptionUtil.exceptionThrow(th3);
            }
        }
        try {
            this.e = new ah(new URI(strB), new a(), socketCreateSocket);
        } catch (Throwable th4) {
            InternalAgent.e(th4);
            ExceptionUtil.exceptionThrow(th4);
        }
    }

    public void b() {
        ah ahVar = this.e;
        if (ahVar != null) {
            ahVar.c();
        }
    }

    public boolean c() {
        ah ahVar = this.e;
        return ahVar != null && ahVar.b();
    }

    public synchronized void d() {
        if (!this.d) {
            ANSLog.i("analysys.visual", "register sensor");
            SensorManager sensorManager = (SensorManager) AnalysysUtil.getContext().getSystemService("sensor");
            sensorManager.registerListener(this.c, sensorManager.getDefaultSensor(1), 3);
            this.d = true;
        }
    }

    public synchronized void e() {
        if (this.d) {
            ANSLog.i("analysys.visual", "unregister sensor");
            ((SensorManager) AnalysysUtil.getContext().getSystemService("sensor")).unregisterListener(this.c);
            this.c.a();
            this.d = false;
        }
    }

    public OutputStream f() {
        ah ahVar = this.e;
        if (ahVar != null && ahVar.a() && this.e.b()) {
            return this.e.d();
        }
        return null;
    }
}
