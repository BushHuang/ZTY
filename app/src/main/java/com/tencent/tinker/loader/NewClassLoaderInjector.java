package com.tencent.tinker.loader;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import dalvik.system.PathClassLoader;
import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

final class NewClassLoaderInjector {
    private NewClassLoaderInjector() {
        throw new UnsupportedOperationException();
    }

    private static ClassLoader createNewClassLoader(Context context, ClassLoader classLoader, File file, String... strArr) throws Throwable {
        Object obj = findField(Class.forName("dalvik.system.BaseDexClassLoader", false, classLoader), "pathList").get(classLoader);
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        if (strArr != null && strArr.length > 0) {
            for (int i = 0; i < strArr.length; i++) {
                if (i > 0) {
                    sb.append(File.pathSeparator);
                }
                sb.append(strArr[i]);
            }
        }
        String string = sb.toString();
        Field fieldFindField = findField(obj.getClass(), "nativeLibraryDirectories");
        List<File> listAsList = fieldFindField.getType().isArray() ? Arrays.asList((File[]) fieldFindField.get(obj)) : (List) fieldFindField.get(obj);
        StringBuilder sb2 = new StringBuilder();
        for (File file2 : listAsList) {
            if (file2 != null) {
                if (z) {
                    z = false;
                } else {
                    sb2.append(File.pathSeparator);
                }
                sb2.append(file2.getAbsolutePath());
            }
        }
        TinkerClassLoader tinkerClassLoader = new TinkerClassLoader(string, file, sb2.toString(), classLoader);
        findField(obj.getClass(), "definingContext").set(obj, tinkerClassLoader);
        return tinkerClassLoader;
    }

    private static void doInject(Application application, ClassLoader classLoader) throws Throwable {
        Thread.currentThread().setContextClassLoader(classLoader);
        Context context = (Context) findField(application.getClass(), "mBase").get(application);
        Object obj = findField(context.getClass(), "mPackageInfo").get(context);
        findField(obj.getClass(), "mClassLoader").set(obj, classLoader);
        if (Build.VERSION.SDK_INT < 27) {
            Resources resources = application.getResources();
            try {
                findField(resources.getClass(), "mClassLoader").set(resources, classLoader);
                Object obj2 = findField(resources.getClass(), "mDrawableInflater").get(resources);
                if (obj2 != null) {
                    findField(obj2.getClass(), "mClassLoader").set(obj2, classLoader);
                }
            } catch (Throwable unused) {
            }
        }
    }

    private static Field findField(Class<?> cls, String str) throws Throwable {
        Class<?> superclass = cls;
        while (true) {
            try {
                Field declaredField = superclass.getDeclaredField(str);
                declaredField.setAccessible(true);
                return declaredField;
            } catch (Throwable unused) {
                if (superclass == Object.class) {
                    throw new NoSuchFieldException("Cannot find field " + str + " in class " + cls.getName() + " and its super classes.");
                }
                superclass = superclass.getSuperclass();
            }
        }
    }

    public static ClassLoader inject(Application application, ClassLoader classLoader, File file, List<File> list) throws Throwable {
        int size = list.size();
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = list.get(i).getAbsolutePath();
        }
        ClassLoader classLoaderCreateNewClassLoader = createNewClassLoader(application, classLoader, file, strArr);
        doInject(application, classLoaderCreateNewClassLoader);
        return classLoaderCreateNewClassLoader;
    }

    public static void triggerDex2Oat(Context context, File file, String... strArr) throws Throwable {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String str : strArr) {
            if (z) {
                z = false;
            } else {
                sb.append(File.pathSeparator);
            }
            sb.append(str);
        }
        new PathClassLoader(sb.toString(), ClassLoader.getSystemClassLoader());
    }
}
