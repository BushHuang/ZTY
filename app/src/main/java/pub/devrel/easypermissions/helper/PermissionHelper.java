package pub.devrel.easypermissions.helper;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import java.util.Iterator;
import java.util.List;

public abstract class PermissionHelper<T> {
    private T mHost;

    public PermissionHelper(T t) {
        this.mHost = t;
    }

    public static PermissionHelper<? extends Activity> newInstance(Activity activity) {
        return Build.VERSION.SDK_INT < 23 ? new LowApiPermissionsHelper(activity) : activity instanceof AppCompatActivity ? new AppCompatActivityPermissionsHelper((AppCompatActivity) activity) : new ActivityPermissionHelper(activity);
    }

    public static PermissionHelper<Fragment> newInstance(Fragment fragment) {
        return Build.VERSION.SDK_INT < 23 ? new LowApiPermissionsHelper(fragment) : new SupportFragmentPermissionHelper(fragment);
    }

    private boolean shouldShowRationale(String... strArr) {
        for (String str : strArr) {
            if (shouldShowRequestPermissionRationale(str)) {
                return true;
            }
        }
        return false;
    }

    public abstract void directRequestPermissions(int i, String... strArr);

    public abstract Context getContext();

    public T getHost() {
        return this.mHost;
    }

    public boolean permissionPermanentlyDenied(String str) {
        return !shouldShowRequestPermissionRationale(str);
    }

    public void requestPermissions(String str, String str2, String str3, int i, int i2, String... strArr) {
        if (shouldShowRationale(strArr)) {
            showRequestPermissionRationale(str, str2, str3, i, i2, strArr);
        } else {
            directRequestPermissions(i2, strArr);
        }
    }

    public abstract boolean shouldShowRequestPermissionRationale(String str);

    public abstract void showRequestPermissionRationale(String str, String str2, String str3, int i, int i2, String... strArr);

    public boolean somePermissionDenied(String... strArr) {
        return shouldShowRationale(strArr);
    }

    public boolean somePermissionPermanentlyDenied(List<String> list) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (permissionPermanentlyDenied(it.next())) {
                return true;
            }
        }
        return false;
    }
}
