package com.xh.xhcore.common.util;

import android.util.Base64;
import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002J\b\u0010\b\u001a\u00020\tH\u0007J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002J\n\u0010\u000b\u001a\u0004\u0018\u00010\u0004H\u0007J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\r2\u0006\u0010\u000e\u001a\u00020\u0004H\u0002J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002J\u001c\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00042\b\u0010\u0013\u001a\u0004\u0018\u00010\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/xh/xhcore/common/util/XhSecretKeyUtils;", "", "()V", "DIR_NAME", "", "appendRandom", "text", "decode", "deleteSecretFile", "", "encode", "readSecretFile", "splitRandom", "", "random", "subtractRandom", "writeSecretToFile", "", "version", "key", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class XhSecretKeyUtils {
    private static final String DIR_NAME = "sec";
    public static final XhSecretKeyUtils INSTANCE = new XhSecretKeyUtils();

    private XhSecretKeyUtils() {
    }

    private final String appendRandom(String text) {
        IntRange intRange = new IntRange(1, 6);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange, 10));
        Iterator<Integer> it = intRange.iterator();
        while (it.hasNext()) {
            ((IntIterator) it).nextInt();
            arrayList.add(Character.valueOf(StringsKt.random("ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789", Random.INSTANCE)));
        }
        String strJoinToString$default = CollectionsKt.joinToString$default(arrayList, "", null, null, 0, null, null, 62, null);
        Log.e("XhSecretKeyUtils", Intrinsics.stringPlus("randomText=", strJoinToString$default));
        return Intrinsics.stringPlus(text, strJoinToString$default);
    }

    private final String decode(String text) {
        try {
            byte[] bArrDecode = Base64.decode(text, 0);
            Intrinsics.checkNotNullExpressionValue(bArrDecode, "decode(text, Base64.DEFAULT)");
            return new String(bArrDecode, Charsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return text;
        }
    }

    @JvmStatic
    public static final synchronized boolean deleteSecretFile() {
        String xhDir;
        boolean z = true;
        try {
            xhDir = XhFileUtils.getXhDir();
        } catch (Exception e) {
            e.printStackTrace();
            z = false;
        }
        if (xhDir == null) {
            return true;
        }
        XhFileUtils.deleteDirs(xhDir + ((Object) File.separator) + "sec");
        return z;
    }

    private final String encode(String text) {
        try {
            byte[] bytes = text.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            String strEncodeToString = Base64.encodeToString(bytes, 0);
            Intrinsics.checkNotNullExpressionValue(strEncodeToString, "{\n            Base64.enc…Base64.DEFAULT)\n        }");
            return strEncodeToString;
        } catch (Exception e) {
            e.printStackTrace();
            return text;
        }
    }

    @JvmStatic
    public static final synchronized String readSecretFile() {
        String file;
        try {
            file = XhFileUtils.readFile((((Object) XhFileUtils.getXhDir()) + ((Object) File.separator) + "sec") + ((Object) File.separator) + "sec.ini");
        } catch (Exception e) {
            e.printStackTrace();
            file = (String) null;
        }
        return file;
    }

    private final List<String> splitRandom(String random) {
        int length = random.length() / 3;
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            arrayList.add(StringsKt.substring(random, new IntRange(i * 3, (i == length + (-1) ? random.length() : i2 * 3) - 1)));
            i = i2;
        }
        return arrayList;
    }

    private final String subtractRandom(String text) {
        if (text.length() <= 6) {
            return text;
        }
        String strSubstring = text.substring(0, text.length() - 6);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    @JvmStatic
    public static final synchronized void writeSecretToFile(String version, String key) {
        if (version != null && key != null) {
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!Intrinsics.areEqual(version, "") && !Intrinsics.areEqual(key, "")) {
                String xhDir = XhFileUtils.getXhDir();
                if (xhDir == null) {
                    return;
                }
                String str = xhDir + ((Object) File.separator) + "sec";
                XhFileUtils.deleteDirs(str);
                XhFileUtils.createDirs(str);
                String str2 = str + ((Object) File.separator) + "sec.ini";
                XhFileUtils.createFile(str2);
                XhFileUtils.writeFile(str2, ((Object) version) + "$$" + ((Object) key));
                return;
            }
        }
        Log.d("writeToFile", "version || key == null");
    }
}
