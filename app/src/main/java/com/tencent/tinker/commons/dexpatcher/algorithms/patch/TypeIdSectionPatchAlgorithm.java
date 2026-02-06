package com.tencent.tinker.commons.dexpatcher.algorithms.patch;

import com.tencent.tinker.android.dex.Dex;
import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.io.DexDataBuffer;
import com.tencent.tinker.commons.dexpatcher.struct.DexPatchFile;
import com.tencent.tinker.commons.dexpatcher.util.AbstractIndexMap;
import com.tencent.tinker.commons.dexpatcher.util.SparseIndexMap;

public class TypeIdSectionPatchAlgorithm extends DexSectionPatchAlgorithm<Integer> {
    private Dex.Section patchedTypeIdSec;
    private TableOfContents.Section patchedTypeIdTocSec;

    public TypeIdSectionPatchAlgorithm(DexPatchFile dexPatchFile, Dex dex, Dex dex2, SparseIndexMap sparseIndexMap) {
        super(dexPatchFile, dex, sparseIndexMap);
        this.patchedTypeIdTocSec = null;
        this.patchedTypeIdSec = null;
        if (dex2 != null) {
            TableOfContents.Section section = dex2.getTableOfContents().typeIds;
            this.patchedTypeIdTocSec = section;
            this.patchedTypeIdSec = dex2.openSection(section);
        }
    }

    @Override
    protected Integer adjustItem(AbstractIndexMap abstractIndexMap, Integer num) {
        return Integer.valueOf(abstractIndexMap.adjustStringIndex(num.intValue()));
    }

    @Override
    protected int getItemSize(Integer num) {
        return 4;
    }

    @Override
    protected TableOfContents.Section getTocSection(Dex dex) {
        return dex.getTableOfContents().typeIds;
    }

    @Override
    protected void markDeletedIndexOrOffset(SparseIndexMap sparseIndexMap, int i, int i2) {
        sparseIndexMap.markTypeIdDeleted(i);
    }

    @Override
    protected Integer nextItem(DexDataBuffer dexDataBuffer) {
        return Integer.valueOf(dexDataBuffer.readInt());
    }

    @Override
    protected void updateIndexOrOffset(SparseIndexMap sparseIndexMap, int i, int i2, int i3, int i4) {
        if (i != i3) {
            sparseIndexMap.mapTypeIds(i, i3);
        }
    }

    @Override
    protected int writePatchedItem(Integer num) {
        int iPosition = this.patchedTypeIdSec.position();
        this.patchedTypeIdSec.writeInt(num.intValue());
        this.patchedTypeIdTocSec.size++;
        return iPosition;
    }
}
