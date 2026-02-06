package com.xuehai.launcher.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010\u0013\u001a\u00020\u0014J\u001c\u0010\u0015\u001a\u00020\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u0014H\u0002J\b\u0010\u001a\u001a\u00020\u0014H\u0002J\u000e\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u0010R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/xuehai/launcher/widget/LoadingTextView;", "Landroidx/appcompat/widget/AppCompatTextView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "endString", "", "loadingRunnable", "Ljava/lang/Runnable;", "loadingStatus", "", "loopCount", "originText", "runLooper", "", "setText", "text", "type", "Landroid/widget/TextView$BufferType;", "startLoading", "stopLoading", "updateLoadingStatus", "enable", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LoadingTextView extends AppCompatTextView {
    public Map<Integer, View> _$_findViewCache;
    private CharSequence endString;
    private final Runnable loadingRunnable;
    private boolean loadingStatus;
    private int loopCount;
    private CharSequence originText;

    public LoadingTextView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
        this.loadingRunnable = new Runnable() {
            @Override
            public final void run() {
                LoadingTextView.m185loadingRunnable$lambda0(this.f$0);
            }
        };
    }

    public LoadingTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
        this.loadingRunnable = new Runnable() {
            @Override
            public final void run() {
                LoadingTextView.m185loadingRunnable$lambda0(this.f$0);
            }
        };
    }

    public LoadingTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
        this.loadingRunnable = new Runnable() {
            @Override
            public final void run() {
                LoadingTextView.m185loadingRunnable$lambda0(this.f$0);
            }
        };
    }

    private static final void m185loadingRunnable$lambda0(LoadingTextView loadingTextView) {
        Intrinsics.checkNotNullParameter(loadingTextView, "this$0");
        loadingTextView.runLooper();
    }

    private final void startLoading() {
        if (this.loadingStatus) {
            return;
        }
        this.loadingStatus = true;
        runLooper();
    }

    private final void stopLoading() {
        this.loadingStatus = false;
        removeCallbacks(this.loadingRunnable);
        setText(this.originText);
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

    public final void runLooper() {
        if (this.loadingStatus) {
            int i = this.loopCount + 1;
            this.loopCount = i;
            if (i > 3) {
                this.loopCount = 0;
            }
            int i2 = this.loopCount;
            String str = i2 != 0 ? i2 != 1 ? i2 != 2 ? "..." : ".. " : ".  " : "   ";
            this.endString = str;
            append(str);
            setText(this.originText);
            postDelayed(this.loadingRunnable, 300L);
        }
    }

    @Override
    public void setText(CharSequence text, TextView.BufferType type) {
        StringBuilder sb = new StringBuilder();
        sb.append((Object) this.originText);
        sb.append((Object) this.endString);
        if (!Intrinsics.areEqual(text, sb.toString())) {
            this.originText = text;
        }
        if (this.loadingStatus && text != null) {
            if (text.length() > 0) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append((Object) this.originText);
                sb2.append((Object) this.endString);
                super.setText(sb2.toString(), type);
                return;
            }
        }
        super.setText(this.originText, type);
    }

    public final void updateLoadingStatus(boolean enable) {
        if (enable) {
            startLoading();
        } else {
            stopLoading();
        }
    }
}
