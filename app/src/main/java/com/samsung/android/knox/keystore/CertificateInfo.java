package com.samsung.android.knox.keystore;

import android.os.Parcel;
import android.os.Parcelable;
import java.security.Key;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CertificateInfo implements Parcelable {
    public static final Parcelable.Creator<CertificateInfo> CREATOR = new Parcelable.Creator<CertificateInfo>() {
        @Override
        public CertificateInfo createFromParcel(Parcel parcel) {
            return new CertificateInfo(parcel);
        }

        @Override
        public CertificateInfo[] newArray(int i) {
            return new CertificateInfo[i];
        }
    };
    private String mAlias;
    Certificate mCertificate;
    private boolean mEnabled;
    private boolean mHasPrivateKey;
    Key mKey;
    private int mKeystore;
    private boolean mSystemPreloaded;

    public CertificateInfo() {
        this.mCertificate = null;
        this.mKey = null;
        this.mAlias = "";
        this.mKeystore = -1;
        this.mSystemPreloaded = false;
        this.mEnabled = true;
        this.mHasPrivateKey = false;
    }

    public CertificateInfo(Parcel parcel) {
        this.mCertificate = null;
        this.mKey = null;
        this.mAlias = "";
        this.mKeystore = -1;
        this.mSystemPreloaded = false;
        this.mEnabled = true;
        this.mHasPrivateKey = false;
        readFromParcel(parcel);
    }

    public CertificateInfo(Certificate certificate) {
        this.mCertificate = null;
        this.mKey = null;
        this.mAlias = "";
        this.mKeystore = -1;
        this.mSystemPreloaded = false;
        this.mEnabled = true;
        this.mHasPrivateKey = false;
        this.mCertificate = certificate;
    }

    private boolean compareKeys(Key key, Key key2) {
        if (key == key2) {
            return true;
        }
        if (key == null || key2 == null) {
            return false;
        }
        return Arrays.equals(key.getEncoded(), key2.getEncoded());
    }

    public static CertificateInfo convertToNew(android.app.enterprise.CertificateInfo certificateInfo) {
        if (certificateInfo == null) {
            return null;
        }
        CertificateInfo certificateInfo2 = new CertificateInfo();
        certificateInfo2.setCertificate(certificateInfo.getCertificate());
        certificateInfo2.setAlias(certificateInfo.getAlias());
        certificateInfo2.setEnabled(certificateInfo.getEnabled());
        certificateInfo2.setHasPrivateKey(certificateInfo.getHasPrivateKey());
        certificateInfo2.setKeystore(certificateInfo.getKeystore());
        certificateInfo2.setSystemPreloaded(certificateInfo.getSystemPreloaded());
        return certificateInfo2;
    }

    static List<CertificateInfo> convertToNewList(List<android.app.enterprise.CertificateInfo> list) {
        ArrayList arrayList = new ArrayList();
        if (list == null) {
            return null;
        }
        Iterator<android.app.enterprise.CertificateInfo> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(convertToNew(it.next()));
        }
        return arrayList;
    }

    static android.app.enterprise.CertificateInfo convertToOld(CertificateInfo certificateInfo) {
        if (certificateInfo == null) {
            return null;
        }
        android.app.enterprise.CertificateInfo certificateInfo2 = new android.app.enterprise.CertificateInfo();
        certificateInfo2.setCertificate(certificateInfo.getCertificate());
        certificateInfo2.setAlias(certificateInfo.getAlias());
        certificateInfo2.setEnabled(certificateInfo.getEnabled());
        certificateInfo2.setHasPrivateKey(certificateInfo.getHasPrivateKey());
        certificateInfo2.setKeystore(certificateInfo.getKeystore());
        certificateInfo2.setSystemPreloaded(certificateInfo.getSystemPreloaded());
        return certificateInfo2;
    }

    private void readFromParcel(Parcel parcel) {
        this.mCertificate = (Certificate) parcel.readSerializable();
        this.mKey = (Key) parcel.readSerializable();
        this.mAlias = (String) parcel.readSerializable();
        this.mKeystore = ((Integer) parcel.readSerializable()).intValue();
        this.mSystemPreloaded = ((Boolean) parcel.readSerializable()).booleanValue();
        this.mEnabled = ((Boolean) parcel.readSerializable()).booleanValue();
        this.mHasPrivateKey = ((Boolean) parcel.readSerializable()).booleanValue();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof CertificateInfo)) {
            CertificateInfo certificateInfo = (CertificateInfo) obj;
            Certificate certificate = this.mCertificate;
            if (certificate != null && certificate.equals(certificateInfo.mCertificate) && compareKeys(this.mKey, certificateInfo.mKey)) {
                return true;
            }
        }
        return super.equals(obj);
    }

    public String getAlias() {
        return this.mAlias;
    }

    public Certificate getCertificate() {
        return this.mCertificate;
    }

    public boolean getEnabled() {
        return this.mEnabled;
    }

    public boolean getHasPrivateKey() {
        return this.mHasPrivateKey;
    }

    public int getKeystore() {
        return this.mKeystore;
    }

    public Key getPrivateKey() {
        return this.mKey;
    }

    public String getSubject() {
        Certificate certificate = this.mCertificate;
        if (certificate instanceof X509Certificate) {
            return ((X509Certificate) certificate).getSubjectDN().getName();
        }
        return null;
    }

    public boolean getSystemPreloaded() {
        return this.mSystemPreloaded;
    }

    public void setAlias(String str) {
        this.mAlias = str;
    }

    public void setCertificate(Certificate certificate) {
        this.mCertificate = certificate;
    }

    public void setEnabled(boolean z) {
        this.mEnabled = z;
    }

    public void setHasPrivateKey(boolean z) {
        this.mHasPrivateKey = z;
    }

    public void setKeystore(int i) {
        this.mKeystore = i;
    }

    public void setPrivateKey(Key key) {
        this.mKey = key;
    }

    public void setSystemPreloaded(boolean z) {
        this.mSystemPreloaded = z;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            parcel.writeSerializable(this.mCertificate);
            parcel.writeSerializable(this.mKey);
            parcel.writeSerializable(this.mAlias);
            parcel.writeSerializable(Integer.valueOf(this.mKeystore));
            parcel.writeSerializable(Boolean.valueOf(this.mSystemPreloaded));
            parcel.writeSerializable(Boolean.valueOf(this.mEnabled));
            parcel.writeSerializable(Boolean.valueOf(this.mHasPrivateKey));
        }
    }
}
