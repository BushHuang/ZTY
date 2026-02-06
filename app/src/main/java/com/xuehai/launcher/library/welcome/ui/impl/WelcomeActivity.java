package com.xuehai.launcher.library.welcome.ui.impl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.xuehai.launcher.common.base.AbsActivity;
import com.xuehai.launcher.compat.R;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;

@Deprecated(message = "")
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0014J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014¨\u0006\t"}, d2 = {"Lcom/xuehai/launcher/library/welcome/ui/impl/WelcomeActivity;", "Lcom/xuehai/launcher/common/base/AbsActivity;", "()V", "isFullScreen", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "compat_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class WelcomeActivity extends AbsActivity {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    @Override
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        if (viewFindViewById == null) {
            return null;
        }
        map.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override
    protected boolean isFullScreen() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        Unit unit;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
        if (launchIntentForPackage != null) {
            startActivity(launchIntentForPackage);
            finish();
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            showToast("发生未知错误！！");
        }
    }
}
