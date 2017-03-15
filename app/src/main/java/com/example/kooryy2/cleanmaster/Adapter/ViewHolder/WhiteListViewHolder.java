package com.example.kooryy2.cleanmaster.Adapter.ViewHolder;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kooryy2.cleanmaster.Base.OnClickViewHolder;
import com.example.kooryy2.cleanmaster.CallBack.OnClickRecycleView;
import com.example.kooryy2.cleanmaster.R;

import butterknife.OnClick;

/**
 * Created by kooryy2 on 3/7/2017.
 */

public class WhiteListViewHolder extends OnClickViewHolder {
    private ImageView app_icon;
    private TextView app_name;
    private ImageView delete_app;
    private String packetName;
    private OnClickRecycleView onItemClick;

    public WhiteListViewHolder(View itemView) {
        super(itemView);
        app_icon = (ImageView) itemView.findViewById(R.id.app_icon);
        app_name = (TextView) itemView.findViewById(R.id.app_name);
        delete_app = (ImageView) itemView.findViewById(R.id.delete_app_whitelist);
    }

    public void bind(String packetName, OnClickRecycleView onItemClick) throws PackageManager.NameNotFoundException {
        this.packetName = packetName;
        this.onItemClick = onItemClick;
        ApplicationInfo appInfo = itemView.getContext().getPackageManager().getApplicationInfo(packetName, 0);
        app_name.setText(itemView.getContext().getPackageManager().getApplicationLabel(appInfo));
        app_icon.setImageDrawable(itemView.getContext().getPackageManager().getApplicationIcon(appInfo));
    }

    @OnClick(R.id.delete_app_whitelist)
    public void onDeleteApp() {
        if (onItemClick != null) {
            onItemClick.onItemClick(packetName, getAdapterPosition());
        }
    }
}


