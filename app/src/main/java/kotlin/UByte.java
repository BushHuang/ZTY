package kotlin;

import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0005\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0087@\u0018\u0000 t2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001tB\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\u0097\nø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u0000H\u0087\nø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001a\u0010\u0005J\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u000fJ\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u0012J\u001b\u0010\u001b\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b \u0010\u0018J\u001a\u0010!\u001a\u00020\"2\b\u0010\t\u001a\u0004\u0018\u00010#HÖ\u0003¢\u0006\u0004\b$\u0010%J\u001b\u0010&\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b'\u0010\u000fJ\u001b\u0010&\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\bø\u0001\u0000¢\u0006\u0004\b(\u0010\u0012J\u001b\u0010&\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\bø\u0001\u0000¢\u0006\u0004\b)\u0010\u001fJ\u001b\u0010&\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\bø\u0001\u0000¢\u0006\u0004\b*\u0010\u0018J\u0010\u0010+\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b,\u0010-J\u0016\u0010.\u001a\u00020\u0000H\u0087\nø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b/\u0010\u0005J\u0016\u00100\u001a\u00020\u0000H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b1\u0010\u0005J\u001b\u00102\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b3\u0010\u000fJ\u001b\u00102\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\b4\u0010\u0012J\u001b\u00102\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\b5\u0010\u001fJ\u001b\u00102\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b6\u0010\u0018J\u001b\u00107\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b8\u0010\u000bJ\u001b\u00107\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\bø\u0001\u0000¢\u0006\u0004\b9\u0010\u0012J\u001b\u00107\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\bø\u0001\u0000¢\u0006\u0004\b:\u0010\u001fJ\u001b\u00107\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\u0016H\u0087\bø\u0001\u0000¢\u0006\u0004\b;\u0010<J\u001b\u0010=\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b>\u0010\u000bJ\u001b\u0010?\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b@\u0010\u000fJ\u001b\u0010?\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\bA\u0010\u0012J\u001b\u0010?\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\bB\u0010\u001fJ\u001b\u0010?\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\bC\u0010\u0018J\u001b\u0010D\u001a\u00020E2\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bF\u0010GJ\u001b\u0010H\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bI\u0010\u000fJ\u001b\u0010H\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\bJ\u0010\u0012J\u001b\u0010H\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\bK\u0010\u001fJ\u001b\u0010H\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\bL\u0010\u0018J\u001b\u0010M\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bN\u0010\u000fJ\u001b\u0010M\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\bO\u0010\u0012J\u001b\u0010M\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\bP\u0010\u001fJ\u001b\u0010M\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\bQ\u0010\u0018J\u0010\u0010R\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\bS\u0010\u0005J\u0010\u0010T\u001a\u00020UH\u0087\b¢\u0006\u0004\bV\u0010WJ\u0010\u0010X\u001a\u00020YH\u0087\b¢\u0006\u0004\bZ\u0010[J\u0010\u0010\\\u001a\u00020\rH\u0087\b¢\u0006\u0004\b]\u0010-J\u0010\u0010^\u001a\u00020_H\u0087\b¢\u0006\u0004\b`\u0010aJ\u0010\u0010b\u001a\u00020cH\u0087\b¢\u0006\u0004\bd\u0010eJ\u000f\u0010f\u001a\u00020gH\u0016¢\u0006\u0004\bh\u0010iJ\u0016\u0010j\u001a\u00020\u0000H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bk\u0010\u0005J\u0016\u0010l\u001a\u00020\u0010H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bm\u0010-J\u0016\u0010n\u001a\u00020\u0013H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bo\u0010aJ\u0016\u0010p\u001a\u00020\u0016H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bq\u0010eJ\u001b\u0010r\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\bs\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003ø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006u"}, d2 = {"Lkotlin/UByte;", "", "data", "", "constructor-impl", "(B)B", "getData$annotations", "()V", "and", "other", "and-7apg3OU", "(BB)B", "compareTo", "", "compareTo-7apg3OU", "(BB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(BI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(BJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(BS)I", "dec", "dec-w2LRezQ", "div", "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(BJ)J", "div-xj2QHRw", "equals", "", "", "equals-impl", "(BLjava/lang/Object;)Z", "floorDiv", "floorDiv-7apg3OU", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "floorDiv-xj2QHRw", "hashCode", "hashCode-impl", "(B)I", "inc", "inc-w2LRezQ", "inv", "inv-w2LRezQ", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "mod", "mod-7apg3OU", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "mod-xj2QHRw", "(BS)S", "or", "or-7apg3OU", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-7apg3OU", "(BB)Lkotlin/ranges/UIntRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "toByte-impl", "toDouble", "", "toDouble-impl", "(B)D", "toFloat", "", "toFloat-impl", "(B)F", "toInt", "toInt-impl", "toLong", "", "toLong-impl", "(B)J", "toShort", "", "toShort-impl", "(B)S", "toString", "", "toString-impl", "(B)Ljava/lang/String;", "toUByte", "toUByte-w2LRezQ", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toUShort", "toUShort-Mh2AYeg", "xor", "xor-7apg3OU", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 6, 0}, xi = 48)
@JvmInline
public final class UByte implements Comparable<UByte> {
    public static final byte MAX_VALUE = -1;
    public static final byte MIN_VALUE = 0;
    public static final int SIZE_BITS = 8;
    public static final int SIZE_BYTES = 1;
    private final byte data;

    private UByte(byte b) {
        this.data = b;
    }

    private static final byte m239and7apg3OU(byte b, byte b2) {
        return m246constructorimpl((byte) (b & b2));
    }

    public static final UByte m240boximpl(byte b) {
        return new UByte(b);
    }

    private int m241compareTo7apg3OU(byte b) {
        return Intrinsics.compare(getData() & 255, b & 255);
    }

    private static int m242compareTo7apg3OU(byte b, byte b2) {
        return Intrinsics.compare(b & 255, b2 & 255);
    }

    private static final int m243compareToVKZWuLQ(byte b, long j) {
        return UnsignedKt.ulongCompare(ULong.m400constructorimpl(b & 255), j);
    }

    private static final int m244compareToWZ4Q5Ns(byte b, int i) {
        return UnsignedKt.uintCompare(UInt.m322constructorimpl(b & 255), i);
    }

    private static final int m245compareToxj2QHRw(byte b, short s) {
        return Intrinsics.compare(b & 255, s & 65535);
    }

    public static byte m246constructorimpl(byte b) {
        return b;
    }

    private static final byte m247decw2LRezQ(byte b) {
        return m246constructorimpl((byte) (b - 1));
    }

    private static final int m248div7apg3OU(byte b, byte b2) {
        return UnsignedKt.m575uintDivideJ1ME1BU(UInt.m322constructorimpl(b & 255), UInt.m322constructorimpl(b2 & 255));
    }

    private static final long m249divVKZWuLQ(byte b, long j) {
        return UnsignedKt.m577ulongDivideeb3DHEI(ULong.m400constructorimpl(b & 255), j);
    }

    private static final int m250divWZ4Q5Ns(byte b, int i) {
        return UnsignedKt.m575uintDivideJ1ME1BU(UInt.m322constructorimpl(b & 255), i);
    }

    private static final int m251divxj2QHRw(byte b, short s) {
        return UnsignedKt.m575uintDivideJ1ME1BU(UInt.m322constructorimpl(b & 255), UInt.m322constructorimpl(s & 65535));
    }

    public static boolean m252equalsimpl(byte b, Object obj) {
        return (obj instanceof UByte) && b == ((UByte) obj).getData();
    }

    public static final boolean m253equalsimpl0(byte b, byte b2) {
        return b == b2;
    }

    private static final int m254floorDiv7apg3OU(byte b, byte b2) {
        return UnsignedKt.m575uintDivideJ1ME1BU(UInt.m322constructorimpl(b & 255), UInt.m322constructorimpl(b2 & 255));
    }

    private static final long m255floorDivVKZWuLQ(byte b, long j) {
        return UnsignedKt.m577ulongDivideeb3DHEI(ULong.m400constructorimpl(b & 255), j);
    }

    private static final int m256floorDivWZ4Q5Ns(byte b, int i) {
        return UnsignedKt.m575uintDivideJ1ME1BU(UInt.m322constructorimpl(b & 255), i);
    }

    private static final int m257floorDivxj2QHRw(byte b, short s) {
        return UnsignedKt.m575uintDivideJ1ME1BU(UInt.m322constructorimpl(b & 255), UInt.m322constructorimpl(s & 65535));
    }

    public static void getData$annotations() {
    }

    public static int m258hashCodeimpl(byte b) {
        return b;
    }

    private static final byte m259incw2LRezQ(byte b) {
        return m246constructorimpl((byte) (b + 1));
    }

    private static final byte m260invw2LRezQ(byte b) {
        return m246constructorimpl((byte) (b ^ (-1)));
    }

    private static final int m261minus7apg3OU(byte b, byte b2) {
        return UInt.m322constructorimpl(UInt.m322constructorimpl(b & 255) - UInt.m322constructorimpl(b2 & 255));
    }

    private static final long m262minusVKZWuLQ(byte b, long j) {
        return ULong.m400constructorimpl(ULong.m400constructorimpl(b & 255) - j);
    }

    private static final int m263minusWZ4Q5Ns(byte b, int i) {
        return UInt.m322constructorimpl(UInt.m322constructorimpl(b & 255) - i);
    }

    private static final int m264minusxj2QHRw(byte b, short s) {
        return UInt.m322constructorimpl(UInt.m322constructorimpl(b & 255) - UInt.m322constructorimpl(s & 65535));
    }

    private static final byte m265mod7apg3OU(byte b, byte b2) {
        return m246constructorimpl((byte) UnsignedKt.m576uintRemainderJ1ME1BU(UInt.m322constructorimpl(b & 255), UInt.m322constructorimpl(b2 & 255)));
    }

    private static final long m266modVKZWuLQ(byte b, long j) {
        return UnsignedKt.m578ulongRemaindereb3DHEI(ULong.m400constructorimpl(b & 255), j);
    }

    private static final int m267modWZ4Q5Ns(byte b, int i) {
        return UnsignedKt.m576uintRemainderJ1ME1BU(UInt.m322constructorimpl(b & 255), i);
    }

    private static final short m268modxj2QHRw(byte b, short s) {
        return UShort.m506constructorimpl((short) UnsignedKt.m576uintRemainderJ1ME1BU(UInt.m322constructorimpl(b & 255), UInt.m322constructorimpl(s & 65535)));
    }

    private static final byte m269or7apg3OU(byte b, byte b2) {
        return m246constructorimpl((byte) (b | b2));
    }

    private static final int m270plus7apg3OU(byte b, byte b2) {
        return UInt.m322constructorimpl(UInt.m322constructorimpl(b & 255) + UInt.m322constructorimpl(b2 & 255));
    }

    private static final long m271plusVKZWuLQ(byte b, long j) {
        return ULong.m400constructorimpl(ULong.m400constructorimpl(b & 255) + j);
    }

    private static final int m272plusWZ4Q5Ns(byte b, int i) {
        return UInt.m322constructorimpl(UInt.m322constructorimpl(b & 255) + i);
    }

    private static final int m273plusxj2QHRw(byte b, short s) {
        return UInt.m322constructorimpl(UInt.m322constructorimpl(b & 255) + UInt.m322constructorimpl(s & 65535));
    }

    private static final UIntRange m274rangeTo7apg3OU(byte b, byte b2) {
        return new UIntRange(UInt.m322constructorimpl(b & 255), UInt.m322constructorimpl(b2 & 255), null);
    }

    private static final int m275rem7apg3OU(byte b, byte b2) {
        return UnsignedKt.m576uintRemainderJ1ME1BU(UInt.m322constructorimpl(b & 255), UInt.m322constructorimpl(b2 & 255));
    }

    private static final long m276remVKZWuLQ(byte b, long j) {
        return UnsignedKt.m578ulongRemaindereb3DHEI(ULong.m400constructorimpl(b & 255), j);
    }

    private static final int m277remWZ4Q5Ns(byte b, int i) {
        return UnsignedKt.m576uintRemainderJ1ME1BU(UInt.m322constructorimpl(b & 255), i);
    }

    private static final int m278remxj2QHRw(byte b, short s) {
        return UnsignedKt.m576uintRemainderJ1ME1BU(UInt.m322constructorimpl(b & 255), UInt.m322constructorimpl(s & 65535));
    }

    private static final int m279times7apg3OU(byte b, byte b2) {
        return UInt.m322constructorimpl(UInt.m322constructorimpl(b & 255) * UInt.m322constructorimpl(b2 & 255));
    }

    private static final long m280timesVKZWuLQ(byte b, long j) {
        return ULong.m400constructorimpl(ULong.m400constructorimpl(b & 255) * j);
    }

    private static final int m281timesWZ4Q5Ns(byte b, int i) {
        return UInt.m322constructorimpl(UInt.m322constructorimpl(b & 255) * i);
    }

    private static final int m282timesxj2QHRw(byte b, short s) {
        return UInt.m322constructorimpl(UInt.m322constructorimpl(b & 255) * UInt.m322constructorimpl(s & 65535));
    }

    private static final byte m283toByteimpl(byte b) {
        return b;
    }

    private static final double m284toDoubleimpl(byte b) {
        return b & 255;
    }

    private static final float m285toFloatimpl(byte b) {
        return b & 255;
    }

    private static final int m286toIntimpl(byte b) {
        return b & 255;
    }

    private static final long m287toLongimpl(byte b) {
        return b & 255;
    }

    private static final short m288toShortimpl(byte b) {
        return (short) (b & 255);
    }

    public static String m289toStringimpl(byte b) {
        return String.valueOf(b & 255);
    }

    private static final byte m290toUBytew2LRezQ(byte b) {
        return b;
    }

    private static final int m291toUIntpVg5ArA(byte b) {
        return UInt.m322constructorimpl(b & 255);
    }

    private static final long m292toULongsVKNKU(byte b) {
        return ULong.m400constructorimpl(b & 255);
    }

    private static final short m293toUShortMh2AYeg(byte b) {
        return UShort.m506constructorimpl((short) (b & 255));
    }

    private static final byte m294xor7apg3OU(byte b, byte b2) {
        return m246constructorimpl((byte) (b ^ b2));
    }

    @Override
    public int compareTo(UByte uByte) {
        return Intrinsics.compare(getData() & 255, uByte.getData() & 255);
    }

    public boolean equals(Object obj) {
        return m252equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m258hashCodeimpl(this.data);
    }

    public String toString() {
        return m289toStringimpl(this.data);
    }

    public final byte getData() {
        return this.data;
    }
}
