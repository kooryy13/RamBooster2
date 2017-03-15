package com.example.kooryy2.cleanmaster.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.kooryy2.cleanmaster.Adapter.ViewHolder.ItemBigViewHolder;
import com.example.kooryy2.cleanmaster.Adapter.ViewHolder.ItemSmallViewHolder;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdsManager;

import java.util.List;


/**
 * Created by kooryy2 on 3/9/2017.
 */

public class DiscoveryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int ITEM_TYPE_BIG = 1;
    public static final int ITEM_TYPE_SMALL = 2;

    NativeAdsManager mNativeAd;
    List<NativeAd> nativeAdsList;

    public DiscoveryAdapter(List<NativeAd> nativeAdsList) {
        this.nativeAdsList = nativeAdsList;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == 5) {
            return ITEM_TYPE_BIG;
        } else {
            return ITEM_TYPE_SMALL;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case ITEM_TYPE_BIG:
                return new ItemBigViewHolder(layoutInflater.inflate(ItemBigViewHolder.LAYOUT_ID, parent, false));
            case ITEM_TYPE_SMALL:
                return new ItemSmallViewHolder(layoutInflater.inflate(ItemSmallViewHolder.LAYOUT_ID, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case ITEM_TYPE_BIG:
                ((ItemBigViewHolder)holder).bind(nativeAdsList.get(position));
                break;
            case ITEM_TYPE_SMALL:
                ((ItemSmallViewHolder)holder).bind(nativeAdsList.get(position));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return nativeAdsList.size();
    }
}
