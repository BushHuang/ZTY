package com.analysys;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.analysys.network.NetworkUtils;
import com.analysys.process.AgentProcess;
import com.analysys.strategy.PolicyManager;
import com.analysys.utils.ANSLog;
import com.analysys.utils.AnalysysUtil;
import com.analysys.utils.CommonUtils;
import com.analysys.utils.Constants;
import com.analysys.utils.ExceptionUtil;
import com.analysys.utils.SharedUtil;
import java.util.Map;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class p {

    private Context f66a;
    private b b;
    private int c;
    private int d;
    private int e;
    private String f;

    static class a {

        public static final p f67a = new p();
    }

    class b extends Handler {
        private b(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message message) {
            try {
                String url = CommonUtils.getUrl(p.this.f66a);
                if (CommonUtils.isEmpty(url)) {
                    ah.b(" Please check the upload URL.");
                } else {
                    int i = message.what;
                    if (i == p.this.c || i == p.this.d) {
                        p.this.a(url);
                    } else if (i == p.this.e) {
                        p.this.b(url);
                    }
                }
            } catch (Throwable th) {
                ExceptionUtil.exceptionThrow(th);
            }
        }
    }

    private p() {
        this.c = 1;
        this.d = 2;
        this.e = 3;
        this.f = "";
        HandlerThread handlerThread = new HandlerThread("send_data_thread", 1);
        handlerThread.start();
        this.b = new b(handlerThread.getLooper());
    }

    public static p a(Context context) {
        if (a.f67a.f66a == null && context != null) {
            a.f67a.f66a = context;
        }
        return a.f67a;
    }

    private String a(String str, int i) {
        Object objReflexUtils;
        try {
            if (t.b == null) {
                return null;
            }
            String strOptString = t.b.optString("start");
            if (TextUtils.isEmpty(strOptString) || (objReflexUtils = CommonUtils.reflexUtils(CommonUtils.getClassPath(strOptString), CommonUtils.getMethod(strOptString), new Class[]{String.class, String.class, String.class, Integer.TYPE}, CommonUtils.getAppKey(this.f66a), "4.5.2", str, Integer.valueOf(i))) == null) {
                return null;
            }
            return String.valueOf(objReflexUtils);
        } catch (Throwable unused) {
            return null;
        }
    }

    private JSONArray a(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null) {
            return null;
        }
        JSONArray jSONArray2 = new JSONArray();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObjectA = ad.a(jSONArray.optJSONObject(i));
            if (jSONObjectA != null) {
                jSONObjectA.put("xwhen", b(jSONObjectA.optLong("xwhen")));
                JSONObject jSONObjectOptJSONObject = jSONObjectA.optJSONObject("xcontext");
                if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject.has("$is_time_calibrated")) {
                    jSONObjectOptJSONObject.put("$is_time_calibrated", Constants.isCalibration);
                }
            }
            jSONArray2.put(jSONObjectA);
        }
        return jSONArray2;
    }

    private void a(String str) throws JSONException {
        if (!NetworkUtils.isUploadPolicy(NetworkUtils.networkType(AnalysysUtil.getContext(), false), AgentProcess.getInstance().getUploadNetworkType())) {
            ANSLog.d("网络类型控制不上传");
            return;
        }
        if (Constants.isTimeCheck && !Constants.isFinishCalibration) {
            ANSLog.d("时间校准未完成,等时间校准之后数据发送");
            return;
        }
        if (!NetworkUtils.isNetworkAvailable(this.f66a)) {
            ah.c();
            return;
        }
        JSONArray jSONArrayA = a(g.a(this.f66a).a());
        if (CommonUtils.isEmpty(jSONArrayA)) {
            g.a(this.f66a).c();
        } else {
            ah.a(str, jSONArrayA);
            a(str, String.valueOf(jSONArrayA));
        }
    }

    private void a(String str, String str2) {
        if (CommonUtils.isEmpty(this.f)) {
            this.f = CommonUtils.getSpvInfo(this.f66a);
        }
        Map<String, String> mapE = null;
        if (Constants.encryptType != 0) {
            String strA = a(str2, Constants.encryptType);
            if (!TextUtils.isEmpty(strA)) {
                mapE = e();
                if (!CommonUtils.isEmpty(mapE)) {
                    ah.a(true);
                    str2 = strA;
                }
            }
        }
        a(str, CommonUtils.messageZip(str2), mapE);
    }

    private void a(String str, String str2, Map<String, String> map) {
        try {
            a(c(str.startsWith("http://") ? o.a(str, str2, this.f, map) : o.a(this.f66a, str, str2, this.f, map)));
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    private void a(JSONObject jSONObject) {
        try {
            if (CommonUtils.isEmpty(jSONObject)) {
                f();
                return;
            }
            int iOptInt = jSONObject.optInt("code", -1);
            if (iOptInt != 200 && iOptInt != 4200 && iOptInt != 400) {
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("policy");
                if (!CommonUtils.isEmpty(jSONObjectOptJSONObject)) {
                    String string = SharedUtil.getString(this.f66a, "serviceHash", null);
                    if (CommonUtils.isEmpty(string) || (jSONObjectOptJSONObject != null && !string.equals(jSONObjectOptJSONObject.optString("hash")))) {
                        PolicyManager.analysysStrategy(this.f66a, jSONObjectOptJSONObject);
                    }
                }
                ah.c(false);
                f();
                return;
            }
            g();
            g.a(this.f66a).c();
            SharedUtil.setLong(this.f66a, "uploadTime", System.currentTimeMillis());
            SharedUtil.setBoolean(this.f66a, "sendSuccess", true);
            ah.c(true);
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
            try {
                f();
            } catch (Throwable th2) {
                ExceptionUtil.exceptionThrow(th2);
            }
        }
    }

    private long b(long j) {
        return (Constants.isTimeCheck && Constants.isCalibration) ? j + Constants.diffTime : j;
    }

    private void b(String str) {
        long jA = o.a(str);
        if (jA != 0) {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                long j = jA - jCurrentTimeMillis;
                long jAbs = Math.abs(j);
                if (jAbs > Constants.ignoreDiffTime) {
                    Constants.diffTime = j;
                    i.a().a(j);
                    SharedUtil.setString(this.f66a, "diffTime", Long.toString(j));
                    Constants.isCalibration = true;
                    ah.a(jA, jCurrentTimeMillis, jAbs);
                }
            } catch (Throwable th) {
                ExceptionUtil.exceptionThrow(th);
            }
        }
        Constants.isFinishCalibration = true;
    }

    private JSONObject c(String str) {
        try {
            try {
                if (CommonUtils.isEmpty(str)) {
                    return null;
                }
                String strMessageUnzip = CommonUtils.messageUnzip(str);
                if (TextUtils.isEmpty(strMessageUnzip)) {
                    return new JSONObject(str);
                }
                ah.c(strMessageUnzip);
                return new JSONObject(strMessageUnzip);
            } catch (Throwable unused) {
                return null;
            }
        } catch (Throwable unused2) {
            return new JSONObject(str);
        }
    }

    private void c() {
        if (AgentProcess.getInstance().getMaxCacheSize() <= g.a(this.f66a).b()) {
            g.a(this.f66a).a(10);
        }
    }

    private void d() {
        if (this.b.hasMessages(this.d)) {
            this.b.removeMessages(this.c);
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = this.c;
        this.b.sendMessage(messageObtain);
    }

    private Map<String, String> e() {
        if (t.f79a == null) {
            return null;
        }
        String strOptString = t.f79a.optString("start");
        if (TextUtils.isEmpty(strOptString)) {
            return null;
        }
        int iLastIndexOf = strOptString.lastIndexOf(".");
        Object objReflexUtils = CommonUtils.reflexUtils(strOptString.substring(0, iLastIndexOf), strOptString.substring(iLastIndexOf + 1));
        if (objReflexUtils != null) {
            return (Map) objReflexUtils;
        }
        return null;
    }

    private synchronized void f() {
        int i = SharedUtil.getInt(this.f66a, "failureCount", 0);
        long jI = i();
        if (i < j()) {
            SharedUtil.setInt(this.f66a, "failureCount", i + 1);
            long j = SharedUtil.getLong(this.f66a, "failureTime", -1L);
            if (j == 0) {
                a(jI + h());
            } else {
                long jAbs = Math.abs(System.currentTimeMillis() - j);
                if (jAbs > jI) {
                    d();
                } else {
                    a((jI - jAbs) + h());
                }
            }
        } else {
            SharedUtil.remove(this.f66a, "failureCount");
            SharedUtil.setLong(this.f66a, "failureTime", System.currentTimeMillis());
        }
        SharedUtil.setLong(this.f66a, "failureTime", System.currentTimeMillis());
    }

    private void g() {
        SharedUtil.remove(this.f66a, "failureCount");
        SharedUtil.remove(this.f66a, "failureTime");
    }

    private int h() {
        return new Random().nextInt(20000) + 10000;
    }

    private long i() {
        return SharedUtil.getLong(this.f66a, "failTryDelay", 30000L);
    }

    private long j() {
        return SharedUtil.getLong(this.f66a, "failCount", 3L);
    }

    public void a() {
        if (!CommonUtils.isMainProcess(this.f66a)) {
            ah.a();
            return;
        }
        long j = SharedUtil.getLong(this.f66a, "policyNo", -1L);
        if (j == -1 || j == 1) {
            d();
        }
    }

    public void a(long j) {
        if (this.b.hasMessages(this.d)) {
            this.b.removeMessages(this.d);
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = this.c;
        this.b.sendMessageDelayed(messageObtain, j);
    }

    public void a(String str, JSONObject jSONObject) {
        if (CommonUtils.isEmpty(jSONObject)) {
            return;
        }
        c();
        g.a(this.f66a).a(jSONObject.toString(), str);
        if (!CommonUtils.isMainProcess(this.f66a)) {
            ah.a();
        } else if (PolicyManager.getPolicyType(this.f66a).a(this.f66a)) {
            d();
        }
    }

    public void b() {
        if (this.b.hasMessages(this.e)) {
            return;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = this.e;
        this.b.sendMessage(messageObtain);
    }
}
