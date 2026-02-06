package com.tencent.tinker.commons.dexpatcher.algorithms.patch;

import com.tencent.tinker.android.dex.Dex;
import com.tencent.tinker.android.dex.MethodId;
import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.io.DexDataBuffer;
import com.tencent.tinker.commons.dexpatcher.struct.DexPatchFile;
import com.tencent.tinker.commons.dexpatcher.util.AbstractIndexMap;
import com.tencent.tinker.commons.dexpatcher.util.SparseIndexMap;

public class MethodIdSectionPatchAlgorithm extends DexSectionPatchAlgorithm<MethodId> {
    private Dex.Section patchedMethodIdSec;
    private TableOfContents.Section patchedMethodIdTocSec;

    public MethodIdSectionPatchAlgorithm(DexPatchFile dexPatchFile, Dex dex, Dex dex2, SparseIndexMap sparseIndexMap) {
        super(dexPatchFile, dex, sparseIndexMap);
        this.patchedMethodIdTocSec = null;
        this.patchedMethodIdSec = null;
        if (dex2 != null) {
            TableOfContents.Section section = dex2.getTableOfContents().methodIds;
            this.patchedMethodIdTocSec = section;
            this.patchedMethodIdSec = dex2.openSection(section);
        }
    }

    @Override
    protected MethodId adjustItem(AbstractIndexMap abstractIndexMap, MethodId methodId) {
        return abstractIndexMap.adjust(methodId);
    }

    @Override
    protected int getItemSize(MethodId methodId) {
        return methodId.byteCountInDex();
    }

    @Override
    protected TableOfContents.Section getTocSection(Dex dex) {
        return dex.getTableOfContents().methodIds;
    }

    @Override
    protected void markDeletedIndexOrOffset(SparseIndexMap sparseIndexMap, int i, int i2) {
        sparseIndexMap.markMethodIdDeleted(i);
    }

    @Override
    protected MethodId nextItem(DexDataBuffer dexDataBuffer) {
        return dexDataBuffer.readMethodId();
    }

    @Override
    protected void updateIndexOrOffset(SparseIndexMap sparseIndexMap, int i, int i2, int i3, int i4) {
        if (i != i3) {
            sparseIndexMap.mapMethodIds(i, i3);
        }
    }

    @Override
    protected int writePatchedItem(MethodId methodId) {
        this.patchedMethodIdTocSec.size++;
        return this.patchedMethodIdSec.writeMethodId(methodId);
    }
}
