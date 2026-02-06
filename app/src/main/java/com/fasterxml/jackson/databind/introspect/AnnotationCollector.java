package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.util.Annotations;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class AnnotationCollector {
    protected static final Annotations NO_ANNOTATIONS = new NoAnnotations();
    protected final Object _data;

    static class EmptyCollector extends AnnotationCollector {
        public static final EmptyCollector instance = new EmptyCollector(null);

        EmptyCollector(Object obj) {
            super(obj);
        }

        @Override
        public AnnotationCollector addOrOverride(Annotation annotation) {
            return new OneCollector(this._data, annotation.annotationType(), annotation);
        }

        @Override
        public AnnotationMap asAnnotationMap() {
            return new AnnotationMap();
        }

        @Override
        public Annotations asAnnotations() {
            return NO_ANNOTATIONS;
        }

        @Override
        public boolean isPresent(Annotation annotation) {
            return false;
        }
    }

    static class NCollector extends AnnotationCollector {
        protected final HashMap<Class<?>, Annotation> _annotations;

        public NCollector(Object obj, Class<?> cls, Annotation annotation, Class<?> cls2, Annotation annotation2) {
            super(obj);
            HashMap<Class<?>, Annotation> map = new HashMap<>();
            this._annotations = map;
            map.put(cls, annotation);
            this._annotations.put(cls2, annotation2);
        }

        @Override
        public AnnotationCollector addOrOverride(Annotation annotation) {
            this._annotations.put(annotation.annotationType(), annotation);
            return this;
        }

        @Override
        public AnnotationMap asAnnotationMap() {
            AnnotationMap annotationMap = new AnnotationMap();
            Iterator<Annotation> it = this._annotations.values().iterator();
            while (it.hasNext()) {
                annotationMap.add(it.next());
            }
            return annotationMap;
        }

        @Override
        public Annotations asAnnotations() {
            if (this._annotations.size() != 2) {
                return new AnnotationMap(this._annotations);
            }
            Iterator<Map.Entry<Class<?>, Annotation>> it = this._annotations.entrySet().iterator();
            Map.Entry<Class<?>, Annotation> next = it.next();
            Map.Entry<Class<?>, Annotation> next2 = it.next();
            return new TwoAnnotations(next.getKey(), next.getValue(), next2.getKey(), next2.getValue());
        }

        @Override
        public boolean isPresent(Annotation annotation) {
            return this._annotations.containsKey(annotation.annotationType());
        }
    }

    public static class NoAnnotations implements Annotations, Serializable {
        private static final long serialVersionUID = 1;

        NoAnnotations() {
        }

        @Override
        public <A extends Annotation> A get(Class<A> cls) {
            return null;
        }

        @Override
        public boolean has(Class<?> cls) {
            return false;
        }

        @Override
        public boolean hasOneOf(Class<? extends Annotation>[] clsArr) {
            return false;
        }

        @Override
        public int size() {
            return 0;
        }
    }

    public static class OneAnnotation implements Annotations, Serializable {
        private static final long serialVersionUID = 1;
        private final Class<?> _type;
        private final Annotation _value;

        public OneAnnotation(Class<?> cls, Annotation annotation) {
            this._type = cls;
            this._value = annotation;
        }

        @Override
        public <A extends Annotation> A get(Class<A> cls) {
            if (this._type == cls) {
                return (A) this._value;
            }
            return null;
        }

        @Override
        public boolean has(Class<?> cls) {
            return this._type == cls;
        }

        @Override
        public boolean hasOneOf(Class<? extends Annotation>[] clsArr) {
            for (Class<? extends Annotation> cls : clsArr) {
                if (cls == this._type) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public int size() {
            return 1;
        }
    }

    static class OneCollector extends AnnotationCollector {
        private Class<?> _type;
        private Annotation _value;

        public OneCollector(Object obj, Class<?> cls, Annotation annotation) {
            super(obj);
            this._type = cls;
            this._value = annotation;
        }

        @Override
        public AnnotationCollector addOrOverride(Annotation annotation) {
            Class<? extends Annotation> clsAnnotationType = annotation.annotationType();
            if (this._type != clsAnnotationType) {
                return new NCollector(this._data, this._type, this._value, clsAnnotationType, annotation);
            }
            this._value = annotation;
            return this;
        }

        @Override
        public AnnotationMap asAnnotationMap() {
            return AnnotationMap.of(this._type, this._value);
        }

        @Override
        public Annotations asAnnotations() {
            return new OneAnnotation(this._type, this._value);
        }

        @Override
        public boolean isPresent(Annotation annotation) {
            return annotation.annotationType() == this._type;
        }
    }

    public static class TwoAnnotations implements Annotations, Serializable {
        private static final long serialVersionUID = 1;
        private final Class<?> _type1;
        private final Class<?> _type2;
        private final Annotation _value1;
        private final Annotation _value2;

        public TwoAnnotations(Class<?> cls, Annotation annotation, Class<?> cls2, Annotation annotation2) {
            this._type1 = cls;
            this._value1 = annotation;
            this._type2 = cls2;
            this._value2 = annotation2;
        }

        @Override
        public <A extends Annotation> A get(Class<A> cls) {
            if (this._type1 == cls) {
                return (A) this._value1;
            }
            if (this._type2 == cls) {
                return (A) this._value2;
            }
            return null;
        }

        @Override
        public boolean has(Class<?> cls) {
            return this._type1 == cls || this._type2 == cls;
        }

        @Override
        public boolean hasOneOf(Class<? extends Annotation>[] clsArr) {
            for (Class<? extends Annotation> cls : clsArr) {
                if (cls == this._type1 || cls == this._type2) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public int size() {
            return 2;
        }
    }

    protected AnnotationCollector(Object obj) {
        this._data = obj;
    }

    public static Annotations emptyAnnotations() {
        return NO_ANNOTATIONS;
    }

    public static AnnotationCollector emptyCollector() {
        return EmptyCollector.instance;
    }

    public static AnnotationCollector emptyCollector(Object obj) {
        return new EmptyCollector(obj);
    }

    public abstract AnnotationCollector addOrOverride(Annotation annotation);

    public abstract AnnotationMap asAnnotationMap();

    public abstract Annotations asAnnotations();

    public Object getData() {
        return this._data;
    }

    public abstract boolean isPresent(Annotation annotation);
}
