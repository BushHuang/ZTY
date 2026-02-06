package com.xh.view.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FileHelper {
    private static final String APP_UPLOAD_PREFIX = "appError";
    private static final String LOG_SUFFIX = ".log";
    private static final String NAME_SPLIT = "#";
    private static final String TAG = "FileHelper";
    private static final String logPath = "/sdcard/xuehai/log/statistics/realtime/string/error/";
    private final Context mContext;
    private String packageName;

    public FileHelper(Context context) {
        this.mContext = context;
    }

    private ArrayList<String> collectDeviceInfo(Context context) throws PackageManager.NameNotFoundException {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 1);
            if (packageInfo != null) {
                arrayList.add("packageName=" + context.getPackageName());
                this.packageName = context.getPackageName();
                String str = packageInfo.versionName == null ? "null" : packageInfo.versionName;
                String str2 = packageInfo.versionCode + "";
                arrayList.add("versionName=" + str);
                arrayList.add("versionCode=" + str2);
            }
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e("FileHelper", "an error occured when collect package info");
        }
        for (Field field : Build.class.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                arrayList.add(field.getName() + "=" + field.get(null).toString());
                Log.v("FileHelper", field.getName() + "=" + field.get(null));
            } catch (Exception unused2) {
                Log.e("FileHelper", "an error occured when collect crash info");
            }
        }
        return arrayList;
    }

    private String saveStrInfo2File(String str, String str2) throws IOException {
        try {
            String str3 = str + "#" + this.packageName + "#" + new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date()) + "#.log";
            if (Environment.getExternalStorageState().equals("mounted")) {
                File file = new File("/sdcard/xuehai/log/statistics/realtime/string/error/");
                if (!file.exists()) {
                    file.mkdirs();
                }
                FileOutputStream fileOutputStream = new FileOutputStream("/sdcard/xuehai/log/statistics/realtime/string/error/" + str3);
                fileOutputStream.write(str2.getBytes());
                fileOutputStream.close();
            }
            Log.e("FileHelper", str3);
            return str3;
        } catch (Exception e) {
            Log.e("FileHelper", e.getMessage());
            return null;
        }
    }

    public void uploadLog(String str) throws PackageManager.NameNotFoundException, IOException {
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList<String> arrayListCollectDeviceInfo = collectDeviceInfo(this.mContext);
        for (int i = 0; i < arrayListCollectDeviceInfo.size(); i++) {
            stringBuffer.append(arrayListCollectDeviceInfo.get(i) + "\n");
        }
        stringBuffer.append(str);
        saveStrInfo2File("appError", stringBuffer.toString());
    }
}
