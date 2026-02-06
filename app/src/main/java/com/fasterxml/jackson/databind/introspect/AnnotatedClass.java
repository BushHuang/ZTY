package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotationCollector;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public final class AnnotatedClass extends Annotated implements TypeResolutionContext {
    private static final Creators NO_CREATORS = new Creators(null, Collections.emptyList(), Collections.emptyList());
    protected final AnnotationIntrospector _annotationIntrospector;
    protected final TypeBindings _bindings;
    protected final Class<?> _class;
    protected final Annotations _classAnnotations;
    protected Creators _creators;
    protected List<AnnotatedField> _fields;
    protected AnnotatedMethodMap _memberMethods;
    protected final ClassIntrospector.MixInResolver _mixInResolver;
    protected transient Boolean _nonStaticInnerClass;
    protected final Class<?> _primaryMixIn;
    protected final List<JavaType> _superTypes;
    protected final JavaType _type;
    protected final TypeFactory _typeFactory;

    public static final class Creators {
        public final List<AnnotatedConstructor> constructors;
        public final List<AnnotatedMethod> creatorMethods;
        public final AnnotatedConstructor defaultConstructor;

        public Creators(AnnotatedConstructor annotatedConstructor, List<AnnotatedConstructor> list, List<AnnotatedMethod> list2) {
            this.defaultConstructor = annotatedConstructor;
            this.constructors = list;
            this.creatorMethods = list2;
        }
    }

    AnnotatedClass(JavaType javaType, Class<?> cls, List<JavaType> list, Class<?> cls2, Annotations annotations, TypeBindings typeBindings, AnnotationIntrospector annotationIntrospector, ClassIntrospector.MixInResolver mixInResolver, TypeFactory typeFactory) {
        this._type = javaType;
        this._class = cls;
        this._superTypes = list;
        this._primaryMixIn = cls2;
        this._classAnnotations = annotations;
        this._bindings = typeBindings;
        this._annotationIntrospector = annotationIntrospector;
        this._mixInResolver = mixInResolver;
        this._typeFactory = typeFactory;
    }

    AnnotatedClass(Class<?> cls) {
        this._type = null;
        this._class = cls;
        this._superTypes = Collections.emptyList();
        this._primaryMixIn = null;
        this._classAnnotations = AnnotationCollector.emptyAnnotations();
        this._bindings = TypeBindings.emptyBindings();
        this._annotationIntrospector = null;
        this._mixInResolver = null;
        this._typeFactory = null;
    }

    private final Creators _creators() {
        Creators creatorsCollectCreators = this._creators;
        if (creatorsCollectCreators == null) {
            JavaType javaType = this._type;
            creatorsCollectCreators = javaType == null ? NO_CREATORS : AnnotatedCreatorCollector.collectCreators(this._annotationIntrospector, this, javaType, this._primaryMixIn);
            this._creators = creatorsCollectCreators;
        }
        return creatorsCollectCreators;
    }

    private final List<AnnotatedField> _fields() {
        List<AnnotatedField> listEmptyList = this._fields;
        if (listEmptyList == null) {
            JavaType javaType = this._type;
            listEmptyList = javaType == null ? Collections.emptyList() : AnnotatedFieldCollector.collectFields(this._annotationIntrospector, this, this._mixInResolver, this._typeFactory, javaType);
            this._fields = listEmptyList;
        }
        return listEmptyList;
    }

    private final AnnotatedMethodMap _methods() {
        AnnotatedMethodMap annotatedMethodMap = this._memberMethods;
        if (annotatedMethodMap == null) {
            JavaType javaType = this._type;
            annotatedMethodMap = javaType == null ? new AnnotatedMethodMap() : AnnotatedMethodCollector.collectMethods(this._annotationIntrospector, this, this._mixInResolver, this._typeFactory, javaType, this._superTypes, this._primaryMixIn);
            this._memberMethods = annotatedMethodMap;
        }
        return annotatedMethodMap;
    }

    @Deprecated
    public static AnnotatedClass construct(JavaType javaType, MapperConfig<?> mapperConfig) {
        return construct(javaType, mapperConfig, mapperConfig);
    }

    @Deprecated
    public static AnnotatedClass construct(JavaType javaType, MapperConfig<?> mapperConfig, ClassIntrospector.MixInResolver mixInResolver) {
        return AnnotatedClassResolver.resolve(mapperConfig, javaType, mixInResolver);
    }

    @Deprecated
    public static AnnotatedClass constructWithoutSuperTypes(Class<?> cls, MapperConfig<?> mapperConfig) {
        return constructWithoutSuperTypes(cls, mapperConfig, mapperConfig);
    }

    @Deprecated
    public static AnnotatedClass constructWithoutSuperTypes(Class<?> cls, MapperConfig<?> mapperConfig, ClassIntrospector.MixInResolver mixInResolver) {
        return AnnotatedClassResolver.resolveWithoutSuperTypes(mapperConfig, cls, mixInResolver);
    }

    @Override
    @Deprecated
    public Iterable<Annotation> annotations() {
        Annotations annotations = this._classAnnotations;
        if (annotations instanceof AnnotationMap) {
            return ((AnnotationMap) annotations).annotations();
        }
        if ((annotations instanceof AnnotationCollector.OneAnnotation) || (annotations instanceof AnnotationCollector.TwoAnnotations)) {
            throw new UnsupportedOperationException("please use getAnnotations/ hasAnnotation to check for Annotations");
        }
        return Collections.emptyList();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return ClassUtil.hasClass(obj, getClass()) && ((AnnotatedClass) obj)._class == this._class;
    }

    public Iterable<AnnotatedField> fields() {
        return _fields();
    }

    public AnnotatedMethod findMethod(String str, Class<?>[] clsArr) {
        return _methods().find(str, clsArr);
    }

    @Override
    public Class<?> getAnnotated() {
        return this._class;
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        return (A) this._classAnnotations.get(cls);
    }

    public Annotations getAnnotations() {
        return this._classAnnotations;
    }

    public List<AnnotatedConstructor> getConstructors() {
        return _creators().constructors;
    }

    public AnnotatedConstructor getDefaultConstructor() {
        return _creators().defaultConstructor;
    }

    public List<AnnotatedMethod> getFactoryMethods() {
        return _creators().creatorMethods;
    }

    public int getFieldCount() {
        return _fields().size();
    }

    public int getMemberMethodCount() {
        return _methods().size();
    }

    @Override
    public int getModifiers() {
        return this._class.getModifiers();
    }

    @Override
    public String getName() {
        return this._class.getName();
    }

    @Override
    public Class<?> getRawType() {
        return this._class;
    }

    @Deprecated
    public List<AnnotatedMethod> getStaticMethods() {
        return getFactoryMethods();
    }

    @Override
    public JavaType getType() {
        return this._type;
    }

    @Override
    public boolean hasAnnotation(Class<?> cls) {
        return this._classAnnotations.has(cls);
    }

    public boolean hasAnnotations() {
        return this._classAnnotations.size() > 0;
    }

    @Override
    public boolean hasOneOf(Class<? extends Annotation>[] clsArr) {
        return this._classAnnotations.hasOneOf(clsArr);
    }

    @Override
    public int hashCode() {
        return this._class.getName().hashCode();
    }

    public boolean isNonStaticInnerClass() {
        Boolean boolValueOf = this._nonStaticInnerClass;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(ClassUtil.isNonStaticInnerClass(this._class));
            this._nonStaticInnerClass = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }

    public Iterable<AnnotatedMethod> memberMethods() {
        return _methods();
    }

    @Override
    public JavaType resolveType(Type type) {
        return this._typeFactory.constructType(type, this._bindings);
    }

    @Override
    public String toString() {
        return "[AnnotedClass " + this._class.getName() + "]";
    }
}
