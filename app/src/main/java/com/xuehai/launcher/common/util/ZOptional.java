package com.xuehai.launcher.common.util;

import android.os.Build;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class ZOptional<T> {
    private static final ZOptional<?> EMPTY = new ZOptional<>();
    private final T value;

    private ZOptional() {
        this.value = null;
    }

    private ZOptional(T t) {
        if (t == null) {
            throw null;
        }
        this.value = t;
    }

    public static <T> ZOptional<T> empty() {
        return (ZOptional<T>) EMPTY;
    }

    public static <T> ZOptional<T> of(T t) {
        return new ZOptional<>(t);
    }

    public static <T> ZOptional<T> ofNullable(T t) {
        return t == null ? empty() : of(t);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ZOptional)) {
            return false;
        }
        ZOptional zOptional = (ZOptional) obj;
        if (Build.VERSION.SDK_INT >= 19) {
            return Objects.equals(this.value, zOptional.value);
        }
        T t = this.value;
        T t2 = zOptional.value;
        if (t != t2) {
            return t != null && t.equals(t2);
        }
        return true;
    }

    public T get() {
        T t = this.value;
        if (t != null) {
            return t;
        }
        throw new NoSuchElementException("No value present");
    }

    public int hashCode() {
        T t = this.value;
        if (t != null) {
            return t.hashCode();
        }
        return 0;
    }

    public boolean isPresent() {
        return this.value != null;
    }

    public T orElse(T t) {
        T t2 = this.value;
        return t2 != null ? t2 : t;
    }

    public String toString() {
        T t = this.value;
        return t != null ? String.format("Optional[%s]", t) : "Optional.empty";
    }
}
