package androidx.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

public class ObservableDouble extends BaseObservableField implements Parcelable, Serializable {
    public static final Parcelable.Creator<ObservableDouble> CREATOR = new Parcelable.Creator<ObservableDouble>() {
        @Override
        public ObservableDouble createFromParcel(Parcel parcel) {
            return new ObservableDouble(parcel.readDouble());
        }

        @Override
        public ObservableDouble[] newArray(int i) {
            return new ObservableDouble[i];
        }
    };
    static final long serialVersionUID = 1;
    private double mValue;

    public ObservableDouble() {
    }

    public ObservableDouble(double d) {
        this.mValue = d;
    }

    public ObservableDouble(Observable... observableArr) {
        super(observableArr);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public double get() {
        return this.mValue;
    }

    public void set(double d) {
        if (d != this.mValue) {
            this.mValue = d;
            notifyChange();
        }
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.mValue);
    }
}
