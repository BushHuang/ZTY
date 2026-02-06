package com.xuehai.launcher.common.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.Fragment;
import com.xuehai.launcher.common.log.MyLog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J-\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ \u0010\f\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u000f\u001a\u00020\nJ\u001a\u0010\f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00112\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0013¨\u0006\u0014"}, d2 = {"Lcom/xuehai/launcher/common/util/ActivityUtil;", "", "()V", "finish", "", "context", "Landroid/content/Context;", "data", "Landroid/content/Intent;", "code", "", "(Landroid/content/Context;Landroid/content/Intent;Ljava/lang/Integer;)V", "startActivity", "", "intent", "requestCode", "fragment", "Landroidx/fragment/app/Fragment;", "target", "Ljava/lang/Class;", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ActivityUtil {
    public static final ActivityUtil INSTANCE = new ActivityUtil();

    private ActivityUtil() {
    }

    public static void finish$default(ActivityUtil activityUtil, Context context, Intent intent, Integer num, int i, Object obj) {
        if ((i & 2) != 0) {
            intent = null;
        }
        if ((i & 4) != 0) {
            num = null;
        }
        activityUtil.finish(context, intent, num);
    }

    public static boolean startActivity$default(ActivityUtil activityUtil, Context context, Intent intent, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = -1;
        }
        return activityUtil.startActivity(context, intent, i);
    }

    public final void finish(Context context, Intent data, Integer code) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (activity.isFinishing()) {
                return;
            }
            if (code != null) {
                if (data == null) {
                    activity.setResult(code.intValue());
                } else {
                    activity.setResult(code.intValue(), data);
                }
            }
            activity.finish();
        }
    }

    public final void startActivity(Fragment fragment, Class<?> target) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(target, "target");
        fragment.startActivity(new Intent(fragment.getActivity(), target));
    }

    public final boolean startActivity(Context context, Intent intent, int requestCode) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        if (requestCode >= 0) {
            try {
                if (context instanceof Activity) {
                    ((Activity) context).startActivityForResult(intent, requestCode);
                } else {
                    context.startActivity(intent);
                }
            } catch (Exception e) {
                MyLog.w("Error[MDM]", "startActivity error", e);
                return false;
            }
        }
        return true;
    }
}
