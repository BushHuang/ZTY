package pub.devrel.easypermissions;

import android.app.Activity;
import android.content.DialogInterface;
import androidx.fragment.app.Fragment;
import java.util.Arrays;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.helper.PermissionHelper;

class RationaleDialogClickListener implements DialogInterface.OnClickListener {
    private EasyPermissions.PermissionCallbacks mCallbacks;
    private RationaleDialogConfig mConfig;
    private Object mHost;
    private EasyPermissions.RationaleCallbacks mRationaleCallbacks;

    RationaleDialogClickListener(RationaleDialogFragment rationaleDialogFragment, RationaleDialogConfig rationaleDialogConfig, EasyPermissions.PermissionCallbacks permissionCallbacks, EasyPermissions.RationaleCallbacks rationaleCallbacks) {
        this.mHost = rationaleDialogFragment.getActivity();
        this.mConfig = rationaleDialogConfig;
        this.mCallbacks = permissionCallbacks;
        this.mRationaleCallbacks = rationaleCallbacks;
    }

    RationaleDialogClickListener(RationaleDialogFragmentCompat rationaleDialogFragmentCompat, RationaleDialogConfig rationaleDialogConfig, EasyPermissions.PermissionCallbacks permissionCallbacks, EasyPermissions.RationaleCallbacks rationaleCallbacks) {
        this.mHost = rationaleDialogFragmentCompat.getParentFragment() != null ? rationaleDialogFragmentCompat.getParentFragment() : rationaleDialogFragmentCompat.getActivity();
        this.mConfig = rationaleDialogConfig;
        this.mCallbacks = permissionCallbacks;
        this.mRationaleCallbacks = rationaleCallbacks;
    }

    private void notifyPermissionDenied() {
        EasyPermissions.PermissionCallbacks permissionCallbacks = this.mCallbacks;
        if (permissionCallbacks != null) {
            permissionCallbacks.onPermissionsDenied(this.mConfig.requestCode, Arrays.asList(this.mConfig.permissions));
        }
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        int i2 = this.mConfig.requestCode;
        if (i != -1) {
            EasyPermissions.RationaleCallbacks rationaleCallbacks = this.mRationaleCallbacks;
            if (rationaleCallbacks != null) {
                rationaleCallbacks.onRationaleDenied(i2);
            }
            notifyPermissionDenied();
            return;
        }
        String[] strArr = this.mConfig.permissions;
        EasyPermissions.RationaleCallbacks rationaleCallbacks2 = this.mRationaleCallbacks;
        if (rationaleCallbacks2 != null) {
            rationaleCallbacks2.onRationaleAccepted(i2);
        }
        Object obj = this.mHost;
        if (obj instanceof Fragment) {
            PermissionHelper.newInstance((Fragment) obj).directRequestPermissions(i2, strArr);
        } else {
            if (!(obj instanceof Activity)) {
                throw new RuntimeException("Host must be an Activity or Fragment!");
            }
            PermissionHelper.newInstance((Activity) obj).directRequestPermissions(i2, strArr);
        }
    }
}
