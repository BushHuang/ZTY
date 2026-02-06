package com.tencent.tinker.commons.dexpatcher.algorithms.patch;

import com.tencent.tinker.android.dex.AnnotationSet;
import com.tencent.tinker.android.dex.Dex;
import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.io.DexDataBuffer;
import com.tencent.tinker.commons.dexpatcher.struct.DexPatchFile;
import com.tencent.tinker.commons.dexpatcher.util.AbstractIndexMap;
import com.tencent.tinker.commons.dexpatcher.util.SparseIndexMap;

public class AnnotationSetSectionPatchAlgorithm extends DexSectionPatchAlgorithm<AnnotationSet> {
    private Dex.Section patchedAnnotationSetSec;
    private TableOfContents.Section patchedAnnotationSetTocSec;

    public AnnotationSetSectionPatchAlgorithm(DexPatchFile dexPatchFile, Dex dex, Dex dex2, SparseIndexMap sparseIndexMap) {
        super(dexPatchFile, dex, sparseIndexMap);
        this.patchedAnnotationSetTocSec = null;
        this.patchedAnnotationSetSec = null;
        if (dex2 != null) {
            TableOfContents.Section section = dex2.getTableOfContents().annotationSets;
            this.patchedAnnotationSetTocSec = section;
            this.patchedAnnotationSetSec = dex2.openSection(section);
        }
    }

    @Override
    protected AnnotationSet adjustItem(AbstractIndexMap abstractIndexMap, AnnotationSet annotationSet) {
        return abstractIndexMap.adjust(annotationSet);
    }

    @Override
    protected int getItemSize(AnnotationSet annotationSet) {
        return annotationSet.byteCountInDex();
    }

    @Override
    protected TableOfContents.Section getTocSection(Dex dex) {
        return dex.getTableOfContents().annotationSets;
    }

    @Override
    protected void markDeletedIndexOrOffset(SparseIndexMap sparseIndexMap, int i, int i2) {
        sparseIndexMap.markAnnotationSetDeleted(i2);
    }

    @Override
    protected AnnotationSet nextItem(DexDataBuffer dexDataBuffer) {
        return dexDataBuffer.readAnnotationSet();
    }

    @Override
    protected void updateIndexOrOffset(SparseIndexMap sparseIndexMap, int i, int i2, int i3, int i4) {
        if (i2 != i4) {
            sparseIndexMap.mapAnnotationSetOffset(i2, i4);
        }
    }

    @Override
    protected int writePatchedItem(AnnotationSet annotationSet) {
        this.patchedAnnotationSetTocSec.size++;
        return this.patchedAnnotationSetSec.writeAnnotationSet(annotationSet);
    }
}
