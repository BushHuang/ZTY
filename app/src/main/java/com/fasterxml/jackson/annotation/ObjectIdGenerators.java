package com.fasterxml.jackson.annotation;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import java.util.UUID;

public class ObjectIdGenerators {

    private static abstract class Base<T> extends ObjectIdGenerator<T> {
        protected final Class<?> _scope;

        protected Base(Class<?> cls) {
            this._scope = cls;
        }

        @Override
        public boolean canUseFor(ObjectIdGenerator<?> objectIdGenerator) {
            return objectIdGenerator.getClass() == getClass() && objectIdGenerator.getScope() == this._scope;
        }

        @Override
        public abstract T generateId(Object obj);

        @Override
        public final Class<?> getScope() {
            return this._scope;
        }
    }

    public static final class IntSequenceGenerator extends Base<Integer> {
        private static final long serialVersionUID = 1;
        protected transient int _nextValue;

        public IntSequenceGenerator() {
            this(Object.class, -1);
        }

        public IntSequenceGenerator(Class<?> cls, int i) {
            super(cls);
            this._nextValue = i;
        }

        @Override
        public boolean canUseFor(ObjectIdGenerator objectIdGenerator) {
            return super.canUseFor(objectIdGenerator);
        }

        @Override
        public ObjectIdGenerator<Integer> forScope(Class<?> cls) {
            return this._scope == cls ? this : new IntSequenceGenerator(cls, this._nextValue);
        }

        @Override
        public Integer generateId(Object obj) {
            if (obj == null) {
                return null;
            }
            int i = this._nextValue;
            this._nextValue = i + 1;
            return Integer.valueOf(i);
        }

        protected int initialValue() {
            return 1;
        }

        @Override
        public ObjectIdGenerator.IdKey key(Object obj) {
            if (obj == null) {
                return null;
            }
            return new ObjectIdGenerator.IdKey(getClass(), this._scope, obj);
        }

        @Override
        public ObjectIdGenerator<Integer> newForSerialization(Object obj) {
            return new IntSequenceGenerator(this._scope, initialValue());
        }
    }

    public static abstract class None extends ObjectIdGenerator<Object> {
    }

    public static abstract class PropertyGenerator extends Base<Object> {
        private static final long serialVersionUID = 1;

        protected PropertyGenerator(Class<?> cls) {
            super(cls);
        }

        @Override
        public boolean canUseFor(ObjectIdGenerator objectIdGenerator) {
            return super.canUseFor(objectIdGenerator);
        }
    }

    public static final class StringIdGenerator extends Base<String> {
        private static final long serialVersionUID = 1;

        public StringIdGenerator() {
            this(Object.class);
        }

        private StringIdGenerator(Class<?> cls) {
            super(Object.class);
        }

        @Override
        public boolean canUseFor(ObjectIdGenerator<?> objectIdGenerator) {
            return objectIdGenerator instanceof StringIdGenerator;
        }

        @Override
        public ObjectIdGenerator<String> forScope(Class<?> cls) {
            return this;
        }

        @Override
        public String generateId(Object obj) {
            return UUID.randomUUID().toString();
        }

        @Override
        public ObjectIdGenerator.IdKey key(Object obj) {
            if (obj == null) {
                return null;
            }
            return new ObjectIdGenerator.IdKey(getClass(), null, obj);
        }

        @Override
        public ObjectIdGenerator<String> newForSerialization(Object obj) {
            return this;
        }
    }

    public static final class UUIDGenerator extends Base<UUID> {
        private static final long serialVersionUID = 1;

        public UUIDGenerator() {
            this(Object.class);
        }

        private UUIDGenerator(Class<?> cls) {
            super(Object.class);
        }

        @Override
        public boolean canUseFor(ObjectIdGenerator<?> objectIdGenerator) {
            return objectIdGenerator.getClass() == getClass();
        }

        @Override
        public ObjectIdGenerator<UUID> forScope(Class<?> cls) {
            return this;
        }

        @Override
        public UUID generateId(Object obj) {
            return UUID.randomUUID();
        }

        @Override
        public ObjectIdGenerator.IdKey key(Object obj) {
            if (obj == null) {
                return null;
            }
            return new ObjectIdGenerator.IdKey(getClass(), null, obj);
        }

        @Override
        public ObjectIdGenerator<UUID> newForSerialization(Object obj) {
            return this;
        }
    }
}
