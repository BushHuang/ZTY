package com.tencent.tinker.commons.dexpatcher.algorithms.patch;

import com.tencent.tinker.android.dex.Dex;
import com.tencent.tinker.android.dex.FieldId;
import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.io.DexDataBuffer;
import com.tencent.tinker.commons.dexpatcher.struct.DexPatchFile;
import com.tencent.tinker.commons.dexpatcher.util.AbstractIndexMap;
import com.tencent.tinker.commons.dexpatcher.util.SparseIndexMap;

public class FieldIdSectionPatchAlgorithm extends DexSectionPatchAlgorithm<FieldId> {
    private Dex.Section patchedFieldIdSec;
    private TableOfContents.Section patchedFieldIdTocSec;

    public FieldIdSectionPatchAlgorithm(DexPatchFile dexPatchFile, Dex dex, Dex dex2, SparseIndexMap sparseIndexMap) {
        super(dexPatchFile, dex, sparseIndexMap);
        this.patchedFieldIdTocSec = null;
        this.patchedFieldIdSec = null;
        if (dex2 != null) {
            TableOfContents.Section section = dex2.getTableOfContents().fieldIds;
            this.patchedFieldIdTocSec = section;
            this.patchedFieldIdSec = dex2.openSection(section);
        }
    }

    @Override
    protected FieldId adjustItem(AbstractIndexMap abstractIndexMap, FieldId fieldId) {
        return abstractIndexMap.adjust(fieldId);
    }

    @Override
    protected int getItemSize(FieldId fieldId) {
        return fieldId.byteCountInDex();
    }

    @Override
    protected TableOfContents.Section getTocSection(Dex dex) {
        return dex.getTableOfContents().fieldIds;
    }

    @Override
    protected void markDeletedIndexOrOffset(SparseIndexMap sparseIndexMap, int i, int i2) {
        sparseIndexMap.markFieldIdDeleted(i);
    }

    @Override
    protected FieldId nextItem(DexDataBuffer dexDataBuffer) {
        return dexDataBuffer.readFieldId();
    }

    @Override
    protected void updateIndexOrOffset(SparseIndexMap sparseIndexMap, int i, int i2, int i3, int i4) {
        if (i != i3) {
            sparseIndexMap.mapFieldIds(i, i3);
        }
    }

    @Override
    protected int writePatchedItem(FieldId fieldId) {
        this.patchedFieldIdTocSec.size++;
        return this.patchedFieldIdSec.writeFieldId(fieldId);
    }
}
