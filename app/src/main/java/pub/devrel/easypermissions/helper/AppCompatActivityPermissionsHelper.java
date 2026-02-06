package pub.devrel.easypermissions.helper;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

class AppCompatActivityPermissionsHelper extends BaseSupportPermissionsHelper<AppCompatActivity> {
    public AppCompatActivityPermissionsHelper(AppCompatActivity appCompatActivity) {
        super(appCompatActivity);
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
    public FragmentManager getSupportFragmentManager() {
        return getHost().getSupportFragmentManager();
    }

    @Override
    public boolean shouldShowRequestPermissionRationale(String str) {
        return ActivityCompat.shouldShowRequestPermissionRationale(getHost(), str);
    }
}
