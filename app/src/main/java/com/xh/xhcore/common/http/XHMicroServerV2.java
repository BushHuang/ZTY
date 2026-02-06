package com.xh.xhcore.common.http;

import com.google.gson.reflect.TypeToken;
import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.config.IXHAppConfig;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.config.XmConfig;
import com.xh.xhcore.common.data.XmData;
import com.xh.xhcore.common.http.BaseMicroServer;
import com.xh.xhcore.common.http.XHRequestCallBack;
import com.xh.xhcore.common.http.microserver.v3.ClassServerTypesBean;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.http.strategy.xh.request.XHRequestOkHttpProxy;
import com.xh.xhcore.common.http.strategy.xh.request.XHRequestProxy;
import com.xh.xhcore.common.util.JsonUtil;
import com.xh.xhcore.common.util.WifiUtil;
import com.xh.xhcore.common.util.XHEncodeUtil;
import com.xh.xhcore.common.util.XHLog;
import com.xuehai.provider.core.db.CPVDUserData;
import com.xuehai.provider.core.dto.CPVDUser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class XHMicroServerV2 extends BaseMicroServer {
    public static final String PATH_RESTFUL_V2 = "/api/v2/pub/microServer/list/";
    private static final String RESETFul_MICRO_SERVICE_URL = getRESETFul_MICRO_SERVICE_HOST() + "/api/v2/pub/microServer/list/";
    private static final String TAG = "XHMicroServerV2";
    protected int defaultUrlIndex = 0;

    abstract class MicroServerHttpCallback<T> extends XHRequestCallBack.HttpCallBack<T> {
        private XHRequestCallBack.HttpCallBack callBack;
        private MicroServerReqBeanV2 microServerReqBeanV2;

        public MicroServerHttpCallback(MicroServerReqBeanV2 microServerReqBeanV2, XHRequestCallBack.HttpCallBack httpCallBack) {
            super(false);
            this.microServerReqBeanV2 = microServerReqBeanV2;
            this.callBack = httpCallBack;
        }

        protected void callSuccess() {
            if (this.callBack == null) {
                return;
            }
            XhBaseApplication.isMicroServerSuccess = true;
            this.callBack.success(XHMicroServerV2.this.getURL_MAP().size() == 0 ? "更新后无IP列表!" : "更新IP列表成功");
        }

        @Override
        public void failed(int i, String str) {
            XHLog.i("XHMicroServerV2", "getMicroServiceIPList errorCode = [" + i + "], errorStr = [" + str + "]");
            if (this.callBack != null) {
                if (!XHHttpUtil.isNeedPolling(i)) {
                    XhBaseApplication.isMicroServerSuccess = false;
                    this.callBack.failed(i, str);
                    return;
                }
                XHMicroServerV2 xHMicroServerV2 = XHMicroServerV2.this;
                if (xHMicroServerV2.isPollingEnd(this.microServerReqBeanV2, xHMicroServerV2.defaultUrlIndex)) {
                    XHMicroServerV2.this.defaultUrlIndex = 0;
                    XhBaseApplication.isMicroServerSuccess = false;
                    this.callBack.failed(i, str);
                } else {
                    XHLog.i("XHMicroServerV2", "进行轮询");
                    XHMicroServerV2.this.defaultUrlIndex++;
                    XHMicroServerV2.this.getMicroServiceIPListWithRESTFul(this.callBack);
                }
            }
        }
    }

    public static class MicroServerReqBeanV2 {
        private int schoolId;
        private boolean isSupportHttps = true;
        private List<ServerTypeBean> serverTypes = new ArrayList();
        private List<ClassServerTypesBean> classServers = new ArrayList();

        public static class ServerTypeBean {
            private String serverType;
            private long version;

            public ServerTypeBean() {
                this.serverType = "";
                this.version = 0L;
            }

            public ServerTypeBean(String str) {
                this.serverType = "";
                this.version = 0L;
                this.serverType = str;
            }

            public String getServerType() {
                return this.serverType;
            }

            public long getVersion() {
                return this.version;
            }

            public void setServerType(String str) {
                this.serverType = str;
            }

            public void setVersion(long j) {
                this.version = j;
            }
        }

        public void addServerType(ServerTypeBean serverTypeBean) {
            this.serverTypes.add(serverTypeBean);
        }

        public List<ClassServerTypesBean> getClassServers() {
            return this.classServers;
        }

        public int getSchoolId() {
            return this.schoolId;
        }

        public String getServerTypes() {
            StringBuilder sb = new StringBuilder();
            Iterator<ServerTypeBean> it = this.serverTypes.iterator();
            while (it.hasNext()) {
                sb.append(it.next().getServerType());
                sb.append(",");
            }
            return sb.substring(0, sb.length() - 1);
        }

        public List<ServerTypeBean> getServerTypesBeans() {
            return this.serverTypes;
        }

        public MicroServerReqBeanV2 setClassServers(List<ClassServerTypesBean> list) {
            this.classServers = list;
            return this;
        }

        public void setSchoolId(int i) {
            this.schoolId = i;
        }

        public void setServerTypes(List<ServerTypeBean> list) {
            this.serverTypes = list;
        }
    }

    private MicroServerReqBeanV2 initReqV2SchoolIdAndServerTypes(CPVDUser cPVDUser) {
        MicroServerReqBeanV2 microServerReqBeanV2 = new MicroServerReqBeanV2();
        microServerReqBeanV2.setSchoolId(cPVDUser.getSchoolId());
        List listAsList = Arrays.asList(XHAppConfigProxy.getInstance().getMicroServerTypes().split(","));
        if (listAsList.size() == 0) {
            LogUtils.e("serverTypes is empty");
            return microServerReqBeanV2;
        }
        Iterator it = listAsList.iterator();
        while (it.hasNext()) {
            microServerReqBeanV2.addServerType(new MicroServerReqBeanV2.ServerTypeBean((String) it.next()));
        }
        if (getClassServers() != null && getClassServers().size() > 0) {
            microServerReqBeanV2.setClassServers(getClassServers());
        }
        return microServerReqBeanV2;
    }

    private void initServerTypesVersionFromCacheOrLocal(MicroServerReqBeanV2 microServerReqBeanV2) {
        if (microServerReqBeanV2.getServerTypesBeans().size() > 0) {
            for (MicroServerReqBeanV2.ServerTypeBean serverTypeBean : microServerReqBeanV2.getServerTypesBeans()) {
                serverTypeBean.setVersion(getServerUrl(serverTypeBean.serverType).getVersion());
            }
        }
        List<ClassServerTypesBean> classServers = microServerReqBeanV2.getClassServers();
        if (classServers == null || classServers.size() <= 0) {
            return;
        }
        for (ClassServerTypesBean classServerTypesBean : classServers) {
            if (classServerTypesBean != null && classServerTypesBean.getServerTypes() != null) {
                for (MicroServerReqBeanV2.ServerTypeBean serverTypeBean2 : classServerTypesBean.getServerTypes()) {
                    serverTypeBean2.setVersion(getServerUrl(serverTypeBean2.serverType, classServerTypesBean.getClassId()).getVersion());
                }
            }
        }
    }

    private boolean isPollingEnd(MicroServerReqBeanV2 microServerReqBeanV2, int i) {
        return microServerReqBeanV2 == null || getMicroServerUrl() == null || getMicroServerUrl().length == 0 || i >= getMicroServerUrl().length - 1;
    }

    private MicroServerReqBeanV2 makeMicroServerReqBeanV2V3(CPVDUser cPVDUser) {
        MicroServerReqBeanV2 microServerReqBeanV2InitReqV2SchoolIdAndServerTypes = initReqV2SchoolIdAndServerTypes(cPVDUser);
        initServerTypesVersionFromCacheOrLocal(microServerReqBeanV2InitReqV2SchoolIdAndServerTypes);
        return microServerReqBeanV2InitReqV2SchoolIdAndServerTypes;
    }

    private void parseMicroUrl(String str) {
        List<BaseMicroServer.ServerUrlUnify> toList = JsonUtil.parseToList(str, new TypeToken<List<BaseMicroServer.ServerUrlUnify>>() {
        }.getType());
        synchronized (getURL_MAP()) {
            if (toList != null) {
                if (!toList.isEmpty()) {
                    for (BaseMicroServer.ServerUrlUnify serverUrlUnify : toList) {
                        if (serverUrlUnify != null) {
                            serverUrlUnify.clearOldCurrentProperties();
                            getURL_MAP().put(getServerKey(serverUrlUnify), serverUrlUnify);
                            saveMicroServerCache(getServerKey(serverUrlUnify), serverUrlUnify);
                        }
                    }
                }
            }
        }
    }

    protected String[] getMicroServerUrl() {
        return XmData.getMicroServerUrl(IXHAppConfig.MicroServerVersion.VERSION2);
    }

    protected String getMicroServiceDefaultUrl() {
        return RESETFul_MICRO_SERVICE_URL;
    }

    @Override
    public void getMicroServiceIPListWithRESTFul(XHRequestCallBack.HttpCallBack httpCallBack) {
        CPVDUser user = CPVDUserData.getUser(XhBaseApplication.mContext);
        if (user == null) {
            httpCallBack.failed(0, "cpvdUser为空 e = ");
        } else {
            MicroServerReqBeanV2 microServerReqBeanV2MakeMicroServerReqBeanV2V3 = makeMicroServerReqBeanV2V3(user);
            ((XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) XHRequestProxy.createOkHttp().setUrl(getRequestUrlWithRESTFul())).setMethod("POST")).setRequestBody((Object) microServerReqBeanV2MakeMicroServerReqBeanV2V3).setCallback(new MicroServerHttpCallback<String>(microServerReqBeanV2MakeMicroServerReqBeanV2V3, httpCallBack) {
                @Override
                public void success(String str) {
                    XHMicroServerV2.this.parseMicroUrl(str);
                    callSuccess();
                }
            })).addHeader(WifiUtil.WIFI_INFO, (Object) XHEncodeUtil.urlEncode(WifiUtil.getWifiInfo()))).request();
        }
    }

    protected String getRequestUrlWithRESTFul() {
        String[] microServerUrl = getMicroServerUrl();
        if (microServerUrl == null || microServerUrl.length == 0) {
            return getMicroServiceDefaultUrl();
        }
        int i = this.defaultUrlIndex;
        return i < microServerUrl.length ? microServerUrl[i] : microServerUrl[0];
    }

    protected String getServerKey(BaseMicroServer.ServerUrlUnify serverUrlUnify) {
        return serverUrlUnify.getServerType();
    }

    @Override
    public XmConfig getXmConfigInstance() {
        return XmConfig.newInstanceV2();
    }
}
