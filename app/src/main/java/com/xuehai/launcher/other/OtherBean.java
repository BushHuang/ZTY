package com.xuehai.launcher.other;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/xuehai/launcher/other/OtherBean;", "", "title", "", "click", "Landroid/view/View$OnClickListener;", "(Ljava/lang/String;Landroid/view/View$OnClickListener;)V", "getClick", "()Landroid/view/View$OnClickListener;", "getTitle", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class OtherBean {
    private final View.OnClickListener click;
    private final String title;

    public OtherBean(String str, View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(str, "title");
        Intrinsics.checkNotNullParameter(onClickListener, "click");
        this.title = str;
        this.click = onClickListener;
    }

    public static OtherBean copy$default(OtherBean otherBean, String str, View.OnClickListener onClickListener, int i, Object obj) {
        if ((i & 1) != 0) {
            str = otherBean.title;
        }
        if ((i & 2) != 0) {
            onClickListener = otherBean.click;
        }
        return otherBean.copy(str, onClickListener);
    }

    public final String getTitle() {
        return this.title;
    }

    public final View.OnClickListener getClick() {
        return this.click;
    }

    public final OtherBean copy(String title, View.OnClickListener click) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(click, "click");
        return new OtherBean(title, click);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OtherBean)) {
            return false;
        }
        OtherBean otherBean = (OtherBean) other;
        return Intrinsics.areEqual(this.title, otherBean.title) && Intrinsics.areEqual(this.click, otherBean.click);
    }

    public final View.OnClickListener getClick() {
        return this.click;
    }

    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        return (this.title.hashCode() * 31) + this.click.hashCode();
    }

    public String toString() {
        return "OtherBean(title=" + this.title + ", click=" + this.click + ')';
    }
}
