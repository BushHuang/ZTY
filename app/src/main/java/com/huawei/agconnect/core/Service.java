package com.huawei.agconnect.core;

import com.huawei.agconnect.annotation.Singleton;
import java.lang.reflect.Modifier;

public class Service {

    private final Class<?> f172a;
    private final Class<?> b;
    private final Object c;
    private boolean d;

    public static class Builder {

        Class<?> f173a;
        Class<?> b;
        Object c;
        private boolean d;

        public Service build() {
            Class<?> cls = this.f173a;
            if (cls == null) {
                throw new IllegalArgumentException("the interface parameter cannot be NULL");
            }
            Class<?> cls2 = this.b;
            if (cls2 != null) {
                if (cls2.isInterface() || !Modifier.isPublic(this.b.getModifiers())) {
                    throw new IllegalArgumentException("the clazz parameter cant be interface type or not public");
                }
                Service service = new Service((Class) this.f173a, (Class) this.b);
                service.d = this.d;
                return service;
            }
            Object obj = this.c;
            if (obj == null) {
                throw new IllegalArgumentException("the clazz or object parameter must set one");
            }
            Service service2 = new Service(cls, obj);
            service2.d = this.d;
            return service2;
        }

        public Builder isSingleton(boolean z) {
            this.d = z;
            return this;
        }

        public Builder setClass(Class<?> cls) {
            this.b = cls;
            return this;
        }

        public Builder setInterface(Class<?> cls) {
            this.f173a = cls;
            return this;
        }

        public Builder setObject(Object obj) {
            this.c = obj;
            return this;
        }
    }

    private Service(Class<?> cls, Class<?> cls2) {
        this.f172a = cls;
        this.b = cls2;
        this.c = null;
    }

    private Service(Class<?> cls, Object obj) {
        this.f172a = cls;
        this.b = null;
        this.c = obj;
    }

    public static Builder builder(Class<?> cls) {
        return new Builder().setInterface(cls).setClass(cls).isSingleton(cls.isAnnotationPresent(Singleton.class));
    }

    public static Builder builder(Class<?> cls, Class<?> cls2) {
        return new Builder().setInterface(cls).setClass(cls2).isSingleton(cls2.isAnnotationPresent(Singleton.class));
    }

    public static Builder builder(Class<?> cls, Object obj) {
        return new Builder().setInterface(cls).setObject(obj).isSingleton(true);
    }

    public Object getInstance() {
        return this.c;
    }

    public Class<?> getInterface() {
        return this.f172a;
    }

    public Class<?> getType() {
        return this.b;
    }

    public boolean isSingleton() {
        return this.d;
    }
}
