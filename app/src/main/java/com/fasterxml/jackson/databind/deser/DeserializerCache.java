package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.ReferenceType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public final class DeserializerCache implements Serializable {
    private static final long serialVersionUID = 1;
    protected final ConcurrentHashMap<JavaType, JsonDeserializer<Object>> _cachedDeserializers = new ConcurrentHashMap<>(64, 0.75f, 4);
    protected final HashMap<JavaType, JsonDeserializer<Object>> _incompleteDeserializers = new HashMap<>(8);

    private boolean _hasCustomHandlers(JavaType javaType) {
        if (!javaType.isContainerType()) {
            return false;
        }
        JavaType contentType = javaType.getContentType();
        if (contentType == null || (contentType.getValueHandler() == null && contentType.getTypeHandler() == null)) {
            return javaType.isMapLikeType() && javaType.getKeyType().getValueHandler() != null;
        }
        return true;
    }

    private Class<?> _verifyAsClass(Object obj, String str, Class<?> cls) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Class) {
            Class<?> cls2 = (Class) obj;
            if (cls2 == cls || ClassUtil.isBogusClass(cls2)) {
                return null;
            }
            return cls2;
        }
        throw new IllegalStateException("AnnotationIntrospector." + str + "() returned value of type " + obj.getClass().getName() + ": expected type JsonSerializer or Class<JsonSerializer> instead");
    }

    private JavaType modifyTypeByAnnotation(DeserializationContext deserializationContext, Annotated annotated, JavaType javaType) throws JsonMappingException {
        Object objFindContentDeserializer;
        JavaType keyType;
        Object objFindKeyDeserializer;
        KeyDeserializer keyDeserializerKeyDeserializerInstance;
        AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        if (annotationIntrospector == null) {
            return javaType;
        }
        if (javaType.isMapLikeType() && (keyType = javaType.getKeyType()) != null && keyType.getValueHandler() == null && (objFindKeyDeserializer = annotationIntrospector.findKeyDeserializer(annotated)) != null && (keyDeserializerKeyDeserializerInstance = deserializationContext.keyDeserializerInstance(annotated, objFindKeyDeserializer)) != null) {
            javaType = ((MapLikeType) javaType).withKeyValueHandler(keyDeserializerKeyDeserializerInstance);
            javaType.getKeyType();
        }
        JavaType contentType = javaType.getContentType();
        if (contentType != null && contentType.getValueHandler() == null && (objFindContentDeserializer = annotationIntrospector.findContentDeserializer(annotated)) != null) {
            JsonDeserializer<Object> jsonDeserializerDeserializerInstance = null;
            if (objFindContentDeserializer instanceof JsonDeserializer) {
            } else {
                Class<?> cls_verifyAsClass = _verifyAsClass(objFindContentDeserializer, "findContentDeserializer", JsonDeserializer.None.class);
                if (cls_verifyAsClass != null) {
                    jsonDeserializerDeserializerInstance = deserializationContext.deserializerInstance(annotated, cls_verifyAsClass);
                }
            }
            if (jsonDeserializerDeserializerInstance != null) {
                javaType = javaType.withContentValueHandler(jsonDeserializerDeserializerInstance);
            }
        }
        return annotationIntrospector.refineDeserializationType(deserializationContext.getConfig(), annotated, javaType);
    }

    protected JsonDeserializer<Object> _createAndCache2(DeserializationContext deserializationContext, DeserializerFactory deserializerFactory, JavaType javaType) throws JsonMappingException {
        try {
            JsonDeserializer<Object> jsonDeserializer_createDeserializer = _createDeserializer(deserializationContext, deserializerFactory, javaType);
            if (jsonDeserializer_createDeserializer == 0) {
                return null;
            }
            boolean z = !_hasCustomHandlers(javaType) && jsonDeserializer_createDeserializer.isCachable();
            if (jsonDeserializer_createDeserializer instanceof ResolvableDeserializer) {
                this._incompleteDeserializers.put(javaType, jsonDeserializer_createDeserializer);
                ((ResolvableDeserializer) jsonDeserializer_createDeserializer).resolve(deserializationContext);
                this._incompleteDeserializers.remove(javaType);
            }
            if (z) {
                this._cachedDeserializers.put(javaType, jsonDeserializer_createDeserializer);
            }
            return jsonDeserializer_createDeserializer;
        } catch (IllegalArgumentException e) {
            throw JsonMappingException.from(deserializationContext, ClassUtil.exceptionMessage(e), e);
        }
    }

    protected JsonDeserializer<Object> _createAndCacheValueDeserializer(DeserializationContext deserializationContext, DeserializerFactory deserializerFactory, JavaType javaType) throws JsonMappingException {
        JsonDeserializer<Object> jsonDeserializer;
        synchronized (this._incompleteDeserializers) {
            JsonDeserializer<Object> jsonDeserializer_findCachedDeserializer = _findCachedDeserializer(javaType);
            if (jsonDeserializer_findCachedDeserializer != null) {
                return jsonDeserializer_findCachedDeserializer;
            }
            int size = this._incompleteDeserializers.size();
            if (size > 0 && (jsonDeserializer = this._incompleteDeserializers.get(javaType)) != null) {
                return jsonDeserializer;
            }
            try {
                return _createAndCache2(deserializationContext, deserializerFactory, javaType);
            } finally {
                if (size == 0 && this._incompleteDeserializers.size() > 0) {
                    this._incompleteDeserializers.clear();
                }
            }
        }
    }

    protected JsonDeserializer<Object> _createDeserializer(DeserializationContext deserializationContext, DeserializerFactory deserializerFactory, JavaType javaType) throws JsonMappingException {
        DeserializationConfig config = deserializationContext.getConfig();
        if (javaType.isAbstract() || javaType.isMapLikeType() || javaType.isCollectionLikeType()) {
            javaType = deserializerFactory.mapAbstractType(config, javaType);
        }
        BeanDescription beanDescriptionIntrospect = config.introspect(javaType);
        JsonDeserializer<Object> jsonDeserializerFindDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationContext, beanDescriptionIntrospect.getClassInfo());
        if (jsonDeserializerFindDeserializerFromAnnotation != null) {
            return jsonDeserializerFindDeserializerFromAnnotation;
        }
        JavaType javaTypeModifyTypeByAnnotation = modifyTypeByAnnotation(deserializationContext, beanDescriptionIntrospect.getClassInfo(), javaType);
        if (javaTypeModifyTypeByAnnotation != javaType) {
            beanDescriptionIntrospect = config.introspect(javaTypeModifyTypeByAnnotation);
            javaType = javaTypeModifyTypeByAnnotation;
        }
        Class<?> clsFindPOJOBuilder = beanDescriptionIntrospect.findPOJOBuilder();
        if (clsFindPOJOBuilder != null) {
            return deserializerFactory.createBuilderBasedDeserializer(deserializationContext, javaType, beanDescriptionIntrospect, clsFindPOJOBuilder);
        }
        Converter<Object, Object> converterFindDeserializationConverter = beanDescriptionIntrospect.findDeserializationConverter();
        if (converterFindDeserializationConverter == null) {
            return _createDeserializer2(deserializationContext, deserializerFactory, javaType, beanDescriptionIntrospect);
        }
        JavaType inputType = converterFindDeserializationConverter.getInputType(deserializationContext.getTypeFactory());
        if (!inputType.hasRawClass(javaType.getRawClass())) {
            beanDescriptionIntrospect = config.introspect(inputType);
        }
        return new StdDelegatingDeserializer(converterFindDeserializationConverter, inputType, _createDeserializer2(deserializationContext, deserializerFactory, inputType, beanDescriptionIntrospect));
    }

    protected JsonDeserializer<?> _createDeserializer2(DeserializationContext deserializationContext, DeserializerFactory deserializerFactory, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        JsonFormat.Value valueFindExpectedFormat;
        JsonFormat.Value valueFindExpectedFormat2;
        DeserializationConfig config = deserializationContext.getConfig();
        if (javaType.isEnumType()) {
            return deserializerFactory.createEnumDeserializer(deserializationContext, javaType, beanDescription);
        }
        if (javaType.isContainerType()) {
            if (javaType.isArrayType()) {
                return deserializerFactory.createArrayDeserializer(deserializationContext, (ArrayType) javaType, beanDescription);
            }
            if (javaType.isMapLikeType() && ((valueFindExpectedFormat2 = beanDescription.findExpectedFormat(null)) == null || valueFindExpectedFormat2.getShape() != JsonFormat.Shape.OBJECT)) {
                MapLikeType mapLikeType = (MapLikeType) javaType;
                return mapLikeType.isTrueMapType() ? deserializerFactory.createMapDeserializer(deserializationContext, (MapType) mapLikeType, beanDescription) : deserializerFactory.createMapLikeDeserializer(deserializationContext, mapLikeType, beanDescription);
            }
            if (javaType.isCollectionLikeType() && ((valueFindExpectedFormat = beanDescription.findExpectedFormat(null)) == null || valueFindExpectedFormat.getShape() != JsonFormat.Shape.OBJECT)) {
                CollectionLikeType collectionLikeType = (CollectionLikeType) javaType;
                return collectionLikeType.isTrueCollectionType() ? deserializerFactory.createCollectionDeserializer(deserializationContext, (CollectionType) collectionLikeType, beanDescription) : deserializerFactory.createCollectionLikeDeserializer(deserializationContext, collectionLikeType, beanDescription);
            }
        }
        return javaType.isReferenceType() ? deserializerFactory.createReferenceDeserializer(deserializationContext, (ReferenceType) javaType, beanDescription) : JsonNode.class.isAssignableFrom(javaType.getRawClass()) ? deserializerFactory.createTreeDeserializer(config, javaType, beanDescription) : deserializerFactory.createBeanDeserializer(deserializationContext, javaType, beanDescription);
    }

    protected JsonDeserializer<Object> _findCachedDeserializer(JavaType javaType) {
        if (javaType == null) {
            throw new IllegalArgumentException("Null JavaType passed");
        }
        if (_hasCustomHandlers(javaType)) {
            return null;
        }
        return this._cachedDeserializers.get(javaType);
    }

    protected KeyDeserializer _handleUnknownKeyDeserializer(DeserializationContext deserializationContext, JavaType javaType) throws JsonMappingException {
        return (KeyDeserializer) deserializationContext.reportBadDefinition(javaType, "Cannot find a (Map) Key deserializer for type " + javaType);
    }

    protected JsonDeserializer<Object> _handleUnknownValueDeserializer(DeserializationContext deserializationContext, JavaType javaType) throws JsonMappingException {
        if (ClassUtil.isConcrete(javaType.getRawClass())) {
            return (JsonDeserializer) deserializationContext.reportBadDefinition(javaType, "Cannot find a Value deserializer for type " + javaType);
        }
        return (JsonDeserializer) deserializationContext.reportBadDefinition(javaType, "Cannot find a Value deserializer for abstract type " + javaType);
    }

    public int cachedDeserializersCount() {
        return this._cachedDeserializers.size();
    }

    protected Converter<Object, Object> findConverter(DeserializationContext deserializationContext, Annotated annotated) throws JsonMappingException {
        Object objFindDeserializationConverter = deserializationContext.getAnnotationIntrospector().findDeserializationConverter(annotated);
        if (objFindDeserializationConverter == null) {
            return null;
        }
        return deserializationContext.converterInstance(annotated, objFindDeserializationConverter);
    }

    protected JsonDeserializer<Object> findConvertingDeserializer(DeserializationContext deserializationContext, Annotated annotated, JsonDeserializer<Object> jsonDeserializer) throws JsonMappingException {
        Converter<Object, Object> converterFindConverter = findConverter(deserializationContext, annotated);
        return converterFindConverter == null ? jsonDeserializer : new StdDelegatingDeserializer(converterFindConverter, converterFindConverter.getInputType(deserializationContext.getTypeFactory()), jsonDeserializer);
    }

    protected JsonDeserializer<Object> findDeserializerFromAnnotation(DeserializationContext deserializationContext, Annotated annotated) throws JsonMappingException {
        Object objFindDeserializer = deserializationContext.getAnnotationIntrospector().findDeserializer(annotated);
        if (objFindDeserializer == null) {
            return null;
        }
        return findConvertingDeserializer(deserializationContext, annotated, deserializationContext.deserializerInstance(annotated, objFindDeserializer));
    }

    public KeyDeserializer findKeyDeserializer(DeserializationContext deserializationContext, DeserializerFactory deserializerFactory, JavaType javaType) throws JsonMappingException {
        KeyDeserializer keyDeserializerCreateKeyDeserializer = deserializerFactory.createKeyDeserializer(deserializationContext, javaType);
        if (keyDeserializerCreateKeyDeserializer == 0) {
            return _handleUnknownKeyDeserializer(deserializationContext, javaType);
        }
        if (keyDeserializerCreateKeyDeserializer instanceof ResolvableDeserializer) {
            ((ResolvableDeserializer) keyDeserializerCreateKeyDeserializer).resolve(deserializationContext);
        }
        return keyDeserializerCreateKeyDeserializer;
    }

    public JsonDeserializer<Object> findValueDeserializer(DeserializationContext deserializationContext, DeserializerFactory deserializerFactory, JavaType javaType) throws JsonMappingException {
        JsonDeserializer<Object> jsonDeserializer_findCachedDeserializer = _findCachedDeserializer(javaType);
        if (jsonDeserializer_findCachedDeserializer != null) {
            return jsonDeserializer_findCachedDeserializer;
        }
        JsonDeserializer<Object> jsonDeserializer_createAndCacheValueDeserializer = _createAndCacheValueDeserializer(deserializationContext, deserializerFactory, javaType);
        return jsonDeserializer_createAndCacheValueDeserializer == null ? _handleUnknownValueDeserializer(deserializationContext, javaType) : jsonDeserializer_createAndCacheValueDeserializer;
    }

    public void flushCachedDeserializers() {
        this._cachedDeserializers.clear();
    }

    public boolean hasValueDeserializerFor(DeserializationContext deserializationContext, DeserializerFactory deserializerFactory, JavaType javaType) throws JsonMappingException {
        JsonDeserializer<Object> jsonDeserializer_findCachedDeserializer = _findCachedDeserializer(javaType);
        if (jsonDeserializer_findCachedDeserializer == null) {
            jsonDeserializer_findCachedDeserializer = _createAndCacheValueDeserializer(deserializationContext, deserializerFactory, javaType);
        }
        return jsonDeserializer_findCachedDeserializer != null;
    }

    Object writeReplace() {
        this._incompleteDeserializers.clear();
        return this;
    }
}
