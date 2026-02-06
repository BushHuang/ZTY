package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.subscribers.DefaultSubscriber;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class BlockingFlowableMostRecent<T> implements Iterable<T> {
    final T initialValue;
    final Flowable<T> source;

    static final class MostRecentSubscriber<T> extends DefaultSubscriber<T> {
        volatile Object value;

        final class Iterator implements java.util.Iterator<T> {
            private Object buf;

            Iterator() {
            }

            @Override
            public boolean hasNext() {
                this.buf = MostRecentSubscriber.this.value;
                return !NotificationLite.isComplete(r0);
            }

            @Override
            public T next() {
                try {
                    if (this.buf == null) {
                        this.buf = MostRecentSubscriber.this.value;
                    }
                    if (NotificationLite.isComplete(this.buf)) {
                        throw new NoSuchElementException();
                    }
                    if (NotificationLite.isError(this.buf)) {
                        throw ExceptionHelper.wrapOrThrow(NotificationLite.getError(this.buf));
                    }
                    return (T) NotificationLite.getValue(this.buf);
                } finally {
                    this.buf = null;
                }
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Read only iterator");
            }
        }

        MostRecentSubscriber(T t) {
            this.value = NotificationLite.next(t);
        }

        public MostRecentSubscriber<T>.Iterator getIterable() {
            return new Iterator();
        }

        @Override
        public void onComplete() {
            this.value = NotificationLite.complete();
        }

        @Override
        public void onError(Throwable th) {
            this.value = NotificationLite.error(th);
        }

        @Override
        public void onNext(T t) {
            this.value = NotificationLite.next(t);
        }
    }

    public BlockingFlowableMostRecent(Flowable<T> flowable, T t) {
        this.source = flowable;
        this.initialValue = t;
    }

    @Override
    public Iterator<T> iterator() {
        MostRecentSubscriber mostRecentSubscriber = new MostRecentSubscriber(this.initialValue);
        this.source.subscribe((FlowableSubscriber) mostRecentSubscriber);
        return mostRecentSubscriber.getIterable();
    }
}
