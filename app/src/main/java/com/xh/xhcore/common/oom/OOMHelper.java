package com.xh.xhcore.common.oom;

import android.app.Application;
import com.kwai.koom.javaoom.KOOMInitializer;
import com.kwai.koom.javaoom.common.KLog;
import com.kwai.koom.javaoom.report.FileUploader;
import com.kwai.koom.javaoom.report.HeapReportUploader;
import com.xh.logutils.LogManager;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.statistic.XHEnvironment;
import com.xh.xhcore.common.util.XHAppUtil;
import com.xh.xhcore.common.util.XHFileUtil;
import com.xh.xhcore.common.util.XhPermissionUtil;
import java.io.File;
import org.json.JSONObject;

public class OOMHelper {
    private static final String TAG = "OOM";

    public static OOMHelper create() {
        return new OOMHelper();
    }

    static void lambda$init$0(File file) throws Throwable {
        KLog.d("OOM", "upload report file = " + file.getAbsolutePath());
        String file2String = XHFileUtil.readFile2String(file, "UTF-8");
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.putOpt("APMTag", "OOM").putOpt("APMContent", new JSONObject(file2String));
            LogManager.getInstance().uploadAPMLog(jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removeOtherVersionFile(String str, String str2) {
        File[] fileArrListFiles = new File(str).listFiles();
        if (fileArrListFiles != null) {
            for (File file : fileArrListFiles) {
                if (file.isDirectory() && !file.getName().equals(str2)) {
                    XHFileUtil.deleteDir(file);
                }
            }
        }
    }

    public void init(Application application) {
        if (!XHAppConfigProxy.getInstance().enableOOMTrace()) {
            LogUtils.d("KOOM is disable");
            return;
        }
        if (XhPermissionUtil.hasPermissions(application, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            String str = XHEnvironment.getExternalStorageDirectory().getAbsolutePath() + "/xuehai/oom/" + XHAppUtil.getPackageName();
            String versionName = XHAppUtil.getVersionName();
            removeOtherVersionFile(str, versionName);
            KOOMInitializer.create().setDebug(XHAppConfigProxy.getInstance().isAppBuildTypeDebug()).setKOOMRootDirectory(str + "/" + versionName).setHeapReportUploader(new HeapReportUploader() {
                @Override
                public boolean deleteWhenUploaded() {
                    return FileUploader.CC.$default$deleteWhenUploaded(this);
                }

                @Override
                public final void upload(File file) throws Throwable {
                    OOMHelper.lambda$init$0(file);
                }
            }).init(application);
        }
    }
}
