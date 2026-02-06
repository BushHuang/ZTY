package com.xh.view;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HandlerThreadManager {
    private static final String HANDLER_THREAD_APP = "handler_thread_app";
    static final String HANDLER_THREAD_TOAST = "handler_thread_toast";
    private static final String TAG = "HandlerThreadManager";
    private static ConcurrentHashMap<String, HandlerThread> handlerThreads = new ConcurrentHashMap<>();

    public static Handler getAppHandler() {
        return new Handler(getCustomHandlerThread("handler_thread_app").getLooper());
    }

    public static Handler getCustomHandler(String str) {
        return new Handler(getCustomHandlerThread(str).getLooper());
    }

    private static synchronized HandlerThread getCustomHandlerThread(String str) {
        Log.w("HandlerThreadManager", String.format("warning: get new handler thread with name %s, alive thread size:%d", str, Integer.valueOf(handlerThreads.size())));
        for (Map.Entry<String, HandlerThread> entry : handlerThreads.entrySet()) {
            if (!entry.getValue().isAlive()) {
                handlerThreads.remove(entry.getKey());
                Log.w("HandlerThreadManager", String.format("warning: remove dead handler thread with name %s", str));
            }
        }
        if (handlerThreads.containsKey(str) && handlerThreads.get(str) != null) {
            return handlerThreads.get(str);
        }
        HandlerThread handlerThread = new HandlerThread(str);
        handlerThread.start();
        handlerThreads.put(str, handlerThread);
        return handlerThread;
    }
}
