package com.xh.view.base.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.xh.view.base.dialog.cancelable.BaseCancelableHandler;
import com.xh.view.base.dialog.cancelable.CanCancelHandler;
import com.xh.view.base.dialog.gravity.BaseDialogGravityHandler;
import com.xh.view.base.dialog.gravity.GravityEnum;
import com.xh.view.base.ext.ListExtensionKt;
import com.xh.view.base.outlifecycle.OutLifecycleFragmentObserver;
import com.xh.xhcore.common.http.strategy.LogUtils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001:\u0001LB\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001bJ\u000e\u0010!\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020#J\u000e\u0010$\u001a\u00020\u00002\u0006\u0010%\u001a\u00020&J\b\u0010'\u001a\u00020\u001fH\u0016J\b\u0010(\u001a\u00020\u0004H\u0016J\b\u0010)\u001a\u00020\u001fH\u0016J\u0012\u0010*\u001a\u00020\u001f2\b\u0010+\u001a\u0004\u0018\u00010,H\u0016J\u0010\u0010-\u001a\u00020\u001f2\u0006\u0010.\u001a\u00020/H\u0016J\u0010\u0010-\u001a\u00020\u001f2\u0006\u00100\u001a\u000201H\u0016J\u0010\u00102\u001a\u00020\u001f2\u0006\u00103\u001a\u000204H\u0016J\u0012\u00105\u001a\u00020\u001f2\b\u0010+\u001a\u0004\u0018\u00010,H\u0016J&\u00106\u001a\u0004\u0018\u0001072\u0006\u00108\u001a\u0002092\b\u0010:\u001a\u0004\u0018\u00010;2\b\u0010+\u001a\u0004\u0018\u00010,H\u0016J\b\u0010<\u001a\u00020\u001fH\u0016J\u0010\u0010=\u001a\u00020\u001f2\u0006\u0010>\u001a\u00020?H\u0016J\u0006\u0010@\u001a\u00020\u001fJ\u000e\u0010A\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001bJ\u0010\u0010B\u001a\u00020\u00002\b\b\u0001\u0010C\u001a\u00020\u0004J\u000e\u0010D\u001a\u00020\u00002\u0006\u0010E\u001a\u00020\u0014J\u0016\u0010F\u001a\u00020\u00002\u0006\u0010G\u001a\u00020\u00042\u0006\u0010H\u001a\u00020\u0004J\u000e\u0010I\u001a\u00020\u00002\u0006\u0010J\u001a\u00020KR\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006M"}, d2 = {"Lcom/xh/view/base/dialog/BaseDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "()V", "INVALID_LAYOUT_ID", "", "getINVALID_LAYOUT_ID", "()I", "baseCancelableHandler", "Lcom/xh/view/base/dialog/cancelable/BaseCancelableHandler;", "getBaseCancelableHandler", "()Lcom/xh/view/base/dialog/cancelable/BaseCancelableHandler;", "setBaseCancelableHandler", "(Lcom/xh/view/base/dialog/cancelable/BaseCancelableHandler;)V", "baseDialogGravityHandler", "Lcom/xh/view/base/dialog/gravity/BaseDialogGravityHandler;", "getBaseDialogGravityHandler", "()Lcom/xh/view/base/dialog/gravity/BaseDialogGravityHandler;", "setBaseDialogGravityHandler", "(Lcom/xh/view/base/dialog/gravity/BaseDialogGravityHandler;)V", "mDefaultListener", "Lcom/xh/view/base/dialog/BaseDialogFragment$BaseDialogListener;", "getMDefaultListener", "()Lcom/xh/view/base/dialog/BaseDialogFragment$BaseDialogListener;", "setMDefaultListener", "(Lcom/xh/view/base/dialog/BaseDialogFragment$BaseDialogListener;)V", "outLifecycleObservers", "Ljava/util/ArrayList;", "Lcom/xh/view/base/outlifecycle/OutLifecycleFragmentObserver;", "getOutLifecycleObservers", "()Ljava/util/ArrayList;", "addOutLifecycleObservers", "", "outLifecycleFragmentObserver", "applyCancelable", "cancelable", "", "applyGravityStyle", "gravityEnum", "Lcom/xh/view/base/dialog/gravity/GravityEnum;", "createViewModelAndObserveLiveData", "getViewLayoutId", "init", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onAttach", "activity", "Landroid/app/Activity;", "context", "Landroid/content/Context;", "onAttachFragment", "childFragment", "Landroidx/fragment/app/Fragment;", "onCreate", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onDismiss", "dialog", "Landroid/content/DialogInterface;", "removeAllOutLifecycleObservers", "removeOutLifecycleObservers", "setAnimationStyle", "resId", "setDefaultListener", "defaultListener", "setXYPosition", "xPosition", "yPosition", "show", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "BaseDialogListener", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class BaseDialogFragment extends DialogFragment {
    private BaseCancelableHandler baseCancelableHandler;
    private BaseDialogGravityHandler baseDialogGravityHandler;
    private BaseDialogListener mDefaultListener;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private final int INVALID_LAYOUT_ID = -1;
    private final ArrayList<OutLifecycleFragmentObserver> outLifecycleObservers = new ArrayList<>();

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0001H\u0016J\u001a\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0001H\u0016J\u001a\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0001H\u0016¨\u0006\t"}, d2 = {"Lcom/xh/view/base/dialog/BaseDialogFragment$BaseDialogListener;", "", "onDialogNegativeClick", "", "dialog", "Landroidx/fragment/app/DialogFragment;", "any", "onDialogPositiveClick", "onDismiss", "xhcore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface BaseDialogListener {

        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        public static final class DefaultImpls {
            public static void onDialogNegativeClick(BaseDialogListener baseDialogListener, DialogFragment dialogFragment, Object obj) {
                Intrinsics.checkNotNullParameter(baseDialogListener, "this");
                Intrinsics.checkNotNullParameter(dialogFragment, "dialog");
                Intrinsics.checkNotNullParameter(obj, "any");
            }

            public static void onDialogNegativeClick$default(BaseDialogListener baseDialogListener, DialogFragment dialogFragment, Object obj, int i, Object obj2) {
                if (obj2 != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onDialogNegativeClick");
                }
                if ((i & 2) != 0) {
                    obj = new Object();
                }
                baseDialogListener.onDialogNegativeClick(dialogFragment, obj);
            }

            public static void onDialogPositiveClick(BaseDialogListener baseDialogListener, DialogFragment dialogFragment, Object obj) {
                Intrinsics.checkNotNullParameter(baseDialogListener, "this");
                Intrinsics.checkNotNullParameter(dialogFragment, "dialog");
                Intrinsics.checkNotNullParameter(obj, "any");
            }

            public static void onDialogPositiveClick$default(BaseDialogListener baseDialogListener, DialogFragment dialogFragment, Object obj, int i, Object obj2) {
                if (obj2 != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onDialogPositiveClick");
                }
                if ((i & 2) != 0) {
                    obj = new Object();
                }
                baseDialogListener.onDialogPositiveClick(dialogFragment, obj);
            }

            public static void onDismiss(BaseDialogListener baseDialogListener, DialogFragment dialogFragment, Object obj) {
                Intrinsics.checkNotNullParameter(baseDialogListener, "this");
                Intrinsics.checkNotNullParameter(dialogFragment, "dialog");
                Intrinsics.checkNotNullParameter(obj, "any");
            }

            public static void onDismiss$default(BaseDialogListener baseDialogListener, DialogFragment dialogFragment, Object obj, int i, Object obj2) {
                if (obj2 != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onDismiss");
                }
                if ((i & 2) != 0) {
                    obj = new Object();
                }
                baseDialogListener.onDismiss(dialogFragment, obj);
            }
        }

        void onDialogNegativeClick(DialogFragment dialog, Object any);

        void onDialogPositiveClick(DialogFragment dialog, Object any);

        void onDismiss(DialogFragment dialog, Object any);
    }

    public BaseDialogFragment() {
        BaseDialogFragment baseDialogFragment = this;
        this.baseDialogGravityHandler = new BaseDialogGravityHandler(baseDialogFragment);
        this.baseCancelableHandler = new CanCancelHandler(baseDialogFragment);
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        View viewFindViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null || (viewFindViewById = view2.findViewById(i)) == null) {
            return null;
        }
        map.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    public final void addOutLifecycleObservers(OutLifecycleFragmentObserver outLifecycleFragmentObserver) {
        Intrinsics.checkNotNullParameter(outLifecycleFragmentObserver, "outLifecycleFragmentObserver");
        ListExtensionKt.addIfAbsent(this.outLifecycleObservers, outLifecycleFragmentObserver);
    }

    public final BaseDialogFragment applyCancelable(boolean cancelable) {
        this.baseCancelableHandler.setCancelable(cancelable);
        return this;
    }

    public final BaseDialogFragment applyGravityStyle(GravityEnum gravityEnum) {
        Intrinsics.checkNotNullParameter(gravityEnum, "gravityEnum");
        this.baseDialogGravityHandler.setGravityEnum(gravityEnum);
        return this;
    }

    public void createViewModelAndObserveLiveData() {
    }

    public final BaseCancelableHandler getBaseCancelableHandler() {
        return this.baseCancelableHandler;
    }

    public final BaseDialogGravityHandler getBaseDialogGravityHandler() {
        return this.baseDialogGravityHandler;
    }

    public final int getINVALID_LAYOUT_ID() {
        return this.INVALID_LAYOUT_ID;
    }

    public final BaseDialogListener getMDefaultListener() {
        return this.mDefaultListener;
    }

    public final ArrayList<OutLifecycleFragmentObserver> getOutLifecycleObservers() {
        return this.outLifecycleObservers;
    }

    public int getViewLayoutId() {
        return this.INVALID_LAYOUT_ID;
    }

    public void init() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    @Override
    public void onAttach(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        super.onAttach(activity);
        if (this.mDefaultListener == null && (activity instanceof BaseDialogListener)) {
            this.mDefaultListener = (BaseDialogListener) activity;
        }
        LogUtils.INSTANCE.d(Intrinsics.stringPlus("activity = ", activity));
    }

    @Override
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        LogUtils.INSTANCE.d(Intrinsics.stringPlus("context = ", context));
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        Intrinsics.checkNotNullParameter(childFragment, "childFragment");
        super.onAttachFragment(childFragment);
        LogUtils.INSTANCE.d(Intrinsics.stringPlus("childFragment = ", childFragment));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLifecycle().addObserver(this.baseDialogGravityHandler);
        addOutLifecycleObservers(this.baseDialogGravityHandler);
        getLifecycle().addObserver(this.baseCancelableHandler);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = getViewLayoutId() != this.INVALID_LAYOUT_ID ? inflater.inflate(getViewLayoutId(), container, false) : super.onCreateView(inflater, container, savedInstanceState);
        createViewModelAndObserveLiveData();
        ListExtensionKt.notifyOutLifecycleObservers(this.outLifecycleObservers, new Function1<OutLifecycleFragmentObserver, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(OutLifecycleFragmentObserver outLifecycleFragmentObserver) {
                invoke2(outLifecycleFragmentObserver);
                return Unit.INSTANCE;
            }

            public final void invoke2(OutLifecycleFragmentObserver outLifecycleFragmentObserver) {
                Intrinsics.checkNotNullParameter(outLifecycleFragmentObserver, "it");
                outLifecycleFragmentObserver.onCreateView(inflater, container, savedInstanceState);
            }
        });
        return viewInflate;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ListExtensionKt.notifyOutLifecycleObservers(this.outLifecycleObservers, new Function1<OutLifecycleFragmentObserver, Unit>() {
            @Override
            public Unit invoke(OutLifecycleFragmentObserver outLifecycleFragmentObserver) {
                invoke2(outLifecycleFragmentObserver);
                return Unit.INSTANCE;
            }

            public final void invoke2(OutLifecycleFragmentObserver outLifecycleFragmentObserver) {
                Intrinsics.checkNotNullParameter(outLifecycleFragmentObserver, "it");
                outLifecycleFragmentObserver.onDestroyView();
            }
        });
        removeAllOutLifecycleObservers();
        _$_clearFindViewByIdCache();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        super.onDismiss(dialog);
        LogUtils.INSTANCE.d(Intrinsics.stringPlus("dialog = ", dialog));
        BaseDialogListener baseDialogListener = this.mDefaultListener;
        if (baseDialogListener == null) {
            return;
        }
        BaseDialogListener.DefaultImpls.onDismiss$default(baseDialogListener, this, null, 2, null);
    }

    public final void removeAllOutLifecycleObservers() {
        this.outLifecycleObservers.clear();
    }

    public final void removeOutLifecycleObservers(OutLifecycleFragmentObserver outLifecycleFragmentObserver) {
        Intrinsics.checkNotNullParameter(outLifecycleFragmentObserver, "outLifecycleFragmentObserver");
        ListExtensionKt.removeIfIn(this.outLifecycleObservers, outLifecycleFragmentObserver);
    }

    public final BaseDialogFragment setAnimationStyle(int resId) {
        this.baseDialogGravityHandler.setAnimationStyleresId(Integer.valueOf(resId));
        return this;
    }

    public final void setBaseCancelableHandler(BaseCancelableHandler baseCancelableHandler) {
        Intrinsics.checkNotNullParameter(baseCancelableHandler, "<set-?>");
        this.baseCancelableHandler = baseCancelableHandler;
    }

    public final void setBaseDialogGravityHandler(BaseDialogGravityHandler baseDialogGravityHandler) {
        Intrinsics.checkNotNullParameter(baseDialogGravityHandler, "<set-?>");
        this.baseDialogGravityHandler = baseDialogGravityHandler;
    }

    public final BaseDialogFragment setDefaultListener(BaseDialogListener defaultListener) {
        Intrinsics.checkNotNullParameter(defaultListener, "defaultListener");
        this.mDefaultListener = defaultListener;
        return this;
    }

    public final void setMDefaultListener(BaseDialogListener baseDialogListener) {
        this.mDefaultListener = baseDialogListener;
    }

    public final BaseDialogFragment setXYPosition(int xPosition, int yPosition) {
        this.baseDialogGravityHandler.setXPosition(xPosition);
        this.baseDialogGravityHandler.setYPosition(yPosition);
        return this;
    }

    public final BaseDialogFragment show(FragmentManager fragmentManager) {
        Intrinsics.checkNotNullParameter(fragmentManager, "fragmentManager");
        this.baseDialogGravityHandler = this.baseDialogGravityHandler.build();
        this.baseCancelableHandler = this.baseCancelableHandler.build();
        super.show(fragmentManager, getClass().getSimpleName());
        return this;
    }
}
