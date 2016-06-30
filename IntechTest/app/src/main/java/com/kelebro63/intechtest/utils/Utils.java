package com.kelebro63.intechtest.utils;

import android.support.v4.media.MediaMetadataCompat;

import com.kelebro63.intechtest.models.Collection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bistrov Alexey on 30.06.2016.
 */
public class Utils {

    static public ArrayList<MediaMetadataCompat> parseMelodyToMediaMetadataCompat(List<Collection> melodies) {
        ArrayList<MediaMetadataCompat> tracks = new ArrayList<>();
        for (Collection melody : melodies) {
            tracks.add(buildFromCollection(melody));
        }
        return tracks;
    }

    static private MediaMetadataCompat buildFromCollection(Collection melody) {
        String title = melody.getTitle();
        String album = melody.getTagList();
        String artist = melody.getUser().getUsername();
        String genre = melody.getGenre();
        String source = melody.getStreamUrl() + "?client_id=4eba3b80bf836b52beab8a357da618bf";
        String iconUrl = melody.getUser().getAvatarUrl();
        int trackNumber = melody.getId();
        // int totalTrackCount = ;
        int duration = melody.getDuration(); // ms

        // Since we don't have a unique ID in the server, we fake one using the hashcode of
        // the music source. In a real world app, this could come from the server.
        String id = String.valueOf(source.hashCode());

        // Adding the music source to the MediaMetadata (and consequently using it in the
        // mediaSession.setMetadata) is not a good idea for a real world music app, because
        // the session metadata can be accessed by notification listeners. This is done in this
        // sample for convenience only.
        //noinspection ResourceType
        return new MediaMetadataCompat.Builder()
                .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, id)
                //.putString(MusicProviderSource.CUSTOM_METADATA_TRACK_SOURCE, source)
                .putString(MediaMetadataCompat.METADATA_KEY_ALBUM, album)
                .putString(MediaMetadataCompat.METADATA_KEY_ARTIST, artist)
                .putLong(MediaMetadataCompat.METADATA_KEY_DURATION, duration)
                .putString(MediaMetadataCompat.METADATA_KEY_GENRE, genre)
                .putString(MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI, iconUrl)
                .putString(MediaMetadataCompat.METADATA_KEY_TITLE, title)
                .putLong(MediaMetadataCompat.METADATA_KEY_TRACK_NUMBER, trackNumber)
                //.putLong(MediaMetadataCompat.METADATA_KEY_NUM_TRACKS, totalTrackCount)
                .build();
    }
}
