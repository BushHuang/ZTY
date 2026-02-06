package com.tencent.tinker.android.dex;

import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.util.CompareUtils;
import com.tencent.tinker.android.dex.util.HashCodeHelper;

public final class MethodId extends TableOfContents.Section.Item<MethodId> {
    public int declaringClassIndex;
    public int nameIndex;
    public int protoIndex;

    public MethodId(int i, int i2, int i3, int i4) {
        super(i);
        this.declaringClassIndex = i2;
        this.protoIndex = i3;
        this.nameIndex = i4;
    }

    @Override
    public int byteCountInDex() {
        return 8;
    }

    @Override
    public int compareTo(MethodId methodId) {
        int i = this.declaringClassIndex;
        int i2 = methodId.declaringClassIndex;
        if (i != i2) {
            return CompareUtils.uCompare(i, i2);
        }
        int i3 = this.nameIndex;
        int i4 = methodId.nameIndex;
        return i3 != i4 ? CompareUtils.uCompare(i3, i4) : CompareUtils.uCompare(this.protoIndex, methodId.protoIndex);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof MethodId) && compareTo((MethodId) obj) == 0;
    }

    @Override
    public int hashCode() {
        return HashCodeHelper.hash(Integer.valueOf(this.declaringClassIndex), Integer.valueOf(this.protoIndex), Integer.valueOf(this.nameIndex));
    }
}
