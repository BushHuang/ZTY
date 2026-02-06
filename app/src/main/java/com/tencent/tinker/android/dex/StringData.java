package com.tencent.tinker.android.dex;

import com.tencent.tinker.android.dex.TableOfContents;
import java.io.UTFDataFormatException;

public class StringData extends TableOfContents.Section.Item<StringData> {
    public String value;

    public StringData(int i, String str) {
        super(i);
        this.value = str;
    }

    @Override
    public int byteCountInDex() {
        try {
            return Leb128.unsignedLeb128Size(this.value.length()) + ((int) Mutf8.countBytes(this.value, false)) + 1;
        } catch (UTFDataFormatException e) {
            throw new DexException(e);
        }
    }

    @Override
    public int compareTo(StringData stringData) {
        return this.value.compareTo(stringData.value);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof StringData) && compareTo((StringData) obj) == 0;
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
}
