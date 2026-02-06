package com.kwai.koom.javaoom.dump;

import com.kwai.koom.javaoom.monitor.TriggerReason;

public interface HeapDumpListener {
    void onHeapDumpFailed();

    void onHeapDumpTrigger(TriggerReason.DumpReason dumpReason);

    void onHeapDumped(TriggerReason.DumpReason dumpReason);
}
