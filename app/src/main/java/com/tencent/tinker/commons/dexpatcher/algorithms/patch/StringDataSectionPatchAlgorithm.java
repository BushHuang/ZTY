package com.tencent.tinker.commons.dexpatcher.algorithms.patch;

import com.tencent.tinker.android.dex.Dex;
import com.tencent.tinker.android.dex.StringData;
import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.io.DexDataBuffer;
import com.tencent.tinker.commons.dexpatcher.struct.DexPatchFile;
import com.tencent.tinker.commons.dexpatcher.util.SparseIndexMap;

public class StringDataSectionPatchAlgorithm extends DexSectionPatchAlgorithm<StringData> {
    private Dex.Section patchedStringDataSec;
    private TableOfContents.Section patchedStringDataTocSec;
    private Dex.Section patchedStringIdSec;
    private TableOfContents.Section patchedStringIdTocSec;

    public StringDataSectionPatchAlgorithm(DexPatchFile dexPatchFile, Dex dex, Dex dex2, SparseIndexMap sparseIndexMap) {
        super(dexPatchFile, dex, sparseIndexMap);
        this.patchedStringDataTocSec = null;
        this.patchedStringIdTocSec = null;
        this.patchedStringDataSec = null;
        this.patchedStringIdSec = null;
        if (dex2 != null) {
            this.patchedStringDataTocSec = dex2.getTableOfContents().stringDatas;
            this.patchedStringIdTocSec = dex2.getTableOfContents().stringIds;
            this.patchedStringDataSec = dex2.openSection(this.patchedStringDataTocSec);
            this.patchedStringIdSec = dex2.openSection(this.patchedStringIdTocSec);
        }
    }

    @Override
    protected int getItemSize(StringData stringData) {
        return stringData.byteCountInDex();
    }

    @Override
    protected TableOfContents.Section getTocSection(Dex dex) {
        return dex.getTableOfContents().stringDatas;
    }

    @Override
    protected void markDeletedIndexOrOffset(SparseIndexMap sparseIndexMap, int i, int i2) {
        sparseIndexMap.markStringIdDeleted(i);
    }

    @Override
    protected StringData nextItem(DexDataBuffer dexDataBuffer) {
        return dexDataBuffer.readStringData();
    }

    @Override
    protected void updateIndexOrOffset(SparseIndexMap sparseIndexMap, int i, int i2, int i3, int i4) {
        if (i != i3) {
            sparseIndexMap.mapStringIds(i, i3);
        }
    }

    @Override
    protected int writePatchedItem(StringData stringData) {
        int iWriteStringData = this.patchedStringDataSec.writeStringData(stringData);
        this.patchedStringIdSec.writeInt(iWriteStringData);
        this.patchedStringDataTocSec.size++;
        this.patchedStringIdTocSec.size++;
        return iWriteStringData;
    }
}
