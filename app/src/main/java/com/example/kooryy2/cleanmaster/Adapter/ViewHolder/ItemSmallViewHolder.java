package com.example.kooryy2.cleanmaster.Adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kooryy2.cleanmaster.R;
import com.facebook.ads.AdChoicesView;
import com.facebook.ads.NativeAd;

/**
 * Created by kooryy2 on 3/9/2017.
 */

public class ItemSmallViewHolder extends RecyclerView.ViewHolder {

    public static final int LAYOUT_ID = R.layout.item_discover_small;
    private ImageView app_icon;
    private TextView app_name, app_description;
    private Button btnInstall;
    LinearLayout adChoice;

    public ItemSmallViewHolder(View itemView) {
        super(itemView);
        app_icon = (ImageView) itemView.findViewById(R.id.app_icon);
        app_name = (TextView) itemView.findViewById(R.id.app_name_discory_small);
        app_description = (TextView) itemView.findViewById(R.id.app_title_discover_small);
        btnInstall = (Button) itemView.findViewById(R.id.btnDowload);
        adChoice = (LinearLayout) itemView.findViewById(R.id.imgAdchoice);
    }

    public void bind(NativeAd nativeAd) {
        app_name.setText(nativeAd.getAdTitle());
        app_description.setText(nativeAd.getAdSocialContext());

        NativeAd.Image adIcon = nativeAd.getAdIcon();
        NativeAd.downloadAndDisplayImage(adIcon, app_icon);

        AdChoicesView adChoicesView = new AdChoicesView(itemView.getContext(), nativeAd, true);
        adChoice.removeAllViews();
        adChoice.addView(adChoicesView);
    }
}
