package kotlinx.coroutines.flow.internal;

import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.YieldKt;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001an\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0004\"\u0004\b\u0002\u0010\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00030\u00012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00040\u00012(\u0010\u0007\u001a$\b\u0001\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\bH\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001a\u0090\u0001\u0010\f\u001a\u00020\r\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u00020\u000f2\u0014\u0010\u0010\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\u000e0\u00010\u00112\u0016\u0010\u0012\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u0001H\u000e\u0018\u00010\u00110\u001329\u0010\u0007\u001a5\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\b¢\u0006\u0002\b\u0014H\u0081@ø\u0001\u0000¢\u0006\u0002\u0010\u0015*\u001c\b\u0002\u0010\u0016\"\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u00172\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0017\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, d2 = {"zipImpl", "Lkotlinx/coroutines/flow/Flow;", "R", "T1", "T2", "flow", "flow2", "transform", "Lkotlin/Function3;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "combineInternal", "", "T", "Lkotlinx/coroutines/flow/FlowCollector;", "flows", "", "arrayFactory", "Lkotlin/Function0;", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/flow/FlowCollector;[Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Update", "Lkotlin/collections/IndexedValue;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class CombineKt {

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "R", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2", f = "Combine.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2}, l = {57, 79, 82}, m = "invokeSuspend", n = {"latestValues", "resultChannel", "lastReceivedEpoch", "remainingAbsentValues", "currentEpoch", "latestValues", "resultChannel", "lastReceivedEpoch", "remainingAbsentValues", "currentEpoch", "latestValues", "resultChannel", "lastReceivedEpoch", "remainingAbsentValues", "currentEpoch"}, s = {"L$0", "L$1", "L$2", "I$0", "I$1", "L$0", "L$1", "L$2", "I$0", "I$1", "L$0", "L$1", "L$2", "I$0", "I$1"})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final Function0<T[]> $arrayFactory;
        final Flow<T>[] $flows;
        final FlowCollector<R> $this_combineInternal;
        final Function3<FlowCollector<? super R>, T[], Continuation<? super Unit>, Object> $transform;
        int I$0;
        int I$1;
        private Object L$0;
        Object L$1;
        Object L$2;
        int label;

        @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "R", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2$1", f = "Combine.kt", i = {}, l = {34}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final Flow<T>[] $flows;
            final int $i;
            final AtomicInteger $nonClosed;
            final Channel<IndexedValue<Object>> $resultChannel;
            int label;

            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0003H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "R", "T", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 6, 0}, xi = 48)
            static final class C00271<T> implements FlowCollector, SuspendFunction {
                final int $i;
                final Channel<IndexedValue<Object>> $resultChannel;

                C00271(Channel<IndexedValue<Object>> channel, int i) {
                    this.$resultChannel = channel;
                    this.$i = i;
                }

                @Override
                public final Object emit(T t, Continuation<? super Unit> continuation) throws Throwable {
                    CombineKt$combineInternal$2$1$1$emit$1 combineKt$combineInternal$2$1$1$emit$1;
                    if (continuation instanceof CombineKt$combineInternal$2$1$1$emit$1) {
                        combineKt$combineInternal$2$1$1$emit$1 = (CombineKt$combineInternal$2$1$1$emit$1) continuation;
                        if ((combineKt$combineInternal$2$1$1$emit$1.label & Integer.MIN_VALUE) != 0) {
                            combineKt$combineInternal$2$1$1$emit$1.label -= Integer.MIN_VALUE;
                        } else {
                            combineKt$combineInternal$2$1$1$emit$1 = new CombineKt$combineInternal$2$1$1$emit$1(this, continuation);
                        }
                    }
                    Object obj = combineKt$combineInternal$2$1$1$emit$1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = combineKt$combineInternal$2$1$1$emit$1.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        Channel<IndexedValue<Object>> channel = this.$resultChannel;
                        IndexedValue<Object> indexedValue = new IndexedValue<>(this.$i, t);
                        combineKt$combineInternal$2$1$1$emit$1.label = 1;
                        if (channel.send(indexedValue, combineKt$combineInternal$2$1$1$emit$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            if (i != 2) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                            return Unit.INSTANCE;
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    combineKt$combineInternal$2$1$1$emit$1.label = 2;
                    if (YieldKt.yield(combineKt$combineInternal$2$1$1$emit$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                }
            }

            AnonymousClass1(Flow<? extends T>[] flowArr, int i, AtomicInteger atomicInteger, Channel<IndexedValue<Object>> channel, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.$flows = flowArr;
                this.$i = i;
                this.$nonClosed = atomicInteger;
                this.$resultChannel = channel;
            }

            @Override
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.$flows, this.$i, this.$nonClosed, this.$resultChannel, continuation);
            }

            @Override
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override
            public final Object invokeSuspend(Object obj) throws Throwable {
                AtomicInteger atomicInteger;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                try {
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.label = 1;
                        if (this.$flows[this.$i].collect(new C00271(this.$resultChannel, this.$i), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    if (atomicInteger.decrementAndGet() == 0) {
                        SendChannel.DefaultImpls.close$default(this.$resultChannel, null, 1, null);
                    }
                    return Unit.INSTANCE;
                } finally {
                    if (this.$nonClosed.decrementAndGet() == 0) {
                        SendChannel.DefaultImpls.close$default(this.$resultChannel, null, 1, null);
                    }
                }
            }
        }

        AnonymousClass2(Flow<? extends T>[] flowArr, Function0<T[]> function0, Function3<? super FlowCollector<? super R>, ? super T[], ? super Continuation<? super Unit>, ? extends Object> function3, FlowCollector<? super R> flowCollector, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$flows = flowArr;
            this.$arrayFactory = function0;
            this.$transform = function3;
            this.$this_combineInternal = flowCollector;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$flows, this.$arrayFactory, this.$transform, this.$this_combineInternal, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            int length;
            Object[] objArr;
            byte[] bArr;
            AnonymousClass2 anonymousClass2;
            Channel channel;
            Object[] objArr2;
            Object holder;
            AnonymousClass2 anonymousClass22;
            Channel channel2;
            byte b;
            int i;
            IndexedValue indexedValue;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            byte b2 = 0;
            int i3 = 2;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                length = this.$flows.length;
                if (length == 0) {
                    return Unit.INSTANCE;
                }
                objArr = new Object[length];
                ArraysKt.fill$default(objArr, NullSurrogateKt.UNINITIALIZED, 0, 0, 6, (Object) null);
                Channel channelChannel$default = ChannelKt.Channel$default(length, null, null, 6, null);
                AtomicInteger atomicInteger = new AtomicInteger(length);
                for (int i4 = 0; i4 < length; i4++) {
                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(this.$flows, i4, atomicInteger, channelChannel$default, null), 3, null);
                    atomicInteger = atomicInteger;
                }
                bArr = new byte[length];
                anonymousClass2 = this;
                channel = channelChannel$default;
            } else if (i2 == 1) {
                ?? r2 = this.I$1;
                i = this.I$0;
                byte[] bArr2 = (byte[]) this.L$2;
                channel2 = (Channel) this.L$1;
                Object[] objArr3 = (Object[]) this.L$0;
                ResultKt.throwOnFailure(obj);
                holder = ((ChannelResult) obj).getHolder();
                objArr2 = objArr3;
                anonymousClass22 = this;
                b = r2;
                bArr = bArr2;
                indexedValue = (IndexedValue) ChannelResult.m1673getOrNullimpl(holder);
                if (indexedValue != null) {
                    return Unit.INSTANCE;
                }
                do {
                    int index = indexedValue.getIndex();
                    Object obj2 = objArr2[index];
                    objArr2[index] = indexedValue.getValue();
                    if (obj2 == NullSurrogateKt.UNINITIALIZED) {
                        i--;
                    }
                    if (bArr[index] == b) {
                        break;
                    }
                    bArr[index] = b;
                    indexedValue = (IndexedValue) ChannelResult.m1673getOrNullimpl(channel2.mo1662tryReceivePtdJZtk());
                } while (indexedValue != null);
                if (i != 0) {
                    length = i;
                    objArr = objArr2;
                    b2 = b;
                    channel = channel2;
                    anonymousClass2 = anonymousClass22;
                } else {
                    Object[] objArr4 = (Object[]) anonymousClass22.$arrayFactory.invoke();
                    if (objArr4 == null) {
                        Function3 function3 = anonymousClass22.$transform;
                        Object obj3 = anonymousClass22.$this_combineInternal;
                        anonymousClass22.L$0 = objArr2;
                        anonymousClass22.L$1 = channel2;
                        anonymousClass22.L$2 = bArr;
                        anonymousClass22.I$0 = i;
                        anonymousClass22.I$1 = b;
                        anonymousClass22.label = i3;
                        if (function3.invoke(obj3, objArr2, anonymousClass22) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        length = i;
                        b2 = b;
                        channel = channel2;
                        anonymousClass2 = anonymousClass22;
                        objArr = objArr2;
                    } else {
                        Object[] objArr5 = objArr2;
                        ArraysKt.copyInto$default(objArr2, objArr4, 0, 0, 0, 14, (Object) null);
                        Function3 function32 = anonymousClass22.$transform;
                        Object obj4 = anonymousClass22.$this_combineInternal;
                        anonymousClass22.L$0 = objArr5;
                        anonymousClass22.L$1 = channel2;
                        anonymousClass22.L$2 = bArr;
                        anonymousClass22.I$0 = i;
                        anonymousClass22.I$1 = b;
                        anonymousClass22.label = 3;
                        if (function32.invoke(obj4, objArr4, anonymousClass22) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        length = i;
                        objArr = objArr5;
                        b2 = b;
                        channel = channel2;
                        anonymousClass2 = anonymousClass22;
                    }
                }
                i3 = 2;
            } else if (i2 == 2) {
                ?? r22 = this.I$1;
                int i5 = this.I$0;
                byte[] bArr3 = (byte[]) this.L$2;
                Channel channel3 = (Channel) this.L$1;
                Object[] objArr6 = (Object[]) this.L$0;
                ResultKt.throwOnFailure(obj);
                length = i5;
                objArr = objArr6;
                b2 = r22;
                bArr = bArr3;
                channel = channel3;
                anonymousClass2 = this;
            } else {
                if (i2 != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ?? r23 = this.I$1;
                int i6 = this.I$0;
                byte[] bArr4 = (byte[]) this.L$2;
                Channel channel4 = (Channel) this.L$1;
                Object[] objArr7 = (Object[]) this.L$0;
                ResultKt.throwOnFailure(obj);
                length = i6;
                objArr = objArr7;
                b2 = r23;
                bArr = bArr4;
                channel = channel4;
                anonymousClass2 = this;
                i3 = 2;
            }
            byte b3 = (byte) (b2 + 1);
            anonymousClass2.L$0 = objArr;
            anonymousClass2.L$1 = channel;
            anonymousClass2.L$2 = bArr;
            anonymousClass2.I$0 = length;
            anonymousClass2.I$1 = b3;
            anonymousClass2.label = 1;
            holder = channel.mo1661receiveCatchingJP2dKIU(anonymousClass2);
            if (holder == coroutine_suspended) {
                return coroutine_suspended;
            }
            anonymousClass22 = anonymousClass2;
            objArr2 = objArr;
            channel2 = channel;
            b = b3;
            i = length;
            indexedValue = (IndexedValue) ChannelResult.m1673getOrNullimpl(holder);
            if (indexedValue != null) {
            }
        }
    }

    public static final <R, T> Object combineInternal(FlowCollector<? super R> flowCollector, Flow<? extends T>[] flowArr, Function0<T[]> function0, Function3<? super FlowCollector<? super R>, ? super T[], ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super Unit> continuation) {
        Object objFlowScope = FlowCoroutineKt.flowScope(new AnonymousClass2(flowArr, function0, function3, flowCollector, null), continuation);
        return objFlowScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objFlowScope : Unit.INSTANCE;
    }

    public static final <T1, T2, R> Flow<R> zipImpl(final Flow<? extends T1> flow, final Flow<? extends T2> flow2, final Function3<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> function3) {
        return new Flow<R>() {
            @Override
            public Object collect(FlowCollector<? super R> flowCollector, Continuation<? super Unit> continuation) {
                Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new CombineKt$zipImpl$1$1(flowCollector, flow2, flow, function3, null), continuation);
                return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
            }
        };
    }
}
