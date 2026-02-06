package com.analysys.process;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.LruCache;
import com.analysys.AnalysysConfig;
import com.analysys.AnsRamControl;
import com.analysys.LogObserverListener;
import com.analysys.ObserverListener;
import com.analysys.a;
import com.analysys.ab;
import com.analysys.ac;
import com.analysys.ad;
import com.analysys.af;
import com.analysys.ah;
import com.analysys.b;
import com.analysys.hybrid.HybridBridge;
import com.analysys.hybrid.WebViewInjectManager;
import com.analysys.i;
import com.analysys.ipc.IpcManager;
import com.analysys.p;
import com.analysys.push.PushListener;
import com.analysys.q;
import com.analysys.s;
import com.analysys.t;
import com.analysys.u;
import com.analysys.utils.ANSLog;
import com.analysys.utils.AThreadPool;
import com.analysys.utils.ActivityLifecycleUtils;
import com.analysys.utils.AnalysysUtil;
import com.analysys.utils.AnsReflectUtils;
import com.analysys.utils.CommonUtils;
import com.analysys.utils.Constants;
import com.analysys.utils.ExceptionUtil;
import com.analysys.utils.InternalAgent;
import com.analysys.utils.SharedUtil;
import com.analysys.v;
import com.analysys.w;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import org.json.JSONObject;

public class AgentProcess {
    private static final int URL_VISIT_MIN_TIME_INTERVAL = 100;
    private LogObserverListener logObserverListener;
    private IEventObserver mEventObserver;
    private boolean mInited;
    private String mUploadUrl;
    private String mVisualConfigUrl;
    private String mVisualDebugUrl;
    private Map<Integer, Object> mWebViewClientCache;
    private LruCache<String, Long> mWebViewUrlVisitCache;
    private Map<String, Object> properties;
    private final String GET_SETTINGS = "getSettings";
    private final String GET_USER_AGENT = "getUserAgentString";
    private final String SET_USER_AGENT = "setUserAgentString";
    private final String START = "start";
    private Application mApp = null;
    private String mTitle = "";
    private String mUrl = "";
    private AnalysysConfig mConfig = new AnalysysConfig();
    private HashSet<Integer> mPageViewBlackListByPages = new HashSet<>();
    private HashSet<Integer> mPageViewWhiteListByPages = new HashSet<>();
    private HashSet<Integer> mIgnoreByPages = new HashSet<>();
    private HashSet<Integer> mAutoByPages = new HashSet<>();
    private HashSet<Integer> mIgnoreByViewTypes = new HashSet<>();
    private HashSet<Integer> mAutoByByViewTypes = new HashSet<>();
    private HashSet<Integer> mIgnoreByView = new HashSet<>();
    private HashSet<Integer> mAutoByView = new HashSet<>();

    static class Holder {
        public static final AgentProcess INSTANCE = new AgentProcess();

        private Holder() {
        }
    }

    public interface IEventObserver {
        void onEvent(String str, JSONObject jSONObject);
    }

    private void changeUrlResetUser(Context context, String str) {
        if (CommonUtils.isEmpty(SharedUtil.getString(context, "serviceUrl", null))) {
            String string = SharedUtil.getString(context, "userUrl", null);
            if (CommonUtils.isEmpty(string)) {
                return;
            }
            String host = new URL(str).getHost();
            String host2 = new URL(string).getHost();
            if (CommonUtils.isEmpty(host) || CommonUtils.isEmpty(host2) || host.equals(host2)) {
                return;
            }
            resetInfo(context);
        }
    }

    private boolean checkoutEvent(JSONObject jSONObject) {
        if (jSONObject != null && !CommonUtils.isEmpty(jSONObject.optString("appid"))) {
            return true;
        }
        ah.b();
        return false;
    }

    private void debugResetUserInfo(Context context, int i) {
        if (SharedUtil.getInt(context, "userDebug", 0) == 1) {
            if (i == 0 || i == 2) {
                resetInfo(context);
            }
        }
    }

    public static AgentProcess getInstance() {
        return Holder.INSTANCE;
    }

    private String getNewChannel(Context context, String str) {
        String manifestData = CommonUtils.getManifestData(context, "ANALYSYS_CHANNEL");
        return (!CommonUtils.isEmpty(manifestData) || CommonUtils.isEmpty(str)) ? manifestData : str;
    }

    private String getNewKey(Context context, String str) {
        String manifestData = CommonUtils.getManifestData(context, "ANALYSYS_APPKEY");
        if (CommonUtils.isEmpty(str)) {
            return manifestData;
        }
        if (CommonUtils.isEmpty(manifestData) || TextUtils.equals(manifestData, str)) {
            return str;
        }
        return null;
    }

    private Runnable getTrackRunnable(final String str, final Map<String, Object> map, final long j) {
        return new Runnable() {
            @Override
            public void run() {
                JSONObject jSONObjectA;
                try {
                    Map mapDeepCopy = CommonUtils.deepCopy(map);
                    Context context = AnalysysUtil.getContext();
                    if (context != null && str != null) {
                        if (AgentProcess.this.isPushTrack(str)) {
                            AgentProcess.this.updateLastOperateTime(context);
                            jSONObjectA = q.a(context).a(Long.valueOf(j), "track", "$track", null, mapDeepCopy, str);
                        } else {
                            jSONObjectA = q.a(context).a(Long.valueOf(j), "track", "$track", mapDeepCopy, null, str);
                        }
                        AgentProcess.this.trackEvent(context, "track", str, jSONObjectA);
                        return;
                    }
                    ah.a("track", false);
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        };
    }

    private void handleClearSuperProperty(final String str) {
        AThreadPool.asyncHighPriorityExecutor(new Runnable() {
            @Override
            public void run() {
                try {
                    Context context = AnalysysUtil.getContext();
                    if (context != null) {
                        SharedUtil.remove(context, str);
                        ah.a("clearSuperProperties", true);
                    }
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    private Object handleGetSuperProperty(String str, String str2) {
        try {
            Context context = AnalysysUtil.getContext();
            if (context != null && !CommonUtils.isEmpty(str)) {
                String string = SharedUtil.getString(context, str2, null);
                if (!CommonUtils.isEmpty(string)) {
                    return new JSONObject(string).opt(str);
                }
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
        return null;
    }

    private void handleSuperProperties(final Map<String, Object> map, final String str) {
        AThreadPool.asyncHighPriorityExecutor(new Runnable() {
            @Override
            public void run() {
                try {
                    Context context = AnalysysUtil.getContext();
                    if (context == null) {
                        ah.a("registerSuperProperties", u.c());
                        return;
                    }
                    Map mapDeepCopy = CommonUtils.deepCopy(map);
                    if (ad.a("registerSuperProperties", (Map<String, Object>) mapDeepCopy)) {
                        if (u.a() == 200) {
                            ah.a("registerSuperProperties", true);
                        }
                        AgentProcess.this.saveSuperProperty(context, mapDeepCopy, str);
                    }
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    private void handleSuperProperty(final String str, final Object obj, final String str2) {
        AThreadPool.asyncHighPriorityExecutor(new Runnable() {
            @Override
            public void run() {
                try {
                    Context context = AnalysysUtil.getContext();
                    if (context == null) {
                        return;
                    }
                    HashMap map = new HashMap();
                    map.put(str, obj);
                    if (!ad.a("registerSuperProperty", map)) {
                        ah.a("registerSuperProperty", u.c());
                        return;
                    }
                    if (u.a() == 200) {
                        ah.a("registerSuperProperty", true);
                    }
                    AgentProcess.this.saveSuperProperty(context, map, str2);
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    private void handleSuperProperty(final String str, final String str2) {
        AThreadPool.asyncHighPriorityExecutor(new Runnable() {
            @Override
            public void run() {
                try {
                    Context context = AnalysysUtil.getContext();
                    if (context == null || CommonUtils.isEmpty(str)) {
                        return;
                    }
                    String string = SharedUtil.getString(context, str2, null);
                    if (CommonUtils.isEmpty(string)) {
                        return;
                    }
                    JSONObject jSONObject = new JSONObject(string);
                    jSONObject.remove(str);
                    SharedUtil.setString(context, str2, String.valueOf(jSONObject));
                    ah.a("unRegisterSuperProperty", true);
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    private boolean isPushTrack(String str) {
        return "$push_receiver_success".equals(str) || "$push_click".equals(str) || "$push_process_success".equals(str);
    }

    private void probeInit(Context context) {
        try {
            t.d(context);
            if (t.h != null) {
                String strOptString = t.h.optString("start");
                int iLastIndexOf = strOptString.lastIndexOf(".");
                CommonUtils.reflexStaticMethod(strOptString.substring(0, iLastIndexOf), strOptString.substring(iLastIndexOf + 1), new Class[0], new Object[0]);
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    private void resetInfo(Context context) {
        a.a(context);
    }

    private void saveChannel(Context context, String str) {
        String newChannel = getNewChannel(context, str);
        if (CommonUtils.isEmpty(newChannel)) {
            ah.a(false, newChannel);
        } else {
            SharedUtil.setString(context, "appChannel", newChannel);
            ah.a(true, newChannel);
        }
    }

    private void saveKey(Context context, String str) {
        String newKey = getNewKey(context, str);
        String appKey = CommonUtils.getAppKey(context);
        if (CommonUtils.isEmpty(newKey)) {
            ah.b(false, newKey);
            return;
        }
        SharedUtil.setString(context, "appKey", newKey);
        if (!CommonUtils.isEmpty(appKey) && !TextUtils.equals(appKey, newKey)) {
            resetInfo(context);
        }
        ah.b(true, newKey);
    }

    private void saveSuperProperty(Context context, Map<String, Object> map, String str) {
        JSONObject jSONObject;
        if (CommonUtils.isEmpty(map)) {
            return;
        }
        String string = SharedUtil.getString(context, str, null);
        if (CommonUtils.isEmpty(string)) {
            jSONObject = new JSONObject(map);
        } else {
            jSONObject = new JSONObject(string);
            CommonUtils.mergeJson(new JSONObject(map), jSONObject);
        }
        SharedUtil.setString(context, str, String.valueOf(jSONObject));
    }

    private void saveUploadUrl(Context context, String str) {
        changeUrlResetUser(context, str);
        SharedUtil.setString(context, "userUrl", str);
        if (Constants.isTimeCheck && CommonUtils.isMainProcess(context)) {
            p.a(context).b();
        }
    }

    private void sendFirstInstall(Context context, long j) {
        if (context != null) {
            trackEvent(context, "firstInstallation", "$first_installation", q.a(context).a(Long.valueOf(j), "firstInstallation", "$first_installation", null, Constants.utm));
        }
    }

    private void sendProfileSetOnce(Context context, int i, long j) {
        if (Constants.isAutoProfile) {
            HashMap map = new HashMap();
            if (i == 0) {
                map.put("$first_visit_time", CommonUtils.getFirstStartTime(context));
                map.put("$first_visit_language", Locale.getDefault().getLanguage());
            } else if (i != 1) {
                return;
            } else {
                map.put("$reset_time", CommonUtils.getTime());
            }
            trackEvent(context, "profileSetOnce", "$pageview", q.a(context).a(Long.valueOf(j), "profileSetOnce", "$profile_set_once", null, map));
        }
    }

    private void setBaseUrl(Context context, String str) {
        if (CommonUtils.isEmpty(str)) {
            return;
        }
        setVisitorBaseURL(str);
        saveUploadUrl(context, "https://" + str + ":4089/up");
    }

    private void setVisitorBaseURL(final String str) {
        AThreadPool.asyncHighPriorityExecutor(new Runnable() {
            @Override
            public void run() {
                try {
                    Context context = AnalysysUtil.getContext();
                    if (context != null) {
                        t.b(context);
                        if (t.c != null) {
                            AgentProcess.this.setVisualUrl(context, t.c.optString("start"), str);
                        }
                    }
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    private void setVisualUrl(Context context, String str, String str2) {
        int iLastIndexOf = str.lastIndexOf(".");
        CommonUtils.reflexStaticMethod(str.substring(0, iLastIndexOf), str.substring(iLastIndexOf + 1), new Class[]{Context.class, String.class}, context, str2);
    }

    private void trackEvent(Context context, String str, String str2, JSONObject jSONObject) {
        if (CommonUtils.isEmpty(str2) || !checkoutEvent(jSONObject)) {
            return;
        }
        if (u.a() == 200) {
            ah.a(str, true);
        }
        i.a().b(jSONObject.toString());
        IEventObserver iEventObserver = this.mEventObserver;
        if (iEventObserver != null) {
            iEventObserver.onEvent(str2, jSONObject);
        }
        p.a(context).a(str2, jSONObject);
    }

    private void updateLastOperateTime(Context context) {
        v.a(context).a(false);
        SharedUtil.setString(context, "pageEndTime", String.valueOf(System.currentTimeMillis()));
    }

    public void alias(final String str) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        AThreadPool.asyncLowPriorityExecutor(new Runnable() {
            @Override
            public void run() {
                try {
                    Context context = AnalysysUtil.getContext();
                    if (context != null) {
                        if (!ad.a(str)) {
                            ah.a("alias", u.c());
                            return;
                        }
                        String strB = ab.b();
                        ab.d(strB);
                        ab.a(str);
                        HashMap map = new HashMap();
                        map.put("$original_id", strB);
                        JSONObject jSONObjectA = q.a(context).a(Long.valueOf(jCurrentTimeMillis), "alias", "$alias", map, null);
                        if (CommonUtils.isEmpty(jSONObjectA)) {
                            ah.a("alias", false);
                            return;
                        }
                        i.a().a(str);
                        AgentProcess.this.trackEvent(context, "alias", "$alias", jSONObjectA);
                        AgentProcess.this.sendProfileSetOnce(context, 0, jCurrentTimeMillis);
                    }
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    public void alias(final String str, final String str2) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        AThreadPool.asyncLowPriorityExecutor(new Runnable() {
            @Override
            public void run() {
                try {
                    Context context = AnalysysUtil.getContext();
                    if (context != null) {
                        if (!ad.a(str)) {
                            ah.a("alias", u.c());
                            return;
                        }
                        if (!CommonUtils.isEmpty(str2) && !ad.b(str2)) {
                            ah.a("alias", u.c());
                            return;
                        }
                        String strB = str2;
                        if (CommonUtils.isEmpty(strB)) {
                            strB = ab.b();
                        } else {
                            ab.d(strB);
                        }
                        ab.a(str);
                        HashMap map = new HashMap();
                        map.put("$original_id", strB);
                        JSONObject jSONObjectA = q.a(context).a(Long.valueOf(jCurrentTimeMillis), "alias", "$alias", map, null);
                        if (CommonUtils.isEmpty(jSONObjectA)) {
                            ah.a("alias", false);
                            return;
                        }
                        i.a().a(str);
                        AgentProcess.this.trackEvent(context, "alias", "$alias", jSONObjectA);
                        AgentProcess.this.sendProfileSetOnce(context, 0, jCurrentTimeMillis);
                    }
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    public void appEnd(String str, JSONObject jSONObject) {
        Context context;
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            long j = CommonUtils.parseLong(str, 0L);
            if (j <= 0 || (context = AnalysysUtil.getContext()) == null || jSONObject == null) {
                return;
            }
            JSONObject jSONObjectA = q.a(context).a(Long.valueOf(j), "appEnd", "$end", null, null);
            jSONObjectA.put("xwhen", j);
            JSONObject jSONObjectOptJSONObject = jSONObjectA.optJSONObject("xcontext");
            CommonUtils.mergeJson(jSONObject, jSONObjectOptJSONObject);
            jSONObjectA.put("xcontext", jSONObjectOptJSONObject);
            trackEvent(context, "appEnd", "$end", jSONObjectA);
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    public void appStart(boolean z, long j) {
        try {
            Context context = AnalysysUtil.getContext();
            if (context == null) {
                return;
            }
            HashMap map = new HashMap();
            map.put("$is_from_background", Boolean.valueOf(z));
            if (!CommonUtils.isEmpty(Constants.utm)) {
                map.putAll(Constants.utm);
            }
            trackEvent(context, "appStart", "$startup", q.a(context).a(Long.valueOf(j), "appStart", "$startup", null, map));
            if (Constants.isFirstInstall) {
                Constants.isFirstInstall = false;
                sendProfileSetOnce(context, 0, j);
                if (Constants.autoInstallation) {
                    sendFirstInstall(context, j);
                }
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    public void autoCollectPageView(Map<String, Object> map, long j) {
        Context context = AnalysysUtil.getContext();
        if (context != null) {
            trackEvent(context, "pageView", "$pageview", q.a(context).a(Long.valueOf(j), "pageView", "$pageview", map, null));
        }
    }

    public void autoTrackViewClick(Map<String, Object> map, long j) {
        Context context = AnalysysUtil.getContext();
        if (context != null) {
            trackEvent(context, "userClick", "$user_click", q.a(context).a(Long.valueOf(j), "userClick", "$user_click", null, map));
        }
    }

    public void clearJsSuperProperty() {
        handleClearSuperProperty("js_superProperty");
    }

    public void clearSuperProperty() {
        handleClearSuperProperty("superProperty");
    }

    public void enablePush(final String str, final String str2) {
        AThreadPool.asyncHighPriorityExecutor(new Runnable() {
            @Override
            public void run() {
                try {
                    Context context = AnalysysUtil.getContext();
                    if (context != null) {
                        t.c(context);
                        if (t.f != null) {
                            String strOptString = t.f.optString("start");
                            int iLastIndexOf = strOptString.lastIndexOf(".");
                            CommonUtils.reflexUtils(strOptString.substring(0, iLastIndexOf), strOptString.substring(iLastIndexOf + 1), new Class[]{Context.class, String.class, String.class}, context, str, str2);
                        }
                    }
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    public void flush() {
        AThreadPool.asyncHighPriorityExecutor(new Runnable() {
            @Override
            public void run() {
                Context context = AnalysysUtil.getContext();
                if (context != null) {
                    p.a(context).a();
                }
            }
        });
    }

    public AnalysysConfig getConfig() {
        return this.mConfig;
    }

    public String getDistinctId() {
        return ab.b();
    }

    public LogObserverListener getLogObserverListener() {
        return this.logObserverListener;
    }

    public long getMaxCacheSize() {
        long cacheMaxCount = AnsRamControl.getInstance().getCacheMaxCount();
        if (cacheMaxCount < 1) {
            return 10000L;
        }
        return cacheMaxCount;
    }

    public Map<String, Object> getPresetProperties() {
        try {
            Context context = AnalysysUtil.getContext();
            if (context != null) {
                if (this.properties != null) {
                    return this.properties;
                }
                HashMap map = new HashMap();
                this.properties = map;
                map.put("$time_zone", InternalAgent.getTimeZone(context));
                this.properties.put("$platform", "Android");
                this.properties.put("$app_version", InternalAgent.getVersionName(context));
                this.properties.put("$language", InternalAgent.getDeviceLanguage(context));
                this.properties.put("$lib_version", InternalAgent.getLibVersion(context));
                this.properties.put("$lib", "Android");
                this.properties.put("$screen_width", InternalAgent.getScreenWidth(context));
                this.properties.put("$screen_height", InternalAgent.getScreenHeight(context));
                this.properties.put("$network", InternalAgent.getNetwork(context));
                this.properties.put("$manufacturer", InternalAgent.getManufacturer(context));
                this.properties.put("$mac", InternalAgent.getMac(context));
                this.properties.put("$imei", InternalAgent.getIMEI(context));
                this.properties.put("$first_visit_time", SharedUtil.getString(context, "firstStartTime", ""));
                this.properties.put("$brand", InternalAgent.getBrand(context));
                this.properties.put("$model", InternalAgent.getDeviceModel(context));
                this.properties.put("$os", "Android");
                this.properties.put("$session_id", v.a(context).a());
                return this.properties;
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
        return new HashMap();
    }

    public Object getSuperProperty(String str) {
        Object objHandleGetSuperProperty = handleGetSuperProperty(str, "superProperty");
        return objHandleGetSuperProperty == null ? handleGetSuperProperty(str, "js_superProperty") : objHandleGetSuperProperty;
    }

    public Map<String, Object> getSuperProperty() {
        Context context;
        HashMap map = new HashMap(16);
        JSONObject jSONObject = new JSONObject();
        try {
            context = AnalysysUtil.getContext();
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
        if (context == null) {
            return new HashMap();
        }
        JSONObject jSONObject2 = null;
        String string = SharedUtil.getString(context, "superProperty", null);
        String string2 = SharedUtil.getString(context, "js_superProperty", null);
        JSONObject jSONObject3 = (string == null || string.length() <= 0) ? null : new JSONObject(string);
        if (string2 != null && string2.length() > 0) {
            jSONObject2 = new JSONObject(string2);
        }
        if (jSONObject3 == null || jSONObject2 == null) {
            if (jSONObject3 == null) {
                jSONObject = jSONObject2;
            }
            if (jSONObject2 != null) {
                jSONObject3 = jSONObject;
            }
        } else {
            CommonUtils.mergeJson(jSONObject3, jSONObject2);
            jSONObject3 = jSONObject2;
        }
        if (jSONObject3 != null) {
            map.putAll(CommonUtils.jsonToMap(jSONObject3));
        }
        return map;
    }

    public int getUploadNetworkType() {
        return AnsRamControl.getInstance().getUploadNetworkType();
    }

    public String getUploadURL() {
        return this.mUploadUrl;
    }

    public String getVisitorConfigURL() {
        return this.mVisualConfigUrl;
    }

    public String getVisitorDebugURL() {
        return this.mVisualDebugUrl;
    }

    public boolean hasAutoClickWhiteList() {
        return (this.mAutoByView.isEmpty() && this.mAutoByByViewTypes.isEmpty() && this.mAutoByPages.isEmpty()) ? false : true;
    }

    public boolean hasAutoPageViewWhiteList() {
        if (this.mPageViewWhiteListByPages == null) {
            return false;
        }
        return !r0.isEmpty();
    }

    public void hybridPageView(final String str, final Map<String, Object> map) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        AThreadPool.asyncMiddlePriorityExecutor(new Runnable() {
            @Override
            public void run() {
                try {
                    Context context = AnalysysUtil.getContext();
                    if (context == null) {
                        return;
                    }
                    Map mapDeepCopy = CommonUtils.deepCopy(map);
                    if (!CommonUtils.isEmpty(str) && map != null) {
                        map.put("$title", str);
                    }
                    AgentProcess.this.trackEvent(context, "pageView", "$pageview", q.a(context).a(Long.valueOf(jCurrentTimeMillis), "pageView", "$pageview", mapDeepCopy, null));
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    public void identify(final String str) {
        AThreadPool.asyncHighPriorityExecutor(new Runnable() {
            @Override
            public void run() {
                if (!ad.a(str)) {
                    ah.a("distinctId", u.c());
                } else {
                    i.a().a(str);
                    ab.b(str);
                }
            }
        });
    }

    public void init(final Context context, AnalysysConfig analysysConfig) {
        if (this.mInited) {
            ANSLog.e("多次调用AnalysysAgent.init，请保证只初始化一次");
            return;
        }
        this.mInited = true;
        AnalysysUtil.init(context);
        af.a().a(new af.a() {
            @Override
            public void onAppCrash(Throwable th) {
                af.a().a(context, th, 0);
            }
        });
        if (analysysConfig != null) {
            this.mConfig = analysysConfig;
        }
        ActivityLifecycleUtils.initLifecycle();
        ActivityLifecycleUtils.addCallback(new b());
        probeInit(context);
        AThreadPool.initHighPriorityExecutor(new Callable() {
            @Override
            public Object call() {
                try {
                    IpcManager.getInstance().init();
                    Context context2 = AnalysysUtil.getContext();
                    if (context2 != null) {
                        w.a(context2);
                        AgentProcess.this.saveKey(context2, AgentProcess.this.mConfig.getAppKey());
                        AgentProcess.this.saveChannel(context2, AgentProcess.this.mConfig.getChannel());
                        if (CommonUtils.isMainProcess(context2)) {
                            Constants.isFirstInstall = CommonUtils.isFirst(context);
                            AgentProcess.this.setBaseUrl(context2, AgentProcess.this.mConfig.getBaseUrl());
                            Constants.isAutoProfile = AgentProcess.this.mConfig.isAutoProfile();
                            Constants.encryptType = AgentProcess.this.mConfig.getEncryptType().getType();
                            Constants.autoInstallation = AgentProcess.this.mConfig.isAutoInstallation();
                            a.a();
                            if (0 <= AgentProcess.this.mConfig.getMaxDiffTimeInterval()) {
                                Constants.ignoreDiffTime = AgentProcess.this.mConfig.getMaxDiffTimeInterval();
                            }
                        }
                        Constants.isTimeCheck = AgentProcess.this.mConfig.isTimeCheck();
                        t.a(context2);
                        ah.b(true);
                    } else {
                        ah.b(false);
                    }
                    ac.a();
                    return true;
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                    return true;
                }
            }
        });
    }

    public void interceptUrl(String str, Object obj) {
        Object obj2;
        try {
            if (CommonUtils.isEmpty(str)) {
                return;
            }
            if (this.mWebViewUrlVisitCache == null) {
                this.mWebViewUrlVisitCache = new LruCache<>(100);
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            Long l = this.mWebViewUrlVisitCache.get(str);
            this.mWebViewUrlVisitCache.put(str, Long.valueOf(jCurrentTimeMillis));
            if (l == null || Math.abs(jCurrentTimeMillis - l.longValue()) >= 100) {
                if (str.startsWith("analysysagent")) {
                    HybridBridge.getInstance().execute(str.substring(14), obj);
                }
                if (this.mWebViewClientCache == null || (obj2 = this.mWebViewClientCache.get(Integer.valueOf(obj.hashCode()))) == null) {
                    return;
                }
                String name = obj2.getClass().getName();
                for (StackTraceElement stackTraceElement : new Throwable().getStackTrace()) {
                    if (TextUtils.equals(stackTraceElement.getClassName(), name)) {
                        return;
                    }
                }
                AnsReflectUtils.invokeMethodByName(obj2, "shouldOverrideUrlLoading", new Object[]{obj, str});
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    public boolean isInited() {
        return this.mInited;
    }

    public boolean isThisPageInAutoClickBlackList(String str) {
        return !TextUtils.isEmpty(str) && this.mIgnoreByPages.contains(Integer.valueOf(str.hashCode()));
    }

    public boolean isThisPageInAutoClickWhiteList(String str) {
        return !TextUtils.isEmpty(str) && this.mAutoByPages.contains(Integer.valueOf(str.hashCode()));
    }

    public boolean isThisPageInPageViewBlackList(String str) {
        if (this.mPageViewBlackListByPages == null || TextUtils.isEmpty(str)) {
            return false;
        }
        return this.mPageViewBlackListByPages.contains(Integer.valueOf(str.hashCode()));
    }

    public boolean isThisPageInPageViewWhiteList(String str) {
        if (this.mPageViewWhiteListByPages == null || TextUtils.isEmpty(str)) {
            return false;
        }
        return this.mPageViewWhiteListByPages.contains(Integer.valueOf(str.hashCode()));
    }

    public boolean isThisViewInAutoClickBlackList(Object obj) {
        HashSet<Integer> hashSet;
        if (obj == null || (hashSet = this.mIgnoreByView) == null) {
            return false;
        }
        return hashSet.contains(Integer.valueOf(obj.hashCode()));
    }

    public boolean isThisViewInAutoClickWhiteList(Object obj) {
        HashSet<Integer> hashSet;
        if (obj == null || (hashSet = this.mAutoByView) == null) {
            return false;
        }
        return hashSet.contains(Integer.valueOf(obj.hashCode()));
    }

    public boolean isThisViewTypeInAutoClickBlackList(Class<?> cls) {
        if (cls != null) {
            try {
                String name = cls.getName();
                if (TextUtils.isEmpty(name)) {
                    return false;
                }
                return this.mIgnoreByViewTypes.contains(Integer.valueOf(name.hashCode()));
            } catch (Throwable th) {
                ExceptionUtil.exceptionThrow(th);
            }
        }
        return false;
    }

    public boolean isThisViewTypeInAutoClickWhiteList(Class<?> cls) {
        if (cls != null) {
            try {
                String name = cls.getName();
                if (TextUtils.isEmpty(name)) {
                    return false;
                }
                return this.mAutoByByViewTypes.contains(Integer.valueOf(name.hashCode()));
            } catch (Throwable th) {
                ExceptionUtil.exceptionThrow(th);
            }
        }
        return false;
    }

    public void pageTouchInfo(Map<String, Object> map, long j) {
        try {
            Context context = AnalysysUtil.getContext();
            Map mapDeepCopy = CommonUtils.deepCopy(map);
            if (context != null) {
                trackEvent(context, "appClick", "$app_click", q.a(context).a(Long.valueOf(j), "appClick", "$app_click", null, mapDeepCopy));
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    public void pageView(Context context, final String str, final Map<String, Object> map) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            this.mUrl = activity.getClass().getCanonicalName();
            this.mTitle = String.valueOf(activity.getTitle());
        }
        final long jCurrentTimeMillis = System.currentTimeMillis();
        AThreadPool.asyncMiddlePriorityExecutor(new Runnable() {
            @Override
            public void run() {
                try {
                    Context context2 = AnalysysUtil.getContext();
                    if (context2 == null) {
                        return;
                    }
                    Map mapDeepCopy = CommonUtils.deepCopy(map);
                    mapDeepCopy.put("$title", str);
                    HashMap map2 = new HashMap();
                    if (!mapDeepCopy.containsKey("$url")) {
                        map2.put("$url", AgentProcess.this.mUrl);
                    }
                    if (!mapDeepCopy.containsKey("$title")) {
                        map2.put("$title", AgentProcess.this.mTitle);
                    }
                    AgentProcess.this.trackEvent(context2, "pageView", "$pageview", q.a(context2).a(Long.valueOf(jCurrentTimeMillis), "pageView", "$pageview", mapDeepCopy, map2));
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    public void profileAppend(String str, Object obj) {
        if (AnalysysUtil.getContext() == null) {
            ah.a("profileAppend", false);
            return;
        }
        HashMap map = new HashMap();
        map.put(str, obj);
        profileAppend(map);
    }

    public void profileAppend(String str, List<Object> list) {
        if (AnalysysUtil.getContext() == null) {
            ah.a("profileAppend", false);
            return;
        }
        HashMap map = new HashMap();
        map.put(str, list);
        profileAppend(map);
    }

    public void profileAppend(final Map<String, Object> map) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        AThreadPool.asyncHighPriorityExecutor(new Runnable() {
            @Override
            public void run() {
                try {
                    Context context = AnalysysUtil.getContext();
                    if (context != null) {
                        AgentProcess.this.trackEvent(context, "profileAppend", "$profile_append", q.a(context).a(Long.valueOf(jCurrentTimeMillis), "profileAppend", "$profile_append", CommonUtils.deepCopy(map), null));
                    } else {
                        ah.a("profileAppend", false);
                    }
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    public void profileDelete() {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        AThreadPool.asyncHighPriorityExecutor(new Runnable() {
            @Override
            public void run() {
                try {
                    Context context = AnalysysUtil.getContext();
                    if (context == null) {
                        return;
                    }
                    AgentProcess.this.trackEvent(context, "profileDelete", "$profile_delete", q.a(context).a(Long.valueOf(jCurrentTimeMillis), "profileDelete", "$profile_delete", null, null));
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    public void profileIncrement(String str, Number number) {
        if (AnalysysUtil.getContext() == null) {
            ah.a("profileIncrement", false);
            return;
        }
        HashMap map = new HashMap();
        map.put(str, number);
        profileIncrement(map);
    }

    public void profileIncrement(final Map<String, ? extends Number> map) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        AThreadPool.asyncHighPriorityExecutor(new Runnable() {
            @Override
            public void run() {
                try {
                    Context context = AnalysysUtil.getContext();
                    if (context != null) {
                        AgentProcess.this.trackEvent(context, "profileIncrement", "$profile_increment", q.a(context).a(Long.valueOf(jCurrentTimeMillis), "profileIncrement", "$profile_increment", CommonUtils.deepCopy(map), null));
                    } else {
                        ah.a("profileIncrement", false);
                    }
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    public void profileSet(String str, Object obj) {
        HashMap map = new HashMap();
        map.put(str, obj);
        profileSet(map);
    }

    public void profileSet(final Map<String, Object> map) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        AThreadPool.asyncHighPriorityExecutor(new Runnable() {
            @Override
            public void run() {
                try {
                    Context context = AnalysysUtil.getContext();
                    if (context != null) {
                        AgentProcess.this.trackEvent(context, "profileSet", "$profile_set", q.a(context).a(Long.valueOf(jCurrentTimeMillis), "profileSet", "$profile_set", CommonUtils.deepCopy(map), null));
                    }
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    public void profileSetOnce(String str, Object obj) {
        if (AnalysysUtil.getContext() == null) {
            ah.a("profileSetOnce", false);
            return;
        }
        HashMap map = new HashMap();
        map.put(str, obj);
        profileSetOnce(map);
    }

    public void profileSetOnce(final Map<String, Object> map) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        AThreadPool.asyncHighPriorityExecutor(new Runnable() {
            @Override
            public void run() {
                try {
                    Context context = AnalysysUtil.getContext();
                    if (context != null) {
                        AgentProcess.this.trackEvent(context, "profileSetOnce", "$profile_set_once", q.a(context).a(Long.valueOf(jCurrentTimeMillis), "profileSetOnce", "$profile_set_once", CommonUtils.deepCopy(map), null));
                    } else {
                        ah.a("profileSetOnce", false);
                    }
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    public void profileUnset(final String str) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        AThreadPool.asyncHighPriorityExecutor(new Runnable() {
            @Override
            public void run() {
                try {
                    Context context = AnalysysUtil.getContext();
                    if (context == null || CommonUtils.isEmpty(str)) {
                        ah.a("profileUnset", false);
                    } else {
                        HashMap map = new HashMap();
                        map.put(str, "");
                        AgentProcess.this.trackEvent(context, "profileUnset", "$profile_unset", q.a(context).a(Long.valueOf(jCurrentTimeMillis), "profileUnset", "$profile_unset", map, null));
                    }
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    public void registerJsSuperProperties(Map<String, Object> map) {
        handleSuperProperties(map, "js_superProperty");
    }

    public void registerJsSuperProperty(String str, Object obj) {
        handleSuperProperty(str, obj, "js_superProperty");
    }

    public void registerSuperProperties(Map<String, Object> map) {
        handleSuperProperties(map, "superProperty");
    }

    public void registerSuperProperty(String str, Object obj) {
        handleSuperProperty(str, obj, "superProperty");
    }

    public void removeViewFromAutoClickBlackList(Object obj) {
        if (obj != null) {
            this.mIgnoreByView.remove(Integer.valueOf(obj.hashCode()));
        }
    }

    public void reset() {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        AThreadPool.asyncHighPriorityExecutor(new Runnable() {
            @Override
            public void run() {
                try {
                    Context context = AnalysysUtil.getContext();
                    if (context != null) {
                        AgentProcess.this.resetInfo(context);
                        ah.a("reset", true);
                        AgentProcess.this.sendProfileSetOnce(context, 1, jCurrentTimeMillis);
                    }
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                    ah.a("reset", false);
                }
            }
        });
    }

    public void resetAnalysysAgentHybrid(Object obj) {
        WebViewInjectManager.getInstance().clearHybrid(obj);
    }

    public void resetHybridModel(Object obj) {
        if (obj != null) {
            try {
                Object objInvoke = obj.getClass().getMethod("getSettings", new Class[0]).invoke(obj, new Object[0]);
                if (objInvoke != null) {
                    String str = (String) objInvoke.getClass().getMethod("getUserAgentString", new Class[0]).invoke(objInvoke, new Object[0]);
                    Method method = objInvoke.getClass().getMethod("setUserAgentString", String.class);
                    if (str != null && str.contains(" AnalysysAgent/Hybrid")) {
                        method.invoke(objInvoke, str.replace(" AnalysysAgent/Hybrid", ""));
                    }
                }
                if (this.mWebViewClientCache != null) {
                    this.mWebViewClientCache.remove(Integer.valueOf(obj.hashCode()));
                }
            } catch (Throwable th) {
                ExceptionUtil.exceptionThrow(th);
            }
        }
    }

    public void setAnalysysAgentHybrid(Object obj) {
        WebViewInjectManager.getInstance().injectHybridObject(obj);
    }

    public void setAutoClickBlackListByPages(List<String> list) {
        try {
            this.mIgnoreByPages.clear();
            if (list == null || list.size() <= 0) {
                return;
            }
            for (String str : list) {
                if (!TextUtils.isEmpty(str)) {
                    this.mIgnoreByPages.add(Integer.valueOf(str.hashCode()));
                }
            }
        } catch (Exception e) {
            ExceptionUtil.exceptionThrow(e);
        }
    }

    public void setAutoClickBlackListByView(Object obj) {
        if (obj != null) {
            this.mIgnoreByView.add(Integer.valueOf(obj.hashCode()));
        }
    }

    public void setAutoClickBlackListByViewTypes(List<Class> list) {
        try {
            this.mIgnoreByViewTypes.clear();
            if (list != null) {
                Iterator<Class> it = list.iterator();
                while (it.hasNext()) {
                    String name = it.next().getName();
                    if (!TextUtils.isEmpty(name)) {
                        this.mIgnoreByViewTypes.add(Integer.valueOf(name.hashCode()));
                    }
                }
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    public void setAutoClickWhiteListByPages(List<String> list) {
        try {
            this.mAutoByPages.clear();
            for (String str : list) {
                if (!TextUtils.isEmpty(str)) {
                    this.mAutoByPages.add(Integer.valueOf(str.hashCode()));
                }
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    public void setAutoClickWhiteListByView(Object obj) {
        if (obj != null) {
            this.mAutoByView.add(Integer.valueOf(obj.hashCode()));
        }
    }

    public void setAutoClickWhiteListByViewTypes(List<Class> list) {
        try {
            this.mAutoByByViewTypes.clear();
            if (list != null) {
                Iterator<Class> it = list.iterator();
                while (it.hasNext()) {
                    String name = it.next().getName();
                    if (!TextUtils.isEmpty(name)) {
                        this.mAutoByByViewTypes.add(Integer.valueOf(name.hashCode()));
                    }
                }
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    public void setDebug(final int i) {
        AThreadPool.asyncHighPriorityExecutor(new Runnable() {
            @Override
            public void run() {
                int i2;
                Context context = AnalysysUtil.getContext();
                if (context == null || (i2 = i) < 0 || 2 < i2) {
                    ANSLog.w("setDebugMode: set failed!");
                    return;
                }
                AgentProcess.this.debugResetUserInfo(context, i2);
                SharedUtil.setInt(context, "userDebug", i);
                if (i != 0) {
                    ANSLog.isShowLog = true;
                }
                ah.a("setDebugMode", true);
            }
        });
    }

    public void setEventObserver(IEventObserver iEventObserver) {
        this.mEventObserver = iEventObserver;
    }

    public void setHeatMapBlackListByPages(List<String> list) {
        s.a().a(list);
    }

    public void setHeatMapWhiteListByPages(List<String> list) {
        s.a().b(list);
    }

    public void setHybridModel(Object obj) {
        if (CommonUtils.isEmpty(obj)) {
            return;
        }
        try {
            Object objInvoke = obj.getClass().getMethod("getSettings", new Class[0]).invoke(obj, new Object[0]);
            if (objInvoke != null) {
                String str = (String) objInvoke.getClass().getMethod("getUserAgentString", new Class[0]).invoke(objInvoke, new Object[0]);
                Method method = objInvoke.getClass().getMethod("setUserAgentString", String.class);
                if (CommonUtils.isEmpty(str)) {
                    method.invoke(objInvoke, " AnalysysAgent/Hybrid");
                } else {
                    method.invoke(objInvoke, str + " AnalysysAgent/Hybrid");
                }
            }
            Object objInvokeMethod = AnsReflectUtils.invokeMethod(obj, "getWebViewClient");
            if (objInvokeMethod != null) {
                if (this.mWebViewClientCache == null) {
                    this.mWebViewClientCache = new HashMap();
                }
                this.mWebViewClientCache.put(Integer.valueOf(obj.hashCode()), objInvokeMethod);
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    public void setIntervalTime(final long j) {
        AThreadPool.asyncHighPriorityExecutor(new Runnable() {
            @Override
            public void run() {
                Context context = AnalysysUtil.getContext();
                if (context != null) {
                    long j2 = j;
                    if (1 <= j2) {
                        SharedUtil.setLong(context, "userIntervalTime", j2 * 1000);
                        ah.a("setIntervalTime", true);
                        return;
                    }
                }
                ah.a("setIntervalTime", "time must be > 1,otherwise use default.");
            }
        });
    }

    public void setLogObserverListener(LogObserverListener logObserverListener) {
        this.logObserverListener = logObserverListener;
    }

    public void setMaxCacheSize(final long j) {
        AThreadPool.asyncHighPriorityExecutor(new Runnable() {
            @Override
            public void run() {
                long j2 = j;
                if (j2 < 100 || j2 > 10000) {
                    ah.a("setMaxCacheSize", "count must be > 100 and <=10000,otherwise use default.");
                } else {
                    ah.a("setMaxCacheSize", true);
                    AnsRamControl.getInstance().setCacheMaxCount(j);
                }
            }
        });
    }

    public void setMaxEventSize(final long j) {
        AThreadPool.asyncHighPriorityExecutor(new Runnable() {
            @Override
            public void run() {
                Context context = AnalysysUtil.getContext();
                if (context != null) {
                    long j2 = j;
                    if (1 <= j2) {
                        SharedUtil.setLong(context, "userEventCount", j2);
                        ah.a("setMaxEventSize", true);
                        return;
                    }
                }
                ah.a("setMaxEventSize", "count must be > 1,otherwise use default.");
            }
        });
    }

    public void setObserverListener(ObserverListener observerListener) {
        i.a().a(observerListener);
    }

    public void setPageViewBlackListByPages(List<String> list) {
        try {
            this.mPageViewBlackListByPages.clear();
            if (list != null) {
                for (String str : list) {
                    if (!TextUtils.isEmpty(str)) {
                        this.mPageViewBlackListByPages.add(Integer.valueOf(str.hashCode()));
                    }
                }
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    public void setPageViewWhiteListByPages(List<String> list) {
        try {
            this.mPageViewWhiteListByPages.clear();
            if (list != null) {
                for (String str : list) {
                    if (!TextUtils.isEmpty(str)) {
                        this.mPageViewWhiteListByPages.add(Integer.valueOf(str.hashCode()));
                    }
                }
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    public void setUploadNetworkType(int i) {
        AnsRamControl.getInstance().setUploadNetworkType(i);
    }

    public void setUploadURL(final String str) {
        this.mUploadUrl = str;
        AThreadPool.asyncHighPriorityExecutor(new Runnable() {
            @Override
            public void run() {
                try {
                    Context context = AnalysysUtil.getContext();
                    if (context == null || CommonUtils.isEmpty(str)) {
                        return;
                    }
                    if (!str.startsWith("http://") && !str.startsWith("https://")) {
                        ah.a("setUploadURL", false);
                        return;
                    }
                    String strCheckUrl = CommonUtils.checkUrl(str);
                    if (CommonUtils.isEmpty(strCheckUrl)) {
                        ah.b(" Please check the upload URL.");
                        return;
                    }
                    AgentProcess.this.saveUploadUrl(context, strCheckUrl + "/up");
                } catch (Throwable unused) {
                }
            }
        });
    }

    public void setVisitorConfigURL(final String str) {
        this.mVisualConfigUrl = str;
        AThreadPool.asyncHighPriorityExecutor(new Runnable() {
            @Override
            public void run() {
                try {
                    Context context = AnalysysUtil.getContext();
                    if (context != null) {
                        t.b(context);
                        if (t.e != null) {
                            AgentProcess.this.setVisualUrl(context, t.e.optString("start"), str);
                        }
                    }
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    public void setVisitorDebugURL(final String str) {
        this.mVisualDebugUrl = str;
        AThreadPool.asyncHighPriorityExecutor(new Runnable() {
            @Override
            public void run() {
                try {
                    Context context = AnalysysUtil.getContext();
                    if (context != null) {
                        t.b(context);
                        if (t.d != null) {
                            AgentProcess.this.setVisualUrl(context, t.d.optString("start"), str);
                        }
                    }
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    public void track(String str, Map<String, Object> map) {
        AThreadPool.asyncLowPriorityExecutor(getTrackRunnable(str, map, System.currentTimeMillis()));
    }

    public void trackCampaign(final String str, final boolean z, final PushListener pushListener) {
        AThreadPool.asyncHighPriorityExecutor(new Runnable() {
            @Override
            public void run() {
                try {
                    Context context = AnalysysUtil.getContext();
                    if (context != null) {
                        t.c(context);
                        if (t.g != null) {
                            String strOptString = t.g.optString("start");
                            CommonUtils.reflexUtils(CommonUtils.getClassPath(strOptString), CommonUtils.getMethod(strOptString), new Class[]{Context.class, String.class, Boolean.TYPE, PushListener.class}, context, str, Boolean.valueOf(z), pushListener);
                        }
                    }
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    public void trackSync(String str, Map<String, Object> map) {
        getTrackRunnable(str, map, System.currentTimeMillis()).run();
    }

    public void unregisterJsSuperProperty(String str) {
        handleSuperProperty(str, "js_superProperty");
    }

    public void unregisterSuperProperty(String str) {
        handleSuperProperty(str, "superProperty");
    }
}
