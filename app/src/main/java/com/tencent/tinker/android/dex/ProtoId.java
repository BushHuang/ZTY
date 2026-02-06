package com.tencent.tinker.android.dex;

import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.util.CompareUtils;
import com.tencent.tinker.android.dex.util.HashCodeHelper;

public final class ProtoId extends TableOfContents.Section.Item<ProtoId> {
    public int parametersOffset;
    public int returnTypeIndex;
    public int shortyIndex;

    public ProtoId(int i, int i2, int i3, int i4) {
        super(i);
        this.shortyIndex = i2;
        this.returnTypeIndex = i3;
        this.parametersOffset = i4;
    }

    @Override
    public int byteCountInDex() {
        return 12;
    }

    @Override
    public int compareTo(ProtoId protoId) {
        int iUCompare = CompareUtils.uCompare(this.shortyIndex, protoId.shortyIndex);
        if (iUCompare != 0) {
            return iUCompare;
        }
        int iUCompare2 = CompareUtils.uCompare(this.returnTypeIndex, protoId.returnTypeIndex);
        return iUCompare2 != 0 ? iUCompare2 : CompareUtils.sCompare(this.parametersOffset, protoId.parametersOffset);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof ProtoId) && compareTo((ProtoId) obj) == 0;
    }

    @Override
    public int hashCode() {
        return HashCodeHelper.hash(Integer.valueOf(this.shortyIndex), Integer.valueOf(this.returnTypeIndex), Integer.valueOf(this.parametersOffset));
    }
}
