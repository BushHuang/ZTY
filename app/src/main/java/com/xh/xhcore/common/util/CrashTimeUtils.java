package com.xh.xhcore.common.util;

import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.statistic.XHEnvironment;
import java.io.File;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/xh/xhcore/common/util/CrashTimeUtils;", "", "()V", "readCrashTime", "", "writeCrashTime", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CrashTimeUtils {
    public static final CrashTimeUtils INSTANCE = new CrashTimeUtils();

    private CrashTimeUtils() {
    }

    public final long readCrashTime() {
        try {
            if (!XhPermissionUtil.hasPermissions(XhBaseApplication.mContext, "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE")) {
                return -1L;
            }
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format("%s/crash#%s#.log", Arrays.copyOf(new Object[]{Intrinsics.stringPlus(XHEnvironment.getExternalStorageDirectory().toString(), "/xuehai/log/crash/"), XhBaseApplication.sPackageName}, 2));
            Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
            File file = new File(str);
            if (file.exists()) {
                return Long.parseLong(FilesKt.readText$default(file, null, 1, null));
            }
            return -1L;
        } catch (Exception e) {
            e.printStackTrace();
            return -1L;
        }
    }

    public final boolean writeCrashTime() {
        try {
            if (!XhPermissionUtil.hasPermissions(XhBaseApplication.mContext, "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE")) {
                return false;
            }
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format("%s/crash#%s#.log", Arrays.copyOf(new Object[]{Intrinsics.stringPlus(XHEnvironment.getExternalStorageDirectory().toString(), "/xuehai/log/crash/"), XhBaseApplication.sPackageName}, 2));
            Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
            XHFileUtil.writeFileFromString(str, System.currentTimeMillis() + "", true);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
