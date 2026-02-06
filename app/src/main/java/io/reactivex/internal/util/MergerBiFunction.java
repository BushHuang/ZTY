package io.reactivex.internal.util;

import io.reactivex.functions.BiFunction;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public final class MergerBiFunction<T> implements BiFunction<List<T>, List<T>, List<T>> {
    final Comparator<? super T> comparator;

    public MergerBiFunction(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public List<T> apply(List<T> list, List<T> list2) throws Exception {
        T next;
        int size = list.size() + list2.size();
        if (size == 0) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(size);
        Iterator<T> it = list.iterator();
        Iterator<T> it2 = list2.iterator();
        T next2 = it.hasNext() ? it.next() : null;
        if (it2.hasNext()) {
            next = it2.next();
            while (next2 != null && next != null) {
                if (this.comparator.compare(next2, next) < 0) {
                    arrayList.add(next2);
                    next2 = it.hasNext() ? it.next() : null;
                } else {
                    arrayList.add(next);
                    if (it2.hasNext()) {
                        next = it2.next();
                    }
                }
            }
            if (next2 != null) {
                arrayList.add(next2);
                while (it.hasNext()) {
                    arrayList.add(it.next());
                }
            } else {
                arrayList.add(next);
                while (it2.hasNext()) {
                    arrayList.add(it2.next());
                }
            }
            return arrayList;
        }
        next = null;
    }
}
