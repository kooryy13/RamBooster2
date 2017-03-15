package com.example.kooryy2.cleanmaster.Base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.kooryy2.cleanmaster.CallBack.OnRecyclerViewItemClick;

import butterknife.ButterKnife;

/**
 * Created by kooryy2 on 3/14/2017.
 */
public class OnClickViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    OnRecyclerViewItemClick onRecyclerViewItemClick;

    public void setOnRecyclerViewItemClick(OnRecyclerViewItemClick onRecyclerViewItemClick) {
        this.onRecyclerViewItemClick = onRecyclerViewItemClick;
    }

    public OnClickViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (onRecyclerViewItemClick != null) {
            onRecyclerViewItemClick.onItemClick(view, getAdapterPosition());
        }
    }
}