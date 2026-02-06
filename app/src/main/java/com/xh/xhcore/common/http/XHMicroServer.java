package com.xh.xhcore.common.http;

import android.text.TextUtils;
import com.google.gson.reflect.TypeToken;
import com.xh.xhcore.common.base.XhBaseApplication;
import com.xh.xhcore.common.config.IXHAppConfig;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.config.XmConfig;
import com.xh.xhcore.common.data.XmData;
import com.xh.xhcore.common.http.BaseMicroServer;
import com.xh.xhcore.common.http.MicroServerResponse;
import com.xh.xhcore.common.http.XHRequestCallBack;
import com.xh.xhcore.common.http.archi.CommonUtil;
import com.xh.xhcore.common.http.strategy.xh.request.XHRequestOkHttpProxy;
import com.xh.xhcore.common.http.strategy.xh.request.XHRequestProxy;
import com.xh.xhcore.common.util.JsonUtil;
import com.xh.xhcore.common.util.XHLog;
import com.xuehai.provider.core.db.CPVDUserData;
import com.xuehai.provider.core.dto.CPVDUser;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class XHMicroServer extends BaseMicroServer {
    private static final String MICRO_SERVICE_URL = "http://ms.yunzuoye.net/XhServerMg/GetXsList";
    public static final String PATH_KZ = "/XhServerMg/GetXsList";
    public static final String PATH_RESTFUL = "/api/v1/pub/microServer/";
    private static final String RESETFul_MICRO_SERVICE_URL = getRESETFul_MICRO_SERVICE_HOST() + "/api/v1/pub/microServer/";
    private static final String TAG = "XHMicroServer";
    private int defaultUrlIndex = 0;

    abstract class MicroServerHttpCallback<T> extends XHRequestCallBack.HttpCallBack<T> {
        private XHRequestCallBack.HttpCallBack callBack;
        private MicroServiceReqBean microServiceReqBean;

        public MicroServerHttpCallback(MicroServiceReqBean microServiceReqBean, XHRequestCallBack.HttpCallBack httpCallBack) {
            super(false);
            this.microServiceReqBean = microServiceReqBean;
            this.callBack = httpCallBack;
        }

        protected void callFailed(int i, String str) {
            XHRequestCallBack.HttpCallBack httpCallBack = this.callBack;
            if (httpCallBack == null) {
                return;
            }
            httpCallBack.failed(i, str);
        }

        protected void callSuccess() {
            if (this.callBack == null) {
                return;
            }
            XhBaseApplication.isMicroServerSuccess = true;
            this.callBack.success(XHMicroServer.this.getURL_MAP().size() == 0 ? "增量更新后无IP列表!" : "增量更新IP列表成功");
        }

        @Override
        public void failed(int i, String str) {
            XHLog.i("XHMicroServer", "getMicroServiceIPList errorCode = [" + i + "], errorStr = [" + str + "]");
            if (this.callBack != null) {
                if (!XHHttpUtil.isNeedPolling(i)) {
                    XhBaseApplication.isMicroServerSuccess = false;
                    this.callBack.failed(i, str);
                    return;
                }
                XHMicroServer xHMicroServer = XHMicroServer.this;
                if (xHMicroServer.isPollingEnd(this.microServiceReqBean, xHMicroServer.defaultUrlIndex)) {
                    XHMicroServer.this.defaultUrlIndex = 0;
                    XhBaseApplication.isMicroServerSuccess = false;
                    this.callBack.failed(i, str);
                } else {
                    XHLog.i("XHMicroServer", "进行轮询");
                    XHMicroServer.access$008(XHMicroServer.this);
                    XHMicroServer.this.getMicroServiceIPListWithRESTFul(this.callBack);
                }
            }
        }
    }

    static int access$008(XHMicroServer xHMicroServer) {
        int i = xHMicroServer.defaultUrlIndex;
        xHMicroServer.defaultUrlIndex = i + 1;
        return i;
    }

    public static BaseMicroServer getInstance() {
        return BaseMicroServer.getInstance();
    }

    private String getRequestUrlWithRESTFul(MicroServiceReqBean microServiceReqBean) {
        return microServiceReqBean == null ? RESETFul_MICRO_SERVICE_URL : (microServiceReqBean.getMicroServiceUrls() == null || microServiceReqBean.getMicroServiceUrls().length == 0) ? RESETFul_MICRO_SERVICE_URL : this.defaultUrlIndex < microServiceReqBean.getMicroServiceUrls().length ? microServiceReqBean.getMicroServiceUrls()[this.defaultUrlIndex] : microServiceReqBean.getMicroServiceUrls()[0];
    }

    public static boolean isIP(String str) {
        return CommonUtil.isIP(str);
    }

    private boolean isPollingEnd(MicroServiceReqBean microServiceReqBean, int i) {
        return microServiceReqBean == null || microServiceReqBean.getMicroServiceUrls() == null || microServiceReqBean.getMicroServiceUrls().length == 0 || i >= microServiceReqBean.getMicroServiceUrls().length - 1;
    }

    private MicroServiceReqBean makeMicroServiceReqBean(CPVDUser cPVDUser) {
        MicroServiceReqBean microServiceReqBean = new MicroServiceReqBean();
        microServiceReqBean.setServerTypes(XHAppConfigProxy.getInstance().getMicroServerTypes());
        microServiceReqBean.setMicroServiceUrls(XmData.getMicroServerUrl(IXHAppConfig.MicroServerVersion.VERSION1));
        microServiceReqBean.setSchoolId(cPVDUser.getSchoolId());
        return microServiceReqBean;
    }

    private void parseMicroUrl(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        List<MicroServerResponse.MicroServerDto> toList = JsonUtil.parseToList(jSONObject.getString("microServerDtoList"), new TypeToken<List<MicroServerResponse.MicroServerDto>>() {
        }.getType());
        String[] strArrSplit = jSONObject.getString("serverType").split(",");
        synchronized (getURL_MAP()) {
            long jOptLong = jSONObject.optLong("updateTime", 0L);
            if (toList != null && !toList.isEmpty()) {
                for (MicroServerResponse.MicroServerDto microServerDto : toList) {
                    if (!TextUtils.isEmpty(microServerDto.getDomainOrIp())) {
                        String serverType = microServerDto.getServerType();
                        BaseMicroServer.ServerUrlUnify serverUrlUnify = getURL_MAP().containsKey(serverType) ? getURL_MAP().get(serverType) : null;
                        if (serverUrlUnify == null) {
                            serverUrlUnify = new BaseMicroServer.ServerUrlUnify(serverType);
                        }
                        serverUrlUnify.update(microServerDto, jOptLong);
                        getURL_MAP().put(serverType, serverUrlUnify);
                    }
                }
                for (String str2 : strArrSplit) {
                    BaseMicroServer.ServerUrlUnify serverUrlUnify2 = getURL_MAP().get(str2);
                    if (serverUrlUnify2 == null) {
                        serverUrlUnify2 = new BaseMicroServer.ServerUrlUnify(str2);
                        serverUrlUnify2.setVersion(jOptLong);
                    }
                    saveMicroServerCache(str2, serverUrlUnify2);
                }
            }
        }
    }

    private void readServicesFromCacheOrLocal(MicroServiceReqBean microServiceReqBean) {
        String serverTypes = microServiceReqBean.getServerTypes();
        if (TextUtils.isEmpty(serverTypes)) {
            return;
        }
        String[] strArrSplit = serverTypes.split(",");
        long jCurrentTimeMillis = System.currentTimeMillis();
        for (String str : strArrSplit) {
            long version = getServerUrl(str).getVersion();
            if (jCurrentTimeMillis > version) {
                jCurrentTimeMillis = version;
            }
        }
        microServiceReqBean.setUpdateTime(jCurrentTimeMillis);
    }

    public static List<XHMicroServiceBodyBean> reorderToIPPrior(List<XHMicroServiceBodyBean> list) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (XHMicroServiceBodyBean xHMicroServiceBodyBean : list) {
            if (isIP(xHMicroServiceBodyBean.getSServerIp())) {
                arrayList.add(i, xHMicroServiceBodyBean);
                i++;
            } else {
                arrayList.add(xHMicroServiceBodyBean);
            }
        }
        return arrayList;
    }

    @Override
    public void getMicroServiceIPListWithRESTFul(XHRequestCallBack.HttpCallBack httpCallBack) {
        CPVDUser user = CPVDUserData.getUser(XhBaseApplication.mContext);
        if (user == null) {
            httpCallBack.failed(0, "cpvdUser为空 e = ");
            return;
        }
        MicroServiceReqBean microServiceReqBeanMakeMicroServiceReqBean = makeMicroServiceReqBean(user);
        readServicesFromCacheOrLocal(microServiceReqBeanMakeMicroServiceReqBean);
        ((XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) ((XHRequestOkHttpProxy) XHRequestProxy.createOkHttp().setUrl(getRequestUrlWithRESTFul(microServiceReqBeanMakeMicroServiceReqBean))).setMethod("GET")).setBodyData(microServiceReqBeanMakeMicroServiceReqBean).setCallback(new MicroServerHttpCallback<String>(microServiceReqBeanMakeMicroServiceReqBean, httpCallBack) {
            @Override
            public void success(String str) {
                try {
                    XHMicroServer.this.parseMicroUrl(str);
                    callSuccess();
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    callFailed(1, "微服务响应解析RuntimeException = " + e.getMessage());
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    callFailed(0, "微服务响应解析JSONException = " + e2.getMessage());
                }
            }
        })).request();
    }

    @Override
    public XmConfig getXmConfigInstance() {
        return XmConfig.newInstanceV1_1();
    }
}
