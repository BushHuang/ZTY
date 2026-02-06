package com.fasterxml.jackson.databind.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<T> implements Iterator<T>, Iterable<T> {
    private final T[] _a;
    private int _index = 0;

    public ArrayIterator(T[] tArr) {
        this._a = tArr;
    }

    @Override
    public boolean hasNext() {
        return this._index < this._a.length;
    }

    @Override
    public Iterator<T> iterator() {
        return this;
    }

    @Override
    public T next() {
        int i = this._index;
        T[] tArr = this._a;
        if (i >= tArr.length) {
            throw new NoSuchElementException();
        }
        this._index = i + 1;
        return tArr[i];
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
