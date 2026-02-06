package com.kwai.koom.javaoom.dump;

import android.os.Debug;
import com.kwai.koom.javaoom.KOOMEnableChecker;
import com.kwai.koom.javaoom.common.KGlobalConfig;
import com.kwai.koom.javaoom.common.KLog;
import java.io.IOException;

public class StripHprofHeapDumper implements HeapDumper {
    private static final String TAG = "StripHprofHeapDumper";
    private boolean soLoaded;

    public StripHprofHeapDumper() {
        boolean zLoadLib = KGlobalConfig.getSoLoader().loadLib("koom-java");
        this.soLoaded = zLoadLib;
        if (zLoadLib) {
            initStripDump();
        }
    }

    @Override
    public boolean dump(String str) throws IOException {
        KLog.i("StripHprofHeapDumper", "dump " + str);
        if (!this.soLoaded) {
            KLog.e("StripHprofHeapDumper", "dump failed caused by so not loaded!");
            return false;
        }
        if (!KOOMEnableChecker.get().isVersionPermit()) {
            KLog.e("StripHprofHeapDumper", "dump failed caused by version net permitted!");
            return false;
        }
        try {
            hprofName(str);
            Debug.dumpHprofData(str);
            return isStripSuccess();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public native void hprofName(String str);

    public native void initStripDump();

    public native boolean isStripSuccess();
}
