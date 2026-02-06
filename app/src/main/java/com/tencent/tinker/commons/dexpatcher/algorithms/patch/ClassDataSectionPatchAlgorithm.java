package com.tencent.tinker.commons.dexpatcher.algorithms.patch;

import com.tencent.tinker.android.dex.ClassData;
import com.tencent.tinker.android.dex.Dex;
import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.io.DexDataBuffer;
import com.tencent.tinker.commons.dexpatcher.struct.DexPatchFile;
import com.tencent.tinker.commons.dexpatcher.util.AbstractIndexMap;
import com.tencent.tinker.commons.dexpatcher.util.SparseIndexMap;

public class ClassDataSectionPatchAlgorithm extends DexSectionPatchAlgorithm<ClassData> {
    private Dex.Section patchedClassDataSec;
    private TableOfContents.Section patchedClassDataTocSec;

    public ClassDataSectionPatchAlgorithm(DexPatchFile dexPatchFile, Dex dex, Dex dex2, SparseIndexMap sparseIndexMap) {
        super(dexPatchFile, dex, sparseIndexMap);
        this.patchedClassDataTocSec = null;
        this.patchedClassDataSec = null;
        if (dex2 != null) {
            TableOfContents.Section section = dex2.getTableOfContents().classDatas;
            this.patchedClassDataTocSec = section;
            this.patchedClassDataSec = dex2.openSection(section);
        }
    }

    @Override
    protected ClassData adjustItem(AbstractIndexMap abstractIndexMap, ClassData classData) {
        return abstractIndexMap.adjust(classData);
    }

    @Override
    protected int getItemSize(ClassData classData) {
        return classData.byteCountInDex();
    }

    @Override
    protected TableOfContents.Section getTocSection(Dex dex) {
        return dex.getTableOfContents().classDatas;
    }

    @Override
    protected void markDeletedIndexOrOffset(SparseIndexMap sparseIndexMap, int i, int i2) {
        sparseIndexMap.markClassDataDeleted(i2);
    }

    @Override
    protected ClassData nextItem(DexDataBuffer dexDataBuffer) {
        return dexDataBuffer.readClassData();
    }

    @Override
    protected void updateIndexOrOffset(SparseIndexMap sparseIndexMap, int i, int i2, int i3, int i4) {
        if (i2 != i4) {
            sparseIndexMap.mapClassDataOffset(i2, i4);
        }
    }

    @Override
    protected int writePatchedItem(ClassData classData) {
        this.patchedClassDataTocSec.size++;
        return this.patchedClassDataSec.writeClassData(classData);
    }
}
