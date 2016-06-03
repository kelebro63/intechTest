package com.kelebro63.intechtest.main.melodies_list;

import android.view.View;

import com.kelebro63.intechtest.models.Melody;

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
    public void bind(Melody item) {
        super.bind(item);
    }

}
