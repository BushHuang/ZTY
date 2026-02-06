package com.tencent.matrix.trace.core;

import android.os.Build;
import android.os.Looper;
import android.os.MessageQueue;
import android.os.SystemClock;
import android.util.Log;
import android.util.Printer;
import com.tencent.matrix.util.MatrixLog;
import com.tencent.matrix.util.ReflectUtils;
import java.util.HashSet;
import java.util.Iterator;

public class LooperMonitor implements MessageQueue.IdleHandler {
    private static final long CHECK_TIME = 60000;
    private static final String TAG = "Matrix.LooperMonitor";
    private long lastCheckPrinterTime;
    private final HashSet<LooperDispatchListener> listeners;
    private Looper looper;
    private LooperPrinter printer;
    private static final LooperMonitor mainMonitor = new LooperMonitor();
    private static boolean isReflectLoggingError = false;

    public static abstract class LooperDispatchListener {
        boolean isHasDispatchStart = false;

        public void dispatchEnd() {
        }

        public void dispatchStart() {
        }

        public boolean isValid() {
            return false;
        }

        public void onDispatchEnd(String str) {
            this.isHasDispatchStart = false;
            dispatchEnd();
        }

        public void onDispatchStart(String str) {
            this.isHasDispatchStart = true;
            dispatchStart();
        }
    }

    class LooperPrinter implements Printer {
        boolean isHasChecked = false;
        boolean isValid = false;
        public Printer origin;

        LooperPrinter(Printer printer) {
            this.origin = printer;
        }

        @Override
        public void println(String str) {
            Printer printer = this.origin;
            if (printer != null) {
                printer.println(str);
                if (this.origin == this) {
                    throw new RuntimeException("Matrix.LooperMonitor origin == this");
                }
            }
            if (!this.isHasChecked) {
                boolean z = str.charAt(0) == '>' || str.charAt(0) == '<';
                this.isValid = z;
                this.isHasChecked = true;
                if (!z) {
                    MatrixLog.e("Matrix.LooperMonitor", "[println] Printer is inValid! x:%s", str);
                }
            }
            if (this.isValid) {
                LooperMonitor.this.dispatch(str.charAt(0) == '>', str);
            }
        }
    }

    private LooperMonitor() {
        this(Looper.getMainLooper());
    }

    public LooperMonitor(Looper looper) {
        this.listeners = new HashSet<>();
        this.lastCheckPrinterTime = 0L;
        if (looper == null) {
            Log.e("Matrix.LooperMonitor", "Looper is null");
            return;
        }
        this.looper = looper;
        resetPrinter();
        addIdleHandler(looper);
    }

    private synchronized void addIdleHandler(Looper looper) {
        if (Build.VERSION.SDK_INT >= 23) {
            looper.getQueue().addIdleHandler(this);
        } else {
            try {
                ((MessageQueue) ReflectUtils.get(looper.getClass(), "mQueue", looper)).addIdleHandler(this);
            } catch (Exception e) {
                Log.e("Matrix.LooperMonitor", "[removeIdleHandler] %s", e);
            }
        }
    }

    private void dispatch(boolean z, String str) {
        Iterator<LooperDispatchListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            LooperDispatchListener next = it.next();
            if (next.isValid()) {
                if (z) {
                    if (!next.isHasDispatchStart) {
                        next.onDispatchStart(str);
                    }
                } else if (next.isHasDispatchStart) {
                    next.onDispatchEnd(str);
                }
            } else if (!z && next.isHasDispatchStart) {
                next.dispatchEnd();
            }
        }
    }

    static void register(LooperDispatchListener looperDispatchListener) {
        mainMonitor.addListener(looperDispatchListener);
    }

    private synchronized void removeIdleHandler(Looper looper) {
        if (Build.VERSION.SDK_INT >= 23) {
            looper.getQueue().removeIdleHandler(this);
        } else {
            try {
                ((MessageQueue) ReflectUtils.get(looper.getClass(), "mQueue", looper)).removeIdleHandler(this);
            } catch (Exception e) {
                Log.e("Matrix.LooperMonitor", "[removeIdleHandler] %s", e);
            }
        }
    }

    private synchronized void resetPrinter() {
        Printer printer;
        Exception e;
        Printer printer2 = null;
        try {
        } catch (Exception e2) {
            printer = null;
            e = e2;
        }
        if (!isReflectLoggingError) {
            printer = (Printer) ReflectUtils.get(this.looper.getClass(), "mLogging", this.looper);
            try {
                if (printer == this.printer) {
                    if (this.printer != null) {
                        return;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                isReflectLoggingError = true;
                Log.e("Matrix.LooperMonitor", "[resetPrinter] %s", e);
                printer2 = printer;
                if (this.printer != null) {
                }
                Looper looper = this.looper;
                LooperPrinter looperPrinter = new LooperPrinter(printer2);
                this.printer = looperPrinter;
                looper.setMessageLogging(looperPrinter);
                if (printer2 != null) {
                }
            }
            printer2 = printer;
        }
        if (this.printer != null) {
            MatrixLog.w("Matrix.LooperMonitor", "maybe thread:%s printer[%s] was replace other[%s]!", this.looper.getThread().getName(), this.printer, printer2);
        }
        Looper looper2 = this.looper;
        LooperPrinter looperPrinter2 = new LooperPrinter(printer2);
        this.printer = looperPrinter2;
        looper2.setMessageLogging(looperPrinter2);
        if (printer2 != null) {
            MatrixLog.i("Matrix.LooperMonitor", "reset printer, originPrinter[%s] in %s", printer2, this.looper.getThread().getName());
        }
    }

    static void unregister(LooperDispatchListener looperDispatchListener) {
        mainMonitor.removeListener(looperDispatchListener);
    }

    public void addListener(LooperDispatchListener looperDispatchListener) {
        synchronized (this.listeners) {
            this.listeners.add(looperDispatchListener);
        }
    }

    public HashSet<LooperDispatchListener> getListeners() {
        return this.listeners;
    }

    public synchronized void onRelease() {
        if (this.printer != null) {
            synchronized (this.listeners) {
                this.listeners.clear();
            }
            MatrixLog.v("Matrix.LooperMonitor", "[onRelease] %s, origin printer:%s", this.looper.getThread().getName(), this.printer.origin);
            this.looper.setMessageLogging(this.printer.origin);
            removeIdleHandler(this.looper);
            this.looper = null;
            this.printer = null;
        }
    }

    @Override
    public boolean queueIdle() {
        if (SystemClock.uptimeMillis() - this.lastCheckPrinterTime < 60000) {
            return true;
        }
        resetPrinter();
        this.lastCheckPrinterTime = SystemClock.uptimeMillis();
        return true;
    }

    public void removeListener(LooperDispatchListener looperDispatchListener) {
        synchronized (this.listeners) {
            this.listeners.remove(looperDispatchListener);
        }
    }
}
