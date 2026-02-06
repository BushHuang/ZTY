package com.xuehai.system.mdm;

import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import com.xuehai.launcher.App;
import com.xuehai.launcher.common.log.MyLog;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J#\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\u0010\nJ\u0018\u0010\u000b\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f¨\u0006\u0010"}, d2 = {"Lcom/xuehai/system/mdm/WriteFileTask;", "", "()V", "writeDataFileToPipe", "", "fileName", "", "pipes", "", "Landroid/os/ParcelFileDescriptor;", "(Ljava/lang/String;[Landroid/os/ParcelFileDescriptor;)V", "writeFileToStream", "file", "Ljava/io/File;", "outputStream", "Ljava/io/OutputStream;", "app_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class WriteFileTask {
    public static final WriteFileTask INSTANCE = new WriteFileTask();

    private WriteFileTask() {
    }

    public final void writeDataFileToPipe(String fileName, ParcelFileDescriptor[] pipes) throws Throwable {
        Intrinsics.checkNotNullParameter(pipes, "pipes");
        MyLog.i("Debug[MDM]", "writeDataFileToPipe: " + fileName);
        File file = new File(Environment.getDataDirectory().getAbsolutePath() + "/data/" + App.INSTANCE.getInstance().getPackageName() + '/' + fileName);
        if (TextUtils.isEmpty(fileName)) {
            file = (File) null;
        } else if (!file.exists()) {
            file = new File(file.getAbsoluteFile() + ".bak");
        }
        writeFileToStream(file, new ParcelFileDescriptor.AutoCloseOutputStream(pipes[1]));
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
