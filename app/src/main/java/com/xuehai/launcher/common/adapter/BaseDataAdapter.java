package com.xuehai.launcher.common.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.BaseAdapter;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

abstract class BaseDataAdapter<V> extends BaseAdapter implements ResourceAdapter {
    private Context context;
    private final List<V> dataList = new ArrayList();

    BaseDataAdapter(Context context, Collection<V> collection) {
        this.context = context;
        setDataList(collection, false);
    }

    private void setDataList(Collection<V> collection, boolean z) {
        this.dataList.clear();
        if (collection != null && collection.size() > 0) {
            this.dataList.addAll(collection);
        }
        if (z) {
            notifyDataSetChanged();
        }
    }

    @Override
    public <T extends View> T findView(View view, int i) {
        return (T) view.findViewById(i);
    }

    @Override
    public Bitmap getBitmap(int i) {
        return BitmapFactory.decodeResource(this.context.getResources(), i);
    }

    @Override
    public int getColor(int i) {
        return ContextCompat.getColor(this.context, i);
    }

    public Context getContext() {
        return this.context;
    }

    @Override
    public int getCount() {
        return this.dataList.size();
    }

    public List<V> getDataList() {
        return this.dataList;
    }

    @Override
    public Drawable getDrawable(int i) {
        return ContextCompat.getDrawable(this.context, i);
    }

    @Override
    public V getItem(int i) {
        return this.dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public String getString(int i, Object... objArr) {
        return this.context.getString(i, objArr);
    }

    public void setDataList(List<V> list) {
        setDataList(list, true);
    }
}
