package com.example.kooryy2.cleanmaster.Adapter;

import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.kooryy2.cleanmaster.Adapter.ViewHolder.WhiteListViewHolder;
import com.example.kooryy2.cleanmaster.Base.AdapterWithItemClick;
import com.example.kooryy2.cleanmaster.CallBack.OnClickRecycleView;
import com.example.kooryy2.cleanmaster.R;

import java.util.List;

/**
 * Created by kooryy2 on 3/7/2017.
 */

public class WhiteListAdapter extends AdapterWithItemClick<WhiteListViewHolder> {
    private List<String> appItemList;
    private OnClickRecycleView onItemClick;

    public WhiteListAdapter(List<String> appItemList) {
        this.appItemList = appItemList;
    }

    public void setOnItemClick(OnClickRecycleView onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public WhiteListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new WhiteListViewHolder(layoutInflater.inflate(R.layout.item_white_list, parent, false));
    }

    @Override
    public void onBindViewHolder(WhiteListViewHolder holder, int position) {
        try {
            holder.bind(appItemList.get(position), onItemClick);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return appItemList.size();
    }

}
