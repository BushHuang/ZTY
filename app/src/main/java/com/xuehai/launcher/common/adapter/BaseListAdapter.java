package com.xuehai.launcher.common.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.Collection;
import java.util.List;

public abstract class BaseListAdapter<V, H> extends BaseDataAdapter<V> implements ChildViewAdapter<V, H> {
    private OnItemClickListener<V> onItemClickListener;

    public BaseListAdapter(Context context, Collection<V> collection) {
        super(context, collection);
    }

    @Override
    public View findView(View view, int i) {
        return super.findView(view, i);
    }

    @Override
    public Bitmap getBitmap(int i) {
        return super.getBitmap(i);
    }

    @Override
    public int getColor(int i) {
        return super.getColor(i);
    }

    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public List getDataList() {
        return super.getDataList();
    }

    @Override
    public Drawable getDrawable(int i) {
        return super.getDrawable(i);
    }

    @Override
    public Object getItem(int i) {
        return super.getItem(i);
    }

    @Override
    public long getItemId(int i) {
        return super.getItemId(i);
    }

    @Override
    public String getString(int i, Object[] objArr) {
        return super.getString(i, objArr);
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        Object tag;
        try {
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(getViewLayoutId(), viewGroup, false);
                tag = createViewHolder(view);
                view.setTag(tag);
            } else {
                tag = view.getTag();
            }
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view2) {
                    BaseListAdapter baseListAdapter = BaseListAdapter.this;
                    baseListAdapter.onItemClick(view2, baseListAdapter.getItem(i), i);
                }
            });
            setViewData(getItem(i), tag, i, view, viewGroup);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    protected void onItemClick(View view, V v, int i) {
        OnItemClickListener<V> onItemClickListener = this.onItemClickListener;
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(view, v, i);
        }
    }

    @Override
    public void setDataList(List list) {
        super.setDataList(list);
    }

    public void setOnItemClickListener(OnItemClickListener<V> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
