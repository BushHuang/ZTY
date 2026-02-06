package com.obs.services.internal.utils;

import com.obs.services.internal.IHeaders;
import com.obs.services.internal.ServiceException;
import com.obs.services.internal.security.ProviderCredentials;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public abstract class AbstractAuthentication {
    public static String caculateSignature(String str, String str2) throws ServiceException {
        return ServiceUtils.signWithHmacSha1(str2, str);
    }

    protected abstract String getAuthPrefix();

    protected abstract IHeaders getIHeaders();

    public final IAuthentication makeAuthorizationString(String str, Map<String, String> map, String str2, List<String> list, ProviderCredentials providerCredentials) throws ServiceException {
        try {
            String strMakeServiceCanonicalString = makeServiceCanonicalString(str, str2, map, null, list);
            return new DefaultAuthentication(strMakeServiceCanonicalString, strMakeServiceCanonicalString, getAuthPrefix() + " " + providerCredentials.getAccessKey() + ":" + caculateSignature(strMakeServiceCanonicalString, providerCredentials.getSecretKey()));
        } catch (UnsupportedEncodingException e) {
            throw new ServiceException(e);
        }
    }

    public final String makeServiceCanonicalString(String str, String str2, Map<String, String> map, String str3, List<String> list) throws UnsupportedEncodingException {
        List list2;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("\n");
        String lowerCase = "Date".toLowerCase();
        String lowerCase2 = "Content-Type".toLowerCase();
        String lowerCase3 = "Content-MD5".toLowerCase();
        String strHeaderPrefix = getIHeaders().headerPrefix();
        String strHeaderMetaPrefix = getIHeaders().headerMetaPrefix();
        TreeMap treeMap = new TreeMap();
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (key != null) {
                    String lowerCase4 = key.toLowerCase(Locale.getDefault());
                    if (lowerCase4.equals(lowerCase2) || lowerCase4.equals(lowerCase3) || lowerCase4.equals(lowerCase)) {
                        treeMap.put(lowerCase4, value);
                    } else if (lowerCase4.startsWith(strHeaderPrefix)) {
                        if (treeMap.containsKey(lowerCase4)) {
                            list2 = (List) treeMap.get(lowerCase4);
                        } else {
                            ArrayList arrayList = new ArrayList();
                            treeMap.put(lowerCase4, arrayList);
                            list2 = arrayList;
                        }
                        list2.add(value);
                    }
                }
            }
        }
        if (treeMap.containsKey(getIHeaders().dateHeader())) {
            treeMap.put(lowerCase, "");
        }
        if (str3 != null) {
            treeMap.put(lowerCase, str3);
        }
        if (!treeMap.containsKey(lowerCase2)) {
            treeMap.put(lowerCase2, "");
        }
        if (!treeMap.containsKey(lowerCase3)) {
            treeMap.put(lowerCase3, "");
        }
        for (Map.Entry entry2 : treeMap.entrySet()) {
            String str4 = (String) entry2.getKey();
            Object value2 = entry2.getValue();
            if (value2 instanceof List) {
                value2 = ServiceUtils.join((List<?>) value2, ",", true);
            } else if (value2 == null) {
                value2 = "";
            }
            if (str4.startsWith(strHeaderMetaPrefix)) {
                sb.append(str4);
                sb.append(':');
                sb.append(value2.toString().trim());
            } else if (str4.startsWith(strHeaderPrefix)) {
                sb.append(str4);
                sb.append(':');
                sb.append(value2);
            } else {
                sb.append(value2);
            }
            sb.append("\n");
        }
        int iIndexOf = str2.indexOf(63);
        boolean z = false;
        if (iIndexOf == -1) {
            sb.append(str2);
        } else {
            sb.append(str2.substring(0, iIndexOf));
        }
        if (iIndexOf >= 0) {
            TreeMap treeMap2 = new TreeMap();
            for (String str5 : str2.substring(iIndexOf + 1).split("&")) {
                String[] strArrSplit = str5.split("=");
                String strDecode = URLDecoder.decode(strArrSplit[0], "UTF-8");
                String strDecode2 = strArrSplit.length > 1 ? URLDecoder.decode(strArrSplit[1], "UTF-8") : null;
                if (list.contains(strDecode.toLowerCase()) || strDecode.toLowerCase().startsWith(strHeaderPrefix)) {
                    treeMap2.put(strDecode, strDecode2);
                }
            }
            if (treeMap2.size() > 0) {
                sb.append("?");
            }
            for (Map.Entry entry3 : treeMap2.entrySet()) {
                if (z) {
                    sb.append("&");
                }
                sb.append((String) entry3.getKey());
                if (entry3.getValue() != null) {
                    sb.append("=");
                    sb.append((String) entry3.getValue());
                }
                z = true;
            }
        }
        return sb.toString();
    }
}
