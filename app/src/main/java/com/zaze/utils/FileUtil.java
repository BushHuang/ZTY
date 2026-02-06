package com.zaze.utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.zaze.utils.ZCommand;
import com.zaze.utils.log.ZLog;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000f\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0007J \u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H\u0007J\u0010\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u0011H\u0007J\u0010\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u0011H\u0007J\u0010\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u0017H\u0007J\u0010\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u0011H\u0007J\u0010\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u0017H\u0007J\u0010\u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u0011H\u0007J\u0010\u0010\u001f\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u0017H\u0007J\u0010\u0010!\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u0011H\u0007J\u0010\u0010!\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u0017H\u0007J\u0012\u0010\"\u001a\u00020\u00062\b\u0010\u001c\u001a\u0004\u0018\u00010\u0017H\u0007J\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0007J\u0010\u0010'\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0007J\u0010\u0010(\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0007J\u0010\u0010)\u001a\u00020$2\u0006\u0010\u001a\u001a\u00020\u0011H\u0007J\b\u0010*\u001a\u00020\u0017H\u0007J\u0010\u0010+\u001a\u00020$2\u0006\u0010\u001a\u001a\u00020\u0011H\u0007J\u0012\u0010,\u001a\u00020\u00062\b\u0010\u001c\u001a\u0004\u0018\u00010\u0017H\u0007J\u0012\u0010-\u001a\u00020\u00062\b\u0010\u001c\u001a\u0004\u0018\u00010\u0017H\u0007J\u0010\u0010.\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u0017H\u0007J\u0010\u00100\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u0017H\u0007J\b\u00101\u001a\u00020\u0006H\u0007J\u0018\u00102\u001a\u00020\u00062\u0006\u00103\u001a\u00020\u00172\u0006\u00104\u001a\u00020\u0017H\u0007J\u000e\u00105\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u0011J\u0010\u00105\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u0017H\u0007J\u0010\u00106\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u0011H\u0007J\u0010\u00106\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u0017H\u0007J\u0010\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:H\u0007J\u0012\u0010;\u001a\u0004\u0018\u00010<2\u0006\u00109\u001a\u00020:H\u0007J\u0010\u0010=\u001a\u0002082\u0006\u0010\u001c\u001a\u00020\u0017H\u0007J\u0010\u0010>\u001a\u0002082\u0006\u0010?\u001a\u00020@H\u0007J\b\u0010A\u001a\u00020\u000fH\u0002J\b\u0010B\u001a\u00020\u000fH\u0002J\u0018\u0010C\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u00172\u0006\u0010D\u001a\u00020\u0017H\u0007J\u0018\u0010E\u001a\u00020\u00062\u0006\u00103\u001a\u00020\u00172\u0006\u00104\u001a\u00020\u0017H\u0003J(\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00110G2\u0006\u0010H\u001a\u00020\u00112\u0006\u0010I\u001a\u00020\u00172\b\b\u0002\u0010J\u001a\u00020\u0006H\u0007J(\u0010K\u001a\b\u0012\u0004\u0012\u00020\u00110G2\u0006\u0010L\u001a\u00020\u00112\u0006\u0010M\u001a\u00020\u00172\b\b\u0002\u0010J\u001a\u00020\u0006H\u0007J(\u0010K\u001a\b\u0012\u0004\u0012\u00020\u00110G2\u0006\u0010N\u001a\u00020\u00172\u0006\u0010M\u001a\u00020\u00172\b\b\u0002\u0010J\u001a\u00020\u0006H\u0007J\b\u0010O\u001a\u00020\u000fH\u0002J\"\u0010P\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u00112\u0006\u00109\u001a\u00020:2\b\b\u0002\u0010Q\u001a\u00020\u0006H\u0007J \u0010P\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u00109\u001a\u00020:2\u0006\u0010R\u001a\u00020$H\u0007J\"\u0010P\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u00112\u0006\u0010S\u001a\u00020\u00172\b\b\u0002\u0010Q\u001a\u00020\u0006H\u0007J \u0010P\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010S\u001a\u00020\u00172\u0006\u0010R\u001a\u00020$H\u0007J\"\u0010P\u001a\u00020\u00062\u0006\u0010T\u001a\u00020\u00172\u0006\u0010S\u001a\u00020\u00172\b\b\u0002\u0010Q\u001a\u00020\u0006H\u0007J\b\u0010U\u001a\u00020\u000fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\n¨\u0006V"}, d2 = {"Lcom/zaze/utils/FileUtil;", "", "()V", "lock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "needLock", "", "getNeedLock", "()Z", "setNeedLock", "(Z)V", "showLog", "getShowLog", "setShowLog", "copy", "", "from", "Ljava/io/File;", "to", "copyAssetsFileToSdcard", "context", "Landroid/content/Context;", "assetsFile", "", "outPath", "createDirNotExists", "file", "createFileNotExists", "filePath", "createParentDir", "savePath", "deleteFile", "destFile", "deleteFileByCmd", "exists", "getAvailableBlocks", "", "statFs", "Landroid/os/StatFs;", "getBlockCount", "getBlockSize", "getFreeSpace", "getSDCardRoot", "getTotalSpace", "isCanRead", "isCanWrite", "isDirectory", "path", "isFile", "isSdcardEnable", "move", "source", "target", "reCreateDir", "reCreateFile", "readByBytes", "Ljava/lang/StringBuffer;", "inputStream", "Ljava/io/InputStream;", "readBytes", "", "readFromFile", "readLine", "reader", "Ljava/io/Reader;", "readLock", "readUnlock", "rename", "newFileName", "renameFile", "searchFileAndDir", "Ljava/util/ArrayList;", "dir", "fileName", "isDeep", "searchFileBySuffix", "dirFile", "suffix", "dirPath", "writeLock", "writeToFile", "append", "maxSize", "dataStr", "destFilePath", "writeUnlock", "util_release"}, k = 1, mv = {1, 4, 1})
public final class FileUtil {
    public static final FileUtil INSTANCE = new FileUtil();
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static boolean needLock;
    private static boolean showLog;

    private FileUtil() {
    }

    @JvmStatic
    public static final void copy(File from, File to) {
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(to, "to");
        writeToFile$default(to, (InputStream) new FileInputStream(from), false, 4, (Object) null);
    }

    @JvmStatic
    public static final void copyAssetsFileToSdcard(Context context, String assetsFile, String outPath) throws Throwable {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(assetsFile, "assetsFile");
        Intrinsics.checkNotNullParameter(outPath, "outPath");
        ZLog.i("Debug[ZZ]", "copyAssetsFileToSdcard " + outPath, new Object[0]);
        reCreateFile(outPath);
        File file = new File(outPath);
        InputStream inputStreamOpen = context.getAssets().open(assetsFile);
        Intrinsics.checkNotNullExpressionValue(inputStreamOpen, "context.assets.open(assetsFile)");
        writeToFile(file, inputStreamOpen, false);
    }

    @JvmStatic
    public static final boolean createDirNotExists(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        return file.exists() ? file.isDirectory() : file.mkdirs();
    }

    @JvmStatic
    public static final boolean createFileNotExists(File file) throws IOException {
        boolean zCreateNewFile;
        Intrinsics.checkNotNullParameter(file, "file");
        if (file.exists()) {
            zCreateNewFile = true;
        } else if (createParentDir(file)) {
            try {
                zCreateNewFile = file.createNewFile();
            } catch (Exception unused) {
            }
        } else {
            zCreateNewFile = false;
        }
        if (showLog) {
            ZLog.v("File[ZZ]", "createFileNotExists filePath : " + file.getAbsolutePath(), new Object[0]);
            ZLog.v("File[ZZ]", "createFileNotExists code : " + zCreateNewFile, new Object[0]);
        }
        return zCreateNewFile;
    }

    @JvmStatic
    public static final boolean createFileNotExists(String filePath) {
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        return createFileNotExists(new File(filePath));
    }

    @JvmStatic
    public static final boolean createParentDir(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        File parentFile = file.getParentFile();
        if (parentFile == null || !parentFile.exists()) {
            return parentFile.mkdirs();
        }
        return true;
    }

    @JvmStatic
    public static final boolean createParentDir(String savePath) {
        Intrinsics.checkNotNullParameter(savePath, "savePath");
        return createParentDir(new File(savePath));
    }

    @JvmStatic
    public static final void deleteFile(String filePath) {
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        deleteFile(new File(filePath));
    }

    @JvmStatic
    public static final boolean deleteFile(File destFile) {
        File[] fileArrListFiles;
        Intrinsics.checkNotNullParameter(destFile, "destFile");
        if (TextUtils.equals(getSDCardRoot(), destFile.getAbsolutePath())) {
            return false;
        }
        if (destFile.isFile()) {
            return destFile.delete();
        }
        if (destFile.isDirectory() && (fileArrListFiles = destFile.listFiles()) != null) {
            if (!(fileArrListFiles.length == 0)) {
                for (File file : fileArrListFiles) {
                    Intrinsics.checkNotNullExpressionValue(file, "file");
                    deleteFile(file);
                }
            }
        }
        return destFile.delete();
    }

    @JvmStatic
    public static final boolean deleteFileByCmd(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        String absolutePath = file.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "file.absolutePath");
        return deleteFileByCmd(absolutePath);
    }

    @JvmStatic
    public static final boolean deleteFileByCmd(String filePath) {
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        ZCommand.CommandResult commandResultExecCmdForRes = ZCommand.execCmdForRes("rm -r " + filePath);
        Intrinsics.checkNotNullExpressionValue(commandResultExecCmdForRes, "ZCommand.execCmdForRes(\"rm -r $filePath\")");
        return commandResultExecCmdForRes.isSuccess();
    }

    @JvmStatic
    public static final boolean exists(String filePath) {
        String str = filePath;
        if (str == null || str.length() == 0) {
            return false;
        }
        return new File(filePath).exists();
    }

    @JvmStatic
    public static final long getAvailableBlocks(StatFs statFs) {
        Intrinsics.checkNotNullParameter(statFs, "statFs");
        return Build.VERSION.SDK_INT >= 18 ? statFs.getAvailableBlocksLong() : statFs.getAvailableBlocks();
    }

    @JvmStatic
    public static final long getBlockCount(StatFs statFs) {
        Intrinsics.checkNotNullParameter(statFs, "statFs");
        return Build.VERSION.SDK_INT >= 18 ? statFs.getBlockCountLong() : statFs.getBlockCount();
    }

    @JvmStatic
    public static final long getBlockSize(StatFs statFs) {
        Intrinsics.checkNotNullParameter(statFs, "statFs");
        return Build.VERSION.SDK_INT >= 18 ? statFs.getBlockSizeLong() : statFs.getBlockSize();
    }

    @JvmStatic
    public static final long getFreeSpace(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        StatFs statFs = new StatFs(file.getPath());
        return getBlockSize(statFs) * getAvailableBlocks(statFs);
    }

    @JvmStatic
    public static final String getSDCardRoot() {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        Intrinsics.checkNotNullExpressionValue(externalStorageDirectory, "Environment.getExternalStorageDirectory()");
        String absolutePath = externalStorageDirectory.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "Environment.getExternalS…eDirectory().absolutePath");
        return absolutePath;
    }

    @JvmStatic
    public static final long getTotalSpace(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        ZLog.i("Debug[ZZ]", "getTotalSpace : " + file.getPath(), new Object[0]);
        StatFs statFs = new StatFs(file.getPath());
        return getBlockSize(statFs) * getBlockCount(statFs);
    }

    @JvmStatic
    public static final boolean isCanRead(String filePath) {
        String str = filePath;
        return !(str == null || str.length() == 0) && exists(filePath) && new File(filePath).canRead();
    }

    @JvmStatic
    public static final boolean isCanWrite(String filePath) {
        String str = filePath;
        return !(str == null || str.length() == 0) && exists(filePath) && new File(filePath).canWrite();
    }

    @JvmStatic
    public static final boolean isDirectory(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        return new File(path).isDirectory();
    }

    @JvmStatic
    public static final boolean isFile(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        return new File(path).isFile();
    }

    @JvmStatic
    public static final boolean isSdcardEnable() {
        return Intrinsics.areEqual(Environment.getExternalStorageState(), "mounted");
    }

    @JvmStatic
    public static final boolean move(String source, String target) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(target, "target");
        return renameFile(source, target);
    }

    @JvmStatic
    public static final boolean reCreateDir(String filePath) {
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        return INSTANCE.reCreateDir(new File(filePath));
    }

    @JvmStatic
    public static final boolean reCreateFile(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        if (file.exists()) {
            deleteFile(file);
        }
        return createFileNotExists(file);
    }

    @JvmStatic
    public static final boolean reCreateFile(String filePath) {
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        return reCreateFile(new File(filePath));
    }

    @JvmStatic
    public static final StringBuffer readByBytes(InputStream inputStream) throws IOException {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        INSTANCE.readLock();
        StringBuffer stringBuffer = new StringBuffer();
        try {
            try {
                byte[] bArr = new byte[4096];
                int i = inputStream.read(bArr);
                while (i != -1) {
                    stringBuffer.append(new String(bArr, 0, i, Charsets.UTF_8));
                    i = inputStream.read(bArr);
                }
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    e = e2;
                    e.printStackTrace();
                    INSTANCE.readUnlock();
                    return stringBuffer;
                }
            }
            try {
                inputStream.close();
            } catch (IOException e3) {
                e = e3;
                e.printStackTrace();
                INSTANCE.readUnlock();
                return stringBuffer;
            }
            INSTANCE.readUnlock();
            return stringBuffer;
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            INSTANCE.readUnlock();
            throw th;
        }
    }

    @JvmStatic
    public static final byte[] readBytes(InputStream inputStream) throws IOException {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        INSTANCE.readLock();
        try {
            try {
                byte[] bArr = new byte[inputStream.available()];
                inputStream.read(bArr);
                inputStream.close();
                return bArr;
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                INSTANCE.readUnlock();
                return null;
            }
        } finally {
            try {
                inputStream.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            INSTANCE.readUnlock();
        }
    }

    @JvmStatic
    public static final StringBuffer readFromFile(String filePath) {
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        File file = new File(filePath);
        StringBuffer stringBuffer = new StringBuffer();
        if (!exists(filePath)) {
            return stringBuffer;
        }
        try {
            return readByBytes(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return stringBuffer;
        }
    }

    @JvmStatic
    public static final StringBuffer readLine(Reader reader) throws Throwable {
        BufferedReader bufferedReader;
        Intrinsics.checkNotNullParameter(reader, "reader");
        INSTANCE.readLock();
        BufferedReader bufferedReader2 = (BufferedReader) null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            try {
                BufferedReader bufferedReader3 = new BufferedReader(reader);
                try {
                    String line = bufferedReader3.readLine();
                    bufferedReader = bufferedReader2;
                    while (line != null) {
                        stringBuffer.append(line);
                        StringBuilder sb = new StringBuilder();
                        sb.append('\n');
                        String line2 = bufferedReader3.readLine();
                        sb.append(line2);
                        line = sb.toString();
                        bufferedReader = line2;
                    }
                    bufferedReader3.close();
                } catch (Exception e) {
                    e = e;
                    bufferedReader2 = bufferedReader3;
                    e.printStackTrace();
                    bufferedReader2 = bufferedReader2;
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                            bufferedReader2 = bufferedReader2;
                        } catch (IOException e2) {
                            e = e2;
                            e.printStackTrace();
                            INSTANCE.readUnlock();
                            return stringBuffer;
                        }
                    }
                    INSTANCE.readUnlock();
                    return stringBuffer;
                } catch (Throwable th) {
                    th = th;
                    bufferedReader2 = bufferedReader3;
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    INSTANCE.readUnlock();
                    throw th;
                }
                try {
                    bufferedReader3.close();
                    bufferedReader2 = bufferedReader;
                } catch (IOException e4) {
                    e = e4;
                    e.printStackTrace();
                    INSTANCE.readUnlock();
                    return stringBuffer;
                }
            } catch (Exception e5) {
                e = e5;
            }
            INSTANCE.readUnlock();
            return stringBuffer;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private final void readLock() {
        if (needLock) {
            lock.readLock().lock();
        }
    }

    private final void readUnlock() {
        if (needLock) {
            lock.readLock().unlock();
        }
    }

    @JvmStatic
    public static final boolean rename(String filePath, String newFileName) {
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        Intrinsics.checkNotNullParameter(newFileName, "newFileName");
        File file = new File(filePath);
        if (!file.exists()) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        File parentFile = file.getParentFile();
        Intrinsics.checkNotNullExpressionValue(parentFile, "file.parentFile");
        sb.append(parentFile.getAbsolutePath());
        sb.append(File.separator);
        sb.append(newFileName);
        return renameFile(filePath, sb.toString());
    }

    @JvmStatic
    private static final boolean renameFile(String source, String target) {
        File file = new File(source);
        if (!file.exists()) {
            return false;
        }
        reCreateFile(target);
        createParentDir(target);
        return file.renameTo(new File(target));
    }

    @JvmStatic
    public static final ArrayList<File> searchFileAndDir(File file, String str) {
        return searchFileAndDir$default(file, str, false, 4, null);
    }

    @JvmStatic
    public static final ArrayList<File> searchFileAndDir(File dir, String fileName, boolean isDeep) {
        Intrinsics.checkNotNullParameter(dir, "dir");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        ArrayList<File> arrayList = new ArrayList<>();
        if (dir.exists() && dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                Intrinsics.checkNotNullExpressionValue(file, "childFile");
                if (Intrinsics.areEqual(file.getName(), fileName)) {
                    arrayList.add(file);
                }
                if (isDeep && file.isDirectory()) {
                    arrayList.addAll(searchFileAndDir(file, fileName, isDeep));
                }
            }
        }
        return arrayList;
    }

    public static ArrayList searchFileAndDir$default(File file, String str, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return searchFileAndDir(file, str, z);
    }

    @JvmStatic
    public static final ArrayList<File> searchFileBySuffix(File file, String str) {
        return searchFileBySuffix$default(file, str, false, 4, (Object) null);
    }

    @JvmStatic
    public static final ArrayList<File> searchFileBySuffix(File dirFile, String suffix, boolean isDeep) {
        boolean z;
        Intrinsics.checkNotNullParameter(dirFile, "dirFile");
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        ArrayList<File> arrayList = new ArrayList<>();
        if (dirFile.exists() && dirFile.isDirectory()) {
            File[] fileArrListFiles = dirFile.listFiles();
            if (fileArrListFiles == null) {
                z = true;
                if (!z) {
                    return arrayList;
                }
                for (File file : fileArrListFiles) {
                    Intrinsics.checkNotNullExpressionValue(file, "childFile");
                    if (file.isFile()) {
                        String name = file.getName();
                        Intrinsics.checkNotNullExpressionValue(name, "childFile.name");
                        if (StringsKt.endsWith(name, '.' + suffix, true)) {
                            arrayList.add(file);
                        }
                    }
                    if (isDeep && file.isDirectory()) {
                        arrayList.addAll(searchFileBySuffix(file, suffix, isDeep));
                    }
                }
            } else {
                if (!(fileArrListFiles.length == 0)) {
                    z = false;
                }
                if (!z) {
                }
            }
        }
        return arrayList;
    }

    @JvmStatic
    public static final ArrayList<File> searchFileBySuffix(String str, String str2) {
        return searchFileBySuffix$default(str, str2, false, 4, (Object) null);
    }

    @JvmStatic
    public static final ArrayList<File> searchFileBySuffix(String dirPath, String suffix, boolean isDeep) {
        Intrinsics.checkNotNullParameter(dirPath, "dirPath");
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        return searchFileBySuffix(new File(dirPath), suffix, isDeep);
    }

    public static ArrayList searchFileBySuffix$default(File file, String str, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return searchFileBySuffix(file, str, z);
    }

    public static ArrayList searchFileBySuffix$default(String str, String str2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return searchFileBySuffix(str, str2, z);
    }

    private final void writeLock() {
        if (needLock) {
            lock.writeLock().lock();
        }
    }

    @JvmStatic
    public static final boolean writeToFile(File file, InputStream inputStream) {
        return writeToFile$default(file, inputStream, false, 4, (Object) null);
    }

    @JvmStatic
    public static final boolean writeToFile(File file, InputStream inputStream, long maxSize) throws Throwable {
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        createFileNotExists(file);
        if (maxSize > 0 && file.length() > maxSize) {
            File file2 = new File(file.getAbsolutePath() + ".1");
            reCreateFile(file2);
            writeToFile(file2, (InputStream) new FileInputStream(file), true);
            deleteFile(file);
        }
        writeToFile(file, inputStream, true);
        return true;
    }

    @JvmStatic
    public static final boolean writeToFile(File destFile, InputStream inputStream, boolean append) throws Throwable {
        Intrinsics.checkNotNullParameter(destFile, "destFile");
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        INSTANCE.writeLock();
        OutputStream outputStream = (OutputStream) null;
        boolean z = false;
        try {
            try {
                createFileNotExists(destFile);
                FileOutputStream fileOutputStream = new FileOutputStream(destFile, append);
                try {
                    byte[] bArr = new byte[4096];
                    int i = inputStream.read(bArr);
                    while (i != -1) {
                        fileOutputStream.write(bArr, 0, i);
                        i = inputStream.read(bArr);
                    }
                    fileOutputStream.flush();
                    try {
                        inputStream.close();
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    z = true;
                } catch (IOException e2) {
                    e = e2;
                    outputStream = fileOutputStream;
                    e.printStackTrace();
                    try {
                        inputStream.close();
                        if (outputStream != null) {
                            outputStream.close();
                        }
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                    INSTANCE.writeUnlock();
                    return z;
                } catch (Throwable th) {
                    th = th;
                    outputStream = fileOutputStream;
                    try {
                        inputStream.close();
                        if (outputStream != null) {
                            outputStream.close();
                        }
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e5) {
            e = e5;
        }
        INSTANCE.writeUnlock();
        return z;
    }

    @JvmStatic
    public static final boolean writeToFile(File file, String str) {
        return writeToFile$default(file, str, false, 4, (Object) null);
    }

    @JvmStatic
    public static final boolean writeToFile(File file, String dataStr, long maxSize) {
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(dataStr, "dataStr");
        byte[] bytes = dataStr.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
        return writeToFile(file, new ByteArrayInputStream(bytes), maxSize);
    }

    @JvmStatic
    public static final boolean writeToFile(File destFile, String dataStr, boolean append) {
        Intrinsics.checkNotNullParameter(destFile, "destFile");
        Intrinsics.checkNotNullParameter(dataStr, "dataStr");
        byte[] bytes = dataStr.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
        return writeToFile(destFile, new ByteArrayInputStream(bytes), append);
    }

    @JvmStatic
    public static final boolean writeToFile(String str, String str2) {
        return writeToFile$default(str, str2, false, 4, (Object) null);
    }

    @JvmStatic
    public static final boolean writeToFile(String destFilePath, String dataStr, boolean append) {
        Intrinsics.checkNotNullParameter(destFilePath, "destFilePath");
        Intrinsics.checkNotNullParameter(dataStr, "dataStr");
        return writeToFile(new File(destFilePath), dataStr, append);
    }

    public static boolean writeToFile$default(File file, InputStream inputStream, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return writeToFile(file, inputStream, z);
    }

    public static boolean writeToFile$default(File file, String str, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return writeToFile(file, str, z);
    }

    public static boolean writeToFile$default(String str, String str2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return writeToFile(str, str2, z);
    }

    private final void writeUnlock() {
        if (needLock) {
            lock.writeLock().unlock();
        }
    }

    public final boolean getNeedLock() {
        return needLock;
    }

    public final boolean getShowLog() {
        return showLog;
    }

    public final boolean reCreateDir(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        if (file.exists()) {
            deleteFile(file);
        }
        return createDirNotExists(file);
    }

    public final void setNeedLock(boolean z) {
        needLock = z;
    }

    public final void setShowLog(boolean z) {
        showLog = z;
    }
}
