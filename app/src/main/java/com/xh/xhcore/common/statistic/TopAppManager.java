package com.xh.xhcore.common.statistic;

import com.google.gson.JsonObject;
import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.util.XHAppUtil;
import com.xh.xhcore.common.util.XHFileUtil;
import com.xh.xhcore.common.util.XhPermissionUtil;
import java.io.File;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0006"}, d2 = {"Lcom/xh/xhcore/common/statistic/TopAppManager;", "", "()V", "updateTopApp", "", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TopAppManager {

    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<TopAppManager> instance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<TopAppManager>() {
        @Override
        public final TopAppManager invoke() {
            return new TopAppManager();
        }
    });

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R!\u0010\u0003\u001a\u00020\u00048FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\b\u0010\t\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/xh/xhcore/common/statistic/TopAppManager$Companion;", "", "()V", "instance", "Lcom/xh/xhcore/common/statistic/TopAppManager;", "getInstance$annotations", "getInstance", "()Lcom/xh/xhcore/common/statistic/TopAppManager;", "instance$delegate", "Lkotlin/Lazy;", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public static void getInstance$annotations() {
        }

        public final TopAppManager getInstance() {
            return (TopAppManager) TopAppManager.instance$delegate.getValue();
        }
    }

    public static final TopAppManager getInstance() {
        return INSTANCE.getInstance();
    }

    private static final void m36updateTopApp$lambda0(String str, JsonObject jsonObject) {
        Intrinsics.checkNotNullParameter(jsonObject, "$jsonObject");
        String strStringPlus = Intrinsics.stringPlus(XHEnvironment.getExternalStorageDirectory().toString(), "/xuehai/keep/");
        File file = new File(strStringPlus);
        if (file.exists() || file.mkdirs()) {
            Boolean bool = DataCollectionUtil.isOnTop;
            Intrinsics.checkNotNullExpressionValue(bool, "isOnTop");
            if (bool.booleanValue()) {
                LogUtils.INSTANCE.i(Intrinsics.stringPlus("记录应用到前台 : ", str));
                XHFileUtil.writeFileFromString(Intrinsics.stringPlus(strStringPlus, "StatisticsKeep.log"), jsonObject.toString(), false);
            }
        }
    }

    public final void updateTopApp() {
        if (XhPermissionUtil.hasPermissions(XhBaseApplication.mContext, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            final JsonObject jsonObject = new JsonObject();
            final String packageName = XHAppUtil.getPackageName();
            jsonObject.addProperty("packageName", packageName);
            ThreadManager.getDISK_IO().execute(new Runnable() {
                @Override
                public final void run() {
                    TopAppManager.m36updateTopApp$lambda0(packageName, jsonObject);
                }
            });
        }
    }
}
