package com.tencent.tinker.android.dex;

import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.util.CompareUtils;
import com.tencent.tinker.android.dex.util.HashCodeHelper;

public final class FieldId extends TableOfContents.Section.Item<FieldId> {
    public int declaringClassIndex;
    public int nameIndex;
    public int typeIndex;

    public FieldId(int i, int i2, int i3, int i4) {
        super(i);
        this.declaringClassIndex = i2;
        this.typeIndex = i3;
        this.nameIndex = i4;
    }

    @Override
    public int byteCountInDex() {
        return 8;
    }

    @Override
    public int compareTo(FieldId fieldId) {
        int i = this.declaringClassIndex;
        int i2 = fieldId.declaringClassIndex;
        if (i != i2) {
            return CompareUtils.uCompare(i, i2);
        }
        int i3 = this.nameIndex;
        int i4 = fieldId.nameIndex;
        return i3 != i4 ? CompareUtils.uCompare(i3, i4) : CompareUtils.uCompare(this.typeIndex, fieldId.typeIndex);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof FieldId) && compareTo((FieldId) obj) == 0;
    }

    @Override
    public int hashCode() {
        return HashCodeHelper.hash(Integer.valueOf(this.declaringClassIndex), Integer.valueOf(this.typeIndex), Integer.valueOf(this.nameIndex));
    }
}
