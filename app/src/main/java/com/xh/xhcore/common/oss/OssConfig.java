package com.xh.xhcore.common.oss;

import android.content.Context;
import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.OSSLogToFileUtils;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.model.ObjectMetadata;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.http.strategy.xh.request.XHRequestUtil;

public class OssConfig {
    public static final String ALI_AK_SK_HOST_NON_RELEASE = "http://filesoss.xht.com";
    public static final String ALI_AK_SK_HOST_RELEASE = "https://filesoss.yunzuoye.net";
    public static final String ALI_HOST = "oss-cn-hangzhou.aliyuncs.com";
    public static final String[] BUCKET_NAME_RELEASE;
    public static final String HOST_HTTPS_HEAD = "https://";
    public static final String HOST_HTTP_HEAD = "http://";
    public static final String HOST_START_INDICATOR = "://";
    public static final String akPrivateKey = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAtP9m87y3AnwhINdP4ehsK1ho9Sd6K4AnNGRgBmtjZMB0pC3PRTPFI9XiWMReBkR7p-fbHtKAFoaKOuSE9wq5pwIDAQABAkB1AT4OhopQIupPjqDnSxbSFn8TLAgAIwI1DkY9ICLKt78uR7aqm6v7RBRTk0TD9aFD8Zr1vsIHKRkX3Wa6wClBAiEA8GHw3b6IgiuRCMs5AFA0dBDqs72zcpjE4UNcGfnkzjcCIQDAwcRy3AlaZbMhH5JlxtKFVqeANZFtlLMMKw-fhas4EQIhALxlxoXEYvDCfHVL8WzRb4rYB1q__vh9yzBRvsmNFUqZAiAg5PBRaZtvAcv_6xNgqv1iI9eR2ZJn0Oh9Oxv_bSA7AQIhAKu-TATFVjkIpA03oolot5SKq3As_EfGep7UJ-QD5bkj";
    public static String[] bucketNames = null;
    public static final String skPrivateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAO2toogEhPsijqGDuh4AkwO2CadGdR3ZMkDUbQ_u5YMl-x61TLcGg8CSPNQ7PYbVVdx6CAgQCN6MM0SCZQzcr_bNL7UfJ7X0Ml2RSMhSfsZexGXsqF5mikmS52Yan40WYCpbaJrt735eVg-xEN5t1mDzT78g8w0EFWvzlju6n-BtAgMBAAECgYEAgDOGVcPKTwPm12wMOh6pLflE37t7dKwxFRL_-PYtSj50Y637QUX8TyUSNo_1sgLkm1YfeunbPt90z-FdzEBFkNoerkfSkCdBLARF3O3fa5IyevLzthRhBNU45z7-4gPqLGLBQsJ-HEbWCdAZG6h9L4Nvvr736ydjn1hRdibdbekCQQD6Kt9fQnucg5Bo3dhEFWWBBSBoW3Cc9KmQWn52PIj1EDsoonXUS9I7bIAKBNjxWUkBqugipyDvMwiEJQ--X2izAkEA8zg4aL2ddL34nPRomzhPv8tfJIPJKEzV40DCri1GL0XwPARmR1uyeTCOQteBUzuc0C1n1wp0b7w0OAHJ2w7iXwJAJf8RAkT0xWbuLh9T07N0QU5oB7GrvwsiHfzArHCxVJdshNV4FBCYE9J8-AiYcr_e6NPugiTkSbB0oK2IYJ_ZJQJBAMl4-MFK0POzqg3aKAzsSKOHw_hrcmZ_v23QZ3QA7PKb7LTKsC7MNrtNDYsxGV_pMa0A79OiRXGJZXg_gwaC_eECQBUGnpD6Vihvoj9D-rR75ld8G9tWEUdXbFtGy8fEduyMfcOIBDNN1AxfiDd68cb71FvTetUnmXfs5YEaUCYhDWg=";
    public static final String[] BUCKET_NAME_NON_RELEASE = {"xhfs0-staging"};
    public static String ALI_AK_SK_HOST = "https://filesoss.yunzuoye.net";
    private static volatile int bucketTimes = 0;
    private static int CONNECTION_TIMEOUT = 15000;
    private static int SOCKET_TIMEOUT = 15000;
    private static int MAX_CONCURRENT_REQUEST = 10;
    private static int MAX_ERROR_RETRY = 2;

    static {
        String[] strArr = {"xhfs0", "xhfs1", "xhfs2", "xhfs3", "xhfs4", "xhfs5", "xhfs6", "xhfs7", "xhfs8", "xhfs9"};
        BUCKET_NAME_RELEASE = strArr;
        bucketNames = strArr;
    }

    public static synchronized String getBucketName() {
        String str;
        if (bucketTimes < 0 || bucketTimes >= bucketNames.length) {
            bucketTimes = 0;
        }
        String[] strArr = bucketNames;
        int i = bucketTimes;
        bucketTimes = i + 1;
        str = strArr[i % bucketNames.length];
        LogUtils.d("use bucketName: " + str);
        return str;
    }

    public static PutObjectRequest newPutObjectRequest(String str, String str2, String str3) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setHeader("x-oss-forbid-overwrite", "true");
        return new PutObjectRequest(str, str2, str3, objectMetadata);
    }

    public OSS getOss(Context context, String str, String str2, String str3) {
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setConnectionTimeout(CONNECTION_TIMEOUT);
        clientConfiguration.setSocketTimeout(SOCKET_TIMEOUT);
        clientConfiguration.setMaxConcurrentRequest(MAX_CONCURRENT_REQUEST);
        clientConfiguration.setMaxErrorRetry(MAX_ERROR_RETRY);
        clientConfiguration.setUserAgentMark(XHRequestUtil.getDefaultUserAgent(null));
        OSSLogToFileUtils.getInstance().setUseSdCard(false);
        return new OSSClient(context, "oss-cn-hangzhou.aliyuncs.com", new OSSStsTokenCredentialProvider(str, str2, str3), clientConfiguration);
    }

    public OssConfig setConnectionTimeOut(int i) {
        CONNECTION_TIMEOUT = i;
        return this;
    }

    public OssConfig setMaxConcurrentRequest(int i) {
        MAX_CONCURRENT_REQUEST = i;
        return this;
    }

    public OssConfig setMaxErrorRetry(int i) {
        MAX_ERROR_RETRY = i;
        return this;
    }

    public OssConfig setSocketTimeOut(int i) {
        SOCKET_TIMEOUT = i;
        return this;
    }
}
