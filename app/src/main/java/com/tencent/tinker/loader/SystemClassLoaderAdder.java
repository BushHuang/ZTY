package com.tencent.tinker.loader;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import dalvik.system.DexFile;
import dalvik.system.PathClassLoader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.zip.ZipFile;

public class SystemClassLoaderAdder {
    public static final String CHECK_DEX_CLASS = "com.tencent.tinker.loader.TinkerTestDexLoad";
    public static final String CHECK_DEX_FIELD = "isPatch";
    private static final String TAG = "Tinker.ClassLoaderAdder";
    private static int sPatchDexCount;

    private static final class ArkHot {
        private ArkHot() {
        }

        private static void install(ClassLoader classLoader, List<File> list) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, IOException, SecurityException, IllegalArgumentException, InvocationTargetException {
            Class<?> clsLoadClass = ClassLoader.getSystemClassLoader().getParent().loadClass("com.huawei.ark.classloader.ExtendedClassLoaderHelper");
            Iterator<File> it = list.iterator();
            while (it.hasNext()) {
                String canonicalPath = it.next().getCanonicalPath();
                Method declaredMethod = clsLoadClass.getDeclaredMethod("applyPatch", ClassLoader.class, String.class);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(null, classLoader, canonicalPath);
                Log.i("Tinker.ClassLoaderAdder", "ArkHot install path = " + canonicalPath);
            }
        }
    }

    private static final class V14 {
        private V14() {
        }

        private static void install(ClassLoader classLoader, List<File> list, File file) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
            Object obj = ShareReflectUtil.findField(classLoader, "pathList").get(classLoader);
            ShareReflectUtil.expandFieldArray(obj, "dexElements", makeDexElements(obj, new ArrayList(list), file));
        }

        private static Object[] makeDexElements(Object obj, ArrayList<File> arrayList, File file) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
            return (Object[]) ShareReflectUtil.findMethod(obj, "makeDexElements", (Class<?>[]) new Class[]{ArrayList.class, File.class}).invoke(obj, arrayList, file);
        }
    }

    private static final class V19 {
        private V19() {
        }

        private static void install(ClassLoader classLoader, List<File> list, File file) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, IOException, IllegalArgumentException, InvocationTargetException {
            Object obj = ShareReflectUtil.findField(classLoader, "pathList").get(classLoader);
            ArrayList arrayList = new ArrayList();
            ShareReflectUtil.expandFieldArray(obj, "dexElements", makeDexElements(obj, new ArrayList(list), file, arrayList));
            if (arrayList.size() > 0) {
                Iterator it = arrayList.iterator();
                if (it.hasNext()) {
                    IOException iOException = (IOException) it.next();
                    Log.w("Tinker.ClassLoaderAdder", "Exception in makeDexElement", iOException);
                    throw iOException;
                }
            }
        }

        private static Object[] makeDexElements(Object obj, ArrayList<File> arrayList, File file, ArrayList<IOException> arrayList2) throws IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException {
            Method methodFindMethod;
            try {
                methodFindMethod = ShareReflectUtil.findMethod(obj, "makeDexElements", (Class<?>[]) new Class[]{ArrayList.class, File.class, ArrayList.class});
            } catch (NoSuchMethodException unused) {
                Log.e("Tinker.ClassLoaderAdder", "NoSuchMethodException: makeDexElements(ArrayList,File,ArrayList) failure");
                try {
                    methodFindMethod = ShareReflectUtil.findMethod(obj, "makeDexElements", (Class<?>[]) new Class[]{List.class, File.class, List.class});
                } catch (NoSuchMethodException e) {
                    Log.e("Tinker.ClassLoaderAdder", "NoSuchMethodException: makeDexElements(List,File,List) failure");
                    throw e;
                }
            }
            return (Object[]) methodFindMethod.invoke(obj, arrayList, file, arrayList2);
        }
    }

    private static final class V23 {
        private V23() {
        }

        private static void install(ClassLoader classLoader, List<File> list, File file) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, IOException, IllegalArgumentException, InvocationTargetException {
            Object obj = ShareReflectUtil.findField(classLoader, "pathList").get(classLoader);
            ArrayList arrayList = new ArrayList();
            ShareReflectUtil.expandFieldArray(obj, "dexElements", makePathElements(obj, new ArrayList(list), file, arrayList));
            if (arrayList.size() > 0) {
                Iterator it = arrayList.iterator();
                if (it.hasNext()) {
                    IOException iOException = (IOException) it.next();
                    Log.w("Tinker.ClassLoaderAdder", "Exception in makePathElement", iOException);
                    throw iOException;
                }
            }
        }

        private static Object[] makePathElements(Object obj, ArrayList<File> arrayList, File file, ArrayList<IOException> arrayList2) throws IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException {
            Method methodFindMethod;
            try {
                methodFindMethod = ShareReflectUtil.findMethod(obj, "makePathElements", (Class<?>[]) new Class[]{List.class, File.class, List.class});
            } catch (NoSuchMethodException unused) {
                Log.e("Tinker.ClassLoaderAdder", "NoSuchMethodException: makePathElements(List,File,List) failure");
                try {
                    methodFindMethod = ShareReflectUtil.findMethod(obj, "makePathElements", (Class<?>[]) new Class[]{ArrayList.class, File.class, ArrayList.class});
                } catch (NoSuchMethodException unused2) {
                    Log.e("Tinker.ClassLoaderAdder", "NoSuchMethodException: makeDexElements(ArrayList,File,ArrayList) failure");
                    try {
                        Log.e("Tinker.ClassLoaderAdder", "NoSuchMethodException: try use v19 instead");
                        return V19.makeDexElements(obj, arrayList, file, arrayList2);
                    } catch (NoSuchMethodException e) {
                        Log.e("Tinker.ClassLoaderAdder", "NoSuchMethodException: makeDexElements(List,File,List) failure");
                        throw e;
                    }
                }
            }
            return (Object[]) methodFindMethod.invoke(obj, arrayList, file, arrayList2);
        }
    }

    private static final class V4 {
        private V4() {
        }

        private static void install(ClassLoader classLoader, List<File> list, File file) throws IllegalAccessException, NoSuchFieldException, IOException, IllegalArgumentException {
            int size = list.size();
            Field fieldFindField = ShareReflectUtil.findField(classLoader, "path");
            StringBuilder sb = new StringBuilder((String) fieldFindField.get(classLoader));
            String[] strArr = new String[size];
            File[] fileArr = new File[size];
            ZipFile[] zipFileArr = new ZipFile[size];
            DexFile[] dexFileArr = new DexFile[size];
            ListIterator<File> listIterator = list.listIterator();
            while (listIterator.hasNext()) {
                File next = listIterator.next();
                String absolutePath = next.getAbsolutePath();
                sb.append(':');
                sb.append(absolutePath);
                int iPreviousIndex = listIterator.previousIndex();
                strArr[iPreviousIndex] = absolutePath;
                fileArr[iPreviousIndex] = next;
                zipFileArr[iPreviousIndex] = new ZipFile(next);
                dexFileArr[iPreviousIndex] = DexFile.loadDex(absolutePath, SharePatchFileUtil.optimizedPathFor(next, file), 0);
            }
            fieldFindField.set(classLoader, sb.toString());
            ShareReflectUtil.expandFieldArray(classLoader, "mPaths", strArr);
            ShareReflectUtil.expandFieldArray(classLoader, "mFiles", fileArr);
            ShareReflectUtil.expandFieldArray(classLoader, "mZips", zipFileArr);
            try {
                ShareReflectUtil.expandFieldArray(classLoader, "mDexs", dexFileArr);
            } catch (Exception unused) {
            }
        }
    }

    private static boolean checkDexInstall(ClassLoader classLoader) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        boolean zBooleanValue = ((Boolean) ShareReflectUtil.findField(Class.forName("com.tencent.tinker.loader.TinkerTestDexLoad", true, classLoader), "isPatch").get(null)).booleanValue();
        Log.w("Tinker.ClassLoaderAdder", "checkDexInstall result:" + zBooleanValue);
        return zBooleanValue;
    }

    private static List<File> createSortedAdditionalPathEntries(List<File> list) {
        ArrayList arrayList = new ArrayList(list);
        final HashMap map = new HashMap();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String name = ((File) it.next()).getName();
            map.put(name, Boolean.valueOf(ShareConstants.CLASS_N_PATTERN.matcher(name).matches()));
        }
        Collections.sort(arrayList, new Comparator<File>() {
            @Override
            public int compare(File file, File file2) {
                if (file == null && file2 == null) {
                    return 0;
                }
                if (file == null) {
                    return -1;
                }
                if (file2 == null) {
                    return 1;
                }
                String name2 = file.getName();
                String name3 = file2.getName();
                if (name2.equals(name3)) {
                    return 0;
                }
                if (name2.startsWith("test.dex")) {
                    return 1;
                }
                if (name3.startsWith("test.dex")) {
                    return -1;
                }
                boolean zBooleanValue = ((Boolean) map.get(name2)).booleanValue();
                boolean zBooleanValue2 = ((Boolean) map.get(name3)).booleanValue();
                if (!zBooleanValue || !zBooleanValue2) {
                    if (zBooleanValue) {
                        return -1;
                    }
                    if (zBooleanValue2) {
                        return 1;
                    }
                    return name2.compareTo(name3);
                }
                int iIndexOf = name2.indexOf(46);
                int iIndexOf2 = name3.indexOf(46);
                int i = iIndexOf > 7 ? Integer.parseInt(name2.substring(7, iIndexOf)) : 1;
                int i2 = iIndexOf2 > 7 ? Integer.parseInt(name3.substring(7, iIndexOf2)) : 1;
                if (i == i2) {
                    return 0;
                }
                return i < i2 ? -1 : 1;
            }
        });
        return arrayList;
    }

    public static void installApk(PathClassLoader pathClassLoader, List<File> list) throws Throwable {
        if (list.isEmpty()) {
            return;
        }
        List<File> listCreateSortedAdditionalPathEntries = createSortedAdditionalPathEntries(list);
        ArkHot.install(pathClassLoader, listCreateSortedAdditionalPathEntries);
        sPatchDexCount = listCreateSortedAdditionalPathEntries.size();
        Log.i("Tinker.ClassLoaderAdder", "after loaded classloader: " + pathClassLoader + ", dex size:" + sPatchDexCount);
        checkDexInstall(pathClassLoader);
    }

    public static void installDexes(Application application, ClassLoader classLoader, File file, List<File> list, boolean z) throws Throwable {
        Log.i("Tinker.ClassLoaderAdder", "installDexes dexOptDir: " + file.getAbsolutePath() + ", dex size:" + list.size());
        if (list.isEmpty()) {
            return;
        }
        List<File> listCreateSortedAdditionalPathEntries = createSortedAdditionalPathEntries(list);
        if (Build.VERSION.SDK_INT >= 24 && !z) {
            classLoader = NewClassLoaderInjector.inject(application, classLoader, file, listCreateSortedAdditionalPathEntries);
        } else if (Build.VERSION.SDK_INT >= 23) {
            V23.install(classLoader, listCreateSortedAdditionalPathEntries, file);
        } else if (Build.VERSION.SDK_INT >= 19) {
            V19.install(classLoader, listCreateSortedAdditionalPathEntries, file);
        } else if (Build.VERSION.SDK_INT >= 14) {
            V14.install(classLoader, listCreateSortedAdditionalPathEntries, file);
        } else {
            V4.install(classLoader, listCreateSortedAdditionalPathEntries, file);
        }
        sPatchDexCount = listCreateSortedAdditionalPathEntries.size();
        Log.i("Tinker.ClassLoaderAdder", "after loaded classloader: " + classLoader + ", dex size:" + sPatchDexCount);
        if (checkDexInstall(classLoader)) {
            return;
        }
        uninstallPatchDex(classLoader);
        throw new TinkerRuntimeException("checkDexInstall failed");
    }

    public static void uninstallPatchDex(ClassLoader classLoader) throws Throwable {
        if (sPatchDexCount <= 0) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 14) {
            ShareReflectUtil.reduceFieldArray(ShareReflectUtil.findField(classLoader, "pathList").get(classLoader), "dexElements", sPatchDexCount);
            return;
        }
        ShareReflectUtil.reduceFieldArray(classLoader, "mPaths", sPatchDexCount);
        ShareReflectUtil.reduceFieldArray(classLoader, "mFiles", sPatchDexCount);
        ShareReflectUtil.reduceFieldArray(classLoader, "mZips", sPatchDexCount);
        try {
            ShareReflectUtil.reduceFieldArray(classLoader, "mDexs", sPatchDexCount);
        } catch (Exception unused) {
        }
    }
}
