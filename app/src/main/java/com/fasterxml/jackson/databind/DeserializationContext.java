package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualKeyDeserializer;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.deser.DeserializerCache;
import com.fasterxml.jackson.databind.deser.DeserializerFactory;
import com.fasterxml.jackson.databind.deser.UnresolvedForwardReference;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.deser.impl.TypeWrappedDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.LinkedNode;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;

public abstract class DeserializationContext extends DatabindContext implements Serializable {
    private static final long serialVersionUID = 1;
    protected transient ArrayBuilders _arrayBuilders;
    protected transient ContextAttributes _attributes;
    protected final DeserializerCache _cache;
    protected final DeserializationConfig _config;
    protected LinkedNode<JavaType> _currentType;
    protected transient DateFormat _dateFormat;
    protected final DeserializerFactory _factory;
    protected final int _featureFlags;
    protected final InjectableValues _injectableValues;
    protected transient ObjectBuffer _objectBuffer;
    protected transient JsonParser _parser;
    protected final Class<?> _view;

    protected DeserializationContext(DeserializationContext deserializationContext) {
        this._cache = new DeserializerCache();
        this._factory = deserializationContext._factory;
        this._config = deserializationContext._config;
        this._featureFlags = deserializationContext._featureFlags;
        this._view = deserializationContext._view;
        this._injectableValues = null;
    }

    protected DeserializationContext(DeserializationContext deserializationContext, DeserializationConfig deserializationConfig, JsonParser jsonParser, InjectableValues injectableValues) {
        this._cache = deserializationContext._cache;
        this._factory = deserializationContext._factory;
        this._config = deserializationConfig;
        this._featureFlags = deserializationConfig.getDeserializationFeatures();
        this._view = deserializationConfig.getActiveView();
        this._parser = jsonParser;
        this._injectableValues = injectableValues;
        this._attributes = deserializationConfig.getAttributes();
    }

    protected DeserializationContext(DeserializationContext deserializationContext, DeserializerFactory deserializerFactory) {
        this._cache = deserializationContext._cache;
        this._factory = deserializerFactory;
        this._config = deserializationContext._config;
        this._featureFlags = deserializationContext._featureFlags;
        this._view = deserializationContext._view;
        this._parser = deserializationContext._parser;
        this._injectableValues = deserializationContext._injectableValues;
        this._attributes = deserializationContext._attributes;
    }

    protected DeserializationContext(DeserializerFactory deserializerFactory) {
        this(deserializerFactory, (DeserializerCache) null);
    }

    protected DeserializationContext(DeserializerFactory deserializerFactory, DeserializerCache deserializerCache) {
        if (deserializerFactory == null) {
            throw new IllegalArgumentException("Cannot pass null DeserializerFactory");
        }
        this._factory = deserializerFactory;
        this._cache = deserializerCache == null ? new DeserializerCache() : deserializerCache;
        this._featureFlags = 0;
        this._config = null;
        this._injectableValues = null;
        this._view = null;
        this._attributes = null;
    }

    protected boolean _isCompatible(Class<?> cls, Object obj) {
        if (obj == null || cls.isInstance(obj)) {
            return true;
        }
        return cls.isPrimitive() && ClassUtil.wrapperType(cls).isInstance(obj);
    }

    @Override
    public final boolean canOverrideAccessModifiers() {
        return this._config.canOverrideAccessModifiers();
    }

    public abstract void checkUnresolvedObjectId() throws UnresolvedForwardReference;

    public Calendar constructCalendar(Date date) {
        Calendar calendar = Calendar.getInstance(getTimeZone());
        calendar.setTime(date);
        return calendar;
    }

    public final JavaType constructType(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        return this._config.constructType(cls);
    }

    public abstract JsonDeserializer<Object> deserializerInstance(Annotated annotated, Object obj) throws JsonMappingException;

    @Deprecated
    public JsonMappingException endOfInputException(Class<?> cls) {
        return MismatchedInputException.from(this._parser, cls, "Unexpected end-of-input when trying to deserialize a " + cls.getName());
    }

    public Class<?> findClass(String str) throws ClassNotFoundException {
        return getTypeFactory().findClass(str);
    }

    public final JsonDeserializer<Object> findContextualValueDeserializer(JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        JsonDeserializer<Object> jsonDeserializerFindValueDeserializer = this._cache.findValueDeserializer(this, this._factory, javaType);
        return jsonDeserializerFindValueDeserializer != null ? handleSecondaryContextualization(jsonDeserializerFindValueDeserializer, beanProperty, javaType) : jsonDeserializerFindValueDeserializer;
    }

    public final Object findInjectableValue(Object obj, BeanProperty beanProperty, Object obj2) throws JsonMappingException {
        if (this._injectableValues == null) {
            reportBadDefinition(ClassUtil.classOf(obj), String.format("No 'injectableValues' configured, cannot inject value with id [%s]", obj));
        }
        return this._injectableValues.findInjectableValue(obj, this, beanProperty, obj2);
    }

    public final KeyDeserializer findKeyDeserializer(JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        KeyDeserializer keyDeserializerFindKeyDeserializer = this._cache.findKeyDeserializer(this, this._factory, javaType);
        return keyDeserializerFindKeyDeserializer instanceof ContextualKeyDeserializer ? ((ContextualKeyDeserializer) keyDeserializerFindKeyDeserializer).createContextual(this, beanProperty) : keyDeserializerFindKeyDeserializer;
    }

    public final JsonDeserializer<Object> findNonContextualValueDeserializer(JavaType javaType) throws JsonMappingException {
        return this._cache.findValueDeserializer(this, this._factory, javaType);
    }

    public abstract ReadableObjectId findObjectId(Object obj, ObjectIdGenerator<?> objectIdGenerator, ObjectIdResolver objectIdResolver);

    public final JsonDeserializer<Object> findRootValueDeserializer(JavaType javaType) throws JsonMappingException {
        JsonDeserializer<Object> jsonDeserializerFindValueDeserializer = this._cache.findValueDeserializer(this, this._factory, javaType);
        if (jsonDeserializerFindValueDeserializer == null) {
            return null;
        }
        JsonDeserializer<?> jsonDeserializerHandleSecondaryContextualization = handleSecondaryContextualization(jsonDeserializerFindValueDeserializer, null, javaType);
        TypeDeserializer typeDeserializerFindTypeDeserializer = this._factory.findTypeDeserializer(this._config, javaType);
        return typeDeserializerFindTypeDeserializer != null ? new TypeWrappedDeserializer(typeDeserializerFindTypeDeserializer.forProperty(null), jsonDeserializerHandleSecondaryContextualization) : jsonDeserializerHandleSecondaryContextualization;
    }

    @Override
    public final Class<?> getActiveView() {
        return this._view;
    }

    @Override
    public final AnnotationIntrospector getAnnotationIntrospector() {
        return this._config.getAnnotationIntrospector();
    }

    public final ArrayBuilders getArrayBuilders() {
        if (this._arrayBuilders == null) {
            this._arrayBuilders = new ArrayBuilders();
        }
        return this._arrayBuilders;
    }

    @Override
    public Object getAttribute(Object obj) {
        return this._attributes.getAttribute(obj);
    }

    public final Base64Variant getBase64Variant() {
        return this._config.getBase64Variant();
    }

    @Override
    public DeserializationConfig getConfig() {
        return this._config;
    }

    public JavaType getContextualType() {
        LinkedNode<JavaType> linkedNode = this._currentType;
        if (linkedNode == null) {
            return null;
        }
        return linkedNode.value();
    }

    protected DateFormat getDateFormat() {
        DateFormat dateFormat = this._dateFormat;
        if (dateFormat != null) {
            return dateFormat;
        }
        DateFormat dateFormat2 = (DateFormat) this._config.getDateFormat().clone();
        this._dateFormat = dateFormat2;
        return dateFormat2;
    }

    @Override
    public final JsonFormat.Value getDefaultPropertyFormat(Class<?> cls) {
        return this._config.getDefaultPropertyFormat(cls);
    }

    public final int getDeserializationFeatures() {
        return this._featureFlags;
    }

    public DeserializerFactory getFactory() {
        return this._factory;
    }

    @Override
    public Locale getLocale() {
        return this._config.getLocale();
    }

    public final JsonNodeFactory getNodeFactory() {
        return this._config.getNodeFactory();
    }

    public final JsonParser getParser() {
        return this._parser;
    }

    @Override
    public TimeZone getTimeZone() {
        return this._config.getTimeZone();
    }

    @Override
    public final TypeFactory getTypeFactory() {
        return this._config.getTypeFactory();
    }

    public Object handleInstantiationProblem(Class<?> cls, Object obj, Throwable th) throws IOException {
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            Object objHandleInstantiationProblem = problemHandlers.value().handleInstantiationProblem(this, cls, obj, th);
            if (objHandleInstantiationProblem != DeserializationProblemHandler.NOT_HANDLED) {
                if (_isCompatible(cls, objHandleInstantiationProblem)) {
                    return objHandleInstantiationProblem;
                }
                reportBadDefinition(constructType(cls), String.format("DeserializationProblemHandler.handleInstantiationProblem() for type %s returned value of type %s", cls, ClassUtil.classNameOf(objHandleInstantiationProblem)));
            }
        }
        ClassUtil.throwIfIOE(th);
        throw instantiationException(cls, th);
    }

    public Object handleMissingInstantiator(Class<?> cls, ValueInstantiator valueInstantiator, JsonParser jsonParser, String str, Object... objArr) throws IOException {
        if (jsonParser == null) {
            jsonParser = getParser();
        }
        String str_format = _format(str, objArr);
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            Object objHandleMissingInstantiator = problemHandlers.value().handleMissingInstantiator(this, cls, valueInstantiator, jsonParser, str_format);
            if (objHandleMissingInstantiator != DeserializationProblemHandler.NOT_HANDLED) {
                if (_isCompatible(cls, objHandleMissingInstantiator)) {
                    return objHandleMissingInstantiator;
                }
                reportBadDefinition(constructType(cls), String.format("DeserializationProblemHandler.handleMissingInstantiator() for type %s returned value of type %s", cls, ClassUtil.classNameOf(objHandleMissingInstantiator)));
            }
        }
        return (valueInstantiator == null || valueInstantiator.canInstantiate()) ? reportInputMismatch(cls, String.format("Cannot construct instance of %s (although at least one Creator exists): %s", ClassUtil.nameOf(cls), str_format), new Object[0]) : reportBadDefinition(constructType(cls), String.format("Cannot construct instance of %s (no Creators, like default construct, exist): %s", ClassUtil.nameOf(cls), str_format));
    }

    public JavaType handleMissingTypeId(JavaType javaType, TypeIdResolver typeIdResolver, String str) throws IOException {
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            JavaType javaTypeHandleMissingTypeId = problemHandlers.value().handleMissingTypeId(this, javaType, typeIdResolver, str);
            if (javaTypeHandleMissingTypeId != null) {
                if (javaTypeHandleMissingTypeId.hasRawClass(Void.class)) {
                    return null;
                }
                if (javaTypeHandleMissingTypeId.isTypeOrSubTypeOf(javaType.getRawClass())) {
                    return javaTypeHandleMissingTypeId;
                }
                throw invalidTypeIdException(javaType, null, "problem handler tried to resolve into non-subtype: " + javaTypeHandleMissingTypeId);
            }
        }
        throw missingTypeIdException(javaType, str);
    }

    public JsonDeserializer<?> handlePrimaryContextualization(JsonDeserializer<?> jsonDeserializer, BeanProperty beanProperty, JavaType javaType) throws JsonMappingException {
        boolean z = jsonDeserializer instanceof ContextualDeserializer;
        JsonDeserializer<?> jsonDeserializer2 = jsonDeserializer;
        if (z) {
            this._currentType = new LinkedNode<>(javaType, this._currentType);
            try {
                JsonDeserializer<?> jsonDeserializerCreateContextual = ((ContextualDeserializer) jsonDeserializer).createContextual(this, beanProperty);
            } finally {
                this._currentType = this._currentType.next();
            }
        }
        return jsonDeserializer2;
    }

    public JsonDeserializer<?> handleSecondaryContextualization(JsonDeserializer<?> jsonDeserializer, BeanProperty beanProperty, JavaType javaType) throws JsonMappingException {
        boolean z = jsonDeserializer instanceof ContextualDeserializer;
        JsonDeserializer<?> jsonDeserializer2 = jsonDeserializer;
        if (z) {
            this._currentType = new LinkedNode<>(javaType, this._currentType);
            try {
                JsonDeserializer<?> jsonDeserializerCreateContextual = ((ContextualDeserializer) jsonDeserializer).createContextual(this, beanProperty);
            } finally {
                this._currentType = this._currentType.next();
            }
        }
        return jsonDeserializer2;
    }

    public Object handleUnexpectedToken(Class<?> cls, JsonParser jsonParser) throws IOException {
        return handleUnexpectedToken(cls, jsonParser.getCurrentToken(), jsonParser, null, new Object[0]);
    }

    public Object handleUnexpectedToken(Class<?> cls, JsonToken jsonToken, JsonParser jsonParser, String str, Object... objArr) throws IOException {
        String str_format = _format(str, objArr);
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            Object objHandleUnexpectedToken = problemHandlers.value().handleUnexpectedToken(this, cls, jsonToken, jsonParser, str_format);
            if (objHandleUnexpectedToken != DeserializationProblemHandler.NOT_HANDLED) {
                if (_isCompatible(cls, objHandleUnexpectedToken)) {
                    return objHandleUnexpectedToken;
                }
                reportBadDefinition(constructType(cls), String.format("DeserializationProblemHandler.handleUnexpectedToken() for type %s returned value of type %s", ClassUtil.nameOf(cls), ClassUtil.classNameOf(objHandleUnexpectedToken)));
            }
        }
        if (str_format == null) {
            str_format = jsonToken == null ? String.format("Unexpected end-of-input when binding data into %s", ClassUtil.nameOf(cls)) : String.format("Cannot deserialize instance of %s out of %s token", ClassUtil.nameOf(cls), jsonToken);
        }
        reportInputMismatch(cls, str_format, new Object[0]);
        return null;
    }

    public boolean handleUnknownProperty(JsonParser jsonParser, JsonDeserializer<?> jsonDeserializer, Object obj, String str) throws IOException {
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            if (problemHandlers.value().handleUnknownProperty(this, jsonParser, jsonDeserializer, obj, str)) {
                return true;
            }
        }
        if (isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)) {
            throw UnrecognizedPropertyException.from(this._parser, obj, str, jsonDeserializer == null ? null : jsonDeserializer.getKnownPropertyNames());
        }
        jsonParser.skipChildren();
        return true;
    }

    public JavaType handleUnknownTypeId(JavaType javaType, String str, TypeIdResolver typeIdResolver, String str2) throws IOException {
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            JavaType javaTypeHandleUnknownTypeId = problemHandlers.value().handleUnknownTypeId(this, javaType, str, typeIdResolver, str2);
            if (javaTypeHandleUnknownTypeId != null) {
                if (javaTypeHandleUnknownTypeId.hasRawClass(Void.class)) {
                    return null;
                }
                if (javaTypeHandleUnknownTypeId.isTypeOrSubTypeOf(javaType.getRawClass())) {
                    return javaTypeHandleUnknownTypeId;
                }
                throw invalidTypeIdException(javaType, str, "problem handler tried to resolve into non-subtype: " + javaTypeHandleUnknownTypeId);
            }
        }
        if (isEnabled(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE)) {
            throw invalidTypeIdException(javaType, str, str2);
        }
        return null;
    }

    public Object handleWeirdKey(Class<?> cls, String str, String str2, Object... objArr) throws IOException {
        String str_format = _format(str2, objArr);
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            Object objHandleWeirdKey = problemHandlers.value().handleWeirdKey(this, cls, str, str_format);
            if (objHandleWeirdKey != DeserializationProblemHandler.NOT_HANDLED) {
                if (objHandleWeirdKey == null || cls.isInstance(objHandleWeirdKey)) {
                    return objHandleWeirdKey;
                }
                throw weirdStringException(str, cls, String.format("DeserializationProblemHandler.handleWeirdStringValue() for type %s returned value of type %s", cls, objHandleWeirdKey.getClass()));
            }
        }
        throw weirdKeyException(cls, str, str_format);
    }

    public Object handleWeirdNativeValue(JavaType javaType, Object obj, JsonParser jsonParser) throws IOException {
        Class<?> rawClass = javaType.getRawClass();
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            Object objHandleWeirdNativeValue = problemHandlers.value().handleWeirdNativeValue(this, javaType, obj, jsonParser);
            if (objHandleWeirdNativeValue != DeserializationProblemHandler.NOT_HANDLED) {
                if (objHandleWeirdNativeValue == null || rawClass.isInstance(objHandleWeirdNativeValue)) {
                    return objHandleWeirdNativeValue;
                }
                throw JsonMappingException.from(jsonParser, _format("DeserializationProblemHandler.handleWeirdNativeValue() for type %s returned value of type %s", javaType, objHandleWeirdNativeValue.getClass()));
            }
        }
        throw weirdNativeValueException(obj, rawClass);
    }

    public Object handleWeirdNumberValue(Class<?> cls, Number number, String str, Object... objArr) throws IOException {
        String str_format = _format(str, objArr);
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            Object objHandleWeirdNumberValue = problemHandlers.value().handleWeirdNumberValue(this, cls, number, str_format);
            if (objHandleWeirdNumberValue != DeserializationProblemHandler.NOT_HANDLED) {
                if (_isCompatible(cls, objHandleWeirdNumberValue)) {
                    return objHandleWeirdNumberValue;
                }
                throw weirdNumberException(number, cls, _format("DeserializationProblemHandler.handleWeirdNumberValue() for type %s returned value of type %s", cls, objHandleWeirdNumberValue.getClass()));
            }
        }
        throw weirdNumberException(number, cls, str_format);
    }

    public Object handleWeirdStringValue(Class<?> cls, String str, String str2, Object... objArr) throws IOException {
        String str_format = _format(str2, objArr);
        for (LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers(); problemHandlers != null; problemHandlers = problemHandlers.next()) {
            Object objHandleWeirdStringValue = problemHandlers.value().handleWeirdStringValue(this, cls, str, str_format);
            if (objHandleWeirdStringValue != DeserializationProblemHandler.NOT_HANDLED) {
                if (_isCompatible(cls, objHandleWeirdStringValue)) {
                    return objHandleWeirdStringValue;
                }
                throw weirdStringException(str, cls, String.format("DeserializationProblemHandler.handleWeirdStringValue() for type %s returned value of type %s", cls, objHandleWeirdStringValue.getClass()));
            }
        }
        throw weirdStringException(str, cls, str_format);
    }

    public final boolean hasDeserializationFeatures(int i) {
        return (this._featureFlags & i) == i;
    }

    public final boolean hasSomeOfFeatures(int i) {
        return (i & this._featureFlags) != 0;
    }

    public boolean hasValueDeserializerFor(JavaType javaType, AtomicReference<Throwable> atomicReference) {
        try {
            return this._cache.hasValueDeserializerFor(this, this._factory, javaType);
        } catch (JsonMappingException e) {
            if (atomicReference == null) {
                return false;
            }
            atomicReference.set(e);
            return false;
        } catch (RuntimeException e2) {
            if (atomicReference == null) {
                throw e2;
            }
            atomicReference.set(e2);
            return false;
        }
    }

    public JsonMappingException instantiationException(Class<?> cls, String str) {
        return InvalidDefinitionException.from(this._parser, String.format("Cannot construct instance of %s: %s", ClassUtil.nameOf(cls), str), constructType(cls));
    }

    public JsonMappingException instantiationException(Class<?> cls, Throwable th) {
        String strExceptionMessage;
        JavaType javaTypeConstructType = constructType(cls);
        if (th == null) {
            strExceptionMessage = "N/A";
        } else {
            strExceptionMessage = ClassUtil.exceptionMessage(th);
            if (strExceptionMessage == null) {
                strExceptionMessage = ClassUtil.nameOf(th.getClass());
            }
        }
        InvalidDefinitionException invalidDefinitionExceptionFrom = InvalidDefinitionException.from(this._parser, String.format("Cannot construct instance of %s, problem: %s", ClassUtil.nameOf(cls), strExceptionMessage), javaTypeConstructType);
        invalidDefinitionExceptionFrom.initCause(th);
        return invalidDefinitionExceptionFrom;
    }

    @Override
    public JsonMappingException invalidTypeIdException(JavaType javaType, String str, String str2) {
        return InvalidTypeIdException.from(this._parser, _colonConcat(String.format("Could not resolve type id '%s' as a subtype of %s", str, javaType), str2), javaType, str);
    }

    public final boolean isEnabled(DeserializationFeature deserializationFeature) {
        return (deserializationFeature.getMask() & this._featureFlags) != 0;
    }

    @Override
    public final boolean isEnabled(MapperFeature mapperFeature) {
        return this._config.isEnabled(mapperFeature);
    }

    public abstract KeyDeserializer keyDeserializerInstance(Annotated annotated, Object obj) throws JsonMappingException;

    public final ObjectBuffer leaseObjectBuffer() {
        ObjectBuffer objectBuffer = this._objectBuffer;
        if (objectBuffer == null) {
            return new ObjectBuffer();
        }
        this._objectBuffer = null;
        return objectBuffer;
    }

    @Deprecated
    public JsonMappingException mappingException(Class<?> cls) {
        return mappingException(cls, this._parser.getCurrentToken());
    }

    @Deprecated
    public JsonMappingException mappingException(Class<?> cls, JsonToken jsonToken) {
        return JsonMappingException.from(this._parser, String.format("Cannot deserialize instance of %s out of %s token", ClassUtil.nameOf(cls), jsonToken));
    }

    @Deprecated
    public JsonMappingException mappingException(String str) {
        return JsonMappingException.from(getParser(), str);
    }

    @Deprecated
    public JsonMappingException mappingException(String str, Object... objArr) {
        return JsonMappingException.from(getParser(), _format(str, objArr));
    }

    public JsonMappingException missingTypeIdException(JavaType javaType, String str) {
        return InvalidTypeIdException.from(this._parser, _colonConcat(String.format("Missing type id when trying to resolve subtype of %s", javaType), str), javaType, null);
    }

    public Date parseDate(String str) throws IllegalArgumentException {
        try {
            return getDateFormat().parse(str);
        } catch (ParseException e) {
            throw new IllegalArgumentException(String.format("Failed to parse Date value '%s': %s", str, ClassUtil.exceptionMessage(e)));
        }
    }

    public <T> T readPropertyValue(JsonParser jsonParser, BeanProperty beanProperty, JavaType javaType) throws IOException {
        JsonDeserializer<Object> jsonDeserializerFindContextualValueDeserializer = findContextualValueDeserializer(javaType, beanProperty);
        return jsonDeserializerFindContextualValueDeserializer == null ? (T) reportBadDefinition(javaType, String.format("Could not find JsonDeserializer for type %s (via property %s)", javaType, ClassUtil.nameOf(beanProperty))) : (T) jsonDeserializerFindContextualValueDeserializer.deserialize(jsonParser, this);
    }

    public <T> T readPropertyValue(JsonParser jsonParser, BeanProperty beanProperty, Class<T> cls) throws IOException {
        return (T) readPropertyValue(jsonParser, beanProperty, getTypeFactory().constructType(cls));
    }

    public <T> T readValue(JsonParser jsonParser, JavaType javaType) throws IOException {
        JsonDeserializer<Object> jsonDeserializerFindRootValueDeserializer = findRootValueDeserializer(javaType);
        if (jsonDeserializerFindRootValueDeserializer == null) {
            reportBadDefinition(javaType, "Could not find JsonDeserializer for type " + javaType);
        }
        return (T) jsonDeserializerFindRootValueDeserializer.deserialize(jsonParser, this);
    }

    public <T> T readValue(JsonParser jsonParser, Class<T> cls) throws IOException {
        return (T) readValue(jsonParser, getTypeFactory().constructType(cls));
    }

    @Override
    public <T> T reportBadDefinition(JavaType javaType, String str) throws JsonMappingException {
        throw InvalidDefinitionException.from(this._parser, str, javaType);
    }

    public <T> T reportBadMerge(JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        if (isEnabled(MapperFeature.IGNORE_MERGE_FOR_UNMERGEABLE)) {
            return null;
        }
        JavaType javaTypeConstructType = constructType(jsonDeserializer.handledType());
        throw InvalidDefinitionException.from(getParser(), String.format("Invalid configuration: values of type %s cannot be merged", javaTypeConstructType), javaTypeConstructType);
    }

    public <T> T reportBadPropertyDefinition(BeanDescription beanDescription, BeanPropertyDefinition beanPropertyDefinition, String str, Object... objArr) throws JsonMappingException {
        throw InvalidDefinitionException.from(this._parser, String.format("Invalid definition for property %s (of type %s): %s", ClassUtil.nameOf(beanPropertyDefinition), ClassUtil.nameOf(beanDescription.getBeanClass()), _format(str, objArr)), beanDescription, beanPropertyDefinition);
    }

    public <T> T reportBadTypeDefinition(BeanDescription beanDescription, String str, Object... objArr) throws JsonMappingException {
        throw InvalidDefinitionException.from(this._parser, String.format("Invalid type definition for type %s: %s", ClassUtil.nameOf(beanDescription.getBeanClass()), _format(str, objArr)), beanDescription, (BeanPropertyDefinition) null);
    }

    public <T> T reportInputMismatch(BeanProperty beanProperty, String str, Object... objArr) throws JsonMappingException {
        throw MismatchedInputException.from(getParser(), beanProperty == null ? null : beanProperty.getType(), _format(str, objArr));
    }

    public <T> T reportInputMismatch(JavaType javaType, String str, Object... objArr) throws JsonMappingException {
        throw MismatchedInputException.from(getParser(), javaType, _format(str, objArr));
    }

    public <T> T reportInputMismatch(JsonDeserializer<?> jsonDeserializer, String str, Object... objArr) throws JsonMappingException {
        throw MismatchedInputException.from(getParser(), jsonDeserializer.handledType(), _format(str, objArr));
    }

    public <T> T reportInputMismatch(Class<?> cls, String str, Object... objArr) throws JsonMappingException {
        throw MismatchedInputException.from(getParser(), cls, _format(str, objArr));
    }

    @Deprecated
    public void reportMappingException(String str, Object... objArr) throws JsonMappingException {
        throw JsonMappingException.from(getParser(), _format(str, objArr));
    }

    @Deprecated
    public void reportMissingContent(String str, Object... objArr) throws JsonMappingException {
        throw MismatchedInputException.from(getParser(), (JavaType) null, "No content to map due to end-of-input");
    }

    public <T> T reportTrailingTokens(Class<?> cls, JsonParser jsonParser, JsonToken jsonToken) throws JsonMappingException {
        throw MismatchedInputException.from(jsonParser, cls, String.format("Trailing token (of type %s) found after value (bound as %s): not allowed as per `DeserializationFeature.FAIL_ON_TRAILING_TOKENS`", jsonToken, ClassUtil.nameOf(cls)));
    }

    @Deprecated
    public void reportUnknownProperty(Object obj, String str, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        if (isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)) {
            throw UnrecognizedPropertyException.from(this._parser, obj, str, jsonDeserializer == null ? null : jsonDeserializer.getKnownPropertyNames());
        }
    }

    public <T> T reportUnresolvedObjectId(ObjectIdReader objectIdReader, Object obj) throws JsonMappingException {
        return (T) reportInputMismatch(objectIdReader.idProperty, String.format("No Object Id found for an instance of %s, to assign to property '%s'", ClassUtil.classNameOf(obj), objectIdReader.propertyName), new Object[0]);
    }

    @Deprecated
    public void reportWrongTokenException(JsonParser jsonParser, JsonToken jsonToken, String str, Object... objArr) throws JsonMappingException {
        throw wrongTokenException(jsonParser, jsonToken, _format(str, objArr));
    }

    public void reportWrongTokenException(JavaType javaType, JsonToken jsonToken, String str, Object... objArr) throws JsonMappingException {
        throw wrongTokenException(getParser(), javaType, jsonToken, _format(str, objArr));
    }

    public void reportWrongTokenException(JsonDeserializer<?> jsonDeserializer, JsonToken jsonToken, String str, Object... objArr) throws JsonMappingException {
        throw wrongTokenException(getParser(), jsonDeserializer.handledType(), jsonToken, _format(str, objArr));
    }

    public void reportWrongTokenException(Class<?> cls, JsonToken jsonToken, String str, Object... objArr) throws JsonMappingException {
        throw wrongTokenException(getParser(), cls, jsonToken, _format(str, objArr));
    }

    public final void returnObjectBuffer(ObjectBuffer objectBuffer) {
        if (this._objectBuffer == null || objectBuffer.initialCapacity() >= this._objectBuffer.initialCapacity()) {
            this._objectBuffer = objectBuffer;
        }
    }

    @Override
    public DeserializationContext setAttribute(Object obj, Object obj2) {
        this._attributes = this._attributes.withPerCallAttribute(obj, obj2);
        return this;
    }

    @Deprecated
    public JsonMappingException unknownTypeException(JavaType javaType, String str, String str2) {
        return MismatchedInputException.from(this._parser, javaType, _colonConcat(String.format("Could not resolve type id '%s' into a subtype of %s", str, javaType), str2));
    }

    public JsonMappingException weirdKeyException(Class<?> cls, String str, String str2) {
        return InvalidFormatException.from(this._parser, String.format("Cannot deserialize Map key of type %s from String %s: %s", ClassUtil.nameOf(cls), _quotedString(str), str2), str, cls);
    }

    public JsonMappingException weirdNativeValueException(Object obj, Class<?> cls) {
        return InvalidFormatException.from(this._parser, String.format("Cannot deserialize value of type %s from native value (`JsonToken.VALUE_EMBEDDED_OBJECT`) of type %s: incompatible types", ClassUtil.nameOf(cls), ClassUtil.classNameOf(obj)), obj, cls);
    }

    public JsonMappingException weirdNumberException(Number number, Class<?> cls, String str) {
        return InvalidFormatException.from(this._parser, String.format("Cannot deserialize value of type %s from number %s: %s", ClassUtil.nameOf(cls), String.valueOf(number), str), number, cls);
    }

    public JsonMappingException weirdStringException(String str, Class<?> cls, String str2) {
        return InvalidFormatException.from(this._parser, String.format("Cannot deserialize value of type %s from String %s: %s", ClassUtil.nameOf(cls), _quotedString(str), str2), str, cls);
    }

    @Deprecated
    public JsonMappingException wrongTokenException(JsonParser jsonParser, JsonToken jsonToken, String str) {
        return wrongTokenException(jsonParser, (JavaType) null, jsonToken, str);
    }

    public JsonMappingException wrongTokenException(JsonParser jsonParser, JavaType javaType, JsonToken jsonToken, String str) {
        return MismatchedInputException.from(jsonParser, javaType, _colonConcat(String.format("Unexpected token (%s), expected %s", jsonParser.getCurrentToken(), jsonToken), str));
    }

    public JsonMappingException wrongTokenException(JsonParser jsonParser, Class<?> cls, JsonToken jsonToken, String str) {
        return MismatchedInputException.from(jsonParser, cls, _colonConcat(String.format("Unexpected token (%s), expected %s", jsonParser.getCurrentToken(), jsonToken), str));
    }
}
