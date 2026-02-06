package com.samsung.android.knox.custom;

import android.app.enterprise.knoxcustom.KnoxCustomWidgetItem;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.knox.SupportLibUtils;

public class WidgetItem implements Parcelable {
    public static final Parcelable.Creator<WidgetItem> CREATOR = new Parcelable.Creator<WidgetItem>() {
        @Override
        public WidgetItem createFromParcel(Parcel parcel) {
            return new WidgetItem(parcel);
        }

        @Override
        public WidgetItem[] newArray(int i) {
            return new WidgetItem[i];
        }
    };
    private int mCellX;
    private int mCellY;
    private Intent mIntent;
    private int mMoreItems;
    private String mParent;
    private int mSizeX;
    private int mSizeY;
    private int mWidgetId;
    private int mWidgetType;

    public WidgetItem(int i, Intent intent, int i2, String str, int i3, int i4, int i5, int i6, int i7) {
        this.mWidgetType = i;
        this.mIntent = intent;
        this.mWidgetId = i2;
        this.mParent = str;
        this.mCellX = i3;
        this.mCellY = i4;
        this.mSizeX = i5;
        this.mSizeY = i6;
        this.mMoreItems = i7;
    }

    private WidgetItem(Parcel parcel) {
        this.mIntent = (Intent) parcel.readBundle().getParcelable("intent");
        this.mWidgetType = parcel.readInt();
        this.mWidgetId = parcel.readInt();
        this.mParent = parcel.readString();
        this.mCellX = parcel.readInt();
        this.mCellY = parcel.readInt();
        this.mSizeX = parcel.readInt();
        this.mSizeY = parcel.readInt();
        this.mMoreItems = parcel.readInt();
    }

    static KnoxCustomWidgetItem convertToOld(WidgetItem widgetItem) throws NoClassDefFoundError {
        if (widgetItem == null) {
            return null;
        }
        try {
            return new KnoxCustomWidgetItem(widgetItem.getWidgetType(), widgetItem.getIntent(), widgetItem.getWidgetId(), widgetItem.getParent(), widgetItem.getCellX(), widgetItem.getCellY(), widgetItem.getSizeX(), widgetItem.getSizeY(), widgetItem.getMoreItems());
        } catch (NoClassDefFoundError unused) {
            throw new NoClassDefFoundError(SupportLibUtils.buildClassErrorMsg(WidgetItem.class, 20));
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getCellX() {
        return this.mCellX;
    }

    public int getCellY() {
        return this.mCellY;
    }

    public Intent getIntent() {
        return this.mIntent;
    }

    public int getMoreItems() {
        return this.mMoreItems;
    }

    public String getParent() {
        return this.mParent;
    }

    public int getSizeX() {
        return this.mSizeX;
    }

    public int getSizeY() {
        return this.mSizeY;
    }

    public int getWidgetId() {
        return this.mWidgetId;
    }

    public int getWidgetType() {
        return this.mWidgetType;
    }

    public String toString() {
        return "descr:" + describeContents() + " widgetType:" + this.mWidgetType + " parent:" + this.mParent + " intent:" + this.mIntent;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("intent", this.mIntent);
        parcel.writeBundle(bundle);
        parcel.writeInt(this.mWidgetType);
        parcel.writeInt(this.mWidgetId);
        parcel.writeString(this.mParent);
        parcel.writeInt(this.mCellX);
        parcel.writeInt(this.mCellY);
        parcel.writeInt(this.mSizeX);
        parcel.writeInt(this.mSizeY);
        parcel.writeInt(this.mMoreItems);
    }
}
