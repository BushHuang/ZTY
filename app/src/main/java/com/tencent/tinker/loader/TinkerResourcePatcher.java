package com.tencent.tinker.loader;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import android.util.ArrayMap;
import android.util.Log;
import com.tencent.tinker.loader.shareutil.SharePatchFileUtil;
import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class TinkerResourcePatcher {
    private static final String TAG = "Tinker.ResourcePatcher";
    private static final String TEST_ASSETS_VALUE = "only_use_to_test_tinker_resource.txt";
    private static Method addAssetPathAsSharedLibraryMethod;
    private static Method addAssetPathMethod;
    private static Field assetsFiled;
    private static Object currentActivityThread;
    private static Method ensureStringBlocksMethod;
    private static AssetManager newAssetManager;
    private static Field packagesFiled;
    private static Field publicSourceDirField;
    private static Collection<WeakReference<Resources>> references;
    private static Field resDir;
    private static Field resourcePackagesFiled;
    private static Field resourcesImplFiled;
    private static Field stringBlocksField;

    TinkerResourcePatcher() {
    }

    private static boolean checkResUpdate(Context context) {
        try {
            SharePatchFileUtil.closeQuietly(context.getAssets().open("only_use_to_test_tinker_resource.txt"));
            Log.i("Tinker.ResourcePatcher", "checkResUpdate success, found test resource assets file only_use_to_test_tinker_resource.txt");
            return true;
        } catch (Throwable th) {
            try {
                Log.e("Tinker.ResourcePatcher", "checkResUpdate failed, can't find test resource assets file only_use_to_test_tinker_resource.txt e:" + th.getMessage());
                return false;
            } finally {
                SharePatchFileUtil.closeQuietly(null);
            }
        }
    }

    private static void clearPreloadTypedArrayIssue(Resources resources) {
        Log.w("Tinker.ResourcePatcher", "try to clear typedArray cache!");
        try {
            Object obj = ShareReflectUtil.findField((Class<?>) Resources.class, "mTypedArrayPool").get(resources);
            do {
            } while (ShareReflectUtil.findMethod(obj, "acquire", (Class<?>[]) new Class[0]).invoke(obj, new Object[0]) != null);
        } catch (Throwable th) {
            Log.e("Tinker.ResourcePatcher", "clearPreloadTypedArrayIssue failed, ignore error: " + th);
        }
    }

    public static void isResourceCanPatch(Context context) throws Throwable {
        Class<?> cls;
        Class<?> cls2 = Class.forName("android.app.ActivityThread");
        currentActivityThread = ShareReflectUtil.getActivityThread(context, cls2);
        try {
            cls = Class.forName("android.app.LoadedApk");
        } catch (ClassNotFoundException unused) {
            cls = Class.forName("android.app.ActivityThread$PackageInfo");
        }
        resDir = ShareReflectUtil.findField(cls, "mResDir");
        packagesFiled = ShareReflectUtil.findField(cls2, "mPackages");
        if (Build.VERSION.SDK_INT < 27) {
            resourcePackagesFiled = ShareReflectUtil.findField(cls2, "mResourcePackages");
        }
        AssetManager assets = context.getAssets();
        addAssetPathMethod = ShareReflectUtil.findMethod(assets, "addAssetPath", (Class<?>[]) new Class[]{String.class});
        if (shouldAddSharedLibraryAssets(context.getApplicationInfo())) {
            addAssetPathAsSharedLibraryMethod = ShareReflectUtil.findMethod(assets, "addAssetPathAsSharedLibrary", (Class<?>[]) new Class[]{String.class});
        }
        try {
            stringBlocksField = ShareReflectUtil.findField(assets, "mStringBlocks");
            ensureStringBlocksMethod = ShareReflectUtil.findMethod(assets, "ensureStringBlocks", (Class<?>[]) new Class[0]);
        } catch (Throwable unused2) {
        }
        newAssetManager = (AssetManager) ShareReflectUtil.findConstructor(assets, new Class[0]).newInstance(new Object[0]);
        if (Build.VERSION.SDK_INT >= 19) {
            Class<?> cls3 = Class.forName("android.app.ResourcesManager");
            Object objInvoke = ShareReflectUtil.findMethod(cls3, "getInstance", (Class<?>[]) new Class[0]).invoke(null, new Object[0]);
            try {
                references = ((ArrayMap) ShareReflectUtil.findField(cls3, "mActiveResources").get(objInvoke)).values();
            } catch (NoSuchFieldException unused3) {
                references = (Collection) ShareReflectUtil.findField(cls3, "mResourceReferences").get(objInvoke);
            }
        } else {
            references = ((HashMap) ShareReflectUtil.findField(cls2, "mActiveResources").get(currentActivityThread)).values();
        }
        if (references == null) {
            throw new IllegalStateException("resource references is null");
        }
        Resources resources = context.getResources();
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                resourcesImplFiled = ShareReflectUtil.findField(resources, "mResourcesImpl");
            } catch (Throwable unused4) {
                assetsFiled = ShareReflectUtil.findField(resources, "mAssets");
            }
        } else {
            assetsFiled = ShareReflectUtil.findField(resources, "mAssets");
        }
        try {
            publicSourceDirField = ShareReflectUtil.findField((Class<?>) ApplicationInfo.class, "publicSourceDir");
        } catch (NoSuchFieldException unused5) {
        }
    }

    public static void monkeyPatchExistingResources(Context context, String str) throws Throwable {
        if (str == null) {
            return;
        }
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        for (Field field : Build.VERSION.SDK_INT < 27 ? new Field[]{packagesFiled, resourcePackagesFiled} : new Field[]{packagesFiled}) {
            Iterator it = ((Map) field.get(currentActivityThread)).entrySet().iterator();
            while (it.hasNext()) {
                Object obj = ((WeakReference) ((Map.Entry) it.next()).getValue()).get();
                if (obj != null && applicationInfo.sourceDir.equals((String) resDir.get(obj))) {
                    resDir.set(obj, str);
                }
            }
        }
        if (((Integer) addAssetPathMethod.invoke(newAssetManager, str)).intValue() == 0) {
            throw new IllegalStateException("Could not create new AssetManager");
        }
        if (shouldAddSharedLibraryAssets(applicationInfo)) {
            for (String str2 : applicationInfo.sharedLibraryFiles) {
                if (str2.endsWith(".apk")) {
                    if (((Integer) addAssetPathAsSharedLibraryMethod.invoke(newAssetManager, str2)).intValue() == 0) {
                        throw new IllegalStateException("AssetManager add SharedLibrary Fail");
                    }
                    Log.i("Tinker.ResourcePatcher", "addAssetPathAsSharedLibrary " + str2);
                }
            }
        }
        Field field2 = stringBlocksField;
        if (field2 != null && ensureStringBlocksMethod != null) {
            field2.set(newAssetManager, null);
            ensureStringBlocksMethod.invoke(newAssetManager, new Object[0]);
        }
        Iterator<WeakReference<Resources>> it2 = references.iterator();
        while (it2.hasNext()) {
            Resources resources = it2.next().get();
            if (resources != null) {
                try {
                    assetsFiled.set(resources, newAssetManager);
                } catch (Throwable unused) {
                    Object obj2 = resourcesImplFiled.get(resources);
                    ShareReflectUtil.findField(obj2, "mAssets").set(obj2, newAssetManager);
                }
                clearPreloadTypedArrayIssue(resources);
                resources.updateConfiguration(resources.getConfiguration(), resources.getDisplayMetrics());
            }
        }
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                if (publicSourceDirField != null) {
                    publicSourceDirField.set(context.getApplicationInfo(), str);
                }
            } catch (Throwable unused2) {
            }
        }
        if (!checkResUpdate(context)) {
            throw new TinkerRuntimeException("checkResInstall failed");
        }
    }

    private static boolean shouldAddSharedLibraryAssets(ApplicationInfo applicationInfo) {
        return (Build.VERSION.SDK_INT < 24 || applicationInfo == null || applicationInfo.sharedLibraryFiles == null) ? false : true;
    }
}
