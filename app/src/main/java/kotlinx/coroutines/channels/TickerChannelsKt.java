package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.AbstractTimeSource;
import kotlinx.coroutines.AbstractTimeSourceKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.EventLoop_commonKt;
import kotlinx.coroutines.GlobalScope;

@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a/\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a/\u0010\b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a4\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"fixedDelayTicker", "", "delayMillis", "", "initialDelayMillis", "channel", "Lkotlinx/coroutines/channels/SendChannel;", "(JJLkotlinx/coroutines/channels/SendChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fixedPeriodTicker", "ticker", "Lkotlinx/coroutines/channels/ReceiveChannel;", "context", "Lkotlin/coroutines/CoroutineContext;", "mode", "Lkotlinx/coroutines/channels/TickerMode;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class TickerChannelsKt {

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.TickerChannelsKt", f = "TickerChannels.kt", i = {0, 0, 1, 1, 2, 2}, l = {106, 108, 109}, m = "fixedDelayTicker", n = {"channel", "delayMillis", "channel", "delayMillis", "channel", "delayMillis"}, s = {"L$0", "J$0", "L$0", "J$0", "L$0", "J$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        long J$0;
        Object L$0;
        int label;
        Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TickerChannelsKt.fixedDelayTicker(0L, 0L, null, this);
        }
    }

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.TickerChannelsKt", f = "TickerChannels.kt", i = {0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3}, l = {84, 88, 94, 96}, m = "fixedPeriodTicker", n = {"channel", "delayMillis", "deadline", "channel", "deadline", "delayNs", "channel", "deadline", "delayNs", "channel", "deadline", "delayNs"}, s = {"L$0", "J$0", "J$1", "L$0", "J$0", "J$1", "L$0", "J$0", "J$1", "L$0", "J$0", "J$1"})
    static final class AnonymousClass1 extends ContinuationImpl {
        long J$0;
        long J$1;
        Object L$0;
        int label;
        Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TickerChannelsKt.fixedPeriodTicker(0L, 0L, null, this);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.TickerChannelsKt$ticker$3", f = "TickerChannels.kt", i = {}, l = {72, 73}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass3 extends SuspendLambda implements Function2<ProducerScope<? super Unit>, Continuation<? super Unit>, Object> {
        final long $delayMillis;
        final long $initialDelayMillis;
        final TickerMode $mode;
        private Object L$0;
        int label;

        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        public class WhenMappings {
            public static final int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[TickerMode.values().length];
                iArr[TickerMode.FIXED_PERIOD.ordinal()] = 1;
                iArr[TickerMode.FIXED_DELAY.ordinal()] = 2;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        AnonymousClass3(TickerMode tickerMode, long j, long j2, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$mode = tickerMode;
            this.$delayMillis = j;
            this.$initialDelayMillis = j2;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$mode, this.$delayMillis, this.$initialDelayMillis, continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        @Override
        public final Object invoke(ProducerScope<? super Unit> producerScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ProducerScope producerScope = (ProducerScope) this.L$0;
                int i2 = WhenMappings.$EnumSwitchMapping$0[this.$mode.ordinal()];
                if (i2 == 1) {
                    this.label = 1;
                    if (TickerChannelsKt.fixedPeriodTicker(this.$delayMillis, this.$initialDelayMillis, producerScope.getChannel(), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i2 == 2) {
                    this.label = 2;
                    if (TickerChannelsKt.fixedDelayTicker(this.$delayMillis, this.$initialDelayMillis, producerScope.getChannel(), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else {
                if (i != 1 && i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    private static final Object fixedDelayTicker(long j, long j2, SendChannel<? super Unit> sendChannel, Continuation<? super Unit> continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        SendChannel<? super Unit> sendChannel2;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            anonymousClass1.L$0 = sendChannel;
            anonymousClass1.J$0 = j;
            anonymousClass1.label = 1;
            if (DelayKt.delay(j2, anonymousClass1) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            j = anonymousClass1.J$0;
            sendChannel = (SendChannel) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            j = anonymousClass1.J$0;
            sendChannel2 = (SendChannel) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj);
            anonymousClass1.L$0 = sendChannel2;
            anonymousClass1.J$0 = j;
            anonymousClass1.label = 3;
            if (DelayKt.delay(j, anonymousClass1) == coroutine_suspended) {
                return coroutine_suspended;
            }
            sendChannel = sendChannel2;
        } else {
            if (i != 3) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            j = anonymousClass1.J$0;
            sendChannel2 = (SendChannel) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj);
            sendChannel = sendChannel2;
        }
        Unit unit = Unit.INSTANCE;
        anonymousClass1.L$0 = sendChannel;
        anonymousClass1.J$0 = j;
        anonymousClass1.label = 2;
        if (sendChannel.send(unit, anonymousClass1) != coroutine_suspended) {
            return coroutine_suspended;
        }
        sendChannel2 = sendChannel;
        anonymousClass1.L$0 = sendChannel2;
        anonymousClass1.J$0 = j;
        anonymousClass1.label = 3;
        if (DelayKt.delay(j, anonymousClass1) == coroutine_suspended) {
        }
        sendChannel = sendChannel2;
        Unit unit2 = Unit.INSTANCE;
        anonymousClass1.L$0 = sendChannel;
        anonymousClass1.J$0 = j;
        anonymousClass1.label = 2;
        if (sendChannel.send(unit2, anonymousClass1) != coroutine_suspended) {
        }
    }

    private static final Object fixedPeriodTicker(long j, long j2, SendChannel<? super Unit> sendChannel, Continuation<? super Unit> continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        SendChannel sendChannel2;
        long j3;
        long j4;
        long jDelayToNanos;
        long j5;
        long j6;
        SendChannel sendChannel3;
        long jCoerceAtLeast;
        long jDelayNanosToMillis;
        Unit unit;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            AbstractTimeSource timeSource = AbstractTimeSourceKt.getTimeSource();
            Long lBoxLong = timeSource == null ? null : Boxing.boxLong(timeSource.nanoTime());
            long jNanoTime = (lBoxLong == null ? System.nanoTime() : lBoxLong.longValue()) + EventLoop_commonKt.delayToNanos(j2);
            sendChannel2 = sendChannel;
            anonymousClass1.L$0 = sendChannel2;
            anonymousClass1.J$0 = j;
            anonymousClass1.J$1 = jNanoTime;
            anonymousClass1.label = 1;
            if (DelayKt.delay(j2, anonymousClass1) == coroutine_suspended) {
                return coroutine_suspended;
            }
            j3 = jNanoTime;
            j4 = j;
        } else if (i == 1) {
            j3 = anonymousClass1.J$1;
            j4 = anonymousClass1.J$0;
            SendChannel sendChannel4 = (SendChannel) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj);
            sendChannel2 = sendChannel4;
        } else if (i == 2) {
            j6 = anonymousClass1.J$1;
            j5 = anonymousClass1.J$0;
            sendChannel3 = (SendChannel) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj);
            AbstractTimeSource timeSource2 = AbstractTimeSourceKt.getTimeSource();
            if (timeSource2 != null) {
            }
            if (lBoxLong != null) {
            }
            jCoerceAtLeast = RangesKt.coerceAtLeast(j5 - jNanoTime, 0L);
            if (jCoerceAtLeast == 0) {
            }
            jDelayNanosToMillis = EventLoop_commonKt.delayNanosToMillis(jCoerceAtLeast);
            anonymousClass1.L$0 = sendChannel3;
            anonymousClass1.J$0 = j5;
            anonymousClass1.J$1 = j6;
            anonymousClass1.label = 4;
            if (DelayKt.delay(jDelayNanosToMillis, anonymousClass1) == coroutine_suspended) {
            }
            long j7 = j6;
            j3 = j5;
            jDelayToNanos = j7;
            sendChannel2 = sendChannel3;
            long j8 = j3 + jDelayToNanos;
            unit = Unit.INSTANCE;
            anonymousClass1.L$0 = sendChannel2;
            anonymousClass1.J$0 = j8;
            anonymousClass1.J$1 = jDelayToNanos;
            anonymousClass1.label = 2;
            if (sendChannel2.send(unit, anonymousClass1) != coroutine_suspended) {
            }
        } else {
            if (i != 3 && i != 4) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            j6 = anonymousClass1.J$1;
            j5 = anonymousClass1.J$0;
            sendChannel3 = (SendChannel) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj);
            long j72 = j6;
            j3 = j5;
            jDelayToNanos = j72;
            sendChannel2 = sendChannel3;
            long j82 = j3 + jDelayToNanos;
            unit = Unit.INSTANCE;
            anonymousClass1.L$0 = sendChannel2;
            anonymousClass1.J$0 = j82;
            anonymousClass1.J$1 = jDelayToNanos;
            anonymousClass1.label = 2;
            if (sendChannel2.send(unit, anonymousClass1) != coroutine_suspended) {
                return coroutine_suspended;
            }
            sendChannel3 = sendChannel2;
            j6 = jDelayToNanos;
            j5 = j82;
            AbstractTimeSource timeSource22 = AbstractTimeSourceKt.getTimeSource();
            Long lBoxLong2 = timeSource22 != null ? null : Boxing.boxLong(timeSource22.nanoTime());
            long jNanoTime2 = lBoxLong2 != null ? System.nanoTime() : lBoxLong2.longValue();
            jCoerceAtLeast = RangesKt.coerceAtLeast(j5 - jNanoTime2, 0L);
            if (jCoerceAtLeast == 0 || j6 == 0) {
                jDelayNanosToMillis = EventLoop_commonKt.delayNanosToMillis(jCoerceAtLeast);
                anonymousClass1.L$0 = sendChannel3;
                anonymousClass1.J$0 = j5;
                anonymousClass1.J$1 = j6;
                anonymousClass1.label = 4;
                if (DelayKt.delay(jDelayNanosToMillis, anonymousClass1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                long j9 = j6 - ((jNanoTime2 - j5) % j6);
                j5 = jNanoTime2 + j9;
                long jDelayNanosToMillis2 = EventLoop_commonKt.delayNanosToMillis(j9);
                anonymousClass1.L$0 = sendChannel3;
                anonymousClass1.J$0 = j5;
                anonymousClass1.J$1 = j6;
                anonymousClass1.label = 3;
                if (DelayKt.delay(jDelayNanosToMillis2, anonymousClass1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            long j722 = j6;
            j3 = j5;
            jDelayToNanos = j722;
            sendChannel2 = sendChannel3;
            long j822 = j3 + jDelayToNanos;
            unit = Unit.INSTANCE;
            anonymousClass1.L$0 = sendChannel2;
            anonymousClass1.J$0 = j822;
            anonymousClass1.J$1 = jDelayToNanos;
            anonymousClass1.label = 2;
            if (sendChannel2.send(unit, anonymousClass1) != coroutine_suspended) {
            }
        }
        jDelayToNanos = EventLoop_commonKt.delayToNanos(j4);
        long j8222 = j3 + jDelayToNanos;
        unit = Unit.INSTANCE;
        anonymousClass1.L$0 = sendChannel2;
        anonymousClass1.J$0 = j8222;
        anonymousClass1.J$1 = jDelayToNanos;
        anonymousClass1.label = 2;
        if (sendChannel2.send(unit, anonymousClass1) != coroutine_suspended) {
        }
    }

    public static final ReceiveChannel<Unit> ticker(long j, long j2, CoroutineContext coroutineContext, TickerMode tickerMode) {
        if (!(j >= 0)) {
            throw new IllegalArgumentException(("Expected non-negative delay, but has " + j + " ms").toString());
        }
        if (j2 >= 0) {
            return ProduceKt.produce(GlobalScope.INSTANCE, Dispatchers.getUnconfined().plus(coroutineContext), 0, new AnonymousClass3(tickerMode, j, j2, null));
        }
        throw new IllegalArgumentException(("Expected non-negative initial delay, but has " + j2 + " ms").toString());
    }

    public static ReceiveChannel ticker$default(long j, long j2, CoroutineContext coroutineContext, TickerMode tickerMode, int i, Object obj) {
        if ((i & 2) != 0) {
            j2 = j;
        }
        if ((i & 4) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i & 8) != 0) {
            tickerMode = TickerMode.FIXED_PERIOD;
        }
        return ticker(j, j2, coroutineContext, tickerMode);
    }
}
