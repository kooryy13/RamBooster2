package com.example.kooryy2.cleanmaster;

import android.app.Application;

import com.google.android.exoplayer2.C;
import com.orhanobut.hawk.Hawk;

/**
 * Created by kooryy2 on 3/14/2017.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(this).build();
    }
}
