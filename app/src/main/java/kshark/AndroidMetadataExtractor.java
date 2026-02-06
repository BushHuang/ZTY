package kshark;

import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kshark.HeapObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010\t\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002¨\u0006\n"}, d2 = {"Lkshark/AndroidMetadataExtractor;", "Lkshark/MetadataExtractor;", "()V", "extractMetadata", "", "", "graph", "Lkshark/HeapGraph;", "readLeakCanaryVersion", "readProcessName", "shark"}, k = 1, mv = {1, 1, 15})
public final class AndroidMetadataExtractor implements MetadataExtractor {
    public static final AndroidMetadataExtractor INSTANCE = new AndroidMetadataExtractor();

    private AndroidMetadataExtractor() {
    }

    private final String readLeakCanaryVersion(HeapGraph graph) {
        HeapField heapField;
        HeapValue value;
        String asJavaString;
        HeapObject.HeapClass heapClassFindClassByName = graph.findClassByName("leakcanary.internal.InternalLeakCanary");
        return (heapClassFindClassByName == null || (heapField = heapClassFindClassByName.get("version")) == null || (value = heapField.getValue()) == null || (asJavaString = value.readAsJavaString()) == null) ? "Unknown" : asJavaString;
    }

    private final String readProcessName(HeapGraph graph) {
        HeapField heapField;
        HeapObject.HeapInstance valueAsInstance;
        String asJavaString;
        HeapField heapField2;
        HeapField heapField3;
        HeapField heapField4;
        HeapObject.HeapClass heapClassFindClassByName = graph.findClassByName("android.app.ActivityThread");
        HeapObject.HeapInstance valueAsInstance2 = null;
        HeapObject.HeapInstance valueAsInstance3 = (heapClassFindClassByName == null || (heapField4 = heapClassFindClassByName.get("sCurrentActivityThread")) == null) ? null : heapField4.getValueAsInstance();
        HeapObject.HeapInstance valueAsInstance4 = (valueAsInstance3 == null || (heapField3 = valueAsInstance3.get("android.app.ActivityThread", "mBoundApplication")) == null) ? null : heapField3.getValueAsInstance();
        if (valueAsInstance4 != null && (heapField2 = valueAsInstance4.get("android.app.ActivityThread$AppBindData", "appInfo")) != null) {
            valueAsInstance2 = heapField2.getValueAsInstance();
        }
        return (valueAsInstance2 == null || (heapField = valueAsInstance2.get("android.content.pm.ApplicationInfo", "processName")) == null || (valueAsInstance = heapField.getValueAsInstance()) == null || (asJavaString = valueAsInstance.readAsJavaString()) == null) ? "Unknown" : asJavaString;
    }

    @Override
    public Map<String, String> extractMetadata(HeapGraph graph) {
        Intrinsics.checkParameterIsNotNull(graph, "graph");
        AndroidBuildMirror androidBuildMirrorFromHeapGraph = AndroidBuildMirror.INSTANCE.fromHeapGraph(graph);
        return MapsKt.mapOf(TuplesKt.to("Build.VERSION.SDK_INT", String.valueOf(androidBuildMirrorFromHeapGraph.getSdkInt())), TuplesKt.to("Build.MANUFACTURER", androidBuildMirrorFromHeapGraph.getManufacturer()), TuplesKt.to("LeakCanary version", readLeakCanaryVersion(graph)), TuplesKt.to("App process name", readProcessName(graph)));
    }
}
