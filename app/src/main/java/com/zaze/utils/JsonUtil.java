package com.zaze.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {
    private static GsonBuilder gsonBuilder;

    public static Gson create() {
        if (gsonBuilder == null) {
            gsonBuilder = new GsonBuilder();
        }
        return gsonBuilder.create();
    }

    public static JSONObject mapToJson(Map<String, Object> map) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            for (String str : map.keySet()) {
                jSONObject.put(str, map.get(str));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public static String objToJson(Object obj) {
        if (obj == null) {
            return null;
        }
        if ((obj instanceof JSONObject) || (obj instanceof JSONArray)) {
            return obj.toString();
        }
        try {
            return create().toJson(obj);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static <T> T parseJson(String str, Class<T> cls) {
        if (str != null && cls != null) {
            try {
                return (T) create().fromJson(str, (Class) cls);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return null;
    }

    public static <T> List<T> parseJsonArrayToList(JSONArray jSONArray, Type type) {
        return parseJsonToList(jSONArray.toString(), type);
    }

    public static <T> List<T> parseJsonToList(String str, final Class<T> cls) {
        return parseJsonToList(str, new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return new Class[]{cls};
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

    public static <T> List<T> parseJsonToList(String str, Type type) {
        List<T> jsonToListHasNull = parseJsonToListHasNull(str, type);
        if (jsonToListHasNull != null) {
            Vector vector = new Vector();
            vector.add(null);
            jsonToListHasNull.removeAll(vector);
        }
        return jsonToListHasNull;
    }

    public static <T> List<T> parseJsonToListByClass(String str, final Class<T> cls) {
        return parseJsonToList(str, new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return new Type[]{cls};
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

    public static <T> List<T> parseJsonToListHasNull(String str, Type type) {
        if (str != null && type != null) {
            try {
                return (List) create().fromJson(str, type);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return null;
    }

    public static <T> JSONArray toJsonArray(Collection<T> collection) {
        JSONArray jSONArray = new JSONArray();
        if (collection != null) {
            for (T t : collection) {
                if (t != null) {
                    if ((t instanceof JSONObject) || (t instanceof JSONArray) || (t instanceof Boolean) || (t instanceof Byte) || (t instanceof Character) || (t instanceof Double) || (t instanceof Float) || (t instanceof Integer) || (t instanceof Long) || (t instanceof Short) || (t instanceof String)) {
                        jSONArray.put(t);
                    } else {
                        try {
                            jSONArray.put(new JSONObject(objToJson(t)));
                        } catch (Throwable unused) {
                            jSONArray.put(t);
                        }
                    }
                }
            }
        }
        return jSONArray;
    }

    public static <T> JSONArray toJsonArray(T[] tArr) {
        return toJsonArray(Arrays.asList(tArr));
    }
}
