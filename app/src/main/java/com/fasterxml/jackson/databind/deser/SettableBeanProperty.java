package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.impl.FailingDeserializer;
import com.fasterxml.jackson.databind.deser.impl.NullsConstantProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.introspect.ConcreteBeanPropertyBase;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.ViewMatcher;
import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.Annotation;

public abstract class SettableBeanProperty extends ConcreteBeanPropertyBase implements Serializable {
    protected static final JsonDeserializer<Object> MISSING_VALUE_DESERIALIZER = new FailingDeserializer("No _valueDeserializer assigned");
    protected final transient Annotations _contextAnnotations;
    protected String _managedReferenceName;
    protected final NullValueProvider _nullProvider;
    protected ObjectIdInfo _objectIdInfo;
    protected final PropertyName _propName;
    protected int _propertyIndex;
    protected final JavaType _type;
    protected final JsonDeserializer<Object> _valueDeserializer;
    protected final TypeDeserializer _valueTypeDeserializer;
    protected ViewMatcher _viewMatcher;
    protected final PropertyName _wrapperName;

    public static abstract class Delegating extends SettableBeanProperty {
        protected final SettableBeanProperty delegate;

        protected Delegating(SettableBeanProperty settableBeanProperty) {
            super(settableBeanProperty);
            this.delegate = settableBeanProperty;
        }

        protected SettableBeanProperty _with(SettableBeanProperty settableBeanProperty) {
            return settableBeanProperty == this.delegate ? this : withDelegate(settableBeanProperty);
        }

        @Override
        public void assignIndex(int i) {
            this.delegate.assignIndex(i);
        }

        @Override
        public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException {
            this.delegate.deserializeAndSet(jsonParser, deserializationContext, obj);
        }

        @Override
        public Object deserializeSetAndReturn(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException {
            return this.delegate.deserializeSetAndReturn(jsonParser, deserializationContext, obj);
        }

        @Override
        public void fixAccess(DeserializationConfig deserializationConfig) {
            this.delegate.fixAccess(deserializationConfig);
        }

        @Override
        public <A extends Annotation> A getAnnotation(Class<A> cls) {
            return (A) this.delegate.getAnnotation(cls);
        }

        @Override
        public int getCreatorIndex() {
            return this.delegate.getCreatorIndex();
        }

        @Override
        protected Class<?> getDeclaringClass() {
            return this.delegate.getDeclaringClass();
        }

        public SettableBeanProperty getDelegate() {
            return this.delegate;
        }

        @Override
        public Object getInjectableValueId() {
            return this.delegate.getInjectableValueId();
        }

        @Override
        public String getManagedReferenceName() {
            return this.delegate.getManagedReferenceName();
        }

        @Override
        public AnnotatedMember getMember() {
            return this.delegate.getMember();
        }

        @Override
        public ObjectIdInfo getObjectIdInfo() {
            return this.delegate.getObjectIdInfo();
        }

        @Override
        public int getPropertyIndex() {
            return this.delegate.getPropertyIndex();
        }

        @Override
        public JsonDeserializer<Object> getValueDeserializer() {
            return this.delegate.getValueDeserializer();
        }

        @Override
        public TypeDeserializer getValueTypeDeserializer() {
            return this.delegate.getValueTypeDeserializer();
        }

        @Override
        public boolean hasValueDeserializer() {
            return this.delegate.hasValueDeserializer();
        }

        @Override
        public boolean hasValueTypeDeserializer() {
            return this.delegate.hasValueTypeDeserializer();
        }

        @Override
        public boolean hasViews() {
            return this.delegate.hasViews();
        }

        @Override
        public void set(Object obj, Object obj2) throws IOException {
            this.delegate.set(obj, obj2);
        }

        @Override
        public Object setAndReturn(Object obj, Object obj2) throws IOException {
            return this.delegate.setAndReturn(obj, obj2);
        }

        @Override
        public boolean visibleInView(Class<?> cls) {
            return this.delegate.visibleInView(cls);
        }

        protected abstract SettableBeanProperty withDelegate(SettableBeanProperty settableBeanProperty);

        @Override
        public SettableBeanProperty withName(PropertyName propertyName) {
            return _with(this.delegate.withName(propertyName));
        }

        @Override
        public SettableBeanProperty withNullProvider(NullValueProvider nullValueProvider) {
            return _with(this.delegate.withNullProvider(nullValueProvider));
        }

        @Override
        public SettableBeanProperty withValueDeserializer(JsonDeserializer<?> jsonDeserializer) {
            return _with(this.delegate.withValueDeserializer(jsonDeserializer));
        }
    }

    protected SettableBeanProperty(PropertyName propertyName, JavaType javaType, PropertyMetadata propertyMetadata, JsonDeserializer<Object> jsonDeserializer) {
        super(propertyMetadata);
        this._propertyIndex = -1;
        if (propertyName == null) {
            this._propName = PropertyName.NO_NAME;
        } else {
            this._propName = propertyName.internSimpleName();
        }
        this._type = javaType;
        this._wrapperName = null;
        this._contextAnnotations = null;
        this._viewMatcher = null;
        this._valueTypeDeserializer = null;
        this._valueDeserializer = jsonDeserializer;
        this._nullProvider = jsonDeserializer;
    }

    protected SettableBeanProperty(PropertyName propertyName, JavaType javaType, PropertyName propertyName2, TypeDeserializer typeDeserializer, Annotations annotations, PropertyMetadata propertyMetadata) {
        super(propertyMetadata);
        this._propertyIndex = -1;
        if (propertyName == null) {
            this._propName = PropertyName.NO_NAME;
        } else {
            this._propName = propertyName.internSimpleName();
        }
        this._type = javaType;
        this._wrapperName = propertyName2;
        this._contextAnnotations = annotations;
        this._viewMatcher = null;
        this._valueTypeDeserializer = typeDeserializer != null ? typeDeserializer.forProperty(this) : typeDeserializer;
        JsonDeserializer<Object> jsonDeserializer = MISSING_VALUE_DESERIALIZER;
        this._valueDeserializer = jsonDeserializer;
        this._nullProvider = jsonDeserializer;
    }

    protected SettableBeanProperty(SettableBeanProperty settableBeanProperty) {
        super(settableBeanProperty);
        this._propertyIndex = -1;
        this._propName = settableBeanProperty._propName;
        this._type = settableBeanProperty._type;
        this._wrapperName = settableBeanProperty._wrapperName;
        this._contextAnnotations = settableBeanProperty._contextAnnotations;
        this._valueDeserializer = settableBeanProperty._valueDeserializer;
        this._valueTypeDeserializer = settableBeanProperty._valueTypeDeserializer;
        this._managedReferenceName = settableBeanProperty._managedReferenceName;
        this._propertyIndex = settableBeanProperty._propertyIndex;
        this._viewMatcher = settableBeanProperty._viewMatcher;
        this._nullProvider = settableBeanProperty._nullProvider;
    }

    protected SettableBeanProperty(SettableBeanProperty settableBeanProperty, JsonDeserializer<?> jsonDeserializer, NullValueProvider nullValueProvider) {
        super(settableBeanProperty);
        this._propertyIndex = -1;
        this._propName = settableBeanProperty._propName;
        this._type = settableBeanProperty._type;
        this._wrapperName = settableBeanProperty._wrapperName;
        this._contextAnnotations = settableBeanProperty._contextAnnotations;
        this._valueTypeDeserializer = settableBeanProperty._valueTypeDeserializer;
        this._managedReferenceName = settableBeanProperty._managedReferenceName;
        this._propertyIndex = settableBeanProperty._propertyIndex;
        if (jsonDeserializer == null) {
            this._valueDeserializer = MISSING_VALUE_DESERIALIZER;
        } else {
            this._valueDeserializer = jsonDeserializer;
        }
        this._viewMatcher = settableBeanProperty._viewMatcher;
        this._nullProvider = nullValueProvider == MISSING_VALUE_DESERIALIZER ? this._valueDeserializer : nullValueProvider;
    }

    protected SettableBeanProperty(SettableBeanProperty settableBeanProperty, PropertyName propertyName) {
        super(settableBeanProperty);
        this._propertyIndex = -1;
        this._propName = propertyName;
        this._type = settableBeanProperty._type;
        this._wrapperName = settableBeanProperty._wrapperName;
        this._contextAnnotations = settableBeanProperty._contextAnnotations;
        this._valueDeserializer = settableBeanProperty._valueDeserializer;
        this._valueTypeDeserializer = settableBeanProperty._valueTypeDeserializer;
        this._managedReferenceName = settableBeanProperty._managedReferenceName;
        this._propertyIndex = settableBeanProperty._propertyIndex;
        this._viewMatcher = settableBeanProperty._viewMatcher;
        this._nullProvider = settableBeanProperty._nullProvider;
    }

    protected SettableBeanProperty(BeanPropertyDefinition beanPropertyDefinition, JavaType javaType, TypeDeserializer typeDeserializer, Annotations annotations) {
        this(beanPropertyDefinition.getFullName(), javaType, beanPropertyDefinition.getWrapperName(), typeDeserializer, annotations, beanPropertyDefinition.getMetadata());
    }

    protected IOException _throwAsIOE(JsonParser jsonParser, Exception exc) throws IOException {
        ClassUtil.throwIfIOE(exc);
        ClassUtil.throwIfRTE(exc);
        Throwable rootCause = ClassUtil.getRootCause(exc);
        throw JsonMappingException.from(jsonParser, ClassUtil.exceptionMessage(rootCause), rootCause);
    }

    @Deprecated
    protected IOException _throwAsIOE(Exception exc) throws IOException {
        return _throwAsIOE((JsonParser) null, exc);
    }

    protected void _throwAsIOE(JsonParser jsonParser, Exception exc, Object obj) throws IOException {
        if (!(exc instanceof IllegalArgumentException)) {
            _throwAsIOE(jsonParser, exc);
            return;
        }
        String strClassNameOf = ClassUtil.classNameOf(obj);
        StringBuilder sb = new StringBuilder("Problem deserializing property '");
        sb.append(getName());
        sb.append("' (expected type: ");
        sb.append(getType());
        sb.append("; actual type: ");
        sb.append(strClassNameOf);
        sb.append(")");
        String strExceptionMessage = ClassUtil.exceptionMessage(exc);
        if (strExceptionMessage != null) {
            sb.append(", problem: ");
            sb.append(strExceptionMessage);
        } else {
            sb.append(" (no error message provided)");
        }
        throw JsonMappingException.from(jsonParser, sb.toString(), exc);
    }

    protected void _throwAsIOE(Exception exc, Object obj) throws IOException {
        _throwAsIOE((JsonParser) null, exc, obj);
    }

    public void assignIndex(int i) {
        if (this._propertyIndex == -1) {
            this._propertyIndex = i;
            return;
        }
        throw new IllegalStateException("Property '" + getName() + "' already had index (" + this._propertyIndex + "), trying to assign " + i);
    }

    @Override
    public void depositSchemaProperty(JsonObjectFormatVisitor jsonObjectFormatVisitor, SerializerProvider serializerProvider) throws JsonMappingException {
        if (isRequired()) {
            jsonObjectFormatVisitor.property(this);
        } else {
            jsonObjectFormatVisitor.optionalProperty(this);
        }
    }

    public final Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.hasToken(JsonToken.VALUE_NULL)) {
            return this._nullProvider.getNullValue(deserializationContext);
        }
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        if (typeDeserializer != null) {
            return this._valueDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
        }
        Object objDeserialize = this._valueDeserializer.deserialize(jsonParser, deserializationContext);
        return objDeserialize == null ? this._nullProvider.getNullValue(deserializationContext) : objDeserialize;
    }

    public abstract void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException;

    public abstract Object deserializeSetAndReturn(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException;

    public final Object deserializeWith(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException {
        if (jsonParser.hasToken(JsonToken.VALUE_NULL)) {
            return NullsConstantProvider.isSkipper(this._nullProvider) ? obj : this._nullProvider.getNullValue(deserializationContext);
        }
        if (this._valueTypeDeserializer != null) {
            deserializationContext.reportBadDefinition(getType(), String.format("Cannot merge polymorphic property '%s'", getName()));
        }
        Object objDeserialize = this._valueDeserializer.deserialize(jsonParser, deserializationContext, obj);
        return objDeserialize == null ? NullsConstantProvider.isSkipper(this._nullProvider) ? obj : this._nullProvider.getNullValue(deserializationContext) : objDeserialize;
    }

    public void fixAccess(DeserializationConfig deserializationConfig) {
    }

    @Override
    public abstract <A extends Annotation> A getAnnotation(Class<A> cls);

    @Override
    public <A extends Annotation> A getContextAnnotation(Class<A> cls) {
        return (A) this._contextAnnotations.get(cls);
    }

    public int getCreatorIndex() {
        throw new IllegalStateException(String.format("Internal error: no creator index for property '%s' (of type %s)", getName(), getClass().getName()));
    }

    protected Class<?> getDeclaringClass() {
        return getMember().getDeclaringClass();
    }

    @Override
    public PropertyName getFullName() {
        return this._propName;
    }

    public Object getInjectableValueId() {
        return null;
    }

    public String getManagedReferenceName() {
        return this._managedReferenceName;
    }

    @Override
    public abstract AnnotatedMember getMember();

    @Override
    public final String getName() {
        return this._propName.getSimpleName();
    }

    public NullValueProvider getNullValueProvider() {
        return this._nullProvider;
    }

    public ObjectIdInfo getObjectIdInfo() {
        return this._objectIdInfo;
    }

    public int getPropertyIndex() {
        return this._propertyIndex;
    }

    @Override
    public JavaType getType() {
        return this._type;
    }

    public JsonDeserializer<Object> getValueDeserializer() {
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        if (jsonDeserializer == MISSING_VALUE_DESERIALIZER) {
            return null;
        }
        return jsonDeserializer;
    }

    public TypeDeserializer getValueTypeDeserializer() {
        return this._valueTypeDeserializer;
    }

    @Override
    public PropertyName getWrapperName() {
        return this._wrapperName;
    }

    public boolean hasValueDeserializer() {
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        return (jsonDeserializer == null || jsonDeserializer == MISSING_VALUE_DESERIALIZER) ? false : true;
    }

    public boolean hasValueTypeDeserializer() {
        return this._valueTypeDeserializer != null;
    }

    public boolean hasViews() {
        return this._viewMatcher != null;
    }

    public boolean isIgnorable() {
        return false;
    }

    public void markAsIgnorable() {
    }

    public abstract void set(Object obj, Object obj2) throws IOException;

    public abstract Object setAndReturn(Object obj, Object obj2) throws IOException;

    public void setManagedReferenceName(String str) {
        this._managedReferenceName = str;
    }

    public void setObjectIdInfo(ObjectIdInfo objectIdInfo) {
        this._objectIdInfo = objectIdInfo;
    }

    public void setViews(Class<?>[] clsArr) {
        if (clsArr == null) {
            this._viewMatcher = null;
        } else {
            this._viewMatcher = ViewMatcher.construct(clsArr);
        }
    }

    public String toString() {
        return "[property '" + getName() + "']";
    }

    public boolean visibleInView(Class<?> cls) {
        ViewMatcher viewMatcher = this._viewMatcher;
        return viewMatcher == null || viewMatcher.isVisibleForView(cls);
    }

    public abstract SettableBeanProperty withName(PropertyName propertyName);

    public abstract SettableBeanProperty withNullProvider(NullValueProvider nullValueProvider);

    public SettableBeanProperty withSimpleName(String str) {
        PropertyName propertyName = this._propName;
        PropertyName propertyName2 = propertyName == null ? new PropertyName(str) : propertyName.withSimpleName(str);
        return propertyName2 == this._propName ? this : withName(propertyName2);
    }

    public abstract SettableBeanProperty withValueDeserializer(JsonDeserializer<?> jsonDeserializer);
}
