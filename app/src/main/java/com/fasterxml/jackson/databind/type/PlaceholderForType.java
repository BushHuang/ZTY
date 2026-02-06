package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;

public class PlaceholderForType extends TypeBase {
    private static final long serialVersionUID = 1;
    protected JavaType _actualType;
    protected final int _ordinal;

    public PlaceholderForType(int i) {
        super(Object.class, TypeBindings.emptyBindings(), TypeFactory.unknownType(), null, 1, null, null, false);
        this._ordinal = i;
    }

    private <T> T _unsupported() {
        throw new UnsupportedOperationException("Operation should not be attempted on " + getClass().getName());
    }

    @Override
    protected JavaType _narrow(Class<?> cls) {
        return (JavaType) _unsupported();
    }

    public JavaType actualType() {
        return this._actualType;
    }

    public void actualType(JavaType javaType) {
        this._actualType = javaType;
    }

    @Override
    protected String buildCanonicalName() {
        return toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this;
    }

    @Override
    public StringBuilder getErasedSignature(StringBuilder sb) {
        sb.append('$');
        sb.append(this._ordinal + 1);
        return sb;
    }

    @Override
    public StringBuilder getGenericSignature(StringBuilder sb) {
        return getErasedSignature(sb);
    }

    @Override
    public boolean isContainerType() {
        return false;
    }

    @Override
    public JavaType refine(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        return (JavaType) _unsupported();
    }

    @Override
    public String toString() {
        return getErasedSignature(new StringBuilder()).toString();
    }

    @Override
    public JavaType withContentType(JavaType javaType) {
        return (JavaType) _unsupported();
    }

    @Override
    public JavaType withContentTypeHandler(Object obj) {
        return (JavaType) _unsupported();
    }

    @Override
    public JavaType withContentValueHandler(Object obj) {
        return (JavaType) _unsupported();
    }

    @Override
    public JavaType withStaticTyping() {
        return (JavaType) _unsupported();
    }

    @Override
    public JavaType withTypeHandler(Object obj) {
        return (JavaType) _unsupported();
    }

    @Override
    public JavaType withValueHandler(Object obj) {
        return (JavaType) _unsupported();
    }
}
