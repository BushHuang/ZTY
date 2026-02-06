package com.tencent.tinker.android.dex;

import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.util.CompareUtils;
import com.tencent.tinker.android.dex.util.HashCodeHelper;

public final class Annotation extends TableOfContents.Section.Item<Annotation> {
    public EncodedValue encodedAnnotation;
    public byte visibility;

    public Annotation(int i, byte b, EncodedValue encodedValue) {
        super(i);
        this.visibility = b;
        this.encodedAnnotation = encodedValue;
    }

    @Override
    public int byteCountInDex() {
        return this.encodedAnnotation.byteCountInDex() + 1;
    }

    @Override
    public int compareTo(Annotation annotation) {
        int iCompareTo = this.encodedAnnotation.compareTo(annotation.encodedAnnotation);
        return iCompareTo != 0 ? iCompareTo : CompareUtils.uCompare(this.visibility, annotation.visibility);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Annotation) && compareTo((Annotation) obj) == 0;
    }

    public EncodedValueReader getReader() {
        return new EncodedValueReader(this.encodedAnnotation, 29);
    }

    public int getTypeIndex() {
        EncodedValueReader reader = getReader();
        reader.readAnnotation();
        return reader.getAnnotationType();
    }

    @Override
    public int hashCode() {
        return HashCodeHelper.hash(Byte.valueOf(this.visibility), this.encodedAnnotation);
    }
}
