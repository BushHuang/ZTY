package com.zaze.utils;

import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ZOnClickHelper {
    private static long clickTime;

    public interface SingleDoListener {
        void doIt(Object... objArr);
    }

    private static boolean isCanClick() {
        return Math.abs(SystemClock.uptimeMillis() - clickTime) > 400;
    }

    public static void setOnClickListener(View view, final View.OnClickListener onClickListener) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                if (ZOnClickHelper.isCanClick()) {
                    View.OnClickListener onClickListener2 = onClickListener;
                    if (onClickListener2 != null) {
                        onClickListener2.onClick(view2);
                    }
                    ZOnClickHelper.updateClickTime();
                }
            }
        });
    }

    public static void setOnItemClickListener(ListView listView, final AdapterView.OnItemClickListener onItemClickListener) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (ZOnClickHelper.isCanClick()) {
                    AdapterView.OnItemClickListener onItemClickListener2 = onItemClickListener;
                    if (onItemClickListener2 != null) {
                        onItemClickListener2.onItemClick(adapterView, view, i, j);
                    }
                    ZOnClickHelper.updateClickTime();
                }
            }
        });
    }

    public static void setSingleDoListener(SingleDoListener singleDoListener, Object... objArr) {
        if (isCanClick()) {
            singleDoListener.doIt(objArr);
            updateClickTime();
        }
    }

    private static void updateClickTime() {
        clickTime = SystemClock.uptimeMillis();
    }
}
