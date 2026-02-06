package com.analysys;

import android.content.Intent;
import android.net.Uri;
import com.analysys.utils.CommonUtils;
import com.analysys.utils.InternalAgent;
import java.util.HashMap;
import java.util.Map;

public class h {

    private static String f47a;

    public static Map<String, Object> a(Intent intent) {
        HashMap map = new HashMap();
        if (!CommonUtils.isEmpty(intent)) {
            Uri data = intent.getData();
            InternalAgent.uri = String.valueOf(data);
            if (CommonUtils.isEmpty(data) || a(data)) {
                return map;
            }
            if (!CommonUtils.isEmpty(data.getQueryParameter("utm_source")) && !CommonUtils.isEmpty(data.getQueryParameter("utm_medium")) && !CommonUtils.isEmpty(data.getQueryParameter("utm_campaign"))) {
                a(map, data);
            } else if (!CommonUtils.isEmpty(data.getQueryParameter("hmsr")) && !CommonUtils.isEmpty(data.getQueryParameter("hmpl")) && !CommonUtils.isEmpty(data.getQueryParameter("hmcu"))) {
                b(map, data);
            }
        }
        return map;
    }

    private static void a(Map<String, Object> map, Uri uri) {
        ad.a(map, "$utm_source", uri.getQueryParameter("utm_source"));
        ad.a(map, "$utm_medium", uri.getQueryParameter("utm_medium"));
        ad.a(map, "$utm_campaign", uri.getQueryParameter("utm_campaign"));
        ad.a(map, "$utm_campaign_id", uri.getQueryParameter("utm_campaign_id"));
        ad.a(map, "$utm_content", uri.getQueryParameter("utm_content"));
        ad.a(map, "$utm_term", uri.getQueryParameter("utm_term"));
    }

    private static boolean a(Uri uri) {
        if (!CommonUtils.isEmpty(f47a) && f47a.equals(uri.toString())) {
            return true;
        }
        f47a = uri.toString();
        return false;
    }

    private static void b(Map<String, Object> map, Uri uri) {
        ad.a(map, "$utm_source", uri.getQueryParameter("hmsr"));
        ad.a(map, "$utm_medium", uri.getQueryParameter("hmpl"));
        ad.a(map, "$utm_campaign", uri.getQueryParameter("hmcu"));
        ad.a(map, "$utm_campaign", uri.getQueryParameter("hmkw"));
        ad.a(map, "$utm_content", uri.getQueryParameter("hmci"));
    }
}
