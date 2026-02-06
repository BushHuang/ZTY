package com.xh.view.base.dialog;

import android.view.View;
import com.xh.view.base.dialog.gravity.GravityEnum;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/xh/view/base/dialog/BaseCenterDialogFragment;", "Lcom/xh/view/base/dialog/BaseDialogFragment;", "()V", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class BaseCenterDialogFragment extends BaseDialogFragment {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    public BaseCenterDialogFragment() {
        applyGravityStyle(GravityEnum.Center);
    }

    @Override
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }
}
