package pub.devrel.easypermissions;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;

class RationaleDialogConfig {
    private static final String KEY_NEGATIVE_BUTTON = "negativeButton";
    private static final String KEY_PERMISSIONS = "permissions";
    private static final String KEY_POSITIVE_BUTTON = "positiveButton";
    private static final String KEY_RATIONALE_MESSAGE = "rationaleMsg";
    private static final String KEY_REQUEST_CODE = "requestCode";
    private static final String KEY_THEME = "theme";
    String negativeButton;
    String[] permissions;
    String positiveButton;
    String rationaleMsg;
    int requestCode;
    int theme;

    RationaleDialogConfig(Bundle bundle) {
        this.positiveButton = bundle.getString("positiveButton");
        this.negativeButton = bundle.getString("negativeButton");
        this.rationaleMsg = bundle.getString("rationaleMsg");
        this.theme = bundle.getInt("theme");
        this.requestCode = bundle.getInt("requestCode");
        this.permissions = bundle.getStringArray("permissions");
    }

    RationaleDialogConfig(String str, String str2, String str3, int i, int i2, String[] strArr) {
        this.positiveButton = str;
        this.negativeButton = str2;
        this.rationaleMsg = str3;
        this.theme = i;
        this.requestCode = i2;
        this.permissions = strArr;
    }

    AlertDialog createFrameworkDialog(Context context, DialogInterface.OnClickListener onClickListener) {
        return (this.theme > 0 ? new AlertDialog.Builder(context, this.theme) : new AlertDialog.Builder(context)).setCancelable(false).setPositiveButton(this.positiveButton, onClickListener).setNegativeButton(this.negativeButton, onClickListener).setMessage(this.rationaleMsg).create();
    }

    androidx.appcompat.app.AlertDialog createSupportDialog(Context context, DialogInterface.OnClickListener onClickListener) {
        int i = this.theme;
        return (i > 0 ? new AlertDialog.Builder(context, i) : new AlertDialog.Builder(context)).setCancelable(false).setPositiveButton(this.positiveButton, onClickListener).setNegativeButton(this.negativeButton, onClickListener).setMessage(this.rationaleMsg).create();
    }

    Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("positiveButton", this.positiveButton);
        bundle.putString("negativeButton", this.negativeButton);
        bundle.putString("rationaleMsg", this.rationaleMsg);
        bundle.putInt("theme", this.theme);
        bundle.putInt("requestCode", this.requestCode);
        bundle.putStringArray("permissions", this.permissions);
        return bundle;
    }
}
