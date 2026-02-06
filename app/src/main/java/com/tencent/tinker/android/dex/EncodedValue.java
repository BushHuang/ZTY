package com.tencent.tinker.android.dex;

import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.util.ByteInput;
import com.tencent.tinker.android.dex.util.CompareUtils;
import java.util.Arrays;

public final class EncodedValue extends TableOfContents.Section.Item<EncodedValue> {
    public byte[] data;

    public EncodedValue(int i, byte[] bArr) {
        super(i);
        this.data = bArr;
    }

    public ByteInput asByteInput() {
        return new ByteInput() {
            private int position = 0;

            @Override
            public byte readByte() {
                byte[] bArr = EncodedValue.this.data;
                int i = this.position;
                this.position = i + 1;
                return bArr[i];
            }
        };
    }

    @Override
    public int byteCountInDex() {
        return this.data.length * 1;
    }

    @Override
    public int compareTo(EncodedValue encodedValue) {
        return CompareUtils.uArrCompare(this.data, encodedValue.data);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof EncodedValue) && compareTo((EncodedValue) obj) == 0;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.data);
    }
}
