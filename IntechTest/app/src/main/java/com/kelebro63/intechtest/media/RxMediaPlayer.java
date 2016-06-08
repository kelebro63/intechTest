package com.kelebro63.intechtest.media;

/**
 * Created by Bistrov Alexey on 08.06.2016.
 */

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Pair;

import com.kelebro63.intechtest.base.BaseActivity;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.subscriptions.Subscriptions;

public class RxMediaPlayer {
    public static Observable<MediaPlayer> from(String url, BaseActivity activity) {
        return Observable.create(subscriber -> {
            final MediaPlayer player = create();
            try {
                player.setDataSource(url);
                subscriber.onNext(player);
                subscriber.onCompleted();
            } catch (Exception e) {
                e.printStackTrace();
                subscriber.onError(e);
            }
        });
    }

    public static  Observable<Pair<Integer, Integer>> play(MediaPlayer mp) {
        return prepare(mp).flatMap(RxMediaPlayer::stream);
    }

    static MediaPlayer create() {
        final MediaPlayer player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        return player;
    }

    static Observable<MediaPlayer> prepare(MediaPlayer mp) {
        return Observable.create(subscriber -> {
            try {
                mp.prepare();
                subscriber.onNext(mp);
                subscriber.onCompleted();
            } catch (IOException e) {
                mp.reset();
                mp.release();
                subscriber.onError(e);
            }
        });
    }

    static Observable<Pair<Integer, Integer>> stream( MediaPlayer mp) {
        return Observable.create(subscriber -> {
            subscriber.add(Subscriptions.create(() -> {
                if (mp.isPlaying()) {
                    mp.stop();
                }
                mp.reset();
                mp.release();
            }));
            mp.start();
            subscriber.add(ticks(mp)
                    .takeUntil(complete(mp))
                    .subscribe(subscriber));
        });
    }

    static Observable<Pair<Integer, Integer>> ticks(MediaPlayer mp) {
        return Observable.interval(16, TimeUnit.MILLISECONDS)
                .map(y -> Pair.create(mp.getCurrentPosition(), mp.getDuration()));
    }

    static Observable<MediaPlayer> complete(MediaPlayer player) {
        return Observable.create(subscriber -> player.setOnCompletionListener(mp -> {
            subscriber.onNext(mp);
            subscriber.onCompleted();
        }));
    }
}
