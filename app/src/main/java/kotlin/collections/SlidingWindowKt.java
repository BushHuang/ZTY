package kotlin.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;

@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0000\u001aH\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u00070\u0006\"\u0004\b\u0000\u0010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\u00062\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0000\u001aD\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u00070\u000e\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u000e2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0000¨\u0006\u000f"}, d2 = {"checkWindowSizeStep", "", "size", "", "step", "windowedIterator", "", "", "T", "iterator", "partialWindows", "", "reuseBuffer", "windowedSequence", "Lkotlin/sequences/Sequence;", "kotlin-stdlib"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class SlidingWindowKt {

    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u0003H\u008a@"}, d2 = {"<anonymous>", "", "T", "Lkotlin/sequences/SequenceScope;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlin.collections.SlidingWindowKt$windowedIterator$1", f = "SlidingWindow.kt", i = {0, 0, 0, 2, 2, 3, 3}, l = {34, 40, 49, 55, 58}, m = "invokeSuspend", n = {"$this$iterator", "buffer", "gap", "$this$iterator", "buffer", "$this$iterator", "buffer"}, s = {"L$0", "L$1", "I$0", "L$0", "L$1", "L$0", "L$1"})
    static final class AnonymousClass1<T> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super List<? extends T>>, Continuation<? super Unit>, Object> {
        final Iterator<T> $iterator;
        final boolean $partialWindows;
        final boolean $reuseBuffer;
        final int $size;
        final int $step;
        int I$0;
        private Object L$0;
        Object L$1;
        Object L$2;
        int label;

        AnonymousClass1(int i, int i2, Iterator<? extends T> it, boolean z, boolean z2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$size = i;
            this.$step = i2;
            this.$iterator = it;
            this.$reuseBuffer = z;
            this.$partialWindows = z2;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$size, this.$step, this.$iterator, this.$reuseBuffer, this.$partialWindows, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override
        public final Object invoke(SequenceScope<? super List<? extends T>> sequenceScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            int i;
            RingBuffer ringBuffer;
            Iterator<T> it;
            SequenceScope sequenceScope;
            AnonymousClass1<T> anonymousClass1;
            int i2;
            SequenceScope sequenceScope2;
            AnonymousClass1<T> anonymousClass12;
            ArrayList arrayList;
            Iterator<T> it2;
            RingBuffer ringBuffer2;
            SequenceScope sequenceScope3;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                SequenceScope sequenceScope4 = (SequenceScope) this.L$0;
                int iCoerceAtMost = RangesKt.coerceAtMost(this.$size, 1024);
                i = this.$step - this.$size;
                if (i < 0) {
                    ringBuffer = new RingBuffer(iCoerceAtMost);
                    it = this.$iterator;
                    sequenceScope = sequenceScope4;
                    anonymousClass1 = this;
                    while (it.hasNext()) {
                    }
                    if (anonymousClass1.$partialWindows) {
                    }
                    return Unit.INSTANCE;
                }
                ArrayList arrayList2 = new ArrayList(iCoerceAtMost);
                i2 = 0;
                sequenceScope2 = sequenceScope4;
                anonymousClass12 = this;
                arrayList = arrayList2;
                it2 = this.$iterator;
                while (it2.hasNext()) {
                }
                if (!arrayList.isEmpty()) {
                    anonymousClass12.L$0 = null;
                    anonymousClass12.L$1 = null;
                    anonymousClass12.L$2 = null;
                    anonymousClass12.label = 2;
                    if (sequenceScope2.yield(arrayList, anonymousClass12) == coroutine_suspended) {
                    }
                }
                return Unit.INSTANCE;
            }
            if (i3 == 1) {
                int i4 = this.I$0;
                it2 = (Iterator) this.L$2;
                arrayList = (ArrayList) this.L$1;
                sequenceScope2 = (SequenceScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                anonymousClass12 = this;
                i = i4;
                if (anonymousClass12.$reuseBuffer) {
                    arrayList = new ArrayList(anonymousClass12.$size);
                } else {
                    arrayList.clear();
                }
                i2 = i;
                while (it2.hasNext()) {
                    T next = it2.next();
                    if (i2 > 0) {
                        i2--;
                    } else {
                        arrayList.add(next);
                        if (arrayList.size() == anonymousClass12.$size) {
                            anonymousClass12.L$0 = sequenceScope2;
                            anonymousClass12.L$1 = arrayList;
                            anonymousClass12.L$2 = it2;
                            anonymousClass12.I$0 = i;
                            anonymousClass12.label = 1;
                            if (sequenceScope2.yield(arrayList, anonymousClass12) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            if (anonymousClass12.$reuseBuffer) {
                            }
                            i2 = i;
                            while (it2.hasNext()) {
                            }
                        }
                    }
                }
                if ((!arrayList.isEmpty()) && (anonymousClass12.$partialWindows || arrayList.size() == anonymousClass12.$size)) {
                    anonymousClass12.L$0 = null;
                    anonymousClass12.L$1 = null;
                    anonymousClass12.L$2 = null;
                    anonymousClass12.label = 2;
                    if (sequenceScope2.yield(arrayList, anonymousClass12) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return Unit.INSTANCE;
            }
            if (i3 != 2) {
                if (i3 == 3) {
                    it = (Iterator) this.L$2;
                    ringBuffer = (RingBuffer) this.L$1;
                    sequenceScope = (SequenceScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    anonymousClass1 = this;
                    ringBuffer.removeFirst(anonymousClass1.$step);
                    while (it.hasNext()) {
                        ringBuffer.add((RingBuffer) it.next());
                        if (ringBuffer.isFull()) {
                            int size = ringBuffer.size();
                            int i5 = anonymousClass1.$size;
                            if (size >= i5) {
                                List arrayList3 = anonymousClass1.$reuseBuffer ? ringBuffer : new ArrayList(ringBuffer);
                                anonymousClass1.L$0 = sequenceScope;
                                anonymousClass1.L$1 = ringBuffer;
                                anonymousClass1.L$2 = it;
                                anonymousClass1.label = 3;
                                if (sequenceScope.yield(arrayList3, anonymousClass1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                ringBuffer.removeFirst(anonymousClass1.$step);
                                while (it.hasNext()) {
                                }
                            } else {
                                ringBuffer = ringBuffer.expanded(i5);
                            }
                        }
                    }
                    if (anonymousClass1.$partialWindows) {
                        ringBuffer2 = ringBuffer;
                        sequenceScope3 = sequenceScope;
                        if (ringBuffer2.size() <= anonymousClass1.$step) {
                        }
                    }
                    return Unit.INSTANCE;
                }
                if (i3 == 4) {
                    ringBuffer2 = (RingBuffer) this.L$1;
                    sequenceScope3 = (SequenceScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    anonymousClass1 = this;
                    ringBuffer2.removeFirst(anonymousClass1.$step);
                    if (ringBuffer2.size() <= anonymousClass1.$step) {
                        List arrayList4 = anonymousClass1.$reuseBuffer ? ringBuffer2 : new ArrayList(ringBuffer2);
                        anonymousClass1.L$0 = sequenceScope3;
                        anonymousClass1.L$1 = ringBuffer2;
                        anonymousClass1.L$2 = null;
                        anonymousClass1.label = 4;
                        if (sequenceScope3.yield(arrayList4, anonymousClass1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        ringBuffer2.removeFirst(anonymousClass1.$step);
                        if (ringBuffer2.size() <= anonymousClass1.$step) {
                            if (!ringBuffer2.isEmpty()) {
                                anonymousClass1.L$0 = null;
                                anonymousClass1.L$1 = null;
                                anonymousClass1.L$2 = null;
                                anonymousClass1.label = 5;
                                if (sequenceScope3.yield(ringBuffer2, anonymousClass1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            }
                            return Unit.INSTANCE;
                        }
                    }
                } else if (i3 != 5) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
    }

    public static final void checkWindowSizeStep(int i, int i2) {
        String str;
        if (i > 0 && i2 > 0) {
            return;
        }
        if (i != i2) {
            str = "Both size " + i + " and step " + i2 + " must be greater than zero.";
        } else {
            str = "size " + i + " must be greater than zero.";
        }
        throw new IllegalArgumentException(str.toString());
    }

    public static final <T> Iterator<List<T>> windowedIterator(Iterator<? extends T> it, int i, int i2, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(it, "iterator");
        return !it.hasNext() ? EmptyIterator.INSTANCE : SequencesKt.iterator(new AnonymousClass1(i, i2, it, z2, z, null));
    }

    public static final <T> Sequence<List<T>> windowedSequence(final Sequence<? extends T> sequence, final int i, final int i2, final boolean z, final boolean z2) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        checkWindowSizeStep(i, i2);
        return new Sequence<List<? extends T>>() {
            @Override
            public Iterator<List<? extends T>> iterator() {
                return SlidingWindowKt.windowedIterator(sequence.iterator(), i, i2, z, z2);
            }
        };
    }
}
