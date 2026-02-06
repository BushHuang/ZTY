package com.xuehai.provider.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Vector;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.reflect.KProperty;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u0007J-\u0010\f\u001a\u0004\u0018\u0001H\r\"\u0004\b\u0000\u0010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\n2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\r0\u0010H\u0007¢\u0006\u0002\u0010\u0011J.\u0010\u0012\u001a\n\u0012\u0004\u0012\u0002H\r\u0018\u00010\u0013\"\u0004\b\u0000\u0010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\n2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\r0\u0010H\u0007J(\u0010\u0012\u001a\n\u0012\u0004\u0012\u0002H\r\u0018\u00010\u0013\"\u0004\b\u0000\u0010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J(\u0010\u0016\u001a\n\u0012\u0004\u0012\u0002H\r\u0018\u00010\u0017\"\u0004\b\u0000\u0010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0014\u001a\u00020\u0015H\u0003R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0018"}, d2 = {"Lcom/xuehai/provider/utils/JsonUtil;", "", "()V", "gson", "Lcom/google/gson/Gson;", "getGson", "()Lcom/google/gson/Gson;", "gson$delegate", "Lkotlin/Lazy;", "objToJson", "", "obj", "parseJson", "T", "json", "clazz", "Ljava/lang/Class;", "(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;", "parseJsonToList", "", "type", "Ljava/lang/reflect/Type;", "parseJsonToListHasNull", "", "library_release"}, k = 1, mv = {1, 1, 15})
public final class JsonUtil {
    static final KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(JsonUtil.class), "gson", "getGson()Lcom/google/gson/Gson;"))};
    public static final JsonUtil INSTANCE = new JsonUtil();

    private static final Lazy gson = LazyKt.lazy(new Function0<Gson>() {
        @Override
        public final Gson invoke() {
            return new GsonBuilder().create();
        }
    });

    private JsonUtil() {
    }

    private final Gson getGson() {
        Lazy lazy = gson;
        KProperty kProperty = $$delegatedProperties[0];
        return (Gson) lazy.getValue();
    }

    @JvmStatic
    public static final String objToJson(Object obj) {
        if (obj == null) {
            return null;
        }
        if ((obj instanceof JSONObject) || (obj instanceof JSONArray)) {
            return obj.toString();
        }
        try {
            return INSTANCE.getGson().toJson(obj);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    @JvmStatic
    public static final <T> T parseJson(String json, Class<T> clazz) {
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        if (json == null) {
            return null;
        }
        try {
            return (T) INSTANCE.getGson().fromJson(json, (Class) clazz);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    @JvmStatic
    public static final <T> List<T> parseJsonToList(String json, final Class<T> clazz) {
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        String str = json;
        if (str == null || str.length() == 0) {
            return null;
        }
        return parseJsonToList(json, new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return new Type[]{clazz};
            }

            @Override
            public Type getOwnerType() {
                return null;
            }

            @Override
            public Type getRawType() {
                return List.class;
            }
        });
    }

    @JvmStatic
    public static final <T> List<T> parseJsonToList(String json, Type type) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        List<T> jsonToListHasNull = parseJsonToListHasNull(json, type);
        if (jsonToListHasNull != null) {
            Vector vector = new Vector();
            vector.add(null);
            TypeIntrinsics.asMutableCollection(jsonToListHasNull).removeAll(vector);
        }
        return jsonToListHasNull;
    }

    @JvmStatic
    private static final <T> List<T> parseJsonToListHasNull(String json, Type type) {
        if (json == null) {
            return null;
        }
        try {
            return (List) INSTANCE.getGson().fromJson(json, type);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}
