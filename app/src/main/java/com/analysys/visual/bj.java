package com.analysys.visual;

import com.analysys.visual.bl;
import java.nio.ByteBuffer;

public class bj extends bf {

    private int f135a;
    private String b;

    public bj() {
        super(bl.a.CLOSING);
        a("");
        a(1000);
    }

    private void j() {
        byte[] bArrA = cc.a(this.b);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        byteBufferAllocate.putInt(this.f135a);
        byteBufferAllocate.position(2);
        ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(bArrA.length + 2);
        byteBufferAllocate2.put(byteBufferAllocate);
        byteBufferAllocate2.put(bArrA);
        byteBufferAllocate2.rewind();
        super.a(byteBufferAllocate2);
    }

    @Override
    public void a() throws ax {
        super.a();
        if (this.f135a == 1007 && this.b == null) {
            throw new ax(1007, "Received text is no valid utf8 string!");
        }
        if (this.f135a == 1005 && this.b.length() > 0) {
            throw new ax(1002, "A close frame must have a closecode if it has a reason");
        }
        int i = this.f135a;
        if (i > 1015 && i < 3000) {
            throw new ax(1002, "Trying to send an illegal close code!");
        }
        int i2 = this.f135a;
        if (i2 == 1006 || i2 == 1015 || i2 == 1005 || i2 > 4999 || i2 < 1000 || i2 == 1004) {
            throw new ay("closecode must not be sent over the wire: " + this.f135a);
        }
    }

    public void a(int i) {
        this.f135a = i;
        if (i == 1015) {
            this.f135a = 1005;
            this.b = "";
        }
        j();
    }

    public void a(String str) {
        if (str == null) {
            str = "";
        }
        this.b = str;
        j();
    }

    @Override
    public void a(ByteBuffer byteBuffer) {
        this.f135a = 1005;
        this.b = "";
        byteBuffer.mark();
        if (byteBuffer.remaining() == 0) {
            this.f135a = 1000;
            return;
        }
        if (byteBuffer.remaining() == 1) {
            this.f135a = 1002;
            return;
        }
        if (byteBuffer.remaining() >= 2) {
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
            byteBufferAllocate.position(2);
            byteBufferAllocate.putShort(byteBuffer.getShort());
            byteBufferAllocate.position(0);
            this.f135a = byteBufferAllocate.getInt();
        }
        byteBuffer.reset();
        try {
            int iPosition = byteBuffer.position();
            try {
                try {
                    byteBuffer.position(byteBuffer.position() + 2);
                    this.b = cc.a(byteBuffer);
                } catch (IllegalArgumentException unused) {
                    throw new ax(1007);
                }
            } finally {
                byteBuffer.position(iPosition);
            }
        } catch (ax unused2) {
            this.f135a = 1007;
            this.b = null;
        }
    }

    @Override
    public ByteBuffer g() {
        return this.f135a == 1005 ? cb.a() : super.g();
    }

    public int h() {
        return this.f135a;
    }

    public String i() {
        return this.b;
    }

    @Override
    public String toString() {
        return super.toString() + "code: " + this.f135a;
    }
}
