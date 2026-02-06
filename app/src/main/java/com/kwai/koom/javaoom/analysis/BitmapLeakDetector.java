package com.kwai.koom.javaoom.analysis;

import android.graphics.Bitmap;
import com.kwai.koom.javaoom.common.KLog;
import kshark.HeapField;
import kshark.HeapGraph;
import kshark.HeapObject;

public class BitmapLeakDetector extends LeakDetector {
    static final boolean $assertionsDisabled = false;
    private static final String BITMAP_CLASS_NAME = "android.graphics.Bitmap";
    private static final String TAG = "BitmapLeakDetector";
    private long bitmapClassId;
    private ClassCounter bitmapCounter;

    private BitmapLeakDetector() {
    }

    public BitmapLeakDetector(HeapGraph heapGraph) {
        this.bitmapClassId = heapGraph.findClassByName("android.graphics.Bitmap").getObjectId();
        this.bitmapCounter = new ClassCounter();
    }

    @Override
    public long classId() {
        return this.bitmapClassId;
    }

    @Override
    public String className() {
        return "android.graphics.Bitmap";
    }

    @Override
    public Class<?> clazz() {
        return Bitmap.class;
    }

    @Override
    public ClassCounter instanceCount() {
        return this.bitmapCounter;
    }

    @Override
    public boolean isLeak(HeapObject.HeapInstance heapInstance) {
        if (this.VERBOSE_LOG) {
            KLog.i("BitmapLeakDetector", "run isLeak");
        }
        this.bitmapCounter.instancesCount++;
        HeapField heapField = heapInstance.get("android.graphics.Bitmap", "mWidth");
        HeapField heapField2 = heapInstance.get("android.graphics.Bitmap", "mHeight");
        if (heapField2.getValue().getAsInt() == null || heapField.getValue().getAsInt() == null) {
            KLog.e("BitmapLeakDetector", "ABNORMAL fieldWidth or fieldHeight is null");
            return false;
        }
        int iIntValue = heapField.getValue().getAsInt().intValue();
        int iIntValue2 = heapField2.getValue().getAsInt().intValue();
        boolean z = iIntValue * iIntValue2 >= 1049088;
        if (z) {
            KLog.e("BitmapLeakDetector", "bitmap leak : " + heapInstance.getInstanceClassName() + " width:" + iIntValue + " height:" + iIntValue2);
            ClassCounter classCounter = this.bitmapCounter;
            classCounter.leakInstancesCount = classCounter.leakInstancesCount + 1;
        }
        return z;
    }

    @Override
    public String leakReason() {
        return "Bitmap Size";
    }
}
