package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableCount<T> extends AbstractFlowableWithUpstream<T, Long> {

    static final class CountSubscriber extends DeferredScalarSubscription<Long> implements FlowableSubscriber<Object> {
        private static final long serialVersionUID = 4973004223787171406L;
        long count;
        Subscription s;

        CountSubscriber(Subscriber<? super Long> subscriber) {
            super(subscriber);
        }

        @Override
        public void cancel() {
            super.cancel();
            this.s.cancel();
        }

        @Override
        public void onComplete() {
            complete(Long.valueOf(this.count));
        }

        @Override
        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        @Override
        public void onNext(Object obj) {
            this.count++;
        }

        @Override
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                this.actual.onSubscribe(this);
                subscription.request(Long.MAX_VALUE);
            }
        }
    }

    public FlowableCount(Flowable<T> flowable) {
        super(flowable);
    }

    @Override
    protected void subscribeActual(Subscriber<? super Long> subscriber) {
        this.source.subscribe((FlowableSubscriber) new CountSubscriber(subscriber));
    }
}
