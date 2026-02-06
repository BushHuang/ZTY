package com.analysys;

import com.analysys.utils.AnalysysUtil;
import com.analysys.utils.CommonUtils;

public class i {

    private static i f55a;
    private ObserverListener b;

    private i() {
    }

    public static i a() {
        if (f55a == null) {
            synchronized (i.class) {
                if (f55a == null) {
                    f55a = new i();
                }
            }
        }
        return f55a;
    }

    public void a(long j) {
        ObserverListener observerListener = this.b;
        if (observerListener != null) {
            observerListener.onUserProfile("calibration_time", String.valueOf(j));
        }
    }

    public void a(ObserverListener observerListener) {
        this.b = observerListener;
        if (observerListener != null) {
            observerListener.onUserProfile("xwho", CommonUtils.getUserId(AnalysysUtil.getContext()));
        }
    }

    public void a(String str) {
        ObserverListener observerListener = this.b;
        if (observerListener != null) {
            observerListener.onUserProfile("xwho", str);
        }
    }

    public void b(String str) {
        ObserverListener observerListener = this.b;
        if (observerListener != null) {
            observerListener.onEventMessage(str);
        }
    }
}
