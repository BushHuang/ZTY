package kshark;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u00062\u00020\u0001:\n\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\fB\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\t\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015¨\u0006\u0016"}, d2 = {"Lkshark/ValueHolder;", "", "()V", "BooleanHolder", "ByteHolder", "CharHolder", "Companion", "DoubleHolder", "FloatHolder", "IntHolder", "LongHolder", "ReferenceHolder", "ShortHolder", "Lkshark/ValueHolder$ReferenceHolder;", "Lkshark/ValueHolder$BooleanHolder;", "Lkshark/ValueHolder$CharHolder;", "Lkshark/ValueHolder$FloatHolder;", "Lkshark/ValueHolder$DoubleHolder;", "Lkshark/ValueHolder$ByteHolder;", "Lkshark/ValueHolder$ShortHolder;", "Lkshark/ValueHolder$IntHolder;", "Lkshark/ValueHolder$LongHolder;", "shark"}, k = 1, mv = {1, 1, 15})
public abstract class ValueHolder {
    public static final long NULL_REFERENCE = 0;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lkshark/ValueHolder$BooleanHolder;", "Lkshark/ValueHolder;", "value", "", "(Z)V", "getValue", "()Z", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class BooleanHolder extends ValueHolder {
        private final boolean value;

        public BooleanHolder(boolean z) {
            super(null);
            this.value = z;
        }

        public static BooleanHolder copy$default(BooleanHolder booleanHolder, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = booleanHolder.value;
            }
            return booleanHolder.copy(z);
        }

        public final boolean getValue() {
            return this.value;
        }

        public final BooleanHolder copy(boolean value) {
            return new BooleanHolder(value);
        }

        public boolean equals(Object other) {
            if (this != other) {
                if (other instanceof BooleanHolder) {
                    if (this.value == ((BooleanHolder) other).value) {
                    }
                }
                return false;
            }
            return true;
        }

        public final boolean getValue() {
            return this.value;
        }

        public int hashCode() {
            boolean z = this.value;
            if (z) {
                return 1;
            }
            return z ? 1 : 0;
        }

        public String toString() {
            return "BooleanHolder(value=" + this.value + ")";
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lkshark/ValueHolder$ByteHolder;", "Lkshark/ValueHolder;", "value", "", "(B)V", "getValue", "()B", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class ByteHolder extends ValueHolder {
        private final byte value;

        public ByteHolder(byte b) {
            super(null);
            this.value = b;
        }

        public static ByteHolder copy$default(ByteHolder byteHolder, byte b, int i, Object obj) {
            if ((i & 1) != 0) {
                b = byteHolder.value;
            }
            return byteHolder.copy(b);
        }

        public final byte getValue() {
            return this.value;
        }

        public final ByteHolder copy(byte value) {
            return new ByteHolder(value);
        }

        public boolean equals(Object other) {
            if (this != other) {
                if (other instanceof ByteHolder) {
                    if (this.value == ((ByteHolder) other).value) {
                    }
                }
                return false;
            }
            return true;
        }

        public final byte getValue() {
            return this.value;
        }

        public int hashCode() {
            return this.value;
        }

        public String toString() {
            return "ByteHolder(value=" + ((int) this.value) + ")";
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lkshark/ValueHolder$CharHolder;", "Lkshark/ValueHolder;", "value", "", "(C)V", "getValue", "()C", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class CharHolder extends ValueHolder {
        private final char value;

        public CharHolder(char c) {
            super(null);
            this.value = c;
        }

        public static CharHolder copy$default(CharHolder charHolder, char c, int i, Object obj) {
            if ((i & 1) != 0) {
                c = charHolder.value;
            }
            return charHolder.copy(c);
        }

        public final char getValue() {
            return this.value;
        }

        public final CharHolder copy(char value) {
            return new CharHolder(value);
        }

        public boolean equals(Object other) {
            if (this != other) {
                if (other instanceof CharHolder) {
                    if (this.value == ((CharHolder) other).value) {
                    }
                }
                return false;
            }
            return true;
        }

        public final char getValue() {
            return this.value;
        }

        public int hashCode() {
            return this.value;
        }

        public String toString() {
            return "CharHolder(value=" + this.value + ")";
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lkshark/ValueHolder$DoubleHolder;", "Lkshark/ValueHolder;", "value", "", "(D)V", "getValue", "()D", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class DoubleHolder extends ValueHolder {
        private final double value;

        public DoubleHolder(double d) {
            super(null);
            this.value = d;
        }

        public static DoubleHolder copy$default(DoubleHolder doubleHolder, double d, int i, Object obj) {
            if ((i & 1) != 0) {
                d = doubleHolder.value;
            }
            return doubleHolder.copy(d);
        }

        public final double getValue() {
            return this.value;
        }

        public final DoubleHolder copy(double value) {
            return new DoubleHolder(value);
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof DoubleHolder) && Double.compare(this.value, ((DoubleHolder) other).value) == 0;
            }
            return true;
        }

        public final double getValue() {
            return this.value;
        }

        public int hashCode() {
            long jDoubleToLongBits = Double.doubleToLongBits(this.value);
            return (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
        }

        public String toString() {
            return "DoubleHolder(value=" + this.value + ")";
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lkshark/ValueHolder$FloatHolder;", "Lkshark/ValueHolder;", "value", "", "(F)V", "getValue", "()F", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class FloatHolder extends ValueHolder {
        private final float value;

        public FloatHolder(float f) {
            super(null);
            this.value = f;
        }

        public static FloatHolder copy$default(FloatHolder floatHolder, float f, int i, Object obj) {
            if ((i & 1) != 0) {
                f = floatHolder.value;
            }
            return floatHolder.copy(f);
        }

        public final float getValue() {
            return this.value;
        }

        public final FloatHolder copy(float value) {
            return new FloatHolder(value);
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof FloatHolder) && Float.compare(this.value, ((FloatHolder) other).value) == 0;
            }
            return true;
        }

        public final float getValue() {
            return this.value;
        }

        public int hashCode() {
            return Float.floatToIntBits(this.value);
        }

        public String toString() {
            return "FloatHolder(value=" + this.value + ")";
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u0003HÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lkshark/ValueHolder$IntHolder;", "Lkshark/ValueHolder;", "value", "", "(I)V", "getValue", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class IntHolder extends ValueHolder {
        private final int value;

        public IntHolder(int i) {
            super(null);
            this.value = i;
        }

        public static IntHolder copy$default(IntHolder intHolder, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = intHolder.value;
            }
            return intHolder.copy(i);
        }

        public final int getValue() {
            return this.value;
        }

        public final IntHolder copy(int value) {
            return new IntHolder(value);
        }

        public boolean equals(Object other) {
            if (this != other) {
                if (other instanceof IntHolder) {
                    if (this.value == ((IntHolder) other).value) {
                    }
                }
                return false;
            }
            return true;
        }

        public final int getValue() {
            return this.value;
        }

        public int hashCode() {
            return this.value;
        }

        public String toString() {
            return "IntHolder(value=" + this.value + ")";
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lkshark/ValueHolder$LongHolder;", "Lkshark/ValueHolder;", "value", "", "(J)V", "getValue", "()J", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class LongHolder extends ValueHolder {
        private final long value;

        public LongHolder(long j) {
            super(null);
            this.value = j;
        }

        public static LongHolder copy$default(LongHolder longHolder, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                j = longHolder.value;
            }
            return longHolder.copy(j);
        }

        public final long getValue() {
            return this.value;
        }

        public final LongHolder copy(long value) {
            return new LongHolder(value);
        }

        public boolean equals(Object other) {
            if (this != other) {
                if (other instanceof LongHolder) {
                    if (this.value == ((LongHolder) other).value) {
                    }
                }
                return false;
            }
            return true;
        }

        public final long getValue() {
            return this.value;
        }

        public int hashCode() {
            long j = this.value;
            return (int) (j ^ (j >>> 32));
        }

        public String toString() {
            return "LongHolder(value=" + this.value + ")";
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0013"}, d2 = {"Lkshark/ValueHolder$ReferenceHolder;", "Lkshark/ValueHolder;", "value", "", "(J)V", "isNull", "", "()Z", "getValue", "()J", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class ReferenceHolder extends ValueHolder {
        private final long value;

        public ReferenceHolder(long j) {
            super(null);
            this.value = j;
        }

        public static ReferenceHolder copy$default(ReferenceHolder referenceHolder, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                j = referenceHolder.value;
            }
            return referenceHolder.copy(j);
        }

        public final long getValue() {
            return this.value;
        }

        public final ReferenceHolder copy(long value) {
            return new ReferenceHolder(value);
        }

        public boolean equals(Object other) {
            if (this != other) {
                if (other instanceof ReferenceHolder) {
                    if (this.value == ((ReferenceHolder) other).value) {
                    }
                }
                return false;
            }
            return true;
        }

        public final long getValue() {
            return this.value;
        }

        public int hashCode() {
            long j = this.value;
            return (int) (j ^ (j >>> 32));
        }

        public final boolean isNull() {
            return this.value == 0;
        }

        public String toString() {
            return "ReferenceHolder(value=" + this.value + ")";
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\n\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lkshark/ValueHolder$ShortHolder;", "Lkshark/ValueHolder;", "value", "", "(S)V", "getValue", "()S", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class ShortHolder extends ValueHolder {
        private final short value;

        public ShortHolder(short s) {
            super(null);
            this.value = s;
        }

        public static ShortHolder copy$default(ShortHolder shortHolder, short s, int i, Object obj) {
            if ((i & 1) != 0) {
                s = shortHolder.value;
            }
            return shortHolder.copy(s);
        }

        public final short getValue() {
            return this.value;
        }

        public final ShortHolder copy(short value) {
            return new ShortHolder(value);
        }

        public boolean equals(Object other) {
            if (this != other) {
                if (other instanceof ShortHolder) {
                    if (this.value == ((ShortHolder) other).value) {
                    }
                }
                return false;
            }
            return true;
        }

        public final short getValue() {
            return this.value;
        }

        public int hashCode() {
            return this.value;
        }

        public String toString() {
            return "ShortHolder(value=" + ((int) this.value) + ")";
        }
    }

    private ValueHolder() {
    }

    public ValueHolder(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
