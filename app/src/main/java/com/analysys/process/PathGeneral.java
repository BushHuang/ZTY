package com.analysys.process;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.analysys.utils.ANSLog;
import com.analysys.utils.ExceptionUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.json.JSONException;
import org.json.JSONObject;

public class PathGeneral {
    private boolean isDebug;
    private int mContentID;
    private String[] mNoSupportList;
    private String[] mNosupportEntryName;
    private ArrayList<JSONObject> mTempPath;

    static class a {

        private static PathGeneral f69a = new PathGeneral();
    }

    private PathGeneral() {
        this.isDebug = false;
        this.mContentID = -1;
        this.mNosupportEntryName = new String[]{"action_bar", "content", "decor_content_parent"};
        this.mNoSupportList = new String[]{"com.android.internal.widget.ActionBarOverlayLayout", "com.android.internal.policy.DecorView", "com.android.internal.widget.ActionBarView", "com.android.internal.widget.ActionBarContainer"};
    }

    private boolean checkClass(View view) {
        return Arrays.asList(this.mNoSupportList).contains(view.getClass().getCanonicalName());
    }

    private void getDynamicPath(View view) {
        try {
            JSONObject jSONObject = new JSONObject();
            if (isFinalPoint(view)) {
                return;
            }
            getUniquePath(jSONObject, view);
            if (jSONObject.length() > 0) {
                this.mTempPath.add(jSONObject);
            }
            Object parent = view.getParent();
            if (parent instanceof View) {
                getDynamicPath((View) parent);
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    public static PathGeneral getInstance() {
        return a.f69a;
    }

    private void getIntrinsicPath() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("prefix", "shortest");
            jSONObject.put("index", 0);
            jSONObject.put("id", this.mContentID);
            this.mTempPath.add(jSONObject);
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    private void getUniquePath(JSONObject jSONObject, View view) {
        int id;
        if (jSONObject == null) {
            try {
                jSONObject = new JSONObject();
            } catch (Throwable th) {
                ExceptionUtil.exceptionThrow(th);
                return;
            }
        }
        CharSequence contentDescription = view.getContentDescription();
        if (!TextUtils.isEmpty(contentDescription)) {
            jSONObject.put("contentDescription", contentDescription);
            jSONObject.put("index", 0);
        }
        Object tag = view.getTag();
        if (tag != null && (tag instanceof CharSequence)) {
            jSONObject.put("tag", tag.toString());
            jSONObject.put("index", 0);
        }
        if (TextUtils.isEmpty(contentDescription) && (id = view.getId()) != -1) {
            String strNameFromId = SystemIds.getInstance().nameFromId(view.getResources(), id);
            if (!TextUtils.isEmpty(strNameFromId)) {
                jSONObject.put("mp_id_name", strNameFromId);
                jSONObject.put("index", 0);
            }
        }
        if (jSONObject.length() == 0) {
            updateIndexWhenUseViewClass(jSONObject, view);
        }
    }

    private boolean isContextType(View view, String str) {
        ArrayList arrayList = new ArrayList();
        for (Class<?> superclass = view.getClass(); superclass != null && superclass != Object.class; superclass = superclass.getSuperclass()) {
            arrayList.add(superclass.getCanonicalName());
        }
        return arrayList.contains(str);
    }

    private void updateIndexWhenUseViewClass(JSONObject jSONObject, View view) throws JSONException {
        String canonicalName = view.getClass().getCanonicalName();
        if (TextUtils.isEmpty(canonicalName)) {
            return;
        }
        jSONObject.put("view_class", canonicalName);
        jSONObject.put("index", getInstance().getIndex(view));
    }

    public String general(View view) {
        try {
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
        if (isFinalPoint(view)) {
            return null;
        }
        this.mContentID = -1;
        if (this.mTempPath != null) {
            this.mTempPath.clear();
        } else {
            this.mTempPath = new ArrayList<>();
        }
        getDynamicPath(view);
        if (this.mTempPath.size() <= 0 || this.mContentID == -1) {
            this.mTempPath.clear();
        } else {
            getIntrinsicPath();
            if (this.mTempPath.size() > 0) {
                Collections.reverse(this.mTempPath);
            }
        }
        this.mContentID = -1;
        return String.valueOf(this.mTempPath);
    }

    public int getIndex(View view) {
        ViewGroup viewGroup;
        int childCount;
        String canonicalName = view.getClass().getCanonicalName();
        if (this.isDebug) {
            ANSLog.w("getIndex selfClass:" + canonicalName);
        }
        ViewParent parent = view.getParent();
        if (!(parent instanceof ViewGroup) || (childCount = (viewGroup = (ViewGroup) parent).getChildCount()) <= 1) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt == null) {
                return -1;
            }
            if (this.isDebug) {
                ANSLog.w("getIndex 兄弟节点:" + canonicalName);
            }
            if (childAt.equals(view)) {
                return i;
            }
            if (isContextType(childAt, canonicalName)) {
                i++;
            }
        }
        return i;
    }

    protected boolean isFinalPoint(View view) {
        int id = view.getId();
        if (id != -1) {
            String strNameFromId = SystemIds.getInstance().nameFromId(view.getResources(), id);
            if (!TextUtils.isEmpty(strNameFromId) && "android:content".equals(strNameFromId)) {
                this.mContentID = id;
                return true;
            }
        }
        try {
            String resourceEntryName = view.getResources().getResourceEntryName(id);
            if (TextUtils.isEmpty(resourceEntryName) || !Arrays.asList(this.mNosupportEntryName).contains(resourceEntryName)) {
                return checkClass(view);
            }
            return true;
        } catch (Throwable unused) {
            return checkClass(view);
        }
    }
}
