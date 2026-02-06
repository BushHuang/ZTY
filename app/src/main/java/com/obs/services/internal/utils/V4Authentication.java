package com.obs.services.internal.utils;

import com.obs.services.internal.Constants;
import com.obs.services.internal.ServiceException;
import com.obs.services.internal.security.ProviderCredentials;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class V4Authentication {
    public static final String content_sha256 = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";
    private String ak;
    private String nowISOtime;
    private String region;
    private String sk;

    protected V4Authentication() {
    }

    public static String byteToHex(byte[] bArr) {
        return ServiceUtils.toHex(bArr);
    }

    public static String caculateSignature(String str, String str2, String str3) throws Exception {
        return byteToHex(hmac_sha256Encode(hmac_sha256Encode(hmac_sha256Encode(hmac_sha256Encode(hmac_sha256Encode(("AWS4" + str3).getBytes("UTF-8"), str2), "region"), "s3"), "aws4_request"), str));
    }

    private String getCanonicalRequest(String str, String str2, List<String> list) throws ServiceException {
        List<String> canonicalURIAndQuery = getCanonicalURIAndQuery(str2);
        return str + "\n" + canonicalURIAndQuery.get(0) + "\n" + canonicalURIAndQuery.get(1) + "\n" + list.get(1) + "\n" + list.get(0) + "\ne3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";
    }

    private List<String> getCanonicalURIAndQuery(String str) throws ServiceException {
        String[] strArrSplit = str.split("[?]");
        String str2 = "";
        boolean z = false;
        String str3 = strArrSplit.length > 0 ? "" + strArrSplit[0] : "";
        if (strArrSplit.length > 1) {
            String[] strArrSplit2 = strArrSplit[1].split("[&]");
            TreeMap treeMap = new TreeMap();
            for (String str4 : strArrSplit2) {
                String[] strArrSplit3 = str4.split("[=]");
                treeMap.put(strArrSplit3[0], strArrSplit3.length > 1 ? strArrSplit3[1] : "");
            }
            for (Map.Entry entry : treeMap.entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                if (z) {
                    str2 = str2 + "&";
                }
                str2 = str2 + key.toString() + "=" + value.toString();
                z = true;
            }
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(str3);
        arrayList.add(str2);
        return arrayList;
    }

    private String getScope() {
        return this.nowISOtime.split("T")[0] + "/" + this.region + "/s3/aws4_request";
    }

    private List<String> getSignedAndCanonicalHeaders(Map<String, String> map) {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        TreeMap treeMap = new TreeMap();
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (key != null && !"".equals(key) && !"connection".equalsIgnoreCase(key)) {
                    String lowerCase = key.toLowerCase(Locale.getDefault());
                    List arrayList2 = (List) treeMap.get(lowerCase);
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList();
                        treeMap.put(lowerCase, arrayList2);
                    }
                    arrayList2.add(value);
                }
            }
            boolean z = false;
            for (Map.Entry entry2 : treeMap.entrySet()) {
                String str = (String) entry2.getKey();
                List<String> list = (List) entry2.getValue();
                if (z) {
                    sb.append(";");
                }
                z = true;
                sb.append(str);
                for (String str2 : list) {
                    sb2.append(str);
                    sb2.append(":");
                    sb2.append(str2);
                    sb2.append("\n");
                }
            }
        }
        arrayList.add(sb.toString());
        arrayList.add(sb2.toString());
        return arrayList;
    }

    private byte[] getSigningKey() throws ServiceException {
        try {
            return hmac_sha256Encode(hmac_sha256Encode(hmac_sha256Encode(hmac_sha256Encode(("AWS4" + this.sk).getBytes("UTF-8"), this.nowISOtime.split("[T]")[0]), this.region), "s3"), "aws4_request");
        } catch (Exception e) {
            throw new ServiceException("Get sign string for v4 aurhentication error", e);
        }
    }

    public static byte[] hmac_sha256Encode(byte[] bArr, String str) throws IllegalStateException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(bArr, "HmacSHA256"));
        return mac.doFinal(str.getBytes("UTF-8"));
    }

    public static IAuthentication makeServiceCanonicalString(String str, Map<String, String> map, String str2, ProviderCredentials providerCredentials, Date date) throws ServiceException {
        V4Authentication v4Authentication = new V4Authentication();
        v4Authentication.setAk(providerCredentials.getAccessKey());
        v4Authentication.setSk(providerCredentials.getSecretKey());
        v4Authentication.setRegion(providerCredentials.getRegion());
        v4Authentication.setNowISOTime(date);
        List<String> signedAndCanonicalHeaders = v4Authentication.getSignedAndCanonicalHeaders(map);
        String scope = v4Authentication.getScope();
        try {
            String canonicalRequest = v4Authentication.getCanonicalRequest(str, str2, signedAndCanonicalHeaders);
            String str3 = "AWS4-HMAC-SHA256\n" + v4Authentication.nowISOtime + "\n" + scope + "\n" + byteToHex(sha256encode(canonicalRequest));
            return new DefaultAuthentication(canonicalRequest, str3, "AWS4-HMAC-SHA256 Credential=" + v4Authentication.ak + "/" + scope + ",SignedHeaders=" + signedAndCanonicalHeaders.get(0) + ",Signature=" + byteToHex(hmac_sha256Encode(v4Authentication.getSigningKey(), str3)));
        } catch (Exception e) {
            throw new ServiceException("has an err when V4 aurhentication ", e);
        }
    }

    private void setNowISOTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
        simpleDateFormat.setTimeZone(Constants.GMT_TIMEZONE);
        this.nowISOtime = simpleDateFormat.format(date);
    }

    public static byte[] sha256encode(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return MessageDigest.getInstance("SHA-256").digest(str.getBytes("UTF-8"));
    }

    public String getAk() {
        return this.ak;
    }

    public String getRegion() {
        return this.region;
    }

    public String getSk() {
        return this.sk;
    }

    public void setAk(String str) {
        this.ak = str;
    }

    public void setRegion(String str) {
        this.region = str;
    }

    public void setSk(String str) {
        this.sk = str;
    }
}
