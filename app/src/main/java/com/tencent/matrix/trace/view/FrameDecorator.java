package com.tencent.matrix.trace.view;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;
import com.tencent.matrix.AppActiveMatrixDelegate;
import com.tencent.matrix.Matrix;
import com.tencent.matrix.listeners.IAppForeground;
import com.tencent.matrix.trace.R;
import com.tencent.matrix.trace.TracePlugin;
import com.tencent.matrix.trace.core.UIThreadMonitor;
import com.tencent.matrix.trace.listeners.IDoFrameListener;
import com.tencent.matrix.util.MatrixHandlerThread;
import com.tencent.matrix.util.MatrixLog;
import java.util.concurrent.Executor;

public class FrameDecorator extends IDoFrameListener implements IAppForeground {
    private static final String TAG = "Matrix.FrameDecorator";
    private static FrameDecorator instance;
    private View.OnClickListener clickListener;
    private DisplayMetrics displayMetrics;
    private Executor executor;
    private Handler handler;
    private boolean isEnable;
    private boolean isShowing;
    private long[] lastCost;
    private long[] lastFrames;
    private WindowManager.LayoutParams layoutParam;
    private long sumFrameCost;
    private long sumFrames;
    private Runnable updateDefaultRunnable;
    private FloatFrameView view;
    private WindowManager windowManager;
    private static Handler mainHandler = new Handler(Looper.getMainLooper());
    private static final Object lock = new Object();

    private FrameDecorator(Context context, final FloatFrameView floatFrameView) {
        this.displayMetrics = new DisplayMetrics();
        this.isEnable = true;
        this.lastCost = new long[1];
        this.lastFrames = new long[1];
        this.updateDefaultRunnable = new Runnable() {
            @Override
            public void run() {
                FrameDecorator.this.view.fpsView.setText("60.00 FPS");
                FrameDecorator.this.view.fpsView.setTextColor(FrameDecorator.this.view.getResources().getColor(17170453));
            }
        };
        this.executor = new Executor() {
            @Override
            public void execute(Runnable runnable) {
                FrameDecorator.this.getHandler().post(runnable);
            }
        };
        this.view = floatFrameView;
        AppActiveMatrixDelegate.INSTANCE.addListener(this);
        floatFrameView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View view) {
                TracePlugin tracePlugin;
                MatrixLog.i("Matrix.FrameDecorator", "onViewAttachedToWindow", new Object[0]);
                if (!Matrix.isInstalled() || (tracePlugin = (TracePlugin) Matrix.with().getPluginByClass(TracePlugin.class)) == null) {
                    return;
                }
                tracePlugin.getFrameTracer().addListener(FrameDecorator.this);
            }

            @Override
            public void onViewDetachedFromWindow(View view) {
                TracePlugin tracePlugin;
                MatrixLog.i("Matrix.FrameDecorator", "onViewDetachedFromWindow", new Object[0]);
                if (!Matrix.isInstalled() || (tracePlugin = (TracePlugin) Matrix.with().getPluginByClass(TracePlugin.class)) == null) {
                    return;
                }
                tracePlugin.getFrameTracer().removeListener(FrameDecorator.this);
            }
        });
        initLayoutParams(context);
        floatFrameView.setOnTouchListener(new View.OnTouchListener() {
            float downX = 0.0f;
            float downY = 0.0f;
            int downOffsetX = 0;
            int downOffsetY = 0;

            @Override
            public boolean onTouch(final View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    this.downX = motionEvent.getX();
                    this.downY = motionEvent.getY();
                    this.downOffsetX = FrameDecorator.this.layoutParam.x;
                    this.downOffsetY = FrameDecorator.this.layoutParam.y;
                } else if (action == 1) {
                    int[] iArr = new int[2];
                    iArr[0] = FrameDecorator.this.layoutParam.x;
                    iArr[1] = FrameDecorator.this.layoutParam.x > FrameDecorator.this.displayMetrics.widthPixels / 2 ? FrameDecorator.this.displayMetrics.widthPixels - floatFrameView.getWidth() : 0;
                    ValueAnimator valueAnimatorOfPropertyValuesHolder = ValueAnimator.ofPropertyValuesHolder(PropertyValuesHolder.ofInt("trans", iArr));
                    valueAnimatorOfPropertyValuesHolder.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            if (FrameDecorator.this.isShowing) {
                                FrameDecorator.this.layoutParam.x = ((Integer) valueAnimator.getAnimatedValue("trans")).intValue();
                                FrameDecorator.this.windowManager.updateViewLayout(view, FrameDecorator.this.layoutParam);
                            }
                        }
                    });
                    valueAnimatorOfPropertyValuesHolder.setInterpolator(new AccelerateInterpolator());
                    valueAnimatorOfPropertyValuesHolder.setDuration(180L).start();
                    int i = FrameDecorator.this.layoutParam.x;
                    int i2 = FrameDecorator.this.layoutParam.y;
                    if (Math.abs(i - this.downOffsetX) <= 20 && Math.abs(i2 - this.downOffsetY) <= 20 && FrameDecorator.this.clickListener != null) {
                        FrameDecorator.this.clickListener.onClick(view);
                    }
                } else if (action == 2) {
                    float x = motionEvent.getX();
                    float y = motionEvent.getY();
                    FrameDecorator.this.layoutParam.x = (int) (r2.x + ((x - this.downX) / 3.0f));
                    FrameDecorator.this.layoutParam.y = (int) (r0.y + ((y - this.downY) / 3.0f));
                    if (view != null) {
                        FrameDecorator.this.windowManager.updateViewLayout(view, FrameDecorator.this.layoutParam);
                    }
                }
                return true;
            }
        });
    }

    public static FrameDecorator get() {
        return instance;
    }

    private Handler getHandler() {
        Handler handler = this.handler;
        if (handler == null || !handler.getLooper().getThread().isAlive()) {
            this.handler = new Handler(MatrixHandlerThread.getDefaultHandlerThread().getLooper());
        }
        return this.handler;
    }

    public static FrameDecorator getInstance(final Context context) {
        if (instance == null) {
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                instance = new FrameDecorator(context, new FloatFrameView(context));
            } else {
                try {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            FrameDecorator unused = FrameDecorator.instance = new FrameDecorator(context, new FloatFrameView(context));
                            synchronized (FrameDecorator.lock) {
                                FrameDecorator.lock.notifyAll();
                            }
                        }
                    });
                    synchronized (lock) {
                        lock.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return instance;
    }

    private void initLayoutParams(Context context) {
        WindowManager windowManager = (WindowManager) context.getApplicationContext().getSystemService("window");
        this.windowManager = windowManager;
        try {
            windowManager.getDefaultDisplay().getMetrics(this.displayMetrics);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            this.windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            this.layoutParam = new WindowManager.LayoutParams();
            if (Build.VERSION.SDK_INT >= 26) {
                this.layoutParam.type = 2038;
            } else {
                this.layoutParam.type = 2002;
            }
            this.layoutParam.flags = 40;
            this.layoutParam.gravity = 8388659;
            this.layoutParam.x = displayMetrics.widthPixels - (this.view.getLayoutParams().width * 2);
            this.layoutParam.y = 0;
            this.layoutParam.width = -2;
            this.layoutParam.height = -2;
            this.layoutParam.format = -2;
        } catch (Exception unused) {
        }
    }

    private void updateView(final TextView textView, final float f) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                textView.setText(String.format("%.2f FPS", Float.valueOf(f)));
                float f2 = f;
                if (f2 >= 50.0f) {
                    TextView textView2 = textView;
                    textView2.setTextColor(textView2.getResources().getColor(17170453));
                } else if (f2 >= 30.0f) {
                    TextView textView3 = textView;
                    textView3.setTextColor(textView3.getResources().getColor(17170457));
                } else {
                    TextView textView4 = textView;
                    textView4.setTextColor(textView4.getResources().getColor(17170455));
                }
            }
        });
    }

    public void dismiss() {
        if (this.isEnable) {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (FrameDecorator.this.isShowing) {
                        FrameDecorator.this.isShowing = false;
                        FrameDecorator.this.windowManager.removeView(FrameDecorator.this.view);
                    }
                }
            });
        }
    }

    @Override
    public void doFrameAsync(String str, long j, long j2, int i, boolean z) {
        super.doFrameAsync(str, j, j2, i, z);
        long frameIntervalNanos = this.sumFrameCost + (((i + 1) * UIThreadMonitor.getMonitor().getFrameIntervalNanos()) / 1000000);
        this.sumFrameCost = frameIntervalNanos;
        long j3 = this.sumFrames + 1;
        this.sumFrames = j3;
        long j4 = frameIntervalNanos - this.lastCost[0];
        long j5 = j3 - this.lastFrames[0];
        if (j4 >= 200) {
            float fMin = Math.min(60.0f, (j5 * 1000.0f) / j4);
            updateView(this.view.fpsView, fMin);
            this.view.chartView.addFps((int) fMin);
            this.lastCost[0] = this.sumFrameCost;
            this.lastFrames[0] = this.sumFrames;
            mainHandler.removeCallbacks(this.updateDefaultRunnable);
            mainHandler.postDelayed(this.updateDefaultRunnable, 130L);
        }
    }

    @Override
    public Executor getExecutor() {
        return this.executor;
    }

    public FloatFrameView getView() {
        return this.view;
    }

    public boolean isEnable() {
        return this.isEnable;
    }

    public boolean isShowing() {
        return this.isShowing;
    }

    @Override
    public void onForeground(final boolean z) {
        Handler handler;
        MatrixLog.i("Matrix.FrameDecorator", "[onForeground] isForeground:%s", Boolean.valueOf(z));
        if (this.isEnable && (handler = mainHandler) != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (z) {
                        FrameDecorator.this.show();
                    } else {
                        FrameDecorator.this.dismiss();
                    }
                }
            });
        }
    }

    public void setClickListener(View.OnClickListener onClickListener) {
        this.clickListener = onClickListener;
    }

    public void setEnable(boolean z) {
        this.isEnable = z;
    }

    public void setExtraInfo(String str) {
        TextView textView;
        if (getView() == null || (textView = (TextView) getView().findViewById(R.id.extra_info)) == null) {
            return;
        }
        textView.setText(str);
    }

    public void show() {
        if (this.isEnable) {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (FrameDecorator.this.isShowing) {
                        return;
                    }
                    FrameDecorator.this.isShowing = true;
                    FrameDecorator.this.windowManager.addView(FrameDecorator.this.view, FrameDecorator.this.layoutParam);
                }
            });
        }
    }
}
