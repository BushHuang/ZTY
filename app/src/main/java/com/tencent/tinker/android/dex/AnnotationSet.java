package com.tencent.tinker.android.dex;

import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.util.CompareUtils;
import java.util.Arrays;

public class AnnotationSet extends TableOfContents.Section.Item<AnnotationSet> {
    public int[] annotationOffsets;

    public AnnotationSet(int i, int[] iArr) {
        super(i);
        this.annotationOffsets = iArr;
    }

    @Override
    public int byteCountInDex() {
        return (this.annotationOffsets.length + 1) * 4;
    }

    @Override
    public int compareTo(AnnotationSet annotationSet) {
        int length = this.annotationOffsets.length;
        int length2 = annotationSet.annotationOffsets.length;
        if (length != length2) {
            return CompareUtils.uCompare(length, length2);
        }
        for (int i = 0; i < length; i++) {
            int[] iArr = this.annotationOffsets;
            int i2 = iArr[i];
            int[] iArr2 = annotationSet.annotationOffsets;
            if (i2 != iArr2[i]) {
                return CompareUtils.uCompare(iArr[i], iArr2[i]);
            }
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof AnnotationSet) && compareTo((AnnotationSet) obj) == 0;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.annotationOffsets);
    }
}
