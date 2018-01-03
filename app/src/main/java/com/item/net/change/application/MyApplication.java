package com.item.net.change.application;

import android.app.Application;

/**
 * Created by Jie on 2018/1/3.
 */

public class MyApplication extends Application {
    private static MyApplication mApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static MyApplication getInstance() {
        return mApplication;
    }
}
