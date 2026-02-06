package com.huawei.agconnect.config.a;

import android.content.Context;
import android.util.Log;
import com.huawei.agconnect.AGCRoutePolicy;
import com.huawei.agconnect.AGConnectOptions;
import com.huawei.agconnect.JsonProcessingFactory;
import com.huawei.agconnect.core.Service;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class b implements AGConnectOptions {

    private final String f165a;
    private final Context b;
    private final String c;
    private final AGCRoutePolicy d;
    private final d e;
    private final Map<String, String> f;
    private final List<Service> g;
    private final Map<String, String> h = new HashMap();

    public b(Context context, String str, AGCRoutePolicy aGCRoutePolicy, InputStream inputStream, Map<String, String> map, List<Service> list, String str2) throws IOException {
        this.b = context;
        str = str == null ? context.getPackageName() : str;
        this.c = str;
        if (inputStream != null) {
            this.e = new f(inputStream);
            j.a(inputStream);
        } else {
            this.e = new i(context, str);
        }
        if ("1.0".equals(this.e.a("/configuration_version", null))) {
            Log.e("AGConnectOptionsImpl", "The file version does not match, please download the latest agconnect-services.json from the AGC website.");
        }
        this.d = aGCRoutePolicy == AGCRoutePolicy.UNKNOWN ? j.a(this.e.a("/region", null), this.e.a("/agcgw/url", null)) : aGCRoutePolicy;
        this.f = j.a(map);
        this.g = list;
        this.f165a = str2 == null ? b() : str2;
    }

    private String a(String str) {
        Map<String, JsonProcessingFactory.JsonProcessor> processors = JsonProcessingFactory.getProcessors();
        if (!processors.containsKey(str)) {
            return null;
        }
        if (this.h.containsKey(str)) {
            return this.h.get(str);
        }
        JsonProcessingFactory.JsonProcessor jsonProcessor = processors.get(str);
        if (jsonProcessor == null) {
            return null;
        }
        String strProcessOption = jsonProcessor.processOption(this);
        this.h.put(str, strProcessOption);
        return strProcessOption;
    }

    private String b() {
        return String.valueOf(("{packageName='" + this.c + "', routePolicy=" + this.d + ", reader=" + this.e.toString().hashCode() + ", customConfigMap=" + new JSONObject(this.f).toString().hashCode() + '}').hashCode());
    }

    public List<Service> a() {
        return this.g;
    }

    @Override
    public boolean getBoolean(String str) {
        return getBoolean(str, false);
    }

    @Override
    public boolean getBoolean(String str, boolean z) {
        return Boolean.parseBoolean(getString(str, String.valueOf(z)));
    }

    @Override
    public Context getContext() {
        return this.b;
    }

    @Override
    public String getIdentifier() {
        return this.f165a;
    }

    @Override
    public int getInt(String str) {
        return getInt(str, 0);
    }

    @Override
    public int getInt(String str, int i) {
        try {
            return Integer.parseInt(getString(str, String.valueOf(i)));
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    @Override
    public String getPackageName() {
        return this.c;
    }

    @Override
    public AGCRoutePolicy getRoutePolicy() {
        return this.d;
    }

    @Override
    public String getString(String str) {
        return getString(str, null);
    }

    @Override
    public String getString(String str, String str2) {
        if (str == null) {
            return str2;
        }
        String strA = j.a(str);
        String str3 = this.f.get(strA);
        if (str3 != null) {
            return str3;
        }
        String strA2 = a(strA);
        return strA2 != null ? strA2 : this.e.a(strA, str2);
    }
}
