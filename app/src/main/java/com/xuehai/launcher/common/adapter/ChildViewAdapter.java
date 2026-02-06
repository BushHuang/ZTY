package com.xuehai.launcher.common.adapter;

import android.view.View;
import android.view.ViewGroup;

interface ChildViewAdapter<V, H> {
    H createViewHolder(View view);

    int getViewLayoutId();

    void setViewData(V v, H h, int i, View view, ViewGroup viewGroup);
}
