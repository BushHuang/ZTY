package com.xuehai.launcher.common.config;

import android.os.Build;
import com.xuehai.launcher.common.base.BaseApplication;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\n\u001a\u00020\u0004J\u0006\u0010\u000b\u001a\u00020\u0004J\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\u0004J\u0006\u0010\u000f\u001a\u00020\u0004J\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u0011J\u0006\u0010\u0013\u001a\u00020\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0014"}, d2 = {"Lcom/xuehai/launcher/common/config/ClientConfig;", "", "()V", "APP_CODE", "", "CLIENT_STUDENT", "CLIENT_TEACHER", "clientName", "getClientName", "()Ljava/lang/String;", "getAppId", "getAppVersion", "getAppVersionCode", "", "getLauncherActivity", "getPackageName", "is2C", "", "isStudentClient", "isTeacherClient", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ClientConfig {
    public static final String APP_CODE = "CA106002";
    public static final String CLIENT_STUDENT = "com.xuehai.launcher";
    public static final String CLIENT_TEACHER = "com.xuehai.response_launcher_teacher";
    public static final ClientConfig INSTANCE;
    private static final String clientName;

    static {
        ClientConfig clientConfig = new ClientConfig();
        INSTANCE = clientConfig;
        clientName = clientConfig.isStudentClient() ? "学生端" : "教师端";
    }

    private ClientConfig() {
    }

    public final String getAppId() {
        return is2C() ? "mdm_stu_to_c" : Intrinsics.areEqual("com.xuehai.launcher", BaseApplication.INSTANCE.getInstance().getPackageName()) ? "mdm_stu" : "mdm_tea";
    }

    public final String getAppVersion() {
        return BaseApplication.INSTANCE.getInstance().getVersion();
    }

    public final int getAppVersionCode() {
        return BaseApplication.INSTANCE.getInstance().getVersionCode();
    }

    public final String getClientName() {
        return clientName;
    }

    public final String getLauncherActivity() {
        return "com.xuehai.launcher.MainActivity";
    }

    public final String getPackageName() {
        String packageName = (Build.VERSION.SDK_INT >= 24 ? BaseApplication.INSTANCE.getInstance().createDeviceProtectedStorageContext() : BaseApplication.INSTANCE.getInstance()).getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "ctx.packageName");
        return packageName;
    }

    public final boolean is2C() {
        return Intrinsics.areEqual("toB", "toC");
    }

    public final boolean isStudentClient() {
        return Intrinsics.areEqual("com.xuehai.launcher", getPackageName());
    }

    public final boolean isTeacherClient() {
        return !isStudentClient();
    }
}
