package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import java.io.IOException;

public class MergingSettableBeanProperty extends SettableBeanProperty.Delegating {
    private static final long serialVersionUID = 1;
    protected final AnnotatedMember _accessor;

    protected MergingSettableBeanProperty(SettableBeanProperty settableBeanProperty, AnnotatedMember annotatedMember) {
        super(settableBeanProperty);
        this._accessor = annotatedMember;
    }

    protected MergingSettableBeanProperty(MergingSettableBeanProperty mergingSettableBeanProperty, SettableBeanProperty settableBeanProperty) {
        super(settableBeanProperty);
        this._accessor = mergingSettableBeanProperty._accessor;
    }

    public static MergingSettableBeanProperty construct(SettableBeanProperty settableBeanProperty, AnnotatedMember annotatedMember) {
        return new MergingSettableBeanProperty(settableBeanProperty, annotatedMember);
    }

    @Override
    public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws UnsupportedOperationException, IOException, IllegalArgumentException {
        Object value = this._accessor.getValue(obj);
        Object objDeserialize = value == null ? this.delegate.deserialize(jsonParser, deserializationContext) : this.delegate.deserializeWith(jsonParser, deserializationContext, value);
        if (objDeserialize != value) {
            this.delegate.set(obj, objDeserialize);
        }
    }

    @Override
    public Object deserializeSetAndReturn(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws UnsupportedOperationException, IOException, IllegalArgumentException {
        Object value = this._accessor.getValue(obj);
        Object objDeserialize = value == null ? this.delegate.deserialize(jsonParser, deserializationContext) : this.delegate.deserializeWith(jsonParser, deserializationContext, value);
        return (objDeserialize == value || objDeserialize == null) ? obj : this.delegate.setAndReturn(obj, objDeserialize);
    }

    @Override
    public void set(Object obj, Object obj2) throws IOException {
        if (obj2 != null) {
            this.delegate.set(obj, obj2);
        }
    }

    @Override
    public Object setAndReturn(Object obj, Object obj2) throws IOException {
        return obj2 != null ? this.delegate.setAndReturn(obj, obj2) : obj;
    }

    @Override
    protected SettableBeanProperty withDelegate(SettableBeanProperty settableBeanProperty) {
        return new MergingSettableBeanProperty(settableBeanProperty, this._accessor);
    }
}
