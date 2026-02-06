package com.zaze.utils.compat;

import android.os.Build;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class OptionalCompat<T> {
    private static final OptionalCompat<?> EMPTY = new OptionalCompat<>();
    private final T value;

    private OptionalCompat() {
        this.value = null;
    }

    private OptionalCompat(T t) {
        this.value = (T) requireNonNull(t);
    }

    public static <T> OptionalCompat<T> empty() {
        return (OptionalCompat<T>) EMPTY;
    }

    public static <T> OptionalCompat<T> of(T t) {
        return new OptionalCompat<>(t);
    }

    public static <T> OptionalCompat<T> ofNullable(T t) {
        return t == null ? empty() : of(t);
    }

    public static <T> T requireNonNull(T t) {
        if (t != null) {
            return t;
        }
        throw null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OptionalCompat)) {
            return false;
        }
        OptionalCompat optionalCompat = (OptionalCompat) obj;
        if (Build.VERSION.SDK_INT >= 19) {
            return Objects.equals(this.value, optionalCompat.value);
        }
        T t = this.value;
        T t2 = optionalCompat.value;
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
        if (Build.VERSION.SDK_INT >= 19) {
            return Objects.hashCode(this.value);
        }
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
