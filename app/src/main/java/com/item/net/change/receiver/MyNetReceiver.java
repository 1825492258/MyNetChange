package com.item.net.change.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import com.item.net.change.MainActivity;

/**
 * Created by Jie on 2018/1/3.
 * 广播接收器 监听网络变化
 */

public class MyNetReceiver extends BroadcastReceiver {

    private NetChangeEvent event ;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        // 这个监听包括WiFi和移动数据的打开和关闭
        // 最好用的还是这个监听 但是比较慢
        assert action != null;
        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            int state = NetUtil.getNetWorkState(); // 判断网络是什么类型，0为流量1为wifi
            if(event == null){
                event = MainActivity.event;
            }
            event.onNetChange(state);
        }
    }

    public interface NetChangeEvent {
        void onNetChange(int state);
    }
}
