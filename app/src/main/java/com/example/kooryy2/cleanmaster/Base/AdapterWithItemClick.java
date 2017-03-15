package com.example.kooryy2.cleanmaster.Base;

import android.support.v7.widget.RecyclerView;

import com.example.kooryy2.cleanmaster.CallBack.OnRecyclerViewItemClick;

/**
 * Created by kooryy2 on 3/14/2017.
 */
public abstract class AdapterWithItemClick<VH extends OnClickViewHolder> extends RecyclerView.Adapter<VH> {

    OnRecyclerViewItemClick onRecyclerViewItemClick;

    public OnRecyclerViewItemClick getOnRecyclerViewItemClick() {
        return onRecyclerViewItemClick;
    }

    public void setOnRecyclerViewItemClick(OnRecyclerViewItemClick onRecyclerViewItemClick) {
        this.onRecyclerViewItemClick = onRecyclerViewItemClick;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.setOnRecyclerViewItemClick(onRecyclerViewItemClick);
    }
}