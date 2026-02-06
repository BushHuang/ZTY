package com.xuehai.system.common.compat.root;

import android.content.Context;
import com.xuehai.system.common.compat.ApplicationPolicyDefault;
import com.xuehai.system.common.log.MdmLog;
import com.xuehai.system.common.util.Command;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0016\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\bH\u0016¨\u0006\f"}, d2 = {"Lcom/xuehai/system/common/compat/root/ApplicationPolicyRoot;", "Lcom/xuehai/system/common/compat/ApplicationPolicyDefault;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "installApplication", "", "apkFilePath", "", "uninstallApplication", "packageName", "Companion", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class ApplicationPolicyRoot extends ApplicationPolicyDefault {
    private static final String TAG = "ApplicationPolicyDefault";

    public ApplicationPolicyRoot(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override
    public boolean installApplication(String apkFilePath) {
        Intrinsics.checkNotNullParameter(apkFilePath, "apkFilePath");
        MdmLog.i("ApplicationPolicyDefault", "开始静默安装 " + apkFilePath);
        if (Command.execRootCmdForRes("pm install -r " + apkFilePath).isSuccess()) {
            MdmLog.i("ApplicationPolicyDefault", "静默安装成功!");
            return true;
        }
        MdmLog.i("ApplicationPolicyDefault", "静默安装失败!");
        return false;
    }

    @Override
    public boolean uninstallApplication(String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        MdmLog.i("ApplicationPolicyDefault", "开始静默卸载 " + packageName);
        if (Command.execRootCmdForRes("pm uninstall " + packageName).isSuccess()) {
            MdmLog.i("ApplicationPolicyDefault", "静默卸载成功!");
            return true;
        }
        MdmLog.i("ApplicationPolicyDefault", "静默卸载失败!");
        return false;
    }
}
