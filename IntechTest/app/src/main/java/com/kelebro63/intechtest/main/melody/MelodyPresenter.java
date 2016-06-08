package com.kelebro63.intechtest.main.melody;

import com.kelebro63.intechtest.base.BaseActivity;
import com.kelebro63.intechtest.base.BasePresenter;
import com.kelebro63.intechtest.base.NetworkInvisSubscriber;
import com.kelebro63.intechtest.media.RxMediaPlayer;
import com.kelebro63.intechtest.models.Melody;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Bistrov Alexey on 08.06.2016.
 */
public class MelodyPresenter extends BasePresenter<IMelodyView> {

    private final BaseActivity activity;

    @Inject
    public MelodyPresenter(Observable.Transformer transformer, BaseActivity activity) {
        super(transformer);
        this.activity = activity;
    }

    public void playStream(Melody melody) {
        subscribe(createObservable(melody.getDemoUrl()), mediaPlayerSubscriber());
    }

    private NetworkInvisSubscriber<Object> mediaPlayerSubscriber() {
        return new NetworkInvisSubscriber<Object>(getView(), this) {
            @Override
            public void onNext(Object o) {
            }

            @Override
            public void onError(Throwable throwable) {
                super.onError(throwable);
            }
        };
    }

    private Observable<Object> createObservable(String url) {
        return RxMediaPlayer.from(url, this.activity).flatMap(player -> RxMediaPlayer.play(player));
    }
}
