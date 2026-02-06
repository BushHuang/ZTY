package com.analysys.visual;

import com.analysys.visual.ap;
import com.analysys.visual.at;
import com.analysys.visual.bl;
import java.io.PrintStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

public class au extends at {
    static final boolean e = !au.class.desiredAssertionStatus();
    private final Random f;
    private be g;
    private List<be> h;
    private by i;
    private List<by> j;
    private bl k;
    private List<ByteBuffer> l;
    private ByteBuffer m;

    public au() {
        this(Collections.emptyList());
    }

    public au(List<be> list) {
        this(list, Collections.singletonList(new bz("")));
    }

    public au(List<be> list, List<by> list2) {
        this.f = new Random();
        this.g = new bd();
        if (list == null || list2 == null) {
            throw new IllegalArgumentException();
        }
        this.h = new ArrayList(list.size());
        this.j = new ArrayList(list2.size());
        boolean z = false;
        this.l = new ArrayList();
        Iterator<be> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().getClass().equals(bd.class)) {
                z = true;
            }
        }
        this.h.addAll(list);
        if (!z) {
            List<be> list3 = this.h;
            list3.add(list3.size(), this.g);
        }
        this.j.addAll(list2);
    }

    private byte a(bl.a aVar) {
        if (aVar == bl.a.CONTINUOUS) {
            return (byte) 0;
        }
        if (aVar == bl.a.TEXT) {
            return (byte) 1;
        }
        if (aVar == bl.a.BINARY) {
            return (byte) 2;
        }
        if (aVar == bl.a.CLOSING) {
            return (byte) 8;
        }
        if (aVar == bl.a.PING) {
            return (byte) 9;
        }
        if (aVar == bl.a.PONG) {
            return (byte) 10;
        }
        throw new IllegalArgumentException("Don't know how to handle " + aVar.toString());
    }

    private bl.a a(byte b) throws ay {
        if (b == 0) {
            return bl.a.CONTINUOUS;
        }
        if (b == 1) {
            return bl.a.TEXT;
        }
        if (b == 2) {
            return bl.a.BINARY;
        }
        switch (b) {
            case 8:
                return bl.a.CLOSING;
            case 9:
                return bl.a.PING;
            case 10:
                return bl.a.PONG;
            default:
                throw new ay("Unknown opcode " + ((int) b));
        }
    }

    private String a(String str) throws NoSuchAlgorithmException {
        try {
            return ca.a(MessageDigest.getInstance("SHA1").digest((str.trim() + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").getBytes()));
        } catch (NoSuchAlgorithmException e2) {
            throw new IllegalStateException(e2);
        }
    }

    private byte[] a(long j, int i) {
        byte[] bArr = new byte[i];
        int i2 = (i * 8) - 8;
        for (int i3 = 0; i3 < i; i3++) {
            bArr[i3] = (byte) (j >>> (i2 - (i3 * 8)));
        }
        return bArr;
    }

    private ByteBuffer b(bl blVar) {
        ByteBuffer byteBufferG = blVar.g();
        int i = 0;
        boolean z = this.c == ap.b.CLIENT;
        int i2 = byteBufferG.remaining() <= 125 ? 1 : byteBufferG.remaining() <= 65535 ? 2 : 8;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate((i2 > 1 ? i2 + 1 : i2) + 1 + (z ? 4 : 0) + byteBufferG.remaining());
        byteBufferAllocate.put((byte) (((byte) (blVar.e() ? -128 : 0)) | a(blVar.f())));
        byte[] bArrA = a(byteBufferG.remaining(), i2);
        if (!e && bArrA.length != i2) {
            throw new AssertionError();
        }
        if (i2 == 1) {
            byteBufferAllocate.put((byte) (bArrA[0] | (z ? (byte) -128 : (byte) 0)));
        } else if (i2 == 2) {
            byteBufferAllocate.put((byte) ((z ? (byte) -128 : (byte) 0) | 126));
            byteBufferAllocate.put(bArrA);
        } else {
            if (i2 != 8) {
                throw new RuntimeException("Size representation not supported/specified");
            }
            byteBufferAllocate.put((byte) ((z ? (byte) -128 : (byte) 0) | 127));
            byteBufferAllocate.put(bArrA);
        }
        if (z) {
            ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(4);
            byteBufferAllocate2.putInt(this.f.nextInt());
            byteBufferAllocate.put(byteBufferAllocate2.array());
            while (byteBufferG.hasRemaining()) {
                byteBufferAllocate.put((byte) (byteBufferG.get() ^ byteBufferAllocate2.get(i % 4)));
                i++;
            }
        } else {
            byteBufferAllocate.put(byteBufferG);
            byteBufferG.flip();
        }
        if (!e && byteBufferAllocate.remaining() != 0) {
            throw new AssertionError(byteBufferAllocate.remaining());
        }
        byteBufferAllocate.flip();
        return byteBufferAllocate;
    }

    private String h() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat.format(calendar.getTime());
    }

    private ByteBuffer i() throws ba {
        long jLimit = 0;
        while (this.l.iterator().hasNext()) {
            jLimit += r0.next().limit();
        }
        if (jLimit > 2147483647L) {
            throw new ba("Payloadsize is to big...");
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate((int) jLimit);
        Iterator<ByteBuffer> it = this.l.iterator();
        while (it.hasNext()) {
            byteBufferAllocate.put(it.next());
        }
        byteBufferAllocate.flip();
        return byteBufferAllocate;
    }

    @Override
    public at.b a(bp bpVar) {
        if (b(bpVar) != 13) {
            return at.b.NOT_MATCHED;
        }
        at.b bVar = at.b.NOT_MATCHED;
        String strB = bpVar.b("Sec-WebSocket-Extensions");
        Iterator<be> it = this.h.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            be next = it.next();
            if (next.a(strB)) {
                this.g = next;
                bVar = at.b.MATCHED;
                break;
            }
        }
        at.b bVar2 = at.b.NOT_MATCHED;
        String strB2 = bpVar.b("Sec-WebSocket-Protocol");
        Iterator<by> it2 = this.j.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            by next2 = it2.next();
            if (next2.a(strB2)) {
                this.i = next2;
                bVar2 = at.b.MATCHED;
                break;
            }
        }
        return (bVar2 == at.b.MATCHED && bVar == at.b.MATCHED) ? at.b.MATCHED : at.b.NOT_MATCHED;
    }

    @Override
    public at.b a(bp bpVar, bw bwVar) {
        if (!a(bwVar)) {
            return at.b.NOT_MATCHED;
        }
        if (!bpVar.c("Sec-WebSocket-Key") || !bwVar.c("Sec-WebSocket-Accept")) {
            return at.b.NOT_MATCHED;
        }
        if (!a(bpVar.b("Sec-WebSocket-Key")).equals(bwVar.b("Sec-WebSocket-Accept"))) {
            return at.b.NOT_MATCHED;
        }
        at.b bVar = at.b.NOT_MATCHED;
        String strB = bwVar.b("Sec-WebSocket-Extensions");
        Iterator<be> it = this.h.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            be next = it.next();
            if (next.b(strB)) {
                this.g = next;
                bVar = at.b.MATCHED;
                break;
            }
        }
        at.b bVar2 = at.b.NOT_MATCHED;
        String strB2 = bwVar.b("Sec-WebSocket-Protocol");
        Iterator<by> it2 = this.j.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            by next2 = it2.next();
            if (next2.a(strB2)) {
                this.i = next2;
                bVar2 = at.b.MATCHED;
                break;
            }
        }
        return (bVar2 == at.b.MATCHED && bVar == at.b.MATCHED) ? at.b.MATCHED : at.b.NOT_MATCHED;
    }

    @Override
    public bq a(bq bqVar) {
        bqVar.a("Upgrade", "websocket");
        bqVar.a("Connection", "Upgrade");
        byte[] bArr = new byte[16];
        this.f.nextBytes(bArr);
        bqVar.a("Sec-WebSocket-Key", ca.a(bArr));
        bqVar.a("Sec-WebSocket-Version", "13");
        StringBuilder sb = new StringBuilder();
        for (be beVar : this.h) {
            if (beVar.a() != null && beVar.a().length() != 0) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(beVar.a());
            }
        }
        if (sb.length() != 0) {
            bqVar.a("Sec-WebSocket-Extensions", sb.toString());
        }
        StringBuilder sb2 = new StringBuilder();
        for (by byVar : this.j) {
            if (byVar.a().length() != 0) {
                if (sb2.length() > 0) {
                    sb2.append(", ");
                }
                sb2.append(byVar.a());
            }
        }
        if (sb2.length() != 0) {
            bqVar.a("Sec-WebSocket-Protocol", sb2.toString());
        }
        return bqVar;
    }

    @Override
    public br a(bp bpVar, bx bxVar) throws az {
        bxVar.a("Upgrade", "websocket");
        bxVar.a("Connection", bpVar.b("Connection"));
        String strB = bpVar.b("Sec-WebSocket-Key");
        if (strB == null) {
            throw new az("missing Sec-WebSocket-Key");
        }
        bxVar.a("Sec-WebSocket-Accept", a(strB));
        if (d().b().length() != 0) {
            bxVar.a("Sec-WebSocket-Extensions", d().b());
        }
        if (f() != null && f().a().length() != 0) {
            bxVar.a("Sec-WebSocket-Protocol", f().a());
        }
        bxVar.a("Web Socket Protocol Handshake");
        bxVar.a("Server", "TooTallNate Java-WebSocket");
        bxVar.a("Date", h());
        return bxVar;
    }

    @Override
    public ByteBuffer a(bl blVar) {
        d().b(blVar);
        if (aq.b) {
            PrintStream printStream = System.out;
            StringBuilder sb = new StringBuilder();
            sb.append("afterEnconding(");
            sb.append(blVar.g().remaining());
            sb.append("): {");
            sb.append(blVar.g().remaining() > 1000 ? "too big to display" : new String(blVar.g().array()));
            sb.append('}');
            printStream.println(sb.toString());
        }
        return b(blVar);
    }

    @Override
    public void a() {
        this.m = null;
        be beVar = this.g;
        if (beVar != null) {
            beVar.d();
        }
        this.g = new bd();
        this.i = null;
    }

    @Override
    public void a(aq aqVar, bl blVar) throws ax {
        String strI;
        bl.a aVarF = blVar.f();
        if (aVarF == bl.a.CLOSING) {
            int iH = 1005;
            if (blVar instanceof bj) {
                bj bjVar = (bj) blVar;
                iH = bjVar.h();
                strI = bjVar.i();
            } else {
                strI = "";
            }
            if (aqVar.g() == ap.a.CLOSING) {
                aqVar.b(iH, strI, true);
                return;
            } else if (b() == at.a.TWOWAY) {
                aqVar.a(iH, strI, true);
                return;
            } else {
                aqVar.c(iH, strI, false);
                return;
            }
        }
        if (aVarF == bl.a.PING) {
            aqVar.j().a(aqVar, blVar);
            return;
        }
        if (aVarF == bl.a.PONG) {
            aqVar.i();
            aqVar.j().b(aqVar, blVar);
            return;
        }
        if (blVar.e() && aVarF != bl.a.CONTINUOUS) {
            if (this.k != null) {
                throw new ax(1002, "Continuous frame sequence not completed.");
            }
            if (aVarF == bl.a.TEXT) {
                try {
                    aqVar.j().a(aqVar, cc.a(blVar.g()));
                    return;
                } catch (RuntimeException e2) {
                    aqVar.j().a(aqVar, e2);
                    return;
                }
            }
            if (aVarF != bl.a.BINARY) {
                throw new ax(1002, "non control or continious frame expected");
            }
            try {
                aqVar.j().a(aqVar, blVar.g());
                return;
            } catch (RuntimeException e3) {
                aqVar.j().a(aqVar, e3);
                return;
            }
        }
        if (aVarF != bl.a.CONTINUOUS) {
            if (this.k != null) {
                throw new ax(1002, "Previous continuous frame sequence not completed.");
            }
            this.k = blVar;
            this.l.add(blVar.g());
        } else if (blVar.e()) {
            if (this.k == null) {
                throw new ax(1002, "Continuous frame sequence was not started.");
            }
            this.l.add(blVar.g());
            if (this.k.f() == bl.a.TEXT) {
                ((bh) this.k).a(i());
                ((bh) this.k).a();
                try {
                    aqVar.j().a(aqVar, cc.a(this.k.g()));
                } catch (RuntimeException e4) {
                    aqVar.j().a(aqVar, e4);
                }
            } else if (this.k.f() == bl.a.BINARY) {
                ((bh) this.k).a(i());
                ((bh) this.k).a();
                try {
                    aqVar.j().a(aqVar, this.k.g());
                } catch (RuntimeException e5) {
                    aqVar.j().a(aqVar, e5);
                }
            }
            this.k = null;
            this.l.clear();
        } else if (this.k == null) {
            throw new ax(1002, "Continuous frame sequence was not started.");
        }
        if (aVarF == bl.a.TEXT && !cc.b(blVar.g())) {
            throw new ax(1007);
        }
        if (aVarF != bl.a.CONTINUOUS || this.k == null) {
            return;
        }
        this.l.add(blVar.g());
    }

    @Override
    public at.a b() {
        return at.a.TWOWAY;
    }

    @Override
    public at c() {
        ArrayList arrayList = new ArrayList();
        Iterator<be> it = e().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().c());
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator<by> it2 = g().iterator();
        while (it2.hasNext()) {
            arrayList2.add(it2.next().b());
        }
        return new au(arrayList, arrayList2);
    }

    @Override
    public List<bl> c(ByteBuffer byteBuffer) {
        while (true) {
            LinkedList linkedList = new LinkedList();
            if (this.m == null) {
                break;
            }
            try {
                byteBuffer.mark();
                int iRemaining = byteBuffer.remaining();
                int iRemaining2 = this.m.remaining();
                if (iRemaining2 > iRemaining) {
                    this.m.put(byteBuffer.array(), byteBuffer.position(), iRemaining);
                    byteBuffer.position(byteBuffer.position() + iRemaining);
                    return Collections.emptyList();
                }
                this.m.put(byteBuffer.array(), byteBuffer.position(), iRemaining2);
                byteBuffer.position(byteBuffer.position() + iRemaining2);
                linkedList.add(e((ByteBuffer) this.m.duplicate().position(0)));
                this.m = null;
            } catch (av e2) {
                ByteBuffer byteBufferAllocate = ByteBuffer.allocate(a(e2.a()));
                if (!e && byteBufferAllocate.limit() <= this.m.limit()) {
                    throw new AssertionError();
                }
                this.m.rewind();
                byteBufferAllocate.put(this.m);
                this.m = byteBufferAllocate;
            }
        }
    }

    public be d() {
        return this.g;
    }

    public bl e(ByteBuffer byteBuffer) throws ay, ba, av {
        boolean z;
        int iIntValue;
        int iRemaining = byteBuffer.remaining();
        int i = 2;
        if (iRemaining < 2) {
            throw new av(2);
        }
        byte b = byteBuffer.get();
        boolean z2 = (b >> 8) != 0;
        boolean z3 = (b & 64) != 0;
        boolean z4 = (b & 32) != 0;
        boolean z5 = (b & 16) != 0;
        byte b2 = byteBuffer.get();
        boolean z6 = (b2 & (-128)) != 0;
        byte b3 = (byte) (b2 & 127);
        bl.a aVarA = a((byte) (b & 15));
        if (b3 >= 0 && b3 <= 125) {
            z = z3;
            iIntValue = b3;
        } else {
            if (aVarA == bl.a.PING || aVarA == bl.a.PONG || aVarA == bl.a.CLOSING) {
                throw new ay("more than 125 octets");
            }
            if (b3 != 126) {
                i = 10;
                if (iRemaining < 10) {
                    throw new av(10);
                }
                byte[] bArr = new byte[8];
                for (int i2 = 0; i2 < 8; i2++) {
                    bArr[i2] = byteBuffer.get();
                }
                z = z3;
                long jLongValue = new BigInteger(bArr).longValue();
                if (jLongValue > 2147483647L) {
                    throw new ba("Payloadsize is to big...");
                }
                iIntValue = (int) jLongValue;
            } else {
                if (iRemaining < 4) {
                    throw new av(4);
                }
                z = z3;
                iIntValue = new BigInteger(new byte[]{0, byteBuffer.get(), byteBuffer.get()}).intValue();
                i = 4;
            }
        }
        int i3 = i + (z6 ? 4 : 0) + iIntValue;
        if (iRemaining < i3) {
            throw new av(i3);
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(a(iIntValue));
        if (z6) {
            byte[] bArr2 = new byte[4];
            byteBuffer.get(bArr2);
            for (int i4 = 0; i4 < iIntValue; i4++) {
                byteBufferAllocate.put((byte) (byteBuffer.get() ^ bArr2[i4 % 4]));
            }
        } else {
            byteBufferAllocate.put(byteBuffer.array(), byteBuffer.position(), byteBufferAllocate.limit());
            byteBuffer.position(byteBuffer.position() + byteBufferAllocate.limit());
        }
        bh bhVarA = bh.a(aVarA);
        bhVarA.d(z2);
        bhVarA.a(z);
        bhVarA.b(z4);
        bhVarA.c(z5);
        byteBufferAllocate.flip();
        bhVarA.a(byteBufferAllocate);
        d().c(bhVarA);
        d().a(bhVarA);
        if (aq.b) {
            PrintStream printStream = System.out;
            StringBuilder sb = new StringBuilder();
            sb.append("afterDecoding(");
            sb.append(bhVarA.g().remaining());
            sb.append("): {");
            sb.append(bhVarA.g().remaining() > 1000 ? "too big to display" : new String(bhVarA.g().array()));
            sb.append('}');
            printStream.println(sb.toString());
        }
        bhVarA.a();
        return bhVarA;
    }

    public List<be> e() {
        return this.h;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        au auVar = (au) obj;
        be beVar = this.g;
        if (beVar == null ? auVar.g != null : !beVar.equals(auVar.g)) {
            return false;
        }
        by byVar = this.i;
        by byVar2 = auVar.i;
        return byVar != null ? byVar.equals(byVar2) : byVar2 == null;
    }

    public by f() {
        return this.i;
    }

    public List<by> g() {
        return this.j;
    }

    public int hashCode() {
        be beVar = this.g;
        int iHashCode = (beVar != null ? beVar.hashCode() : 0) * 31;
        by byVar = this.i;
        return iHashCode + (byVar != null ? byVar.hashCode() : 0);
    }

    @Override
    public String toString() {
        String string = super.toString();
        if (d() != null) {
            string = string + " extension: " + d().toString();
        }
        if (f() == null) {
            return string;
        }
        return string + " protocol: " + f().toString();
    }
}
