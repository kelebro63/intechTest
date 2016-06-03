package com.kelebro63.intechtest.main.melodies_list;

import com.kelebro63.intechtest.api.IIntechAPI;
import com.kelebro63.intechtest.base.BasePresenter;
import com.kelebro63.intechtest.base.NetworkSubscriber;
import com.kelebro63.intechtest.main.MainNavigator;
import com.kelebro63.intechtest.models.ResponseMelody;
import com.kelebro63.intechtest.utils.RetrofitUtils;

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
            }

            @Override
            public void onError(Throwable throwable) {
                int errorCode = RetrofitUtils.getErrorCode(throwable);
                super.onError(throwable);
            }
        };
    }
}
