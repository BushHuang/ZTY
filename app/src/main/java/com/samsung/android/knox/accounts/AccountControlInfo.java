package com.samsung.android.knox.accounts;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.knox.ControlInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AccountControlInfo extends ControlInfo {
    public static final Parcelable.Creator<AccountControlInfo> CREATOR = new Parcelable.Creator<AccountControlInfo>() {
        @Override
        public AccountControlInfo createFromParcel(Parcel parcel) {
            return new AccountControlInfo(parcel);
        }

        @Override
        public AccountControlInfo[] newArray(int i) {
            return new AccountControlInfo[i];
        }
    };

    public AccountControlInfo() {
    }

    private AccountControlInfo(Parcel parcel) {
        readFromParcel(parcel);
    }

    static AccountControlInfo convertToNew(android.app.enterprise.AccountControlInfo accountControlInfo) {
        if (accountControlInfo == null) {
            return null;
        }
        AccountControlInfo accountControlInfo2 = new AccountControlInfo();
        accountControlInfo2.adminPackageName = accountControlInfo.adminPackageName;
        accountControlInfo2.entries = new ArrayList(accountControlInfo.entries);
        return accountControlInfo2;
    }

    static List<AccountControlInfo> convertToNewList(List<android.app.enterprise.AccountControlInfo> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<android.app.enterprise.AccountControlInfo> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(convertToNew(it.next()));
        }
        return arrayList;
    }
}
