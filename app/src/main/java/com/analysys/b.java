package com.analysys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.ViewTreeObserver;
import com.analysys.network.NetworkUtils;
import com.analysys.process.AgentProcess;
import com.analysys.utils.AThreadPool;
import com.analysys.utils.ActivityLifecycleUtils;
import com.analysys.utils.AnalysysUtil;
import com.analysys.utils.CommonUtils;
import com.analysys.utils.Constants;
import com.analysys.utils.ExceptionUtil;
import com.analysys.utils.SharedUtil;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class b extends ActivityLifecycleUtils.BaseLifecycleCallback {

    private String f35a = null;
    private Context b = null;
    private boolean c = false;
    private ViewTreeObserver.OnGlobalLayoutListener d;
    private ViewTreeObserver.OnScrollChangedListener e;
    private HandlerThread f;
    private Handler g;

    public b() {
        HandlerThread handlerThread = new HandlerThread("WorkThread");
        this.f = handlerThread;
        handlerThread.start();
        this.g = new Handler(this.f.getLooper()) {
            @Override
            public void handleMessage(Message message) {
                try {
                    int i = message.what;
                    if (i == 1) {
                        b.this.a(message);
                    } else if (i == 2 && SharedUtil.getInt(AnalysysUtil.getContext(), "page_count", 0) != 0) {
                        b.this.a();
                        sendEmptyMessageDelayed(2, 10000L);
                    }
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        };
    }

    private String a(Context context) {
        return SharedUtil.getString(context, Constants.SP_REFER, "");
    }

    private Map<String, Object> a(Activity activity) {
        Map<String, Object> map;
        String strRegisterPageUrl;
        String strValueOf;
        if (activity instanceof ANSAutoPageTracker) {
            ANSAutoPageTracker aNSAutoPageTracker = (ANSAutoPageTracker) activity;
            map = CommonUtils.deepCopy(aNSAutoPageTracker.registerPageProperties());
            strRegisterPageUrl = aNSAutoPageTracker.registerPageUrl();
        } else {
            map = new HashMap<>();
            strRegisterPageUrl = null;
        }
        if (!CommonUtils.isEmpty(strRegisterPageUrl)) {
            map.put("$url", strRegisterPageUrl);
        }
        if (map.containsKey("$url")) {
            strValueOf = String.valueOf(map.get("$url"));
        } else {
            strValueOf = activity.getClass().getCanonicalName();
            map.put("$url", strValueOf);
        }
        String strA = a(activity.getApplicationContext());
        if (!CommonUtils.isEmpty(strA)) {
            map.put("$referrer", strA);
        }
        a(activity.getApplicationContext(), strValueOf);
        return map;
    }

    private void a() {
        try {
            JSONObject jSONObject = new JSONObject();
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            String string = SharedUtil.getString(this.b, "app_start_time", "");
            long j = 0;
            long j2 = !TextUtils.isEmpty(string) ? CommonUtils.parseLong(string, 0L) : 0L;
            long j3 = jElapsedRealtime - j2;
            if (j3 >= 0 && j2 > 0) {
                j = j3;
            }
            jSONObject.put("$duration", j);
            jSONObject.put("$network", NetworkUtils.networkType(this.b, true));
            jSONObject.put("$is_first_day", CommonUtils.isFirstDay(this.b));
            jSONObject.put("$is_time_calibrated", true);
            jSONObject.put("$is_login", ab.d());
            jSONObject.put("$session_id", v.a(this.b).a());
            SharedUtil.setString(this.b, "app_end_info", new String(Base64.encode(String.valueOf(jSONObject).getBytes(), 2)));
            SharedUtil.setString(this.b, "last_op_time", String.valueOf(System.currentTimeMillis()));
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    private void a(Activity activity, long j) {
        if (activity == null || !a(activity.getClass().getName())) {
            return;
        }
        Map<String, Object> mapA = a(activity);
        if (!mapA.containsKey("$url")) {
            String canonicalName = activity.getClass().getCanonicalName();
            if (!TextUtils.isEmpty(canonicalName)) {
                mapA.put("$url", canonicalName);
            }
        }
        if (!mapA.containsKey("$title")) {
            mapA.put("$title", activity.getTitle());
        }
        AgentProcess.getInstance().autoCollectPageView(mapA, j);
    }

    private void a(Context context, long j, long j2) {
        if (SharedUtil.getInt(AnalysysUtil.getContext(), "page_count", 0) == 0) {
            this.g.removeMessages(1);
            if (c(context)) {
                a(e());
                AgentProcess.getInstance().appStart(this.c, j2);
                SharedUtil.setString(context, "app_start_time", String.valueOf(j));
                if (!this.c) {
                    this.c = true;
                }
            }
            this.g.sendEmptyMessageDelayed(2, 200L);
        }
        c();
    }

    private void a(Context context, Intent intent) {
        Constants.utm = h.a(intent);
        v.a(context).a(!CommonUtils.isEmpty(Constants.utm));
    }

    private void a(Context context, String str) {
        SharedUtil.setString(context, Constants.SP_REFER, str);
    }

    private void a(Message message) {
        try {
            Bundle data = message.getData();
            if (data != null) {
                String string = data.getString("last_op_time");
                String string2 = data.getString("app_end_info");
                if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
                    return;
                }
                try {
                    AgentProcess.getInstance().appEnd(string, new JSONObject(string2));
                    SharedUtil.remove(AnalysysUtil.getContext(), "app_end_info");
                    b(this.b);
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        } catch (Throwable th2) {
            ExceptionUtil.exceptionThrow(th2);
        }
    }

    private void a(final WeakReference<Activity> weakReference) {
        this.d = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Activity activity;
                WeakReference weakReference2 = weakReference;
                if (weakReference2 == null || (activity = (Activity) weakReference2.get()) == null) {
                    return;
                }
                try {
                    s.a().a(activity);
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
                s.b();
            }
        };
        this.e = new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                s.b();
            }
        };
    }

    private void a(WeakReference<Activity> weakReference, boolean z) {
        View viewFindViewById;
        if (weakReference == null || (viewFindViewById = weakReference.get().findViewById(16908290)) == null || viewFindViewById.getViewTreeObserver() == null || !viewFindViewById.getViewTreeObserver().isAlive()) {
            return;
        }
        if (this.d != null) {
            if (z) {
                viewFindViewById.getViewTreeObserver().addOnGlobalLayoutListener(this.d);
            } else if (Build.VERSION.SDK_INT > 15) {
                viewFindViewById.getViewTreeObserver().removeOnGlobalLayoutListener(this.d);
            }
        }
        if (this.e != null) {
            if (z) {
                viewFindViewById.getViewTreeObserver().addOnScrollChangedListener(this.e);
            } else {
                viewFindViewById.getViewTreeObserver().removeOnScrollChangedListener(this.e);
            }
        }
    }

    private boolean a(String str) {
        AgentProcess agentProcess = AgentProcess.getInstance();
        if (!agentProcess.getConfig().isAutoTrackPageView() || agentProcess.isThisPageInPageViewBlackList(str)) {
            return false;
        }
        if (agentProcess.hasAutoPageViewWhiteList()) {
            return agentProcess.isThisPageInPageViewWhiteList(str);
        }
        return true;
    }

    private void b() {
        d();
        if (SharedUtil.getInt(AnalysysUtil.getContext(), "page_count", 0) < 1) {
            this.g.removeMessages(2);
            a();
            this.g.sendMessageDelayed(e(), 30000L);
        }
    }

    private void b(Context context) {
        SharedUtil.setString(context, Constants.SP_REFER, "");
    }

    private void b(final WeakReference<Activity> weakReference) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        final long jElapsedRealtime = SystemClock.elapsedRealtime();
        AThreadPool.asyncMiddlePriorityExecutor(new Runnable() {
            @Override
            public void run() {
                Activity activity;
                try {
                    if (weakReference == null || (activity = (Activity) weakReference.get()) == null) {
                        return;
                    }
                    Context applicationContext = activity.getApplicationContext();
                    b.this.f35a = activity.getFilesDir().getAbsolutePath();
                    b.this.a(applicationContext, activity.getIntent());
                    SharedUtil.setString(activity.getApplicationContext(), "pageEndTime", String.valueOf(System.currentTimeMillis()));
                    b.this.a(applicationContext, jElapsedRealtime, jCurrentTimeMillis - 1);
                    b.this.a(activity, jCurrentTimeMillis);
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    private synchronized void c() {
        SharedUtil.setInt(AnalysysUtil.getContext(), "page_count", SharedUtil.getInt(AnalysysUtil.getContext(), "page_count", 0) + 1);
    }

    private void c(WeakReference<Activity> weakReference) {
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        if (AgentProcess.getInstance().getConfig().isAutoHeatMap()) {
            a(weakReference, false);
        }
        AThreadPool.asyncMiddlePriorityExecutor(new Runnable() {
            @Override
            public void run() {
                try {
                    b.this.b();
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    private static boolean c(Context context) {
        String string = SharedUtil.getString(context, "last_op_time", "");
        long jAbs = !CommonUtils.isEmpty(string) ? Math.abs(CommonUtils.parseLong(string, 0L) - System.currentTimeMillis()) : 0L;
        return jAbs == 0 || jAbs > 30000;
    }

    private synchronized void d() {
        int i = 0;
        int i2 = SharedUtil.getInt(AnalysysUtil.getContext(), "page_count", 0) - 1;
        if (i2 >= 0) {
            i = i2;
        }
        SharedUtil.setInt(AnalysysUtil.getContext(), "page_count", i);
    }

    private Message e() {
        Message messageObtain = Message.obtain();
        messageObtain.what = 1;
        Bundle bundle = new Bundle();
        String string = SharedUtil.getString(this.b, "app_end_info", "");
        if (!TextUtils.isEmpty(string)) {
            bundle.putString("app_end_info", new String(Base64.decode(string.getBytes(), 0)));
        }
        String string2 = SharedUtil.getString(this.b, "last_op_time", "");
        if (!TextUtils.isEmpty(string2)) {
            bundle.putString("last_op_time", string2);
        }
        messageObtain.setData(bundle);
        return messageObtain;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        AnalysysConfig config = AgentProcess.getInstance().getConfig();
        if (config.isAutoTrackClick()) {
            AnalysysUtil.onActivityCreated(activity);
        }
        if (config.isAutoHeatMap()) {
            a(new WeakReference<>(activity));
        }
    }

    @Override
    public void onActivityPaused(final Activity activity) {
        AThreadPool.asyncMiddlePriorityExecutor(new Runnable() {
            @Override
            public void run() {
                SharedUtil.setString(activity.getApplicationContext(), "pageEndTime", String.valueOf(System.currentTimeMillis()));
            }
        });
    }

    @Override
    public void onActivityResumed(Activity activity) {
        if (AgentProcess.getInstance().getConfig().isAutoHeatMap()) {
            a(new WeakReference<>(activity), true);
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {
        if (this.b == null) {
            this.b = activity.getApplicationContext();
        }
        b(new WeakReference<>(activity));
    }

    @Override
    public void onActivityStopped(Activity activity) {
        c(new WeakReference<>(activity));
    }
}
