package com.tencent.tinker.loader;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.tencent.tinker.loader.shareutil.ShareFileLockHelper;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import dalvik.system.DexFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public final class TinkerDexOptimizer {
    private static final String INTERPRET_LOCK_FILE_NAME = "interpret.lock";
    private static final String TAG = "Tinker.ParallelDex";

    private static class OptimizeWorker {
        private static String targetISA;
        private final ResultCallback callback;
        private final Context context;
        private final File dexFile;
        private final File optimizedDir;
        private final boolean useInterpretMode;

        OptimizeWorker(Context context, File file, File file2, boolean z, String str, ResultCallback resultCallback) {
            this.context = context;
            this.dexFile = file;
            this.optimizedDir = file2;
            this.useInterpretMode = z;
            this.callback = resultCallback;
            targetISA = str;
        }

        private void interpretDex2Oat(String str, String str2) throws IOException {
            File file = new File(str2);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }
            File file2 = new File(file.getParentFile(), "interpret.lock");
            ShareFileLockHelper fileLock = null;
            try {
                fileLock = ShareFileLockHelper.getFileLock(file2);
                ArrayList arrayList = new ArrayList();
                arrayList.add("dex2oat");
                if (Build.VERSION.SDK_INT >= 24) {
                    arrayList.add("--runtime-arg");
                    arrayList.add("-classpath");
                    arrayList.add("--runtime-arg");
                    arrayList.add("&");
                }
                arrayList.add("--dex-file=" + str);
                arrayList.add("--oat-file=" + str2);
                arrayList.add("--instruction-set=" + targetISA);
                if (Build.VERSION.SDK_INT > 25) {
                    arrayList.add("--compiler-filter=quicken");
                } else {
                    arrayList.add("--compiler-filter=interpret-only");
                }
                ProcessBuilder processBuilder = new ProcessBuilder(arrayList);
                processBuilder.redirectErrorStream(true);
                Process processStart = processBuilder.start();
                StreamConsumer.consumeInputStream(processStart.getInputStream());
                StreamConsumer.consumeInputStream(processStart.getErrorStream());
                try {
                    int iWaitFor = processStart.waitFor();
                    if (iWaitFor != 0) {
                        throw new IOException("dex2oat works unsuccessfully, exit code: " + iWaitFor);
                    }
                    if (fileLock != null) {
                        try {
                            fileLock.close();
                        } catch (IOException e) {
                            Log.w("Tinker.ParallelDex", "release interpret Lock error", e);
                        }
                    }
                } catch (InterruptedException e2) {
                    throw new IOException("dex2oat is interrupted, msg: " + e2.getMessage(), e2);
                }
            } catch (Throwable th) {
                if (fileLock != null) {
                    try {
                        fileLock.close();
                    } catch (IOException e3) {
                        Log.w("Tinker.ParallelDex", "release interpret Lock error", e3);
                    }
                }
                throw th;
            }
        }

        boolean run() {
            try {
            } catch (Throwable th) {
                Log.e("Tinker.ParallelDex", "Failed to optimize dex: " + this.dexFile.getAbsolutePath(), th);
                ResultCallback resultCallback = this.callback;
                if (resultCallback != null) {
                    resultCallback.onFailed(this.dexFile, this.optimizedDir, th);
                    return false;
                }
            }
            if (!SharePatchFileUtil.isLegalFile(this.dexFile) && this.callback != null) {
                this.callback.onFailed(this.dexFile, this.optimizedDir, new IOException("dex file " + this.dexFile.getAbsolutePath() + " is not exist!"));
                return false;
            }
            if (this.callback != null) {
                this.callback.onStart(this.dexFile, this.optimizedDir);
            }
            String strOptimizedPathFor = SharePatchFileUtil.optimizedPathFor(this.dexFile, this.optimizedDir);
            if (!ShareTinkerInternals.isArkHotRuning()) {
                if (this.useInterpretMode) {
                    interpretDex2Oat(this.dexFile.getAbsolutePath(), strOptimizedPathFor);
                } else if (Build.VERSION.SDK_INT >= 26 || (Build.VERSION.SDK_INT >= 25 && Build.VERSION.PREVIEW_SDK_INT != 0)) {
                    NewClassLoaderInjector.triggerDex2Oat(this.context, this.optimizedDir, this.dexFile.getAbsolutePath());
                } else {
                    DexFile.loadDex(this.dexFile.getAbsolutePath(), strOptimizedPathFor, 0);
                }
            }
            if (this.callback != null) {
                this.callback.onSuccess(this.dexFile, this.optimizedDir, new File(strOptimizedPathFor));
            }
            return true;
        }
    }

    public interface ResultCallback {
        void onFailed(File file, File file2, Throwable th);

        void onStart(File file, File file2);

        void onSuccess(File file, File file2, File file3);
    }

    private static class StreamConsumer {
        static final Executor STREAM_CONSUMER = Executors.newSingleThreadExecutor();

        private StreamConsumer() {
        }

        static void consumeInputStream(final InputStream inputStream) {
            STREAM_CONSUMER.execute(new Runnable() {
                @Override
                public void run() throws IOException {
                    if (inputStream == null) {
                        return;
                    }
                    do {
                        try {
                        } catch (IOException unused) {
                        } catch (Throwable th) {
                            try {
                                inputStream.close();
                            } catch (Exception unused2) {
                            }
                            throw th;
                        }
                    } while (inputStream.read(new byte[256]) > 0);
                    try {
                        inputStream.close();
                    } catch (Exception unused3) {
                    }
                }
            });
        }
    }

    public static boolean optimizeAll(Context context, Collection<File> collection, File file, ResultCallback resultCallback) {
        return optimizeAll(context, collection, file, false, null, resultCallback);
    }

    public static boolean optimizeAll(Context context, Collection<File> collection, File file, boolean z, String str, ResultCallback resultCallback) {
        ArrayList arrayList = new ArrayList(collection);
        Collections.sort(arrayList, new Comparator<File>() {
            @Override
            public int compare(File file2, File file3) {
                long length = file2.length();
                long length2 = file3.length();
                if (length < length2) {
                    return 1;
                }
                return length == length2 ? 0 : -1;
            }
        });
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (!new OptimizeWorker(context, (File) it.next(), file, z, str, resultCallback).run()) {
                return false;
            }
        }
        return true;
    }
}
