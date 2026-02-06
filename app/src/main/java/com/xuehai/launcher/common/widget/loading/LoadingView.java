package com.xuehai.launcher.common.widget.loading;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0006\b&\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH&J\b\u0010\u0010\u001a\u00020\fH\u0016J\b\u0010\u0011\u001a\u00020\fH\u0016J\u000e\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\nJ\u0012\u0010\u0014\u001a\u00020\u00002\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H&J\u0012\u0010\u0017\u001a\u00020\u00002\b\b\u0001\u0010\u0018\u001a\u00020\u0005H&J\b\u0010\u0019\u001a\u00020\fH&J\b\u0010\u001a\u001a\u00020\fH&R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/xuehai/launcher/common/widget/loading/LoadingView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "layoutRes", "", "(Landroid/content/Context;I)V", "getLayoutRes", "()I", "mListener", "Landroid/view/animation/Animation$AnimationListener;", "createRootView", "", "initView", "root", "Landroid/view/View;", "onAnimationEnd", "onAnimationStart", "setAnimationListener", "listener", "setText", "text", "", "setTextColor", "colorRes", "startAnimation", "stopAnimation", "Companion", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class LoadingView extends LinearLayout {

    public static final Companion INSTANCE = new Companion(null);
    public static final String DEFAULT_TIP_TEXT = "加载中";
    public Map<Integer, View> _$_findViewCache;
    private final int layoutRes;
    private Animation.AnimationListener mListener;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/xuehai/launcher/common/widget/loading/LoadingView$Companion;", "", "()V", "DEFAULT_TIP_TEXT", "", "createHorizontalLoading", "Lcom/xuehai/launcher/common/widget/loading/LoadingView;", "context", "Landroid/content/Context;", "createVerticalLoading", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final LoadingView createHorizontalLoading(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return new HorizontalLoading(context);
        }

        @JvmStatic
        public final LoadingView createVerticalLoading(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return new VerticalLoading(context);
        }
    }

    public LoadingView(Context context, int i) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
        this.layoutRes = i;
        createRootView(context);
    }

    @JvmStatic
    public static final LoadingView createHorizontalLoading(Context context) {
        return INSTANCE.createHorizontalLoading(context);
    }

    private final void createRootView(Context context) {
        View viewInflate = LayoutInflater.from(context).inflate(this.layoutRes, (ViewGroup) this, true);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "from(context).inflate(layoutRes, this, true)");
        initView(viewInflate);
    }

    @JvmStatic
    public static final LoadingView createVerticalLoading(Context context) {
        return INSTANCE.createVerticalLoading(context);
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

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

    public final int getLayoutRes() {
        return this.layoutRes;
    }

    public abstract void initView(View root);

    @Override
    public void onAnimationEnd() {
        super.onAnimationEnd();
        Animation.AnimationListener animationListener = this.mListener;
        if (animationListener != null) {
            animationListener.onAnimationEnd(getAnimation());
        }
    }

    @Override
    public void onAnimationStart() {
        super.onAnimationStart();
        Animation.AnimationListener animationListener = this.mListener;
        if (animationListener != null) {
            animationListener.onAnimationStart(getAnimation());
        }
    }

    public final void setAnimationListener(Animation.AnimationListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.mListener = listener;
    }

    public abstract LoadingView setText(String text);

    public abstract LoadingView setTextColor(int colorRes);

    public abstract void startAnimation();

    public abstract void stopAnimation();
}
