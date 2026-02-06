package com.tencent.tinker.commons.dexpatcher.algorithms.patch;

import com.tencent.tinker.android.dex.ClassDef;
import com.tencent.tinker.android.dex.Dex;
import com.tencent.tinker.android.dex.TableOfContents;
import com.tencent.tinker.android.dex.io.DexDataBuffer;
import com.tencent.tinker.commons.dexpatcher.struct.DexPatchFile;
import com.tencent.tinker.commons.dexpatcher.util.AbstractIndexMap;
import com.tencent.tinker.commons.dexpatcher.util.SparseIndexMap;

public class ClassDefSectionPatchAlgorithm extends DexSectionPatchAlgorithm<ClassDef> {
    private Dex.Section patchedClassDefSec;
    private TableOfContents.Section patchedClassDefTocSec;

    public ClassDefSectionPatchAlgorithm(DexPatchFile dexPatchFile, Dex dex, Dex dex2, SparseIndexMap sparseIndexMap) {
        super(dexPatchFile, dex, sparseIndexMap);
        this.patchedClassDefTocSec = null;
        this.patchedClassDefSec = null;
        if (dex2 != null) {
            TableOfContents.Section section = dex2.getTableOfContents().classDefs;
            this.patchedClassDefTocSec = section;
            this.patchedClassDefSec = dex2.openSection(section);
        }
    }

    @Override
    protected ClassDef adjustItem(AbstractIndexMap abstractIndexMap, ClassDef classDef) {
        return abstractIndexMap.adjust(classDef);
    }

    @Override
    protected int getItemSize(ClassDef classDef) {
        return classDef.byteCountInDex();
    }

    @Override
    protected TableOfContents.Section getTocSection(Dex dex) {
        return dex.getTableOfContents().classDefs;
    }

    @Override
    protected ClassDef nextItem(DexDataBuffer dexDataBuffer) {
        return dexDataBuffer.readClassDef();
    }

    @Override
    protected int writePatchedItem(ClassDef classDef) {
        this.patchedClassDefTocSec.size++;
        return this.patchedClassDefSec.writeClassDef(classDef);
    }
}
