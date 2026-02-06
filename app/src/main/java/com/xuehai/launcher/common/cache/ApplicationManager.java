package com.xuehai.launcher.common.cache;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;
import com.xuehai.launcher.common.R;
import com.xuehai.launcher.common.base.BaseApplication;
import com.xuehai.launcher.common.config.InvariantDeviceProfile;
import com.xuehai.launcher.common.util.app.AppShortcut;
import com.zaze.utils.AppUtil;
import com.zaze.utils.BmpUtil;
import java.lang.ref.SoftReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0005H\u0002J\b\u0010\u0015\u001a\u0004\u0018\u00010\u0006J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u0005J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0005J\u001a\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0014\u0010\u001e\u001a\u0004\u0018\u00010\b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0005H\u0002J\u0014\u0010\u001f\u001a\u0004\u0018\u00010\b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0005H\u0002J\u001c\u0010 \u001a\u0004\u0018\u00010\u00062\b\u0010!\u001a\u0004\u0018\u00010\u00192\b\b\u0002\u0010\"\u001a\u00020\u001dJ\u0010\u0010#\u001a\u00020\u00122\b\u0010$\u001a\u0004\u0018\u00010%J\u001a\u0010&\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00052\b\u0010'\u001a\u0004\u0018\u00010\bH\u0002J\u000e\u0010(\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0005R\u001c\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\t\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006)"}, d2 = {"Lcom/xuehai/launcher/common/cache/ApplicationManager;", "", "()V", "BITMAP_CACHE", "Landroid/util/LruCache;", "", "Landroid/graphics/Bitmap;", "SHORTCUT_CACHE", "Lcom/xuehai/launcher/common/util/app/AppShortcut;", "defaultLogoBmpRef", "Ljava/lang/ref/SoftReference;", "invariantDeviceProfile", "Lcom/xuehai/launcher/common/config/InvariantDeviceProfile;", "getInvariantDeviceProfile", "()Lcom/xuehai/launcher/common/config/InvariantDeviceProfile;", "setInvariantDeviceProfile", "(Lcom/xuehai/launcher/common/config/InvariantDeviceProfile;)V", "clearAllCache", "", "clearCache", "packageName", "getAppDefaultLogo", "getAppIconHasDefault", "getAppShortcut", "getFullResIcon", "Landroid/graphics/drawable/Drawable;", "resources", "Landroid/content/res/Resources;", "iconId", "", "getShortcutFromCache", "initAppShortcut", "makeDefaultIcon", "drawable", "bitmapSize", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "saveShortcutToCache", "appShortcut", "tryLoadAppShortcut", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ApplicationManager {
    private static SoftReference<Bitmap> defaultLogoBmpRef;
    public static final ApplicationManager INSTANCE = new ApplicationManager();
    private static InvariantDeviceProfile invariantDeviceProfile = new InvariantDeviceProfile();
    private static final LruCache<String, Bitmap> BITMAP_CACHE = new LruCache<>(60);
    private static final LruCache<String, AppShortcut> SHORTCUT_CACHE = new LruCache<>(60);

    private ApplicationManager() {
    }

    private final void clearCache(String packageName) {
        SHORTCUT_CACHE.remove(packageName);
        BITMAP_CACHE.remove(packageName);
    }

    private final Drawable getFullResIcon(Resources resources, int iconId) {
        try {
            return Build.VERSION.SDK_INT >= 21 ? resources.getDrawableForDensity(iconId, invariantDeviceProfile.getFillResIconDpi(), null) : resources.getDrawableForDensity(iconId, invariantDeviceProfile.getFillResIconDpi());
        } catch (Resources.NotFoundException e) {
            Log.w("Error[MDM]", "getFullResIcon()", e);
            return (Drawable) null;
        }
    }

    private final AppShortcut getShortcutFromCache(String packageName) {
        if (packageName != null) {
            return SHORTCUT_CACHE.get(packageName);
        }
        return null;
    }

    private final AppShortcut initAppShortcut(String packageName) {
        if (packageName == null) {
            return null;
        }
        AppShortcut appShortcutTransform = AppShortcut.INSTANCE.transform(AppUtil.getPackageInfo$default(BaseApplication.INSTANCE.getInstance(), packageName, 0, 4, (Object) null));
        INSTANCE.saveShortcutToCache(packageName, appShortcutTransform);
        return appShortcutTransform;
    }

    public static Bitmap makeDefaultIcon$default(ApplicationManager applicationManager, Drawable drawable, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = invariantDeviceProfile.getIconBitmapSize();
        }
        return applicationManager.makeDefaultIcon(drawable, i);
    }

    private final void saveShortcutToCache(String packageName, AppShortcut appShortcut) {
        if (appShortcut != null) {
            SHORTCUT_CACHE.put(packageName, appShortcut);
        }
    }

    public final void clearAllCache() {
        BITMAP_CACHE.evictAll();
        SHORTCUT_CACHE.evictAll();
    }

    public final Bitmap getAppDefaultLogo() {
        Bitmap bitmapMakeDefaultIcon$default;
        SoftReference<Bitmap> softReference = defaultLogoBmpRef;
        if (softReference == null || (bitmapMakeDefaultIcon$default = softReference.get()) == null) {
            bitmapMakeDefaultIcon$default = makeDefaultIcon$default(this, getFullResIcon(BaseApplication.INSTANCE.getInstance().getResources(), R.mipmap.ic_app_default_logo), 0, 2, null);
            if (bitmapMakeDefaultIcon$default == null) {
                return null;
            }
            defaultLogoBmpRef = new SoftReference<>(bitmapMakeDefaultIcon$default);
        }
        return bitmapMakeDefaultIcon$default;
    }

    public final Bitmap getAppIconHasDefault(String packageName) throws PackageManager.NameNotFoundException {
        Resources resourcesForApplication;
        Drawable fullResIcon;
        Bitmap bitmap = null;
        if (TextUtils.isEmpty(packageName)) {
            return null;
        }
        Bitmap bitmap2 = BITMAP_CACHE.get(packageName);
        if (bitmap2 != null) {
            return bitmap2;
        }
        ApplicationInfo applicationInfo$default = AppUtil.getApplicationInfo$default(BaseApplication.INSTANCE.getInstance(), packageName, 0, 4, null);
        if (applicationInfo$default != null) {
            try {
                resourcesForApplication = BaseApplication.INSTANCE.getInstance().getPackageManager().getResourcesForApplication(applicationInfo$default);
            } catch (PackageManager.NameNotFoundException unused) {
                resourcesForApplication = (Resources) null;
            }
            if (resourcesForApplication != null && (fullResIcon = INSTANCE.getFullResIcon(resourcesForApplication, applicationInfo$default.icon)) != null) {
                Bitmap bitmapMakeDefaultIcon$default = makeDefaultIcon$default(INSTANCE, fullResIcon, 0, 2, null);
                BITMAP_CACHE.put(packageName, bitmapMakeDefaultIcon$default);
                bitmap = bitmapMakeDefaultIcon$default;
            }
        }
        return bitmap == null ? getAppDefaultLogo() : bitmap;
    }

    public final AppShortcut getAppShortcut(String packageName) {
        if (TextUtils.isEmpty(packageName)) {
            return null;
        }
        AppShortcut shortcutFromCache = getShortcutFromCache(packageName);
        return shortcutFromCache == null ? initAppShortcut(packageName) : shortcutFromCache;
    }

    public final InvariantDeviceProfile getInvariantDeviceProfile() {
        return invariantDeviceProfile;
    }

    public final Bitmap makeDefaultIcon(Drawable drawable, int bitmapSize) {
        return BmpUtil.drawable2Bitmap(drawable, bitmapSize);
    }

    public final void onConfigurationChanged(Configuration newConfig) {
        invariantDeviceProfile = new InvariantDeviceProfile();
    }

    public final void setInvariantDeviceProfile(InvariantDeviceProfile invariantDeviceProfile2) {
        Intrinsics.checkNotNullParameter(invariantDeviceProfile2, "<set-?>");
        invariantDeviceProfile = invariantDeviceProfile2;
    }

    public final void tryLoadAppShortcut(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        if (TextUtils.isEmpty(packageName) || getShortcutFromCache(packageName) != null) {
            return;
        }
        initAppShortcut(packageName);
    }
}
