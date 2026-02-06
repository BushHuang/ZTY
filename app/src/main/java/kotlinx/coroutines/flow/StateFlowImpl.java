package kotlinx.coroutines.flow;

import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;
import kotlinx.coroutines.flow.internal.FusibleFlow;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import kotlinx.coroutines.internal.Symbol;

@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00020\u0010062\b\u0012\u0004\u0012\u00028\u0000072\b\u0012\u0004\u0012\u00028\u0000082\b\u0012\u0004\u0012\u00028\u000009B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J!\u0010\t\u001a\u00020\b2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006H\u0096@ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00028\u00002\u0006\u0010\f\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0014¢\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u00152\u0006\u0010\u0014\u001a\u00020\u0013H\u0014¢\u0006\u0004\b\u0016\u0010\u0017J\u001b\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ-\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000!2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u001fH\u0016¢\u0006\u0004\b\"\u0010#J\u000f\u0010$\u001a\u00020\u0019H\u0016¢\u0006\u0004\b$\u0010%J\u0017\u0010&\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00028\u0000H\u0016¢\u0006\u0004\b&\u0010'J!\u0010*\u001a\u00020\r2\b\u0010(\u001a\u0004\u0018\u00010\u00022\u0006\u0010)\u001a\u00020\u0002H\u0002¢\u0006\u0004\b*\u0010\u000fR\u001a\u0010.\u001a\b\u0012\u0004\u0012\u00028\u00000+8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-R\u0016\u0010/\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b/\u00100R*\u0010\u0018\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00028\u00008V@VX\u0096\u000e¢\u0006\u0012\u0012\u0004\b4\u0010%\u001a\u0004\b1\u00102\"\u0004\b3\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u00065"}, d2 = {"Lkotlinx/coroutines/flow/StateFlowImpl;", "T", "", "initialState", "<init>", "(Ljava/lang/Object;)V", "Lkotlinx/coroutines/flow/FlowCollector;", "collector", "", "collect", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expect", "update", "", "compareAndSet", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "Lkotlinx/coroutines/flow/StateFlowSlot;", "createSlot", "()Lkotlinx/coroutines/flow/StateFlowSlot;", "", "size", "", "createSlotArray", "(I)[Lkotlinx/coroutines/flow/StateFlowSlot;", "value", "", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlin/coroutines/CoroutineContext;", "context", "capacity", "Lkotlinx/coroutines/channels/BufferOverflow;", "onBufferOverflow", "Lkotlinx/coroutines/flow/Flow;", "fuse", "(Lkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/channels/BufferOverflow;)Lkotlinx/coroutines/flow/Flow;", "resetReplayCache", "()V", "tryEmit", "(Ljava/lang/Object;)Z", "expectedState", "newState", "updateState", "", "getReplayCache", "()Ljava/util/List;", "replayCache", "sequence", "I", "getValue", "()Ljava/lang/Object;", "setValue", "getValue$annotations", "kotlinx-coroutines-core", "Lkotlinx/coroutines/flow/internal/AbstractSharedFlow;", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lkotlinx/coroutines/flow/CancellableFlow;", "Lkotlinx/coroutines/flow/internal/FusibleFlow;"}, k = 1, mv = {1, 6, 0}, xi = 48)
final class StateFlowImpl<T> extends AbstractSharedFlow<StateFlowSlot> implements MutableStateFlow<T>, CancellableFlow<T>, FusibleFlow<T> {
    private volatile Object _state;
    private int sequence;

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.StateFlowImpl", f = "StateFlow.kt", i = {0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2}, l = {386, 398, 403}, m = "collect", n = {"this", "collector", "slot", "this", "collector", "slot", "collectorJob", "newState", "this", "collector", "slot", "collectorJob", "oldState"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        Object result;
        final StateFlowImpl<T> this$0;

        AnonymousClass1(StateFlowImpl<T> stateFlowImpl, Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
            this.this$0 = stateFlowImpl;
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.collect(null, this);
        }
    }

    public StateFlowImpl(Object obj) {
        this._state = obj;
    }

    public static void getValue$annotations() {
    }

    private final boolean updateState(Object expectedState, Object newState) {
        getSlots();
        synchronized (this) {
            Object obj = this._state;
            if (expectedState != null && !Intrinsics.areEqual(obj, expectedState)) {
                return false;
            }
            if (Intrinsics.areEqual(obj, newState)) {
                return true;
            }
            this._state = newState;
            int i = this.sequence;
            if ((i & 1) != 0) {
                this.sequence = i + 2;
                return true;
            }
            int i2 = i + 1;
            this.sequence = i2;
            StateFlowSlot[] slots = getSlots();
            Unit unit = Unit.INSTANCE;
            while (true) {
                StateFlowSlot[] stateFlowSlotArr = slots;
                if (stateFlowSlotArr != null) {
                    int length = stateFlowSlotArr.length;
                    int i3 = 0;
                    while (i3 < length) {
                        StateFlowSlot stateFlowSlot = stateFlowSlotArr[i3];
                        i3++;
                        if (stateFlowSlot != null) {
                            stateFlowSlot.makePending();
                        }
                    }
                }
                synchronized (this) {
                    if (this.sequence == i2) {
                        this.sequence = i2 + 1;
                        return true;
                    }
                    i2 = this.sequence;
                    slots = getSlots();
                    Unit unit2 = Unit.INSTANCE;
                }
            }
        }
    }

    @Override
    public Object collect(FlowCollector<? super T> flowCollector, Continuation<?> continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        StateFlowImpl<T> stateFlowImpl;
        StateFlowSlot stateFlowSlot;
        FlowCollector flowCollector2;
        Job job;
        Object obj;
        StateFlowSlot stateFlowSlot2;
        boolean zTakePending;
        T t;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(this, continuation);
            }
        }
        Object obj2 = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        ?? r6 = 1;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj2);
                StateFlowSlot stateFlowSlotAllocateSlot = allocateSlot();
                try {
                    if (flowCollector instanceof SubscribedFlowCollector) {
                        anonymousClass1.L$0 = this;
                        anonymousClass1.L$1 = flowCollector;
                        anonymousClass1.L$2 = stateFlowSlotAllocateSlot;
                        anonymousClass1.label = 1;
                        if (((SubscribedFlowCollector) flowCollector).onSubscription(anonymousClass1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    stateFlowImpl = this;
                    stateFlowSlot = stateFlowSlotAllocateSlot;
                } catch (Throwable th) {
                    th = th;
                    stateFlowImpl = this;
                    r6 = stateFlowSlotAllocateSlot;
                    stateFlowImpl.freeSlot((AbstractSharedFlowSlot) r6);
                    throw th;
                }
            } else if (i == 1) {
                StateFlowSlot stateFlowSlot3 = (StateFlowSlot) anonymousClass1.L$2;
                flowCollector = (FlowCollector) anonymousClass1.L$1;
                stateFlowImpl = (StateFlowImpl) anonymousClass1.L$0;
                ResultKt.throwOnFailure(obj2);
                stateFlowSlot = stateFlowSlot3;
            } else if (i == 2) {
                obj = anonymousClass1.L$4;
                job = (Job) anonymousClass1.L$3;
                StateFlowSlot stateFlowSlot4 = (StateFlowSlot) anonymousClass1.L$2;
                flowCollector2 = (FlowCollector) anonymousClass1.L$1;
                stateFlowImpl = (StateFlowImpl) anonymousClass1.L$0;
                ResultKt.throwOnFailure(obj2);
                stateFlowSlot2 = stateFlowSlot4;
                zTakePending = stateFlowSlot2.takePending();
                r6 = stateFlowSlot2;
                if (!zTakePending) {
                }
                ?? r12 = stateFlowImpl._state;
                if (job != null) {
                }
                if (obj != null) {
                }
                if (r12 != NullSurrogateKt.NULL) {
                }
                anonymousClass1.L$0 = stateFlowImpl;
                anonymousClass1.L$1 = flowCollector2;
                anonymousClass1.L$2 = r6;
                anonymousClass1.L$3 = job;
                anonymousClass1.L$4 = r12;
                anonymousClass1.label = 2;
                if (flowCollector2.emit(t, anonymousClass1) != coroutine_suspended) {
                }
            } else {
                if (i != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                obj = anonymousClass1.L$4;
                job = (Job) anonymousClass1.L$3;
                StateFlowSlot stateFlowSlot5 = (StateFlowSlot) anonymousClass1.L$2;
                flowCollector2 = (FlowCollector) anonymousClass1.L$1;
                stateFlowImpl = (StateFlowImpl) anonymousClass1.L$0;
                ResultKt.throwOnFailure(obj2);
                r6 = stateFlowSlot5;
                ?? r122 = stateFlowImpl._state;
                if (job != null) {
                    JobKt.ensureActive(job);
                }
                if (obj != null) {
                    stateFlowSlot2 = r6;
                    if (!Intrinsics.areEqual(obj, (Object) r122)) {
                    }
                    zTakePending = stateFlowSlot2.takePending();
                    r6 = stateFlowSlot2;
                    if (!zTakePending) {
                        anonymousClass1.L$0 = stateFlowImpl;
                        anonymousClass1.L$1 = flowCollector2;
                        anonymousClass1.L$2 = stateFlowSlot2;
                        anonymousClass1.L$3 = job;
                        anonymousClass1.L$4 = obj;
                        anonymousClass1.label = 3;
                        Object objAwaitPending = stateFlowSlot2.awaitPending(anonymousClass1);
                        r6 = stateFlowSlot2;
                        if (objAwaitPending == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    ?? r1222 = stateFlowImpl._state;
                    if (job != null) {
                    }
                    if (obj != null) {
                    }
                }
                t = r1222 != NullSurrogateKt.NULL ? null : r1222;
                anonymousClass1.L$0 = stateFlowImpl;
                anonymousClass1.L$1 = flowCollector2;
                anonymousClass1.L$2 = r6;
                anonymousClass1.L$3 = job;
                anonymousClass1.L$4 = r1222;
                anonymousClass1.label = 2;
                if (flowCollector2.emit(t, anonymousClass1) != coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj = r1222;
                stateFlowSlot2 = r6;
                zTakePending = stateFlowSlot2.takePending();
                r6 = stateFlowSlot2;
                if (!zTakePending) {
                }
                ?? r12222 = stateFlowImpl._state;
                if (job != null) {
                }
                if (obj != null) {
                }
                if (r12222 != NullSurrogateKt.NULL) {
                }
                anonymousClass1.L$0 = stateFlowImpl;
                anonymousClass1.L$1 = flowCollector2;
                anonymousClass1.L$2 = r6;
                anonymousClass1.L$3 = job;
                anonymousClass1.L$4 = r12222;
                anonymousClass1.label = 2;
                if (flowCollector2.emit(t, anonymousClass1) != coroutine_suspended) {
                }
            }
            flowCollector2 = flowCollector;
            job = (Job) anonymousClass1.getContext().get(Job.INSTANCE);
            obj = null;
            r6 = stateFlowSlot;
            ?? r122222 = stateFlowImpl._state;
            if (job != null) {
            }
            if (obj != null) {
            }
            if (r122222 != NullSurrogateKt.NULL) {
            }
            anonymousClass1.L$0 = stateFlowImpl;
            anonymousClass1.L$1 = flowCollector2;
            anonymousClass1.L$2 = r6;
            anonymousClass1.L$3 = job;
            anonymousClass1.L$4 = r122222;
            anonymousClass1.label = 2;
            if (flowCollector2.emit(t, anonymousClass1) != coroutine_suspended) {
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override
    public boolean compareAndSet(T expect, T update) {
        if (expect == null) {
            expect = (T) NullSurrogateKt.NULL;
        }
        if (update == null) {
            update = (T) NullSurrogateKt.NULL;
        }
        return updateState(expect, update);
    }

    @Override
    protected StateFlowSlot createSlot() {
        return new StateFlowSlot();
    }

    @Override
    protected StateFlowSlot[] createSlotArray(int size) {
        return new StateFlowSlot[size];
    }

    @Override
    public Object emit(T t, Continuation<? super Unit> continuation) {
        setValue(t);
        return Unit.INSTANCE;
    }

    @Override
    public Flow<T> fuse(CoroutineContext context, int capacity, BufferOverflow onBufferOverflow) {
        return StateFlowKt.fuseStateFlow(this, context, capacity, onBufferOverflow);
    }

    @Override
    public List<T> getReplayCache() {
        return CollectionsKt.listOf(getValue());
    }

    @Override
    public T getValue() {
        Symbol symbol = NullSurrogateKt.NULL;
        T t = (T) this._state;
        if (t == symbol) {
            return null;
        }
        return t;
    }

    @Override
    public void resetReplayCache() {
        throw new UnsupportedOperationException("MutableStateFlow.resetReplayCache is not supported");
    }

    @Override
    public void setValue(T t) {
        if (t == null) {
            t = (T) NullSurrogateKt.NULL;
        }
        updateState(null, t);
    }

    @Override
    public boolean tryEmit(T value) {
        setValue(value);
        return true;
    }
}
