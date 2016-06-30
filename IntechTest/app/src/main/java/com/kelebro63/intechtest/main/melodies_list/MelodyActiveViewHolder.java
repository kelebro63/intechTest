package com.kelebro63.intechtest.main.melodies_list;

import android.support.v4.media.MediaMetadataCompat;
import android.view.View;

/**
 * Created by kelebro63 on 02.06.20165.
 */
public class MelodyActiveViewHolder extends MelodyNormalViewHolder {
    public MelodyActiveViewHolder(View itemView) {
        super(itemView);
        itemView.setAlpha(.5f);
        itemView.setClickable(false);
    }

    @Override
    public void bind(MediaMetadataCompat item) {
        super.bind(item);
    }

}
