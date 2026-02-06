package kshark;

import java.lang.reflect.Field;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0002\u001a\b\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\b\u0010\u0004\u001a\u00020\u0001H\u0002¨\u0006\u0005"}, d2 = {"androidManufacturer", "", "androidSdkInt", "", "leakCanaryVersion", "shark"}, k = 2, mv = {1, 1, 15})
public final class HeapAnalysisKt {
    private static final String androidManufacturer() throws IllegalAccessException, IllegalArgumentException {
        try {
            Object obj = Class.forName("android.os.Build").getDeclaredField("MANUFACTURER").get(null);
            if (obj != null) {
                return (String) obj;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
        } catch (Exception unused) {
            return "Unknown";
        }
    }

    private static final int androidSdkInt() throws IllegalAccessException, IllegalArgumentException {
        try {
            Object obj = Class.forName("android.os.Build$VERSION").getDeclaredField("SDK_INT").get(null);
            if (obj != null) {
                return ((Integer) obj).intValue();
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
        } catch (Exception unused) {
            return -1;
        }
    }

    private static final String leakCanaryVersion() throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        try {
            Field declaredField = Class.forName("leakcanary.internal.InternalLeakCanary").getDeclaredField("version");
            Intrinsics.checkExpressionValueIsNotNull(declaredField, "versionField");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(null);
            if (obj != null) {
                return (String) obj;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
        } catch (Exception unused) {
            return "Unknown";
        }
    }
}
