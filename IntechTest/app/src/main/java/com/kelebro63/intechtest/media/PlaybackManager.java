package com.kelebro63.intechtest.media;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;

import com.kelebro63.intechtest.utils.LogHelper;

/**
 * Created by Bistrov Alexey on 20.06.2016.
 */
public class PlaybackManager implements Playback.Callback {

    private MediaSessionCallback mMediaSessionCallback;
    private PlaybackServiceCallback mServiceCallback;
    private Playback mPlayback;
    private QueueManager mQueueManager;

    public PlaybackManager(PlaybackServiceCallback serviceCallback, Playback playback, QueueManager queueManager) {
        mMediaSessionCallback = new MediaSessionCallback();
        mServiceCallback = serviceCallback;
        mPlayback = playback;
        mPlayback.setCallback(this);
        QueueManager = queueManager;
    }

    public MediaSessionCompat.Callback getMediaSessionCallback() {
        return mMediaSessionCallback;
    }

    @Override
    public void onCompletion() {

    }

    @Override
    public void onPlaybackStatusChanged(int state) {

    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void setCurrentMediaId(String mediaId) {

    }

    private class MediaSessionCallback extends MediaSessionCompat.Callback {
        @Override
        public void onPlay() {

        }

        @Override
        public void onSkipToQueueItem(long queueId) {

        }

        @Override
        public void onSeekTo(long position) {

        }

        @Override
        public void onPlayFromMediaId(String mediaId, Bundle extras) {

        }

        @Override
        public void onPause() {

        }

        @Override
        public void onStop() {

        }

        @Override
        public void onSkipToNext() {

        }

        @Override
        public void onSkipToPrevious() {

        }

        @Override
        public void onCustomAction(@NonNull String action, Bundle extras) {

        }

        /**
         * Handle free and contextual searches.
         * <p/>
         * All voice searches on Android Auto are sent to this method through a connected
         * {@link android.support.v4.media.session.MediaControllerCompat}.
         * <p/>
         * Threads and async handling:
         * Search, as a potentially slow operation, should run in another thread.
         * <p/>
         * Since this method runs on the main thread, most apps with non-trivial metadata
         * should defer the actual search to another thread (for example, by using
         * an {@link AsyncTask} as we do here).
         **/
        @Override
        public void onPlayFromSearch(final String query, final Bundle extras) {

        }

        @Override
        public void onPlayFromUri(Uri uri, Bundle extras) {
            super.onPlayFromUri(uri, extras);
        }

        public void handlePlayRequest() {
            MediaSessionCompat.QueueItem currentMusic = mQueueManager.getCurrentMusic();
            if (currentMusic != null) {
                mServiceCallback.onPlaybackStart();
                mPlayback.play(currentMusic);
            }
        }
    }

    public interface PlaybackServiceCallback {
        void onPlaybackStart();

        void onNotificationRequired();

        void onPlaybackStop();

        void onPlaybackStateUpdated(PlaybackStateCompat newState);
    }
}
