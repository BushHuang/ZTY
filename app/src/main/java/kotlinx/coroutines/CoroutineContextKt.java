package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.internal.ThreadContextKt;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a8\u0010\u0006\u001a\u0002H\u0007\"\u0004\b\u0000\u0010\u00072\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00070\rH\u0080\b¢\u0006\u0002\u0010\u000e\u001a4\u0010\u000f\u001a\u0002H\u0007\"\u0004\b\u0000\u0010\u00072\u0006\u0010\u0010\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00070\rH\u0080\b¢\u0006\u0002\u0010\u0011\u001a\f\u0010\u0012\u001a\u00020\u0003*\u00020\u0003H\u0002\u001a\u0014\u0010\u0013\u001a\u00020\u0003*\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u0003H\u0007\u001a\u0013\u0010\u0015\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0016*\u00020\u0017H\u0080\u0010\u001a(\u0010\u0018\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0016*\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\u0010\u001a\u00020\u00032\b\u0010\u0019\u001a\u0004\u0018\u00010\u000bH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u00038@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u001a"}, d2 = {"DEBUG_THREAD_NAME_SEPARATOR", "", "coroutineName", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineName", "(Lkotlin/coroutines/CoroutineContext;)Ljava/lang/String;", "withContinuationContext", "T", "continuation", "Lkotlin/coroutines/Continuation;", "countOrElement", "", "block", "Lkotlin/Function0;", "(Lkotlin/coroutines/Continuation;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withCoroutineContext", "context", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "foldCopiesForChildCoroutine", "newCoroutineContext", "Lkotlinx/coroutines/CoroutineScope;", "undispatchedCompletion", "Lkotlinx/coroutines/UndispatchedCoroutine;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "updateUndispatchedCompletion", "oldValue", "kotlinx-coroutines-core"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class CoroutineContextKt {
    private static final String DEBUG_THREAD_NAME_SEPARATOR = " @";

    private static final CoroutineContext foldCopiesForChildCoroutine(CoroutineContext coroutineContext) {
        return !((Boolean) coroutineContext.fold(false, new Function2<Boolean, CoroutineContext.Element, Boolean>() {
            public final Boolean invoke(boolean z, CoroutineContext.Element element) {
                return Boolean.valueOf(z || (element instanceof CopyableThreadContextElement));
            }

            @Override
            public Boolean invoke(Boolean bool, CoroutineContext.Element element) {
                return invoke(bool.booleanValue(), element);
            }
        })).booleanValue() ? coroutineContext : (CoroutineContext) coroutineContext.fold(EmptyCoroutineContext.INSTANCE, new Function2<CoroutineContext, CoroutineContext.Element, CoroutineContext>() {
            @Override
            public final CoroutineContext invoke(CoroutineContext coroutineContext2, CoroutineContext.Element element) {
                if (element instanceof CopyableThreadContextElement) {
                    element = ((CopyableThreadContextElement) element).copyForChildCoroutine();
                }
                return coroutineContext2.plus(element);
            }
        });
    }

    public static final String getCoroutineName(CoroutineContext coroutineContext) {
        CoroutineId coroutineId;
        String name;
        if (!DebugKt.getDEBUG() || (coroutineId = (CoroutineId) coroutineContext.get(CoroutineId.INSTANCE)) == null) {
            return null;
        }
        CoroutineName coroutineName = (CoroutineName) coroutineContext.get(CoroutineName.INSTANCE);
        String str = "coroutine";
        if (coroutineName != null && (name = coroutineName.getName()) != null) {
            str = name;
        }
        return str + '#' + coroutineId.getId();
    }

    public static final CoroutineContext newCoroutineContext(CoroutineScope coroutineScope, CoroutineContext coroutineContext) {
        CoroutineContext coroutineContextPlus = foldCopiesForChildCoroutine(coroutineScope.getCoroutineContext()).plus(coroutineContext);
        CoroutineContext coroutineContextPlus2 = DebugKt.getDEBUG() ? coroutineContextPlus.plus(new CoroutineId(DebugKt.getCOROUTINE_ID().incrementAndGet())) : coroutineContextPlus;
        return (coroutineContextPlus == Dispatchers.getDefault() || coroutineContextPlus.get(ContinuationInterceptor.INSTANCE) != null) ? coroutineContextPlus2 : coroutineContextPlus2.plus(Dispatchers.getDefault());
    }

    public static final UndispatchedCoroutine<?> undispatchedCompletion(CoroutineStackFrame coroutineStackFrame) {
        while (!(coroutineStackFrame instanceof DispatchedCoroutine) && (coroutineStackFrame = coroutineStackFrame.getCallerFrame()) != null) {
            if (coroutineStackFrame instanceof UndispatchedCoroutine) {
                return (UndispatchedCoroutine) coroutineStackFrame;
            }
        }
        return null;
    }

    public static final UndispatchedCoroutine<?> updateUndispatchedCompletion(Continuation<?> continuation, CoroutineContext coroutineContext, Object obj) {
        if (!(continuation instanceof CoroutineStackFrame)) {
            return null;
        }
        if (!(coroutineContext.get(UndispatchedMarker.INSTANCE) != null)) {
            return null;
        }
        UndispatchedCoroutine<?> undispatchedCoroutineUndispatchedCompletion = undispatchedCompletion((CoroutineStackFrame) continuation);
        if (undispatchedCoroutineUndispatchedCompletion != null) {
            undispatchedCoroutineUndispatchedCompletion.saveThreadContext(coroutineContext, obj);
        }
        return undispatchedCoroutineUndispatchedCompletion;
    }

    public static final <T> T withContinuationContext(Continuation<?> continuation, Object obj, Function0<? extends T> function0) {
        CoroutineContext coroutineContext = continuation.getContext();
        Object objUpdateThreadContext = ThreadContextKt.updateThreadContext(coroutineContext, obj);
        UndispatchedCoroutine<?> undispatchedCoroutineUpdateUndispatchedCompletion = objUpdateThreadContext != ThreadContextKt.NO_THREAD_ELEMENTS ? updateUndispatchedCompletion(continuation, coroutineContext, objUpdateThreadContext) : (UndispatchedCoroutine) null;
        try {
            return function0.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            if (undispatchedCoroutineUpdateUndispatchedCompletion == null || undispatchedCoroutineUpdateUndispatchedCompletion.clearThreadContext()) {
                ThreadContextKt.restoreThreadContext(coroutineContext, objUpdateThreadContext);
            }
            InlineMarker.finallyEnd(1);
        }
    }

    public static final <T> T withCoroutineContext(CoroutineContext coroutineContext, Object obj, Function0<? extends T> function0) {
        Object objUpdateThreadContext = ThreadContextKt.updateThreadContext(coroutineContext, obj);
        try {
            return function0.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            ThreadContextKt.restoreThreadContext(coroutineContext, objUpdateThreadContext);
            InlineMarker.finallyEnd(1);
        }
    }
}
