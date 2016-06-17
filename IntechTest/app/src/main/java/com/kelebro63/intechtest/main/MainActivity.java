package com.kelebro63.intechtest.main;

import android.content.ComponentName;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;

import com.kelebro63.intechtest.R;
import com.kelebro63.intechtest.base.BaseToolbarActivity;
import com.kelebro63.intechtest.utils.LogHelper;

import javax.inject.Inject;

/**
 * Created by kelebro63 on 02.06.2016
 */
public class MainActivity extends BaseToolbarActivity implements MediaBrowserProvider{
    @Inject
    MainNavigator navigator;
    @Inject
    MainPresenter presenter;

    private static final String TAG = LogHelper.makeLogTag(MainActivity.class);

    private MediaBrowserCompat mMediaBrowser;

    private final MediaBrowserCompat.ConnectionCallback mConnectionCallback =
            new MediaBrowserCompat.ConnectionCallback() {
                @Override
                public void onConnected() {
                    LogHelper.d(TAG, "onConnected");
                    try {
                        connectToSession(mMediaBrowser.getSessionToken());
                    } catch (RemoteException e) {
                        LogHelper.e(TAG, e, "could not connect media controller");
                        //hidePlaybackControls();
                    }
                }
            };

    @Override
    protected int getLayoutId() {
        return R.layout.container_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createActivityComponent().inject(this);
        presenter.setView(this);
        presenter.determineScreenToShow();
        mMediaBrowser = new MediaBrowserCompat(this,
                new ComponentName(this, MusicService.class), mConnectionCallback, null);
    }

    private void connectToSession(MediaSessionCompat.Token token) throws RemoteException {
        MediaControllerCompat mediaController = new MediaControllerCompat(this, token);
        setSupportMediaController(mediaController);
        mediaController.registerCallback(mMediaControllerCallback);

//        if (shouldShowControls()) {
//            showPlaybackControls();
//        } else {
//            LogHelper.d(TAG, "connectionCallback.onConnected: " +
//                    "hiding controls because metadata is null");
//            hidePlaybackControls();
//        }
//
//        if (mControlsFragment != null) {
//            mControlsFragment.onConnected();
//        }
//
//        onMediaControllerConnected();
    }


    // Callback that ensures that we are showing the controls
    private final MediaControllerCompat.Callback mMediaControllerCallback =
            new MediaControllerCompat.Callback() {
                @Override
                public void onPlaybackStateChanged(@NonNull PlaybackStateCompat state) {
//                    if (shouldShowControls()) {
//                        showPlaybackControls();
//                    } else {
//                        LogHelper.d(TAG, "mediaControllerCallback.onPlaybackStateChanged: " +
//                                "hiding controls because state is ", state.getState());
//                        hidePlaybackControls();
//                    }
                }

                @Override
                public void onMetadataChanged(MediaMetadataCompat metadata) {
//                    if (shouldShowControls()) {
//                        showPlaybackControls();
//                    } else {
//                        LogHelper.d(TAG, "mediaControllerCallback.onMetadataChanged: " +
//                                "hiding controls because metadata is null");
//                        hidePlaybackControls();
//                    }
                }
            };

    @Override
    public MediaBrowserCompat getMediaBrowser() {
        return mMediaBrowser;
    }
}
