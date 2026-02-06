package com.kwai.koom.javaoom.dump;

import com.kwai.koom.javaoom.common.KLog;
import java.io.IOException;

public class ForkAndStripHeapDumper extends ForkJvmHeapDumper {
    private static final String TAG = "ForkAndStripHeapDumper";
    StripHprofHeapDumper stripHprofHeapDumper;

    @Override
    protected void beforeDumpInForkedProcess(String str) {
        StripHprofHeapDumper stripHprofHeapDumper = new StripHprofHeapDumper();
        this.stripHprofHeapDumper = stripHprofHeapDumper;
        stripHprofHeapDumper.hprofName(str);
    }

    @Override
    public boolean dump(String str) throws IOException {
        boolean zDump = super.dump(str);
        KLog.e("ForkAndStripHeapDumper", "forkDumpSuccess = " + zDump + " stripSuccess = true");
        return zDump;
    }
}
