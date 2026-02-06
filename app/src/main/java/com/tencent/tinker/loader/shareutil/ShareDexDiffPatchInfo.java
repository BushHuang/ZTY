package com.tencent.tinker.loader.shareutil;

import com.tencent.tinker.loader.TinkerRuntimeException;
import java.util.ArrayList;

public class ShareDexDiffPatchInfo {
    public final String destMd5InArt;
    public final String destMd5InDvm;
    public final String dexDiffMd5;
    public final String dexMode;
    public final boolean isJarMode;
    public final String newOrPatchedDexCrC;
    public final String oldDexCrC;
    public final String path;
    public final String rawName;
    public final String realName;

    public ShareDexDiffPatchInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this.rawName = str;
        this.path = str2;
        this.destMd5InDvm = str3;
        this.destMd5InArt = str4;
        this.dexDiffMd5 = str5;
        this.oldDexCrC = str6;
        this.newOrPatchedDexCrC = str7;
        this.dexMode = str8;
        if (!str8.equals("jar")) {
            if (str8.equals("raw")) {
                this.isJarMode = false;
                this.realName = str;
                return;
            } else {
                throw new TinkerRuntimeException("can't recognize dex mode:" + str8);
            }
        }
        this.isJarMode = true;
        if (!SharePatchFileUtil.isRawDexFile(str)) {
            this.realName = str;
            return;
        }
        this.realName = str + ".jar";
    }

    public static boolean checkDexDiffPatchInfo(ShareDexDiffPatchInfo shareDexDiffPatchInfo) {
        if (shareDexDiffPatchInfo == null) {
            return false;
        }
        String str = shareDexDiffPatchInfo.rawName;
        String str2 = ShareTinkerInternals.isVmArt() ? shareDexDiffPatchInfo.destMd5InArt : shareDexDiffPatchInfo.destMd5InDvm;
        return str != null && str.length() > 0 && str2 != null && str2.length() == 32;
    }

    public static void parseDexDiffPatchInfo(String str, ArrayList<ShareDexDiffPatchInfo> arrayList) {
        String[] strArrSplit;
        if (str == null || str.length() == 0) {
            return;
        }
        for (String str2 : str.split("\n")) {
            if (str2 != null && str2.length() > 0 && (strArrSplit = str2.split(",", 8)) != null && strArrSplit.length >= 8) {
                arrayList.add(new ShareDexDiffPatchInfo(strArrSplit[0].trim(), strArrSplit[1].trim(), strArrSplit[2].trim(), strArrSplit[3].trim(), strArrSplit[4].trim(), strArrSplit[5].trim(), strArrSplit[6].trim(), strArrSplit[7].trim()));
            }
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.rawName);
        stringBuffer.append(",");
        stringBuffer.append(this.path);
        stringBuffer.append(",");
        stringBuffer.append(this.destMd5InDvm);
        stringBuffer.append(",");
        stringBuffer.append(this.destMd5InArt);
        stringBuffer.append(",");
        stringBuffer.append(this.oldDexCrC);
        stringBuffer.append(",");
        stringBuffer.append(this.newOrPatchedDexCrC);
        stringBuffer.append(",");
        stringBuffer.append(this.dexDiffMd5);
        stringBuffer.append(",");
        stringBuffer.append(this.dexMode);
        return stringBuffer.toString();
    }
}
