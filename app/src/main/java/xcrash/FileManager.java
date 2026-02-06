package xcrash;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

class FileManager {
    private static final FileManager instance = new FileManager();
    private String placeholderPrefix = "placeholder";
    private String placeholderCleanSuffix = ".clean.xcrash";
    private String placeholderDirtySuffix = ".dirty.xcrash";
    private String logDir = null;
    private int javaLogCountMax = 0;
    private int nativeLogCountMax = 0;
    private int anrLogCountMax = 0;
    private int traceLogCountMax = 1;
    private int placeholderCountMax = 0;
    private int placeholderSizeKb = 0;
    private int delayMs = 0;
    private AtomicInteger unique = new AtomicInteger();

    private FileManager() {
    }

    private boolean cleanTheDirtyFile(File file) throws Throwable {
        boolean zRenameTo;
        Locale locale;
        Object[] objArr;
        FileOutputStream fileOutputStream = null;
        try {
            try {
                byte[] bArr = new byte[1024];
                Arrays.fill(bArr, (byte) 0);
                long j = this.placeholderSizeKb;
                long length = file.length();
                if (length > this.placeholderSizeKb * 1024) {
                    j = length / 1024;
                    if (length % 1024 != 0) {
                        j++;
                    }
                }
                FileOutputStream fileOutputStream2 = new FileOutputStream(file.getAbsoluteFile(), false);
                int i = 0;
                while (i < j) {
                    i++;
                    if (i == j) {
                        try {
                            try {
                                if (length % 1024 != 0) {
                                    fileOutputStream2.write(bArr, 0, (int) (length % 1024));
                                }
                            } catch (Exception e) {
                                e = e;
                                fileOutputStream = fileOutputStream2;
                                XCrash.getLogger().e("xcrash", "FileManager cleanTheDirtyFile failed", e);
                                if (fileOutputStream != null) {
                                }
                                zRenameTo = false;
                                if (!zRenameTo) {
                                }
                                return zRenameTo;
                            }
                        } catch (Throwable th) {
                            th = th;
                            fileOutputStream = fileOutputStream2;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (Exception unused) {
                                }
                            }
                            throw th;
                        }
                    }
                    fileOutputStream2.write(bArr);
                }
                try {
                    fileOutputStream2.flush();
                    locale = Locale.US;
                    objArr = new Object[4];
                    try {
                        objArr[0] = this.logDir;
                        objArr[1] = this.placeholderPrefix;
                        objArr[2] = Long.valueOf((new Date().getTime() * 1000) + getNextUnique());
                        objArr[3] = this.placeholderCleanSuffix;
                    } catch (Exception e2) {
                        e = e2;
                    }
                } catch (Exception e3) {
                    e = e3;
                }
                try {
                    zRenameTo = file.renameTo(new File(String.format(locale, "%s/%s_%020d%s", objArr)));
                    try {
                        fileOutputStream2.close();
                    } catch (Exception unused2) {
                    }
                } catch (Exception e4) {
                    e = e4;
                    fileOutputStream = fileOutputStream2;
                    XCrash.getLogger().e("xcrash", "FileManager cleanTheDirtyFile failed", e);
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception unused3) {
                        }
                    }
                    zRenameTo = false;
                    if (!zRenameTo) {
                    }
                    return zRenameTo;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e5) {
            e = e5;
        }
        if (!zRenameTo) {
            try {
                file.delete();
            } catch (Exception unused4) {
            }
        }
        return zRenameTo;
    }

    private void doMaintain() {
        if (Util.checkAndCreateDir(this.logDir)) {
            File file = new File(this.logDir);
            try {
                doMaintainTombstone(file);
            } catch (Exception e) {
                XCrash.getLogger().e("xcrash", "FileManager doMaintainTombstone failed", e);
            }
            try {
                doMaintainPlaceholder(file);
            } catch (Exception e2) {
                XCrash.getLogger().e("xcrash", "FileManager doMaintainPlaceholder failed", e2);
            }
        }
    }

    private void doMaintainPlaceholder(File file) {
        File[] fileArrListFiles;
        int i;
        File[] fileArrListFiles2 = file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File file2, String str) {
                StringBuilder sb = new StringBuilder();
                sb.append(FileManager.this.placeholderPrefix);
                sb.append("_");
                return str.startsWith(sb.toString()) && str.endsWith(FileManager.this.placeholderCleanSuffix);
            }
        });
        if (fileArrListFiles2 == null || (fileArrListFiles = file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File file2, String str) {
                StringBuilder sb = new StringBuilder();
                sb.append(FileManager.this.placeholderPrefix);
                sb.append("_");
                return str.startsWith(sb.toString()) && str.endsWith(FileManager.this.placeholderDirtySuffix);
            }
        })) == null) {
            return;
        }
        int length = fileArrListFiles2.length;
        int length2 = fileArrListFiles.length;
        char c = 0;
        int i2 = 0;
        while (length < this.placeholderCountMax) {
            if (length2 > 0) {
                if (cleanTheDirtyFile(fileArrListFiles[length2 - 1])) {
                    length++;
                }
                length2--;
            } else {
                try {
                    Locale locale = Locale.US;
                    Object[] objArr = new Object[4];
                    objArr[c] = this.logDir;
                    objArr[1] = this.placeholderPrefix;
                    i = i2;
                    try {
                        objArr[2] = Long.valueOf((new Date().getTime() * 1000) + getNextUnique());
                        objArr[3] = this.placeholderDirtySuffix;
                        File file2 = new File(String.format(locale, "%s/%s_%020d%s", objArr));
                        if (file2.createNewFile() && cleanTheDirtyFile(file2)) {
                            length++;
                        }
                    } catch (Exception unused) {
                    }
                } catch (Exception unused2) {
                }
                i2 = i + 1;
                if (i2 <= this.placeholderCountMax * 2) {
                    break;
                } else {
                    c = 0;
                }
            }
            i = i2;
            i2 = i + 1;
            if (i2 <= this.placeholderCountMax * 2) {
            }
        }
        if (i2 > 0) {
            fileArrListFiles2 = file.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File file3, String str) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(FileManager.this.placeholderPrefix);
                    sb.append("_");
                    return str.startsWith(sb.toString()) && str.endsWith(FileManager.this.placeholderCleanSuffix);
                }
            });
            fileArrListFiles = file.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File file3, String str) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(FileManager.this.placeholderPrefix);
                    sb.append("_");
                    return str.startsWith(sb.toString()) && str.endsWith(FileManager.this.placeholderDirtySuffix);
                }
            });
        }
        if (fileArrListFiles2 != null && fileArrListFiles2.length > this.placeholderCountMax) {
            for (int i3 = 0; i3 < fileArrListFiles2.length - this.placeholderCountMax; i3++) {
                fileArrListFiles2[i3].delete();
            }
        }
        if (fileArrListFiles != null) {
            for (File file3 : fileArrListFiles) {
                file3.delete();
            }
        }
    }

    private void doMaintainTombstone(File file) {
        doMaintainTombstoneType(file, ".native.xcrash", this.nativeLogCountMax);
        doMaintainTombstoneType(file, ".java.xcrash", this.javaLogCountMax);
        doMaintainTombstoneType(file, ".anr.xcrash", this.anrLogCountMax);
        doMaintainTombstoneType(file, ".trace.xcrash", this.traceLogCountMax);
    }

    private boolean doMaintainTombstoneType(File file, final String str, int i) {
        File[] fileArrListFiles = file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File file2, String str2) {
                return str2.startsWith("tombstone_") && str2.endsWith(str);
            }
        });
        boolean z = true;
        if (fileArrListFiles != null && fileArrListFiles.length > i) {
            if (i > 0) {
                Arrays.sort(fileArrListFiles, new Comparator<File>() {
                    @Override
                    public int compare(File file2, File file3) {
                        return file2.getName().compareTo(file3.getName());
                    }
                });
            }
            for (int i2 = 0; i2 < fileArrListFiles.length - i; i2++) {
                if (!recycleLogFile(fileArrListFiles[i2])) {
                    z = false;
                }
            }
        }
        return z;
    }

    static FileManager getInstance() {
        return instance;
    }

    private int getNextUnique() {
        int iIncrementAndGet = this.unique.incrementAndGet();
        if (iIncrementAndGet >= 999) {
            this.unique.set(0);
        }
        return iIncrementAndGet;
    }

    boolean appendText(String str, String str2) throws Throwable {
        RandomAccessFile randomAccessFile = null;
        try {
            try {
                RandomAccessFile randomAccessFile2 = new RandomAccessFile(str, "rws");
                try {
                    long j = 0;
                    if (randomAccessFile2.length() > 0) {
                        MappedByteBuffer map = randomAccessFile2.getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, randomAccessFile2.length());
                        long length = randomAccessFile2.length();
                        while (length > 0 && map.get(((int) length) - 1) == 0) {
                            length--;
                        }
                        j = length;
                    }
                    randomAccessFile2.seek(j);
                    randomAccessFile2.write(str2.getBytes("UTF-8"));
                    try {
                        randomAccessFile2.close();
                    } catch (Exception unused) {
                    }
                    return true;
                } catch (Exception e) {
                    e = e;
                    randomAccessFile = randomAccessFile2;
                    XCrash.getLogger().e("xcrash", "FileManager appendText failed", e);
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (Exception unused2) {
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    randomAccessFile = randomAccessFile2;
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (Exception unused3) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    File createLogFile(String str) {
        String str2 = this.logDir;
        if (str2 == null || !Util.checkAndCreateDir(str2)) {
            return null;
        }
        File file = new File(str);
        File[] fileArrListFiles = new File(this.logDir).listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File file2, String str3) {
                StringBuilder sb = new StringBuilder();
                sb.append(FileManager.this.placeholderPrefix);
                sb.append("_");
                return str3.startsWith(sb.toString()) && str3.endsWith(FileManager.this.placeholderCleanSuffix);
            }
        });
        if (fileArrListFiles != null) {
            for (int length = fileArrListFiles.length; length > 0; length--) {
                File file2 = fileArrListFiles[length - 1];
                try {
                } catch (Exception e) {
                    XCrash.getLogger().e("xcrash", "FileManager createLogFile by renameTo failed", e);
                }
                if (file2.renameTo(file)) {
                    return file;
                }
                file2.delete();
            }
        }
        try {
            if (file.createNewFile()) {
                return file;
            }
            XCrash.getLogger().e("xcrash", "FileManager createLogFile by createNewFile failed, file already exists");
            return null;
        } catch (Exception e2) {
            XCrash.getLogger().e("xcrash", "FileManager createLogFile by createNewFile failed", e2);
            return null;
        }
    }

    void initialize(String str, int i, int i2, int i3, int i4, int i5, int i6) {
        File[] fileArrListFiles;
        this.logDir = str;
        this.javaLogCountMax = i;
        this.nativeLogCountMax = i2;
        this.anrLogCountMax = i3;
        this.placeholderCountMax = i4;
        this.placeholderSizeKb = i5;
        this.delayMs = i6;
        try {
            File file = new File(str);
            if (file.exists() && file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
                int i7 = 0;
                int i8 = 0;
                int i9 = 0;
                int i10 = 0;
                int i11 = 0;
                int i12 = 0;
                for (File file2 : fileArrListFiles) {
                    if (file2.isFile()) {
                        String name = file2.getName();
                        if (!name.startsWith("tombstone_")) {
                            if (name.startsWith(this.placeholderPrefix + "_")) {
                                if (name.endsWith(this.placeholderCleanSuffix)) {
                                    i11++;
                                } else if (name.endsWith(this.placeholderDirtySuffix)) {
                                    i12++;
                                }
                            }
                        } else if (name.endsWith(".java.xcrash")) {
                            i7++;
                        } else if (name.endsWith(".native.xcrash")) {
                            i8++;
                        } else if (name.endsWith(".anr.xcrash")) {
                            i9++;
                        } else if (name.endsWith(".trace.xcrash")) {
                            i10++;
                        }
                    }
                }
                if (i7 <= this.javaLogCountMax && i8 <= this.nativeLogCountMax && i9 <= this.anrLogCountMax && i10 <= this.traceLogCountMax && i11 == this.placeholderCountMax && i12 == 0) {
                    this.delayMs = -1;
                    return;
                }
                if (i7 <= this.javaLogCountMax + 10 && i8 <= this.nativeLogCountMax + 10 && i9 <= this.anrLogCountMax + 10 && i10 <= this.traceLogCountMax + 10 && i11 <= this.placeholderCountMax + 10 && i12 <= 10) {
                    if (i7 > this.javaLogCountMax || i8 > this.nativeLogCountMax || i9 > this.anrLogCountMax || i10 > this.traceLogCountMax || i11 > this.placeholderCountMax || i12 > 0) {
                        this.delayMs = 0;
                        return;
                    }
                    return;
                }
                doMaintain();
                this.delayMs = -1;
            }
        } catch (Exception e) {
            XCrash.getLogger().e("xcrash", "FileManager init failed", e);
        }
    }

    void maintain() {
        int i;
        if (this.logDir == null || (i = this.delayMs) < 0) {
            return;
        }
        try {
            if (i == 0) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        FileManager.this.doMaintain();
                    }
                }, "xcrash_file_mgr").start();
            } else {
                new Timer("xcrash_file_mgr").schedule(new TimerTask() {
                    @Override
                    public void run() {
                        FileManager.this.doMaintain();
                    }
                }, this.delayMs);
            }
        } catch (Exception e) {
            XCrash.getLogger().e("xcrash", "FileManager maintain start failed", e);
        }
    }

    boolean maintainAnr() {
        if (!Util.checkAndCreateDir(this.logDir)) {
            return false;
        }
        try {
            return doMaintainTombstoneType(new File(this.logDir), ".anr.xcrash", this.anrLogCountMax);
        } catch (Exception e) {
            XCrash.getLogger().e("xcrash", "FileManager maintainAnr failed", e);
            return false;
        }
    }

    boolean recycleLogFile(File file) {
        if (file == null) {
            return false;
        }
        if (this.logDir == null || this.placeholderCountMax <= 0) {
            try {
                return file.delete();
            } catch (Exception unused) {
                return false;
            }
        }
        try {
            File[] fileArrListFiles = new File(this.logDir).listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File file2, String str) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(FileManager.this.placeholderPrefix);
                    sb.append("_");
                    return str.startsWith(sb.toString()) && str.endsWith(FileManager.this.placeholderCleanSuffix);
                }
            });
            if (fileArrListFiles != null && fileArrListFiles.length >= this.placeholderCountMax) {
                try {
                    return file.delete();
                } catch (Exception unused2) {
                    return false;
                }
            }
            File file2 = new File(String.format(Locale.US, "%s/%s_%020d%s", this.logDir, this.placeholderPrefix, Long.valueOf((new Date().getTime() * 1000) + getNextUnique()), this.placeholderDirtySuffix));
            if (file.renameTo(file2)) {
                return cleanTheDirtyFile(file2);
            }
            try {
                return file.delete();
            } catch (Exception unused3) {
                return false;
            }
        } catch (Exception e) {
            XCrash.getLogger().e("xcrash", "FileManager recycleLogFile failed", e);
            try {
                return file.delete();
            } catch (Exception unused4) {
                return false;
            }
        }
    }
}
