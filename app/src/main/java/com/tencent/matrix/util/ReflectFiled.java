package com.tencent.matrix.util;

import java.lang.reflect.Field;

public class ReflectFiled<Type> {
    private static final String TAG = "ReflectFiled";
    private Class<?> mClazz;
    private Field mField;
    private String mFieldName;
    private boolean mInit;

    public ReflectFiled(Class<?> cls, String str) {
        if (cls == null || str == null || str.length() == 0) {
            throw new IllegalArgumentException("Both of invoker and fieldName can not be null or nil.");
        }
        this.mClazz = cls;
        this.mFieldName = str;
    }

    private synchronized void prepare() {
        if (this.mInit) {
            return;
        }
        for (Class<?> superclass = this.mClazz; superclass != null; superclass = superclass.getSuperclass()) {
            try {
                Field declaredField = superclass.getDeclaredField(this.mFieldName);
                declaredField.setAccessible(true);
                this.mField = declaredField;
                break;
            } catch (Exception unused) {
            }
        }
        this.mInit = true;
    }

    public synchronized Type get() throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        return get(false);
    }

    public synchronized Type get(Object obj) throws IllegalAccessException, NoSuchFieldException {
        return get(false, obj);
    }

    public synchronized Type get(boolean z) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        prepare();
        if (this.mField != null) {
            try {
                return (Type) this.mField.get(null);
            } catch (ClassCastException unused) {
                throw new IllegalArgumentException("unable to cast object");
            }
        }
        if (!z) {
            throw new NoSuchFieldException();
        }
        MatrixLog.w("ReflectFiled", String.format("Field %s is no exists.", this.mFieldName), new Object[0]);
        return null;
    }

    public synchronized Type get(boolean z, Object obj) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        prepare();
        if (this.mField != null) {
            try {
                return (Type) this.mField.get(obj);
            } catch (ClassCastException unused) {
                throw new IllegalArgumentException("unable to cast object");
            }
        }
        if (!z) {
            throw new NoSuchFieldException();
        }
        MatrixLog.w("ReflectFiled", String.format("Field %s is no exists.", this.mFieldName), new Object[0]);
        return null;
    }

    public synchronized Type getWithoutThrow() {
        Type type;
        try {
            try {
                type = get(true);
            } catch (IllegalAccessException e) {
                MatrixLog.i("ReflectFiled", "getWithoutThrow, exception occur :%s", e);
                type = null;
                return type;
            } catch (IllegalArgumentException e2) {
                MatrixLog.i("ReflectFiled", "getWithoutThrow, exception occur :%s", e2);
                type = null;
                return type;
            }
        } catch (NoSuchFieldException e3) {
            MatrixLog.i("ReflectFiled", "getWithoutThrow, exception occur :%s", e3);
            type = null;
            return type;
        }
        return type;
    }

    public synchronized Type getWithoutThrow(Object obj) {
        Type type;
        try {
            try {
                type = get(true, obj);
            } catch (IllegalAccessException e) {
                MatrixLog.i("ReflectFiled", "getWithoutThrow, exception occur :%s", e);
                type = null;
                return type;
            } catch (IllegalArgumentException e2) {
                MatrixLog.i("ReflectFiled", "getWithoutThrow, exception occur :%s", e2);
                type = null;
                return type;
            }
        } catch (NoSuchFieldException e3) {
            MatrixLog.i("ReflectFiled", "getWithoutThrow, exception occur :%s", e3);
            type = null;
            return type;
        }
        return type;
    }

    public synchronized boolean set(Type type) throws IllegalAccessException, NoSuchFieldException {
        return set(null, type, false);
    }

    public synchronized boolean set(Object obj, Type type) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        return set(obj, type, false);
    }

    public synchronized boolean set(Object obj, Type type, boolean z) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        prepare();
        if (this.mField != null) {
            this.mField.set(obj, type);
            return true;
        }
        if (z) {
            MatrixLog.w("ReflectFiled", String.format("Field %s is no exists.", this.mFieldName), new Object[0]);
            return false;
        }
        throw new NoSuchFieldException("Method " + this.mFieldName + " is not exists.");
    }

    public synchronized boolean setWithoutThrow(Type type) {
        boolean z;
        z = false;
        try {
            try {
                z = set(null, type, true);
            } catch (IllegalAccessException e) {
                MatrixLog.i("ReflectFiled", "setWithoutThrow, exception occur :%s", e);
            } catch (IllegalArgumentException e2) {
                MatrixLog.i("ReflectFiled", "setWithoutThrow, exception occur :%s", e2);
            }
        } catch (NoSuchFieldException e3) {
            MatrixLog.i("ReflectFiled", "setWithoutThrow, exception occur :%s", e3);
        }
        return z;
    }

    public synchronized boolean setWithoutThrow(Object obj, Type type) {
        boolean z;
        z = false;
        try {
            try {
                z = set(obj, type, true);
            } catch (IllegalAccessException e) {
                MatrixLog.i("ReflectFiled", "setWithoutThrow, exception occur :%s", e);
            } catch (IllegalArgumentException e2) {
                MatrixLog.i("ReflectFiled", "setWithoutThrow, exception occur :%s", e2);
            }
        } catch (NoSuchFieldException e3) {
            MatrixLog.i("ReflectFiled", "setWithoutThrow, exception occur :%s", e3);
        }
        return z;
    }
}
