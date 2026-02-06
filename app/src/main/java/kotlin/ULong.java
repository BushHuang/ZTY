package kotlin;

import kotlin.jvm.JvmInline;
import kotlin.ranges.ULongRange;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\"\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0087@\u0018\u0000 |2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001|B\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\u0097\nø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u0000H\u0087\nø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001a\u0010\u0005J\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b \u0010\u000bJ\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b!\u0010\"J\u001a\u0010#\u001a\u00020$2\b\u0010\t\u001a\u0004\u0018\u00010%HÖ\u0003¢\u0006\u0004\b&\u0010'J\u001b\u0010(\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0087\bø\u0001\u0000¢\u0006\u0004\b)\u0010\u001dJ\u001b\u0010(\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\u0087\bø\u0001\u0000¢\u0006\u0004\b*\u0010\u001fJ\u001b\u0010(\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b+\u0010\u000bJ\u001b\u0010(\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\u0087\bø\u0001\u0000¢\u0006\u0004\b,\u0010\"J\u0010\u0010-\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b.\u0010/J\u0016\u00100\u001a\u00020\u0000H\u0087\nø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b1\u0010\u0005J\u0016\u00102\u001a\u00020\u0000H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b3\u0010\u0005J\u001b\u00104\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b5\u0010\u001dJ\u001b\u00104\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b6\u0010\u001fJ\u001b\u00104\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b7\u0010\u000bJ\u001b\u00104\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b8\u0010\"J\u001b\u00109\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\u000eH\u0087\bø\u0001\u0000¢\u0006\u0004\b:\u0010;J\u001b\u00109\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\bø\u0001\u0000¢\u0006\u0004\b<\u0010\u0013J\u001b\u00109\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b=\u0010\u000bJ\u001b\u00109\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\u0016H\u0087\bø\u0001\u0000¢\u0006\u0004\b>\u0010?J\u001b\u0010@\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\bA\u0010\u000bJ\u001b\u0010B\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\bC\u0010\u001dJ\u001b\u0010B\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bD\u0010\u001fJ\u001b\u0010B\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bE\u0010\u000bJ\u001b\u0010B\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\bF\u0010\"J\u001b\u0010G\u001a\u00020H2\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bI\u0010JJ\u001b\u0010K\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\bL\u0010\u001dJ\u001b\u0010K\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bM\u0010\u001fJ\u001b\u0010K\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bN\u0010\u000bJ\u001b\u0010K\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\bO\u0010\"J\u001e\u0010P\u001a\u00020\u00002\u0006\u0010Q\u001a\u00020\rH\u0087\fø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bR\u0010\u001fJ\u001e\u0010S\u001a\u00020\u00002\u0006\u0010Q\u001a\u00020\rH\u0087\fø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bT\u0010\u001fJ\u001b\u0010U\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\bV\u0010\u001dJ\u001b\u0010U\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bW\u0010\u001fJ\u001b\u0010U\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bX\u0010\u000bJ\u001b\u0010U\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\bY\u0010\"J\u0010\u0010Z\u001a\u00020[H\u0087\b¢\u0006\u0004\b\\\u0010]J\u0010\u0010^\u001a\u00020_H\u0087\b¢\u0006\u0004\b`\u0010aJ\u0010\u0010b\u001a\u00020cH\u0087\b¢\u0006\u0004\bd\u0010eJ\u0010\u0010f\u001a\u00020\rH\u0087\b¢\u0006\u0004\bg\u0010/J\u0010\u0010h\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\bi\u0010\u0005J\u0010\u0010j\u001a\u00020kH\u0087\b¢\u0006\u0004\bl\u0010mJ\u000f\u0010n\u001a\u00020oH\u0016¢\u0006\u0004\bp\u0010qJ\u0016\u0010r\u001a\u00020\u000eH\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bs\u0010]J\u0016\u0010t\u001a\u00020\u0011H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bu\u0010/J\u0016\u0010v\u001a\u00020\u0000H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bw\u0010\u0005J\u0016\u0010x\u001a\u00020\u0016H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\by\u0010mJ\u001b\u0010z\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b{\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003ø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006}"}, d2 = {"Lkotlin/ULong;", "", "data", "", "constructor-impl", "(J)J", "getData$annotations", "()V", "and", "other", "and-VKZWuLQ", "(JJ)J", "compareTo", "", "Lkotlin/UByte;", "compareTo-7apg3OU", "(JB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(JI)I", "compareTo-VKZWuLQ", "(JJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(JS)I", "dec", "dec-s-VKNKU", "div", "div-7apg3OU", "(JB)J", "div-WZ4Q5Ns", "(JI)J", "div-VKZWuLQ", "div-xj2QHRw", "(JS)J", "equals", "", "", "equals-impl", "(JLjava/lang/Object;)Z", "floorDiv", "floorDiv-7apg3OU", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "floorDiv-xj2QHRw", "hashCode", "hashCode-impl", "(J)I", "inc", "inc-s-VKNKU", "inv", "inv-s-VKNKU", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "mod", "mod-7apg3OU", "(JB)B", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "mod-xj2QHRw", "(JS)S", "or", "or-VKZWuLQ", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/ULongRange;", "rangeTo-VKZWuLQ", "(JJ)Lkotlin/ranges/ULongRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "shl", "bitCount", "shl-s-VKNKU", "shr", "shr-s-VKNKU", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "", "toByte-impl", "(J)B", "toDouble", "", "toDouble-impl", "(J)D", "toFloat", "", "toFloat-impl", "(J)F", "toInt", "toInt-impl", "toLong", "toLong-impl", "toShort", "", "toShort-impl", "(J)S", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "toUByte", "toUByte-w2LRezQ", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toUShort", "toUShort-Mh2AYeg", "xor", "xor-VKZWuLQ", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 6, 0}, xi = 48)
@JvmInline
public final class ULong implements Comparable<ULong> {
    public static final long MAX_VALUE = -1;
    public static final long MIN_VALUE = 0;
    public static final int SIZE_BITS = 64;
    public static final int SIZE_BYTES = 8;
    private final long data;

    private ULong(long j) {
        this.data = j;
    }

    private static final long m393andVKZWuLQ(long j, long j2) {
        return m400constructorimpl(j & j2);
    }

    public static final ULong m394boximpl(long j) {
        return new ULong(j);
    }

    private static final int m395compareTo7apg3OU(long j, byte b) {
        return UnsignedKt.ulongCompare(j, m400constructorimpl(b & 255));
    }

    private int m396compareToVKZWuLQ(long j) {
        return UnsignedKt.ulongCompare(getData(), j);
    }

    private static int m397compareToVKZWuLQ(long j, long j2) {
        return UnsignedKt.ulongCompare(j, j2);
    }

    private static final int m398compareToWZ4Q5Ns(long j, int i) {
        return UnsignedKt.ulongCompare(j, m400constructorimpl(i & 4294967295L));
    }

    private static final int m399compareToxj2QHRw(long j, short s) {
        return UnsignedKt.ulongCompare(j, m400constructorimpl(s & 65535));
    }

    public static long m400constructorimpl(long j) {
        return j;
    }

    private static final long m401decsVKNKU(long j) {
        return m400constructorimpl(j - 1);
    }

    private static final long m402div7apg3OU(long j, byte b) {
        return UnsignedKt.m577ulongDivideeb3DHEI(j, m400constructorimpl(b & 255));
    }

    private static final long m403divVKZWuLQ(long j, long j2) {
        return UnsignedKt.m577ulongDivideeb3DHEI(j, j2);
    }

    private static final long m404divWZ4Q5Ns(long j, int i) {
        return UnsignedKt.m577ulongDivideeb3DHEI(j, m400constructorimpl(i & 4294967295L));
    }

    private static final long m405divxj2QHRw(long j, short s) {
        return UnsignedKt.m577ulongDivideeb3DHEI(j, m400constructorimpl(s & 65535));
    }

    public static boolean m406equalsimpl(long j, Object obj) {
        return (obj instanceof ULong) && j == ((ULong) obj).getData();
    }

    public static final boolean m407equalsimpl0(long j, long j2) {
        return j == j2;
    }

    private static final long m408floorDiv7apg3OU(long j, byte b) {
        return UnsignedKt.m577ulongDivideeb3DHEI(j, m400constructorimpl(b & 255));
    }

    private static final long m409floorDivVKZWuLQ(long j, long j2) {
        return UnsignedKt.m577ulongDivideeb3DHEI(j, j2);
    }

    private static final long m410floorDivWZ4Q5Ns(long j, int i) {
        return UnsignedKt.m577ulongDivideeb3DHEI(j, m400constructorimpl(i & 4294967295L));
    }

    private static final long m411floorDivxj2QHRw(long j, short s) {
        return UnsignedKt.m577ulongDivideeb3DHEI(j, m400constructorimpl(s & 65535));
    }

    public static void getData$annotations() {
    }

    public static int m412hashCodeimpl(long j) {
        return (int) (j ^ (j >>> 32));
    }

    private static final long m413incsVKNKU(long j) {
        return m400constructorimpl(j + 1);
    }

    private static final long m414invsVKNKU(long j) {
        return m400constructorimpl(j ^ (-1));
    }

    private static final long m415minus7apg3OU(long j, byte b) {
        return m400constructorimpl(j - m400constructorimpl(b & 255));
    }

    private static final long m416minusVKZWuLQ(long j, long j2) {
        return m400constructorimpl(j - j2);
    }

    private static final long m417minusWZ4Q5Ns(long j, int i) {
        return m400constructorimpl(j - m400constructorimpl(i & 4294967295L));
    }

    private static final long m418minusxj2QHRw(long j, short s) {
        return m400constructorimpl(j - m400constructorimpl(s & 65535));
    }

    private static final byte m419mod7apg3OU(long j, byte b) {
        return UByte.m246constructorimpl((byte) UnsignedKt.m578ulongRemaindereb3DHEI(j, m400constructorimpl(b & 255)));
    }

    private static final long m420modVKZWuLQ(long j, long j2) {
        return UnsignedKt.m578ulongRemaindereb3DHEI(j, j2);
    }

    private static final int m421modWZ4Q5Ns(long j, int i) {
        return UInt.m322constructorimpl((int) UnsignedKt.m578ulongRemaindereb3DHEI(j, m400constructorimpl(i & 4294967295L)));
    }

    private static final short m422modxj2QHRw(long j, short s) {
        return UShort.m506constructorimpl((short) UnsignedKt.m578ulongRemaindereb3DHEI(j, m400constructorimpl(s & 65535)));
    }

    private static final long m423orVKZWuLQ(long j, long j2) {
        return m400constructorimpl(j | j2);
    }

    private static final long m424plus7apg3OU(long j, byte b) {
        return m400constructorimpl(j + m400constructorimpl(b & 255));
    }

    private static final long m425plusVKZWuLQ(long j, long j2) {
        return m400constructorimpl(j + j2);
    }

    private static final long m426plusWZ4Q5Ns(long j, int i) {
        return m400constructorimpl(j + m400constructorimpl(i & 4294967295L));
    }

    private static final long m427plusxj2QHRw(long j, short s) {
        return m400constructorimpl(j + m400constructorimpl(s & 65535));
    }

    private static final ULongRange m428rangeToVKZWuLQ(long j, long j2) {
        return new ULongRange(j, j2, null);
    }

    private static final long m429rem7apg3OU(long j, byte b) {
        return UnsignedKt.m578ulongRemaindereb3DHEI(j, m400constructorimpl(b & 255));
    }

    private static final long m430remVKZWuLQ(long j, long j2) {
        return UnsignedKt.m578ulongRemaindereb3DHEI(j, j2);
    }

    private static final long m431remWZ4Q5Ns(long j, int i) {
        return UnsignedKt.m578ulongRemaindereb3DHEI(j, m400constructorimpl(i & 4294967295L));
    }

    private static final long m432remxj2QHRw(long j, short s) {
        return UnsignedKt.m578ulongRemaindereb3DHEI(j, m400constructorimpl(s & 65535));
    }

    private static final long m433shlsVKNKU(long j, int i) {
        return m400constructorimpl(j << i);
    }

    private static final long m434shrsVKNKU(long j, int i) {
        return m400constructorimpl(j >>> i);
    }

    private static final long m435times7apg3OU(long j, byte b) {
        return m400constructorimpl(j * m400constructorimpl(b & 255));
    }

    private static final long m436timesVKZWuLQ(long j, long j2) {
        return m400constructorimpl(j * j2);
    }

    private static final long m437timesWZ4Q5Ns(long j, int i) {
        return m400constructorimpl(j * m400constructorimpl(i & 4294967295L));
    }

    private static final long m438timesxj2QHRw(long j, short s) {
        return m400constructorimpl(j * m400constructorimpl(s & 65535));
    }

    private static final byte m439toByteimpl(long j) {
        return (byte) j;
    }

    private static final double m440toDoubleimpl(long j) {
        return UnsignedKt.ulongToDouble(j);
    }

    private static final float m441toFloatimpl(long j) {
        return (float) UnsignedKt.ulongToDouble(j);
    }

    private static final int m442toIntimpl(long j) {
        return (int) j;
    }

    private static final long m443toLongimpl(long j) {
        return j;
    }

    private static final short m444toShortimpl(long j) {
        return (short) j;
    }

    public static String m445toStringimpl(long j) {
        return UnsignedKt.ulongToString(j);
    }

    private static final byte m446toUBytew2LRezQ(long j) {
        return UByte.m246constructorimpl((byte) j);
    }

    private static final int m447toUIntpVg5ArA(long j) {
        return UInt.m322constructorimpl((int) j);
    }

    private static final long m448toULongsVKNKU(long j) {
        return j;
    }

    private static final short m449toUShortMh2AYeg(long j) {
        return UShort.m506constructorimpl((short) j);
    }

    private static final long m450xorVKZWuLQ(long j, long j2) {
        return m400constructorimpl(j ^ j2);
    }

    @Override
    public int compareTo(ULong uLong) {
        return UnsignedKt.ulongCompare(getData(), uLong.getData());
    }

    public boolean equals(Object obj) {
        return m406equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m412hashCodeimpl(this.data);
    }

    public String toString() {
        return m445toStringimpl(this.data);
    }

    public final long getData() {
        return this.data;
    }
}
