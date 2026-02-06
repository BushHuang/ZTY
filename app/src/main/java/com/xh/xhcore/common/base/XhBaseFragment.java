package com.xh.xhcore.common.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import com.xh.xhcore.common.base.XhBasePresenter;
import com.xh.xhcore.common.config.XHAppConfigProxy;
import com.xh.xhcore.common.util.XHLog;

public abstract class XhBaseFragment<V, T extends XhBasePresenter<V>> extends Fragment implements IBaseView {
    protected View mRootView;
    public T presenter;

    public void Lg(String str) {
        XHLog.d(getClass().getSimpleName(), str);
    }

    public boolean containsActiveView() {
        return this.mRootView != null;
    }

    protected abstract void getBundleData();

    @Override
    public void hideProgress() {
    }

    public abstract T initPresenter();

    protected abstract void initView();

    protected abstract int layoutId();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        T t = (T) initPresenter();
        this.presenter = t;
        t.attach(this);
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getBundleData();
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(layoutId(), viewGroup, false);
        if (XHAppConfigProxy.getInstance().getAutoBindUnbindButterKnife()) {
            ButterKnife.bind(this, viewInflate);
        }
        this.mRootView = viewInflate;
        initView();
        return viewInflate;
    }

    @Override
    public void onDestroyView() {
        if (XHAppConfigProxy.getInstance().getAutoBindUnbindButterKnife()) {
            ButterKnife.unbind(this);
        }
        this.mRootView = null;
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        this.presenter.detach();
        super.onDetach();
    }

    @Override
    public void requestFailed(String... strArr) {
    }

    @Override
    public void showProgress(String... strArr) {
    }

    @Override
    public void showToast(String str) {
    }
}
