package com.kelebro63.intechtest.main.melodies_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kelebro63.intechtest.R;
import com.kelebro63.intechtest.base.BaseArrayAdapter;
import com.kelebro63.intechtest.models.Melody;

/**
 * Created by kelebro63 on 02.06.2016
 */
public class MelodiesListAdapter extends BaseArrayAdapter<BaseMelodyViewHolder, Melody> {
    @Override
    protected BaseMelodyViewHolder onCreateViewHolder(Context context, ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(context).inflate(R.layout.melodies_normal_item, parent, false);
                return new MelodyNormalViewHolder(view);
            case 1:
                view = LayoutInflater.from(context).inflate(R.layout.melodies_header_item, parent, false);
                return new MelodyHeaderViewHolder(view);
            case 2:
                view = LayoutInflater.from(context).inflate(R.layout.melodies_divider_item, parent, false);
                return new BaseMelodyViewHolder(view);
            case 3:
                view = LayoutInflater.from(context).inflate(R.layout.melodies_normal_item, parent, false);
                return new MelodyActiveViewHolder(view);
        }
        throw new IllegalStateException("View type is not implemented");
    }

    @Override
    public int getItemViewType(int position) {
        Melody item = getItem(position);
        switch (item.getItemType()) {

            case NORMAL:
                return 0;
            case HEADER:
                return 1;
            case DIVIDER:
                return 2;
            case ACTIVE:
                return 3;
        }
        return 0;
    }
}