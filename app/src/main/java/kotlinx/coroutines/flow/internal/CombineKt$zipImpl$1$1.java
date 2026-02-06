package kotlinx.coroutines.flow.internal;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.internal.ThreadContextKt;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004*\u00020\u0005H\u008a@"}, d2 = {"<anonymous>", "", "T1", "T2", "R", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1", f = "Combine.kt", i = {0}, l = {129}, m = "invokeSuspend", n = {"second"}, s = {"L$0"})
final class CombineKt$zipImpl$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final Flow<T1> $flow;
    final Flow<T2> $flow2;
    final FlowCollector<R> $this_unsafeFlow;
    final Function3<T1, T2, Continuation<? super R>, Object> $transform;
    private Object L$0;
    int label;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u00042\u0006\u0010\u0005\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", "", "T1", "T2", "R", "it"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2", f = "Combine.kt", i = {}, l = {130}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<Unit, Continuation<? super Unit>, Object> {
        final Object $cnt;
        final Flow<T1> $flow;
        final CoroutineContext $scopeContext;
        final ReceiveChannel<Object> $second;
        final FlowCollector<R> $this_unsafeFlow;
        final Function3<T1, T2, Continuation<? super R>, Object> $transform;
        int label;

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u00042\u0006\u0010\u0005\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "T1", "T2", "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        static final class AnonymousClass1<T> implements FlowCollector, SuspendFunction {
            final Object $cnt;
            final CoroutineContext $scopeContext;
            final ReceiveChannel<Object> $second;
            final FlowCollector<R> $this_unsafeFlow;
            final Function3<T1, T2, Continuation<? super R>, Object> $transform;

            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u00042\u0006\u0010\u0005\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", "", "T1", "T2", "R", "it"}, k = 3, mv = {1, 6, 0}, xi = 48)
            @DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$zipImpl$1$1$2$1$1", f = "Combine.kt", i = {}, l = {132, 135, 135}, m = "invokeSuspend", n = {}, s = {})
            static final class C00281 extends SuspendLambda implements Function2<Unit, Continuation<? super Unit>, Object> {
                final ReceiveChannel<Object> $second;
                final FlowCollector<R> $this_unsafeFlow;
                final Function3<T1, T2, Continuation<? super R>, Object> $transform;
                final T1 $value;
                Object L$0;
                int label;

                C00281(ReceiveChannel<? extends Object> receiveChannel, FlowCollector<? super R> flowCollector, Function3<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> function3, T1 t1, Continuation<? super C00281> continuation) {
                    super(2, continuation);
                    this.$second = receiveChannel;
                    this.$this_unsafeFlow = flowCollector;
                    this.$transform = function3;
                    this.$value = t1;
                }

                @Override
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C00281(this.$second, this.$this_unsafeFlow, this.$transform, this.$value, continuation);
                }

                @Override
                public final Object invoke(Unit unit, Continuation<? super Unit> continuation) {
                    return ((C00281) create(unit, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override
                public final Object invokeSuspend(Object obj) throws Throwable {
                    Object objMo1661receiveCatchingJP2dKIU;
                    FlowCollector flowCollector;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.label = 1;
                        objMo1661receiveCatchingJP2dKIU = this.$second.mo1661receiveCatchingJP2dKIU(this);
                        if (objMo1661receiveCatchingJP2dKIU == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            if (i != 2) {
                                if (i != 3) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                ResultKt.throwOnFailure(obj);
                                return Unit.INSTANCE;
                            }
                            FlowCollector flowCollector2 = (FlowCollector) this.L$0;
                            ResultKt.throwOnFailure(obj);
                            flowCollector = flowCollector2;
                            this.L$0 = null;
                            this.label = 3;
                            if (flowCollector.emit(obj, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            return Unit.INSTANCE;
                        }
                        ResultKt.throwOnFailure(obj);
                        objMo1661receiveCatchingJP2dKIU = ((ChannelResult) obj).getHolder();
                    }
                    FlowCollector flowCollector3 = this.$this_unsafeFlow;
                    if (objMo1661receiveCatchingJP2dKIU instanceof ChannelResult.Failed) {
                        Throwable thM1672exceptionOrNullimpl = ChannelResult.m1672exceptionOrNullimpl(objMo1661receiveCatchingJP2dKIU);
                        if (thM1672exceptionOrNullimpl == null) {
                            throw new AbortFlowException(flowCollector3);
                        }
                        throw thM1672exceptionOrNullimpl;
                    }
                    Function3<T1, T2, Continuation<? super R>, Object> function3 = this.$transform;
                    T1 t1 = this.$value;
                    if (objMo1661receiveCatchingJP2dKIU == NullSurrogateKt.NULL) {
                        objMo1661receiveCatchingJP2dKIU = null;
                    }
                    this.L$0 = flowCollector3;
                    this.label = 2;
                    obj = function3.invoke(t1, objMo1661receiveCatchingJP2dKIU, this);
                    flowCollector = flowCollector3;
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    this.L$0 = null;
                    this.label = 3;
                    if (flowCollector.emit(obj, this) == coroutine_suspended) {
                    }
                    return Unit.INSTANCE;
                }
            }

            AnonymousClass1(CoroutineContext coroutineContext, Object obj, ReceiveChannel<? extends Object> receiveChannel, FlowCollector<? super R> flowCollector, Function3<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> function3) {
                this.$scopeContext = coroutineContext;
                this.$cnt = obj;
                this.$second = receiveChannel;
                this.$this_unsafeFlow = flowCollector;
                this.$transform = function3;
            }

            @Override
            public final Object emit(T1 t1, Continuation<? super Unit> continuation) throws Throwable {
                CombineKt$zipImpl$1$1$2$1$emit$1 combineKt$zipImpl$1$1$2$1$emit$1;
                if (continuation instanceof CombineKt$zipImpl$1$1$2$1$emit$1) {
                    combineKt$zipImpl$1$1$2$1$emit$1 = (CombineKt$zipImpl$1$1$2$1$emit$1) continuation;
                    if ((combineKt$zipImpl$1$1$2$1$emit$1.label & Integer.MIN_VALUE) != 0) {
                        combineKt$zipImpl$1$1$2$1$emit$1.label -= Integer.MIN_VALUE;
                    } else {
                        combineKt$zipImpl$1$1$2$1$emit$1 = new CombineKt$zipImpl$1$1$2$1$emit$1(this, continuation);
                    }
                }
                Object obj = combineKt$zipImpl$1$1$2$1$emit$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = combineKt$zipImpl$1$1$2$1$emit$1.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineContext coroutineContext = this.$scopeContext;
                    Unit unit = Unit.INSTANCE;
                    Object obj2 = this.$cnt;
                    C00281 c00281 = new C00281(this.$second, this.$this_unsafeFlow, this.$transform, t1, null);
                    combineKt$zipImpl$1$1$2$1$emit$1.label = 1;
                    if (ChannelFlowKt.withContextUndispatched(coroutineContext, unit, obj2, c00281, combineKt$zipImpl$1$1$2$1$emit$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        AnonymousClass2(Flow<? extends T1> flow, CoroutineContext coroutineContext, Object obj, ReceiveChannel<? extends Object> receiveChannel, FlowCollector<? super R> flowCollector, Function3<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> function3, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$flow = flow;
            this.$scopeContext = coroutineContext;
            this.$cnt = obj;
            this.$second = receiveChannel;
            this.$this_unsafeFlow = flowCollector;
            this.$transform = function3;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$flow, this.$scopeContext, this.$cnt, this.$second, this.$this_unsafeFlow, this.$transform, continuation);
        }

        @Override
        public final Object invoke(Unit unit, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(unit, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (this.$flow.collect(new AnonymousClass1(this.$scopeContext, this.$cnt, this.$second, this.$this_unsafeFlow, this.$transform), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    CombineKt$zipImpl$1$1(FlowCollector<? super R> flowCollector, Flow<? extends T2> flow, Flow<? extends T1> flow2, Function3<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> function3, Continuation<? super CombineKt$zipImpl$1$1> continuation) {
        super(2, continuation);
        this.$this_unsafeFlow = flowCollector;
        this.$flow2 = flow;
        this.$flow = flow2;
        this.$transform = function3;
    }

    @Override
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CombineKt$zipImpl$1$1 combineKt$zipImpl$1$1 = new CombineKt$zipImpl$1$1(this.$this_unsafeFlow, this.$flow2, this.$flow, this.$transform, continuation);
        combineKt$zipImpl$1$1.L$0 = obj;
        return combineKt$zipImpl$1$1;
    }

    @Override
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CombineKt$zipImpl$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override
    public final Object invokeSuspend(Object obj) throws Throwable {
        ReceiveChannel receiveChannel;
        CoroutineContext coroutineContext;
        Object objThreadContextElements;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ?? r2 = this.label;
        try {
            if (r2 != 0) {
                if (r2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                receiveChannel = (ReceiveChannel) this.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    r2 = receiveChannel;
                } catch (AbortFlowException e) {
                    e = e;
                }
                ReceiveChannel.DefaultImpls.cancel$default((ReceiveChannel) r2, (CancellationException) null, 1, (Object) null);
                return Unit.INSTANCE;
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            ReceiveChannel receiveChannelProduce$default = ProduceKt.produce$default(coroutineScope, null, 0, new CombineKt$zipImpl$1$1$second$1(this.$flow2, null), 3, null);
            final CompletableJob completableJobJob$default = JobKt__JobKt.Job$default((Job) null, 1, (Object) null);
            final FlowCollector flowCollector = this.$this_unsafeFlow;
            ((SendChannel) receiveChannelProduce$default).invokeOnClose(new Function1<Throwable, Unit>() {
                {
                    super(1);
                }

                @Override
                public Unit invoke(Throwable th) {
                    invoke2(th);
                    return Unit.INSTANCE;
                }

                public final void invoke2(Throwable th) {
                    if (completableJobJob$default.isActive()) {
                        completableJobJob$default.cancel((CancellationException) new AbortFlowException(flowCollector));
                    }
                }
            });
            try {
                coroutineContext = coroutineScope.getCoroutineContext();
                objThreadContextElements = ThreadContextKt.threadContextElements(coroutineContext);
                this.L$0 = receiveChannelProduce$default;
                this.label = 1;
            } catch (AbortFlowException e2) {
                e = e2;
                receiveChannel = receiveChannelProduce$default;
            } catch (Throwable th) {
                th = th;
                r2 = receiveChannelProduce$default;
                ReceiveChannel.DefaultImpls.cancel$default((ReceiveChannel) r2, (CancellationException) null, 1, (Object) null);
                throw th;
            }
            if (ChannelFlowKt.withContextUndispatched$default(coroutineScope.getCoroutineContext().plus(completableJobJob$default), Unit.INSTANCE, null, new AnonymousClass2(this.$flow, coroutineContext, objThreadContextElements, receiveChannelProduce$default, this.$this_unsafeFlow, this.$transform, null), this, 4, null) == coroutine_suspended) {
                return coroutine_suspended;
            }
            r2 = receiveChannelProduce$default;
            ReceiveChannel.DefaultImpls.cancel$default((ReceiveChannel) r2, (CancellationException) null, 1, (Object) null);
            return Unit.INSTANCE;
            FlowExceptions_commonKt.checkOwnership(e, this.$this_unsafeFlow);
            r2 = receiveChannel;
            ReceiveChannel.DefaultImpls.cancel$default((ReceiveChannel) r2, (CancellationException) null, 1, (Object) null);
            return Unit.INSTANCE;
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
