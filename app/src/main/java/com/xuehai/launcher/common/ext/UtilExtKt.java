package com.xuehai.launcher.common.ext;

import com.zaze.utils.JsonUtil;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;
import org.json.JSONArray;

@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0016\n\u0002\b\u0005\u001a,\u0010\u0000\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006\u001a\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n\u001a\u000e\u0010\u000b\u001a\u0004\u0018\u00010\u0004*\u0004\u0018\u00010\u0004\u001a\u001e\u0010\f\u001a\u0004\u0018\u0001H\u0002\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u0004\u0018\u00010\u0004H\u0086\b¢\u0006\u0002\u0010\r\u001a\u000e\u0010\u0007\u001a\u0004\u0018\u00010\b*\u0004\u0018\u00010\u0004\u001a\u001f\u0010\u000e\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u0004\u0018\u00010\u0004H\u0086\b¨\u0006\u000f"}, d2 = {"parseJsonToList", "", "T", "json", "", "clazz", "Ljava/lang/Class;", "toJsonArray", "Lorg/json/JSONArray;", "classIds", "", "phoneToCiphertext", "toJson", "(Ljava/lang/String;)Ljava/lang/Object;", "toJsonList", "common_studentToBRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class UtilExtKt {
    public static final <T> List<T> parseJsonToList(String str, final Class<T> cls) {
        Intrinsics.checkNotNullParameter(cls, "clazz");
        return JsonUtil.parseJsonToList(str, new ParameterizedType() {
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

    public static final String phoneToCiphertext(String str) {
        String str2 = str;
        return (!(str2 == null || str2.length() == 0) && str.length() > 8) ? StringsKt.replaceRange((CharSequence) str2, new IntRange(3, 6), (CharSequence) "****").toString() : str;
    }

    public static final <T> T toJson(String str) {
        String str2 = str;
        if (str2 != null) {
            str2.length();
        }
        Intrinsics.reifiedOperationMarker(4, "T");
        return (T) JsonUtil.parseJson(str, Object.class);
    }

    public static final JSONArray toJsonArray(String str) {
        try {
            return new JSONArray(str);
        } catch (Exception unused) {
            return (JSONArray) null;
        }
    }

    public static final JSONArray toJsonArray(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "classIds");
        JSONArray jsonArray = JsonUtil.toJsonArray(ArraysKt.asList(jArr));
        Intrinsics.checkNotNullExpressionValue(jsonArray, "toJsonArray(classIds.asList())");
        return jsonArray;
    }

    public static final <T> List<T> toJsonList(String str) {
        Intrinsics.reifiedOperationMarker(4, "T");
        return parseJsonToList(str, Object.class);
    }
}
