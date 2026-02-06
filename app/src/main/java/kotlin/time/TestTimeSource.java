package kotlin.time;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ\u001b\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b\f\u0010\nJ\b\u0010\r\u001a\u00020\u0004H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"Lkotlin/time/TestTimeSource;", "Lkotlin/time/AbstractLongTimeSource;", "()V", "reading", "", "overflow", "", "duration", "Lkotlin/time/Duration;", "overflow-LRDsOJo", "(J)V", "plusAssign", "plusAssign-LRDsOJo", "read", "kotlin-stdlib"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TestTimeSource extends AbstractLongTimeSource {
    private long reading;

    public TestTimeSource() {
        super(DurationUnit.NANOSECONDS);
    }

    private final void m1638overflowLRDsOJo(long duration) {
        throw new IllegalStateException("TestTimeSource will overflow if its reading " + this.reading + "ns is advanced by " + ((Object) Duration.m1560toStringimpl(duration)) + '.');
    }

    public final void m1639plusAssignLRDsOJo(long duration) {
        long j;
        long jM1557toLongimpl = Duration.m1557toLongimpl(duration, getUnit());
        if (jM1557toLongimpl == Long.MIN_VALUE || jM1557toLongimpl == Long.MAX_VALUE) {
            double dM1554toDoubleimpl = Duration.m1554toDoubleimpl(duration, getUnit());
            double d = this.reading;
            Double.isNaN(d);
            double d2 = d + dM1554toDoubleimpl;
            if (d2 > 9.223372036854776E18d || d2 < -9.223372036854776E18d) {
                m1638overflowLRDsOJo(duration);
            }
            j = (long) d2;
        } else {
            long j2 = this.reading;
            j = j2 + jM1557toLongimpl;
            if ((jM1557toLongimpl ^ j2) >= 0 && (j2 ^ j) < 0) {
                m1638overflowLRDsOJo(duration);
            }
        }
        this.reading = j;
    }

    @Override
    protected long getReading() {
        return this.reading;
    }
}
