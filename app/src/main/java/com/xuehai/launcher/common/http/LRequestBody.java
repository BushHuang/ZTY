package com.xuehai.launcher.common.http;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class LRequestBody {
    private LRequestBody() {
    }

    public static LRequestBody create(final LMediaType lMediaType, final Object obj) {
        return new LRequestBody() {
            {
                super();
            }

            @Override
            public String getContent() {
                Object obj2 = obj;
                return obj2 instanceof String ? (String) obj2 : ((obj2 instanceof JSONObject) || (obj2 instanceof JSONArray)) ? obj2.toString() : "";
            }

            @Override
            public LMediaType getMediaType() {
                return lMediaType;
            }
        };
    }

    public static LRequestBody create(Object obj) {
        return create(new LMediaType(), obj);
    }

    public abstract String getContent();

    public abstract LMediaType getMediaType();
}
