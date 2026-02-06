package com.xuehai.launcher.common.http;

import android.os.Build;
import com.xh.xhcore.common.util.XHLog;
import com.xuehai.launcher.common.base.BaseApplication;
import com.xuehai.launcher.common.config.ClientConfig;
import com.zaze.utils.ZStringUtil;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class LRequestHeader {
    public Map<String, Object> headers;

    public interface DefaultBuilder {
        Map<String, Object> getDefaultHeaderMap();
    }

    public LRequestHeader() {
        this(new DefaultBuilder() {
            @Override
            public final Map getDefaultHeaderMap() {
                return LRequestHeader.lambda$new$0();
            }
        });
    }

    public LRequestHeader(DefaultBuilder defaultBuilder) {
        this.headers = defaultBuilder.getDefaultHeaderMap();
    }

    static Map lambda$new$0() {
        HashMap map = new HashMap();
        try {
            String str = ZStringUtil.format("%s/%s (%s; android; %s; %s)", ClientConfig.INSTANCE.getPackageName(), ClientConfig.INSTANCE.getAppVersion(), Build.MODEL, Build.VERSION.RELEASE, BaseApplication.getInstance().getDeviceId());
            map.put("Content-Type", "application/json");
            map.put("Accept", "*/*");
            map.put("User-Agent", str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public LRequestHeader header(String str, String str2) {
        try {
            this.headers.put(str, str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public LRequestHeader headers(Map<String, Object> map) {
        this.headers = map;
        return this;
    }

    public void log() {
        JSONObject jSONObject = new JSONObject();
        try {
            for (String str : this.headers.keySet()) {
                jSONObject.put(str, this.headers.get(str));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        XHLog.d("Http[MDM]", "jsonHeadLines = %s", jSONObject);
    }
}
