package com.zaze.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.zaze.utils.log.ZLog;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J!\u0010\u000b\u001a\u00020\f\"\u0004\b\u0000\u0010\r2\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u0002H\r¢\u0006\u0002\u0010\u0010J!\u0010\u0011\u001a\u00020\u0012\"\u0004\b\u0000\u0010\r2\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u0002H\r¢\u0006\u0002\u0010\u0013J\u0011\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\u0005H\u0086\u0002J$\u0010\u0015\u001a\u0002H\r\"\u0004\b\u0000\u0010\r2\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u0002H\rH\u0086\u0002¢\u0006\u0002\u0010\u0017J!\u0010\u0018\u001a\u00020\u0019\"\u0004\b\u0000\u0010\r2\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u0002H\r¢\u0006\u0002\u0010\u001aR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u001c"}, d2 = {"Lcom/zaze/utils/SharedPrefUtil;", "", "context", "Landroid/content/Context;", "spName", "", "(Landroid/content/Context;Ljava/lang/String;)V", "sharedPreferences", "Landroid/content/SharedPreferences;", "getSharedPreferences", "()Landroid/content/SharedPreferences;", "apply", "", "T", "key", "value", "(Ljava/lang/String;Ljava/lang/Object;)V", "commit", "", "(Ljava/lang/String;Ljava/lang/Object;)Z", "contains", "get", "defaultValue", "(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "put", "Landroid/content/SharedPreferences$Editor;", "(Ljava/lang/String;Ljava/lang/Object;)Landroid/content/SharedPreferences$Editor;", "Companion", "util_release"}, k = 1, mv = {1, 4, 1})
public final class SharedPrefUtil {

    public static final Companion INSTANCE = new Companion(null);
    private final SharedPreferences sharedPreferences;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J,\u0010\u0003\u001a\u0002H\u0004\"\u0004\b\u0000\u0010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u0002H\u0004H\u0086\u0002¢\u0006\u0002\u0010\nJ\u001c\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\bH\u0007J)\u0010\u0010\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u00042\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0013\u001a\u0002H\u0004¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, d2 = {"Lcom/zaze/utils/SharedPrefUtil$Companion;", "", "()V", "get", "T", "sharedPreferences", "Landroid/content/SharedPreferences;", "key", "", "defaultValue", "(Landroid/content/SharedPreferences;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "newInstance", "Lcom/zaze/utils/SharedPrefUtil;", "context", "Landroid/content/Context;", "spName", "put", "Landroid/content/SharedPreferences$Editor;", "editor", "value", "(Landroid/content/SharedPreferences$Editor;Ljava/lang/String;Ljava/lang/Object;)Landroid/content/SharedPreferences$Editor;", "util_release"}, k = 1, mv = {1, 4, 1})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static SharedPrefUtil newInstance$default(Companion companion, Context context, String str, int i, Object obj) {
            if ((i & 2) != 0) {
                str = (String) null;
            }
            return companion.newInstance(context, str);
        }

        public final <T> T get(SharedPreferences sharedPreferences, String key, T defaultValue) {
            Intrinsics.checkNotNullParameter(sharedPreferences, "sharedPreferences");
            Intrinsics.checkNotNullParameter(key, "key");
            if (defaultValue instanceof String) {
                CharSequence string = sharedPreferences.getString(key, (String) defaultValue);
                if (string != null) {
                    return (T) string;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Any");
            }
            if (defaultValue instanceof Integer) {
                return (T) Integer.valueOf(sharedPreferences.getInt(key, ((Number) defaultValue).intValue()));
            }
            if (defaultValue instanceof Boolean) {
                return (T) Boolean.valueOf(sharedPreferences.getBoolean(key, ((Boolean) defaultValue).booleanValue()));
            }
            if (defaultValue instanceof Float) {
                return (T) Float.valueOf(sharedPreferences.getFloat(key, ((Number) defaultValue).floatValue()));
            }
            if (defaultValue instanceof Long) {
                return (T) Long.valueOf(sharedPreferences.getLong(key, ((Number) defaultValue).longValue()));
            }
            ZLog.w("Error[ZZ]", "get failed not support type", new Object[0]);
            return defaultValue;
        }

        public final SharedPrefUtil newInstance(Context context) {
            return newInstance$default(this, context, null, 2, null);
        }

        public final SharedPrefUtil newInstance(Context context, String spName) {
            Intrinsics.checkNotNullParameter(context, "context");
            return new SharedPrefUtil(context, spName, null);
        }

        public final <T> SharedPreferences.Editor put(SharedPreferences.Editor editor, String key, T value) {
            Intrinsics.checkNotNullParameter(editor, "editor");
            Intrinsics.checkNotNullParameter(key, "key");
            if (value instanceof String) {
                editor.putString(key, (String) value);
            } else if (value instanceof Integer) {
                editor.putInt(key, ((Number) value).intValue());
            } else if (value instanceof Boolean) {
                editor.putBoolean(key, ((Boolean) value).booleanValue());
            } else if (value instanceof Float) {
                editor.putFloat(key, ((Number) value).floatValue());
            } else if (value instanceof Long) {
                editor.putLong(key, ((Number) value).longValue());
            } else {
                ZLog.w("Error[ZZ]", "put failed not support type", new Object[0]);
            }
            return editor;
        }
    }

    private SharedPrefUtil(Context context, String str) {
        SharedPreferences sharedPreferences;
        if (TextUtils.isEmpty(str)) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
            Intrinsics.checkNotNullExpressionValue(sharedPreferences, "PreferenceManager.getDef…ntext.applicationContext)");
        } else {
            sharedPreferences = context.getApplicationContext().getSharedPreferences(str, 0);
            Intrinsics.checkNotNullExpressionValue(sharedPreferences, "context.applicationConte…me, Context.MODE_PRIVATE)");
        }
        this.sharedPreferences = sharedPreferences;
    }

    public SharedPrefUtil(Context context, String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, str);
    }

    public final <T> void apply(String key, T value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Companion companion = INSTANCE;
        SharedPreferences.Editor editorEdit = this.sharedPreferences.edit();
        Intrinsics.checkNotNullExpressionValue(editorEdit, "sharedPreferences.edit()");
        companion.put(editorEdit, key, value).apply();
    }

    public final <T> boolean commit(String key, T value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Companion companion = INSTANCE;
        SharedPreferences.Editor editorEdit = this.sharedPreferences.edit();
        Intrinsics.checkNotNullExpressionValue(editorEdit, "sharedPreferences.edit()");
        return companion.put(editorEdit, key, value).commit();
    }

    public final boolean contains(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.sharedPreferences.contains(key);
    }

    public final <T> T get(String key, T defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        return (T) INSTANCE.get(this.sharedPreferences, key, defaultValue);
    }

    public final SharedPreferences getSharedPreferences() {
        return this.sharedPreferences;
    }

    public final <T> SharedPreferences.Editor put(String key, T value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Companion companion = INSTANCE;
        SharedPreferences.Editor editorEdit = this.sharedPreferences.edit();
        Intrinsics.checkNotNullExpressionValue(editorEdit, "sharedPreferences.edit()");
        return companion.put(editorEdit, key, value);
    }
}
