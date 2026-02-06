package com.xh.xhcore.common.helper;

import com.xh.xhcore.common.config.ServerNetworkConfig;
import com.xh.xhcore.common.config.XmConfig;
import com.xh.xhcore.common.util.MMKVUtil;
import com.xuehai.provider.core.db.CPVDRemoteConfig;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/xh/xhcore/common/helper/ServerNetworkConfigHelper;", "", "()V", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ServerNetworkConfigHelper {

    public static final Companion INSTANCE = new Companion(null);
    private static final String FILE_PATH = XmConfig.getDynamicServerConfigDir();
    public static final String KEY = "network";

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0007J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004H\u0007R\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/xh/xhcore/common/helper/ServerNetworkConfigHelper$Companion;", "", "()V", "FILE_PATH", "", "kotlin.jvm.PlatformType", "getFILE_PATH", "()Ljava/lang/String;", "KEY", "getServerConfig", "Lcom/xh/xhcore/common/config/ServerNetworkConfig;", "saveServerConfig", "", "configJson", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getFILE_PATH() {
            return ServerNetworkConfigHelper.FILE_PATH;
        }

        @JvmStatic
        public final ServerNetworkConfig getServerConfig() {
            ServerNetworkConfig serverNetworkConfig = (ServerNetworkConfig) CPVDRemoteConfig.getConfig("network", ServerNetworkConfig.class);
            return serverNetworkConfig == null ? new ServerNetworkConfig(0L, 0L, 0L, 0L, 0L, 0L, 0L, 127, null) : serverNetworkConfig;
        }

        @Deprecated(message = "通过cpvd进行数据共享")
        @JvmStatic
        public final void saveServerConfig(String configJson) {
            Intrinsics.checkNotNullParameter(configJson, "configJson");
            MMKVUtil.Companion companion = MMKVUtil.INSTANCE;
            String file_path = getFILE_PATH();
            Intrinsics.checkNotNullExpressionValue(file_path, "FILE_PATH");
            companion.put(file_path, "network", configJson);
        }
    }

    @JvmStatic
    public static final ServerNetworkConfig getServerConfig() {
        return INSTANCE.getServerConfig();
    }

    @Deprecated(message = "通过cpvd进行数据共享")
    @JvmStatic
    public static final void saveServerConfig(String str) {
        INSTANCE.saveServerConfig(str);
    }
}
