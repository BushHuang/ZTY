package com.xh.xhcore.common.test;

import com.xh.xhcore.common.statistic.XHEnvironment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u001f\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u00020\u00018\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/xh/xhcore/common/test/TestConst;", "", "()V", "APP_SERVICE_ID", "", "BASE_URL", "getBASE_URL", "()Ljava/lang/String;", "setBASE_URL", "(Ljava/lang/String;)V", "BASE_URL_YUNZUOYE_PRODUCTION", "BATCH_DOWNLOAD_DEFAULT_URL", "BOX_FILE_URL", "CONTROL_SERVICE_URL", "DONWLOAD_FILES_URL_FIRST", "FILES_SERVICE_ID", "FILE_SERVICE_ID", "HTTPS_FILE_URL", "KEY_APPLY", "KEY_CONTROL_APPLY", "LARGE_FILE_URL", "MMKV_ROOT_PATH", "MMKV_UNITTEST_PATH", "PUBLICK_SERVICE_ID", "ROOT_URL_PROD", "SMALL_FILE_URL", "TEST_LOCAL_PATH", "TEST_LOCK", "TEST_URL", "TEST_URL_ERROR", "TEST_URL_HTTPS_IP", "TEST_URL_HTTPS_PRODUCTION", "TEST_URL_REDIRECT", "UPLOAD_FILE_URL", "ZIP_UPLOAD", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TestConst {
    public static final String APP_SERVICE_ID = "SA102002";
    private static String BASE_URL = null;
    public static final String BASE_URL_YUNZUOYE_PRODUCTION = "http://ztp.yunzuoye.net";
    public static final String BATCH_DOWNLOAD_DEFAULT_URL = "";
    public static final String BOX_FILE_URL = "http://192.168.10.48/other/20190217/CA104001/82876a5a19a449ea9c1f3e180dd3ed7e.7z?2f1f5ee086b74b2c8bd9bd913a87bb2a";
    public static final String CONTROL_SERVICE_URL = "http://control.yunzuoye.net";
    public static final String DONWLOAD_FILES_URL_FIRST = "";
    public static final String FILES_SERVICE_ID = "SB103016";
    public static final String FILE_SERVICE_ID = "SB103003";
    public static final String HTTPS_FILE_URL = "https://xuehaifile.oss-cn-hangzhou.aliyuncs.com/XHSVADTKBSCECK/res/questionBank/20190212/89def492c179e439a01e67d622469583.100fen2";
    public static final String KEY_APPLY = "SA105004";
    public static final String KEY_CONTROL_APPLY = "SB103015";
    public static final String LARGE_FILE_URL = "http://xuehaifile.oss-cn-hangzhou.aliyuncs.com/xhtesst/D658D495986664E73E5FA43ACC4DD5D1.apk";
    public static final String PUBLICK_SERVICE_ID = "SB103001";
    public static final String ROOT_URL_PROD = "http://fiction.yunzuoye.net";
    public static final String SMALL_FILE_URL = "http://xhfs0.oss-cn-hangzhou.aliyuncs.com/xhtest/bussiness/20181109/cff988a0c73d4a309af43042f4aeab6e.jpg";
    public static final String TEST_URL = "http://ztp.yunzuoye.net/api/v1/platform/dateTime";
    public static final String TEST_URL_ERROR = "ws://sws.xht.com";
    public static final String TEST_URL_HTTPS_IP = "https://43.227.195.109/health";
    public static final String TEST_URL_HTTPS_PRODUCTION = "https://ztp.yunzuoye.net/api/v1/platform/dateTime";
    public static final String TEST_URL_REDIRECT = "http://ztp.xh.com/api/date/redirect";
    public static final String UPLOAD_FILE_URL = "http://filesoss.yunzuoye.net/XHFileServer/file/upload/CA103001";
    public static final String ZIP_UPLOAD = "http://filesoss.yunzuoye.net/XHFileServer/file/zip/upload//CA103001";
    public static final TestConst INSTANCE = new TestConst();
    public static final Object TEST_LOCK = new Object();
    public static final String TEST_LOCAL_PATH = Intrinsics.stringPlus(XHEnvironment.getExternalStorageDirectory().toString(), "/xuehai/xhcoretest/");
    public static final String MMKV_ROOT_PATH = Intrinsics.stringPlus(XHEnvironment.getExternalStorageDirectory().getAbsolutePath(), "/xuehai/unitTest");
    public static final String MMKV_UNITTEST_PATH = Intrinsics.stringPlus(XHEnvironment.getExternalStorageDirectory().getAbsolutePath(), "/xuehai/unitTest/unitTest");

    private TestConst() {
    }

    public final String getBASE_URL() {
        return BASE_URL;
    }

    public final void setBASE_URL(String str) {
        BASE_URL = str;
    }
}
