package androidx.databinding;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DataBindingUtil {
    private static DataBinderMapper sMapper = new DataBinderMapperImpl();
    private static DataBindingComponent sDefaultComponent = null;

    private DataBindingUtil() {
    }

    public static <T extends ViewDataBinding> T bind(View view) {
        return (T) bind(view, sDefaultComponent);
    }

    public static <T extends ViewDataBinding> T bind(View view, DataBindingComponent dataBindingComponent) {
        T t = (T) getBinding(view);
        if (t != null) {
            return t;
        }
        Object tag = view.getTag();
        if (!(tag instanceof String)) {
            throw new IllegalArgumentException("View is not a binding layout");
        }
        int layoutId = sMapper.getLayoutId((String) tag);
        if (layoutId != 0) {
            return (T) sMapper.getDataBinder(dataBindingComponent, view, layoutId);
        }
        throw new IllegalArgumentException("View is not a binding layout. Tag: " + tag);
    }

    static <T extends ViewDataBinding> T bind(DataBindingComponent dataBindingComponent, View view, int i) {
        return (T) sMapper.getDataBinder(dataBindingComponent, view, i);
    }

    static <T extends ViewDataBinding> T bind(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        return (T) sMapper.getDataBinder(dataBindingComponent, viewArr, i);
    }

    private static <T extends ViewDataBinding> T bindToAddedViews(DataBindingComponent dataBindingComponent, ViewGroup viewGroup, int i, int i2) {
        int childCount = viewGroup.getChildCount();
        int i3 = childCount - i;
        if (i3 == 1) {
            return (T) bind(dataBindingComponent, viewGroup.getChildAt(childCount - 1), i2);
        }
        View[] viewArr = new View[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            viewArr[i4] = viewGroup.getChildAt(i4 + i);
        }
        return (T) bind(dataBindingComponent, viewArr, i2);
    }

    public static String convertBrIdToString(int i) {
        return sMapper.convertBrIdToString(i);
    }

    public static <T extends ViewDataBinding> T findBinding(View view) {
        while (view != null) {
            T t = (T) ViewDataBinding.getBinding(view);
            if (t != null) {
                return t;
            }
            Object tag = view.getTag();
            if (tag instanceof String) {
                String str = (String) tag;
                if (str.startsWith("layout") && str.endsWith("_0")) {
                    char cCharAt = str.charAt(6);
                    int iIndexOf = str.indexOf(47, 7);
                    boolean z = true;
                    boolean z2 = false;
                    if (cCharAt == '/') {
                        if (iIndexOf != -1) {
                            z = false;
                        }
                        z2 = z;
                        if (z2) {
                            return null;
                        }
                    } else {
                        if (cCharAt == '-' && iIndexOf != -1) {
                            if (str.indexOf(47, iIndexOf + 1) != -1) {
                            }
                            z2 = z;
                        }
                        if (z2) {
                        }
                    }
                }
            }
            Object parent = view.getParent();
            view = parent instanceof View ? (View) parent : null;
        }
        return null;
    }

    public static <T extends ViewDataBinding> T getBinding(View view) {
        return (T) ViewDataBinding.getBinding(view);
    }

    public static DataBindingComponent getDefaultComponent() {
        return sDefaultComponent;
    }

    public static <T extends ViewDataBinding> T inflate(LayoutInflater layoutInflater, int i, ViewGroup viewGroup, boolean z) {
        return (T) inflate(layoutInflater, i, viewGroup, z, sDefaultComponent);
    }

    public static <T extends ViewDataBinding> T inflate(LayoutInflater layoutInflater, int i, ViewGroup viewGroup, boolean z, DataBindingComponent dataBindingComponent) {
        boolean z2 = viewGroup != null && z;
        return z2 ? (T) bindToAddedViews(dataBindingComponent, viewGroup, z2 ? viewGroup.getChildCount() : 0, i) : (T) bind(dataBindingComponent, layoutInflater.inflate(i, viewGroup, z), i);
    }

    public static <T extends ViewDataBinding> T setContentView(Activity activity, int i) {
        return (T) setContentView(activity, i, sDefaultComponent);
    }

    public static <T extends ViewDataBinding> T setContentView(Activity activity, int i, DataBindingComponent dataBindingComponent) {
        activity.setContentView(i);
        return (T) bindToAddedViews(dataBindingComponent, (ViewGroup) activity.getWindow().getDecorView().findViewById(16908290), 0, i);
    }

    public static void setDefaultComponent(DataBindingComponent dataBindingComponent) {
        sDefaultComponent = dataBindingComponent;
    }
}
