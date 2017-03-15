package com.example.kooryy2.cleanmaster.Fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kooryy2.cleanmaster.R;
import com.example.kooryy2.cleanmaster.Utils.FragmentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class FragmentSmartLockScreen extends Fragment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    @BindView(R.id.btnSwitch)
    SwitchCompat mSwitch;

    public FragmentSmartLockScreen() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FragmentSmartLockScreen newInstance() {
        FragmentSmartLockScreen fragment = new FragmentSmartLockScreen();
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
        View rootView = inflater.inflate(R.layout.fragment_smart_lock_screen, container, false);
        ButterKnife.bind(this, rootView);
        setHasOptionsMenu(true);
        toolbar.setTitle("");

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/aguda_bold.otf");
        toolbar_title.setTypeface(typeface);
        toolbar_title.setTextColor(Color.WHITE);
        toolbar_title.setText("Smart lock screen");

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @OnClick(R.id.btnViewNow)
    public void goHelp() {
        FragmentUtils.pushFragment(getActivity(), FragmentHelp.newInstance(), null, "FragmentHelp");
    }

    @OnCheckedChanged(R.id.btnSwitch)
    public void onCheckSmartLock() {
        if (mSwitch.isChecked()) {
            Toast.makeText(getActivity(), getString(R.string.smart_lock_on), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), getString(R.string.smart_lock_off), Toast.LENGTH_SHORT).show();
        }
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
