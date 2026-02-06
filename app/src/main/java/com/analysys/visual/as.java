package com.analysys.visual;

import com.analysys.visual.bl;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.URI;
import java.nio.ByteBuffer;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;

public abstract class as extends an implements ap, Runnable {

    private aq f112a;
    protected URI b;
    private OutputStream d;
    private Thread f;
    private at g;
    private Map<String, String> h;
    private int k;
    private Socket c = null;
    private Proxy e = Proxy.NO_PROXY;
    private CountDownLatch i = new CountDownLatch(1);
    private CountDownLatch j = new CountDownLatch(1);

    class a implements Runnable {
        private a() {
        }

        @Override
        public void run() throws IOException {
            Thread.currentThread().setName("WebSocketWriteThread-" + Thread.currentThread().getId());
            while (true) {
                try {
                    try {
                        try {
                            if (Thread.interrupted()) {
                                break;
                            }
                            ByteBuffer byteBufferTake = as.this.f112a.c.take();
                            as.this.d.write(byteBufferTake.array(), 0, byteBufferTake.limit());
                            as.this.d.flush();
                        } catch (IOException e) {
                            as.this.a(e);
                        }
                    } finally {
                        as.this.o();
                        as.this.f = null;
                    }
                } catch (InterruptedException unused) {
                    for (ByteBuffer byteBuffer : as.this.f112a.c) {
                        as.this.d.write(byteBuffer.array(), 0, byteBuffer.limit());
                        as.this.d.flush();
                    }
                }
            }
        }
    }

    public as(URI uri, at atVar, Map<String, String> map, int i) {
        this.b = null;
        this.f112a = null;
        this.k = 0;
        if (uri == null) {
            throw new IllegalArgumentException();
        }
        if (atVar == null) {
            throw new IllegalArgumentException("null as draft is permitted for `BaseWebSocketServer` only!");
        }
        this.b = uri;
        this.g = atVar;
        this.h = map;
        this.k = i;
        a(false);
        b(false);
        this.f112a = new aq(this, atVar);
    }

    private void a(IOException iOException) {
        if (iOException instanceof SSLException) {
            a((Exception) iOException);
        }
        this.f112a.a();
    }

    private int m() {
        int port = this.b.getPort();
        if (port != -1) {
            return port;
        }
        String scheme = this.b.getScheme();
        if ("wss".equals(scheme)) {
            return 443;
        }
        if ("ws".equals(scheme)) {
            return 80;
        }
        throw new IllegalArgumentException("unknown scheme: " + scheme);
    }

    private void n() throws az {
        String rawPath = this.b.getRawPath();
        String rawQuery = this.b.getRawQuery();
        if (rawPath == null || rawPath.length() == 0) {
            rawPath = "/";
        }
        if (rawQuery != null) {
            rawPath = rawPath + '?' + rawQuery;
        }
        int iM = m();
        StringBuilder sb = new StringBuilder();
        sb.append(this.b.getHost());
        sb.append((iM == 80 || iM == 443) ? "" : ":" + iM);
        String string = sb.toString();
        bs bsVar = new bs();
        bsVar.a(rawPath);
        bsVar.a("Host", string);
        Map<String, String> map = this.h;
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                bsVar.a(entry.getKey(), entry.getValue());
            }
        }
        this.f112a.a((bq) bsVar);
    }

    private void o() throws IOException {
        try {
            if (this.c != null) {
                this.c.close();
            }
        } catch (IOException e) {
            a((ap) this, (Exception) e);
        }
    }

    public void a(int i, String str) {
    }

    public abstract void a(int i, String str, boolean z);

    @Override
    public final void a(ap apVar) {
    }

    @Override
    public void a(ap apVar, int i, String str) {
        a(i, str);
    }

    @Override
    public final void a(ap apVar, int i, String str, boolean z) {
        a();
        Thread thread = this.f;
        if (thread != null) {
            thread.interrupt();
        }
        a(i, str, z);
        this.i.countDown();
        this.j.countDown();
    }

    @Override
    public final void a(ap apVar, bu buVar) {
        b();
        a((bw) buVar);
        this.i.countDown();
    }

    @Override
    public final void a(ap apVar, Exception exc) {
        a(exc);
    }

    @Override
    public final void a(ap apVar, String str) {
        a(str);
    }

    @Override
    public final void a(ap apVar, ByteBuffer byteBuffer) {
        a(byteBuffer);
    }

    public void a(bl.a aVar, ByteBuffer byteBuffer, boolean z) {
        this.f112a.a(aVar, byteBuffer, z);
    }

    @Override
    public void a(bl blVar) {
        this.f112a.a(blVar);
    }

    public abstract void a(bw bwVar);

    public abstract void a(Exception exc);

    public abstract void a(String str);

    public void a(Socket socket) {
        if (this.c != null) {
            throw new IllegalStateException("socket has already been set");
        }
        this.c = socket;
    }

    public void a(ByteBuffer byteBuffer) {
    }

    public void b(int i, String str, boolean z) {
    }

    @Override
    public void b(ap apVar, int i, String str, boolean z) {
        b(i, str, z);
    }

    @Override
    protected Collection<ap> c() {
        return Collections.singletonList(this.f112a);
    }

    public void f() {
        if (this.f != null) {
            throw new IllegalStateException("BaseWebSocketClient objects are not reuseable");
        }
        Thread thread = new Thread(this);
        this.f = thread;
        thread.setName("WebSocketConnectReadThread-" + this.f.getId());
        this.f.start();
    }

    public boolean g() throws InterruptedException {
        f();
        this.i.await();
        return this.f112a.c();
    }

    public void h() {
        if (this.f != null) {
            this.f112a.a(1000);
        }
    }

    public boolean i() {
        return this.f112a.c();
    }

    public boolean j() {
        return this.f112a.e();
    }

    public boolean k() {
        return this.f112a.f();
    }

    public boolean l() {
        return this.f112a.d();
    }

    @Override
    public void run() throws NoSuchAlgorithmException, IOException, KeyManagementException {
        boolean z;
        int i;
        try {
            if (this.c == null) {
                this.c = new Socket(this.e);
                z = true;
            } else {
                if (this.c.isClosed()) {
                    throw new IOException();
                }
                z = false;
            }
            this.c.setTcpNoDelay(d());
            this.c.setReuseAddress(e());
            if (!this.c.isBound()) {
                this.c.connect(new InetSocketAddress(this.b.getHost(), m()), this.k);
            }
            if (z && "wss".equals(this.b.getScheme())) {
                SSLContext sSLContext = SSLContext.getInstance("TLS");
                sSLContext.init(null, null, null);
                this.c = sSLContext.getSocketFactory().createSocket(this.c, this.b.getHost(), m(), true);
            }
            InputStream inputStream = this.c.getInputStream();
            this.d = this.c.getOutputStream();
            n();
            Thread thread = new Thread(new a());
            this.f = thread;
            thread.start();
            byte[] bArr = new byte[aq.f111a];
            while (!l() && !k() && (i = inputStream.read(bArr)) != -1) {
                try {
                    this.f112a.a(ByteBuffer.wrap(bArr, 0, i));
                } catch (IOException e) {
                    a(e);
                    return;
                } catch (RuntimeException e2) {
                    a(e2);
                    this.f112a.b(1006, e2.getMessage());
                    return;
                }
            }
            this.f112a.a();
        } catch (Exception e3) {
            a(this.f112a, e3);
            this.f112a.b(-1, e3.getMessage());
        }
    }
}
