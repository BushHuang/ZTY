package com.xuehai.system.common.compat.root;

import android.content.Context;
import com.xuehai.system.common.compat.FirewallPolicyDefault;
import com.xuehai.system.common.log.MdmLog;
import com.xuehai.system.common.util.Command;
import com.xuehai.system.common.util.IptableApi;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0016\u0018\u0000 \r2\u00020\u0001:\u0001\rB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\b\u0010\n\u001a\u00020\u0006H\u0016J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/xuehai/system/common/compat/root/FirewallPolicyRoot;", "Lcom/xuehai/system/common/compat/FirewallPolicyDefault;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "addAllowApps", "", "packageNames", "", "", "cleanAppRules", "setIptablesOption", "status", "Companion", "mdm_std_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class FirewallPolicyRoot extends FirewallPolicyDefault {
    private static final String TAG = "FirewallPolicyRoot";
    private final Context context;

    public FirewallPolicyRoot(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override
    public boolean addAllowApps(List<String> packageNames) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        MdmLog.i("FirewallPolicyRoot", "开始执行 Root 网络防火墙规则...");
        return IptableApi.applyRules(this.context, packageNames, true);
    }

    @Override
    public boolean cleanAppRules() {
        return Command.execRootCmdForRes("iptables -F").isSuccess();
    }

    @Override
    public boolean setIptablesOption(boolean status) {
        IptableApi.setEnabled(this.context, status);
        return true;
    }
}
