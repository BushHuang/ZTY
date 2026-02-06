package com.analysys;

import android.content.Context;
import android.view.View;
import com.analysys.process.AgentProcess;
import com.analysys.push.PushListener;
import com.analysys.utils.AThreadPool;
import com.analysys.utils.AnalysysUtil;
import com.analysys.utils.Constants;
import com.analysys.utils.ExceptionUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class AnalysysAgent {
    private static List<String> IgnoredAcName = new ArrayList();

    public interface AnalysysNetworkType {
        public static final int AnalysysNetworkALL = 255;
        public static final int AnalysysNetworkNONE = 0;
        public static final int AnalysysNetworkWIFI = 4;
        public static final int AnalysysNetworkWWAN = 2;
    }

    public static void alias(Context context, String str) {
        if (ag.a("alias")) {
            AgentProcess.getInstance().alias(str);
        }
    }

    @Deprecated
    public static void alias(Context context, String str, String str2) {
        if (ag.a("alias")) {
            AgentProcess.getInstance().alias(str, str2);
        }
    }

    public static void cleanDBCache() {
        AThreadPool.asyncHighPriorityExecutor(new Runnable() {
            @Override
            public void run() {
                Context context = AnalysysUtil.getContext();
                if (context != null) {
                    g.a(context).d();
                }
            }
        });
    }

    public static void clearSuperProperties(Context context) {
        AgentProcess.getInstance().clearSuperProperty();
    }

    public static void flush(Context context) {
        if (ag.a("flush")) {
            AgentProcess.getInstance().flush();
        }
    }

    @Deprecated
    public static boolean getAutomaticCollection(Context context) {
        return AgentProcess.getInstance().getConfig().isAutoTrackPageView();
    }

    public static String getDistinctId(Context context) {
        return (String) AThreadPool.syncHighPriorityExecutor(new Callable() {
            @Override
            public Object call() {
                return AgentProcess.getInstance().getDistinctId();
            }
        });
    }

    @Deprecated
    public static List<String> getIgnoredAutomaticCollection(Context context) {
        return IgnoredAcName;
    }

    public static long getMaxCacheSize(Context context) {
        Object objSyncHighPriorityExecutor = AThreadPool.syncHighPriorityExecutor(new Callable() {
            @Override
            public Object call() {
                return Long.valueOf(AgentProcess.getInstance().getMaxCacheSize());
            }
        });
        return objSyncHighPriorityExecutor != null ? ((Long) objSyncHighPriorityExecutor).longValue() : AgentProcess.getInstance().getMaxCacheSize();
    }

    public static Map<String, Object> getPresetProperties(Context context) {
        return (Map) AThreadPool.syncHighPriorityExecutor(new Callable() {
            @Override
            public Object call() {
                return AgentProcess.getInstance().getPresetProperties();
            }
        });
    }

    public static Map<String, Object> getSuperProperties(Context context) {
        return (Map) AThreadPool.syncHighPriorityExecutor(new Callable() {
            @Override
            public Object call() {
                return AgentProcess.getInstance().getSuperProperty();
            }
        });
    }

    public static Object getSuperProperty(Context context, final String str) {
        return AThreadPool.syncHighPriorityExecutor(new Callable() {
            @Override
            public Object call() {
                return AgentProcess.getInstance().getSuperProperty(str);
            }
        });
    }

    public static void identify(Context context, String str) {
        AgentProcess.getInstance().identify(str);
    }

    public static void init(Context context, AnalysysConfig analysysConfig) {
        AgentProcess.getInstance().init(context, analysysConfig);
    }

    public static void interceptUrl(Context context, String str, Object obj) {
        if (ag.a("interceptUrl")) {
            AgentProcess.getInstance().interceptUrl(str, obj);
        }
    }

    public static void launchSource(int i) {
        Constants.sourceNum = i;
    }

    public static void pageView(Context context, String str) {
        if (ag.a("pageView")) {
            AgentProcess.getInstance().pageView(context, str, null);
        }
    }

    public static void pageView(Context context, String str, Map<String, Object> map) {
        if (ag.a("pageView")) {
            AgentProcess.getInstance().pageView(context, str, map);
        }
    }

    public static void profileAppend(Context context, String str, Object obj) {
        if (ag.a("profileAppend")) {
            AgentProcess.getInstance().profileAppend(str, obj);
        }
    }

    public static void profileAppend(Context context, String str, List<Object> list) {
        if (ag.a("profileAppend")) {
            AgentProcess.getInstance().profileAppend(str, list);
        }
    }

    public static void profileAppend(Context context, Map<String, Object> map) {
        if (ag.a("profileAppend")) {
            AgentProcess.getInstance().profileAppend(map);
        }
    }

    public static void profileDelete(Context context) {
        if (ag.a("profileDelete")) {
            AgentProcess.getInstance().profileDelete();
        }
    }

    public static void profileIncrement(Context context, String str, Number number) {
        if (ag.a("profileIncrement")) {
            AgentProcess.getInstance().profileIncrement(str, number);
        }
    }

    public static void profileIncrement(Context context, Map<String, Number> map) {
        if (ag.a("profileIncrement")) {
            AgentProcess.getInstance().profileIncrement(map);
        }
    }

    public static void profileSet(Context context, String str, Object obj) {
        if (ag.a("profileSet")) {
            AgentProcess.getInstance().profileSet(str, obj);
        }
    }

    public static void profileSet(Context context, Map<String, Object> map) {
        if (ag.a("profileSet")) {
            AgentProcess.getInstance().profileSet(map);
        }
    }

    public static void profileSetOnce(Context context, String str, Object obj) {
        if (ag.a("profileSetOnce")) {
            AgentProcess.getInstance().profileSetOnce(str, obj);
        }
    }

    public static void profileSetOnce(Context context, Map<String, Object> map) {
        if (ag.a("profileSetOnce")) {
            AgentProcess.getInstance().profileSetOnce(map);
        }
    }

    public static void profileUnset(Context context, String str) {
        if (ag.a("profileUnset")) {
            AgentProcess.getInstance().profileUnset(str);
        }
    }

    public static void registerSuperProperties(Context context, Map<String, Object> map) {
        AgentProcess.getInstance().registerSuperProperties(map);
    }

    public static void registerSuperProperty(Context context, String str, Object obj) {
        AgentProcess.getInstance().registerSuperProperty(str, obj);
    }

    public static void reportException(Context context, Throwable th) {
        if (ag.a("reportException")) {
            af.a().a(context, th, 1);
        }
    }

    public static void reset(Context context) {
        if (ag.a("reset")) {
            AgentProcess.getInstance().reset();
        }
    }

    public static void resetAnalysysAgentHybrid(Object obj) {
        AgentProcess.getInstance().resetAnalysysAgentHybrid(obj);
    }

    public static void resetHybridModel(Context context, Object obj) {
        if (ag.a("resetHybridModel")) {
            AgentProcess.getInstance().resetHybridModel(obj);
        }
    }

    public static void setAnalysysAgentHybrid(Object obj) {
        AgentProcess.getInstance().setAnalysysAgentHybrid(obj);
    }

    public static void setAnsViewID(View view, String str) {
        try {
            System.out.println(Class.forName("com.analysys.allgro.AllegroUtils").getMethod("setViewIdResourceName", View.class, String.class).invoke(null, view, str));
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    public static void setAutoClickBlackListByPages(List<String> list) {
        AgentProcess.getInstance().setAutoClickBlackListByPages(list);
    }

    public static void setAutoClickBlackListByView(View view) {
        AgentProcess.getInstance().setAutoClickBlackListByView(view);
    }

    public static void setAutoClickBlackListByViewTypes(List<Class> list) {
        AgentProcess.getInstance().setAutoClickBlackListByViewTypes(list);
    }

    public static void setAutoClickWhiteListByPages(List<String> list) {
        AgentProcess.getInstance().setAutoClickWhiteListByPages(list);
    }

    public static void setAutoClickWhiteListByView(View view) {
        AgentProcess.getInstance().setAutoClickWhiteListByView(view);
    }

    public static void setAutoClickWhiteListByViewTypes(List<Class> list) {
        AgentProcess.getInstance().setAutoClickWhiteListByViewTypes(list);
    }

    @Deprecated
    public static void setAutoHeatMap(boolean z) {
        AgentProcess.getInstance().getConfig().setAutoHeatMap(z);
    }

    @Deprecated
    public static void setAutomaticCollection(Context context, boolean z) {
        AgentProcess.getInstance().getConfig().setAutoTrackPageView(z);
    }

    public static void setDebugMode(Context context, int i) {
        AgentProcess.getInstance().setDebug(i);
    }

    public static void setHeatMapBlackListByPages(List<String> list) {
        AgentProcess.getInstance().setHeatMapBlackListByPages(list);
    }

    public static void setHeatMapWhiteListByPages(List<String> list) {
        AgentProcess.getInstance().setHeatMapWhiteListByPages(list);
    }

    public static void setHybridModel(Context context, Object obj) {
        if (ag.a("setHybridModel")) {
            AgentProcess.getInstance().setHybridModel(obj);
        }
    }

    @Deprecated
    public static void setIgnoredAutomaticCollectionActivities(Context context, List<String> list) {
        IgnoredAcName = list;
        AgentProcess.getInstance().setPageViewBlackListByPages(list);
    }

    public static void setIntervalTime(Context context, long j) {
        AgentProcess.getInstance().setIntervalTime(j);
    }

    public static void setMaxCacheSize(Context context, long j) {
        AgentProcess.getInstance().setMaxCacheSize(j);
    }

    public static void setMaxEventSize(Context context, long j) {
        AgentProcess.getInstance().setMaxEventSize(j);
    }

    public static void setObserverListener(Context context, ObserverListener observerListener) {
        AgentProcess.getInstance().setObserverListener(observerListener);
    }

    public static void setPageViewBlackListByPages(List<String> list) {
        AgentProcess.getInstance().setPageViewBlackListByPages(list);
    }

    public static void setPageViewWhiteListByPages(List<String> list) {
        AgentProcess.getInstance().setPageViewWhiteListByPages(list);
    }

    public static void setPushID(Context context, String str, String str2) {
        if (ag.a("setPushID")) {
            AgentProcess.getInstance().enablePush(str, str2);
        }
    }

    public static void setUploadNetworkType(int i) {
        AgentProcess.getInstance().setUploadNetworkType(i);
    }

    public static void setUploadURL(Context context, String str) {
        AgentProcess.getInstance().setUploadURL(str);
    }

    public static void setVisitorConfigURL(Context context, String str) {
        if (ag.a("setVisitorConfigURL")) {
            AgentProcess.getInstance().setVisitorConfigURL(str);
        }
    }

    public static void setVisitorDebugURL(Context context, String str) {
        if (ag.a("setVisitorDebugURL")) {
            AgentProcess.getInstance().setVisitorDebugURL(str);
        }
    }

    public static void track(Context context, String str) {
        if (ag.a("track")) {
            AgentProcess.getInstance().track(str, null);
        }
    }

    public static void track(Context context, String str, Map<String, Object> map) {
        if (ag.a("track")) {
            AgentProcess.getInstance().track(str, map);
        }
    }

    public static void trackCampaign(Context context, String str, boolean z) {
        if (ag.a("trackCampaign")) {
            AgentProcess.getInstance().trackCampaign(str, z, null);
        }
    }

    public static void trackCampaign(Context context, String str, boolean z, PushListener pushListener) {
        if (ag.a("trackCampaign")) {
            AgentProcess.getInstance().trackCampaign(str, z, pushListener);
        }
    }

    public static void unRegisterSuperProperty(Context context, String str) {
        AgentProcess.getInstance().unregisterSuperProperty(str);
    }
}
