package com.kelebro63.intechtest.main.melodies_list;

import com.kelebro63.intechtest.api.IIntechAPI;
import com.kelebro63.intechtest.base.BasePresenter;
import com.kelebro63.intechtest.base.NetworkPtrSubscriber;
import com.kelebro63.intechtest.base.NetworkSubscriber;
import com.kelebro63.intechtest.main.MainNavigator;
import com.kelebro63.intechtest.models.ResponseMelody;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by kelebro63 on 02.06.2016
 */
public class MelodiesPresenter extends BasePresenter<IMelodiesView> {

    private final IIntechAPI api;
    private final MainNavigator navigator;

    @Inject
    public MelodiesPresenter(Observable.Transformer transformer, IIntechAPI api, MainNavigator navigator) {
        super(transformer);
        this.api = api;
        this.navigator = navigator;
    }

    public void loadMelodies(int limit, int offset) {
        subscribe(api.getSongsList(limit, offset), getMelodiesSubscriber());
    }

    private NetworkSubscriber<ResponseMelody> getMelodiesSubscriber() {
        return new NetworkSubscriber<ResponseMelody>(getView(), this) {
            @Override
            public void onNext(ResponseMelody responseMelody) {
                super.onNext(responseMelody);
                getView().displayMelodies(ResponseMelody.addDividers(responseMelody.getMelodies()));
            }

            @Override
            public void onError(Throwable throwable) {
                super.onError(throwable);
            }
        };
    }

    public void updateMelodies(int limit, int offset) {
        subscribe(api.getSongsList(limit, offset), updateMelodiesSubscriber());
    }

    private NetworkPtrSubscriber<ResponseMelody> updateMelodiesSubscriber() {
        return new NetworkPtrSubscriber<ResponseMelody>(getView(), this) {
            @Override
            public void onNext(ResponseMelody responseMelody) {
                super.onNext(responseMelody);
                getView().displayMelodies(ResponseMelody.addDividers(responseMelody.getMelodies()));
            }

            @Override
            public void onError(Throwable throwable) {
                super.onError(throwable);
            }
        };
    }
}
