package kshark;

import java.io.File;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u0012\u0010\t\u001a\u00020\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\u0082\u0001\u0002\u000e\u000f¨\u0006\u0010"}, d2 = {"Lkshark/HeapAnalysis;", "Ljava/io/Serializable;", "()V", "analysisDurationMillis", "", "getAnalysisDurationMillis", "()J", "createdAtTimeMillis", "getCreatedAtTimeMillis", "heapDumpFile", "Ljava/io/File;", "getHeapDumpFile", "()Ljava/io/File;", "Companion", "Lkshark/HeapAnalysisFailure;", "Lkshark/HeapAnalysisSuccess;", "shark"}, k = 1, mv = {1, 1, 15})
public abstract class HeapAnalysis implements Serializable {
    private static final long serialVersionUID = -8657286725869987172L;

    private HeapAnalysis() {
    }

    public HeapAnalysis(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract long getAnalysisDurationMillis();

    public abstract long getCreatedAtTimeMillis();

    public abstract File getHeapDumpFile();
}
