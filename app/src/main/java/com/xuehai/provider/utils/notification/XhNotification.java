package com.xuehai.provider.utils.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.app.NotificationCompat;
import com.xuehai.provider.core.CPVDManager;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class XhNotification implements Parcelable {
    public static final int ACTIVITY = 0;
    public static final int BROADCAST = 1;
    public static final Parcelable.Creator<XhNotification> CREATOR = new Parcelable.Creator<XhNotification>() {
        @Override
        public XhNotification createFromParcel(Parcel parcel) {
            return new XhNotification(parcel);
        }

        @Override
        public XhNotification[] newArray(int i) {
            return new XhNotification[i];
        }
    };
    private String appName;
    private boolean autoCancel;
    private String content;
    private long duration;
    private int id;
    private Intent intent;
    private int intentType;
    private Bitmap largeIcon;
    private boolean ongoing;
    private String packageName;
    private int smallIcon;

    @Deprecated
    private Bitmap smallIconBitmap;
    private String title;

    public static class Builder {
        private XhNotification mXhNotification = new XhNotification();

        public static int dp2px(Context context, float f) {
            return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
        }

        public static Bitmap scaleDownIfNecessary(Bitmap bitmap, int i, int i2) {
            if (bitmap == null) {
                return null;
            }
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if (width <= i && height <= i2) {
                return bitmap;
            }
            float f = width;
            float f2 = height;
            float fMin = Math.min(i / f, i2 / f2);
            return Bitmap.createScaledBitmap(bitmap, Math.max(1, (int) (f * fMin)), Math.max(1, (int) (fMin * f2)), true);
        }

        public XhNotification build() throws IllegalArgumentException {
            if (this.mXhNotification.smallIcon != 0 || this.mXhNotification.largeIcon != null) {
                XhNotification xhNotification = this.mXhNotification;
                xhNotification.largeIcon = scaleDownIfNecessary(xhNotification.largeIcon, dp2px(XhNotification.getContext(), 280.0f), dp2px(XhNotification.getContext(), 280.0f));
                return this.mXhNotification;
            }
            throw new IllegalArgumentException("Invalid notification (no valid small icon): " + this.mXhNotification.toString());
        }

        public Builder setAppName(String str) {
            this.mXhNotification.appName = str;
            return this;
        }

        public Builder setAutoCancel(boolean z) {
            this.mXhNotification.autoCancel = z;
            return this;
        }

        public Builder setContent(String str) {
            this.mXhNotification.content = str;
            return this;
        }

        public Builder setDuration(long j) {
            this.mXhNotification.duration = j;
            return this;
        }

        public Builder setId(int i) {
            this.mXhNotification.id = i;
            return this;
        }

        public Builder setIntent(Intent intent) {
            this.mXhNotification.intent = intent;
            return this;
        }

        public Builder setIntentType(int i) {
            this.mXhNotification.intentType = i;
            return this;
        }

        public Builder setLargeIcon(Bitmap bitmap) {
            this.mXhNotification.largeIcon = bitmap;
            return this;
        }

        public Builder setOngoing(boolean z) {
            this.mXhNotification.ongoing = z;
            return this;
        }

        public Builder setPackageName(String str) {
            this.mXhNotification.packageName = str;
            return this;
        }

        public Builder setSmallIcon(int i) {
            this.mXhNotification.smallIcon = i;
            try {
                this.mXhNotification.smallIconBitmap = BitmapFactory.decodeResource(XhNotification.getContext().getResources(), i);
            } catch (Exception unused) {
            }
            return this;
        }

        public Builder setTitle(String str) {
            this.mXhNotification.title = str;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface IntentType {
    }

    public XhNotification() {
    }

    protected XhNotification(Parcel parcel) {
        this.id = parcel.readInt();
        this.appName = parcel.readString();
        this.title = parcel.readString();
        this.content = parcel.readString();
        this.smallIcon = parcel.readInt();
        this.smallIconBitmap = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
        this.largeIcon = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
        this.intent = (Intent) parcel.readParcelable(Intent.class.getClassLoader());
        this.intentType = parcel.readInt();
        this.ongoing = parcel.readByte() != 0;
        this.autoCancel = parcel.readByte() != 0;
        this.duration = parcel.readLong();
        this.packageName = parcel.readString();
    }

    public static Context getContext() {
        return CPVDManager.getContext();
    }

    private PendingIntent getPendingIntent(Intent intent, int i) {
        if (intent == null) {
            return null;
        }
        if (i == 0) {
            return PendingIntent.getActivity(getContext(), this.id, intent, 134217728);
        }
        if (i != 1) {
            return null;
        }
        return PendingIntent.getBroadcast(getContext(), this.id, intent, 134217728);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getAppName() {
        return this.appName;
    }

    public String getContent() {
        return this.content;
    }

    public long getDuration() {
        return this.duration;
    }

    public int getId() {
        return this.id;
    }

    public Intent getIntent() {
        return this.intent;
    }

    public int getIntentType() {
        return this.intentType;
    }

    public Bitmap getLargeIcon() {
        return this.largeIcon;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public int getSmallIcon() {
        return this.smallIcon;
    }

    public Bitmap getSmallIconBitmap() {
        return this.smallIconBitmap;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isAutoCancel() {
        return this.autoCancel;
    }

    public boolean isOngoing() {
        return this.ongoing;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public String toString() {
        return "XhNotification{id=" + this.id + ", appName='" + this.appName + "', title='" + this.title + "', content='" + this.content + "', smallIcon=" + this.smallIcon + ", intent=" + this.intent + ", intentType=" + this.intentType + ", ongoing=" + this.ongoing + ", autoCancel=" + this.autoCancel + ", duration=" + this.duration + ", packageName='" + this.packageName + "'}";
    }

    public Notification transformSystemNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext());
        builder.setContentTitle(this.title).setContentText(this.content).setSmallIcon(this.smallIcon).setLargeIcon(this.largeIcon).setGroup(this.id + "").setOngoing(this.ongoing).setAutoCancel(this.autoCancel).setContentIntent(getPendingIntent(this.intent, this.intentType));
        return builder.build();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.appName);
        parcel.writeString(this.title);
        parcel.writeString(this.content);
        parcel.writeInt(this.smallIcon);
        parcel.writeParcelable(this.smallIconBitmap, i);
        parcel.writeParcelable(this.largeIcon, i);
        parcel.writeParcelable(this.intent, i);
        parcel.writeInt(this.intentType);
        parcel.writeByte(this.ongoing ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.autoCancel ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.duration);
        parcel.writeString(this.packageName);
    }
}
