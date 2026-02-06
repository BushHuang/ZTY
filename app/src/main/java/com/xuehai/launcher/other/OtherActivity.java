package com.xuehai.launcher.other;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import com.xuehai.launcher.R;
import com.xuehai.launcher.common.base.AbsActivity;
import com.xuehai.launcher.common.ext.AppCompatActivityExtKt;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0014¨\u0006\b"}, d2 = {"Lcom/xuehai/launcher/other/OtherActivity;", "Lcom/xuehai/launcher/common/base/AbsActivity;", "()V", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class OtherActivity extends AbsActivity {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    private static final void m163onCreate$lambda0(OtherActivity otherActivity, View view) {
        Intrinsics.checkNotNullParameter(otherActivity, "this$0");
        otherActivity.onBackPressed();
    }

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
    public void onBackPressed() {
        setResult(-1);
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        super.onCreate(savedInstanceState);
        setContentView(2131492947);
        OtherActivity otherActivity = this;
        Toolbar toolbar = (Toolbar) _$_findCachedViewById(R.id.otherToolbar);
        Intrinsics.checkNotNullExpressionValue(toolbar, "otherToolbar");
        AppCompatActivityExtKt.setupActionBar(otherActivity, toolbar, new Function1<ActionBar, Unit>() {
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
        ((Toolbar) _$_findCachedViewById(R.id.otherToolbar)).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                OtherActivity.m163onCreate$lambda0(this.f$0, view);
            }
        });
        AppCompatActivityExtKt.replaceFragmentInActivity(otherActivity, 2131296527, new OtherFragment());
    }
}
