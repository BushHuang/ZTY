package com.tencent.tinker.android.dex;

import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.util.CompareUtils;
import com.tencent.tinker.android.dex.util.HashCodeHelper;

public final class Code extends TableOfContents.Section.Item<Code> {
    public CatchHandler[] catchHandlers;
    public int debugInfoOffset;
    public int insSize;
    public short[] instructions;
    public int outsSize;
    public int registersSize;
    public Try[] tries;

    public static class CatchHandler implements Comparable<CatchHandler> {
        public int[] addresses;
        public int catchAllAddress;
        public int offset;
        public int[] typeIndexes;

        public CatchHandler(int[] iArr, int[] iArr2, int i, int i2) {
            this.typeIndexes = iArr;
            this.addresses = iArr2;
            this.catchAllAddress = i;
            this.offset = i2;
        }

        @Override
        public int compareTo(CatchHandler catchHandler) {
            int iSArrCompare = CompareUtils.sArrCompare(this.typeIndexes, catchHandler.typeIndexes);
            if (iSArrCompare != 0) {
                return iSArrCompare;
            }
            int iSArrCompare2 = CompareUtils.sArrCompare(this.addresses, catchHandler.addresses);
            return iSArrCompare2 != 0 ? iSArrCompare2 : CompareUtils.sCompare(this.catchAllAddress, catchHandler.catchAllAddress);
        }
    }

    public static class Try implements Comparable<Try> {
        public int catchHandlerIndex;
        public int instructionCount;
        public int startAddress;

        public Try(int i, int i2, int i3) {
            this.startAddress = i;
            this.instructionCount = i2;
            this.catchHandlerIndex = i3;
        }

        @Override
        public int compareTo(Try r3) {
            int iSCompare = CompareUtils.sCompare(this.startAddress, r3.startAddress);
            if (iSCompare != 0) {
                return iSCompare;
            }
            int iSCompare2 = CompareUtils.sCompare(this.instructionCount, r3.instructionCount);
            return iSCompare2 != 0 ? iSCompare2 : CompareUtils.sCompare(this.catchHandlerIndex, r3.catchHandlerIndex);
        }
    }

    public Code(int i, int i2, int i3, int i4, int i5, short[] sArr, Try[] tryArr, CatchHandler[] catchHandlerArr) {
        super(i);
        this.registersSize = i2;
        this.insSize = i3;
        this.outsSize = i4;
        this.debugInfoOffset = i5;
        this.instructions = sArr;
        this.tries = tryArr;
        this.catchHandlers = catchHandlerArr;
    }

    @Override
    public int byteCountInDex() {
        int length = this.instructions.length;
        int length2 = (length * 2) + 16;
        if (this.tries.length > 0) {
            if ((length & 1) == 1) {
                length2 += 2;
            }
            length2 = length2 + (this.tries.length * 8) + Leb128.unsignedLeb128Size(this.catchHandlers.length);
            for (CatchHandler catchHandler : this.catchHandlers) {
                int length3 = catchHandler.typeIndexes.length;
                length2 += catchHandler.catchAllAddress != -1 ? Leb128.signedLeb128Size(-length3) + Leb128.unsignedLeb128Size(catchHandler.catchAllAddress) : Leb128.signedLeb128Size(length3);
                for (int i = 0; i < length3; i++) {
                    length2 += Leb128.unsignedLeb128Size(catchHandler.typeIndexes[i]) + Leb128.unsignedLeb128Size(catchHandler.addresses[i]);
                }
            }
        }
        return length2;
    }

    @Override
    public int compareTo(Code code) {
        int iSCompare = CompareUtils.sCompare(this.registersSize, code.registersSize);
        if (iSCompare != 0) {
            return iSCompare;
        }
        int iSCompare2 = CompareUtils.sCompare(this.insSize, code.insSize);
        if (iSCompare2 != 0) {
            return iSCompare2;
        }
        int iSCompare3 = CompareUtils.sCompare(this.outsSize, code.outsSize);
        if (iSCompare3 != 0) {
            return iSCompare3;
        }
        int iSCompare4 = CompareUtils.sCompare(this.debugInfoOffset, code.debugInfoOffset);
        if (iSCompare4 != 0) {
            return iSCompare4;
        }
        int iUArrCompare = CompareUtils.uArrCompare(this.instructions, code.instructions);
        if (iUArrCompare != 0) {
            return iUArrCompare;
        }
        int iAArrCompare = CompareUtils.aArrCompare(this.tries, code.tries);
        return iAArrCompare != 0 ? iAArrCompare : CompareUtils.aArrCompare(this.catchHandlers, code.catchHandlers);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Code) && compareTo((Code) obj) == 0;
    }

    @Override
    public int hashCode() {
        return HashCodeHelper.hash(Integer.valueOf(this.registersSize), Integer.valueOf(this.insSize), Integer.valueOf(this.outsSize), Integer.valueOf(this.debugInfoOffset), this.instructions, this.tries, this.catchHandlers);
    }
}
