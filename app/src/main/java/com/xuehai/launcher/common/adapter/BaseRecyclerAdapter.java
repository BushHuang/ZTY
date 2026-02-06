package com.xuehai.launcher.common.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseRecyclerAdapter<V, H extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<H> implements ResourceAdapter {
    private Context context;
    private final List<V> dataList = new ArrayList();

    public BaseRecyclerAdapter(Context context, Collection<V> collection) {
        this.context = context;
        setDataList(collection, false);
    }

    public abstract H createViewHolder(View view);

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

    public List<V> getDataList() {
        return this.dataList;
    }

    @Override
    public Drawable getDrawable(int i) {
        return ContextCompat.getDrawable(this.context, i);
    }

    public V getItem(int i) {
        if (i < 0 || i >= this.dataList.size()) {
            return null;
        }
        return this.dataList.get(i);
    }

    @Override
    public int getItemCount() {
        return this.dataList.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public String getString(int i, Object... objArr) {
        return this.context.getString(i, objArr);
    }

    public abstract int getViewLayoutId();

    public abstract void onBindView(H h, V v, int i);

    @Override
    public void onBindViewHolder(H h, int i) {
        onBindView(h, getItem(i), i);
    }

    @Override
    public H onCreateViewHolder(ViewGroup viewGroup, int i) {
        return (H) createViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(getViewLayoutId(), viewGroup, false));
    }

    public void setDataList(Collection<V> collection) {
        setDataList(collection, true);
    }

    protected void setDataList(Collection<V> collection, boolean z) {
        this.dataList.clear();
        if (collection != null && collection.size() > 0) {
            this.dataList.addAll(collection);
        }
        if (z) {
            notifyDataSetChanged();
        }
    }
}
