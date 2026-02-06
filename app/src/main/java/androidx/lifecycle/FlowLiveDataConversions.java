package androidx.lifecycle;

import java.time.Duration;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.MainCoroutineDispatcher;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelIterator;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003\u001a0\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007\u001a2\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\nH\u0007¨\u0006\u000b"}, d2 = {"asFlow", "Lkotlinx/coroutines/flow/Flow;", "T", "Landroidx/lifecycle/LiveData;", "asLiveData", "context", "Lkotlin/coroutines/CoroutineContext;", "timeout", "Ljava/time/Duration;", "timeoutInMs", "", "lifecycle-livedata-ktx_release"}, k = 2, mv = {1, 4, 1})
public final class FlowLiveDataConversions {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/flow/FlowCollector;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 4, 1})
    @DebugMetadata(c = "androidx.lifecycle.FlowLiveDataConversions$asFlow$1", f = "FlowLiveData.kt", i = {0, 0, 0, 1, 1, 2, 2}, l = {96, 100, 101}, m = "invokeSuspend", n = {"$this$flow", "channel", "observer", "$this$flow", "observer", "$this$flow", "observer"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$0", "L$1"})
    static final class AnonymousClass1<T> extends SuspendLambda implements Function2<FlowCollector<? super T>, Continuation<? super Unit>, Object> {
        final LiveData $this_asFlow;
        private Object L$0;
        Object L$1;
        Object L$2;
        int label;

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 4, 1})
        @DebugMetadata(c = "androidx.lifecycle.FlowLiveDataConversions$asFlow$1$1", f = "FlowLiveData.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C00031 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final Observer $observer;
            int label;

            C00031(Observer observer, Continuation continuation) {
                super(2, continuation);
                this.$observer = observer;
            }

            @Override
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                Intrinsics.checkNotNullParameter(continuation, "completion");
                return new C00031(this.$observer, continuation);
            }

            @Override
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00031) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                AnonymousClass1.this.$this_asFlow.observeForever(this.$observer);
                return Unit.INSTANCE;
            }
        }

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 4, 1})
        @DebugMetadata(c = "androidx.lifecycle.FlowLiveDataConversions$asFlow$1$2", f = "FlowLiveData.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final Observer $observer;
            int label;

            AnonymousClass2(Observer observer, Continuation continuation) {
                super(2, continuation);
                this.$observer = observer;
            }

            @Override
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                Intrinsics.checkNotNullParameter(continuation, "completion");
                return new AnonymousClass2(this.$observer, continuation);
            }

            @Override
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                AnonymousClass1.this.$this_asFlow.removeObserver(this.$observer);
                return Unit.INSTANCE;
            }
        }

        AnonymousClass1(LiveData liveData, Continuation continuation) {
            super(2, continuation);
            this.$this_asFlow = liveData;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Intrinsics.checkNotNullParameter(continuation, "completion");
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_asFlow, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override
        public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            FlowCollector flowCollector;
            Observer<T> observer;
            Channel channel;
            Throwable th;
            AnonymousClass1<T> anonymousClass1;
            ?? r7;
            Observer observer2;
            ChannelIterator channelIterator;
            ChannelIterator it;
            Object objHasNext;
            Observer<T> observer3;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            ?? r4 = 1;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    flowCollector = (FlowCollector) this.L$0;
                    final Channel channelChannel$default = ChannelKt.Channel$default(-1, null, null, 6, null);
                    observer = new Observer<T>() {
                        @Override
                        public final void onChanged(T t) {
                            channelChannel$default.offer(t);
                        }
                    };
                    MainCoroutineDispatcher immediate = Dispatchers.getMain().getImmediate();
                    C00031 c00031 = new C00031(observer, null);
                    this.L$0 = flowCollector;
                    this.L$1 = channelChannel$default;
                    this.L$2 = observer;
                    this.label = 1;
                    if (BuildersKt.withContext(immediate, c00031, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    channel = channelChannel$default;
                } else if (i != 1) {
                    try {
                        if (i == 2) {
                            ChannelIterator channelIterator2 = (ChannelIterator) this.L$2;
                            Observer observer4 = (Observer) this.L$1;
                            FlowCollector flowCollector2 = (FlowCollector) this.L$0;
                            ResultKt.throwOnFailure(obj);
                            r7 = flowCollector2;
                            observer2 = observer4;
                            channelIterator = channelIterator2;
                            anonymousClass1 = this;
                            if (((Boolean) obj).booleanValue()) {
                            }
                        } else {
                            if (i != 3) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ChannelIterator channelIterator3 = (ChannelIterator) this.L$2;
                            Observer<T> observer5 = (Observer) this.L$1;
                            flowCollector = (FlowCollector) this.L$0;
                            ResultKt.throwOnFailure(obj);
                            it = channelIterator3;
                            observer3 = observer5;
                            anonymousClass1 = this;
                            r4 = observer3;
                            anonymousClass1.L$0 = flowCollector;
                            anonymousClass1.L$1 = r4;
                            anonymousClass1.L$2 = it;
                            anonymousClass1.label = 2;
                            objHasNext = it.hasNext(anonymousClass1);
                            if (objHasNext == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            Observer observer6 = r4;
                            channelIterator = it;
                            obj = objHasNext;
                            r7 = flowCollector;
                            observer2 = observer6;
                            try {
                                if (((Boolean) obj).booleanValue()) {
                                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain().getImmediate(), null, anonymousClass1.new AnonymousClass2(observer2, null), 2, null);
                                    return Unit.INSTANCE;
                                }
                                Object next = channelIterator.next();
                                anonymousClass1.L$0 = r7;
                                anonymousClass1.L$1 = observer2;
                                anonymousClass1.L$2 = channelIterator;
                                anonymousClass1.label = 3;
                                if (r7.emit(next, anonymousClass1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                it = channelIterator;
                                r4 = observer2;
                                flowCollector = r7;
                                anonymousClass1.L$0 = flowCollector;
                                anonymousClass1.L$1 = r4;
                                anonymousClass1.L$2 = it;
                                anonymousClass1.label = 2;
                                objHasNext = it.hasNext(anonymousClass1);
                                if (objHasNext == coroutine_suspended) {
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                r4 = observer2;
                                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain().getImmediate(), null, anonymousClass1.new AnonymousClass2(r4, null), 2, null);
                                throw th;
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        anonymousClass1 = this;
                        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain().getImmediate(), null, anonymousClass1.new AnonymousClass2(r4, null), 2, null);
                        throw th;
                    }
                } else {
                    observer = (Observer) this.L$2;
                    channel = (Channel) this.L$1;
                    flowCollector = (FlowCollector) this.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                anonymousClass1.L$0 = flowCollector;
                anonymousClass1.L$1 = r4;
                anonymousClass1.L$2 = it;
                anonymousClass1.label = 2;
                objHasNext = it.hasNext(anonymousClass1);
                if (objHasNext == coroutine_suspended) {
                }
            } catch (Throwable th4) {
                th = th4;
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain().getImmediate(), null, anonymousClass1.new AnonymousClass2(r4, null), 2, null);
                throw th;
            }
            it = channel.iterator();
            observer3 = observer;
            anonymousClass1 = this;
            r4 = observer3;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "T", "Landroidx/lifecycle/LiveDataScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 4, 1})
    @DebugMetadata(c = "androidx.lifecycle.FlowLiveDataConversions$asLiveData$1", f = "FlowLiveData.kt", i = {}, l = {149}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1<T> extends SuspendLambda implements Function2<LiveDataScope<T>, Continuation<? super Unit>, Object> {
        final Flow $this_asLiveData;
        private Object L$0;
        int label;

        AnonymousClass1(Flow flow, Continuation continuation) {
            super(2, continuation);
            this.$this_asLiveData = flow;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Intrinsics.checkNotNullParameter(continuation, "completion");
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_asLiveData, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override
        public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final LiveDataScope liveDataScope = (LiveDataScope) this.L$0;
                Flow flow = this.$this_asLiveData;
                FlowCollector<T> flowCollector = new FlowCollector<T>() {
                    @Override
                    public Object emit(Object obj2, Continuation continuation) {
                        Object objEmit = liveDataScope.emit(obj2, continuation);
                        return objEmit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
                    }
                };
                this.label = 1;
                if (flow.collect(flowCollector, this) == coroutine_suspended) {
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

    public static final <T> Flow<T> asFlow(LiveData<T> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "$this$asFlow");
        return FlowKt.flow(new AnonymousClass1(liveData, null));
    }

    public static final <T> LiveData<T> asLiveData(Flow<? extends T> flow) {
        return asLiveData$default(flow, (CoroutineContext) null, 0L, 3, (Object) null);
    }

    public static final <T> LiveData<T> asLiveData(Flow<? extends T> flow, CoroutineContext coroutineContext) {
        return asLiveData$default(flow, coroutineContext, 0L, 2, (Object) null);
    }

    public static final <T> LiveData<T> asLiveData(Flow<? extends T> flow, CoroutineContext coroutineContext, long j) {
        Intrinsics.checkNotNullParameter(flow, "$this$asLiveData");
        Intrinsics.checkNotNullParameter(coroutineContext, "context");
        return CoroutineLiveDataKt.liveData(coroutineContext, j, new AnonymousClass1(flow, null));
    }

    public static final <T> LiveData<T> asLiveData(Flow<? extends T> flow, CoroutineContext coroutineContext, Duration duration) {
        Intrinsics.checkNotNullParameter(flow, "$this$asLiveData");
        Intrinsics.checkNotNullParameter(coroutineContext, "context");
        Intrinsics.checkNotNullParameter(duration, "timeout");
        return asLiveData(flow, coroutineContext, duration.toMillis());
    }

    public static LiveData asLiveData$default(Flow flow, CoroutineContext coroutineContext, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i & 2) != 0) {
            j = 5000;
        }
        return asLiveData(flow, coroutineContext, j);
    }

    public static LiveData asLiveData$default(Flow flow, CoroutineContext coroutineContext, Duration duration, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        return asLiveData(flow, coroutineContext, duration);
    }
}
