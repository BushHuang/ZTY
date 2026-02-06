package com.xuehai.platform;

import android.content.Context;
import android.content.Intent;
import com.xuehai.provider.core.CPVDProcess;
import com.xuehai.provider.core.db.CPVDClientData;
import com.xuehai.provider.core.dto.CPVDClient;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J.\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bJ\u0016\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\u000e"}, d2 = {"Lcom/xuehai/platform/AppManager;", "", "()V", "install", "", "context", "Landroid/content/Context;", "packageName", "", "versionCode", "", "url", "md5", "uninstall", "library_release"}, k = 1, mv = {1, 1, 15})
public final class AppManager {
    public static final AppManager INSTANCE = new AppManager();

    private AppManager() {
    }

    public final void install(Context context, String packageName, long versionCode, String url, String md5) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(md5, "md5");
        Intent intent = new Intent("com.xuehai.cpvd.app.install");
        intent.putExtra("packageName", packageName);
        intent.putExtra("versionCode", versionCode);
        intent.putExtra("url", url);
        intent.putExtra("md5", md5);
        CPVDClient clientInfo = CPVDClientData.getClientInfo(context);
        if (clientInfo != null) {
            intent.setPackage(clientInfo.getPackageName());
            CPVDProcess.sendBroadcastCompat(context, intent);
        }
    }

    public final void uninstall(Context context, String packageName) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        Intent intent = new Intent("com.xuehai.cpvd.app.uninstall");
        intent.putExtra("packageName", packageName);
        CPVDClient clientInfo = CPVDClientData.getClientInfo(context);
        if (clientInfo != null) {
            intent.setPackage(clientInfo.getPackageName());
            CPVDProcess.sendBroadcastCompat(context, intent);
        }
    }
}
