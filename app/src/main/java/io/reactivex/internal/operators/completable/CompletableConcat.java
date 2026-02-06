package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

public final class CompletableConcat extends Completable {
    final int prefetch;
    final Publisher<? extends CompletableSource> sources;

    static final class CompletableConcatSubscriber extends AtomicInteger implements FlowableSubscriber<CompletableSource>, Disposable {
        private static final long serialVersionUID = 9032184911934499404L;
        volatile boolean active;
        final CompletableObserver actual;
        int consumed;
        volatile boolean done;
        final int limit;
        final int prefetch;
        SimpleQueue<CompletableSource> queue;
        Subscription s;
        int sourceFused;
        final ConcatInnerObserver inner = new ConcatInnerObserver(this);
        final AtomicBoolean once = new AtomicBoolean();

        static final class ConcatInnerObserver extends AtomicReference<Disposable> implements CompletableObserver {
            private static final long serialVersionUID = -5454794857847146511L;
            final CompletableConcatSubscriber parent;

            ConcatInnerObserver(CompletableConcatSubscriber completableConcatSubscriber) {
                this.parent = completableConcatSubscriber;
            }

            @Override
            public void onComplete() {
                this.parent.innerComplete();
            }

            @Override
            public void onError(Throwable th) {
                this.parent.innerError(th);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                DisposableHelper.replace(this, disposable);
            }
        }

        CompletableConcatSubscriber(CompletableObserver completableObserver, int i) {
            this.actual = completableObserver;
            this.prefetch = i;
            this.limit = i - (i >> 2);
        }

        @Override
        public void dispose() {
            this.s.cancel();
            DisposableHelper.dispose(this.inner);
        }

        void drain() {
            if (getAndIncrement() != 0) {
                return;
            }
            while (!isDisposed()) {
                if (!this.active) {
                    boolean z = this.done;
                    try {
                        CompletableSource completableSourcePoll = this.queue.poll();
                        boolean z2 = completableSourcePoll == null;
                        if (z && z2) {
                            if (this.once.compareAndSet(false, true)) {
                                this.actual.onComplete();
                                return;
                            }
                            return;
                        } else if (!z2) {
                            this.active = true;
                            completableSourcePoll.subscribe(this.inner);
                            request();
                        }
                    } catch (Throwable th) {
                        Exceptions.throwIfFatal(th);
                        innerError(th);
                        return;
                    }
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            }
        }

        void innerComplete() {
            this.active = false;
            drain();
        }

        void innerError(Throwable th) {
            if (!this.once.compareAndSet(false, true)) {
                RxJavaPlugins.onError(th);
            } else {
                this.s.cancel();
                this.actual.onError(th);
            }
        }

        @Override
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(this.inner.get());
        }

        @Override
        public void onComplete() {
            this.done = true;
            drain();
        }

        @Override
        public void onError(Throwable th) {
            if (!this.once.compareAndSet(false, true)) {
                RxJavaPlugins.onError(th);
            } else {
                DisposableHelper.dispose(this.inner);
                this.actual.onError(th);
            }
        }

        @Override
        public void onNext(CompletableSource completableSource) {
            if (this.sourceFused != 0 || this.queue.offer(completableSource)) {
                drain();
            } else {
                onError(new MissingBackpressureException());
            }
        }

        @Override
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                int i = this.prefetch;
                long j = i == Integer.MAX_VALUE ? Long.MAX_VALUE : i;
                if (subscription instanceof QueueSubscription) {
                    QueueSubscription queueSubscription = (QueueSubscription) subscription;
                    int iRequestFusion = queueSubscription.requestFusion(3);
                    if (iRequestFusion == 1) {
                        this.sourceFused = iRequestFusion;
                        this.queue = queueSubscription;
                        this.done = true;
                        this.actual.onSubscribe(this);
                        drain();
                        return;
                    }
                    if (iRequestFusion == 2) {
                        this.sourceFused = iRequestFusion;
                        this.queue = queueSubscription;
                        this.actual.onSubscribe(this);
                        subscription.request(j);
                        return;
                    }
                }
                if (this.prefetch == Integer.MAX_VALUE) {
                    this.queue = new SpscLinkedArrayQueue(Flowable.bufferSize());
                } else {
                    this.queue = new SpscArrayQueue(this.prefetch);
                }
                this.actual.onSubscribe(this);
                subscription.request(j);
            }
        }

        void request() {
            if (this.sourceFused != 1) {
                int i = this.consumed + 1;
                if (i != this.limit) {
                    this.consumed = i;
                } else {
                    this.consumed = 0;
                    this.s.request(i);
                }
            }
        }
    }

    public CompletableConcat(Publisher<? extends CompletableSource> publisher, int i) {
        this.sources = publisher;
        this.prefetch = i;
    }

    @Override
    public void subscribeActual(CompletableObserver completableObserver) {
        this.sources.subscribe(new CompletableConcatSubscriber(completableObserver, this.prefetch));
    }
}
