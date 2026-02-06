package com.huawei.hmf.tasks.a;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.util.Log;
import com.huawei.hmf.tasks.ExecuteResult;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;

public final class g extends Fragment {
    private static final WeakHashMap<Activity, WeakReference<g>> b = new WeakHashMap<>();

    private final List<WeakReference<ExecuteResult<?>>> f190a = new ArrayList();

    private static g a(Activity activity) {
        g gVarA;
        WeakReference<g> weakReference = b.get(activity);
        if (weakReference != null && weakReference.get() != null) {
            return weakReference.get();
        }
        FragmentManager fragmentManager = activity.getFragmentManager();
        try {
            g gVar = (g) fragmentManager.findFragmentByTag("com.huawei.hmf.tasks.lifecycle_fragment_tag");
            if (gVar == null) {
                try {
                    gVarA = a(fragmentManager);
                } catch (ClassCastException e) {
                    e = e;
                    gVarA = gVar;
                    Log.e("LifecycleCallbackFrg", "found LifecycleCallbackFragment but the type do not match. " + e.getMessage());
                    return gVarA;
                }
            } else {
                gVarA = gVar;
            }
        } catch (ClassCastException e2) {
            e = e2;
            gVarA = null;
        }
        try {
            b.put(activity, new WeakReference<>(gVarA));
        } catch (ClassCastException e3) {
            e = e3;
            Log.e("LifecycleCallbackFrg", "found LifecycleCallbackFragment but the type do not match. " + e.getMessage());
            return gVarA;
        }
        return gVarA;
    }

    private static g a(FragmentManager fragmentManager) {
        g gVar = null;
        try {
            g gVar2 = new g();
            try {
                fragmentManager.beginTransaction().add(gVar2, "com.huawei.hmf.tasks.lifecycle_fragment_tag").commitAllowingStateLoss();
                return gVar2;
            } catch (Exception e) {
                e = e;
                gVar = gVar2;
                Log.e("LifecycleCallbackFrg", "create fragment failed." + e.getMessage());
                return gVar;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public static void a(Activity activity, ExecuteResult executeResult) {
        g gVarA = a(activity);
        if (gVarA != null) {
            synchronized (gVarA.f190a) {
                gVarA.f190a.add(new WeakReference<>(executeResult));
            }
        }
    }

    @Override
    public final void onStop() {
        super.onStop();
        synchronized (this.f190a) {
            Iterator<WeakReference<ExecuteResult<?>>> it = this.f190a.iterator();
            while (it.hasNext()) {
                ExecuteResult<?> executeResult = it.next().get();
                if (executeResult != null) {
                    executeResult.cancel();
                }
            }
            this.f190a.clear();
        }
    }
}
