package com.kelebro63.intechtest.main.melodies_list;

import android.view.View;

import com.kelebro63.intechtest.App;
import com.kelebro63.intechtest.models.Melody;

/**
 * Created by kelebro63 on 02.06.20165.
 */
public class MelodyNormalViewHolder extends BaseMelodyViewHolder {

    public MelodyNormalViewHolder(View itemView) {
        super(itemView);
        App.getAppComponent(getContext()).inject(this);
    }

    @Override
    public void bind(Melody item) {

    }


}
