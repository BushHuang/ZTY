package com.xuehai.launcher.common.base;

import android.content.res.Resources;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.xuehai.launcher.common.widget.CustomToast;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0004J\u001b\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0001\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\tJ\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0004J\u0010\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\b¨\u0006\r"}, d2 = {"Lcom/xuehai/launcher/common/base/AbsFragment;", "Landroidx/fragment/app/Fragment;", "()V", "getDimen", "", "resId", "getStringArray", "", "", "(I)[Ljava/lang/String;", "showToast", "", "content", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class AbsFragment extends Fragment {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        View viewFindViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null || (viewFindViewById = view2.findViewById(i)) == null) {
            return null;
        }
        map.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    public final int getDimen(int resId) {
        return getResources().getDimensionPixelSize(resId);
    }

    public final String[] getStringArray(int resId) throws Resources.NotFoundException {
        String[] stringArray = getResources().getStringArray(resId);
        Intrinsics.checkNotNullExpressionValue(stringArray, "this.resources.getStringArray(resId)");
        return stringArray;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final void showToast(int resId) {
        showToast(getString(resId));
    }

    public final void showToast(String content) {
        if (content != null) {
            CustomToast.showToast(content);
        }
    }
}
