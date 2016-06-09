package com.kelebro63.intechtest.main.melody;

import android.media.MediaPlayer;
import android.util.Log;
import android.util.Pair;

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

    private NetworkInvisSubscriber<Pair<Integer, Integer>> objectSubscriber() {
        return new NetworkInvisSubscriber<Pair<Integer, Integer>>(getView(), this) {

            @Override
            public void onNext(Pair<Integer, Integer> pair) {
                Log.d("debug", pair.toString());
                getView().setDurationPlayerProgress(pair.second);
                getView().setCurrentPlayerProgress(pair.first);
            }

            @Override
            public void onError(Throwable throwable) {
                super.onError(throwable);
            }
        };
    }

    private Observable<Pair<Integer, Integer>> createObservable(String url) {
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
        getView().showPlayButton();
    }

    public void cleanRxMP() {
        RxMediaPlayer.cleanPlayer();
    }

    public void playPauseStream(Melody melody) {
        subscribe(RxMediaPlayer.getPlayerObservable(), getPlayerSubscriber(melody));
    }

    private NetworkInvisSubscriber<MediaPlayer> getPlayerSubscriber(Melody melody) {
        return new NetworkInvisSubscriber<MediaPlayer>(getView(), this) {
            @Override
            public void onNext(MediaPlayer player) {
                getView().setDurationPlayerProgress(player.getDuration());
                if (player.isPlaying()) {
                    pauseStream();
                    getView().showPlayButton();
                } else {
                    playStream(melody);
                    getView().showPauseButton();
                }
            }

            @Override
            public void onError(Throwable throwable) {
                super.onError(throwable);
            }
        };
    }
}
