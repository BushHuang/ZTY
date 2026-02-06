package com.analysys.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Base64OutputStream;
import android.view.View;
import android.view.WindowManager;
import com.analysys.utils.AnalysysUtil;
import com.analysys.utils.AnsReflectUtils;
import com.analysys.utils.ExceptionUtil;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WindowUIHelper {
    public static final String DIALOG_SUFFIX = "(dialog)";
    public static final String FLOATWIN_SUFFIX = "(float)";
    public static final String POPWIN_SUFFIX = "(popup)";
    private static List sGlobalViews;
    private static Object sWMGlobal;

    public static class PageRootInfo {
        public final String activityName;
        public final View rootView;
        public PageViewBitmap screenshot = null;
        public float scale = 1.0f;

        public PageRootInfo(String str, View view) {
            this.activityName = str;
            this.rootView = view;
        }

        public String getSign() {
            PageViewBitmap pageViewBitmap = this.screenshot;
            if (pageViewBitmap == null || pageViewBitmap.mCached == null) {
                return null;
            }
            Bitmap bitmap = this.screenshot.mCached;
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int i = width * height;
            bitmap.getPixels(new int[i], 0, width, 0, 0, width, height);
            long j = 0;
            for (int i2 = 0; i2 < i; i2++) {
                j += r9[i2];
            }
            return String.valueOf(j);
        }
    }

    public static class PageViewBitmap {
        private final Paint mPaint = new Paint(2);
        private Bitmap mCached = null;

        public synchronized void recreate(int i, int i2, int i3, Bitmap bitmap) {
            if (this.mCached != null && this.mCached.getWidth() == i && this.mCached.getHeight() == i2) {
                this.mCached.eraseColor(0);
            } else {
                try {
                    this.mCached = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_4444);
                } catch (OutOfMemoryError unused) {
                    this.mCached = null;
                }
                if (this.mCached != null) {
                    this.mCached.setDensity(i3);
                }
            }
            if (this.mCached != null) {
                new Canvas(this.mCached).drawBitmap(bitmap, 0.0f, 0.0f, this.mPaint);
            }
        }

        public synchronized void writeBitmapJSON(Bitmap.CompressFormat compressFormat, int i, OutputStream outputStream) {
            if (this.mCached == null || this.mCached.getWidth() == 0 || this.mCached.getHeight() == 0) {
                outputStream.write("null".getBytes());
            } else {
                outputStream.write(34);
                Base64OutputStream base64OutputStream = new Base64OutputStream(outputStream, 2);
                this.mCached.compress(Bitmap.CompressFormat.PNG, i, base64OutputStream);
                base64OutputStream.flush();
                outputStream.write(34);
            }
        }
    }

    private static Activity getActivityBase(Context context) {
        int i = 0;
        while (!(context instanceof Activity)) {
            if (context == null || !UniqueViewHelper.isContextThemeWrapper(context.getClass().getName())) {
                return null;
            }
            int i2 = i + 1;
            if (i > 10) {
                return null;
            }
            context = (Context) AnsReflectUtils.invokeMethod(context, "getBaseContext");
            i = i2;
        }
        return (Activity) context;
    }

    public static String getActivityName(Object obj) {
        return obj == null ? "" : obj.getClass().getName();
    }

    public static List<RootView> getAllWindowViews() {
        List globalViews = getGlobalViews(true);
        if (globalViews == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < globalViews.size(); i++) {
            RootView rootView = getRootView((View) globalViews.get(i));
            if (rootView != null) {
                arrayList.add(rootView);
            }
        }
        return arrayList;
    }

    public static List<PageRootInfo> getCurrentWindowViews(Activity activity, String str) {
        List globalViews = getGlobalViews(true);
        if (globalViews == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (int i = 0; i < globalViews.size(); i++) {
            View view = (View) globalViews.get(i);
            View viewFindViewById = view.findViewById(16908290);
            Context context = view.getContext();
            if (viewFindViewById != null) {
                Activity activityBase = getActivityBase(viewFindViewById.getContext());
                if (activityBase != activity) {
                    if (activityBase != null && z) {
                        break;
                    }
                } else if (isActivityContext(context)) {
                    arrayList.add(0, new PageRootInfo(str, view));
                    z = true;
                } else {
                    arrayList.add(new PageRootInfo(getDialogName(activity), view));
                }
            } else if (view.getClass().getName().contains("Popup") && context == activity) {
                arrayList.add(new PageRootInfo(getPopupWindowName(activity), view));
            }
        }
        return arrayList;
    }

    public static String getDialogName(Object obj) {
        return (obj instanceof String ? (String) obj : obj instanceof Activity ? getActivityName(obj) : "") + "(dialog)";
    }

    public static String getFloatWindowName(Object obj) {
        Activity activityBase;
        return (obj instanceof String ? (String) obj : (!(obj instanceof Context) || (activityBase = getActivityBase((Context) obj)) == null) ? "" : getActivityName(activityBase)) + "(float)";
    }

    public static List getGlobalViews(boolean z) {
        Object windowManagerGlobal;
        if (sGlobalViews == null && (windowManagerGlobal = getWindowManagerGlobal()) != null) {
            Object field = AnsReflectUtils.getField(windowManagerGlobal, "mViews");
            if (field instanceof List) {
                sGlobalViews = (List) field;
            } else if ((field instanceof View[]) && z) {
                return new ArrayList(Arrays.asList((View[]) field));
            }
        }
        return sGlobalViews;
    }

    public static String getPageName(View view) {
        RootView rootView = getRootView(view);
        return (rootView == null || rootView.pageName == null) ? "" : rootView.pageName;
    }

    public static String getPopupWindowName(Object obj) {
        Activity activityBase;
        return (obj instanceof String ? (String) obj : (!(obj instanceof Context) || (activityBase = getActivityBase((Context) obj)) == null) ? "" : getActivityName(activityBase)) + "(popup)";
    }

    public static RootView getRootView(View view) {
        View rootView;
        if (view == null || (rootView = view.getRootView()) == null) {
            return null;
        }
        Context context = rootView.getContext();
        View viewFindViewById = rootView.findViewById(16908290);
        if (viewFindViewById == null) {
            if (rootView.getClass().getName().contains("Popup")) {
                return new RootView(rootView, getPopupWindowName(context));
            }
            return null;
        }
        Activity activityBase = getActivityBase(viewFindViewById.getContext());
        if (activityBase != null) {
            return isActivityContext(context) ? new RootView(rootView, getActivityName(activityBase)) : new RootView(rootView, getDialogName(activityBase));
        }
        return null;
    }

    public static List<PageRootInfo> getTopOpaqueActivityViews() {
        Activity activityBase;
        List globalViews = getGlobalViews(true);
        if (globalViews == null || globalViews.size() < 2) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int size = globalViews.size() - 1; size >= 0; size--) {
            View view = (View) globalViews.get(size);
            Context context = view.getContext();
            View viewFindViewById = view.findViewById(16908290);
            if (viewFindViewById != null && isActivityContext(context) && (activityBase = getActivityBase(viewFindViewById.getContext())) != null) {
                List<PageRootInfo> currentWindowViews = getCurrentWindowViews(activityBase, activityBase.getClass().getName());
                if (currentWindowViews != null && currentWindowViews.size() > 0) {
                    arrayList.addAll(0, currentWindowViews);
                }
                if (size != globalViews.size() - 1 && !isTranslucentActivity(activityBase)) {
                    break;
                }
            }
        }
        return arrayList;
    }

    public static Object getWindowManagerGlobal() {
        WindowManager windowManager;
        if (sWMGlobal == null && (windowManager = (WindowManager) AnalysysUtil.getContext().getSystemService("window")) != null) {
            sWMGlobal = AnsReflectUtils.getField(windowManager, "mGlobal");
        }
        return sWMGlobal;
    }

    private static boolean isActivityContext(Context context) {
        if (context == null) {
            return false;
        }
        if (context instanceof Activity) {
            return true;
        }
        return context.getClass().getName().endsWith("DecorContext");
    }

    public static boolean isRootViewAlive(View view) {
        List globalViews = getGlobalViews(true);
        return globalViews != null && globalViews.contains(view);
    }

    public static boolean isTranslucentActivity(Activity activity) {
        try {
            return activity.getWindow().getWindowStyle().getBoolean(((Integer) AnsReflectUtils.getStaticField(Class.forName("com.android.internal.R$styleable"), "Window_windowIsTranslucent")).intValue(), false);
        } catch (Throwable th) {
            ExceptionUtil.exceptionPrint(th);
            return false;
        }
    }

    public static boolean setGlobalViews(Object obj, List list) {
        if (!AnsReflectUtils.setField(obj, "mViews", list)) {
            return false;
        }
        sGlobalViews = list;
        return true;
    }
}
