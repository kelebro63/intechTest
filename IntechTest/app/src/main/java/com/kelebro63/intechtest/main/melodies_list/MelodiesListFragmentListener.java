package com.kelebro63.intechtest.main.melodies_list;

import android.support.v4.media.MediaBrowserCompat;

import com.kelebro63.intechtest.media.MediaBrowserProvider;

/**
 * Created by Bistrov Alexey on 30.06.2016.
 */
public interface MelodiesListFragmentListener extends MediaBrowserProvider{
    void onMediaItemSelected(MediaBrowserCompat.MediaItem item);
}
