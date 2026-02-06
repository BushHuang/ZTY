package com.huawei.hms.framework.network.grs.f;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.StringUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class c extends a {
    public c(Context context, boolean z) {
        this.e = z;
        if (a("grs_sdk_global_route_config.json", context) == 0) {
            this.d = true;
        }
    }

    private List<com.huawei.hms.framework.network.grs.local.model.b> a(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray;
        HashSet hashSet;
        int i;
        try {
            ArrayList arrayList = new ArrayList(16);
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                com.huawei.hms.framework.network.grs.local.model.b bVar = new com.huawei.hms.framework.network.grs.local.model.b();
                bVar.b(next);
                JSONObject jSONObject2 = jSONObject.getJSONObject(next);
                bVar.c(jSONObject2.getString("name"));
                bVar.a(jSONObject2.getString("description"));
                JSONArray jSONArray2 = null;
                if (jSONObject2.has("countriesOrAreas")) {
                    jSONArray = jSONObject2.getJSONArray("countriesOrAreas");
                } else {
                    if (!jSONObject2.has("countries")) {
                        Logger.w("LocalManagerV1", "current country or area group has not config countries or areas.");
                        hashSet = new HashSet(16);
                        if (jSONArray2 != null && jSONArray2.length() != 0) {
                            for (i = 0; i < jSONArray2.length(); i++) {
                                hashSet.add((String) jSONArray2.get(i));
                            }
                            bVar.a(hashSet);
                            arrayList.add(bVar);
                        }
                        return new ArrayList();
                    }
                    jSONArray = jSONObject2.getJSONArray("countries");
                }
                jSONArray2 = jSONArray;
                hashSet = new HashSet(16);
                if (jSONArray2 != null) {
                    while (i < jSONArray2.length()) {
                    }
                    bVar.a(hashSet);
                    arrayList.add(bVar);
                }
                return new ArrayList();
            }
            return arrayList;
        } catch (JSONException e) {
            Logger.w("LocalManagerV1", "parse countryGroups failed maybe json style is wrong. %s", StringUtils.anonymizeMessage(e.getMessage()));
            return new ArrayList();
        }
    }

    @Override
    public int a(String str) throws JSONException {
        this.f262a = new com.huawei.hms.framework.network.grs.local.model.a();
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject("application");
            String string = jSONObject.getString("name");
            long j = jSONObject.getLong("cacheControl");
            JSONArray jSONArray = jSONObject.getJSONArray("services");
            this.f262a.b(string);
            this.f262a.a(j);
            if (jSONArray != null) {
                if (jSONArray.length() != 0) {
                    return 0;
                }
            }
            return -1;
        } catch (JSONException e) {
            Logger.w("LocalManagerV1", "parse appbean failed maybe json style is wrong. %s", StringUtils.anonymizeMessage(e.getMessage()));
            return -1;
        }
    }

    public List<com.huawei.hms.framework.network.grs.local.model.b> a(JSONArray jSONArray, JSONObject jSONObject) {
        return (jSONObject == null || jSONObject.length() == 0) ? new ArrayList() : a(jSONObject);
    }

    @Override
    public int b(String str) throws JSONException {
        this.b = new ArrayList(16);
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = null;
            if (jSONObject.has("countryOrAreaGroups")) {
                jSONObject2 = jSONObject.getJSONObject("countryOrAreaGroups");
            } else if (jSONObject.has("countryGroups")) {
                jSONObject2 = jSONObject.getJSONObject("countryGroups");
            } else {
                Logger.e("LocalManagerV1", "maybe local config json is wrong because the default countryOrAreaGroups isn't config.");
            }
            if (jSONObject2 == null) {
                return -1;
            }
            if (jSONObject2.length() != 0) {
                this.b.addAll(a(jSONObject2));
            }
            return 0;
        } catch (JSONException e) {
            Logger.w("LocalManagerV1", "parse countrygroup failed maybe json style is wrong. %s", StringUtils.anonymizeMessage(e.getMessage()));
            return -1;
        }
    }

    @Override
    public int e(String str) throws JSONException {
        JSONObject jSONObject;
        String str2;
        String string;
        Iterator<String> itKeys;
        String str3 = "countryGroup";
        String str4 = "countryOrAreaGroup";
        try {
            JSONObject jSONObject2 = new JSONObject(str).getJSONObject("services");
            Iterator<String> itKeys2 = jSONObject2.keys();
            while (itKeys2.hasNext()) {
                String next = itKeys2.next();
                com.huawei.hms.framework.network.grs.local.model.c cVar = new com.huawei.hms.framework.network.grs.local.model.c();
                cVar.b(next);
                if (!this.g.contains(next)) {
                    this.g.add(next);
                    if (this.e) {
                        JSONObject jSONObject3 = jSONObject2.getJSONObject(next);
                        cVar.c(jSONObject3.getString("routeBy"));
                        JSONArray jSONArray = jSONObject3.getJSONArray("servings");
                        int i = 0;
                        while (i < jSONArray.length()) {
                            JSONObject jSONObject4 = (JSONObject) jSONArray.get(i);
                            com.huawei.hms.framework.network.grs.local.model.d dVar = new com.huawei.hms.framework.network.grs.local.model.d();
                            if (jSONObject4.has(str4)) {
                                string = jSONObject4.getString(str4);
                            } else if (jSONObject4.has(str3)) {
                                string = jSONObject4.getString(str3);
                            } else {
                                Logger.v("LocalManagerV1", "maybe this service routeBy is unconditional.");
                                str2 = "no-country";
                                dVar.a(str2);
                                JSONObject jSONObject5 = jSONObject4.getJSONObject("addresses");
                                String str5 = str3;
                                ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(16);
                                itKeys = jSONObject5.keys();
                                while (itKeys.hasNext()) {
                                    Iterator<String> it = itKeys;
                                    String next2 = itKeys.next();
                                    String string2 = jSONObject5.getString(next2);
                                    if (TextUtils.isEmpty(next2) || TextUtils.isEmpty(string2)) {
                                        itKeys = it;
                                    } else {
                                        concurrentHashMap.put(next2, jSONObject5.getString(next2));
                                        itKeys = it;
                                        str4 = str4;
                                    }
                                }
                                dVar.a(concurrentHashMap);
                                cVar.a(dVar.b(), dVar);
                                i++;
                                str3 = str5;
                                str4 = str4;
                            }
                            str2 = string;
                            dVar.a(str2);
                            JSONObject jSONObject52 = jSONObject4.getJSONObject("addresses");
                            String str52 = str3;
                            ConcurrentHashMap concurrentHashMap2 = new ConcurrentHashMap(16);
                            itKeys = jSONObject52.keys();
                            while (itKeys.hasNext()) {
                            }
                            dVar.a(concurrentHashMap2);
                            cVar.a(dVar.b(), dVar);
                            i++;
                            str3 = str52;
                            str4 = str4;
                        }
                        String str6 = str3;
                        String str7 = str4;
                        List<com.huawei.hms.framework.network.grs.local.model.b> listA = null;
                        if (jSONObject3.has("countryOrAreaGroups")) {
                            jSONObject = jSONObject3.getJSONObject("countryOrAreaGroups");
                        } else if (jSONObject3.has("countryGroups")) {
                            jSONObject = jSONObject3.getJSONObject("countryGroups");
                        } else {
                            Logger.v("LocalManagerV1", "service use default countryOrAreaGroup");
                            cVar.a(listA);
                            if (this.f262a == null) {
                                this.f262a = new com.huawei.hms.framework.network.grs.local.model.a();
                            }
                            this.f262a.a(next, cVar);
                            str3 = str6;
                            str4 = str7;
                        }
                        listA = a((JSONArray) null, jSONObject);
                        cVar.a(listA);
                        if (this.f262a == null) {
                        }
                        this.f262a.a(next, cVar);
                        str3 = str6;
                        str4 = str7;
                    }
                }
            }
            return 0;
        } catch (JSONException e) {
            Logger.w("LocalManagerV1", "parse 1.0 services failed maybe because of json style.please check! %s", StringUtils.anonymizeMessage(e.getMessage()));
            return -1;
        }
    }
}
