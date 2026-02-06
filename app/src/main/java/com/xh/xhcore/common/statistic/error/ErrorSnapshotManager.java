package com.xh.xhcore.common.statistic.error;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import com.xh.xhcore.common.http.strategy.LogUtils;
import com.xh.xhcore.common.statistic.XHEnvironment;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\t\u001a\u00020\u0004H\u0007J\b\u0010\n\u001a\u00020\bH\u0007J\b\u0010\u000b\u001a\u00020\u0004H\u0007J\b\u0010\f\u001a\u00020\bH\u0007J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u000f"}, d2 = {"Lcom/xh/xhcore/common/statistic/error/ErrorSnapshotManager;", "", "()V", "getAvailableMemory", "", "context", "Landroid/content/Context;", "getAvailableMemoryString", "", "getAvailableSize", "getSdcardAvailableSpaceSizeString", "getSdcardTotalSpace", "getSdcardTotalSpaceSizeString", "getTotalMemory", "getTotalMemoryString", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ErrorSnapshotManager {
    public static final ErrorSnapshotManager INSTANCE = new ErrorSnapshotManager();

    private ErrorSnapshotManager() {
    }

    @JvmStatic
    public static final long getAvailableMemory(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("activity");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.app.ActivityManager");
        }
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) systemService).getMemoryInfo(memoryInfo);
        long j = 1024;
        long j2 = (memoryInfo.availMem / j) / j;
        LogUtils.INSTANCE.d(Intrinsics.stringPlus("availRAMsize = ", Long.valueOf(j2)));
        return j2;
    }

    @JvmStatic
    public static final String getAvailableMemoryString(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return String.valueOf(getAvailableMemory(context));
    }

    @JvmStatic
    public static final long getAvailableSize() {
        long blockSize;
        if (Environment.getExternalStorageState().equals("mounted")) {
            long j = 1024;
            blockSize = ((new StatFs(XHEnvironment.getExternalStorageDirectory().getPath()).getBlockSize() * new StatFs(XHEnvironment.getExternalStorageDirectory().getPath()).getAvailableBlocks()) / j) / j;
        } else {
            blockSize = -1;
        }
        LogUtils.INSTANCE.d(Intrinsics.stringPlus("availableSize = ", Long.valueOf(blockSize)));
        return blockSize;
    }

    @JvmStatic
    public static final String getSdcardAvailableSpaceSizeString() {
        return String.valueOf(getAvailableSize());
    }

    @JvmStatic
    public static final long getSdcardTotalSpace() {
        long blockCount;
        if (Environment.getExternalStorageState().equals("mounted")) {
            long j = 1024;
            blockCount = ((new StatFs(XHEnvironment.getExternalStorageDirectory().getPath()).getBlockCount() * new StatFs(XHEnvironment.getExternalStorageDirectory().getPath()).getBlockSize()) / j) / j;
        } else {
            blockCount = -1;
        }
        LogUtils.INSTANCE.d(Intrinsics.stringPlus("sdcardTotalSpace = ", Long.valueOf(blockCount)));
        return blockCount;
    }

    @JvmStatic
    public static final String getSdcardTotalSpaceSizeString() {
        return String.valueOf(getSdcardTotalSpace());
    }

    @JvmStatic
    public static final long getTotalMemory(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("activity");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.app.ActivityManager");
        }
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) systemService).getMemoryInfo(memoryInfo);
        long j = 1024;
        long j2 = (memoryInfo.totalMem / j) / j;
        LogUtils.INSTANCE.d(Intrinsics.stringPlus("totalRAMsize = ", Long.valueOf(j2)));
        return j2;
    }

    @JvmStatic
    public static final String getTotalMemoryString(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return String.valueOf(getTotalMemory(context));
    }
}
