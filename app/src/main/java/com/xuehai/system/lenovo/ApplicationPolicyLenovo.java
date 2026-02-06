package com.xuehai.system.lenovo;

import android.app.mia.MiaMdmPolicyManager;
import android.content.Context;
import com.xuehai.system.common.compat.ApplicationPolicyDefault;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0002J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000bH\u0016J\u0018\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000bH\u0016J\u0010\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u000bH\u0016J\u0010\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u000bH\u0016J\u0018\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000bH\u0016J\u0010\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000bH\u0016J\u0010\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000bH\u0016J\u0010\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000bH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/xuehai/system/lenovo/ApplicationPolicyLenovo;", "Lcom/xuehai/system/common/compat/ApplicationPolicyDefault;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "policy", "Landroid/app/mia/MiaMdmPolicyManager;", "clearApplicationUninstallRule", "", "getUnInstallPackageWhiteList", "", "", "installApplication", "", "apkFilePath", "removeDefaultLauncher", "packageName", "className", "setApplicationUninstallationDisabled", "setApplicationUninstallationEnabled", "setDefaultLauncher", "setDisableApplication", "setEnableApplication", "uninstallApplication", "mdm_lenovo_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ApplicationPolicyLenovo extends ApplicationPolicyDefault {
    private final MiaMdmPolicyManager policy;

    public ApplicationPolicyLenovo(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.policy = new MiaMdmPolicyManager(context);
    }

    private final List<String> getUnInstallPackageWhiteList() {
        List<String> unInstallPackageWhiteList = this.policy.getUnInstallPackageWhiteList();
        return unInstallPackageWhiteList == null ? new ArrayList() : unInstallPackageWhiteList;
    }

    @Override
    public void clearApplicationUninstallRule() {
        MiaMdmPolicyManager miaMdmPolicyManager = this.policy;
        miaMdmPolicyManager.removeUnInstallPackages(miaMdmPolicyManager.getUnInstallPackageWhiteList());
    }

    @Override
    public boolean installApplication(String apkFilePath) {
        Intrinsics.checkNotNullParameter(apkFilePath, "apkFilePath");
        this.policy.silentInstall(apkFilePath);
        return true;
    }

    @Override
    public boolean removeDefaultLauncher(String packageName, String className) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(className, "className");
        return this.policy.removeDefaultApplication();
    }

    @Override
    public void setApplicationUninstallationDisabled(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        MiaMdmPolicyManager miaMdmPolicyManager = this.policy;
        List<String> unInstallPackageWhiteList = getUnInstallPackageWhiteList();
        unInstallPackageWhiteList.add(packageName);
        miaMdmPolicyManager.addUnInstallPackages(unInstallPackageWhiteList);
    }

    @Override
    public void setApplicationUninstallationEnabled(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        this.policy.removeUnInstallPackages(Collections.singletonList(packageName));
    }

    @Override
    public boolean setDefaultLauncher(String packageName, String className) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(className, "className");
        return this.policy.setDefaultApplication(packageName);
    }

    @Override
    public boolean setDisableApplication(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        return this.policy.setAppEnable(packageName, false);
    }

    @Override
    public boolean setEnableApplication(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        return this.policy.setAppEnable(packageName, true);
    }

    @Override
    public boolean uninstallApplication(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        this.policy.silentUnInstall(packageName);
        return true;
    }
}
