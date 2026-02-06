package com.xuehai.launcher.common.widget;

import android.widget.Button;
import com.zaze.utils.ZStringUtil;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.util.concurrent.TimeUnit;

public class IntervalButtonWidget {
    private Button button;
    private String defaultText;
    private Disposable disposable;
    private Observable<Long> observable;

    public IntervalButtonWidget(Button button, String str) {
        this(button, str, 60L);
    }

    public IntervalButtonWidget(Button button, String str, final long j) {
        this.button = button;
        this.defaultText = str;
        this.observable = Observable.interval(0L, 1L, TimeUnit.SECONDS).take(j).map(new Function<Long, Long>() {
            @Override
            public Long apply(Long l) throws Exception {
                return Long.valueOf(j - l.longValue());
            }
        }).observeOn(AndroidSchedulers.mainThread());
    }

    private void updateButton(String str, boolean z) {
        Button button = this.button;
        if (button != null) {
            button.setText(ZStringUtil.parseString(str));
            this.button.setEnabled(z);
        }
    }

    public void start() {
        this.observable.subscribe(new Observer<Long>() {
            @Override
            public void onComplete() {
                IntervalButtonWidget intervalButtonWidget = IntervalButtonWidget.this;
                intervalButtonWidget.updateButton(intervalButtonWidget.defaultText, true);
            }

            @Override
            public void onError(Throwable th) {
                IntervalButtonWidget intervalButtonWidget = IntervalButtonWidget.this;
                intervalButtonWidget.updateButton(intervalButtonWidget.defaultText, true);
            }

            @Override
            public void onNext(Long l) {
                IntervalButtonWidget.this.updateButton(ZStringUtil.format("重新发送(%s%s)", l, "s"), false);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                IntervalButtonWidget.this.disposable = disposable;
                IntervalButtonWidget intervalButtonWidget = IntervalButtonWidget.this;
                intervalButtonWidget.updateButton(intervalButtonWidget.defaultText, false);
            }
        });
    }

    public void stop() {
        Disposable disposable = this.disposable;
        if (disposable != null && !disposable.isDisposed()) {
            this.disposable.dispose();
        }
        updateButton(this.defaultText, true);
    }
}
