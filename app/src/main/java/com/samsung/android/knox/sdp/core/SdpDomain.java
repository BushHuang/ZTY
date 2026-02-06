package com.samsung.android.knox.sdp.core;

import com.samsung.android.knox.SupportLibUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class SdpDomain implements Serializable {
    private final String mAlias;
    private final String mPackageName;

    public SdpDomain(String str, String str2) {
        this.mAlias = str == null ? "" : str;
        this.mPackageName = str2 == null ? "" : str2;
    }

    static SdpDomain convertToNew(com.sec.enterprise.knox.sdp.engine.SdpDomain sdpDomain) {
        if (sdpDomain == null) {
            return null;
        }
        return new SdpDomain(sdpDomain.getAlias(), sdpDomain.getPackageName());
    }

    static ArrayList<SdpDomain> convertToNewList(ArrayList<com.sec.enterprise.knox.sdp.engine.SdpDomain> arrayList) {
        ArrayList<SdpDomain> arrayList2 = new ArrayList<>();
        if (arrayList == null) {
            return null;
        }
        Iterator<com.sec.enterprise.knox.sdp.engine.SdpDomain> it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(convertToNew(it.next()));
        }
        return arrayList2;
    }

    static com.sec.enterprise.knox.sdp.engine.SdpDomain convertToOld(SdpDomain sdpDomain) throws NoClassDefFoundError {
        if (sdpDomain == null) {
            return null;
        }
        try {
            return new com.sec.enterprise.knox.sdp.engine.SdpDomain(sdpDomain.getAlias(), sdpDomain.getPackageName());
        } catch (NoClassDefFoundError unused) {
            throw new NoClassDefFoundError(SupportLibUtils.buildClassErrorMsg(SdpDomain.class, 19));
        }
    }

    static ArrayList<com.sec.enterprise.knox.sdp.engine.SdpDomain> convertToOldList(ArrayList<SdpDomain> arrayList) throws NoClassDefFoundError {
        ArrayList<com.sec.enterprise.knox.sdp.engine.SdpDomain> arrayList2 = new ArrayList<>();
        if (arrayList == null) {
            return null;
        }
        Iterator<SdpDomain> it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(convertToOld(it.next()));
        }
        return arrayList2;
    }

    public String getAlias() {
        return this.mAlias;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public String toString() {
        return new String("SdpDomain { alias : " + this.mAlias + " / pkgName : " + this.mPackageName + " }");
    }
}
