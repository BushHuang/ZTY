package com.tencent.tinker.commons.dexpatcher.algorithms.patch;

import com.tencent.tinker.android.dex.Annotation;
import com.tencent.tinker.android.dex.Dex;
import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.io.DexDataBuffer;
import com.tencent.tinker.commons.dexpatcher.struct.DexPatchFile;
import com.tencent.tinker.commons.dexpatcher.util.AbstractIndexMap;
import com.tencent.tinker.commons.dexpatcher.util.SparseIndexMap;

public class AnnotationSectionPatchAlgorithm extends DexSectionPatchAlgorithm<Annotation> {
    private Dex.Section patchedAnnotationSec;
    private TableOfContents.Section patchedAnnotationTocSec;

    public AnnotationSectionPatchAlgorithm(DexPatchFile dexPatchFile, Dex dex, Dex dex2, SparseIndexMap sparseIndexMap) {
        super(dexPatchFile, dex, sparseIndexMap);
        this.patchedAnnotationTocSec = null;
        this.patchedAnnotationSec = null;
        if (dex2 != null) {
            TableOfContents.Section section = dex2.getTableOfContents().annotations;
            this.patchedAnnotationTocSec = section;
            this.patchedAnnotationSec = dex2.openSection(section);
        }
    }

    @Override
    protected Annotation adjustItem(AbstractIndexMap abstractIndexMap, Annotation annotation) {
        return abstractIndexMap.adjust(annotation);
    }

    @Override
    protected int getItemSize(Annotation annotation) {
        return annotation.byteCountInDex();
    }

    @Override
    protected TableOfContents.Section getTocSection(Dex dex) {
        return dex.getTableOfContents().annotations;
    }

    @Override
    protected void markDeletedIndexOrOffset(SparseIndexMap sparseIndexMap, int i, int i2) {
        sparseIndexMap.markAnnotationDeleted(i2);
    }

    @Override
    protected Annotation nextItem(DexDataBuffer dexDataBuffer) {
        return dexDataBuffer.readAnnotation();
    }

    @Override
    protected void updateIndexOrOffset(SparseIndexMap sparseIndexMap, int i, int i2, int i3, int i4) {
        if (i2 != i4) {
            sparseIndexMap.mapAnnotationOffset(i2, i4);
        }
    }

    @Override
    protected int writePatchedItem(Annotation annotation) {
        this.patchedAnnotationTocSec.size++;
        return this.patchedAnnotationSec.writeAnnotation(annotation);
    }
}
