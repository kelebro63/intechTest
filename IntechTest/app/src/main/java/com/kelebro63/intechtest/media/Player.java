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

    public void start() {
        try {
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void stop() {

    }

    public void pause() {

    }

    public void setSource(String url){
        try {
            mediaPlayer.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
