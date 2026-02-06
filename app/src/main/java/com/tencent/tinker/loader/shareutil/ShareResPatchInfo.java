package com.tencent.tinker.loader.shareutil;

import com.tencent.tinker.loader.TinkerRuntimeException;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Pattern;

public class ShareResPatchInfo {
    public String arscBaseCrc = null;
    public String resArscMd5 = null;
    public ArrayList<String> addRes = new ArrayList<>();
    public ArrayList<String> deleteRes = new ArrayList<>();
    public ArrayList<String> modRes = new ArrayList<>();
    public HashMap<String, File> storeRes = new HashMap<>();
    public ArrayList<String> largeModRes = new ArrayList<>();
    public HashMap<String, LargeModeInfo> largeModMap = new HashMap<>();
    public HashSet<Pattern> patterns = new HashSet<>();

    public static class LargeModeInfo {
        public long crc;
        public String md5 = null;
        public File file = null;
    }

    public static boolean checkFileInPattern(HashSet<Pattern> hashSet, String str) {
        if (hashSet.isEmpty()) {
            return false;
        }
        Iterator<Pattern> it = hashSet.iterator();
        while (it.hasNext()) {
            if (it.next().matcher(str).matches()) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkResPatchInfo(ShareResPatchInfo shareResPatchInfo) {
        String str;
        return (shareResPatchInfo == null || (str = shareResPatchInfo.resArscMd5) == null || str.length() != 32) ? false : true;
    }

    private static Pattern convertToPatternString(String str) {
        if (str.contains(".")) {
            str = str.replaceAll("\\.", "\\\\.");
        }
        if (str.contains("?")) {
            str = str.replaceAll("\\?", "\\.");
        }
        if (str.contains("*")) {
            str = str.replace("*", ".*");
        }
        return Pattern.compile(str);
    }

    public static void parseAllResPatchInfo(String str, ShareResPatchInfo shareResPatchInfo) throws NumberFormatException {
        if (str == null || str.length() == 0) {
            return;
        }
        String[] strArrSplit = str.split("\n");
        int i = 0;
        while (i < strArrSplit.length) {
            String str2 = strArrSplit[i];
            if (str2 != null && str2.length() > 0) {
                if (str2.startsWith("resources_out.zip")) {
                    String[] strArrSplit2 = str2.split(",", 3);
                    shareResPatchInfo.arscBaseCrc = strArrSplit2[1];
                    shareResPatchInfo.resArscMd5 = strArrSplit2[2];
                } else if (str2.startsWith("pattern:")) {
                    for (int i2 = Integer.parseInt(str2.split(":", 2)[1]); i2 > 0; i2--) {
                        i++;
                        shareResPatchInfo.patterns.add(convertToPatternString(strArrSplit[i]));
                    }
                } else if (str2.startsWith("add:")) {
                    for (int i3 = Integer.parseInt(str2.split(":", 2)[1]); i3 > 0; i3--) {
                        i++;
                        shareResPatchInfo.addRes.add(strArrSplit[i]);
                    }
                } else if (str2.startsWith("modify:")) {
                    for (int i4 = Integer.parseInt(str2.split(":", 2)[1]); i4 > 0; i4--) {
                        i++;
                        shareResPatchInfo.modRes.add(strArrSplit[i]);
                    }
                } else if (str2.startsWith("large modify:")) {
                    for (int i5 = Integer.parseInt(str2.split(":", 2)[1]); i5 > 0; i5--) {
                        i++;
                        String[] strArrSplit3 = strArrSplit[i].split(",", 3);
                        String str3 = strArrSplit3[0];
                        LargeModeInfo largeModeInfo = new LargeModeInfo();
                        largeModeInfo.md5 = strArrSplit3[1];
                        largeModeInfo.crc = Long.parseLong(strArrSplit3[2]);
                        shareResPatchInfo.largeModRes.add(str3);
                        shareResPatchInfo.largeModMap.put(str3, largeModeInfo);
                    }
                } else if (str2.startsWith("delete:")) {
                    for (int i6 = Integer.parseInt(str2.split(":", 2)[1]); i6 > 0; i6--) {
                        i++;
                        shareResPatchInfo.deleteRes.add(strArrSplit[i]);
                    }
                } else if (str2.startsWith("store:")) {
                    for (int i7 = Integer.parseInt(str2.split(":", 2)[1]); i7 > 0; i7--) {
                        i++;
                        shareResPatchInfo.storeRes.put(strArrSplit[i], null);
                    }
                }
            }
            i++;
        }
    }

    public static void parseResPatchInfoFirstLine(String str, ShareResPatchInfo shareResPatchInfo) {
        if (str == null || str.length() == 0) {
            return;
        }
        String str2 = str.split("\n")[0];
        if (str2 == null || str2.length() <= 0) {
            throw new TinkerRuntimeException("res meta Corrupted:" + str);
        }
        String[] strArrSplit = str2.split(",", 3);
        shareResPatchInfo.arscBaseCrc = strArrSplit[1];
        shareResPatchInfo.resArscMd5 = strArrSplit[2];
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("resArscMd5:" + this.resArscMd5 + "\n");
        stringBuffer.append("arscBaseCrc:" + this.arscBaseCrc + "\n");
        Iterator<Pattern> it = this.patterns.iterator();
        while (it.hasNext()) {
            stringBuffer.append("pattern:" + it.next() + "\n");
        }
        Iterator<String> it2 = this.addRes.iterator();
        while (it2.hasNext()) {
            stringBuffer.append("addedSet:" + it2.next() + "\n");
        }
        Iterator<String> it3 = this.modRes.iterator();
        while (it3.hasNext()) {
            stringBuffer.append("modifiedSet:" + it3.next() + "\n");
        }
        Iterator<String> it4 = this.largeModRes.iterator();
        while (it4.hasNext()) {
            stringBuffer.append("largeModifiedSet:" + it4.next() + "\n");
        }
        Iterator<String> it5 = this.deleteRes.iterator();
        while (it5.hasNext()) {
            stringBuffer.append("deletedSet:" + it5.next() + "\n");
        }
        Iterator<String> it6 = this.storeRes.keySet().iterator();
        while (it6.hasNext()) {
            stringBuffer.append("storeSet:" + it6.next() + "\n");
        }
        return stringBuffer.toString();
    }
}
