package com.example.kooryy2.cleanmaster.Adapter;

import android.content.Context;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.kooryy2.cleanmaster.Adapter.ViewHolder.AddWhiteListViewHolder;
import com.example.kooryy2.cleanmaster.Base.AdapterWithItemClick;
import com.example.kooryy2.cleanmaster.Bean.AppItem;
import com.example.kooryy2.cleanmaster.CallBack.OnClickRecycleView;
import com.example.kooryy2.cleanmaster.R;

import java.util.List;

/**
 * Created by kooryy2 on 3/7/2017.
 */

public class AdWhiteListAdapter extends AdapterWithItemClick<AddWhiteListViewHolder> {
    private Context context;
    private List<String> appItemList;
    private OnClickRecycleView onItemClick;

    public AdWhiteListAdapter(Context context, List<String> appItemList) {
        this.context = context;
        this.appItemList = appItemList;
    }

    public void setOnItemClick(OnClickRecycleView onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public AddWhiteListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new AddWhiteListViewHolder(layoutInflater.inflate(R.layout.item_add_application, parent, false));
    }

    @Override
    public void onBindViewHolder(AddWhiteListViewHolder holder, int position) {
        try {
            holder.bind(appItemList.get(position),onItemClick);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return appItemList.size();
    }
}
