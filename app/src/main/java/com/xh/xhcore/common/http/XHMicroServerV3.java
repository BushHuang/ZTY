package com.xh.xhcore.common.http;

import com.xh.xhcore.common.config.IXHAppConfig;
import com.xh.xhcore.common.config.XmConfig;
import com.xh.xhcore.common.data.XmData;
import com.xh.xhcore.common.http.BaseMicroServer;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0005¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004H\u0014¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\u0005H\u0014J\b\u0010\b\u001a\u00020\u0005H\u0014J\u0010\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0014J\b\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000f"}, d2 = {"Lcom/xh/xhcore/common/http/XHMicroServerV3;", "Lcom/xh/xhcore/common/http/XHMicroServerV2;", "()V", "getMicroServerUrl", "", "", "()[Ljava/lang/String;", "getMicroServiceDefaultUrl", "getRequestUrlWithRESTFul", "getServerKey", "serverUrlUnify", "Lcom/xh/xhcore/common/http/BaseMicroServer$ServerUrlUnify;", "getXmConfigInstance", "Lcom/xh/xhcore/common/config/XmConfig;", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class XHMicroServerV3 extends XHMicroServerV2 {

    public static final Companion INSTANCE = new Companion(null);
    public static final String PATH_RESTFUL_V3 = "/api/v3/pub/microServer/list/";
    private static final String RESETFul_MICRO_SERVICE_URL = Intrinsics.stringPlus(BaseMicroServer.INSTANCE.getRESETFul_MICRO_SERVICE_HOST(), PATH_RESTFUL_V3);

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0007R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/xh/xhcore/common/http/XHMicroServerV3$Companion;", "", "()V", "PATH_RESTFUL_V3", "", "RESETFul_MICRO_SERVICE_URL", "makeMicroServerV3Key", "serverType", "classId", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final String makeMicroServerV3Key(String serverType, int classId) {
            Intrinsics.checkNotNullParameter(serverType, "serverType");
            if (classId == 0) {
                return serverType;
            }
            return serverType + '_' + classId;
        }
    }

    @JvmStatic
    public static final String makeMicroServerV3Key(String str, int i) {
        return INSTANCE.makeMicroServerV3Key(str, i);
    }

    @Override
    protected String[] getMicroServerUrl() {
        return XmData.getMicroServerUrl(IXHAppConfig.MicroServerVersion.VERSION3);
    }

    @Override
    protected String getMicroServiceDefaultUrl() {
        return RESETFul_MICRO_SERVICE_URL;
    }

    @Override
    protected String getRequestUrlWithRESTFul() {
        String requestUrlWithRESTFul = super.getRequestUrlWithRESTFul();
        Intrinsics.checkNotNullExpressionValue(requestUrlWithRESTFul, "super.getRequestUrlWithRESTFul()");
        return requestUrlWithRESTFul;
    }

    @Override
    protected String getServerKey(BaseMicroServer.ServerUrlUnify serverUrlUnify) {
        Intrinsics.checkNotNullParameter(serverUrlUnify, "serverUrlUnify");
        return INSTANCE.makeMicroServerV3Key(serverUrlUnify.getServerType(), serverUrlUnify.getClassId());
    }

    @Override
    public XmConfig getXmConfigInstance() {
        XmConfig xmConfigNewInstanceV3 = XmConfig.newInstanceV3();
        Intrinsics.checkNotNullExpressionValue(xmConfigNewInstanceV3, "newInstanceV3()");
        return xmConfigNewInstanceV3;
    }
}
