package com.tencent.tinker.lib.library;

import android.content.Context;
import android.os.Build;
import com.tencent.tinker.entry.ApplicationLike;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerApplicationHelper;
import com.tencent.tinker.lib.tinker.TinkerLoadResult;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.TinkerRuntimeException;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TinkerLoadLibrary {
    private static final String TAG = "Tinker.LoadLibrary";

    private static final class V14 {
        private V14() {
        }

        private static void install(ClassLoader classLoader, File file) throws Throwable {
            Object obj = ShareReflectUtil.findField(classLoader, "pathList").get(classLoader);
            Field fieldFindField = ShareReflectUtil.findField(obj, "nativeLibraryDirectories");
            File[] fileArr = (File[]) fieldFindField.get(obj);
            ArrayList arrayList = new ArrayList(fileArr.length + 1);
            arrayList.add(file);
            for (File file2 : fileArr) {
                if (!file.equals(file2)) {
                    arrayList.add(file2);
                }
            }
            fieldFindField.set(obj, arrayList.toArray(new File[0]));
        }
    }

    private static final class V23 {
        private V23() {
        }

        private static void install(ClassLoader classLoader, File file) throws Throwable {
            Object obj = ShareReflectUtil.findField(classLoader, "pathList").get(classLoader);
            List arrayList = (List) ShareReflectUtil.findField(obj, "nativeLibraryDirectories").get(obj);
            if (arrayList == null) {
                arrayList = new ArrayList(2);
            }
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (file.equals((File) it.next())) {
                    it.remove();
                    break;
                }
            }
            arrayList.add(0, file);
            List arrayList2 = (List) ShareReflectUtil.findField(obj, "systemNativeLibraryDirectories").get(obj);
            if (arrayList2 == null) {
                arrayList2 = new ArrayList(2);
            }
            ArrayList arrayList3 = new ArrayList(arrayList.size() + arrayList2.size() + 1);
            arrayList3.addAll(arrayList);
            arrayList3.addAll(arrayList2);
            ShareReflectUtil.findField(obj, "nativeLibraryPathElements").set(obj, (Object[]) ShareReflectUtil.findMethod(obj, "makePathElements", (Class<?>[]) new Class[]{List.class, File.class, List.class}).invoke(obj, arrayList3, null, new ArrayList()));
        }
    }

    private static final class V25 {
        private V25() {
        }

        private static void install(ClassLoader classLoader, File file) throws Throwable {
            Object obj = ShareReflectUtil.findField(classLoader, "pathList").get(classLoader);
            List arrayList = (List) ShareReflectUtil.findField(obj, "nativeLibraryDirectories").get(obj);
            if (arrayList == null) {
                arrayList = new ArrayList(2);
            }
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (file.equals((File) it.next())) {
                    it.remove();
                    break;
                }
            }
            arrayList.add(0, file);
            List arrayList2 = (List) ShareReflectUtil.findField(obj, "systemNativeLibraryDirectories").get(obj);
            if (arrayList2 == null) {
                arrayList2 = new ArrayList(2);
            }
            ArrayList arrayList3 = new ArrayList(arrayList.size() + arrayList2.size() + 1);
            arrayList3.addAll(arrayList);
            arrayList3.addAll(arrayList2);
            ShareReflectUtil.findField(obj, "nativeLibraryPathElements").set(obj, (Object[]) ShareReflectUtil.findMethod(obj, "makePathElements", (Class<?>[]) new Class[]{List.class}).invoke(obj, arrayList3));
        }
    }

    private static final class V4 {
        private V4() {
        }

        private static void install(ClassLoader classLoader, File file) throws Throwable {
            String path = file.getPath();
            Field fieldFindField = ShareReflectUtil.findField(classLoader, "libPath");
            String[] strArrSplit = ((String) fieldFindField.get(classLoader)).split(":");
            StringBuilder sb = new StringBuilder(path);
            for (String str : strArrSplit) {
                if (str != null && !path.equals(str)) {
                    sb.append(':');
                    sb.append(str);
                }
            }
            fieldFindField.set(classLoader, sb.toString());
            Field fieldFindField2 = ShareReflectUtil.findField(classLoader, "libraryPathElements");
            List list = (List) fieldFindField2.get(classLoader);
            Iterator it = list.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (path.equals((String) it.next())) {
                        it.remove();
                        break;
                    }
                } else {
                    break;
                }
            }
            list.add(0, path);
            fieldFindField2.set(classLoader, list);
        }
    }

    public static boolean installNativeLibraryABIWithoutTinkerInstalled(ApplicationLike applicationLike, String str) throws Throwable {
        String currentVersion = TinkerApplicationHelper.getCurrentVersion(applicationLike);
        if (ShareTinkerInternals.isNullOrNil(currentVersion)) {
            TinkerLog.e("Tinker.LoadLibrary", "failed to get current patch version.", new Object[0]);
            return false;
        }
        File patchDirectory = SharePatchFileUtil.getPatchDirectory(applicationLike.getApplication());
        if (patchDirectory == null) {
            TinkerLog.e("Tinker.LoadLibrary", "failed to get current patch directory.", new Object[0]);
            return false;
        }
        File file = new File(new File(patchDirectory.getAbsolutePath() + "/" + SharePatchFileUtil.getPatchVersionDirectory(currentVersion)).getAbsolutePath() + "/lib/lib/" + str);
        if (!file.exists()) {
            TinkerLog.e("Tinker.LoadLibrary", "tinker lib path [%s] is not exists.", file);
            return false;
        }
        ClassLoader classLoader = applicationLike.getApplication().getClassLoader();
        if (classLoader == null) {
            TinkerLog.e("Tinker.LoadLibrary", "classloader is null", new Object[0]);
            return false;
        }
        TinkerLog.i("Tinker.LoadLibrary", "before hack classloader:" + classLoader.toString(), new Object[0]);
        try {
            Method declaredMethod = TinkerLoadLibrary.class.getDeclaredMethod("installNativeLibraryPath", ClassLoader.class, File.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(null, classLoader, file);
            TinkerLog.i("Tinker.LoadLibrary", "after hack classloader:" + classLoader.toString(), new Object[0]);
            return true;
        } catch (Throwable th) {
            try {
                TinkerLog.e("Tinker.LoadLibrary", "installNativeLibraryPath fail:" + file + ", thr: " + th, new Object[0]);
                TinkerLog.i("Tinker.LoadLibrary", "after hack classloader:" + classLoader.toString(), new Object[0]);
                return false;
            } catch (Throwable th2) {
                TinkerLog.i("Tinker.LoadLibrary", "after hack classloader:" + classLoader.toString(), new Object[0]);
                throw th2;
            }
        }
    }

    private static void installNativeLibraryPath(ClassLoader classLoader, File file) throws Throwable {
        if (file == null || !file.exists()) {
            TinkerLog.e("Tinker.LoadLibrary", "installNativeLibraryPath, folder %s is illegal", file);
            return;
        }
        if ((Build.VERSION.SDK_INT == 25 && Build.VERSION.PREVIEW_SDK_INT != 0) || Build.VERSION.SDK_INT > 25) {
            try {
                V25.install(classLoader, file);
                return;
            } catch (Throwable th) {
                TinkerLog.e("Tinker.LoadLibrary", "installNativeLibraryPath, v25 fail, sdk: %d, error: %s, try to fallback to V23", Integer.valueOf(Build.VERSION.SDK_INT), th.getMessage());
                V23.install(classLoader, file);
                return;
            }
        }
        if (Build.VERSION.SDK_INT < 23) {
            if (Build.VERSION.SDK_INT >= 14) {
                V14.install(classLoader, file);
                return;
            } else {
                V4.install(classLoader, file);
                return;
            }
        }
        try {
            V23.install(classLoader, file);
        } catch (Throwable th2) {
            TinkerLog.e("Tinker.LoadLibrary", "installNativeLibraryPath, v23 fail, sdk: %d, error: %s, try to fallback to V14", Integer.valueOf(Build.VERSION.SDK_INT), th2.getMessage());
            V14.install(classLoader, file);
        }
    }

    public static boolean installNavitveLibraryABI(Context context, String str) {
        Tinker tinkerWith = Tinker.with(context);
        if (!tinkerWith.isTinkerLoaded()) {
            TinkerLog.i("Tinker.LoadLibrary", "tinker is not loaded, just return", new Object[0]);
            return false;
        }
        TinkerLoadResult tinkerLoadResultIfPresent = tinkerWith.getTinkerLoadResultIfPresent();
        if (tinkerLoadResultIfPresent.libs == null) {
            TinkerLog.i("Tinker.LoadLibrary", "tinker libs is null, just return", new Object[0]);
            return false;
        }
        File file = new File(tinkerLoadResultIfPresent.libraryDirectory, "lib/" + str);
        if (!file.exists()) {
            TinkerLog.e("Tinker.LoadLibrary", "current libraryABI folder is not exist, path: %s", file.getPath());
            return false;
        }
        ClassLoader classLoader = context.getClassLoader();
        if (classLoader == null) {
            TinkerLog.e("Tinker.LoadLibrary", "classloader is null", new Object[0]);
            return false;
        }
        TinkerLog.i("Tinker.LoadLibrary", "before hack classloader:" + classLoader.toString(), new Object[0]);
        try {
            installNativeLibraryPath(classLoader, file);
            TinkerLog.i("Tinker.LoadLibrary", "after hack classloader:" + classLoader.toString(), new Object[0]);
            return true;
        } catch (Throwable th) {
            try {
                TinkerLog.e("Tinker.LoadLibrary", "installNativeLibraryPath fail:" + th, new Object[0]);
                TinkerLog.i("Tinker.LoadLibrary", "after hack classloader:" + classLoader.toString(), new Object[0]);
                return false;
            } catch (Throwable th2) {
                TinkerLog.i("Tinker.LoadLibrary", "after hack classloader:" + classLoader.toString(), new Object[0]);
                throw th2;
            }
        }
    }

    public static void loadArmLibrary(Context context, String str) {
        if (str == null || str.isEmpty() || context == null) {
            throw new TinkerRuntimeException("libName or context is null!");
        }
        if (Tinker.with(context).isEnabledForNativeLib() && loadLibraryFromTinker(context, "lib/armeabi", str)) {
            return;
        }
        System.loadLibrary(str);
    }

    public static void loadArmLibraryWithoutTinkerInstalled(ApplicationLike applicationLike, String str) {
        if (str == null || str.isEmpty() || applicationLike == null) {
            throw new TinkerRuntimeException("libName or appLike is null!");
        }
        if (TinkerApplicationHelper.isTinkerEnableForNativeLib(applicationLike) && TinkerApplicationHelper.loadLibraryFromTinker(applicationLike, "lib/armeabi", str)) {
            return;
        }
        System.loadLibrary(str);
    }

    public static void loadArmV7Library(Context context, String str) {
        if (str == null || str.isEmpty() || context == null) {
            throw new TinkerRuntimeException("libName or context is null!");
        }
        if (Tinker.with(context).isEnabledForNativeLib() && loadLibraryFromTinker(context, "lib/armeabi-v7a", str)) {
            return;
        }
        System.loadLibrary(str);
    }

    public static void loadArmV7LibraryWithoutTinkerInstalled(ApplicationLike applicationLike, String str) {
        if (str == null || str.isEmpty() || applicationLike == null) {
            throw new TinkerRuntimeException("libName or appLike is null!");
        }
        if (TinkerApplicationHelper.isTinkerEnableForNativeLib(applicationLike) && TinkerApplicationHelper.loadLibraryFromTinker(applicationLike, "lib/armeabi-v7a", str)) {
            return;
        }
        System.loadLibrary(str);
    }

    public static boolean loadLibraryFromTinker(Context context, String str, String str2) throws UnsatisfiedLinkError {
        Tinker tinkerWith = Tinker.with(context);
        if (!str2.startsWith("lib")) {
            str2 = "lib" + str2;
        }
        if (!str2.endsWith(".so")) {
            str2 = str2 + ".so";
        }
        String str3 = str + "/" + str2;
        if (tinkerWith.isEnabledForNativeLib() && tinkerWith.isTinkerLoaded()) {
            TinkerLoadResult tinkerLoadResultIfPresent = tinkerWith.getTinkerLoadResultIfPresent();
            if (tinkerLoadResultIfPresent.libs == null) {
                return false;
            }
            for (String str4 : tinkerLoadResultIfPresent.libs.keySet()) {
                if (str4.equals(str3)) {
                    String str5 = tinkerLoadResultIfPresent.libraryDirectory + "/" + str4;
                    File file = new File(str5);
                    if (!file.exists()) {
                        continue;
                    } else {
                        if (!tinkerWith.isTinkerLoadVerify() || SharePatchFileUtil.verifyFileMd5(file, tinkerLoadResultIfPresent.libs.get(str4))) {
                            System.load(str5);
                            TinkerLog.i("Tinker.LoadLibrary", "loadLibraryFromTinker success:" + str5, new Object[0]);
                            return true;
                        }
                        tinkerWith.getLoadReporter().onLoadFileMd5Mismatch(file, 5);
                    }
                }
            }
        }
        return false;
    }
}
