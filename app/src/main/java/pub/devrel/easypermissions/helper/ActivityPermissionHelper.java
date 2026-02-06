package pub.devrel.easypermissions.helper;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import pub.devrel.easypermissions.RationaleDialogFragment;

class ActivityPermissionHelper extends PermissionHelper<Activity> {
    private static final String TAG = "ActPermissionHelper";

    public ActivityPermissionHelper(Activity activity) {
        super(activity);
    }

    @Override
    public void directRequestPermissions(int i, String... strArr) {
        ActivityCompat.requestPermissions(getHost(), strArr, i);
    }

    @Override
    public Context getContext() {
        return getHost();
    }

    @Override
    public boolean shouldShowRequestPermissionRationale(String str) {
        return ActivityCompat.shouldShowRequestPermissionRationale(getHost(), str);
    }

    @Override
    public void showRequestPermissionRationale(String str, String str2, String str3, int i, int i2, String... strArr) {
        FragmentManager fragmentManager = getHost().getFragmentManager();
        if (fragmentManager.findFragmentByTag("RationaleDialogFragment") instanceof RationaleDialogFragment) {
            Log.d("ActPermissionHelper", "Found existing fragment, not showing rationale.");
        } else {
            RationaleDialogFragment.newInstance(str2, str3, str, i, i2, strArr).showAllowingStateLoss(fragmentManager, "RationaleDialogFragment");
        }
    }
}
