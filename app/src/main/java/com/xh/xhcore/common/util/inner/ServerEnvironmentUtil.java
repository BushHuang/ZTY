package com.xh.xhcore.common.util.inner;

import com.xh.xhcore.common.constant.ConstServerBaseUrlKt;
import com.xh.xhcore.common.oss.OssConfig;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\u0007\u001a\u00020\u0004H\u0007J\b\u0010\b\u001a\u00020\u0004H\u0007¨\u0006\t"}, d2 = {"Lcom/xh/xhcore/common/util/inner/ServerEnvironmentUtil;", "", "()V", "initFileServerEnvironment", "", "cpvdServerEnvironment", "", "initNonProductionEnvironment", "initProductionEnvironment", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ServerEnvironmentUtil {
    public static final ServerEnvironmentUtil INSTANCE = new ServerEnvironmentUtil();

    private ServerEnvironmentUtil() {
    }

    @Deprecated(message = "be refactored into below two method")
    @JvmStatic
    public static final void initFileServerEnvironment(int cpvdServerEnvironment) {
    }

    @JvmStatic
    public static final void initNonProductionEnvironment() {
        ConstServerBaseUrlKt.BASE_URL_FILE_SERVER = "http://192.168.10.93:8080";
        ConstServerBaseUrlKt.BASE_URL_FILE_SERVER_HOST = "192.168.10.93:8080";
        OssConfig.bucketNames = OssConfig.BUCKET_NAME_NON_RELEASE;
        OssConfig.ALI_AK_SK_HOST = "http://filesoss.xht.com";
    }

    @JvmStatic
    public static final void initProductionEnvironment() {
        ConstServerBaseUrlKt.BASE_URL_FILE_SERVER = "https://filesoss.yunzuoye.net";
        ConstServerBaseUrlKt.BASE_URL_FILE_SERVER_HOST = "filesoss.yunzuoye.net";
        OssConfig.bucketNames = OssConfig.BUCKET_NAME_RELEASE;
        OssConfig.ALI_AK_SK_HOST = "https://filesoss.yunzuoye.net";
    }
}
