package com.xuehai.launcher.common.widget.loading;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.xuehai.launcher.common.R;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0012\u0010\r\u001a\u00020\u00002\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0012\u0010\u0010\u001a\u00020\u00002\b\b\u0001\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\nH\u0016J\b\u0010\u0014\u001a\u00020\nH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/xuehai/launcher/common/widget/loading/VerticalLoading;", "Lcom/xuehai/launcher/common/widget/loading/LoadingView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "animationDrawable", "Landroid/graphics/drawable/AnimationDrawable;", "loadingMessage", "Landroid/widget/TextView;", "initView", "", "root", "Landroid/view/View;", "setText", "text", "", "setTextColor", "colorRes", "", "startAnimation", "stopAnimation", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VerticalLoading extends LoadingView {
    public Map<Integer, View> _$_findViewCache;
    private AnimationDrawable animationDrawable;
    private TextView loadingMessage;

    public VerticalLoading(Context context) {
        super(context, R.layout.loading_vertical_layout);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
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
    public void initView(View root) {
        Intrinsics.checkNotNullParameter(root, "root");
        View viewFindViewById = root.findViewById(R.id.vertical_loading_message);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "root.findViewById(R.id.vertical_loading_message)");
        this.loadingMessage = (TextView) viewFindViewById;
        Drawable drawable = ((ImageView) root.findViewById(R.id.vertical_loading_img)).getDrawable();
        if (drawable == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.graphics.drawable.AnimationDrawable");
        }
        this.animationDrawable = (AnimationDrawable) drawable;
        setText("加载中");
    }

    @Override
    public VerticalLoading setText(String text) {
        TextView textView = this.loadingMessage;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingMessage");
            textView = null;
        }
        String str = text;
        if (TextUtils.isEmpty(str)) {
        }
        textView.setText(str);
        return this;
    }

    @Override
    public VerticalLoading setTextColor(int colorRes) {
        TextView textView = this.loadingMessage;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingMessage");
            textView = null;
        }
        textView.setTextColor(colorRes);
        return this;
    }

    @Override
    public void startAnimation() {
        AnimationDrawable animationDrawable = this.animationDrawable;
        if (animationDrawable == null) {
            Intrinsics.throwUninitializedPropertyAccessException("animationDrawable");
            animationDrawable = null;
        }
        animationDrawable.start();
    }

    @Override
    public void stopAnimation() {
        AnimationDrawable animationDrawable = this.animationDrawable;
        AnimationDrawable animationDrawable2 = null;
        if (animationDrawable == null) {
            Intrinsics.throwUninitializedPropertyAccessException("animationDrawable");
            animationDrawable = null;
        }
        if (animationDrawable.isRunning()) {
            AnimationDrawable animationDrawable3 = this.animationDrawable;
            if (animationDrawable3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("animationDrawable");
                animationDrawable3 = null;
            }
            animationDrawable3.selectDrawable(0);
            AnimationDrawable animationDrawable4 = this.animationDrawable;
            if (animationDrawable4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("animationDrawable");
            } else {
                animationDrawable2 = animationDrawable4;
            }
            animationDrawable2.stop();
        }
    }
}
