package com.example.kooryy2.cleanmaster.Adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kooryy2.cleanmaster.R;
import com.facebook.ads.AdChoicesView;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;


/**
 * Created by kooryy2 on 3/9/2017.
 */

public class ItemBigViewHolder extends RecyclerView.ViewHolder {
    public static final int LAYOUT_ID = R.layout.item_discover_big;
    private ImageView app_icon;
    private MediaView imgHeader;
    private TextView app_name, app_description;
    private Button btnInstall;
    LinearLayout adChoicesContainer;

    public ItemBigViewHolder(View itemView) {
        super(itemView);
        app_icon = (ImageView) itemView.findViewById(R.id.app_icon);
        imgHeader = (MediaView) itemView.findViewById(R.id.imgHeaderImageAd);
        app_name = (TextView) itemView.findViewById(R.id.app_name_discory_big);
        app_description = (TextView) itemView.findViewById(R.id.app_title_discover_big);
        btnInstall = (Button) itemView.findViewById(R.id.native_ad_call_to_action);
        adChoicesContainer = (LinearLayout) itemView.findViewById(R.id.ad_choices_container);
    }

    public void bind(NativeAd nativeAd) {
        app_name.setText(nativeAd.getAdTitle());
        app_description.setText(nativeAd.getAdSocialContext());
        imgHeader.setNativeAd(nativeAd);

        NativeAd.Image adIcon = nativeAd.getAdIcon();
        NativeAd.downloadAndDisplayImage(adIcon, app_icon);

        AdChoicesView adChoicesView = new AdChoicesView(itemView.getContext(), nativeAd, true);
        adChoicesContainer.addView(adChoicesView);
    }
}
