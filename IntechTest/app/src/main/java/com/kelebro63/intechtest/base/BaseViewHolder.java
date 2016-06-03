package com.kelebro63.intechtest.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by kelebro63 on 02.06.2016
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    protected Context getContext() {
        return itemView.getContext();
    }

    public abstract void bind(T item);
}
