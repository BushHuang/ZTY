package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionArbiter;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableDelaySubscriptionOther<T, U> extends Flowable<T> {
    final Publisher<? extends T> main;
    final Publisher<U> other;

    final class DelaySubscriber implements FlowableSubscriber<U> {
        final Subscriber<? super T> child;
        boolean done;
        final SubscriptionArbiter serial;

        final class DelaySubscription implements Subscription {
            private final Subscription s;

            DelaySubscription(Subscription subscription) {
                this.s = subscription;
            }

            @Override
            public void cancel() {
                this.s.cancel();
            }

            @Override
            public void request(long j) {
            }
        }

        final class OnCompleteSubscriber implements FlowableSubscriber<T> {
            OnCompleteSubscriber() {
            }

            @Override
            public void onComplete() {
                DelaySubscriber.this.child.onComplete();
            }

            @Override
            public void onError(Throwable th) {
                DelaySubscriber.this.child.onError(th);
            }

            @Override
            public void onNext(T t) {
                DelaySubscriber.this.child.onNext(t);
            }

            @Override
            public void onSubscribe(Subscription subscription) {
                DelaySubscriber.this.serial.setSubscription(subscription);
            }
        }

        DelaySubscriber(SubscriptionArbiter subscriptionArbiter, Subscriber<? super T> subscriber) {
            this.serial = subscriptionArbiter;
            this.child = subscriber;
        }

        @Override
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            FlowableDelaySubscriptionOther.this.main.subscribe(new OnCompleteSubscriber());
        }

        @Override
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
            } else {
                this.done = true;
                this.child.onError(th);
            }
        }

        @Override
        public void onNext(U u) {
            onComplete();
        }

        @Override
        public void onSubscribe(Subscription subscription) {
            this.serial.setSubscription(new DelaySubscription(subscription));
            subscription.request(Long.MAX_VALUE);
        }
    }

    public FlowableDelaySubscriptionOther(Publisher<? extends T> publisher, Publisher<U> publisher2) {
        this.main = publisher;
        this.other = publisher2;
    }

    @Override
    public void subscribeActual(Subscriber<? super T> subscriber) {
        SubscriptionArbiter subscriptionArbiter = new SubscriptionArbiter();
        subscriber.onSubscribe(subscriptionArbiter);
        this.other.subscribe(new DelaySubscriber(subscriptionArbiter, subscriber));
    }
}
