package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class TypeBase extends JavaType implements JsonSerializable {
    private static final TypeBindings NO_BINDINGS = TypeBindings.emptyBindings();
    private static final JavaType[] NO_TYPES = new JavaType[0];
    private static final long serialVersionUID = 1;
    protected final TypeBindings _bindings;
    volatile transient String _canonicalName;
    protected final JavaType _superClass;
    protected final JavaType[] _superInterfaces;

    protected TypeBase(TypeBase typeBase) {
        super(typeBase);
        this._superClass = typeBase._superClass;
        this._superInterfaces = typeBase._superInterfaces;
        this._bindings = typeBase._bindings;
    }

    protected TypeBase(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr, int i, Object obj, Object obj2, boolean z) {
        super(cls, i, obj, obj2, z);
        this._bindings = typeBindings == null ? NO_BINDINGS : typeBindings;
        this._superClass = javaType;
        this._superInterfaces = javaTypeArr;
    }

    protected static JavaType _bogusSuperClass(Class<?> cls) {
        if (cls.getSuperclass() == null) {
            return null;
        }
        return TypeFactory.unknownType();
    }

    protected static StringBuilder _classSignature(Class<?> cls, StringBuilder sb, boolean z) {
        if (!cls.isPrimitive()) {
            sb.append('L');
            String name = cls.getName();
            int length = name.length();
            for (int i = 0; i < length; i++) {
                char cCharAt = name.charAt(i);
                if (cCharAt == '.') {
                    cCharAt = '/';
                }
                sb.append(cCharAt);
            }
            if (z) {
                sb.append(';');
            }
        } else if (cls == Boolean.TYPE) {
            sb.append('Z');
        } else if (cls == Byte.TYPE) {
            sb.append('B');
        } else if (cls == Short.TYPE) {
            sb.append('S');
        } else if (cls == Character.TYPE) {
            sb.append('C');
        } else if (cls == Integer.TYPE) {
            sb.append('I');
        } else if (cls == Long.TYPE) {
            sb.append('J');
        } else if (cls == Float.TYPE) {
            sb.append('F');
        } else if (cls == Double.TYPE) {
            sb.append('D');
        } else {
            if (cls != Void.TYPE) {
                throw new IllegalStateException("Unrecognized primitive type: " + cls.getName());
            }
            sb.append('V');
        }
        return sb;
    }

    protected String buildCanonicalName() {
        return this._class.getName();
    }

    @Override
    public JavaType containedType(int i) {
        return this._bindings.getBoundType(i);
    }

    @Override
    public int containedTypeCount() {
        return this._bindings.size();
    }

    @Override
    @Deprecated
    public String containedTypeName(int i) {
        return this._bindings.getBoundName(i);
    }

    @Override
    public final JavaType findSuperType(Class<?> cls) {
        JavaType javaTypeFindSuperType;
        JavaType[] javaTypeArr;
        if (cls == this._class) {
            return this;
        }
        if (cls.isInterface() && (javaTypeArr = this._superInterfaces) != null) {
            int length = javaTypeArr.length;
            for (int i = 0; i < length; i++) {
                JavaType javaTypeFindSuperType2 = this._superInterfaces[i].findSuperType(cls);
                if (javaTypeFindSuperType2 != null) {
                    return javaTypeFindSuperType2;
                }
            }
        }
        JavaType javaType = this._superClass;
        if (javaType == null || (javaTypeFindSuperType = javaType.findSuperType(cls)) == null) {
            return null;
        }
        return javaTypeFindSuperType;
    }

    @Override
    public JavaType[] findTypeParameters(Class<?> cls) {
        JavaType javaTypeFindSuperType = findSuperType(cls);
        return javaTypeFindSuperType == null ? NO_TYPES : javaTypeFindSuperType.getBindings().typeParameterArray();
    }

    @Override
    public TypeBindings getBindings() {
        return this._bindings;
    }

    @Override
    public abstract StringBuilder getErasedSignature(StringBuilder sb);

    @Override
    public abstract StringBuilder getGenericSignature(StringBuilder sb);

    @Override
    public List<JavaType> getInterfaces() {
        int length;
        JavaType[] javaTypeArr = this._superInterfaces;
        if (javaTypeArr != null && (length = javaTypeArr.length) != 0) {
            return length != 1 ? Arrays.asList(javaTypeArr) : Collections.singletonList(javaTypeArr[0]);
        }
        return Collections.emptyList();
    }

    @Override
    public JavaType getSuperClass() {
        return this._superClass;
    }

    @Override
    public void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(toCanonical());
    }

    @Override
    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        WritableTypeId writableTypeId = new WritableTypeId(this, JsonToken.VALUE_STRING);
        typeSerializer.writeTypePrefix(jsonGenerator, writableTypeId);
        serialize(jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffix(jsonGenerator, writableTypeId);
    }

    @Override
    public String toCanonical() {
        String str = this._canonicalName;
        return str == null ? buildCanonicalName() : str;
    }
}
