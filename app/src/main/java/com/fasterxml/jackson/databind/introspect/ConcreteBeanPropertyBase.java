package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public abstract class ConcreteBeanPropertyBase implements BeanProperty, Serializable {
    private static final long serialVersionUID = 1;
    protected transient List<PropertyName> _aliases;
    protected final PropertyMetadata _metadata;
    protected transient JsonFormat.Value _propertyFormat;

    protected ConcreteBeanPropertyBase(PropertyMetadata propertyMetadata) {
        this._metadata = propertyMetadata == null ? PropertyMetadata.STD_REQUIRED_OR_OPTIONAL : propertyMetadata;
    }

    protected ConcreteBeanPropertyBase(ConcreteBeanPropertyBase concreteBeanPropertyBase) {
        this._metadata = concreteBeanPropertyBase._metadata;
        this._propertyFormat = concreteBeanPropertyBase._propertyFormat;
    }

    @Override
    public List<PropertyName> findAliases(MapperConfig<?> mapperConfig) {
        List<PropertyName> listEmptyList = this._aliases;
        if (listEmptyList == null) {
            AnnotationIntrospector annotationIntrospector = mapperConfig.getAnnotationIntrospector();
            if (annotationIntrospector != null) {
                listEmptyList = annotationIntrospector.findPropertyAliases(getMember());
            }
            if (listEmptyList == null) {
                listEmptyList = Collections.emptyList();
            }
            this._aliases = listEmptyList;
        }
        return listEmptyList;
    }

    @Override
    @Deprecated
    public final JsonFormat.Value findFormatOverrides(AnnotationIntrospector annotationIntrospector) {
        AnnotatedMember member;
        JsonFormat.Value valueFindFormat = (annotationIntrospector == null || (member = getMember()) == null) ? null : annotationIntrospector.findFormat(member);
        return valueFindFormat == null ? EMPTY_FORMAT : valueFindFormat;
    }

    @Override
    public JsonFormat.Value findPropertyFormat(MapperConfig<?> mapperConfig, Class<?> cls) {
        AnnotatedMember member;
        JsonFormat.Value valueFindFormat = this._propertyFormat;
        if (valueFindFormat == null) {
            JsonFormat.Value defaultPropertyFormat = mapperConfig.getDefaultPropertyFormat(cls);
            valueFindFormat = null;
            AnnotationIntrospector annotationIntrospector = mapperConfig.getAnnotationIntrospector();
            if (annotationIntrospector != null && (member = getMember()) != null) {
                valueFindFormat = annotationIntrospector.findFormat(member);
            }
            if (defaultPropertyFormat != null) {
                if (valueFindFormat != null) {
                    defaultPropertyFormat = defaultPropertyFormat.withOverrides(valueFindFormat);
                }
                valueFindFormat = defaultPropertyFormat;
            } else if (valueFindFormat == null) {
                valueFindFormat = EMPTY_FORMAT;
            }
            this._propertyFormat = valueFindFormat;
        }
        return valueFindFormat;
    }

    @Override
    public JsonInclude.Value findPropertyInclusion(MapperConfig<?> mapperConfig, Class<?> cls) {
        AnnotationIntrospector annotationIntrospector = mapperConfig.getAnnotationIntrospector();
        AnnotatedMember member = getMember();
        if (member == null) {
            return mapperConfig.getDefaultPropertyInclusion(cls);
        }
        JsonInclude.Value defaultInclusion = mapperConfig.getDefaultInclusion(cls, member.getRawType());
        if (annotationIntrospector == null) {
            return defaultInclusion;
        }
        JsonInclude.Value valueFindPropertyInclusion = annotationIntrospector.findPropertyInclusion(member);
        return defaultInclusion == null ? valueFindPropertyInclusion : defaultInclusion.withOverrides(valueFindPropertyInclusion);
    }

    @Override
    public PropertyMetadata getMetadata() {
        return this._metadata;
    }

    @Override
    public boolean isRequired() {
        return this._metadata.isRequired();
    }

    @Override
    public boolean isVirtual() {
        return false;
    }
}
