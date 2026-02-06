package io.reactivex.disposables;

import org.reactivestreams.Subscription;

final class SubscriptionDisposable extends ReferenceDisposable<Subscription> {
    private static final long serialVersionUID = -707001650852963139L;

    SubscriptionDisposable(Subscription subscription) {
        super(subscription);
    }

    @Override
    protected void onDisposed(Subscription subscription) {
        subscription.cancel();
    }
}
