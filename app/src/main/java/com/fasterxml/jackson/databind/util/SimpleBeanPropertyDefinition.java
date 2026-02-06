package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.util.Collections;
import java.util.Iterator;

public class SimpleBeanPropertyDefinition extends BeanPropertyDefinition {
    protected final AnnotationIntrospector _annotationIntrospector;
    protected final PropertyName _fullName;
    protected final JsonInclude.Value _inclusion;
    protected final AnnotatedMember _member;
    protected final PropertyMetadata _metadata;

    protected SimpleBeanPropertyDefinition(AnnotationIntrospector annotationIntrospector, AnnotatedMember annotatedMember, PropertyName propertyName, PropertyMetadata propertyMetadata, JsonInclude.Value value) {
        this._annotationIntrospector = annotationIntrospector;
        this._member = annotatedMember;
        this._fullName = propertyName;
        this._metadata = propertyMetadata == null ? PropertyMetadata.STD_OPTIONAL : propertyMetadata;
        this._inclusion = value;
    }

    public static SimpleBeanPropertyDefinition construct(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember) {
        return new SimpleBeanPropertyDefinition(mapperConfig.getAnnotationIntrospector(), annotatedMember, PropertyName.construct(annotatedMember.getName()), null, EMPTY_INCLUDE);
    }

    public static SimpleBeanPropertyDefinition construct(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, PropertyName propertyName) {
        return construct(mapperConfig, annotatedMember, propertyName, (PropertyMetadata) null, EMPTY_INCLUDE);
    }

    public static SimpleBeanPropertyDefinition construct(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, PropertyName propertyName, PropertyMetadata propertyMetadata, JsonInclude.Include include) {
        return new SimpleBeanPropertyDefinition(mapperConfig.getAnnotationIntrospector(), annotatedMember, propertyName, propertyMetadata, (include == null || include == JsonInclude.Include.USE_DEFAULTS) ? EMPTY_INCLUDE : JsonInclude.Value.construct(include, null));
    }

    public static SimpleBeanPropertyDefinition construct(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, PropertyName propertyName, PropertyMetadata propertyMetadata, JsonInclude.Value value) {
        return new SimpleBeanPropertyDefinition(mapperConfig.getAnnotationIntrospector(), annotatedMember, propertyName, propertyMetadata, value);
    }

    @Override
    public JsonInclude.Value findInclusion() {
        return this._inclusion;
    }

    @Override
    public AnnotatedParameter getConstructorParameter() {
        AnnotatedMember annotatedMember = this._member;
        if (annotatedMember instanceof AnnotatedParameter) {
            return (AnnotatedParameter) annotatedMember;
        }
        return null;
    }

    @Override
    public Iterator<AnnotatedParameter> getConstructorParameters() {
        AnnotatedParameter constructorParameter = getConstructorParameter();
        return constructorParameter == null ? ClassUtil.emptyIterator() : Collections.singleton(constructorParameter).iterator();
    }

    @Override
    public AnnotatedField getField() {
        AnnotatedMember annotatedMember = this._member;
        if (annotatedMember instanceof AnnotatedField) {
            return (AnnotatedField) annotatedMember;
        }
        return null;
    }

    @Override
    public PropertyName getFullName() {
        return this._fullName;
    }

    @Override
    public AnnotatedMethod getGetter() {
        AnnotatedMember annotatedMember = this._member;
        if ((annotatedMember instanceof AnnotatedMethod) && ((AnnotatedMethod) annotatedMember).getParameterCount() == 0) {
            return (AnnotatedMethod) this._member;
        }
        return null;
    }

    @Override
    public String getInternalName() {
        return getName();
    }

    @Override
    public PropertyMetadata getMetadata() {
        return this._metadata;
    }

    @Override
    public String getName() {
        return this._fullName.getSimpleName();
    }

    @Override
    public AnnotatedMember getPrimaryMember() {
        return this._member;
    }

    @Override
    public JavaType getPrimaryType() {
        AnnotatedMember annotatedMember = this._member;
        return annotatedMember == null ? TypeFactory.unknownType() : annotatedMember.getType();
    }

    @Override
    public Class<?> getRawPrimaryType() {
        AnnotatedMember annotatedMember = this._member;
        return annotatedMember == null ? Object.class : annotatedMember.getRawType();
    }

    @Override
    public AnnotatedMethod getSetter() {
        AnnotatedMember annotatedMember = this._member;
        if ((annotatedMember instanceof AnnotatedMethod) && ((AnnotatedMethod) annotatedMember).getParameterCount() == 1) {
            return (AnnotatedMethod) this._member;
        }
        return null;
    }

    @Override
    public PropertyName getWrapperName() {
        AnnotatedMember annotatedMember;
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        if (annotationIntrospector == null || (annotatedMember = this._member) == null) {
            return null;
        }
        return annotationIntrospector.findWrapperName(annotatedMember);
    }

    @Override
    public boolean hasConstructorParameter() {
        return this._member instanceof AnnotatedParameter;
    }

    @Override
    public boolean hasField() {
        return this._member instanceof AnnotatedField;
    }

    @Override
    public boolean hasGetter() {
        return getGetter() != null;
    }

    @Override
    public boolean hasName(PropertyName propertyName) {
        return this._fullName.equals(propertyName);
    }

    @Override
    public boolean hasSetter() {
        return getSetter() != null;
    }

    @Override
    public boolean isExplicitlyIncluded() {
        return false;
    }

    @Override
    public boolean isExplicitlyNamed() {
        return false;
    }

    public BeanPropertyDefinition withInclusion(JsonInclude.Value value) {
        return this._inclusion == value ? this : new SimpleBeanPropertyDefinition(this._annotationIntrospector, this._member, this._fullName, this._metadata, value);
    }

    public BeanPropertyDefinition withMetadata(PropertyMetadata propertyMetadata) {
        return propertyMetadata.equals(this._metadata) ? this : new SimpleBeanPropertyDefinition(this._annotationIntrospector, this._member, this._fullName, propertyMetadata, this._inclusion);
    }

    @Override
    public BeanPropertyDefinition withName(PropertyName propertyName) {
        return this._fullName.equals(propertyName) ? this : new SimpleBeanPropertyDefinition(this._annotationIntrospector, this._member, propertyName, this._metadata, this._inclusion);
    }

    @Override
    public BeanPropertyDefinition withSimpleName(String str) {
        return (!this._fullName.hasSimpleName(str) || this._fullName.hasNamespace()) ? new SimpleBeanPropertyDefinition(this._annotationIntrospector, this._member, new PropertyName(str), this._metadata, this._inclusion) : this;
    }
}
