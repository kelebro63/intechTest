package com.kelebro63.intechtest.main.melodies_list;

import android.support.annotation.StringRes;
import android.support.v4.media.MediaMetadataCompat;

import com.kelebro63.intechtest.base.IPtrView;

import java.util.ArrayList;

/**
 * Created by kelebro63 on 02.06.2016
 */
public interface IMelodiesView extends IPtrView {
    void addMelodiesToDisplay(ArrayList<MediaMetadataCompat> melodies);

    void setMelodiesToDisplay(ArrayList<MediaMetadataCompat> melodies);

    void displaySimpleError(@StringRes int resId);

    void setDisplayPermissionError(boolean enabled);
}
