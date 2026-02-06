package com.kwai.koom.javaoom.dump;

import android.os.Debug;
import java.io.IOException;

public class StandardHeapDumper implements HeapDumper {
    @Override
    public boolean dump(String str) throws IOException {
        try {
            Debug.dumpHprofData(str);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
