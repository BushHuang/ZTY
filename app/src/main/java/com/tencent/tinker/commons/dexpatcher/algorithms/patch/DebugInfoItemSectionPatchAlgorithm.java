package com.tencent.tinker.commons.dexpatcher.algorithms.patch;

import com.tencent.tinker.android.dex.DebugInfoItem;
import com.tencent.tinker.android.dex.Dex;
import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.io.DexDataBuffer;
import com.tencent.tinker.commons.dexpatcher.struct.DexPatchFile;
import com.tencent.tinker.commons.dexpatcher.util.AbstractIndexMap;
import com.tencent.tinker.commons.dexpatcher.util.SparseIndexMap;

public class DebugInfoItemSectionPatchAlgorithm extends DexSectionPatchAlgorithm<DebugInfoItem> {
    private Dex.Section patchedDebugInfoItemSec;
    private TableOfContents.Section patchedDebugInfoItemTocSec;

    public DebugInfoItemSectionPatchAlgorithm(DexPatchFile dexPatchFile, Dex dex, Dex dex2, SparseIndexMap sparseIndexMap) {
        super(dexPatchFile, dex, sparseIndexMap);
        this.patchedDebugInfoItemTocSec = null;
        this.patchedDebugInfoItemSec = null;
        if (dex2 != null) {
            TableOfContents.Section section = dex2.getTableOfContents().debugInfos;
            this.patchedDebugInfoItemTocSec = section;
            this.patchedDebugInfoItemSec = dex2.openSection(section);
        }
    }

    @Override
    protected DebugInfoItem adjustItem(AbstractIndexMap abstractIndexMap, DebugInfoItem debugInfoItem) {
        return abstractIndexMap.adjust(debugInfoItem);
    }

    @Override
    protected int getItemSize(DebugInfoItem debugInfoItem) {
        return debugInfoItem.byteCountInDex();
    }

    @Override
    protected TableOfContents.Section getTocSection(Dex dex) {
        return dex.getTableOfContents().debugInfos;
    }

    @Override
    protected void markDeletedIndexOrOffset(SparseIndexMap sparseIndexMap, int i, int i2) {
        sparseIndexMap.markDebugInfoItemDeleted(i2);
    }

    @Override
    protected DebugInfoItem nextItem(DexDataBuffer dexDataBuffer) {
        return dexDataBuffer.readDebugInfoItem();
    }

    @Override
    protected void updateIndexOrOffset(SparseIndexMap sparseIndexMap, int i, int i2, int i3, int i4) {
        if (i2 != i4) {
            sparseIndexMap.mapDebugInfoItemOffset(i2, i4);
        }
    }

    @Override
    protected int writePatchedItem(DebugInfoItem debugInfoItem) {
        this.patchedDebugInfoItemTocSec.size++;
        return this.patchedDebugInfoItemSec.writeDebugInfoItem(debugInfoItem);
    }
}
