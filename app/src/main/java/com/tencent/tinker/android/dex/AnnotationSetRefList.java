package com.tencent.tinker.android.dex;

import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.util.CompareUtils;
import java.util.Arrays;

public class AnnotationSetRefList extends TableOfContents.Section.Item<AnnotationSetRefList> {
    public int[] annotationSetRefItems;

    public AnnotationSetRefList(int i, int[] iArr) {
        super(i);
        this.annotationSetRefItems = iArr;
    }

    @Override
    public int byteCountInDex() {
        return (this.annotationSetRefItems.length + 1) * 4;
    }

    @Override
    public int compareTo(AnnotationSetRefList annotationSetRefList) {
        int length = this.annotationSetRefItems.length;
        int length2 = annotationSetRefList.annotationSetRefItems.length;
        if (length != length2) {
            return CompareUtils.uCompare(length, length2);
        }
        for (int i = 0; i < length; i++) {
            int[] iArr = this.annotationSetRefItems;
            int i2 = iArr[i];
            int[] iArr2 = annotationSetRefList.annotationSetRefItems;
            if (i2 != iArr2[i]) {
                return CompareUtils.uCompare(iArr[i], iArr2[i]);
            }
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof AnnotationSetRefList) && compareTo((AnnotationSetRefList) obj) == 0;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.annotationSetRefItems);
    }
}
