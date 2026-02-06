package com.tencent.matrix.util;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Printer;
import com.tencent.matrix.AppActiveMatrixDelegate;
import com.tencent.matrix.listeners.IAppForeground;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

public class MatrixHandlerThread {
    public static final String MATRIX_THREAD_NAME = "default_matrix_thread";
    private static final String TAG = "Matrix.HandlerThread";
    private static volatile Handler defaultHandler;
    private static volatile HandlerThread defaultHandlerThread;
    private static volatile Handler defaultMainHandler = new Handler(Looper.getMainLooper());
    private static HashSet<HandlerThread> handlerThreads = new HashSet<>();
    public static boolean isDebug = false;

    private static final class LooperPrinter implements Printer, IAppForeground {
        private ConcurrentHashMap<String, Info> hashMap = new ConcurrentHashMap<>();
        private boolean isForeground;

        class Info {
            int count;
            String key;

            Info() {
            }

            public String toString() {
                return this.key + ":" + this.count;
            }
        }

        LooperPrinter() {
            AppActiveMatrixDelegate.INSTANCE.addListener(this);
            this.isForeground = AppActiveMatrixDelegate.INSTANCE.isAppForeground();
        }

        @Override
        public void onForeground(boolean z) {
            this.isForeground = z;
            if (!z) {
                this.hashMap.clear();
                return;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            LinkedList linkedList = new LinkedList();
            for (Info info : this.hashMap.values()) {
                if (info.count > 1) {
                    linkedList.add(info);
                }
            }
            Collections.sort(linkedList, new Comparator<Info>() {
                @Override
                public int compare(Info info2, Info info3) {
                    return info3.count - info2.count;
                }
            });
            this.hashMap.clear();
            if (linkedList.isEmpty()) {
                return;
            }
            MatrixLog.i("Matrix.HandlerThread", "matrix default thread has exec in background! %s cost:%s", linkedList, Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
        }

        @Override
        public void println(String str) {
            if (!this.isForeground && str.charAt(0) == '>') {
                int iIndexOf = str.indexOf("} ");
                int iIndexOf2 = str.indexOf("@", iIndexOf);
                if (iIndexOf < 0 || iIndexOf2 < 0) {
                    return;
                }
                String strSubstring = str.substring(iIndexOf, iIndexOf2);
                Info info = this.hashMap.get(strSubstring);
                if (info == null) {
                    info = new Info();
                    info.key = strSubstring;
                    this.hashMap.put(strSubstring, info);
                }
                info.count++;
            }
        }
    }

    public static Handler getDefaultHandler() {
        if (defaultHandler == null) {
            getDefaultHandlerThread();
        }
        return defaultHandler;
    }

    public static HandlerThread getDefaultHandlerThread() {
        HandlerThread handlerThread;
        synchronized (MatrixHandlerThread.class) {
            if (defaultHandlerThread == null) {
                defaultHandlerThread = new HandlerThread("default_matrix_thread");
                defaultHandlerThread.start();
                defaultHandler = new Handler(defaultHandlerThread.getLooper());
                defaultHandlerThread.getLooper().setMessageLogging(isDebug ? new LooperPrinter() : null);
                MatrixLog.w("Matrix.HandlerThread", "create default handler thread, we should use these thread normal, isDebug:%s", Boolean.valueOf(isDebug));
            }
            handlerThread = defaultHandlerThread;
        }
        return handlerThread;
    }

    public static Handler getDefaultMainHandler() {
        return defaultMainHandler;
    }

    public static HandlerThread getNewHandlerThread(String str) {
        Iterator<HandlerThread> it = handlerThreads.iterator();
        while (it.hasNext()) {
            if (!it.next().isAlive()) {
                it.remove();
                MatrixLog.w("Matrix.HandlerThread", "warning: remove dead handler thread with name %s", str);
            }
        }
        HandlerThread handlerThread = new HandlerThread(str);
        handlerThread.start();
        handlerThreads.add(handlerThread);
        MatrixLog.w("Matrix.HandlerThread", "warning: create new handler thread with name %s, alive thread size:%d", str, Integer.valueOf(handlerThreads.size()));
        return handlerThread;
    }
}
