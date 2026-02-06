package com.tencent.tinker.android.dx.instruction;

import com.tencent.tinker.android.utils.SparseIntArray;

public abstract class CodeCursor {
    private final SparseIntArray baseAddressMap = new SparseIntArray();
    private int cursor = 0;

    protected final void advance(int i) {
        this.cursor += i;
    }

    public final int baseAddressForCursor() {
        int iIndexOfKey = this.baseAddressMap.indexOfKey(this.cursor);
        return iIndexOfKey < 0 ? this.cursor : this.baseAddressMap.valueAt(iIndexOfKey);
    }

    public final int cursor() {
        return this.cursor;
    }

    public void reset() {
        this.baseAddressMap.clear();
        this.cursor = 0;
    }

    public final void setBaseAddress(int i, int i2) {
        this.baseAddressMap.put(i, i2);
    }
}
