package kshark;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kshark.HeapObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\f"}, d2 = {"Lkshark/AndroidBuildMirror;", "", "manufacturer", "", "sdkInt", "", "(Ljava/lang/String;I)V", "getManufacturer", "()Ljava/lang/String;", "getSdkInt", "()I", "Companion", "shark"}, k = 1, mv = {1, 1, 15})
public final class AndroidBuildMirror {

    public static final Companion INSTANCE = new Companion(null);
    private final String manufacturer;
    private final int sdkInt;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lkshark/AndroidBuildMirror$Companion;", "", "()V", "fromHeapGraph", "Lkshark/AndroidBuildMirror;", "graph", "Lkshark/HeapGraph;", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final AndroidBuildMirror fromHeapGraph(final HeapGraph graph) {
            Intrinsics.checkParameterIsNotNull(graph, "graph");
            GraphContext context = graph.getContext();
            String name = AndroidBuildMirror.class.getName();
            Intrinsics.checkExpressionValueIsNotNull(name, "AndroidBuildMirror::class.java.name");
            return (AndroidBuildMirror) context.getOrPut(name, new Function0<AndroidBuildMirror>() {
                {
                    super(0);
                }

                @Override
                public final AndroidBuildMirror invoke() {
                    HeapObject.HeapClass heapClassFindClassByName = graph.findClassByName("android.os.Build");
                    if (heapClassFindClassByName == null) {
                        Intrinsics.throwNpe();
                    }
                    HeapObject.HeapClass heapClassFindClassByName2 = graph.findClassByName("android.os.Build$VERSION");
                    if (heapClassFindClassByName2 == null) {
                        Intrinsics.throwNpe();
                    }
                    HeapField heapField = heapClassFindClassByName.get("MANUFACTURER");
                    if (heapField == null) {
                        Intrinsics.throwNpe();
                    }
                    HeapValue value = heapField.getValue();
                    int i = 0;
                    if (!value.isNonNullReference()) {
                        String asJavaString = value.readAsJavaString();
                        if (!(asJavaString == null || asJavaString.length() == 0)) {
                            HeapField heapField2 = heapClassFindClassByName2.get("SDK_INT");
                            if (heapField2 == null) {
                                Intrinsics.throwNpe();
                            }
                            Integer asInt = heapField2.getValue().getAsInt();
                            if (asInt == null) {
                                Intrinsics.throwNpe();
                            }
                            int iIntValue = asInt.intValue();
                            String asJavaString2 = value.readAsJavaString();
                            if (asJavaString2 == null) {
                                Intrinsics.throwNpe();
                            }
                            return new AndroidBuildMirror(asJavaString2, iIntValue);
                        }
                    }
                    return new AndroidBuildMirror(null, i, 3, 0 == true ? 1 : 0);
                }
            });
        }
    }

    public AndroidBuildMirror() {
        this(null, 0, 3, 0 == true ? 1 : 0);
    }

    public AndroidBuildMirror(String str, int i) {
        Intrinsics.checkParameterIsNotNull(str, "manufacturer");
        this.manufacturer = str;
        this.sdkInt = i;
    }

    public AndroidBuildMirror(String str, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "Crop" : str, (i2 & 2) != 0 ? 21 : i);
    }

    public final String getManufacturer() {
        return this.manufacturer;
    }

    public final int getSdkInt() {
        return this.sdkInt;
    }
}
