package com.kelebro63.intechtest.media;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by Bistrov Alexey on 10.06.2016.
 */
public class Player {

    private static Player instanse;
    public MediaPlayer mediaPlayer;
    public int playbackPosition = -1;

    public Player(Context context) {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    public static Player getInstanse(Context context) {
        if (instanse == null) {
            instanse = new Player(context);
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
        instanse = new Player(context);
    }
}
