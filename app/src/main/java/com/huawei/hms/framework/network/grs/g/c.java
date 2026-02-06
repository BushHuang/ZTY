package com.huawei.hms.framework.network.grs.g;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.framework.network.grs.h.d;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONArray;

public class c {
    private static final String n = "c";

    private final GrsBaseInfo f268a;
    private final Context b;
    private final com.huawei.hms.framework.network.grs.e.a c;
    private d d;
    private final com.huawei.hms.framework.network.grs.g.k.c j;
    private com.huawei.hms.framework.network.grs.g.k.d k;
    private final Map<String, Future<d>> e = new ConcurrentHashMap(16);
    private final List<d> f = new CopyOnWriteArrayList();
    private final JSONArray g = new JSONArray();
    private final List<String> h = new CopyOnWriteArrayList();
    private final List<String> i = new CopyOnWriteArrayList();
    private String l = "";
    private long m = 1;

    class a implements Callable<d> {

        final ExecutorService f269a;
        final String b;
        final com.huawei.hms.framework.network.grs.e.c c;

        a(ExecutorService executorService, String str, com.huawei.hms.framework.network.grs.e.c cVar) {
            this.f269a = executorService;
            this.b = str;
            this.c = cVar;
        }

        @Override
        public d call() {
            return c.this.b(this.f269a, this.b, this.c);
        }
    }

    public c(com.huawei.hms.framework.network.grs.g.k.c cVar, com.huawei.hms.framework.network.grs.e.a aVar) {
        this.j = cVar;
        this.f268a = cVar.b();
        this.b = cVar.a();
        this.c = aVar;
        c();
        d();
    }

    private d a(ExecutorService executorService, List<String> list, String str, com.huawei.hms.framework.network.grs.e.c cVar) throws ExecutionException, InterruptedException, TimeoutException {
        d dVar;
        d dVar2 = null;
        int i = 0;
        while (true) {
            if (i >= list.size()) {
                break;
            }
            String str2 = list.get(i);
            boolean z = true;
            if (!TextUtils.isEmpty(str2)) {
                Future<d> futureSubmit = executorService.submit(new com.huawei.hms.framework.network.grs.g.a(str2, i, this, this.b, str, this.f268a, cVar).g());
                this.e.put(str2, futureSubmit);
                try {
                    dVar = futureSubmit.get(this.m, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e = e;
                } catch (CancellationException unused) {
                } catch (ExecutionException e2) {
                    e = e2;
                } catch (TimeoutException unused2) {
                }
                if (dVar != null) {
                    try {
                    } catch (InterruptedException e3) {
                        e = e3;
                        dVar2 = dVar;
                        Logger.w(n, "the current thread was interrupted while waiting", e);
                        if (!z) {
                        }
                    } catch (CancellationException unused3) {
                        dVar2 = dVar;
                        Logger.i(n, "{requestServer} the computation was cancelled");
                        if (!z) {
                        }
                    } catch (ExecutionException e4) {
                        e = e4;
                        dVar2 = dVar;
                        Logger.w(n, "the computation threw an ExecutionException", e);
                        z = false;
                        if (!z) {
                        }
                    } catch (TimeoutException unused4) {
                        dVar2 = dVar;
                        Logger.w(n, "the wait timed out");
                        z = false;
                        if (!z) {
                        }
                    }
                    if (dVar.o() || dVar.m()) {
                        Logger.i(n, "grs request return body is not null and is OK.");
                        dVar2 = dVar;
                        if (!z) {
                            Logger.v(n, "needBreak is true so need break current circulation");
                            break;
                        }
                        i++;
                    }
                }
                dVar2 = dVar;
            }
            z = false;
            if (!z) {
            }
        }
        return b(dVar2);
    }

    private void a(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append(str);
        String grsReqParamJoint = this.f268a.getGrsReqParamJoint(false, false, e(), this.b);
        if (!TextUtils.isEmpty(grsReqParamJoint)) {
            sb.append("?");
            sb.append(grsReqParamJoint);
        }
        this.i.add(sb.toString());
    }

    private d b(d dVar) throws ExecutionException, InterruptedException, TimeoutException {
        String str;
        String str2;
        for (Map.Entry<String, Future<d>> entry : this.e.entrySet()) {
            if (dVar != null && (dVar.o() || dVar.m())) {
                break;
            }
            try {
                dVar = entry.getValue().get(40000L, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e = e;
                str = n;
                str2 = "{checkResponse} when check result, find InterruptedException, check others";
                Logger.w(str, str2, e);
            } catch (CancellationException unused) {
                Logger.i(n, "{checkResponse} when check result, find CancellationException, check others");
            } catch (ExecutionException e2) {
                e = e2;
                str = n;
                str2 = "{checkResponse} when check result, find ExecutionException, check others";
                Logger.w(str, str2, e);
            } catch (TimeoutException unused2) {
                Logger.w(n, "{checkResponse} when check result, find TimeoutException, cancel current request task");
                if (!entry.getValue().isCancelled()) {
                    entry.getValue().cancel(true);
                }
            }
        }
        return dVar;
    }

    private d b(ExecutorService executorService, String str, com.huawei.hms.framework.network.grs.e.c cVar) throws ExecutionException, InterruptedException, TimeoutException {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        d dVarA = a(executorService, this.i, str, cVar);
        int iB = dVarA == null ? 0 : dVarA.b();
        Logger.v(n, "use 2.0 interface return http's code is：{%s}", Integer.valueOf(iB));
        if (iB == 404 || iB == 401) {
            if (TextUtils.isEmpty(e()) && TextUtils.isEmpty(this.f268a.getAppName())) {
                Logger.i(n, "request grs server use 1.0 API must set appName,please check.");
                return null;
            }
            this.e.clear();
            Logger.i(n, "this env has not deploy new interface,so use old interface.");
            dVarA = a(executorService, this.h, str, cVar);
        }
        e.a(new ArrayList(this.f), SystemClock.elapsedRealtime() - jElapsedRealtime, this.g, this.b);
        this.f.clear();
        return dVarA;
    }

    private void b(String str, String str2) {
        if (TextUtils.isEmpty(this.f268a.getAppName()) && TextUtils.isEmpty(e())) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        Locale locale = Locale.ROOT;
        Object[] objArr = new Object[1];
        objArr[0] = TextUtils.isEmpty(e()) ? this.f268a.getAppName() : e();
        sb.append(String.format(locale, str, objArr));
        String grsReqParamJoint = this.f268a.getGrsReqParamJoint(false, false, "1.0", this.b);
        if (!TextUtils.isEmpty(grsReqParamJoint)) {
            sb.append("?");
            sb.append(grsReqParamJoint);
        }
        this.h.add(sb.toString());
    }

    private void c() {
        com.huawei.hms.framework.network.grs.g.k.d dVarA = com.huawei.hms.framework.network.grs.g.j.a.a(this.b);
        if (dVarA == null) {
            Logger.w(n, "g*s***_se****er_conf*** maybe has a big error");
            return;
        }
        a(dVarA);
        List<String> listA = dVarA.a();
        if (listA == null || listA.size() <= 0) {
            Logger.v(n, "maybe grs_base_url config with [],please check.");
            return;
        }
        if (listA.size() > 10) {
            throw new IllegalArgumentException("grs_base_url's count is larger than MAX value 10");
        }
        String strC = dVarA.c();
        String strB = dVarA.b();
        if (listA.size() > 0) {
            for (String str : listA) {
                if (str.startsWith("https://")) {
                    b(strC, str);
                    a(strB, str);
                } else {
                    Logger.w(n, "grs server just support https scheme url,please check.");
                }
            }
        }
        Logger.v(n, "request to GRS server url is{%s} and {%s}", this.h, this.i);
    }

    private void d() {
        String grsParasKey = this.f268a.getGrsParasKey(true, true, this.b);
        this.l = this.c.a().a(grsParasKey + "ETag", "");
    }

    private String e() {
        com.huawei.hms.framework.network.grs.f.b bVarA = com.huawei.hms.framework.network.grs.f.b.a(this.b.getPackageName(), this.f268a);
        com.huawei.hms.framework.network.grs.local.model.a aVarA = bVarA != null ? bVarA.a() : null;
        if (aVarA == null) {
            return "";
        }
        String strB = aVarA.b();
        Logger.v(n, "get appName from local assets is{%s}", strB);
        return strB;
    }

    public d a(ExecutorService executorService, String str, com.huawei.hms.framework.network.grs.e.c cVar) {
        String str2;
        String str3;
        if (this.h.isEmpty() && this.i.isEmpty()) {
            return null;
        }
        try {
            return (d) executorService.submit(new a(executorService, str, cVar)).get(b() != null ? r0.d() : 10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e = e;
            str2 = n;
            str3 = "{submitExcutorTaskWithTimeout} the current thread was interrupted while waiting";
            Logger.w(str2, str3, e);
            return null;
        } catch (CancellationException unused) {
            Logger.i(n, "{submitExcutorTaskWithTimeout} the computation was cancelled");
            return null;
        } catch (ExecutionException e2) {
            e = e2;
            str2 = n;
            str3 = "{submitExcutorTaskWithTimeout} the computation threw an ExecutionException";
            Logger.w(str2, str3, e);
            return null;
        } catch (TimeoutException unused2) {
            Logger.w(n, "{submitExcutorTaskWithTimeout} the wait timed out");
            return null;
        } catch (Exception e3) {
            e = e3;
            str2 = n;
            str3 = "{submitExcutorTaskWithTimeout} catch Exception";
            Logger.w(str2, str3, e);
            return null;
        }
    }

    public String a() {
        return this.l;
    }

    public synchronized void a(d dVar) {
        this.f.add(dVar);
        d dVar2 = this.d;
        if (dVar2 != null && (dVar2.o() || this.d.m())) {
            Logger.v(n, "grsResponseResult is ok");
            return;
        }
        if (dVar.n()) {
            Logger.i(n, "GRS server open 503 limiting strategy.");
            com.huawei.hms.framework.network.grs.h.d.a(this.f268a.getGrsParasKey(true, true, this.b), new d.a(dVar.k(), SystemClock.elapsedRealtime()));
            return;
        }
        if (dVar.m()) {
            Logger.i(n, "GRS server open 304 Not Modified.");
        }
        if (!dVar.o() && !dVar.m()) {
            Logger.v(n, "grsResponseResult has exception so need return");
            return;
        }
        this.d = dVar;
        this.c.a(this.f268a, dVar, this.b, this.j);
        for (Map.Entry<String, Future<d>> entry : this.e.entrySet()) {
            if (!entry.getKey().equals(dVar.l()) && !entry.getValue().isCancelled()) {
                Logger.i(n, "future cancel");
                entry.getValue().cancel(true);
            }
        }
    }

    public void a(com.huawei.hms.framework.network.grs.g.k.d dVar) {
        this.k = dVar;
    }

    public com.huawei.hms.framework.network.grs.g.k.d b() {
        return this.k;
    }
}
