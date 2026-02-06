package com.tencent.tinker.commons.dexpatcher.algorithms.patch;

import com.tencent.tinker.android.dex.Dex;
import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.io.DexDataBuffer;
import com.tencent.tinker.commons.dexpatcher.struct.DexPatchFile;
import com.tencent.tinker.commons.dexpatcher.util.AbstractIndexMap;
import com.tencent.tinker.commons.dexpatcher.util.SparseIndexMap;
import java.lang.Comparable;
import java.util.Arrays;

public abstract class DexSectionPatchAlgorithm<T extends Comparable<T>> {
    protected final Dex oldDex;
    private final SparseIndexMap oldToPatchedIndexMap;
    protected final DexPatchFile patchFile;

    public DexSectionPatchAlgorithm(DexPatchFile dexPatchFile, Dex dex, SparseIndexMap sparseIndexMap) {
        this.patchFile = dexPatchFile;
        this.oldDex = dex;
        this.oldToPatchedIndexMap = sparseIndexMap;
    }

    private void doFullPatch(Dex.Section section, int i, int[] iArr, int[] iArr2, int[] iArr3) {
        int length = iArr.length;
        int length2 = iArr2.length;
        int length3 = iArr3.length;
        int i2 = (i + length2) - length;
        char c = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (true) {
            if (i7 >= i && i6 >= i2) {
                break;
            }
            if (i5 < length2 && iArr2[i5] == i6) {
                writePatchedItem(nextItem(this.patchFile.getBuffer()));
                i5++;
            } else if (i4 >= length3 || iArr3[i4] != i6) {
                if (Arrays.binarySearch(iArr, i7) >= 0) {
                    markDeletedIndexOrOffset(this.oldToPatchedIndexMap, i7, getItemOffsetOrIndex(i7, nextItem(section)));
                    i7++;
                    i3++;
                } else if (Arrays.binarySearch(iArr3, i7) >= 0) {
                    markDeletedIndexOrOffset(this.oldToPatchedIndexMap, i7, getItemOffsetOrIndex(i7, nextItem(section)));
                    i7++;
                } else if (i7 < i) {
                    Comparable comparableAdjustItem = adjustItem(this.oldToPatchedIndexMap, nextItem(section));
                    int i8 = i4;
                    int i9 = i7;
                    updateIndexOrOffset(this.oldToPatchedIndexMap, i9, getItemOffsetOrIndex(i7, comparableAdjustItem), i6, writePatchedItem(comparableAdjustItem));
                    i7++;
                    i6++;
                    i3 = i3;
                    i4 = i8;
                    i5 = i5;
                }
                c = 0;
            } else {
                writePatchedItem(nextItem(this.patchFile.getBuffer()));
                i4++;
            }
            i6++;
        }
        if (i5 == length2 && i3 == length && i4 == length3) {
            return;
        }
        Object[] objArr = new Object[6];
        objArr[c] = Integer.valueOf(i5);
        objArr[1] = Integer.valueOf(length2);
        objArr[2] = Integer.valueOf(i3);
        objArr[3] = Integer.valueOf(length);
        objArr[4] = Integer.valueOf(i4);
        objArr[5] = Integer.valueOf(length3);
        throw new IllegalStateException(String.format("bad patch operation sequence. addCounter: %d, addCount: %d, delCounter: %d, delCount: %d, replaceCounter: %d, replaceCount:%d", objArr));
    }

    private int getItemOffsetOrIndex(int i, T t) {
        return t instanceof TableOfContents.Section.Item ? ((TableOfContents.Section.Item) t).off : i;
    }

    private int[] readDeltaIndiciesOrOffsets(int i) {
        int[] iArr = new int[i];
        int sleb128 = 0;
        for (int i2 = 0; i2 < i; i2++) {
            sleb128 += this.patchFile.getBuffer().readSleb128();
            iArr[i2] = sleb128;
        }
        return iArr;
    }

    protected T adjustItem(AbstractIndexMap abstractIndexMap, T t) {
        return t;
    }

    public void execute() {
        Dex.Section section;
        int i;
        int[] deltaIndiciesOrOffsets = readDeltaIndiciesOrOffsets(this.patchFile.getBuffer().readUleb128());
        int[] deltaIndiciesOrOffsets2 = readDeltaIndiciesOrOffsets(this.patchFile.getBuffer().readUleb128());
        int[] deltaIndiciesOrOffsets3 = readDeltaIndiciesOrOffsets(this.patchFile.getBuffer().readUleb128());
        TableOfContents.Section tocSection = getTocSection(this.oldDex);
        if (tocSection.exists()) {
            Dex.Section sectionOpenSection = this.oldDex.openSection(tocSection);
            i = tocSection.size;
            section = sectionOpenSection;
        } else {
            section = null;
            i = 0;
        }
        doFullPatch(section, i, deltaIndiciesOrOffsets, deltaIndiciesOrOffsets2, deltaIndiciesOrOffsets3);
    }

    protected abstract int getItemSize(T t);

    protected abstract TableOfContents.Section getTocSection(Dex dex);

    protected void markDeletedIndexOrOffset(SparseIndexMap sparseIndexMap, int i, int i2) {
    }

    protected abstract T nextItem(DexDataBuffer dexDataBuffer);

    protected void updateIndexOrOffset(SparseIndexMap sparseIndexMap, int i, int i2, int i3, int i4) {
    }

    protected abstract int writePatchedItem(T t);
}
