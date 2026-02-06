package com.tencent.tinker.loader.shareutil;

import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;

public class ShareFileLockHelper implements Closeable {
    public static final int LOCK_WAIT_EACH_TIME = 10;
    public static final int MAX_LOCK_ATTEMPTS = 3;
    private static final String TAG = "Tinker.FileLockHelper";
    private final FileLock fileLock;
    private final FileOutputStream outputStream;

    private ShareFileLockHelper(File file) throws InterruptedException, IOException {
        this.outputStream = new FileOutputStream(file);
        FileLock fileLockLock = null;
        Exception e = null;
        int i = 0;
        while (i < 3) {
            i++;
            try {
                fileLockLock = this.outputStream.getChannel().lock();
                if (fileLockLock != null) {
                    break;
                }
            } catch (Exception e2) {
                e = e2;
                Log.e("Tinker.FileLockHelper", "getInfoLock Thread failed time:10");
            }
            try {
                Thread.sleep(10L);
            } catch (Exception e3) {
                Log.e("Tinker.FileLockHelper", "getInfoLock Thread sleep exception", e3);
            }
        }
        if (fileLockLock != null) {
            this.fileLock = fileLockLock;
            return;
        }
        throw new IOException("Tinker Exception:FileLockHelper lock file failed: " + file.getAbsolutePath(), e);
    }

    public static ShareFileLockHelper getFileLock(File file) throws IOException {
        return new ShareFileLockHelper(file);
    }

    @Override
    public void close() throws IOException {
        try {
            if (this.fileLock != null) {
                this.fileLock.release();
            }
        } finally {
            FileOutputStream fileOutputStream = this.outputStream;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }
    }
}
