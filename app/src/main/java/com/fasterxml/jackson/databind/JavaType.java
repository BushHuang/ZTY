package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.List;

public abstract class JavaType extends ResolvedType implements Serializable, Type {
    private static final long serialVersionUID = 1;
    protected final boolean _asStatic;
    protected final Class<?> _class;
    protected final int _hash;
    protected final Object _typeHandler;
    protected final Object _valueHandler;

    protected JavaType(JavaType javaType) {
        this._class = javaType._class;
        this._hash = javaType._hash;
        this._valueHandler = javaType._valueHandler;
        this._typeHandler = javaType._typeHandler;
        this._asStatic = javaType._asStatic;
    }

    protected JavaType(Class<?> cls, int i, Object obj, Object obj2, boolean z) {
        this._class = cls;
        this._hash = cls.getName().hashCode() + i;
        this._valueHandler = obj;
        this._typeHandler = obj2;
        this._asStatic = z;
    }

    @Deprecated
    protected abstract JavaType _narrow(Class<?> cls);

    @Override
    public abstract JavaType containedType(int i);

    @Override
    public abstract int containedTypeCount();

    @Override
    @Deprecated
    public abstract String containedTypeName(int i);

    public JavaType containedTypeOrUnknown(int i) {
        JavaType javaTypeContainedType = containedType(i);
        return javaTypeContainedType == null ? TypeFactory.unknownType() : javaTypeContainedType;
    }

    public abstract boolean equals(Object obj);

    public abstract JavaType findSuperType(Class<?> cls);

    public abstract JavaType[] findTypeParameters(Class<?> cls);

    @Deprecated
    public JavaType forcedNarrowBy(Class<?> cls) {
        return cls == this._class ? this : _narrow(cls);
    }

    public abstract TypeBindings getBindings();

    @Override
    public JavaType getContentType() {
        return null;
    }

    public Object getContentTypeHandler() {
        return null;
    }

    public Object getContentValueHandler() {
        return null;
    }

    public String getErasedSignature() {
        StringBuilder sb = new StringBuilder(40);
        getErasedSignature(sb);
        return sb.toString();
    }

    public abstract StringBuilder getErasedSignature(StringBuilder sb);

    public String getGenericSignature() {
        StringBuilder sb = new StringBuilder(40);
        getGenericSignature(sb);
        return sb.toString();
    }

    public abstract StringBuilder getGenericSignature(StringBuilder sb);

    public abstract List<JavaType> getInterfaces();

    @Override
    public JavaType getKeyType() {
        return null;
    }

    @Override
    @Deprecated
    public Class<?> getParameterSource() {
        return null;
    }

    @Override
    public final Class<?> getRawClass() {
        return this._class;
    }

    @Override
    public JavaType getReferencedType() {
        return null;
    }

    public abstract JavaType getSuperClass();

    public <T> T getTypeHandler() {
        return (T) this._typeHandler;
    }

    public <T> T getValueHandler() {
        return (T) this._valueHandler;
    }

    public boolean hasContentType() {
        return true;
    }

    @Override
    public boolean hasGenericTypes() {
        return containedTypeCount() > 0;
    }

    public boolean hasHandlers() {
        return (this._typeHandler == null && this._valueHandler == null) ? false : true;
    }

    @Override
    public final boolean hasRawClass(Class<?> cls) {
        return this._class == cls;
    }

    public boolean hasValueHandler() {
        return this._valueHandler != null;
    }

    public final int hashCode() {
        return this._hash;
    }

    @Override
    public boolean isAbstract() {
        return Modifier.isAbstract(this._class.getModifiers());
    }

    @Override
    public boolean isArrayType() {
        return false;
    }

    @Override
    public boolean isCollectionLikeType() {
        return false;
    }

    @Override
    public boolean isConcrete() {
        if ((this._class.getModifiers() & 1536) == 0) {
            return true;
        }
        return this._class.isPrimitive();
    }

    @Override
    public abstract boolean isContainerType();

    @Override
    public final boolean isEnumType() {
        return this._class.isEnum();
    }

    @Override
    public final boolean isFinal() {
        return Modifier.isFinal(this._class.getModifiers());
    }

    @Override
    public final boolean isInterface() {
        return this._class.isInterface();
    }

    public final boolean isJavaLangObject() {
        return this._class == Object.class;
    }

    @Override
    public boolean isMapLikeType() {
        return false;
    }

    @Override
    public final boolean isPrimitive() {
        return this._class.isPrimitive();
    }

    @Override
    public boolean isThrowable() {
        return Throwable.class.isAssignableFrom(this._class);
    }

    public final boolean isTypeOrSubTypeOf(Class<?> cls) {
        Class<?> cls2 = this._class;
        return cls2 == cls || cls.isAssignableFrom(cls2);
    }

    public final boolean isTypeOrSuperTypeOf(Class<?> cls) {
        Class<?> cls2 = this._class;
        return cls2 == cls || cls2.isAssignableFrom(cls);
    }

    public abstract JavaType refine(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr);

    public abstract String toString();

    public final boolean useStaticType() {
        return this._asStatic;
    }

    public abstract JavaType withContentType(JavaType javaType);

    public abstract JavaType withContentTypeHandler(Object obj);

    public abstract JavaType withContentValueHandler(Object obj);

    public JavaType withHandlersFrom(JavaType javaType) {
        Object typeHandler = javaType.getTypeHandler();
        JavaType javaTypeWithTypeHandler = typeHandler != this._typeHandler ? withTypeHandler(typeHandler) : this;
        Object valueHandler = javaType.getValueHandler();
        return valueHandler != this._valueHandler ? javaTypeWithTypeHandler.withValueHandler(valueHandler) : javaTypeWithTypeHandler;
    }

    public abstract JavaType withStaticTyping();

    public abstract JavaType withTypeHandler(Object obj);

    public abstract JavaType withValueHandler(Object obj);
}
