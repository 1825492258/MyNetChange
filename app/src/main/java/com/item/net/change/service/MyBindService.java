package com.item.net.change.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyBindService extends Service {

    private static final String TAG = "jiejie";
    private MyBinder mBinder = new MyBinder();

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "bind----onBind");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "bind----onUnBind");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("jiejie", "bind----onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "bind----onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "bind----onDestroy");
    }

    /**
     * 定义一个中间对象
     */
    public class MyBinder extends Binder implements IBindService {

        @Override
        public void callPlay() {
            Log.d(TAG, "-----------callPlay");
        }

        @Override
        public void callPause() {
            Log.d(TAG, "-----------callPause");
        }

        @Override
        public void callRePlay() {
            Log.d(TAG, "-----------callRePlay");
        }
    }
}
