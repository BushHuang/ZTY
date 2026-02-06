package com.huawei.hms.common.data;

import androidx.databinding.ObservableArrayList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public final class FreezableUtils {
    public static <T, E extends Freezable<T>> ArrayList<T> freeze(ArrayList<E> arrayList) {
        return freezeIterable(arrayList);
    }

    public static <T, E extends Freezable<T>> ArrayList<T> freeze(E[] eArr) {
        return freezeIterable(Arrays.asList(eArr));
    }

    public static <T, E extends Freezable<T>> ArrayList<T> freezeIterable(Iterable<E> iterable) {
        ObservableArrayList observableArrayList = (ArrayList<T>) new ArrayList();
        Iterator<E> it = iterable.iterator();
        while (it.hasNext()) {
            observableArrayList.add(it.next().freeze());
        }
        return observableArrayList;
    }
}
