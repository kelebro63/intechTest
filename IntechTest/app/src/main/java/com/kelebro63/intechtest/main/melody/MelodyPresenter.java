package com.kelebro63.intechtest.main.melody;

import com.kelebro63.intechtest.base.BaseActivity;
import com.kelebro63.intechtest.base.BasePresenter;
import com.kelebro63.intechtest.media.Player;
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
        Player.getInstanse(this.activity).setSource(melody.getDemoUrl());
        Player.getInstanse(this.activity).start();
    }

    public void pauseStream() {

    }

    public void stopStream() {
        Player player = Player.getInstanse(this.activity);
        if (player.mediaPlayer.isPlaying()) {
            player.mediaPlayer.stop();
            getView().showPlayButton();
        }
    }

    public void playPauseStream(Melody melody) {
        Player player = Player.getInstanse(this.activity);
        if (player.mediaPlayer.isPlaying()) {
            player.pause();
            getView().showPlayButton();
        } else {
            player.setSource(melody.getDemoUrl());
            player.start();
            getView().showPauseButton();
        }
    }


}
