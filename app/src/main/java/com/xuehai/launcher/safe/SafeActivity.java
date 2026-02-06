package com.xuehai.launcher.safe;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import com.xuehai.launcher.R;
import com.xuehai.launcher.common.base.AbsActivity;
import com.xuehai.launcher.common.ext.AppCompatActivityExtKt;
import com.xuehai.launcher.other.OtherFragment;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"Lcom/xuehai/launcher/safe/SafeActivity;", "Lcom/xuehai/launcher/common/base/AbsActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onKeyDown", "", "keyCode", "", "event", "Landroid/view/KeyEvent;", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SafeActivity extends AbsActivity {
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
        super.onCreate(savedInstanceState);
        setContentView(2131492951);
        SafeActivity safeActivity = this;
        Toolbar toolbar = (Toolbar) _$_findCachedViewById(R.id.safeToolbar);
        Intrinsics.checkNotNullExpressionValue(toolbar, "safeToolbar");
        AppCompatActivityExtKt.setupActionBar(safeActivity, toolbar, new Function1<ActionBar, Unit>() {
            @Override
            public Unit invoke(ActionBar actionBar) {
                invoke2(actionBar);
                return Unit.INSTANCE;
            }

            public final void invoke2(ActionBar actionBar) {
                Intrinsics.checkNotNullParameter(actionBar, "$this$setupActionBar");
                actionBar.setDisplayShowTitleEnabled(false);
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setHomeButtonEnabled(true);
            }
        });
        AppCompatActivityExtKt.replaceFragmentInActivity(safeActivity, 2131296554, new OtherFragment());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (4 == keyCode) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
