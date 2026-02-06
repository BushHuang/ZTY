package com.tencent.tinker.android.dex;

import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.util.CompareUtils;
import com.tencent.tinker.android.dex.util.HashCodeHelper;

public class DebugInfoItem extends TableOfContents.Section.Item<DebugInfoItem> {
    public static final byte DBG_ADVANCE_LINE = 2;
    public static final byte DBG_ADVANCE_PC = 1;
    public static final byte DBG_END_LOCAL = 5;
    public static final byte DBG_END_SEQUENCE = 0;
    public static final byte DBG_RESTART_LOCAL = 6;
    public static final byte DBG_SET_EPILOGUE_BEGIN = 8;
    public static final byte DBG_SET_FILE = 9;
    public static final byte DBG_SET_PROLOGUE_END = 7;
    public static final byte DBG_START_LOCAL = 3;
    public static final byte DBG_START_LOCAL_EXTENDED = 4;
    public byte[] infoSTM;
    public int lineStart;
    public int[] parameterNames;

    public DebugInfoItem(int i, int i2, int[] iArr, byte[] bArr) {
        super(i);
        this.lineStart = i2;
        this.parameterNames = iArr;
        this.infoSTM = bArr;
    }

    @Override
    public int byteCountInDex() {
        int iUnsignedLeb128Size = Leb128.unsignedLeb128Size(this.lineStart) + Leb128.unsignedLeb128Size(this.parameterNames.length);
        for (int i : this.parameterNames) {
            iUnsignedLeb128Size += Leb128.unsignedLeb128p1Size(i);
        }
        return iUnsignedLeb128Size + (this.infoSTM.length * 1);
    }

    @Override
    public int compareTo(DebugInfoItem debugInfoItem) {
        int i = this.lineStart;
        int i2 = debugInfoItem.lineStart;
        if (i != i2) {
            return i - i2;
        }
        int iUArrCompare = CompareUtils.uArrCompare(this.parameterNames, debugInfoItem.parameterNames);
        return iUArrCompare != 0 ? iUArrCompare : CompareUtils.uArrCompare(this.infoSTM, debugInfoItem.infoSTM);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof DebugInfoItem) && compareTo((DebugInfoItem) obj) == 0;
    }

    @Override
    public int hashCode() {
        return HashCodeHelper.hash(Integer.valueOf(this.lineStart), this.parameterNames, this.infoSTM);
    }
}
