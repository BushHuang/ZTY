package com.huawei.hms.api;

import com.huawei.hms.support.log.HMSLog;
import java.sql.Timestamp;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FailedBinderCallBack {
    public static final String CALLER_ID = "callId";

    private static FailedBinderCallBack f222a;
    private static Map<Long, BinderCallBack> b = new ConcurrentHashMap();
    private static final Object c = new Object();

    public interface BinderCallBack {
        void binderCallBack(int i);
    }

    private FailedBinderCallBack() {
    }

    private void a() {
        long time = new Timestamp(System.currentTimeMillis()).getTime() - 10000;
        for (Long l : b.keySet()) {
            if (time >= l.longValue()) {
                b.remove(l);
            }
        }
    }

    private void a(Long l, BinderCallBack binderCallBack) {
        if (b == null) {
            HMSLog.e("FailedBinderCallBack", "binderCallBackMap is null");
        } else {
            a();
            b.put(l, binderCallBack);
        }
    }

    public static FailedBinderCallBack getInstance() {
        synchronized (c) {
            if (f222a == null) {
                f222a = new FailedBinderCallBack();
            }
        }
        return f222a;
    }

    public BinderCallBack getCallBack(Long l) {
        Map<Long, BinderCallBack> map = b;
        if (map != null) {
            return map.remove(l);
        }
        HMSLog.e("FailedBinderCallBack", "binderCallBackMap is null");
        return null;
    }

    public void setCallBack(Long l, BinderCallBack binderCallBack) {
        a(l, binderCallBack);
    }
}
