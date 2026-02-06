package com.xuehai.system.lenovo.csdk.tb_x6c6f;

import android.content.Context;
import cn.com.microtrust.firewall.afw.AFWUtils;
import cn.com.microtrust.firewall.aidl.AFWRes;
import cn.com.microtrust.firewall.aidl.IAFWService;
import com.xuehai.system.common.compat.FirewallPolicyDefault;
import com.xuehai.system.lenovo.firewall.LenovoFWMdmHolder;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J2\u0010\n\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u0016\u0010\r\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u0016\u0010\u000e\u001a\u00020\u00062\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u0016\u0010\u000f\u001a\u00020\u00062\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\b\u0010\u0010\u001a\u00020\u0006H\u0016J\b\u0010\u0011\u001a\u00020\u0006H\u0016J\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u0010\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/xuehai/system/lenovo/csdk/tb_x6c6f/FirewallPolicyLenovoX6C6F;", "Lcom/xuehai/system/common/compat/FirewallPolicyDefault;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "addAllowApps", "", "packageNames", "", "", "addAppDomainsRules", "allowDomains", "denyDomains", "addDenyApps", "addDomainsDenyRules", "addDomainsRules", "cleanAppRules", "cleanDomainRules", "getAllowApps", "getDenyApps", "getDomainsAllowRules", "getDomainsDenyRules", "setIptablesOption", "status", "mdm_lenovo_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FirewallPolicyLenovoX6C6F extends FirewallPolicyDefault {
    private final Context context;

    public FirewallPolicyLenovoX6C6F(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        LenovoFWMdmHolder.registerMdm(context);
    }

    @Override
    public boolean addAllowApps(List<String> packageNames) {
        boolean z;
        AFWRes aFWResAddAppBlackRule;
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        IAFWService mdmService = LenovoFWMdmHolder.INSTANCE.getMdmService();
        Iterator<String> it = packageNames.iterator();
        loop0: while (true) {
            while (it.hasNext()) {
                z = AFWUtils.addAppWhiteRule(this.context, mdmService, it.next()) && z;
            }
        }
        return (mdmService != null && (aFWResAddAppBlackRule = mdmService.addAppBlackRule("*")) != null && aFWResAddAppBlackRule.getOperationResult()) && z;
    }

    @Override
    public boolean addAppDomainsRules(List<String> packageNames, List<String> allowDomains, List<String> denyDomains) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        Intrinsics.checkNotNullParameter(allowDomains, "allowDomains");
        Intrinsics.checkNotNullParameter(denyDomains, "denyDomains");
        if (LenovoFWMdmHolder.INSTANCE.getMdmService() == null) {
            return false;
        }
        return addDomainsDenyRules(denyDomains) && (addDomainsRules(allowDomains) && addAllowApps(packageNames));
    }

    @Override
    public boolean addDenyApps(List<String> packageNames) {
        boolean z;
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        Iterator<String> it = packageNames.iterator();
        while (true) {
            while (it.hasNext()) {
                z = AFWUtils.addAppBlackRule(this.context, LenovoFWMdmHolder.INSTANCE.getMdmService(), it.next()) && z;
            }
            return z;
        }
    }

    @Override
    public boolean addDomainsDenyRules(List<String> denyDomains) {
        boolean z;
        AFWRes aFWResAddBlackRule;
        Intrinsics.checkNotNullParameter(denyDomains, "denyDomains");
        while (true) {
            for (String str : denyDomains) {
                IAFWService mdmService = LenovoFWMdmHolder.INSTANCE.getMdmService();
                z = (mdmService != null && (aFWResAddBlackRule = mdmService.addBlackRule(str)) != null && aFWResAddBlackRule.getOperationResult()) && z;
            }
            return z;
        }
    }

    @Override
    public boolean addDomainsRules(List<String> allowDomains) {
        boolean z;
        AFWRes aFWResAddBlackRule;
        AFWRes aFWResAddWhiteRule;
        Intrinsics.checkNotNullParameter(allowDomains, "allowDomains");
        IAFWService mdmService = LenovoFWMdmHolder.INSTANCE.getMdmService();
        Iterator<T> it = allowDomains.iterator();
        loop0: while (true) {
            while (it.hasNext()) {
                z = (mdmService != null && (aFWResAddWhiteRule = mdmService.addWhiteRule((String) it.next())) != null && aFWResAddWhiteRule.getOperationResult()) && z;
            }
        }
        return (mdmService != null && (aFWResAddBlackRule = mdmService.addBlackRule("*")) != null && aFWResAddBlackRule.getOperationResult()) && z;
    }

    @Override
    public boolean cleanAppRules() {
        AFWRes aFWResClearRules;
        IAFWService mdmService = LenovoFWMdmHolder.INSTANCE.getMdmService();
        return (mdmService == null || (aFWResClearRules = mdmService.clearRules()) == null || !aFWResClearRules.getOperationResult()) ? false : true;
    }

    @Override
    public boolean cleanDomainRules() {
        AFWRes aFWResClearIpHostRules;
        IAFWService mdmService = LenovoFWMdmHolder.INSTANCE.getMdmService();
        return (mdmService == null || (aFWResClearIpHostRules = mdmService.clearIpHostRules()) == null || !aFWResClearIpHostRules.getOperationResult()) ? false : true;
    }

    @Override
    public List<String> getAllowApps() {
        List<String> appWhiteRules = AFWUtils.getAppWhiteRules(this.context, LenovoFWMdmHolder.INSTANCE.getMdmService());
        Intrinsics.checkNotNullExpressionValue(appWhiteRules, "getAppWhiteRules(context…dmHolder.getMdmService())");
        return appWhiteRules;
    }

    @Override
    public List<String> getDenyApps() {
        List<String> appBlackRules = AFWUtils.getAppBlackRules(this.context, LenovoFWMdmHolder.INSTANCE.getMdmService());
        Intrinsics.checkNotNullExpressionValue(appBlackRules, "getAppBlackRules(context…dmHolder.getMdmService())");
        return appBlackRules;
    }

    @Override
    public List<String> getDomainsAllowRules() {
        List<String> whiteRules = AFWUtils.getWhiteRules(LenovoFWMdmHolder.INSTANCE.getMdmService());
        Intrinsics.checkNotNullExpressionValue(whiteRules, "getWhiteRules(LenovoFWMdmHolder.getMdmService())");
        return whiteRules;
    }

    @Override
    public List<String> getDomainsDenyRules() {
        List<String> blackRules = AFWUtils.getBlackRules(LenovoFWMdmHolder.INSTANCE.getMdmService());
        Intrinsics.checkNotNullExpressionValue(blackRules, "getBlackRules(LenovoFWMdmHolder.getMdmService())");
        return blackRules;
    }

    @Override
    public boolean setIptablesOption(boolean status) {
        AFWRes enable;
        IAFWService mdmService = LenovoFWMdmHolder.INSTANCE.getMdmService();
        return (mdmService == null || (enable = mdmService.setEnable(status)) == null || !enable.getOperationResult()) ? false : true;
    }
}
