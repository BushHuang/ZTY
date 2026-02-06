package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.selects.SelectInstance;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u008a@"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;", "downstream", "Lkotlinx/coroutines/flow/FlowCollector;"}, k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$debounceInternal$1", f = "Delay.kt", i = {0, 0, 0, 0, 1, 1, 1, 1}, l = {222, 355}, m = "invokeSuspend", n = {"downstream", "values", "lastValue", "timeoutMillis", "downstream", "values", "lastValue", "timeoutMillis"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3"})
final class FlowKt__DelayKt$debounceInternal$1<T> extends SuspendLambda implements Function3<CoroutineScope, FlowCollector<? super T>, Continuation<? super Unit>, Object> {
    final Flow<T> $this_debounceInternal;
    final Function1<T, Long> $timeoutMillisSelector;
    private Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    FlowKt__DelayKt$debounceInternal$1(Function1<? super T, Long> function1, Flow<? extends T> flow, Continuation<? super FlowKt__DelayKt$debounceInternal$1> continuation) {
        super(3, continuation);
        this.$timeoutMillisSelector = function1;
        this.$this_debounceInternal = flow;
    }

    @Override
    public final Object invoke(CoroutineScope coroutineScope, FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
        FlowKt__DelayKt$debounceInternal$1 flowKt__DelayKt$debounceInternal$1 = new FlowKt__DelayKt$debounceInternal$1(this.$timeoutMillisSelector, this.$this_debounceInternal, continuation);
        flowKt__DelayKt$debounceInternal$1.L$0 = coroutineScope;
        flowKt__DelayKt$debounceInternal$1.L$1 = flowCollector;
        return flowKt__DelayKt$debounceInternal$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override
    public final Object invokeSuspend(Object obj) throws Throwable {
        FlowCollector flowCollector;
        FlowKt__DelayKt$debounceInternal$1<T> flowKt__DelayKt$debounceInternal$1;
        ReceiveChannel receiveChannelProduce$default;
        Ref.ObjectRef objectRef;
        Ref.LongRef longRef;
        Object obj2;
        Object result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        int i2 = 2;
        long j = 0;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            flowCollector = (FlowCollector) this.L$1;
            flowKt__DelayKt$debounceInternal$1 = this;
            receiveChannelProduce$default = ProduceKt.produce$default(coroutineScope, null, 0, new FlowKt__DelayKt$debounceInternal$1$values$1(this.$this_debounceInternal, null), 3, null);
            objectRef = new Ref.ObjectRef();
            if (objectRef.element != NullSurrogateKt.DONE) {
            }
        } else if (i == 1) {
            Ref.LongRef longRef2 = (Ref.LongRef) this.L$3;
            objectRef = (Ref.ObjectRef) this.L$2;
            receiveChannelProduce$default = (ReceiveChannel) this.L$1;
            flowCollector = (FlowCollector) this.L$0;
            ResultKt.throwOnFailure(obj);
            longRef = longRef2;
            flowKt__DelayKt$debounceInternal$1 = this;
            objectRef.element = null;
            FlowKt__DelayKt$debounceInternal$1<T> flowKt__DelayKt$debounceInternal$12 = flowKt__DelayKt$debounceInternal$1;
            obj2 = coroutine_suspended;
            Ref.LongRef longRef3 = longRef;
            FlowCollector flowCollector2 = flowCollector;
            ReceiveChannel receiveChannel = receiveChannelProduce$default;
            if (DebugKt.getASSERTIONS_ENABLED()) {
            }
            flowKt__DelayKt$debounceInternal$12.L$0 = flowCollector2;
            flowKt__DelayKt$debounceInternal$12.L$1 = receiveChannel;
            flowKt__DelayKt$debounceInternal$12.L$2 = objectRef;
            flowKt__DelayKt$debounceInternal$12.L$3 = longRef3;
            flowKt__DelayKt$debounceInternal$12.label = i2;
            FlowKt__DelayKt$debounceInternal$1<T> flowKt__DelayKt$debounceInternal$13 = flowKt__DelayKt$debounceInternal$12;
            SelectInstance selectInstance = new SelectInstance(flowKt__DelayKt$debounceInternal$13);
            SelectInstance selectInstance2 = selectInstance;
            if (objectRef.element != null) {
            }
            selectInstance2.invoke(receiveChannel.getOnReceiveCatching(), new FlowKt__DelayKt$debounceInternal$1$3$2(objectRef, flowCollector2, null));
            result = selectInstance.getResult();
            if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            }
            if (result != obj2) {
            }
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Ref.ObjectRef objectRef2 = (Ref.ObjectRef) this.L$2;
            ReceiveChannel receiveChannel2 = (ReceiveChannel) this.L$1;
            FlowCollector flowCollector3 = (FlowCollector) this.L$0;
            ResultKt.throwOnFailure(obj);
            flowCollector = flowCollector3;
            receiveChannelProduce$default = receiveChannel2;
            objectRef = objectRef2;
            flowKt__DelayKt$debounceInternal$1 = this;
            i2 = 2;
            j = 0;
            if (objectRef.element != NullSurrogateKt.DONE) {
                longRef = new Ref.LongRef();
                if (objectRef.element != null) {
                    Function1<T, Long> function1 = flowKt__DelayKt$debounceInternal$1.$timeoutMillisSelector;
                    Symbol symbol = NullSurrogateKt.NULL;
                    T t = objectRef.element;
                    if (t == symbol) {
                        t = null;
                    }
                    longRef.element = function1.invoke(t).longValue();
                    if (!(longRef.element >= j)) {
                        throw new IllegalArgumentException("Debounce timeout should not be negative".toString());
                    }
                    if (longRef.element == j) {
                        Symbol symbol2 = NullSurrogateKt.NULL;
                        T t2 = objectRef.element;
                        if (t2 == symbol2) {
                            t2 = null;
                        }
                        flowKt__DelayKt$debounceInternal$1.L$0 = flowCollector;
                        flowKt__DelayKt$debounceInternal$1.L$1 = receiveChannelProduce$default;
                        flowKt__DelayKt$debounceInternal$1.L$2 = objectRef;
                        flowKt__DelayKt$debounceInternal$1.L$3 = longRef;
                        flowKt__DelayKt$debounceInternal$1.label = 1;
                        if (flowCollector.emit(t2, flowKt__DelayKt$debounceInternal$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        objectRef.element = null;
                    }
                }
                FlowKt__DelayKt$debounceInternal$1<T> flowKt__DelayKt$debounceInternal$122 = flowKt__DelayKt$debounceInternal$1;
                obj2 = coroutine_suspended;
                Ref.LongRef longRef32 = longRef;
                FlowCollector flowCollector22 = flowCollector;
                ReceiveChannel receiveChannel3 = receiveChannelProduce$default;
                if (DebugKt.getASSERTIONS_ENABLED()) {
                    if (!(objectRef.element == null || longRef32.element > j)) {
                        throw new AssertionError();
                    }
                }
                flowKt__DelayKt$debounceInternal$122.L$0 = flowCollector22;
                flowKt__DelayKt$debounceInternal$122.L$1 = receiveChannel3;
                flowKt__DelayKt$debounceInternal$122.L$2 = objectRef;
                flowKt__DelayKt$debounceInternal$122.L$3 = longRef32;
                flowKt__DelayKt$debounceInternal$122.label = i2;
                FlowKt__DelayKt$debounceInternal$1<T> flowKt__DelayKt$debounceInternal$132 = flowKt__DelayKt$debounceInternal$122;
                SelectInstance selectInstance3 = new SelectInstance(flowKt__DelayKt$debounceInternal$132);
                SelectInstance selectInstance22 = selectInstance3;
                if (objectRef.element != null) {
                    selectInstance22.onTimeout(longRef32.element, new FlowKt__DelayKt$debounceInternal$1$3$1(flowCollector22, objectRef, null));
                }
                selectInstance22.invoke(receiveChannel3.getOnReceiveCatching(), new FlowKt__DelayKt$debounceInternal$1$3$2(objectRef, flowCollector22, null));
                result = selectInstance3.getResult();
                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(flowKt__DelayKt$debounceInternal$132);
                }
                if (result != obj2) {
                    return obj2;
                }
                coroutine_suspended = obj2;
                flowKt__DelayKt$debounceInternal$1 = flowKt__DelayKt$debounceInternal$122;
                receiveChannelProduce$default = receiveChannel3;
                flowCollector = flowCollector22;
                i2 = 2;
                j = 0;
                if (objectRef.element != NullSurrogateKt.DONE) {
                    return Unit.INSTANCE;
                }
            }
        }
    }
}
