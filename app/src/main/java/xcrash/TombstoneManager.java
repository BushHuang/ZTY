package xcrash;

import android.text.TextUtils;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;

public class TombstoneManager {
    private TombstoneManager() {
    }

    public static boolean appendSection(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || str3 == null) {
            return false;
        }
        return FileManager.getInstance().appendText(str, "\n\n" + str2 + ":\n" + str3 + "\n\n");
    }

    public static boolean clearAllTombstones() {
        return clearTombstones(new String[]{".java.xcrash", ".native.xcrash", ".anr.xcrash"});
    }

    public static boolean clearAnrTombstones() {
        return clearTombstones(new String[]{".anr.xcrash"});
    }

    public static boolean clearJavaTombstones() {
        return clearTombstones(new String[]{".java.xcrash"});
    }

    public static boolean clearNativeTombstones() {
        return clearTombstones(new String[]{".native.xcrash"});
    }

    private static boolean clearTombstones(final String[] strArr) {
        File[] fileArrListFiles;
        String logDir = XCrash.getLogDir();
        if (logDir == null) {
            return false;
        }
        File file = new File(logDir);
        if (!file.exists() || !file.isDirectory() || (fileArrListFiles = file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File file2, String str) {
                if (!str.startsWith("tombstone_")) {
                    return false;
                }
                for (String str2 : strArr) {
                    if (str.endsWith(str2)) {
                        return true;
                    }
                }
                return false;
            }
        })) == null) {
            return false;
        }
        boolean z = true;
        for (File file2 : fileArrListFiles) {
            if (!FileManager.getInstance().recycleLogFile(file2)) {
                z = false;
            }
        }
        return z;
    }

    public static boolean deleteTombstone(File file) {
        return FileManager.getInstance().recycleLogFile(file);
    }

    public static boolean deleteTombstone(String str) {
        return FileManager.getInstance().recycleLogFile(new File(str));
    }

    public static File[] getAllTombstones() {
        return getTombstones(new String[]{".java.xcrash", ".native.xcrash", ".anr.xcrash"});
    }

    public static File[] getAnrTombstones() {
        return getTombstones(new String[]{".anr.xcrash"});
    }

    public static File[] getJavaTombstones() {
        return getTombstones(new String[]{".java.xcrash"});
    }

    public static File[] getNativeTombstones() {
        return getTombstones(new String[]{".native.xcrash"});
    }

    private static File[] getTombstones(final String[] strArr) {
        String logDir = XCrash.getLogDir();
        if (logDir == null) {
            return new File[0];
        }
        File file = new File(logDir);
        if (!file.exists() || !file.isDirectory()) {
            return new File[0];
        }
        File[] fileArrListFiles = file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File file2, String str) {
                if (!str.startsWith("tombstone_")) {
                    return false;
                }
                for (String str2 : strArr) {
                    if (str.endsWith(str2)) {
                        return true;
                    }
                }
                return false;
            }
        });
        if (fileArrListFiles == null) {
            return new File[0];
        }
        Arrays.sort(fileArrListFiles, new Comparator<File>() {
            @Override
            public int compare(File file2, File file3) {
                return file2.getName().compareTo(file3.getName());
            }
        });
        return fileArrListFiles;
    }

    public static boolean isAnr(File file) {
        return file.getName().endsWith(".anr.xcrash");
    }

    public static boolean isJavaCrash(File file) {
        return file.getName().endsWith(".java.xcrash");
    }

    public static boolean isNativeCrash(File file) {
        return file.getName().endsWith(".native.xcrash");
    }
}
