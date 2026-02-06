package com.tencent.tinker.commons.dexpatcher.algorithms.patch;

import com.tencent.tinker.android.dex.Dex;
import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.TypeList;
import com.tencent.tinker.android.dex.io.DexDataBuffer;
import com.tencent.tinker.commons.dexpatcher.struct.DexPatchFile;
import com.tencent.tinker.commons.dexpatcher.util.AbstractIndexMap;
import com.tencent.tinker.commons.dexpatcher.util.SparseIndexMap;

public class TypeListSectionPatchAlgorithm extends DexSectionPatchAlgorithm<TypeList> {
    private Dex.Section patchedTypeListSec;
    private TableOfContents.Section patchedTypeListTocSec;

    public TypeListSectionPatchAlgorithm(DexPatchFile dexPatchFile, Dex dex, Dex dex2, SparseIndexMap sparseIndexMap) {
        super(dexPatchFile, dex, sparseIndexMap);
        this.patchedTypeListTocSec = null;
        this.patchedTypeListSec = null;
        if (dex2 != null) {
            TableOfContents.Section section = dex2.getTableOfContents().typeLists;
            this.patchedTypeListTocSec = section;
            this.patchedTypeListSec = dex2.openSection(section);
        }
    }

    @Override
    protected TypeList adjustItem(AbstractIndexMap abstractIndexMap, TypeList typeList) {
        return abstractIndexMap.adjust(typeList);
    }

    @Override
    protected int getItemSize(TypeList typeList) {
        return typeList.byteCountInDex();
    }

    @Override
    protected TableOfContents.Section getTocSection(Dex dex) {
        return dex.getTableOfContents().typeLists;
    }

    @Override
    protected void markDeletedIndexOrOffset(SparseIndexMap sparseIndexMap, int i, int i2) {
        sparseIndexMap.markTypeListDeleted(i2);
    }

    @Override
    protected TypeList nextItem(DexDataBuffer dexDataBuffer) {
        return dexDataBuffer.readTypeList();
    }

    @Override
    protected void updateIndexOrOffset(SparseIndexMap sparseIndexMap, int i, int i2, int i3, int i4) {
        if (i2 != i4) {
            sparseIndexMap.mapTypeListOffset(i2, i4);
        }
    }

    @Override
    protected int writePatchedItem(TypeList typeList) {
        this.patchedTypeListTocSec.size++;
        return this.patchedTypeListSec.writeTypeList(typeList);
    }
}
