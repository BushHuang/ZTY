package com.xuehai.launcher.common.util;

import com.xuehai.launcher.common.log.MyLog;
import com.zaze.utils.FileUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003J\u0010\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0003R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/xuehai/launcher/common/util/KeepCode;", "", "dir", "", "suffix", "(Ljava/lang/String;Ljava/lang/String;)V", "getCode", "updateCode", "", "code", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class KeepCode {
    private final String dir;
    private final String suffix;

    public KeepCode(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "dir");
        Intrinsics.checkNotNullParameter(str2, "suffix");
        this.dir = str;
        this.suffix = str2;
    }

    public final String getCode() {
        ArrayList<File> arrayListSearchFileBySuffix = FileUtil.searchFileBySuffix(this.dir, this.suffix, false);
        ArrayList<File> arrayList = arrayListSearchFileBySuffix;
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        try {
            ArrayList<File> arrayList2 = arrayListSearchFileBySuffix;
            if (arrayList2.size() > 1) {
                CollectionsKt.sortWith(arrayList2, new Comparator() {
                    @Override
                    public final int compare(T t, T t2) {
                        return ComparisonsKt.compareValues(Long.valueOf(((File) t2).lastModified()), Long.valueOf(((File) t).lastModified()));
                    }
                });
            }
            File file = arrayListSearchFileBySuffix.get(0);
            Intrinsics.checkNotNullExpressionValue(file, "list[0]");
            return LauncherAesUtil.decrypt(FilesKt.getNameWithoutExtension(file));
        } catch (Exception e) {
            MyLog.w("Error[MDM]", "KeepCode get " + getClass().getName() + " error", e);
            return null;
        }
    }

    public final boolean updateCode(String code) {
        String str = code;
        if (str == null || str.length() == 0) {
            return false;
        }
        try {
            return FileUtil.createFileNotExists(this.dir + '/' + LauncherAesUtil.encrypt(code) + '.' + this.suffix);
        } catch (Exception e) {
            MyLog.w("Error[MDM]", "KeepCode update " + getClass().getName() + " error", e);
            return false;
        }
    }
}
