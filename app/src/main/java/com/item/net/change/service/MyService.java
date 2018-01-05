package com.item.net.change.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Jie on 2018/1/5.
 * Service
 */

public class MyService extends Service {

    private static final String TAG = "jiejie";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "----my----onBind");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "----my----onUnBind");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "----my----onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "-----my-----onStartCommand---");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "-------my---onDestroy");
    }
}
