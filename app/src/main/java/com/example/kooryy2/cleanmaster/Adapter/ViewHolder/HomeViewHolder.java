package com.example.kooryy2.cleanmaster.Adapter.ViewHolder;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kooryy2.cleanmaster.Bean.AppItem;
import com.example.kooryy2.cleanmaster.R;

/**
 * Created by kooryy2 on 3/10/2017.
 */

public class HomeViewHolder extends RecyclerView.ViewHolder {
    private ImageView app_icon;
    private TextView app_size;

    public HomeViewHolder(View itemView) {
        super(itemView);
        app_icon = (ImageView) itemView.findViewById(R.id.app_icon_home);
        app_size = (TextView) itemView.findViewById(R.id.tvAppSize);
    }

    @SuppressLint("DefaultLocale")
    public void bind(AppItem appItem) {
        app_size.setText(String.format("%.1f",appItem.getCapacity()));
    }
}
