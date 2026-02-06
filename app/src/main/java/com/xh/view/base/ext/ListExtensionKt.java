package com.xh.view.base.ext;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001a#\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u0002¢\u0006\u0002\u0010\u0005\u001a9\u0010\u0006\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00072!\u0010\b\u001a\u001d\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00010\t\u001a#\u0010\r\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u0002¢\u0006\u0002\u0010\u0005\u001aE\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u00020\u00072!\u0010\u0010\u001a\u001d\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u0002H\u000f0\t¨\u0006\u0012"}, d2 = {"addIfAbsent", "", "T", "", "outLifecycleObserver", "(Ljava/util/List;Ljava/lang/Object;)V", "notifyOutLifecycleObservers", "", "notifyItem", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "observer", "removeIfIn", "transformList", "R", "transformItem", "t", "xhcore_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class ListExtensionKt {
    public static final <T> void addIfAbsent(List<T> list, T t) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        if (list.contains(t)) {
            return;
        }
        list.add(t);
    }

    public static final <T> void notifyOutLifecycleObservers(List<? extends T> list, Function1<? super T, Unit> function1) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(function1, "notifyItem");
        Iterator<? extends T> it = list.iterator();
        while (it.hasNext()) {
            function1.invoke(it.next());
        }
    }

    public static final <T> void removeIfIn(List<T> list, T t) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        if (list.contains(t)) {
            list.remove(t);
        }
    }

    public static final <T, R> List<R> transformList(List<? extends T> list, Function1<? super T, ? extends R> function1) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(function1, "transformItem");
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(function1.invoke(list.get(i)));
        }
        return arrayList;
    }
}
