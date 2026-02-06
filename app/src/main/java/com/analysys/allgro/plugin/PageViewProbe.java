package com.analysys.allgro.plugin;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.analysys.allgro.a;
import com.analysys.process.AgentProcess;
import com.analysys.utils.AnalysysUtil;
import com.analysys.utils.AnsReflectUtils;
import com.analysys.utils.Constants;
import com.analysys.utils.ExceptionUtil;
import com.analysys.utils.SharedUtil;
import java.util.Map;

public class PageViewProbe extends ASMHookAdapter {
    private int mFragmentPageViewHashcode;
    private long mFragmentPageViewTime;

    private void autoTrackFragmentPageView(Object obj, boolean z, long j) {
        if (checkFragmentPVEnable(obj, z) && checkTime(obj)) {
            Map<String, Object> mapA = a.a(obj, false);
            String string = SharedUtil.getString(AnalysysUtil.getContext(), Constants.SP_REFER, "");
            if (!TextUtils.isEmpty(string)) {
                mapA.put("$referrer", string);
            }
            SharedUtil.setString(AnalysysUtil.getContext(), Constants.SP_REFER, (String) mapA.get("$url"));
            AgentProcess.getInstance().autoCollectPageView(mapA, j);
        }
    }

    private boolean checkFragmentPVEnable(Object obj, boolean z) {
        AgentProcess agentProcess = AgentProcess.getInstance();
        if (!agentProcess.getConfig().isAutoTrackFragmentPageView()) {
            return false;
        }
        String name = obj.getClass().getName();
        if (agentProcess.isThisPageInPageViewBlackList(name)) {
            return false;
        }
        if (agentProcess.hasAutoPageViewWhiteList()) {
            return agentProcess.isThisPageInPageViewWhiteList(name);
        }
        return true;
    }

    private boolean checkTime(Object obj) {
        int iHashCode = obj.hashCode();
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (this.mFragmentPageViewHashcode != iHashCode) {
            this.mFragmentPageViewHashcode = iHashCode;
            this.mFragmentPageViewTime = jCurrentTimeMillis;
            return true;
        }
        if (Math.abs(this.mFragmentPageViewTime - jCurrentTimeMillis) < 500) {
            return false;
        }
        this.mFragmentPageViewTime = jCurrentTimeMillis;
        return true;
    }

    private static boolean fragmentGetUserVisibleHint(Object obj) {
        Object objInvokeMethod = AnsReflectUtils.invokeMethod(obj, "getUserVisibleHint");
        if (objInvokeMethod != null) {
            return ((Boolean) objInvokeMethod).booleanValue();
        }
        return false;
    }

    private static boolean fragmentIsResumed(Object obj) {
        Object objInvokeMethod = AnsReflectUtils.invokeMethod(obj, "isResumed");
        if (objInvokeMethod != null) {
            return ((Boolean) objInvokeMethod).booleanValue();
        }
        return false;
    }

    private static boolean fragmentIsShow(Object obj) {
        if (AnsReflectUtils.invokeMethod(obj, "isHidden") != null) {
            return !((Boolean) r1).booleanValue();
        }
        return true;
    }

    private static boolean isNotFragment(Object obj) {
        Class<?> classByName = AnsReflectUtils.getClassByName("android.app.Fragment");
        Class<?> classByName2 = AnsReflectUtils.getClassByName("androidx.fragment.app.Fragment");
        Class<?> classByName3 = AnsReflectUtils.getClassByName("androidx.fragment.app.Fragment");
        if (classByName2 == null && classByName3 == null && classByName == null) {
            return true;
        }
        if (classByName2 != null && classByName2.isInstance(obj)) {
            return false;
        }
        if (classByName3 == null || !classByName3.isInstance(obj)) {
            return classByName == null || !classByName.isInstance(obj);
        }
        return false;
    }

    @Override
    public void onFragmentViewCreated(Object obj, View view, Bundle bundle, boolean z) {
    }

    @Override
    public void trackFragmentResume(Object obj, boolean z, long j) {
        try {
            if (checkFragmentPVEnable(obj, z) && !isNotFragment(obj)) {
                Object objC = a.c(obj);
                if (objC == null) {
                    if (fragmentIsShow(obj) && fragmentGetUserVisibleHint(obj)) {
                        autoTrackFragmentPageView(obj, z, j);
                    }
                } else if (fragmentIsShow(obj) && fragmentGetUserVisibleHint(obj) && fragmentIsShow(objC) && fragmentGetUserVisibleHint(objC)) {
                    autoTrackFragmentPageView(obj, z, j);
                }
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    @Override
    public void trackFragmentSetUserVisibleHint(Object obj, boolean z, boolean z2, long j) {
        try {
            if (checkFragmentPVEnable(obj, z2) && !isNotFragment(obj)) {
                Object objC = a.c(obj);
                if (objC == null) {
                    if (z && fragmentIsResumed(obj) && fragmentIsShow(obj)) {
                        autoTrackFragmentPageView(obj, z2, j);
                    }
                } else if (z && fragmentGetUserVisibleHint(objC) && fragmentIsResumed(obj) && fragmentIsResumed(objC) && fragmentIsShow(obj) && fragmentIsShow(objC)) {
                    autoTrackFragmentPageView(obj, z2, j);
                }
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    @Override
    public void trackOnHiddenChanged(Object obj, boolean z, boolean z2, long j) {
        try {
            if (checkFragmentPVEnable(obj, z2) && !isNotFragment(obj)) {
                Object objC = a.c(obj);
                if (objC == null) {
                    if (!z && fragmentIsResumed(obj) && fragmentGetUserVisibleHint(obj)) {
                        autoTrackFragmentPageView(obj, z2, j);
                    }
                } else if (!z && fragmentIsShow(objC) && fragmentIsResumed(obj) && fragmentIsResumed(objC) && fragmentGetUserVisibleHint(obj) && fragmentGetUserVisibleHint(objC)) {
                    autoTrackFragmentPageView(obj, z2, j);
                }
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }
}
