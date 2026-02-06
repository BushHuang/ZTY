package com.tencent.tinker.commons.dexpatcher.algorithms.patch;

import com.tencent.tinker.android.dex.Dex;
import com.tencent.tinker.android.dex.EncodedValue;
import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.io.DexDataBuffer;
import com.tencent.tinker.commons.dexpatcher.struct.DexPatchFile;
import com.tencent.tinker.commons.dexpatcher.util.AbstractIndexMap;
import com.tencent.tinker.commons.dexpatcher.util.SparseIndexMap;

public class StaticValueSectionPatchAlgorithm extends DexSectionPatchAlgorithm<EncodedValue> {
    private Dex.Section patchedEncodedValueSec;
    private TableOfContents.Section patchedEncodedValueTocSec;

    public StaticValueSectionPatchAlgorithm(DexPatchFile dexPatchFile, Dex dex, Dex dex2, SparseIndexMap sparseIndexMap) {
        super(dexPatchFile, dex, sparseIndexMap);
        this.patchedEncodedValueTocSec = null;
        this.patchedEncodedValueSec = null;
        if (dex2 != null) {
            TableOfContents.Section section = dex2.getTableOfContents().encodedArrays;
            this.patchedEncodedValueTocSec = section;
            this.patchedEncodedValueSec = dex2.openSection(section);
        }
    }

    @Override
    protected EncodedValue adjustItem(AbstractIndexMap abstractIndexMap, EncodedValue encodedValue) {
        return abstractIndexMap.adjust(encodedValue);
    }

    @Override
    protected int getItemSize(EncodedValue encodedValue) {
        return encodedValue.byteCountInDex();
    }

    @Override
    protected TableOfContents.Section getTocSection(Dex dex) {
        return dex.getTableOfContents().encodedArrays;
    }

    @Override
    protected void markDeletedIndexOrOffset(SparseIndexMap sparseIndexMap, int i, int i2) {
        sparseIndexMap.markStaticValuesDeleted(i2);
    }

    @Override
    protected EncodedValue nextItem(DexDataBuffer dexDataBuffer) {
        return dexDataBuffer.readEncodedArray();
    }

    @Override
    protected void updateIndexOrOffset(SparseIndexMap sparseIndexMap, int i, int i2, int i3, int i4) {
        if (i2 != i4) {
            sparseIndexMap.mapStaticValuesOffset(i2, i4);
        }
    }

    @Override
    protected int writePatchedItem(EncodedValue encodedValue) {
        this.patchedEncodedValueTocSec.size++;
        return this.patchedEncodedValueSec.writeEncodedArray(encodedValue);
    }
}
