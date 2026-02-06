package com.xuehai.launcher.common.widget.loading;

import android.content.Context;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import com.xuehai.launcher.common.R;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0012\u0010\u000f\u001a\u00020\u00002\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u0012\u0010\u0012\u001a\u00020\u00002\b\b\u0001\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\fH\u0016J\b\u0010\u0016\u001a\u00020\fH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/xuehai/launcher/common/widget/loading/HorizontalLoading;", "Lcom/xuehai/launcher/common/widget/loading/LoadingView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "loadingAnimation", "Landroid/view/animation/RotateAnimation;", "loadingImg", "Landroid/widget/ImageView;", "loadingMessage", "Landroid/widget/TextView;", "initView", "", "root", "Landroid/view/View;", "setText", "text", "", "setTextColor", "colorRes", "", "startAnimation", "stopAnimation", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HorizontalLoading extends LoadingView {
    public Map<Integer, View> _$_findViewCache;
    private RotateAnimation loadingAnimation;
    private ImageView loadingImg;
    private TextView loadingMessage;

    public HorizontalLoading(Context context) {
        super(context, R.layout.loading_horizontal_layout);
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
        View viewFindViewById = root.findViewById(R.id.horizontal_loading_message);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "root.findViewById(R.id.horizontal_loading_message)");
        this.loadingMessage = (TextView) viewFindViewById;
        View viewFindViewById2 = root.findViewById(R.id.horizontal_loading_img);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "root.findViewById(R.id.horizontal_loading_img)");
        this.loadingImg = (ImageView) viewFindViewById2;
        long j = 10000 * 400;
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 10000 * 360.0f, 1, 0.5f, 1, 0.5f);
        this.loadingAnimation = rotateAnimation;
        RotateAnimation rotateAnimation2 = null;
        if (rotateAnimation == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingAnimation");
            rotateAnimation = null;
        }
        rotateAnimation.setDuration(j);
        RotateAnimation rotateAnimation3 = this.loadingAnimation;
        if (rotateAnimation3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingAnimation");
            rotateAnimation3 = null;
        }
        rotateAnimation3.setInterpolator(new LinearInterpolator());
        RotateAnimation rotateAnimation4 = this.loadingAnimation;
        if (rotateAnimation4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingAnimation");
            rotateAnimation4 = null;
        }
        rotateAnimation4.setRepeatCount(-1);
        RotateAnimation rotateAnimation5 = this.loadingAnimation;
        if (rotateAnimation5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingAnimation");
        } else {
            rotateAnimation2 = rotateAnimation5;
        }
        rotateAnimation2.setRepeatMode(1);
    }

    @Override
    public HorizontalLoading setText(String text) {
        TextView textView = this.loadingMessage;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingMessage");
            textView = null;
        }
        textView.setText(text);
        return this;
    }

    @Override
    public HorizontalLoading setTextColor(int colorRes) {
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
        ImageView imageView = this.loadingImg;
        RotateAnimation rotateAnimation = null;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingImg");
            imageView = null;
        }
        RotateAnimation rotateAnimation2 = this.loadingAnimation;
        if (rotateAnimation2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingAnimation");
        } else {
            rotateAnimation = rotateAnimation2;
        }
        imageView.startAnimation(rotateAnimation);
    }

    @Override
    public void stopAnimation() {
    }
}
