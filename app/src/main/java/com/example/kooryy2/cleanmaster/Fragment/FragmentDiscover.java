package com.example.kooryy2.cleanmaster.Fragment;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.kooryy2.cleanmaster.Adapter.DiscoveryAdapter;
import com.example.kooryy2.cleanmaster.R;
import com.example.kooryy2.cleanmaster.Utils.FragmentUtils;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSettings;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdsManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FragmentDiscover extends Fragment {
    @BindView(R.id.recycleView_discover)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    @BindView(R.id.layoutNetworkError)
    FrameLayout layoutError;

    DiscoveryAdapter mAdapter;

    NativeAdsManager manager;

    public FragmentDiscover() {
        // Required empty public constructor
    }

    public static FragmentDiscover newInstance() {
        FragmentDiscover fragment = new FragmentDiscover();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_discover, container, false);
        ButterKnife.bind(this, rootView);
        setHasOptionsMenu(true);

        loadAd();
        toolbar.setTitle("");
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/aguda_bold.otf");
        toolbar_title.setTypeface(typeface);
        toolbar_title.setTextColor(Color.WHITE);
        toolbar_title.setText("Discover");
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                FragmentUtils.popBackStack(getActivity());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadAd() {
        manager = new NativeAdsManager(getActivity(), getString(R.string.placement_id_facebook_ad), 10);
        AdSettings.addTestDevice("5350dbf193dcec7222b05e899ccc3383");
        AdSettings.addTestDevice("945feba86d0850c7681ad88d852f7bfe");
        manager.setListener(new NativeAdsManager.Listener() {
            @Override
            public void onAdsLoaded() {
                List<NativeAd> nativeAdList = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    NativeAd nativeAd = manager.nextNativeAd();
                    if (nativeAd != null) {
                        nativeAdList.add(nativeAd);
                    }
                }
                Log.d("NativeAd Size", "" + nativeAdList.size());
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                mAdapter = new DiscoveryAdapter(nativeAdList);
                recyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onAdError(AdError adError) {
                layoutError.setVisibility(View.VISIBLE);
            }
        });
        manager.loadAds();
    }

}
