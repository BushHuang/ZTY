package com.xuehai.system.common.appusage;

import android.app.AppOpsManager;
import android.app.usage.UsageEvents;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.util.Log;
import com.xuehai.system.common.appusage.internal.IntervalState;
import com.xuehai.system.common.log.MdmLog;
import com.xuehai.system.mdm.SpecialConst;
import defpackage.C$r8$backportedMethods$utility$Long$1$hashCode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001)B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\nH\u0003J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u0011H\u0007J&\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0006H\u0007J&\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0006H\u0007J\u000e\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0011J0\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u0006H\u0007J\u0012\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010\u0010\u001a\u00020\u0011H\u0007J\u0010\u0010!\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0007J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0004H\u0002J&\u0010%\u001a\b\u0012\u0004\u0012\u00020\r0\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0006H\u0007J\u000e\u0010&\u001a\u00020#2\u0006\u0010\u0010\u001a\u00020\u0011J\u0010\u0010'\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u001cH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/xuehai/system/common/appusage/AppUsageHelper;", "", "()V", "TAG", "", "YEAR", "", "lastQuestTimeMills", "topPackageName", "calculate", "", "Lcom/xuehai/system/common/appusage/AppUsageHelper$Stat;", "events", "Landroid/app/usage/UsageEvents$Event;", "checkAppUsagePermission", "", "context", "Landroid/content/Context;", "getAppUsageIntent", "Landroid/content/Intent;", "getDailyStats", "start", "end", "getDailyStatsV2", "getTopActivityPackageName", "getUsageStatsList", "Landroid/app/usage/UsageStats;", "intervalType", "", "beginTime", "endTime", "getUsageStatsManager", "Landroid/app/usage/UsageStatsManager;", "isGrantedUsagePermission", "log", "", "msg", "queryAppUsageEvents", "requestAppUsagePermission", "traceEvent", "eventType", "Stat", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AppUsageHelper {
    private static final String TAG = "AppUsageHelper";
    private static final long YEAR = 31536000000L;
    public static final AppUsageHelper INSTANCE = new AppUsageHelper();
    private static String topPackageName = "";
    private static long lastQuestTimeMills = System.currentTimeMillis();

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\b\u0010\u0016\u001a\u00020\u0003H\u0016R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0017"}, d2 = {"Lcom/xuehai/system/common/appusage/AppUsageHelper$Stat;", "", "packageName", "", "totalTime", "", "lastTimeUsed", "(Ljava/lang/String;JJ)V", "getLastTimeUsed", "()J", "getPackageName", "()Ljava/lang/String;", "getTotalTime", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Stat {
        private final long lastTimeUsed;
        private final String packageName;
        private final long totalTime;

        public Stat(String str, long j, long j2) {
            Intrinsics.checkNotNullParameter(str, "packageName");
            this.packageName = str;
            this.totalTime = j;
            this.lastTimeUsed = j2;
        }

        public static Stat copy$default(Stat stat, String str, long j, long j2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = stat.packageName;
            }
            if ((i & 2) != 0) {
                j = stat.totalTime;
            }
            long j3 = j;
            if ((i & 4) != 0) {
                j2 = stat.lastTimeUsed;
            }
            return stat.copy(str, j3, j2);
        }

        public final String getPackageName() {
            return this.packageName;
        }

        public final long getTotalTime() {
            return this.totalTime;
        }

        public final long getLastTimeUsed() {
            return this.lastTimeUsed;
        }

        public final Stat copy(String packageName, long totalTime, long lastTimeUsed) {
            Intrinsics.checkNotNullParameter(packageName, "packageName");
            return new Stat(packageName, totalTime, lastTimeUsed);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Stat)) {
                return false;
            }
            Stat stat = (Stat) other;
            return Intrinsics.areEqual(this.packageName, stat.packageName) && this.totalTime == stat.totalTime && this.lastTimeUsed == stat.lastTimeUsed;
        }

        public final long getLastTimeUsed() {
            return this.lastTimeUsed;
        }

        public final String getPackageName() {
            return this.packageName;
        }

        public final long getTotalTime() {
            return this.totalTime;
        }

        public int hashCode() {
            return (((this.packageName.hashCode() * 31) + C$r8$backportedMethods$utility$Long$1$hashCode.hashCode(this.totalTime)) * 31) + C$r8$backportedMethods$utility$Long$1$hashCode.hashCode(this.lastTimeUsed);
        }

        public String toString() {
            return "Stat--" + this.packageName + ": " + this.totalTime + "ms;";
        }
    }

    private AppUsageHelper() {
    }

    private final List<Stat> calculate(List<UsageEvents.Event> events) {
        long j;
        ArrayList arrayList = new ArrayList();
        String className = null;
        String packageName = "";
        long j2 = 0;
        long timeStamp = 0;
        long timeStamp2 = 0;
        long j3 = 0;
        for (UsageEvents.Event event : events) {
            packageName = event.getPackageName();
            Intrinsics.checkNotNullExpressionValue(packageName, "it.packageName");
            if (className == null) {
                className = event.getClassName();
            }
            if (event.getEventType() == 1) {
                timeStamp2 = event.getTimeStamp();
                j3 = timeStamp2;
            } else if (event.getEventType() == 2) {
                timeStamp = event.getTimeStamp();
            } else {
                if (event.getEventType() == 23) {
                    j = 0;
                    if (timeStamp2 > 0 && timeStamp == 0) {
                        timeStamp = event.getTimeStamp();
                    }
                }
                if (timeStamp2 == j && timeStamp != j) {
                    timeStamp2 = j3;
                }
                if (timeStamp2 == j && timeStamp != j) {
                    j2 += timeStamp - timeStamp2;
                    timeStamp2 = j;
                    j3 = timeStamp;
                    timeStamp = timeStamp2;
                }
            }
            j = 0;
            if (timeStamp2 == j) {
                timeStamp2 = j3;
            }
            if (timeStamp2 == j) {
            }
        }
        arrayList.add(new Stat(packageName, j2, timeStamp));
        return arrayList;
    }

    private static final void m210getDailyStats$lambda6(List list, String str, List list2) {
        Intrinsics.checkNotNullParameter(list, "$stats");
        Intrinsics.checkNotNullParameter(str, "packageName");
        Intrinsics.checkNotNullParameter(list2, "events");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : list2) {
            UsageEvents.Event event = (UsageEvents.Event) obj;
            if ((Intrinsics.areEqual("com.xh.arespunc", event.getPackageName()) && Intrinsics.areEqual("com.xh.arespunc.main.view.WakeUpResponseActivity", event.getClassName())) ? false : true) {
                arrayList2.add(obj);
            }
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj2 : arrayList2) {
            String className = ((UsageEvents.Event) obj2).getClassName();
            Object obj3 = linkedHashMap.get(className);
            if (obj3 == null) {
                obj3 = (List) new ArrayList();
                linkedHashMap.put(className, obj3);
            }
            ((List) obj3).add(obj2);
        }
        Iterator it = linkedHashMap.values().iterator();
        while (it.hasNext()) {
            arrayList.addAll(INSTANCE.calculate((List) it.next()));
        }
        Iterator it2 = arrayList.iterator();
        long totalTime = 0;
        while (it2.hasNext()) {
            totalTime += ((Stat) it2.next()).getTotalTime();
        }
        list.add(new Stat(str, totalTime, 0L));
    }

    private final void log(String msg) {
        MdmLog.i("AppUsageHelper", msg);
    }

    private final String traceEvent(int eventType) {
        if (eventType == 1) {
            return '(' + eventType + ")ACTIVITY_RESUMED(MOVE_TO_FOREGROUND)";
        }
        if (eventType == 2) {
            return '(' + eventType + ")ACTIVITY_PAUSED(MOVE_TO_BACKGROUND)";
        }
        if (eventType == 10) {
            return '(' + eventType + ")NOTIFICATION_SEEN";
        }
        if (eventType == 11) {
            return '(' + eventType + ")STANDBY_BUCKET_CHANGED";
        }
        if (eventType != 23) {
            StringBuilder sb = new StringBuilder();
            sb.append('(');
            sb.append(eventType);
            sb.append(')');
            return sb.toString();
        }
        return '(' + eventType + ")ACTIVITY_STOPPED";
    }

    public final boolean checkAppUsagePermission(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        MdmLog.i("AppUsageHelper", "checkAppUsagePermission");
        if (Build.VERSION.SDK_INT < 22) {
            MdmLog.i("AppUsageHelper", "Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1");
            return true;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            MdmLog.i("AppUsageHelper", "isGrantedUsagePermission=" + isGrantedUsagePermission(context));
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        List<UsageStats> usageStatsList = getUsageStatsList(context, 3, jCurrentTimeMillis - 31536000000L, jCurrentTimeMillis);
        boolean z = !(usageStatsList == null || usageStatsList.isEmpty());
        MdmLog.i("AppUsageHelper", "checkAppUsagePermission=" + z);
        return z;
    }

    public final Intent getAppUsageIntent(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intent intent = new Intent("android.settings.USAGE_ACCESS_SETTINGS");
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        return intent;
    }

    public final List<Stat> getDailyStats(Context context, long start, long end) {
        Intrinsics.checkNotNullParameter(context, "context");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        UsageStatsManager usageStatsManager = getUsageStatsManager(context);
        if (usageStatsManager == null) {
            return CollectionsKt.emptyList();
        }
        UsageEvents usageEventsQueryEvents = usageStatsManager.queryEvents(start, end);
        while (usageEventsQueryEvents.hasNextEvent()) {
            UsageEvents.Event event = new UsageEvents.Event();
            usageEventsQueryEvents.getNextEvent(event);
            ArrayList arrayList = (List) linkedHashMap.get(event.getPackageName());
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
            arrayList.add(event);
            String packageName = event.getPackageName();
            Intrinsics.checkNotNullExpressionValue(packageName, "event.packageName");
            linkedHashMap.put(packageName, arrayList);
        }
        final ArrayList arrayList2 = new ArrayList();
        linkedHashMap.forEach(new BiConsumer() {
            @Override
            public final void accept(Object obj, Object obj2) {
                AppUsageHelper.m210getDailyStats$lambda6(arrayList2, (String) obj, (List) obj2);
            }
        });
        return arrayList2;
    }

    public final List<Stat> getDailyStatsV2(Context context, long start, long end) {
        String packageName;
        Intrinsics.checkNotNullParameter(context, "context");
        UsageStatsManager usageStatsManager = getUsageStatsManager(context);
        if (usageStatsManager == null) {
            return CollectionsKt.emptyList();
        }
        IntervalState intervalState = new IntervalState(start, end);
        UsageEvents usageEventsQueryEvents = usageStatsManager.queryEvents(start, end);
        while (usageEventsQueryEvents.hasNextEvent()) {
            UsageEvents.Event event = new UsageEvents.Event();
            usageEventsQueryEvents.getNextEvent(event);
            if (!Intrinsics.areEqual("com.xh.arespunc", event.getPackageName()) || !Intrinsics.areEqual("com.xh.arespunc.main.view.WakeUpResponseActivity", event.getClassName())) {
                if (!Intrinsics.areEqual("com.xh.zhitongyunstu", event.getPackageName()) || !SpecialConst.INSTANCE.getZTY_WHITELIST_ACTIVITY().contains(event.getClassName())) {
                    if (!Intrinsics.areEqual(event.getPackageName(), "com.xh.zhitongyunstu") || event.getClassName() == null) {
                        packageName = event.getPackageName();
                        String str = packageName;
                        Intrinsics.checkNotNullExpressionValue(str, "packageName");
                        intervalState.update(str, event.getClassName(), event.getTimeStamp(), event.getEventType());
                    } else {
                        String className = event.getClassName();
                        Intrinsics.checkNotNullExpressionValue(className, "event.className");
                        if (StringsKt.startsWith(className, "com.xh.module.taskrewardstu", true)) {
                            packageName = "com.xh.zhitongyunstu.negative.screen.taskrewardstu";
                        }
                        String str2 = packageName;
                        Intrinsics.checkNotNullExpressionValue(str2, "packageName");
                        intervalState.update(str2, event.getClassName(), event.getTimeStamp(), event.getEventType());
                    }
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, com.xuehai.system.common.appusage.internal.UsageStats> entry : intervalState.getPackageStats().entrySet()) {
            String key = entry.getKey();
            com.xuehai.system.common.appusage.internal.UsageStats value = entry.getValue();
            arrayList.add(new Stat(key, value.getTotalTimeInForeground(), value.getLastTimeUsed()));
        }
        return arrayList;
    }

    public final String getTopActivityPackageName(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (Build.VERSION.SDK_INT < 21) {
            return "";
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (Build.VERSION.SDK_INT < 22) {
            return "";
        }
        List<UsageStats> usageStatsList = getUsageStatsList(context, 4, jCurrentTimeMillis - lastQuestTimeMills, jCurrentTimeMillis);
        List<UsageStats> list = usageStatsList;
        if (list == null || list.isEmpty()) {
            return topPackageName;
        }
        lastQuestTimeMills = jCurrentTimeMillis;
        String packageName = ((UsageStats) CollectionsKt.sortedWith(usageStatsList, new Comparator() {
            @Override
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Long.valueOf(((UsageStats) t2).getLastTimeUsed()), Long.valueOf(((UsageStats) t).getLastTimeUsed()));
            }
        }).get(0)).getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "usageStatsList.sortedByD…         }[0].packageName");
        topPackageName = packageName;
        return packageName;
    }

    public final List<UsageStats> getUsageStatsList(Context context, int intervalType, long beginTime, long endTime) {
        Intrinsics.checkNotNullParameter(context, "context");
        UsageStatsManager usageStatsManager = getUsageStatsManager(context);
        if (usageStatsManager == null) {
            return null;
        }
        return usageStatsManager.queryUsageStats(intervalType, beginTime, endTime);
    }

    public final UsageStatsManager getUsageStatsManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (UsageStatsManager) context.getSystemService("usagestats");
    }

    public final boolean isGrantedUsagePermission(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("appops");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.app.AppOpsManager");
        }
        try {
            int iUnsafeCheckOpNoThrow = ((AppOpsManager) systemService).unsafeCheckOpNoThrow("android:get_usage_stats", Process.myUid(), context.getPackageName());
            if (iUnsafeCheckOpNoThrow == 3) {
                if (context.checkCallingOrSelfPermission("android.permission.PACKAGE_USAGE_STATS") != 0) {
                    return false;
                }
            } else if (iUnsafeCheckOpNoThrow != 0) {
                return false;
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public final List<UsageEvents.Event> queryAppUsageEvents(Context context, long start, long end) {
        Intrinsics.checkNotNullParameter(context, "context");
        ArrayList arrayList = new ArrayList();
        UsageStatsManager usageStatsManager = getUsageStatsManager(context);
        if (usageStatsManager == null) {
            return CollectionsKt.emptyList();
        }
        UsageEvents usageEventsQueryEvents = usageStatsManager.queryEvents(start, end);
        while (usageEventsQueryEvents.hasNextEvent()) {
            UsageEvents.Event event = new UsageEvents.Event();
            usageEventsQueryEvents.getNextEvent(event);
            arrayList.add(event);
        }
        return arrayList;
    }

    public final void requestAppUsagePermission(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                context.startActivity(getAppUsageIntent(context));
            } catch (ActivityNotFoundException e) {
                Log.e("AppUsageHelper", "ACTION_USAGE_ACCESS_SETTINGS FAIL!", e);
            }
        }
    }
}
