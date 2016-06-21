 /*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kelebro63.intechtest.media;

 import android.os.Bundle;
 import android.support.annotation.NonNull;
 import android.support.annotation.Nullable;
 import android.support.v4.media.MediaBrowserCompat;
 import android.support.v4.media.MediaBrowserServiceCompat;
 import android.support.v4.media.session.MediaSessionCompat;
 import android.support.v4.media.session.PlaybackStateCompat;

 import com.kelebro63.intechtest.R;

 import java.util.List;

 public class MusicService extends MediaBrowserServiceCompat implements
         PlaybackManager.PlaybackServiceCallback{


     private MediaSessionCompat mSession;
     private PlaybackManager mPlaybackManager;

     @Override
     public void onCreate() {
         super.onCreate();
         mPlaybackManager = new PlaybackManager(this);
         // Start a new MediaSession
         mSession = new MediaSessionCompat(this, "MusicService");
         setSessionToken(mSession.getSessionToken());
         mSession.setCallback(mPlaybackManager.getMediaSessionCallback());
         mSession.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                 MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);

     }

     @Nullable
     @Override
     public BrowserRoot onGetRoot(@NonNull String clientPackageName, int clientUid, @Nullable Bundle rootHints) {
         return new BrowserRoot(
                 getString(R.string.app_name), // Name visible in Android Auto
                 null);
     }

     @Override
     public void onLoadChildren(@NonNull String parentId, @NonNull Result<List<MediaBrowserCompat.MediaItem>> result) {
         result.sendResult(null);
     }

     @Override
     public void onPlaybackStart() {

     }

     @Override
     public void onNotificationRequired() {

     }

     @Override
     public void onPlaybackStop() {

     }

     @Override
     public void onPlaybackStateUpdated(PlaybackStateCompat newState) {

     }
 }
