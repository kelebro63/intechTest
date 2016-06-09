package com.kelebro63.intechtest.media;

/**
 * Created by Bistrov Alexey on 08.06.2016.
 */

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Pair;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.subscriptions.Subscriptions;

public class RxMediaPlayer { //to do this class singltone in future or provide in DI
    private static MediaPlayer player;
    private static int playbackPosition = 0;

    public static Observable<MediaPlayer> from(String url) {
        if (playbackPosition != 0) {
            return restartPlay();
        } else {
            return Observable.create(subscriber -> {
                MediaPlayer player = get();
                try {
                    player.setDataSource(url);
                    subscriber.onNext(player);
                    subscriber.onCompleted();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
        }
    }

    public static Observable<MediaPlayer> getPlayerObservable() {
        return Observable.create(subscriber -> {
            MediaPlayer player = get();
            subscriber.onNext(player);
            subscriber.onCompleted();
        });
    }

    public static  Observable<Pair<Integer, Integer>> play(MediaPlayer mp) {
        return prepare(mp).flatMap(RxMediaPlayer::stream);
    }

    static MediaPlayer get() {
        if (player != null) {
            return player;
        } else {
            player = new MediaPlayer();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            return player;
        }
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

    public static Observable<MediaPlayer> pause() {
        return Observable.create(subscriber -> {
            MediaPlayer mp = get();
            try {
                if (mp.isPlaying()) {
                    playbackPosition = mp.getCurrentPosition();
                    mp.pause();
                }
            }catch (IllegalStateException e) {
                e.printStackTrace();

            }catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static Observable<MediaPlayer> restartPlay() {
        return Observable.create(subscriber -> {
            MediaPlayer mp = get();
            try {
                if (!mp.isPlaying()) {
                    mp.seekTo(playbackPosition);
                    mp.start();
                }
            }catch (IllegalStateException e) {
                e.printStackTrace();
            }catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static Observable<MediaPlayer> stop() {
        return Observable.create(subscriber -> {
            MediaPlayer mp = get();
            try {
                if (mp.isPlaying()) {
                    playbackPosition = 0;
                    mp.stop();
                    player = null;
                }
            }catch (IllegalStateException e) {
                e.printStackTrace();

            }catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static  void cleanPlayer() {
        player = null;
        playbackPosition = 0;
    }
}
