package com.example.kooryy2.cleanmaster.Fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.kooryy2.cleanmaster.R;
import com.example.kooryy2.cleanmaster.Utils.AppConstants;
import com.example.kooryy2.cleanmaster.Utils.FragmentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FragmentPrivacyPolicy extends Fragment {
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;


    public FragmentPrivacyPolicy() {
        // Required empty public constructor
    }

    public static FragmentPrivacyPolicy newInstance() {
        FragmentPrivacyPolicy fragment = new FragmentPrivacyPolicy();
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
        View rootView = inflater.inflate(R.layout.fragment_privacy_poilicy, container, false);
        ButterKnife.bind(this, rootView);
        setHasOptionsMenu(true);
        webView.loadUrl(AppConstants.PRIVACY_URL);
        webView.setHorizontalScrollBarEnabled(false);

        toolbar.setTitle("");

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/aguda_bold.otf");
        toolbar_title.setTypeface(typeface);
        toolbar_title.setTextColor(Color.WHITE);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
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

}
