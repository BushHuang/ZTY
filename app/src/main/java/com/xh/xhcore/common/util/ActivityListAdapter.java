package com.xh.xhcore.common.util;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import com.xh.xhcore.common.base.BaseActivityLifecycleCallbacks;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001fB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0007J\u001e\u0010\u0015\u001a\u00020\u00142\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\n0\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0014H\u0007J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0007R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\"\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\u0002\u001a\u0004\b\f\u0010\rR\"\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\u000f8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0002\u001a\u0004\b\u0011\u0010\u0012¨\u0006 "}, d2 = {"Lcom/xh/xhcore/common/util/ActivityListAdapter;", "", "()V", "MAX_RECENT_ACTIVITY_SIZE", "", "getMAX_RECENT_ACTIVITY_SIZE$annotations", "getMAX_RECENT_ACTIVITY_SIZE", "()I", "activityStack", "Ljava/util/Deque;", "Lcom/xh/xhcore/common/util/ActivityListAdapter$ActivityItemInfo;", "getActivityStack$annotations", "getActivityStack", "()Ljava/util/Deque;", "recentActivityList", "Ljava/util/concurrent/ConcurrentLinkedQueue;", "getRecentActivityList$annotations", "getRecentActivityList", "()Ljava/util/concurrent/ConcurrentLinkedQueue;", "getActivityStackString", "", "getListString", "collection", "", "reverseOrder", "", "getRecentActivityListString", "init", "", "application", "Landroid/app/Application;", "ActivityItemInfo", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ActivityListAdapter {
    public static final ActivityListAdapter INSTANCE = new ActivityListAdapter();
    private static final int MAX_RECENT_ACTIVITY_SIZE;
    private static final Deque<ActivityItemInfo> activityStack;
    private static final ConcurrentLinkedQueue<ActivityItemInfo> recentActivityList;

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0011\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0006HÆ\u0003J%\u0010\u000e\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÖ\u0001J\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u0019\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/xh/xhcore/common/util/ActivityListAdapter$ActivityItemInfo;", "", "activityClass", "Ljava/lang/Class;", "Landroid/app/Activity;", "instanceHash", "", "(Ljava/lang/Class;I)V", "getActivityClass", "()Ljava/lang/Class;", "getInstanceHash", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class ActivityItemInfo {
        private final Class<? extends Activity> activityClass;
        private final int instanceHash;

        public ActivityItemInfo(Class<? extends Activity> cls, int i) {
            Intrinsics.checkNotNullParameter(cls, "activityClass");
            this.activityClass = cls;
            this.instanceHash = i;
        }

        public static ActivityItemInfo copy$default(ActivityItemInfo activityItemInfo, Class cls, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                cls = activityItemInfo.activityClass;
            }
            if ((i2 & 2) != 0) {
                i = activityItemInfo.instanceHash;
            }
            return activityItemInfo.copy(cls, i);
        }

        public final Class<? extends Activity> component1() {
            return this.activityClass;
        }

        public final int getInstanceHash() {
            return this.instanceHash;
        }

        public final ActivityItemInfo copy(Class<? extends Activity> activityClass, int instanceHash) {
            Intrinsics.checkNotNullParameter(activityClass, "activityClass");
            return new ActivityItemInfo(activityClass, instanceHash);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ActivityItemInfo)) {
                return false;
            }
            ActivityItemInfo activityItemInfo = (ActivityItemInfo) other;
            return Intrinsics.areEqual(this.activityClass, activityItemInfo.activityClass) && this.instanceHash == activityItemInfo.instanceHash;
        }

        public final Class<? extends Activity> getActivityClass() {
            return this.activityClass;
        }

        public final int getInstanceHash() {
            return this.instanceHash;
        }

        public int hashCode() {
            return (this.activityClass.hashCode() * 31) + this.instanceHash;
        }

        public String toString() {
            return this.activityClass.getName() + '@' + Integer.toHexString(hashCode());
        }
    }

    static {
        activityStack = Build.VERSION.SDK_INT >= 21 ? new ConcurrentLinkedDeque() : new ArrayDeque();
        recentActivityList = new ConcurrentLinkedQueue<>();
        MAX_RECENT_ACTIVITY_SIZE = 15;
    }

    private ActivityListAdapter() {
    }

    public static final Deque<ActivityItemInfo> getActivityStack() {
        return activityStack;
    }

    @JvmStatic
    public static void getActivityStack$annotations() {
    }

    @JvmStatic
    public static final String getActivityStackString() {
        return INSTANCE.getListString(activityStack, true);
    }

    private final String getListString(Collection<ActivityItemInfo> collection, boolean reverseOrder) {
        StringBuilder sb = new StringBuilder("[");
        Iterator<ActivityItemInfo> it = collection.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(!reverseOrder ? "->" : "<-");
        }
        sb.append("]");
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "activityStrackSb.append(\"]\").toString()");
        return string;
    }

    public static final int getMAX_RECENT_ACTIVITY_SIZE() {
        return MAX_RECENT_ACTIVITY_SIZE;
    }

    @JvmStatic
    public static void getMAX_RECENT_ACTIVITY_SIZE$annotations() {
    }

    public static final ConcurrentLinkedQueue<ActivityItemInfo> getRecentActivityList() {
        return recentActivityList;
    }

    @JvmStatic
    public static void getRecentActivityList$annotations() {
    }

    @JvmStatic
    public static final String getRecentActivityListString() {
        return INSTANCE.getListString(recentActivityList, false);
    }

    @JvmStatic
    public static final void init(Application application) {
        Intrinsics.checkNotNullParameter(application, "application");
        application.registerActivityLifecycleCallbacks(new BaseActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Intrinsics.checkNotNullParameter(activity, "activity");
                ActivityListAdapter.getActivityStack().addFirst(new ActivityItemInfo(activity.getClass(), activity.hashCode()));
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
                ActivityListAdapter.getActivityStack().pollFirst();
            }

            @Override
            public void onActivityStarted(Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
                if (ActivityListAdapter.getRecentActivityList().size() <= 0 || !Intrinsics.areEqual(((ActivityItemInfo) CollectionsKt.last(ActivityListAdapter.getRecentActivityList())).getActivityClass(), activity.getClass())) {
                    ActivityListAdapter.getRecentActivityList().add(new ActivityItemInfo(activity.getClass(), activity.hashCode()));
                    if (ActivityListAdapter.getRecentActivityList().size() > ActivityListAdapter.getMAX_RECENT_ACTIVITY_SIZE()) {
                        ActivityListAdapter.getRecentActivityList().poll();
                    }
                }
            }
        });
    }
}
