package com.analysys.visual;

import com.analysys.visual.ap;
import com.analysys.visual.bl;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public abstract class at {

    public static int f114a = 1000;
    public static int b = 64;
    protected ap.b c = null;
    protected bl.a d = null;

    public enum a {
        NONE,
        ONEWAY,
        TWOWAY
    }

    public enum b {
        MATCHED,
        NOT_MATCHED
    }

    public static br a(ByteBuffer byteBuffer, ap.b bVar) throws az {
        br brVar;
        String strB = b(byteBuffer);
        if (strB == null) {
            throw new aw(byteBuffer.capacity() + 128);
        }
        String[] strArrSplit = strB.split(" ", 3);
        if (strArrSplit.length != 3) {
            throw new az();
        }
        if (bVar == ap.b.CLIENT) {
            if (!"101".equals(strArrSplit[1])) {
                throw new az("Invalid status code received: " + strArrSplit[1] + " Status line: " + strB);
            }
            if (!"HTTP/1.1".equalsIgnoreCase(strArrSplit[0])) {
                throw new az("Invalid status line received: " + strArrSplit[0] + " Status line: " + strB);
            }
            br btVar = new bt();
            bx bxVar = (bx) btVar;
            bxVar.a(Short.parseShort(strArrSplit[1]));
            bxVar.a(strArrSplit[2]);
            brVar = btVar;
        } else {
            if (!"GET".equalsIgnoreCase(strArrSplit[0])) {
                throw new az("Invalid request method received: " + strArrSplit[0] + " Status line: " + strB);
            }
            if (!"HTTP/1.1".equalsIgnoreCase(strArrSplit[2])) {
                throw new az("Invalid status line received: " + strArrSplit[2] + " Status line: " + strB);
            }
            bs bsVar = new bs();
            bsVar.a(strArrSplit[1]);
            brVar = bsVar;
        }
        String strB2 = b(byteBuffer);
        while (strB2 != null && strB2.length() > 0) {
            String[] strArrSplit2 = strB2.split(":", 2);
            if (strArrSplit2.length != 2) {
                throw new az("not an http header");
            }
            if (brVar.c(strArrSplit2[0])) {
                brVar.a(strArrSplit2[0], brVar.b(strArrSplit2[0]) + "; " + strArrSplit2[1].replaceFirst("^ +", ""));
            } else {
                brVar.a(strArrSplit2[0], strArrSplit2[1].replaceFirst("^ +", ""));
            }
            strB2 = b(byteBuffer);
        }
        if (strB2 != null) {
            return brVar;
        }
        throw new aw();
    }

    public static ByteBuffer a(ByteBuffer byteBuffer) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(byteBuffer.remaining());
        byte b2 = 48;
        while (byteBuffer.hasRemaining()) {
            byte b3 = byteBuffer.get();
            byteBufferAllocate.put(b3);
            if (b2 == 13 && b3 == 10) {
                byteBufferAllocate.limit(byteBufferAllocate.position() - 2);
                byteBufferAllocate.position(0);
                return byteBufferAllocate;
            }
            b2 = b3;
        }
        byteBuffer.position(byteBuffer.position() - byteBufferAllocate.position());
        return null;
    }

    public static String b(ByteBuffer byteBuffer) {
        ByteBuffer byteBufferA = a(byteBuffer);
        if (byteBufferA == null) {
            return null;
        }
        return cc.a(byteBufferA.array(), 0, byteBufferA.limit());
    }

    public int a(int i) throws ax {
        if (i >= 0) {
            return i;
        }
        throw new ax(1002, "Negative count");
    }

    public abstract b a(bp bpVar);

    public abstract b a(bp bpVar, bw bwVar);

    public abstract bq a(bq bqVar);

    public abstract br a(bp bpVar, bx bxVar);

    public abstract ByteBuffer a(bl blVar);

    public List<bl> a(bl.a aVar, ByteBuffer byteBuffer, boolean z) {
        bh biVar;
        if (aVar != bl.a.BINARY && aVar != bl.a.TEXT) {
            throw new IllegalArgumentException("Only Opcode.BINARY or  Opcode.TEXT are allowed");
        }
        if (this.d != null) {
            biVar = new bk();
        } else {
            this.d = aVar;
            biVar = aVar == bl.a.BINARY ? new bi() : aVar == bl.a.TEXT ? new bo() : null;
        }
        biVar.a(byteBuffer);
        biVar.d(z);
        try {
            biVar.a();
            if (z) {
                this.d = null;
            } else {
                this.d = aVar;
            }
            return Collections.singletonList(biVar);
        } catch (ax e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<ByteBuffer> a(bu buVar, ap.b bVar) {
        return a(buVar, bVar, true);
    }

    public List<ByteBuffer> a(bu buVar, ap.b bVar, boolean z) {
        StringBuilder sb = new StringBuilder(100);
        if (buVar instanceof bp) {
            sb.append("GET ");
            sb.append(((bp) buVar).a());
            sb.append(" HTTP/1.1");
        } else {
            if (!(buVar instanceof bw)) {
                throw new IllegalArgumentException("unknown role");
            }
            sb.append("HTTP/1.1 101 ");
            sb.append(((bw) buVar).a());
        }
        sb.append("\r\n");
        Iterator<String> itB = buVar.b();
        while (itB.hasNext()) {
            String next = itB.next();
            String strB = buVar.b(next);
            sb.append(next);
            sb.append(": ");
            sb.append(strB);
            sb.append("\r\n");
        }
        sb.append("\r\n");
        byte[] bArrB = cc.b(sb.toString());
        byte[] bArrC = z ? buVar.c() : null;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate((bArrC == null ? 0 : bArrC.length) + bArrB.length);
        byteBufferAllocate.put(bArrB);
        if (bArrC != null) {
            byteBufferAllocate.put(bArrC);
        }
        byteBufferAllocate.flip();
        return Collections.singletonList(byteBufferAllocate);
    }

    public abstract void a();

    public void a(ap.b bVar) {
        this.c = bVar;
    }

    public abstract void a(aq aqVar, bl blVar);

    protected boolean a(bu buVar) {
        return "websocket".equalsIgnoreCase(buVar.b("Upgrade")) && buVar.b("Connection").toLowerCase(Locale.ENGLISH).contains("upgrade");
    }

    int b(bu buVar) {
        String strB = buVar.b("Sec-WebSocket-Version");
        if (strB.length() > 0) {
            try {
                return new Integer(strB.trim()).intValue();
            } catch (NumberFormatException unused) {
            }
        }
        return -1;
    }

    public abstract a b();

    public abstract at c();

    public abstract List<bl> c(ByteBuffer byteBuffer);

    public bu d(ByteBuffer byteBuffer) {
        return a(byteBuffer, this.c);
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}
