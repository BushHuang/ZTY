package androidx.databinding.adapters;

import android.widget.TabHost;
import androidx.databinding.InverseBindingListener;

public class TabHostBindingAdapter {
    public static int getCurrentTab(TabHost tabHost) {
        return tabHost.getCurrentTab();
    }

    public static String getCurrentTabTag(TabHost tabHost) {
        return tabHost.getCurrentTabTag();
    }

    public static void setCurrentTab(TabHost tabHost, int i) {
        if (tabHost.getCurrentTab() != i) {
            tabHost.setCurrentTab(i);
        }
    }

    public static void setCurrentTabTag(TabHost tabHost, String str) {
        if (tabHost.getCurrentTabTag() != str) {
            tabHost.setCurrentTabByTag(str);
        }
    }

    public static void setListeners(TabHost tabHost, final TabHost.OnTabChangeListener onTabChangeListener, final InverseBindingListener inverseBindingListener) {
        if (inverseBindingListener == null) {
            tabHost.setOnTabChangedListener(onTabChangeListener);
        } else {
            tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
                @Override
                public void onTabChanged(String str) {
                    TabHost.OnTabChangeListener onTabChangeListener2 = onTabChangeListener;
                    if (onTabChangeListener2 != null) {
                        onTabChangeListener2.onTabChanged(str);
                    }
                    inverseBindingListener.onChange();
                }
            });
        }
    }
}
