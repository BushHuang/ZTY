package cn.com.microtrust.firewall.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class AFWRes implements Parcelable {
    public static final Parcelable.Creator<AFWRes> CREATOR = new Parcelable.Creator<AFWRes>() {
        @Override
        public AFWRes createFromParcel(Parcel parcel) {
            return new AFWRes(parcel);
        }

        @Override
        public AFWRes[] newArray(int i) {
            return new AFWRes[i];
        }
    };
    private List<String> data;
    private boolean operationResult;

    public AFWRes() {
        this.data = new ArrayList();
    }

    private AFWRes(Parcel parcel) {
        this.data = new ArrayList();
        readFromParcel(parcel);
    }

    private void readFromParcel(Parcel parcel) {
        parcel.readStringList(this.data);
        if (parcel.readInt() == 1) {
            this.operationResult = true;
        } else {
            this.operationResult = false;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public List<String> getData() {
        return this.data;
    }

    public boolean getOperationResult() {
        return this.operationResult;
    }

    public void setData(List<String> list) {
        this.data.clear();
        this.data.addAll(list);
    }

    public void setOperationResult(boolean z) {
        this.operationResult = z;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringList(this.data);
        if (this.operationResult) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
        }
    }
}
