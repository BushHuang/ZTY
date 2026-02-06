package com.xuehai.launcher.common.ext;

import android.app.Application;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.xuehai.launcher.common.base.AbsAndroidViewModel;
import com.xuehai.launcher.common.base.BaseApplication;
import java.lang.reflect.InvocationTargetException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J%\u0010\u0003\u001a\u0002H\u0004\"\b\b\u0000\u0010\u0004*\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0007H\u0016¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lcom/xuehai/launcher/common/ext/ViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$NewInstanceFactory;", "()V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "common_studentToBRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) throws IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (!AbsAndroidViewModel.class.isAssignableFrom(modelClass)) {
            T t = (T) super.create(modelClass);
            Intrinsics.checkNotNullExpressionValue(t, "super.create(modelClass)");
            return t;
        }
        try {
            T tNewInstance = modelClass.getConstructor(Application.class).newInstance(BaseApplication.INSTANCE.getInstance());
            Intrinsics.checkNotNullExpressionValue(tNewInstance, "{\n            try {\n    …)\n            }\n        }");
            return tNewInstance;
        } catch (Exception e) {
            throw new RuntimeException("Cannot create an instance of " + modelClass, e);
        }
    }
}
