package com.example.kooryy2.cleanmaster.Fragment;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kooryy2.cleanmaster.Adapter.AdWhiteListAdapter;
import com.example.kooryy2.cleanmaster.CallBack.OnClickRecycleView;
import com.example.kooryy2.cleanmaster.R;
import com.example.kooryy2.cleanmaster.Utils.AppConstants;
import com.example.kooryy2.cleanmaster.Utils.DividerItemDecoration;
import com.example.kooryy2.cleanmaster.Utils.FragmentUtils;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FragmentAddWhiteList extends Fragment {
    @BindView(R.id.recycleViewAdd)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    private List<String> appItemList;
    private AdWhiteListAdapter adapter;

    public FragmentAddWhiteList() {

    }

    public static FragmentAddWhiteList newInstance() {
        FragmentAddWhiteList fragment = new FragmentAddWhiteList();
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
        View rootView = inflater.inflate(R.layout.fragment_add_application, container, false);
        ButterKnife.bind(this, rootView);
        setHasOptionsMenu(true);
        getInstallApp();

        adapter.setOnItemClick(new OnClickRecycleView() {
            @Override
            public void onItemClick(String appInfo, int possition) {
                List<String> saveApp;
                if (Hawk.contains(AppConstants.HAWK_KEY)) {
                    saveApp = Hawk.get(AppConstants.HAWK_KEY);
                } else {
                    saveApp = new ArrayList<>();
                }
                appItemList.remove(possition);
                adapter.notifyDataSetChanged();
//                TranferEvent event = new TranferEvent();
//                event.setAppName(appName);
//                EventBus.getDefault().post(event);
                saveApp.add(appInfo);
                Hawk.put(AppConstants.HAWK_KEY, saveApp);
            }
        });
        toolbar.setTitle("");

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/aguda_bold.otf");
        toolbar_title.setTypeface(typeface);
        toolbar_title.setTextColor(Color.WHITE);
        toolbar_title.setText("Add Application");
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
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
        }
        return super.onOptionsItemSelected(item);
    }

    private void getInstallApp() {
        appItemList = new ArrayList<>();
        List<String> savedAppInfos = Hawk.get("app");
        List<ApplicationInfo> packages = getActivity().getPackageManager().getInstalledApplications(0);
        for (ApplicationInfo appInfo : packages) {
            boolean count = false;
            if (!isSystemApp(appInfo.packageName) && !getActivity().getPackageName().equals(appInfo.packageName)) {
                if (savedAppInfos != null) {
                    for (String savedApp : savedAppInfos) {
                        if (savedApp.equals(appInfo.packageName)) {
                            count = true;
                        }
                    }
                    if (!count) {
                        appItemList.add(appInfo.packageName);
                    }
                } else {
                    appItemList.add(appInfo.packageName);
                }
            }
        }
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AdWhiteListAdapter(getActivity(), appItemList);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), null));
    }

    public boolean isSystemApp(String packageName) {
        try {
            ApplicationInfo applicationInfo = getActivity().getPackageManager().getApplicationInfo(packageName, 0);
            boolean isSystemApp = (applicationInfo.flags
                    & (ApplicationInfo.FLAG_SYSTEM | ApplicationInfo.FLAG_UPDATED_SYSTEM_APP)) != 0;
            return isSystemApp;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

}
