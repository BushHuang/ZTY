package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.util.EnumMap;
import java.util.EnumSet;

public class ClassNameIdResolver extends TypeIdResolverBase {
    private static final String JAVA_UTIL_PKG = "java.util.";

    public ClassNameIdResolver(JavaType javaType, TypeFactory typeFactory) {
        super(javaType, typeFactory);
    }

    protected String _idFrom(Object obj, Class<?> cls, TypeFactory typeFactory) {
        if (Enum.class.isAssignableFrom(cls) && !cls.isEnum()) {
            cls = cls.getSuperclass();
        }
        String name = cls.getName();
        return name.startsWith("java.util.") ? obj instanceof EnumSet ? typeFactory.constructCollectionType(EnumSet.class, ClassUtil.findEnumType((EnumSet<?>) obj)).toCanonical() : obj instanceof EnumMap ? typeFactory.constructMapType(EnumMap.class, ClassUtil.findEnumType((EnumMap<?, ?>) obj), Object.class).toCanonical() : name : (name.indexOf(36) < 0 || ClassUtil.getOuterClass(cls) == null || ClassUtil.getOuterClass(this._baseType.getRawClass()) != null) ? name : this._baseType.getRawClass().getName();
    }

    protected JavaType _typeFromId(String str, DatabindContext databindContext) throws IOException, IllegalArgumentException {
        JavaType javaTypeResolveSubType = databindContext.resolveSubType(this._baseType, str);
        return (javaTypeResolveSubType == null && (databindContext instanceof DeserializationContext)) ? ((DeserializationContext) databindContext).handleUnknownTypeId(this._baseType, str, this, "no such class found") : javaTypeResolveSubType;
    }

    @Override
    public String getDescForKnownTypeIds() {
        return "class name used as type id";
    }

    @Override
    public JsonTypeInfo.Id getMechanism() {
        return JsonTypeInfo.Id.CLASS;
    }

    @Override
    public String idFromValue(Object obj) {
        return _idFrom(obj, obj.getClass(), this._typeFactory);
    }

    @Override
    public String idFromValueAndType(Object obj, Class<?> cls) {
        return _idFrom(obj, cls, this._typeFactory);
    }

    public void registerSubtype(Class<?> cls, String str) {
    }

    @Override
    public JavaType typeFromId(DatabindContext databindContext, String str) throws IOException {
        return _typeFromId(str, databindContext);
    }
}
