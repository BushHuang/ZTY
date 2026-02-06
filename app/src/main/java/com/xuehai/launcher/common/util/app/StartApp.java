package com.xuehai.launcher.common.util.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.xuehai.launcher.common.R;
import com.xuehai.launcher.common.base.AbsActivity;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014¨\u0006\b"}, d2 = {"Lcom/xuehai/launcher/common/util/app/StartApp;", "Lcom/xuehai/launcher/common/base/AbsActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StartApp extends AbsActivity {
    public static final String ACTION = "action";
    public static final String APP_NAME = "app_name";
    public static final String PACKAGE_NAME = "package_name";
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
    protected void onCreate(Bundle savedInstanceState) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        Bundle extras;
        String string;
        Unit unit;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_app_act);
        Intent intent = getIntent();
        if (intent != null && (extras = intent.getExtras()) != null && (string = extras.getString("package_name")) != null) {
            Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(string);
            if (launchIntentForPackage != null) {
                String string2 = extras.getString("action");
                if (string2 != null) {
                    launchIntentForPackage.setAction(string2);
                }
                launchIntentForPackage.putExtras(extras);
                launchIntentForPackage.setFlags(getIntent().getFlags());
                startActivity(launchIntentForPackage);
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            if (unit == null) {
                showToast("应用(" + extras.getString("app_name") + ")不可直接打开!");
            }
        }
        finish();
    }
}
