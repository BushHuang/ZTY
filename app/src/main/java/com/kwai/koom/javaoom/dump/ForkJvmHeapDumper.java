package com.kwai.koom.javaoom.dump;

import android.os.Debug;
import com.kwai.koom.javaoom.KOOMEnableChecker;
import com.kwai.koom.javaoom.common.KGlobalConfig;
import com.kwai.koom.javaoom.common.KLog;
import java.io.IOException;

public class ForkJvmHeapDumper implements HeapDumper {
    private static final String TAG = "ForkJvmHeapDumper";
    private boolean soLoaded;

    public ForkJvmHeapDumper() {
        boolean zLoadLib = KGlobalConfig.getSoLoader().loadLib("koom-java");
        this.soLoaded = zLoadLib;
        if (zLoadLib) {
            initForkDump();
        }
    }

    private native void exitProcess();

    private native boolean initForkDump();

    private native void resumeVM();

    private native int trySuspendVMThenFork();

    private boolean waitDumping(int i) {
        waitPid(i);
        return true;
    }

    private native void waitPid(int i);

    protected void beforeDumpInForkedProcess(String str) {
    }

    @Override
    public boolean dump(String str) throws IOException {
        KLog.i("ForkJvmHeapDumper", "dump " + str);
        boolean zWaitDumping = false;
        if (!this.soLoaded) {
            KLog.e("ForkJvmHeapDumper", "dump failed caused by so not loaded!");
            return false;
        }
        if (!KOOMEnableChecker.get().isVersionPermit()) {
            KLog.e("ForkJvmHeapDumper", "dump failed caused by version net permitted!");
            return false;
        }
        if (!KOOMEnableChecker.get().isSpaceEnough()) {
            KLog.e("ForkJvmHeapDumper", "dump failed caused by disk space not enough!");
            return false;
        }
        try {
            int iTrySuspendVMThenFork = trySuspendVMThenFork();
            if (iTrySuspendVMThenFork == 0) {
                beforeDumpInForkedProcess(str);
                Debug.dumpHprofData(str);
                KLog.i("ForkJvmHeapDumper", "notifyDumped:false");
                exitProcess();
            } else {
                resumeVM();
                zWaitDumping = waitDumping(iTrySuspendVMThenFork);
                KLog.i("ForkJvmHeapDumper", "hprof pid:" + iTrySuspendVMThenFork + " dumped: " + str);
            }
        } catch (IOException e) {
            e.printStackTrace();
            KLog.e("ForkJvmHeapDumper", "dump failed caused by IOException!");
        }
        return zWaitDumping;
    }
}
