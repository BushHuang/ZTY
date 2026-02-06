package com.huawei.hms.framework.common;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LimitQueue<E> extends ConcurrentLinkedQueue<E> {
    private static final String TAG = "LimitQueue";
    private static final long serialVersionUID = -4636313759149307798L;
    private boolean deduplication;
    private int limit;

    public LimitQueue(int i) {
        this.deduplication = false;
        this.limit = i;
    }

    public LimitQueue(int i, boolean z) {
        this.deduplication = false;
        this.limit = i;
        this.deduplication = z;
    }

    public LimitQueue(Collection<? extends E> collection, boolean z) {
        this(collection.size(), z);
        addAll(collection);
    }

    @Override
    public boolean add(E e) {
        if (this.deduplication) {
            super.remove(e);
        }
        if (super.size() >= this.limit) {
            super.poll();
        }
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        if (collection.size() > this.limit) {
            return false;
        }
        if (this.deduplication) {
            super.removeAll(collection);
        }
        for (int size = (collection.size() + super.size()) - this.limit; size > 0; size--) {
            super.poll();
        }
        return super.addAll(collection);
    }

    @Override
    public void clear() {
        super.clear();
    }

    public E get(int i) {
        Iterator<E> it = iterator();
        E next = null;
        for (int i2 = 0; i2 <= i && it.hasNext(); i2++) {
            next = it.next();
        }
        return next;
    }

    public int getLimit() {
        return this.limit;
    }

    @Override
    public boolean offer(E e) {
        if (this.deduplication) {
            super.remove(e);
        }
        if (super.size() >= this.limit) {
            super.poll();
        }
        return super.offer(e);
    }

    public E peekLast() {
        Iterator<E> it = iterator();
        E next = null;
        while (it.hasNext()) {
            next = it.next();
        }
        return next;
    }

    @Override
    public E poll() {
        return (E) super.poll();
    }

    @Override
    public E remove() {
        try {
            return (E) super.remove();
        } catch (NoSuchElementException unused) {
            Logger.w("LimitQueue", "remove failed, limitQueue is empty");
            return null;
        }
    }
}
