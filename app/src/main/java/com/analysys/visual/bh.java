package com.analysys.visual;

import com.analysys.visual.bl;
import java.nio.ByteBuffer;

public abstract class bh implements bl {
    private bl.a b;
    private ByteBuffer c = cb.a();

    private boolean f132a = true;
    private boolean d = false;
    private boolean e = false;
    private boolean f = false;
    private boolean g = false;

    static class AnonymousClass1 {

        static final int[] f133a;

        static {
            int[] iArr = new int[bl.a.values().length];
            f133a = iArr;
            try {
                iArr[bl.a.PING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f133a[bl.a.PONG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f133a[bl.a.TEXT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f133a[bl.a.BINARY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f133a[bl.a.CLOSING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f133a[bl.a.CONTINUOUS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public bh(bl.a aVar) {
        this.b = aVar;
    }

    public static bh a(bl.a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("Supplied opcode cannot be null");
        }
        switch (AnonymousClass1.f133a[aVar.ordinal()]) {
            case 1:
                return new bm();
            case 2:
                return new bn();
            case 3:
                return new bo();
            case 4:
                return new bi();
            case 5:
                return new bj();
            case 6:
                return new bk();
            default:
                throw new IllegalArgumentException("Supplied opcode is invalid");
        }
    }

    public abstract void a();

    public void a(ByteBuffer byteBuffer) {
        this.c = byteBuffer;
    }

    public void a(boolean z) {
        this.e = z;
    }

    public void b(boolean z) {
        this.f = z;
    }

    @Override
    public boolean b() {
        return this.e;
    }

    public void c(boolean z) {
        this.g = z;
    }

    @Override
    public boolean c() {
        return this.f;
    }

    public void d(boolean z) {
        this.f132a = z;
    }

    @Override
    public boolean d() {
        return this.g;
    }

    @Override
    public boolean e() {
        return this.f132a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        bh bhVar = (bh) obj;
        if (this.f132a != bhVar.f132a || this.d != bhVar.d || this.e != bhVar.e || this.f != bhVar.f || this.g != bhVar.g || this.b != bhVar.b) {
            return false;
        }
        ByteBuffer byteBuffer = this.c;
        ByteBuffer byteBuffer2 = bhVar.c;
        return byteBuffer != null ? byteBuffer.equals(byteBuffer2) : byteBuffer2 == null;
    }

    @Override
    public bl.a f() {
        return this.b;
    }

    @Override
    public ByteBuffer g() {
        return this.c;
    }

    public int hashCode() {
        int iHashCode = (((this.f132a ? 1 : 0) * 31) + this.b.hashCode()) * 31;
        ByteBuffer byteBuffer = this.c;
        return ((((((((iHashCode + (byteBuffer != null ? byteBuffer.hashCode() : 0)) * 31) + (this.d ? 1 : 0)) * 31) + (this.e ? 1 : 0)) * 31) + (this.f ? 1 : 0)) * 31) + (this.g ? 1 : 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Framedata{ optcode:");
        sb.append(f());
        sb.append(", fin:");
        sb.append(e());
        sb.append(", rsv1:");
        sb.append(b());
        sb.append(", rsv2:");
        sb.append(c());
        sb.append(", rsv3:");
        sb.append(d());
        sb.append(", payloadlength:[pos:");
        sb.append(this.c.position());
        sb.append(", len:");
        sb.append(this.c.remaining());
        sb.append("], payload:");
        sb.append(this.c.remaining() > 1000 ? "(too big to display)" : new String(this.c.array()));
        sb.append('}');
        return sb.toString();
    }
}
