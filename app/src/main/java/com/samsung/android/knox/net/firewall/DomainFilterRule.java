package com.samsung.android.knox.net.firewall;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.knox.AppIdentity;
import com.samsung.android.knox.SupportLibUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DomainFilterRule implements Parcelable {
    public static final List<DomainFilterRule> CLEAR_ALL = null;
    public static final Parcelable.Creator<DomainFilterRule> CREATOR = new Parcelable.Creator<DomainFilterRule>() {
        @Override
        public DomainFilterRule createFromParcel(Parcel parcel) {
            return new DomainFilterRule(parcel);
        }

        @Override
        public DomainFilterRule[] newArray(int i) {
            return new DomainFilterRule[i];
        }
    };
    private List<String> mAllowDomains;
    private AppIdentity mAppIdentity;
    private List<String> mDenyDomains;
    private String mDns1;
    private String mDns2;
    private int mNullCheck;

    public DomainFilterRule() {
        this.mAppIdentity = new AppIdentity();
        this.mDenyDomains = new ArrayList();
        this.mAllowDomains = new ArrayList();
        this.mDns1 = null;
        this.mDns2 = null;
    }

    private DomainFilterRule(Parcel parcel) {
        this();
        this.mAppIdentity = (AppIdentity) parcel.readParcelable(AppIdentity.class.getClassLoader());
        int i = parcel.readInt();
        this.mNullCheck = i;
        if (i == 1) {
            parcel.readStringList(this.mDenyDomains);
        } else {
            this.mDenyDomains = null;
        }
        int i2 = parcel.readInt();
        this.mNullCheck = i2;
        if (i2 == 1) {
            parcel.readStringList(this.mAllowDomains);
        } else {
            this.mAllowDomains = null;
        }
        this.mDns1 = parcel.readString();
        this.mDns2 = parcel.readString();
    }

    public DomainFilterRule(AppIdentity appIdentity) {
        this.mAppIdentity = appIdentity;
        this.mDenyDomains = new ArrayList();
        this.mAllowDomains = new ArrayList();
        this.mDns1 = null;
        this.mDns2 = null;
    }

    public DomainFilterRule(AppIdentity appIdentity, List<String> list, List<String> list2) {
        this.mAppIdentity = appIdentity;
        if (list != null) {
            this.mDenyDomains = new ArrayList(list);
        } else {
            this.mDenyDomains = null;
        }
        if (list2 != null) {
            this.mAllowDomains = new ArrayList(list2);
        } else {
            this.mAllowDomains = null;
        }
    }

    public DomainFilterRule(AppIdentity appIdentity, List<String> list, List<String> list2, String str, String str2) {
        this.mAppIdentity = appIdentity;
        if (list != null) {
            this.mDenyDomains = new ArrayList(list);
        } else {
            this.mDenyDomains = null;
        }
        if (list2 != null) {
            this.mAllowDomains = new ArrayList(list2);
        } else {
            this.mAllowDomains = null;
        }
        this.mDns1 = str;
        this.mDns2 = str2;
    }

    private static DomainFilterRule convertToNew(com.sec.enterprise.firewall.DomainFilterRule domainFilterRule) {
        if (domainFilterRule == null) {
            return null;
        }
        DomainFilterRule domainFilterRule2 = new DomainFilterRule();
        domainFilterRule2.setAllowDomains(domainFilterRule.getAllowDomains());
        domainFilterRule2.setApplication(AppIdentity.convertToNew(domainFilterRule.getApplication()));
        domainFilterRule2.setDenyDomains(domainFilterRule.getDenyDomains());
        try {
            domainFilterRule2.setDns1(domainFilterRule.getDns1());
        } catch (NoSuchMethodError unused) {
        }
        try {
            domainFilterRule2.setDns2(domainFilterRule.getDns2());
        } catch (NoSuchMethodError unused2) {
        }
        return domainFilterRule2;
    }

    static List<DomainFilterRule> convertToNewList(List<com.sec.enterprise.firewall.DomainFilterRule> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<com.sec.enterprise.firewall.DomainFilterRule> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(convertToNew(it.next()));
        }
        return arrayList;
    }

    private static com.sec.enterprise.firewall.DomainFilterRule convertToOld(DomainFilterRule domainFilterRule) throws NoSuchMethodError, NoClassDefFoundError {
        if (domainFilterRule == null) {
            return null;
        }
        try {
            com.sec.enterprise.firewall.DomainFilterRule domainFilterRule2 = new com.sec.enterprise.firewall.DomainFilterRule(AppIdentity.convertToOld(domainFilterRule.getApplication()), domainFilterRule.getDenyDomains(), domainFilterRule.getAllowDomains());
            try {
                domainFilterRule2.setDns1(domainFilterRule.getDns1());
            } catch (NoSuchMethodError unused) {
                if (domainFilterRule.getDns1() != null) {
                    throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(DomainFilterRule.class, "setDns1", new Class[]{String.class}, 20));
                }
            }
            try {
                domainFilterRule2.setDns2(domainFilterRule.getDns2());
            } catch (NoSuchMethodError unused2) {
                if (domainFilterRule.getDns2() != null) {
                    throw new NoSuchMethodError(SupportLibUtils.buildMethodErrorMsg(DomainFilterRule.class, "setDns2", new Class[]{String.class}, 20));
                }
            }
            return domainFilterRule2;
        } catch (NoClassDefFoundError unused3) {
            throw new NoClassDefFoundError(SupportLibUtils.buildClassErrorMsg(DomainFilterRule.class, 19));
        }
    }

    static List<com.sec.enterprise.firewall.DomainFilterRule> convertToOldList(List<DomainFilterRule> list) throws NoSuchMethodError, NoClassDefFoundError {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<DomainFilterRule> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(convertToOld(it.next()));
        }
        return arrayList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public List<String> getAllowDomains() {
        return this.mAllowDomains;
    }

    public AppIdentity getApplication() {
        return this.mAppIdentity;
    }

    public List<String> getDenyDomains() {
        return this.mDenyDomains;
    }

    public String getDns1() {
        return this.mDns1;
    }

    public String getDns2() {
        return this.mDns2;
    }

    public void setAllowDomains(List<String> list) {
        if (list != null) {
            this.mAllowDomains = new ArrayList(list);
        } else {
            this.mAllowDomains = null;
        }
    }

    public void setApplication(AppIdentity appIdentity) {
        this.mAppIdentity = appIdentity;
    }

    public void setDenyDomains(List<String> list) {
        if (list != null) {
            this.mDenyDomains = new ArrayList(list);
        } else {
            this.mDenyDomains = null;
        }
    }

    public void setDns1(String str) {
        this.mDns1 = str;
    }

    public void setDns2(String str) {
        this.mDns2 = str;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mAppIdentity, i);
        if (this.mDenyDomains != null) {
            parcel.writeInt(1);
            parcel.writeStringList(this.mDenyDomains);
        } else {
            parcel.writeInt(0);
        }
        if (this.mAllowDomains != null) {
            parcel.writeInt(1);
            parcel.writeStringList(this.mAllowDomains);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeString(this.mDns1);
        parcel.writeString(this.mDns2);
    }
}
