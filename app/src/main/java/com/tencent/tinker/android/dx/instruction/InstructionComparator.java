package com.tencent.tinker.android.dx.instruction;

import com.tencent.tinker.android.dex.DexException;
import com.tencent.tinker.android.dex.util.CompareUtils;
import com.tencent.tinker.android.dx.util.Hex;
import java.io.EOFException;
import java.util.HashSet;
import java.util.Set;

public abstract class InstructionComparator {
    private final InstructionHolder[] insnHolders1;
    private final InstructionHolder[] insnHolders2;
    private final short[] insns1;
    private final short[] insns2;
    private final Set<String> visitedInsnAddrPairs;

    private static class FillArrayDataPayloadInstructionHolder extends InstructionHolder {
        Object data;
        int elementWidth;
        int size;

        private FillArrayDataPayloadInstructionHolder() {
            super();
            this.data = null;
            this.size = 0;
            this.elementWidth = 0;
        }
    }

    private static class InstructionHolder {

        int f432a;
        int address;
        int b;
        int c;
        int d;
        int e;
        int index;
        int insnFormat;
        long literal;
        int opcode;
        int registerCount;
        int target;

        private InstructionHolder() {
            this.insnFormat = 0;
            this.address = -1;
            this.opcode = -1;
            this.index = 0;
            this.target = 0;
            this.literal = 0L;
            this.registerCount = 0;
            this.f432a = 0;
            this.b = 0;
            this.c = 0;
            this.d = 0;
            this.e = 0;
        }
    }

    private static class PackedSwitchPayloadInsntructionHolder extends InstructionHolder {
        int firstKey;
        int[] targets;

        private PackedSwitchPayloadInsntructionHolder() {
            super();
            this.firstKey = 0;
            this.targets = null;
        }
    }

    private static class SparseSwitchPayloadInsntructionHolder extends InstructionHolder {
        int[] keys;
        int[] targets;

        private SparseSwitchPayloadInsntructionHolder() {
            super();
            this.keys = null;
            this.targets = null;
        }
    }

    public InstructionComparator(short[] sArr, short[] sArr2) {
        this.insns1 = sArr;
        this.insns2 = sArr2;
        if (sArr != null) {
            this.insnHolders1 = readInstructionsIntoHolders(new ShortArrayCodeInput(sArr), sArr.length);
        } else {
            this.insnHolders1 = null;
        }
        if (sArr2 != null) {
            this.insnHolders2 = readInstructionsIntoHolders(new ShortArrayCodeInput(sArr2), sArr2.length);
        } else {
            this.insnHolders2 = null;
        }
        this.visitedInsnAddrPairs = new HashSet();
    }

    private boolean compareIndex(int i, int i2, int i3) {
        int instructionIndexType = InstructionCodec.getInstructionIndexType(i);
        return instructionIndexType != 2 ? instructionIndexType != 3 ? instructionIndexType != 4 ? instructionIndexType != 5 ? i2 == i3 : compareField(i2, i3) : compareMethod(i2, i3) : compareString(i2, i3) : compareType(i2, i3);
    }

    private InstructionHolder[] readInstructionsIntoHolders(ShortArrayCodeInput shortArrayCodeInput, int i) {
        shortArrayCodeInput.reset();
        final InstructionHolder[] instructionHolderArr = new InstructionHolder[i];
        try {
            new InstructionReader(shortArrayCodeInput).accept(new InstructionVisitor(null) {
                @Override
                public void visitFillArrayDataPayloadInsn(int i2, int i3, Object obj, int i4, int i5) {
                    FillArrayDataPayloadInstructionHolder fillArrayDataPayloadInstructionHolder = new FillArrayDataPayloadInstructionHolder();
                    fillArrayDataPayloadInstructionHolder.insnFormat = InstructionCodec.getInstructionFormat(i3);
                    fillArrayDataPayloadInstructionHolder.address = i2;
                    fillArrayDataPayloadInstructionHolder.opcode = i3;
                    fillArrayDataPayloadInstructionHolder.data = obj;
                    fillArrayDataPayloadInstructionHolder.size = i4;
                    fillArrayDataPayloadInstructionHolder.elementWidth = i5;
                    instructionHolderArr[i2] = fillArrayDataPayloadInstructionHolder;
                }

                @Override
                public void visitFiveRegisterInsn(int i2, int i3, int i4, int i5, int i6, long j, int i7, int i8, int i9, int i10, int i11) {
                    InstructionHolder instructionHolder = new InstructionHolder();
                    instructionHolder.insnFormat = InstructionCodec.getInstructionFormat(i3);
                    instructionHolder.address = i2;
                    instructionHolder.opcode = i3;
                    instructionHolder.index = i4;
                    instructionHolder.target = i6;
                    instructionHolder.literal = j;
                    instructionHolder.registerCount = 5;
                    instructionHolder.f432a = i7;
                    instructionHolder.b = i8;
                    instructionHolder.c = i9;
                    instructionHolder.d = i10;
                    instructionHolder.e = i11;
                    instructionHolderArr[i2] = instructionHolder;
                }

                @Override
                public void visitFourRegisterInsn(int i2, int i3, int i4, int i5, int i6, long j, int i7, int i8, int i9, int i10) {
                    InstructionHolder instructionHolder = new InstructionHolder();
                    instructionHolder.insnFormat = InstructionCodec.getInstructionFormat(i3);
                    instructionHolder.address = i2;
                    instructionHolder.opcode = i3;
                    instructionHolder.index = i4;
                    instructionHolder.target = i6;
                    instructionHolder.literal = j;
                    instructionHolder.registerCount = 4;
                    instructionHolder.f432a = i7;
                    instructionHolder.b = i8;
                    instructionHolder.c = i9;
                    instructionHolder.d = i10;
                    instructionHolderArr[i2] = instructionHolder;
                }

                @Override
                public void visitOneRegisterInsn(int i2, int i3, int i4, int i5, int i6, long j, int i7) {
                    InstructionHolder instructionHolder = new InstructionHolder();
                    instructionHolder.insnFormat = InstructionCodec.getInstructionFormat(i3);
                    instructionHolder.address = i2;
                    instructionHolder.opcode = i3;
                    instructionHolder.index = i4;
                    instructionHolder.target = i6;
                    instructionHolder.literal = j;
                    instructionHolder.registerCount = 1;
                    instructionHolder.f432a = i7;
                    instructionHolderArr[i2] = instructionHolder;
                }

                @Override
                public void visitPackedSwitchPayloadInsn(int i2, int i3, int i4, int[] iArr) {
                    PackedSwitchPayloadInsntructionHolder packedSwitchPayloadInsntructionHolder = new PackedSwitchPayloadInsntructionHolder();
                    packedSwitchPayloadInsntructionHolder.insnFormat = InstructionCodec.getInstructionFormat(i3);
                    packedSwitchPayloadInsntructionHolder.address = i2;
                    packedSwitchPayloadInsntructionHolder.opcode = i3;
                    packedSwitchPayloadInsntructionHolder.firstKey = i4;
                    packedSwitchPayloadInsntructionHolder.targets = iArr;
                    instructionHolderArr[i2] = packedSwitchPayloadInsntructionHolder;
                }

                @Override
                public void visitRegisterRangeInsn(int i2, int i3, int i4, int i5, int i6, long j, int i7, int i8) {
                    InstructionHolder instructionHolder = new InstructionHolder();
                    instructionHolder.insnFormat = InstructionCodec.getInstructionFormat(i3);
                    instructionHolder.address = i2;
                    instructionHolder.opcode = i3;
                    instructionHolder.index = i4;
                    instructionHolder.target = i6;
                    instructionHolder.literal = j;
                    instructionHolder.registerCount = i8;
                    instructionHolder.f432a = i7;
                    instructionHolderArr[i2] = instructionHolder;
                }

                @Override
                public void visitSparseSwitchPayloadInsn(int i2, int i3, int[] iArr, int[] iArr2) {
                    SparseSwitchPayloadInsntructionHolder sparseSwitchPayloadInsntructionHolder = new SparseSwitchPayloadInsntructionHolder();
                    sparseSwitchPayloadInsntructionHolder.insnFormat = InstructionCodec.getInstructionFormat(i3);
                    sparseSwitchPayloadInsntructionHolder.address = i2;
                    sparseSwitchPayloadInsntructionHolder.opcode = i3;
                    sparseSwitchPayloadInsntructionHolder.keys = iArr;
                    sparseSwitchPayloadInsntructionHolder.targets = iArr2;
                    instructionHolderArr[i2] = sparseSwitchPayloadInsntructionHolder;
                }

                @Override
                public void visitThreeRegisterInsn(int i2, int i3, int i4, int i5, int i6, long j, int i7, int i8, int i9) {
                    InstructionHolder instructionHolder = new InstructionHolder();
                    instructionHolder.insnFormat = InstructionCodec.getInstructionFormat(i3);
                    instructionHolder.address = i2;
                    instructionHolder.opcode = i3;
                    instructionHolder.index = i4;
                    instructionHolder.target = i6;
                    instructionHolder.literal = j;
                    instructionHolder.registerCount = 3;
                    instructionHolder.f432a = i7;
                    instructionHolder.b = i8;
                    instructionHolder.c = i9;
                    instructionHolderArr[i2] = instructionHolder;
                }

                @Override
                public void visitTwoRegisterInsn(int i2, int i3, int i4, int i5, int i6, long j, int i7, int i8) {
                    InstructionHolder instructionHolder = new InstructionHolder();
                    instructionHolder.insnFormat = InstructionCodec.getInstructionFormat(i3);
                    instructionHolder.address = i2;
                    instructionHolder.opcode = i3;
                    instructionHolder.index = i4;
                    instructionHolder.target = i6;
                    instructionHolder.literal = j;
                    instructionHolder.registerCount = 2;
                    instructionHolder.f432a = i7;
                    instructionHolder.b = i8;
                    instructionHolderArr[i2] = instructionHolder;
                }

                @Override
                public void visitZeroRegisterInsn(int i2, int i3, int i4, int i5, int i6, long j) {
                    InstructionHolder instructionHolder = new InstructionHolder();
                    instructionHolder.insnFormat = InstructionCodec.getInstructionFormat(i3);
                    instructionHolder.address = i2;
                    instructionHolder.opcode = i3;
                    instructionHolder.index = i4;
                    instructionHolder.target = i6;
                    instructionHolder.literal = j;
                    instructionHolderArr[i2] = instructionHolder;
                }
            });
            return instructionHolderArr;
        } catch (EOFException e) {
            throw new RuntimeException(e);
        }
    }

    public final boolean compare() {
        this.visitedInsnAddrPairs.clear();
        if (this.insnHolders1 == null && this.insnHolders2 == null) {
            return true;
        }
        if (this.insnHolders1 == null || this.insnHolders2 == null) {
            return false;
        }
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i < this.insnHolders1.length && i2 < this.insnHolders2.length) {
            InstructionHolder instructionHolder = null;
            InstructionHolder instructionHolder2 = null;
            while (true) {
                InstructionHolder[] instructionHolderArr = this.insnHolders1;
                if (i >= instructionHolderArr.length || instructionHolder2 != null) {
                    break;
                }
                instructionHolder2 = instructionHolderArr[i];
                i++;
            }
            if (instructionHolder2 == null) {
                break;
            }
            i3++;
            while (true) {
                InstructionHolder[] instructionHolderArr2 = this.insnHolders2;
                if (i2 >= instructionHolderArr2.length || instructionHolder != null) {
                    break;
                }
                instructionHolder = instructionHolderArr2[i2];
                i2++;
            }
            if (instructionHolder == null) {
                break;
            }
            i4++;
            if (instructionHolder2.opcode != instructionHolder.opcode) {
                if (instructionHolder2.opcode == 26 && instructionHolder.opcode == 27) {
                    if (!compareString(instructionHolder2.index, instructionHolder.index)) {
                        return false;
                    }
                } else if (instructionHolder2.opcode != 27 || instructionHolder.opcode != 26 || !compareString(instructionHolder2.index, instructionHolder.index)) {
                    return false;
                }
            } else if (!isSameInstruction(instructionHolder2.address, instructionHolder.address)) {
                return false;
            }
        }
        while (true) {
            InstructionHolder[] instructionHolderArr3 = this.insnHolders1;
            if (i < instructionHolderArr3.length) {
                int i5 = i + 1;
                if (instructionHolderArr3[i] != null) {
                    return false;
                }
                i = i5;
            } else {
                while (true) {
                    InstructionHolder[] instructionHolderArr4 = this.insnHolders2;
                    if (i2 >= instructionHolderArr4.length) {
                        return i3 == i4;
                    }
                    int i6 = i2 + 1;
                    if (instructionHolderArr4[i2] != null) {
                        return false;
                    }
                    i2 = i6;
                }
            }
        }
    }

    protected abstract boolean compareField(int i, int i2);

    protected abstract boolean compareMethod(int i, int i2);

    protected abstract boolean compareString(int i, int i2);

    protected abstract boolean compareType(int i, int i2);

    public boolean isSameInstruction(int i, int i2) {
        InstructionHolder instructionHolder = this.insnHolders1[i];
        InstructionHolder instructionHolder2 = this.insnHolders2[i2];
        if (instructionHolder == null && instructionHolder2 == null) {
            return true;
        }
        if (instructionHolder == null || instructionHolder2 == null || instructionHolder.opcode != instructionHolder2.opcode) {
            return false;
        }
        int i3 = instructionHolder.opcode;
        int i4 = instructionHolder.insnFormat;
        if (i4 != 2 && i4 != 11) {
            if (i4 != 13) {
                if (i4 != 15 && i4 != 21 && i4 != 7) {
                    if (i4 != 8) {
                        if (i4 != 18) {
                            if (i4 != 19 && i4 != 23 && i4 != 24) {
                                switch (i4) {
                                    case 26:
                                        FillArrayDataPayloadInstructionHolder fillArrayDataPayloadInstructionHolder = (FillArrayDataPayloadInstructionHolder) instructionHolder;
                                        FillArrayDataPayloadInstructionHolder fillArrayDataPayloadInstructionHolder2 = (FillArrayDataPayloadInstructionHolder) instructionHolder2;
                                        if (fillArrayDataPayloadInstructionHolder.elementWidth != fillArrayDataPayloadInstructionHolder2.elementWidth || fillArrayDataPayloadInstructionHolder.size != fillArrayDataPayloadInstructionHolder2.size) {
                                            return false;
                                        }
                                        int i5 = fillArrayDataPayloadInstructionHolder.elementWidth;
                                        if (i5 == 1) {
                                            return CompareUtils.uArrCompare((byte[]) fillArrayDataPayloadInstructionHolder.data, (byte[]) fillArrayDataPayloadInstructionHolder2.data) == 0;
                                        }
                                        if (i5 == 2) {
                                            return CompareUtils.uArrCompare((short[]) fillArrayDataPayloadInstructionHolder.data, (short[]) fillArrayDataPayloadInstructionHolder2.data) == 0;
                                        }
                                        if (i5 == 4) {
                                            return CompareUtils.uArrCompare((int[]) fillArrayDataPayloadInstructionHolder.data, (int[]) fillArrayDataPayloadInstructionHolder2.data) == 0;
                                        }
                                        if (i5 == 8) {
                                            return CompareUtils.sArrCompare((long[]) fillArrayDataPayloadInstructionHolder.data, (long[]) fillArrayDataPayloadInstructionHolder2.data) == 0;
                                        }
                                        throw new DexException("bogus element_width: " + Hex.u2(i5));
                                    case 27:
                                        PackedSwitchPayloadInsntructionHolder packedSwitchPayloadInsntructionHolder = (PackedSwitchPayloadInsntructionHolder) instructionHolder;
                                        PackedSwitchPayloadInsntructionHolder packedSwitchPayloadInsntructionHolder2 = (PackedSwitchPayloadInsntructionHolder) instructionHolder2;
                                        if (packedSwitchPayloadInsntructionHolder.firstKey != packedSwitchPayloadInsntructionHolder2.firstKey || packedSwitchPayloadInsntructionHolder.targets.length != packedSwitchPayloadInsntructionHolder2.targets.length) {
                                            return false;
                                        }
                                        int length = packedSwitchPayloadInsntructionHolder.targets.length;
                                        for (int i6 = 0; i6 < length; i6++) {
                                            if (!isSameInstruction(packedSwitchPayloadInsntructionHolder.targets[i6], packedSwitchPayloadInsntructionHolder2.targets[i6])) {
                                                return false;
                                            }
                                        }
                                        return true;
                                    case 28:
                                        SparseSwitchPayloadInsntructionHolder sparseSwitchPayloadInsntructionHolder = (SparseSwitchPayloadInsntructionHolder) instructionHolder;
                                        SparseSwitchPayloadInsntructionHolder sparseSwitchPayloadInsntructionHolder2 = (SparseSwitchPayloadInsntructionHolder) instructionHolder2;
                                        if (CompareUtils.uArrCompare(sparseSwitchPayloadInsntructionHolder.keys, sparseSwitchPayloadInsntructionHolder2.keys) != 0 || sparseSwitchPayloadInsntructionHolder.targets.length != sparseSwitchPayloadInsntructionHolder2.targets.length) {
                                            return false;
                                        }
                                        int length2 = sparseSwitchPayloadInsntructionHolder.targets.length;
                                        for (int i7 = 0; i7 < length2; i7++) {
                                            if (!isSameInstruction(sparseSwitchPayloadInsntructionHolder.targets[i7], sparseSwitchPayloadInsntructionHolder2.targets[i7])) {
                                                return false;
                                            }
                                        }
                                        return true;
                                    default:
                                        return instructionHolder.literal == instructionHolder2.literal && instructionHolder.registerCount == instructionHolder2.registerCount && instructionHolder.f432a == instructionHolder2.f432a && instructionHolder.b == instructionHolder2.b && instructionHolder.c == instructionHolder2.c && instructionHolder.d == instructionHolder2.d && instructionHolder.e == instructionHolder2.e;
                                }
                            }
                        }
                    }
                }
            }
            return compareIndex(i3, instructionHolder.index, instructionHolder2.index);
        }
        if (this.visitedInsnAddrPairs.add(i + "-" + i2)) {
            return isSameInstruction(instructionHolder.target, instructionHolder2.target);
        }
        return true;
    }
}
