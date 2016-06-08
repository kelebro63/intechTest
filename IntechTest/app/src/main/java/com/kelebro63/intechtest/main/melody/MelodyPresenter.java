package com.kelebro63.intechtest.main.melody;

import android.media.MediaPlayer;

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
        subscribe(createObservable(melody.getDemoUrl()), objectSubscriber());
    }

    private NetworkInvisSubscriber<Object> objectSubscriber() {
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
        return RxMediaPlayer.from(url).flatMap(player -> RxMediaPlayer.play(player));
    }

    public void pauseStream() {
        subscribe(RxMediaPlayer.pause(), mediaPlayerSubscriber());
    }

    private NetworkInvisSubscriber<MediaPlayer> mediaPlayerSubscriber() {
        return new NetworkInvisSubscriber<MediaPlayer>(getView(), this) {
            @Override
            public void onNext(MediaPlayer player) {

            }

            @Override
            public void onError(Throwable throwable) {
                super.onError(throwable);
            }
        };
    }

    public void stopStream() {
        subscribe(RxMediaPlayer.stop(), mediaPlayerSubscriber());
    }

    public void cleanRxMP() {
        RxMediaPlayer.cleanPlayer();
    }
}
