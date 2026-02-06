package com.xuehai.system.lenovo;

import android.app.mia.MiaMdmPolicyManager;
import android.content.Context;
import com.xuehai.system.common.compat.FirewallPolicyDefault;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u0016\u0010\f\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\b\u0010\r\u001a\u00020\bH\u0016J\b\u0010\u000e\u001a\u00020\bH\u0016J\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u000e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u0010\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\bH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/xuehai/system/lenovo/FirewallPolicyLenovo;", "Lcom/xuehai/system/common/compat/FirewallPolicyDefault;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "policy", "Landroid/app/mia/MiaMdmPolicyManager;", "addAllowApps", "", "packageNames", "", "", "addDenyApps", "cleanAppRules", "cleanDomainRules", "getAllowApps", "getDenyApps", "setIptablesOption", "status", "mdm_lenovo_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FirewallPolicyLenovo extends FirewallPolicyDefault {
    private final MiaMdmPolicyManager policy;

    public FirewallPolicyLenovo(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.policy = new MiaMdmPolicyManager(context);
    }

    @Override
    public boolean addAllowApps(List<String> packageNames) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        Iterator<String> it = packageNames.iterator();
        boolean zAddAppWhiteRule = true;
        while (it.hasNext()) {
            zAddAppWhiteRule = this.policy.AddAppWhiteRule(it.next());
        }
        return zAddAppWhiteRule;
    }

    @Override
    public boolean addDenyApps(List<String> packageNames) {
        Intrinsics.checkNotNullParameter(packageNames, "packageNames");
        Iterator<String> it = packageNames.iterator();
        boolean zAddAppBlackRule = true;
        while (it.hasNext()) {
            zAddAppBlackRule = this.policy.AddAppBlackRule(it.next());
        }
        return zAddAppBlackRule;
    }

    @Override
    public boolean cleanAppRules() {
        return this.policy.ClearAppRules();
    }

    @Override
    public boolean cleanDomainRules() {
        this.policy.urlSetEnable(false);
        return this.policy.ClearURLListRules();
    }

    @Override
    public List<String> getAllowApps() {
        List<String> listAPPIPWhiteListRead = this.policy.APPIPWhiteListRead();
        return listAPPIPWhiteListRead == null ? CollectionsKt.emptyList() : listAPPIPWhiteListRead;
    }

    @Override
    public List<String> getDenyApps() {
        List<String> listAPPIPBlackListRead = this.policy.APPIPBlackListRead();
        return listAPPIPBlackListRead == null ? CollectionsKt.emptyList() : listAPPIPBlackListRead;
    }

    @Override
    public boolean setIptablesOption(boolean status) {
        return this.policy.urlSetEnable(status);
    }
}
