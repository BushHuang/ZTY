package com.analysys.allgro.plugin;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.RadioGroup;
import com.analysys.utils.AThreadPool;
import com.analysys.utils.AnsReflectUtils;
import com.analysys.utils.ExceptionUtil;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ASMProbeHelp {
    private static HashMap<Integer, Long> eventTimestamp = new HashMap<>();
    private static int mCount;
    private Set<ASMHookAdapter> mObservers;

    static class a {

        public static final ASMProbeHelp f34a = new ASMProbeHelp();
    }

    private ASMProbeHelp() {
        this.mObservers = new HashSet();
    }

    public static ASMProbeHelp getInstance() {
        return a.f34a;
    }

    static boolean isInLimitTime(Object obj) {
        if (obj == null) {
            return true;
        }
        boolean z = false;
        try {
            mCount++;
            long jCurrentTimeMillis = System.currentTimeMillis();
            Long l = eventTimestamp.get(Integer.valueOf(obj.hashCode()));
            if (l != null) {
                boolean z2 = jCurrentTimeMillis - l.longValue() < 500;
                try {
                    eventTimestamp.put(Integer.valueOf(obj.hashCode()), Long.valueOf(jCurrentTimeMillis));
                    if (mCount <= 1000) {
                        return z2;
                    }
                    mCount = 0;
                    Iterator<Map.Entry<Integer, Long>> it = eventTimestamp.entrySet().iterator();
                    while (it.hasNext()) {
                        if (jCurrentTimeMillis - it.next().getValue().longValue() > 500) {
                            it.remove();
                        }
                    }
                    return z2;
                } catch (Throwable th) {
                    th = th;
                    z = z2;
                }
            }
        } catch (Throwable th2) {
            th = th2;
        }
        ExceptionUtil.exceptionThrow(th);
        return z;
    }

    public void maybeClickInXML(final View view, final boolean z, final boolean z2) {
        try {
            final long jCurrentTimeMillis = System.currentTimeMillis();
            AThreadPool.asyncLowPriorityExecutor(new Runnable() {
                @Override
                public void run() {
                    Object field;
                    Object field2;
                    Object field3;
                    try {
                        if (!ASMProbeHelp.isInLimitTime(view) && (field = AnsReflectUtils.getField(view, "mListenerInfo")) != null && (field2 = AnsReflectUtils.getField(field, "mOnClickListener")) != null && (field3 = AnsReflectUtils.getField(field2, "mMethodName")) != null && field3.equals(Boolean.valueOf(z))) {
                            Iterator it = ASMProbeHelp.this.mObservers.iterator();
                            while (it.hasNext()) {
                                ((ASMHookAdapter) it.next()).trackViewOnClick(view, z2, jCurrentTimeMillis);
                            }
                        }
                    } catch (Throwable th) {
                        ExceptionUtil.exceptionThrow(th);
                    }
                }
            });
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    public void onFragmentViewCreated(final Object obj, final View view, final Bundle bundle, final boolean z) {
        AThreadPool.asyncMiddlePriorityExecutor(new Runnable() {
            @Override
            public void run() {
                try {
                    Iterator it = ASMProbeHelp.this.mObservers.iterator();
                    while (it.hasNext()) {
                        ((ASMHookAdapter) it.next()).onFragmentViewCreated(obj, view, bundle, z);
                    }
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    public void registerHookObserver(ASMHookAdapter aSMHookAdapter) {
        this.mObservers.add(aSMHookAdapter);
    }

    public void trackDialog(final DialogInterface dialogInterface, final int i, final boolean z) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        AThreadPool.asyncLowPriorityExecutor(new Runnable() {
            @Override
            public void run() {
                try {
                    if (ASMProbeHelp.isInLimitTime(dialogInterface)) {
                        return;
                    }
                    Iterator it = ASMProbeHelp.this.mObservers.iterator();
                    while (it.hasNext()) {
                        ((ASMHookAdapter) it.next()).trackDialog(dialogInterface, i, z, jCurrentTimeMillis);
                    }
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    public void trackDrawerSwitch(final View view, final boolean z, final boolean z2) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        AThreadPool.asyncLowPriorityExecutor(new Runnable() {
            @Override
            public void run() {
                try {
                    if (ASMProbeHelp.isInLimitTime(view)) {
                        return;
                    }
                    Iterator it = ASMProbeHelp.this.mObservers.iterator();
                    while (it.hasNext()) {
                        ((ASMHookAdapter) it.next()).trackDrawerSwitch(view, z, z2, jCurrentTimeMillis);
                    }
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    public void trackExpListViewChildClick(final ExpandableListView expandableListView, final View view, final int i, final int i2, final boolean z) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        AThreadPool.asyncLowPriorityExecutor(new Runnable() {
            @Override
            public void run() {
                try {
                    if (ASMProbeHelp.isInLimitTime(view)) {
                        return;
                    }
                    Iterator it = ASMProbeHelp.this.mObservers.iterator();
                    while (it.hasNext()) {
                        ((ASMHookAdapter) it.next()).trackExpListViewChildClick(expandableListView, view, i, i2, z, jCurrentTimeMillis);
                    }
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    public void trackExpListViewGroupClick(final ExpandableListView expandableListView, final View view, final int i, final boolean z) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        AThreadPool.asyncLowPriorityExecutor(new Runnable() {
            @Override
            public void run() {
                try {
                    if (ASMProbeHelp.isInLimitTime(view)) {
                        return;
                    }
                    Iterator it = ASMProbeHelp.this.mObservers.iterator();
                    while (it.hasNext()) {
                        ((ASMHookAdapter) it.next()).trackExpListViewGroupClick(expandableListView, view, i, z, jCurrentTimeMillis);
                    }
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    public void trackFragmentResume(final Object obj, final boolean z) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        AThreadPool.asyncMiddlePriorityExecutor(new Runnable() {
            @Override
            public void run() {
                try {
                    Iterator it = ASMProbeHelp.this.mObservers.iterator();
                    while (it.hasNext()) {
                        ((ASMHookAdapter) it.next()).trackFragmentResume(obj, z, jCurrentTimeMillis);
                    }
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    public void trackFragmentSetUserVisibleHint(final Object obj, final boolean z, final boolean z2) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        AThreadPool.asyncMiddlePriorityExecutor(new Runnable() {
            @Override
            public void run() {
                try {
                    Iterator it = ASMProbeHelp.this.mObservers.iterator();
                    while (it.hasNext()) {
                        ((ASMHookAdapter) it.next()).trackFragmentSetUserVisibleHint(obj, z, z2, jCurrentTimeMillis);
                    }
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    public void trackListView(final AdapterView<?> adapterView, final View view, final int i, final boolean z) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        AThreadPool.asyncLowPriorityExecutor(new Runnable() {
            @Override
            public void run() {
                try {
                    if (ASMProbeHelp.isInLimitTime(view)) {
                        return;
                    }
                    Iterator it = ASMProbeHelp.this.mObservers.iterator();
                    while (it.hasNext()) {
                        ((ASMHookAdapter) it.next()).trackListView(adapterView, view, i, z, jCurrentTimeMillis);
                    }
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    public void trackMenuItem(MenuItem menuItem, boolean z) {
    }

    public void trackMenuItem(final Object obj, final MenuItem menuItem, final boolean z) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        AThreadPool.asyncLowPriorityExecutor(new Runnable() {
            @Override
            public void run() {
                try {
                    if (ASMProbeHelp.isInLimitTime(menuItem)) {
                        return;
                    }
                    Iterator it = ASMProbeHelp.this.mObservers.iterator();
                    while (it.hasNext()) {
                        ((ASMHookAdapter) it.next()).trackMenuItem(obj, menuItem, z, jCurrentTimeMillis);
                    }
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    public void trackOnHiddenChanged(final Object obj, final boolean z, final boolean z2) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        AThreadPool.asyncMiddlePriorityExecutor(new Runnable() {
            @Override
            public void run() {
                try {
                    Iterator it = ASMProbeHelp.this.mObservers.iterator();
                    while (it.hasNext()) {
                        ((ASMHookAdapter) it.next()).trackOnHiddenChanged(obj, z, z2, jCurrentTimeMillis);
                    }
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    public void trackRadioGroup(final RadioGroup radioGroup, final int i, final boolean z) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        AThreadPool.asyncLowPriorityExecutor(new Runnable() {
            @Override
            public void run() {
                try {
                    if (ASMProbeHelp.isInLimitTime(radioGroup != null ? radioGroup.findViewById(i) : null)) {
                        return;
                    }
                    Iterator it = ASMProbeHelp.this.mObservers.iterator();
                    while (it.hasNext()) {
                        ((ASMHookAdapter) it.next()).trackRadioGroup(radioGroup, i, z, jCurrentTimeMillis);
                    }
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    public void trackSendAccessibilityEvent(final View view, final int i, final boolean z) {
        try {
            AThreadPool.asyncLowPriorityExecutor(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (ASMProbeHelp.isInLimitTime(view)) {
                            return;
                        }
                        Iterator it = ASMProbeHelp.this.mObservers.iterator();
                        while (it.hasNext()) {
                            ((ASMHookAdapter) it.next()).trackSendAccessibilityEvent(view, i, z);
                        }
                    } catch (Throwable th) {
                        ExceptionUtil.exceptionThrow(th);
                    }
                }
            });
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    public void trackTabHost(final String str, final boolean z) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        AThreadPool.asyncLowPriorityExecutor(new Runnable() {
            @Override
            public void run() {
                try {
                    if (ASMProbeHelp.isInLimitTime(str)) {
                        return;
                    }
                    Iterator it = ASMProbeHelp.this.mObservers.iterator();
                    while (it.hasNext()) {
                        ((ASMHookAdapter) it.next()).trackTabHost(str, z, jCurrentTimeMillis);
                    }
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    public void trackTabLayout(final Object obj, final Object obj2, final boolean z) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        AThreadPool.asyncLowPriorityExecutor(new Runnable() {
            @Override
            public void run() {
                try {
                    if (ASMProbeHelp.isInLimitTime(obj2)) {
                        return;
                    }
                    Iterator it = ASMProbeHelp.this.mObservers.iterator();
                    while (it.hasNext()) {
                        ((ASMHookAdapter) it.next()).trackTabLayout(obj, obj2, z, jCurrentTimeMillis);
                    }
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            }
        });
    }

    public void trackViewOnClick(final View view, final boolean z) {
        try {
            final long jCurrentTimeMillis = System.currentTimeMillis();
            AThreadPool.asyncLowPriorityExecutor(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (ASMProbeHelp.isInLimitTime(view)) {
                            return;
                        }
                        Iterator it = ASMProbeHelp.this.mObservers.iterator();
                        while (it.hasNext()) {
                            ((ASMHookAdapter) it.next()).trackViewOnClick(view, z, jCurrentTimeMillis);
                        }
                    } catch (Throwable th) {
                        ExceptionUtil.exceptionThrow(th);
                    }
                }
            });
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }
}
