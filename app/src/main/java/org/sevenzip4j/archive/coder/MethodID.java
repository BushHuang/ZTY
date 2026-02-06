package org.sevenzip4j.archive.coder;

public class MethodID {
    static final int kMethodIDSize = 15;
    byte[] ID;
    byte IDSize;
    public static final MethodID k_LZMA = new MethodID(3, 1, 1);
    public static final MethodID k_PPMD = new MethodID(3, 4, 1);
    public static final MethodID k_BCJ_X86 = new MethodID(3, 3, 1, 3);
    public static final MethodID k_BCJ = new MethodID(3, 3, 1, 3);
    public static final MethodID k_BCJ2 = new MethodID(3, 3, 1, 27);
    public static final MethodID k_Deflate = new MethodID(4, 1, 8);
    public static final MethodID k_Deflate64 = new MethodID(4, 1, 9);
    public static final MethodID k_BZip2 = new MethodID(4, 2, 2);
    public static final MethodID k_Copy = new MethodID(0);
    public static final MethodID k_7zAES = new MethodID(6, 241, 7, 1);

    public MethodID() {
        this.ID = new byte[15];
        this.IDSize = (byte) 0;
    }

    public MethodID(int i) {
        this.ID = new byte[]{(byte) i};
        this.IDSize = (byte) 1;
    }

    public MethodID(int i, int i2, int i3) {
        this.ID = new byte[]{(byte) i, (byte) i2, (byte) i3};
        this.IDSize = (byte) 3;
    }

    public MethodID(int i, int i2, int i3, int i4) {
        this.ID = new byte[]{(byte) i, (byte) i2, (byte) i3, (byte) i4};
        this.IDSize = (byte) 4;
    }

    public boolean equals(MethodID methodID) {
        if (this.IDSize != methodID.IDSize) {
            return false;
        }
        for (int i = 0; i < this.IDSize; i++) {
            if (this.ID[i] != methodID.ID[i]) {
                return false;
            }
        }
        return true;
    }

    public byte[] getID() {
        return this.ID;
    }
}
