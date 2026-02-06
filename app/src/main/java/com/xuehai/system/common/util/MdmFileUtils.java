package com.xuehai.system.common.util;

import android.os.Environment;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0006J\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0006J\u0018\u0010\r\u001a\u00020\u000e2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0011\u001a\u00020\u0012¨\u0006\u0013"}, d2 = {"Lcom/xuehai/system/common/util/MdmFileUtils;", "", "()V", "createFileNotExists", "", "file", "Ljava/io/File;", "createParentDir", "deleteFile", "destFile", "getSDCardRoot", "", "reCreateFile", "writeFileToStream", "", "inFile", "outFile", "outputStream", "Ljava/io/OutputStream;", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MdmFileUtils {
    public static final MdmFileUtils INSTANCE = new MdmFileUtils();

    private MdmFileUtils() {
    }

    public final boolean createFileNotExists(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        if (file.exists()) {
            return true;
        }
        if (!createParentDir(file)) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (Exception unused) {
            return false;
        }
    }

    public final boolean createParentDir(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        File parentFile = file.getParentFile();
        if (parentFile == null || !parentFile.exists()) {
            return parentFile.mkdirs();
        }
        return true;
    }

    public final boolean deleteFile(File destFile) {
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

    public final String getSDCardRoot() {
        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "getExternalStorageDirectory().absolutePath");
        return absolutePath;
    }

    public final boolean reCreateFile(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        if (file.exists()) {
            deleteFile(file);
        }
        return createFileNotExists(file);
    }

    public final void writeFileToStream(File inFile, File outFile) throws Throwable {
        Intrinsics.checkNotNullParameter(inFile, "inFile");
        Intrinsics.checkNotNullParameter(outFile, "outFile");
        reCreateFile(outFile);
        writeFileToStream(inFile, new FileOutputStream(outFile, false));
    }

    public final void writeFileToStream(File file, OutputStream outputStream) throws Throwable {
        Intrinsics.checkNotNullParameter(outputStream, "outputStream");
        boolean z = true;
        FileInputStream fileInputStream = null;
        try {
            try {
                if (file != null) {
                    try {
                        if (file.exists()) {
                        }
                        if (z) {
                            FileInputStream fileInputStream2 = new FileInputStream(file);
                            try {
                                byte[] bArr = new byte[1024];
                                for (int i = fileInputStream2.read(bArr); i != -1; i = fileInputStream2.read(bArr)) {
                                    outputStream.write(bArr, 0, i);
                                }
                                fileInputStream2.close();
                                outputStream.close();
                                fileInputStream = fileInputStream2;
                            } catch (Exception e) {
                                e = e;
                                fileInputStream = fileInputStream2;
                                e.printStackTrace();
                                if (fileInputStream != null) {
                                }
                                outputStream.close();
                                return;
                            } catch (Throwable th) {
                                th = th;
                                fileInputStream = fileInputStream2;
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                        throw th;
                                    }
                                }
                                outputStream.close();
                                throw th;
                            }
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        outputStream.close();
                    } catch (Exception e3) {
                        e = e3;
                        e.printStackTrace();
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        outputStream.close();
                        return;
                    }
                }
                z = false;
                if (z) {
                }
                if (fileInputStream != null) {
                }
                outputStream.close();
            } catch (Exception e4) {
                e4.printStackTrace();
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
