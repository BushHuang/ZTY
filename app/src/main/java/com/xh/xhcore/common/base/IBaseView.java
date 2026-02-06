package com.xh.xhcore.common.base;

public interface IBaseView {
    void hideProgress();

    void requestFailed(String... strArr);

    void showProgress(String... strArr);

    void showToast(String str);
}
