package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.databind.AbstractTypeResolver;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.deser.impl.ErrorThrowingDeserializer;
import com.fasterxml.jackson.databind.deser.impl.FieldProperty;
import com.fasterxml.jackson.databind.deser.impl.MethodProperty;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedObjectIdGenerator;
import com.fasterxml.jackson.databind.deser.impl.SetterlessProperty;
import com.fasterxml.jackson.databind.deser.std.ThrowableDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.impl.SubTypeValidator;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.SimpleBeanPropertyDefinition;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BeanDeserializerFactory extends BasicDeserializerFactory implements Serializable {
    private static final Class<?>[] INIT_CAUSE_PARAMS = {Throwable.class};
    public static final BeanDeserializerFactory instance = new BeanDeserializerFactory(new DeserializerFactoryConfig());
    private static final long serialVersionUID = 1;

    public BeanDeserializerFactory(DeserializerFactoryConfig deserializerFactoryConfig) {
        super(deserializerFactoryConfig);
    }

    private boolean _isSetterlessType(Class<?> cls) {
        return Collection.class.isAssignableFrom(cls) || Map.class.isAssignableFrom(cls);
    }

    protected void _validateSubType(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        SubTypeValidator.instance().validateSubType(deserializationContext, javaType, beanDescription);
    }

    protected void addBackReferenceProperties(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder) throws JsonMappingException {
        List<BeanPropertyDefinition> listFindBackReferences = beanDescription.findBackReferences();
        if (listFindBackReferences != null) {
            for (BeanPropertyDefinition beanPropertyDefinition : listFindBackReferences) {
                beanDeserializerBuilder.addBackReferenceProperty(beanPropertyDefinition.findReferenceName(), constructSettableProperty(deserializationContext, beanDescription, beanPropertyDefinition, beanPropertyDefinition.getPrimaryType()));
            }
        }
    }

    protected void addBeanProps(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder) throws JsonMappingException {
        Set<String> setEmptySet;
        SettableBeanProperty settableBeanPropertyConstructSetterlessProperty;
        CreatorProperty creatorProperty;
        CreatorProperty[] fromObjectArguments = beanDescription.getType().isAbstract() ^ true ? beanDeserializerBuilder.getValueInstantiator().getFromObjectArguments(deserializationContext.getConfig()) : null;
        boolean z = fromObjectArguments != null;
        JsonIgnoreProperties.Value defaultPropertyIgnorals = deserializationContext.getConfig().getDefaultPropertyIgnorals(beanDescription.getBeanClass(), beanDescription.getClassInfo());
        if (defaultPropertyIgnorals != null) {
            beanDeserializerBuilder.setIgnoreUnknownProperties(defaultPropertyIgnorals.getIgnoreUnknown());
            setEmptySet = defaultPropertyIgnorals.findIgnoredForDeserialization();
            Iterator<String> it = setEmptySet.iterator();
            while (it.hasNext()) {
                beanDeserializerBuilder.addIgnorable(it.next());
            }
        } else {
            setEmptySet = Collections.emptySet();
        }
        Set<String> set = setEmptySet;
        AnnotatedMember annotatedMemberFindAnySetterAccessor = beanDescription.findAnySetterAccessor();
        if (annotatedMemberFindAnySetterAccessor != null) {
            beanDeserializerBuilder.setAnySetter(constructAnySetter(deserializationContext, beanDescription, annotatedMemberFindAnySetterAccessor));
        } else {
            Set<String> ignoredPropertyNames = beanDescription.getIgnoredPropertyNames();
            if (ignoredPropertyNames != null) {
                Iterator<String> it2 = ignoredPropertyNames.iterator();
                while (it2.hasNext()) {
                    beanDeserializerBuilder.addIgnorable(it2.next());
                }
            }
        }
        boolean z2 = deserializationContext.isEnabled(MapperFeature.USE_GETTERS_AS_SETTERS) && deserializationContext.isEnabled(MapperFeature.AUTO_DETECT_GETTERS);
        List<BeanPropertyDefinition> listFilterBeanProps = filterBeanProps(deserializationContext, beanDescription, beanDeserializerBuilder, beanDescription.findProperties(), set);
        if (this._factoryConfig.hasDeserializerModifiers()) {
            Iterator<BeanDeserializerModifier> it3 = this._factoryConfig.deserializerModifiers().iterator();
            while (it3.hasNext()) {
                listFilterBeanProps = it3.next().updateProperties(deserializationContext.getConfig(), beanDescription, listFilterBeanProps);
            }
        }
        for (BeanPropertyDefinition beanPropertyDefinition : listFilterBeanProps) {
            if (beanPropertyDefinition.hasSetter()) {
                settableBeanPropertyConstructSetterlessProperty = constructSettableProperty(deserializationContext, beanDescription, beanPropertyDefinition, beanPropertyDefinition.getSetter().getParameterType(0));
            } else if (beanPropertyDefinition.hasField()) {
                settableBeanPropertyConstructSetterlessProperty = constructSettableProperty(deserializationContext, beanDescription, beanPropertyDefinition, beanPropertyDefinition.getField().getType());
            } else {
                AnnotatedMethod getter = beanPropertyDefinition.getGetter();
                if (getter == null) {
                    settableBeanPropertyConstructSetterlessProperty = null;
                } else if (z2 && _isSetterlessType(getter.getRawType())) {
                    if (!beanDeserializerBuilder.hasIgnorable(beanPropertyDefinition.getName())) {
                        settableBeanPropertyConstructSetterlessProperty = constructSetterlessProperty(deserializationContext, beanDescription, beanPropertyDefinition);
                    }
                } else if (!beanPropertyDefinition.hasConstructorParameter() && beanPropertyDefinition.getMetadata().getMergeInfo() != null) {
                    settableBeanPropertyConstructSetterlessProperty = constructSetterlessProperty(deserializationContext, beanDescription, beanPropertyDefinition);
                }
            }
            if (z && beanPropertyDefinition.hasConstructorParameter()) {
                String name = beanPropertyDefinition.getName();
                if (fromObjectArguments != null) {
                    for (CreatorProperty creatorProperty2 : fromObjectArguments) {
                        if (name.equals(creatorProperty2.getName()) && (creatorProperty2 instanceof CreatorProperty)) {
                            creatorProperty = creatorProperty2;
                            break;
                        }
                    }
                    creatorProperty = null;
                    if (creatorProperty != null) {
                        ArrayList arrayList = new ArrayList();
                        for (CreatorProperty creatorProperty3 : fromObjectArguments) {
                            arrayList.add(creatorProperty3.getName());
                        }
                        deserializationContext.reportBadPropertyDefinition(beanDescription, beanPropertyDefinition, "Could not find creator property with name '%s' (known Creator properties: %s)", name, arrayList);
                    } else {
                        if (settableBeanPropertyConstructSetterlessProperty != null) {
                            creatorProperty.setFallbackSetter(settableBeanPropertyConstructSetterlessProperty);
                        }
                        Class<?>[] clsArrFindViews = beanPropertyDefinition.findViews();
                        if (clsArrFindViews == null) {
                            clsArrFindViews = beanDescription.findDefaultViews();
                        }
                        creatorProperty.setViews(clsArrFindViews);
                        beanDeserializerBuilder.addCreatorProperty(creatorProperty);
                    }
                } else {
                    creatorProperty = null;
                    if (creatorProperty != null) {
                    }
                }
            } else if (settableBeanPropertyConstructSetterlessProperty != null) {
                Class<?>[] clsArrFindViews2 = beanPropertyDefinition.findViews();
                if (clsArrFindViews2 == null) {
                    clsArrFindViews2 = beanDescription.findDefaultViews();
                }
                settableBeanPropertyConstructSetterlessProperty.setViews(clsArrFindViews2);
                beanDeserializerBuilder.addProperty(settableBeanPropertyConstructSetterlessProperty);
            }
        }
    }

    protected void addInjectables(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder) throws SecurityException, JsonMappingException {
        Map<Object, AnnotatedMember> mapFindInjectables = beanDescription.findInjectables();
        if (mapFindInjectables != null) {
            for (Map.Entry<Object, AnnotatedMember> entry : mapFindInjectables.entrySet()) {
                AnnotatedMember value = entry.getValue();
                beanDeserializerBuilder.addInjectable(PropertyName.construct(value.getName()), value.getType(), beanDescription.getClassAnnotations(), value, entry.getKey());
            }
        }
    }

    protected void addObjectIdReader(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder) throws JsonMappingException {
        SettableBeanProperty settableBeanPropertyFindProperty;
        ObjectIdGenerator<?> objectIdGeneratorObjectIdGeneratorInstance;
        JavaType type;
        ObjectIdInfo objectIdInfo = beanDescription.getObjectIdInfo();
        if (objectIdInfo == null) {
            return;
        }
        Class<? extends ObjectIdGenerator<?>> generatorType = objectIdInfo.getGeneratorType();
        ObjectIdResolver objectIdResolverObjectIdResolverInstance = deserializationContext.objectIdResolverInstance(beanDescription.getClassInfo(), objectIdInfo);
        if (generatorType == ObjectIdGenerators.PropertyGenerator.class) {
            PropertyName propertyName = objectIdInfo.getPropertyName();
            settableBeanPropertyFindProperty = beanDeserializerBuilder.findProperty(propertyName);
            if (settableBeanPropertyFindProperty == null) {
                throw new IllegalArgumentException("Invalid Object Id definition for " + beanDescription.getBeanClass().getName() + ": cannot find property with name '" + propertyName + "'");
            }
            type = settableBeanPropertyFindProperty.getType();
            objectIdGeneratorObjectIdGeneratorInstance = new PropertyBasedObjectIdGenerator(objectIdInfo.getScope());
        } else {
            JavaType javaType = deserializationContext.getTypeFactory().findTypeParameters(deserializationContext.constructType((Class<?>) generatorType), ObjectIdGenerator.class)[0];
            settableBeanPropertyFindProperty = null;
            objectIdGeneratorObjectIdGeneratorInstance = deserializationContext.objectIdGeneratorInstance(beanDescription.getClassInfo(), objectIdInfo);
            type = javaType;
        }
        beanDeserializerBuilder.setObjectIdReader(ObjectIdReader.construct(type, objectIdInfo.getPropertyName(), objectIdGeneratorObjectIdGeneratorInstance, deserializationContext.findRootValueDeserializer(type), settableBeanPropertyFindProperty, objectIdResolverObjectIdResolverInstance));
    }

    @Deprecated
    protected void addReferenceProperties(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder) throws JsonMappingException {
        addBackReferenceProperties(deserializationContext, beanDescription, beanDeserializerBuilder);
    }

    public JsonDeserializer<Object> buildBeanDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws SecurityException, JsonMappingException {
        try {
            ValueInstantiator valueInstantiatorFindValueInstantiator = findValueInstantiator(deserializationContext, beanDescription);
            BeanDeserializerBuilder beanDeserializerBuilderConstructBeanDeserializerBuilder = constructBeanDeserializerBuilder(deserializationContext, beanDescription);
            beanDeserializerBuilderConstructBeanDeserializerBuilder.setValueInstantiator(valueInstantiatorFindValueInstantiator);
            addBeanProps(deserializationContext, beanDescription, beanDeserializerBuilderConstructBeanDeserializerBuilder);
            addObjectIdReader(deserializationContext, beanDescription, beanDeserializerBuilderConstructBeanDeserializerBuilder);
            addBackReferenceProperties(deserializationContext, beanDescription, beanDeserializerBuilderConstructBeanDeserializerBuilder);
            addInjectables(deserializationContext, beanDescription, beanDeserializerBuilderConstructBeanDeserializerBuilder);
            DeserializationConfig config = deserializationContext.getConfig();
            if (this._factoryConfig.hasDeserializerModifiers()) {
                Iterator<BeanDeserializerModifier> it = this._factoryConfig.deserializerModifiers().iterator();
                while (it.hasNext()) {
                    beanDeserializerBuilderConstructBeanDeserializerBuilder = it.next().updateBuilder(config, beanDescription, beanDeserializerBuilderConstructBeanDeserializerBuilder);
                }
            }
            JsonDeserializer<?> jsonDeserializerBuild = (!javaType.isAbstract() || valueInstantiatorFindValueInstantiator.canInstantiate()) ? beanDeserializerBuilderConstructBeanDeserializerBuilder.build() : beanDeserializerBuilderConstructBeanDeserializerBuilder.buildAbstract();
            if (this._factoryConfig.hasDeserializerModifiers()) {
                Iterator<BeanDeserializerModifier> it2 = this._factoryConfig.deserializerModifiers().iterator();
                while (it2.hasNext()) {
                    jsonDeserializerBuild = it2.next().modifyDeserializer(config, beanDescription, jsonDeserializerBuild);
                }
            }
            return jsonDeserializerBuild;
        } catch (IllegalArgumentException e) {
            throw InvalidDefinitionException.from(deserializationContext.getParser(), ClassUtil.exceptionMessage(e), beanDescription, (BeanPropertyDefinition) null);
        } catch (NoClassDefFoundError e2) {
            return new ErrorThrowingDeserializer(e2);
        }
    }

    protected JsonDeserializer<Object> buildBuilderBasedDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws SecurityException, JsonMappingException {
        try {
            ValueInstantiator valueInstantiatorFindValueInstantiator = findValueInstantiator(deserializationContext, beanDescription);
            DeserializationConfig config = deserializationContext.getConfig();
            BeanDeserializerBuilder beanDeserializerBuilderConstructBeanDeserializerBuilder = constructBeanDeserializerBuilder(deserializationContext, beanDescription);
            beanDeserializerBuilderConstructBeanDeserializerBuilder.setValueInstantiator(valueInstantiatorFindValueInstantiator);
            addBeanProps(deserializationContext, beanDescription, beanDeserializerBuilderConstructBeanDeserializerBuilder);
            addObjectIdReader(deserializationContext, beanDescription, beanDeserializerBuilderConstructBeanDeserializerBuilder);
            addBackReferenceProperties(deserializationContext, beanDescription, beanDeserializerBuilderConstructBeanDeserializerBuilder);
            addInjectables(deserializationContext, beanDescription, beanDeserializerBuilderConstructBeanDeserializerBuilder);
            JsonPOJOBuilder.Value valueFindPOJOBuilderConfig = beanDescription.findPOJOBuilderConfig();
            String str = valueFindPOJOBuilderConfig == null ? "build" : valueFindPOJOBuilderConfig.buildMethodName;
            AnnotatedMethod annotatedMethodFindMethod = beanDescription.findMethod(str, null);
            if (annotatedMethodFindMethod != null && config.canOverrideAccessModifiers()) {
                ClassUtil.checkAndFixAccess(annotatedMethodFindMethod.getMember(), config.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
            }
            beanDeserializerBuilderConstructBeanDeserializerBuilder.setPOJOBuilder(annotatedMethodFindMethod, valueFindPOJOBuilderConfig);
            if (this._factoryConfig.hasDeserializerModifiers()) {
                Iterator<BeanDeserializerModifier> it = this._factoryConfig.deserializerModifiers().iterator();
                while (it.hasNext()) {
                    beanDeserializerBuilderConstructBeanDeserializerBuilder = it.next().updateBuilder(config, beanDescription, beanDeserializerBuilderConstructBeanDeserializerBuilder);
                }
            }
            JsonDeserializer<?> jsonDeserializerBuildBuilderBased = beanDeserializerBuilderConstructBeanDeserializerBuilder.buildBuilderBased(javaType, str);
            if (this._factoryConfig.hasDeserializerModifiers()) {
                Iterator<BeanDeserializerModifier> it2 = this._factoryConfig.deserializerModifiers().iterator();
                while (it2.hasNext()) {
                    jsonDeserializerBuildBuilderBased = it2.next().modifyDeserializer(config, beanDescription, jsonDeserializerBuildBuilderBased);
                }
            }
            return jsonDeserializerBuildBuilderBased;
        } catch (IllegalArgumentException e) {
            throw InvalidDefinitionException.from(deserializationContext.getParser(), ClassUtil.exceptionMessage(e), beanDescription, (BeanPropertyDefinition) null);
        } catch (NoClassDefFoundError e2) {
            return new ErrorThrowingDeserializer(e2);
        }
    }

    public JsonDeserializer<Object> buildThrowableDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws SecurityException, JsonMappingException {
        SettableBeanProperty settableBeanPropertyConstructSettableProperty;
        DeserializationConfig config = deserializationContext.getConfig();
        BeanDeserializerBuilder beanDeserializerBuilderConstructBeanDeserializerBuilder = constructBeanDeserializerBuilder(deserializationContext, beanDescription);
        beanDeserializerBuilderConstructBeanDeserializerBuilder.setValueInstantiator(findValueInstantiator(deserializationContext, beanDescription));
        addBeanProps(deserializationContext, beanDescription, beanDeserializerBuilderConstructBeanDeserializerBuilder);
        AnnotatedMethod annotatedMethodFindMethod = beanDescription.findMethod("initCause", INIT_CAUSE_PARAMS);
        if (annotatedMethodFindMethod != null && (settableBeanPropertyConstructSettableProperty = constructSettableProperty(deserializationContext, beanDescription, SimpleBeanPropertyDefinition.construct(deserializationContext.getConfig(), annotatedMethodFindMethod, new PropertyName("cause")), annotatedMethodFindMethod.getParameterType(0))) != null) {
            beanDeserializerBuilderConstructBeanDeserializerBuilder.addOrReplaceProperty(settableBeanPropertyConstructSettableProperty, true);
        }
        beanDeserializerBuilderConstructBeanDeserializerBuilder.addIgnorable("localizedMessage");
        beanDeserializerBuilderConstructBeanDeserializerBuilder.addIgnorable("suppressed");
        if (this._factoryConfig.hasDeserializerModifiers()) {
            Iterator<BeanDeserializerModifier> it = this._factoryConfig.deserializerModifiers().iterator();
            while (it.hasNext()) {
                beanDeserializerBuilderConstructBeanDeserializerBuilder = it.next().updateBuilder(config, beanDescription, beanDeserializerBuilderConstructBeanDeserializerBuilder);
            }
        }
        JsonDeserializer<?> jsonDeserializerBuild = beanDeserializerBuilderConstructBeanDeserializerBuilder.build();
        if (jsonDeserializerBuild instanceof BeanDeserializer) {
            jsonDeserializerBuild = new ThrowableDeserializer((BeanDeserializer) jsonDeserializerBuild);
        }
        if (this._factoryConfig.hasDeserializerModifiers()) {
            Iterator<BeanDeserializerModifier> it2 = this._factoryConfig.deserializerModifiers().iterator();
            while (it2.hasNext()) {
                jsonDeserializerBuild = it2.next().modifyDeserializer(config, beanDescription, jsonDeserializerBuild);
            }
        }
        return jsonDeserializerBuild;
    }

    protected SettableAnyProperty constructAnySetter(DeserializationContext deserializationContext, BeanDescription beanDescription, AnnotatedMember annotatedMember) throws JsonMappingException {
        JavaType keyType;
        BeanProperty.Std std;
        JavaType javaTypeResolveMemberAndTypeAnnotations;
        KeyDeserializer keyDeserializerCreateContextual;
        if (annotatedMember instanceof AnnotatedMethod) {
            AnnotatedMethod annotatedMethod = (AnnotatedMethod) annotatedMember;
            keyType = annotatedMethod.getParameterType(0);
            javaTypeResolveMemberAndTypeAnnotations = resolveMemberAndTypeAnnotations(deserializationContext, annotatedMember, annotatedMethod.getParameterType(1));
            std = new BeanProperty.Std(PropertyName.construct(annotatedMember.getName()), javaTypeResolveMemberAndTypeAnnotations, null, annotatedMember, PropertyMetadata.STD_OPTIONAL);
        } else {
            if (!(annotatedMember instanceof AnnotatedField)) {
                return (SettableAnyProperty) deserializationContext.reportBadDefinition(beanDescription.getType(), String.format("Unrecognized mutator type for any setter: %s", annotatedMember.getClass()));
            }
            JavaType javaTypeResolveMemberAndTypeAnnotations2 = resolveMemberAndTypeAnnotations(deserializationContext, annotatedMember, ((AnnotatedField) annotatedMember).getType());
            keyType = javaTypeResolveMemberAndTypeAnnotations2.getKeyType();
            JavaType contentType = javaTypeResolveMemberAndTypeAnnotations2.getContentType();
            std = new BeanProperty.Std(PropertyName.construct(annotatedMember.getName()), javaTypeResolveMemberAndTypeAnnotations2, null, annotatedMember, PropertyMetadata.STD_OPTIONAL);
            javaTypeResolveMemberAndTypeAnnotations = contentType;
        }
        KeyDeserializer keyDeserializerFindKeyDeserializerFromAnnotation = findKeyDeserializerFromAnnotation(deserializationContext, annotatedMember);
        ?? r2 = keyDeserializerFindKeyDeserializerFromAnnotation;
        if (keyDeserializerFindKeyDeserializerFromAnnotation == null) {
            r2 = (KeyDeserializer) keyType.getValueHandler();
        }
        if (r2 == 0) {
            keyDeserializerCreateContextual = deserializationContext.findKeyDeserializer(keyType, std);
        } else {
            boolean z = r2 instanceof ContextualKeyDeserializer;
            keyDeserializerCreateContextual = r2;
            if (z) {
                keyDeserializerCreateContextual = ((ContextualKeyDeserializer) r2).createContextual(deserializationContext, std);
            }
        }
        KeyDeserializer keyDeserializer = keyDeserializerCreateContextual;
        JsonDeserializer<?> jsonDeserializerFindContentDeserializerFromAnnotation = findContentDeserializerFromAnnotation(deserializationContext, annotatedMember);
        if (jsonDeserializerFindContentDeserializerFromAnnotation == null) {
            jsonDeserializerFindContentDeserializerFromAnnotation = (JsonDeserializer) javaTypeResolveMemberAndTypeAnnotations.getValueHandler();
        }
        return new SettableAnyProperty(std, annotatedMember, javaTypeResolveMemberAndTypeAnnotations, keyDeserializer, jsonDeserializerFindContentDeserializerFromAnnotation != null ? deserializationContext.handlePrimaryContextualization(jsonDeserializerFindContentDeserializerFromAnnotation, std, javaTypeResolveMemberAndTypeAnnotations) : jsonDeserializerFindContentDeserializerFromAnnotation, (TypeDeserializer) javaTypeResolveMemberAndTypeAnnotations.getTypeHandler());
    }

    protected BeanDeserializerBuilder constructBeanDeserializerBuilder(DeserializationContext deserializationContext, BeanDescription beanDescription) {
        return new BeanDeserializerBuilder(beanDescription, deserializationContext);
    }

    protected SettableBeanProperty constructSettableProperty(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanPropertyDefinition beanPropertyDefinition, JavaType javaType) throws JsonMappingException {
        AnnotatedMember nonConstructorMutator = beanPropertyDefinition.getNonConstructorMutator();
        if (nonConstructorMutator == null) {
            deserializationContext.reportBadPropertyDefinition(beanDescription, beanPropertyDefinition, "No non-constructor mutator available", new Object[0]);
        }
        JavaType javaTypeResolveMemberAndTypeAnnotations = resolveMemberAndTypeAnnotations(deserializationContext, nonConstructorMutator, javaType);
        TypeDeserializer typeDeserializer = (TypeDeserializer) javaTypeResolveMemberAndTypeAnnotations.getTypeHandler();
        SettableBeanProperty methodProperty = nonConstructorMutator instanceof AnnotatedMethod ? new MethodProperty(beanPropertyDefinition, javaTypeResolveMemberAndTypeAnnotations, typeDeserializer, beanDescription.getClassAnnotations(), (AnnotatedMethod) nonConstructorMutator) : new FieldProperty(beanPropertyDefinition, javaTypeResolveMemberAndTypeAnnotations, typeDeserializer, beanDescription.getClassAnnotations(), (AnnotatedField) nonConstructorMutator);
        JsonDeserializer<?> jsonDeserializerFindDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationContext, nonConstructorMutator);
        if (jsonDeserializerFindDeserializerFromAnnotation == null) {
            jsonDeserializerFindDeserializerFromAnnotation = (JsonDeserializer) javaTypeResolveMemberAndTypeAnnotations.getValueHandler();
        }
        if (jsonDeserializerFindDeserializerFromAnnotation != null) {
            methodProperty = methodProperty.withValueDeserializer(deserializationContext.handlePrimaryContextualization(jsonDeserializerFindDeserializerFromAnnotation, methodProperty, javaTypeResolveMemberAndTypeAnnotations));
        }
        AnnotationIntrospector.ReferenceProperty referencePropertyFindReferenceType = beanPropertyDefinition.findReferenceType();
        if (referencePropertyFindReferenceType != null && referencePropertyFindReferenceType.isManagedReference()) {
            methodProperty.setManagedReferenceName(referencePropertyFindReferenceType.getName());
        }
        ObjectIdInfo objectIdInfoFindObjectIdInfo = beanPropertyDefinition.findObjectIdInfo();
        if (objectIdInfoFindObjectIdInfo != null) {
            methodProperty.setObjectIdInfo(objectIdInfoFindObjectIdInfo);
        }
        return methodProperty;
    }

    protected SettableBeanProperty constructSetterlessProperty(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanPropertyDefinition beanPropertyDefinition) throws JsonMappingException {
        AnnotatedMethod getter = beanPropertyDefinition.getGetter();
        JavaType javaTypeResolveMemberAndTypeAnnotations = resolveMemberAndTypeAnnotations(deserializationContext, getter, getter.getType());
        SetterlessProperty setterlessProperty = new SetterlessProperty(beanPropertyDefinition, javaTypeResolveMemberAndTypeAnnotations, (TypeDeserializer) javaTypeResolveMemberAndTypeAnnotations.getTypeHandler(), beanDescription.getClassAnnotations(), getter);
        JsonDeserializer<?> jsonDeserializerFindDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationContext, getter);
        if (jsonDeserializerFindDeserializerFromAnnotation == null) {
            jsonDeserializerFindDeserializerFromAnnotation = (JsonDeserializer) javaTypeResolveMemberAndTypeAnnotations.getValueHandler();
        }
        return jsonDeserializerFindDeserializerFromAnnotation != null ? setterlessProperty.withValueDeserializer(deserializationContext.handlePrimaryContextualization(jsonDeserializerFindDeserializerFromAnnotation, setterlessProperty, javaTypeResolveMemberAndTypeAnnotations)) : setterlessProperty;
    }

    @Override
    public JsonDeserializer<Object> createBeanDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        JavaType javaTypeMaterializeAbstractType;
        DeserializationConfig config = deserializationContext.getConfig();
        JsonDeserializer<Object> jsonDeserializer_findCustomBeanDeserializer = _findCustomBeanDeserializer(javaType, config, beanDescription);
        if (jsonDeserializer_findCustomBeanDeserializer != null) {
            return jsonDeserializer_findCustomBeanDeserializer;
        }
        if (javaType.isThrowable()) {
            return buildThrowableDeserializer(deserializationContext, javaType, beanDescription);
        }
        if (javaType.isAbstract() && !javaType.isPrimitive() && !javaType.isEnumType() && (javaTypeMaterializeAbstractType = materializeAbstractType(deserializationContext, javaType, beanDescription)) != null) {
            return buildBeanDeserializer(deserializationContext, javaTypeMaterializeAbstractType, config.introspect(javaTypeMaterializeAbstractType));
        }
        JsonDeserializer<?> jsonDeserializerFindStdDeserializer = findStdDeserializer(deserializationContext, javaType, beanDescription);
        if (jsonDeserializerFindStdDeserializer != null) {
            return jsonDeserializerFindStdDeserializer;
        }
        if (!isPotentialBeanType(javaType.getRawClass())) {
            return null;
        }
        _validateSubType(deserializationContext, javaType, beanDescription);
        return buildBeanDeserializer(deserializationContext, javaType, beanDescription);
    }

    @Override
    public JsonDeserializer<Object> createBuilderBasedDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription, Class<?> cls) throws JsonMappingException {
        return buildBuilderBasedDeserializer(deserializationContext, javaType, deserializationContext.getConfig().introspectForBuilder(deserializationContext.constructType(cls)));
    }

    protected List<BeanPropertyDefinition> filterBeanProps(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder, List<BeanPropertyDefinition> list, Set<String> set) throws JsonMappingException {
        Class<?> rawPrimaryType;
        ArrayList arrayList = new ArrayList(Math.max(4, list.size()));
        HashMap map = new HashMap();
        for (BeanPropertyDefinition beanPropertyDefinition : list) {
            String name = beanPropertyDefinition.getName();
            if (!set.contains(name)) {
                if (beanPropertyDefinition.hasConstructorParameter() || (rawPrimaryType = beanPropertyDefinition.getRawPrimaryType()) == null || !isIgnorableType(deserializationContext.getConfig(), beanPropertyDefinition, rawPrimaryType, map)) {
                    arrayList.add(beanPropertyDefinition);
                } else {
                    beanDeserializerBuilder.addIgnorable(name);
                }
            }
        }
        return arrayList;
    }

    protected JsonDeserializer<?> findStdDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        JsonDeserializer<?> jsonDeserializerFindDefaultDeserializer = findDefaultDeserializer(deserializationContext, javaType, beanDescription);
        if (jsonDeserializerFindDefaultDeserializer != null && this._factoryConfig.hasDeserializerModifiers()) {
            Iterator<BeanDeserializerModifier> it = this._factoryConfig.deserializerModifiers().iterator();
            while (it.hasNext()) {
                jsonDeserializerFindDefaultDeserializer = it.next().modifyDeserializer(deserializationContext.getConfig(), beanDescription, jsonDeserializerFindDefaultDeserializer);
            }
        }
        return jsonDeserializerFindDefaultDeserializer;
    }

    protected boolean isIgnorableType(DeserializationConfig deserializationConfig, BeanPropertyDefinition beanPropertyDefinition, Class<?> cls, Map<Class<?>, Boolean> map) {
        Boolean isIgnoredType;
        Boolean bool = map.get(cls);
        if (bool != null) {
            return bool.booleanValue();
        }
        if (cls == String.class || cls.isPrimitive()) {
            isIgnoredType = Boolean.FALSE;
        } else {
            isIgnoredType = deserializationConfig.getConfigOverride(cls).getIsIgnoredType();
            if (isIgnoredType == null) {
                isIgnoredType = deserializationConfig.getAnnotationIntrospector().isIgnorableType(deserializationConfig.introspectClassAnnotations(cls).getClassInfo());
                if (isIgnoredType == null) {
                    isIgnoredType = Boolean.FALSE;
                }
            }
        }
        map.put(cls, isIgnoredType);
        return isIgnoredType.booleanValue();
    }

    protected boolean isPotentialBeanType(Class<?> cls) {
        String strCanBeABeanType = ClassUtil.canBeABeanType(cls);
        if (strCanBeABeanType != null) {
            throw new IllegalArgumentException("Cannot deserialize Class " + cls.getName() + " (of type " + strCanBeABeanType + ") as a Bean");
        }
        if (ClassUtil.isProxyType(cls)) {
            throw new IllegalArgumentException("Cannot deserialize Proxy class " + cls.getName() + " as a Bean");
        }
        String strIsLocalType = ClassUtil.isLocalType(cls, true);
        if (strIsLocalType == null) {
            return true;
        }
        throw new IllegalArgumentException("Cannot deserialize Class " + cls.getName() + " (of type " + strIsLocalType + ") as a Bean");
    }

    protected JavaType materializeAbstractType(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        Iterator<AbstractTypeResolver> it = this._factoryConfig.abstractTypeResolvers().iterator();
        while (it.hasNext()) {
            JavaType javaTypeResolveAbstractType = it.next().resolveAbstractType(deserializationContext.getConfig(), beanDescription);
            if (javaTypeResolveAbstractType != null) {
                return javaTypeResolveAbstractType;
            }
        }
        return null;
    }

    @Override
    public DeserializerFactory withConfig(DeserializerFactoryConfig deserializerFactoryConfig) {
        if (this._factoryConfig == deserializerFactoryConfig) {
            return this;
        }
        ClassUtil.verifyMustOverride(BeanDeserializerFactory.class, this, "withConfig");
        return new BeanDeserializerFactory(deserializerFactoryConfig);
    }
}
