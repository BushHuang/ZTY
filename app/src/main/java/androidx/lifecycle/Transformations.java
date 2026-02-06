package androidx.lifecycle;

import androidx.arch.core.util.Function;

public class Transformations {
    private Transformations() {
    }

    public static <X> LiveData<X> distinctUntilChanged(LiveData<X> liveData) {
        final MediatorLiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.addSource(liveData, new Observer<X>() {
            boolean mFirstTime = true;

            @Override
            public void onChanged(X x) {
                T value = mediatorLiveData.getValue();
                if (this.mFirstTime || ((value == 0 && x != null) || !(value == 0 || value.equals(x)))) {
                    this.mFirstTime = false;
                    mediatorLiveData.setValue(x);
                }
            }
        });
        return mediatorLiveData;
    }

    public static <X, Y> LiveData<Y> map(LiveData<X> liveData, final Function<X, Y> function) {
        final MediatorLiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.addSource(liveData, new Observer<X>() {
            @Override
            public void onChanged(X x) {
                mediatorLiveData.setValue(function.apply(x));
            }
        });
        return mediatorLiveData;
    }

    public static <X, Y> LiveData<Y> switchMap(LiveData<X> liveData, final Function<X, LiveData<Y>> function) {
        final MediatorLiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.addSource(liveData, new Observer<X>() {
            LiveData<Y> mSource;

            @Override
            public void onChanged(X x) {
                LiveData<Y> liveData2 = (LiveData) function.apply(x);
                Object obj = this.mSource;
                if (obj == liveData2) {
                    return;
                }
                if (obj != null) {
                    mediatorLiveData.removeSource(obj);
                }
                this.mSource = liveData2;
                if (liveData2 != 0) {
                    mediatorLiveData.addSource(liveData2, new Observer<Y>() {
                        @Override
                        public void onChanged(Y y) {
                            mediatorLiveData.setValue(y);
                        }
                    });
                }
            }
        });
        return mediatorLiveData;
    }
}
