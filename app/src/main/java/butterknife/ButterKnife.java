package butterknife;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.util.Property;
import android.view.View;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ButterKnife {
    static final Map<Class<?>, ViewBinder<Object>> BINDERS = new LinkedHashMap();
    static final ViewBinder<Object> NOP_VIEW_BINDER = new ViewBinder<Object>() {
        @Override
        public void bind(Finder finder, Object obj, Object obj2) {
        }

        @Override
        public void unbind(Object obj) {
        }
    };
    private static final String TAG = "ButterKnife";
    private static boolean debug = false;

    public interface Action<T extends View> {
        void apply(T t, int i);
    }

    public enum Finder {
        VIEW {
            @Override
            protected View findView(Object obj, int i) {
                return ((View) obj).findViewById(i);
            }

            @Override
            public Context getContext(Object obj) {
                return ((View) obj).getContext();
            }
        },
        ACTIVITY {
            @Override
            protected View findView(Object obj, int i) {
                return ((Activity) obj).findViewById(i);
            }

            @Override
            public Context getContext(Object obj) {
                return (Activity) obj;
            }
        },
        DIALOG {
            @Override
            protected View findView(Object obj, int i) {
                return ((Dialog) obj).findViewById(i);
            }

            @Override
            public Context getContext(Object obj) {
                return ((Dialog) obj).getContext();
            }
        };

        public static <T> T[] arrayOf(T... tArr) {
            return (T[]) filterNull(tArr);
        }

        private static <T> T[] filterNull(T[] tArr) {
            int i = 0;
            for (T t : tArr) {
                if (t != null) {
                    tArr[i] = t;
                    i++;
                }
            }
            return (T[]) Arrays.copyOfRange(tArr, 0, i);
        }

        public static <T> List<T> listOf(T... tArr) {
            return new ImmutableList(filterNull(tArr));
        }

        public <T> T castParam(Object obj, String str, int i, String str2, int i2) {
            return obj;
        }

        public <T> T castView(View view, int i, String str) {
            return view;
        }

        public <T> T findOptionalView(Object obj, int i, String str) {
            return (T) castView(findView(obj, i), i, str);
        }

        public <T> T findRequiredView(Object obj, int i, String str) throws Resources.NotFoundException {
            T t = (T) findOptionalView(obj, i, str);
            if (t != null) {
                return t;
            }
            throw new IllegalStateException("Required view '" + getContext(obj).getResources().getResourceEntryName(i) + "' with ID " + i + " for " + str + " was not found. If this view is optional add '@Nullable' annotation.");
        }

        protected abstract View findView(Object obj, int i);

        public abstract Context getContext(Object obj);
    }

    public interface Setter<T extends View, V> {
        void set(T t, V v, int i);
    }

    public interface ViewBinder<T> {
        void bind(Finder finder, T t, Object obj);

        void unbind(T t);
    }

    private ButterKnife() {
        throw new AssertionError("No instances.");
    }

    public static <T extends View, V> void apply(List<T> list, Property<? super T, V> property, V v) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            property.set(list.get(i), v);
        }
    }

    public static <T extends View> void apply(List<T> list, Action<? super T> action) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            action.apply(list.get(i), i);
        }
    }

    public static <T extends View, V> void apply(List<T> list, Setter<? super T, V> setter, V v) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            setter.set(list.get(i), v, i);
        }
    }

    public static void bind(Activity activity) {
        bind(activity, activity, Finder.ACTIVITY);
    }

    public static void bind(Dialog dialog) {
        bind(dialog, dialog, Finder.DIALOG);
    }

    public static void bind(View view) {
        bind(view, view, Finder.VIEW);
    }

    public static void bind(Object obj, Activity activity) {
        bind(obj, activity, Finder.ACTIVITY);
    }

    public static void bind(Object obj, Dialog dialog) {
        bind(obj, dialog, Finder.DIALOG);
    }

    public static void bind(Object obj, View view) {
        bind(obj, view, Finder.VIEW);
    }

    static void bind(Object obj, Object obj2, Finder finder) {
        Class<?> cls = obj.getClass();
        try {
            if (debug) {
                Log.d("ButterKnife", "Looking up view binder for " + cls.getName());
            }
            ViewBinder<Object> viewBinderFindViewBinderForClass = findViewBinderForClass(cls);
            if (viewBinderFindViewBinderForClass != null) {
                viewBinderFindViewBinderForClass.bind(finder, obj, obj2);
            }
        } catch (Exception e) {
            throw new RuntimeException("Unable to bind views for " + cls.getName(), e);
        }
    }

    public static <T extends View> T findById(Activity activity, int i) {
        return (T) activity.findViewById(i);
    }

    public static <T extends View> T findById(Dialog dialog, int i) {
        return (T) dialog.findViewById(i);
    }

    public static <T extends View> T findById(View view, int i) {
        return (T) view.findViewById(i);
    }

    private static ViewBinder<Object> findViewBinderForClass(Class<?> cls) throws IllegalAccessException, InstantiationException {
        ViewBinder<Object> viewBinderFindViewBinderForClass;
        ViewBinder<Object> viewBinder = BINDERS.get(cls);
        if (viewBinder != null) {
            if (debug) {
                Log.d("ButterKnife", "HIT: Cached in view binder map.");
            }
            return viewBinder;
        }
        String name = cls.getName();
        if (name.startsWith("android.") || name.startsWith("java.")) {
            if (debug) {
                Log.d("ButterKnife", "MISS: Reached framework class. Abandoning search.");
            }
            return NOP_VIEW_BINDER;
        }
        try {
            viewBinderFindViewBinderForClass = (ViewBinder) Class.forName(name + "$$ViewBinder").newInstance();
            if (debug) {
                Log.d("ButterKnife", "HIT: Loaded view binder class.");
            }
        } catch (ClassNotFoundException unused) {
            if (debug) {
                Log.d("ButterKnife", "Not found. Trying superclass " + cls.getSuperclass().getName());
            }
            viewBinderFindViewBinderForClass = findViewBinderForClass(cls.getSuperclass());
        }
        BINDERS.put(cls, viewBinderFindViewBinderForClass);
        return viewBinderFindViewBinderForClass;
    }

    public static void setDebug(boolean z) {
        debug = z;
    }

    public static void unbind(Object obj) {
        Class<?> cls = obj.getClass();
        try {
            if (debug) {
                Log.d("ButterKnife", "Looking up view binder for " + cls.getName());
            }
            ViewBinder<Object> viewBinderFindViewBinderForClass = findViewBinderForClass(cls);
            if (viewBinderFindViewBinderForClass != null) {
                viewBinderFindViewBinderForClass.unbind(obj);
            }
        } catch (Exception e) {
            throw new RuntimeException("Unable to unbind views for " + cls.getName(), e);
        }
    }
}
