package pub.devrel.easypermissions.helper;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

class SupportFragmentPermissionHelper extends BaseSupportPermissionsHelper<Fragment> {
    public SupportFragmentPermissionHelper(Fragment fragment) {
        super(fragment);
    }

    @Override
    public void directRequestPermissions(int i, String... strArr) {
        getHost().requestPermissions(strArr, i);
    }

    @Override
    public Context getContext() {
        return getHost().getActivity();
    }

    @Override
    public FragmentManager getSupportFragmentManager() {
        return getHost().getChildFragmentManager();
    }

    @Override
    public boolean shouldShowRequestPermissionRationale(String str) {
        return getHost().shouldShowRequestPermissionRationale(str);
    }
}
