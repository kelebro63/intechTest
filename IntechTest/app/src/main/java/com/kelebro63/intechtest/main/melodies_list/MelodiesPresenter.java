package com.kelebro63.intechtest.main.melodies_list;

import android.support.annotation.NonNull;

import com.kelebro63.intechtest.api.IIntechAPI;
import com.kelebro63.intechtest.base.BasePresenter;
import com.kelebro63.intechtest.base.NetworkPtrSubscriber;
import com.kelebro63.intechtest.base.NetworkSubscriber;
import com.kelebro63.intechtest.main.MainNavigator;
import com.kelebro63.intechtest.models.Collection;
import com.kelebro63.intechtest.models.ResponseMelody;

import java.io.Serializable;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by kelebro63 on 02.06.2016
 */
public class MelodiesPresenter extends BasePresenter<IMelodiesView> implements Serializable {

    private final IIntechAPI api;
    private final MainNavigator navigator;
    private int currentPage = 0;
    private boolean isLoading;

    @Inject
    public MelodiesPresenter(Observable.Transformer transformer, IIntechAPI api, MainNavigator navigator) {
        super(transformer);
        this.api = api;
        this.navigator = navigator;
    }

    public void loadMelodies() { //int limit, int offset
        if (isLoading)
            return;
        subscribe(api.getSongsList("4eba3b80bf836b52beab8a357da618bf", 20, currentPage), getMelodiesSubscriber());
    }

    private NetworkSubscriber<ResponseMelody> getMelodiesSubscriber() {
        return new NetworkSubscriber<ResponseMelody>(getView(), this) {

            @Override
            public void onStart() {
                isLoading = true;
            }

            @Override
            public void onNext(ResponseMelody responseMelody) {
                super.onNext(responseMelody);
                getView().addMelodiesToDisplay(responseMelody.getCollection());
                isLoading = false;
                currentPage = currentPage + 1;
            }

            @Override
            public void onError(Throwable throwable) {
                super.onError(throwable);
            }
        };
    }

    public void updateMelodies(int limit, int from) {
        if (isLoading) {
            return;
        }
        currentPage = from;
        subscribe(api.getSongsList("4eba3b80bf836b52beab8a357da618bf", limit, from), updateMelodiesSubscriber());
    }

    private NetworkPtrSubscriber<ResponseMelody> updateMelodiesSubscriber() {
        return new NetworkPtrSubscriber<ResponseMelody>(getView(), this) {

            @Override
            public void onStart() {
                isLoading = true;
            }

            @Override
            public void onNext(ResponseMelody responseMelody) {
                super.onNext(responseMelody);
                getView().setMelodiesToDisplay(responseMelody.getCollection());
                getView().stopRefreshing();
                isLoading = false;
                currentPage = currentPage + 1;

            }

            @Override
            public void onError(Throwable throwable) {
                super.onError(throwable);
            }
        };
    }

    public void navigateToMelody(@NonNull Collection item) {
        navigator.navigateToMelody(item);
    }
}
