package com.kelebro63.intechtest.base;

/**
 * Created by kelebro63 on 02.06.2016
 */
public class NetworkPtrSubscriber<T> extends NetworkSubscriber<T> {
    public NetworkPtrSubscriber(IPtrView view, BasePresenter presenter) {
        super(view, presenter);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onNext(T t) {
        super.onNext(t);
        ((IPtrView) view).stopRefreshing();
    }

    @Override
    public void onError(Throwable throwable) {
        super.onError(throwable);
        ((IPtrView) view).stopRefreshing();
    }
}
