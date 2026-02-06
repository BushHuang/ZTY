package com.tencent.tinker.android.dex;

import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.util.CompareUtils;
import com.tencent.tinker.android.dex.util.HashCodeHelper;

public final class ClassDef extends TableOfContents.Section.Item<ClassDef> {
    public static final int NO_INDEX = -1;
    public static final int NO_OFFSET = 0;
    public int accessFlags;
    public int annotationsOffset;
    public int classDataOffset;
    public int interfacesOffset;
    public int sourceFileIndex;
    public int staticValuesOffset;
    public int supertypeIndex;
    public int typeIndex;

    public ClassDef(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        super(i);
        this.typeIndex = i2;
        this.accessFlags = i3;
        this.supertypeIndex = i4;
        this.interfacesOffset = i5;
        this.sourceFileIndex = i6;
        this.annotationsOffset = i7;
        this.classDataOffset = i8;
        this.staticValuesOffset = i9;
    }

    @Override
    public int byteCountInDex() {
        return 32;
    }

    @Override
    public int compareTo(ClassDef classDef) {
        int iUCompare = CompareUtils.uCompare(this.typeIndex, classDef.typeIndex);
        if (iUCompare != 0) {
            return iUCompare;
        }
        int iSCompare = CompareUtils.sCompare(this.accessFlags, classDef.accessFlags);
        if (iSCompare != 0) {
            return iSCompare;
        }
        int iUCompare2 = CompareUtils.uCompare(this.supertypeIndex, classDef.supertypeIndex);
        if (iUCompare2 != 0) {
            return iUCompare2;
        }
        int iSCompare2 = CompareUtils.sCompare(this.interfacesOffset, classDef.interfacesOffset);
        if (iSCompare2 != 0) {
            return iSCompare2;
        }
        int iUCompare3 = CompareUtils.uCompare(this.sourceFileIndex, classDef.sourceFileIndex);
        if (iUCompare3 != 0) {
            return iUCompare3;
        }
        int iSCompare3 = CompareUtils.sCompare(this.annotationsOffset, classDef.annotationsOffset);
        if (iSCompare3 != 0) {
            return iSCompare3;
        }
        int iSCompare4 = CompareUtils.sCompare(this.classDataOffset, classDef.classDataOffset);
        return iSCompare4 != 0 ? iSCompare4 : CompareUtils.sCompare(this.staticValuesOffset, classDef.staticValuesOffset);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof ClassDef) && compareTo((ClassDef) obj) == 0;
    }

    @Override
    public int hashCode() {
        return HashCodeHelper.hash(Integer.valueOf(this.typeIndex), Integer.valueOf(this.accessFlags), Integer.valueOf(this.supertypeIndex), Integer.valueOf(this.interfacesOffset), Integer.valueOf(this.sourceFileIndex), Integer.valueOf(this.annotationsOffset), Integer.valueOf(this.classDataOffset), Integer.valueOf(this.staticValuesOffset));
    }
}
