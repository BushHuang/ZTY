package kotlin;

import kotlin.jvm.JvmInline;
import kotlin.ranges.UIntRange;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0087@\u0018\u0000 y2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001yB\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0000H\u0097\nø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u000bJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0016\u0010\u0017\u001a\u00020\u0000H\u0087\nø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u0005J\u001b\u0010\u0019\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u000fJ\u001b\u0010\u0019\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u000bJ\u001b\u0010\u0019\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u0019\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u0016J\u001a\u0010\u001f\u001a\u00020 2\b\u0010\t\u001a\u0004\u0018\u00010!HÖ\u0003¢\u0006\u0004\b\"\u0010#J\u001b\u0010$\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\bø\u0001\u0000¢\u0006\u0004\b%\u0010\u000fJ\u001b\u0010$\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b&\u0010\u000bJ\u001b\u0010$\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\bø\u0001\u0000¢\u0006\u0004\b'\u0010\u001dJ\u001b\u0010$\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\bø\u0001\u0000¢\u0006\u0004\b(\u0010\u0016J\u0010\u0010)\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b*\u0010\u0005J\u0016\u0010+\u001a\u00020\u0000H\u0087\nø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b,\u0010\u0005J\u0016\u0010-\u001a\u00020\u0000H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b.\u0010\u0005J\u001b\u0010/\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\b0\u0010\u000fJ\u001b\u0010/\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b1\u0010\u000bJ\u001b\u0010/\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b2\u0010\u001dJ\u001b\u0010/\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b3\u0010\u0016J\u001b\u00104\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\rH\u0087\bø\u0001\u0000¢\u0006\u0004\b5\u00106J\u001b\u00104\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b7\u0010\u000bJ\u001b\u00104\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\bø\u0001\u0000¢\u0006\u0004\b8\u0010\u001dJ\u001b\u00104\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\bø\u0001\u0000¢\u0006\u0004\b9\u0010:J\u001b\u0010;\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b<\u0010\u000bJ\u001b\u0010=\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\b>\u0010\u000fJ\u001b\u0010=\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b?\u0010\u000bJ\u001b\u0010=\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b@\u0010\u001dJ\u001b\u0010=\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\bA\u0010\u0016J\u001b\u0010B\u001a\u00020C2\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bD\u0010EJ\u001b\u0010F\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\bG\u0010\u000fJ\u001b\u0010F\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bH\u0010\u000bJ\u001b\u0010F\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bI\u0010\u001dJ\u001b\u0010F\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\bJ\u0010\u0016J\u001e\u0010K\u001a\u00020\u00002\u0006\u0010L\u001a\u00020\u0003H\u0087\fø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bM\u0010\u000bJ\u001e\u0010N\u001a\u00020\u00002\u0006\u0010L\u001a\u00020\u0003H\u0087\fø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bO\u0010\u000bJ\u001b\u0010P\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\bQ\u0010\u000fJ\u001b\u0010P\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bR\u0010\u000bJ\u001b\u0010P\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bS\u0010\u001dJ\u001b\u0010P\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\bT\u0010\u0016J\u0010\u0010U\u001a\u00020VH\u0087\b¢\u0006\u0004\bW\u0010XJ\u0010\u0010Y\u001a\u00020ZH\u0087\b¢\u0006\u0004\b[\u0010\\J\u0010\u0010]\u001a\u00020^H\u0087\b¢\u0006\u0004\b_\u0010`J\u0010\u0010a\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\bb\u0010\u0005J\u0010\u0010c\u001a\u00020dH\u0087\b¢\u0006\u0004\be\u0010fJ\u0010\u0010g\u001a\u00020hH\u0087\b¢\u0006\u0004\bi\u0010jJ\u000f\u0010k\u001a\u00020lH\u0016¢\u0006\u0004\bm\u0010nJ\u0016\u0010o\u001a\u00020\rH\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bp\u0010XJ\u0016\u0010q\u001a\u00020\u0000H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\br\u0010\u0005J\u0016\u0010s\u001a\u00020\u0011H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bt\u0010fJ\u0016\u0010u\u001a\u00020\u0014H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bv\u0010jJ\u001b\u0010w\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\bx\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003ø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006z"}, d2 = {"Lkotlin/UInt;", "", "data", "", "constructor-impl", "(I)I", "getData$annotations", "()V", "and", "other", "and-WZ4Q5Ns", "(II)I", "compareTo", "Lkotlin/UByte;", "compareTo-7apg3OU", "(IB)I", "compareTo-WZ4Q5Ns", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(IJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(IS)I", "dec", "dec-pVg5ArA", "div", "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(IJ)J", "div-xj2QHRw", "equals", "", "", "equals-impl", "(ILjava/lang/Object;)Z", "floorDiv", "floorDiv-7apg3OU", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "floorDiv-xj2QHRw", "hashCode", "hashCode-impl", "inc", "inc-pVg5ArA", "inv", "inv-pVg5ArA", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "mod", "mod-7apg3OU", "(IB)B", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "mod-xj2QHRw", "(IS)S", "or", "or-WZ4Q5Ns", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-WZ4Q5Ns", "(II)Lkotlin/ranges/UIntRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "shl", "bitCount", "shl-pVg5ArA", "shr", "shr-pVg5ArA", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "", "toByte-impl", "(I)B", "toDouble", "", "toDouble-impl", "(I)D", "toFloat", "", "toFloat-impl", "(I)F", "toInt", "toInt-impl", "toLong", "", "toLong-impl", "(I)J", "toShort", "", "toShort-impl", "(I)S", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "toUByte", "toUByte-w2LRezQ", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toUShort", "toUShort-Mh2AYeg", "xor", "xor-WZ4Q5Ns", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 6, 0}, xi = 48)
@JvmInline
public final class UInt implements Comparable<UInt> {
    public static final int MAX_VALUE = -1;
    public static final int MIN_VALUE = 0;
    public static final int SIZE_BITS = 32;
    public static final int SIZE_BYTES = 4;
    private final int data;

    private UInt(int i) {
        this.data = i;
    }

    private static final int m315andWZ4Q5Ns(int i, int i2) {
        return m322constructorimpl(i & i2);
    }

    public static final UInt m316boximpl(int i) {
        return new UInt(i);
    }

    private static final int m317compareTo7apg3OU(int i, byte b) {
        return UnsignedKt.uintCompare(i, m322constructorimpl(b & 255));
    }

    private static final int m318compareToVKZWuLQ(int i, long j) {
        return UnsignedKt.ulongCompare(ULong.m400constructorimpl(i & 4294967295L), j);
    }

    private int m319compareToWZ4Q5Ns(int i) {
        return UnsignedKt.uintCompare(getData(), i);
    }

    private static int m320compareToWZ4Q5Ns(int i, int i2) {
        return UnsignedKt.uintCompare(i, i2);
    }

    private static final int m321compareToxj2QHRw(int i, short s) {
        return UnsignedKt.uintCompare(i, m322constructorimpl(s & 65535));
    }

    public static int m322constructorimpl(int i) {
        return i;
    }

    private static final int m323decpVg5ArA(int i) {
        return m322constructorimpl(i - 1);
    }

    private static final int m324div7apg3OU(int i, byte b) {
        return UnsignedKt.m575uintDivideJ1ME1BU(i, m322constructorimpl(b & 255));
    }

    private static final long m325divVKZWuLQ(int i, long j) {
        return UnsignedKt.m577ulongDivideeb3DHEI(ULong.m400constructorimpl(i & 4294967295L), j);
    }

    private static final int m326divWZ4Q5Ns(int i, int i2) {
        return UnsignedKt.m575uintDivideJ1ME1BU(i, i2);
    }

    private static final int m327divxj2QHRw(int i, short s) {
        return UnsignedKt.m575uintDivideJ1ME1BU(i, m322constructorimpl(s & 65535));
    }

    public static boolean m328equalsimpl(int i, Object obj) {
        return (obj instanceof UInt) && i == ((UInt) obj).getData();
    }

    public static final boolean m329equalsimpl0(int i, int i2) {
        return i == i2;
    }

    private static final int m330floorDiv7apg3OU(int i, byte b) {
        return UnsignedKt.m575uintDivideJ1ME1BU(i, m322constructorimpl(b & 255));
    }

    private static final long m331floorDivVKZWuLQ(int i, long j) {
        return UnsignedKt.m577ulongDivideeb3DHEI(ULong.m400constructorimpl(i & 4294967295L), j);
    }

    private static final int m332floorDivWZ4Q5Ns(int i, int i2) {
        return UnsignedKt.m575uintDivideJ1ME1BU(i, i2);
    }

    private static final int m333floorDivxj2QHRw(int i, short s) {
        return UnsignedKt.m575uintDivideJ1ME1BU(i, m322constructorimpl(s & 65535));
    }

    public static void getData$annotations() {
    }

    public static int m334hashCodeimpl(int i) {
        return i;
    }

    private static final int m335incpVg5ArA(int i) {
        return m322constructorimpl(i + 1);
    }

    private static final int m336invpVg5ArA(int i) {
        return m322constructorimpl(i ^ (-1));
    }

    private static final int m337minus7apg3OU(int i, byte b) {
        return m322constructorimpl(i - m322constructorimpl(b & 255));
    }

    private static final long m338minusVKZWuLQ(int i, long j) {
        return ULong.m400constructorimpl(ULong.m400constructorimpl(i & 4294967295L) - j);
    }

    private static final int m339minusWZ4Q5Ns(int i, int i2) {
        return m322constructorimpl(i - i2);
    }

    private static final int m340minusxj2QHRw(int i, short s) {
        return m322constructorimpl(i - m322constructorimpl(s & 65535));
    }

    private static final byte m341mod7apg3OU(int i, byte b) {
        return UByte.m246constructorimpl((byte) UnsignedKt.m576uintRemainderJ1ME1BU(i, m322constructorimpl(b & 255)));
    }

    private static final long m342modVKZWuLQ(int i, long j) {
        return UnsignedKt.m578ulongRemaindereb3DHEI(ULong.m400constructorimpl(i & 4294967295L), j);
    }

    private static final int m343modWZ4Q5Ns(int i, int i2) {
        return UnsignedKt.m576uintRemainderJ1ME1BU(i, i2);
    }

    private static final short m344modxj2QHRw(int i, short s) {
        return UShort.m506constructorimpl((short) UnsignedKt.m576uintRemainderJ1ME1BU(i, m322constructorimpl(s & 65535)));
    }

    private static final int m345orWZ4Q5Ns(int i, int i2) {
        return m322constructorimpl(i | i2);
    }

    private static final int m346plus7apg3OU(int i, byte b) {
        return m322constructorimpl(i + m322constructorimpl(b & 255));
    }

    private static final long m347plusVKZWuLQ(int i, long j) {
        return ULong.m400constructorimpl(ULong.m400constructorimpl(i & 4294967295L) + j);
    }

    private static final int m348plusWZ4Q5Ns(int i, int i2) {
        return m322constructorimpl(i + i2);
    }

    private static final int m349plusxj2QHRw(int i, short s) {
        return m322constructorimpl(i + m322constructorimpl(s & 65535));
    }

    private static final UIntRange m350rangeToWZ4Q5Ns(int i, int i2) {
        return new UIntRange(i, i2, null);
    }

    private static final int m351rem7apg3OU(int i, byte b) {
        return UnsignedKt.m576uintRemainderJ1ME1BU(i, m322constructorimpl(b & 255));
    }

    private static final long m352remVKZWuLQ(int i, long j) {
        return UnsignedKt.m578ulongRemaindereb3DHEI(ULong.m400constructorimpl(i & 4294967295L), j);
    }

    private static final int m353remWZ4Q5Ns(int i, int i2) {
        return UnsignedKt.m576uintRemainderJ1ME1BU(i, i2);
    }

    private static final int m354remxj2QHRw(int i, short s) {
        return UnsignedKt.m576uintRemainderJ1ME1BU(i, m322constructorimpl(s & 65535));
    }

    private static final int m355shlpVg5ArA(int i, int i2) {
        return m322constructorimpl(i << i2);
    }

    private static final int m356shrpVg5ArA(int i, int i2) {
        return m322constructorimpl(i >>> i2);
    }

    private static final int m357times7apg3OU(int i, byte b) {
        return m322constructorimpl(i * m322constructorimpl(b & 255));
    }

    private static final long m358timesVKZWuLQ(int i, long j) {
        return ULong.m400constructorimpl(ULong.m400constructorimpl(i & 4294967295L) * j);
    }

    private static final int m359timesWZ4Q5Ns(int i, int i2) {
        return m322constructorimpl(i * i2);
    }

    private static final int m360timesxj2QHRw(int i, short s) {
        return m322constructorimpl(i * m322constructorimpl(s & 65535));
    }

    private static final byte m361toByteimpl(int i) {
        return (byte) i;
    }

    private static final double m362toDoubleimpl(int i) {
        return UnsignedKt.uintToDouble(i);
    }

    private static final float m363toFloatimpl(int i) {
        return (float) UnsignedKt.uintToDouble(i);
    }

    private static final int m364toIntimpl(int i) {
        return i;
    }

    private static final long m365toLongimpl(int i) {
        return i & 4294967295L;
    }

    private static final short m366toShortimpl(int i) {
        return (short) i;
    }

    public static String m367toStringimpl(int i) {
        return String.valueOf(i & 4294967295L);
    }

    private static final byte m368toUBytew2LRezQ(int i) {
        return UByte.m246constructorimpl((byte) i);
    }

    private static final int m369toUIntpVg5ArA(int i) {
        return i;
    }

    private static final long m370toULongsVKNKU(int i) {
        return ULong.m400constructorimpl(i & 4294967295L);
    }

    private static final short m371toUShortMh2AYeg(int i) {
        return UShort.m506constructorimpl((short) i);
    }

    private static final int m372xorWZ4Q5Ns(int i, int i2) {
        return m322constructorimpl(i ^ i2);
    }

    @Override
    public int compareTo(UInt uInt) {
        return UnsignedKt.uintCompare(getData(), uInt.getData());
    }

    public boolean equals(Object obj) {
        return m328equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m334hashCodeimpl(this.data);
    }

    public String toString() {
        return m367toStringimpl(this.data);
    }

    public final int getData() {
        return this.data;
    }
}
