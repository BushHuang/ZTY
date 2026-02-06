package com.xh.view.base.outlifecycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J&\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\b\u0010\f\u001a\u00020\u0003H\u0016J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u000fH\u0016¨\u0006\u0012"}, d2 = {"Lcom/xh/view/base/outlifecycle/OutLifecycleFragmentObserver;", "Lcom/xh/view/base/outlifecycle/OutLifecycleObserver;", "onActivityCreated", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onHiddenChanged", "hidden", "", "setUserVisibleHint", "isVisibleToUser", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface OutLifecycleFragmentObserver extends OutLifecycleObserver {

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static void onActivityCreated(OutLifecycleFragmentObserver outLifecycleFragmentObserver, Bundle bundle) {
            Intrinsics.checkNotNullParameter(outLifecycleFragmentObserver, "this");
        }

        public static View onCreateView(OutLifecycleFragmentObserver outLifecycleFragmentObserver, LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            Intrinsics.checkNotNullParameter(outLifecycleFragmentObserver, "this");
            Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
            return null;
        }

        public static void onDestroyView(OutLifecycleFragmentObserver outLifecycleFragmentObserver) {
            Intrinsics.checkNotNullParameter(outLifecycleFragmentObserver, "this");
        }

        public static void onHiddenChanged(OutLifecycleFragmentObserver outLifecycleFragmentObserver, boolean z) {
            Intrinsics.checkNotNullParameter(outLifecycleFragmentObserver, "this");
        }

        public static void setUserVisibleHint(OutLifecycleFragmentObserver outLifecycleFragmentObserver, boolean z) {
            Intrinsics.checkNotNullParameter(outLifecycleFragmentObserver, "this");
        }
    }

    void onActivityCreated(Bundle savedInstanceState);

    View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    void onDestroyView();

    void onHiddenChanged(boolean hidden);

    void setUserVisibleHint(boolean isVisibleToUser);
}
