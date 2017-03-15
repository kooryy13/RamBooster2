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
import android.widget.TextView;

import com.example.kooryy2.cleanmaster.R;
import com.example.kooryy2.cleanmaster.Utils.FragmentUtils;
import com.example.kooryy2.cleanmaster.Utils.IntenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FragmentAbout extends Fragment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    public FragmentAbout() {
        // Required empty public constructor
    }


    public static FragmentAbout newInstance() {
        FragmentAbout fragment = new FragmentAbout();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about_us, container, false);
        ButterKnife.bind(this, rootView);
        setHasOptionsMenu(true);

        toolbar.setTitle("");
        toolbar.setBackgroundColor(Color.TRANSPARENT);

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/aguda_bold.otf");
        toolbar_title.setTypeface(typeface);
        toolbar_title.setTextColor(Color.WHITE);
        toolbar_title.setText("About");

        return rootView;
    }

    @OnClick(R.id.layout_1)
    public void goPrivacyPolicy() {
        FragmentUtils.pushFragment(getActivity(), FragmentPrivacyPolicy.newInstance(), null);
    }

    @OnClick(R.id.layout_2)
    public void goReview() {
        IntenUtils.openPlayStore(getActivity(), getActivity().getString(R.string.app_name_packet));
    }

    @OnClick(R.id.layout_3)
    public void goShare() {
        IntenUtils.shareFacebook(getActivity(), getActivity().getString(R.string.pls_share), "Memory Booster");
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
