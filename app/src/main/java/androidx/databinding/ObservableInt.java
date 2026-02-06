package androidx.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

public class ObservableInt extends BaseObservableField implements Parcelable, Serializable {
    public static final Parcelable.Creator<ObservableInt> CREATOR = new Parcelable.Creator<ObservableInt>() {
        @Override
        public ObservableInt createFromParcel(Parcel parcel) {
            return new ObservableInt(parcel.readInt());
        }

        @Override
        public ObservableInt[] newArray(int i) {
            return new ObservableInt[i];
        }
    };
    static final long serialVersionUID = 1;
    private int mValue;

    public ObservableInt() {
    }

    public ObservableInt(int i) {
        this.mValue = i;
    }

    public ObservableInt(Observable... observableArr) {
        super(observableArr);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int get() {
        return this.mValue;
    }

    public void set(int i) {
        if (i != this.mValue) {
            this.mValue = i;
            notifyChange();
        }
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mValue);
    }
}
