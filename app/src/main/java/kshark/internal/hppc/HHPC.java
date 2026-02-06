package kshark.internal.hppc;

import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\b\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0007J\u001e\u0010\u0010\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lkshark/internal/hppc/HHPC;", "", "()V", "MAX_HASH_ARRAY_LENGTH", "", "MIN_HASH_ARRAY_LENGTH", "PHI_C64", "", "expandAtCount", "arraySize", "loadFactor", "", "minBufferSize", "elements", "mixPhi", "k", "nextBufferSize", "nextHighestPowerOfTwo", "input", "shark"}, k = 1, mv = {1, 1, 15})
public final class HHPC {
    public static final HHPC INSTANCE = new HHPC();
    private static final int MAX_HASH_ARRAY_LENGTH = 1073741824;
    private static final int MIN_HASH_ARRAY_LENGTH = 4;
    private static final long PHI_C64 = -7046029254386353131L;

    private HHPC() {
    }

    public final int expandAtCount(int arraySize, double loadFactor) {
        double d = arraySize;
        Double.isNaN(d);
        return Math.min(arraySize - 1, (int) Math.ceil(d * loadFactor));
    }

    public final int minBufferSize(int elements, double loadFactor) {
        double d = elements;
        Double.isNaN(d);
        long jCeil = (long) Math.ceil(d / loadFactor);
        if (jCeil == elements) {
            jCeil++;
        }
        long jMax = Math.max(4, nextHighestPowerOfTwo(jCeil));
        if (jMax <= 1073741824) {
            return (int) jMax;
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ROOT;
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.ROOT");
        String str = String.format(locale, "Maximum array size exceeded for this load factor (elements: %d, load factor: %f)", Arrays.copyOf(new Object[]{Integer.valueOf(elements), Double.valueOf(loadFactor)}, 2));
        Intrinsics.checkExpressionValueIsNotNull(str, "java.lang.String.format(locale, format, *args)");
        throw new RuntimeException(str);
    }

    public final int mixPhi(long k) {
        long j = k * (-7046029254386353131L);
        return (int) (j ^ (j >>> 32));
    }

    public final int nextBufferSize(int arraySize, int elements, double loadFactor) {
        if (arraySize != 1073741824) {
            return arraySize << 1;
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ROOT;
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.ROOT");
        String str = String.format(locale, "Maximum array size exceeded for this load factor (elements: %d, load factor: %f)", Arrays.copyOf(new Object[]{Integer.valueOf(elements), Double.valueOf(loadFactor)}, 2));
        Intrinsics.checkExpressionValueIsNotNull(str, "java.lang.String.format(locale, format, *args)");
        throw new RuntimeException(str);
    }

    public final long nextHighestPowerOfTwo(long input) {
        long j = input - 1;
        long j2 = j | (j >> 1);
        long j3 = j2 | (j2 >> 2);
        long j4 = j3 | (j3 >> 4);
        long j5 = j4 | (j4 >> 8);
        long j6 = j5 | (j5 >> 16);
        return (j6 | (j6 >> 32)) + 1;
    }
}
