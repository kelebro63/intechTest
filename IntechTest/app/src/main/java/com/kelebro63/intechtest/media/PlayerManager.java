package com.kelebro63.intechtest.media;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Pair;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by Bistrov Alexey on 10.06.2016.
 */
public class PlayerManager {

    private static PlayerManager instanse;
    public MediaPlayer mediaPlayer;
    public int playbackPosition = -1;

    public PlayerManager(Context context) {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    public static PlayerManager getInstanse(Context context) {
        if (instanse == null) {
            instanse = new PlayerManager(context);
        }
        return instanse;
    }

    public void start(String demoUrl) {
        try {
            if (playbackPosition == -1) {
                setSource(demoUrl);
                mediaPlayer.prepare();
                mediaPlayer.start();
            } else if (playbackPosition == 0) {
                mediaPlayer.prepare();
                mediaPlayer.start();
            } else {
                mediaPlayer.seekTo(playbackPosition);
                mediaPlayer.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void stop() {
        playbackPosition = 0;
        mediaPlayer.stop();
    }

    public void pause() {
        playbackPosition = mediaPlayer.getCurrentPosition();
        mediaPlayer.pause();
    }

    public void setSource(String url){
        try {
            mediaPlayer.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void reset(Context context) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        playbackPosition = 0;
        instanse = new PlayerManager(context);
    }

    public Observable<Pair<Integer, Integer>> stream() {
        return Observable.create(subscriber -> {
            subscriber.add(ticks(mediaPlayer)
                    .takeUntil(complete(mediaPlayer))
                    .subscribe(subscriber));
        });
    }

    public Observable<Pair<Integer, Integer>> ticks(MediaPlayer mp) {
        return Observable.interval(16, TimeUnit.MILLISECONDS)
                .map(y -> Pair.create(mp.getCurrentPosition(), mp.getDuration()));
    }

    public Observable<MediaPlayer> complete(MediaPlayer player) {
        return Observable.create(subscriber -> player.setOnCompletionListener(mp -> {
            subscriber.onNext(mp);
            subscriber.onCompleted();
        }));
    }
}
