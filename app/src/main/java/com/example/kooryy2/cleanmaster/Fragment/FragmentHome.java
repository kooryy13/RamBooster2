package com.example.kooryy2.cleanmaster.Fragment;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kooryy2.cleanmaster.Adapter.HomeAdapter;
import com.example.kooryy2.cleanmaster.Bean.AppItem;
import com.example.kooryy2.cleanmaster.R;
import com.example.kooryy2.cleanmaster.Utils.FragmentUtils;
import com.jaredrummler.android.processes.AndroidProcesses;
import com.jaredrummler.android.processes.models.AndroidAppProcess;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FragmentHome extends Fragment {
    @BindView(R.id.imgCircleAnim)
    RelativeLayout imgCircleAnim;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recycleView)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    @BindView(R.id.tvNumberProgress)
    TextView tvProcessRunning;
    HomeAdapter homeAdapter;
    List<AppItem> appItemList = new ArrayList<>();
    Animation animation;

    public FragmentHome() {
    }

    public static FragmentHome newInstance() {
        FragmentHome fragment = new FragmentHome();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, rootView);

        toolbar.setTitle("Memory Booster");
        toolbar.setTitleTextColor(Color.WHITE);

        toolbar_title.setVisibility(View.GONE);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        homeAdapter = new HomeAdapter(appItemList, getActivity());
        homeAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(homeAdapter);

        showRunningApp();

        animation = AnimationUtils.loadAnimation(getActivity(),R.anim.rotate_slow_anim_main);
        imgCircleAnim.startAnimation(animation);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_home, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.whiteList:
                FragmentUtils.pushFragment(getActivity(), FragmentWhiteList.newInstance(), null);
                break;
            case R.id.smartLock:
                FragmentUtils.pushFragment(getActivity(), FragmentSmartLockScreen.newInstance(), null);
                break;
            case R.id.aboutUs:
                FragmentUtils.pushFragment(getActivity(), FragmentAbout.newInstance(), null);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void showRunningApp() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                List<AndroidAppProcess> processes = AndroidProcesses.getRunningAppProcesses();
                String packetName;
                for (AndroidAppProcess process : processes) {
                    try {
                        PackageInfo packageInfo = process.getPackageInfo(getActivity(), 0);
                        packetName = packageInfo.packageName;
                        String appName = packageInfo.applicationInfo.loadLabel(getActivity().getPackageManager()).toString();
                        Drawable appIcon = getActivity().getPackageManager().getApplicationIcon(packetName);

                        AppItem appItem = new AppItem();
                        appItem.setAppName(appName);
                        appItem.setPackageName(packetName);
                        appItem.setCapacity(((process.statm().getResidentSetSize() / 1024) / 1024));
                        appItemList.add(appItem);
                    } catch (PackageManager.NameNotFoundException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        tvProcessRunning.setText(String.valueOf(appItemList.size()));
        Log.e("AppRunning", "" + appItemList.size());
    }
}
