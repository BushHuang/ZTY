package com.tencent.tinker.android.dex;

import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.util.CompareUtils;
import java.util.Arrays;

public final class TypeList extends TableOfContents.Section.Item<TypeList> {
    public static final TypeList EMPTY = new TypeList(0, Dex.EMPTY_SHORT_ARRAY);
    public short[] types;

    public TypeList(int i, short[] sArr) {
        super(i);
        this.types = sArr;
    }

    @Override
    public int byteCountInDex() {
        return (this.types.length * 2) + 4;
    }

    @Override
    public int compareTo(TypeList typeList) {
        return CompareUtils.uArrCompare(this.types, typeList.types);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof TypeList) && compareTo((TypeList) obj) == 0;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.types);
    }
}
