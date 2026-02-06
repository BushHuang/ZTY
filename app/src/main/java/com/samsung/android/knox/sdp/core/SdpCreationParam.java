package com.samsung.android.knox.sdp.core;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.knox.SupportLibUtils;
import java.util.ArrayList;
import java.util.Iterator;

public class SdpCreationParam implements Parcelable {
    public static final Parcelable.Creator<SdpCreationParam> CREATOR = new Parcelable.Creator<SdpCreationParam>() {
        @Override
        public SdpCreationParam createFromParcel(Parcel parcel) {
            return new SdpCreationParam(parcel);
        }

        @Override
        public SdpCreationParam[] newArray(int i) {
            return new SdpCreationParam[i];
        }
    };
    private String mAlias;
    private int mFlags;
    private ArrayList<SdpDomain> mPrivilegedApps;

    private SdpCreationParam(Parcel parcel) {
        this.mFlags = 0;
        this.mAlias = parcel.readString();
        this.mFlags = parcel.readInt();
        this.mPrivilegedApps = (ArrayList) parcel.readSerializable();
    }

    public SdpCreationParam(String str, int i, ArrayList<SdpDomain> arrayList) {
        this.mFlags = 0;
        this.mAlias = str == null ? "" : str;
        this.mFlags = validateFlags(i);
        this.mPrivilegedApps = validatePrivilegedApps(arrayList);
    }

    static SdpCreationParam convertToNew(com.sec.enterprise.knox.sdp.engine.SdpCreationParam sdpCreationParam) {
        if (sdpCreationParam == null) {
            return null;
        }
        return new SdpCreationParam(sdpCreationParam.getAlias(), sdpCreationParam.getFlags(), SdpDomain.convertToNewList(sdpCreationParam.getPrivilegedApps()));
    }

    static com.sec.enterprise.knox.sdp.engine.SdpCreationParam convertToOld(SdpCreationParam sdpCreationParam) throws NoClassDefFoundError {
        if (sdpCreationParam == null) {
            return null;
        }
        try {
            return new com.sec.enterprise.knox.sdp.engine.SdpCreationParam(sdpCreationParam.getAlias(), sdpCreationParam.getFlags(), SdpDomain.convertToOldList(sdpCreationParam.getPrivilegedApps()));
        } catch (NoClassDefFoundError unused) {
            throw new NoClassDefFoundError(SupportLibUtils.buildClassErrorMsg(SdpCreationParam.class, 19));
        }
    }

    private int validateFlags(int i) {
        if (i < 0 || i > 1) {
            return 0;
        }
        return i;
    }

    private ArrayList<SdpDomain> validatePrivilegedApps(ArrayList<SdpDomain> arrayList) {
        ArrayList<SdpDomain> arrayList2 = new ArrayList<>();
        if (arrayList != null) {
            Iterator<SdpDomain> it = arrayList.iterator();
            while (it.hasNext()) {
                SdpDomain next = it.next();
                if (next.getPackageName() != null && !next.getPackageName().trim().isEmpty()) {
                    arrayList2.add(next);
                }
            }
        }
        return arrayList2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getAlias() {
        return this.mAlias;
    }

    public int getFlags() {
        return this.mFlags;
    }

    public ArrayList<SdpDomain> getPrivilegedApps() {
        return this.mPrivilegedApps;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nSdpCreationParam { ");
        sb.append("\n");
        sb.append("alias:" + this.mAlias);
        sb.append("\n");
        Iterator<SdpDomain> it = this.mPrivilegedApps.iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
            sb.append("\n");
        }
        sb.append("\n}");
        return sb.toString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mAlias);
        parcel.writeInt(this.mFlags);
        parcel.writeSerializable(this.mPrivilegedApps);
    }
}
