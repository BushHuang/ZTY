package butterknife;

import java.util.AbstractList;
import java.util.RandomAccess;

final class ImmutableList<T> extends AbstractList<T> implements RandomAccess {
    private final T[] views;

    ImmutableList(T[] tArr) {
        this.views = tArr;
    }

    @Override
    public boolean contains(Object obj) {
        for (T t : this.views) {
            if (t == obj) {
                return true;
            }
        }
        return false;
    }

    @Override
    public T get(int i) {
        return this.views[i];
    }

    @Override
    public int size() {
        return this.views.length;
    }
}
