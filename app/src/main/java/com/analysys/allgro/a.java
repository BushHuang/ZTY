package com.analysys.allgro;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.analysys.ANSAutoPageTracker;
import com.analysys.utils.AnalysysUtil;
import com.analysys.utils.AnsReflectUtils;
import com.analysys.utils.ExceptionUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class a {
    public static Activity a() {
        return AnalysysUtil.getCurActivity();
    }

    public static Activity a(Dialog dialog) {
        Activity activityA = a(dialog.getContext());
        if (activityA == null) {
            activityA = dialog.getOwnerActivity();
        }
        return activityA == null ? a() : activityA;
    }

    private static Activity a(Context context) {
        boolean z;
        if (context != null) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            if (context instanceof ContextWrapper) {
                while (true) {
                    z = context instanceof Activity;
                    if (z || !(context instanceof ContextWrapper)) {
                        break;
                    }
                    context = ((ContextWrapper) context).getBaseContext();
                }
                if (z) {
                    return (Activity) context;
                }
            }
        }
        return a();
    }

    public static String a(int i) {
        if (i == -1) {
            return "";
        }
        try {
            return AnalysysUtil.getContext().getResources().getResourceEntryName(i);
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
            return "";
        }
    }

    private static String a(Activity activity) {
        String string;
        PackageManager packageManager;
        if (activity != null) {
            try {
                if (Build.VERSION.SDK_INT >= 11) {
                    string = b(activity);
                    if (TextUtils.isEmpty(string)) {
                        string = null;
                    }
                }
                if (TextUtils.isEmpty(string)) {
                    string = activity.getTitle().toString();
                }
                if (!TextUtils.isEmpty(string) || (packageManager = activity.getPackageManager()) == null) {
                    return string;
                }
                ActivityInfo activityInfo = packageManager.getActivityInfo(activity.getComponentName(), 0);
                return !TextUtils.isEmpty(activityInfo.loadLabel(packageManager)) ? activityInfo.loadLabel(packageManager).toString() : string;
            } catch (Throwable th) {
                ExceptionUtil.exceptionThrow(th);
            }
        }
        return null;
    }

    public static String a(View view) {
        String str;
        String str2 = "";
        try {
            str = (String) view.getTag(R.id.analysys_tag_view_id);
        } catch (Throwable th) {
            th = th;
        }
        try {
            return (!TextUtils.isEmpty(str) || view.getId() == -1) ? str : a(view.getId());
        } catch (Throwable th2) {
            th = th2;
            str2 = str;
            ExceptionUtil.exceptionThrow(th);
            return str2;
        }
    }

    private static String a(StringBuilder sb, ViewGroup viewGroup) {
        if (sb == null) {
            try {
                sb = new StringBuilder();
            } catch (Throwable th) {
                ExceptionUtil.exceptionThrow(th);
                return sb != null ? sb.toString() : "";
            }
        }
        if (viewGroup == null) {
            return sb.toString();
        }
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt != null && childAt.getVisibility() == 0) {
                if (childAt instanceof ViewGroup) {
                    a(sb, (ViewGroup) childAt);
                } else {
                    String strF = f(childAt);
                    if (!TextUtils.isEmpty(strF)) {
                        sb.append(strF);
                        sb.append("-");
                    }
                }
            }
        }
        return sb.toString();
    }

    public static Map<String, Object> a(Object obj, boolean z) {
        HashMap map = new HashMap(3);
        if (obj == null) {
            return map;
        }
        try {
            if (obj instanceof ANSAutoPageTracker) {
                ANSAutoPageTracker aNSAutoPageTracker = (ANSAutoPageTracker) obj;
                Map<String, Object> mapRegisterPageProperties = aNSAutoPageTracker.registerPageProperties();
                String strRegisterPageUrl = aNSAutoPageTracker.registerPageUrl();
                if (mapRegisterPageProperties != null && !TextUtils.isEmpty(strRegisterPageUrl)) {
                    mapRegisterPageProperties.put("$url", strRegisterPageUrl);
                }
                if (mapRegisterPageProperties != null && mapRegisterPageProperties.size() > 0) {
                    map.putAll(mapRegisterPageProperties);
                }
            }
            if (obj instanceof Activity) {
                if (!map.containsKey("$title")) {
                    String strA = a((Activity) obj);
                    if (TextUtils.isEmpty(strA)) {
                        map.put("$title", strA);
                    }
                }
                if (!map.containsKey("$url")) {
                    map.put("$url", obj.getClass().getCanonicalName());
                }
            } else if (obj instanceof Dialog) {
                Activity activityA = a((Dialog) obj);
                if (!map.containsKey("$title")) {
                    String name = obj.getClass().getName();
                    if (activityA != null) {
                        String strA2 = a(activityA);
                        if (!TextUtils.isEmpty(strA2)) {
                            name = name + " in " + strA2;
                        }
                    }
                    map.put("$title", name);
                }
                if (!map.containsKey("$url")) {
                    map.put("$url", obj.getClass().getName());
                }
                if (z && activityA != null) {
                    map.put("$parent_url", d(activityA));
                }
            } else if (b(obj)) {
                Activity activityE = e(obj);
                if (!map.containsKey("$title")) {
                    String name2 = obj.getClass().getName();
                    if (activityE != null) {
                        String strA3 = a(activityE);
                        if (!TextUtils.isEmpty(strA3)) {
                            name2 = name2 + " in " + strA3;
                        }
                    }
                    map.put("$title", name2);
                }
                if (!map.containsKey("$url")) {
                    map.put("$url", obj.getClass().getName());
                }
                if (z && activityE != null) {
                    map.put("$parent_url", d(activityE));
                }
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
        return map;
    }

    private static boolean a(View view, View view2) {
        if (view == view2) {
            return true;
        }
        Object parent = view2.getParent();
        if (parent instanceof View) {
            return a(view, (View) parent);
        }
        return false;
    }

    public static boolean a(Object obj) {
        return obj != null && ((obj instanceof Activity) || (obj instanceof Dialog) || b(obj));
    }

    private static Class<?> b() {
        Class<?> classByName = AnsReflectUtils.getClassByName("androidx.appcompat.app.AppCompatActivity");
        return classByName == null ? AnsReflectUtils.getClassByName("androidx.appcompat.app.AppCompatActivity") : classByName;
    }

    private static String b(Activity activity) {
        Object objInvoke;
        CharSequence charSequence;
        try {
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
        if ("com.tencent.connect.common.AssistActivity".equals(activity.getClass().getCanonicalName())) {
            if (TextUtils.isEmpty(activity.getTitle())) {
                return null;
            }
            return activity.getTitle().toString();
        }
        ActionBar actionBar = activity.getActionBar();
        if (actionBar != null) {
            try {
                if (!TextUtils.isEmpty(actionBar.getTitle())) {
                    return actionBar.getTitle().toString();
                }
            } catch (Throwable th2) {
                ExceptionUtil.exceptionPrint(th2);
            }
        } else {
            try {
                Class<?> clsB = b();
                if (clsB != null && clsB.isInstance(activity) && (objInvoke = activity.getClass().getMethod("getSupportActionBar", new Class[0]).invoke(activity, new Object[0])) != null && (charSequence = (CharSequence) objInvoke.getClass().getMethod("getTitle", new Class[0]).invoke(objInvoke, new Object[0])) != null) {
                    return charSequence.toString();
                }
            } catch (Throwable th3) {
                ExceptionUtil.exceptionPrint(th3);
            }
        }
        return null;
    }

    public static boolean b(Object obj) {
        Class<?> classByName;
        Class<?> classByName2;
        Class<?> classByName3;
        try {
            classByName = AnsReflectUtils.getClassByName("android.app.Fragment");
            classByName2 = AnsReflectUtils.getClassByName("androidx.fragment.app.Fragment");
            classByName3 = AnsReflectUtils.getClassByName("androidx.fragment.app.Fragment");
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
        if (classByName2 == null && classByName3 == null && classByName == null) {
            return false;
        }
        if (classByName2 != null && classByName2.isInstance(obj)) {
            return true;
        }
        if (classByName3 != null && classByName3.isInstance(obj)) {
            return true;
        }
        if (classByName != null) {
            if (classByName.isInstance(obj)) {
                return true;
            }
        }
        return false;
    }

    public static String[] b(View view) {
        String contentDescription;
        CharSequence string = "";
        if (view instanceof CheckBox) {
            contentDescription = ((CheckBox) view).getText();
        } else if (view instanceof RadioButton) {
            contentDescription = ((RadioButton) view).getText();
        } else if ((view instanceof ToggleButton) || (view instanceof CompoundButton)) {
            contentDescription = e(view);
        } else if (view instanceof Button) {
            contentDescription = ((Button) view).getText();
        } else if (view instanceof CheckedTextView) {
            contentDescription = ((CheckedTextView) view).getText();
        } else if (view instanceof TextView) {
            contentDescription = ((TextView) view).getText();
        } else if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            contentDescription = !TextUtils.isEmpty(imageView.getContentDescription()) ? imageView.getContentDescription().toString() : "";
        } else if (view instanceof RatingBar) {
            contentDescription = String.valueOf(((RatingBar) view).getRating());
        } else if (view instanceof SeekBar) {
            contentDescription = String.valueOf(((SeekBar) view).getProgress());
        } else if (!(view instanceof ExpandableListView) && !(view instanceof ListView) && !(view instanceof GridView)) {
            if (view instanceof Spinner) {
                contentDescription = a(new StringBuilder(), (ViewGroup) view);
                if (!TextUtils.isEmpty(contentDescription)) {
                    contentDescription = contentDescription.toString().substring(0, contentDescription.length() - 1);
                }
            } else if (view instanceof ViewGroup) {
                contentDescription = view.getContentDescription();
                if (TextUtils.isEmpty(contentDescription)) {
                    try {
                        contentDescription = a(new StringBuilder(), (ViewGroup) view);
                        if (!TextUtils.isEmpty(contentDescription)) {
                            contentDescription = contentDescription.toString().substring(0, contentDescription.length() - 1);
                        }
                    } catch (Throwable th) {
                        ExceptionUtil.exceptionThrow(th);
                    }
                }
            }
        }
        String name = view.getClass().getName();
        if (!TextUtils.isEmpty(contentDescription)) {
            string = contentDescription;
        } else if (!TextUtils.isEmpty(view.getContentDescription())) {
            string = view.getContentDescription().toString();
        }
        return new String[]{name, string.toString()};
    }

    public static Object c(View view) {
        if (view != null) {
            try {
                Dialog dialogH = h(view);
                if (dialogH != null) {
                    return dialogH;
                }
                Object objG = g(view);
                if (objG != null) {
                    return objG;
                }
                Activity activityD = d(view);
                if (activityD != null) {
                    return activityD;
                }
            } catch (Throwable th) {
                ExceptionUtil.exceptionThrow(th);
            }
        }
        return AnalysysUtil.getCurActivity();
    }

    public static Object c(Object obj) {
        try {
            return obj.getClass().getMethod("getParentFragment", new Class[0]).invoke(obj, new Object[0]);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Activity d(View view) {
        return view == null ? a() : a(view.getContext());
    }

    public static String d(Object obj) {
        String strRegisterPageUrl;
        Map<String, Object> mapRegisterPageProperties;
        if (obj instanceof ANSAutoPageTracker) {
            ANSAutoPageTracker aNSAutoPageTracker = (ANSAutoPageTracker) obj;
            strRegisterPageUrl = aNSAutoPageTracker.registerPageUrl();
            if (TextUtils.isEmpty(strRegisterPageUrl) && (mapRegisterPageProperties = aNSAutoPageTracker.registerPageProperties()) != null) {
                strRegisterPageUrl = (String) mapRegisterPageProperties.get("$url");
            }
            if (TextUtils.isEmpty(strRegisterPageUrl)) {
                return strRegisterPageUrl;
            }
        } else {
            strRegisterPageUrl = "";
        }
        return obj instanceof Activity ? obj.getClass().getCanonicalName() : obj != null ? obj.getClass().getName() : strRegisterPageUrl;
    }

    public static Activity e(Object obj) {
        Activity activity = null;
        if (obj != null) {
            try {
                if (obj instanceof Fragment) {
                    activity = ((Fragment) obj).getActivity();
                }
            } catch (Throwable th) {
                ExceptionUtil.exceptionThrow(th);
                return null;
            }
        }
        if (activity == null) {
            activity = (Activity) obj.getClass().getMethod("getActivity", new Class[0]).invoke(obj, new Object[0]);
        }
        return activity == null ? a() : activity;
    }

    private static String e(View view) {
        try {
            return (String) (((CompoundButton) view).isChecked() ? view.getClass().getMethod("getTextOn", new Class[0]) : view.getClass().getMethod("getTextOff", new Class[0])).invoke(view, new Object[0]);
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
            return "UNKNOWN";
        }
    }

    private static String f(View view) {
        if (view == null || (view instanceof EditText)) {
            return "";
        }
        try {
            Class<?> classByName = AnsReflectUtils.getClassByName("androidx.appcompat.widget.SwitchCompat");
            if (classByName == null) {
                classByName = AnsReflectUtils.getClassByName("androidx.appcompat.widget.SwitchCompat");
            }
            CharSequence contentDescription = null;
            if (view instanceof CheckBox) {
                contentDescription = ((CheckBox) view).getText();
            } else if (classByName != null && classByName.isInstance(view)) {
                contentDescription = (String) (((CompoundButton) view).isChecked() ? view.getClass().getMethod("getTextOn", new Class[0]) : view.getClass().getMethod("getTextOff", new Class[0])).invoke(view, new Object[0]);
            } else if (view instanceof RadioButton) {
                contentDescription = ((RadioButton) view).getText();
            } else if (view instanceof ToggleButton) {
                ToggleButton toggleButton = (ToggleButton) view;
                contentDescription = toggleButton.isChecked() ? toggleButton.getTextOn() : toggleButton.getTextOff();
            } else if (view instanceof Button) {
                contentDescription = ((Button) view).getText();
            } else if (view instanceof CheckedTextView) {
                contentDescription = ((CheckedTextView) view).getText();
            } else if (view instanceof TextView) {
                contentDescription = ((TextView) view).getText();
            } else if (view instanceof ImageView) {
                ImageView imageView = (ImageView) view;
                if (!TextUtils.isEmpty(imageView.getContentDescription())) {
                    contentDescription = imageView.getContentDescription().toString();
                }
            } else {
                contentDescription = view.getContentDescription();
            }
            if (TextUtils.isEmpty(contentDescription) && (view instanceof TextView)) {
                contentDescription = ((TextView) view).getHint();
            }
            if (!TextUtils.isEmpty(contentDescription)) {
                return contentDescription.toString();
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
        return "";
    }

    private static Object g(View view) {
        Context context = view.getContext();
        Object objInvokeMethod = AnsReflectUtils.invokeMethod(context, "getSupportFragmentManager");
        if (objInvokeMethod == null && (objInvokeMethod = AnsReflectUtils.invokeMethod(context, "getFragmentManager")) == null) {
            return null;
        }
        Object objInvokeMethod2 = AnsReflectUtils.invokeMethod(objInvokeMethod, "getFragments");
        if (!(objInvokeMethod2 instanceof List)) {
            return null;
        }
        for (Object obj : (List) objInvokeMethod2) {
            Object objInvokeMethod3 = AnsReflectUtils.invokeMethod(obj, "isAdded");
            if ((objInvokeMethod3 instanceof Boolean) && ((Boolean) objInvokeMethod3).booleanValue()) {
                Object objInvokeMethod4 = AnsReflectUtils.invokeMethod(obj, "getView");
                if ((objInvokeMethod4 instanceof View) && a((View) objInvokeMethod4, view)) {
                    return obj;
                }
            }
        }
        return null;
    }

    private static Dialog h(View view) {
        Object objInvokeMethod = AnsReflectUtils.invokeMethod(view, "getWindow");
        if (!(objInvokeMethod instanceof Window)) {
            return null;
        }
        Window.Callback callback = ((Window) objInvokeMethod).getCallback();
        if (callback instanceof Dialog) {
            return (Dialog) callback;
        }
        return null;
    }
}
