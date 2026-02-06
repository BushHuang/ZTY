package com.xuehai.launcher.common.config;

import android.os.Build;
import com.xuehai.launcher.common.base.BaseApplication;
import com.xuehai.system.common.log.MdmLog;
import com.zaze.utils.DisplayUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class InvariantDeviceProfile {
    private static final float ICON_SIZE_DEFINED_IN_APP_DP = 48.0f;
    private int fillResIconDpi;
    private int iconBitmapSize;
    private float iconSize;
    private float minHeightDps;
    private float minWidthDps;
    private String name;
    public int numColumns;
    public int numRows;

    public InvariantDeviceProfile() {
        DisplayUtil.init(BaseApplication.getInstance());
        this.minWidthDps = DisplayUtil.getScreenWidthDp();
        float screenHeightDp = DisplayUtil.getScreenHeightDp();
        this.minHeightDps = screenHeightDp;
        InvariantDeviceProfile invariantDeviceProfile = findClosestDeviceProfiles(this.minWidthDps, screenHeightDp, getPredefinedDeviceProfiles()).get(0);
        float f = invariantDeviceProfile.iconSize;
        this.iconSize = f;
        this.numRows = invariantDeviceProfile.numRows;
        this.numColumns = invariantDeviceProfile.numColumns;
        int iRound = Math.round(DisplayUtil.pxFromDp(f));
        this.iconBitmapSize = iRound;
        this.fillResIconDpi = getLauncherIconDensity(iRound);
        MdmLog.v("Debug[MDM]", toString());
    }

    private InvariantDeviceProfile(String str, int i, int i2, int i3, int i4, int i5) {
        this.name = str;
        this.minWidthDps = i;
        this.minHeightDps = i2;
        this.iconSize = i5;
        this.numRows = i3;
        this.numColumns = i4;
    }

    private ArrayList<InvariantDeviceProfile> findClosestDeviceProfiles(final float f, final float f2, ArrayList<InvariantDeviceProfile> arrayList) {
        Collections.sort(arrayList, new Comparator<InvariantDeviceProfile>() {
            @Override
            public int compare(InvariantDeviceProfile invariantDeviceProfile, InvariantDeviceProfile invariantDeviceProfile2) {
                return (int) (InvariantDeviceProfile.this.dist(f, f2, invariantDeviceProfile.minWidthDps, invariantDeviceProfile.minHeightDps) - InvariantDeviceProfile.this.dist(f, f2, invariantDeviceProfile2.minWidthDps, invariantDeviceProfile2.minHeightDps));
            }
        });
        return arrayList;
    }

    private int getLauncherIconDensity(int i) {
        int[] iArr = {120, 160, 213, 240, 320, 480};
        int i2 = Build.VERSION.SDK_INT >= 18 ? 640 : 480;
        for (int i3 = 5; i3 >= 0; i3--) {
            if ((iArr[i3] * 48.0f) / 160.0f >= i) {
                i2 = iArr[i3];
            }
        }
        return i2;
    }

    private ArrayList<InvariantDeviceProfile> getPredefinedDeviceProfiles() {
        ArrayList<InvariantDeviceProfile> arrayList = new ArrayList<>();
        arrayList.add(new InvariantDeviceProfile("SM-P200", 600, 912, 5, 5, 72));
        arrayList.add(new InvariantDeviceProfile("BZT-W09", 600, 960, 5, 5, 72));
        arrayList.add(new InvariantDeviceProfile("BZT-W09 虚拟导航", 600, 926, 5, 5, 72));
        arrayList.add(new InvariantDeviceProfile("Lenovo TB-8604F", 601, 913, 4, 5, 72));
        arrayList.add(new InvariantDeviceProfile("SM-P355C", 768, 1024, 4, 5, 72));
        return arrayList;
    }

    float dist(float f, float f2, float f3, float f4) {
        return (float) Math.hypot(f3 - f, f4 - f2);
    }

    public int getFillResIconDpi() {
        return this.fillResIconDpi;
    }

    public int getIconBitmapSize() {
        return this.iconBitmapSize;
    }

    public String toString() {
        return "InvariantDeviceProfile{name='" + this.name + "', iconSize=" + this.iconSize + ", iconBitmapSize=" + this.iconBitmapSize + ", fillResIconDpi=" + this.fillResIconDpi + ", numRows=" + this.numRows + ", numColumns=" + this.numColumns + ", minWidthDps=" + this.minWidthDps + ", minHeightDps=" + this.minHeightDps + '}';
    }
}
