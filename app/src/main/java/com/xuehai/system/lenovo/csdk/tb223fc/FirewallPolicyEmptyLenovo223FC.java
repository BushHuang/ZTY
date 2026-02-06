package com.xuehai.system.lenovo.csdk.tb223fc;

import android.content.Context;
import cn.com.microtrust.firewall.aidl.AFWRes;
import cn.com.microtrust.firewall.aidl.IAFWService;
import com.xuehai.system.common.compat.FirewallPolicyDefault;
import com.xuehai.system.common.log.MdmLog;
import com.xuehai.system.lenovo.firewall.LenovoFWMdmHolder;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J2\u0010\n\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u0016\u0010\r\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u0016\u0010\u000e\u001a\u00020\u00062\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u0016\u0010\u000f\u001a\u00020\u00062\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\b\u0010\u0010\u001a\u00020\u0006H\u0016J\b\u0010\u0011\u001a\u00020\u0006H\u0016J\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/xuehai/system/lenovo/csdk/tb223fc/FirewallPolicyEmptyLenovo223FC;", "Lcom/xuehai/system/common/compat/FirewallPolicyDefault;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "addAllowApps", "", "packageNames", "", "", "addAppDomainsRules", "allowDomains", "denyDomains", "addDenyApps", "addDomainsDenyRules", "addDomainsRules", "cleanAppRules", "cleanDomainRules", "getAllowApps", "getDenyApps", "getDomainsAllowRules", "getDomainsDenyRules", "printLenovoFW", "", "setIptablesOption", "status", "Companion", "mdm_lenovo_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FirewallPolicyEmptyLenovo223FC extends FirewallPolicyDefault {
    private static final String TAG = "FirewallPolicyLenovo223FC";
    private final Context context;

    public FirewallPolicyEmptyLenovo223FC(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        LenovoFWMdmHolder.registerMdm(context);
    }

    private final void printLenovoFW() {
        AFWRes aFWResIsEnable;
        if (LenovoFWMdmHolder.INSTANCE.isMdmServiceAlive()) {
            IAFWService mdmService = LenovoFWMdmHolder.INSTANCE.getMdmService();
            StringBuilder sb = new StringBuilder();
            sb.append("联想K10 Pro防火墙开关：");
            sb.append((mdmService == null || (aFWResIsEnable = mdmService.isEnable()) == null) ? null : Boolean.valueOf(aFWResIsEnable.getOperationResult()));
            MdmLog.d("FirewallPolicyLenovo223FC", sb.toString());
            MdmLog.d("FirewallPolicyLenovo223FC", "应用防火墙白名单：" + getAllowApps());
            MdmLog.d("FirewallPolicyLenovo223FC", "应用防火墙黑名单：" + getDenyApps());
            MdmLog.d("FirewallPolicyLenovo223FC", "域名防火墙白名单：" + getDomainsAllowRules());
            MdmLog.d("FirewallPolicyLenovo223FC", "域名防火墙黑名单：" + getDomainsDenyRules());
        }
    }

    @Override
    public boolean addAllowApps(List<String> packageNames) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        return false;
    }

    @Override
    public boolean addAppDomainsRules(List<String> packageNames, List<String> allowDomains, List<String> denyDomains) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        Intrinsics.checkNotNullParameter(allowDomains, "allowDomains");
        Intrinsics.checkNotNullParameter(denyDomains, "denyDomains");
        return false;
    }

    @Override
    public boolean addDenyApps(List<String> packageNames) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        return false;
    }

    @Override
    public boolean addDomainsDenyRules(List<String> denyDomains) {
        Intrinsics.checkNotNullParameter(denyDomains, "denyDomains");
        return false;
    }

    @Override
    public boolean addDomainsRules(List<String> allowDomains) {
        Intrinsics.checkNotNullParameter(allowDomains, "allowDomains");
        return false;
    }

    @Override
    public boolean cleanAppRules() {
        AFWRes aFWResClearRules;
        IAFWService mdmService = LenovoFWMdmHolder.INSTANCE.getMdmService();
        boolean z = (mdmService == null || (aFWResClearRules = mdmService.clearRules()) == null || !aFWResClearRules.getOperationResult()) ? false : true;
        MdmLog.d("FirewallPolicyLenovo223FC", "cleanAppRules() > clearRules() >> " + z);
        printLenovoFW();
        return z;
    }

    @Override
    public boolean cleanDomainRules() {
        AFWRes aFWResClearIpHostRules;
        IAFWService mdmService = LenovoFWMdmHolder.INSTANCE.getMdmService();
        boolean z = (mdmService == null || (aFWResClearIpHostRules = mdmService.clearIpHostRules()) == null || !aFWResClearIpHostRules.getOperationResult()) ? false : true;
        MdmLog.d("FirewallPolicyLenovo223FC", "cleanDomainRules() > clearIpHostRules() >> " + z);
        printLenovoFW();
        return z;
    }

    @Override
    public List<String> getAllowApps() {
        return CollectionsKt.emptyList();
    }

    @Override
    public List<String> getDenyApps() {
        return CollectionsKt.emptyList();
    }

    @Override
    public List<String> getDomainsAllowRules() {
        return CollectionsKt.emptyList();
    }

    @Override
    public List<String> getDomainsDenyRules() {
        return CollectionsKt.emptyList();
    }

    @Override
    public boolean setIptablesOption(boolean status) {
        AFWRes enable;
        IAFWService mdmService = LenovoFWMdmHolder.INSTANCE.getMdmService();
        boolean z = (mdmService == null || (enable = mdmService.setEnable(false)) == null || !enable.getOperationResult()) ? false : true;
        MdmLog.d("FirewallPolicyLenovo223FC", "setIptablesOption(" + status + ") > setEnable(false) >> " + z);
        printLenovoFW();
        return z;
    }
}
