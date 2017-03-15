package com.example.kooryy2.cleanmaster.Adapter.ViewHolder;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kooryy2.cleanmaster.Base.OnClickViewHolder;
import com.example.kooryy2.cleanmaster.Bean.AppItem;
import com.example.kooryy2.cleanmaster.CallBack.OnClickRecycleView;
import com.example.kooryy2.cleanmaster.R;

import butterknife.OnClick;

/**
 * Created by kooryy2 on 3/13/2017.
 */

public class AddWhiteListViewHolder extends OnClickViewHolder {
    OnClickRecycleView onItemClick;
    private LinearLayout app_add;
    private TextView app_name;
    private ImageView app_icon;
    private String packetName;

    public AddWhiteListViewHolder(View itemView) {
        super(itemView);
        app_icon = (ImageView) itemView.findViewById(R.id.app_icon_add_whitelist);
        app_name = (TextView) itemView.findViewById(R.id.app_name_add_whitelist);
        app_add = (LinearLayout) itemView.findViewById(R.id.linerClickAdd);
    }

    public void bind(String packetName, OnClickRecycleView onItemClick) throws PackageManager.NameNotFoundException {
        this.onItemClick = onItemClick;
        this.packetName = packetName;

        ApplicationInfo appInfo = itemView.getContext().getPackageManager().getApplicationInfo(packetName, 0);
        app_name.setText(itemView.getContext().getPackageManager().getApplicationLabel(appInfo));
        app_icon.setImageDrawable(itemView.getContext().getPackageManager().getApplicationIcon(appInfo));
    }

    @OnClick(R.id.linerClickAdd)
    public void onAddItem() {
        if (onItemClick != null) {
            onItemClick.onItemClick(packetName, getAdapterPosition());
        }
    }

}