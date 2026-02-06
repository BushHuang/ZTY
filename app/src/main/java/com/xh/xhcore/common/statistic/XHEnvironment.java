package com.xh.xhcore.common.statistic;

import android.os.Environment;
import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import java.io.File;

public class XHEnvironment {
    public static File getExternalStorageDirectory() {
        return XHAppConfigProxy.getInstance().isInside() ? XhBaseApplication.getXhBaseApplication().getFilesDir() : Environment.getExternalStorageDirectory();
    }

    public static String getUpLoadZipPath() {
        return XHAppConfigProxy.getInstance().isInside() ? XhBaseApplication.getXhBaseApplication().getFilesDir().getAbsolutePath() : "/sdcard/xuehai";
    }
}
