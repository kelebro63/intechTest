package com.kelebro63.intechtest.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kelebro63 on 02.06.2016
 */
public abstract class BaseArrayAdapter<VH extends BaseViewHolder<T>, T> extends RecyclerView.Adapter<VH> {
    private List<T> items;
    private OnItemClickListener<T> onItemClickListener;

    public BaseArrayAdapter() {
        items = new ArrayList<>();
    }

    @Override
    public final VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateViewHolder(parent.getContext(), parent, viewType);
    }

    protected abstract VH onCreateViewHolder(Context context, ViewGroup parent, int viewType);

    @Override
    public final void onBindViewHolder(VH holder, int position) {
        holder.bind(getItem(position));
        if (onItemClickListener != null && holder.itemView.isClickable()) {
            holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClicked(getItem(position)));
        }

    }

    @Override
    public final int getItemCount() {
        return items.size();
    }

    public final List<T> getItems() {
        return items;
    }

    public final void setItems(List<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public final void addItem(T item) {
        items.add(item);
        notifyDataSetChanged();
    }

    public final void addItems(List<T> newItems) {
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    public T getItem(int position) {
        return items.get(position);
    }

    public OnItemClickListener<T> getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
