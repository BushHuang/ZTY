package com.analysys.visual;

import com.analysys.utils.ANSLog;
import com.analysys.utils.ExceptionUtil;
import com.analysys.utils.InternalAgent;
import com.analysys.visual.bl;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URI;
import java.nio.ByteBuffer;
import org.json.JSONObject;

public class ah {

    private static final ByteBuffer f95a = ByteBuffer.allocate(0);
    private final a b;
    private final b c;

    public interface a {
        void a();

        void a(JSONObject jSONObject);

        void b();

        void b(JSONObject jSONObject);
    }

    class b extends as {
        public b(URI uri, int i, Socket socket) {
            super(uri, new au(), null, i);
            a(socket);
        }

        @Override
        public void a(int i, String str, boolean z) {
            ah.this.b.b();
        }

        @Override
        public void a(bw bwVar) {
        }

        @Override
        public void a(Exception exc) {
            if (exc == null || exc.getMessage() == null) {
                InternalAgent.e("analysys.visual", "The connection between the server and the client is wrong");
                return;
            }
            InternalAgent.i("analysys.visual", "The connection between the server and the client is wrong，Resource: " + exc.getMessage());
        }

        @Override
        public void a(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String string = jSONObject.getString("type");
                ANSLog.i("analysys.visual", "Received ws command: " + string);
                if ("device_info_request".equals(string)) {
                    ah.this.b.a();
                } else if ("snapshot_request".equals(string)) {
                    ah.this.b.a(jSONObject);
                } else if ("event_binding_request".equals(string)) {
                    ah.this.b.b(jSONObject);
                }
            } catch (Throwable th) {
                ExceptionUtil.exceptionThrow(th);
            }
        }
    }

    public class c extends IOException {
        public c(Throwable th) {
            super(th.getMessage());
        }
    }

    class d extends OutputStream {
        private d() {
        }

        @Override
        public void close() {
            try {
                ah.this.c.a(bl.a.TEXT, ah.f95a, true);
            } catch (Exception unused) {
            }
        }

        @Override
        public void write(int i) {
            try {
                write(new byte[]{(byte) i}, 0, 1);
            } catch (Exception unused) {
            }
        }

        @Override
        public void write(byte[] bArr) {
            try {
                write(bArr, 0, bArr.length);
            } catch (Exception unused) {
            }
        }

        @Override
        public void write(byte[] bArr, int i, int i2) throws c {
            try {
                ah.this.c.a(bl.a.TEXT, ByteBuffer.wrap(bArr, i, i2), false);
            } catch (bb e) {
                throw ah.this.new c(e);
            } catch (bc e2) {
                InternalAgent.e(e2);
                throw ah.this.new c(e2);
            }
        }
    }

    public ah(URI uri, a aVar, Socket socket) throws c {
        this.b = aVar;
        try {
            b bVar = new b(uri, 10000, socket);
            this.c = bVar;
            bVar.g();
        } catch (InterruptedException e) {
            throw new c(e);
        }
    }

    public boolean a() {
        return (this.c.k() || this.c.l() || this.c.j()) ? false : true;
    }

    public boolean b() {
        return this.c.i();
    }

    public void c() {
        this.c.h();
    }

    public BufferedOutputStream d() {
        return new BufferedOutputStream(new d());
    }
}
