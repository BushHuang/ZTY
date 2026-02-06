package com.xh.xhcore.common.http;

import android.text.TextUtils;
import com.xh.xhcore.common.config.IXHAppConfig;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.config.XmConfig;
import com.xh.xhcore.common.http.MicroServerResponse;
import com.xh.xhcore.common.http.XHRequestCallBack;
import com.xh.xhcore.common.http.microserver.v3.ClassServerTypesBean;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.util.StringUtil;
import com.xh.xhcore.common.util.XmJsonUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u0000 #2\u00020\u0001:\u0003#$%B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005H\u0002J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0006\u0010\u0014\u001a\u00020\u0015J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0011\u001a\u00020\u0005H\u0016J\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0011\u001a\u00020\u0005H\u0004J\u0012\u0010\u0018\u001a\u00020\u00152\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u001aJ\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0011\u001a\u00020\u0005H\u0016J\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0005J\u0016\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013J\b\u0010\u001d\u001a\u00020\u001eH&J\u0012\u0010\u001f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005H\u0016J\u0018\u0010\u001f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0012\u001a\u00020\u0013J\u0018\u0010 \u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u0006H\u0004J\u0014\u0010\"\u001a\u00020\u00002\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nR \u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR*\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006&"}, d2 = {"Lcom/xh/xhcore/common/http/BaseMicroServer;", "Lcom/xh/xhcore/common/http/IMicroServer;", "()V", "URL_MAP", "", "", "Lcom/xh/xhcore/common/http/BaseMicroServer$ServerUrlUnify;", "getURL_MAP", "()Ljava/util/Map;", "<set-?>", "", "Lcom/xh/xhcore/common/http/microserver/v3/ClassServerTypesBean;", "classServers", "getClassServers", "()Ljava/util/List;", "cancelIp", "", "serverType", "classId", "", "clearMicroServerMemoryCache", "", "getActualIp", "getMicroServerCache", "getMicroServiceIPList", "callBack", "Lcom/xh/xhcore/common/http/XHRequestCallBack$HttpCallBack;", "getNextIp", "getServerUrl", "getXmConfigInstance", "Lcom/xh/xhcore/common/config/XmConfig;", "requestAgain", "saveMicroServerCache", "serverUrlUnify", "setClassServers", "Companion", "MicroServerEntityUnify", "ServerUrlUnify", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class BaseMicroServer implements IMicroServer {

    public static final Companion INSTANCE = new Companion(null);
    private static final String RESETFul_MICRO_SERVICE_HOST = "http://ms.yunzuoye.net";
    private static volatile BaseMicroServer sMicroServer;
    private final Map<String, ServerUrlUnify> URL_MAP = new HashMap();
    private List<? extends ClassServerTypesBean> classServers = new ArrayList();

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000e\u001a\u00020\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u0004H\u0002J\b\u0010\u0010\u001a\u00020\tH\u0017R\u001c\u0010\u0003\u001a\u00020\u00048\u0016X\u0097D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0011"}, d2 = {"Lcom/xh/xhcore/common/http/BaseMicroServer$Companion;", "", "()V", "RESETFul_MICRO_SERVICE_HOST", "", "getRESETFul_MICRO_SERVICE_HOST$annotations", "getRESETFul_MICRO_SERVICE_HOST", "()Ljava/lang/String;", "sMicroServer", "Lcom/xh/xhcore/common/http/BaseMicroServer;", "getSMicroServer", "()Lcom/xh/xhcore/common/http/BaseMicroServer;", "setSMicroServer", "(Lcom/xh/xhcore/common/http/BaseMicroServer;)V", "getDefaultUrl", "serverType", "getInstance", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final String getDefaultUrl(String serverType) {
            String str = XHAppConfigProxy.getInstance().getMicroServerDefaultMap().get(serverType);
            LogUtils.Companion companion = LogUtils.INSTANCE;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str2 = String.format("本地不存在%s服务的历史配置地址, 使用对应默认地址 : %s", Arrays.copyOf(new Object[]{serverType, str}, 2));
            Intrinsics.checkNotNullExpressionValue(str2, "format(format, *args)");
            companion.w(str2);
            return str == null ? "" : str;
        }

        @JvmStatic
        public static void getRESETFul_MICRO_SERVICE_HOST$annotations() {
        }

        @JvmStatic
        public BaseMicroServer getInstance() {
            if (getSMicroServer() == null) {
                synchronized (XHMicroServer.class) {
                    if (BaseMicroServer.INSTANCE.getSMicroServer() == null) {
                        if (XHAppConfigProxy.getInstance().getMicroServerVersion() == IXHAppConfig.MicroServerVersion.VERSION1) {
                            BaseMicroServer.INSTANCE.setSMicroServer(new XHMicroServer());
                        } else if (XHAppConfigProxy.getInstance().getMicroServerVersion() == IXHAppConfig.MicroServerVersion.VERSION2) {
                            BaseMicroServer.INSTANCE.setSMicroServer(new XHMicroServerV2());
                        } else {
                            BaseMicroServer.INSTANCE.setSMicroServer(new XHMicroServerV3());
                        }
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            BaseMicroServer sMicroServer = getSMicroServer();
            Intrinsics.checkNotNull(sMicroServer);
            return sMicroServer;
        }

        public String getRESETFul_MICRO_SERVICE_HOST() {
            return BaseMicroServer.RESETFul_MICRO_SERVICE_HOST;
        }

        protected final BaseMicroServer getSMicroServer() {
            return BaseMicroServer.sMicroServer;
        }

        protected final void setSMicroServer(BaseMicroServer baseMicroServer) {
            BaseMicroServer.sMicroServer = baseMicroServer;
        }
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u000e\u0018\u00002\u00020\u0001:\u000245B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u0006\u00103\u001a\u00020\rR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u00020\r8FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u00020\r8FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0018\"\u0004\b\u001d\u0010\u001aR\u001a\u0010\u001e\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0018\"\u0004\b \u0010\u001aR\u001a\u0010!\u001a\u00020\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R \u0010'\u001a\b\u0012\u0004\u0012\u00020\u00160(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001a\u0010-\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u0018\"\u0004\b/\u0010\u001aR\u001a\u00100\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u000f\"\u0004\b2\u0010\u0011¨\u00066"}, d2 = {"Lcom/xh/xhcore/common/http/BaseMicroServer$MicroServerEntityUnify;", "", "microServerDto", "Lcom/xh/xhcore/common/http/MicroServerResponse$MicroServerDto;", "(Lcom/xh/xhcore/common/http/MicroServerResponse$MicroServerDto;)V", "()V", "addressType", "Lcom/xh/xhcore/common/http/BaseMicroServer$MicroServerEntityUnify$AddressType;", "getAddressType", "()Lcom/xh/xhcore/common/http/BaseMicroServer$MicroServerEntityUnify$AddressType;", "setAddressType", "(Lcom/xh/xhcore/common/http/BaseMicroServer$MicroServerEntityUnify$AddressType;)V", "dirLocation", "", "getDirLocation", "()Ljava/lang/String;", "setDirLocation", "(Ljava/lang/String;)V", "domainOrIp", "getDomainOrIp", "setDomainOrIp", "index", "", "getIndex", "()I", "setIndex", "(I)V", "mode", "getMode", "setMode", "port", "getPort", "setPort", "protocol", "Lcom/xh/xhcore/common/http/BaseMicroServer$MicroServerEntityUnify$Protocol;", "getProtocol", "()Lcom/xh/xhcore/common/http/BaseMicroServer$MicroServerEntityUnify$Protocol;", "setProtocol", "(Lcom/xh/xhcore/common/http/BaseMicroServer$MicroServerEntityUnify$Protocol;)V", "schList", "", "getSchList", "()Ljava/util/List;", "setSchList", "(Ljava/util/List;)V", "serverId", "getServerId", "setServerId", "serverType", "getServerType", "setServerType", "getUrl", "AddressType", "Protocol", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class MicroServerEntityUnify {
        private AddressType addressType;
        private String dirLocation;
        private String domainOrIp;
        private int index;
        private int mode;
        private int port;
        private Protocol protocol;
        private List<Integer> schList;
        private int serverId;
        private String serverType;

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/xh/xhcore/common/http/BaseMicroServer$MicroServerEntityUnify$AddressType;", "", "(Ljava/lang/String;I)V", "IP", "DOMAIN", "NONE", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
        public enum AddressType {
            IP,
            DOMAIN,
            NONE
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\r\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000e"}, d2 = {"Lcom/xh/xhcore/common/http/BaseMicroServer$MicroServerEntityUnify$Protocol;", "", "(Ljava/lang/String;I)V", "HTTP", "HTTPS", "TELNET", "FTP", "SMTP", "DNS", "TCP", "UDP", "WS", "WSS", "NONE", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
        public enum Protocol {
            HTTP,
            HTTPS,
            TELNET,
            FTP,
            SMTP,
            DNS,
            TCP,
            UDP,
            WS,
            WSS,
            NONE
        }

        public MicroServerEntityUnify() {
            this.addressType = AddressType.NONE;
            this.protocol = Protocol.NONE;
            this.dirLocation = "";
            this.domainOrIp = "";
            this.schList = new ArrayList();
            this.serverType = "";
        }

        public MicroServerEntityUnify(MicroServerResponse.MicroServerDto microServerDto) {
            this();
            Intrinsics.checkNotNullParameter(microServerDto, "microServerDto");
            String dirLocation = microServerDto.getDirLocation();
            Intrinsics.checkNotNullExpressionValue(dirLocation, "microServerDto.dirLocation");
            this.dirLocation = dirLocation;
            String domainOrIp = microServerDto.getDomainOrIp();
            Intrinsics.checkNotNullExpressionValue(domainOrIp, "microServerDto.domainOrIp");
            this.domainOrIp = domainOrIp;
            this.port = microServerDto.getPort();
            this.index = microServerDto.getIndex();
            List<Integer> schList = microServerDto.getSchList();
            Intrinsics.checkNotNullExpressionValue(schList, "microServerDto.schList");
            this.schList = schList;
            this.serverId = microServerDto.getServerId();
            String serverType = microServerDto.getServerType();
            Intrinsics.checkNotNullExpressionValue(serverType, "microServerDto.serverType");
            this.serverType = serverType;
            this.mode = microServerDto.getMode();
        }

        public final AddressType getAddressType() {
            return this.addressType;
        }

        public final String getDirLocation() {
            String strRemoveAllStartBackslash = StringUtil.removeAllStartBackslash(this.dirLocation);
            Intrinsics.checkNotNullExpressionValue(strRemoveAllStartBackslash, "removeAllStartBackslash(field)");
            this.dirLocation = strRemoveAllStartBackslash;
            return strRemoveAllStartBackslash;
        }

        public final String getDomainOrIp() {
            String strRemoveAllEndBackslash = StringUtil.removeAllEndBackslash(this.domainOrIp);
            Intrinsics.checkNotNullExpressionValue(strRemoveAllEndBackslash, "removeAllEndBackslash(field)");
            this.domainOrIp = strRemoveAllEndBackslash;
            return strRemoveAllEndBackslash;
        }

        public final int getIndex() {
            return this.index;
        }

        public final int getMode() {
            return this.mode;
        }

        public final int getPort() {
            return this.port;
        }

        public final Protocol getProtocol() {
            return this.protocol;
        }

        public final List<Integer> getSchList() {
            return this.schList;
        }

        public final int getServerId() {
            return this.serverId;
        }

        public final String getServerType() {
            return this.serverType;
        }

        public final String getUrl() {
            String domainOrIp;
            if (this.port > 0) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                domainOrIp = String.format("%s:%s", Arrays.copyOf(new Object[]{getDomainOrIp(), Integer.valueOf(this.port)}, 2));
                Intrinsics.checkNotNullExpressionValue(domainOrIp, "format(format, *args)");
            } else {
                domainOrIp = getDomainOrIp();
            }
            if (!TextUtils.isEmpty(getDirLocation())) {
                domainOrIp = domainOrIp + '/' + getDirLocation();
            }
            String str = domainOrIp;
            if (TextUtils.isEmpty(str) || StringsKt.contains$default((CharSequence) str, (CharSequence) "://", false, 2, (Object) null) || this.protocol == Protocol.NONE) {
                return domainOrIp;
            }
            StringBuilder sb = new StringBuilder();
            String lowerCase = this.protocol.name().toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
            sb.append(lowerCase);
            sb.append("://");
            sb.append(domainOrIp);
            return sb.toString();
        }

        public final void setAddressType(AddressType addressType) {
            Intrinsics.checkNotNullParameter(addressType, "<set-?>");
            this.addressType = addressType;
        }

        public final void setDirLocation(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.dirLocation = str;
        }

        public final void setDomainOrIp(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.domainOrIp = str;
        }

        public final void setIndex(int i) {
            this.index = i;
        }

        public final void setMode(int i) {
            this.mode = i;
        }

        public final void setPort(int i) {
            this.port = i;
        }

        public final void setProtocol(Protocol protocol) {
            Intrinsics.checkNotNullParameter(protocol, "<set-?>");
            this.protocol = protocol;
        }

        public final void setSchList(List<Integer> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            this.schList = list;
        }

        public final void setServerId(int i) {
            this.serverId = i;
        }

        public final void setServerType(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.serverType = str;
        }
    }

    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\r\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 22\u00020\u0001:\u00012B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0006\u0010*\u001a\u00020+J\u0006\u0010,\u001a\u00020-J\u0016\u0010.\u001a\u00020+2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020%R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001e\u0010\r\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\tR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0012\u001a\u0004\u0018\u00010\u00138BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\"\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00130\u00178FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\t\"\u0004\b\u001e\u0010\u000bR\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0003@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0011R\u0013\u0010 \u001a\u0004\u0018\u00010\u00038F¢\u0006\u0006\u001a\u0004\b!\u0010\u0011R\u0016\u0010\"\u001a\u0004\u0018\u00010\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b#\u0010\u0011R\u001a\u0010$\u001a\u00020%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)¨\u00063"}, d2 = {"Lcom/xh/xhcore/common/http/BaseMicroServer$ServerUrlUnify;", "", "serverType", "", "(Ljava/lang/String;)V", "classId", "", "(Ljava/lang/String;I)V", "getClassId", "()I", "setClassId", "(I)V", "<set-?>", "currentIndex", "getCurrentIndex", "domainOrIp", "getDomainOrIp", "()Ljava/lang/String;", "micro", "Lcom/xh/xhcore/common/http/BaseMicroServer$MicroServerEntityUnify;", "getMicro", "()Lcom/xh/xhcore/common/http/BaseMicroServer$MicroServerEntityUnify;", "microServerNewDtoList", "", "getMicroServerNewDtoList", "()Ljava/util/List;", "setMicroServerNewDtoList", "(Ljava/util/List;)V", "schoolId", "getSchoolId", "setSchoolId", "getServerType", "url", "getUrl", "urlFromList", "getUrlFromList", "version", "", "getVersion", "()J", "setVersion", "(J)V", "clearOldCurrentProperties", "", "nextMicro", "", "update", "microBean", "Lcom/xh/xhcore/common/http/MicroServerResponse$MicroServerDto;", "updateTime", "Companion", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class ServerUrlUnify {
        private static final Object LOCK = new Object();
        private int classId;
        private transient int currentIndex;
        private List<MicroServerEntityUnify> microServerNewDtoList;
        private int schoolId;
        private String serverType;
        private long version;

        public ServerUrlUnify(String str) {
            Intrinsics.checkNotNullParameter(str, "serverType");
            this.microServerNewDtoList = new CopyOnWriteArrayList();
            this.serverType = "";
            this.currentIndex = -1;
            this.serverType = str;
        }

        public ServerUrlUnify(String str, int i) {
            Intrinsics.checkNotNullParameter(str, "serverType");
            this.microServerNewDtoList = new CopyOnWriteArrayList();
            this.serverType = "";
            this.currentIndex = -1;
            this.serverType = str;
            this.classId = i;
        }

        private final MicroServerEntityUnify getMicro() {
            if (this.currentIndex == -1) {
                nextMicro();
            }
            int i = this.currentIndex;
            if (i <= -1 || i >= getMicroServerNewDtoList().size()) {
                return null;
            }
            return getMicroServerNewDtoList().get(i);
        }

        private final String getUrlFromList() {
            String url;
            MicroServerEntityUnify micro = getMicro();
            return (micro == null || (url = micro.getUrl()) == null) ? "" : url;
        }

        public final void clearOldCurrentProperties() {
            this.currentIndex = -1;
        }

        public final int getClassId() {
            return this.classId;
        }

        public final int getCurrentIndex() {
            return this.currentIndex;
        }

        public final String getDomainOrIp() {
            MicroServerEntityUnify micro = getMicro();
            String domainOrIp = micro != null ? micro.getDomainOrIp() : "";
            return (TextUtils.isEmpty(domainOrIp) && this.classId == 0) ? BaseMicroServer.INSTANCE.getDefaultUrl(this.serverType) : domainOrIp;
        }

        public final List<MicroServerEntityUnify> getMicroServerNewDtoList() {
            if (this.microServerNewDtoList == null) {
                this.microServerNewDtoList = new CopyOnWriteArrayList();
            }
            return this.microServerNewDtoList;
        }

        public final int getSchoolId() {
            return this.schoolId;
        }

        public final String getServerType() {
            return this.serverType;
        }

        public final String getUrl() {
            String urlFromList = getUrlFromList();
            return (TextUtils.isEmpty(urlFromList) && this.classId == 0) ? BaseMicroServer.INSTANCE.getDefaultUrl(this.serverType) : urlFromList;
        }

        public final long getVersion() {
            return this.version;
        }

        public final boolean nextMicro() {
            boolean z;
            synchronized (LOCK) {
                z = true;
                if (getMicroServerNewDtoList().isEmpty()) {
                    z = false;
                } else {
                    this.currentIndex = getCurrentIndex() + 1;
                    if (getCurrentIndex() >= getMicroServerNewDtoList().size()) {
                        this.currentIndex = 0;
                        z = false;
                    }
                }
            }
            return z;
        }

        public final void setClassId(int i) {
            this.classId = i;
        }

        public final void setMicroServerNewDtoList(List<MicroServerEntityUnify> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            this.microServerNewDtoList = list;
        }

        public final void setSchoolId(int i) {
            this.schoolId = i;
        }

        public final void setVersion(long j) {
            this.version = j;
        }

        public final void update(MicroServerResponse.MicroServerDto microBean, long updateTime) {
            Intrinsics.checkNotNullParameter(microBean, "microBean");
            this.currentIndex = -1;
            MicroServerEntityUnify microServerEntityUnify = new MicroServerEntityUnify(microBean);
            ArrayList arrayList = new ArrayList();
            for (MicroServerEntityUnify microServerEntityUnify2 : getMicroServerNewDtoList()) {
                if (microServerEntityUnify2.getServerId() == microServerEntityUnify.getServerId()) {
                    arrayList.add(microServerEntityUnify2);
                }
            }
            getMicroServerNewDtoList().removeAll(arrayList);
            if (microServerEntityUnify.getMode() == 0) {
                LogUtils.Companion companion = LogUtils.INSTANCE;
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String str = String.format("更新微服务地址%s : %s(%s)", Arrays.copyOf(new Object[]{microServerEntityUnify.getServerType(), microServerEntityUnify.getDomainOrIp(), Integer.valueOf(microServerEntityUnify.getPort())}, 3));
                Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
                companion.i(str);
                getMicroServerNewDtoList().add(microServerEntityUnify);
            } else {
                LogUtils.Companion companion2 = LogUtils.INSTANCE;
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                String str2 = String.format("去除已关闭地址%s : %s(%s)", Arrays.copyOf(new Object[]{microServerEntityUnify.getServerType(), microServerEntityUnify.getDomainOrIp(), Integer.valueOf(microServerEntityUnify.getPort())}, 3));
                Intrinsics.checkNotNullExpressionValue(str2, "format(format, *args)");
                companion2.w(str2);
            }
            this.version = updateTime;
        }
    }

    private final boolean cancelIp(String serverType) {
        return cancelIp(serverType, 0);
    }

    private final boolean cancelIp(String serverType, int classId) {
        return getServerUrl(serverType, classId).nextMicro();
    }

    @JvmStatic
    public static BaseMicroServer getInstance() {
        return INSTANCE.getInstance();
    }

    public static String getRESETFul_MICRO_SERVICE_HOST() {
        return INSTANCE.getRESETFul_MICRO_SERVICE_HOST();
    }

    public final void clearMicroServerMemoryCache() {
        synchronized (this.URL_MAP) {
            getURL_MAP().clear();
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override
    public String getActualIp(String serverType) {
        Intrinsics.checkNotNullParameter(serverType, "serverType");
        return getActualIp(serverType, 0);
    }

    public final String getActualIp(String serverType, int classId) {
        Intrinsics.checkNotNullParameter(serverType, "serverType");
        return getServerUrl(serverType, classId).getUrl();
    }

    public final List<ClassServerTypesBean> getClassServers() {
        return this.classServers;
    }

    protected final ServerUrlUnify getMicroServerCache(String serverType) {
        Intrinsics.checkNotNullParameter(serverType, "serverType");
        String property = getXmConfigInstance().getProperty(serverType);
        LogUtils.INSTANCE.d("尝试读取本地缓存地址 : " + serverType + " >>> " + ((Object) property));
        return (ServerUrlUnify) XmJsonUtil.parseJson(property, ServerUrlUnify.class);
    }

    public final void getMicroServiceIPList(XHRequestCallBack.HttpCallBack<?> callBack) {
        Intrinsics.checkNotNullParameter(callBack, "callBack");
        getMicroServiceIPListWithRESTFul(callBack);
    }

    @Override
    public String getNextIp(String serverType) {
        Intrinsics.checkNotNullParameter(serverType, "serverType");
        return getNextIp(serverType, 0);
    }

    public final String getNextIp(String serverType, int classId) {
        Intrinsics.checkNotNullParameter(serverType, "serverType");
        return !cancelIp(serverType, classId) ? "" : getActualIp(serverType, classId);
    }

    public final ServerUrlUnify getServerUrl(String serverType) {
        Intrinsics.checkNotNullParameter(serverType, "serverType");
        return getServerUrl(serverType, 0);
    }

    public final ServerUrlUnify getServerUrl(String serverType, int classId) {
        ServerUrlUnify microServerCache;
        Intrinsics.checkNotNullParameter(serverType, "serverType");
        synchronized (this.URL_MAP) {
            microServerCache = getURL_MAP().get(XHMicroServerV3.INSTANCE.makeMicroServerV3Key(serverType, classId));
            if (microServerCache == null) {
                microServerCache = getMicroServerCache(XHMicroServerV3.INSTANCE.makeMicroServerV3Key(serverType, classId));
                if (microServerCache == null) {
                    microServerCache = new ServerUrlUnify(serverType, classId);
                } else {
                    getURL_MAP().put(XHMicroServerV3.INSTANCE.makeMicroServerV3Key(serverType, classId), microServerCache);
                }
            }
        }
        return microServerCache;
    }

    protected final Map<String, ServerUrlUnify> getURL_MAP() {
        return this.URL_MAP;
    }

    @Override
    public abstract XmConfig getXmConfigInstance();

    @Override
    public boolean requestAgain(String serverType) {
        return requestAgain(serverType, 0);
    }

    public final boolean requestAgain(String serverType, int classId) {
        if (serverType == null) {
            return false;
        }
        return !TextUtils.isEmpty(getNextIp(serverType, classId));
    }

    protected final void saveMicroServerCache(String serverType, ServerUrlUnify serverUrlUnify) {
        Intrinsics.checkNotNullParameter(serverType, "serverType");
        Intrinsics.checkNotNullParameter(serverUrlUnify, "serverUrlUnify");
        String strObjToJson = XmJsonUtil.objToJson(serverUrlUnify);
        LogUtils.INSTANCE.d("保存地址到本地缓存 : " + serverType + " >>> " + ((Object) strObjToJson));
        getXmConfigInstance().setProperty(serverType, strObjToJson);
    }

    public final BaseMicroServer setClassServers(List<? extends ClassServerTypesBean> classServers) {
        Intrinsics.checkNotNullParameter(classServers, "classServers");
        this.classServers = classServers;
        return this;
    }
}
