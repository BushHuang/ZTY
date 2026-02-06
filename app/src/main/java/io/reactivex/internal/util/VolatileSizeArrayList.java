package io.reactivex.internal.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import java.util.concurrent.atomic.AtomicInteger;

public final class VolatileSizeArrayList<T> extends AtomicInteger implements List<T>, RandomAccess {
    private static final long serialVersionUID = 3972397474470203923L;
    final ArrayList<T> list;

    public VolatileSizeArrayList() {
        this.list = new ArrayList<>();
    }

    public VolatileSizeArrayList(int i) {
        this.list = new ArrayList<>(i);
    }

    @Override
    public void add(int i, T t) {
        this.list.add(i, t);
        lazySet(this.list.size());
    }

    @Override
    public boolean add(T t) {
        boolean zAdd = this.list.add(t);
        lazySet(this.list.size());
        return zAdd;
    }

    @Override
    public boolean addAll(int i, Collection<? extends T> collection) {
        boolean zAddAll = this.list.addAll(i, collection);
        lazySet(this.list.size());
        return zAddAll;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        boolean zAddAll = this.list.addAll(collection);
        lazySet(this.list.size());
        return zAddAll;
    }

    @Override
    public void clear() {
        this.list.clear();
        lazySet(0);
    }

    @Override
    public boolean contains(Object obj) {
        return this.list.contains(obj);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return this.list.containsAll(collection);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof VolatileSizeArrayList ? this.list.equals(((VolatileSizeArrayList) obj).list) : this.list.equals(obj);
    }

    @Override
    public T get(int i) {
        return this.list.get(i);
    }

    @Override
    public int hashCode() {
        return this.list.hashCode();
    }

    @Override
    public int indexOf(Object obj) {
        return this.list.indexOf(obj);
    }

    @Override
    public boolean isEmpty() {
        return get() == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return this.list.iterator();
    }

    @Override
    public int lastIndexOf(Object obj) {
        return this.list.lastIndexOf(obj);
    }

    @Override
    public ListIterator<T> listIterator() {
        return this.list.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        return this.list.listIterator(i);
    }

    @Override
    public T remove(int i) {
        T tRemove = this.list.remove(i);
        lazySet(this.list.size());
        return tRemove;
    }

    @Override
    public boolean remove(Object obj) {
        boolean zRemove = this.list.remove(obj);
        lazySet(this.list.size());
        return zRemove;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean zRemoveAll = this.list.removeAll(collection);
        lazySet(this.list.size());
        return zRemoveAll;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean zRetainAll = this.list.retainAll(collection);
        lazySet(this.list.size());
        return zRetainAll;
    }

    @Override
    public T set(int i, T t) {
        return this.list.set(i, t);
    }

    @Override
    public int size() {
        return get();
    }

    @Override
    public List<T> subList(int i, int i2) {
        return this.list.subList(i, i2);
    }

    @Override
    public Object[] toArray() {
        return this.list.toArray();
    }

    @Override
    public <E> E[] toArray(E[] eArr) {
        return (E[]) this.list.toArray(eArr);
    }

    @Override
    public String toString() {
        return this.list.toString();
    }
}
