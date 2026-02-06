package com.samsung.android.knox.datetime;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.Settings;
import com.samsung.android.knox.SupportLibUtils;

public class NtpInfo implements Parcelable {
    public static final Parcelable.Creator<NtpInfo> CREATOR = new Parcelable.Creator<NtpInfo>() {
        @Override
        public NtpInfo createFromParcel(Parcel parcel) {
            return new NtpInfo(parcel);
        }

        @Override
        public NtpInfo[] newArray(int i) {
            return new NtpInfo[i];
        }
    };
    public static final int NOT_SET_INT = 0;
    public static final long NOT_SET_LONG = 0;
    private int mMaxAttempts;
    private long mPollingInterval;
    private long mPollingIntervalShorter;
    private String mServer;
    private int mTimeErrorThreshold;
    private long mTimeout;

    private NtpInfo() {
        this.mServer = null;
        this.mTimeout = 0L;
        this.mMaxAttempts = 0;
        this.mPollingInterval = 0L;
        this.mPollingIntervalShorter = 0L;
        this.mTimeErrorThreshold = 0;
    }

    public NtpInfo(Context context) throws Resources.NotFoundException {
        this.mServer = null;
        this.mTimeout = 0L;
        this.mMaxAttempts = 0;
        this.mPollingInterval = 0L;
        this.mPollingIntervalShorter = 0L;
        this.mTimeErrorThreshold = 0;
        Resources resources = context.getResources();
        ContentResolver contentResolver = context.getContentResolver();
        String string = resources.getString(Resources.getSystem().getIdentifier("config_ntpServer", "string", "android"));
        long integer = resources.getInteger(Resources.getSystem().getIdentifier("config_ntpTimeout", "integer", "android"));
        String string2 = Settings.Global.getString(contentResolver, "ntp_server");
        this.mTimeout = Settings.Global.getLong(contentResolver, "ntp_timeout", integer);
        this.mServer = string2 != null ? string2 : string;
        this.mPollingInterval = context.getResources().getInteger(Resources.getSystem().getIdentifier("config_ntpPollingInterval", "integer", "android"));
        this.mPollingIntervalShorter = context.getResources().getInteger(Resources.getSystem().getIdentifier("config_ntpPollingIntervalShorter", "integer", "android"));
        this.mMaxAttempts = context.getResources().getInteger(Resources.getSystem().getIdentifier("config_ntpRetry", "integer", "android"));
        this.mTimeErrorThreshold = context.getResources().getInteger(Resources.getSystem().getIdentifier("config_ntpThreshold", "integer", "android"));
    }

    private NtpInfo(Parcel parcel) {
        this.mServer = null;
        this.mTimeout = 0L;
        this.mMaxAttempts = 0;
        this.mPollingInterval = 0L;
        this.mPollingIntervalShorter = 0L;
        this.mTimeErrorThreshold = 0;
        this.mServer = parcel.readString();
        this.mTimeout = parcel.readLong();
        this.mMaxAttempts = parcel.readInt();
        this.mPollingInterval = parcel.readLong();
        this.mPollingIntervalShorter = parcel.readLong();
        this.mTimeErrorThreshold = parcel.readInt();
    }

    static NtpInfo convertToNew(android.app.enterprise.NtpInfo ntpInfo) {
        if (ntpInfo == null) {
            return null;
        }
        NtpInfo ntpInfo2 = new NtpInfo();
        ntpInfo2.setMaxAttempts(ntpInfo.getMaxAttempts());
        ntpInfo2.setPollingInterval(ntpInfo.getPollingInterval());
        ntpInfo2.setPollingIntervalShorter(ntpInfo.getPollingIntervalShorter());
        ntpInfo2.setServer(ntpInfo.getServer());
        ntpInfo2.setTimeErrorThreshold(ntpInfo.getTimeErrorThreshold());
        ntpInfo2.setTimeout(ntpInfo.getTimeout());
        return ntpInfo2;
    }

    static android.app.enterprise.NtpInfo convertToOld(Context context, NtpInfo ntpInfo) throws NoClassDefFoundError {
        if (ntpInfo == null) {
            return null;
        }
        try {
            android.app.enterprise.NtpInfo ntpInfo2 = new android.app.enterprise.NtpInfo(context);
            ntpInfo2.setMaxAttempts(ntpInfo.getMaxAttempts());
            ntpInfo2.setPollingInterval(ntpInfo.getPollingInterval());
            ntpInfo2.setPollingIntervalShorter(ntpInfo.getPollingIntervalShorter());
            ntpInfo2.setServer(ntpInfo.getServer());
            ntpInfo2.setTimeErrorThreshold(ntpInfo.getTimeErrorThreshold());
            ntpInfo2.setTimeout(ntpInfo.getTimeout());
            return ntpInfo2;
        } catch (NoClassDefFoundError unused) {
            throw new NoClassDefFoundError(SupportLibUtils.buildClassErrorMsg(NtpInfo.class, 17));
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getMaxAttempts() {
        return this.mMaxAttempts;
    }

    public long getPollingInterval() {
        return this.mPollingInterval;
    }

    public long getPollingIntervalShorter() {
        return this.mPollingIntervalShorter;
    }

    public String getServer() {
        return this.mServer;
    }

    public int getTimeErrorThreshold() {
        return this.mTimeErrorThreshold;
    }

    public long getTimeout() {
        return this.mTimeout;
    }

    public void setMaxAttempts(int i) {
        this.mMaxAttempts = i;
    }

    public void setPollingInterval(long j) {
        this.mPollingInterval = j;
    }

    public void setPollingIntervalShorter(long j) {
        this.mPollingIntervalShorter = j;
    }

    public void setServer(String str) {
        this.mServer = str;
    }

    public void setTimeErrorThreshold(int i) {
        this.mTimeErrorThreshold = i;
    }

    public void setTimeout(long j) {
        this.mTimeout = j;
    }

    public String toString() {
        return "server=" + this.mServer + " timeout=" + this.mTimeout + " maxAttempts=" + this.mMaxAttempts + " pollingInterval=" + this.mPollingInterval + " pollingIntervalShorter=" + this.mPollingIntervalShorter + " timeErrorThreshold=" + this.mTimeErrorThreshold;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mServer);
        parcel.writeLong(this.mTimeout);
        parcel.writeInt(this.mMaxAttempts);
        parcel.writeLong(this.mPollingInterval);
        parcel.writeLong(this.mPollingIntervalShorter);
        parcel.writeInt(this.mTimeErrorThreshold);
    }
}
