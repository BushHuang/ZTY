package com.analysys;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import com.analysys.utils.AnalysysUtil;
import com.analysys.utils.ExceptionUtil;
import java.util.HashMap;
import java.util.Map;

public class AnsRamControl {
    private static volatile AnsRamControl instance;

    public static final class a {

        private static volatile a f9a;
        private Map<String, String> b = new HashMap();

        private a() {
            c();
        }

        public static a a() {
            if (f9a == null) {
                synchronized (a.class) {
                    if (f9a == null) {
                        f9a = new a();
                    }
                }
            }
            return f9a;
        }

        private void c() {
            this.b.put("ram_net_work_type", String.valueOf(255));
            this.b.put("ram_cache_max_count", String.valueOf(10000L));
        }

        public Map<String, String> b() {
            return this.b;
        }
    }

    private AnsRamControl() {
    }

    private String get(String str, String str2) {
        Cursor cursorQuery;
        String string = null;
        try {
            cursorQuery = AnalysysUtil.getContext().getContentResolver().query(e.c(AnalysysUtil.getContext()), new String[]{str, str2}, str2, null, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.getCount() >= 1 && cursorQuery.moveToPosition(0)) {
                        string = cursorQuery.getString(0);
                    }
                } catch (Throwable th) {
                    th = th;
                    try {
                        ExceptionUtil.exceptionThrow(th);
                        if (cursorQuery != null) {
                        }
                        if (string != null) {
                        }
                    } finally {
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
            cursorQuery = null;
        }
        return string != null ? str2 : string;
    }

    public static AnsRamControl getInstance() {
        if (instance == null) {
            synchronized (AnsRamControl.class) {
                if (instance == null) {
                    instance = new AnsRamControl();
                }
            }
        }
        return instance;
    }

    private void set(String str, String str2) {
        try {
            Uri uriC = e.c(AnalysysUtil.getContext());
            ContentValues contentValues = new ContentValues();
            contentValues.put("key", str);
            contentValues.put("values", str2);
            AnalysysUtil.getContext().getContentResolver().insert(uriC, contentValues);
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    public long getCacheMaxCount() {
        try {
            return Long.valueOf(get("ram_cache_max_count", String.valueOf(10000L))).longValue();
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
            return 10000L;
        }
    }

    public int getUploadNetworkType() {
        try {
            return Integer.valueOf(get("ram_net_work_type", String.valueOf(255))).intValue();
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
            return 255;
        }
    }

    public void setCacheMaxCount(long j) {
        set("ram_cache_max_count", String.valueOf(j));
    }

    public void setUploadNetworkType(int i) {
        set("ram_net_work_type", String.valueOf(i));
    }
}
