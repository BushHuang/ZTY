package com.analysys.process;

import android.content.res.Resources;
import android.text.TextUtils;
import android.util.SparseArray;
import com.analysys.utils.AnalysysUtil;
import com.analysys.utils.ExceptionUtil;
import java.util.HashMap;
import java.util.Map;

public class SystemIds {
    private final Map<String, Integer> mIdNameToId;
    private final SparseArray<String> mIdToIdName;
    private String mPkgName;

    static class a {

        private static final SystemIds f70a = new SystemIds();
    }

    private SystemIds() {
        this.mIdNameToId = new HashMap();
        this.mIdToIdName = new SparseArray<>();
        this.mPkgName = AnalysysUtil.getContext().getPackageName();
    }

    public static SystemIds getInstance() {
        return a.f70a;
    }

    public int idFromName(Resources resources, String str) {
        Integer num = this.mIdNameToId.get(str);
        if (num != null) {
            return num.intValue();
        }
        if (resources == null) {
            try {
                resources = AnalysysUtil.getContext().getResources();
            } catch (Throwable th) {
                ExceptionUtil.exceptionThrow(th);
                return -1;
            }
        }
        int identifier = resources.getIdentifier(str, "id", this.mPkgName);
        if (identifier <= 0) {
            identifier = -1;
        }
        this.mIdNameToId.put(str, Integer.valueOf(identifier));
        return identifier;
    }

    public String nameFromId(Resources resources, int i) {
        String str = this.mIdToIdName.get(i);
        if (str != null) {
            return str;
        }
        if (resources == null) {
            try {
                resources = AnalysysUtil.getContext().getResources();
            } catch (Throwable th) {
                ExceptionUtil.exceptionPrint(th);
                return null;
            }
        }
        String resourceName = resources.getResourceName(i);
        if (TextUtils.isEmpty(resourceName)) {
            resourceName = null;
        } else {
            String[] strArrSplit = resourceName.split("/");
            if (strArrSplit.length == 2) {
                String str2 = strArrSplit[0];
                StringBuilder sb = new StringBuilder();
                sb.append(this.mPkgName);
                sb.append(":id");
                resourceName = str2.equals(sb.toString()) ? strArrSplit[1] : resourceName.replaceAll("id/", "");
            }
        }
        this.mIdToIdName.put(i, resourceName);
        return resourceName;
    }
}
