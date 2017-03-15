package com.example.kooryy2.cleanmaster.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.kooryy2.cleanmaster.Adapter.ViewHolder.HomeViewHolder;
import com.example.kooryy2.cleanmaster.Bean.AppItem;
import com.example.kooryy2.cleanmaster.R;

import java.util.List;

/**
 * Created by kooryy2 on 3/10/2017.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder> {
    private List<AppItem> appItemList;
    private Context mContext;

    public HomeAdapter(List<AppItem> appItemList, Context mContext) {
        this.appItemList = appItemList;
        this.mContext = mContext;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new HomeViewHolder(layoutInflater.inflate(R.layout.item_home, parent, false));
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        holder.bind(appItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return appItemList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }


}
