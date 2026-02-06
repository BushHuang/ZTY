package com.analysys;

import android.content.Context;
import android.net.Uri;

public class e {
    public static Uri a(Context context) {
        return Uri.parse("content://" + context.getPackageName() + ".AnsContentProvider/ e_fz ");
    }

    public static Uri b(Context context) {
        return Uri.parse("content://" + context.getPackageName() + ".AnsContentProvider/sp");
    }

    public static Uri c(Context context) {
        return Uri.parse("content://" + context.getPackageName() + ".AnsContentProvider/RAM");
    }

    public static Uri d(Context context) {
        return Uri.parse("content://" + context.getPackageName() + ".AnsContentProvider/ipc");
    }
}
