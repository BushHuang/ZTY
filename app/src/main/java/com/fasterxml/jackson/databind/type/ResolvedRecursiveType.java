package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;

public class ResolvedRecursiveType extends TypeBase {
    private static final long serialVersionUID = 1;
    protected JavaType _referencedType;

    public ResolvedRecursiveType(Class<?> cls, TypeBindings typeBindings) {
        super(cls, typeBindings, null, null, 0, null, null, false);
    }

    @Override
    @Deprecated
    protected JavaType _narrow(Class<?> cls) {
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == getClass()) {
        }
        return false;
    }

    @Override
    public StringBuilder getErasedSignature(StringBuilder sb) {
        return this._referencedType.getErasedSignature(sb);
    }

    @Override
    public StringBuilder getGenericSignature(StringBuilder sb) {
        return this._referencedType.getGenericSignature(sb);
    }

    public JavaType getSelfReferencedType() {
        return this._referencedType;
    }

    @Override
    public JavaType getSuperClass() {
        JavaType javaType = this._referencedType;
        return javaType != null ? javaType.getSuperClass() : super.getSuperClass();
    }

    @Override
    public boolean isContainerType() {
        return false;
    }

    @Override
    public JavaType refine(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        return null;
    }

    public void setReference(JavaType javaType) {
        if (this._referencedType == null) {
            this._referencedType = javaType;
            return;
        }
        throw new IllegalStateException("Trying to re-set self reference; old value = " + this._referencedType + ", new = " + javaType);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(40);
        sb.append("[recursive type; ");
        JavaType javaType = this._referencedType;
        if (javaType == null) {
            sb.append("UNRESOLVED");
        } else {
            sb.append(javaType.getRawClass().getName());
        }
        return sb.toString();
    }

    @Override
    public JavaType withContentType(JavaType javaType) {
        return this;
    }

    @Override
    public JavaType withContentTypeHandler(Object obj) {
        return this;
    }

    @Override
    public JavaType withContentValueHandler(Object obj) {
        return this;
    }

    @Override
    public JavaType withStaticTyping() {
        return this;
    }

    @Override
    public JavaType withTypeHandler(Object obj) {
        return this;
    }

    @Override
    public JavaType withValueHandler(Object obj) {
        return this;
    }
}
