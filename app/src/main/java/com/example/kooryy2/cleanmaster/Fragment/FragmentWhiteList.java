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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kooryy2.cleanmaster.Adapter.WhiteListAdapter;
import com.example.kooryy2.cleanmaster.Bean.AppItem;
import com.example.kooryy2.cleanmaster.CallBack.OnClickRecycleView;
import com.example.kooryy2.cleanmaster.R;
import com.example.kooryy2.cleanmaster.Utils.AppConstants;
import com.example.kooryy2.cleanmaster.Utils.FragmentUtils;
import com.example.kooryy2.cleanmaster.bus.TranferEvent;
import com.orhanobut.hawk.Hawk;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentWhiteList extends Fragment {
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar_sub)
    Toolbar toolbar;

    @BindView(R.id.toolbar_title_sub)
    TextView toolbar_title;

    private List<String> appItemList;
    List<AppItem> appIcons;
    WhiteListAdapter mAdapter;

    public FragmentWhiteList() {
    }

    public static FragmentWhiteList newInstance() {
        FragmentWhiteList fragment = new FragmentWhiteList();
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
        View rootView = inflater.inflate(R.layout.fragment_whitelist, container, false);
        ButterKnife.bind(this, rootView);
        setHasOptionsMenu(true);

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/aguda_bold.otf");
        toolbar_title.setTypeface(typeface);
        toolbar_title.setTextColor(Color.WHITE);
        toolbar_title.setText("White list");

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }

    @Override
    public void onResume() {
        if (!Hawk.contains(AppConstants.HAWK_KEY)) {
            appItemList = new ArrayList<>();

        } else {
            appItemList = Hawk.get(AppConstants.HAWK_KEY);
            Log.e("appListReciver", "===========================>" + appItemList.size());
            mAdapter = new WhiteListAdapter(appItemList);
            mAdapter.notifyDataSetChanged();
            mAdapter.setOnItemClick(new OnClickRecycleView() {
                @Override
                public void onItemClick(String appInfo, int possition) {
                    if (Hawk.contains(AppConstants.HAWK_KEY)){
                        List<String> savedApp = Hawk.get(AppConstants.HAWK_KEY);
                        savedApp.remove(appInfo);
                        Hawk.put(AppConstants.HAWK_KEY,appInfo);
                    }
                }
            });
            recyclerView.setAdapter(mAdapter);
        }
        super.onResume();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_whitelist, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_ignore_list:
                FragmentUtils.pushFragment(getActivity(), FragmentAddWhiteList.newInstance(), null);
                break;
            case android.R.id.home:
                FragmentUtils.popBackStack(getActivity());
        }
        return super.onOptionsItemSelected(item);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(TranferEvent event) {
        Log.e("onMessageEvent", "=====> CCCXXXXXX");
        if (event == null)
            return;
        Log.e("onMessageEvent", "=====> CCC");
        String appName = event.getAppName();
        if (appIcons.contains(appName)) {
            Log.e("onMessageEvent", "=====> remove");
            appIcons.remove(appName);
            Log.e("onMessageEvent", "=====> appIcons.size 1= " + appIcons.size());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
