package com.xuehai.launcher.common.ext;

import androidx.lifecycle.MutableLiveData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0016\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003\u001a\u001d\u0010\u0004\u001a\u0004\u0018\u0001H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003¢\u0006\u0002\u0010\u0005\u001a%\u0010\u0006\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u0001H\u0002¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"action", "", "T", "Landroidx/lifecycle/MutableLiveData;", "get", "(Landroidx/lifecycle/MutableLiveData;)Ljava/lang/Object;", "set", "value", "(Landroidx/lifecycle/MutableLiveData;Ljava/lang/Object;)V", "common_studentToBRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class LiveDataExtKt {
    public static final <T> void action(MutableLiveData<T> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<this>");
        mutableLiveData.postValue(null);
    }

    public static final <T> T get(MutableLiveData<T> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<this>");
        return mutableLiveData.getValue();
    }

    public static final <T> void set(MutableLiveData<T> mutableLiveData, T t) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<this>");
        mutableLiveData.postValue(t);
    }
}
