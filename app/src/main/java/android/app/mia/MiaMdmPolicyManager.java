package android.app.mia;

import android.content.Context;
import android.graphics.Bitmap;
import java.util.List;

public class MiaMdmPolicyManager {
    private static final Bitmap Bitmap = null;
    private List List;

    public MiaMdmPolicyManager(Context context) {
    }

    public List<String> APPIPBlackListRead() {
        return null;
    }

    public List<String> APPIPWhiteListRead() {
        return null;
    }

    public boolean AddAppBlackRule(String str) {
        return false;
    }

    public boolean AddAppWhiteRule(String str) {
        return false;
    }

    public boolean ClearAppRules() {
        return false;
    }

    public boolean ClearURLListRules() {
        return false;
    }

    public void addInstallPackages(List<String> list) {
    }

    public void addUnInstallPackages(List<String> list) {
    }

    public List<String> getInstallPackageWhiteList() {
        return null;
    }

    public List<String> getUnInstallPackageWhiteList() {
        return null;
    }

    public boolean isExternalStorageDisabled() {
        return false;
    }

    public boolean isForbidCamera() {
        return false;
    }

    public boolean isInstallAppWL(String str) {
        return false;
    }

    public boolean isScreenshotDisabled() {
        return false;
    }

    public boolean isUnInstallAppWL(String str) {
        return false;
    }

    public boolean isUsbDisabled() {
        return false;
    }

    public boolean removeDefaultApplication() {
        return false;
    }

    public void removeInstallPackages(List<String> list) {
    }

    public void removeUnInstallPackages(List<String> list) {
    }

    public boolean setAppEnable(String str, boolean z) {
        return false;
    }

    public void setCamera(boolean z) {
    }

    public boolean setDefaultApplication(String str) {
        return false;
    }

    public void setDefaultInput(String str) {
    }

    public void setExternalStorageDisabled(boolean z) {
    }

    public void setScreenshotDisabled(boolean z) {
    }

    public void setUsbDebuggingEnabled(boolean z) {
    }

    public void silentInstall(String str) {
    }

    public void silentUnInstall(String str) {
    }

    public boolean updateSystemTime(String str) {
        return false;
    }

    public List<String> urlBlackListRead() {
        return null;
    }

    public boolean urlBlackListWrite(List<String> list) {
        return false;
    }

    public boolean urlSetEnable(boolean z) {
        return false;
    }

    public List<String> urlWhiteListRead() {
        return null;
    }

    public boolean urlWhiteListWrite(List<String> list) {
        return false;
    }
}
