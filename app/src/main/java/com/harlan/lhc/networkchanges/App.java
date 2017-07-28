package com.harlan.lhc.networkchanges;

import android.app.Application;

import com.tiny.connect_change.ConnectionChangeInit;

/**
 * Created by a1 on 2017/7/28.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ConnectionChangeInit.init(getApplicationContext());
    }
}
