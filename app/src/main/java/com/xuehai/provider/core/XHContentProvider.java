package com.xuehai.provider.core;

import android.content.ContentProvider;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import com.xuehai.launcher.common.log.MyLog;
import com.zaze.utils.FileUtil;
import com.zaze.utils.ZStringUtil;
import com.zaze.utils.config.ConfigHelper;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0005¢\u0006\u0002\u0010\u0002J/\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u000bH\u0016¢\u0006\u0002\u0010\fJ\u0012\u0010\r\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016JK\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0006\u001a\u00020\u00072\u000e\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u000b2\b\u0010\b\u001a\u0004\u0018\u00010\t2\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\tH\u0016¢\u0006\u0002\u0010\u0017J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J9\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\b\u001a\u0004\u0018\u00010\t2\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u000bH\u0016¢\u0006\u0002\u0010\u001cR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/xuehai/provider/core/XHContentProvider;", "Landroid/content/ContentProvider;", "()V", "flag", "", "delete", "uri", "Landroid/net/Uri;", "selection", "", "selectionArgs", "", "(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I", "getType", "insert", "values", "Landroid/content/ContentValues;", "onCreate", "", "query", "Landroid/database/Cursor;", "projection", "sortOrder", "(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;", "resetFlag", "", "transformUri", "update", "(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I", "Companion", "compat_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class XHContentProvider extends ContentProvider {
    private static final String AUTHORITY = "com.xuehai.provider";

    public static final Companion INSTANCE = new Companion(null);
    private static final int FLAG_INTERRUPT = 5;
    private static final String TAG = "XHContentProvider";
    private static final String ZTY_AUTHORITY = "com.xh.zhitongyun.provider";
    private int flag;

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0018\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/xuehai/provider/core/XHContentProvider$Companion;", "", "()V", "AUTHORITY", "", "FLAG_INTERRUPT", "", "TAG", "ZTY_AUTHORITY", "clearFlag", "", "context", "Landroid/content/Context;", "getFile", "Ljava/io/File;", "getFlag", "updateFlag", "flag", "compat_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final File getFile(Context context) {
            return new File(context.getFilesDir().getAbsolutePath() + "/provider_config");
        }

        private final int getFlag(Context context) {
            return ZStringUtil.parseInt(ConfigHelper.newInstance(getFile(context)).getProperty("flag"), 0);
        }

        private final void updateFlag(Context context, int flag) throws Throwable {
            ConfigHelper.newInstance(getFile(context)).setProperty("flag", String.valueOf(flag));
        }

        public final void clearFlag(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            FileUtil.deleteFile(getFile(context));
        }
    }

    private final void resetFlag() throws Throwable {
        this.flag = 0;
        Context context = getContext();
        if (context != null) {
            INSTANCE.updateFlag(context, this.flag);
        }
    }

    private final Uri transformUri(Uri uri) {
        ContentResolver contentResolver;
        PackageManager packageManager;
        List<ProviderInfo> listQueryContentProviders;
        boolean z = false;
        if (this.flag >= 5) {
            Context context = getContext();
            this.flag = context != null ? INSTANCE.getFlag(context) : 0;
        }
        if (!Intrinsics.areEqual("com.xuehai.provider", uri.getAuthority()) || this.flag >= 5) {
            Uri uri2 = Uri.parse(uri.getScheme() + "://com.xuehai.provider.default" + uri.getPath());
            Intrinsics.checkNotNullExpressionValue(uri2, "{\n            Uri.parse(…Y}${uri.path}\")\n        }");
            return uri2;
        }
        Uri uri3 = Uri.parse(uri.getScheme() + "://com.xh.zhitongyun.provider" + uri.getPath());
        if (this.flag > 0) {
            MyLog.i("XHContentProvider", "查询是否存在ContentProvider : " + uri3.getAuthority());
            try {
                Context context2 = getContext();
                ContentProviderClient contentProviderClientAcquireContentProviderClient = null;
                if (context2 != null && (packageManager = context2.getPackageManager()) != null && (listQueryContentProviders = packageManager.queryContentProviders((String) null, 0, 0)) != null) {
                    Intrinsics.checkNotNullExpressionValue(listQueryContentProviders, "queryContentProviders(null, 0, 0)");
                    Iterator<T> it = listQueryContentProviders.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        if (Intrinsics.areEqual(((ProviderInfo) it.next()).authority, uri.getAuthority())) {
                            z = true;
                            break;
                        }
                    }
                }
                if (z) {
                    MyLog.i("XHContentProvider", "存在ContentProvider: " + uri3.getAuthority() + ", 检测能否获取到ContentProviderClient");
                    Context context3 = getContext();
                    if (context3 != null && (contentResolver = context3.getContentResolver()) != null) {
                        contentProviderClientAcquireContentProviderClient = contentResolver.acquireContentProviderClient(uri3);
                    }
                    MyLog.i("XHContentProvider", "获取ContentProviderClient " + contentProviderClientAcquireContentProviderClient);
                    if (contentProviderClientAcquireContentProviderClient != null) {
                        resetFlag();
                    }
                    if (contentProviderClientAcquireContentProviderClient != null) {
                        contentProviderClientAcquireContentProviderClient.release();
                    }
                } else {
                    MyLog.i("XHContentProvider", "不存在ContentProvider: " + uri3.getAuthority() + ", 重置flag");
                    resetFlag();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        Intrinsics.checkNotNullExpressionValue(uri3, "{ // 若为com.xuehai.provid…}\n            }\n        }");
        return uri3;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        ContentResolver contentResolver;
        Intrinsics.checkNotNullParameter(uri, "uri");
        try {
            Context context = getContext();
            if (context == null || (contentResolver = context.getContentResolver()) == null) {
                return 0;
            }
            return contentResolver.delete(transformUri(uri), selection, selectionArgs);
        } catch (Exception e) {
            MyLog.w("XHContentProvider", "delete error uri : " + uri, e);
            return 0;
        }
    }

    @Override
    public String getType(Uri uri) {
        ContentResolver contentResolver;
        Intrinsics.checkNotNullParameter(uri, "uri");
        try {
            Context context = getContext();
            if (context == null || (contentResolver = context.getContentResolver()) == null) {
                return null;
            }
            return contentResolver.getType(transformUri(uri));
        } catch (Exception e) {
            MyLog.w("XHContentProvider", "getType error uri : " + uri, e);
            return null;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        ContentResolver contentResolver;
        Intrinsics.checkNotNullParameter(uri, "uri");
        try {
            Context context = getContext();
            if (context == null || (contentResolver = context.getContentResolver()) == null) {
                return null;
            }
            return contentResolver.insert(transformUri(uri), values);
        } catch (Exception e) {
            MyLog.w("XHContentProvider", "insert error uri : " + uri, e);
            return null;
        }
    }

    @Override
    public boolean onCreate() throws Throwable {
        Context context = getContext();
        if (context != null) {
            int flag = INSTANCE.getFlag(context) + 1;
            this.flag = flag;
            INSTANCE.updateFlag(context, flag);
        }
        MyLog.i("XHContentProvider", "onCreate: " + this.flag);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        ContentResolver contentResolver;
        Intrinsics.checkNotNullParameter(uri, "uri");
        try {
            Context context = getContext();
            if (context == null || (contentResolver = context.getContentResolver()) == null) {
                return null;
            }
            return contentResolver.query(transformUri(uri), projection, selection, selectionArgs, sortOrder);
        } catch (Exception e) {
            MyLog.w("XHContentProvider", "query error uri : " + uri, e);
            return null;
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        ContentResolver contentResolver;
        Intrinsics.checkNotNullParameter(uri, "uri");
        try {
            Context context = getContext();
            if (context == null || (contentResolver = context.getContentResolver()) == null) {
                return 0;
            }
            return contentResolver.update(transformUri(uri), values, selection, selectionArgs);
        } catch (Exception e) {
            MyLog.w("XHContentProvider", "update error uri : " + uri, e);
            return 0;
        }
    }
}
