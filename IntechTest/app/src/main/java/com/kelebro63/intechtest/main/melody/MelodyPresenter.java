package com.kelebro63.intechtest.main.melody;

import android.util.Log;
import android.util.Pair;

import com.kelebro63.intechtest.base.BaseActivity;
import com.kelebro63.intechtest.base.BasePresenter;
import com.kelebro63.intechtest.base.NetworkInvisSubscriber;
import com.kelebro63.intechtest.media.PlayerManager;
import com.kelebro63.intechtest.models.Melody;

import java.io.IOException;

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

    public void playStream(Melody melody) throws IOException {
        PlayerManager.getInstanse(this.activity).setSource(melody.getDemoUrl());
        PlayerManager.getInstanse(this.activity).start(melody.getDemoUrl());
    }

    public void pauseStream() {
        PlayerManager player = PlayerManager.getInstanse(this.activity);
        if (player.mediaPlayer.isPlaying()) {
            player.pause();
            getView().showPlayButton();
        }
    }

    public void stopStream() {
        PlayerManager player = PlayerManager.getInstanse(this.activity);
            if (player.mediaPlayer.isPlaying()) {
            player.stop();
            getView().showPlayButton();
        }
    }

    public void playPauseStream(Melody melody) {
        PlayerManager player = PlayerManager.getInstanse(this.activity);
        if (player.mediaPlayer.isPlaying()) {
            player.pause();
            getView().showPlayButton();
        } else {
            player.start(melody.getDemoUrl());
            getPlaybackPosition();
            getView().showPauseButton();
        }
    }

    public void determinateShowButtons() {
        PlayerManager player = PlayerManager.getInstanse(this.activity);
        if (player.mediaPlayer.isPlaying()) {
            getView().showPauseButton();
        } else {
            getView().showPlayButton();
        }
    }

    public void clearPlayer() {
        PlayerManager player = PlayerManager.getInstanse(this.activity);
        player.reset(this.activity);
    }

    public void getPlaybackPosition() {
        PlayerManager player = PlayerManager.getInstanse(this.activity);
        subscribe(player.stream(), getPositionSubscriber());
    }

    private NetworkInvisSubscriber<Pair<Integer, Integer>> getPositionSubscriber() {
        return new NetworkInvisSubscriber<Pair<Integer, Integer>>(getView(), this) {
            @Override
            public void onNext(Pair<Integer, Integer> pair) {
                super.onNext(pair);
                Log.d("debug", "pair = " + pair.first + ", " + pair.second);
                getView().setDurationPlayerProgress(pair.second);
                getView().setCurrentPlayerProgress(pair.first);
                getView().setTime(pair.first, pair.second);
            }

            @Override
            public void onCompleted() {
                super.onCompleted();
                getView().showPlayButton();
                clearPlayer();
                getView().setCurrentPlayerProgress(0);
            }

            @Override
            public void onError(Throwable throwable) {
            }
        };
    }

    public void setProgressPlayer(int progress) {
        PlayerManager player = PlayerManager.getInstanse(this.activity);
        player.mediaPlayer.seekTo(progress);
    }
}
