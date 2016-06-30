package com.kelebro63.intechtest.base;

import android.content.ComponentName;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.View;

import com.kelebro63.intechtest.R;
import com.kelebro63.intechtest.main.melodies_list.control.PlaybackControlsFragment;
import com.kelebro63.intechtest.media.MediaBrowserProvider;
import com.kelebro63.intechtest.media.MusicService;

import java.util.List;

/**
 * Created by Bistrov Alexey on 30.06.2016.
 */
public abstract class PlaybackControlBaseActivity extends BaseToolbarActivity implements MediaBrowserProvider {
    private MediaBrowserCompat mMediaBrowser;
    private PlaybackControlsFragment mControlsFragment;
    private MediaControllerCompat mMediaController;
    private View mControlsContainer;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMediaBrowser = new MediaBrowserCompat(this, new ComponentName(this, MusicService.class), mMediaBrowserConnectionCallback, null);
    }


    @Override
    protected void onStart() {
        super.onStart();

        mControlsContainer = findViewById(R.id.controls_container);
        mControlsFragment = (PlaybackControlsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_playback_controls);

        if (mControlsFragment == null) {
            throw new IllegalStateException("Missing fragment with id 'fragment_playback_controls'");
        }

       // hidePlaybackControls();

        if (mMediaBrowser != null) {
            mMediaBrowser.connect();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mMediaController != null) {
            mMediaController.unregisterCallback(mMediaControllerCallback);
        }
        if (mMediaBrowser != null) {
            mMediaBrowser.disconnect();
        }
    }

    @Override
    public MediaBrowserCompat getMediaBrowser() {
        return mMediaBrowser;
    }

    // Callback that ensures that we are showing the controls
    private final MediaControllerCompat.Callback mMediaControllerCallback =
            new MediaControllerCompat.Callback() {
                @Override
                public void onSessionDestroyed() {
                    super.onSessionDestroyed();
                }

                @Override
                public void onSessionEvent(String event, Bundle extras) {
                    super.onSessionEvent(event, extras);
                }

                @Override
                public void onQueueChanged(List<MediaSessionCompat.QueueItem> queue) {
                    super.onQueueChanged(queue);
                }

                @Override
                public void onQueueTitleChanged(CharSequence title) {
                    super.onQueueTitleChanged(title);
                }

                @Override
                public void onExtrasChanged(Bundle extras) {
                    super.onExtrasChanged(extras);
                }

                @Override
                public void onAudioInfoChanged(MediaControllerCompat.PlaybackInfo info) {
                    super.onAudioInfoChanged(info);
                }

                @Override
                public void binderDied() {
                    super.binderDied();
                }

                @Override
                public void onPlaybackStateChanged(PlaybackStateCompat state) {
//                    if (shouldShowControls()) {
//                        showPlaybackControls();
//                    } else {
//                        LogUtils.d(TAG, "mediaControllerCallback.onPlaybackStateChanged: " + "hiding controls because state is ",
//                                state == null ? "null" : state.getState());
//                        hidePlaybackControls();
//                    }
                }

                @Override
                public void onMetadataChanged(MediaMetadataCompat metadata) {
//                    if (shouldShowControls()) {
//                        showPlaybackControls();
//                    } else {
//                        hidePlaybackControls();
//                    }
                }
            };

    private MediaBrowserCompat.ConnectionCallback mMediaBrowserConnectionCallback =
            new MediaBrowserCompat.ConnectionCallback() {

                @Override
                public void onConnectionSuspended() {
                    super.onConnectionSuspended();
                }

                @Override
                public void onConnectionFailed() {
                    super.onConnectionFailed();
                }

                @Override
                public void onConnected() {

                    MediaSessionCompat.Token token = mMediaBrowser.getSessionToken();
                    if (token == null) {
                        throw new IllegalArgumentException("No Session token");
                    }
       //             connectToSession(token);
                }
            };

}
