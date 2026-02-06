package com.xh.xhcore.common.util;

import android.os.Build;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {
    static final String LINE_SPLIT_REGEX = "\n\t|\r\n|\n";
    private static GsonBuilder gsonBuilder;

    public static String JSONObjectToString(JSONObject jSONObject) {
        try {
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                stringValueToJSONArray(jSONObject, itKeys.next());
            }
            return jSONObject.toString(4);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static Gson create() {
        if (gsonBuilder == null) {
            gsonBuilder = new GsonBuilder();
        }
        return gsonBuilder.create();
    }

    public static <T> T parseObject(String str, Class<T> cls) {
        if (str != null && cls != null) {
            try {
                return (T) create().fromJson(str, (Class) cls);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return null;
    }

    public static <T> T parseObject(String str, Type type) {
        if (str != null && type != null) {
            try {
                return (T) create().fromJson(str, type);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return null;
    }

    public static <T> List<T> parseToList(String str, Type type) {
        List<T> toListHasNull = parseToListHasNull(str, type);
        if (toListHasNull != null) {
            Vector vector = new Vector();
            vector.add(null);
            toListHasNull.removeAll(vector);
        }
        return toListHasNull;
    }

    public static <T> List<T> parseToListHasNull(String str, Type type) {
        if (str != null && type != null) {
            try {
                return (List) create().fromJson(str, type);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return null;
    }

    public static JsonObject stringToJsonObject(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return new JsonParser().parse(str).getAsJsonObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void stringValueToJSONArray(JSONObject jSONObject, String str) throws JSONException {
        Object objOpt = jSONObject.opt(str);
        if (objOpt instanceof String) {
            String[] strArrSplit = ((String) objOpt).split("\n\t|\r\n|\n");
            if (strArrSplit.length <= 1 || Build.VERSION.SDK_INT < 19) {
                return;
            }
            try {
                jSONObject.put(str, new JSONArray(strArrSplit));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String toJsonString(Object obj) {
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

    public static String toJsonStringFormat(Object obj) {
        return obj instanceof JSONObject ? JSONObjectToString((JSONObject) obj) : toJsonString(obj);
    }
}
