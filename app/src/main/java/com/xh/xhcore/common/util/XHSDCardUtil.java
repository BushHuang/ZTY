package com.xh.xhcore.common.util;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import com.xh.xhcore.common.statistic.XHEnvironment;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class XHSDCardUtil {

    public static class SDCardInfo {
        long availableBlocks;
        long availableBytes;
        long blockByteSize;
        long freeBlocks;
        long freeBytes;
        boolean isExist;
        long totalBlocks;
        long totalBytes;

        public String toString() {
            return "isExist=" + this.isExist + "\ntotalBlocks=" + this.totalBlocks + "\nfreeBlocks=" + this.freeBlocks + "\navailableBlocks=" + this.availableBlocks + "\nblockByteSize=" + this.blockByteSize + "\ntotalBytes=" + this.totalBytes + "\nfreeBytes=" + this.freeBytes + "\navailableBytes=" + this.availableBytes;
        }
    }

    private XHSDCardUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static String getDataPath() {
        if (!isSDCardEnable()) {
            return "sdcard unable!";
        }
        return XHEnvironment.getExternalStorageDirectory().getPath() + File.separator + "data" + File.separator;
    }

    public static String getFreeSpace() {
        if (!isSDCardEnable()) {
            return "sdcard unable!";
        }
        StatFs statFs = new StatFs(getSDCardPath());
        return XHConvertUtil.byte2FitMemorySize(statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong());
    }

    public static String getSDCardInfo() {
        SDCardInfo sDCardInfo = new SDCardInfo();
        if (!isSDCardEnable()) {
            return "sdcard unable!";
        }
        sDCardInfo.isExist = true;
        StatFs statFs = new StatFs(XHEnvironment.getExternalStorageDirectory().getPath());
        sDCardInfo.totalBlocks = statFs.getBlockCountLong();
        sDCardInfo.blockByteSize = statFs.getBlockSizeLong();
        sDCardInfo.availableBlocks = statFs.getAvailableBlocksLong();
        sDCardInfo.availableBytes = statFs.getAvailableBytes();
        sDCardInfo.freeBlocks = statFs.getFreeBlocksLong();
        sDCardInfo.freeBytes = statFs.getFreeBytes();
        sDCardInfo.totalBytes = statFs.getTotalBytes();
        return sDCardInfo.toString();
    }

    public static String getSDCardPath() throws Throwable {
        ?? line;
        if (!isSDCardEnable()) {
            return "sdcard unable!";
        }
        BufferedReader bufferedReader = null;
        BufferedReader bufferedReader2 = null;
        try {
            try {
                Process processExec = Runtime.getRuntime().exec("cat /proc/mounts");
                BufferedReader bufferedReader3 = new BufferedReader(new InputStreamReader(new BufferedInputStream(processExec.getInputStream())));
                while (true) {
                    try {
                        line = bufferedReader3.readLine();
                        if (line != 0) {
                            if (line.contains("sdcard") && line.contains(".android_secure")) {
                                String[] strArrSplit = line.split(" ");
                                if (strArrSplit.length >= 5) {
                                    String str = strArrSplit[1].replace("/.android_secure", "") + File.separator;
                                    XHCloseUtil.closeIO(bufferedReader3);
                                    return str;
                                }
                            }
                            if (processExec.waitFor() != 0 && (line = processExec.exitValue()) == 1) {
                                break;
                            }
                        } else {
                            break;
                        }
                    } catch (Exception e) {
                        e = e;
                        bufferedReader2 = bufferedReader3;
                        e.printStackTrace();
                        XHCloseUtil.closeIO(bufferedReader2);
                        bufferedReader = bufferedReader2;
                        return XHEnvironment.getExternalStorageDirectory().getPath() + File.separator;
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader3;
                        XHCloseUtil.closeIO(bufferedReader);
                        throw th;
                    }
                }
                XHCloseUtil.closeIO(bufferedReader3);
                bufferedReader = line;
            } catch (Exception e2) {
                e = e2;
            }
            return XHEnvironment.getExternalStorageDirectory().getPath() + File.separator;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String getStoragePath23(Context context, boolean z) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, SecurityException, IllegalArgumentException, InvocationTargetException {
        String absolutePath;
        if (Build.VERSION.SDK_INT < 23) {
            return "";
        }
        StorageManager storageManager = (StorageManager) context.getSystemService("storage");
        try {
            Class<?> cls = Class.forName("android.os.storage.VolumeInfo");
            Class<?> cls2 = Class.forName("android.os.storage.DiskInfo");
            Method method = Class.forName("android.os.storage.StorageManager").getMethod("getVolumes", new Class[0]);
            Method method2 = cls.getMethod("getDisk", new Class[0]);
            Method method3 = cls.getMethod("getPath", new Class[0]);
            Method method4 = cls2.getMethod("isUsb", new Class[0]);
            Method method5 = cls2.getMethod("isSd", new Class[0]);
            List list = (List) method.invoke(storageManager, new Object[0]);
            for (int i = 0; i < list.size(); i++) {
                Object obj = list.get(i);
                Object objInvoke = method2.invoke(obj, new Object[0]);
                if (objInvoke != null) {
                    boolean zBooleanValue = ((Boolean) method5.invoke(objInvoke, new Object[0])).booleanValue();
                    boolean zBooleanValue2 = ((Boolean) method4.invoke(objInvoke, new Object[0])).booleanValue();
                    File file = (File) method3.invoke(obj, new Object[0]);
                    if (z == zBooleanValue2) {
                        absolutePath = file.getAbsolutePath();
                    } else if ((!z) == zBooleanValue) {
                        absolutePath = file.getAbsolutePath();
                    }
                    return absolutePath;
                }
            }
            return "";
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return "";
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return "";
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
            return "";
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
            return "";
        }
    }

    public static boolean isSDCardEnable() {
        return "mounted".equals(Environment.getExternalStorageState());
    }
}
