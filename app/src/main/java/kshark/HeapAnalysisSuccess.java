package kshark;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 -2\u00020\u0001:\u0001-BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\b\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000b¢\u0006\u0002\u0010\u000fJ\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003J\u0015\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J\u000f\u0010#\u001a\b\u0012\u0004\u0012\u00020\f0\u000bHÆ\u0003J\u000f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000bHÆ\u0003J]\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\b2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000bHÆ\u0001J\u0013\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010)HÖ\u0003J\t\u0010*\u001a\u00020+HÖ\u0001J\b\u0010,\u001a\u00020\tH\u0016R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00118F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0006\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e¨\u0006."}, d2 = {"Lkshark/HeapAnalysisSuccess;", "Lkshark/HeapAnalysis;", "heapDumpFile", "Ljava/io/File;", "createdAtTimeMillis", "", "analysisDurationMillis", "metadata", "", "", "applicationLeaks", "", "Lkshark/ApplicationLeak;", "libraryLeaks", "Lkshark/LibraryLeak;", "(Ljava/io/File;JJLjava/util/Map;Ljava/util/List;Ljava/util/List;)V", "allLeaks", "Lkotlin/sequences/Sequence;", "Lkshark/Leak;", "getAllLeaks", "()Lkotlin/sequences/Sequence;", "getAnalysisDurationMillis", "()J", "getApplicationLeaks", "()Ljava/util/List;", "getCreatedAtTimeMillis", "getHeapDumpFile", "()Ljava/io/File;", "getLibraryLeaks", "getMetadata", "()Ljava/util/Map;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Companion", "shark"}, k = 1, mv = {1, 1, 15})
public final class HeapAnalysisSuccess extends HeapAnalysis {

    public static final Companion INSTANCE = new Companion(null);
    private static final long serialVersionUID = 130453013437459642L;
    private final long analysisDurationMillis;
    private final List<ApplicationLeak> applicationLeaks;
    private final long createdAtTimeMillis;
    private final File heapDumpFile;
    private final List<LibraryLeak> libraryLeaks;
    private final Map<String, String> metadata;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lkshark/HeapAnalysisSuccess$Companion;", "", "()V", "serialVersionUID", "", "upgradeFrom20Deserialized", "Lkshark/HeapAnalysisSuccess;", "fromV20", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final HeapAnalysisSuccess upgradeFrom20Deserialized(HeapAnalysisSuccess fromV20) {
            Intrinsics.checkParameterIsNotNull(fromV20, "fromV20");
            List<ApplicationLeak> applicationLeaks = fromV20.getApplicationLeaks();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(applicationLeaks, 10));
            Iterator<T> it = applicationLeaks.iterator();
            while (it.hasNext()) {
                arrayList.add(((ApplicationLeak) it.next()).leakTraceFromV20$shark());
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Object obj : arrayList) {
                String signature = ((LeakTrace) obj).getSignature();
                Object arrayList2 = linkedHashMap.get(signature);
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList();
                    linkedHashMap.put(signature, arrayList2);
                }
                ((List) arrayList2).add(obj);
            }
            Collection collectionValues = linkedHashMap.values();
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(collectionValues, 10));
            Iterator it2 = collectionValues.iterator();
            while (it2.hasNext()) {
                arrayList3.add(new ApplicationLeak((List) it2.next()));
            }
            ArrayList arrayList4 = arrayList3;
            List<LibraryLeak> libraryLeaks = fromV20.getLibraryLeaks();
            ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(libraryLeaks, 10));
            for (LibraryLeak libraryLeak : libraryLeaks) {
                arrayList5.add(TuplesKt.to(libraryLeak, libraryLeak.leakTraceFromV20$shark()));
            }
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            for (Object obj2 : arrayList5) {
                String signature2 = ((LeakTrace) ((Pair) obj2).getSecond()).getSignature();
                Object arrayList6 = linkedHashMap2.get(signature2);
                if (arrayList6 == null) {
                    arrayList6 = new ArrayList();
                    linkedHashMap2.put(signature2, arrayList6);
                }
                ((List) arrayList6).add(obj2);
            }
            Collection<List> collectionValues2 = linkedHashMap2.values();
            ArrayList arrayList7 = new ArrayList(CollectionsKt.collectionSizeOrDefault(collectionValues2, 10));
            for (List list : collectionValues2) {
                LibraryLeak libraryLeak2 = (LibraryLeak) ((Pair) CollectionsKt.first(list)).getFirst();
                ReferencePattern pattern = libraryLeak2.getPattern();
                String description = libraryLeak2.getDescription();
                List list2 = list;
                ArrayList arrayList8 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                Iterator it3 = list2.iterator();
                while (it3.hasNext()) {
                    arrayList8.add((LeakTrace) ((Pair) it3.next()).getSecond());
                }
                arrayList7.add(new LibraryLeak(arrayList8, pattern, description));
            }
            return new HeapAnalysisSuccess(fromV20.getHeapDumpFile(), fromV20.getCreatedAtTimeMillis(), fromV20.getAnalysisDurationMillis(), fromV20.getMetadata(), arrayList4, arrayList7);
        }
    }

    public HeapAnalysisSuccess(File file, long j, long j2, Map<String, String> map, List<ApplicationLeak> list, List<LibraryLeak> list2) {
        super(null);
        Intrinsics.checkParameterIsNotNull(file, "heapDumpFile");
        Intrinsics.checkParameterIsNotNull(map, "metadata");
        Intrinsics.checkParameterIsNotNull(list, "applicationLeaks");
        Intrinsics.checkParameterIsNotNull(list2, "libraryLeaks");
        this.heapDumpFile = file;
        this.createdAtTimeMillis = j;
        this.analysisDurationMillis = j2;
        this.metadata = map;
        this.applicationLeaks = list;
        this.libraryLeaks = list2;
    }

    public final File component1() {
        return getHeapDumpFile();
    }

    public final long component2() {
        return getCreatedAtTimeMillis();
    }

    public final long component3() {
        return getAnalysisDurationMillis();
    }

    public final Map<String, String> component4() {
        return this.metadata;
    }

    public final List<ApplicationLeak> component5() {
        return this.applicationLeaks;
    }

    public final List<LibraryLeak> component6() {
        return this.libraryLeaks;
    }

    public final HeapAnalysisSuccess copy(File heapDumpFile, long createdAtTimeMillis, long analysisDurationMillis, Map<String, String> metadata, List<ApplicationLeak> applicationLeaks, List<LibraryLeak> libraryLeaks) {
        Intrinsics.checkParameterIsNotNull(heapDumpFile, "heapDumpFile");
        Intrinsics.checkParameterIsNotNull(metadata, "metadata");
        Intrinsics.checkParameterIsNotNull(applicationLeaks, "applicationLeaks");
        Intrinsics.checkParameterIsNotNull(libraryLeaks, "libraryLeaks");
        return new HeapAnalysisSuccess(heapDumpFile, createdAtTimeMillis, analysisDurationMillis, metadata, applicationLeaks, libraryLeaks);
    }

    public boolean equals(Object other) {
        if (this != other) {
            if (other instanceof HeapAnalysisSuccess) {
                HeapAnalysisSuccess heapAnalysisSuccess = (HeapAnalysisSuccess) other;
                if (Intrinsics.areEqual(getHeapDumpFile(), heapAnalysisSuccess.getHeapDumpFile())) {
                    if (getCreatedAtTimeMillis() == heapAnalysisSuccess.getCreatedAtTimeMillis()) {
                        if (!(getAnalysisDurationMillis() == heapAnalysisSuccess.getAnalysisDurationMillis()) || !Intrinsics.areEqual(this.metadata, heapAnalysisSuccess.metadata) || !Intrinsics.areEqual(this.applicationLeaks, heapAnalysisSuccess.applicationLeaks) || !Intrinsics.areEqual(this.libraryLeaks, heapAnalysisSuccess.libraryLeaks)) {
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final Sequence<Leak> getAllLeaks() {
        return SequencesKt.plus(CollectionsKt.asSequence(this.applicationLeaks), CollectionsKt.asSequence(this.libraryLeaks));
    }

    @Override
    public long getAnalysisDurationMillis() {
        return this.analysisDurationMillis;
    }

    public final List<ApplicationLeak> getApplicationLeaks() {
        return this.applicationLeaks;
    }

    @Override
    public long getCreatedAtTimeMillis() {
        return this.createdAtTimeMillis;
    }

    @Override
    public File getHeapDumpFile() {
        return this.heapDumpFile;
    }

    public final List<LibraryLeak> getLibraryLeaks() {
        return this.libraryLeaks;
    }

    public final Map<String, String> getMetadata() {
        return this.metadata;
    }

    public int hashCode() {
        File heapDumpFile = getHeapDumpFile();
        int iHashCode = heapDumpFile != null ? heapDumpFile.hashCode() : 0;
        long createdAtTimeMillis = getCreatedAtTimeMillis();
        int i = ((iHashCode * 31) + ((int) (createdAtTimeMillis ^ (createdAtTimeMillis >>> 32)))) * 31;
        long analysisDurationMillis = getAnalysisDurationMillis();
        int i2 = (i + ((int) (analysisDurationMillis ^ (analysisDurationMillis >>> 32)))) * 31;
        Map<String, String> map = this.metadata;
        int iHashCode2 = (i2 + (map != null ? map.hashCode() : 0)) * 31;
        List<ApplicationLeak> list = this.applicationLeaks;
        int iHashCode3 = (iHashCode2 + (list != null ? list.hashCode() : 0)) * 31;
        List<LibraryLeak> list2 = this.libraryLeaks;
        return iHashCode3 + (list2 != null ? list2.hashCode() : 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("====================================\nHEAP ANALYSIS RESULT\n====================================\n");
        sb.append(this.applicationLeaks.size());
        sb.append(" APPLICATION LEAKS\n\nReferences underlined with \"~~~\" are likely causes.\nLearn more at https://squ.re/leaks.\n");
        String string = "";
        sb.append(!this.applicationLeaks.isEmpty() ? "\n" + CollectionsKt.joinToString$default(this.applicationLeaks, "\n\n", null, null, 0, null, null, 62, null) + "\n" : "");
        sb.append("====================================\n");
        sb.append(this.libraryLeaks.size());
        sb.append(" LIBRARY LEAKS\n\nLibrary Leaks are leaks coming from the Android Framework or Google libraries.\n");
        sb.append(!this.libraryLeaks.isEmpty() ? "\n" + CollectionsKt.joinToString$default(this.libraryLeaks, "\n\n", null, null, 0, null, null, 62, null) + "\n" : "");
        sb.append("====================================\nMETADATA\n\nPlease include this in bug reports and Stack Overflow questions.\n");
        if (!this.metadata.isEmpty()) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("\n");
            Map<String, String> map = this.metadata;
            ArrayList arrayList = new ArrayList(map.size());
            for (Map.Entry<String, String> entry : map.entrySet()) {
                arrayList.add(entry.getKey() + ": " + entry.getValue());
            }
            sb2.append(CollectionsKt.joinToString$default(arrayList, "\n", null, null, 0, null, null, 62, null));
            string = sb2.toString();
        }
        sb.append(string);
        sb.append("\nAnalysis duration: ");
        sb.append(getAnalysisDurationMillis());
        sb.append(" ms\nHeap dump file path: ");
        sb.append(getHeapDumpFile().getAbsolutePath());
        sb.append("\nHeap dump timestamp: ");
        sb.append(getCreatedAtTimeMillis());
        sb.append("\n====================================");
        return sb.toString();
    }
}
