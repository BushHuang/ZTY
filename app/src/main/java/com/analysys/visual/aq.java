package com.analysys.visual;

import com.analysys.visual.ap;
import com.analysys.visual.at;
import com.analysys.visual.bl;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class aq implements ap {
    public final BlockingQueue<ByteBuffer> c;
    public final BlockingQueue<ByteBuffer> d;
    public SelectionKey e;
    public ByteChannel f;
    private final ar i;
    private List<at> l;
    private at m;
    private ap.b n;
    private bm v;
    static final boolean g = !aq.class.desiredAssertionStatus();
    private static final Object h = new Object();

    public static int f111a = 16384;
    public static boolean b = false;
    private volatile boolean j = false;
    private ap.a k = ap.a.NOT_YET_CONNECTED;
    private ByteBuffer o = ByteBuffer.allocate(0);
    private bp p = null;
    private String q = null;
    private Integer r = null;
    private Boolean s = null;
    private String t = null;
    private long u = System.currentTimeMillis();

    public aq(ar arVar, at atVar) {
        this.m = null;
        if (arVar == null || (atVar == null && this.n == ap.b.SERVER)) {
            throw new IllegalArgumentException("parameters must not be null");
        }
        this.c = new LinkedBlockingQueue();
        this.d = new LinkedBlockingQueue();
        this.i = arVar;
        this.n = ap.b.CLIENT;
        if (atVar != null) {
            this.m = atVar.c();
        }
    }

    private void a(ap.a aVar) {
        this.k = aVar;
    }

    private void a(bu buVar) {
        if (b) {
            System.out.println("open using draft: " + this.m);
        }
        a(ap.a.OPEN);
        try {
            this.i.a(this, buVar);
        } catch (RuntimeException e) {
            this.i.a(this, e);
        }
    }

    private void a(RuntimeException runtimeException) {
        d(b(500));
        c(-1, runtimeException.getMessage(), false);
    }

    private void a(Collection<bl> collection) {
        if (!c()) {
            throw new bc();
        }
        if (collection == null) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList();
        for (bl blVar : collection) {
            if (b) {
                System.out.println("send frame: " + blVar);
            }
            arrayList.add(this.m.a(blVar));
        }
        a((List<ByteBuffer>) arrayList);
    }

    private void a(List<ByteBuffer> list) {
        synchronized (h) {
            Iterator<ByteBuffer> it = list.iterator();
            while (it.hasNext()) {
                d(it.next());
            }
        }
    }

    private ByteBuffer b(int i) {
        String str = i != 404 ? "500 Internal Server Error" : "404 WebSocket Upgrade Failure";
        return ByteBuffer.wrap(cc.b("HTTP/1.1 " + str + "\r\nContent-Type: text/html\nServer: TooTallNate Java-WebSocket\r\nContent-Length: " + (str.length() + 48) + "\r\n\r\n<html><head></head><body><h1>" + str + "</h1></body></html>"));
    }

    private void b(ax axVar) {
        d(b(404));
        c(axVar.a(), axVar.getMessage(), false);
    }

    private boolean b(ByteBuffer byteBuffer) {
        ByteBuffer byteBuffer2;
        bu buVarD;
        if (this.o.capacity() == 0) {
            byteBuffer2 = byteBuffer;
        } else {
            if (this.o.remaining() < byteBuffer.remaining()) {
                ByteBuffer byteBufferAllocate = ByteBuffer.allocate(this.o.capacity() + byteBuffer.remaining());
                this.o.flip();
                byteBufferAllocate.put(this.o);
                this.o = byteBufferAllocate;
            }
            this.o.put(byteBuffer);
            this.o.flip();
            byteBuffer2 = this.o;
        }
        byteBuffer2.mark();
        try {
            try {
            } catch (az e) {
                a(e);
            }
        } catch (aw e2) {
            if (this.o.capacity() == 0) {
                byteBuffer2.reset();
                int iA = e2.a();
                if (iA == 0) {
                    iA = byteBuffer2.capacity() + 16;
                } else if (!g && e2.a() < byteBuffer2.remaining()) {
                    throw new AssertionError();
                }
                ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(iA);
                this.o = byteBufferAllocate2;
                byteBufferAllocate2.put(byteBuffer);
            } else {
                ByteBuffer byteBuffer3 = this.o;
                byteBuffer3.position(byteBuffer3.limit());
                ByteBuffer byteBuffer4 = this.o;
                byteBuffer4.limit(byteBuffer4.capacity());
            }
        }
        if (this.n != ap.b.SERVER) {
            if (this.n == ap.b.CLIENT) {
                this.m.a(this.n);
                bu buVarD2 = this.m.d(byteBuffer2);
                if (!(buVarD2 instanceof bw)) {
                    c(1002, "wrong http function", false);
                    return false;
                }
                bw bwVar = (bw) buVarD2;
                if (this.m.a(this.p, bwVar) == at.b.MATCHED) {
                    try {
                        this.i.a(this, this.p, bwVar);
                        a(bwVar);
                        return true;
                    } catch (ax e3) {
                        c(e3.a(), e3.getMessage(), false);
                        return false;
                    } catch (RuntimeException e4) {
                        this.i.a(this, e4);
                        c(-1, e4.getMessage(), false);
                        return false;
                    }
                }
                a(1002, "draft " + this.m + " refuses handshake");
            }
            return false;
        }
        if (this.m != null) {
            bu buVarD3 = this.m.d(byteBuffer2);
            if (!(buVarD3 instanceof bp)) {
                c(1002, "wrong http function", false);
                return false;
            }
            bp bpVar = (bp) buVarD3;
            if (this.m.a(bpVar) == at.b.MATCHED) {
                a(bpVar);
                return true;
            }
            a(1002, "the handshake did finaly not match");
            return false;
        }
        Iterator<at> it = this.l.iterator();
        while (it.hasNext()) {
            at atVarC = it.next().c();
            try {
                atVarC.a(this.n);
                byteBuffer2.reset();
                buVarD = atVarC.d(byteBuffer2);
            } catch (az unused) {
            }
            if (!(buVarD instanceof bp)) {
                b(new ax(1002, "wrong http function"));
                return false;
            }
            bp bpVar2 = (bp) buVarD;
            if (atVarC.a(bpVar2) == at.b.MATCHED) {
                this.t = bpVar2.a();
                try {
                    a(atVarC.a(atVarC.a(bpVar2, this.i.a(this, atVarC, bpVar2)), this.n));
                    this.m = atVarC;
                    a(bpVar2);
                    return true;
                } catch (ax e5) {
                    b(e5);
                    return false;
                } catch (RuntimeException e6) {
                    this.i.a(this, e6);
                    a(e6);
                    return false;
                }
            }
        }
        if (this.m == null) {
            b(new ax(1002, "no draft matches"));
        }
        return false;
    }

    private void c(ByteBuffer byteBuffer) {
        try {
            for (bl blVar : this.m.c(byteBuffer)) {
                if (b) {
                    System.out.println("matched frame: " + blVar);
                }
                this.m.a(this, blVar);
            }
        } catch (ax e) {
            this.i.a(this, e);
            a(e);
        }
    }

    private void d(ByteBuffer byteBuffer) {
        if (b) {
            PrintStream printStream = System.out;
            StringBuilder sb = new StringBuilder();
            sb.append("write(");
            sb.append(byteBuffer.remaining());
            sb.append("): {");
            sb.append(byteBuffer.remaining() > 1000 ? "too big to display" : new String(byteBuffer.array()));
            sb.append('}');
            printStream.println(sb.toString());
        }
        this.c.add(byteBuffer);
        this.i.a(this);
    }

    public void a() {
        if (g() == ap.a.NOT_YET_CONNECTED) {
            a(-1, true);
            return;
        }
        if (this.j) {
            b(this.r.intValue(), this.q, this.s.booleanValue());
            return;
        }
        if (this.m.b() == at.a.NONE) {
            a(1000, true);
            return;
        }
        if (this.m.b() != at.a.ONEWAY) {
            a(1006, true);
        } else if (this.n == ap.b.SERVER) {
            a(1006, true);
        } else {
            a(1000, true);
        }
    }

    public void a(int i) {
        a(i, "", false);
    }

    public void a(int i, String str) {
        a(i, str, false);
    }

    public synchronized void a(int i, String str, boolean z) {
        if (g() == ap.a.CLOSING || this.k == ap.a.CLOSED) {
            return;
        }
        if (g() == ap.a.OPEN) {
            if (i == 1006) {
                if (!g && z) {
                    throw new AssertionError();
                }
                a(ap.a.CLOSING);
                c(i, str, false);
                return;
            }
            if (this.m.b() != at.a.NONE) {
                if (!z) {
                    try {
                        try {
                            this.i.a(this, i, str);
                        } catch (ax e) {
                            this.i.a(this, e);
                            c(1006, "generated frame is invalid", false);
                        }
                    } catch (RuntimeException e2) {
                        this.i.a(this, e2);
                    }
                    if (c()) {
                        bj bjVar = new bj();
                        bjVar.a(str);
                        bjVar.a(i);
                        bjVar.a();
                        a(bjVar);
                    }
                } else if (c()) {
                }
            }
            c(i, str, z);
        } else if (i == -3) {
            if (!g && !z) {
                throw new AssertionError();
            }
            c(-3, str, true);
        } else if (i == 1002) {
            c(i, str, z);
        } else {
            c(-1, str, false);
        }
        a(ap.a.CLOSING);
        this.o = null;
    }

    protected void a(int i, boolean z) {
        b(i, "", z);
    }

    public void a(ax axVar) {
        a(axVar.a(), axVar.getMessage(), false);
    }

    public void a(bl.a aVar, ByteBuffer byteBuffer, boolean z) {
        a(this.m.a(aVar, byteBuffer, z));
    }

    @Override
    public void a(bl blVar) {
        a((Collection<bl>) Collections.singletonList(blVar));
    }

    public void a(bq bqVar) throws az {
        this.p = this.m.a(bqVar);
        String strA = bqVar.a();
        this.t = strA;
        if (!g && strA == null) {
            throw new AssertionError();
        }
        try {
            this.i.a((ap) this, this.p);
            a(this.m.a(this.p, this.n));
        } catch (ax unused) {
            throw new az("Handshake data rejected by client.");
        } catch (RuntimeException e) {
            this.i.a(this, e);
            throw new az("rejected because of" + e);
        }
    }

    public void a(ByteBuffer byteBuffer) {
        if (!g && !byteBuffer.hasRemaining()) {
            throw new AssertionError();
        }
        if (b) {
            PrintStream printStream = System.out;
            StringBuilder sb = new StringBuilder();
            sb.append("process(");
            sb.append(byteBuffer.remaining());
            sb.append("): {");
            sb.append(byteBuffer.remaining() > 1000 ? "too big to display" : new String(byteBuffer.array(), byteBuffer.position(), byteBuffer.remaining()));
            sb.append('}');
            printStream.println(sb.toString());
        }
        if (g() != ap.a.NOT_YET_CONNECTED) {
            if (g() == ap.a.OPEN) {
                c(byteBuffer);
            }
        } else if (b(byteBuffer) && !d() && !f()) {
            if (!g && this.o.hasRemaining() == byteBuffer.hasRemaining() && byteBuffer.hasRemaining()) {
                throw new AssertionError();
            }
            if (byteBuffer.hasRemaining()) {
                c(byteBuffer);
            } else if (this.o.hasRemaining()) {
                c(this.o);
            }
        }
        if (!g && !d() && !e() && byteBuffer.hasRemaining()) {
            throw new AssertionError();
        }
    }

    public void b() {
        if (this.v == null) {
            this.v = new bm();
        }
        a(this.v);
    }

    public void b(int i, String str) {
        b(i, str, false);
    }

    public synchronized void b(int i, String str, boolean z) {
        if (g() == ap.a.CLOSED) {
            return;
        }
        if (g() == ap.a.OPEN && i == 1006) {
            a(ap.a.CLOSING);
        }
        if (this.e != null) {
            this.e.cancel();
        }
        if (this.f == null) {
            this.i.a(this, i, str, z);
            if (this.m != null) {
            }
            this.p = null;
            a(ap.a.CLOSED);
            return;
        }
        try {
            this.f.close();
        } catch (IOException e) {
            if (!"Broken pipe".equals(e.getMessage())) {
                this.i.a(this, e);
            } else if (b) {
                System.out.println("Caught IOException: Broken pipe during closeConnection()");
            }
        }
        try {
            this.i.a(this, i, str, z);
        } catch (RuntimeException e2) {
            this.i.a(this, e2);
        }
        if (this.m != null) {
            this.m.a();
        }
        this.p = null;
        a(ap.a.CLOSED);
        return;
    }

    public synchronized void c(int i, String str, boolean z) {
        if (this.j) {
            return;
        }
        this.r = Integer.valueOf(i);
        this.q = str;
        this.s = Boolean.valueOf(z);
        this.j = true;
        this.i.a(this);
        try {
            this.i.b(this, i, str, z);
        } catch (RuntimeException e) {
            this.i.a(this, e);
        }
        if (this.m != null) {
            this.m.a();
        }
        this.p = null;
    }

    public boolean c() {
        return g() == ap.a.OPEN;
    }

    public boolean d() {
        return g() == ap.a.CLOSING;
    }

    public boolean e() {
        return this.j;
    }

    public boolean f() {
        return g() == ap.a.CLOSED;
    }

    public ap.a g() {
        return this.k;
    }

    long h() {
        return this.u;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public void i() {
        this.u = System.currentTimeMillis();
    }

    public ar j() {
        return this.i;
    }

    public String toString() {
        return super.toString();
    }
}
