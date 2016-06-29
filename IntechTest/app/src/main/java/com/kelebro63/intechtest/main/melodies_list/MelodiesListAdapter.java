package com.kelebro63.intechtest.main.melodies_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kelebro63.intechtest.R;
import com.kelebro63.intechtest.base.BaseArrayAdapter;
import com.kelebro63.intechtest.models.Collection;

import java.util.List;

/**
 * Created by kelebro63 on 02.06.2016
 */
public class MelodiesListAdapter extends BaseArrayAdapter<BaseMelodyViewHolder, Collection> {


    private boolean isLoading;

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
            case 4:
                view = LayoutInflater.from(context).inflate(R.layout.melodies_item_footer, parent, false);
                return new MelodiesLoadingFooterViewHolder(view);
        }
        throw new IllegalStateException("View type is not implemented");
    }

//    @Override
//    public int getItemViewType(int position) {
//        Melody item = getItem(position);
//        if (item == null )
//            return 4;
//        switch (item.getItemType()) {
//
//            case NORMAL:
//                return 0;
//            case HEADER:
//                return 1;
//            case DIVIDER:
//                return 2;
//            case ACTIVE:
//                return 3;
//            case PROGRESS:
//                return 4;
//        }
//        return 0;
//    }

    public void displayLoadingFooter() {
        isLoading = true;
        addItem(null);//null item means the progress item
    }

    public boolean isLoading() {
        return isLoading;
    }

    @Override
    public void addItems(List<Collection> newItems) {
        if (!isEmpty()) {
            //remove the progress item
            Collection remove = getItems().remove(getItemCount() - 1);
            if (remove != null) {
            }
        }
        super.addItems(newItems);
        isLoading = false;
    }

    @Override
    public void setItems(List<Collection> items) {
        super.setItems(items);
        isLoading = false;

    }
}
