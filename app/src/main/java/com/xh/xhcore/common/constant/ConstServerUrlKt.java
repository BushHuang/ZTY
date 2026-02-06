package com.xh.xhcore.common.constant;

import com.xh.xhcore.common.config.XHAppConfigProxy;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u00020\u00018\u0006X\u0087\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0002\u001a\u00020\u00018\u0006X\u0087\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0003\u001a\u00020\u00018\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0004"}, d2 = {"FILE_SERVER_DOWNLOAD_ZIP", "", "FILE_SERVER_UPLOAD", "FILE_SERVER_UPLOAD_ZIP", "xhcore_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class ConstServerUrlKt {
    public static final String FILE_SERVER_UPLOAD_ZIP = ConstServerBaseUrlKt.BASE_URL_FILE_SERVER + "/XHFileServer/file/zip/upload/" + XHAppConfigProxy.getInstance().getAppId();
    public static final String FILE_SERVER_UPLOAD = ConstServerBaseUrlKt.BASE_URL_FILE_SERVER + "/XHFileServer/file/upload/" + XHAppConfigProxy.getInstance().getAppId();
    public static final String FILE_SERVER_DOWNLOAD_ZIP = ConstServerBaseUrlKt.BASE_URL_FILE_SERVER + "/XHFileServer/file/batch/download/" + XHAppConfigProxy.getInstance().getAppId();
}
