package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableLimit<T> extends AbstractFlowableWithUpstream<T, T> {
    final long n;

    static final class LimitSubscriber<T> extends AtomicLong implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = 2288246011222124525L;
        final Subscriber<? super T> actual;
        long remaining;
        Subscription upstream;

        LimitSubscriber(Subscriber<? super T> subscriber, long j) {
            this.actual = subscriber;
            this.remaining = j;
            lazySet(j);
        }

        @Override
        public void cancel() {
            this.upstream.cancel();
        }

        @Override
        public void onComplete() {
            if (this.remaining > 0) {
                this.remaining = 0L;
                this.actual.onComplete();
            }
        }

        @Override
        public void onError(Throwable th) {
            if (this.remaining <= 0) {
                RxJavaPlugins.onError(th);
            } else {
                this.remaining = 0L;
                this.actual.onError(th);
            }
        }

        @Override
        public void onNext(T t) {
            long j = this.remaining;
            if (j > 0) {
                long j2 = j - 1;
                this.remaining = j2;
                this.actual.onNext(t);
                if (j2 == 0) {
                    this.upstream.cancel();
                    this.actual.onComplete();
                }
            }
        }

        @Override
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.upstream, subscription)) {
                if (this.remaining == 0) {
                    subscription.cancel();
                    EmptySubscription.complete(this.actual);
                } else {
                    this.upstream = subscription;
                    this.actual.onSubscribe(this);
                }
            }
        }

        @Override
        public void request(long j) {
            long j2;
            long j3;
            if (SubscriptionHelper.validate(j)) {
                do {
                    j2 = get();
                    if (j2 == 0) {
                        return;
                    } else {
                        j3 = j2 <= j ? j2 : j;
                    }
                } while (!compareAndSet(j2, j2 - j3));
                this.upstream.request(j3);
            }
        }
    }

    public FlowableLimit(Flowable<T> flowable, long j) {
        super(flowable);
        this.n = j;
    }

    @Override
    protected void subscribeActual(Subscriber<? super T> subscriber) {
        this.source.subscribe((FlowableSubscriber) new LimitSubscriber(subscriber, this.n));
    }
}
