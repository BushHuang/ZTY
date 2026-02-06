package com.xuehai.launcher.device;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J3\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lcom/xuehai/launcher/device/DeviceResetBean;", "", "name", "", "click", "Landroid/view/View$OnClickListener;", "type", "", "color", "(Ljava/lang/String;Landroid/view/View$OnClickListener;ILjava/lang/String;)V", "getClick", "()Landroid/view/View$OnClickListener;", "getColor", "()Ljava/lang/String;", "getName", "getType", "()I", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DeviceResetBean {
    private final View.OnClickListener click;
    private final String color;
    private final String name;
    private final int type;

    public DeviceResetBean(String str, View.OnClickListener onClickListener, int i, String str2) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(onClickListener, "click");
        this.name = str;
        this.click = onClickListener;
        this.type = i;
        this.color = str2;
    }

    public DeviceResetBean(String str, View.OnClickListener onClickListener, int i, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, onClickListener, (i2 & 4) != 0 ? 0 : i, (i2 & 8) != 0 ? null : str2);
    }

    public static DeviceResetBean copy$default(DeviceResetBean deviceResetBean, String str, View.OnClickListener onClickListener, int i, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = deviceResetBean.name;
        }
        if ((i2 & 2) != 0) {
            onClickListener = deviceResetBean.click;
        }
        if ((i2 & 4) != 0) {
            i = deviceResetBean.type;
        }
        if ((i2 & 8) != 0) {
            str2 = deviceResetBean.color;
        }
        return deviceResetBean.copy(str, onClickListener, i, str2);
    }

    public final String getName() {
        return this.name;
    }

    public final View.OnClickListener getClick() {
        return this.click;
    }

    public final int getType() {
        return this.type;
    }

    public final String getColor() {
        return this.color;
    }

    public final DeviceResetBean copy(String name, View.OnClickListener click, int type, String color) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(click, "click");
        return new DeviceResetBean(name, click, type, color);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeviceResetBean)) {
            return false;
        }
        DeviceResetBean deviceResetBean = (DeviceResetBean) other;
        return Intrinsics.areEqual(this.name, deviceResetBean.name) && Intrinsics.areEqual(this.click, deviceResetBean.click) && this.type == deviceResetBean.type && Intrinsics.areEqual(this.color, deviceResetBean.color);
    }

    public final View.OnClickListener getClick() {
        return this.click;
    }

    public final String getColor() {
        return this.color;
    }

    public final String getName() {
        return this.name;
    }

    public final int getType() {
        return this.type;
    }

    public int hashCode() {
        int iHashCode = ((((this.name.hashCode() * 31) + this.click.hashCode()) * 31) + this.type) * 31;
        String str = this.color;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "DeviceResetBean(name=" + this.name + ", click=" + this.click + ", type=" + this.type + ", color=" + this.color + ')';
    }
}
